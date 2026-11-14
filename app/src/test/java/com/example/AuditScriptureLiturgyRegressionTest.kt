package com.example

import com.example.data.liturgy.*
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleData
import org.junit.Assert.*
import org.junit.Test

class AuditScriptureLiturgyRegressionTest {
    private fun day(y: Int,m: Int,d: Int) = LiturgicalCalendar.dayInfo(CalendarDate(y,m,d))
    private fun refs(s: String) = parseFoundation(s,AppLanguage.EN,BibleData.catholicCanonBooks)
    private fun gospel(y: Int,m: Int,d: Int): String? = day(y,m,d).let { LectionaryData.entryFor(it.lectionaryKey,it.sundayCycle)?.gospel }

    @Test fun octaveWeekdaysAreSolemnitiesWithProperSelectionsAndNoFridayAbstinence() {
        for (year in listOf(2025,2026,2027,2030)) for (offset in 1..6) {
            val info=LiturgicalCalendar.dayInfo(LiturgicalCalendar.easter(year).plusDays(offset))
            assertEquals(CelebrationRank.SOLEMNITY,info.rank)
            assertEquals(LiturgicalColor.WHITE,info.color)
            assertFalse(info.isAbstinenceDay)
            assertNotNull(LectionaryData.entryFor(info.lectionaryKey,info.sundayCycle))
        }
    }
    @Test fun generalCalendarIncludesMotherOfChurchVisitationAndUniversalMemorials() {
        assertEquals(CelebrationRank.MEMORIAL,day(2027,5,17).rank)
        assertEquals(LiturgicalColor.WHITE,day(2027,5,17).color)
        assertEquals(CelebrationRank.FEAST,day(2027,5,31).rank)
        assertTrue(day(2026,1,28).title.en.contains("Thomas"))
        assertEquals(CelebrationRank.MEMORIAL,day(2026,1,28).rank)
        assertTrue(day(2026,10,7).title.en.contains("Rosary"))
        // The obligatory Monday memorial outranks optional Bernardino on 20 May.
        assertTrue(day(2024,5,20).title.en.contains("Mother of the Church"))
    }
    @Test fun sundayAndLentenPrecedenceDoNotTurnEveryMemorialIntoAnObligation() {
        assertEquals(CelebrationRank.SOLEMNITY,day(2026,11,22).rank)
        assertEquals(CelebrationRank.SUNDAY,day(2027,1,24).rank) // Francis de Sales yields to Sunday.
        assertEquals(CelebrationRank.OPTIONAL_MEMORIAL,day(2027,3,17).rank) // Patrick in Lent.
        assertFalse(day(2026,1,28).isHolyDayOfObligation)
    }
    @Test fun wrongCycleGospelsAreCorrected() {
        assertEquals("joh 19:31-37",gospel(2027,6,4))
        assertEquals("luk 15:3-7",gospel(2025,6,27))
        assertEquals("luk 3:15-16,21-22",gospel(2025,1,12))
        assertEquals("luk 9:28-36",gospel(2025,8,6))
        assertEquals("joh 15:26-27; 16:12-15",gospel(2027,5,16))
    }
    @Test fun memorialCollisionOffersBothOptionsAndPreservesHigherSolemnity() {
        val collision=day(2027,6,5)
        assertEquals(CelebrationRank.OPTIONAL_MEMORIAL,collision.rank)
        assertEquals(2,collision.optionalCelebrations.size)
        assertTrue(collision.title.en.contains("Boniface"))
        assertEquals(CelebrationRank.SOLEMNITY,day(2030,6,29).rank)
        assertTrue(day(2030,6,29).title.en.contains("Peter"))
    }
    @Test fun roseIsAnAlternativeAndSaturdayDoesNotClaimDaytimeVigil() {
        assertEquals(setOf(LiturgicalColor.ROSE,LiturgicalColor.VIOLET),day(2026,12,13).permittedColors)
        assertEquals("Holy Saturday",day(2026,4,4).title.en)
        assertEquals("holy_thursday_evening",day(2026,4,2).lectionaryKey)
    }
    @Test fun humanReferencesPreserveWholeCrossChapterAndDisjointRanges() {
        val cross=(refs("John 18:1-19:42").single() as FoundationRef.BibleRef).reference
        assertEquals(ScriptureReference.parse("joh 18:1-19:42"),cross)
        val disjoint=(refs("Luke 1:46-48,50-55").single() as FoundationRef.BibleRef).reference
        assertEquals(ScriptureReference.parse("luk 1:46-48,50-55"),disjoint)
    }
    @Test fun commonAliasesAndUnicodeDashesAreRecognized() {
        for (s in listOf("1 Cor 13:4-7","1Cor 13:4-7","Ps 23:1","1 Co. 13:4–7")) assertEquals(s,1,refs(s).size)
    }
    @Test fun unsupportedVersePartsAndMalformedRangesAreNeverSilentlyClipped() {
        for (s in listOf("John 3:16b-18","Luke 1:50-48","John 0:1","John 99:1","John 3:16-18b")) assertTrue(s,refs(s).isEmpty())
    }
}
