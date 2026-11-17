package com.example.data.backup

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.security.GeneralSecurityException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/** Version 1: CHBACKUP | version (u32 BE) | iterations (u32 BE) | salt[16] | IV[12] | GCM ciphertext/tag. */
class BackupCodec(private val random: SecureRandom = SecureRandom()) {
    companion object {
        const val FORMAT_VERSION = 1
        const val ITERATIONS = 310_000
        const val MAX_BYTES = 32 * 1024 * 1024
        internal const val HEADER_BYTES = 44
        private val MAGIC = "CHBACKUP".toByteArray(StandardCharsets.US_ASCII)
        private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        internal val adapter = moshi.adapter(BackupData::class.java).failOnUnknown()

        /** Bound the stream before allocating or deriving a key from an untrusted file. */
        fun read(input: InputStream): ByteArray {
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(8192)
            while (true) {
                val n = input.read(buffer)
                if (n < 0) break
                if (n == 0) continue
                if (out.size() + n > MAX_BYTES + HEADER_BYTES + 16) throw BackupException(BackupError.TOO_LARGE)
                out.write(buffer, 0, n)
            }
            return out.toByteArray()
        }

        /** JCA on supported devices; the standard HMAC construction also supports Android 24–25. */
        internal fun derive(password: CharArray, salt: ByteArray, iterations: Int, usePortable: Boolean = false): ByteArray {
            if (!usePortable) {
                val spec = PBEKeySpec(password, salt, iterations, 256)
                try { return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).encoded }
                catch (_: NoSuchAlgorithmException) { /* Older Android providers: HmacSHA256 exists since API 1. */ }
                finally { spec.clearPassword() }
            }
            val encoded = StandardCharsets.UTF_8.encode(java.nio.CharBuffer.wrap(password))
            val bytes = ByteArray(encoded.remaining()).also { encoded.get(it) }
            if (encoded.hasArray()) encoded.array().fill(0)
            try {
                val mac = Mac.getInstance("HmacSHA256")
                mac.init(SecretKeySpec(bytes, "HmacSHA256"))
                var u = mac.doFinal(salt + byteArrayOf(0, 0, 0, 1))
                val result = u.copyOf()
                repeat(iterations - 1) {
                    val next = mac.doFinal(u)
                    u.fill(0); u = next
                    for (i in result.indices) result[i] = (result[i].toInt() xor u[i].toInt()).toByte()
                }
                u.fill(0)
                return result
            } finally { bytes.fill(0) }
        }
    }

    fun encrypt(data: BackupData, password: CharArray): ByteArray {
        checkPassword(password)
        BackupValidation.validate(data)
        val clear = adapter.toJson(data).toByteArray(StandardCharsets.UTF_8)
        try {
            if (clear.size > MAX_BYTES) throw BackupException(BackupError.TOO_LARGE)
            val salt = ByteArray(16).also(random::nextBytes)
            val iv = ByteArray(12).also(random::nextBytes)
            val header = ByteArrayOutputStream().apply {
                DataOutputStream(this).use { out -> out.write(MAGIC); out.writeInt(FORMAT_VERSION); out.writeInt(ITERATIONS); out.write(salt); out.write(iv) }
            }.toByteArray()
            val key = derive(password, salt, ITERATIONS)
            try {
                val cipher = Cipher.getInstance("AES/GCM/NoPadding")
                cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(128, iv))
                cipher.updateAAD(header)
                return header + cipher.doFinal(clear)
            } finally { key.fill(0) }
        } finally { clear.fill(0) }
    }

    fun decrypt(file: ByteArray, password: CharArray): PreparedBackup {
        checkPassword(password)
        if (file.size > MAX_BYTES + HEADER_BYTES + 16) throw BackupException(BackupError.TOO_LARGE)
        if (file.size < HEADER_BYTES + 16) throw BackupException(BackupError.INVALID_FILE)
        val input = DataInputStream(file.inputStream())
        val magic = ByteArray(8).also(input::readFully)
        if (!magic.contentEquals(MAGIC)) throw BackupException(BackupError.INVALID_FILE)
        if (input.readInt() != FORMAT_VERSION) throw BackupException(BackupError.UNSUPPORTED_VERSION)
        val iterations = input.readInt()
        // Avoid resource exhaustion from malicious KDF work factors.
        if (iterations !in 210_000..1_000_000) throw BackupException(BackupError.INVALID_FILE)
        val salt = ByteArray(16).also(input::readFully)
        val iv = ByteArray(12).also(input::readFully)
        val key = derive(password, salt, iterations)
        val clear = try {
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(128, iv))
            cipher.updateAAD(file.copyOfRange(0, HEADER_BYTES))
            cipher.doFinal(file, HEADER_BYTES, file.size - HEADER_BYTES)
        } catch (e: GeneralSecurityException) {
            throw BackupException(BackupError.WRONG_PASSWORD_OR_DAMAGED, e)
        } finally { key.fill(0) }
        try {
            val data = try { adapter.fromJson(String(clear, StandardCharsets.UTF_8)) }
                catch (e: Exception) { throw BackupException(BackupError.INVALID_FILE, e) }
                ?: throw BackupException(BackupError.INVALID_FILE)
            BackupValidation.validate(data)
            return PreparedBackup(BackupMerge.canonicalDays(data))
        } finally { clear.fill(0) }
    }

    private fun checkPassword(password: CharArray) {
        if (password.isEmpty() || password.size > 1024) throw BackupException(BackupError.PASSWORD_REQUIRED)
    }
}
