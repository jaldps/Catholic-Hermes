package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.repository.CatholicDataRepository
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ui.MainApp
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
            val prefs by dao.getAppPreferences().collectAsState(initial = null)
            val appPrefs = prefs ?: com.example.data.local.AppPreferencesEntity()
            val isSerifChoice = appPrefs.fontFamilyChoice == "SERIF" && appPrefs.themePreset !in listOf("APPLE_CLEAN", "MATERIAL_CLEAN")
            SanctaFidesTheme(
                themePreset = appPrefs.themePreset,
                darkModeSetting = appPrefs.darkModeSetting,
                isSerif = isSerifChoice,
                fontScale = appPrefs.fontSizeScale
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
