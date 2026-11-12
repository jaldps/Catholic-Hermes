package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp

val LocalAppTextStyles = staticCompositionLocalOf {
    AppTypographyFactory.build(DesignLanguage.APPLE, serifPreferred = true, scale = 1f).second
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
    designLanguage: String = DesignLanguage.APPLE.id,
    themePreset: String = "LITURGICAL_DYNAMIC",
    darkModeSetting: String = "SYSTEM",
    isSerif: Boolean = true,
    fontScale: Float = 1.0f,
    fontFamilyChoice: String? = null,
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val isDark = when (darkModeSetting) {
        "DARK" -> true
        "LIGHT" -> false
        else -> systemDark
    }

    val identity = DesignLanguage.fromId(designLanguage)
    val base = DesignTokens.of(identity, isDark)
    val (typography, textStyles) = AppTypographyFactory.build(identity, isSerif, fontScale, fontFamilyChoice)

    // The preset only tints: Lumen calibrates each preset's ink so it clears
    // 4.5:1 on canvas and glass in both modes.
    val accent = lumenAccent(themePreset, isDark)
    val design = base.copy(palette = base.palette.copy(accent = accent, ringEnd = if (themePreset == "LITURGICAL_DYNAMIC") base.palette.ringEnd else accent))
    val colorScheme = buildColorScheme(identity, design.palette, isDark, accent)

    // Edge-to-edge: system bar glyphs follow the app's own mode, not the
    // system's, so a light app on a dark phone still gets dark status icons.
    val view = androidx.compose.ui.platform.LocalView.current
    if (!view.isInEditMode) {
        androidx.compose.runtime.SideEffect {
            val window = (view.context as? android.app.Activity)?.window ?: return@SideEffect
            androidx.core.view.WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !isDark
                isAppearanceLightNavigationBars = !isDark
            }
        }
    }

    CompositionLocalProvider(
        LocalAppDesign provides design,
        LocalAppTextStyles provides textStyles
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            // Straight minor corners like the kit buttons: extraSmall takes
            // buttonSmall (8dp), small takes button (12dp) so every M3
            // component (Button, chips, menus) renders with slightly rounded
            // corners instead of the 4dp field / 50% pill defaults.
            shapes = Shapes(
                extraSmall = design.shapes.buttonSmall,
                small = design.shapes.button,
                medium = design.shapes.card,
                large = design.shapes.cardLarge,
                extraLarge = RoundedCornerShape(28.dp)
            ),
            content = content
        )
    }
}

/** Lumen ink for each tint preset, light and dark. */
private fun lumenAccent(preset: String, isDark: Boolean): Color = when (preset) {
    "MONASTIC_MINIMALIST" -> if (isDark) Color(0xFFAEB8C6) else Color(0xFF465163)
    "SACRED_BURGUNDY" -> if (isDark) Color(0xFFE59AA6) else Color(0xFF8A2D3B)
    "MARIAN_AZURE" -> if (isDark) Color(0xFF8FC2EC) else Color(0xFF2A6597)
    else -> if (isDark) Color(0xFF7FC48A) else Color(0xFF2F7A3E)
}

/**
 * Maps an identity's [AppPalette] onto the Material colour roles, tinted with
 * [accent]. Each identity assigns the roles differently on purpose: Cupertino
 * keeps surfaces flat and near-white so hairlines do the separating, Material
 * steps its surfaces tonally.
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
        DesignLanguage.APPLE, DesignLanguage.IVE -> base.copy(
            primary = accent,
            onPrimary = onAccent,
            primaryContainer = palette.accentMuted,
            onPrimaryContainer = accent,
            secondary = palette.accent,
            onSecondary = if (palette.accent.contrastsBetterWithWhite()) Color.White else Color.Black,
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

 
    }
}

/**
 * Tonal step of [this] colour. With [containerAmount] it blends toward the
 * far end (white in light, black in dark) to make a container (≈ tone 90/30).
 * With [onAmount] (small) it blends the *other* way — toward black in light
 * (≈ tone 10) and toward white in dark (≈ tone 90) — so an on-colour reads
 * on its container while staying in the accent's family.
 */
private fun Color.tonalToward(isDark: Boolean, containerAmount: Float? = null, onAmount: Float? = null): Color {
    return when {
        containerAmount != null -> lerp(this, if (isDark) Color.Black else Color.White, containerAmount)
        else -> lerp(this, if (isDark) Color.White else Color.Black, onAmount ?: 0f)
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
