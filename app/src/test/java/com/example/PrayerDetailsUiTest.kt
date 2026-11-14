package com.example

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.data.models.*
import com.example.ui.screens.PrayerDetailView
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
class PrayerDetailsUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private fun lt(s: String) = LocalizedText(s, s, s)
    private val empty = lt("")
    private val prayer = VaticanPrayer("fixture", lt("Oración"), null, PrayerCategory.BASIC, lt("Texto de la oración"), null, lt("Siempre"))

    private fun check(identity: String, details: PrayerDetails, approvalExpected: Boolean) {
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity, darkModeSetting = "LIGHT", reduceMotion = true, fontScale = 1.3f) {
                Box(Modifier.fillMaxSize()) {
                    PrayerDetailView(prayer, AppLanguage.ES, null, {}, {}, 0.dp, details)
                }
            }
        }
        val list = compose.onNodeWithTag("prayer_detail_fixture")
        listOf("prayer_instructions", "prayer_history", "prayer_promise").forEach {
            list.performScrollToNode(hasTestTag(it)); compose.onNodeWithTag(it).assertExists()
        }
        compose.onAllNodesWithTag("prayer_approval").assertCountEquals(if (approvalExpected) 1 else 0)
    }

    private val promise = NovenaPromise(PromiseKind.TRADITIONAL, lt("Promesa"), lt("Atrib"), lt("Fuente"), lt("Cond"))
    private fun details(approval: LocalizedText) =
        PrayerDetails("fixture", lt("Historia\n\nDos"), lt("Instrucciones"), lt("Fuente"), approval, promise, lt("Nota"))

    @Test fun sectionsRenderInLumen() {
        check("LUMEN", details(lt("Aprobada")), true)
        compose.onNodeWithTag("prayer_detail_fixture").performScrollToNode(hasTestTag("prayer_approval"))
    }
    @Test fun blankApprovalIsHiddenInSanctuary() = check("SANCTUARY", details(empty), false)
}
