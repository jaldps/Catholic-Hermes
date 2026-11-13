package com.example

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.compositeOver
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.DesignTokens
import org.junit.Assert.assertTrue
import org.junit.Test

class DesignContrastTest {
    @Test fun liturgicalCaptionsAndRubricsRemainReadableOnEveryPaperSurface() {
        for (identity in DesignLanguage.selectable) {
         for (dark in listOf(false, true)) {
            val palette = DesignTokens.of(identity, dark).palette
            val endpoints = listOf(palette.canvasTop, palette.canvasBottom)
            val glassComposites = listOf(palette.glass, palette.glassStrong).flatMap { glass ->
                endpoints.flatMap { ground -> listOf(glass.compositeOver(ground), glass.copy(alpha = glass.alpha * 0.96f).compositeOver(ground)) }
            }
            for (surface in listOf(palette.canvas, palette.groupedSurface, palette.raised) + endpoints + glassComposites) {
                for (ink in listOf(palette.textPrimary, palette.textSecondary, palette.textTertiary, palette.accent)) {
                    val ratio = contrast(ink, surface)
                    assertTrue("Small liturgical text must reach 4.5:1 (${identity.id}, dark=$dark, ratio=$ratio)", ratio >= 4.5f)
                }
            }
            assertTrue("Primary action text must reach4.5:1 (${identity.id}, dark=$dark)",
                contrast(palette.action, palette.onAction) >= 4.5f)
        }
         }
    }

    private fun contrast(a: Color, b: Color): Float {
        val first = a.luminance()
        val second = b.luminance()
        return (maxOf(first, second) + 0.05f) / (minOf(first, second) + 0.05f)
    }
}
