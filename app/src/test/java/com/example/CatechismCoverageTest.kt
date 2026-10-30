package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.CatechismOutline
import com.example.data.repository.CatholicDataRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The Catechism text was always complete, but the hand-written browse tree
 * named only twenty-six paragraphs, so the rest could be reached only by typing
 * a number. These guard the index that replaced it.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class CatechismCoverageTest {

    private val repository =
        CatholicDataRepository(ApplicationProvider.getApplicationContext())

    @Test
    fun theOutlineTilesTheWholeCatechismWithoutGapOrOverlap() {
        val sections = CatechismOutline.sections.sortedBy { it.firstNumber }
        assertEquals(1, sections.first().firstNumber)
        assertEquals(2865, sections.last().lastNumber)
        sections.zipWithNext { a, b ->
            assertEquals(
                "gap or overlap between §${a.lastNumber} and §${b.firstNumber}",
                a.lastNumber + 1,
                b.firstNumber
            )
        }
        assertEquals(2865, CatechismOutline.totalParagraphs)
    }

    @Test
    fun everyParagraphIsReachableFromTheIndex() {
        val unreachable = (1..2865).filter { CatechismOutline.sectionFor(it) == null }
        assertTrue("paragraphs no section covers: ${unreachable.take(20)}", unreachable.isEmpty())
    }

    /**
     * Reaching a paragraph is not enough — it has to carry real text. The
     * article builder falls back to a generic sentence when the bundled content
     * is missing, so this checks against that fallback.
     *
     * Two paragraphs came out of the source incomplete (§2349 has no Spanish or
     * English, §2357 no Portuguese); the builder fills those from a language
     * that does have the text, so none of the 2,865 renders empty.
     */
    @Test
    fun everyParagraphCarriesRealTextInEveryLanguage() {
        val placeholders = mutableListOf<String>()
        for (language in listOf(AppLanguage.ES, AppLanguage.EN, AppLanguage.PT)) {
            for (number in 1..2865) {
                val article = repository.findCatechismArticle(number)
                if (article == null) {
                    placeholders += "§$number missing (${language.code})"
                    continue
                }
                val text = article.content.get(language)
                if (text.isBlank() ||
                    text.startsWith("Enseñanza íntegra de la Iglesia") ||
                    text.startsWith("Official teaching of the Universal") ||
                    text.startsWith("Doutrina oficial da Igreja")
                ) {
                    placeholders += "§$number is placeholder in ${language.code}"
                }
            }
        }
        assertTrue(
            "paragraphs without real text (${placeholders.size}): ${placeholders.take(20)}",
            placeholders.isEmpty()
        )
    }

    @Test
    fun theRepositoryExposesTheWholeIndex() {
        assertEquals(2865, repository.catechismParagraphCount)
        assertEquals(31, repository.catechismOutline.size)
        assertEquals(1324, repository.catechismSectionFor(1324)?.let { 1324 })
        assertTrue(repository.catechismSectionFor(1324)!!.title.en.contains("Eucharist"))
    }
}
