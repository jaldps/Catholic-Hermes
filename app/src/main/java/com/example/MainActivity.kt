package com.example

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var pendingReminderId by mutableStateOf<String?>(null)
    private var appReady by mutableStateOf(false)

    private lateinit var preferencesStore: UserPreferencesStore
    private lateinit var repository: CatholicDataRepository
    private lateinit var ttsManager: LiturgicalTtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        acceptReminderIntent(intent)

        preferencesStore = UserPreferencesStore(applicationContext)
        repository = CatholicDataRepository(applicationContext, preferencesStore)
        ttsManager = LiturgicalTtsManager(this)
        val database = SanctaFidesDatabase.getInstance(this)
        val dao = database.dao()

        com.example.data.persistence.PersonalDataCommands.submit("initial-content") {
            dao.insertDefaultHabits(com.example.data.repository.PilgrimageAndHabitsData.defaultHabitsToSeed)
            dao.insertLiturgicalDays(com.example.data.repository.PilgrimageAndHabitsData.defaultLiturgicalDaysToSeed)
        }

        // Restore the stored language before the first frame so the UI never
        // flashes the device-locale default over the user's own choice.
        lifecycleScope.launch {
            val stored = preferencesStore.preferences.first()
            repository.applyCalendarRules(stored.calendarRules)
            if (stored.hasChosenLanguage) {
                repository.applyPersistedLanguage(stored.language)
            }
            ttsManager.setLanguage(repository.currentLanguage.value)
            appReady = true
            // Reminders that are ON by default (midday Angelus, Chaplet) are
            // scheduled at app start, not only after a reboot; idempotent.
            com.example.data.reminders.ReminderScheduler.rescheduleAll(
                applicationContext,
                repository.currentLanguage.value.code
            )
        }

        setContent {
            val storedPreferences by preferencesStore.preferences.collectAsState<UserPreferences, UserPreferences?>(initial = null)
            val preferences = storedPreferences
            if (!appReady || preferences == null) return@setContent

            SanctaFidesTheme(
                designLanguage = preferences.designLanguage,
                themePreset = preferences.themePreset,
                darkModeSetting = preferences.darkModeSetting,
                isSerif = preferences.fontFamilyChoice == "SERIF",
                fontScale = preferences.fontSizeScale,
                fontFamilyChoice = preferences.fontFamilyChoice,
                reduceMotion = preferences.reduceMotion,
                reduceTransparency = preferences.reduceTransparency,
                highContrast = preferences.highContrast
            ) {
                MainApp(
                    repository = repository,
                    dao = dao,
                    ttsManager = ttsManager,
                    preferencesStore = preferencesStore,
                    preferences = preferences,
                    initialReminderId = pendingReminderId,
                    onReminderConsumed = { pendingReminderId = null }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::ttsManager.isInitialized) ttsManager.refreshAvailability()
        if (appReady) com.example.data.reminders.ReminderScheduler.rescheduleAll(applicationContext, repository.currentLanguage.value.code)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        acceptReminderIntent(intent)
    }

    private fun acceptReminderIntent(intent: Intent?) {
        if (intent?.action == com.example.data.reminders.ReminderScheduler.ACTION_OPEN_REMINDER) {
            pendingReminderId = intent.getStringExtra(com.example.data.reminders.ReminderScheduler.EXTRA_ID)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.release()
        repository.close()
    }
}
