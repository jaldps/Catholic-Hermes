package com.example

import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.data.repository.ChapletPrayers
import com.example.ui.screens.ChapletVariables
import com.example.ui.screens.buildChapletGuide
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** Owner's correction: Saint Rita is a guided five-decade rosary, one full prayer per bead. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class SantaRitaRosaryGuideTest {
    private fun guide(lang: AppLanguage) = run {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        ChapletCatalog.install(context)
        val chaplet = ChapletCatalog.default.all.first { it.id == "santa-rita-coronilla" }
        val prayers = ChapletPrayers.of(CatholicDataRepository(context).vaticanPrayers)
        buildChapletGuide(chaplet, lang, prayers, ChapletVariables())
    }

    @Test fun sequenceIsOnePrayerPerBeadWithFiveMysteries() {
        val g = guide(AppLanguage.ES)
        val steps = g.guide.steps
        assertEquals(71, steps.size)
        assertEquals(6, g.guide.sections.size) // opening + five mysteries
        assertEquals("Persignarse", steps[0].name)
        assertTrue(steps[1].leader.startsWith("Mi gloriosa Santa Rita"))
        assertTrue(steps[1].leader.contains("35 años"))
        assertTrue(steps[2].name.contains("Credo"))
        assertEquals("Padre Nuestro", steps[3].name)
        assertEquals(3, steps.subList(4, 7).size)
        assertTrue(steps.subList(4, 7).all { it.name == "Ave María" })

        // First mystery: long prayer on the large bead, then ten jaculations on ten small beads.
        assertTrue(steps[8].leader.startsWith("Mi gloriosa Santa Rita de lo imposible"))
        assertTrue(steps[8].meditation!!.contains("hija obediente"))
        assertEquals(7, steps[8].bead)
        assertEquals((8..17).toList(), steps.subList(9, 19).map { it.bead })
        assertTrue(steps.subList(9, 19).all { it.leader == "Santa Rita de lo imposible: nada te es imposible." })
        assertTrue(steps.subList(9, 19).all { it.response.isEmpty() })
        assertTrue(steps[19].name.contains("Gloria al Padre"))

        // The same pattern repeats for all five decades; the fifth ends before the consecration.
        assertEquals(51, steps[56].bead)
        assertEquals((52..61).toList(), steps.subList(57, 67).map { it.bead })
        assertTrue(steps[67].name.contains("Gloria al Padre"))
        assertTrue(steps[68].leader.startsWith("Poderosa Santa Rita"))
        assertTrue(steps[69].leader.contains("ruega por nosotros"))
        assertTrue(steps.last().name.contains("Señal de la Cruz"))
    }

    @Test fun englishAndPortugueseGuidesBuildWithFullText() {
        val en = guide(AppLanguage.EN).guide.steps
        val pt = guide(AppLanguage.PT).guide.steps
        assertNotNull(en[1].leader)
        assertTrue(en[1].leader.contains("35 years"))
        assertTrue(en[9].leader.startsWith("Saint Rita of the impossible"))
        assertTrue(pt[1].leader.contains("35 anos"))
        assertTrue(pt[9].leader == "Santa Rita dos impossíveis: nada para vós é impossível.")
        assertTrue(pt.last().name.contains("Sinal da Cruz"))
    }
}
