package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.backup.*
import com.example.data.models.AppLanguage
import com.example.ui.backup.*
import com.example.ui.design.AppSheet
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowDialog
import java.io.File

internal fun captureBackup(name:String) {
    val view=requireNotNull(ShadowDialog.getLatestDialog()?.window).decorView
    check(view.width>0&&view.height>0)
    val bitmap=Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
    view.draw(Canvas(bitmap))
    val file=File("build/screenshots/backup/$name.png");file.parentFile!!.mkdirs()
    file.outputStream().use {bitmap.compress(Bitmap.CompressFormat.PNG,100,it)}
    bitmap.recycle()
}
internal val backupFixture=BackupSummary(1794866400000,12,3,5,42,6,1,8,124,17,15,9,34,27,12)

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class BackupScreenshotTest(private val identity:DesignLanguage,private val dark:Boolean) {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    private var currentState by mutableStateOf(BackupUiState())
    private fun render(state:BackupUiState,onMode:(RestoreMode)->Unit={}) {
        currentState=state
        compose.setContent {SanctaFidesTheme(designLanguage=identity.id,darkModeSetting=if(dark)"DARK" else "LIGHT",reduceMotion=true) {
            AppSheet({},title="Copia de seguridad",tag="backup_sheet") {
                BackupContent(currentState,AppLanguage.ES,{}, {},{}, {},onMode,{}, {}, {})
            }
        }}
        compose.waitForIdle()
    }
    private fun save(stage:String) {compose.runOnIdle {captureBackup("${identity.id.lowercase()}_${if(dark)"dark" else "light"}_$stage")}}
    @Test fun overviewAndPassword() {
        render(BackupUiState());compose.onNodeWithTag("backup_export_button").assertIsDisplayed();save("overview")
        compose.runOnUiThread {currentState=BackupUiState(page=BackupPage.EXPORT)}
        compose.onNodeWithTag("backup_password_field").assertIsDisplayed();save("password")
    }
    @Test fun summaryAndMergeActions() {
        render(BackupUiState(page=BackupPage.SUMMARY,summary=backupFixture))
        compose.onNodeWithTag("backup_summary").assertExists();save("summary")
        compose.onNodeWithTag("backup_apply_button").performScrollTo().assertIsDisplayed();save("merge_actions")
    }
    @Test fun explicitReplacementConfirmation() {
        render(BackupUiState(page=BackupPage.SUMMARY,summary=backupFixture,mode=RestoreMode.REPLACE))
        compose.onNodeWithTag("backup_apply_button").performScrollTo().performClick()
        compose.onNodeWithTag("backup_replace_confirmation_confirm").assertIsDisplayed();save("replace_confirmation")
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name="{0}, dark={1}")
        fun modes()=DesignLanguage.selectable.flatMap {listOf(arrayOf<Any>(it,false),arrayOf<Any>(it,true))}
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w320dp-h891dp-xhdpi")
class BackupNarrowScreenshotTest(private val identity:DesignLanguage,private val language:AppLanguage) {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    @Test fun enlargedTextStillReachesRestoreAndPasswordActions() {
        var state by mutableStateOf(BackupUiState(page=BackupPage.SUMMARY,summary=backupFixture))
        val title=when(language){AppLanguage.ES->"Copia de seguridad";AppLanguage.EN->"Backup";AppLanguage.PT->"Cópia de segurança"}
        compose.setContent {SanctaFidesTheme(designLanguage=identity.id,darkModeSetting="LIGHT",reduceMotion=true,fontScale=1.3f) {
            AppSheet({},title=title,tag="backup_sheet") {BackupContent(state,language,{}, {},{}, {},{}, {},{}, {})}
        }}
        compose.waitForIdle()
        val prefix="${identity.id.lowercase()}_${language.code}_320_130"
        compose.runOnIdle {captureBackup("${prefix}_summary")}
        val replace=when(language){AppLanguage.ES->"Reemplazar";AppLanguage.EN->"Replace";AppLanguage.PT->"Substituir"}
        compose.onNodeWithText(replace,useUnmergedTree=true).performScrollTo().assertIsDisplayed()
        compose.onNodeWithText(replace,useUnmergedTree=true).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult) { getResults ->
            val layouts=mutableListOf<androidx.compose.ui.text.TextLayoutResult>()
            getResults(layouts)
            check(layouts.size==1) {"Expected one layout for $replace, got ${layouts.size}"}
            val layout=layouts.single()
            check(!layout.hasVisualOverflow) {"$replace overflow: size=${layout.size}, paragraph=${layout.multiParagraph.width}x${layout.multiParagraph.height}, lines=${layout.lineCount}, width=${layout.didOverflowWidth}, height=${layout.didOverflowHeight}"}
        }
        compose.onNodeWithTag("backup_apply_button").performScrollTo().assertIsDisplayed()
        compose.runOnIdle {captureBackup("${prefix}_actions")}
        compose.runOnUiThread {state=BackupUiState(page=BackupPage.EXPORT)}
        compose.onNodeWithTag("backup_password_field").performScrollTo().assertIsDisplayed()
        compose.runOnIdle {captureBackup("${prefix}_password")}
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name="{0}, {1}")
        fun modes()=DesignLanguage.selectable.flatMap {id->AppLanguage.entries.map {arrayOf<Any>(id,it)}}
    }
}
