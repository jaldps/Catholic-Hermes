package com.example

import com.example.data.repository.PrayerAndNovenaData
import com.example.data.repository.PrayerDetailsCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** The bundled extra prayers (task T-003) and details (T-001/T-002) reach the merged prayer list. */
class PrayerExtrasIntegrationTest {
    @Test fun extrasAreMergedWithDetails() {
        val base = PrayerAndNovenaData.vaticanOfficialPrayers
        val merged = PrayerDetailsCatalog.default.mergeInto(base)
        val extras = PrayerDetailsCatalog.default.extraPrayers
        assertTrue("extra prayers loaded", extras.size >= 25)
        assertEquals(base.size + extras.size, merged.size)
        assertEquals(merged.size, merged.map { it.id }.toSet().size)
        val details = PrayerDetailsCatalog.default.detailsById
        merged.forEach { assertTrue("details for ${it.id}", details.containsKey(it.id)) }
    }

    @Test fun authenticTextOverridesApply() {
        val merged = PrayerDetailsCatalog.default.mergeInto(PrayerAndNovenaData.vaticanOfficialPrayers).associateBy { it.id }
        val veni = merged.getValue("come_holy_spirit")
        assertTrue("full sequence", veni.leaderText.es.contains("Oh luz beatísima"))
        assertEquals("added response removed", null, veni.responseText)
        assertEquals(null, merged.getValue("stay_with_me_lord").responseText)
        val michael = merged.getValue("chaplet_of_st_michael").leaderText.es
        assertTrue("no exorcism formula", !michael.contains("Huid") && !michael.contains("Pelea hoy"))
    }
}
