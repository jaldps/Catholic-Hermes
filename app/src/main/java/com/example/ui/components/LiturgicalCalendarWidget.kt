package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.LiturgicalCalendarDay
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** M3 minimum touch target: the meditation toggle and language chip meet it. */
private val MinTouchTarget = 48.dp

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
 * The liturgical day as one tonal card: date eyebrow, celebration title,
 * season/rank line, lectionary and fasting badges, then the Gospel citation
 * with its expandable meditation. Colours come only from the theme — the
 * seasonal hex tints the surface container and is never used as text — so
 * the widget follows the active identity and the light/dark scheme. The two
 * touch surfaces (language chip, meditation toggle) are at least 48dp tall.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LiturgicalCalendarWidget(
    liturgicalDay: LiturgicalCalendarDay,
    currentLanguage: AppLanguage,
    onOpenLanguageSelector: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val scheme = MaterialTheme.colorScheme
    val type = MaterialTheme.typography

    // The seasonal colour only ever tints a container and fills a dot;
    // lettering on it is solved for contrast instead of painted raw.
    val liturgicalColor = Color(liturgicalDay.liturgicalColorHex)
    val colorContainer = liturgicalColor.copy(alpha = 0.14f).compositeOver(scheme.surfaceContainer)
    val onColorContainer = liturgicalColor.readableOn(colorContainer)

    // Liturgical color name translation
    val colorLabel = when (liturgicalDay.liturgicalColorHex) {
        0xFF2C6B3E -> when (currentLanguage) {
            AppLanguage.ES -> "Verde"
            AppLanguage.EN -> "Green"
            AppLanguage.PT -> "Verde"
        }
        0xFFD4AF37 -> when (currentLanguage) {
            AppLanguage.ES -> "Blanco / Oro"
            AppLanguage.EN -> "White / Gold"
            AppLanguage.PT -> "Branco / Dourado"
        }
        0xFF5E2777 -> when (currentLanguage) {
            AppLanguage.ES -> "Morado"
            AppLanguage.EN -> "Violet"
            AppLanguage.PT -> "Roxo"
        }
        0xFFB71C1C -> when (currentLanguage) {
            AppLanguage.ES -> "Rojo"
            AppLanguage.EN -> "Red"
            AppLanguage.PT -> "Vermelho"
        }
        else -> when (currentLanguage) {
            AppLanguage.ES -> "Color Litúrgico"
            AppLanguage.EN -> "Liturgical Color"
            AppLanguage.PT -> "Cor Litúrgica"
        }
    }

    val meditationLabel = when (currentLanguage) {
        AppLanguage.ES -> "MEDITACIÓN LITÚRGICA DEL DÍA"
        AppLanguage.EN -> "LITURGICAL MEDITATION OF THE DAY"
        AppLanguage.PT -> "MEDITAÇÃO LITÚRGICA DO DIA"
    }
    val toggleLabel = if (isExpanded) {
        when (currentLanguage) {
            AppLanguage.ES -> "Ocultar"
            AppLanguage.EN -> "Hide"
            AppLanguage.PT -> "Ocultar"
        }
    } else {
        when (currentLanguage) {
            AppLanguage.ES -> "Meditación"
            AppLanguage.EN -> "Meditation"
            AppLanguage.PT -> "Meditação"
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = design.spacing.gutter,
                vertical = design.spacing.cardGap / 2
            )
            .testTag("liturgical_calendar_widget"),
        shape = design.shapes.cardLarge,
        colors = CardDefaults.cardColors(
            containerColor = scheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(design.spacing.cardPadding)
        ) {
            // Top Bar: Date + Language Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(design.decor.rowIconSize / 2)
                            .clip(design.shapes.badge)
                            .background(palette.gold.readableOn(scheme.surfaceContainerLow))
                    )
                    Spacer(modifier = Modifier.width(design.spacing.cardGap / 2))
                    Text(
                        text = "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName}".uppercase(),
                        style = styles.sectionEyebrow,
                        color = palette.textSecondary
                    )
                }

                // Language switch: 48dp hit area on a tonal chip.
                Surface(
                    modifier = Modifier
                        .clip(design.shapes.chip)
                        .clickable(role = Role.Button, onClick = onOpenLanguageSelector)
                        .heightIn(min = MinTouchTarget)
                        .testTag("widget_lang_button"),
                    color = scheme.surfaceContainer,
                    shape = design.shapes.chip
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = design.spacing.iconLabelGap,
                            vertical = design.spacing.cardGap / 2
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = currentLanguage.flag, style = type.titleSmall)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = currentLanguage.code.uppercase(),
                            style = type.labelLarge,
                            color = scheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(design.spacing.cardGap))

            // Celebration Title, set in the identity's display face.
            Text(
                text = liturgicalDay.celebrationTitle.get(currentLanguage),
                style = styles.sectionTitle,
                color = palette.textPrimary
            )

            Spacer(modifier = Modifier.height(design.spacing.cardGap / 2))

            // Season and Liturgical Rank
            Text(
                text = "${liturgicalDay.seasonName.get(currentLanguage)} • ${liturgicalDay.rank.get(currentLanguage)}",
                style = type.bodyMedium,
                color = palette.textSecondary
            )

            Spacer(modifier = Modifier.height(design.spacing.cardGap))

            // Lectionary Cycles & Liturgical Color Badges
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap),
                verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
            ) {
                // Seasonal colour badge.
                Surface(
                    shape = design.shapes.chip,
                    color = colorContainer,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        liturgicalColor.copy(alpha = 0.45f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = design.spacing.cardGap,
                            vertical = design.spacing.cardGap / 2
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(design.decor.rowIconSize / 2)
                                .clip(design.shapes.badge)
                                .background(liturgicalColor)
                        )
                        Spacer(modifier = Modifier.width(design.spacing.cardGap / 2))
                        Text(
                            text = colorLabel,
                            style = type.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = onColorContainer
                        )
                    }
                }

                // Sunday Cycle (A / B / C)
                liturgicalDay.sundayCycle?.let { cycle ->
                    Surface(
                        shape = design.shapes.chip,
                        color = scheme.secondaryContainer,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            scheme.outline
                        )
                    ) {
                        Text(
                            text = cycle.get(currentLanguage),
                            style = type.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = scheme.onSecondaryContainer,
                            modifier = Modifier.padding(
                                horizontal = design.spacing.cardGap,
                                vertical = design.spacing.cardGap / 2
                            )
                        )
                    }
                }

                // Weekday Cycle (I / II)
                liturgicalDay.weekdayCycle?.let { cycle ->
                    Surface(
                        shape = design.shapes.chip,
                        color = scheme.tertiaryContainer,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            scheme.outline
                        )
                    ) {
                        Text(
                            text = cycle.get(currentLanguage),
                            style = type.labelSmall,
                            color = scheme.onTertiaryContainer,
                            modifier = Modifier.padding(
                                horizontal = design.spacing.cardGap,
                                vertical = design.spacing.cardGap / 2
                            )
                        )
                    }
                }

                // Fasting / Abstinence or Festive Obligation Indicator
                liturgicalDay.fastingRule?.let { rule ->
                    val fastContainer = if (liturgicalDay.isFastOrAbstinence) {
                        scheme.surfaceContainer
                    } else {
                        scheme.primaryContainer
                    }
                    val fastContent = if (liturgicalDay.isFastOrAbstinence) {
                        scheme.onSurfaceVariant
                    } else {
                        scheme.onPrimaryContainer
                    }
                    Surface(
                        shape = design.shapes.chip,
                        color = fastContainer,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            fastContent.copy(alpha = 0.35f)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = design.spacing.cardGap,
                                vertical = design.spacing.cardGap / 2
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (liturgicalDay.isFastOrAbstinence) Icons.Default.Restaurant else Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.size(design.decor.rowIconSize * 0.6f),
                                tint = fastContent
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = rule.get(currentLanguage),
                                style = type.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = fastContent
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(design.spacing.cardGap))

            // Gospel Verse Citation and Expandable Reflection Button (48dp).
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(design.shapes.card)
                    .clickable(role = Role.Button) { isExpanded = !isExpanded }
                    .background(scheme.surfaceContainer)
                    .heightIn(min = MinTouchTarget)
                    .padding(
                        horizontal = design.spacing.iconLabelGap,
                        vertical = design.spacing.cardGap / 2
                    )
                    .semantics {
                        stateDescription = if (isExpanded) {
                            when (currentLanguage) {
                                AppLanguage.ES -> "Expandida"
                                AppLanguage.EN -> "Expanded"
                                AppLanguage.PT -> "Expandida"
                            }
                        } else {
                            when (currentLanguage) {
                                AppLanguage.ES -> "Contraída"
                                AppLanguage.EN -> "Collapsed"
                                AppLanguage.PT -> "Recolhida"
                            }
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoStories,
                        contentDescription = null,
                        tint = palette.gold.readableOn(scheme.surfaceContainer),
                        modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
                    )
                    Spacer(modifier = Modifier.width(design.spacing.cardGap))
                    Text(
                        text = liturgicalDay.gospelVerseRef,
                        style = type.titleSmall,
                        color = scheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = toggleLabel,
                        style = type.labelLarge,
                        color = scheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = scheme.primary,
                        modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
                    )
                }
            }

            // Expandable Missal Reflection & Patristic Commentary
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = design.spacing.cardGap)
                        .background(
                            color = scheme.surfaceContainerHigh,
                            shape = design.shapes.card
                        )
                        .padding(design.spacing.cardPadding)
                ) {
                    Text(
                        text = meditationLabel,
                        style = styles.sectionEyebrow,
                        color = palette.textSecondary
                    )
                    Spacer(modifier = Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = liturgicalDay.missalMeditation?.get(currentLanguage)
                            ?: liturgicalDay.celebrationTitle.get(currentLanguage),
                        style = styles.scripture,
                        color = palette.textPrimary
                    )
                }
            }
        }
    }
}
