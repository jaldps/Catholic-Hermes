package com.example

import com.example.data.repository.PrayerAndNovenaData
import com.example.data.repository.PrayerDetailsCatalog
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Test

class PrayerDetailsContentTest {
    private fun exists(f: String) = PrayerDetailsContentTest::class.java.classLoader!!.getResource(f) != null

    @Test fun detailsCoverEveryExistingPrayerExactlyOnce() {
        assumeTrue(exists("details-a.json") && exists("details-b.json"))
        val cl = PrayerDetailsContentTest::class.java.classLoader!!
        val ids = listOf("details-a.json", "details-b.json").flatMap { f ->
            Regex("\"prayerId\"\\s*:\\s*\"([^\"]+)\"").findAll(cl.getResource(f)!!.readText()).map { it.groupValues[1] }.toList()
        }
        assertEquals("duplicate ids", ids.size, ids.toSet().size)
        assertEquals(PrayerAndNovenaData.vaticanOfficialPrayers.map { it.id }.toSet(), ids.toSet())
        val details = PrayerDetailsCatalog.default.detailsById
        ids.forEach { id ->
            val d = details.getValue(id)
            listOf(d.history, d.instructions).forEach {
                assertTrue("$id es", it.es.isNotBlank()); assertTrue("$id en", it.en.isNotBlank()); assertTrue("$id pt", it.pt.isNotBlank())
            }
        }
    }
}
