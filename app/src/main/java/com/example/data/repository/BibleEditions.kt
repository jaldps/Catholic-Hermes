package com.example.data.repository

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.Context
import com.example.data.models.AppLanguage
import org.json.JSONObject

/** One Bible the reader can open. Every edition ships inside the APK; none needs a download. */
data class BibleEdition(
    val id: String,
    val name: String,
    /** The edition's own language: es, en, pt or la. */
    val language: String,
    val description: Map<String, String>,
    /** For the three original per-language Bibles: the asset language they live in. */
    val bundledLanguage: AppLanguage?
) {
    fun description(language: AppLanguage): String = description[language.code].orEmpty()
}

/**
 * The installed editions (assets/bibles/editions.json) and where their text lives.
 *
 * Ids: "bundled-es/en/pt" are the original per-language Bibles (assets/bible/bible_xx.json, assets/xref/xref_xx.bin);
 * the rest keep per-book JSON and an own xref.bin under assets/bibles/<id>/. The legacy stored id "bundled" means
 * "the bundled Bible of the app language" and stays valid everywhere.
 */
class BibleEditionCatalog(private val context: Context) {
    val editions: List<BibleEdition> by lazy { loadEditions() }

    private fun loadEditions(): List<BibleEdition> = runCatching {
        val root = JSONObject(context.assets.open("bibles/editions.json").bufferedReader().use { it.readText() })
        val array = root.getJSONArray("editions")
        (0 until array.length()).map { i ->
            val e = array.getJSONObject(i)
            val d = e.optJSONObject("description")
            val id = e.getString("id")
            BibleEdition(
                id = id, name = e.getString("name"), language = e.getString("language"),
                description = d?.keys()?.asSequence()?.associateWith { d.getString(it) } ?: emptyMap(),
                bundledLanguage = if (e.optBoolean("bundled")) AppLanguage.entries.firstOrNull { id == "bundled-${it.code}" } else null
            )
        }
    }.onFailure {AppLog.failure(LogEvent.BIBLE_EDITIONS,it)}.getOrDefault(emptyList())

    fun edition(id: String?): BibleEdition? = editions.firstOrNull { it.id == id }

    /** True for "bundled" (legacy) and every listed edition. */
    fun isValid(id: String): Boolean = id == LEGACY_BUNDLED || edition(id) != null

    /** Editions grouped in picker order, empty groups omitted. */
    fun groups(): List<Pair<String, List<BibleEdition>>> =
        LANGUAGE_ORDER.mapNotNull { lang -> editions.filter { it.language == lang }.takeIf { it.isNotEmpty() }?.let { lang to it } }

    /** The own-text edition (not one of the three original Bibles) with this id, else null. */
    fun separate(id: String?): BibleEdition? = edition(id)?.takeIf { it.bundledLanguage == null }

    /** The asset language of an original Bible: "bundled-en" is English whatever the app language. */
    fun bundledLanguage(id: String?): AppLanguage? = edition(id)?.bundledLanguage

    private val bookFiles = java.util.concurrent.ConcurrentHashMap<String, Map<String, String>>()

    /** bookId -> asset file name, e.g. "joh" -> "50-joh.json". */
    private fun files(id: String): Map<String, String> = bookFiles.getOrPut(id) {
        runCatching { context.assets.list("bibles/$id").orEmpty() }.onFailure {AppLog.failure(LogEvent.BIBLE_BOOK_LIST,it)}.getOrDefault(emptyArray())
            .filter { it.endsWith(".json") }.sorted()
            .associateBy { it.substringAfter('-').removeSuffix(".json") }
    }

    /** Book ids of a separate edition, in file (canonical) order. */
    fun bookIds(id: String): List<String> = files(id).keys.toList()

    fun readBook(id: String, bookId: String): JSONObject? {
        val file = files(id)[bookId] ?: return null
        return runCatching { JSONObject(context.assets.open("bibles/$id/$file").bufferedReader().use { it.readText() }) }.onFailure {AppLog.failure(LogEvent.BIBLE_BOOK,it)}.getOrNull()
    }

    /** Asset path of the cross-reference table for [id]; null falls back to the app language's table. */
    fun xrefAsset(id: String): String? {
        val e = edition(id) ?: return null
        return e.bundledLanguage?.let { "xref/xref_${it.code}.bin" } ?: "bibles/$id/xref.bin"
    }

    companion object {
        const val LEGACY_BUNDLED = "bundled"
        val LANGUAGE_ORDER = listOf("es", "en", "pt", "la")
        val INITIAL_DEFAULTS: Map<String, String> = mapOf("es" to "bundled-es", "en" to "bundled-en", "pt" to "bundled-pt")
        fun initialDefault(language: AppLanguage) = "bundled-${language.code}"
    }
}
