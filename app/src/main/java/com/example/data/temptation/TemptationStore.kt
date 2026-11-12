package com.example.data.temptation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private val Context.temptationDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "temptation_counts"
)

/**
 * Per-day counts of temptations overcome (victories) and of falls, keyed
 * `yyyy-MM-dd` (DataStore).
 *
 * Daily semantics: each date holds its own count, so the daily counter
 * naturally reads 0 on a new day — there is no stored "today" to reset.
 * Monthly semantics: [cumulativeForMonth] sums only within the requested
 * month, so the running total restarts from zero when the month rolls over.
 * Falls are a separate counter under a separate key prefix, so the two
 * numbers can never be summed by accident.
 * No database, no account, no network — the data never leaves the device.
 */
class TemptationStore(
    private val context: Context,
    private val dataStore: DataStore<Preferences> = context.temptationDataStore,
) {
    private fun keyFor(dateKey: String) = intPreferencesKey("temptation_count_$dateKey")
    private fun fellKeyFor(dateKey: String) = intPreferencesKey("temptation_fall_count_$dateKey")

    /** All per-date counts: `"yyyy-MM-dd" -> temptations overcome that day`. */
    val counts: Flow<Map<String, Int>> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { prefs -> extractCounts(prefs, VICTORY_PREFIX) }

    /** All per-date fall counts: `"yyyy-MM-dd" -> falls that day`. Separate
     * from [counts] — same shape, never summed with victories. */
    val fellCounts: Flow<Map<String, Int>> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { prefs -> extractCounts(prefs, FALL_PREFIX) }

    /** Records one more temptation overcome on [date] (defaults to today). */
    suspend fun recordVictory(date: LocalDate = LocalDate.now()) {
        val key = keyFor(dateKey(date))
        dataStore.edit { prefs ->
            prefs[key] = (prefs[key] ?: 0) + 1
        }
    }

    /** Records one more fall on [date] (defaults to today). */
    suspend fun recordFall(date: LocalDate = LocalDate.now()) {
        val key = fellKeyFor(dateKey(date))
        dataStore.edit { prefs ->
            prefs[key] = (prefs[key] ?: 0) + 1
        }
    }

    /** Temptations overcome on [date] — 0 for a fresh day (the daily reset). */
    fun countFor(counts: Map<String, Int>, date: LocalDate): Int = counts[dateKey(date)] ?: 0

    /** Falls on [date] — 0 for a fresh day (the daily reset). */
    fun fellCountFor(counts: Map<String, Int>, date: LocalDate): Int = counts[dateKey(date)] ?: 0

    companion object {
        private const val VICTORY_PREFIX = "temptation_count_"
        private const val FALL_PREFIX = "temptation_fall_count_"
        private val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val DATE_SUFFIX = Regex("""^\d{4}-\d{2}-\d{2}$""")

        fun dateKey(date: LocalDate): String = date.format(DATE_FORMAT)

        /** Extracts `"yyyy-MM-dd" -> count` entries under [prefix]. The suffix
         * must be a plain date, so victim and fall keys can never bleed into
         * each other's maps (without this a fall key, stripped of the victory
         * prefix it does not carry, would surface as a pseudo-date). */
        fun extractCounts(prefs: Preferences, prefix: String): Map<String, Int> =
            prefs.asMap()
                .entries
                .mapNotNull { (key, value) ->
                    if (!key.name.startsWith(prefix)) return@mapNotNull null
                    val dateKey = key.name.removePrefix(prefix)
                    if (!DATE_SUFFIX.matches(dateKey)) return@mapNotNull null
                    (value as? Int)?.let { dateKey to it }
                }
                .toMap()

        /**
         * Cumulative temptations overcome per day of [month]: each value is
         * the total from the 1st of the month through that day, inclusive.
         * Days outside [month] never contribute, so a new month starts at 0
         * (the monthly reset). Days with no victories carry the running total.
         * Works for any per-date map, so the fall counter shares it.
         */
        fun cumulativeForMonth(counts: Map<String, Int>, month: YearMonth): Map<Int, Int> {
            var running = 0
            return (1..month.lengthOfMonth()).associateWith { day ->
                running += counts[dateKey(month.atDay(day))] ?: 0
                running
            }
        }

        /** Sum of the map for the calendar week (Mon–Sun) containing [date]. */
        fun weeklyCount(counts: Map<String, Int>, date: LocalDate): Int {
            val monday = date.with(
                java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)
            )
            return (0..6).sumOf { offset -> counts[dateKey(monday.plusDays(offset.toLong()))] ?: 0 }
        }

        /** Sum of the map for the calendar month containing [date]. */
        fun monthlyCount(counts: Map<String, Int>, date: LocalDate): Int =
            counts.entries
                .filter { (key, _) -> key.startsWith(dateKey(date).substring(0, 7)) }
                .sumOf { it.value }

        /** Groups raw rows into the per-date count map. */
        fun countsFrom(rows: List<com.example.data.local.HabitCompletionLogEntity>): Map<String, Int> =
            rows.groupBy({ it.completionDate }, { it.habitId })
                .mapValues { (_, habitIds) -> habitIds.distinct().size }
    }
}
