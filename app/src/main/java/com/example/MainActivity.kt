package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.SanctaFidesTheme

class MainActivity : ComponentActivity() {

    private lateinit var repository: CatholicDataRepository
    private lateinit var ttsManager: LiturgicalTtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        repository = CatholicDataRepository(this)
        ttsManager = LiturgicalTtsManager(this)
        val database = SanctaFidesDatabase.getInstance(this)
        val dao = database.dao()

        lifecycleScope.launch {
            dao.insertDefaultHabits(com.example.data.repository.PilgrimageAndHabitsData.defaultHabitsToSeed)
            dao.insertLiturgicalDays(com.example.data.repository.PilgrimageAndHabitsData.defaultLiturgicalDaysToSeed)
        }

        setContent {
            val preferences by dao.getAppPreferences().collectAsState(initial = null)
            val currentLanguage by repository.currentLanguage.collectAsState()
            val prefs = preferences ?: com.example.data.local.AppPreferencesEntity()
            val liturgicalDay = repository.getCurrentLiturgicalDay()

            SanctaFidesTheme(
                themePreset = AppThemePreset.normalizeId(prefs.themePreset),
                darkModeSetting = prefs.darkModeSetting,
                isSerif = prefs.fontFamilyChoice != "SANS",
                fontScale = prefs.fontSizeScale,
                liturgicalSeason = liturgicalDay.seasonName.get(currentLanguage)
            ) {
                MainApp(
                    repository = repository,
                    dao = dao,
                    ttsManager = ttsManager
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.release()
    }
}
