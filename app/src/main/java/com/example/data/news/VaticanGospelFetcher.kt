package com.example.data.news

import com.example.data.models.AppLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.util.concurrent.TimeUnit

/** One reading of the day: its heading, its citation, and its text. */
data class MassSection(
    val heading: String,
    val citation: String,
    val text: String
)

/** The readings Vatican News publishes for one day. */
data class VaticanDailyReadings(
    val title: String,
    val publishedAt: String,
    val link: String,
    val sections: List<MassSection>
)

sealed interface ReadingsResult {
    data class Success(val readings: VaticanDailyReadings) : ReadingsResult
    data class Failure(val reason: String) : ReadingsResult
}

/**
 * Reads the day's Mass readings from the Vatican's own feed.
 *
 * The readings page itself fills its content in with JavaScript and shows a
 * cookie banner that covers half the screen and cannot be dismissed from a
 * WebView, so the page is not used. The same text is published per language as
 * an RSS feed, one item per day, which is read and rendered natively instead.
 */
class VaticanGospelFetcher(
    private val client: OkHttpClient = VaticanNewsFetcher.defaultClient()
) {

    companion object {
        private const val TIMEOUT_MS = 20_000L

        fun feedUrl(language: AppLanguage): String = when (language) {
            AppLanguage.ES -> "https://www.vaticannews.va/content/vaticannews/es/evangelio-de-hoy.rss.xml"
            AppLanguage.EN -> "https://www.vaticannews.va/content/vaticannews/en/word-of-the-day.rss.xml"
            AppLanguage.PT -> "https://www.vaticannews.va/content/vaticannews/pt/palavra-do-dia.rss.xml"
        }

        /** The day's readings page, for opening in a browser. */
        fun pageUrl(language: AppLanguage): String = when (language) {
            AppLanguage.ES -> "https://www.vaticannews.va/es/evangelio-de-hoy.html"
            AppLanguage.EN -> "https://www.vaticannews.va/en/word-of-the-day.html"
            AppLanguage.PT -> "https://www.vaticannews.va/pt/palavra-do-dia.html"
        }

        // How each language opens a reading. Spanish then gives the citation a
        // line of its own; English and Portuguese end the heading with it.
        private val HEADING = Regex(
            "^(Lectura|Lecturas|Salmo|Evangelio|Aleluya|Secuencia" +
                "|A reading|Reading|From the Gospel|The Gospel|Responsorial|Psalm|Alleluia" +
                "|Leitura|Proclamação|Evangelho|Salmo responsorial)\\b",
            RegexOption.IGNORE_CASE
        )

        // "Filipenses 1, 18-26", "Lucas 14, 1. 7-11", "1 Juan 3, 1-2".
        private val CITATION = Regex("^[1-3]?\\s?\\p{L}[\\p{L} .]{1,30}\\s\\d{1,3}[,:].{0,40}$")

        /**
         * Splits one day's HTML description into readings. Exposed so the
         * parsing can be tested against a captured feed without the network.
         */
        fun sectionsFrom(descriptionHtml: String): List<MassSection> {
            val paragraphs = Regex("<p[^>]*>(.*?)</p>", RegexOption.DOT_MATCHES_ALL)
                .findAll(descriptionHtml)
                .map { it.groupValues[1].stripHtml() }
                .filter { it.isNotBlank() }
                .toList()

            val sections = mutableListOf<MassSection>()
            var heading: String? = null
            var citation = ""
            val body = StringBuilder()

            fun flush() {
                val h = heading ?: return
                sections += MassSection(h, citation, body.toString().trim())
                body.clear()
                citation = ""
            }

            for (p in paragraphs) {
                when {
                    HEADING.containsMatchIn(p) && p.length < 160 -> {
                        flush()
                        heading = p
                    }
                    heading != null && citation.isEmpty() && body.isEmpty() &&
                        p.length < 60 && CITATION.matches(p) -> citation = p
                    heading != null -> {
                        if (body.isNotEmpty()) body.append("\n\n")
                        body.append(p)
                    }
                }
            }
            flush()
            return sections.filter { it.text.isNotBlank() }
        }

        private fun String.stripHtml(): String =
            replace(Regex("<[^>]*>"), " ")
                .replace("&nbsp;", " ")
                .replace(Regex("&([a-zA-Z]+|#\\d+|#[xX][0-9a-fA-F]+);")) { decode(it.value) }
                .replace(Regex("\\s+"), " ")
                .trim()

        // The feed escapes accented letters as named entities (&aacute;). This
        // covers every one Spanish and Portuguese text uses, plus numeric forms,
        // without relying on android.text.Html.
        private val NAMED = mapOf(
            "amp" to "&", "quot" to "\"", "apos" to "'", "lt" to "<", "gt" to ">",
            "nbsp" to " ", "laquo" to "«", "raquo" to "»", "ldquo" to "“", "rdquo" to "”",
            "lsquo" to "‘", "rsquo" to "’", "mdash" to "—", "ndash" to "–", "hellip" to "…",
            "iexcl" to "¡", "iquest" to "¿", "ordf" to "ª", "ordm" to "º", "middot" to "·",
            "aacute" to "á", "eacute" to "é", "iacute" to "í", "oacute" to "ó", "uacute" to "ú",
            "Aacute" to "Á", "Eacute" to "É", "Iacute" to "Í", "Oacute" to "Ó", "Uacute" to "Ú",
            "agrave" to "à", "egrave" to "è", "Agrave" to "À",
            "acirc" to "â", "ecirc" to "ê", "ocirc" to "ô", "Acirc" to "Â", "Ecirc" to "Ê", "Ocirc" to "Ô",
            "atilde" to "ã", "otilde" to "õ", "Atilde" to "Ã", "Otilde" to "Õ",
            "ntilde" to "ñ", "Ntilde" to "Ñ", "ccedil" to "ç", "Ccedil" to "Ç",
            "uuml" to "ü", "Uuml" to "Ü", "iuml" to "ï"
        )

        private fun decode(entity: String): String {
            val name = entity.substring(1, entity.length - 1)
            return when {
                name.startsWith("#x") || name.startsWith("#X") ->
                    name.substring(2).toIntOrNull(16)?.let { String(Character.toChars(it)) } ?: entity
                name.startsWith("#") ->
                    name.substring(1).toIntOrNull()?.let { String(Character.toChars(it)) } ?: entity
                else -> NAMED[name] ?: entity
            }
        }
    }

    /**
     * Today's readings, or the most recent day the feed carries when today's
     * has not been published yet.
     */
    suspend fun today(language: AppLanguage, isoDate: String): ReadingsResult =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MS) { fetch(language, isoDate) }
                ?: ReadingsResult.Failure("timed out")
        }

    private fun fetch(language: AppLanguage, isoDate: String): ReadingsResult {
        val request = Request.Builder()
            .url(feedUrl(language))
            .header("User-Agent", "SanctaFides/1.0 (Catholic companion app)")
            .build()
        return runCatching {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use ReadingsResult.Failure("HTTP ${response.code}")
                val body = response.body?.string()
                    ?: return@use ReadingsResult.Failure("empty response")
                parse(body, isoDate)
            }
        }.getOrElse { ReadingsResult.Failure(it.message ?: it::class.java.simpleName) }
    }

    /** Picks the item for [isoDate] ("2026-10-31") by its link, else the newest. */
    fun parse(xml: String, isoDate: String): ReadingsResult {
        val items = items(xml)
        if (items.isEmpty()) return ReadingsResult.Failure("no readings in feed")
        val datePath = isoDate.replace('-', '/')
        val chosen = items.firstOrNull { it.link.contains(datePath) } ?: items.first()
        val sections = sectionsFrom(chosen.description)
        if (sections.isEmpty()) return ReadingsResult.Failure("no readings in item")
        return ReadingsResult.Success(
            VaticanDailyReadings(chosen.title, chosen.date, chosen.link, sections)
        )
    }

    private data class RawItem(val title: String, val date: String, val link: String, val description: String)

    private fun items(xml: String): List<RawItem> {
        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(StringReader(xml))
        val out = mutableListOf<RawItem>()
        var inItem = false
        var current = ""
        var title = ""; var date = ""; var link = ""; var description = ""
        var event = parser.eventType
        while (event != XmlPullParser.END_DOCUMENT) {
            when (event) {
                XmlPullParser.START_TAG -> {
                    current = parser.name.orEmpty()
                    if (current.equals("item", true)) {
                        inItem = true
                        title = ""; date = ""; link = ""; description = ""
                    }
                }
                XmlPullParser.TEXT, XmlPullParser.CDSECT -> if (inItem) {
                    val t = parser.text.orEmpty()
                    when {
                        current.equals("title", true) -> title += t
                        current.equals("pubDate", true) -> date += t
                        current.equals("link", true) -> link += t
                        current.equals("description", true) -> description += t
                    }
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name.equals("item", true)) {
                        inItem = false
                        out += RawItem(title.trim(), date.trim(), link.trim(), description)
                    }
                    current = ""
                }
            }
            event = parser.next()
        }
        return out
    }
}
