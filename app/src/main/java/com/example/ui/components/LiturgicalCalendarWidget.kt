package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.LiturgicalCalendarDay
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppThemePreset

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LiturgicalCalendarWidget(
    liturgicalDay: LiturgicalCalendarDay,
    currentLanguage: AppLanguage,
    onOpenLanguageSelector: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val activeIdentity = LocalAppThemePreset.current
    val isApple = activeIdentity == AppThemePreset.APPLE.id
    val isMaterial = activeIdentity == AppThemePreset.MATERIAL.id
    val isVatican = activeIdentity == AppThemePreset.VATICAN.id
    val liturgicalColor = Color(liturgicalDay.liturgicalColorHex)
    val calendarShape = when (activeIdentity) {
        AppThemePreset.APPLE.id -> MaterialTheme.shapes.large
        AppThemePreset.MATERIAL.id -> MaterialTheme.shapes.extraLarge
        else -> MaterialTheme.shapes.small
    }

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

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = if (isApple) 20.dp else if (isMaterial) 16.dp else 24.dp,
                vertical = if (isApple) 4.dp else 8.dp
            )
            .then(
                if (isVatican) Modifier.border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.78f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.28f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.78f)
                        )
                    ),
                    shape = calendarShape
                ) else Modifier
            )
            .testTag("liturgical_calendar_widget"),
        shape = calendarShape,
        colors = CardDefaults.cardColors(
            containerColor = when {
                isApple -> MaterialTheme.colorScheme.surfaceVariant
                isMaterial -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                else -> MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isMaterial) 3.dp else 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isApple) 18.dp else 16.dp)
        ) {
            // Top Bar: Date, Language Selector & Minimal Gold Cross Motif
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${liturgicalDay.dayOfMonth} de ${liturgicalDay.monthName}".uppercase(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Language flag / switch button
                    Surface(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .clickable(onClick = onOpenLanguageSelector)
                            .testTag("widget_lang_button"),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = currentLanguage.flag, fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = currentLanguage.code.uppercase(),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Celebration Title (Big Serif Header)
            Text(
                text = liturgicalDay.celebrationTitle.get(currentLanguage),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.primary,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Season and Liturgical Rank
            Text(
                text = "${liturgicalDay.seasonName.get(currentLanguage)} • ${liturgicalDay.rank.get(currentLanguage)}",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Lectionary Cycles & Liturgical Color Badges (Row of Pills)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Color Badge
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = liturgicalColor.copy(alpha = 0.12f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, liturgicalColor.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .clip(CircleShape)
                                .background(liturgicalColor)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = colorLabel,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = liturgicalColor
                        )
                    }
                }

                // Sunday Cycle (A / B / C)
                liturgicalDay.sundayCycle?.let { cycle ->
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f))
                    ) {
                        Text(
                            text = cycle.get(currentLanguage),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Weekday Cycle (I / II)
                liturgicalDay.weekdayCycle?.let { cycle ->
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = cycle.get(currentLanguage),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Fasting / Abstinence or Festive Obligation Indicator
                liturgicalDay.fastingRule?.let { rule ->
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = if (liturgicalDay.isFastOrAbstinence) {
                            MaterialTheme.colorScheme.secondaryContainer
                        } else {
                            MaterialTheme.colorScheme.tertiaryContainer
                        },
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (liturgicalDay.isFastOrAbstinence) MaterialTheme.colorScheme.secondary.copy(alpha = 0.55f)
                            else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.55f)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (liturgicalDay.isFastOrAbstinence) Icons.Default.Restaurant else Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.size(11.dp),
                                tint = if (liturgicalDay.isFastOrAbstinence) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = rule.get(currentLanguage),
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = if (liturgicalDay.isFastOrAbstinence) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onTertiaryContainer,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Gospel Verse Citation and Expandable Reflection Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .clickable { isExpanded = !isExpanded }
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoStories,
                        contentDescription = "Evangelio",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = liturgicalDay.gospelVerseRef,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isExpanded) {
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
                        },
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(16.dp)
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
                        .padding(top = 10.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "MEDITACIÓN LITÚRGICA DEL DÍA"
                            AppLanguage.EN -> "LITURGICAL MEDITATION OF THE DAY"
                            AppLanguage.PT -> "MEDITAÇÃO LITÚRGICA DO DIA"
                        },
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = liturgicalDay.missalMeditation?.get(currentLanguage)
                            ?: liturgicalDay.celebrationTitle.get(currentLanguage),
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            }
        }
    }
}
