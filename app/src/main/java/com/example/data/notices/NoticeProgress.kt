package com.example.data.notices

import android.content.Context
import com.example.data.local.SanctaFidesDao
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.Novena
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.ReminderStore
import com.example.data.repository.NovenaCatalog
import com.example.data.repository.ReadingPlan
import com.example.data.repository.ReadingPlansCatalog
import com.example.ui.screens.DailyRule
import com.example.ui.screens.HabitsToday
import com.example.ui.screens.MyPathSummaryMath
import com.example.ui.screens.NovenaExtraDaysStore
import com.example.ui.screens.novenaDaysPrayed
import kotlinx.coroutines.flow.first
import java.time.LocalDate

/**
 * Today's progress as «Mi camino» computes it (same rules, same records): the novena and the reading plan in progress
 * and the habits kept. Read once, on demand, by the notice receiver and by the «Mi camino» widget; nothing is stored.
 */
data class PathProgress(
    val habits: HabitsToday?,
    val novena: Novena?,
    val novenaDay: Int?,
    val novenaTotal: Int?,
    val plan: ReadingPlan?,
    val planDay: Int?,
    val planDoneToday: Boolean,
    /** A novena started from the Reminders screen by free text: its name is never shown. */
    val namelessNovenaDay: Int?
) {
    val novenaNotice: NovenaNoticeData?
        get() = novena?.let { n -> NovenaNoticeData(NovenaRecipient.of(n.title), novenaDay ?: 1) }
            ?: namelessNovenaDay?.let { NovenaNoticeData(null, it) }
    val planNotice: PlanNoticeData?
        get() = plan?.let { PlanNoticeData(it.title, planDay ?: 1, planDoneToday) }
}

object NoticeProgress {
    suspend fun load(
        context: Context,
        dao: SanctaFidesDao = SanctaFidesDatabase.getInstance(context.applicationContext).dao(),
        store: UserPreferencesStore = UserPreferencesStore(context.applicationContext),
        today: LocalDate = LocalDate.now(),
        novenas: () -> List<Novena>? = { runCatching { NovenaCatalog.default.allNovenas }.getOrNull() },
        plans: () -> List<ReadingPlan>? = { ReadingPlansCatalog.default.load() }
    ): PathProgress {
        val app = context.applicationContext
        val habits = runCatching {
            val kept = dao.getCompletionsForDate(today.toString()).first().map { it.habitId }.toSet()
            MyPathSummaryMath.habitsToday(DailyRule.habits.map { it.id }, kept)
        }.getOrNull()
        val novena = runCatching {
            val persisted = dao.novenaProgressOnce()
            val prayed = novenaDaysPrayed(persisted, dao.novenaDaySnapshot().first(), NovenaExtraDaysStore(app).all())
            MyPathSummaryMath.novenasInProgress(novenas().orEmpty(), prayed,
                persisted.filter { it.startDate > 0L }.associate { it.novenaId to it.startDate }).firstOrNull()
        }.getOrNull()
        val plan = runCatching {
            val progress = store.planProgress.first()
            MyPathSummaryMath.plansInProgress(plans().orEmpty(), progress, today.toEpochDay()).firstOrNull()
                ?.let { it to (it.day in progress[it.plan.id]?.done.orEmpty()) }
        }.getOrNull()
        val nameless = if (novena != null) null else runCatching {
            ReminderStore(app).novena.first()?.takeUnless { it.isComplete(today.toEpochDay()) }?.dayNumber(today.toEpochDay())
        }.getOrNull()
        return PathProgress(habits, novena?.novena, novena?.nextDay, novena?.total, plan?.first?.plan, plan?.first?.day,
            plan?.second ?: false, nameless)
    }
}
