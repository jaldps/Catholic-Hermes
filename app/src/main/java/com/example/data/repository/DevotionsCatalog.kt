package com.example.data.repository

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.data.models.ChristManifestation
import com.example.data.models.LocalizedText
import com.example.data.models.MarianApparition
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

/** Override block for promises, miracles or prayer: keep (absent), remove, or replace. */
internal data class RawDevotionOverride(
    val remove: Boolean? = null, val text: RawLT? = null, val attribution: RawLT? = null,
    val source: String? = null
)
internal data class RawDevotionDetails(
    val id: String? = null, val recognition: RawLT? = null, val practice: RawLT? = null,
    val promises: RawDevotionOverride? = null, val miracles: RawDevotionOverride? = null,
    val prayer: RawDevotionOverride? = null, val sources: List<String>? = null
)
internal data class RawDevotionNew(
    val id: String? = null, val title: RawLT? = null, val feastDay: RawLT? = null, val year: String? = null,
    val location: RawLT? = null, val primarySanctuary: RawLT? = null, val history: RawLT? = null,
    val theologicalMeaning: RawLT? = null, val promises: RawDevotionOverride? = null,
    val miracles: RawLT? = null, val prayer: RawDevotionOverride? = null,
    val region: String? = null, val country: RawLT? = null, val patronageOf: RawLT? = null,
    val recognition: RawLT? = null, val practice: RawLT? = null, val sources: List<String>? = null
)

/**
 * Merges the optional JSON files in the `devotions` asset folder over [DevotionsAndHistoryData].
 * Missing or malformed files are ignored, so the base data always shows.
 */
class DevotionsCatalog(private val open: (String) -> InputStream) {
    companion object {
        const val MARIAN_DETAILS = "marian-details.json"
        const val CHRIST_DETAILS = "christ-details.json"
        const val MARIAN_NEW = "marian-new.json"
        const val CHRIST_NEW = "christ-new.json"

        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: DevotionsCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled by lazy {
            DevotionsCatalog { file ->
                assets?.open("devotions/$file")
                    ?: DevotionsCatalog::class.java.classLoader!!.getResourceAsStream(file)
                    ?: error("Devotions asset unavailable")
            }
        }
        val default: DevotionsCatalog get() = override ?: bundled
    }

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private inline fun <reified T> read(name: String): List<T> = try {
        val adapter = moshi.adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java))
        open(name).bufferedReader().use { adapter.fromJson(it.readText()) }.orEmpty().filterNotNull()
    } catch (e: Exception) {
        AppLog.failure(LogEvent.DEVOTION_CATALOG,e)
        if (e !is java.io.FileNotFoundException && e.message?.contains("unavailable") != true)
            runCatching { Log.w("DevotionsCatalog", "Ignoring $name: ${e.message}") }
        emptyList()
    }

    private val empty = LocalizedText("", "", "")
    private fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
    private fun RawLT?.ltOrNull() = lt().takeIf { it.es.isNotBlank() || it.en.isNotBlank() || it.pt.isNotBlank() }
    private fun List<String>?.clean() = orEmpty().map { it.trim() }.filter { it.isNotEmpty() }

    private fun details(file: String): Map<String, RawDevotionDetails> =
        read<RawDevotionDetails>(file)
            .mapNotNull { d -> d.id?.takeIf { it.isNotBlank() }?.let { it to d } }.toMap()

    private val marianDetails by lazy { details(MARIAN_DETAILS) }
    private val christDetails by lazy { details(CHRIST_DETAILS) }

    private data class Promises(val text: LocalizedText, val attribution: LocalizedText?, val source: String?)
    private data class Prayer(val text: LocalizedText, val source: String?)

    private fun RawDevotionOverride?.promises(current: LocalizedText, attribution: LocalizedText?, source: String?): Promises {
        if (this == null) return Promises(current, attribution, source)
        if (remove == true) return Promises(empty, null, null)
        val t = text.ltOrNull() ?: return Promises(current, attribution, source)
        return Promises(t, this.attribution.ltOrNull(), this.source?.trim()?.takeIf { it.isNotEmpty() })
    }

    private fun RawDevotionOverride?.prayer(current: LocalizedText, source: String?): Prayer {
        if (this == null) return Prayer(current, source)
        if (remove == true) return Prayer(empty, null)
        val t = text.ltOrNull() ?: return Prayer(current, source)
        return Prayer(t, this.source?.trim()?.takeIf { it.isNotEmpty() })
    }

    private fun RawDevotionOverride?.miracles(current: LocalizedText): LocalizedText =
        if (this == null) current else if (remove == true) empty else text.ltOrNull() ?: current

    private fun MarianApparition.withDetails(): MarianApparition {
        val d = marianDetails[id] ?: return this
        val p = d.promises.promises(promises, promiseAttribution, promiseSource)
        val pr = d.prayer.prayer(venerationPrayer, prayerSource)
        return copy(
            recognition = d.recognition.ltOrNull(), practice = d.practice.ltOrNull(), sources = d.sources.clean(),
            promises = p.text, promiseAttribution = p.attribution, promiseSource = p.source,
            recordedMiracles = d.miracles.miracles(recordedMiracles),
            venerationPrayer = pr.text, prayerSource = pr.source
        )
    }

    private fun ChristManifestation.withDetails(): ChristManifestation {
        val d = christDetails[id] ?: return this
        val p = d.promises.promises(promises, promiseAttribution, promiseSource)
        val pr = d.prayer.prayer(officialPrayer, prayerSource)
        return copy(
            recognition = d.recognition.ltOrNull(), practice = d.practice.ltOrNull(), sources = d.sources.clean(),
            promises = p.text, promiseAttribution = p.attribution, promiseSource = p.source,
            recordedMiracles = d.miracles.miracles(recordedMiracles),
            officialPrayer = pr.text, prayerSource = pr.source
        )
    }

    private fun RawDevotionNew.valid(seen: MutableSet<String>): String? {
        val key = id?.takeIf { it.isNotBlank() } ?: return null
        if (title.ltOrNull() == null || history.ltOrNull() == null) return null
        return key.takeIf { seen.add(it) }
    }

    val marian: List<MarianApparition> by lazy {
        val base = DevotionsAndHistoryData.marianApparitions
        val seen = base.map { it.id }.toMutableSet()
        val added = read<RawDevotionNew>(MARIAN_NEW).mapNotNull { r ->
            val key = r.valid(seen) ?: return@mapNotNull null
            val p = r.promises.promises(empty, null, null)
            val pr = r.prayer.prayer(empty, null)
            MarianApparition(
                key, r.title.lt(), r.feastDay.lt(), r.year.orEmpty(), r.location.lt(), r.primarySanctuary.lt(),
                r.history.lt(), p.text, r.miracles.lt(), pr.text,
                region = r.region?.takeIf { it.isNotBlank() } ?: "Universal",
                country = r.country.ltOrNull() ?: LocalizedText("Universal", "Universal", "Universal"),
                patronageOf = r.patronageOf.lt(),
                recognition = r.recognition.ltOrNull(), practice = r.practice.ltOrNull(), sources = r.sources.clean(),
                promiseAttribution = p.attribution, promiseSource = p.source, prayerSource = pr.source
            )
        }
        (base + added).map { it.withDetails() }
    }

    val christ: List<ChristManifestation> by lazy {
        val base = DevotionsAndHistoryData.christManifestations
        val seen = base.map { it.id }.toMutableSet()
        val added = read<RawDevotionNew>(CHRIST_NEW).mapNotNull { r ->
            val key = r.valid(seen) ?: return@mapNotNull null
            val p = r.promises.promises(empty, null, null)
            val pr = r.prayer.prayer(empty, null)
            ChristManifestation(
                key, r.title.lt(), r.feastDay.lt(), r.history.lt(), p.text, r.theologicalMeaning.lt(), pr.text,
                region = r.region?.takeIf { it.isNotBlank() } ?: "Universal",
                country = r.country.ltOrNull() ?: LocalizedText("Universal", "Universal", "Universal"),
                primarySanctuary = r.primarySanctuary.lt(), recordedMiracles = r.miracles.lt(),
                year = r.year.orEmpty(),
                recognition = r.recognition.ltOrNull(), practice = r.practice.ltOrNull(), sources = r.sources.clean(),
                promiseAttribution = p.attribution, promiseSource = p.source, prayerSource = pr.source
            )
        }
        (base + added).map { it.withDetails() }
    }
}
