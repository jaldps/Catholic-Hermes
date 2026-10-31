package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.LitanyOfLoreto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** The Litany of Loreto, as taken from the Holy See's texts, in each language. */
class LitanyOfLoretoTest {

    private val languages = listOf(AppLanguage.ES, AppLanguage.EN, AppLanguage.PT)

    @Test
    fun everyLanguageHasTheSameInvocations() {
        // 5 Kyrie lines, 4 to the Trinity, 54 to Our Lady, 3 Agnus Dei.
        for (lang in languages) {
            assertEquals("${lang.code} invocation count", 66, LitanyOfLoreto.lines(lang).size)
        }
    }

    @Test
    fun theOpeningIsRepeatedByThePeople() {
        for (lang in languages) {
            for (line in LitanyOfLoreto.lines(lang).take(5)) {
                assertEquals("${lang.code}: ${line.call}", line.call, line.response)
            }
        }
    }

    @Test
    fun everyMarianInvocationIsAnsweredPrayForUs() {
        val answer = mapOf(
            AppLanguage.ES to "ruega por nosotros.",
            AppLanguage.EN to "pray for us.",
            AppLanguage.PT to "rogai por nós."
        )
        for (lang in languages) {
            val marian = LitanyOfLoreto.lines(lang).subList(9, 63)
            assertTrue(marian.all { it.response == answer[lang] })
            assertTrue("${lang.code} starts with Holy Mary", marian.first().call.startsWith(
                when (lang) { AppLanguage.ES -> "Santa María"; AppLanguage.EN -> "Holy Mary"; AppLanguage.PT -> "Santa Maria" }
            ))
        }
    }

    @Test
    fun theLitanyEndsWithTheLambOfGodAndAPrayer() {
        for (lang in languages) {
            val last = LitanyOfLoreto.lines(lang).takeLast(3)
            assertTrue(last.all { it.call.startsWith("Cordero") || it.call.startsWith("Lamb") || it.call.startsWith("Cordeiro") })
            assertTrue(LitanyOfLoreto.closingPrayer(lang).length > 150)
            assertTrue(LitanyOfLoreto.versicle(lang).response.isNotBlank())
        }
    }
}
