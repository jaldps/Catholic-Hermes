package com.example

import com.example.data.models.LocalizedText
import com.example.data.repository.PlanProgress
import com.example.data.repository.ReadingPlan
import com.example.data.repository.ReadingPlanDay
import com.example.ui.screens.MyPathSummaryMath
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class MyPathSummaryTest {
    private val today = LocalDate.of(2026, 11, 17)
    private fun key(daysAgo: Int) = today.minusDays(daysAgo.toLong()).toString()

    @Test fun streakCountsBackFromTodayAndDoesNotBreakOnAnOpenDay() {
        assertEquals(0, MyPathSummaryMath.fidelityStreak(emptyMap(), today))
        assertEquals(3, MyPathSummaryMath.fidelityStreak(mapOf(key(0) to 2, key(1) to 1, key(2) to 5), today))
        // Nothing kept yet today: the run so far still counts.
        assertEquals(2, MyPathSummaryMath.fidelityStreak(mapOf(key(1) to 1, key(2) to 1), today))
        // A gap ends the run; older days do not count.
        assertEquals(1, MyPathSummaryMath.fidelityStreak(mapOf(key(0) to 1, key(2) to 4, key(3) to 4), today))
        assertEquals(0, MyPathSummaryMath.fidelityStreak(mapOf(key(2) to 1), today))
    }

    @Test fun habitsTodaySplitsDoneFromPendingInRuleOrder() {
        val result = MyPathSummaryMath.habitsToday(listOf("a", "b", "c"), setOf("b", "legacy_non_rule"))
        assertEquals(1, result.done); assertEquals(3, result.total); assertEquals(listOf("a", "c"), result.pendingIds)
    }

    @Test fun onlyStartedUnfinishedPlansAreInProgress() {
        fun plan(id: String, days: Int) = ReadingPlan(id, LocalizedText("a", "a", "a"), LocalizedText("b", "b", "b"),
            (1..days).map { ReadingPlanDay(it, listOf("Gen 1")) })
        val plans = listOf(plan("one", 5), plan("two", 5), plan("three", 5))
        val progress = mapOf(
            "one" to PlanProgress(100L, setOf(1)),
            "two" to PlanProgress(100L, setOf(1, 2, 3, 4, 5)),
            "three" to PlanProgress(null, emptySet())
        )
        val result = MyPathSummaryMath.plansInProgress(plans, progress, 102L)
        assertEquals(listOf("one"), result.map { it.plan.id })
        assertEquals(3, result.single().day); assertEquals(20, result.single().percent)
        assertTrue(MyPathSummaryMath.plansInProgress(plans, emptyMap(), 102L).isEmpty())
    }
}
