package com.example

import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.ThemePalettes
import com.example.ui.theme.buildIdentity
import com.example.ui.theme.buildSpacing
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Guards the AAA requirement that every theme preset transforms the whole visual
 * language (color + typography + shapes + spacing + icons + transparency + motion),
 * and that no preset silently falls back to another one's tokens.
 */
class ThemeIdentityTokensTest {

    private val presetIds = AppThemePreset.values().map { it.id }

    @Test
    fun everyPresetHasDistinctLightAndDarkPalettes() {
        presetIds.forEach { id ->
            val light = ThemePalettes.getColorScheme(id, isDark = false)
            val dark = ThemePalettes.getColorScheme(id, isDark = true)
            assertNotEquals("$id must differ between light and dark", light.primary, dark.primary)
        }
    }

    @Test
    fun unknownPresetFallsBackToLiturgicalGreen() {
        val fallback = ThemePalettes.getColorScheme("DOES_NOT_EXIST", isDark = false)
        val liturgical = ThemePalettes.getColorScheme("LITURGICAL_DYNAMIC", isDark = false)
        assertEquals(liturgical.primary, fallback.primary)
    }

    @Test
    fun appleIdentityUsesFrostedFlatChrome() {
        val apple = buildIdentity("APPLE_CLEAN")
        assertTrue("HIG bars are translucent", apple.navContainerAlpha < 1f)
        assertEquals("HIG bars cast no shadow", 0.dp, apple.navElevation)
        assertEquals("HIG action icons are 20dp", 20.dp, apple.actionIconSize)
        assertEquals("HIG motion is the snappiest", 180, apple.motionMs)
    }

    @Test
    fun materialIdentityUsesTonalLayeringNotShadows() {
        val material = buildIdentity("MATERIAL_CLEAN")
        assertEquals("Material 3 layers by tonal elevation", 3.dp, material.navTonalElevation)
        assertEquals("Material 3 avoids drop shadows", 0.dp, material.navElevation)
        assertEquals("Material bars stay opaque", 1f, material.navContainerAlpha)
        assertEquals("Material nav icons are 24dp", 24.dp, material.navIconSize)
    }

    @Test
    fun monasticAndImperialIdentitiesAreOpposites() {
        val monastic = buildIdentity("MONASTIC_MINIMALIST")
        val imperial = buildIdentity("SACRED_BURGUNDY")
        assertEquals(20.dp, monastic.navIconSize)
        assertEquals(22.dp, imperial.navIconSize)
        assertEquals(0.dp, monastic.navElevation)
        assertEquals(10.dp, imperial.navElevation)
        assertTrue("imperial tracking must exceed monastic", imperial.titleTracking.value >= monastic.titleTracking.value)
        assertTrue("imperial pacing must be slower", imperial.motionMs > monastic.motionMs)
    }

    @Test
    fun spacingAndShapesAlsoFollowThePreset() {
        assertNotEquals(
            "Apple spacing must be denser than the default grid",
            buildSpacing("LITURGICAL_DYNAMIC").xl,
            buildSpacing("APPLE_CLEAN").xl
        )
        assertNotEquals(
            "Apple geometry must be rounder than Material's",
            ThemePalettes.buildShapes("APPLE_CLEAN").large,
            ThemePalettes.buildShapes("MATERIAL_CLEAN").large
        )
        val appleType = ThemePalettes.buildScaledTypography(false, 1f, "APPLE_CLEAN")
        assertTrue("Apple typography tightens titles", appleType.titleLarge.letterSpacing.value < 0f)
        val materialType = ThemePalettes.buildScaledTypography(false, 1f, "MATERIAL_CLEAN")
        assertTrue("Material typography widens labels", materialType.labelSmall.letterSpacing.value > 0f)
    }

    @Test
    fun noPresetIsAVisualNoOp() {
        val signatures = presetIds.associateWith { id ->
            val scheme = ThemePalettes.getColorScheme(id, isDark = false)
            val identity = buildIdentity(id)
            listOf(
                scheme.primary.value,
                scheme.surfaceVariant.value,
                ThemePalettes.buildShapes(id).large.toString(),
                buildSpacing(id).xl.value,
                identity.navIconSize.value,
                identity.navElevation.value,
                identity.navContainerAlpha,
                identity.motionMs
            ).joinToString("|")
        }
        val bySignature = signatures.entries.groupBy({ it.value }, { it.key })
        val collisions = bySignature.filterValues { it.size > 1 }
        assertTrue(
            "these presets are indistinguishable in rendered output: $collisions",
            collisions.isEmpty()
        )
        assertEquals(AppThemePreset.values().size, bySignature.size)
    }
}
