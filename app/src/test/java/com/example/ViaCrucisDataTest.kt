package com.example

import com.example.data.models.LocalizedText
import com.example.data.repository.DoctrineAndSpiritualData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** The Way of the Cross: the traditional fourteen stations, complete in every language. */
class ViaCrucisDataTest {

    private val stations get() = DoctrineAndSpiritualData.viaCrucisStations

    private fun LocalizedText.all() = listOf(es, en, pt)

    @Test
    fun hasTheFourteenTraditionalStationsInOrder() {
        assertEquals(14, stations.size)
        assertEquals((1..14).toList(), stations.map { it.stationNumber })
    }

    @Test
    fun everyFieldIsFilledInSpanishEnglishAndPortuguese() {
        for (s in stations) {
            val fields = mapOf(
                "title" to s.title,
                "scripture" to s.scripture,
                "meditation" to s.meditation,
                "prayer" to s.prayer
            )
            for ((name, text) in fields) {
                assertTrue("station ${s.stationNumber} $name", text.all().all { it.isNotBlank() })
            }
        }
    }

    @Test
    fun translationsAreDistinctAndScriptureCitesAReference() {
        for (s in stations) {
            for ((name, text) in listOf("title" to s.title, "meditation" to s.meditation, "prayer" to s.prayer)) {
                assertEquals("station ${s.stationNumber} $name", 3, text.all().toSet().size)
            }
            assertTrue("station ${s.stationNumber} scripture", s.scripture.all().all { Regex("[0-9]").containsMatchIn(it) })
        }
    }
}
