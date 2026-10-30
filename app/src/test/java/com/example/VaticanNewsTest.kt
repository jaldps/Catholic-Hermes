package com.example

import com.example.data.models.AppLanguage
import com.example.data.news.NewsResult
import com.example.data.news.VaticanNewsFetcher
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.robolectric.annotation.Config
import kotlinx.coroutines.runBlocking

/**
 * The news section used to show two hard-coded articles. It now reads the
 * Vatican's own RSS feed, so these check the parser against a real capture of
 * that feed rather than a hand-made fixture.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class VaticanNewsTest {

    private val feed: String =
        javaClass.classLoader!!.getResourceAsStream("vaticannews_es.rss.xml")!!
            .bufferedReader().use { it.readText() }

    /** Serves the captured feed instead of going to the network. */
    private fun fetcherServing(body: String, code: Int = 200) = VaticanNewsFetcher(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(code)
                    .message(if (code == 200) "OK" else "Error")
                    .body(body.toResponseBody())
                    .build()
            }
            .build()
    )

    @Test
    fun feedUrlsCoverEveryLanguage() {
        assertTrue(VaticanNewsFetcher.feedUrl(AppLanguage.ES).endsWith("/es.rss.xml"))
        assertTrue(VaticanNewsFetcher.feedUrl(AppLanguage.EN).endsWith("/en.rss.xml"))
        assertTrue(VaticanNewsFetcher.feedUrl(AppLanguage.PT).endsWith("/pt.rss.xml"))
    }

    @Test
    fun parsesTheRealFeed() = runBlocking {
        val result = fetcherServing(feed).latest(AppLanguage.ES, limit = 25)
        assertTrue("expected success, got $result", result is NewsResult.Success)
        val items = (result as NewsResult.Success).items

        assertEquals(25, items.size)
        for (item in items) {
            assertTrue("blank title", item.title.isNotBlank())
            assertTrue("link is not a Vatican URL: ${item.link}", item.link.startsWith("https://www.vaticannews.va"))
            // The descriptions arrive as HTML; no tag may survive into the UI.
            assertTrue("markup left in title: ${item.title}", !item.title.contains("<"))
            assertTrue("markup left in summary: ${item.summary.take(80)}", !item.summary.contains("<"))
            assertTrue("entity left in summary: ${item.summary.take(80)}", !item.summary.contains("&nbsp;"))
        }
    }

    @Test
    fun respectsTheRequestedLimit() = runBlocking {
        val result = fetcherServing(feed).latest(AppLanguage.ES, limit = 5)
        assertEquals(5, (result as NewsResult.Success).items.size)
    }

    @Test
    fun reportsFailureRatherThanThrowing() = runBlocking {
        val serverError = fetcherServing("", code = 503).latest(AppLanguage.ES)
        assertTrue("expected failure, got $serverError", serverError is NewsResult.Failure)

        val garbage = fetcherServing("not xml at all").latest(AppLanguage.ES)
        assertTrue("expected failure, got $garbage", garbage is NewsResult.Failure)
    }
}
