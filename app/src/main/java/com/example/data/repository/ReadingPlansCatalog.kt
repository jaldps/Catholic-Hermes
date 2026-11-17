package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.liturgy.PsalmNumberingMap
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.LocalizedText
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

/** One day of a plan: Bible references (Hebrew psalm numbering) or one inclusive Catechism paragraph range. */
data class ReadingPlanDay(val day: Int, val readings: List<String> = emptyList(), val paragraphs: IntRange? = null)

data class ReadingPlan(val id: String, val title: LocalizedText, val description: LocalizedText, val days: List<ReadingPlanDay>) {
    val totalDays: Int get() = days.size
    val isCatechism: Boolean get() = days.any { it.paragraphs != null }
    fun day(number: Int): ReadingPlanDay? = days.getOrNull(number - 1)?.takeIf { it.day == number }
}

internal data class RawPlanDay(val day: Int? = null, val readings: List<String>? = null, val paragraphs: List<Int>? = null)
internal data class RawPlan(val id: String? = null, val title: RawLT? = null, val description: RawLT? = null, val days: List<RawPlanDay>? = null)

/** The reviewed plan assets are the sole source of the readings; nothing is generated. */
class ReadingPlansCatalog(private val open: (String) -> InputStream) {
    companion object {
        val FILES = listOf("bible-year.json", "catechism-year.json")
        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: ReadingPlansCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled = ReadingPlansCatalog { file ->
            assets?.open("plans/$file")
                ?: ReadingPlansCatalog::class.java.classLoader!!.getResourceAsStream(file)
                ?: error("Reading plan asset unavailable")
        }
        val default: ReadingPlansCatalog get() = override ?: bundled
    }

    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RawPlan::class.java)

    private fun parse(file: String): ReadingPlan {
        val raw = open(file).bufferedReader().use { requireNotNull(adapter.fromJson(it.readText())) }
        val id = requireNotNull(raw.id?.takeIf { it.matches(Regex("[a-z0-9_]{1,40}")) })
        fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
            .also { require(it.es.isNotBlank() && it.en.isNotBlank() && it.pt.isNotBlank()) }
        val days = requireNotNull(raw.days).mapIndexed { index, d ->
            require(d.day == index + 1)
            val range = d.paragraphs?.let { p -> require(p.size == 2 && p[0] in 1..p[1]); p[0]..p[1] }
            val readings = d.readings.orEmpty().onEach { require(ScriptureReference.parse(it) != null) }
            require(range != null || readings.isNotEmpty())
            ReadingPlanDay(index + 1, readings, range)
        }
        require(days.isNotEmpty())
        return ReadingPlan(id, raw.title.lt(), raw.description.lt(), days)
    }

    /** Called on IO by the screen; failure remains retryable, without caching an empty catalog. */
    fun load(): List<ReadingPlan>? = try {
        FILES.map(::parse).also { plans -> require(plans.map { it.id }.distinct().size == plans.size) }
    } catch (e: Exception) { AppLog.failure(LogEvent.PLAN_CATALOG, e); null }
}

/** Where a plan reading opens in the Bible reader, in the numbering of the edition being read. */
data class PlanBibleTarget(val bookId: String, val chapter: Int, val verse: Int?)

object ReadingPlanLinks {
    private val numberingOnly = ScriptureResolver({ _, _ -> null }) { _, _, _ -> emptyList() }

    /** Plans cite psalms in Hebrew numbering; the reader of a Vulgate-numbered edition needs the converted chapter. */
    fun bibleTarget(raw: String, editionId: String?): PlanBibleTarget? {
        val reference = ScriptureReference.parse(raw) ?: return null
        val converted = numberingOnly.convert(reference, PsalmNumberingMap.numberingOf(editionId))
        return PlanBibleTarget(converted.bookId, converted.chapter, converted.verseStart)
    }

    /** "CIC 1–8" ("CCC" in English); a single paragraph reads "CIC 8". */
    fun catechismLabel(range: IntRange, english: Boolean): String =
        (if (english) "CCC " else "CIC ") + if (range.first == range.last) "${range.first}" else "${range.first}–${range.last}"
}

/** Progress of one plan: the epoch day it began (null = not started) and its completed day numbers. */
data class PlanProgress(val startEpochDay: Long? = null, val done: Set<Int> = emptySet()) {
    val started: Boolean get() = startEpochDay != null
}

/** Pure progress rules, shared by the store, the screen and the tests. */
object PlanProgressMath {
    /** Today's day in the plan: (today - start) + 1, clamped to 1..[total]. */
    fun dayIndex(startEpochDay: Long, todayEpochDay: Long, total: Int): Int =
        (todayEpochDay - startEpochDay + 1).coerceIn(1L, total.toLong()).toInt()

    /** Days before [today] that are not completed. Never blames: future days are never pending. */
    fun pending(done: Set<Int>, today: Int): List<Int> = (1 until today).filter { it !in done }

    fun percent(done: Set<Int>, total: Int): Int = if (total <= 0) 0 else done.count { it in 1..total } * 100 / total

    /** Start date that makes the first uncompleted day today, keeping completed days; null when everything is done. */
    fun rebasedStart(todayEpochDay: Long, done: Set<Int>, total: Int): Long? {
        val first = (1..total).firstOrNull { it !in done } ?: return null
        return todayEpochDay - (first - 1)
    }

    /** Compact storage: "1-5,7,9-12". Unknown or out-of-range text is ignored on decode. */
    fun encode(done: Set<Int>): String {
        val days = done.filter { it > 0 }.sorted()
        val out = mutableListOf<String>()
        var i = 0
        while (i < days.size) {
            var j = i
            while (j + 1 < days.size && days[j + 1] == days[j] + 1) j++
            out += if (j == i) "${days[i]}" else "${days[i]}-${days[j]}"
            i = j + 1
        }
        return out.joinToString(",")
    }

    fun decode(raw: String?, max: Int = 3660): Set<Int> {
        val out = linkedSetOf<Int>()
        raw.orEmpty().split(',').forEach { part ->
            val bounds = part.trim().split('-')
            val a = bounds.getOrNull(0)?.toIntOrNull() ?: return@forEach
            val b = if (bounds.size == 2) bounds[1].toIntOrNull() ?: return@forEach else a
            if (bounds.size <= 2 && a >= 1 && b >= a && b <= max) for (n in a..b) out += n
        }
        return out
    }
}
