package com.example

import android.app.Application
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class ErrorReportUiTest {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    @Test fun settingsEntryExplainsTheReportAndOnlySharesAfterAnExplicitClick() {
        val app=ApplicationProvider.getApplicationContext<Application>()
        val store=UserPreferencesStore(app)
        val prefs=runBlocking {store.preferences.first()}.copy(language=AppLanguage.EN,hasChosenLanguage=true)
        val repo=CatholicDataRepository(app).also {it.setLanguage(AppLanguage.EN)}
        val db=Room.inMemoryDatabaseBuilder(app,SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        val tts=LiturgicalTtsManager(app)
        try {
            compose.setContent {SanctaFidesTheme(reduceMotion=true) {
                CompositionLocalProvider(LocalInspectionMode provides true) {MainApp(repo,db.dao(),tts,store,prefs)}
            }}
            compose.onNodeWithTag("nav_more").performClick()
            compose.onNodeWithTag("error_report_entry").performScrollTo().assertHasClickAction().performClick()
            compose.onNodeWithTag("error_report_sheet").assertExists()
            compose.onNodeWithText("You choose the app",substring=true).assertExists()
            assertNull(shadowOf(compose.activity).nextStartedActivity)
            compose.onNodeWithTag("error_report_share_button").performScrollTo().assertIsDisplayed().performClick()
            var chooser:Intent?=null
            compose.waitUntil(15_000) {chooser=shadowOf(compose.activity).nextStartedActivity;chooser!=null}
            assertEquals(Intent.ACTION_CHOOSER,chooser!!.action)
            val send=chooser!!.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)!!
            assertEquals(Intent.ACTION_SEND,send.action);assertEquals("text/plain",send.type)
            assertTrue(send.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION!=0)
            assertNotNull(send.clipData)
        } finally {
            compose.runOnUiThread {compose.activity.setContent {}}
            compose.waitForIdle();repo.close();tts.release();db.close()
        }
    }
}
