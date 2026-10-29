package com.example.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Per-preset visual-language tokens that are neither color, type, shape nor spacing:
 * icon metrics, bar transparency, elevation depth, motion timing and title tracking.
 *
 * Provided through [LocalIdentity] so a preset switch changes how the app *feels*
 * (HIG frosted translucency vs Material tonal layering vs solemn serif tracking),
 * not only which colors are used.
 */
data class ThemeIdentity(
    val navIconSize: Dp,
    val actionIconSize: Dp,
    val unselectedIconAlpha: Float,
    val navContainerAlpha: Float,
    val topBarAlpha: Float,
    val topBarElevation: Dp,
    val navElevation: Dp,
    val navTonalElevation: Dp,
    val titleTracking: TextUnit,
    val motionMs: Int,
    val shellEdgePadding: Dp
)

val LocalIdentity = staticCompositionLocalOf {
    ThemeIdentity(
        navIconSize = 22.dp,
        actionIconSize = 22.dp,
        unselectedIconAlpha = 0.60f,
        navContainerAlpha = 1f,
        topBarAlpha = 1f,
        topBarElevation = 0.dp,
        navElevation = 8.dp,
        navTonalElevation = 0.dp,
        titleTracking = 2.sp,
        motionMs = 260,
        shellEdgePadding = 8.dp
    )
}

val androidx.compose.material3.MaterialTheme.identity: ThemeIdentity
    @Composable get() = LocalIdentity.current

fun buildIdentity(themePreset: String): ThemeIdentity {
    return when (themePreset) {
        // HIG: translucent frosted bars, flat (no shadow), tight tracking, snappy motion.
        "APPLE_CLEAN" -> ThemeIdentity(
            navIconSize = 24.dp,
            actionIconSize = 20.dp,
            unselectedIconAlpha = 0.45f,
            navContainerAlpha = 0.82f,
            topBarAlpha = 0.82f,
            topBarElevation = 0.dp,
            navElevation = 0.dp,
            navTonalElevation = 0.dp,
            titleTracking = 0.4.sp,
            motionMs = 180,
            shellEdgePadding = 4.dp
        )
        // Material 3: opaque bars layered by tonal elevation, no drop shadow, medium tracking.
        "MATERIAL_CLEAN" -> ThemeIdentity(
            navIconSize = 24.dp,
            actionIconSize = 24.dp,
            unselectedIconAlpha = 0.70f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 0.dp,
            navElevation = 0.dp,
            navTonalElevation = 3.dp,
            titleTracking = 0.6.sp,
            motionMs = 300,
            shellEdgePadding = 0.dp
        )
        // Sober slate: small icons, hairline flatness, no motion flourish.
        "MONASTIC_MINIMALIST" -> ThemeIdentity(
            navIconSize = 20.dp,
            actionIconSize = 20.dp,
            unselectedIconAlpha = 0.50f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 0.dp,
            navElevation = 0.dp,
            navTonalElevation = 0.dp,
            titleTracking = 3.sp,
            motionMs = 200,
            shellEdgePadding = 4.dp
        )
        // Pontifical: stately bar depth, generous tracking, unhurried motion.
        "VATICAN_NAVY" -> ThemeIdentity(
            navIconSize = 22.dp,
            actionIconSize = 22.dp,
            unselectedIconAlpha = 0.60f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 2.dp,
            navElevation = 6.dp,
            navTonalElevation = 0.dp,
            titleTracking = 2.sp,
            motionMs = 280,
            shellEdgePadding = 8.dp
        )
        // Imperial: deepest bar shadows, widest tracking, most solemn pacing.
        "SACRED_BURGUNDY" -> ThemeIdentity(
            navIconSize = 22.dp,
            actionIconSize = 22.dp,
            unselectedIconAlpha = 0.55f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 3.dp,
            navElevation = 10.dp,
            navTonalElevation = 0.dp,
            titleTracking = 3.sp,
            motionMs = 320,
            shellEdgePadding = 10.dp
        )
        // Our Lady: airy celestial bars with tighter tracking.
        "MARIAN_AZURE" -> ThemeIdentity(
            navIconSize = 22.dp,
            actionIconSize = 22.dp,
            unselectedIconAlpha = 0.62f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 1.dp,
            navElevation = 6.dp,
            navTonalElevation = 0.dp,
            titleTracking = 1.6.sp,
            motionMs = 260,
            shellEdgePadding = 8.dp
        )
        // Minimal liturgical: light chrome, restrained tracking, quick transitions.
        "LITURGICAL_CLEAN" -> ThemeIdentity(
            navIconSize = 22.dp,
            actionIconSize = 22.dp,
            unselectedIconAlpha = 0.58f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 0.dp,
            navElevation = 2.dp,
            navTonalElevation = 0.dp,
            titleTracking = 1.2.sp,
            motionMs = 220,
            shellEdgePadding = 6.dp
        )
        // Liturgical Dynamic (default green): processional depth and pacing.
        else -> ThemeIdentity(
            navIconSize = 22.dp,
            actionIconSize = 22.dp,
            unselectedIconAlpha = 0.58f,
            navContainerAlpha = 1f,
            topBarAlpha = 1f,
            topBarElevation = 1.dp,
            navElevation = 8.dp,
            navTonalElevation = 0.dp,
            titleTracking = 2.sp,
            motionMs = 280,
            shellEdgePadding = 8.dp
        )
    }
}
