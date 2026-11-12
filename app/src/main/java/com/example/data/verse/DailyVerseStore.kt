package com.example.data.verse

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.repository.BibleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

private val Context.dailyVerseDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "daily_verse_cache"
)

/**
 * Day-of-year rotation for the verse of the day.
 *
 * The verse must *change* daily, so the reference is picked deterministically:
 * index = (dayOfYear - 1) % pool size. Every reader sees the same verse on the
 * same day with no stored state. References use the canonical book-id form the
 * lectionary uses (e.g. "phi 4:6-7") so [com.example.data.liturgy.ScriptureReference]
 * parses them and the bundled Bible resolves the text offline in all three
 * languages. Chapters/verses were chosen against the bundled canon so the
 * offline fallback never renders blank.
 */
object DailyVerseRotation {
    val REFERENCES: List<String> = listOf(
        "phi 4:6-7",
        "psa 23:1-3",
        "jer 29:11-13",
        "joh 3:16-17",
        "rom 8:28-30",
        "isa 40:28-31",
        "mat 11:28-30",
        "psa 46:1-3",
        "eph 2:8-10",
        "1co 13:4-7",
        "luk 18:1-8",
        "heb 11:1-6",
        "pro 3:1-6",
        "wis 3:1-9",
        "dan 3:16-18",
        "eze 37:1-10",
        "psa 19:7-10",
        "psa 27:1-4",
        "psa 121:1-4",
        "gen 1:1-5",
        "exo 20:1-3",
        "jos 1:1-5",
        "1sa 16:7-13",
        "psa 51:10-12",
        "1ki 19:11-13",
        "isa 6:1-3",
        "jer 1:4-9",
        "lam 3:22-26",
        "hos 6:1-3",
        "joh 8:12-14",
        "mar 1:14-15",
        "rom 12:1-2",
        "eph 6:10-13",
        "1pe 1:3-5",
        "1jo 4:7-10",
        "rev 21:1-4"
    )

    fun referenceFor(date: LocalDate): String =
        REFERENCES[(date.dayOfYear - 1).mod(REFERENCES.size)]
}

/**
 * Per-date verse-text cache and the free-network fetcher.
 *
 * Primary source is bible-api.com (free, no key, CORS-open static JSON):
 * `https://bible-api.com/{book chapter:verses}?translation={...}` where the
 * translation is `clementina` (ES, the Vulgate), `web` (EN) and `almeida` (PT).
 * The response's `text` field is the verse. Any failure (offline, HTTP error,
 * parse miss) returns null so callers fall back to the bundled-Bible passage
 * via [DailyVerseRotation] — the verse still changes daily without a network.
 *
 * Cache: one DataStore entry per date+language (`verse_text_yyyy-MM-dd_es`…),
 * so a fresh online fetch never re-downloads the same day's verse. No account,
 * no API key — the cache never leaves the device.
 */
class DailyVerseStore(
    context: Context,
    private val dataStore: DataStore<Preferences> = context.dailyVerseDataStore,
) {
    private fun keyFor(date: LocalDate, language: AppLanguage) =
        stringPreferencesKey("verse_text_${dateKey(date)}_${language.code}")

    /** Cached verse text for [date] in [language], or null on first open. */
    suspend fun cachedText(date: LocalDate, language: AppLanguage): String? =
        dataStore.data
            .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
            .map { it[keyFor(date, language)] }
            .first()
            ?.takeIf { txt -> txt.isNotBlank() }

    /** Stores [text] as today's verse in [language]. */
    suspend fun saveText(date: LocalDate, language: AppLanguage, text: String) {
        if (text.isBlank()) return
        dataStore.edit { prefs -> prefs[keyFor(date, language)] = text.trim() }
    }

    companion object {
        private val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        fun dateKey(date: LocalDate): String = date.format(DATE_FORMAT)
    }
}

/** Fetches one verse's text from bible-api.com; null on any failure. */
class DailyVerseFetcher(
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(12, TimeUnit.SECONDS)
        .callTimeout(15, TimeUnit.SECONDS)
        .build()
) {
    /**
     * The verse's text in [language], e.g. "En el principio Dios creó…".
     * [referenceId] is the canonical form ("phi 4:6-7"); bible-api.com wants
     * the English book name, so it is translated via the bundled canon.
     * Returns null when offline or when the id cannot be translated.
     */
    suspend fun fetchText(referenceId: String, language: AppLanguage): String? =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MS) { fetch(referenceId, language) }
        }

    private fun fetch(referenceId: String, language: AppLanguage): String? {
        val url = urlFor(referenceId, language) ?: return null
        return runCatching {
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "CatholicHermes/1.0 (Catholic companion app)")
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body?.string() ?: return@use null
                parseText(body)
            }
        }.getOrNull()?.takeIf { it.isNotBlank() }
    }

    /** Translates the canonical id into a bible-api.com URL, or null. */
    fun urlFor(referenceId: String, language: AppLanguage): String? {
        val parts = referenceId.trim().split(Regex("\\s+"), limit = 2)
        if (parts.size != 2) return null
        val (bookId, chapterVerses) = parts
        val englishName = BibleData.catholicCanonBooks
            .firstOrNull { it.id == bookId }?.name?.en
            ?: return null
        val query = "$englishName $chapterVerses".replace(" ", "%20").replace(":", "%3A")
        val translation = when (language) {
            AppLanguage.ES -> "clementina"
            AppLanguage.EN -> "web"
            AppLanguage.PT -> "almeida"
        }
        return "https://bible-api.com/$query?translation=$translation"
    }

    /** Reads the `text` field of a bible-api.com response; null when absent. */
    fun parseText(json: String): String? =
        runCatching { JSONObject(json).optString("text", "").trim() }
            .getOrNull()?.takeIf { it.isNotBlank() }

    companion object {
        private const val TIMEOUT_MS = 20_000L
    }
}

/** Meditation and challenge of the rotating verse: one shared devotional line
 * per language, trilingual, valid for any verse in the pool. */
object DailyVerseMeditation {
    val meditation = LocalizedText(
        "La Palabra de hoy no es letra muerta: resuena en la vida de quien la escucha con fe y la guarda en el corazón.",
        "Today's Word is living: it resounds in the life of whoever hears it in faith and keeps it in the heart.",
        "A Palavra de hoje não é letra morta: ressoa na vida de quem a escuta com fé e a guarda no coração."
    )
    val challenge = LocalizedText(
        "Hoy relee este versículo despacio tres veces, deteniéndote entre cada lectura, y rézalo como tuyo.",
        "Today reread this verse slowly three times, pausing between each reading, and pray it as your own.",
        "Hoje releia este versículo devagar três vezes, parando entre cada leitura, e reze como se fosse seu."
    )
}
