package com.example

import androidx.compose.ui.graphics.Color
import com.example.ui.design.tintedGlassSpec
import com.example.ui.theme.AccentColors
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.DesignTokens
import com.example.ui.theme.NonEcclesialAccent
import com.example.ui.theme.lumenAmbientForTests
import com.example.ui.theme.resolveAccent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Accent ink for fixed swatches and custom colours holds 4.5:1 in every identity and mode. */
class AccentColorsTest {
    private val identities = listOf(DesignLanguage.APPLE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY)

    private fun bases(): List<Pair<String, Color>> =
        NonEcclesialAccent.entries.map { it.id to it.base } +
            (0 until 24).flatMap { i -> listOf(0.25f, 0.5f, 0.75f).map { l ->
                "custom h=${i * 15} l=$l" to AccentColors.fromHsl(i * 15f, 0.9f, l) } } +
            listOf("black" to Color.Black, "white" to Color.White, "grey" to Color(0xFF808080), "CUSTOM_3A7BD5" to Color(0xFF3A7BD5))

    /** The accent as the theme draws it, through the same resolution as SanctaFidesTheme. */
    private fun drawn(base: Color, identity: DesignLanguage, dark: Boolean): Color {
        val id = AccentColors.customId(base)
        return resolveAccent(id, identity, dark, null, DesignTokens.of(identity, dark).palette.accent)
    }

    private fun pools(identity: DesignLanguage, base: Color, dark: Boolean): List<Color> = when (identity) {
        DesignLanguage.SANCTUARY, DesignLanguage.CLASSIC -> emptyList()
        DesignLanguage.GLASS -> com.example.ui.theme.glassAmbient(AccentColors.ambient(base, dark))
        else -> AccentColors.ambient(base, dark)
    }

    @Test fun inkReachesFourPointFiveOnCanvasCardsAndPanesInEveryIdentity() {
        for (identity in identities) for (dark in listOf(false, true)) for ((name, base) in bases()) {
            val ink = drawn(base, identity, dark)
            // Round-trip through the stored hex, as a saved custom colour would.
            for (surface in AccentColors.surfaces(identity, dark, pools(identity, AccentColors.parseCustom(AccentColors.customId(base))!!, dark))) {
                val ratio = AccentColors.contrast(ink, surface)
                assertTrue("$name ${identity.id} dark=$dark ink=$ink on $surface = $ratio", ratio >= 4.5f)
            }
        }
    }

    @Test fun actionLabelReadsOnTheAccentFilledButtonOfLumenAndCristal() {
        for (identity in listOf(DesignLanguage.APPLE, DesignLanguage.GLASS)) for (dark in listOf(false, true)) {
            val design = DesignTokens.of(identity, dark)
            val onAction = AccentColors.liquidOnAction(dark)
            for ((name, base) in bases()) {
                val spec = design.tintedGlassSpec(drawn(base, identity, dark))
                assertTrue("$name ${identity.id} dark=$dark top", AccentColors.contrast(spec.fillTop, onAction) >= 4.5f)
                assertTrue("$name ${identity.id} dark=$dark bottom", AccentColors.contrast(spec.fillBottom, onAction) >= 4.5f)
            }
        }
    }

    @Test fun classicKeepsItsNeutralActionAndFixedGreenUnderTheDynamicTint() {
        for (dark in listOf(false, true)) {
            val p = DesignTokens.of(DesignLanguage.CLASSIC, dark).palette
            assertTrue(p.ambient.isEmpty())
            assertTrue(p.action != p.accent)
            assertEquals(p.accent, resolveAccent("LITURGICAL_DYNAMIC", DesignLanguage.CLASSIC, dark, null, p.accent))
            assertTrue(AccentColors.contrast(p.action, p.onAction) >= 4.5f)
        }
    }

    @Test fun fixedSwatchesAndCustomIdsParse() {
        assertEquals(10, NonEcclesialAccent.entries.size)
        NonEcclesialAccent.entries.forEach { assertNotNull(AccentColors.baseOf(it.id)) }
        AppThemePreset.entries.forEach { assertNull(AccentColors.baseOf(it.id)) }
        assertEquals(Color(0xFF3A7BD5), AccentColors.parseCustom("CUSTOM_3A7BD5"))
        assertEquals("CUSTOM_3A7BD5", AccentColors.customId(Color(0xFF3A7BD5)))
        assertNull(AccentColors.parseCustom("CUSTOM_XYZ"))
        assertTrue(lumenAmbientForTests("NE_TEAL", false).size == 4 && lumenAmbientForTests("CUSTOM_3A7BD5", true).size == 4)
    }
}
