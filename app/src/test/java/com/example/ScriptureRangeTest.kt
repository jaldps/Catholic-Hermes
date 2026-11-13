package com.example

import com.example.data.liturgy.LectionaryData
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.AppLanguage
import com.example.data.models.BibleVerse
import com.example.data.models.LocalizedText
import org.junit.Assert.*
import org.junit.Test

class ScriptureRangeTest {
    private fun resolver(missingChapter: Int? = null, missingVerse: Int? = null) = ScriptureResolver(
        bookName = { _, _ -> "John" },
        versesOf = { book, chapter, _ ->
            if (chapter == missingChapter) emptyList()
            else (1..42).filter { it != missingVerse }.map { verse ->
                val text = "$book $chapter:$verse"
                BibleVerse(verse, LocalizedText(text, text, text))
            }
        }
    )

    /** USCCB, Good Friday, 29 March 2024: Jn 18:1—19:42; Heb 4:14-16; 5:7-9. */
    @Test fun goodFridayIncludesBothPassionChaptersAndAllHebrewsSegments() {
        val entry = LectionaryData.fixed.getValue("good_friday")
        assertEquals("joh 18:1-19:42", entry.gospel)
        assertEquals("isa 52:13-53:12", entry.firstReading)
        assertEquals("heb 4:14-16; 5:7-9", entry.secondReading)
        val passage = resolver().passage(entry.gospel, AppLanguage.EN)!!
        assertTrue(passage.startsWith("joh 18:1 joh 18:2"))
        assertTrue(passage.endsWith("joh 19:41 joh 19:42"))
        val hebrews = resolver().passage(entry.secondReading!!, AppLanguage.EN)!!
        assertEquals("heb 4:14 heb 4:15 heb 4:16 heb 5:7 heb 5:8 heb 5:9", hebrews)
    }

    @Test fun omittedVersesAreNotBridged() {
        assertEquals("mat 6:1 mat 6:2 mat 6:5 mat 6:8 mat 6:9",
            resolver().passage("mat 6:1-2,5,8-9", AppLanguage.EN))
        assertEquals("John 6:1-2; 6:5; 6:8-9",
            resolver().display("mat 6:1-2,5,8-9", AppLanguage.EN))
    }

    @Test fun missingChaptersAndMissingIndividualVersesNeverProduceTruncatedPassages() {
        assertNull(resolver(missingChapter = 18).passage("joh 18:1-19:42", AppLanguage.EN))
        assertNull(resolver(missingVerse = 42).passage("joh 18:1-19:42", AppLanguage.EN))
        assertNull(resolver().passage("joh 18:41-45", AppLanguage.EN))
    }

    @Test fun absentRequestedLanguageIsNotSilentlyReplacedByEnglish() {
        var englishRequests = 0
        val resolver = ScriptureResolver({ _, _ -> "John" }) { _, _, language ->
            if (language == AppLanguage.EN) {
                englishRequests++
                listOf(BibleVerse(1, LocalizedText("English", "English", "English")))
            } else emptyList()
        }
        assertNull(resolver.passage("joh 1:1", AppLanguage.ES))
        assertEquals(0, englishRequests)
    }

    @Test fun invalidRangesAndUnsupportedVersePartsFailClearly() {
        for (raw in listOf("joh 0:1", "joh 1:0", "joh 3:8-2", "joh 3:2-2:8", "joh 3:1b", "joh 3:1,", "joh 3:1;")) {
            assertNull(raw, ScriptureReference.parse(raw))
        }
        assertNotNull(ScriptureReference.parse("2co 5:20-6:2"))
        assertNotNull(ScriptureReference.parse("isa 7:10-14; 8:10"))
    }
}
