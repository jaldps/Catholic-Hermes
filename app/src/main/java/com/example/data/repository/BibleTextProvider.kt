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
 * Bundled reads are language-first: a chapter is rendered from the requested
 * language's own verse list, so chapters whose versification differs between
 * translations (notably the Psalms, where the Vulgate tradition counts the
 * title line) keep their own verse numbering instead of being forced onto the
 * English one. Parsed bundled languages are cached for this provider lifetime,
 * so a trilingual devotional lookup never reparses all assets on every request.
 */
class BibleTextProvider internal constructor(
    private val context: Context,
    private val downloads: BibleDownloadManager = BibleDownloadManager(context)
) {
    private fun isAlternate(editionId: String?): Boolean = downloads.specs.any { it.id == editionId }
    private var loadedDownloadedKey: String? = null
    private var loadedDownloadedBook: JSONObject? = null

    @Synchronized
    private fun downloadedBook(editionId: String, bookId: String): JSONObject? {
        if (!downloads.isDownloaded(editionId)) {
            loadedDownloadedKey = null
            loadedDownloadedBook = null
            return null
        }
        val key = "$editionId:$bookId"
        if (loadedDownloadedKey != key) {
            loadedDownloadedBook = downloads.downloadedBook(editionId, bookId)
            loadedDownloadedKey = key
        }
        return loadedDownloadedBook
    }

    private val bundledRoots = java.util.EnumMap<AppLanguage, JSONObject>(AppLanguage::class.java)

    /** At most the three supported languages are parsed once per provider. */
    @Synchronized
    private fun root(language: AppLanguage): JSONObject? {
        bundledRoots[language]?.let { return it }
        val parsed = try {
            val json = context.assets.open("bible/bible_${language.code}.json")
                .bufferedReader()
                .use { it.readText() }
            JSONObject(json)
        } catch (e: Exception) {
            null
        }
        if (parsed != null) bundledRoots[language] = parsed
        return parsed
    }

    private fun chapterArray(language: AppLanguage, bookId: String, chapterNumber: Int, editionId: String? = null): JSONArray? {
        if (isAlternate(editionId)) {
            val chapters = downloadedBook(editionId!!, bookId)?.optJSONArray("chapters") ?: return null
            val verses = chapters.optJSONObject(chapterNumber - 1)?.optJSONArray("verses") ?: return null
            return JSONArray().apply {
                for (index in 0 until verses.length()) put(verses.getJSONObject(index).getString("text"))
            }
        }
        val book = root(language)?.optJSONObject(bookId) ?: return null
        return book.optJSONArray(chapterNumber.toString())
    }

    private fun JSONArray.toStringList(): List<String> =
        (0 until length()).map { optString(it, "") }

    /**
     * Verse list for [bookId]/[chapterNumber] in [language]. Verse numbers are
     * that language's own, 1-based. Falls back to English when the requested
     * language has no text for the chapter. Known alternate editions never silently
     * fall back: an unavailable book or incomplete edition returns an empty list.
     */
    fun getVerses(
        bookId: String,
        chapterNumber: Int,
        language: AppLanguage = AppLanguage.EN,
        editionId: String? = null
    ): List<BibleVerse> {
        val verses = chapterArray(language, bookId, chapterNumber, editionId)?.toStringList()
            ?: (if (!isAlternate(editionId)) chapterArray(AppLanguage.EN, bookId, chapterNumber)?.toStringList() else null)
            ?: return emptyList()
        return verses.mapIndexed { idx, text ->
            BibleVerse(number = idx + 1, text = LocalizedText(es = text, en = text, pt = text))
        }
    }

    /**
     * Verses per chapter for every book in [language], in that edition's own
     * numbering: index 0 is chapter 1. Used to lay the whole Bible on one line.
     */
    fun verseCounts(language: AppLanguage, editionId: String? = null): Map<String, IntArray> {
        if (isAlternate(editionId)) {
            if (!downloads.isDownloaded(editionId!!)) return emptyMap()
            return downloads.bookIds(editionId).associateWith { bookId ->
                val chapters = downloadedBook(editionId, bookId)?.optJSONArray("chapters")
                IntArray(chapters?.length() ?: 0) { chapters!!.getJSONObject(it).getJSONArray("verses").length() }
            }
        }
        val root = root(language) ?: return emptyMap()
        return BibleData.catholicCanonBooks.associate { book ->
            val chapters = root.optJSONObject(book.id)
            val count = chapters?.keys()?.asSequence()?.count { it.toIntOrNull() != null } ?: 0
            book.id to IntArray(count) { c -> chapters?.optJSONArray((c + 1).toString())?.length() ?: 0 }
        }
    }

    /** Number of chapters present for [bookId] in [language]. */
    fun chapterCount(bookId: String, language: AppLanguage = AppLanguage.EN, editionId: String? = null): Int {
        if (isAlternate(editionId)) return downloadedBook(editionId!!, bookId)?.optJSONArray("chapters")?.length() ?: 0
        val book = root(language)?.optJSONObject(bookId) ?: return 0
        return book.keys().asSequence().count { it.toIntOrNull() != null }
    }

    fun hasText(bookId: String, chapterNumber: Int, language: AppLanguage = AppLanguage.EN, editionId: String? = null): Boolean =
        chapterArray(language, bookId, chapterNumber, editionId) != null

    /** Full-text search over [language], returning (bookId, chapter, verse). */
    fun search(
        query: String,
        language: AppLanguage = AppLanguage.EN,
        limit: Int = 50,
        editionId: String? = null
    ): List<Triple<String, Int, BibleVerse>> {
        if (query.isBlank() || limit <= 0) return emptyList()
        if (isAlternate(editionId)) {
            val matches = mutableListOf<Triple<String, Int, BibleVerse>>()
            for (bookId in downloads.bookIds(editionId!!)) {
                for (chapter in 1..chapterCount(bookId, language, editionId)) {
                    for (verse in getVerses(bookId, chapter, language, editionId)) {
                        if (verse.text.get(language).contains(query, ignoreCase = true)) {
                            matches += Triple(bookId, chapter, verse)
                            if (matches.size >= limit) return matches
                        }
                    }
                }
            }
            return matches
        }
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
        downloads.isDownloaded(versionId)

    companion object {
        @Deprecated("Use BibleDownloadManager.specs and downloadUrl; downloads are pinned and verified")
        val DOWNLOADABLE_VERSIONS = BibleDownloadManager.VERSION_SPECS.map { spec ->
            DownloadableVersion(spec.id, spec.name,
                "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/${spec.path}/")
        }
    }

    data class DownloadableVersion(val id: String, val name: String, val baseUrl: String)
}
