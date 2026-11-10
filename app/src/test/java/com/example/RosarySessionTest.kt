package com.example

import androidx.compose.runtime.saveable.SaverScope
import com.example.data.models.AppLanguage
import com.example.data.models.RosaryMysteryType
import com.example.data.repository.PrayerAndNovenaData
import com.example.data.repository.RosaryMysterySchedule
import com.example.ui.screens.Devotion
import com.example.ui.screens.MysterySelection
import com.example.ui.screens.PrayerGuide
import com.example.ui.screens.RosaryEvent
import com.example.ui.screens.RosarySession
import com.example.ui.screens.Said
import com.example.ui.screens.StepMap
import com.example.ui.screens.buildMercyGuide
import com.example.ui.screens.buildRosaryGuide
import com.example.ui.screens.countLine
import com.example.ui.screens.firstRequiredAfter
import com.example.ui.screens.whereLine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

/**
 * Pure JVM tests (no Robolectric) pinning the Rosary / Divine Mercy step
 * sequences, the mystery-of-the-day rule, the skip rules and the session
 * reducer. They were written against the pre-refactor behaviour.
 */
class RosarySessionTest {
    private val creed = Said("I believe in God", "Amen.")
    private val today = RosaryMysteryType.JOYFUL

    private fun rosary(type: RosaryMysteryType = RosaryMysteryType.SORROWFUL, lang: AppLanguage = AppLanguage.EN): PrayerGuide {
        val mystery = PrayerAndNovenaData.run {
            when (type) {
                RosaryMysteryType.JOYFUL -> joyfulMysteries
                RosaryMysteryType.SORROWFUL -> sorrowfulMysteries
                RosaryMysteryType.GLORIOUS -> gloriousMysteries
                RosaryMysteryType.LUMINOUS -> luminousMysteries
            }
        }
        return buildRosaryGuide(mystery, creed, lang)
    }

    private fun mercy(lang: AppLanguage = AppLanguage.EN): PrayerGuide =
        buildMercyGuide(creed, Said("Opening prayer text", "Resp"), lang)

    // ------------------------------------------------------------ sequences

    @Test fun rosaryHas85StepsOn61BeadsInSevenSections() {
        val g = rosary()
        assertEquals(85, g.steps.size)
        assertEquals(61, g.beads.size)
        assertEquals(7, g.sections.size)
        assertEquals(5, g.decadeCount)
    }

    @Test fun rosarySectionsStartWhereTheyShould() {
        val g = rosary()
        // Opening 7 steps, then 5 decades of 14, then 8 closing steps.
        assertEquals(listOf(0, 7, 21, 35, 49, 63, 77), g.sections.map { it.firstStep })
        assertEquals(List(7) { 0 }, g.steps.take(7).map { it.section })
        assertEquals(List(14) { 1 }, g.steps.subList(7, 21).map { it.section })
        assertEquals(List(8) { 6 }, g.steps.drop(77).map { it.section })
    }

    @Test fun rosaryOpeningAndClosingOrder() {
        val g = rosary()
        assertEquals(listOf("Sign of the Cross", "Apostles' Creed", "Our Father"), g.steps.take(3).map { it.name })
        assertEquals(listOf(1, 2, 3), g.steps.subList(3, 6).map { it.repetition })
        assertEquals("Sign of the Cross", g.steps.last().name)
        // Every closing step sits on the medal, the last bead.
        assertEquals(setOf(g.beads.lastIndex), g.steps.drop(77).map { it.bead }.toSet())
    }

    @Test fun decadeHasTenHailMarysCountedOneToTen() {
        val g = rosary()
        val hail = g.steps.filter { it.section == 1 && it.count == 10 }
        assertEquals(10, hail.size)
        assertEquals((1..10).toList(), hail.map { it.repetition })
        assertEquals("1 of 10", countLine(hail.first(), AppLanguage.EN))
        assertNull(countLine(g.steps.first(), AppLanguage.EN))
    }

    @Test fun whereLineNamesTheSection() {
        val g = rosary()
        assertEquals("Opening", g.whereLine(g.steps[0], AppLanguage.EN))
        assertEquals("Decade 1 of 5", g.whereLine(g.steps[7], AppLanguage.EN))
        assertEquals("Decade 5 of 5", g.whereLine(g.steps[63], AppLanguage.EN))
        assertEquals("Closing", g.whereLine(g.steps.last(), AppLanguage.EN))
        assertEquals("Década 2 de 5", g.whereLine(g.steps[21], AppLanguage.ES))
    }

    @Test fun mysteryTypeDoesNotChangeShape() {
        for (type in RosaryMysteryType.values()) {
            val g = rosary(type)
            assertEquals(type.name, 85, g.steps.size)
            assertEquals(type.name, 61, g.beads.size)
        }
    }

    @Test fun everyBeadHasAtLeastOneStepAndFirstStepOnBeadIsTheLowest() {
        for (g in listOf(rosary(), mercy())) {
            for (bead in g.beads.indices) {
                val first = g.firstStepOn(bead)
                assertTrue("bead $bead has a step", first >= 0)
                assertEquals(g.steps.indexOfFirst { it.bead == bead }, first)
            }
            // Steps never move backwards along the rail.
            assertEquals(g.steps.map { it.bead }.sorted(), g.steps.map { it.bead })
        }
    }

    @Test fun chapletHas68StepsOn60Beads() {
        val g = mercy()
        assertEquals(68, g.steps.size)
        assertEquals(60, g.beads.size)
        assertEquals(5, g.decadeCount)
        assertEquals("Sign of the Cross", g.steps.first().name)
        assertEquals("Opening prayer", g.steps[1].name)
        assertEquals("Sign of the Cross", g.steps.last().name)
    }

    // ----------------------------------------------------------- skip rules

    @Test fun optionalStepsAreExactlyTheOnesTheGuideFlags() {
        val r = rosary()
        // Litany 1-3, "prayer of the litany" (the "Pray for us" V/R is required).
        assertEquals(r.steps.indices.filter { r.steps[it].optional }.toString(), listOf(78, 79, 80, 82), r.steps.indices.filter { r.steps[it].optional })
        val m = mercy()
        val optional = m.steps.indices.filter { m.steps[it].optional }
        assertEquals(listOf(1, 2, 3, 4), optional.take(4))
        assertEquals("exactly the opening block and the closing prayer", 5, optional.size)
        assertEquals(m.steps.size - 2, optional.last())
    }

    @Test fun skippingTheLitanyLandsOnPrayForUs() {
        val r = rosary()
        val litany = r.steps.indexOfFirst { it.optional }
        val target = r.firstRequiredAfter(litany)!!
        assertFalse(r.steps[target].optional)
        assertEquals("Pray for us", r.steps[target].name)
        // Every optional step of the litany block skips to the same place.
        assertEquals(target, r.firstRequiredAfter(litany + 1))
        assertEquals(target, r.firstRequiredAfter(litany + 2))
    }

    @Test fun skippingTheLitanyPrayerLandsOnTheCollect() {
        val r = rosary()
        val prayer = r.steps.indexOfFirst { it.name.startsWith("Prayer of the litany") }
        assertTrue(r.steps[prayer].optional)
        assertFalse(r.steps[prayer + 1].optional)
        assertEquals(prayer + 1, r.firstRequiredAfter(prayer))
    }

    @Test fun skippingTheChapletOpeningLandsOnTheOurFather() {
        val m = mercy()
        val target = m.firstRequiredAfter(1)!!
        assertFalse(m.steps[target].optional)
        assertEquals("Our Father", m.steps[target].name)
        assertEquals(target, m.firstRequiredAfter(2))
    }

    @Test fun skippingWithNothingRequiredAfterDoesNothing() {
        val onlyOptional = object : StepMap {
            override val stepCount = 3
            override fun isOptional(index: Int) = index >= 1
        }
        assertNull(onlyOptional.firstRequiredAfter(1))
        val s = RosarySession(rosaryStep = 1)
        assertEquals(s, s.reduce(RosaryEvent.SkipOptional, onlyOptional, today))
    }

    @Test fun skipEventMovesTheSessionInTheCurrentDevotion() {
        val m = mercy()
        val s = RosarySession(devotion = Devotion.MERCY, mercyStep = 1, rosaryStep = 9)
        val after = s.reduce(RosaryEvent.SkipOptional, m, today)
        assertEquals(m.firstRequiredAfter(1), after.mercyStep)
        assertEquals(9, after.rosaryStep)
    }

    // ------------------------------------------------------ step progression

    @Test fun nextAndPreviousClampAtTheEnds() {
        val g = rosary()
        var s = RosarySession()
        assertEquals(0, s.reduce(RosaryEvent.Previous, g, today).step)
        s = s.copy(rosaryStep = 84)
        assertEquals(84, s.reduce(RosaryEvent.Next, g, today).step)
        assertEquals(1, RosarySession().reduce(RosaryEvent.Next, g, today).step)
    }

    @Test fun goToIsClampedAndNeverStoredOutOfRange() {
        val g = rosary()
        assertEquals(84, RosarySession().reduce(RosaryEvent.GoTo(999), g, today).rosaryStep)
        assertEquals(0, RosarySession().reduce(RosaryEvent.GoTo(-5), g, today).rosaryStep)
        assertEquals(40, RosarySession().reduce(RosaryEvent.GoTo(40), g, today).rosaryStep)
    }

    @Test fun eachDevotionKeepsItsOwnPosition() {
        val r = rosary()
        val m = mercy()
        var s = RosarySession()
        s = s.reduce(RosaryEvent.Next, r, today).reduce(RosaryEvent.Next, r, today)
        assertEquals(2, s.rosaryStep)
        s = s.reduce(RosaryEvent.SelectDevotion(Devotion.MERCY), m, today)
        assertEquals(0, s.step)
        s = s.reduce(RosaryEvent.Next, m, today)
        assertEquals(1, s.mercyStep)
        s = s.reduce(RosaryEvent.SelectDevotion(Devotion.ROSARY), r, today)
        assertEquals(2, s.step)
        assertEquals(1, s.mercyStep)
    }

    @Test fun finishingADecadeIsTheLastHailMaryToTheNextDecade() {
        val g = rosary()
        // Step 20 is the last of decade 1 (index 7..20), 21 begins decade 2.
        assertTrue(g.finishesDecade(20, 21))
        assertFalse(g.finishesDecade(19, 20))
        assertFalse(g.finishesDecade(21, 20))
        // The opening into decade 1 is not a completed decade; decade 5 into the closing is.
        assertFalse(g.finishesDecade(6, 7))
        assertTrue(g.finishesDecade(76, 77))
        // Jumps (more than one step) are never a completed decade.
        assertFalse(g.finishesDecade(20, 22))
        assertFalse(mercy().finishesDecade(0, 1))
    }

    // ------------------------------------------------------- mystery of the day

    @Test fun weekdaySelectsTheTraditionalSet() {
        val expected = mapOf(
            Calendar.MONDAY to RosaryMysteryType.JOYFUL,
            Calendar.TUESDAY to RosaryMysteryType.SORROWFUL,
            Calendar.WEDNESDAY to RosaryMysteryType.GLORIOUS,
            Calendar.THURSDAY to RosaryMysteryType.LUMINOUS,
            Calendar.FRIDAY to RosaryMysteryType.SORROWFUL,
            Calendar.SATURDAY to RosaryMysteryType.JOYFUL,
            Calendar.SUNDAY to RosaryMysteryType.GLORIOUS
        )
        for ((day, type) in expected) assertEquals("day $day", type, RosaryMysterySchedule.typeFor(day))
        assertEquals(RosaryMysteryType.JOYFUL, RosaryMysterySchedule.typeFor(99))
    }

    @Test fun freshSessionFollowsTheWeekdayUntilThePersonPicks() {
        val r = rosary()
        var s = RosarySession()
        assertEquals(MysterySelection.ByDay, s.mysterySelection)
        assertEquals(RosaryMysteryType.JOYFUL, s.mysterySet(RosaryMysteryType.JOYFUL))
        assertEquals(RosaryMysteryType.LUMINOUS, s.mysterySet(RosaryMysteryType.LUMINOUS))
        s = s.reduce(RosaryEvent.PickMysterySet(RosaryMysteryType.GLORIOUS), r, today)
        // A pick overrides the weekday, whatever the day is.
        assertEquals(RosaryMysteryType.GLORIOUS, s.mysterySet(RosaryMysteryType.JOYFUL))
        assertEquals(RosaryMysteryType.GLORIOUS, s.mysterySet(RosaryMysteryType.LUMINOUS))
    }

    @Test fun pickingADifferentSetRestartsTheRosaryOnly() {
        val r = rosary()
        val s = RosarySession(rosaryStep = 30, mercyStep = 12)
        val after = s.reduce(RosaryEvent.PickMysterySet(RosaryMysteryType.SORROWFUL), r, today)
        assertEquals(0, after.rosaryStep)
        assertEquals(12, after.mercyStep)
    }

    @Test fun pickingTheCurrentSetKeepsThePosition() {
        val r = rosary()
        val s = RosarySession(rosaryStep = 30)
        // Today's set, chosen while still following the weekday.
        assertEquals(30, s.reduce(RosaryEvent.PickMysterySet(today), r, today).rosaryStep)
        // The already picked set, picked again.
        val picked = s.copy(mysterySelection = MysterySelection.Picked(RosaryMysteryType.LUMINOUS))
        assertEquals(30, picked.reduce(RosaryEvent.PickMysterySet(RosaryMysteryType.LUMINOUS), r, today).rosaryStep)
    }

    // ------------------------------------------------------------ instructions

    @Test fun instructionsStayOnAcrossStepsAndDevotions() {
        val r = rosary()
        val m = mercy()
        var s = RosarySession().reduce(RosaryEvent.ToggleInstructions, r, today)
        assertTrue(s.instructionsOn)
        s = s.reduce(RosaryEvent.Next, r, today)
        assertTrue(s.instructionsOn)
        s = s.reduce(RosaryEvent.SelectDevotion(Devotion.MERCY), m, today).reduce(RosaryEvent.Next, m, today)
        assertTrue(s.instructionsOn)
        assertFalse(s.reduce(RosaryEvent.ToggleInstructions, m, today).instructionsOn)
    }

    // ---------------------------------------------------------- tab and saver

    @Test fun initialTabIsPulledIntoRange() {
        assertEquals(Devotion.ROSARY, RosarySession.opening(-3).devotion)
        assertEquals(Devotion.ROSARY, RosarySession.opening(0).devotion)
        assertEquals(Devotion.MERCY, RosarySession.opening(1).devotion)
        assertEquals(Devotion.MERCY, RosarySession.opening(7).devotion)
    }

    @Test fun saverRoundTripsEveryField() {
        val scope = SaverScope { true }
        val cases = listOf(
            RosarySession(),
            RosarySession(
                devotion = Devotion.MERCY,
                mysterySelection = MysterySelection.Picked(RosaryMysteryType.LUMINOUS),
                rosaryStep = 44,
                mercyStep = 17,
                instructionsOn = true
            )
        )
        for (case in cases) {
            val saved = with(RosarySession.Saver) { scope.save(case) }!!
            assertEquals(case, RosarySession.Saver.restore(saved))
        }
    }
}
