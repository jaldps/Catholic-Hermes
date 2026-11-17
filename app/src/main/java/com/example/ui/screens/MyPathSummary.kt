package com.example.ui.screens

import com.example.data.fidelity.FidelityStore
import com.example.data.models.Novena
import com.example.data.repository.PlanProgress
import com.example.data.repository.PlanProgressMath
import com.example.data.repository.ReadingPlan
import com.example.data.repository.dayNumbers
import java.time.LocalDate

/** Today's rule of life: how many practices are kept and which ones are still open, in the rule's own order. */
data class HabitsToday(val done: Int, val total: Int, val pendingIds: List<String>) {
    val pending: Int get() = pendingIds.size
}

/** A reading plan that has begun and is not finished: [day] is today's day in it. */
data class PlanInProgress(val plan: ReadingPlan, val day: Int, val percent: Int)

/** A novena with some days prayed and some still to pray: [nextDay] is the first one not yet prayed. */
data class NovenaInProgress(val novena: Novena, val nextDay: Int, val prayed: Int, val total: Int)

/**
 * What «Hoy en mi camino» shows. Pure rules over data the app already stores (habit completions, plan progress,
 * novena days): nothing here is saved, so nothing can drift from the screens that own the records.
 */
object MyPathSummaryMath {
    fun habitsToday(ruleIds: List<String>, keptToday: Set<String>): HabitsToday {
        val pending = ruleIds.filter { it !in keptToday }
        return HabitsToday(done = ruleIds.size - pending.size, total = ruleIds.size, pendingIds = pending)
    }

    /**
     * Consecutive days with at least one practice kept, counted back from today. A day that is still open does not
     * break the run: when nothing is kept yet today the count starts from yesterday, so the number never drops to zero
     * in the middle of the morning.
     */
    fun fidelityStreak(counts: Map<String, Int>, today: LocalDate): Int {
        fun kept(day: LocalDate) = (counts[FidelityStore.dateKey(day)] ?: 0) > 0
        var day = if (kept(today)) today else today.minusDays(1)
        var run = 0
        while (kept(day)) { run++; day = day.minusDays(1) }
        return run
    }

    /** Plans started and not completed, in the catalogue's order. */
    fun plansInProgress(plans: List<ReadingPlan>, progress: Map<String, PlanProgress>, todayEpochDay: Long): List<PlanInProgress> =
        plans.mapNotNull { plan ->
            val state = progress[plan.id] ?: return@mapNotNull null
            val start = state.startEpochDay ?: return@mapNotNull null
            if (state.done.count { it in 1..plan.totalDays } >= plan.totalDays) return@mapNotNull null
            PlanInProgress(
                plan = plan,
                day = PlanProgressMath.dayIndex(start, todayEpochDay, plan.totalDays),
                percent = PlanProgressMath.percent(state.done, plan.totalDays)
            )
        }

    /** Novenas begun and not finished, the most recently started first. */
    fun novenasInProgress(novenas: List<Novena>, daysPrayed: Map<String, Set<Int>>, startedOn: Map<String, Long>): List<NovenaInProgress> =
        novenas.mapNotNull { novena ->
            val required = novena.dayNumbers()
            val prayed = daysPrayed[novena.id].orEmpty()
            if (required.isEmpty() || prayed.none { it in required }) return@mapNotNull null
            val next = required.firstOrNull { it !in prayed } ?: return@mapNotNull null
            NovenaInProgress(novena, nextDay = required.indexOf(next) + 1, prayed = required.count { it in prayed }, total = required.size)
        }.sortedByDescending { startedOn[it.novena.id] ?: 0L }
}
