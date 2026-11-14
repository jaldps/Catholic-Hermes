package com.example

import com.example.data.models.LocalizedText
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.data.repository.TemptationGuidesData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TemptationGuidesDataTest {
    private fun check(label: String, t: LocalizedText) {
        assertTrue("$label es blank", t.es.isNotBlank())
        assertTrue("$label en blank", t.en.isNotBlank())
        assertTrue("$label pt blank", t.pt.isNotBlank())
        assertNotEquals("$label es == en", t.es, t.en)
    }

    private fun checkSource(label: String, t: LocalizedText) {
        assertTrue("$label es blank", t.es.isNotBlank())
        assertTrue("$label en blank", t.en.isNotBlank())
        assertTrue("$label pt blank", t.pt.isNotBlank())
    }

    @Test fun everyCatalogKeyHasExactlyOneGuide() {
        val keys = PilgrimageAndHabitsData.sinAvoiderCatalog.map { it.sinKey }
        assertTrue(keys.isNotEmpty())
        keys.forEach { key ->
            assertEquals("guides for $key", 1, TemptationGuidesData.guides.count { it.sinKey == key })
            assertNotNull(TemptationGuidesData.forKey(key))
        }
        assertEquals(keys.toSet(), TemptationGuidesData.guides.map { it.sinKey }.toSet())
    }

    @Test fun guidesAreCompleteAndTrilingual() {
        assertTrue(TemptationGuidesData.guides.isNotEmpty())
        TemptationGuidesData.guides.forEach { g ->
            val k = g.sinKey
            check("$k summary", g.summary)
            check("$k howItWorks", g.howItWorks)
            check("$k whyItIsHard", g.whyItIsHard)
            check("$k sacramentalHelps", g.sacramentalHelps)
            check("$k whenToSeekHelp", g.whenToSeekHelp)
            assertTrue("$k warningSigns ${g.warningSigns.size}", g.warningSigns.size in 4..8)
            assertTrue("$k inTheMoment ${g.inTheMoment.size}", g.inTheMoment.size in 5..8)
            assertTrue("$k dailyRoutine ${g.dailyRoutine.size}", g.dailyRoutine.size in 5..8)
            assertTrue("$k plan ${g.plan.size}", g.plan.size in 4..6)
            assertTrue("$k scripture", g.scripture.size >= 3)
            assertTrue("$k saintsWisdom", g.saintsWisdom.size >= 3)
            assertTrue("$k sources", g.sources.size >= 3)
            g.warningSigns.forEachIndexed { i, t -> check("$k sign$i", t) }
            g.inTheMoment.forEachIndexed { i, t -> check("$k moment$i", t) }
            g.dailyRoutine.forEachIndexed { i, t -> check("$k routine$i", t) }
            g.plan.forEachIndexed { i, ph ->
                check("$k phase$i title", ph.title)
                check("$k phase$i focus", ph.focus)
                assertTrue("$k phase$i days", ph.days.isNotBlank())
                assertTrue("$k phase$i practices", ph.practices.size >= 2)
                ph.practices.forEachIndexed { j, t -> check("$k phase$i practice$j", t) }
            }
            g.scripture.forEachIndexed { i, s ->
                checkSource("$k scripture$i ref", s.reference)
                check("$k scripture$i note", s.note)
            }
            g.saintsWisdom.forEachIndexed { i, c ->
                checkSource("$k saint$i author", c.author)
                check("$k saint$i text", c.text)
                checkSource("$k saint$i source", c.source)
            }
            g.sources.forEachIndexed { i, t -> checkSource("$k source$i", t) }
        }
    }
}
