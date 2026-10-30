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

/** One story from the Vatican's own feed. */
data class VaticanNewsItem(
    val title: String,
    val summary: String,
    val link: String,
    val publishedAt: String
)

sealed interface NewsResult {
    data class Success(val items: List<VaticanNewsItem>) : NewsResult
    data class Failure(val reason: String) : NewsResult
}

/**
 * Reads the Vatican's published RSS feed, which carries the same stories as
 * vaticannews.va and needs no API key.
 *
 * The feed is fetched rather than the web page because the page's cookie
 * banner covers half a phone screen and cannot be dismissed from a WebView —
 * the stories are rendered natively here instead.
 */
class VaticanNewsFetcher(
    private val client: OkHttpClient = defaultClient()
) {

    companion object {
        private const val TIMEOUT_MS = 20_000L

        fun feedUrl(language: AppLanguage): String = when (language) {
            AppLanguage.ES -> "https://www.vaticannews.va/es.rss.xml"
            AppLanguage.PT -> "https://www.vaticannews.va/pt.rss.xml"
            AppLanguage.EN -> "https://www.vaticannews.va/en.rss.xml"
        }

        fun defaultClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    suspend fun latest(language: AppLanguage, limit: Int = 25): NewsResult =
        withContext(Dispatchers.IO) {
            withTimeoutOrNull(TIMEOUT_MS) { fetch(language, limit) }
                ?: NewsResult.Failure("timed out")
        }

    private fun fetch(language: AppLanguage, limit: Int): NewsResult {
        val request = Request.Builder()
            .url(feedUrl(language))
            .header("User-Agent", "SanctaFides/1.0 (Catholic companion app)")
            .build()
        return runCatching {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use NewsResult.Failure("HTTP ${response.code}")
                val body = response.body?.string()
                    ?: return@use NewsResult.Failure("empty response")
                NewsResult.Success(parse(body, limit))
            }
        }.getOrElse { NewsResult.Failure(it.message ?: it::class.java.simpleName) }
    }

    /**
     * Pulls title, link, date and description out of the feed's `item`
     * elements. The descriptions arrive as HTML, so the markup is stripped —
     * a news list wants a sentence, not a paragraph of tags.
     */
    private fun parse(xml: String, limit: Int): List<VaticanNewsItem> {
        val parser = XmlPullParserFactory.newInstance().apply {
            isNamespaceAware = false
        }.newPullParser()
        parser.setInput(StringReader(xml))

        val items = mutableListOf<VaticanNewsItem>()
        var inItem = false
        var title = ""
        var description = ""
        var link = ""
        var date = ""
        var current = ""

        var event = parser.eventType
        while (event != XmlPullParser.END_DOCUMENT && items.size < limit) {
            when (event) {
                XmlPullParser.START_TAG -> {
                    current = parser.name.orEmpty()
                    if (current.equals("item", ignoreCase = true)) {
                        inItem = true
                        title = ""; description = ""; link = ""; date = ""
                    }
                }

                XmlPullParser.TEXT, XmlPullParser.CDSECT -> if (inItem) {
                    val text = parser.text.orEmpty().trim()
                    if (text.isNotEmpty()) when {
                        current.equals("title", true) -> title += text
                        current.equals("description", true) -> description += text
                        current.equals("link", true) -> link += text
                        current.equals("pubDate", true) -> date += text
                    }
                }

                XmlPullParser.END_TAG -> {
                    if (parser.name.equals("item", ignoreCase = true)) {
                        inItem = false
                        if (title.isNotBlank()) {
                            items += VaticanNewsItem(
                                title = title.stripHtml(),
                                summary = description.stripHtml(),
                                link = link.trim(),
                                publishedAt = date.trim()
                            )
                        }
                    }
                    current = ""
                }
            }
            event = parser.next()
        }
        return items
    }

    private fun String.stripHtml(): String =
        replace(Regex("<[^>]*>"), " ")
            .replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&#39;", "'")
            .replace("&laquo;", "«")
            .replace("&raquo;", "»")
            .replace(Regex("\\s+"), " ")
            .trim()
}
