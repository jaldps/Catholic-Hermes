package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.data.models.LocalizedText
import com.example.data.models.NovenaPromise
import com.example.data.models.PrayerCategory
import com.example.data.models.PrayerDetails
import com.example.data.models.PromiseKind
import com.example.data.models.VaticanPrayer
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

internal data class RawLT(val es: String? = null, val en: String? = null, val pt: String? = null)
internal data class RawPrayerPromise(
    val kind: String? = null, val text: RawLT? = null, val attribution: RawLT? = null,
    val source: RawLT? = null, val conditions: RawLT? = null
)
internal data class RawPrayerEntry(
    val prayerId: String? = null, val id: String? = null,
    val history: RawLT? = null, val instructions: RawLT? = null, val source: RawLT? = null,
    val approval: RawLT? = null, val promise: RawPrayerPromise? = null, val textNote: RawLT? = null,
    val title: RawLT? = null, val latinTitle: String? = null, val category: String? = null,
    val leaderText: RawLT? = null, val responseText: RawLT? = null, val whenToPray: RawLT? = null,
    val indulgenceNote: RawLT? = null, val thematicTags: List<String>? = null
)

internal val PrayerDetailFiles = listOf("details-a.json", "details-b.json")
internal const val PrayerExtraFile = "extra.json"
internal const val PrayerOverridesFile = "text-overrides.json"
/** Second overrides file (task T-009, Hermes) so two agents never edit the same file. */
internal val PrayerOverridesFiles = listOf(PrayerOverridesFile, "text-overrides-b.json")

/**
 * Loads optional prayer details and extra prayers from the `prayers` asset folder.
 * Missing or malformed files are ignored.
 */
class PrayerDetailsCatalog(private val open: (String) -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        val default by lazy {
            PrayerDetailsCatalog { file ->
                assets?.open("prayers/$file")
                    ?: PrayerDetailsCatalog::class.java.classLoader!!.getResourceAsStream(file)
                    ?: error("Prayer asset unavailable")
            }
        }
    }

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter<List<RawPrayerEntry>>(
        Types.newParameterizedType(List::class.java, RawPrayerEntry::class.java)
    )

    private fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
    private fun RawLT?.ltOrNull() = this?.lt()
    private fun RawPrayerEntry.toDetails(id: String) = PrayerDetails(
        id, history.lt(), instructions.lt(), source.lt(), approval.lt(),
        promise?.let {
            NovenaPromise(PromiseKind.parse(it.kind) ?: PromiseKind.NONE, it.text.lt(), it.attribution.lt(), it.source.lt(), it.conditions.lt())
        },
        textNote.lt()
    )

    private fun read(name: String): List<RawPrayerEntry>? = try {
        open("$name").bufferedReader().use { adapter.fromJson(it.readText()) }
    } catch (e: Exception) {
        // Missing file is expected while content is being delivered.
        if (e !is java.io.FileNotFoundException && e.message?.contains("unavailable") != true)
            runCatching { Log.w("PrayerDetailsCatalog", "Ignoring $name: ${e.message}") }
        null
    }

    private val loaded: Triple<Map<String, PrayerDetails>, List<VaticanPrayer>, Map<String, RawPrayerEntry>> by lazy {
        val details = LinkedHashMap<String, PrayerDetails>()
        for (f in PrayerDetailFiles) read(f)?.forEach { e ->
            val id = e.prayerId ?: e.id
            if (!id.isNullOrBlank()) details[id] = e.toDetails(id)
        }
        val extras = ArrayList<VaticanPrayer>()
        read(PrayerExtraFile)?.forEach { e ->
            val id = e.id ?: e.prayerId
            if (id.isNullOrBlank() || e.title == null || e.leaderText == null) return@forEach
            if (extras.any { it.id == id }) return@forEach
            details.putIfAbsent(id, e.toDetails(id))
            extras += VaticanPrayer(
                id = id, title = e.title.lt(), latinTitle = e.latinTitle,
                category = PrayerCategory.entries.firstOrNull { it.name.equals(e.category?.trim(), ignoreCase = true) }
                    ?: PrayerCategory.INTERCESSION,
                leaderText = e.leaderText.lt(), responseText = e.responseText.ltOrNull(),
                whenToPray = e.whenToPray.lt(), indulgenceNote = e.indulgenceNote.ltOrNull(),
                thematicTags = e.thematicTags.orEmpty()
            )
        }
        val overrides = LinkedHashMap<String, RawPrayerEntry>()
        PrayerOverridesFiles.forEach { f -> read(f)?.forEach { e ->
            val id = e.id ?: e.prayerId
            if (!id.isNullOrBlank() && e.leaderText != null) overrides[id] = e
        } }
        overrides.forEach { (id, o) ->
            val d = details[id]
            if (d != null && o.textNote != null) details[id] = d.copy(textNote = o.textNote.lt())
        }
        Triple(details, extras, overrides)
    }

    val detailsById: Map<String, PrayerDetails> get() = loaded.first
    val extraPrayers: List<VaticanPrayer> get() = loaded.second

    /** [existing] with text overrides applied, followed by the extra prayers whose ids do not collide with it. */
    fun mergeInto(existing: List<VaticanPrayer>): List<VaticanPrayer> {
        val ids = existing.mapTo(HashSet()) { it.id }
        val overrides = loaded.third
        return (existing + extraPrayers.filter { ids.add(it.id) }).map { p ->
            val o = overrides[p.id] ?: return@map p
            p.copy(
                title = o.title?.lt() ?: p.title,
                latinTitle = o.latinTitle ?: p.latinTitle,
                leaderText = o.leaderText.lt(),
                // An override responseText with every language blank removes the original response.
                responseText = when {
                    o.responseText == null -> p.responseText
                    o.responseText.lt().let { it.es.isBlank() && it.en.isBlank() && it.pt.isBlank() } -> null
                    else -> o.responseText.lt()
                },
                whenToPray = o.whenToPray?.lt() ?: p.whenToPray
            )
        }
    }
}
