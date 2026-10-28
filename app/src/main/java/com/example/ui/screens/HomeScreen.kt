package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.AppLanguage
import com.example.data.models.DailyMassReading
import com.example.data.models.SaintOfTheDay
import com.example.data.models.VerseOfTheDay
import com.example.data.models.LiturgicalCalendarDay
import com.example.data.repository.PilgrimageAndHabitsData
import com.example.ui.components.LiturgicalCalendarWidget
import com.example.ui.components.LiturgicalHeader
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppThemePreset
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.LocationOn

@Composable
fun HomeScreen(
    dailyReading: DailyMassReading,
    verseOfTheDay: VerseOfTheDay,
    saintOfTheDay: SaintOfTheDay,
    currentLanguage: AppLanguage,
    onOpenLanguageSelector: () -> Unit,
    onNavigateToBible: () -> Unit,
    onNavigateToRosary: () -> Unit,
    onNavigateToPrayers: () -> Unit,
    onNavigateToDoctrine: () -> Unit,
    onNavigateToSpiritualLife: () -> Unit,
    onNavigateToQuiz: () -> Unit = onNavigateToSpiritualLife,
    onNavigateToCatechism: () -> Unit = {},
    onNavigateToPilgrimage: () -> Unit = {},
    onNavigateToHabits: () -> Unit = {},
    liturgicalDay: LiturgicalCalendarDay = PilgrimageAndHabitsData.upcomingLiturgicalDays.first(),
    modifier: Modifier = Modifier
) {
    val activeIdentity = LocalAppThemePreset.current
    val isApple = activeIdentity == AppThemePreset.APPLE.id
    val isMaterial = activeIdentity == AppThemePreset.MATERIAL.id
    val screenGutter = if (isApple) 20.dp else if (isMaterial) 16.dp else 24.dp

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        verticalArrangement = Arrangement.spacedBy(if (isApple) 22.dp else if (isMaterial) 14.dp else 18.dp)
    ) {
        // Apple leads with a reading-first hero while keeping liturgical context close.
        if (isApple) {
            item {
                AppleReadingHero(
                    dailyReading = dailyReading,
                    currentLanguage = currentLanguage,
                    onNavigateToBible = onNavigateToBible,
                    horizontalGutter = screenGutter
                )
            }
        }
        item {
            LiturgicalCalendarWidget(
                liturgicalDay = liturgicalDay,
                currentLanguage = currentLanguage,
                onOpenLanguageSelector = onOpenLanguageSelector
            )
        }

        // Habit & Virtue Tracker Quick Summary Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenGutter)
                    .clickable(onClick = onNavigateToHabits)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                        MaterialTheme.shapes.large
                    )
                    .testTag("home_habit_tracker_card"),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isMaterial) 3.dp else 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "HÁBITOS Y VIRTUDES (REGLA DE VIDA)"
                                    AppLanguage.EN -> "HABITS & VIRTUES (RULE OF LIFE)"
                                    AppLanguage.PT -> "HÁBITOS E VIRTUDES (REGRA DE VIDA)"
                                },
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Sigue tus devociones diarias, ayuno y el Antídoto contra la tentación."
                                AppLanguage.EN -> "Track your daily devotions, fasting, and Grace Antidote against sins."
                                AppLanguage.PT -> "Acompanhe suas práticas diárias, jejum e o Antídoto contra o pecado."
                            },
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clip(MaterialTheme.shapes.small)
                    ) {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Ver Panel"
                                AppLanguage.EN -> "Dashboard"
                                AppLanguage.PT -> "Painel"
                            },
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        // Quick Daily Sanctification Routine Hub
        item {
            Column(modifier = Modifier.padding(horizontal = screenGutter)) {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "RUTINA ESPIRITUAL DIARIA"
                        AppLanguage.EN -> "DAILY SPIRITUAL ROUTINE"
                        AppLanguage.PT -> "ROTINA ESPIRITUAL DIÁRIA"
                    },
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column(verticalArrangement = Arrangement.spacedBy(if (isApple) 12.dp else if (isMaterial) 8.dp else 10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(if (isApple) 14.dp else if (isMaterial) 10.dp else 12.dp)
                    ) {
                        RoutineQuickCard(
                            title = when (currentLanguage) {
                                AppLanguage.ES -> "Laudes"
                                AppLanguage.EN -> "Morning"
                                AppLanguage.PT -> "Laudes"
                            },
                            subtitle = "6:00 AM",
                            icon = Icons.Default.LightMode,
                            modifier = Modifier.weight(1f),
                            onClick = onNavigateToPrayers
                        )
                        RoutineQuickCard(
                            title = when (currentLanguage) {
                                AppLanguage.ES -> "El Ángelus"
                                AppLanguage.EN -> "The Angelus"
                                AppLanguage.PT -> "O Angelus"
                            },
                            subtitle = "12:00 PM",
                            icon = Icons.Default.WbTwilight,
                            modifier = Modifier.weight(1f),
                            onClick = onNavigateToPrayers
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(if (isApple) 14.dp else if (isMaterial) 10.dp else 12.dp)
                    ) {
                        RoutineQuickCard(
                            title = when (currentLanguage) {
                                AppLanguage.ES -> "Santo Rosario"
                                AppLanguage.EN -> "Holy Rosary"
                                AppLanguage.PT -> "Santo Terço"
                            },
                            subtitle = when (currentLanguage) {
                                AppLanguage.ES -> "Hoy"
                                AppLanguage.EN -> "Today"
                                AppLanguage.PT -> "Hoje"
                            },
                            icon = Icons.Default.SelfImprovement,
                            modifier = Modifier.weight(1f),
                            onClick = onNavigateToRosary
                        )
                        RoutineQuickCard(
                            title = when (currentLanguage) {
                                AppLanguage.ES -> "Completas"
                                AppLanguage.EN -> "Night Examen"
                                AppLanguage.PT -> "Completas"
                            },
                            subtitle = "9:00 PM",
                            icon = Icons.Default.NightsStay,
                            modifier = Modifier.weight(1f),
                            onClick = onNavigateToPrayers
                        )
                    }
                }
            }
        }

        if (!isApple) {
        // Daily Mass Reading Featured Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("daily_mass_card"),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isMaterial) 3.dp else 0.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoStories,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Lecturas de la Misa de Hoy"
                                    AppLanguage.EN -> "Today's Mass Readings"
                                    AppLanguage.PT -> "Leituras da Santa Missa"
                                },
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Text(
                            text = dailyReading.gospelRef,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "«${dailyReading.gospelText.get(currentLanguage).take(190)}...»",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    FilledTonalButton(
                        onClick = onNavigateToBible,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("read_mass_full_button"),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Icon(imageVector = Icons.Default.AutoStories, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Leer Misa Completa y Comentarios"
                                AppLanguage.EN -> "Read Full Mass & Commentaries"
                                AppLanguage.PT -> "Ler Missa Completa e Reflexões"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        }

        // Verse of the Day Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("verse_of_the_day_card"),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.FormatQuote,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Versículo del Día"
                                    AppLanguage.EN -> "Verse of the Day"
                                    AppLanguage.PT -> "Versículo do Dia"
                                },
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        Text(
                            text = verseOfTheDay.reference,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = verseOfTheDay.text.get(currentLanguage),
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 25.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Spiritual challenge
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "💡 " + verseOfTheDay.spiritualChallenge.get(currentLanguage),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Saint of the Day Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenGutter)
                    .testTag("saint_of_the_day_card"),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isMaterial) 3.dp else 0.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Santo del Día"
                                AppLanguage.EN -> "Saint of the Day"
                                AppLanguage.PT -> "Santo do Dia"
                            },
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            letterSpacing = 1.sp
                        )

                        Text(
                            text = saintOfTheDay.feastDate,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = saintOfTheDay.name.get(currentLanguage),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = saintOfTheDay.title.get(currentLanguage),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = saintOfTheDay.biography.get(currentLanguage).take(220) + "...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Saint Quote
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.surface)
                            .border(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f), MaterialTheme.shapes.small)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = saintOfTheDay.iconicQuote.get(currentLanguage),
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Featured Direct GPS Nearby Churches Finder Banner
        item {
            val context = LocalContext.current
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("home_nearby_churches_banner"),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                ),
                border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.NearMe,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Buscar Iglesias Cerca de Mí"
                                    AppLanguage.EN -> "Find Churches Near Me"
                                    AppLanguage.PT -> "Buscar Igrejas Perto de Mim"
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "Ubicación en Google Maps y horarios de misa"
                                    AppLanguage.EN -> "Google Maps nearby & mass times"
                                    AppLanguage.PT -> "Localização no Google Maps e horários"
                                },
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    FilledTonalButton(
                        onClick = {
                            val mapUri = Uri.parse("geo:0,0?q=parroquia+catolica")
                            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
                            context.startActivity(mapIntent)
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Buscar"
                                AppLanguage.EN -> "Locate"
                                AppLanguage.PT -> "Localizar"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // Quick Gateway to Church Finder & Trivia
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionBanner(
                    title = when (currentLanguage) {
                        AppLanguage.ES -> "Directorio Iglesias"
                        AppLanguage.EN -> "Church Directory"
                        AppLanguage.PT -> "Diretório Igrejas"
                    },
                    subtitle = when (currentLanguage) {
                        AppLanguage.ES -> "Por país y ciudad"
                        AppLanguage.EN -> "By Country & City"
                        AppLanguage.PT -> "Por país e cidade"
                    },
                    icon = Icons.Default.Church,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToDoctrine
                )

                QuickActionBanner(
                    title = when (currentLanguage) {
                        AppLanguage.ES -> "Trivia Católica"
                        AppLanguage.EN -> "Catholic Trivia"
                        AppLanguage.PT -> "Quiz Católico"
                    },
                    subtitle = when (currentLanguage) {
                        AppLanguage.ES -> "Aprende jugando"
                        AppLanguage.EN -> "Test Your Faith"
                        AppLanguage.PT -> "Aprenda a Fé"
                    },
                    icon = Icons.Default.Star,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToQuiz
                )
            }
        }

        // Quick Gateway to Catechism & Pilgrimages
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionBanner(
                    title = when (currentLanguage) {
                        AppLanguage.ES -> "Catecismo (CCC)"
                        AppLanguage.EN -> "Catechism (CCC)"
                        AppLanguage.PT -> "Catecismo (CIC)"
                    },
                    subtitle = when (currentLanguage) {
                        AppLanguage.ES -> "4 Pilares de la Fe"
                        AppLanguage.EN -> "4 Pillars of Faith"
                        AppLanguage.PT -> "4 Pilares da Fé"
                    },
                    icon = Icons.Default.MenuBook,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToCatechism
                )

                QuickActionBanner(
                    title = when (currentLanguage) {
                        AppLanguage.ES -> "Santuarios y Fe"
                        AppLanguage.EN -> "Shrines & Pilgrims"
                        AppLanguage.PT -> "Santuários e Fé"
                    },
                    subtitle = when (currentLanguage) {
                        AppLanguage.ES -> "Indulgencias y Rutas"
                        AppLanguage.EN -> "Indulgences & Maps"
                        AppLanguage.PT -> "Indulgências e Rotas"
                    },
                    icon = Icons.Default.Place,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToPilgrimage
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun AppleReadingHero(
    dailyReading: DailyMassReading,
    currentLanguage: AppLanguage,
    onNavigateToBible: () -> Unit,
    horizontalGutter: androidx.compose.ui.unit.Dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalGutter)
            .padding(top = 8.dp)
    ) {
        Text(
            text = dailyReading.dateDisplay.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
            letterSpacing = 1.1.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = when (currentLanguage) {
                AppLanguage.ES -> "La Palabra de hoy"
                AppLanguage.EN -> "Today's Scripture"
                AppLanguage.PT -> "A Palavra de hoje"
            },
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth().testTag("apple_daily_reading_hero"),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = dailyReading.feastTitle.get(currentLanguage),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dailyReading.gospelRef,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "“${dailyReading.gospelText.get(currentLanguage).take(220).trim()}…”",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 25.sp
                )
                Spacer(modifier = Modifier.height(18.dp))
                Button(
                    onClick = onNavigateToBible,
                    modifier = Modifier.fillMaxWidth().testTag("apple_read_today_button"),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Abrir las lecturas"
                            AppLanguage.EN -> "Read today's passages"
                            AppLanguage.PT -> "Abrir as leituras"
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun RoutineQuickCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val identity = LocalAppThemePreset.current
    val isApple = identity == AppThemePreset.APPLE.id
    val isMaterial = identity == AppThemePreset.MATERIAL.id
    val cardShape = when (identity) {
        AppThemePreset.APPLE.id -> MaterialTheme.shapes.large
        AppThemePreset.MATERIAL.id -> MaterialTheme.shapes.medium
        else -> MaterialTheme.shapes.small
    }
    val iconShape = when (identity) {
        AppThemePreset.APPLE.id -> MaterialTheme.shapes.small
        AppThemePreset.MATERIAL.id -> CircleShape
        else -> MaterialTheme.shapes.extraSmall
    }
    val iconContainer = when {
        isApple -> MaterialTheme.colorScheme.surfaceVariant
        isMaterial -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.secondaryContainer
    }
    val iconTint = when {
        isApple -> MaterialTheme.colorScheme.primary
        isMaterial -> MaterialTheme.colorScheme.onPrimaryContainer
        else -> MaterialTheme.colorScheme.secondary
    }

    Surface(
        modifier = modifier
            .clip(cardShape)
            .clickable(onClick = onClick),
        shape = cardShape,
        color = when {
            isApple -> MaterialTheme.colorScheme.surfaceVariant
            isMaterial -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.38f)
            else -> MaterialTheme.colorScheme.surface
        },
        tonalElevation = if (isMaterial) 3.dp else 0.dp,
        border = if (isApple || isMaterial) null else CardDefaults.outlinedCardBorder().copy(width = 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = if (isApple) 14.dp else 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(if (isApple) 40.dp else 36.dp)
                    .clip(iconShape)
                    .background(iconContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(if (isApple) 21.dp else 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = if (isApple) MaterialTheme.typography.labelLarge else if (isMaterial) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyMedium,
                fontFamily = MaterialTheme.typography.headlineSmall.fontFamily,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun QuickActionBanner(
    title: String,
    subtitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
        border = CardDefaults.outlinedCardBorder().copy(width = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(26.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text(text = subtitle, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
