package com.example.data.fidelity

import com.example.data.local.HabitCompletionLogEntity
import com.example.data.local.SanctaFidesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/**
 * Per-date fidelity completions, mirroring the TemptationStore pattern.
 *
 * Daily semantics: each date holds its own count, so the daily counter
 * naturally reads 0 on a new day — there is no stored "today" to reset.
 * Monthly semantics: [cumulativeForMonth] sums only within the requested
 * month, so the running total restarts from zero when the month rolls over.
 *
 * Unlike the temptation counters this is Room-backed, not DataStore-backed:
 * completions already live in `habit_completion_logs`, so this store is a
 * live view over that table (one point per distinct habit kept that day),
 * never a second copy that could drift from the rule of life.
 */
class FidelityStore(
    private val dao: SanctaFidesDao,
) {
    /** All per-date completion counts: `"yyyy-MM-dd" -> practices kept that day`.
     * Only dates with at least one completion appear; absence reads as 0. */
    val counts: Flow<Map<String, Int>> = dao.getAllCompletions().map { rows ->
        rows.groupBy({ it.completionDate }, { it.habitId })
            .mapValues { (_, habitIds) -> habitIds.distinct().size }
    }

    /** Practices kept on [date] — 0 for a fresh day (the daily reset). */
    fun countFor(counts: Map<String, Int>, date: LocalDate): Int = counts[dateKey(date)] ?: 0

    companion object {
        private val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        fun dateKey(date: LocalDate): String = date.format(DATE_FORMAT)

        /**
         * Cumulative completions per day of [month]: each value is the total
         * from the 1st of the month through that day, inclusive. Days outside
         * [month] never contribute, so a new month starts at 0 (the monthly
         * reset). Days with no completions carry the running total.
         */
        fun cumulativeForMonth(counts: Map<String, Int>, month: YearMonth): Map<Int, Int> {
            var running = 0
            return (1..month.lengthOfMonth()).associateWith { day ->
                running += counts[dateKey(month.atDay(day))] ?: 0
                running
            }
        }

        /** Practices kept in the calendar week (Mon–Sun) containing [date]. */
        fun weeklyCount(counts: Map<String, Int>, date: LocalDate): Int {
            val monday = date.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
            return (0..6).sumOf { offset -> counts[dateKey(monday.plusDays(offset.toLong()))] ?: 0 }
        }

        /** Practices kept in the calendar month containing [date]. */
        fun monthlyCount(counts: Map<String, Int>, date: LocalDate): Int =
            counts.entries
                .filter { (key, _) -> key.startsWith(dateKey(date).substring(0, 7)) }
                .sumOf { it.value }

        /** Groups raw completion rows into the per-date count map. */
        fun countsFrom(rows: List<HabitCompletionLogEntity>): Map<String, Int> =
            rows.groupBy({ it.completionDate }, { it.habitId })
                .mapValues { (_, habitIds) -> habitIds.distinct().size }
    }
}
