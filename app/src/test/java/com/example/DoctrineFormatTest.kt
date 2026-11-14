package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicQuestionsData
import com.example.data.repository.DevotionsAndHistoryData
import com.example.data.repository.ReligiousOrdersData
import com.example.ui.screens.OfficeGroup
import com.example.ui.screens.centurySpan
import com.example.ui.screens.chronologicalEpochIndices
import com.example.ui.screens.cleanClarification
import com.example.ui.screens.devotionYear
import com.example.ui.screens.eraYear
import com.example.ui.screens.firstSentence
import com.example.ui.screens.isCitationOnly
import com.example.ui.screens.localizedCitation
import com.example.ui.screens.newsDate
import com.example.ui.screens.officeDisplayOrder
import com.example.ui.screens.officeGroup
import com.example.ui.screens.orderMeta
import com.example.ui.screens.parseNumbered
import com.example.ui.screens.questionThemes
import com.example.ui.screens.romanToInt
import com.example.ui.screens.schismInsertPosition
import com.example.ui.screens.splitAlias
import com.example.ui.screens.trackResumeIndex
import com.example.ui.screens.LEARNING_TRACKS
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.TimeZone

/** The rules that turn Doctrine & Study data into display text. */
class DoctrineFormatTest {

    private val es = AppLanguage.ES
    private val en = AppLanguage.EN
    private val pt = AppLanguage.PT

    @Test
    fun clarificationsLoseTheirStrayColon() {
        assertEquals("Esto no demuestra", cleanClarification(": Esto no demuestra"))
        assertEquals("Plain", cleanClarification("Plain"))
        assertEquals("x", cleanClarification("  :   x"))
    }

    @Test
    fun aliasesMoveOutOfTheTitle() {
        val a = splitAlias("Nuestra Señora del Valle (Virgen del Valle)")
        assertEquals("Nuestra Señora del Valle", a.title)
        assertEquals("Virgen del Valle", a.alias)
        assertNull(splitAlias("Nuestra Señora de Guadalupe").alias)
        // A title that is only a parenthetical stays whole.
        assertEquals("(only)", splitAlias("(only)").title)
    }

    @Test
    fun centuriesAreLocalised() {
        val a = centurySpan("Siglos I - IV (Años 33 - 313 d.C.)", es)
        assertEquals("Siglos I–IV", a.centuries)
        assertEquals("33–313", a.years)
        assertEquals("Centuries I–IV", centurySpan("Siglos I - IV (Años 33 - 313 d.C.)", en).centuries)
        assertEquals("Séculos I–IV", centurySpan("Siglos I - IV (Años 33 - 313 d.C.)", pt).centuries)
        val single = centurySpan("Siglos XVI (1517 - 1563)", en)
        assertEquals("Century XVI", single.centuries)
        assertEquals("1517–1563", single.years)
        assertNull(centurySpan("Siglos XVI - XXI", es).years)
        // Unknown shapes survive untouched.
        assertEquals("Edad Media", centurySpan("Edad Media", es).centuries)
    }

    @Test
    fun everyEpochRangeParsesInEveryLanguage() {
        for (epoch in DevotionsAndHistoryData.churchHistoryEpochs) {
            for (lang in AppLanguage.entries) {
                val span = centurySpan(epoch.centuryRange, lang)
                assertTrue("${epoch.epochId} ${lang.code}: ${span.centuries}", !span.centuries.contains("Siglo") || lang == es)
            }
        }
    }

    @Test
    fun epochsReadInChronologicalOrder() {
        val epochs = DevotionsAndHistoryData.churchHistoryEpochs
        val ids = chronologicalEpochIndices(epochs).map { epochs[it].epochId }
        assertEquals(epochs.size, ids.size)
        assertEquals("apostolic", ids.first())
        assertEquals("modern", ids.last())
        assertTrue(ids.indexOf("reformation_trent") < ids.indexOf("baroque_missions"))
        assertEquals(epochs.map { it.epochId }.toSet(), ids.toSet())
    }

    @Test
    fun schismsSitBeforeTheEpochThatFollowsThem() {
        val epochs = DevotionsAndHistoryData.churchHistoryEpochs
        val order = chronologicalEpochIndices(epochs)
        val schisms = DevotionsAndHistoryData.churchSchisms
        val east = schisms.first { it.id == "east_west_1054" }
        val reform = schisms.first { it.id == "reformation_1517" }
        assertEquals("gregorian_reform", epochs[order[schismInsertPosition(east, epochs, order)!!]].epochId)
        assertEquals("reformation_trent", epochs[order[schismInsertPosition(reform, epochs, order)!!]].epochId)
    }

    @Test
    fun yearsGetAnEraOnlyWhenTheyNeedOne() {
        assertEquals("1054", eraYear("1054 d.C.", es))
        assertEquals("AD 33", eraYear("33 d.C.", en))
        assertEquals("33 d. C.", eraYear("33 d.C.", es))
    }

    @Test
    fun marianYearsAreNotLeftInSpanishForOtherLanguages() {
        assertEquals("1531", devotionYear("1531", en))
        assertEquals("AD 40", devotionYear("40 d.C.", en))
        assertEquals("40 d. C.", devotionYear("40 d.C.", es))
        assertEquals("Medieval tradition", devotionYear("Tradición medieval", en))
        assertEquals("Tradição medieval", devotionYear("Tradición medieval", pt))
        assertEquals("9th century (tradition)", devotionYear("Siglo IX (tradición)", en))
        assertEquals("Século IX (tradição)", devotionYear("Siglo IX (tradición)", pt))
        assertEquals("1854 (dogma)", devotionYear("1854 (dogma)", en))
        assertEquals("1392 (Canary Islands)", devotionYear("1392 (Canarias)", en))
        assertEquals("1499 (ancient icon)", devotionYear("1499 (icono milenario)", en))
        for (m in DevotionsAndHistoryData.marianApparitions) {
            val shown = devotionYear(m.year, en)
            assertTrue("${m.id}: $shown", !shown.contains("Siglo") && !shown.contains("Tradición") && !shown.contains("icono"))
        }
    }

    @Test
    fun newsDatesAreRelativeThenAbsolute() {
        val utc = TimeZone.getTimeZone("UTC")
        val published = "Fri, 30 Oct 2026 13:55:00 +0100"
        val t = com.example.ui.screens.parseFeedDate(published)!!
        assertEquals("Hace 2 h", newsDate(published, es, now = t + 2 * 3_600_000L + 60_000L, zone = utc))
        assertEquals("2 h ago", newsDate(published, en, now = t + 2 * 3_600_000L + 60_000L, zone = utc))
        assertEquals("Há 12 min", newsDate(published, pt, now = t + 12 * 60_000L, zone = utc))
        val later = t + 3 * 24 * 3_600_000L
        assertEquals("30 oct", newsDate(published, es, now = later, zone = utc))
        assertEquals("Oct 30", newsDate(published, en, now = later, zone = utc))
        assertEquals("30 out", newsDate(published, pt, now = later, zone = utc))
        assertEquals("", newsDate("not a date", en, now = later, zone = utc))
    }

    @Test
    fun elevenThemesInEveryLanguage() {
        val themes = questionThemes(CatholicQuestionsData.questions)
        assertEquals(11, themes.size)
        assertEquals(101, themes.sumOf { it.indices.size })
        assertEquals(1, themes.count { it.indices.size == 1 })
    }

    @Test
    fun citationLists_arePrintedOnlyAsChips() {
        assertTrue(isCitationOnly("Romanos 8,18-23; Juan 9,1-3; Apocalipsis 21,4. Catecismo, 309-314."))
        assertTrue(isCitationOnly("Romans 8:18-23; 1 Timothy 2:4; Catechism, 293-294 and 2096-2097."))
        assertTrue(!isCitationOnly("Mateo 5,3; véase también la enseñanza de los Padres."))
        for (q in CatholicQuestionsData.questions) {
            // Whatever the verdict, the function must not throw on real data.
            isCitationOnly(q.foundation.es); isCitationOnly(q.foundation.en); isCitationOnly(q.foundation.pt)
        }
    }

    @Test
    fun citationsUseTheLanguagesSeparators() {
        assertEquals("Éxodo 20,2–3", localizedCitation("Éxodo 20, 2–3", es))
        assertEquals("Exodus 20:2–3", localizedCitation("Exodus 20:2–3", en))
        assertEquals("Êxodo 20,2–3", localizedCitation("Êxodo 20:2–3", pt))
    }

    @Test
    fun numberedBlocksSplitIntoSteps() {
        val b = parseNumbered("Pasos para una Santa Confesión:\n1. Examen.\n2. Dolor.\n3. Propósito.")
        assertEquals(listOf("Pasos para una Santa Confesión:"), b.lead)
        assertEquals(listOf("Examen.", "Dolor.", "Propósito."), b.steps)
        assertTrue(parseNumbered("no numbers").steps.isEmpty())
    }

    @Test
    fun firstSentenceStopsAtTheFirstFullStop() {
        assertEquals("La respuesta directa es esta.", firstSentence("La respuesta directa es esta. Y más."))
        assertEquals("Sin punto final", firstSentence("Sin punto final"))
    }

    @Test
    fun everyOfficeHasAGroupAndOrdersDropPlaceholderAbbreviations() {
        val offices = ReligiousOrdersData.offices
        for (o in offices) assertTrue("office ${o.id} has no group", officeGroup(o.id) != OfficeGroup.Other)
        assertEquals(offices.size, officeDisplayOrder(offices.map { it.id }).size)
        assertEquals("OFM · 1209", orderMeta("OFM", "1209"))
        assertEquals("1928", orderMeta("—", "1928"))
        assertNull(orderMeta("", ""))
        assertEquals("OFM", orderMeta("OFM", ""))
    }

    @Test
    fun romanNumeralsRoundTrip() {
        assertEquals(4, romanToInt("IV"))
        assertEquals(16, romanToInt("XVI"))
        assertNull(romanToInt("ABC"))
    }

    @Test
    fun itinerariesResumeAtTheFirstUnreadQuestion() {
        val t = LEARNING_TRACKS[0]
        assertEquals(0, trackResumeIndex(t, emptySet()))
        assertEquals(12, trackResumeIndex(t, (1..12).toSet()))
        // Complete: start again.
        assertEquals(0, trackResumeIndex(t, (t.startQuestion..t.endQuestion).toSet()))
    }
}
