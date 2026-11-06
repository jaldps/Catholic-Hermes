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
 * Per-day count of temptations overcome, keyed `yyyy-MM-dd` (DataStore).
 *
 * Daily semantics: each date holds its own count, so the daily counter
 * naturally reads 0 on a new day — there is no stored "today" to reset.
 * Monthly semantics: [cumulativeForMonth] sums only within the requested
 * month, so the running total restarts from zero when the month rolls over.
 * No database, no account, no network — the data never leaves the device.
 */
class TemptationStore(
    private val context: Context,
    private val dataStore: DataStore<Preferences> = context.temptationDataStore,
) {
    private fun keyFor(dateKey: String) = intPreferencesKey("temptation_count_$dateKey")

    /** All per-date counts: `"yyyy-MM-dd" -> temptations overcome that day`. */
    val counts: Flow<Map<String, Int>> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { prefs ->
            prefs.asMap()
                .entries
                .mapNotNull { (key, value) ->
                    val dateKey = key.name.removePrefix("temptation_count_")
                    (value as? Int)?.let { dateKey to it }
                }
                .toMap()
        }

    /** Records one more temptation overcome on [date] (defaults to today). */
    suspend fun recordVictory(date: LocalDate = LocalDate.now()) {
        val key = keyFor(dateKey(date))
        dataStore.edit { prefs ->
            prefs[key] = (prefs[key] ?: 0) + 1
        }
    }

    /** Temptations overcome on [date] — 0 for a fresh day (the daily reset). */
    fun countFor(counts: Map<String, Int>, date: LocalDate): Int = counts[dateKey(date)] ?: 0

    companion object {
        private val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        fun dateKey(date: LocalDate): String = date.format(DATE_FORMAT)

        /**
         * Cumulative temptations overcome per day of [month]: each value is
         * the total from the 1st of the month through that day, inclusive.
         * Days outside [month] never contribute, so a new month starts at 0
         * (the monthly reset). Days with no victories carry the running total.
         */
        fun cumulativeForMonth(counts: Map<String, Int>, month: YearMonth): Map<Int, Int> {
            var running = 0
            return (1..month.lengthOfMonth()).associateWith { day ->
                running += counts[dateKey(month.atDay(day))] ?: 0
                running
            }
        }
    }
}
