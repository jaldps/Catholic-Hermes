package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.CatechismCompendiumData
import com.example.data.repository.CatechismFullData
import com.example.data.repository.CatechismOutline
import com.example.ui.screens.CccStructure
import com.example.ui.screens.buildSnippet
import com.example.ui.screens.cccDisplayText
import com.example.ui.screens.compendiumGroups
import com.example.ui.screens.firstCccRange
import com.example.ui.screens.parseParagraphQuery
import com.example.ui.screens.queryTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The Catechism reader's pure logic: the book structure, the display-time
 * clean-up of the bundled text, the search snippet and the honest grouping of
 * the Compendium. (The data itself is guarded by [CatechismCoverageTest].)
 */
class CatechismReaderLogicTest {

    private val languages = listOf(AppLanguage.ES, AppLanguage.EN, AppLanguage.PT)

    @Test
    fun theBookStructureTilesTheWholeCatechism() {
        val sections = CccStructure.sections
        assertEquals(1, sections.first().first)
        assertEquals(2865, sections.last().last)
        sections.zipWithNext { a, b -> assertEquals(a.last + 1, b.first) }
        // Every outline section sits inside exactly one division of the book.
        for (outline in CatechismOutline.sections) {
            val holders = sections.filter { outline.firstNumber >= it.first && outline.lastNumber <= it.last }
            assertEquals("outline §§ ${outline.firstNumber}-${outline.lastNumber}", 1, holders.size)
        }
        // Each part has a title and a contiguous range.
        for (part in 0..4) {
            val range = CccStructure.partRange(part)
            assertTrue(range.first <= range.last)
        }
        assertEquals(1..25, CccStructure.partRange(0))
        assertEquals(2558..2865, CccStructure.partRange(4))
    }

    @Test
    fun displayTextHasNoHardWrapsStrayFootnotesOrPaddedParentheses() {
        val leftovers = mutableListOf<String>()
        var longest = 0
        for (n in 1..2865) {
            val full = CatechismFullData.contentByNumber[n] ?: continue
            for (lang in languages) {
                val clean = cccDisplayText(full.get(lang), lang)
                longest = maxOf(longest, clean.length)
                if (clean.contains('\n') || clean.contains('\r')) leftovers += "§$n ${lang.code}: line break"
                if (clean.contains("( ") || clean.contains(" )")) leftovers += "§$n ${lang.code}: padded parenthesis"
                if (clean.contains("  ")) leftovers += "§$n ${lang.code}: double space"
                if (lang == AppLanguage.EN && DANGLING_FOOTNOTE.containsMatchIn(clean) && !ABBREVIATED.containsMatchIn(clean)) {
                    leftovers += "§$n en: dangling footnote digit"
                }
                if (lang == AppLanguage.PT && Regex("""\(\d{1,3}\)""").containsMatchIn(clean)) {
                    leftovers += "§$n pt: footnote marker"
                }
            }
        }
        assertTrue("display text problems (${leftovers.size}): ${leftovers.take(15)}", leftovers.isEmpty())
        // The footnote apparatus appended to the last paragraph of a section is gone.
        assertTrue("longest paragraph is $longest characters", longest < 3000)
    }

    @Test
    fun englishParagraphIsJoinedAndFreeOfFootnoteDigits() {
        val raw = CatechismFullData.contentByNumber.getValue(2).en
        // The verified source is normalized; exercise display joining explicitly.
        val clean = cccDisplayText(raw.replace("Strengthened", "\nStrengthened"), AppLanguage.EN)
        assertFalse(clean.contains('\n'))
        assertFalse(clean.contains("\" 4 Strengthened"))
        assertTrue(clean.contains("age.\" Strengthened by this mission"))
        assertTrue(clean.endsWith("that attended it.\""))
    }

    @Test
    fun citationsAreNormalisedAndScriptureReferencesSurvive() {
        val es = cccDisplayText("Id y haced discípulos ( Mt 28,19-20) y ( primera sección ).", AppLanguage.ES)
        assertEquals("Id y haced discípulos (Mt 28,19-20) y (primera sección).", es)
        // "Cf. 1 Cor" is a citation, not a footnote.
        val en = cccDisplayText("As we read (cf. 1 Cor 15, 3-4) in the Creed. 7 Next sentence.", AppLanguage.EN)
        assertTrue(en, en.contains("(cf. 1 Cor 15, 3-4)"))
        assertFalse(en, en.contains(". 7 Next"))
        val pt = cccDisplayText("o «Ámen» (474) do seu sacrifício (1974).", AppLanguage.PT)
        assertEquals("o «Ámen» do seu sacrifício (1974).", pt)
    }

    @Test
    fun hardWrappedNotesAtTheEndOfASectionAreCut() {
        val clean = cccDisplayText(CatechismFullData.contentByNumber.getValue(1065).pt, AppLanguage.PT)
        assertTrue(clean.length < 1500)
        assertFalse(clean.contains("Demonstratio"))
    }

    @Test
    fun snippetsCentreOnTheFirstMatchAndMarkIt() {
        val text = "Lorem ipsum ".repeat(30) + "la gracia santificante es un don habitual " + "dolor sit amet ".repeat(20)
        val snippet = buildSnippet(text, queryTokens("Gracia"))
        assertTrue(snippet.text.length <= 175)
        assertTrue(snippet.text.startsWith("…"))
        val marked = snippet.highlights.map { snippet.text.substring(it.first, it.last + 1) }
        assertEquals(listOf("gracia"), marked)
    }

    @Test
    fun aParagraphQueryAcceptsSectionSignsAndHashes() {
        assertEquals(1324, parseParagraphQuery("1324"))
        assertEquals(1324, parseParagraphQuery("§1324"))
        assertEquals(1324, parseParagraphQuery(" § 1324 "))
        assertEquals(7, parseParagraphQuery("#7"))
        assertNull(parseParagraphQuery("gracia"))
        assertNull(parseParagraphQuery("13a"))
        assertNull(parseParagraphQuery(""))
    }

    @Test
    fun theCompendiumIsGroupedWithoutLosingAnyNumber() {
        val entries = CatechismCompendiumData.keyCompendiumEntries
        val groups = compendiumGroups(entries)
        val covered = groups.flatMap { it.first..it.last }
        assertEquals((1..598).toList(), covered)
        assertTrue("grouping should collapse the templated ranges (${groups.size} groups)", groups.size < 150)
        // Any number is findable in exactly one group.
        for (n in listOf(1, 75, 250, 598)) {
            assertEquals(1, groups.count { n in it.first..it.last })
        }
    }

    @Test
    fun aCompendiumReferenceNamesItsFirstParagraphRange() {
        assertEquals(31 to 36, firstCccRange("CCC 31-36, 46-47"))
        assertEquals(1 to 25, firstCccRange("CCC 1-25"))
        assertEquals(1324 to null, firstCccRange("CCC 1324"))
        assertNull(firstCccRange("sin referencia"))
        assertNotNull(firstCccRange("CCC 2759-2865"))
    }

    private companion object {
        val DANGLING_FOOTNOTE = Regex("""["”’)][.,;:!?]?\s+\d{1,3}(?:\s+[A-Za-z]|\s*$)|[.!?]["”’)]?\s+\d{1,3}(?:\s+[A-Za-z]|\s*$)""")
        val ABBREVIATED = Regex("""(?i)(?:^|[\s(])(?:cf|st|sts|no|nos|n|nn|art|arts|vol|p|pp|ch|chap|sess|sec|ibid)\.\s+\d{1,3}\s""")
    }
}
