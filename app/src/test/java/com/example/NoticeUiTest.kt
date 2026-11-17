package com.example

import android.Manifest
import android.app.AlarmManager
import android.app.Application
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.notices.NoticeScheduler
import com.example.data.notices.NoticeStore
import com.example.data.notices.NoticeType
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Reminders screen: turning a notice on asks for the permission and schedules it; a notice tap lands on its screen. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class NoticeUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val app get() = ApplicationProvider.getApplicationContext<Application>()
    private var db: SanctaFidesDatabase? = null
    private var repo: CatholicDataRepository? = null
    private var tts: LiturgicalTtsManager? = null

    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} }
        compose.waitForIdle(); runBlocking { PersonalDataCommands.awaitIdle() }
        Thread.sleep(300)
        tts?.release(); repo?.close(); db?.close()
    }

    private var openId by mutableStateOf<String?>(null)

    private fun launch(initial: AppDestination) {
        val store = UserPreferencesStore(app)
        runBlocking { store.setLanguage(AppLanguage.ES); NoticeType.entries.forEach { NoticeStore(app).setEnabled(it, false) } }
        val repository = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }.also { repo = it }
        val database = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also { db = it }
        val prefs = runBlocking { store.preferences.first() }.copy(language = AppLanguage.ES, hasChosenLanguage = true)
        val narrator = LiturgicalTtsManager(app).also { tts = it }
        compose.activity.setContent { SanctaFidesTheme(reduceMotion = true) { CompositionLocalProvider(LocalInspectionMode provides true) {
            MainApp(repository, database.dao(), narrator, store, prefs, initialDestination = initial,
                initialReminderId = openId, onReminderConsumed = { openId = null })
        } } }
    }

    private fun exists(tag: String) = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    private fun waitFor(tag: String, timeout: Long = 30_000) = compose.waitUntil(timeout) { exists(tag) }

    @Test fun turningANoticeOnAsksPermissionAndSchedulesItsAlarm() {
        launch(AppDestination.REMINDERS)
        waitFor("reminders_screen")
        compose.onNodeWithTag("reminders_screen").performScrollToNode(hasTestTag("notice_group_reminders"))
        compose.onNodeWithTag("reminders_screen").performScrollToNode(hasTestTag("notice_switch_sunday_mass"))
        waitFor("notice_switch_sunday_mass")
        compose.onNodeWithTag("notice_switch_sunday_mass", useUnmergedTree = true).performClick()
        compose.waitForIdle()
        val asked = shadowOf(compose.activity).lastRequestedPermission
        assertTrue("POST_NOTIFICATIONS requested", asked?.requestedPermissions?.contains(Manifest.permission.POST_NOTIFICATIONS) == true)
        // The write hops back to the main thread to confirm; let the looper run instead of blocking it.
        fun noticeAlarms() = shadowOf(app.getSystemService(Context.ALARM_SERVICE) as AlarmManager).scheduledAlarms
            .filter { shadowOf(it.operation).savedIntent.action == NoticeScheduler.ACTION_NOTICE_FIRE }
        compose.waitUntil(15_000) { noticeAlarms().isNotEmpty() }
        val alarms = noticeAlarms()
        assertEquals(listOf("sunday_mass"), alarms.map { shadowOf(it.operation).savedIntent.getStringExtra(NoticeScheduler.EXTRA_NOTICE_TYPE) })
        assertTrue(runBlocking { NoticeStore(app).settings.first() }.pref(NoticeType.SUNDAY_MASS).enabled)
    }

    @Test fun holyDaysAskWhichDaysAndExplainTheConference() {
        launch(AppDestination.REMINDERS)
        waitFor("reminders_screen")
        compose.onNodeWithTag("reminders_screen").performScrollToNode(hasTestTag("notice_switch_holy_day_of_obligation"))
        compose.onNodeWithTag("notice_switch_holy_day_of_obligation", useUnmergedTree = true).performClick()
        waitFor("obligation_note")
        compose.onNodeWithTag("notice_switch_obligation_all_saints", useUnmergedTree = true).performScrollTo().performClick()
        compose.onNodeWithTag("obligation_save", useUnmergedTree = true).performScrollTo().performClick()
        compose.waitUntil(15_000) { runBlocking { NoticeStore(app).settings.first() }.obligationDays.isNotEmpty() }
        assertEquals(setOf("all_saints"), runBlocking { NoticeStore(app).settings.first() }.obligationDays)
    }

    @Test fun tappingANoticeOpensItsDestination() {
        openId = NoticeScheduler.openId("rosary_evening", "ROSARY")
        launch(AppDestination.HOME)
        waitFor("rosary_screen")
        // The «Mi camino» widget opens the hub.
        compose.runOnUiThread { openId = NoticeScheduler.openId("widget_path", "MY_PATH") }
        waitFor("my_path_screen")
    }
}
