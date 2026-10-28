package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember

@Composable
fun SanctaFidesTheme(
    themePreset: String = AppThemePreset.LITURGICAL.id,
    darkModeSetting: String = "SYSTEM",
    isSerif: Boolean = true,
    fontScale: Float = 1.0f,
    liturgicalSeason: String = "Ordinary Time",
    content: @Composable () -> Unit
) {
    val isDark = when (darkModeSetting) {
        "DARK" -> true
        "LIGHT" -> false
        else -> isSystemInDarkTheme()
    }

    val colorScheme = ThemePalettes.getColorScheme(themePreset, isDark, liturgicalSeason)
    val typography = ThemePalettes.buildScaledTypography(themePreset, isSerif, fontScale)
    val shapes = remember(themePreset) {
        when (themePreset) {
            AppThemePreset.APPLE.id -> Shapes(
                extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                small = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),
                medium = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
                large = androidx.compose.foundation.shape.RoundedCornerShape(22.dp),
                extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp)
            )
            AppThemePreset.MATERIAL.id -> Shapes(
                extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
                small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp)
            )
            else -> Shapes(
                extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(2.dp),
                small = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
                medium = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                large = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
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
