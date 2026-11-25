package com.example

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
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
import com.example.ui.screens.HabitsAndVirtuesScreen
import com.example.ui.theme.SanctaFidesTheme
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L6 2/6: Habits uses a Today panel with the fidelity calendar and the checklist in two lanes. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class HabitsLandscapeL6Test {
    @get:Rule val compose = createComposeRule()
    private var voice: LiturgicalTtsManager? = null
    private var db: SanctaFidesDatabase? = null

    @After fun close() { voice?.release(); db?.close() }

    @Test fun habitsShowRingFidelityAndChecklist() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        ChapletCatalog.install(app)
        val repository = CatholicDataRepository(app).apply { setLanguage(AppLanguage.ES) }
        db = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        voice = LiturgicalTtsManager(app)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                HabitsAndVirtuesScreen(repository, db!!.dao(), voice!!)
            }
        }
        compose.waitForIdle()
        compose.onNodeWithTag("app_page_panel").assertExists()
        compose.onNodeWithTag("habits_title").assertExists()
        compose.onNodeWithTag("habits_checklist_card").assertExists()
        val days = compose.onAllNodes(SemanticsMatcher("a fidelity day") { node ->
            node.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("habit_month_day_") == true
        }, useUnmergedTree = true).fetchSemanticsNodes()
        assertTrue("fidelity days are composed", days.isNotEmpty())
    }
}
