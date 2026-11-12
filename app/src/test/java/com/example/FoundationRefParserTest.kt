package com.example

import com.example.data.liturgy.FoundationRef
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.parseFoundation
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Foundation refs: Bible citations and Catechism paragraphs become clickable refs. */
class FoundationRefParserTest {

    private val books = BibleData.catholicCanonBooks

    private fun bible(bookId: String, chapter: Int, start: Int?, end: Int? = null) =
        FoundationRef.BibleRef(ScriptureReference(bookId, chapter, start, end))

    @Test
    fun q1SpanishParsesJohnRomansRevelationAndCatechismRange() {
        val refs = parseFoundation(
            "Romanos 8,18-23; Juan 9,1-3; Apocalipsis 21,4. Catecismo, 309-314.",
            AppLanguage.ES, books
        )
        assertEquals(
            listOf(
                bible("rom", 8, 18, 23),
                bible("joh", 9, 1, 3),
                bible("rev", 21, 4),
                FoundationRef.CatechismRef(309, 314)
            ), refs
        )
    }

    @Test
    fun q1EnglishParsesJohnRomansRevelationAndCatechismRange() {
        val refs = parseFoundation(
            "Romans 8:18-23; John 9:1-3; Revelation 21:4. Catechism, 309-314.",
            AppLanguage.EN, books
        )
        assertEquals(
            listOf(
                bible("rom", 8, 18, 23),
                bible("joh", 9, 1, 3),
                bible("rev", 21, 4),
                FoundationRef.CatechismRef(309, 314)
            ), refs
        )
    }

    @Test
    fun q1PortugueseParsesJohnRomansRevelationAndCatechismRange() {
        val refs = parseFoundation(
            "Romanos 8,18-23; João 9,1-3; Apocalipse 21,4. Catecismo, 309-314.",
            AppLanguage.PT, books
        )
        assertEquals(
            listOf(
                bible("rom", 8, 18, 23),
                bible("joh", 9, 1, 3),
                bible("rev", 21, 4),
                FoundationRef.CatechismRef(309, 314)
            ), refs
        )
    }

    @Test
    fun singleParagraphCatechismHasNullEnd() {
        val refs = parseFoundation("Ver Catecismo 1030.", AppLanguage.ES, books)
        assertEquals(listOf(FoundationRef.CatechismRef(1030, null)), refs)
    }

    @Test
    fun numberedDeuterocanonicalBookMatches() {
        val refs = parseFoundation("2 Macabeos 12,42-46", AppLanguage.ES, books)
        assertEquals(listOf(bible("2ma", 12, 42, 46)), refs)
    }

    @Test
    fun psalmSingularAndPluralMatch() {
        assertEquals(
            listOf(bible("psa", 139, 1, 16)),
            parseFoundation("Salmo 139,1-16", AppLanguage.ES, books)
        )
        assertEquals(
            listOf(bible("psa", 139, 1, 16)),
            parseFoundation("Psalm 139:1-16", AppLanguage.EN, books)
        )
    }

    @Test
    fun refsComeInTextOrderAndDuplicatesAreRemoved() {
        val refs = parseFoundation(
            "Catecismo, 31-35; Juan 1,18; Juan 1,18; Romanos 1,20",
            AppLanguage.ES, books
        )
        assertEquals(
            listOf(
                FoundationRef.CatechismRef(31, 35),
                bible("joh", 1, 18),
                bible("rom", 1, 20)
            ), refs
        )
    }

    @Test
    fun nonReferenceTextYieldsEmpty() {
        assertTrue(parseFoundation("El clima es agradable hoy.", AppLanguage.ES, books).isEmpty())
        assertTrue(parseFoundation("The weather is nice today.", AppLanguage.EN, books).isEmpty())
        assertTrue(parseFoundation("", AppLanguage.EN, books).isEmpty())
        assertTrue(parseFoundation("Quanto custa o pão?", AppLanguage.PT, books).isEmpty())
    }

    @Test
    fun accentInsensitiveMatching() {
        val refs = parseFoundation("Isaias 7,10-14; Gálatas 5,22-23", AppLanguage.ES, books)
        assertEquals(listOf(bible("isa", 7, 10, 14), bible("gal", 5, 22, 23)), refs)
    }
}
