package com.example.data.news

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.Context
import com.example.data.models.AppLanguage
import java.io.File
import java.time.LocalDate

/**
 * Keeps the raw Vatican News readings feed per language and civil date so the
 * day's readings still show offline after one successful load. Only the last
 * [keepDays] days are kept.
 */
class VaticanReadingsCache(private val dir: File, private val keepDays: Int = 7) {

    companion object {
        fun forContext(context: Context): VaticanReadingsCache = VaticanReadingsCache(context.cacheDir)
        private val NAME = Regex("^vatican-readings-([a-z]{2})-(\\d{4}-\\d{2}-\\d{2})\\.xml$")
    }

    fun file(language: AppLanguage, isoDate: String): File =
        File(dir, "vatican-readings-${language.name.lowercase()}-$isoDate.xml")

    fun write(language: AppLanguage, isoDate: String, xml: String) {
        runCatching {
            dir.mkdirs()
            val target = file(language, isoDate)
            val tmp = File(dir, target.name + ".tmp")
            tmp.writeText(xml)
            if (!tmp.renameTo(target)) { target.writeText(xml); tmp.delete() }
            prune(isoDate)
        }.onFailure {AppLog.failure(LogEvent.READINGS_CACHE_WRITE,it)}
    }

    fun read(language: AppLanguage, isoDate: String): String? =
        runCatching { file(language, isoDate).takeIf { it.isFile }?.readText()?.takeIf { it.isNotBlank() } }
            .onFailure {AppLog.failure(LogEvent.READINGS_CACHE_READ,it)}.getOrNull()

    /** Deletes cached feeds older than [keepDays] days before [isoDate]. */
    fun prune(isoDate: String) {
        val today = runCatching { LocalDate.parse(isoDate) }.getOrNull() ?: return
        val oldest = today.minusDays(keepDays.toLong() - 1)
        dir.listFiles()?.forEach { f ->
            val date = NAME.matchEntire(f.name)?.groupValues?.get(2)
                ?.let { runCatching { LocalDate.parse(it) }.getOrNull() } ?: return@forEach
            if (date.isBefore(oldest)) f.delete()
        }
    }
}

/** Which readings the Mass tab and Home show, decided from the feed outcome. */
enum class ReadingsSource { LOADING, VATICAN, BUNDLED }

data class ReadingsChoice(val source: ReadingsSource, val showLocalNote: Boolean)

/**
 * Vatican readings are always primary; the bundled lectionary is used only
 * when neither the network nor the cache produced them. The local note shows
 * when the user's transfer rules celebrate something else that day.
 */
fun chooseReadingsSource(feed: ReadingsResult?, calendarMatches: Boolean): ReadingsChoice = when (feed) {
    null -> ReadingsChoice(ReadingsSource.LOADING, false)
    is ReadingsResult.Success -> ReadingsChoice(ReadingsSource.VATICAN, !calendarMatches)
    is ReadingsResult.Failure -> ReadingsChoice(ReadingsSource.BUNDLED, false)
}

/** Test seam: how a screen obtains the day's readings. */
typealias ReadingsLoader = suspend (AppLanguage, String) -> ReadingsResult

val LocalReadingsLoader = androidx.compose.runtime.compositionLocalOf<ReadingsLoader?> { null }
