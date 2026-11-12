package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.DidacheData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** The Didache (clerus.org), 16 chapters in ES/EN/PT. */
class DidacheDataTest {

    private val languages = listOf(AppLanguage.ES, AppLanguage.EN, AppLanguage.PT)

    @Test
    fun sixteenChaptersNumberedInOrder() {
        assertEquals(16, DidacheData.chapters.size)
        assertEquals((1..16).toList(), DidacheData.chapters.map { it.number })
    }

    @Test
    fun everyChapterExistsInEveryLanguage() {
        for (chapter in DidacheData.chapters) {
            for (lang in languages) {
                assertTrue(
                    "${lang.code} ch${chapter.number} heading",
                    chapter.heading.get(lang).isNotBlank()
                )
                assertTrue(
                    "${lang.code} ch${chapter.number} body",
                    chapter.body.get(lang).length > 200
                )
            }
        }
        for (lang in languages) {
            assertTrue("${lang.code} intro", DidacheData.intro.get(lang).isNotBlank())
        }
    }

    @Test
    fun translationsAgreeOnChapterDivisions() {
        // Chapter 1 opens with the Two Ways; chapter 16 ends with the clouds.
        val firstWords = mapOf(
            AppLanguage.ES to "dos caminos",
            AppLanguage.EN to "two Ways",
            AppLanguage.PT to "dois caminhos"
        )
        for (lang in languages) {
            assertTrue(
                "${lang.code} ch1",
                DidacheData.chapters[0].body.get(lang).lowercase()
                    .contains(firstWords[lang]!!.lowercase())
            )
        }
        val lastWords = mapOf(
            AppLanguage.ES to "nubes del cielo",
            AppLanguage.EN to "clouds of heaven",
            AppLanguage.PT to "nuvens do céu"
        )
        for (lang in languages) {
            assertTrue(
                "${lang.code} ch16",
                DidacheData.chapters[15].body.get(lang).lowercase()
                    .contains(lastWords[lang]!!)
            )
        }
    }
}
