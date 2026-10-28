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

enum class AppThemePreset(val id: String, val label: LocalizedText, val previewPrimary: Color) {
    LITURGICAL("LITURGICAL", LocalizedText("Vaticano · Tiempo Litúrgico", "Vatican · Liturgical Season", "Vaticano · Tempo Litúrgico"), Color(0xFF7A1F35)),
    APPLE("APPLE", LocalizedText("Apple · Human Interface", "Apple · Human Interface", "Apple · Human Interface"), Color(0xFF007AFF)),
    MATERIAL("MATERIAL", LocalizedText("Android · Material 3", "Android · Material 3", "Android · Material 3"), Color(0xFF6750A4))
}

object ThemePalettes {
    private fun liturgicalAccent(season: String): Color = when {
        season.contains("advent", true) || season.contains("lent", true) -> Color(0xFF6B3A78)
        season.contains("christmas", true) || season.contains("easter", true) -> Color(0xFFB28A24)
        season.contains("ordinary", true) -> Color(0xFF2F6B43)
        else -> Color(0xFF7A1F35)
    }

    fun liturgical(isDark: Boolean, season: String): ColorScheme {
        val accent = liturgicalAccent(season)
        return if (isDark) darkColorScheme(
            primary = if (accent == Color(0xFFB28A24)) Color(0xFFE4C56A) else accent,
            onPrimary = Color.White,
            primaryContainer = Color(0xFF3A2029),
            onPrimaryContainer = Color(0xFFF4DDE3),
            secondary = Color(0xFFD2B15A),
            onSecondary = Color(0xFF241B08),
            secondaryContainer = Color(0xFF3A3119),
            onSecondaryContainer = Color(0xFFF4E7B8),
            background = Color(0xFF111012),
            onBackground = Color(0xFFF4F0EA),
            surface = Color(0xFF181619),
            onSurface = Color(0xFFF4F0EA),
            surfaceVariant = Color(0xFF211D21),
            onSurfaceVariant = Color(0xFFC9C0C2),
            outline = Color(0xFF51484B)
        ) else lightColorScheme(
            primary = accent,
            onPrimary = Color.White,
            primaryContainer = Color(0xFFF1DEE4),
            onPrimaryContainer = Color(0xFF3A101D),
            secondary = Color(0xFFA68120),
            onSecondary = Color.White,
            secondaryContainer = Color(0xFFF7EFD4),
            onSecondaryContainer = Color(0xFF3C2E08),
            background = Color(0xFFFCFAF6),
            onBackground = Color(0xFF211D1B),
            surface = Color(0xFFF6F1E9),
            onSurface = Color(0xFF211D1B),
            surfaceVariant = Color(0xFFFFFFFF),
            onSurfaceVariant = Color(0xFF625A54),
            outline = Color(0xFFD9D0C6)
        )
    }

    fun apple(isDark: Boolean): ColorScheme = if (isDark) darkColorScheme(
        primary = Color(0xFF0A84FF), onPrimary = Color.White,
        primaryContainer = Color(0xFF173B60), onPrimaryContainer = Color(0xFFD6E9FF),
        secondary = Color(0xFF64D2FF), onSecondary = Color(0xFF06283A),
        secondaryContainer = Color(0xFF183C4C), onSecondaryContainer = Color(0xFFD4F5FF),
        background = Color(0xFF000000), onBackground = Color(0xFFF5F5F7),
        surface = Color(0xFF1C1C1E), onSurface = Color(0xFFF5F5F7),
        surfaceVariant = Color(0xFF2C2C2E), onSurfaceVariant = Color(0xFFAEAEB2),
        outline = Color(0xFF48484A)
    ) else lightColorScheme(
        primary = Color(0xFF007AFF), onPrimary = Color.White,
        primaryContainer = Color(0xFFDCEBFF), onPrimaryContainer = Color(0xFF003D80),
        secondary = Color(0xFF5856D6), onSecondary = Color.White,
        secondaryContainer = Color(0xFFE8E7FF), onSecondaryContainer = Color(0xFF29266F),
        background = Color(0xFFF2F2F7), onBackground = Color(0xFF1C1C1E),
        surface = Color.White, onSurface = Color(0xFF1C1C1E),
        surfaceVariant = Color(0xFFF2F2F7), onSurfaceVariant = Color(0xFF6C6C70),
        outline = Color(0xFFD1D1D6)
    )

    fun material(isDark: Boolean): ColorScheme = if (isDark) darkColorScheme(
        primary = Color(0xFFD0BCFF), onPrimary = Color(0xFF381E72),
        primaryContainer = Color(0xFF4F378B), onPrimaryContainer = Color(0xFFEADDFF),
        secondary = Color(0xFFCCC2DC), onSecondary = Color(0xFF332D41),
        secondaryContainer = Color(0xFF4A4458), onSecondaryContainer = Color(0xFFE8DEF8),
        tertiary = Color(0xFFEFB8C8), onTertiary = Color(0xFF492532),
        background = Color(0xFF141218), onBackground = Color(0xFFE6E0E9),
        surface = Color(0xFF141218), onSurface = Color(0xFFE6E0E9),
        surfaceVariant = Color(0xFF49454F), onSurfaceVariant = Color(0xFFCAC4D0),
        outline = Color(0xFF938F99)
    ) else lightColorScheme(
        primary = Color(0xFF6750A4), onPrimary = Color.White,
        primaryContainer = Color(0xFFEADDFF), onPrimaryContainer = Color(0xFF21005D),
        secondary = Color(0xFF625B71), onSecondary = Color.White,
        secondaryContainer = Color(0xFFE8DEF8), onSecondaryContainer = Color(0xFF1D192B),
        tertiary = Color(0xFF7D5260), onTertiary = Color.White,
        background = Color(0xFFFFFBFE), onBackground = Color(0xFF1C1B1F),
        surface = Color(0xFFFFFBFE), onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFE7E0EC), onSurfaceVariant = Color(0xFF49454F),
        outline = Color(0xFF79747E)
    )

    fun getColorScheme(presetId: String, isDark: Boolean, season: String): ColorScheme = when (presetId) {
        AppThemePreset.APPLE.id -> apple(isDark)
        AppThemePreset.MATERIAL.id -> material(isDark)
        else -> liturgical(isDark, season)
    }

    fun buildScaledTypography(presetId: String, isSerif: Boolean, scale: Float): Typography {
        val family = if (presetId == AppThemePreset.LITURGICAL.id && isSerif) FontFamily.Serif else FontFamily.Default
        val displayWeight = if (presetId == AppThemePreset.APPLE.id) FontWeight.SemiBold else FontWeight.Bold
        return Typography(
            displayLarge = TextStyle(fontFamily = family, fontWeight = displayWeight, fontSize = (36 * scale).sp, lineHeight = (43 * scale).sp),
            headlineLarge = TextStyle(fontFamily = family, fontWeight = displayWeight, fontSize = (30 * scale).sp, lineHeight = (36 * scale).sp),
            headlineMedium = TextStyle(fontFamily = family, fontWeight = FontWeight.SemiBold, fontSize = (24 * scale).sp, lineHeight = (30 * scale).sp),
            headlineSmall = TextStyle(fontFamily = family, fontWeight = FontWeight.SemiBold, fontSize = (20 * scale).sp, lineHeight = (26 * scale).sp),
            titleLarge = TextStyle(fontFamily = family, fontWeight = FontWeight.Bold, fontSize = (19 * scale).sp, lineHeight = (25 * scale).sp),
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
