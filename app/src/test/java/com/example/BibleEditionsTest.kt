package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.BibleData
import com.example.data.repository.BibleEditionCatalog
import com.example.data.repository.BibleTextProvider
import com.example.data.repository.CatholicDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** All ten Catholic editions ship in the APK: text, cross-references, defaults per language and legacy ids. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class BibleEditionsTest {
    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()
    private val catalog = BibleEditionCatalog(context)
    private val provider = BibleTextProvider(context, catalog)
    private var repository: CatholicDataRepository? = null
    @After fun close() { repository?.close() }

    private fun settle() = runBlocking { withTimeout(10_000) { PersonalDataCommands.awaitIdle() } }

    @Test fun allTenEditionsLoadSeventyThreeBooksAndJohnThreeSixteen() {
        assertEquals(10, catalog.editions.size)
        for (edition in catalog.editions) {
            val missing = BibleData.catholicCanonBooks.filter { provider.chapterCount(it.id, AppLanguage.ES, edition.id) == 0 }.map { it.id }
            assertTrue("${edition.id} lacks books $missing", missing.isEmpty())
            val verse = provider.getVerses("joh", 3, AppLanguage.ES, edition.id).firstOrNull { it.number == 16 }
            assertNotNull("${edition.id} John 3:16", verse)
            assertTrue(edition.id, verse!!.text.es.length > 20)
            assertTrue(edition.id, provider.getVerses("gen", 1, AppLanguage.ES, edition.id).isNotEmpty())
        }
    }

    @Test fun latinJohnOneOneAndOriginalBiblesKeepTheirOwnLanguage() {
        assertTrue(provider.getVerses("joh", 1, AppLanguage.EN, "la.nvbse").first().text.en.contains("In principio erat Verbum"))
        val english = provider.getVerses("joh", 3, AppLanguage.ES, "bundled-en").first { it.number == 16 }.text.es
        assertTrue(english, english.contains("loved the world"))
        assertTrue(provider.search("principio", AppLanguage.EN, 5, "la.nvbse").isNotEmpty())
    }

    @Test fun storedLegacyBundledStillMeansTheAppLanguagesOriginalBible() {
        for (language in AppLanguage.entries) {
            assertEquals(
                provider.getVerses("joh", 3, language, "bundled-${language.code}").map { it.text.get(language) },
                provider.getVerses("joh", 3, language, "bundled").map { it.text.get(language) }
            )
            assertEquals(provider.getVerses("joh", 3, language), provider.getVerses("joh", 3, language, "bundled"))
        }
        val repo = CatholicDataRepository(context).also { repository = it }
        assertEquals("bundled", repo.activeBibleEditionId.value)
        assertEquals("bundled-pt", repo.resolvedEditionId("bundled", AppLanguage.PT))
        repo.selectBibleEdition("bundled")
        assertEquals("bundled", repo.activeBibleEditionId.value)
        repo.selectBibleEdition("jerusalem") // an id this build does not know falls back to the original Bible
        assertEquals("bundled", repo.activeBibleEditionId.value)
    }

    @Test fun everyEditionsOwnCrossReferencesResolveInThatEdition() {
        val repo = CatholicDataRepository(context).also { repository = it }
        for (edition in catalog.editions) {
            val refs = repo.crossReferences("gen", 1, 2, AppLanguage.ES, 60, edition.id)
            assertTrue("${edition.id} Gen 1:2 has references", refs.isNotEmpty())
            for (ref in refs) {
                val t = ref.reference.target
                val verses = provider.getVerses(t.bookId, t.chapter, AppLanguage.ES, edition.id)
                assertTrue("${edition.id} target $t exists", verses.any { it.number == t.verse })
                assertTrue("${edition.id} preview of $t", ref.preview.isNotBlank())
            }
        }
        assertTrue(repo.crossReferenceCounts("gen", 1, AppLanguage.ES, "la.nvbse").isNotEmpty())
        assertNotNull(repo.bibleLine(AppLanguage.ES, "la.nvbse"))
    }

    @Test fun defaultsStartAsTheOriginalBiblesAndPersistPerLanguage() {
        val store = UserPreferencesStore(context)
        val repo = CatholicDataRepository(context, store).also { repository = it }
        assertEquals("bundled-es", repo.defaultBibleEditionFor(AppLanguage.ES))
        assertEquals("bundled-en", repo.defaultBibleEditionFor(AppLanguage.EN))
        assertEquals("bundled-pt", repo.defaultBibleEditionFor(AppLanguage.PT))
        repo.setDefaultBibleEdition(AppLanguage.EN, "la.nvbse")
        repo.setDefaultBibleEdition(AppLanguage.ES, "es.lbl")
        settle()
        assertEquals("la.nvbse", repo.defaultBibleEditionFor(AppLanguage.EN))
        val stored = runBlocking { store.preferences.first() }.bibleDefaultEditions
        assertEquals("la.nvbse", stored["en"])
        assertEquals("es.lbl", stored["es"])
        assertNull(stored["pt"])
        // A new repository over the same store restores them.
        val again = CatholicDataRepository(context, store).also { repository?.close(); repository = it }
        val deadline = System.currentTimeMillis() + 10_000
        while (again.defaultBibleEditionFor(AppLanguage.EN) != "la.nvbse" && System.currentTimeMillis() < deadline) Thread.sleep(50)
        assertEquals("la.nvbse", again.defaultBibleEditionFor(AppLanguage.EN))
        assertEquals("es.lbl", again.defaultBibleEditionFor(AppLanguage.ES))
        assertEquals("bundled-pt", again.defaultBibleEditionFor(AppLanguage.PT))
    }

    @Test fun changingTheAppLanguageOpensThatLanguagesDefaultBible() {
        val repo = CatholicDataRepository(context).also { repository = it }
        repo.setLanguage(AppLanguage.ES)
        repo.setDefaultBibleEdition(AppLanguage.EN, "en.ccb")
        repo.setDefaultBibleEdition(AppLanguage.ES, "es.lsb") // current language: also opens it
        settle()
        assertEquals("es.lsb", repo.activeBibleEditionId.value)
        repo.setLanguage(AppLanguage.EN)
        settle()
        assertEquals("en.ccb", repo.activeBibleEditionId.value)
        repo.setLanguage(AppLanguage.PT)
        settle()
        assertEquals("bundled-pt", repo.resolvedEditionId())
        repo.requestBibleEdition("la.nvbse")
        settle()
        assertEquals("la.nvbse", repo.activeBibleEditionId.value)
        repo.setLanguage(AppLanguage.ES)
        settle()
        assertEquals("es.lsb", repo.activeBibleEditionId.value)
    }
}
