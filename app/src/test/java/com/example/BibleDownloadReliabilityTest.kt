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
    private val manifest get() = BibleDownloadManifest("pinned-test-commit", mapOf("es.lbl" to listOf(
        book("gen", "01-Gen.json", "Gen"), book("joh", "43-John.json", "John")
    )), bases = mapOf("es.lbl" to "https://data.example.test/pinned-test-commit/"))

    @Test fun interruptedDownloadStaysPartialThenRetryReadsSelectedEditionOffline() = runBlocking {
        File(context.filesDir, "bibles/es.lbl").deleteRecursively()
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
        assertFalse(manager.download("es.lbl") { _, _ -> })
        assertEquals(1, manager.status("es.lbl").completedBooks)
        assertTrue(manager.status("es.lbl").partial)
        assertFalse(manager.isDownloaded("es.lbl"))
        assertTrue(provider.getVerses("gen", 1, AppLanguage.EN, "es.lbl").isEmpty())
        assertFalse(manager.versionDir("es.lbl").listFiles().orEmpty().any { it.name.endsWith(".part") })
        failJohn = false
        assertTrue(manager.download("es.lbl") { _, _ -> })
        assertEquals(1, requested.count { it == "01-Gen.json" })
        assertEquals("Downloaded John wording", provider.getVerses("joh", 1, AppLanguage.EN, "es.lbl").single().text.en)
        assertEquals(1, provider.chapterCount("joh", AppLanguage.EN, "es.lbl"))
        assertEquals(0, provider.chapterCount("tob", AppLanguage.EN, "es.lbl"))
        assertTrue(provider.getVerses("tob", 1, AppLanguage.EN, "es.lbl").isEmpty())
        assertEquals("joh", provider.search("John wording", AppLanguage.EN, editionId = "es.lbl").single().first)
        assertTrue(manager.delete("es.lbl"))
        assertTrue(provider.getVerses("joh", 1, AppLanguage.EN, "es.lbl").isEmpty())
    }

    @Test fun checksumMismatchNeverPromotesACompleteLookingBook() = runBlocking {
        File(context.filesDir, "bibles/es.lbl").deleteRecursively()
        var attempts = 0
        val manager = BibleDownloadManager(context, manifest) { url, destination ->
            attempts++
            val bytes = sources.getValue(url.substringAfterLast('/')).replace("Downloaded", "Corrupted!")
            destination.writeText(bytes)
        }
        assertFalse(manager.download("es.lbl") { _, _ -> })
        assertEquals(3, attempts)
        assertEquals(0, manager.status("es.lbl").completedBooks)
        assertTrue(manager.versionDir("es.lbl").listFiles().orEmpty().isEmpty())
    }

    @Test fun cancellationNeverPromotesPartialFileAndCanBeRetried() = runBlocking {
        File(context.filesDir, "bibles/es.lbl").deleteRecursively()
        val manager = BibleDownloadManager(context, manifest) { _, destination ->
            destination.writeText("partial")
            throw CancellationException("User cancelled")
        }
        try {
            manager.download("es.lbl") { _, _ -> }
            fail("Cancellation must propagate")
        } catch (_: CancellationException) { }
        assertFalse(manager.isDownloaded("es.lbl"))
        assertTrue(manager.versionDir("es.lbl").listFiles().orEmpty().isEmpty())
    }

    @Test fun editionsAreOfferedOnlyWhenTheManifestListsThem() {
        // The shipped manifest publishes the seven Catholic editions, each with its own cross-reference table and pinned base.
        val shipped = BibleDownloadManager(context)
        assertEquals(BibleDownloadManager.VERSION_SPECS.map { it.id }.toSet(), shipped.specs.map { it.id }.toSet())
        shipped.specs.forEach { assertTrue(it.id, shipped.downloadUrl(it, 1).startsWith("https://raw.githubusercontent.com/jaldps/Catholic-Hermes/a4895797797ae11a9d32b005e03774349420e2df/bibles/${it.path}/")) }
        assertTrue(BibleDownloadManager.VERSION_SPECS.map { it.id }.toSet() ==
            setOf("en.ccb", "en.drbcr", "es.eldpdd", "es.lbl", "es.lsb", "pt.bs", "la.nvbse"))
        assertTrue(BibleDownloadManager.VERSION_SPECS.none { it.id == "rv1909" || it.id == "almeida" })
        val listed = BibleDownloadManager(context, manifest)
        assertEquals(listOf("es.lbl"), listed.specs.map { it.id })
        assertEquals("es", listed.specs.single().language)
        assertFalse(listed.isDownloaded("la.nvbse"))
        assertTrue(listed.bookIds("la.nvbse").isEmpty())
        assertThrows(IllegalArgumentException::class.java) { listed.versionDir("la.nvbse") }
    }

    @Test fun downloadUrlUsesPerEditionBaseOrTheSharedPlaceholder() {
        val listed = BibleDownloadManager(context, manifest)
        val spec = listed.specs.single()
        assertEquals("https://data.example.test/pinned-test-commit/es.lbl/01-Gen.json", listed.downloadUrl(spec, 1))
        val shared = BibleDownloadManager(context, BibleDownloadManifest("c", manifest.books))
        assertEquals(BibleDownloadManager.BIBLES_DATA_BASE + "es.lbl/43-John.json", shared.downloadUrl(spec, 2))
    }

    @Test fun retiredProtestantEditionsAreRemovedFromDevice() {
        for (id in listOf("rv1909", "almeida")) File(context.filesDir, "bibles/$id").apply { mkdirs(); File(this, "01-Gen.json").writeText("{}") }
        File(context.filesDir, "bibles/es.lbl").apply { mkdirs() }
        BibleDownloadManager(context, manifest)
        assertFalse(File(context.filesDir, "bibles/rv1909").exists())
        assertFalse(File(context.filesDir, "bibles/almeida").exists())
        assertTrue(File(context.filesDir, "bibles/es.lbl").exists())
    }

    @Test fun editionKeepsItsOwnVerseNumbersIncludingGaps() = runBlocking {
        File(context.filesDir, "bibles/es.lbl").deleteRecursively()
        val gapped = """{"abbreviation":"Gen","chapters":[{"chapter":1,"verses":[{"verse":1,"text":"Uno"},{"verse":3,"text":"Tres"}]}]}"""
        val bytes = gapped.toByteArray()
        val hash = MessageDigest.getInstance("SHA-256").digest(bytes).joinToString("") { "%02x".format(it.toInt() and 0xff) }
        val one = BibleDownloadManifest("c", mapOf("es.lbl" to listOf(BibleDownloadManifest.Book("gen", "01-Gen.json", "Gen", hash, bytes.size.toLong(), listOf(3)))))
        val manager = BibleDownloadManager(context, one) { _, destination -> destination.writeText(gapped) }
        assertTrue(manager.download("es.lbl") { _, _ -> })
        val provider = BibleTextProvider(context, manager)
        assertEquals(listOf(1, 3), provider.getVerses("gen", 1, AppLanguage.ES, "es.lbl").map { it.number })
        assertEquals(3, provider.verseCounts(AppLanguage.ES, "es.lbl").getValue("gen")[0])
        manager.delete("es.lbl")
        Unit
    }

    @Test fun editionXrefIsDownloadedVerifiedAndRemovedWithTheEdition() = runBlocking {
        File(context.filesDir, "bibles/es.lbl").deleteRecursively()
        val xref = byteArrayOf(1, 2, 3, 4)
        val hash = MessageDigest.getInstance("SHA-256").digest(xref).joinToString("") { "%02x".format(it.toInt() and 0xff) }
        val withXref = BibleDownloadManifest("c", manifest.books, xref = mapOf("es.lbl" to BibleDownloadManifest.Xref(hash, 4)))
        val manager = BibleDownloadManager(context, withXref) { url, destination ->
            if (url.endsWith("/xref.bin")) destination.writeBytes(xref) else destination.writeText(sources.getValue(url.substringAfterLast('/')))
        }
        assertNull(manager.xrefFile("es.lbl"))
        assertTrue(manager.download("es.lbl") { _, _ -> })
        assertNotNull(manager.xrefFile("es.lbl"))
        File(manager.versionDir("es.lbl"), "xref.bin").writeBytes(byteArrayOf(9, 9, 9, 9))
        assertNull("a corrupted xref is never used", manager.xrefFile("es.lbl"))
        manager.delete("es.lbl")
        assertNull(manager.xrefFile("es.lbl"))
        Unit
    }
}
