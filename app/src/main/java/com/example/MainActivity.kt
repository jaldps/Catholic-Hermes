package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    private lateinit var preferencesStore: UserPreferencesStore
    private lateinit var repository: CatholicDataRepository
    private lateinit var ttsManager: LiturgicalTtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        preferencesStore = UserPreferencesStore(applicationContext)
        repository = CatholicDataRepository(this, preferencesStore)
        ttsManager = LiturgicalTtsManager(this)
        val database = SanctaFidesDatabase.getInstance(this)
        val dao = database.dao()

        lifecycleScope.launch {
            dao.insertDefaultHabits(com.example.data.repository.PilgrimageAndHabitsData.defaultHabitsToSeed)
            dao.insertLiturgicalDays(com.example.data.repository.PilgrimageAndHabitsData.defaultLiturgicalDaysToSeed)
        }

        // Restore the stored language before the first frame so the UI never
        // flashes the device-locale default over the user's own choice.
        lifecycleScope.launch {
            val stored = preferencesStore.preferences.first()
            if (stored.hasChosenLanguage) {
                repository.applyPersistedLanguage(stored.language)
                ttsManager.setLanguage(stored.language)
            }
        }

        setContent {
            val preferences by preferencesStore.preferences.collectAsState(
                initial = UserPreferences(
                    language = repository.currentLanguage.value,
                    designLanguage = UserPreferences.DEFAULT_DESIGN_LANGUAGE,
                    themePreset = UserPreferences.DEFAULT_THEME_PRESET,
                    darkModeSetting = UserPreferences.DEFAULT_DARK_MODE,
                    fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY,
                    fontSizeScale = 1.0f,
                    audioNarrationSpeed = 1.0f,
                    activeBibleTranslationId = "jerusalem",
                    isFullCccDownloaded = false,
                    hasChosenLanguage = false
                )
            )

            SanctaFidesTheme(
                designLanguage = preferences.designLanguage,
                themePreset = preferences.themePreset,
                darkModeSetting = preferences.darkModeSetting,
                isSerif = preferences.fontFamilyChoice == "SERIF",
                fontScale = preferences.fontSizeScale
            ) {
                MainApp(
                    repository = repository,
                    dao = dao,
                    ttsManager = ttsManager,
                    preferencesStore = preferencesStore,
                    preferences = preferences
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.release()
    }
}
