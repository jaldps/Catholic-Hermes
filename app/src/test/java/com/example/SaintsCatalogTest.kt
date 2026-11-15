package com.example

import com.example.data.liturgy.CalendarDate
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.SaintsCatalog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.io.FileNotFoundException

internal const val SAINTS_FIXTURE = """[
 {"key":"francis_de_sales_bishop","month":1,"day":24,
  "bio":{"es":"Obispo de Ginebra.","en":"Bishop of Geneva.","pt":"Bispo de Genebra."},
  "patronages":{"es":["Periodistas","Escritores"],"en":["Journalists","Writers"],"pt":["Jornalistas","Escritores"]},
  "quote":{"text":{"es":"Texto es","en":"Quote en","pt":"Texto pt"},"source":"Introducción a la vida devota, I, 3"},
  "sources":["Martirologio Romano (2004)","Calendarium Romanum (1969)"]},
 {"key":"agatha_of_sicily_virgin","month":2,"day":5,
  "bio":{"es":"Virgen y mártir.","en":"Virgin and martyr.","pt":"Virgem e mártir."},
  "patronages":{"es":[],"en":[],"pt":[]},"quote":null,"sources":["Martirologio Romano (2004)"]}
]"""

internal fun saintsFixtureCatalog(q1: String = SAINTS_FIXTURE) =
    SaintsCatalog { name -> if (name == "saints-q1.json") q1.byteInputStream() else throw FileNotFoundException(name) }

class SaintsCatalogTest {
    @Test fun parsesFixtureAndIgnoresMissingFiles() {
        val c = saintsFixtureCatalog()
        assertEquals(setOf("francis_de_sales_bishop", "agatha_of_sicily_virgin"), c.byKey.keys)
        val f = c.byKey.getValue("francis_de_sales_bishop")
        assertEquals(1, f.month); assertEquals(24, f.day)
        assertEquals("Bishop of Geneva.", f.bio.en)
        assertEquals(listOf("Journalists", "Writers"), f.patronages.get(AppLanguage.EN))
        assertEquals("Introducción a la vida devota, I, 3", f.quote?.source)
        assertEquals(2, f.sources.size)
        assertNull(c.byKey.getValue("agatha_of_sicily_virgin").quote)
    }

    @Test fun malformedFileIsIgnoredAndQuoteWithoutSourceDropped() {
        assertTrue(saintsFixtureCatalog("not json").byKey.isEmpty())
        val noSource = """[{"key":"k","month":1,"day":1,"quote":{"text":{"es":"a","en":"a","pt":"a"}}}]"""
        assertNull(saintsFixtureCatalog(noSource).byKey.getValue("k").quote)
    }

    @Test fun saintOfTheDayUsesProfileWhenPresent() = runBlocking {
        val repo = CatholicDataRepository(dateProvider = { CalendarDate(2026, 1, 24) }, saintsCatalog = saintsFixtureCatalog())
        try {
            val saint = kotlinx.coroutines.withTimeout(10_000) { repo.saintOfTheDayFlow.first { it.id.startsWith("saint_profile_") } }
            assertEquals("saint_profile_francis_de_sales_bishop_2026-01-24", saint.id)
            assertTrue(saint.name.en.contains("Francis de Sales"))
            assertEquals("Bishop of Geneva.", saint.biography.en)
            assertEquals("Introducción a la vida devota, I, 3", saint.quoteSource)
            assertEquals("Journalists, Writers", saint.patronages.en)
        } finally { repo.close() }
    }

    @Test fun saintOfTheDayFallsBackWithoutProfile() {
        val repo = CatholicDataRepository(dateProvider = { CalendarDate(2026, 1, 24) }, saintsCatalog = saintsFixtureCatalog("[]"))
        try { assertFalse(repo.saintOfTheDayFlow.value.id.startsWith("saint_profile_")) } finally { repo.close() }
    }
}
