package com.example.data.repository

import android.content.Context
import com.example.data.models.BibleVerse
import com.example.data.models.LocalizedText
import org.json.JSONObject
import java.io.File

/**
 * Loads complete Bible text from bundled assets (bible/bible_en.json, _es.json, _pt.json)
 * and optionally from locally downloaded alternate versions stored in filesDir/bibles/.
 */
class BibleTextProvider(private val context: Context) {

    private val enMap: Map<String, Map<String, List<String>>> by lazy { loadAsset("bible/bible_en.json") }
    private val esMap: Map<String, Map<String, List<String>>> by lazy { loadAsset("bible/bible_es.json") }
    private val ptMap: Map<String, Map<String, List<String>>> by lazy { loadAsset("bible/bible_pt.json") }

    private fun loadAsset(path: String): Map<String, Map<String, List<String>>> {
        return try {
            val json = context.assets.open(path).bufferedReader().use { it.readText() }
            parseBibleJson(json)
        } catch (e: Exception) {
            emptyMap()
        }
    }

    private fun parseBibleJson(json: String): Map<String, Map<String, List<String>>> {
        val root = JSONObject(json)
        val result = mutableMapOf<String, Map<String, List<String>>>()
        val bookKeys = root.keys()
        while (bookKeys.hasNext()) {
            val bookId = bookKeys.next()
            val chaptersObj = root.getJSONObject(bookId)
            val chapters = mutableMapOf<String, List<String>>()
            val chKeys = chaptersObj.keys()
            while (chKeys.hasNext()) {
                val ch = chKeys.next()
                val arr = chaptersObj.getJSONArray(ch)
                val verses = mutableListOf<String>()
                for (i in 0 until arr.length()) {
                    verses.add(arr.getString(i))
                }
                chapters[ch] = verses
            }
            result[bookId] = chapters
        }
        return result
    }

    fun getVerses(bookId: String, chapterNumber: Int): List<BibleVerse> {
        val enChapters = enMap[bookId] ?: return emptyList()
        val enVerses = enChapters[chapterNumber.toString()] ?: return emptyList()
        val esVerses = esMap[bookId]?.get(chapterNumber.toString())
        val ptVerses = ptMap[bookId]?.get(chapterNumber.toString())
        return enVerses.mapIndexed { idx, enText ->
            BibleVerse(
                number = idx + 1,
                text = LocalizedText(
                    es = esVerses?.getOrNull(idx) ?: enText,
                    en = enText,
                    pt = ptVerses?.getOrNull(idx) ?: enText
                )
            )
        }
    }

    fun hasText(bookId: String, chapterNumber: Int): Boolean {
        return enMap[bookId]?.containsKey(chapterNumber.toString()) == true
    }

    fun search(query: String, limit: Int = 50): List<Triple<String, Int, BibleVerse>> {
        if (query.isBlank()) return emptyList()
        val q = query.lowercase()
        val results = mutableListOf<Triple<String, Int, BibleVerse>>()
        for (bookId in enMap.keys) {
            val chapters = enMap[bookId] ?: continue
            for ((chStr, verses) in chapters) {
                val chNum = chStr.toIntOrNull() ?: continue
                verses.forEachIndexed { idx, text ->
                    if (text.lowercase().contains(q)) {
                        val esText = esMap[bookId]?.get(chStr)?.getOrNull(idx) ?: text
                        val ptText = ptMap[bookId]?.get(chStr)?.getOrNull(idx) ?: text
                        val verse = BibleVerse(
                            number = idx + 1,
                            text = LocalizedText(es = esText, en = text, pt = ptText)
                        )
                        results.add(Triple(bookId, chNum, verse))
                        if (results.size >= limit) return results
                    }
                }
            }
        }
        return results
    }

    // Offline download support
    fun downloadedVersionsDir(): File = File(context.filesDir, "bibles").also { it.mkdirs() }

    fun isDownloaded(versionId: String): Boolean =
        File(downloadedVersionsDir(), "$versionId.json").exists()

    fun downloadProgress(versionId: String): Float? = null // managed by caller

    companion object {
        val DOWNLOADABLE_VERSIONS = listOf(
            DownloadableVersion("dra", "Douay-Rheims (English)", "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/English/Douay-Rheims-Challoner/"),
            DownloadableVersion("rv1909", "Reina-Valera 1909 (Spanish)", "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/Spanish/Reina-Valera-1909/"),
            DownloadableVersion("almeida", "Almeida (Portuguese)", "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/Portuguese/Almeida-Portuguese/")
        )
    }

    data class DownloadableVersion(val id: String, val name: String, val baseUrl: String)
}
