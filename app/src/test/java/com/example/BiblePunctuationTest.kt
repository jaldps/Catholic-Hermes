package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.json.JSONObject
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** The bundled Bibles must never regain a space before closing punctuation. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class BiblePunctuationTest {

    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()
    private val spacedClosing = Regex("""\s[.,;:!?]""")

    private fun offenders(fileName: String): List<String> {
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val bible = JSONObject(json)
        val found = mutableListOf<String>()
        for (bookKey in bible.keys()) {
            val chapters = bible.getJSONObject(bookKey)
            for (chapterKey in chapters.keys()) {
                val verses = chapters.getJSONArray(chapterKey)
                for (i in 0 until verses.length()) {
                    val verse = verses.getString(i)
                    val match = spacedClosing.find(verse)
                    if (match != null) {
                        found += "$bookKey $chapterKey:${i + 1} …${match.value}…"
                    }
                }
            }
        }
        return found
    }

    @Test
    fun noBundledBibleHasASpaceBeforeClosingPunctuation() {
        for (file in listOf("bible/bible_es.json", "bible/bible_en.json", "bible/bible_pt.json")) {
            val found = offenders(file)
            assertTrue(
                "$file has ${found.size} verses with a space before punctuation: ${found.take(10)}",
                found.isEmpty()
            )
        }
    }
}
