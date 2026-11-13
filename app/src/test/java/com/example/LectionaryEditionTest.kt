package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.liturgy.LectionaryData
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleTextProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class LectionaryEditionTest {
    private val provider = BibleTextProvider(ApplicationProvider.getApplicationContext())
    private val resolver = ScriptureResolver({ book, _ -> book }) { book, chapter, language ->
        provider.getVerses(book, chapter, language)
    }

    /**
     * Published independently: https://bible.usccb.org/bible/readings/032924.cfm
     * Ps 31:2,6,12-13,15-16,17,25 -> Vulgate Ps 30. The bundled English
     * chapter omits the title counted in Spanish/Portuguese, so the English
     * verse indices are one lower. Verify the actual words, not just a number.
     */
    @Test fun goodFridayPsalmSelectsEightVersesInEachEditionWithoutItsTitle() {
        val entry = LectionaryData.fixed.getValue("good_friday")
        val opening = mapOf(AppLanguage.EN to "In thee, O Lord", AppLanguage.ES to "En Ti, Yahvé", AppLanguage.PT to "A ti recorro")
        val commend = mapOf(AppLanguage.EN to "Into thy hands", AppLanguage.ES to "En tus manos", AppLanguage.PT to "Nas tuas mãos")
        for (language in AppLanguage.entries) {
            val selected = resolver.verses(ScriptureReference.parse(entry.psalmFor(language))!!, language)
            assertEquals(language.name, 8, selected.size)
            assertTrue(selected.first().text.get(language).startsWith(opening.getValue(language)))
            assertTrue(selected[1].text.get(language).startsWith(commend.getValue(language)))
        }
    }

    @Test fun goodFridayRangesResolveCompletelyInAllBundledLanguages() {
        val entry = LectionaryData.fixed.getValue("good_friday")
        for (language in AppLanguage.entries) {
            assertEquals(82, resolver.verses(ScriptureReference.parse(entry.gospel)!!, language).size)
            assertEquals(15, resolver.verses(ScriptureReference.parse(entry.firstReading)!!, language).size)
            assertEquals(6, resolver.verses(ScriptureReference.parse(entry.secondReading!!)!!, language).size)
            assertFalse(entry.isCompleteAppointedReadings)
        }
    }
}
