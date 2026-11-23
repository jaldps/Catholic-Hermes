package com.example

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.room.Room
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.SanctaFidesDatabase
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.data.repository.CatholicDataRepository
import com.example.data.search.SearchIndexHolder
import com.example.ui.AppDestination
import com.example.ui.MainApp
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.runBlocking
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowDialog
import java.io.File

internal val landscapeRoots = listOf(AppDestination.HOME, AppDestination.PRAYERS, AppDestination.BIBLE, AppDestination.MY_PATH, AppDestination.MORE)

internal class LandscapeRealAppFixture(context: Application, private val language: AppLanguage) : AutoCloseable {
    private val repository = CatholicDataRepository(context).apply { setLanguage(language) }
    private val database = Room.inMemoryDatabaseBuilder(context, SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
    private val narrator = LiturgicalTtsManager(context)
    private val store = UserPreferencesStore(context)
    init { runBlocking { store.setLanguage(language) } }

    @Composable fun Content(destination: AppDestination, identity: DesignLanguage, scale: Float = 1f, dark: Boolean = false, opaque: Boolean = false) {
        val prefs = UserPreferences(language = language, designLanguage = identity.id,
            themePreset = UserPreferences.DEFAULT_THEME_PRESET, darkModeSetting = if (dark) "DARK" else "LIGHT", fontFamilyChoice = UserPreferences.DEFAULT_FONT_FAMILY,
            fontSizeScale = scale, audioNarrationSpeed = 1f, activeBibleTranslationId = "bundled", isFullCccDownloaded = false,
            hasChosenLanguage = true, reduceMotion = true, reduceTransparency = opaque)
        SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT", fontScale = scale, reduceMotion = true, reduceTransparency = opaque) {
            CompositionLocalProvider(LocalInspectionMode provides true) { MainApp(repository, database.dao(), narrator, store, prefs, initialDestination = destination) }
        }
    }
    override fun close() {
        runBlocking { PersonalDataCommands.awaitIdle() }
        narrator.release(); repository.close(); database.close(); SearchIndexHolder.reset()
    }
}

internal fun landscapeViewport(compose: ComposeTestRule, activity: ComponentActivity, width: Int, height: Int) {
    RuntimeEnvironment.setQualifiers("w${width}dp-h${height}dp-${if (width > height) "land-" else ""}xhdpi")
    compose.runOnUiThread { activity.onConfigurationChanged(activity.resources.configuration) }
}

/** Draws the native activity and modal windows; generated pixels never enter the APK. */
internal fun landscapeCapture(compose: ComposeTestRule, activity: ComponentActivity, width: Int, height: Int, file: File) {
    compose.runOnIdle {
        val view = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
        view.layoutParams = view.layoutParams.apply { this.width = width * 2; this.height = height * 2 }
        view.measure(View.MeasureSpec.makeMeasureSpec(width * 2, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height * 2, View.MeasureSpec.EXACTLY))
        view.layout(0, 0, width * 2, height * 2)
    }
    compose.waitForIdle()
    compose.runOnIdle {
        val view = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
        val bitmap = Bitmap.createBitmap(width * 2, height * 2, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        ShadowDialog.getLatestDialog()?.takeIf { it.isShowing }?.window?.decorView?.takeIf { it.width > 0 }?.draw(canvas)
        androidx.compose.runtime.snapshots.Snapshot.sendApplyNotifications()
        view.draw(canvas)
        ShadowDialog.getLatestDialog()?.takeIf { it.isShowing }?.window?.decorView?.takeIf { it.width > 0 }?.draw(canvas)
        file.parentFile!!.mkdirs(); file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
    }
}
