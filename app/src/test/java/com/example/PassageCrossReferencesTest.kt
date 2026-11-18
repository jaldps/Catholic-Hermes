package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleEditionCatalog
import com.example.data.repository.CatholicDataRepository
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The citations of courses and plans (Hebrew psalm numbering) become cross-references in the edition the
 * reader uses: the psalm is renumbered first, then the bundled table is read.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class PassageCrossReferencesTest {
    private val repo = CatholicDataRepository(ApplicationProvider.getApplicationContext())
    @After fun close() = repo.close()

    private fun targets(passage: CatholicDataRepository.PassageCrossReferences) =
        passage.verses.flatMap { it.references }.map { it.reference.target.let { t -> "${t.bookId} ${t.chapter}:${t.verse}" } }

    @Test fun johnThreeSixteenReachesRomansFiveEightInEveryBundledEdition() {
        for (language in AppLanguage.entries) {
            val edition = BibleEditionCatalog.initialDefault(language)
            val passage = repo.passageCrossReferences("joh 3:16", language, edition, perVerse = 60)
            assertEquals("$language", 1, passage.verseCount)
            assertEquals("$language", 1, passage.verses.size)
            assertTrue("$language ${targets(passage)}", "rom 5:8" in targets(passage))
            assertTrue(passage.verses.single().references.all { it.preview.isNotBlank() && it.label.isNotBlank() })
        }
    }

    @Test fun theStrongestFewAreKeptPerVerseAndTheRestAreCounted() {
        val passage = repo.passageCrossReferences("joh 3:16", AppLanguage.EN, "bundled-en")
        val verse = passage.verses.single()
        assertEquals(3, verse.references.size)
        assertTrue(verse.total > 3)
        assertEquals(verse.total, passage.totalReferences)
        assertEquals("John 3:16".lowercase(), verse.citation.lowercase().replace("st. ", ""))
    }

    @Test fun hebrewPsalmTwentyThreeIsVulgateTwentyTwoBeforeTheLookup() {
        val verses = repo.citedVerses("psa 23", "bundled-es", AppLanguage.ES)
        assertTrue(verses.isNotEmpty())
        assertTrue(verses.all { it.bookId == "psa" && it.chapter == 22 })
        assertEquals((1..verses.size).toList(), verses.map { it.verse })
        // The same table the reader opens for Psalm 22: the Good Shepherd answers it.
        val passage = repo.passageCrossReferences("psa 23", AppLanguage.ES, "bundled-es", perVerse = 60)
        assertTrue(passage.verses.all { it.source.chapter == 22 })
        assertTrue(targets(passage).any { it.startsWith("joh 10:") })
        // The label keeps the course's number and adds the edition's.
        assertEquals("Salmos 23 (22)".substringAfter(' '), passage.label.substringAfter(' '))
    }

    @Test fun otherEditionsOfTheVulgatePsalterAlsoRenumber() {
        assertTrue(repo.citedVerses("psa 23", "bundled-en", AppLanguage.EN).all { it.chapter == 22 })
        assertTrue(repo.citedVerses("psa 23", "bundled-pt", AppLanguage.PT).all { it.chapter == 22 })
    }

    @Test fun verseRangesAndChapterSpansAreExact() {
        val range = repo.citedVerses("luk 1:26-38", "bundled-es", AppLanguage.ES)
        assertEquals((26..38).toList(), range.map { it.verse })
        val span = repo.citedVerses("gen 1:1-2:3", "bundled-es", AppLanguage.ES)
        assertEquals(31 + 3, span.size)
        assertEquals(2, span.last().chapter)
        // Two Hebrew chapters that are one in the Vulgate: a whole citation reads both Vulgate chapters.
        assertEquals(setOf(114, 115), repo.citedVerses("psa 116", "bundled-es", AppLanguage.ES).map { it.chapter }.toSet())
        // Half of Psalm 119 stays inside Vulgate 118.
        assertEquals((1..88).toList(), repo.citedVerses("psa 119:1-88", "bundled-es", AppLanguage.ES).map { it.verse })
        assertTrue(repo.citedVerses("psa 119:1-88", "bundled-es", AppLanguage.ES).all { it.chapter == 118 })
    }

    @Test fun aWholeChapterIsCappedByVerseAndSaysSo() {
        val chapter = repo.passageCrossReferences("gen 1", AppLanguage.EN, "bundled-en", maxVerses = 5)
        assertEquals(31, chapter.verseCount)
        assertEquals(5, chapter.verses.size)
        assertTrue(chapter.truncated)
        assertTrue(chapter.versesWithReferences > 5)
        assertEquals(chapter.verses.map { it.source.verse }, chapter.verses.map { it.source.verse }.sorted())
        assertFalse(repo.passageCrossReferences("gen 1", AppLanguage.EN, "bundled-en").let { it.verses.size > 40 })
    }

    @Test fun deuterocanonicalBooksAndUnreadableCitationsAreEmptyNotBroken() {
        val wisdom = repo.passageCrossReferences("wis 3:1-9", AppLanguage.EN, "bundled-en")
        assertTrue(wisdom.verseCount > 0)
        assertTrue(wisdom.verses.isEmpty())
        val junk = repo.passageCrossReferences("not a reference", AppLanguage.EN, "bundled-en")
        assertEquals(0, junk.verseCount)
        assertTrue(junk.verses.isEmpty())
    }
}
