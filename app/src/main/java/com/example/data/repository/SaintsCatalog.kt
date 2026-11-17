package com.example.data.repository

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.data.models.LocalizedList
import com.example.data.models.LocalizedText
import com.example.data.models.SaintProfile
import com.example.data.models.SaintQuote
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

internal data class RawLTList(val es: List<String>? = null, val en: List<String>? = null, val pt: List<String>? = null)
internal data class RawSaintQuote(val text: RawLT? = null, val source: String? = null)
internal data class RawSaint(
    val key: String? = null, val month: Int? = null, val day: Int? = null,
    val bio: RawLT? = null, val patronages: RawLTList? = null,
    val quote: RawSaintQuote? = null, val sources: List<String>? = null
)

internal val SaintsFiles = listOf("saints-q1.json", "saints-q2.json", "saints-q3.json", "saints-q4.json")

/** Loads optional saint profiles from the `saints` asset folder. Missing or malformed files are ignored. */
class SaintsCatalog(private val open: (String) -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: SaintsCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled by lazy {
            SaintsCatalog { file ->
                assets?.open("saints/$file")
                    ?: SaintsCatalog::class.java.classLoader!!.getResourceAsStream(file)
                    ?: error("Saints asset unavailable")
            }
        }
        val default: SaintsCatalog get() = override ?: bundled
    }

    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter<List<RawSaint>>(
        Types.newParameterizedType(List::class.java, RawSaint::class.java)
    )

    private fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
    private fun List<String>?.clean() = orEmpty().filter { it.isNotBlank() }

    private fun read(name: String): List<RawSaint>? = try {
        open(name).bufferedReader().use { adapter.fromJson(it.readText()) }
    } catch (e: Exception) {
        AppLog.failure(LogEvent.SAINT_CATALOG,e)
        if (e !is java.io.FileNotFoundException && e.message?.contains("unavailable") != true)
            runCatching { Log.w("SaintsCatalog", "Ignoring $name: ${e.message}") }
        null
    }

    val byKey: Map<String, SaintProfile> by lazy {
        val out = LinkedHashMap<String, SaintProfile>()
        for (f in SaintsFiles) read(f)?.forEach { e ->
            val key = e?.key?.takeIf { it.isNotBlank() } ?: return@forEach
            val quote = e.quote?.takeIf { !it.source.isNullOrBlank() && it.text.lt().let { t -> t.es.isNotBlank() || t.en.isNotBlank() || t.pt.isNotBlank() } }
                ?.let { SaintQuote(it.text.lt(), it.source!!.trim()) }
            out[key] = SaintProfile(
                key, e.month ?: 0, e.day ?: 0, e.bio.lt(),
                LocalizedList(e.patronages?.es.clean(), e.patronages?.en.clean(), e.patronages?.pt.clean()),
                quote, e.sources.clean()
            )
        }
        out
    }
}
