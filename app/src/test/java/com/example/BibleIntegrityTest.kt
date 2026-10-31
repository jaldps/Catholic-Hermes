package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleData
import com.example.data.repository.BibleTextProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Guards the bundled Bible against the defect that prompted this work: the
 * assets were keyed by a different book order than the app's, so every book
 * from Esther onward returned another book's text.
 *
 * These assertions check identity, not merely non-emptiness — a chapter that
 * exists but holds the wrong book would pass a "not empty" test.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class BibleIntegrityTest {

    private val provider = BibleTextProvider(ApplicationProvider.getApplicationContext())
    private val languages = listOf(AppLanguage.EN, AppLanguage.ES, AppLanguage.PT)

    @Test
    fun allSeventyThreeBooksHaveEveryDeclaredChapterInEveryLanguage() {
        assertEquals(73, BibleData.catholicCanonBooks.size)
        val missing = mutableListOf<String>()
        for (language in languages) {
            for (book in BibleData.catholicCanonBooks) {
                for (chapter in 1..book.chaptersCount) {
                    if (provider.getVerses(book.id, chapter, language).isEmpty()) {
                        missing += "${language.code}:${book.id}:$chapter"
                    }
                }
            }
        }
        assertTrue("chapters with no text: ${missing.take(25)} (${missing.size} total)", missing.isEmpty())
    }

    @Test
    fun declaredChapterCountsMatchTheBundledText() {
        val mismatched = BibleData.catholicCanonBooks.mapNotNull { book ->
            val actual = provider.chapterCount(book.id, AppLanguage.EN)
            if (actual != book.chaptersCount) {
                "${book.id}: declared ${book.chaptersCount}, bundled $actual"
            } else {
                null
            }
        }
        assertTrue("chapter-count mismatches: $mismatched", mismatched.isEmpty())
    }

    /**
     * Each book id must hold that book and not its neighbour. These openings
     * are distinctive enough that a one-book shift fails every one of them.
     */
    @Test
    fun bookIdsHoldTheRightBook() {
        val openings = mapOf(
            "gen" to "In the beginning God created heaven",
            "exo" to "These are the names of the children of Israel",
            "tob" to "Tobias of the tribe and city of Nephtali",
            "jdt" to "Now Arphaxad king of the Medes",
            "est" to "In the days of Assuerus",
            "job" to "There was a man in the land of Hus",
            "psa" to "Blessed is the man who hath not walked",
            "pro" to "The parables of Solomon",
            "ecc" to "The words of Ecclesiastes",
            "sng" to "Let him kiss me with the kiss of his mouth",
            "wis" to "Love justice, you that are the judges of the earth",
            "sir" to "All wisdom is from the Lord God",
            "isa" to "The vision of Isaias",
            "jer" to "The words of Jeremias",
            "lam" to "Aleph. How doth the city sit solitary",
            "bar" to "And these are the words of the book, which Baruch",
            "eze" to "Now it came to pass in the thirtieth year",
            "dan" to "In the third year of the reign of Joakim",
            "mat" to "The book of the generation of Jesus Christ",
            "mar" to "The beginning of the gospel of Jesus Christ",
            "luk" to "Forasmuch as many have taken in hand",
            "joh" to "In the beginning was the Word",
            "act" to "The former treatise I made, O Theophilus",
            "rom" to "Paul, a servant of Jesus Christ",
            "1co" to "Paul, called to be an apostle of Jesus Christ",
            "heb" to "God, who, at sundry times and in divers manners",
            "jam" to "James, the servant of God",
            "1pe" to "Peter, an apostle of Jesus Christ",
            "1jo" to "That which was from the beginning",
            "jde" to "Jude, the servant of Jesus Christ",
            "rev" to "The Revelation of Jesus Christ",
            "1ma" to "Now it came to pass, after that Alexander",
            "2ma" to "To the brethren, the Jews that are throughout Egypt"
        )
        val wrong = openings.mapNotNull { (bookId, expectedOpening) ->
            val first = provider.getVerses(bookId, 1, AppLanguage.EN).firstOrNull()
                ?.text?.get(AppLanguage.EN)
                ?: return@mapNotNull "$bookId: no text at chapter 1"
            if (!first.startsWith(expectedOpening)) {
                "$bookId: expected \"$expectedOpening…\" but found \"${first.take(60)}…\""
            } else {
                null
            }
        }
        assertTrue("books holding the wrong text:\n${wrong.joinToString("\n")}", wrong.isEmpty())
    }

    /** Verse numbers must run 1..n with no gaps, in each language. */
    @Test
    fun verseNumbersAreContiguousAndLanguageOwned() {
        for (language in languages) {
            for (bookId in listOf("gen", "psa", "joh", "sir", "rev")) {
                val verses = provider.getVerses(bookId, 1, language)
                assertTrue("$bookId/$language has no verses", verses.isNotEmpty())
                assertEquals(
                    "$bookId/$language verse numbering",
                    (1..verses.size).toList(),
                    verses.map { it.number }
                )
            }
        }
    }

    /**
     * Spanish and Portuguese must return their own text, not fall through to
     * English — the defect this replaced silently substituted English whenever
     * a translation's verse count differed.
     */
    @Test
    fun spanishAndPortugueseReturnTheirOwnText() {
        val english = provider.getVerses("joh", 1, AppLanguage.EN).first().text.get(AppLanguage.EN)
        val spanish = provider.getVerses("joh", 1, AppLanguage.ES).first().text.get(AppLanguage.ES)
        val portuguese = provider.getVerses("joh", 1, AppLanguage.PT).first().text.get(AppLanguage.PT)

        assertTrue("Spanish John 1:1 is English", spanish != english)
        assertTrue("Portuguese John 1:1 is English", portuguese != english)
        assertTrue("Spanish John 1:1 unexpected: $spanish", spanish.contains("Verbo"))
        assertTrue("Portuguese John 1:1 unexpected: $portuguese", portuguese.contains("Verbo"))
    }

    /**
     * Psalm 150 must exist in all three languages — it is the last chapter of
     * the book that was previously keyed as Esther, so it was unreachable.
     */
    @Test
    fun psalmOneHundredFiftyIsReachable() {
        for (language in languages) {
            val verses = provider.getVerses("psa", 150, language)
            assertTrue("Psalm 150 missing in ${language.code}", verses.isNotEmpty())
        }
    }

    /**
     * All three psalters must use the same numbering. Spanish and Portuguese
     * originally carried Masoretic numbers while the English Douay-Rheims
     * carries Vulgate ones, so `psa 22` named a different psalm in each — and
     * every lectionary psalm resolved wrongly in two languages out of three.
     */
    @Test
    fun psaltersAreAlignedAcrossLanguages() {
        val structural = mutableListOf<String>()
        for (psalm in 1..150) {
            val counts = languages.associateWith { provider.getVerses("psa", psalm, it).size }
            val english = counts.getValue(AppLanguage.EN)
            assertTrue("Psalm $psalm missing in English", english > 0)
            for ((language, count) in counts) {
                assertTrue("Psalm $psalm missing in ${language.code}", count > 0)
                // Two verses of slack absorbs the title-line conventions that
                // differ legitimately between translations.
                if (kotlin.math.abs(count - english) > 2) {
                    structural += "psa $psalm: en=$english ${language.code}=$count"
                }
            }
        }
        assertTrue("psalters misaligned:\n${structural.joinToString("\n")}", structural.isEmpty())
    }

    /**
     * Psalm 22 must be the shepherd psalm in every language — the check that
     * catches a psalter slipping back to Masoretic numbering, where 22 is
     * "My God, why hast thou forsaken me".
     *
     * Each translation renders the image its own way, so each is matched on
     * its own wording: the Douay-Rheims "ruleth me", Straubinger's "pastor",
     * and Matos Soares' "apascenta" (he pastures me).
     */
    @Test
    fun theShepherdPsalmIsPsalmTwentyTwo() {
        val expected = mapOf(
            AppLanguage.EN to listOf("ruleth me", "shepherd"),
            AppLanguage.ES to listOf("pastor", "apacienta"),
            AppLanguage.PT to listOf("apascenta", "pastor")
        )
        for ((language, fragments) in expected) {
            val opening = provider.getVerses("psa", 22, language).first().text.get(language).lowercase()
            assertTrue(
                "${language.code} Psalm 22 is not the shepherd psalm: $opening",
                fragments.any { opening.contains(it) }
            )
            // And it must not be the psalm of the Passion, which is 21 in the
            // Vulgate and 22 in the Masoretic.
            assertTrue(
                "${language.code} Psalm 22 looks Masoretic-numbered: $opening",
                !opening.contains("abandonado") && !opening.contains("forsaken") &&
                    !opening.contains("desamparaste")
            )
        }
    }
}
