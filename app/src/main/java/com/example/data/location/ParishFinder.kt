package com.example.data.location

import com.example.data.models.NearbyParish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/** A place name resolved to coordinates. */
data class GeocodedPlace(
    val displayName: String,
    val latitude: Double,
    val longitude: Double
)

sealed interface ParishLookupResult {
    data class Success(val parishes: List<NearbyParish>) : ParishLookupResult
    data class Empty(val searchedRadiusKm: Int) : ParishLookupResult
    data class Failure(val reason: String) : ParishLookupResult
}

/**
 * Finds Catholic churches near a coordinate from OpenStreetMap.
 *
 * Overpass and Nominatim are used rather than Google Places because they need
 * no API key and no billing account, so the feature works in a build anyone can
 * compile. Both are community-run: the client identifies itself, keeps the
 * radius modest and accepts a slow reply instead of hammering them.
 */
class ParishFinder(
    private val client: OkHttpClient = defaultClient()
) {

    companion object {
        private const val USER_AGENT = "SanctaFides/1.0 (Catholic companion app)"

        // Several mirrors run the same API; a busy one answers 429 or drops the
        // connection, so the next is tried in turn.
        private val OVERPASS_ENDPOINTS = listOf(
            "https://overpass-api.de/api/interpreter",
            "https://overpass.kumi.systems/api/interpreter",
            "https://overpass.private.coffee/api/interpreter"
        )

        private const val NOMINATIM = "https://nominatim.openstreetmap.org/search"

        /** Seconds before the whole lookup gives up, mirrors tried and all. */
        private const val OVERALL_TIMEOUT_MS = 20_000L

        fun defaultClient(): OkHttpClient = OkHttpClient.Builder()
            // Overpass mirrors are community-run and frequently slow. A long
            // per-request timeout multiplied by three mirrors left the search
            // spinning for minutes with no way out, so each attempt is kept
            // short and the whole lookup is bounded by OVERALL_TIMEOUT_MS.
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    /** Resolves a typed "city, region, country" to coordinates. */
    suspend fun geocode(query: String): GeocodedPlace? = withContext(Dispatchers.IO) {
        if (query.isBlank()) return@withContext null
        withTimeoutOrNull(OVERALL_TIMEOUT_MS) { geocodeInner(query) }
    }

    private suspend fun geocodeInner(query: String): GeocodedPlace? {
        val url = "$NOMINATIM?q=${encode(query)}&format=json&limit=1"
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", USER_AGENT)
            .build()
        return runCatchingNonCancel {
            client.newCall(request).await().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body?.string().orEmpty()
                val array = org.json.JSONArray(body)
                if (array.length() == 0) return@use null
                val first = array.getJSONObject(0)
                GeocodedPlace(
                    displayName = first.optString("display_name"),
                    latitude = first.optString("lat").toDoubleOrNull() ?: return@use null,
                    longitude = first.optString("lon").toDoubleOrNull() ?: return@use null
                )
            }
        }
    }

    /**
     * Catholic churches within [radiusKm] of a point, nearest first.
     *
     * The query asks for `religion=christian` and filters denomination in code
     * rather than in the query, because a great many churches in OSM carry no
     * denomination tag at all; excluding them server-side would hide most
     * parishes in much of the world. Anything explicitly tagged as another
     * denomination is dropped.
     */
    suspend fun findNearby(
        latitude: Double,
        longitude: Double,
        radiusKm: Int = 10
    ): ParishLookupResult = withContext(Dispatchers.IO) {
        withTimeoutOrNull(OVERALL_TIMEOUT_MS) { lookup(latitude, longitude, radiusKm) }
            ?: ParishLookupResult.Failure("timed out")
    }

    private suspend fun lookup(
        latitude: Double,
        longitude: Double,
        radiusKm: Int
    ): ParishLookupResult {
        val radiusMeters = radiusKm * 1000
        val query = """
            [out:json][timeout:20];
            (
              node["amenity"="place_of_worship"]["religion"="christian"](around:$radiusMeters,$latitude,$longitude);
              way["amenity"="place_of_worship"]["religion"="christian"](around:$radiusMeters,$latitude,$longitude);
            );
            out center tags 120;
        """.trimIndent()

        // All mirrors are asked at once and the first usable answer wins. Asked
        // one after another, a single slow mirror held the screen on its
        // spinner for most of a minute.
        return coroutineScope {
            val attempts = OVERPASS_ENDPOINTS.map { endpoint ->
                async { askMirror(endpoint, query, latitude, longitude, radiusKm) }
            }.toMutableList()
            var lastFailure: ParishLookupResult = ParishLookupResult.Failure("unreachable")
            while (attempts.isNotEmpty()) {
                val (finished, outcome) = select {
                    attempts.forEach { attempt -> attempt.onAwait { attempt to it } }
                }
                attempts.remove(finished)
                if (outcome !is ParishLookupResult.Failure) {
                    attempts.forEach { it.cancel() }
                    return@coroutineScope outcome
                }
                lastFailure = outcome
            }
            lastFailure
        }
    }

    private suspend fun askMirror(
        endpoint: String,
        query: String,
        latitude: Double,
        longitude: Double,
        radiusKm: Int
    ): ParishLookupResult {
        val request = Request.Builder()
            .url(endpoint)
            .header("User-Agent", USER_AGENT)
            .post(FormBody.Builder().add("data", query).build())
            .build()
        val body = try {
            client.newCall(request).await().use { response ->
                if (!response.isSuccessful) return ParishLookupResult.Failure("HTTP ${response.code}")
                response.body?.string() ?: return ParishLookupResult.Failure("empty response")
            }
        } catch (e: IOException) {
            return ParishLookupResult.Failure(e.message ?: "network error")
        }
        val parsed = runCatching { parseOverpass(body, latitude, longitude) }.getOrNull()
            ?: return ParishLookupResult.Failure("malformed response")
        return if (parsed.isEmpty()) ParishLookupResult.Empty(radiusKm)
        else ParishLookupResult.Success(parsed)
    }

    private fun parseOverpass(
        body: String,
        fromLat: Double,
        fromLon: Double
    ): List<NearbyParish> {
        val elements = JSONObject(body).optJSONArray("elements") ?: return emptyList()
        val results = mutableListOf<Pair<Double, NearbyParish>>()

        for (i in 0 until elements.length()) {
            val element = elements.optJSONObject(i) ?: continue
            val tags = element.optJSONObject("tags") ?: continue

            val denomination = tags.optString("denomination").lowercase()
            val isCatholic = denomination.isEmpty() ||
                denomination.contains("catholic") ||
                denomination.contains("roman")
            if (!isCatholic) continue

            val lat = element.optDouble("lat").takeIf { !it.isNaN() }
                ?: element.optJSONObject("center")?.optDouble("lat")?.takeIf { !it.isNaN() }
                ?: continue
            val lon = element.optDouble("lon").takeIf { !it.isNaN() }
                ?: element.optJSONObject("center")?.optDouble("lon")?.takeIf { !it.isNaN() }
                ?: continue

            val name = tags.optString("name").ifBlank { continue }
            val distanceKm = haversineKm(fromLat, fromLon, lat, lon)

            results += distanceKm to NearbyParish(
                id = "osm_${element.optString("type")}_${element.optLong("id")}",
                name = name,
                diocese = tags.optString("diocese").ifBlank {
                    tags.optString("operator").ifBlank { "" }
                },
                address = buildAddress(tags),
                country = tags.optString("addr:country"),
                stateOrRegion = tags.optString("addr:state"),
                city = tags.optString("addr:city"),
                latitude = lat,
                longitude = lon,
                distanceDisplay = formatDistance(distanceKm),
                // OSM rarely records Mass times. Showing an empty string is
                // honest; inventing a schedule would send someone to a locked
                // church.
                sundayMasses = tags.optString("service_times"),
                weekdayMasses = "",
                confessionTimes = "",
                adorationTimes = "",
                contactPhone = tags.optString("phone").ifBlank { tags.optString("contact:phone") },
                isPerpetualAdoration = false
            )
        }
        return results.sortedBy { it.first }.map { it.second }
    }

    private fun buildAddress(tags: JSONObject): String {
        val street = tags.optString("addr:street")
        val number = tags.optString("addr:housenumber")
        val city = tags.optString("addr:city")
        val postcode = tags.optString("addr:postcode")
        return listOf(
            listOf(street, number).filter { it.isNotBlank() }.joinToString(" "),
            listOf(postcode, city).filter { it.isNotBlank() }.joinToString(" ")
        ).filter { it.isNotBlank() }.joinToString(", ")
    }

    private fun formatDistance(km: Double): String = when {
        km < 1.0 -> "${(km * 1000).toInt()} m"
        km < 10.0 -> String.format("%.1f km", km)
        else -> "${km.toInt()} km"
    }

    /** Great-circle distance in kilometres. */
    private fun haversineKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadiusKm = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2)
        return 2 * earthRadiusKm * asin(sqrt(a))
    }

    private fun encode(value: String): String =
        java.net.URLEncoder.encode(value, "UTF-8")

    /**
     * Runs the call asynchronously and cancels it if the coroutine is cancelled.
     * A blocking execute() cannot be interrupted by withTimeoutOrNull, which only
     * cancels at suspension points — that is what let the search hang.
     */
    private suspend fun Call.await(): Response = suspendCancellableCoroutine { cont ->
        cont.invokeOnCancellation { cancel() }
        enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) = cont.resume(response)
            override fun onFailure(call: Call, e: IOException) {
                if (!cont.isCancelled) cont.resumeWithException(e)
            }
        })
    }

    private inline fun <T> runCatchingNonCancel(block: () -> T?): T? = try {
        block()
    } catch (e: kotlinx.coroutines.CancellationException) {
        throw e
    } catch (e: Exception) {
        null
    }
}
