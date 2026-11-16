package com.example

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import com.example.ui.design.GlassRole
import com.example.ui.design.LiquidLens
import com.example.ui.design.chromeGlassSpec
import com.example.ui.design.glassSpec
import com.example.ui.design.tintedGlassSpec
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.DesignTokens
import com.example.ui.theme.LiturgicalLight
import com.example.ui.theme.lumenAccent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The Liquid Glass material: legibility on every pane, shader and fallbacks. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class LiquidGlassTest {

    private fun contrast(a: Color, b: Color): Float {
        val first = a.luminance()
        val second = b.luminance()
        return (maxOf(first, second) + 0.05f) / (minOf(first, second) + 0.05f)
    }

    /** Every surface text can sit on: the canvas, and each glass over it and over each pool of light. */
    private fun surfaces(dark: Boolean, ambient: List<Color>): List<Color> {
        val p = DesignTokens.of(DesignLanguage.APPLE, dark).palette
        val grounds = listOf(p.canvasTop, p.canvasBottom).flatMap { ground ->
            listOf(ground) + ambient.map { it.compositeOver(ground) }
        }
        return grounds + grounds.flatMap { ground ->
            listOf(p.pane, p.glass, p.glassStrong).map { it.compositeOver(ground) }
        }
    }

    @Test @Config(sdk = [34]) fun everyInkStaysReadableOnGlassOverEveryLiturgicalLight() {
        for (dark in listOf(false, true)) {
            val p = DesignTokens.of(DesignLanguage.APPLE, dark).palette
            for (light in LiturgicalLight.entries) {
                for (surface in surfaces(dark, light.ambient(dark))) {
                    for (ink in listOf(p.textPrimary, p.textSecondary, p.textTertiary, light.accent(dark))) {
                        val ratio = contrast(ink, surface)
                        assertTrue("$light dark=$dark ink=$ink surface=$surface ratio=$ratio", ratio >= 4.5f)
                    }
                }
            }
        }
    }

    @Test @Config(sdk = [34]) fun everyTintPresetKeepsItsAccentAndPrimaryActionReadable() {
        for (dark in listOf(false, true)) {
            val p = DesignTokens.of(DesignLanguage.APPLE, dark).palette
            val onAction = if (dark) Color(0xFF070B16) else Color.White
            val accents = AppThemePreset.entries.map { lumenAccent(it.id, dark) } + LiturgicalLight.entries.map { it.accent(dark) }
            for (accent in accents) {
                for (surface in listOf(p.canvasTop, p.canvasBottom, p.groupedSurface, p.raised,
                    p.pane.compositeOver(p.canvasBottom), p.glass.compositeOver(p.canvasBottom))) {
                    assertTrue("accent=$accent dark=$dark surface=$surface", contrast(accent, surface) >= 4.5f)
                }
                assertTrue("action label on $accent, dark=$dark", contrast(accent, onAction) >= 4.5f)
                // The tinted pane is a gradient: its label must hold at both ends.
                val spec = DesignTokens.of(DesignLanguage.APPLE, dark).tintedGlassSpec(accent)
                assertTrue("tinted glass top, $accent dark=$dark", contrast(spec.fillTop, onAction) >= 4.0f)
                assertTrue("tinted glass bottom, $accent dark=$dark", contrast(spec.fillBottom, onAction) >= 4.5f)
            }
        }
    }

    /** Clear glass hides nothing, so its inks must hold on the bare, brightened light and through the lens. */
    @Test @Config(sdk = [34]) fun clearGlassKeepsEveryInkReadableOnTheLightItLetsThrough() {
        for (dark in listOf(false, true)) {
            val design = DesignTokens.of(DesignLanguage.GLASS, dark)
            val p = design.palette
            val lights = LiturgicalLight.entries.map { it.accent(dark) to it.ambient(dark) } +
                AppThemePreset.entries.map { lumenAccent(it.id, dark) to com.example.ui.theme.lumenAmbientForTests(it.id, dark) }
            for ((rawAccent, rawPools) in lights) {
                val accent = com.example.ui.theme.glassAccent(rawAccent, dark)
                val pools = com.example.ui.theme.glassAmbient(rawPools)
                // Bare light, and the same light as a lens shows it (brighter).
                val grounds = listOf(p.canvasTop, p.canvasBottom).flatMap { ground ->
                    listOf(ground) + pools.flatMap { pool ->
                        listOf(pool, pool.copy(alpha = (pool.alpha * com.example.ui.design.LensGain).coerceAtMost(1f)))
                            .map { it.compositeOver(ground) }
                    }
                }
                fun through(role: GlassRole): List<Color> {
                    val spec = design.glassSpec(role)
                    return grounds.flatMap { g -> listOf(spec.fillTop.compositeOver(g), spec.fillBottom.compositeOver(g)) }
                }
                val panes = listOf(GlassRole.Content, GlassRole.Control, GlassRole.Floating).flatMap { through(it) }
                for (surface in grounds + panes) {
                    for (ink in listOf(p.textPrimary, p.textSecondary, p.textTertiary, accent)) {
                        val ratio = contrast(ink, surface)
                        assertTrue("glass dark=$dark ink=$ink surface=$surface ratio=$ratio", ratio >= 4.5f)
                    }
                }
                // The sliding thumb only ever carries the selected label: primary ink or the accent.
                for (surface in through(GlassRole.Thumb)) {
                    for (ink in listOf(p.textPrimary, accent)) {
                        val ratio = contrast(ink, surface)
                        assertTrue("glass thumb dark=$dark ink=$ink surface=$surface ratio=$ratio", ratio >= 4.5f)
                    }
                }
                val onAction = if (dark) Color(0xFF070B16) else Color.White
                assertTrue("glass action label, dark=$dark", contrast(accent, onAction) >= 4.5f)
            }
            // It really is clear: no pane is dense enough to hide the light.
            assertTrue(design.glassSpec(GlassRole.Content).fillTop.alpha < 0.35f)
            assertTrue(design.glassSpec(GlassRole.Control).fillTop.alpha < 0.25f)
            assertTrue(design.chromeGlassSpec(live = false).fillTop.alpha < 0.35f)
            assertTrue(design.glassSpec(GlassRole.Content).lens > 0f)
        }
        // With transparency reduced it becomes solid, like every identity.
        val solid = DesignTokens.of(DesignLanguage.GLASS, false).copy(reduceTransparency = true)
        assertEquals(1f, solid.glassSpec(GlassRole.Content).fillBottom.alpha)
        assertEquals(0f, solid.glassSpec(GlassRole.Content).lens)
    }

    @Test @Config(sdk = [34]) fun liturgicalColoursResolveToTheirOwnLight() {
        assertEquals(LiturgicalLight.GREEN, LiturgicalLight.of(0xFF2C6B3E))
        assertEquals(LiturgicalLight.VIOLET, LiturgicalLight.of(0xFF5E2777))
        assertEquals(LiturgicalLight.WHITE, LiturgicalLight.of(0xFFD4AF37))
        assertEquals(LiturgicalLight.RED, LiturgicalLight.of(0xFF9E1F27))
        assertEquals(LiturgicalLight.ROSE, LiturgicalLight.of(0xFFB85977))
        assertNull(LiturgicalLight.of(null))
        LiturgicalLight.entries.forEach {
            assertEquals(4, it.ambient(false).size)
            assertEquals(4, it.ambient(true).size)
        }
    }

    @Test @Config(sdk = [34]) fun opaqueModesReplaceGlassWithSolidMaterial() {
        val glass = DesignTokens.of(DesignLanguage.APPLE, false)
        assertTrue(glass.glassSpec(GlassRole.Content).fillBottom.alpha < 1f)
        assertTrue(glass.chromeGlassSpec(live = true).fillBottom.alpha < glass.chromeGlassSpec(live = false).fillBottom.alpha)
        for (design in listOf(
            glass.copy(reduceTransparency = true), glass.copy(highContrast = true),
            DesignTokens.of(DesignLanguage.SANCTUARY, false), DesignTokens.of(DesignLanguage.SANCTUARY, true)
        )) {
            GlassRole.entries.forEach { role ->
                assertEquals("$role must be opaque", 1f, design.glassSpec(role).fillBottom.alpha)
                assertEquals(0f, design.glassSpec(role).sheen)
            }
            assertEquals(1f, design.chromeGlassSpec(live = true).fillBottom.alpha)
            assertTrue(design.palette.ambient.isEmpty() || design.reduceTransparency || design.highContrast)
        }
    }

    @Test @Config(sdk = [34]) fun theLensShaderCompilesOnSupportedAndroid() {
        assertNotNull("The lens shader must compile", LiquidLens.create())
    }

    @Test @Config(sdk = [28]) fun olderAndroidFallsBackToDenseGlass() {
        assertNull(LiquidLens.create())
        assertFalse(com.example.ui.design.liquidBlurSupportedForTests())
    }
}
