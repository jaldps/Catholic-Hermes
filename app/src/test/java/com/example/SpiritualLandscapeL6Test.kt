package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.ui.screens.SpiritualLifeScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L6 3/6: Spiritual life hub in landscape with journal and practices lanes. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class SpiritualLandscapeL6Test {
    @get:Rule val compose = createComposeRule()
    private var voice: LiturgicalTtsManager? = null
    private var db: SanctaFidesDatabase? = null

    @After fun close() { voice?.release(); db?.close() }

    @Test fun hubShowsJournalAndPractices() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        ChapletCatalog.install(app)
        val repository = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }
        db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        voice = LiturgicalTtsManager(app)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                SpiritualLifeScreen(repository, db!!.dao(), voice!!, AppLanguage.ES)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("app_page_panel").assertExists()
        compose.onNodeWithTag("spiritual_life_title").assertExists()
        compose.onNodeWithTag("journal_landscape_list").assertExists()
        compose.onNodeWithTag("practices_landscape_list").assertExists()
        compose.onNodeWithTag("open_temptation_landscape").assertExists()
    }
}
