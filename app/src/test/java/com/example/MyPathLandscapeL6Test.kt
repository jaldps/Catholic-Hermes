package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.screens.MyPathHub
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** L6 1/6: My path uses the Today page layout with two lanes in landscape. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34], qualifiers = "w891dp-h411dp-land-xhdpi")
class MyPathLandscapeL6Test {
    @get:Rule val compose = createComposeRule()

    @Test fun todayPanelShowsBothLanes() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        val repository = CatholicDataRepository(app)
        val dao = Room.inMemoryDatabaseBuilder(app, SanctaFidesDatabase::class.java)
            .allowMainThreadQueries().build().dao()
        val store = UserPreferencesStore(app)
        compose.setContent {
            SanctaFidesTheme(designLanguage = "LUMEN", darkModeSetting = "LIGHT", reduceMotion = true) {
                MyPathHub(repository, dao, store, AppLanguage.ES, {}, {}, {}, {})
            }
        }
        compose.waitForIdle()
        println("TAGS=" + compose.onAllNodes(androidx.compose.ui.test.SemanticsMatcher("any") { true }, useUnmergedTree = true)
            .fetchSemanticsNodes().mapNotNull { it.config.getOrNull(androidx.compose.ui.semantics.SemanticsProperties.TestTag) }.distinct().joinToString(","))
        compose.onNodeWithTag("app_page_panel").assertExists()
        compose.onNodeWithTag("my_path_title").assertIsDisplayed()
        compose.onNodeWithTag("my_path_today_header").assertExists()
        compose.onNodeWithTag("my_path_today").assertExists()
        compose.onNodeWithTag("my_path_sections").assertExists()
        compose.onNodeWithTag("my_path_confession_entry").assertExists()
    }
}
