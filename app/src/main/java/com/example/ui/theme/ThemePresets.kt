package com.example.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.data.models.LocalizedText

/**
 * Three intentionally different visual systems. The IDs are persisted in Room,
 * so normalize legacy preset names to Vatican instead of breaking existing installs.
 */
enum class AppThemePreset(val id: String, val label: LocalizedText, val previewPrimary: Color) {
    VATICAN(
        "VATICAN",
        LocalizedText("Vaticano · Estilo editorial", "Vatican · Ecclesial editorial", "Vaticano · Editorial eclesial"),
        Color(0xFF486F87)
    ),
    APPLE(
        "APPLE",
        LocalizedText("Apple · Human Interface", "Apple · Human Interface", "Apple · Human Interface"),
        Color(0xFF0066CC)
    ),
    MATERIAL(
        "MATERIAL",
        LocalizedText("Android · Material 3 Expressive", "Android · Material 3 Expressive", "Android · Material 3 Expressive"),
        Color(0xFF6750A4)
    );

    companion object {
        fun normalizeId(id: String?): String = when (id?.uppercase()) {
            APPLE.id -> APPLE.id
            MATERIAL.id -> MATERIAL.id
            // Older installs used LITURGICAL, LITURGICAL_DYNAMIC, or one of
            // the previous palette names. Keep them readable as Vatican style.
            else -> VATICAN.id
        }
    }
}

object ThemePalettes {
    private enum class Season { PURPLE, GOLD, GREEN, RED }

    private fun seasonOf(name: String): Season = when {
        listOf("advent", "lent", "cuaresma", "quaresma", "adviento", "advento")
            .any { name.contains(it, ignoreCase = true) } -> Season.PURPLE
        listOf("christmas", "easter", "navidad", "pascua", "natal", "páscoa", "pascoa")
            .any { name.contains(it, ignoreCase = true) } -> Season.GOLD
        listOf("ordinary", "ordinario", "comum")
            .any { name.contains(it, ignoreCase = true) } -> Season.GREEN
        else -> Season.RED
    }

    private fun seasonAccent(name: String, dark: Boolean): Color = when (seasonOf(name)) {
        Season.PURPLE -> if (dark) Color(0xFFD8B7E5) else Color(0xFF684477)
        Season.GOLD -> if (dark) Color(0xFFE8CE83) else Color(0xFF856313)
        Season.GREEN -> if (dark) Color(0xFF9BD2A7) else Color(0xFF28623D)
        Season.RED -> if (dark) Color(0xFFFFB5B9) else Color(0xFF8C2F39)
    }

    private fun seasonContainer(name: String, dark: Boolean): Color = when (seasonOf(name)) {
        Season.PURPLE -> if (dark) Color(0xFF392841) else Color(0xFFF0E4F3)
        Season.GOLD -> if (dark) Color(0xFF3A321E) else Color(0xFFF5EBD0)
        Season.GREEN -> if (dark) Color(0xFF22372A) else Color(0xFFE1EEE3)
        Season.RED -> if (dark) Color(0xFF3E272B) else Color(0xFFF5E3E4)
    }

    /**
     * Vatican identity: sky-and-stone surfaces, editorial typography, blue
     * institutional chrome and a smaller accent that follows the liturgical time.
     * It is inspired by the official Vatican.va visual direction, not an official
     * Vatican product or logo.
     */
    fun vatican(isDark: Boolean, season: String): ColorScheme {
        val accent = seasonAccent(season, isDark)
        val accentContainer = seasonContainer(season, isDark)
        return if (isDark) darkColorScheme(
            primary = Color(0xFF9BC4D8),
            onPrimary = Color(0xFF102A36),
            primaryContainer = Color(0xFF244656),
            onPrimaryContainer = Color(0xFFDDEFF6),
            secondary = accent,
            onSecondary = Color(0xFF1A1A18),
            secondaryContainer = accentContainer,
            onSecondaryContainer = Color(0xFFF2E9EE),
            tertiary = Color(0xFFE1C78D),
            onTertiary = Color(0xFF30240B),
            background = Color(0xFF11171B),
            onBackground = Color(0xFFE9ECEE),
            surface = Color(0xFF182126),
            onSurface = Color(0xFFE9ECEE),
            surfaceVariant = Color(0xFF24323A),
            onSurfaceVariant = Color(0xFFB9C6CD),
            outline = Color(0xFF4A5A62)
        ) else lightColorScheme(
            primary = Color(0xFF486F87),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFDCEAF0),
            onPrimaryContainer = Color(0xFF153746),
            secondary = accent,
            onSecondary = Color.White,
            secondaryContainer = accentContainer,
            onSecondaryContainer = Color(0xFF2C2528),
            tertiary = Color(0xFF9A762E),
            onTertiary = Color.White,
            background = Color(0xFFF6F3ED),
            onBackground = Color(0xFF252C30),
            surface = Color(0xFFFFFEFC),
            onSurface = Color(0xFF252C30),
            surfaceVariant = Color(0xFFE8EFF2),
            onSurfaceVariant = Color(0xFF536169),
            outline = Color(0xFFC9D4D9)
        )
    }

    /** Apple-inspired system gray, blue accents, restrained borders and flat chrome. */
    fun apple(isDark: Boolean): ColorScheme = if (isDark) darkColorScheme(
        primary = Color(0xFF0071E3),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF163D62),
        onPrimaryContainer = Color(0xFFD9EAFF),
        secondary = Color(0xFF64D2FF),
        onSecondary = Color(0xFF092737),
        secondaryContainer = Color(0xFF183B4B),
        onSecondaryContainer = Color(0xFFD9F5FF),
        tertiary = Color(0xFFBFBAFF),
        onTertiary = Color(0xFF211D52),
        background = Color(0xFF000000),
        onBackground = Color(0xFFF5F5F7),
        surface = Color(0xFF1C1C1E),
        onSurface = Color(0xFFF5F5F7),
        surfaceVariant = Color(0xFF2C2C2E),
        onSurfaceVariant = Color(0xFFAEAEB2),
        outline = Color(0xFF48484A)
    ) else lightColorScheme(
        primary = Color(0xFF0066CC),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFDCEBFF),
        onPrimaryContainer = Color(0xFF003D80),
        secondary = Color(0xFF5856D6),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFE8E7FF),
        onSecondaryContainer = Color(0xFF29266F),
        tertiary = Color(0xFF9850C7),
        onTertiary = Color.White,
        background = Color(0xFFF2F2F7),
        onBackground = Color(0xFF1C1C1E),
        surface = Color.White,
        onSurface = Color(0xFF1C1C1E),
        surfaceVariant = Color(0xFFF2F2F7),
        onSurfaceVariant = Color(0xFF6C6C70),
        outline = Color(0xFFD1D1D6)
    )

    /** Material 3 tonal palette with a more expressive, shape-aware hierarchy. */
    fun material(isDark: Boolean): ColorScheme = if (isDark) darkColorScheme(
        primary = Color(0xFFD0BCFF),
        onPrimary = Color(0xFF381E72),
        primaryContainer = Color(0xFF4F378B),
        onPrimaryContainer = Color(0xFFEADDFF),
        secondary = Color(0xFFCCC2DC),
        onSecondary = Color(0xFF332D41),
        secondaryContainer = Color(0xFF4A4458),
        onSecondaryContainer = Color(0xFFE8DEF8),
        tertiary = Color(0xFFEFB8C8),
        onTertiary = Color(0xFF492532),
        background = Color(0xFF141218),
        onBackground = Color(0xFFE6E0E9),
        surface = Color(0xFF141218),
        onSurface = Color(0xFFE6E0E9),
        surfaceVariant = Color(0xFF49454F),
        onSurfaceVariant = Color(0xFFCAC4D0),
        outline = Color(0xFF938F99)
    ) else lightColorScheme(
        primary = Color(0xFF6750A4),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFEADDFF),
        onPrimaryContainer = Color(0xFF21005D),
        secondary = Color(0xFF625B71),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFE8DEF8),
        onSecondaryContainer = Color(0xFF1D192B),
        tertiary = Color(0xFF7D5260),
        onTertiary = Color.White,
        background = Color(0xFFFFFBFE),
        onBackground = Color(0xFF1C1B1F),
        surface = Color(0xFFFFFBFE),
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFE7E0EC),
        onSurfaceVariant = Color(0xFF49454F),
        outline = Color(0xFF79747E)
    )

    fun getColorScheme(presetId: String, isDark: Boolean, season: String): ColorScheme =
        when (AppThemePreset.normalizeId(presetId)) {
            AppThemePreset.APPLE.id -> apple(isDark)
            AppThemePreset.MATERIAL.id -> material(isDark)
            else -> vatican(isDark, season)
        }

    fun buildScaledTypography(presetId: String, isSerif: Boolean, scale: Float): Typography {
        val preset = AppThemePreset.normalizeId(presetId)
        val family = if (preset == AppThemePreset.VATICAN.id && isSerif) FontFamily.Serif else FontFamily.Default
        val displayWeight = if (preset == AppThemePreset.APPLE.id) FontWeight.SemiBold else FontWeight.Bold
        return Typography(
            displayLarge = TextStyle(fontFamily = family, fontWeight = displayWeight, fontSize = (36 * scale).sp, lineHeight = (43 * scale).sp),
            headlineLarge = TextStyle(fontFamily = family, fontWeight = displayWeight, fontSize = (30 * scale).sp, lineHeight = (36 * scale).sp),
            headlineMedium = TextStyle(fontFamily = family, fontWeight = FontWeight.SemiBold, fontSize = (24 * scale).sp, lineHeight = (30 * scale).sp),
            headlineSmall = TextStyle(fontFamily = family, fontWeight = FontWeight.SemiBold, fontSize = (20 * scale).sp, lineHeight = (26 * scale).sp),
            titleLarge = TextStyle(fontFamily = family, fontWeight = if (preset == AppThemePreset.APPLE.id) FontWeight.SemiBold else FontWeight.Bold, fontSize = (19 * scale).sp, lineHeight = (25 * scale).sp),
            titleMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = (16 * scale).sp, lineHeight = (22 * scale).sp),
            titleSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = (14 * scale).sp, lineHeight = (20 * scale).sp),
            bodyLarge = TextStyle(fontFamily = family, fontWeight = FontWeight.Normal, fontSize = (16 * scale).sp, lineHeight = (26 * scale).sp),
            bodyMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = (14 * scale).sp, lineHeight = (20 * scale).sp),
            bodySmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = (12 * scale).sp, lineHeight = (16 * scale).sp),
            labelLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = (14 * scale).sp, lineHeight = (20 * scale).sp),
            labelMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = (12 * scale).sp, lineHeight = (16 * scale).sp),
            labelSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = (10 * scale).sp, lineHeight = (14 * scale).sp)
        )
    }
}
