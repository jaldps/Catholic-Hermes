package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

internal data class NovenaMetadata(
    val id: String, val title: LocalizedText, val patron: LocalizedText,
    val category: LocalizedText, val description: LocalizedText,
    val miraculousHistory: LocalizedText, val promises: LocalizedText,
    val imageUrl: String?, val tags: List<String>, val bodyFile: String, val dayNumbers: List<Int>
)
internal data class NovenaBody(val days: List<NovenaDay>)

/** Metadata loads once; only requested bodies enter a bounded, evictable cache. */
class NovenaCatalog(private val open: (String) -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        val default by lazy {
            NovenaCatalog { file -> assets?.open("novenas/$file")
                ?: NovenaCatalog::class.java.classLoader!!.getResourceAsStream(file)
                ?: error("Novena asset unavailable") }
        }
    }
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val metadataAdapter = moshi.adapter<List<NovenaMetadata>>(Types.newParameterizedType(List::class.java,NovenaMetadata::class.java))
    private val bodyAdapter = moshi.adapter(NovenaBody::class.java)
    private data class Cached(val days: List<NovenaDay>, val bytes: Int)
    private val bodies = LinkedHashMap<String,Cached>(4,0.75f,true)
    private var cachedBytes = 0
    private val cacheBudget = 8 * 1024 * 1024

    val allNovenas: List<Novena> by lazy {
        val entries = open("index.json").bufferedReader().use { metadataAdapter.fromJson(it.readText()) } ?: error("Novena index missing")
        require(entries.map { it.id }.distinct().size == entries.size)
        entries.map { metadata ->
            require(metadata.dayNumbers.distinct().size == metadata.dayNumbers.size && metadata.dayNumbers.all { it > 0 })
            Novena(metadata.id,metadata.title,metadata.patron,metadata.category,metadata.description,
                metadata.miraculousHistory,metadata.promises,
                CatalogDays(metadata.dayNumbers) { loadBody(metadata) },metadata.imageUrl,metadata.tags)
        }
    }

    @Synchronized private fun loadBody(metadata: NovenaMetadata): List<NovenaDay> {
        bodies[metadata.id]?.let { return it.days }
        require(metadata.bodyFile.matches(Regex("[0-9]{3}\\.json")))
        val days = open(metadata.bodyFile).bufferedReader().use { bodyAdapter.fromJson(it.readText())?.days }
            ?: error("Novena body missing")
        require(days.map { it.dayNumber } == metadata.dayNumbers)
        fun LocalizedText.cost() = (es.length+en.length+pt.length)*2
        val cost=days.sumOf { it.theme.cost()+it.meditation.cost()+it.prayer.cost()+it.intentionGuidance.cost()+it.scripture.length*2 }
        bodies[metadata.id]=Cached(days,cost);cachedBytes+=cost
        // An oversized single body remains usable; it never causes all bodies to be retained.
        while (cachedBytes>cacheBudget && bodies.size>1) {
            val first=bodies.entries.iterator();val oldest=first.next();cachedBytes-=oldest.value.bytes;first.remove()
        }
        return days
    }
    fun preload(id: String): Novena = allNovenas.first { it.id==id }.let {
        it.copy(days = (it.days as? CatalogDays)?.snapshot() ?: it.days)
    }
    internal fun cachedBodyCount(): Int = synchronized(this) { bodies.size }
}

private class CatalogDays(val numbers: List<Int>, private val load: () -> List<NovenaDay>): AbstractList<NovenaDay>() {
    fun snapshot(): List<NovenaDay> = load()
    override val size: Int get() = numbers.size
    override fun get(index: Int): NovenaDay = load()[index]
}

/** Completion/navigation only needs day identifiers, never the text of every prayer. */
fun Novena.dayNumbers(): List<Int> = (days as? CatalogDays)?.numbers ?: days.map { it.dayNumber }
