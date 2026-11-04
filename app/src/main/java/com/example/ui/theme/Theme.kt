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
    val design = DesignTokens.of(identity, isDark)
    val (typography, textStyles) = AppTypographyFactory.build(identity, isSerif, fontScale, fontFamilyChoice)

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

        DesignLanguage.MATERIAL -> {
            // Tonal containers struck from the accent itself (M3 seeded tonal
            // palette), not the palette's fixed tints. In light, accent toned
            // toward white (≈ tone 90/87); in dark, toward black (≈ tone 30/25).
            // onPrimaryContainer is the accent's own tone-10/90 so legible text
            // on the container stays in-family. onSurfaceVariant/outline* are
            // seeded neutral tones, and outlineVariant is deliberately paler
            // than outline (M3 outlineVariant is the hairline used for dividers
            // and chip borders).
            val container = accent.tonalToward(isDark, containerAmount = 0.76f)
            val onContainer = accent.tonalToward(isDark, onAmount = 0.78f)
            val secondaryContainer = accent.tonalToward(isDark, containerAmount = 0.84f)
            val onSecondaryContainer = accent.tonalToward(isDark, onAmount = 0.82f)
            val goldContainer = palette.gold.tonalToward(isDark, containerAmount = 0.80f)
            val onGoldContainer = palette.gold.tonalToward(isDark, onAmount = 0.78f)
            // Deep end of the surfaceContainer ladder (see below).
            val ladderEnd = lerp(palette.raised, palette.hairline, 0.5f)
            base.copy(
                primary = accent,
                onPrimary = onAccent,
                primaryContainer = container,
                onPrimaryContainer = onContainer,
                secondary = palette.accent,
                onSecondary = if (palette.accent.contrastsBetterWithWhite()) Color.White else Color.Black,
                secondaryContainer = secondaryContainer,
                onSecondaryContainer = onSecondaryContainer,
                tertiary = palette.gold,
                onTertiary = if (palette.gold.contrastsBetterWithWhite()) Color.White else Color.Black,
                tertiaryContainer = goldContainer,
                onTertiaryContainer = onGoldContainer,
                error = base.error,
                onError = base.onError,
                errorContainer = base.errorContainer,
                onErrorContainer = base.onErrorContainer,
                background = palette.canvas,
                onBackground = palette.textPrimary,
                surface = palette.canvas,
                onSurface = palette.textPrimary,
                surfaceVariant = palette.raised,
                onSurfaceVariant = palette.textSecondary,
                // Surface tint carries the accent under elevated surfaces.
                surfaceTint = accent,
                surfaceBright = palette.groupedSurface,
                surfaceDim = if (isDark) palette.canvas else Color(0xFFDADFD9),
                // Five distinct steps, stepped from the canvas itself — not
                // from groupedSurface. The old groupedSurface→raised ladder
                // sat almost on top of the canvas in light mode (the middle
                // step was ~0.01 of a channel off surface), so list cards —
                // which read surfaceContainer — were invisible on it. The deep
                // end is halfway from raised toward hairline, the palette's
                // own mid tone, so the full ladder spans a visible tone range
                // in both modes without leaving the palette family: in light
                // the steps darken toward it, in dark they lighten toward it,
                // and every step (including the middle, surfaceContainer)
                // clears the canvas by a visible margin. The tonal
                // containers above stay accent-derived and untouched.
                surfaceContainerLowest = lerp(palette.canvas, ladderEnd, 0.08f),
                surfaceContainerLow = lerp(palette.canvas, ladderEnd, 0.30f),
                surfaceContainer = lerp(palette.canvas, ladderEnd, 0.52f),
                surfaceContainerHigh = lerp(palette.canvas, ladderEnd, 0.76f),
                surfaceContainerHighest = ladderEnd,
                outline = palette.hairline,
                outlineVariant = lerp(palette.hairline, palette.canvas, 0.45f),
                scrim = palette.scrim,
                inversePrimary = accent,
                inverseSurface = if (isDark) palette.groupedSurface else palette.raised,
                inverseOnSurface = palette.textPrimary
            )
        }
 
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
