package com.example

import com.example.data.location.ParishFinder
import com.example.data.location.ParishLookupResult
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.robolectric.annotation.Config

/**
 * The in-app parish list was reported stuck on "Searching". The cause was a
 * blocking call that a coroutine timeout cannot interrupt, with the mirrors
 * tried one after another. These pin down the fix.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class ParishFinderTest {

    private val oneChurch = """
        {"elements":[{"type":"node","id":7,"lat":19.43,"lon":-99.13,
          "tags":{"name":"Parroquia de San José","amenity":"place_of_worship",
                  "religion":"christian","denomination":"catholic"}}]}
    """.trimIndent()

    /** Serves [fastHost] immediately and makes every other host hang. */
    private fun client(fastHost: String?) = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val host = chain.request().url.host
            if (host != fastHost) Thread.sleep(120_000)
            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("OK")
                .body(oneChurch.toResponseBody())
                .build()
        }
        .build()

    @Test
    fun aHangingNetworkEndsInFailureNotAnEndlessSpinner() = runBlocking {
        val started = System.currentTimeMillis()
        val result = ParishFinder(client(fastHost = null)).findNearby(19.43, -99.13)
        val seconds = (System.currentTimeMillis() - started) / 1000.0
        assertTrue("expected a failure, got $result", result is ParishLookupResult.Failure)
        assertTrue("took ${seconds}s; must give up within the 20s bound", seconds < 25)
    }

    @Test
    fun oneFastMirrorAnswersWithoutWaitingForTheSlowOnes() = runBlocking {
        val started = System.currentTimeMillis()
        val result = ParishFinder(client(fastHost = "overpass.private.coffee")).findNearby(19.43, -99.13)
        val seconds = (System.currentTimeMillis() - started) / 1000.0
        assertTrue("expected success, got $result", result is ParishLookupResult.Success)
        assertEquals("Parroquia de San José", (result as ParishLookupResult.Success).parishes.first().name)
        assertTrue("took ${seconds}s; the mirrors must be asked at once", seconds < 5)
    }
}
