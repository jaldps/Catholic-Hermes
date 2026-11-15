package com.example

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.news.ReadingsResult
import com.example.data.news.ReadingsSource
import com.example.data.news.VaticanGospelFetcher
import com.example.data.news.VaticanReadingsCache
import com.example.data.news.chooseReadingsSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class VaticanReadingsCacheTest {
    @get:Rule val tmp = TemporaryFolder()
    private fun feed(lang: String) =
        javaClass.classLoader!!.getResourceAsStream("vaticangospel_$lang.rss.xml")!!.bufferedReader().use { it.readText() }

    @Test fun writesAndReadsKeyedByLanguageAndDate() {
        val cache = VaticanReadingsCache(tmp.newFolder())
        assertNull(cache.read(AppLanguage.ES, "2026-10-31"))
        cache.write(AppLanguage.ES, "2026-10-31", "es-xml")
        cache.write(AppLanguage.EN, "2026-10-31", "en-xml")
        assertEquals("es-xml", cache.read(AppLanguage.ES, "2026-10-31"))
        assertEquals("en-xml", cache.read(AppLanguage.EN, "2026-10-31"))
        assertNull(cache.read(AppLanguage.ES, "2026-11-01"))
        assertNull(cache.read(AppLanguage.PT, "2026-10-31"))
    }

    @Test fun keepsOnlyTheLastSevenDays() {
        val cache = VaticanReadingsCache(tmp.newFolder())
        (1..10).forEach { cache.write(AppLanguage.ES, "2026-10-%02d".format(it), "x$it") }
        assertNull(cache.read(AppLanguage.ES, "2026-10-03"))
        assertNotNull(cache.read(AppLanguage.ES, "2026-10-04"))
        assertNotNull(cache.read(AppLanguage.ES, "2026-10-10"))
    }

    @Test fun orderIsNetworkThenCacheThenNothing() = runBlocking {
        val cache = VaticanReadingsCache(tmp.newFolder())
        val xml = feed("es")
        // Online: succeeds and fills the cache.
        val online = VaticanGospelFetcher(cache = cache, rawLoader = { xml })
        assertTrue(online.today(AppLanguage.ES, "2026-10-31") is ReadingsResult.Success)
        assertNotNull(cache.read(AppLanguage.ES, "2026-10-31"))
        // Offline: served from the cache for that date.
        val offline = VaticanGospelFetcher(cache = cache, rawLoader = { error("offline") })
        assertTrue(offline.today(AppLanguage.ES, "2026-10-31") is ReadingsResult.Success)
        // Offline, other date or language: no cache, so the caller falls back to bundled.
        val miss = offline.today(AppLanguage.ES, "2026-11-01")
        assertTrue(miss is ReadingsResult.Failure)
        assertTrue(offline.today(AppLanguage.EN, "2026-10-31") is ReadingsResult.Failure)
        assertEquals(ReadingsSource.BUNDLED, chooseReadingsSource(miss, true).source)
    }

    @Test fun choiceShowsLocalNoteOnlyWhenVaticanReadingsAndCalendarDiffers() {
        val ok = VaticanGospelFetcher().parse(feed("es"), "2026-10-31")
        assertEquals(ReadingsSource.LOADING, chooseReadingsSource(null, false).source)
        assertTrue(chooseReadingsSource(ok, false).showLocalNote)
        assertFalse(chooseReadingsSource(ok, true).showLocalNote)
        assertFalse(chooseReadingsSource(ReadingsResult.Failure("x"), false).showLocalNote)
    }
}
