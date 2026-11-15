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
}
