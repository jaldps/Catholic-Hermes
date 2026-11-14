package com.example

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.example.data.models.*
import com.example.ui.screens.NovenaOverviewView
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
class NovenaProvenanceUiTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private fun lt(s: String) = LocalizedText(s, s, s)
    private val empty = lt("")
    private fun novena(kind: PromiseKind, commercial: String, pastoral: String) = Novena(
        "fixture", lt("Fixture"), lt("Patron"), lt("Cat"), lt("Desc"), empty, empty,
        listOf(NovenaDay(1, lt("D1"), "Jn 1", lt("m"), lt("p"), lt("i")), NovenaDay(2, lt("D2"), "Jn 2", lt("m"), lt("p"), lt("i"))),
        null, emptyList(),
        NovenaProvenance(
            "fixture", NovenaStatus.TRADITIONAL, lt("Origen"), lt("Historia uno\n\nHistoria dos"), lt("Aprobada"),
            NovenaPromise(kind, lt("Texto promesa"), lt("Atribuida"), lt("Fuente"), lt("Condiciones")),
            lt(commercial), lt(pastoral), listOf("Enchiridion 1999")
        )
    )

    private fun render(identity: String, n: Novena) {
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity, darkModeSetting = "LIGHT", reduceMotion = true, fontScale = 1.3f) {
                Box(Modifier.fillMaxSize()) {
                    NovenaOverviewView(n, AppLanguage.ES, emptySet(), null, {}, {}, {}, 0.dp)
                }
            }
        }
    }

    private fun assertHistoryAndPromise(identity: String) {
        render(identity, novena(PromiseKind.SOURCED, "", ""))
        compose.onNodeWithTag("novena_detail_fixture").performScrollToNode(hasTestTag("novena_history"))
        compose.onNodeWithTag("novena_history").assertExists()
        compose.onNodeWithTag("novena_detail_fixture").performScrollToNode(hasTestTag("novena_promise"))
        compose.onNodeWithTag("novena_promise").assertExists()
        compose.onAllNodesWithTag("novena_commercial_warning").assertCountEquals(0)
    }

    @Test fun historyAndPromiseRenderInLumen() = assertHistoryAndPromise("LUMEN")
    @Test fun historyAndPromiseRenderInSanctuary() = assertHistoryAndPromise("SANCTUARY")

    @Test fun warningAndPastoralNoteAppearWhenPresent() {
        render("LUMEN", novena(PromiseKind.NONE, "Cadenas", "Prudencia"))
        val list = compose.onNodeWithTag("novena_detail_fixture")
        list.performScrollToNode(hasTestTag("novena_commercial_warning"))
        compose.onNodeWithTag("novena_commercial_warning").assertExists()
        list.performScrollToNode(hasTestTag("novena_pastoral_note"))
        compose.onNodeWithTag("novena_pastoral_note").assertExists()
    }
}
