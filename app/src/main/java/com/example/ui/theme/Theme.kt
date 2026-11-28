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

/**
 * The user's own text size (Settings → Apariencia), 1 = 100 %. The text styles already carry it; layouts read it
 * through [com.example.ui.design.appLargeText] to give long labels room instead of clipping them.
 */
val LocalAppFontScale = staticCompositionLocalOf { 1f }

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
    reduceMotion: Boolean = false,
    reduceTransparency: Boolean = false,
    highContrast: Boolean = false,
    /**
     * Today's liturgical colour. With the "Liturgical Dynamic" tint, Lumen's
     * accent and ambient light follow it through the Church's year; null keeps
     * Ordinary Time green.
     */
    liturgicalColorHex: Long? = null,
    /** Catholic1 dock icons: "LITURGICAL" (today's colour) or "THEME" (navy / ivory). */
    dockIconTint: String = "LITURGICAL",
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
    // Sanctuary and Tradicional keep their own fixed accent under the dynamic tint.
    val classic = identity == DesignLanguage.CLASSIC
    val season = if (identity != DesignLanguage.SANCTUARY && !classic && themePreset == "LITURGICAL_DYNAMIC") LiturgicalLight.of(liturgicalColorHex) else null
    val glass = identity == DesignLanguage.GLASS
    val accent = resolveAccent(themePreset, identity, isDark, season, base.palette.accent)
    // Chrome (ring, muted accent) follows the tint everywhere but Sanctuary; the
    // action fill and ambient light only in the liquid identities.
    val tintsChrome = identity != DesignLanguage.SANCTUARY && !(classic && themePreset == "LITURGICAL_DYNAMIC")
    val liquid = identity != DesignLanguage.SANCTUARY && !classic
    val catholic1 = identity == DesignLanguage.CATHOLIC1
    // Catholic1 is never tinted: its gold and navy are the brand. Today's colour
    // reaches only the dock icons (LocalDockIconTint, CATHOLIC1.md §2.3).
    val tinted = if (catholic1) base.palette else base.palette.copy(
        accent = accent,
        ringEnd = if (themePreset == "LITURGICAL_DYNAMIC" && season == null) base.palette.ringEnd else accent,
        ringStart = if (!tintsChrome || (themePreset == "LITURGICAL_DYNAMIC" && season == null)) base.palette.ringStart
            else lerp(accent, if (isDark) Color(0xFF0A0E1C) else Color.White, 0.68f),
        // Liquid glass tints its primary actions with the preset's accent.
        action = if (liquid) accent else base.palette.action,
        onAction = if (liquid) (if (isDark) Color(0xFF070B16) else Color.White) else base.palette.onAction,
        accentMuted = if (tintsChrome) lerp(accent, if (isDark) Color(0xFF0A0E1C) else Color.White, if (isDark) 0.80f else 0.86f) else base.palette.accentMuted,
        ambient = if (liquid) resolveAmbient(themePreset, identity, isDark, season) else emptyList()
    )
    val accessible = if (highContrast) tinted.copy(
        textSecondary = tinted.textPrimary, textTertiary = tinted.textPrimary,
        hairline = if (isDark) Color(0xFFAC9F92) else Color(0xFF827364),
        separator = if (isDark) Color(0xFF8C8074) else Color(0xFF948575)
    ) else tinted
    val design = base.copy(palette = accessible, reduceMotion = reduceMotion,
        reduceTransparency = reduceTransparency, highContrast = highContrast,
        motion = if (reduceMotion) base.motion.copy(fastMillis = 0, mediumMillis = 0, slowMillis = 0) else base.motion)
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

    val dockTint = if (catholic1 && dockIconTint != "THEME")
        dockLiturgicalInk(LiturgicalLight.of(liturgicalColorHex) ?: LiturgicalLight.GREEN, isDark)
        else design.palette.textPrimary
    CompositionLocalProvider(
        LocalDockIconTint provides dockTint,
        LocalAppDesign provides design,
        LocalAppTextStyles provides textStyles,
        LocalAppFontScale provides fontScale
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

/**
 * The light of the liturgical day: the accent ink (4.5:1 on canvas and glass
 * in both modes) and the four ambient pools for each liturgical colour.
 */
enum class LiturgicalLight(
    private val hex: Long,
    private val accentLight: Long, private val accentDark: Long,
    private val ambientLight: List<Long>, private val ambientDark: List<Long>
) {
    GREEN(0xFF2C6B3E, 0xFF2A6E38, 0xFF7FC48A,
        listOf(0x94A6E0B8, 0x80FFDD9C, 0x5CCFBCFF, 0x6BAAD6F8),
        listOf(0x4D1F6B45, 0x2EE08A3C, 0x385A43D6, 0x331E5C8C)),
    VIOLET(0xFF5E2777, 0xFF6A489E, 0xFFBBA6EC,
        listOf(0x94C9B6F2, 0x80FFDD9C, 0x5CF2BCD6, 0x6BAAC6F8),
        listOf(0x593E2A8C, 0x2EE08A3C, 0x386A2A9C, 0x331E3C8C)),
    WHITE(0xFFD4AF37, 0xFF856014, 0xFFE8C877,
        listOf(0x99FFE6A8, 0x80FFF0CC, 0x5CF6D9C0, 0x6BCFE0F8),
        listOf(0x4D8C6A1E, 0x33E0A03C, 0x33503C8C, 0x2E1E5C8C)),
    RED(0xFF9E1F27, 0xFFA8281F, 0xFFF29B92,
        listOf(0x8CF4B4AC, 0x80FFDD9C, 0x5CF2BCD0, 0x66F8CDB8),
        listOf(0x528C2420, 0x33E08A3C, 0x385A2A8C, 0x33A8323A)),
    ROSE(0xFFB85977, 0xFF93395D, 0xFFF0AAC2,
        listOf(0x94F6BCD2, 0x80FFDD9C, 0x5CD9BCF2, 0x66F8C8D8),
        listOf(0x528C2A58, 0x2EE08A3C, 0x385A3AA8, 0x33A83A6A));

    fun accent(isDark: Boolean) = Color(if (isDark) accentDark else accentLight)
    fun ambient(isDark: Boolean) = (if (isDark) ambientDark else ambientLight).map { Color(it) }

    companion object {
        /** The liturgical colour nearest to [colorHex]; null stays null. */
        fun of(colorHex: Long?): LiturgicalLight? {
            val c = Color(colorHex ?: return null)
            return entries.minBy { e ->
                val o = Color(e.hex)
                val dr = o.red - c.red; val dg = o.green - c.green; val db = o.blue - c.blue
                dr * dr + dg * dg + db * db
            }
        }
    }
}

/**
 * Clear glass lets the ambient light through at full strength, so the Glass
 * identity brightens that light and moves the accent ink further from it:
 * deeper in light mode, paler in dark. Calibrated so every ink still clears
 * 4.5:1 on bare light and through every pane (LiquidGlassTest).
 */
internal const val GlassAmbientBoost = 1.4f
internal fun glassAmbient(pools: List<Color>): List<Color> =
    pools.map { it.copy(alpha = (it.alpha * GlassAmbientBoost).coerceAtMost(1f)) }
internal fun glassAccent(accent: Color, isDark: Boolean): Color =
    if (isDark) lerp(accent, Color.White, 0.45f) else lerp(accent, Color.Black, 0.15f)

/**
 * The accent the app draws for [preset] in [identity]: the season's ink where
 * the liquid identities follow it, [dynamicBase] for the dynamic tint without a
 * season, else the preset's ink, derived to hold 4.5:1 for any base colour.
 */
internal fun resolveAccent(
    preset: String, identity: DesignLanguage, isDark: Boolean,
    season: LiturgicalLight?, dynamicBase: Color
): Color {
    if (preset == "LITURGICAL_DYNAMIC" && season == null &&
        (identity == DesignLanguage.SANCTUARY || identity == DesignLanguage.CLASSIC)) return dynamicBase
    val ink = season?.accent(isDark) ?: lumenAccent(preset, isDark, identity)
    return if (identity == DesignLanguage.GLASS) glassAccent(ink, isDark) else ink
}

/** The four pools of light for [preset] in a liquid [identity]. */
internal fun resolveAmbient(preset: String, identity: DesignLanguage, isDark: Boolean, season: LiturgicalLight?): List<Color> =
    (season?.ambient(isDark) ?: lumenAmbient(preset, isDark)).let { if (identity == DesignLanguage.GLASS) glassAmbient(it) else it }

/**
 * Ink for each tint preset, light and dark. The four ecclesiastical presets are
 * hand-calibrated; every other id (`NE_*`, `CUSTOM_RRGGBB`) is derived from its
 * base colour for [identity] by [AccentColors.ink].
 */
internal fun lumenAccent(preset: String, isDark: Boolean, identity: DesignLanguage = DesignLanguage.APPLE): Color =
    AccentColors.baseOf(preset)?.let { AccentColors.ink(it, identity, isDark) } ?: lumenAccentPreset(preset, isDark)

private fun lumenAccentPreset(preset: String, isDark: Boolean): Color = when (preset) {
    "MONASTIC_MINIMALIST" -> if (isDark) Color(0xFFAEB8C6) else Color(0xFF465163)
    "SACRED_BURGUNDY" -> if (isDark) Color(0xFFE59AA6) else Color(0xFF8A2D3B)
    "MARIAN_AZURE" -> if (isDark) Color(0xFF9DBDFF) else Color(0xFF2459B5)
    else -> if (isDark) Color(0xFF7FC48A) else Color(0xFF2A6E38)
}

/**
 * The four pools of ambient light for each tint preset: the accent's own
 * light top-left, candle gold top-right, a violet shade and a cool return.
 */
private fun lumenAmbient(preset: String, isDark: Boolean): List<Color> =
    AccentColors.baseOf(preset)?.let { AccentColors.ambient(it, isDark) } ?: lumenAmbientPreset(preset, isDark)

private fun lumenAmbientPreset(preset: String, isDark: Boolean): List<Color> = when (preset) {
    "MONASTIC_MINIMALIST" ->
        if (isDark) listOf(Color(0x40485878), Color(0x1FC9A27A), Color(0x2E4A4A8C), Color(0x2E3C5A7A))
        else listOf(Color(0x8CC3D0E6), Color(0x66F3E0C0), Color(0x59D2CEEC), Color(0x66BFD2E8))
    "SACRED_BURGUNDY" ->
        if (isDark) listOf(Color(0x4D8C2A45), Color(0x33E08A3C), Color(0x385A3AA8), Color(0x33A83A5A))
        else listOf(Color(0x8FF4B8C4), Color(0x80FFDD9C), Color(0x5CDDC2F2), Color(0x66F8CBB8))
    "MARIAN_AZURE" ->
        if (isDark) listOf(Color(0x592A5BD6), Color(0x29E0A050), Color(0x406A43D6), Color(0x381E7AA8))
        else listOf(Color(0x99A9C9FF), Color(0x7AFFDFA0), Color(0x5CCFBCFF), Color(0x73ACE0F4))
    else ->
        if (isDark) listOf(Color(0x4D1F6B45), Color(0x2EE08A3C), Color(0x385A43D6), Color(0x331E5C8C))
        else listOf(Color(0x94A6E0B8), Color(0x80FFDD9C), Color(0x5CCFBCFF), Color(0x6BAAD6F8))
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
        DesignLanguage.APPLE, DesignLanguage.IVE, DesignLanguage.CLASSIC, DesignLanguage.GLASS, DesignLanguage.SANCTUARY, DesignLanguage.CATHOLIC1 -> base.copy(
            primary = accent,
            onPrimary = onAccent,
            primaryContainer = palette.accentMuted,
            onPrimaryContainer = accent,
            secondary = palette.accent,
            onSecondary = if (palette.accent.contrastsBetterWithWhite()) Color.White else Color.Black,
            secondaryContainer = palette.selectionTint,
            onSecondaryContainer = palette.textPrimary,
            tertiary = palette.gold,
            onTertiary = if (palette.gold.contrastsBetterWithWhite()) Color.White else Color.Black,
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

/** The ambient pools of a tint preset, for contrast tests. */
internal fun lumenAmbientForTests(preset: String, isDark: Boolean): List<Color> = lumenAmbient(preset, isDark)


/** Ink of the dock's idle icons: today's liturgical colour or the theme ink (CATHOLIC1.md §2.3). */
val LocalDockIconTint = androidx.compose.runtime.staticCompositionLocalOf { Color(0xFF0C2848) }

/**
 * Liturgical icon inks measured against the dock (white by day, #162438 by night):
 * each clears 4.7:1 or more. White vestments read as gold, since white on white vanishes.
 */
internal fun dockLiturgicalInk(season: LiturgicalLight, isDark: Boolean): Color = when (season) {
    // Sober vestment tones (owner, 28-nov), each ≥ 4.5:1 on the dock by day (#FFFFFF) and night (#162438/#1D2D44).
    LiturgicalLight.GREEN -> if (isDark) Color(0xFF86B894) else Color(0xFF2C6B3E)
    LiturgicalLight.VIOLET -> if (isDark) Color(0xFFB6A4D8) else Color(0xFF5B3A86)
    LiturgicalLight.RED -> if (isDark) Color(0xFFE59A8E) else Color(0xFF9E2A2B)
    LiturgicalLight.ROSE -> if (isDark) Color(0xFFE6A6BC) else Color(0xFFA8506E)
    LiturgicalLight.WHITE -> if (isDark) Color(0xFFE3B65A) else Color(0xFF8A5E14)
}
