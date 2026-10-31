package com.example

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.news.ReadingsResult
import com.example.data.news.VaticanGospelFetcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The daily missal now reads the Vatican's own feed. These check the parser
 * against real captures of all three language feeds from 31 October 2026.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class VaticanGospelTest {

    private fun feed(lang: String) =
        javaClass.classLoader!!.getResourceAsStream("vaticangospel_$lang.rss.xml")!!
            .bufferedReader().use { it.readText() }

    private fun readings(lang: String) =
        (VaticanGospelFetcher().parse(feed(lang), "2026-10-31") as ReadingsResult.Success).readings

    @Test
    fun picksTheItemForTheRequestedDay() {
        val es = readings("es")
        assertTrue(es.link, es.link.endsWith("/2026/10/31.html"))
        assertTrue(es.title, es.title.contains("31 octubre 2026"))
    }

    @Test
    fun splitsSpanishIntoReadingsWithCitations() {
        val sections = readings("es").sections
        assertEquals(2, sections.size)
        assertTrue(sections[0].heading, sections[0].heading.startsWith("Lectura de la carta"))
        assertEquals("Filipenses 1, 18-26", sections[0].citation)
        assertTrue(sections[0].text, sections[0].text.startsWith("Hermanos:"))
        assertTrue(sections[1].heading, sections[1].heading.contains("evangelio"))
        assertEquals("Lucas 14, 1. 7-11", sections[1].citation)
        assertTrue(sections[1].text.contains("banquete de bodas"))
    }

    @Test
    fun splitsEnglishAndPortuguese() {
        val en = readings("en").sections
        assertEquals(2, en.size)
        assertTrue(en[1].heading, en[1].heading.startsWith("From the Gospel according to Luke"))
        assertTrue(en[1].text.isNotBlank())

        val pt = readings("pt").sections
        assertEquals(2, pt.size)
        assertTrue(pt[0].heading, pt[0].heading.startsWith("Leitura da Carta"))
        assertTrue(pt[1].heading, pt[1].heading.startsWith("Proclamação do Evangelho"))
    }

    @Test
    fun noMarkupOrEntitiesSurvive() {
        for (lang in listOf("es", "en", "pt")) {
            for (s in readings(lang).sections) {
                assertTrue("$lang markup: ${s.text.take(80)}", !s.text.contains("<"))
                assertTrue("$lang entity: ${s.text.take(80)}", !Regex("&[a-z]+;").containsMatchIn(s.text))
            }
        }
        // Accents must be decoded, not left as &aacute;.
        assertTrue(readings("es").sections[0].text.contains("me seguirá alegrando"))
    }

    @Test
    fun fallsBackToNewestWhenTheDayIsMissing() {
        val r = VaticanGospelFetcher().parse(feed("es"), "1999-01-01")
        assertTrue(r is ReadingsResult.Success)
    }
}
