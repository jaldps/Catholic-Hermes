package com.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScaffold
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Interaction and touch-target contracts shared by the two selectable identities. */
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class DesignNavigationTest(private val identity: DesignLanguage, private val dark: Boolean) {
    @get:Rule val compose = createComposeRule()

    @Test fun navigationAndPrimaryActionRemainUsableInEveryIdentity() {
        var actions = 0
        compose.setContent {
            SanctaFidesTheme(designLanguage = identity.id, darkModeSetting = if (dark) "DARK" else "LIGHT") {
                var selected by remember { mutableIntStateOf(0) }
                AppScaffold(
                    title = "Sancta Fides",
                    navItems = listOf(
                        AppNavItem("Home", Icons.Default.Home, tag = "test_home"),
                        AppNavItem("Bible", Icons.Default.MenuBook, tag = "test_bible")
                    ),
                    selectedNavIndex = selected,
                    onNavSelected = { selected = it }
                ) { insets ->
                    Column(Modifier.padding(insets)) {
                        Text("${identity.id}: $selected")
                        AppPrimaryButton(
                            label = "Continuar a leitura do Evangelho",
                            onClick = { actions++ },
                            modifier = Modifier.testTag("test_action")
                        )
                    }
                }
            }
        }
        compose.onNodeWithTag("test_home").assertIsSelected().assertHasClickAction()
            .assertHeightIsAtLeast(48.dp)
        compose.onNodeWithTag("test_bible").assertHeightIsAtLeast(48.dp).performClick().assertIsSelected()
        compose.onNodeWithTag("test_action").assertHasClickAction().assertHeightIsAtLeast(48.dp).performClick()
        compose.runOnIdle { assertEquals(1, actions) }
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}, dark={1}")
        fun identities(): List<Array<Any>> = DesignLanguage.selectable.flatMap { identity ->
            listOf(arrayOf<Any>(identity, false), arrayOf<Any>(identity, true))
        }
    }
}
