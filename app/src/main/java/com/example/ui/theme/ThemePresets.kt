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
    LITURGICAL_DYNAMIC(
        "LITURGICAL_DYNAMIC",
        LocalizedText("Dinámico Litúrgico (Tiempo de la Iglesia)", "Liturgical Dynamic (Church Seasons)", "Dinâmico Litúrgico (Tempo da Igreja)"),
        Color(0xFF2C6B3E)
    ),
    MONASTIC_MINIMALIST(
        "MONASTIC_MINIMALIST",
        LocalizedText("Monástico Minimalista (Sobriedad y Piedra)", "Monastic Minimalist (Sober Slate & Ivory)", "Monástico Minimalista (Ardósia e Marfim)"),
        Color(0xFF334155)
    ),
    VATICAN_NAVY(
        "VATICAN_NAVY",
        LocalizedText("Vaticano Azul y Plata (Pontificio)", "Vatican Navy & Silver (Pontifical)", "Vaticano Azul e Prata (Pontifício)"),
        Color(0xFF1E3A8A)
    ),
    SACRED_BURGUNDY(
        "SACRED_BURGUNDY",
        LocalizedText("Borgoña Sagrado y Oro (Tradicional)", "Sacred Burgundy & Gold (Imperial)", "Borgonha Sagrado e Ouro (Imperial)"),
        Color(0xFF6B1D2F)
    ),
    MARIAN_AZURE(
        "MARIAN_AZURE",
        LocalizedText("Azul Mariano Celestial (Nuestra Señora)", "Marian Azure & Celestial (Our Lady)", "Azul Mariano Celeste (Nossa Senhora)"),
        Color(0xFF0284C7)
    )
}

object ThemePalettes {

    // 1. Liturgical Dynamic (Changes with Church Seasons - Defaults to Liturgical Green)
    val LiturgicalGreenLight = lightColorScheme(
        primary = Color(0xFF1B5E20),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFC8E6C9),
        onPrimaryContainer = Color(0xFF003300),
        secondary = Color(0xFFC59B27),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFF9F1D8),
        onSecondaryContainer = Color(0xFF420D1A),
        background = Color(0xFFFBFDFB),
        onBackground = Color(0xFF1A1C1A),
        surface = Color(0xFFF2F6F2),
        onSurface = Color(0xFF1A1C1A),
        surfaceVariant = Color(0xFFFFFFFF),
        onSurfaceVariant = Color(0xFF434942),
        outline = Color(0xFFD0D7D0)
    )

    val LiturgicalGreenDark = darkColorScheme(
        primary = Color(0xFF81C784),
        onPrimary = Color(0xFF003300),
        primaryContainer = Color(0xFF1B5E20),
        onPrimaryContainer = Color(0xFFC8E6C9),
        secondary = Color(0xFFE5C158),
        onSecondary = Color.Black,
        background = Color(0xFF0F1410),
        onBackground = Color(0xFFE2E7E2),
        surface = Color(0xFF171F18),
        onSurface = Color(0xFFE2E7E2),
        surfaceVariant = Color(0xFF1F2920),
        onSurfaceVariant = Color(0xFFB0B8B0),
        outline = Color(0xFF333E34)
    )

    // 2. Monastic Minimalist (Sober Slate, warm ivory, high readability, ultra contemporary)
    val MonasticLight = lightColorScheme(
        primary = Color(0xFF1E293B),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFE2E8F0),
        onPrimaryContainer = Color(0xFF0F172A),
        secondary = Color(0xFF475569),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFF1F5F9),
        onSecondaryContainer = Color(0xFF1E293B),
        background = Color(0xFFFAFAF9),
        onBackground = Color(0xFF0F172A),
        surface = Color(0xFFF5F5F4),
        onSurface = Color(0xFF0F172A),
        surfaceVariant = Color(0xFFFFFFFF),
        onSurfaceVariant = Color(0xFF64748B),
        outline = Color(0xFFCBD5E1)
    )

    val MonasticDark = darkColorScheme(
        primary = Color(0xFF94A3B8),
        onPrimary = Color(0xFF0F172A),
        primaryContainer = Color(0xFF334155),
        onPrimaryContainer = Color(0xFFF1F5F9),
        secondary = Color(0xFF64748B),
        onSecondary = Color.White,
        background = Color(0xFF0B0F17),
        onBackground = Color(0xFFF1F5F9),
        surface = Color(0xFF131B26),
        onSurface = Color(0xFFF1F5F9),
        surfaceVariant = Color(0xFF1E293B),
        onSurfaceVariant = Color(0xFF94A3B8),
        outline = Color(0xFF283548)
    )

    // 3. Vatican Navy & Silver
    val VaticanNavyLight = lightColorScheme(
        primary = Color(0xFF1E3A8A),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFDBEAFE),
        onPrimaryContainer = Color(0xFF172554),
        secondary = Color(0xFFC59B27),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFFEF3C7),
        onSecondaryContainer = Color(0xFF78350F),
        background = Color(0xFFF8FAFC),
        onBackground = Color(0xFF0F172A),
        surface = Color(0xFFEFF6FF),
        onSurface = Color(0xFF0F172A),
        surfaceVariant = Color(0xFFFFFFFF),
        onSurfaceVariant = Color(0xFF475569),
        outline = Color(0xFFBFDBFE)
    )

    val VaticanNavyDark = darkColorScheme(
        primary = Color(0xFF60A5FA),
        onPrimary = Color(0xFF172554),
        primaryContainer = Color(0xFF1E3A8A),
        onPrimaryContainer = Color(0xFFDBEAFE),
        secondary = Color(0xFFFCD34D),
        onSecondary = Color.Black,
        background = Color(0xFF0A0F1D),
        onBackground = Color(0xFFF8FAFC),
        surface = Color(0xFF111827),
        onSurface = Color(0xFFF8FAFC),
        surfaceVariant = Color(0xFF1F2937),
        onSurfaceVariant = Color(0xFF9CA3AF),
        outline = Color(0xFF374151)
    )

    // 4. Sacred Burgundy & Gold
    val SacredBurgundyLightScheme = lightColorScheme(
        primary = SacredBurgundy,
        onPrimary = Color.White,
        primaryContainer = GoldContainerLight,
        onPrimaryContainer = SacredBurgundyDark,
        secondary = LiturgicalGold,
        onSecondary = Color.White,
        secondaryContainer = ParchmentSurface,
        onSecondaryContainer = SacredBurgundy,
        tertiary = SacredBurgundyLight,
        onTertiary = Color.White,
        background = WarmAlabasterBg,
        onBackground = TextSacredDark,
        surface = ParchmentSurface,
        onSurface = TextSacredDark,
        surfaceVariant = ParchmentCard,
        onSurfaceVariant = TextMutedBrown,
        outline = BorderParchment
    )

    val SacredBurgundyDarkScheme = darkColorScheme(
        primary = LiturgicalGoldLight,
        onPrimary = SacredBurgundyDark,
        primaryContainer = SacredBurgundy,
        onPrimaryContainer = LiturgicalGoldLight,
        secondary = LiturgicalGold,
        onSecondary = Color.Black,
        secondaryContainer = GoldContainerDark,
        onSecondaryContainer = LiturgicalGoldLight,
        tertiary = SacredBurgundyLight,
        onTertiary = Color.White,
        background = DarkSanctuaryBg,
        onBackground = TextSacredLight,
        surface = DarkSanctuarySurface,
        onSurface = TextSacredLight,
        surfaceVariant = DarkSanctuaryCard,
        onSurfaceVariant = TextMutedLight,
        outline = BorderDarkSanctuary
    )

    // 5. Marian Azure & Celestial
    val MarianAzureLight = lightColorScheme(
        primary = Color(0xFF0284C7),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFE0F2FE),
        onPrimaryContainer = Color(0xFF075985),
        secondary = Color(0xFF0EA5E9),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFF0F9FF),
        onSecondaryContainer = Color(0xFF0369A1),
        background = Color(0xFFF8FAFC),
        onBackground = Color(0xFF0F172A),
        surface = Color(0xFFEBF5FB),
        onSurface = Color(0xFF0F172A),
        surfaceVariant = Color(0xFFFFFFFF),
        onSurfaceVariant = Color(0xFF475569),
        outline = Color(0xFFBAE6FD)
    )

    val MarianAzureDark = darkColorScheme(
        primary = Color(0xFF38BDF8),
        onPrimary = Color(0xFF082F49),
        primaryContainer = Color(0xFF0369A1),
        onPrimaryContainer = Color(0xFFE0F2FE),
        secondary = Color(0xFF7DD3FC),
        onSecondary = Color.Black,
        background = Color(0xFF0B131C),
        onBackground = Color(0xFFF0F9FF),
        surface = Color(0xFF132030),
        onSurface = Color(0xFFF0F9FF),
        surfaceVariant = Color(0xFF1E3147),
        onSurfaceVariant = Color(0xFF94A3B8),
        outline = Color(0xFF263E5A)
    )

    fun getColorScheme(presetId: String, isDark: Boolean): ColorScheme {
        return when (presetId) {
            "MONASTIC_MINIMALIST" -> if (isDark) MonasticDark else MonasticLight
            "VATICAN_NAVY" -> if (isDark) VaticanNavyDark else VaticanNavyLight
            "SACRED_BURGUNDY" -> if (isDark) SacredBurgundyDarkScheme else SacredBurgundyLightScheme
            "MARIAN_AZURE" -> if (isDark) MarianAzureDark else MarianAzureLight
            else -> if (isDark) LiturgicalGreenDark else LiturgicalGreenLight
        }
    }

    fun buildScaledTypography(isSerif: Boolean, scale: Float): Typography {
        val family = if (isSerif) FontFamily.Serif else FontFamily.Default
        return Typography(
            headlineLarge = TextStyle(
                fontFamily = family,
                fontWeight = FontWeight.Bold,
                fontSize = (30 * scale).sp,
                lineHeight = (36 * scale).sp
            ),
            headlineMedium = TextStyle(
                fontFamily = family,
                fontWeight = FontWeight.SemiBold,
                fontSize = (24 * scale).sp,
                lineHeight = (30 * scale).sp
            ),
            headlineSmall = TextStyle(
                fontFamily = family,
                fontWeight = FontWeight.SemiBold,
                fontSize = (20 * scale).sp,
                lineHeight = (26 * scale).sp
            ),
            titleLarge = TextStyle(
                fontFamily = family,
                fontWeight = FontWeight.Bold,
                fontSize = (19 * scale).sp,
                lineHeight = (25 * scale).sp
            ),
            titleMedium = TextStyle(
                fontFamily = if (isSerif) FontFamily.Serif else FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = (16 * scale).sp,
                lineHeight = (22 * scale).sp
            ),
            titleSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = (14 * scale).sp,
                lineHeight = (20 * scale).sp
            ),
            bodyLarge = TextStyle(
                fontFamily = family,
                fontWeight = FontWeight.Normal,
                fontSize = (16 * scale).sp,
                lineHeight = (26 * scale).sp
            ),
            bodyMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = (14 * scale).sp,
                lineHeight = (20 * scale).sp
            ),
            bodySmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = (12 * scale).sp,
                lineHeight = (16 * scale).sp
            ),
            labelLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = (14 * scale).sp,
                lineHeight = (20 * scale).sp
            ),
            labelMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = (12 * scale).sp,
                lineHeight = (16 * scale).sp
            ),
            labelSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = (10 * scale).sp,
                lineHeight = (14 * scale).sp
            )
        )
    }
}
