package com.example.data.repository

import android.content.Context
import com.example.data.models.AppLanguage
import com.example.data.models.BibleVerse
import com.example.data.models.LocalizedText
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Loads the complete Catholic Bible (73 books) from bundled assets
 * (bible/bible_en.json, bible_es.json, bible_pt.json) and optionally from
 * locally downloaded alternate versions in filesDir/bibles/.
 *
 * Each asset is {bookId: {chapterNumber: [verse, verse, ...]}} keyed by the
 * same book ids as [BibleData.catholicCanonBooks].
 *
 * Reads are language-first: a chapter is rendered from the requested
 * language's own verse list, so chapters whose versification differs between
 * translations (notably the Psalms, where the Vulgate tradition counts the
 * title line) keep their own verse numbering instead of being forced onto the
 * English one. Only one language is held parsed at a time, because each asset
 * is roughly 4.7 MB of JSON.
 */
class BibleTextProvider(private val context: Context) {

    private var loadedLanguage: AppLanguage? = null
    private var loadedRoot: JSONObject? = null

    /** Parses the asset for [language], releasing any other language first. */
    private fun root(language: AppLanguage): JSONObject? {
        if (loadedLanguage == language) return loadedRoot
        loadedRoot = null
        loadedLanguage = null
        val parsed = try {
            val json = context.assets.open("bible/bible_${language.code}.json")
                .bufferedReader()
                .use { it.readText() }
            JSONObject(json)
        } catch (e: Exception) {
            null
        }
        loadedRoot = parsed
        loadedLanguage = if (parsed != null) language else null
        return parsed
    }

    private fun chapterArray(language: AppLanguage, bookId: String, chapterNumber: Int): JSONArray? {
        val book = root(language)?.optJSONObject(bookId) ?: return null
        return book.optJSONArray(chapterNumber.toString())
    }

    private fun JSONArray.toStringList(): List<String> =
        (0 until length()).map { optString(it, "") }

    /**
     * Verse list for [bookId]/[chapterNumber] in [language]. Verse numbers are
     * that language's own, 1-based. Falls back to English when the requested
     * language has no text for the chapter.
     */
    fun getVerses(
        bookId: String,
        chapterNumber: Int,
        language: AppLanguage = AppLanguage.EN
    ): List<BibleVerse> {
        val verses = chapterArray(language, bookId, chapterNumber)?.toStringList()
            ?: chapterArray(AppLanguage.EN, bookId, chapterNumber)?.toStringList()
            ?: return emptyList()
        return verses.mapIndexed { idx, text ->
            BibleVerse(number = idx + 1, text = LocalizedText(es = text, en = text, pt = text))
        }
    }

    /**
     * Verses per chapter for every book in [language], in that edition's own
     * numbering: index 0 is chapter 1. Used to lay the whole Bible on one line.
     */
    fun verseCounts(language: AppLanguage): Map<String, IntArray> {
        val root = root(language) ?: return emptyMap()
        return BibleData.catholicCanonBooks.associate { book ->
            val chapters = root.optJSONObject(book.id)
            val count = chapters?.keys()?.asSequence()?.count { it.toIntOrNull() != null } ?: 0
            book.id to IntArray(count) { c -> chapters?.optJSONArray((c + 1).toString())?.length() ?: 0 }
        }
    }

    /** Number of chapters present for [bookId] in [language]. */
    fun chapterCount(bookId: String, language: AppLanguage = AppLanguage.EN): Int {
        val book = root(language)?.optJSONObject(bookId) ?: return 0
        return book.keys().asSequence().count { it.toIntOrNull() != null }
    }

    fun hasText(bookId: String, chapterNumber: Int, language: AppLanguage = AppLanguage.EN): Boolean =
        chapterArray(language, bookId, chapterNumber) != null

    /** Full-text search over [language], returning (bookId, chapter, verse). */
    fun search(
        query: String,
        language: AppLanguage = AppLanguage.EN,
        limit: Int = 50
    ): List<Triple<String, Int, BibleVerse>> {
        if (query.isBlank()) return emptyList()
        val root = root(language) ?: return emptyList()
        val needle = query.lowercase()
        val results = mutableListOf<Triple<String, Int, BibleVerse>>()
        for (bookId in BibleData.catholicCanonBooks.map { it.id }) {
            val book = root.optJSONObject(bookId) ?: continue
            val chapters = book.keys().asSequence()
                .mapNotNull { it.toIntOrNull() }
                .sorted()
            for (chapterNumber in chapters) {
                val arr = book.optJSONArray(chapterNumber.toString()) ?: continue
                for (i in 0 until arr.length()) {
                    val text = arr.optString(i, "")
                    if (!text.lowercase().contains(needle)) continue
                    results.add(
                        Triple(
                            bookId,
                            chapterNumber,
                            BibleVerse(
                                number = i + 1,
                                text = LocalizedText(es = text, en = text, pt = text)
                            )
                        )
                    )
                    if (results.size >= limit) return results
                }
            }
        }
        return results
    }

    // Offline download support
    fun downloadedVersionsDir(): File = File(context.filesDir, "bibles").also { it.mkdirs() }

    fun isDownloaded(versionId: String): Boolean =
        File(downloadedVersionsDir(), "$versionId.json").exists()

    companion object {
        val DOWNLOADABLE_VERSIONS = listOf(
            DownloadableVersion("dra", "Douay-Rheims (English)", "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/English/Douay-Rheims-Challoner/"),
            DownloadableVersion("rv1909", "Reina-Valera 1909 (Spanish)", "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/Spanish/Reina-Valera-1909/"),
            DownloadableVersion("almeida", "Almeida (Portuguese)", "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/Portuguese/Almeida-Portuguese/")
        )
    }

    data class DownloadableVersion(val id: String, val name: String, val baseUrl: String)
}
