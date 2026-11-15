package com.example

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.models.QuestionDeeper
import com.example.ui.screens.QuestionDeeperBlock
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w320dp-h640dp-xhdpi")
class QuestionDeeperUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun lt(s: String) = LocalizedText(s, s, s)
    private val fixture = QuestionDeeper(1, lt("Explicación larga"), lt("Escritura"), lt("Padres"), lt("Magisterio"),
        lt("Objeción"), lt("Imagen"), lt("Síntesis breve"), listOf("CIC 309", "Agustín, Enchiridion 11"))

    private fun check(identity: String) {
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity, darkModeSetting = "LIGHT", reduceMotion = true, fontScale = 1.3f) {
                Box(Modifier.verticalScroll(rememberScrollState())) { QuestionDeeperBlock(fixture, AppLanguage.ES) }
            }
        }
        compose.onNodeWithTag("qa_deeper").assertExists()
        compose.onNodeWithTag("qa_deeper_synthesis").assertExists()
        compose.onAllNodesWithTag("qa_deeper_explanation").assertCountEquals(0)
        compose.onNodeWithTag("qa_deeper_expand").performScrollTo().performClick()
        listOf("explanation", "scripture", "tradition", "magisterium", "objection", "analogy", "synthesis").forEach {
            compose.onNodeWithTag("qa_deeper_$it").assertExists()
        }
        compose.onNodeWithTag("qa_deeper_sources").assertExists()
        compose.onNodeWithText("Agustín, Enchiridion 11").assertExists()
    }

    @Test fun collapsedThenExpandsInLumen() = check("LUMEN")
    @Test fun collapsedThenExpandsInSanctuary() = check("SANCTUARY")
}
