package com.example.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp
)

val LocalSpacing = staticCompositionLocalOf {
    Spacing(xs = 4.dp, sm = 8.dp, md = 12.dp, lg = 16.dp, xl = 24.dp, xxl = 32.dp)
}

val androidx.compose.material3.MaterialTheme.spacing: Spacing
    @Composable get() = LocalSpacing.current

fun buildSpacing(themePreset: String): Spacing {
    return when (themePreset) {
        "APPLE_CLEAN" -> Spacing(xs = 4.dp, sm = 8.dp, md = 12.dp, lg = 16.dp, xl = 20.dp, xxl = 28.dp)
        "MATERIAL_CLEAN" -> Spacing(xs = 4.dp, sm = 8.dp, md = 12.dp, lg = 16.dp, xl = 24.dp, xxl = 32.dp)
        else -> Spacing(xs = 4.dp, sm = 8.dp, md = 12.dp, lg = 16.dp, xl = 24.dp, xxl = 32.dp)
    }
}
