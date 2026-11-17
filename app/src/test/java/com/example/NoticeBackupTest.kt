package com.example

import android.content.Context
import android.content.ContextWrapper
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.backup.BackupPreferences
import com.example.data.backup.BackupService
import com.example.data.backup.RestoreMode
import com.example.data.local.SanctaFidesDatabase
import com.example.data.notices.NoticeStore
import com.example.data.notices.NoticeType
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.ReminderStore
import com.example.data.temptation.TemptationStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File

/**
 * The notice switches, times and chosen holy days live in the reminders DataStore and so travel in the backup
 * (same harness as [BackupCoreTest], kept apart so the two can change independently).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NoticeBackupTest {
    @get:Rule val folder = TemporaryFolder()
    private val password = "Una contraseña única 42"
    private val devices = mutableListOf<Device>()

    private class DeviceContext(base: Context, private val prefix: String) : ContextWrapper(base) {
        override fun getApplicationContext(): Context = this
        override fun getSharedPreferences(name: String, mode: Int) = super.getSharedPreferences("$prefix.$name", mode)
    }

    private inner class Device(name: String) {
        val context = DeviceContext(ApplicationProvider.getApplicationContext(), name)
        val directory = folder.newFolder(name)
        val job = SupervisorJob()
        private fun data(name: String) = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + job)) { File(directory, "$name.preferences_pb") }
        val remindersData = data("reminders")
        val settings = UserPreferencesStore(context, data("settings"))
        val reminders = ReminderStore(context, remindersData)
        val notices = NoticeStore(context, remindersData)
        val db = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        val service = BackupService(context, db, BackupPreferences(context, settings, TemptationStore(context, data("temptation")), reminders),
            File(directory, "restore.json"))
    }

    private fun device(name: String) = Device(name).also { devices += it }

    @After fun release() = runBlocking {
        PersonalDataCommands.awaitIdle()
        devices.forEach { it.job.cancelAndJoin(); it.db.close() }
    }

    @Test fun noticeChoicesRoundTripThroughTheBackup() = runBlocking {
        val source = device("source"); val target = device("target")
        source.notices.setEnabled(NoticeType.FEAST_EVE, true)
        source.notices.setTime(NoticeType.FEAST_EVE, 20, 15)
        source.notices.setEnabled(NoticeType.HOLY_DAY_OF_OBLIGATION, true)
        source.notices.setObligationDays(setOf("all_saints", "christmas_day"))
        val file = source.service.export(password.toCharArray())
        target.service.apply(target.service.preview(file, password.toCharArray()), RestoreMode.REPLACE)
        val restored = target.notices.settings.first()
        assertTrue(restored.pref(NoticeType.FEAST_EVE).enabled)
        assertEquals(20, restored.pref(NoticeType.FEAST_EVE).hour); assertEquals(15, restored.pref(NoticeType.FEAST_EVE).minute)
        assertTrue(restored.pref(NoticeType.HOLY_DAY_OF_OBLIGATION).enabled)
        assertFalse(restored.pref(NoticeType.SUNDAY_MASS).enabled)
        assertEquals(setOf("all_saints", "christmas_day"), restored.obligationDays)
    }
}
