package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.ChapletCatalog
import com.example.data.repository.ChapletContent
import com.example.data.repository.ChapletGroup
import com.example.data.repository.ChapletIds
import com.example.data.repository.ChapletPrayers
import com.example.data.repository.PrayerAndNovenaData
import com.example.data.repository.PrayerDetailsCatalog
import com.example.ui.components.BeadStyle
import com.example.ui.screens.ChapletPlan
import com.example.ui.screens.buildChapletGuide
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/** The 55 chaplets of assets/chaplets: they load, every cited prayer exists and the steps fit the strings. */
/** The bundled chaplets read straight from the asset folder (the JVM classpath has two `images/credits.json`). */
internal val chapletFileCatalog = ChapletCatalog { name -> File("src/main/assets/chaplets/$name").inputStream() }

class ChapletCatalogTest {
    private val catalog = chapletFileCatalog
    private val prayers = ChapletPrayers.of(PrayerDetailsCatalog.default.mergeInto(PrayerAndNovenaData.vaticanOfficialPrayers))

    @Test fun loadsAll55AndTheIdListEqualsTheFolder() {
        assertEquals(55, ChapletIds.size)
        assertEquals(55, catalog.all.size)
        val onDisk = File("src/main/assets/chaplets").listFiles { f -> f.extension == "json" && f.name != "credits.json" }!!
            .map { it.nameWithoutExtension }.toSet()
        assertEquals(onDisk, ChapletIds.toSet())
    }

    @Test fun groupsHaveTheSpecifiedSizes() {
        val sizes = ChapletGroup.entries.associateWith { catalog.inGroup(it).size }
        assertEquals(mapOf(
            ChapletGroup.GOD_CHRIST to 13, ChapletGroup.MARY to 15, ChapletGroup.SAINTS to 16,
            ChapletGroup.ANGELS to 3, ChapletGroup.DEPARTED to 3, ChapletGroup.INTENTION to 5
        ), sizes)
    }

    @Test fun everyChapletHasItsThreeLanguagesImageAndCredit() {
        catalog.all.forEach { c ->
            AppLanguage.entries.forEach { l ->
                assertTrue("${c.id} title $l", c.title.get(l).isNotBlank())
                assertTrue("${c.id} summary $l", c.summary.get(l).isNotBlank())
            }
            assertNotNull("${c.id} credit", c.credit)
            assertTrue("${c.id} image on disk", File("src/main/assets/${c.imagePath}").isFile)
        }
    }

    @Test fun everyPrayerRefResolves() {
        val missing = catalog.all.flatMap { c ->
            c.steps.mapNotNull { (it.content as? ChapletContent.PrayerRef)?.id }.filter { prayers.find(it) == null }.map { "${c.id}:$it" }
        }
        assertEquals(emptyList<String>(), missing)
    }

    @Test fun stepMapsMatchTheBeads() {
        catalog.all.forEach { c ->
            val plan = ChapletPlan(c)
            assertEquals("${c.id} saying count", c.steps.sumOf { it.repeat }, plan.stepCount)
            assertEquals("${c.id} bead count", c.beads.sumOf { it.count }, plan.beads.size)
            if (!c.hasBeads) {
                assertTrue("${c.id} has no beads", plan.beads.isEmpty())
                return@forEach
            }
            plan.steps.forEach { assertTrue("${c.id} bead ${it.bead}", it.bead in plan.beads.indices) }
            // A step on a bead group never rests on another group's beads.
            plan.steps.forEach { s ->
                val g = c.steps[s.def].bead ?: return@forEach
                assertEquals("${c.id} step on group", g, plan.beads[s.bead].group)
            }
            // A group the steps pass a whole number of times is prayed on every one of its beads.
            c.beads.forEachIndexed { g, group ->
                val said = c.steps.filter { it.bead == g }.sumOf { it.repeat }
                if (said > 0 && said % group.count == 0) {
                    val unused = plan.beads.indices.filter { plan.beads[it].group == g && it in plan.dimmed }
                    assertEquals("${c.id} group $g dimmed beads", emptyList<Int>(), unused)
                }
            }
            // Dimmed beads cannot be picked.
            plan.dimmed.forEach { assertEquals(-1, plan.firstStepOn(it)) }
        }
    }

    @Test fun everyChapletBuildsAGuideInThreeLanguages() {
        catalog.all.forEach { c ->
            AppLanguage.entries.forEach { lang ->
                val g = buildChapletGuide(c, lang, prayers).guide
                assertTrue("${c.id}/$lang steps", g.steps.isNotEmpty())
                g.steps.forEachIndexed { i, s ->
                    assertTrue("${c.id}/$lang step $i has text", s.leader.isNotBlank())
                    assertFalse("${c.id}/$lang step $i leaks a marker", s.leader.contains("{{") || s.response.contains("{{"))
                }
                assertEquals(c.beads.sumOf { it.count }, g.beads.size)
                assertEquals(g.sections.size, g.sections.map { it.index }.distinct().size)
            }
        }
    }

    @Test fun saintAnneHasNoStringAndStMichaelIsTheRaccoltaChaplet() {
        val anne = catalog.get("santa-ana-coronilla")!!
        assertFalse(anne.hasBeads)
        val michael = catalog.get("corona-san-miguel-raccolta")!!
        assertEquals(ChapletGroup.ANGELS, michael.group)
        assertEquals("APP-ST-MICHAEL", michael.sourceId)
    }

    @Test fun forTodayListsTheChapletsOfThatWeekday() {
        assertEquals(listOf("rosario-espiritu-santo"), catalog.forWeekday(7).map { it.id })
        assertTrue(catalog.forWeekday(3).isEmpty())
    }

    @Test fun rubricsAreShownButNeverNarrated() {
        var withRubric = 0
        catalog.all.forEach { c ->
            AppLanguage.entries.forEach { lang ->
                buildChapletGuide(c, lang, prayers).guide.steps.forEach { s ->
                    val rubric = s.chapletRubric ?: return@forEach
                    withRubric++
                    assertFalse("${c.id} narrates its rubric", s.narration.contains(rubric))
                    assertFalse("${c.id} voices its rubric", s.leader.contains(rubric) || s.response.contains(rubric))
                }
            }
        }
        assertTrue("some chaplets carry rubrics", withRubric > 0)
    }

    @Test fun dimmedBeadsAreSkippedByTheReelAndTheMap() {
        val style = BeadStyle(dimmed = setOf(2, 3))
        assertEquals(4, style.step(1, 1, 6))
        assertEquals(1, style.step(4, -1, 6).let { if (it == 1) it else -1 })
        assertEquals(1, style.nearest(2, 6, -1))
        assertEquals(4, style.nearest(2, 6, 1))
        assertEquals(5, BeadStyle.Plain.nearest(5, 6))
        assertEquals(6, BeadStyle.Plain.step(6, 1, 6))
    }
}
