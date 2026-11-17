package com.example

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.backup.*
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.backup.*
import com.example.ui.design.AppSheet
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class BackupUiTest {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    private var db:SanctaFidesDatabase?=null
    private var repository:CatholicDataRepository?=null
    private var narration:LiturgicalTtsManager?=null
    @After fun close() {
        compose.runOnUiThread {compose.activity.setContent {}}
        compose.waitForIdle();repository?.close();narration?.release();db?.close()
    }
    @Test fun backupEntryAndExportImportButtonsAreReachableFromRealSettings() {
        val app=ApplicationProvider.getApplicationContext<Application>()
        val store=UserPreferencesStore(app)
        val prefs=runBlocking {store.preferences.first()}.copy(language=AppLanguage.EN,hasChosenLanguage=true,designLanguage="LUMEN")
        val repo=CatholicDataRepository(app).also {repository=it;it.setLanguage(AppLanguage.EN)}
        val database=Room.inMemoryDatabaseBuilder(app,SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also {db=it}
        val tts=LiturgicalTtsManager(app).also {narration=it}
        val restoration=StateRestorationTester(compose)
        restoration.setContent {SanctaFidesTheme(designLanguage="LUMEN",darkModeSetting="LIGHT",reduceMotion=true) {
            CompositionLocalProvider(LocalInspectionMode provides true) {MainApp(repo,database.dao(),tts,store,prefs)}
        }}
        compose.onNodeWithTag("nav_more").performClick()
        compose.onNodeWithTag("backup_entry").performScrollTo().assertIsDisplayed().assertHasClickAction()
        compose.mainClock.advanceTimeByFrame()
        compose.waitForIdle()
        compose.onNodeWithTag("backup_entry").performClick()
        compose.onNodeWithTag("backup_export_button").assertIsDisplayed().assertIsEnabled()
        compose.onNodeWithTag("backup_import_button").assertIsDisplayed().assertIsEnabled()
        compose.onNodeWithTag("backup_export_button").performClick()
        compose.onNodeWithTag("backup_password_field").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("backup_password_field").assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.Password))
        compose.onNodeWithTag("backup_password_confirm_field").assertExists()
        compose.onNodeWithTag("backup_unlock_button").assertIsNotEnabled()
        compose.onNodeWithTag("backup_password_field").performTextInput("a transient secret")
        restoration.emulateSavedInstanceStateRestore()
        compose.onNodeWithTag("backup_password_field").assertIsDisplayed().assertTextEquals("")
    }
    @Test fun passwordConfirmationAndReadOnlyPreviewProtectTheApplyAction() {
        var submitted=0
        compose.setContent {SanctaFidesTheme(reduceMotion=true) {AppSheet({},title="Backup") {
            BackupContent(BackupUiState(page=BackupPage.EXPORT),AppLanguage.EN,{}, {},{it.fill('\u0000');submitted++},{}, {}, {}, {}, {})
        }}}
        compose.onNodeWithTag("backup_password_field").performTextInput("a strong secret 42")
        compose.onNodeWithTag("backup_password_confirm_field").performScrollTo().performTextInput("different")
        compose.onNodeWithTag("backup_unlock_button").performScrollTo().assertIsNotEnabled()
        compose.onNodeWithTag("backup_password_confirm_field").performTextReplacement("a strong secret 42")
        compose.onNodeWithTag("backup_unlock_button").performScrollTo().assertIsEnabled().performClick()
        assertEquals(1,submitted)
    }
    @Test fun replaceRequiresSeparateConfirmationAndCannotBeAppliedWhileBusy() {
        var state by mutableStateOf(BackupUiState(page=BackupPage.SUMMARY,summary=BackupSummary(1700000000000,12,3,5,20,4,1,7,40,8,5,3,20)))
        var applied=0
        compose.setContent {SanctaFidesTheme(reduceMotion=true) {AppSheet({},title="Copia de seguridad") {
            BackupContent(state,AppLanguage.ES,{}, {},{}, {},{state=state.copy(mode=it)}, {applied++;state=state.copy(busy=true)}, {}, {})
        }}}
        compose.onNodeWithTag("backup_summary").assertExists();assertEquals(0,applied)
        compose.onNodeWithText("Reemplazar",useUnmergedTree=true).performScrollTo().performClick()
        compose.onNodeWithTag("backup_apply_button").performScrollTo().performClick()
        assertEquals(0,applied)
        compose.onNodeWithTag("backup_replace_confirmation_dismiss").performClick()
        assertEquals(0,applied)
        compose.onNodeWithTag("backup_apply_button").performClick()
        compose.onNodeWithTag("backup_replace_confirmation_confirm").performClick()
        assertEquals(1,applied)
        compose.onNodeWithTag("backup_apply_button").assertIsNotEnabled()
    }
}
