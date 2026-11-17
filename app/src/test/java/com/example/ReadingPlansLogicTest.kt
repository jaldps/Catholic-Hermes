package com.example

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.test.core.app.ApplicationProvider
import com.example.data.backup.BackupPreferences
import com.example.data.liturgy.CalendarDate
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.ReminderStore
import com.example.data.repository.BibleEditionCatalog
import com.example.data.repository.BibleTextProvider
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.PlanProgressMath
import com.example.data.repository.ReadingPlanLinks
import com.example.data.repository.ReadingPlansCatalog
import com.example.data.temptation.TemptationStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ReadingPlansLogicTest {
    @get:Rule val folder = TemporaryFolder()

    @Test fun catalogLoadsBothPlansWith365Days() {
        val plans = ReadingPlansCatalog.default.load()!!
        assertEquals(listOf("bible_year", "catechism_year"), plans.map { it.id })
        plans.forEach { assertEquals(365, it.totalDays) }
        assertEquals("La Biblia en un año", plans[0].title.es)
        assertEquals(listOf("gen 1", "gen 2", "mat 1", "psa 1"), plans[0].day(1)!!.readings)
        assertFalse(plans[0].isCatechism)
        assertTrue(plans[1].isCatechism)
        assertEquals(1..8, plans[1].day(1)!!.paragraphs)
        assertEquals(2865, plans[1].day(365)!!.paragraphs!!.last)
    }

    @Test fun malformedCatalogReturnsNullWithoutInventingContent() {
        ReadingPlansCatalog { error("missing") }.also { assertNull(it.load()) }
        ReadingPlansCatalog { java.io.ByteArrayInputStream("{}".toByteArray()) }.also { assertNull(it.load()) }
    }

    @Test fun dayIndexIsClampedToThePlan() {
        assertEquals(1, PlanProgressMath.dayIndex(100, 100, 365))
        assertEquals(34, PlanProgressMath.dayIndex(100, 133, 365))
        assertEquals(1, PlanProgressMath.dayIndex(100, 90, 365))
        assertEquals(365, PlanProgressMath.dayIndex(100, 5000, 365))
    }

    @Test fun pendingDaysAreOnlyEarlierUnreadDays() {
        assertEquals(listOf(2, 3), PlanProgressMath.pending(setOf(1, 4), 4))
        assertEquals(emptyList<Int>(), PlanProgressMath.pending(emptySet(), 1))
        assertEquals(36, PlanProgressMath.percent((1..134).toSet(), 365))
    }

    @Test fun compactEncodingRoundTrips() {
        val days = setOf(1, 2, 3, 5, 9, 10, 365)
        assertEquals("1-3,5,9-10,365", PlanProgressMath.encode(days))
        assertEquals(days, PlanProgressMath.decode(PlanProgressMath.encode(days)))
        assertEquals(emptySet<Int>(), PlanProgressMath.decode("x,-,3-1,7-"))
    }

    @Test fun rebaseKeepsCompletedDaysAndMakesTheFirstPendingDayToday() {
        val done = setOf(1, 2, 5)
        val start = PlanProgressMath.rebasedStart(1000, done, 365)!!
        assertEquals(3, PlanProgressMath.dayIndex(start, 1000, 365))
        assertEquals(emptyList<Int>(), PlanProgressMath.pending(done, 3))
        assertNull(PlanProgressMath.rebasedStart(1000, (1..365).toSet(), 365))
    }

    @Test fun storeStartsTogglesRebasesAndRestarts() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val job = SupervisorJob()
        val store = UserPreferencesStore(app, PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + job)) { File(folder.newFolder(), "s.preferences_pb") })
        assertEquals(emptyMap<String, Any>(), store.planProgress.first())
        store.setPlanStart("bible_year", 2000)
        store.setPlanDayDone("bible_year", 1, true); store.setPlanDayDone("bible_year", 2, true); store.setPlanDayDone("bible_year", 4, true)
        assertEquals(setOf(1, 2, 4), store.planProgress.first().getValue("bible_year").done)
        store.setPlanDayDone("bible_year", 2, false)
        assertEquals(setOf(1, 4), store.planProgress.first().getValue("bible_year").done)
        // Three days behind: today is day 4 of the start, but day 2 and 3 are unread.
        store.rebasePlan("bible_year", 2003, 365)
        val rebased = store.planProgress.first().getValue("bible_year")
        assertEquals(setOf(1, 4), rebased.done)
        assertEquals(2, PlanProgressMath.dayIndex(rebased.startEpochDay!!, 2003, 365))
        assertEquals(null, store.planProgress.first()["catechism_year"])
        store.restartPlan("bible_year")
        assertTrue(store.planProgress.first().isEmpty())
        runCatching { store.setPlanStart("Bad Id", 1) }.also { assertTrue(it.isFailure) }
        job.cancel()
    }

    @Test fun backupRoundTripCarriesPlanProgressAndMergesWithoutLoss() = runBlocking {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val job = SupervisorJob()
        fun device(): Pair<UserPreferencesStore, BackupPreferences> {
            val dir = folder.newFolder()
            fun data(name: String) = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + job)) { File(dir, "$name.preferences_pb") }
            val store = UserPreferencesStore(app, data("settings"))
            return store to BackupPreferences(app, store, TemptationStore(app, data("t")), ReminderStore(app, data("r")))
        }
        val (a, aBackup) = device()
        val (b, bBackup) = device()
        a.setPlanStart("catechism_year", 2000); a.setPlanDayDone("catechism_year", 1, true); a.setPlanDayDone("catechism_year", 2, true)
        val snapshot = aBackup.snapshot()
        bBackup.write(snapshot)
        assertEquals(a.planProgress.first(), b.planProgress.first())
        // Merging another phone's progress unions the days and keeps the earlier start.
        b.setPlanDayDone("catechism_year", 7, true); b.setPlanStart("catechism_year", 1990)
        val merged = BackupPreferences.merge(bBackup.snapshot(), snapshot)
        val settings = merged.first { it.name == "settings" }.entries.associateBy { it.key }
        assertEquals("1-2,7", settings.getValue("plan_done_catechism_year").value)
        assertEquals("1990", settings.getValue("plan_start_catechism_year").value)
        job.cancel()
    }

    @Test fun psalmsAreConvertedToTheEditionNumbering() {
        assertEquals(22, ReadingPlanLinks.bibleTarget("psa 23", "bundled-es")!!.chapter)
        assertEquals(50, ReadingPlanLinks.bibleTarget("psa 51", "bundled")!!.chapter)
        assertEquals(23, ReadingPlanLinks.bibleTarget("psa 23", "es.lbl")!!.chapter)
        assertEquals(118, ReadingPlanLinks.bibleTarget("psa 119:89-176", "bundled-es")!!.chapter)
        assertEquals(89, ReadingPlanLinks.bibleTarget("psa 119:89-176", "bundled-es")!!.verse)
        assertEquals(ReadingPlanLinks.bibleTarget("gen 1", "bundled-es")!!.let { it.chapter to it.verse }, 1 to null)
        assertEquals("CCC 1–8", ReadingPlanLinks.catechismLabel(1..8, true))
        assertEquals("CIC 8", ReadingPlanLinks.catechismLabel(8..8, false))
    }

    @Test fun psalm23InTheBundledSpanishEditionIsTheShepherdAndPsalm51TheMiserere() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val provider = BibleTextProvider(app, BibleEditionCatalog(app))
        val edition = BibleEditionCatalog.initialDefault(AppLanguage.ES)
        fun opening(raw: String): String {
            val target = ReadingPlanLinks.bibleTarget(raw, edition)!!
            return provider.getVerses(target.bookId, target.chapter, AppLanguage.ES, edition).take(3).joinToString(" ") { it.text.es }
        }
        assertEquals(22, ReadingPlanLinks.bibleTarget("psa 23", edition)!!.chapter)
        assertTrue(opening("psa 23"), opening("psa 23").contains("es mi pastor"))
        assertTrue(opening("psa 51"), opening("psa 51").contains("misericordia", true))
        val repo = CatholicDataRepository(app)
        try {
            assertEquals("Salmos 23 (22)", repo.studyScriptureLabel("psa 23", AppLanguage.ES, edition))
            assertEquals("Ps 51 (50)".substringAfter(' '), repo.studyScriptureLabel("psa 51", AppLanguage.EN, edition).substringAfter(' '))
        } finally { repo.close() }
    }
}
