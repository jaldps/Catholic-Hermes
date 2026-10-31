package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The cross-references are keyed to each bundled Bible's own numbering, so a
 * reference must land on the verse whose words it is about, in every language.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class CrossReferenceTest {

    private val repo = CatholicDataRepository(ApplicationProvider.getApplicationContext())

    private fun targets(book: String, ch: Int, v: Int, lang: AppLanguage) =
        repo.crossReferences(book, ch, v, lang).map { it.reference.target.let { t -> "${t.bookId} ${t.chapter}:${t.verse}" } }

    @Test fun genesisOneOneReachesJohnOne() {
        for (lang in AppLanguage.entries) {
            assertTrue("$lang", "joh 1:1" in targets("gen", 1, 1, lang))
        }
    }

    @Test fun johnThreeSixteenIsRichAndPreviewed() {
        val refs = repo.crossReferences("joh", 3, 16, AppLanguage.EN)
        assertTrue(refs.size >= 10)
        assertTrue(refs.all { it.preview.isNotBlank() && it.label.isNotBlank() })
        assertTrue("rom 5:8" in targets("joh", 3, 16, AppLanguage.EN))
    }

    @Test fun shepherdPsalmUsesVulgateNumbering() {
        // Hebrew Psalm 23 is Psalm 22 in these Bibles; "I am the good shepherd" answers it.
        for (lang in AppLanguage.entries) {
            assertTrue("$lang", targets("psa", 22, 1, lang).any { it.startsWith("joh 10:") })
        }
    }

    @Test fun miserereTitleVersesAreSkipped() {
        // KJV Ps 51:1 is Vulgate 50:3 in Spanish and Portuguese (two title verses), 50:2 in the Douay.
        assertTrue(targets("psa", 50, 3, AppLanguage.ES).isNotEmpty())
        assertTrue(targets("psa", 50, 3, AppLanguage.PT).isNotEmpty())
        assertTrue(targets("psa", 50, 2, AppLanguage.EN).isNotEmpty())
    }

    @Test fun referenceLabelsAreLocalized() {
        val es = repo.crossReferences("joh", 3, 16, AppLanguage.ES).first().label
        val en = repo.crossReferences("joh", 3, 16, AppLanguage.EN).first().label
        assertTrue(es, es.contains(","))
        assertTrue(en, en.contains(":"))
    }

    @Test fun bibleLineCoversTheWholeCanon() {
        val line = repo.bibleLine(AppLanguage.ES)!!
        assertEquals(73, line.books.size)
        assertTrue(line.total > 35000)
    }
}
