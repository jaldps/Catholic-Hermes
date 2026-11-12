package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.ui.theme.LocalAppDesign

/** Minimum interactive size so every tappable surface meets M3's 48dp target. */
private val MinTouchTarget = 48.dp

/** WCAG relative luminance, for picking legible text on the seasonal tint. */
private fun Color.relativeLuminance(): Float {
    fun ch(c: Float): Float {
        val v = c.toDouble()
        return (if (v <= 0.03928) v / 12.92 else Math.pow((v + 0.055) / 1.055, 2.4)).toFloat()
    }
    return 0.2126f * ch(red) + 0.7152f * ch(green) + 0.0722f * ch(blue)
}

private fun Color.contrastAgainst(other: Color): Float {
    val hi = maxOf(relativeLuminance(), other.relativeLuminance())
    val lo = minOf(relativeLuminance(), other.relativeLuminance())
    return (hi + 0.05f) / (lo + 0.05f)
}

/** Walks this colour toward black or white until it reads on [background]. */
private fun Color.readableOn(background: Color, target: Float = 4.5f): Color {
    var candidate = this
    val goLighter = background.relativeLuminance() < 0.5f
    repeat(14) {
        if (contrastAgainst(background) >= target) return candidate
        candidate = if (goLighter) {
            Color(
                red + (1f - red) * 0.11f,
                green + (1f - green) * 0.11f,
                blue + (1f - blue) * 0.11f,
                alpha
            )
        } else {
            Color(red * 0.89f, green * 0.89f, blue * 0.89f, alpha)
        }
    }
    return candidate
}

/**
 * The day's header: season chip, feast title, saint subtitle and language
 * switch. Every colour is derived from the theme (season tint composited
 * over the surface container, text solved against it) so the header follows
 * the light/dark schemes and the active identity instead of fixed liturgical
 * constants. The language pill is a real 48dp button for semantics and touch.
 */
@Composable
fun LiturgicalHeader(
    dailyReading: DailyMassReading,
    currentLanguage: AppLanguage,
    onLanguageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val scheme = MaterialTheme.colorScheme
    val type = MaterialTheme.typography

    // Seasonal tint: the celebration colour at a low alpha over the surface
    // container, with text solved for contrast against the result. The raw
    // hex is only ever a dot — never text — so gold-on-white can never fail.
    val season = Color(dailyReading.liturgicalColorHex)
    val seasonContainer = season.copy(alpha = 0.14f).compositeOver(scheme.surfaceContainer)
    val onSeason = season.readableOn(seasonContainer)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("liturgical_header"),
        color = scheme.surfaceContainerLow,
        shape = design.shapes.cardLarge
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = design.spacing.cardPadding,
                    vertical = design.spacing.cardPadding
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Liturgical season chip (read-only).
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(design.shapes.chip)
                        .background(seasonContainer)
                        .border(1.dp, season.copy(alpha = 0.45f), design.shapes.chip)
                        .padding(
                            horizontal = design.spacing.iconLabelGap,
                            vertical = design.spacing.cardGap / 2
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(design.decor.rowIconSize / 2)
                            .clip(design.shapes.badge)
                            .background(season)
                    )
                    Spacer(modifier = Modifier.width(design.spacing.cardGap / 2))
                    Text(
                        text = dailyReading.liturgicalSeason.get(currentLanguage).uppercase(),
                        style = type.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = onSeason
                    )
                }

                // Language switch: a 48dp minimum hit area with button role.
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .testTag("language_selector_button")
                        .clip(design.shapes.chip)
                        .background(scheme.primaryContainer)
                        .clickable(role = Role.Button, onClick = onLanguageClick)
                        .heightIn(min = MinTouchTarget)
                        .padding(horizontal = design.spacing.iconLabelGap, vertical = 6.dp)
                ) {
                    Text(
                        text = currentLanguage.flag,
                        style = type.titleSmall
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = currentLanguage.name,
                        style = type.labelLarge,
                        color = scheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "Change Language",
                        modifier = Modifier.size(18.dp),
                        tint = scheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(design.spacing.cardGap))

            // Feast / Daily Title
            Text(
                text = dailyReading.feastTitle.get(currentLanguage),
                style = type.headlineSmall,
                color = scheme.primary
            )

            Spacer(modifier = Modifier.height(design.spacing.cardGap / 2))

            // Saint of the day subtitle
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(design.decor.rowIconSize * 0.7f),
                    tint = palette.gold.readableOn(scheme.surfaceContainerLow)
                )
                Spacer(modifier = Modifier.width(design.spacing.cardGap / 2))
                Text(
                    text = dailyReading.saintOfFeast.get(currentLanguage),
                    style = type.bodySmall,
                    color = palette.textSecondary
                )
            }
        }
    }
}
