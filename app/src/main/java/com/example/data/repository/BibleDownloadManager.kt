package com.example.data.repository

import android.content.Context
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap

/** Pinned, per-edition manifest. Book order is supplied by the source, never by the Catholic UI order. */
internal class BibleDownloadManifest(val commit: String, val books: Map<String, List<Book>>) {
    data class Book(val id: String, val file: String, val abbreviation: String, val sha256: String, val bytes: Long, val verseCounts: List<Int>)

    companion object {
        fun load(context: Context): BibleDownloadManifest {
            val root = JSONObject(context.assets.open("bible/download_manifest.json").bufferedReader().use { it.readText() })
            val editions = root.getJSONObject("editions")
            return BibleDownloadManifest(root.getString("sourceCommit"), editions.keys().asSequence().associateWith { id ->
                val array = editions.getJSONArray(id)
                (0 until array.length()).map { index ->
                    val book = array.getJSONObject(index)
                    val counts = book.getJSONArray("verseCounts")
                    Book(book.getString("id"), book.getString("file"), book.getString("abbreviation"),
                        book.getString("sha256"), book.getLong("bytes"), (0 until counts.length()).map { counts.getInt(it) })
                }
            })
        }
    }
}

class BibleDownloadManager internal constructor(
    context: Context,
    private val manifest: BibleDownloadManifest = BibleDownloadManifest.load(context),
    private val transfer: suspend (String, File) -> Unit = { url, destination -> transferBook(url, destination) }
) {
    private val app = context.applicationContext
    data class VersionSpec(val id: String, val name: String, val language: String, val bookCount: Int, val path: String)
    data class DownloadStatus(val completedBooks: Int, val totalBooks: Int) {
        val complete: Boolean get() = totalBooks > 0 && completedBooks == totalBooks
        val partial: Boolean get() = completedBooks > 0 && !complete
    }

    val specs: List<VersionSpec> get() = VERSION_SPECS

    private data class Verified(val size: Long, val modified: Long)
    private val verified = ConcurrentHashMap<String, Verified>()

    /** Reject unknown IDs, including path traversal. Reading status never creates directories. */
    fun versionDir(versionId: String): File {
        require(specs.any { it.id == versionId }) { "Unknown Bible edition" }
        return File(app.filesDir, "bibles/$versionId")
    }

    fun status(versionId: String): DownloadStatus {
        val books = manifest.books[versionId] ?: return DownloadStatus(0, 0)
        return DownloadStatus(books.count { validBook(File(versionDir(versionId), it.file), it) }, books.size)
    }

    fun isDownloaded(versionId: String): Boolean = status(versionId).complete

    fun downloadUrl(spec: VersionSpec, index: Int): String {
        val book = manifest.books.getValue(spec.id)[index - 1]
        return "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/${manifest.commit}/${spec.path}/${book.file}"
    }

    private fun validBook(file: File, expected: BibleDownloadManifest.Book): Boolean {
        if (!file.isFile || file.length() != expected.bytes) return false
        val stamp = Verified(file.length(), file.lastModified())
        if (verified[file.path] == stamp) return true
        val valid = runCatching {
            val digest = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { stream ->
                val buffer = ByteArray(8192)
                while (true) {
                    val count = stream.read(buffer)
                    if (count < 0) break
                    digest.update(buffer, 0, count)
                }
            }
            val actual = digest.digest().joinToString("") { "%02x".format(it.toInt() and 0xff) }
            if (actual != expected.sha256) return@runCatching false
            val book = JSONObject(file.readText())
            val chapters = book.getJSONArray("chapters")
            if (book.getString("abbreviation") != expected.abbreviation || chapters.length() != expected.verseCounts.size) return@runCatching false
            expected.verseCounts.indices.all { c ->
                val chapter = chapters.getJSONObject(c)
                val verses = chapter.getJSONArray("verses")
                chapter.getInt("chapter") == c + 1 && verses.length() == expected.verseCounts[c] &&
                    (0 until verses.length()).all { v ->
                        val verse = verses.getJSONObject(v)
                        verse.getInt("verse") == v + 1 && verse.getString("text").isNotBlank()
                    }
            }
        }.getOrDefault(false)
        if (valid) verified[file.path] = stamp else verified.remove(file.path)
        return valid
    }

    /** A single-edition lock also prevents delete/retry races across repository/provider instances. */
    private fun lock(versionId: String) = locks.getOrPut(versionDir(versionId).path) { Mutex() }

    suspend fun download(versionId: String, onProgress: (Float, String) -> Unit): Boolean = withContext(Dispatchers.IO) {
        val spec = specs.firstOrNull { it.id == versionId } ?: return@withContext false
        lock(versionId).withLock {
            val books = manifest.books.getValue(versionId)
            val dir = versionDir(versionId).also { check(it.isDirectory || it.mkdirs()) }
            try {
                for ((index, book) in books.withIndex()) {
                    currentCoroutineContext().ensureActive()
                    val file = File(dir, book.file)
                    if (!validBook(file, book)) {
                        val part = File(dir, "${book.file}.part")
                        try {
                            var lastError: Exception? = null
                            for (attempt in 1..3) {
                                currentCoroutineContext().ensureActive()
                                try {
                                    part.delete()
                                    transfer(downloadUrl(spec, index + 1), part)
                                    currentCoroutineContext().ensureActive()
                                    if (!validBook(part, book)) throw IOException("Invalid book ${book.file}")
                                    if (!part.renameTo(file)) throw IOException("Could not save ${book.file}")
                                    lastError = null
                                    break
                                } catch (cancelled: CancellationException) { throw cancelled }
                                catch (failure: Exception) { lastError = failure }
                            }
                            lastError?.let { throw it }
                        } finally { part.delete() }
                    }
                    onProgress((index + 1).toFloat() / books.size, "Book ${index + 1} of ${books.size}")
                }
                isDownloaded(versionId)
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) {
                val state = status(versionId)
                onProgress(state.completedBooks.toFloat() / books.size, "Partial download: ${state.completedBooks}/${books.size}. Retry to continue.")
                false
            }
        }
    }

    suspend fun delete(versionId: String): Boolean = withContext(Dispatchers.IO) {
        if (specs.none { it.id == versionId }) return@withContext false
        lock(versionId).withLock {
            val dir = versionDir(versionId)
            val removed = !dir.exists() || dir.deleteRecursively()
            verified.keys.removeAll { it.startsWith(dir.path + File.separator) }
            removed
        }
    }

    /** Reads only verified files, with no silent substitution from another edition. */
    internal fun downloadedBook(versionId: String, bookId: String): JSONObject? {
        if (!isDownloaded(versionId)) return null
        val book = manifest.books[versionId]?.firstOrNull { it.id == bookId } ?: return null
        return runCatching { JSONObject(File(versionDir(versionId), book.file).readText()) }.getOrNull()
    }

    fun bookIds(versionId: String): List<String> = manifest.books[versionId]?.map { it.id }.orEmpty()

    companion object {
        // English Douay-Rheims is already bundled. The remote DRC source was
        // withdrawn after finding duplicate verse labels and misplaced 1 Kings text.
        val VERSION_SPECS = listOf(
            VersionSpec("rv1909", "Reina-Valera 1909 (Spanish)", "es", 66, "Spanish/Reina-Valera-1909"),
            VersionSpec("almeida", "Almeida (Portuguese)", "pt", 66, "Portuguese/Almeida-Portuguese")
        )

        private val locks = ConcurrentHashMap<String, Mutex>()
        private suspend fun transferBook(url: String, destination: File) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connectTimeout = 15_000
            connection.readTimeout = 15_000
            try {
                if (connection.responseCode != HttpURLConnection.HTTP_OK) throw IOException("HTTP ${connection.responseCode}")
                connection.inputStream.use { input ->
                    destination.outputStream().use { output ->
                        val buffer = ByteArray(8192)
                        while (true) {
                            currentCoroutineContext().ensureActive()
                            val count = input.read(buffer)
                            if (count < 0) break
                            output.write(buffer, 0, count)
                        }
                    }
                }
            } finally { connection.disconnect() }
        }
    }
}
