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
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {

    private lateinit var repository: CatholicDataRepository
    private lateinit var ttsManager: LiturgicalTtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        repository = CatholicDataRepository()
        ttsManager = LiturgicalTtsManager(this)
        val database = SanctaFidesDatabase.getInstance(this)
        val dao = database.dao()

        lifecycleScope.launch {
            dao.insertDefaultHabits(com.example.data.repository.PilgrimageAndHabitsData.defaultHabitsToSeed)
            dao.insertLiturgicalDays(com.example.data.repository.PilgrimageAndHabitsData.defaultLiturgicalDaysToSeed)
            com.example.data.repository.BibleDownloadManager(dao).initializeDefaultBibles()
        }

        setContent {
            val appPreferences by dao.getAppPreferences().collectAsState(initial = null)
            val themePreset = appPreferences?.themePreset ?: "LITURGICAL_DYNAMIC"
            val darkModeSetting = appPreferences?.darkModeSetting ?: "SYSTEM"
            val isSerif = (appPreferences?.fontFamilyChoice ?: "SERIF") == "SERIF"
            val fontScale = appPreferences?.fontSizeScale ?: 1.0f

            SanctaFidesTheme(
                themePreset = themePreset,
                darkModeSetting = darkModeSetting,
                isSerif = isSerif,
                fontScale = fontScale
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
