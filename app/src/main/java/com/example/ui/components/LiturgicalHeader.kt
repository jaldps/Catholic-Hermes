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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.NotificationsActive
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppThemePreset

@Composable
fun LiturgicalHeader(
    dailyReading: DailyMassReading,
    currentLanguage: AppLanguage,
    onLanguageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val seasonColor = Color(dailyReading.liturgicalColorHex)
    val activeIdentity = LocalAppThemePreset.current
    val isApple = activeIdentity == AppThemePreset.APPLE.id
    val isMaterial = activeIdentity == AppThemePreset.MATERIAL.id
    val isVatican = activeIdentity == AppThemePreset.VATICAN.id
    val headerShape = when (activeIdentity) {
        AppThemePreset.APPLE.id -> RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
        AppThemePreset.MATERIAL.id -> RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
        else -> RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("liturgical_header"),
        color = if (isApple) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface,
        tonalElevation = if (isMaterial) 3.dp else 0.dp,
        shape = headerShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (isApple) 20.dp else if (isMaterial) 16.dp else 24.dp, vertical = if (isApple) 18.dp else 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Liturgical season badge
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(when (activeIdentity) {
                            AppThemePreset.APPLE.id -> MaterialTheme.shapes.large
                            AppThemePreset.MATERIAL.id -> MaterialTheme.shapes.medium
                            else -> MaterialTheme.shapes.extraSmall
                        })
                        .background(
                            when {
                                isApple -> MaterialTheme.colorScheme.surface
                                isMaterial -> MaterialTheme.colorScheme.secondaryContainer
                                else -> seasonColor.copy(alpha = 0.15f)
                            }
                        )
                        .then(
                            if (isVatican) Modifier.border(
                                1.dp,
                                seasonColor.copy(alpha = 0.4f),
                                MaterialTheme.shapes.extraSmall
                            ) else Modifier
                        )
                        .padding(horizontal = if (isApple) 12.dp else 10.dp, vertical = 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(seasonColor)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = dailyReading.liturgicalSeason.get(currentLanguage).uppercase(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isMaterial) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurface,
                        letterSpacing = 0.5.sp
                    )
                }

                // Language Selector Pill
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .testTag("language_selector_button")
                         .clip(when (activeIdentity) {
                            AppThemePreset.APPLE.id -> MaterialTheme.shapes.large
                            AppThemePreset.MATERIAL.id -> MaterialTheme.shapes.medium
                            else -> MaterialTheme.shapes.extraSmall
                        })
                        .background(
                            if (isApple) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primaryContainer
                        )
                        .clickable(onClick = onLanguageClick)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = currentLanguage.flag,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = currentLanguage.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "Change Language",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Feast / Daily Title
            Text(
                text = dailyReading.feastTitle.get(currentLanguage),
                style = if (isApple) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = if (isApple) FontWeight.SemiBold else FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Saint of the day subtitle
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = dailyReading.saintOfFeast.get(currentLanguage),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
