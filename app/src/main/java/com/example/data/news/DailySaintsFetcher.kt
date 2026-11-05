package com.example.data.news

import com.example.data.models.AppLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.Calendar

/**
 * One saint (or feast) from the daily saints feed. All text arrives in
 * English only; localization is the repository's job.
 */
data class DailySaint(
    val name: String,
    val feastDay: String,
    val era: String?,
    val patronage: List<String>,
    val legacy: String?,
    val summary: String,
    val image: String?,
    val wikipedia: String?
)

/** One day of the feed: [date] is the zero-padded "MM-DD" key. */
data class DailySaintsDay(
    val date: String,
    val feastDay: String,
    val saints: List<DailySaint>
)

/**
 * One saint as published by Vatican News for today, in the language the
 * page was fetched in. The page carries exactly one saint: its name in
 * the section heading and a short biography excerpt after it.
 */
data class VaticanSaint(
    val name: String,
    val biography: String
)

/**
 * Reads today's saint from two sources:
 *
 * 1. Primary: the Vatican News saint-of-the-day page, fetched in the
 *    current app language —
 *    - ES: https://www.vaticannews.va/es/santos/MM/DD.html
 *    - EN: https://www.vaticannews.va/en/saints/MM/DD.html
 *    - PT: https://www.vaticannews.va/pt/santo-do-dia/MM/DD.html
 *    keyed by the local date. Returns null on any failure so callers can
 *    fall back to the daily-saints API and then to the bundled entry.
 * 2. Fallback: the daily-saints feed
 *    (https://acoci86.github.io/daily-saints/api/v1/saints/MM-DD.json),
 *    English only. Returns null on any failure the same way.
 *
 * Same HTTP shape as [VaticanNewsFetcher].
 */
class DailySaintsFetcher(
    private val client: OkHttpClient = VaticanNewsFetcher.defaultClient()
) {

    companion object {
        private const val TIMEOUT_MS = 20_000L
        private const val BASE_URL = "https://acoci86.github.io/daily-saints/api/v1/saints"

        fun urlFor(monthDay: String): String = "$BASE_URL/$monthDay.json"

        /**
         * The Vatican News saint page for [monthDay] in [language]; the
         * path differs per language (`/santos/`, `/saints/`,
         * `/santo-do-dia/`), so this is the single place that knows them.
         */
        fun vaticanUrlFor(language: AppLanguage, monthDay: String): String {
            val (month, day) = monthDay.split("-")
            return when (language) {
                AppLanguage.ES -> "https://www.vaticannews.va/es/santos/$month/$day.html"
                AppLanguage.EN -> "https://www.vaticannews.va/en/saints/$month/$day.html"
                AppLanguage.PT -> "https://www.vaticannews.va/pt/santo-do-dia/$month/$day.html"
            }
        }

        /** Local date as the feed's zero-padded "MM-DD" key. */
        fun todayMonthDay(cal: Calendar = Calendar.getInstance()): String =
            "%02d-%02d".format(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH))

        /**
         * Parses one day's JSON. Exposed so parsing can be tested against a
         * captured payload without the network. Null when the payload has
         * no saints.
         */
        fun parse(json: String): DailySaintsDay? {
            val root = runCatching { JSONObject(json) }.getOrNull() ?: return null
            val array = root.optJSONArray("saints") ?: return null
            val saints = mutableListOf<DailySaint>()
            for (i in 0 until array.length()) {
                val obj = array.optJSONObject(i) ?: continue
                val name = obj.optNullableString("name")?.takeIf { it.isNotBlank() } ?: continue
                val patronage = mutableListOf<String>()
                val rawPatronage = obj.opt("patronage")
                if (rawPatronage is org.json.JSONArray) {
                    for (j in 0 until rawPatronage.length()) {
                        rawPatronage.optString(j).takeIf { it.isNotBlank() }?.let(patronage::add)
                    }
                }
                saints += DailySaint(
                    name = name,
                    feastDay = obj.optNullableString("feast_day").orEmpty(),
                    era = obj.optNullableString("era")?.takeIf { it.isNotBlank() },
                    patronage = patronage,
                    legacy = obj.optNullableString("legacy")?.takeIf { it.isNotBlank() },
                    summary = obj.optNullableString("summary").orEmpty(),
                    image = obj.optNullableString("image")?.takeIf { it.isNotBlank() },
                    wikipedia = obj.optNullableString("wikipedia")?.takeIf { it.isNotBlank() }
                )
            }
            if (saints.isEmpty()) return null
            return DailySaintsDay(
                date = root.optNullableString("date").orEmpty(),
                feastDay = root.optNullableString("feast_day").orEmpty(),
                saints = saints
            )
        }

        /** Null JSON values and missing keys both read as null, never "null". */
        private fun JSONObject.optNullableString(key: String): String? {
            val value = opt(key) ?: return null
            if (value == JSONObject.NULL) return null
            return value.toString()
        }

        /**
         * Headings that are page chrome, never the saint: navigation labels
         * in every language (the Spanish/Portuguese search headings are
         * "Buscar"/"Busca", not "Search") plus the page's own title.
         */
        private val NAV_HEADINGS = setOf(
            "menu", "search", "buscar", "busca", "menú",
            "santo del día", "saint of the day", "santo do dia"
        )

        /**
         * Parses one Vatican News saint page. Exposed so parsing can be
         * tested against a captured page without the network. Null on any
         * failure — no saint heading, or no biography paragraph after it.
         *
         * Strategy, from the page's actual structure:
         * - The saint's name is the first substantive `<h2>` (the section
         *   heading). Chrome headings ("Menu", "Search") are skipped.
         * - The biography is the first non-trivial `<p>` after that
         *   heading. The banner figure sits *above* the heading and the
         *   generic intro paragraph too, so neither can be mistaken for
         *   the biography. Trivial-length paragraphs and known
         *   boilerplate (donation banner, share links) are skipped.
         */
        fun parseVatican(html: String): VaticanSaint? {
            if (html.isBlank()) return null
            val cleaned = html
                .replace(Regex("(?is)<script.*?</script>"), " ")
                .replace(Regex("(?is)<style.*?</style>"), " ")
            val heading = Regex("(?is)<h2[^>]*>(.*?)</h2>")
            val paragraphs = Regex("(?is)<p[^>]*>(.*?)</p>")
            for (match in heading.findAll(cleaned)) {
                val name = stripTags(match.groupValues[1]).trim()
                if (name.isEmpty() || name.lowercase() in NAV_HEADINGS) continue
                val after = cleaned.substring(match.range.last + 1)
                for (p in paragraphs.findAll(after)) {
                    val bio = stripTags(p.groupValues[1]).trim()
                    if (bio.length < 20) continue
                    val lower = bio.lowercase()
                    if ("your contribution for a great mission" in lower ||
                        "su contribución a una gran misión" in lower ||
                        "sua contribuição para uma grande missão" in lower ||
                        lower.startsWith("send") || lower.startsWith("print") ||
                        lower.startsWith("enviar") || lower.startsWith("imprimir")
                    ) continue
                    return VaticanSaint(name = name, biography = bio)
                }
                // Saint heading with no biography paragraph: malformed page.
                return null
            }
            return null
        }

        /** Tags out, entities decoded, whitespace collapsed. */
        private fun stripTags(fragment: String): String {
            val text = fragment.replace(Regex("(?s)<[^>]+>"), " ")
            return text
                .replace("&nbsp;", " ")
                .replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&#39;", "'")
                .replace("&apos;", "'")
                .replace(Regex("\\s+"), " ")
        }
    }

    /** Today's Vatican News saint in [language], or null on any failure. */
    suspend fun fetchVaticanSaint(language: AppLanguage): VaticanSaint? =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MS) {
                fetchVatican(vaticanUrlFor(language, todayMonthDay()))
            }
        }

    private fun fetchVatican(url: String): VaticanSaint? {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "SanctaFides/1.0 (Catholic companion app)")
            .build()
        return runCatching {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body?.string() ?: return@use null
                parseVatican(body)
            }
        }.getOrNull()
    }

    /** Today's saints from the daily-saints API, or null on any failure. */
    suspend fun fetchToday(): DailySaintsDay? =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MS) { fetch(todayMonthDay()) }
        }

    private fun fetch(monthDay: String): DailySaintsDay? {
        val request = Request.Builder()
            .url(urlFor(monthDay))
            .header("User-Agent", "SanctaFides/1.0 (Catholic companion app)")
            .build()
        return runCatching {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body?.string() ?: return@use null
                parse(body)
            }
        }.getOrNull()
    }
}
