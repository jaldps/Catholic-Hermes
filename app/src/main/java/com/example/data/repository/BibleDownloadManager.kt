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

/**
 * Per-edition manifest (assets/bible/download_manifest.json). An edition is offered only when it is listed here.
 * Book order is supplied by the source, never by the Catholic UI order. Verse numbers are the edition's own and may
 * have gaps (verses a critical edition omits); [Book.verseCounts] holds each chapter's LAST verse number.
 */
internal class BibleDownloadManifest(
    val commit: String,
    val books: Map<String, List<Book>>,
    /** Optional per-edition cross-reference file (xref.bin next to the books). */
    val xref: Map<String, Xref> = emptyMap(),
    /** Optional per-edition base URL (ending in "/") overriding [BibleDownloadManager.BIBLES_DATA_BASE]. */
    val bases: Map<String, String> = emptyMap()
) {
    data class Book(val id: String, val file: String, val abbreviation: String, val sha256: String, val bytes: Long, val verseCounts: List<Int>)
    data class Xref(val sha256: String, val bytes: Long)

    companion object {
        fun load(context: Context): BibleDownloadManifest {
            val root = JSONObject(context.assets.open("bible/download_manifest.json").bufferedReader().use { it.readText() })
            val editions = root.getJSONObject("editions")
            val xrefs = root.optJSONObject("xref")
            val bases = root.optJSONObject("bases")
            return BibleDownloadManifest(root.optString("sourceCommit", ""), editions.keys().asSequence().associateWith { id ->
                val array = editions.getJSONArray(id)
                (0 until array.length()).map { index ->
                    val book = array.getJSONObject(index)
                    val counts = book.getJSONArray("verseCounts")
                    Book(book.getString("id"), book.getString("file"), book.getString("abbreviation"),
                        book.getString("sha256"), book.getLong("bytes"), (0 until counts.length()).map { counts.getInt(it) })
                }
            }, xrefs?.keys()?.asSequence()?.associateWith { id ->
                xrefs.getJSONObject(id).let { Xref(it.getString("sha256"), it.getLong("bytes")) }
            } ?: emptyMap(), bases?.keys()?.asSequence()?.associateWith { bases.getString(it) } ?: emptyMap())
        }
    }
}

class BibleDownloadManager internal constructor(
    context: Context,
    private val manifest: BibleDownloadManifest = BibleDownloadManifest.load(context),
    private val transfer: suspend (String, File) -> Unit = { url, destination -> transferBook(url, destination) }
) {
    private val app = context.applicationContext
    /** [language] is the edition's own (es/en/pt/la); [path] is its folder under the data base URL. */
    data class VersionSpec(val id: String, val name: String, val language: String, val bookCount: Int, val path: String)
    data class DownloadStatus(val completedBooks: Int, val totalBooks: Int) {
        val complete: Boolean get() = totalBooks > 0 && completedBooks == totalBooks
        val partial: Boolean get() = completedBooks > 0 && !complete
    }

    /** Only editions the manifest lists are offered: unpublished ones stay invisible. */
    val specs: List<VersionSpec> = VERSION_SPECS.filter { manifest.books.containsKey(it.id) }

    init { purgeRetiredEditions(app) }

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
        return "${baseUrl(spec)}${spec.path}/${book.file}"
    }

    private fun baseUrl(spec: VersionSpec) = manifest.bases[spec.id] ?: BIBLES_DATA_BASE

    fun xrefUrl(spec: VersionSpec): String = "${baseUrl(spec)}${spec.path}/$XREF_FILE"

    /** The edition's own cross-reference table when it was downloaded and verifies; null falls back to the bundled language one. */
    fun xrefFile(versionId: String): File? {
        val expected = manifest.xref[versionId] ?: return null
        if (specs.none { it.id == versionId }) return null
        val file = File(File(app.filesDir, "bibles/$versionId"), XREF_FILE)
        return file.takeIf { validXref(it, expected) }
    }

    private fun validXref(file: File, expected: BibleDownloadManifest.Xref): Boolean {
        if (!file.isFile || file.length() != expected.bytes) return false
        val stamp = Verified(file.length(), file.lastModified())
        if (verified[file.path] == stamp) return true
        val ok = runCatching { sha256(file) == expected.sha256 }.getOrDefault(false)
        if (ok) verified[file.path] = stamp else verified.remove(file.path)
        return ok
    }

    private fun sha256(file: File): String {
        val digest = MessageDigest.getInstance("SHA-256")
        file.inputStream().use { stream ->
            val buffer = ByteArray(8192)
            while (true) {
                val count = stream.read(buffer)
                if (count < 0) break
                digest.update(buffer, 0, count)
            }
        }
        return digest.digest().joinToString("") { "%02x".format(it.toInt() and 0xff) }
    }

    private fun validBook(file: File, expected: BibleDownloadManifest.Book): Boolean {
        if (!file.isFile || file.length() != expected.bytes) return false
        val stamp = Verified(file.length(), file.lastModified())
        if (verified[file.path] == stamp) return true
        val valid = runCatching {
            val actual = sha256(file)
            if (actual != expected.sha256) return@runCatching false
            val book = JSONObject(file.readText())
            val chapters = book.getJSONArray("chapters")
            if (book.getString("abbreviation") != expected.abbreviation || chapters.length() != expected.verseCounts.size) return@runCatching false
            expected.verseCounts.indices.all { c ->
                val chapter = chapters.getJSONObject(c)
                val verses = chapter.getJSONArray("verses")
                // Verse numbers ascend and may skip omitted verses; the last one is the chapter's length.
                var previous = 0
                chapter.getInt("chapter") == c + 1 && verses.length() > 0 &&
                    (0 until verses.length()).all { v ->
                        val verse = verses.getJSONObject(v)
                        val number = verse.getInt("verse")
                        (number > previous).also { previous = number } && verse.getString("text").isNotBlank()
                    } && previous == expected.verseCounts[c]
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
                fetchXref(spec, dir)
                isDownloaded(versionId)
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) {
                val state = status(versionId)
                onProgress(state.completedBooks.toFloat() / books.size, "Partial download: ${state.completedBooks}/${books.size}. Retry to continue.")
                false
            }
        }
    }

    /** Best effort: without it the reader falls back to the bundled language's cross-references. */
    private suspend fun fetchXref(spec: VersionSpec, dir: File) {
        val expected = manifest.xref[spec.id] ?: return
        val file = File(dir, XREF_FILE)
        if (validXref(file, expected)) return
        val part = File(dir, "$XREF_FILE.part")
        try {
            for (attempt in 1..3) {
                currentCoroutineContext().ensureActive()
                try {
                    part.delete()
                    transfer(xrefUrl(spec), part)
                    if (validXref(part, expected) && part.renameTo(file)) return
                } catch (cancelled: CancellationException) { throw cancelled }
                catch (_: Exception) { }
            }
        } finally { part.delete() }
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
        /** Folder of Catholic Bible Tools conversions; the coordinator fills in the final host and commit. */
        const val BIBLES_DATA_BASE = "https://raw.githubusercontent.com/jaldps/Catholic-Hermes/<commit>/bibles/"
        const val XREF_FILE = "xref.bin"

        /** Editions this build knows how to read; each is offered only if download_manifest.json lists it. */
        val VERSION_SPECS = listOf(
            VersionSpec("es.eldpdd", "El Libro del Pueblo de Dios", "es", 73, "es.eldpdd"),
            VersionSpec("es.lbl", "La Biblia Latinoamericana", "es", 73, "es.lbl"),
            VersionSpec("es.lsb", "La Santa Biblia", "es", 73, "es.lsb"),
            VersionSpec("en.ccb", "Christian Community Bible", "en", 73, "en.ccb"),
            VersionSpec("en.drbcr", "Douay-Rheims (Challoner Revision)", "en", 73, "en.drbcr"),
            VersionSpec("pt.bs", "Bíblia Sagrada", "pt", 73, "pt.bs"),
            VersionSpec("la.nvbse", "Nova Vulgata", "la", 73, "la.nvbse")
        )

        /** Language groups in picker order. */
        val LANGUAGE_ORDER = listOf("es", "en", "pt", "la")

        /** Protestant editions offered before the Catholic-only change; their files are removed. */
        private val RETIRED = listOf("rv1909", "almeida")

        private fun purgeRetiredEditions(context: Context) {
            RETIRED.forEach { runCatching { File(context.filesDir, "bibles/$it").takeIf { dir -> dir.exists() }?.deleteRecursively() } }
        }

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
