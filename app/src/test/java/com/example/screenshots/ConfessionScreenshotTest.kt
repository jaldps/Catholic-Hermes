package com.example.screenshots

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
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
import com.example.data.persistence.PersonalDataCommands
import com.example.data.repository.CatholicDataRepository
import com.example.ui.MainApp
import com.example.ui.AppDestination
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk=[34],qualifiers="w411dp-h891dp-xhdpi")
class ConfessionScreenshotTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose=createAndroidComposeRule<ComponentActivity>()
    private var database: SanctaFidesDatabase?=null
    private var repository: CatholicDataRepository?=null
    private var narrator: LiturgicalTtsManager?=null
    @After fun close() {
        compose.runOnUiThread { compose.activity.setContent {} };compose.waitForIdle()
        runBlocking {PersonalDataCommands.awaitIdle()};narrator?.release();repository?.close();database?.close()
    }
    private fun render(language: AppLanguage, scale: Float) {
        val app=ApplicationProvider.getApplicationContext<Application>()
        val store=UserPreferencesStore(app)
        val prefs=runBlocking {store.preferences.first()}.copy(language=language,hasChosenLanguage=true,designLanguage=identity.id,darkModeSetting=if(dark)"DARK" else "LIGHT",fontSizeScale=scale)
        val db=Room.inMemoryDatabaseBuilder(app,SanctaFidesDatabase::class.java).allowMainThreadQueries().build().also {database=it}
        val repo=CatholicDataRepository(app).apply {setLanguage(language)}.also {repository=it}
        val tts=LiturgicalTtsManager(app).also {narrator=it}
        compose.setContent {SanctaFidesTheme(designLanguage=identity.id,darkModeSetting=if(dark)"DARK" else "LIGHT",fontScale=scale,reduceMotion=true) {
            CompositionLocalProvider(LocalInspectionMode provides true) {MainApp(repo,db.dao(),tts,store,prefs,initialDestination=AppDestination.CONFESSION)}
        }}
        val gotIt=when(language){AppLanguage.ES->"Entendido";AppLanguage.EN->"Got it";AppLanguage.PT->"Entendi"}
        compose.onNodeWithText(gotIt).performScrollTo().performClick()
        compose.waitUntil(15_000) {runCatching {compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_profile_button"));true}.getOrDefault(false)}
    }
    private fun capture(name: String) {
        compose.waitForIdle()
        compose.runOnIdle {
            val view=compose.activity.window.decorView
            check(view.width>0 && view.height>0)
            val bitmap=Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            val file=File("build/screenshots/confession/${identity.id.lowercase()}_${if(dark)"dark" else "light"}_$name.png")
            file.parentFile!!.mkdirs();file.outputStream().use {bitmap.compress(Bitmap.CompressFormat.PNG,100,it)};bitmap.recycle()
        }
    }
    private fun tab(text: String)=compose.onNode(hasText(text) and hasAnyAncestor(hasTestTag("confession_tabs")),useUnmergedTree=true).performScrollTo().performClick()
    @Test fun examinationPrivateListAndReviewedGuide() {
        render(AppLanguage.ES,1f)
        compose.onNodeWithTag("confession_screen").performScrollToIndex(0);capture("overview")
        compose.onNodeWithTag("confession_section_c1").performScrollTo().performClick()
        compose.onNodeWithTag("confession_question_single:c1:0").performScrollTo().assertIsDisplayed();capture("examen")
        compose.onNodeWithTag("confession_question_single:c1:0").performClick();tab("Mi lista")
        compose.onNodeWithTag("confession_selected_single:c1:0").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().performTextInput("Recordar lo que quiero conversar.")
        compose.onNodeWithTag("confession_finish_erase").assertIsDisplayed();capture("list")
        tab("El sacramento")
        compose.onNodeWithTag("confession_guide").performScrollTo().assertIsDisplayed();capture("guide")
    }
    @Test @Config(qualifiers="w320dp-h891dp-xhdpi") fun narrowLargeTextKeepsActionsReachable() {
        render(AppLanguage.PT,1.3f)
        compose.onNodeWithTag("confession_profile_button").performScrollTo().assertIsDisplayed();capture("320_130_profile")
        tab("Minha lista")
        compose.onNodeWithTag("confession_screen").performScrollToNode(hasTestTag("confession_notes"))
        compose.onNodeWithTag("confession_notes").performScrollTo().performTextInput("Nota temporária")
        compose.onNodeWithTag("confession_finish_erase").assertIsDisplayed()
        capture("320_130_list")
        compose.onNodeWithText("Terminar e apagar",useUnmergedTree=true).performSemanticsAction(androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult) { get ->
            val results=mutableListOf<androidx.compose.ui.text.TextLayoutResult>();get(results)
            check(results.size == 1)
            val layout = results.single()
            check(layout.multiParagraph.height <= layout.size.height + 1f) {"Erase label is clipped vertically"}
            // Centred Text can shrink its measured box while retaining the wider paragraph.
            // Check ink against the paragraph that actually paints it, not the intrinsic box.
            repeat(layout.lineCount) { line ->
                check(!layout.isLineEllipsized(line) && layout.getLineLeft(line) >= -1f && layout.getLineRight(line) <= layout.multiParagraph.width + 1f) {
                    "Erase label line $line is clipped: ${layout.getLineLeft(line)}..${layout.getLineRight(line)} in paragraph ${layout.multiParagraph.width} (text box ${layout.size.width})"
                }
            }
        }
        tab("O sacramento")
        compose.onNodeWithTag("confession_guide").performScrollTo().assertIsDisplayed();capture("320_130_guide")
    }
    companion object {
        @JvmStatic @ParameterizedRobolectricTestRunner.Parameters(name="{0}, dark={1}")
        fun modes()=DesignLanguage.selectable.flatMap {listOf(arrayOf<Any>(it,false),arrayOf<Any>(it,true))}
    }
}
