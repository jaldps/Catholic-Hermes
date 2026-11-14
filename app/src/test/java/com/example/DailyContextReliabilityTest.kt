package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.liturgy.*
import com.example.data.models.AppLanguage
import com.example.data.news.DailySaintsFetcher
import com.example.data.news.VaticanSaint
import com.example.data.network.awaitText
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.DevotionsAndHistoryData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Collections

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class DailyContextReliabilityTest {
    private fun client(paths: MutableList<String> = Collections.synchronizedList(mutableListOf())) =
        OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            paths += request.url.encodedPath
            val name = if (request.url.encodedPath.contains("20.html")) "St. Audit Twenty" else "St. Audit Twenty One"
            Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                .body("<html><h2>$name</h2><p>A faithful servant of Christ who devoted a long life to prayer, teaching and the care of the poor. The community remembered this example of Christian charity.</p></html>".toResponseBody()).build()
        }.build()

    @Test fun dailyContentAndSaintRefreshTogetherAcrossDateRollover() = runBlocking {
        val date=MutableStateFlow(CalendarDate(2026,3,20))
        val paths: MutableList<String> = Collections.synchronizedList(mutableListOf())
        val repo=CatholicDataRepository(ApplicationProvider.getApplicationContext<Application>(),
            dateProvider={date.value},dateChanges=date,saintFetcher=DailySaintsFetcher(client(paths)))
        try {
            repo.applyPersistedLanguage(AppLanguage.EN)
            withTimeout(10_000) { repo.dailyContent.first { it?.date==date.value && it.language==AppLanguage.EN } }
            withTimeout(10_000) { repo.saintOfTheDayFlow.first { it.id.contains("2026-03-20") } }
            date.value=CalendarDate(2026,3,21)
            withTimeout(10_000) { repo.dailyContent.first { it?.date==date.value } }
            val saint=withTimeout(10_000) { repo.saintOfTheDayFlow.first { it.id.contains("2026-03-21") } }
            assertEquals(date.value,repo.currentDate.value)
            assertTrue(saint.name.en.contains("Twenty One"))
            assertTrue(paths.any { it.endsWith("03/20.html") })
            assertTrue(paths.any { it.endsWith("03/21.html") })
        } finally { repo.close() }
    }

    @Test fun transfersChangeReadingsAndCacheWithoutChangingLanguage() {
        val repo=CatholicDataRepository(saintFetcher=DailySaintsFetcher(client()))
        try {
            repo.applyPersistedLanguage(AppLanguage.EN)
            val date=CalendarDate(2026,5,17)
            val general=repo.getMassReading(date)
            repo.applyCalendarRules(CalendarRules(ascensionOnSunday=true))
            val transferred=repo.getMassReading(date)
            assertTrue(transferred.feastTitle.en.contains("Ascension"))
            assertNotEquals(general.gospelRef,transferred.gospelRef)
            assertFalse(repo.vaticanCalendarMatches(date))
            repo.applyCalendarRules(CalendarRules())
            assertEquals(general,repo.getMassReading(date))
        } finally { repo.close() }
    }

    @Test fun saintMappingUsesCapturedRequestDate() {
        val repo=CatholicDataRepository(saintFetcher=DailySaintsFetcher(client()),dateProvider={CalendarDate(2026,3,21)})
        try {
            val saint=repo.mapVaticanSaint(VaticanSaint("St. Audit", "A documented biography"),DevotionsAndHistoryData.saintOfTheDay,AppLanguage.EN,CalendarDate(2026,3,20))
            assertTrue(saint.id.contains("2026-03-20"))
        } finally { repo.close() }
    }

    @Test fun marianPrayerChangesForEasterThroughPentecost() {
        assertEquals("angelus",SeasonalMarianPrayer.forDate(CalendarDate(2026,4,4)).id)
        assertEquals("regina_caeli",SeasonalMarianPrayer.forDate(CalendarDate(2026,4,5)).id)
        assertEquals("regina_caeli",SeasonalMarianPrayer.forDate(LiturgicalCalendar.pentecost(2026)).id)
        assertEquals("angelus",SeasonalMarianPrayer.forDate(LiturgicalCalendar.pentecost(2026).plusDays(1)).id)
    }

    @Test fun oversizedHttpBodiesAreUnavailableRatherThanReadWithoutLimit() = runBlocking {
        val response=client().awaitText(Request.Builder().url("https://audit.invalid/body").build(),maxBytes=12)
        assertNull(response)
    }
}
