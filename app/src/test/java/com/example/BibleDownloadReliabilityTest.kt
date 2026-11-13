package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleDownloadManager
import com.example.data.repository.BibleDownloadManifest
import com.example.data.repository.BibleTextProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import java.io.IOException
import java.security.MessageDigest

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class BibleDownloadReliabilityTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val genesis = """{"abbreviation":"Gen","chapters":[{"chapter":1,"verses":[{"verse":1,"text":"Downloaded Genesis wording"}]}]}"""
    private val john = """{"abbreviation":"John","chapters":[{"chapter":1,"verses":[{"verse":1,"text":"Downloaded John wording"}]}]}"""
    private val sources = mapOf("01-Gen.json" to genesis, "43-John.json" to john)

    private fun book(id: String, file: String, abbreviation: String): BibleDownloadManifest.Book {
        val bytes = sources.getValue(file).toByteArray()
        val hash = MessageDigest.getInstance("SHA-256").digest(bytes).joinToString("") { "%02x".format(it.toInt() and 0xff) }
        return BibleDownloadManifest.Book(id, file, abbreviation, hash, bytes.size.toLong(), listOf(1))
    }
    private val manifest get() = BibleDownloadManifest("pinned-test-commit", mapOf("rv1909" to listOf(
        book("gen", "01-Gen.json", "Gen"), book("joh", "43-John.json", "John")
    )))

    @Test fun interruptedDownloadStaysPartialThenRetryReadsSelectedEditionOffline() = runBlocking {
        File(context.filesDir, "bibles/rv1909").deleteRecursively()
        var failJohn = true
        val requested = mutableListOf<String>()
        val manager = BibleDownloadManager(context, manifest) { url, destination ->
            val name = url.substringAfterLast('/')
            requested += name
            assertTrue(url.contains("pinned-test-commit"))
            if (name == "43-John.json" && failJohn) {
                destination.writeText("truncated")
                throw IOException("Disconnected")
            }
            destination.writeText(sources.getValue(name))
        }
        val provider = BibleTextProvider(context, manager)
        assertFalse(manager.download("rv1909") { _, _ -> })
        assertEquals(1, manager.status("rv1909").completedBooks)
        assertTrue(manager.status("rv1909").partial)
        assertFalse(manager.isDownloaded("rv1909"))
        assertTrue(provider.getVerses("gen", 1, AppLanguage.EN, "rv1909").isEmpty())
        assertFalse(manager.versionDir("rv1909").listFiles().orEmpty().any { it.name.endsWith(".part") })
        failJohn = false
        assertTrue(manager.download("rv1909") { _, _ -> })
        assertEquals(1, requested.count { it == "01-Gen.json" })
        assertEquals("Downloaded John wording", provider.getVerses("joh", 1, AppLanguage.EN, "rv1909").single().text.en)
        assertEquals(1, provider.chapterCount("joh", AppLanguage.EN, "rv1909"))
        assertEquals(0, provider.chapterCount("tob", AppLanguage.EN, "rv1909"))
        assertTrue(provider.getVerses("tob", 1, AppLanguage.EN, "rv1909").isEmpty())
        assertEquals("joh", provider.search("John wording", AppLanguage.EN, editionId = "rv1909").single().first)
        assertTrue(manager.delete("rv1909"))
        assertTrue(provider.getVerses("joh", 1, AppLanguage.EN, "rv1909").isEmpty())
    }

    @Test fun checksumMismatchNeverPromotesACompleteLookingBook() = runBlocking {
        File(context.filesDir, "bibles/rv1909").deleteRecursively()
        var attempts = 0
        val manager = BibleDownloadManager(context, manifest) { url, destination ->
            attempts++
            val bytes = sources.getValue(url.substringAfterLast('/')).replace("Downloaded", "Corrupted!")
            destination.writeText(bytes)
        }
        assertFalse(manager.download("rv1909") { _, _ -> })
        assertEquals(3, attempts)
        assertEquals(0, manager.status("rv1909").completedBooks)
        assertTrue(manager.versionDir("rv1909").listFiles().orEmpty().isEmpty())
    }

    @Test fun cancellationNeverPromotesPartialFileAndCanBeRetried() = runBlocking {
        File(context.filesDir, "bibles/rv1909").deleteRecursively()
        val manager = BibleDownloadManager(context, manifest) { _, destination ->
            destination.writeText("partial")
            throw CancellationException("User cancelled")
        }
        try {
            manager.download("rv1909") { _, _ -> }
            fail("Cancellation must propagate")
        } catch (_: CancellationException) { }
        assertFalse(manager.isDownloaded("rv1909"))
        assertTrue(manager.versionDir("rv1909").listFiles().orEmpty().isEmpty())
    }

    @Test fun pinnedManifestUsesActualEditionBookOrders() {
        val manager = BibleDownloadManager(context)
        val spanish = manager.specs.single { it.id == "rv1909" }
        assertEquals(66, manager.bookIds(spanish.id).size)
        assertFalse(manager.bookIds(spanish.id).contains("tob"))
        assertTrue(manager.downloadUrl(spanish, 17).endsWith("17-Esth.json"))
        assertTrue(manager.bookIds(spanish.id).contains("jud"))
        assertFalse("Unsafe remote DRC must remain withdrawn", manager.specs.any { it.id == "drc" })
        assertTrue(manager.bookIds("drc").isEmpty())
        assertFalse(manager.isDownloaded("drc"))
        // The full Catholic English edition remains available from its bundled asset.
        assertTrue(BibleTextProvider(context).getVerses("tob", 1, AppLanguage.EN).isNotEmpty())
    }

    @Test fun tenLegacyFilesAndMalformedJsonAreNeverComplete() {
        val manager = BibleDownloadManager(context)
        val dir = manager.versionDir("almeida").also { it.deleteRecursively(); it.mkdirs() }
        repeat(16) { File(dir, "${it + 1}.json").writeText("{}") }
        assertFalse(manager.isDownloaded("almeida"))
        assertEquals(0, manager.status("almeida").completedBooks)
        dir.deleteRecursively()
    }
}
