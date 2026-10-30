package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

val LocalAppTextStyles = staticCompositionLocalOf {
    AppTypographyFactory.build(DesignLanguage.VATICAN, serifPreferred = true, scale = 1f).second
}

/**
 * The app's single theme entry point.
 *
 * Two axes compose here and mean different things. [designLanguage] decides the
 * structure of the interface — type, shape, spacing, depth, motion, navigation
 * and ornament. [themePreset] decides only the accent the identity is tinted
 * with, which is how a real iOS or Material app carries a brand colour without
 * abandoning its platform idiom.
 */
@Composable
fun SanctaFidesTheme(
    designLanguage: String = DesignLanguage.VATICAN.id,
    themePreset: String = "LITURGICAL_DYNAMIC",
    darkModeSetting: String = "SYSTEM",
    isSerif: Boolean = true,
    fontScale: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val isDark = when (darkModeSetting) {
        "DARK" -> true
        "LIGHT" -> false
        else -> systemDark
    }

    val identity = DesignLanguage.fromId(designLanguage)
    val design = DesignTokens.of(identity, isDark)
    val (typography, textStyles) = AppTypographyFactory.build(identity, isSerif, fontScale)

    val accent = ThemePalettes.getColorScheme(themePreset, isDark).primary
    val colorScheme = buildColorScheme(identity, design.palette, isDark, accent)

    CompositionLocalProvider(
        LocalAppDesign provides design,
        LocalAppTextStyles provides textStyles
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = DesignTokens.materialShapes(design.shapes),
            content = content
        )
    }
}

/**
 * Maps an identity's [AppPalette] onto the Material colour roles, tinted with
 * [accent]. Each identity assigns the roles differently on purpose: Cupertino
 * keeps surfaces flat and near-white so hairlines do the separating, Material
 * steps its surfaces tonally, and Vatican sits everything on parchment.
 */
private fun buildColorScheme(
    identity: DesignLanguage,
    palette: AppPalette,
    isDark: Boolean,
    accent: Color
): ColorScheme {
    val onAccent = if (accent.contrastsBetterWithWhite()) Color.White else Color.Black
    val base = if (isDark) darkColorScheme() else lightColorScheme()

    return when (identity) {
        DesignLanguage.APPLE -> base.copy(
            primary = accent,
            onPrimary = onAccent,
            primaryContainer = palette.accentMuted,
            onPrimaryContainer = accent,
            secondary = palette.accent,
            onSecondary = Color.White,
            secondaryContainer = palette.selectionTint,
            onSecondaryContainer = palette.textPrimary,
            tertiary = palette.gold,
            onTertiary = Color.White,
            background = palette.canvas,
            onBackground = palette.textPrimary,
            surface = palette.groupedSurface,
            onSurface = palette.textPrimary,
            surfaceVariant = palette.raised,
            onSurfaceVariant = palette.textSecondary,
            surfaceContainerLowest = palette.groupedSurface,
            surfaceContainerLow = palette.groupedSurface,
            surfaceContainer = palette.raised,
            surfaceContainerHigh = palette.raised,
            surfaceContainerHighest = palette.raised,
            outline = palette.hairline,
            outlineVariant = palette.hairline,
            scrim = palette.scrim
        )

        DesignLanguage.MATERIAL -> base.copy(
            primary = accent,
            onPrimary = onAccent,
            primaryContainer = palette.accentMuted,
            onPrimaryContainer = if (isDark) palette.textPrimary else accent,
            secondary = palette.accent,
            onSecondary = Color.White,
            secondaryContainer = palette.selectionTint,
            onSecondaryContainer = palette.textPrimary,
            tertiary = palette.gold,
            onTertiary = Color.White,
            background = palette.canvas,
            onBackground = palette.textPrimary,
            surface = palette.canvas,
            onSurface = palette.textPrimary,
            surfaceVariant = palette.raised,
            onSurfaceVariant = palette.textSecondary,
            // Five distinct steps, not two values spread over five roles.
            // Material separates layers by tone rather than by shadow, so a
            // collapsed ladder leaves dark mode with no visible hierarchy at
            // all — every card, row and tile renders the same colour.
            surfaceContainerLowest = palette.groupedSurface,
            surfaceContainerLow = lerp(palette.groupedSurface, palette.raised, 0.25f),
            surfaceContainer = lerp(palette.groupedSurface, palette.raised, 0.5f),
            surfaceContainerHigh = lerp(palette.groupedSurface, palette.raised, 0.75f),
            surfaceContainerHighest = palette.raised,
            outline = palette.hairline,
            outlineVariant = palette.hairline,
            scrim = palette.scrim
        )

        DesignLanguage.VATICAN -> base.copy(
            primary = accent,
            onPrimary = onAccent,
            primaryContainer = palette.accentMuted,
            onPrimaryContainer = palette.textPrimary,
            secondary = palette.gold,
            onSecondary = if (isDark) Color.Black else Color.White,
            secondaryContainer = palette.selectionTint,
            onSecondaryContainer = palette.textPrimary,
            tertiary = palette.accent,
            onTertiary = Color.White,
            background = palette.canvas,
            onBackground = palette.textPrimary,
            surface = palette.groupedSurface,
            onSurface = palette.textPrimary,
            surfaceVariant = palette.raised,
            onSurfaceVariant = palette.textSecondary,
            surfaceContainerLowest = palette.raised,
            surfaceContainerLow = palette.groupedSurface,
            surfaceContainer = palette.groupedSurface,
            surfaceContainerHigh = palette.raised,
            surfaceContainerHighest = palette.raised,
            outline = palette.hairline,
            outlineVariant = palette.hairline,
            scrim = palette.scrim
        )
    }
}

/** Relative luminance, used to pick legible text over an arbitrary accent. */
private fun Color.contrastsBetterWithWhite(): Boolean {
    fun channel(c: Float): Double {
        val v = c.toDouble()
        return if (v <= 0.03928) v / 12.92 else Math.pow((v + 0.055) / 1.055, 2.4)
    }
    val luminance = 0.2126 * channel(red) + 0.7152 * channel(green) + 0.0722 * channel(blue)
    val contrastWithWhite = 1.05 / (luminance + 0.05)
    val contrastWithBlack = (luminance + 0.05) / 0.05
    return contrastWithWhite >= contrastWithBlack
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    SanctaFidesTheme(
        darkModeSetting = if (darkTheme) "DARK" else "LIGHT",
        content = content
    )
}
