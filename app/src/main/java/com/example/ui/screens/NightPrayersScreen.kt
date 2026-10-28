package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.models.AppLanguage
import com.example.data.models.ComplineOffice
import com.example.data.models.IgnatianExamenStep
import com.example.data.models.MarianAntiphonItem
import com.example.data.repository.CatholicDataRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NightPrayersScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var selectedNightOfficeTab by remember { mutableIntStateOf(0) } // 0 = Roman Compline, 1 = Ignatian Examen

    val compline = repository.complineOffice
    val examenSteps = repository.ignatianExamenSteps
    val isPlayingAudio by ttsManager.isPlaying.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("night_prayers_screen")
    ) {
        SecondaryTabRow(
            selectedTabIndex = selectedNightOfficeTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedNightOfficeTab == 0,
                onClick = { selectedNightOfficeTab = 0 },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.NightsStay, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = when (currentLanguage) {
                                AppLanguage.ES -> "Completas (Breviario)"
                                AppLanguage.EN -> "Roman Compline"
                                AppLanguage.PT -> "Completas (Breviário)"
                            },
                            fontSize = 12.sp,
                            fontWeight = if (selectedNightOfficeTab == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            )
            Tab(
                selected = selectedNightOfficeTab == 1,
                onClick = { selectedNightOfficeTab = 1 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Examen Ignaciano"
                            AppLanguage.EN -> "Ignatian Examen"
                            AppLanguage.PT -> "Exame Inaciano"
                        },
                        fontSize = 12.sp,
                        fontWeight = if (selectedNightOfficeTab == 1) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }

        when (selectedNightOfficeTab) {
            0 -> ComplineView(compline = compline, ttsManager = ttsManager, currentLanguage = currentLanguage)
            1 -> IgnatianExamenView(steps = examenSteps, ttsManager = ttsManager, currentLanguage = currentLanguage)
        }
    }
}

@Composable
private fun ComplineView(
    compline: ComplineOffice,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage
) {
    var selectedMarianAntiphonIndex by remember { mutableIntStateOf(0) }
    val currentAntiphon = compline.marianAntiphons.getOrElse(selectedMarianAntiphonIndex) { compline.marianAntiphons.first() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = compline.title.get(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Liturgia oficial de la Iglesia antes del reposo de la noche",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        ElevatedButton(
                            onClick = {
                                val fullText = buildString {
                                    append(compline.openingVerseTranslation.get(currentLanguage))
                                    append("\n\n")
                                    append(compline.confiteorText.get(currentLanguage))
                                    append("\n\n")
                                    append(compline.hymnTranslation.get(currentLanguage))
                                    append("\n\n")
                                    append(compline.psalmText.get(currentLanguage))
                                    append("\n\n")
                                    append(compline.canticleOfSimeonTranslation.get(currentLanguage))
                                    append("\n\n")
                                    append(compline.concludingPrayer.get(currentLanguage))
                                }
                                ttsManager.speakSingle(fullText)
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Rezar", fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // 1. Invocación inicial
        item {
            ComplineCard(
                stepLabel = "1. INVOCACIÓN INICIAL",
                latinTitle = "Deus in adiutorium",
                latinText = compline.openingVerseLatin,
                translatedText = compline.openingVerseTranslation.get(currentLanguage)
            )
        }

        // 2. Confiteor
        item {
            ComplineCard(
                stepLabel = "2. ACTO PENITENCIAL",
                latinTitle = "Confiteor Deo omnipotenti",
                latinText = "Confíteor Deo omnipoténti, et vobis, fratres, quia peccávi nimis cogitatióne, verbo, ópere et omissióne: mea culpa, mea culpa, mea máxima culpa...",
                translatedText = compline.confiteorText.get(currentLanguage)
            )
        }

        // 3. Himno
        item {
            ComplineCard(
                stepLabel = "3. HIMNO DE COMPLETAS",
                latinTitle = compline.hymnLatinTitle,
                latinText = compline.hymnLatin,
                translatedText = compline.hymnTranslation.get(currentLanguage)
            )
        }

        // 4. Salmo 91
        item {
            ComplineCard(
                stepLabel = "4. SALMODIA DE LA NOCHE",
                latinTitle = compline.psalmReference,
                latinText = "Qui hábitat in protectióne Altíssimi, in umbra Omnipoténtis commorábitur. Dicet Dómino: Refúgium meum et fortitúdo mea, Deus meus, in quo confídam...",
                translatedText = "${compline.psalmAntiphon.get(currentLanguage)}\n\n${compline.psalmText.get(currentLanguage)}"
            )
        }

        // 5. Lectura breve & Responsorio
        item {
            ComplineCard(
                stepLabel = "5. LECTURA BREVE Y RESPONSORIO",
                latinTitle = compline.readingReference,
                latinText = compline.responsoryLatin,
                translatedText = "${compline.readingText.get(currentLanguage)}\n\n${compline.responsoryTranslation.get(currentLanguage)}"
            )
        }

        // 6. Cántico de Simeón (Nunc Dimittis)
        item {
            ComplineCard(
                stepLabel = "6. CÁNTICO EVANGÉLICO (NUNC DIMITTIS)",
                latinTitle = "Canticum Simeonis (Lc 2, 29-32)",
                latinText = "${compline.canticleAntiphon.get(currentLanguage)}\n\n${compline.canticleOfSimeonLatin}",
                translatedText = "${compline.canticleAntiphon.get(currentLanguage)}\n\n${compline.canticleOfSimeonTranslation.get(currentLanguage)}",
                isHighlighted = true
            )
        }

        // 7. Oración Conclusiva y Bendición
        item {
            ComplineCard(
                stepLabel = "7. ORACIÓN CONCLUSIVA Y BENDICIÓN",
                latinTitle = "Oratio et Benedictio",
                latinText = "Noctem quiétam et finem perféctum concédat nobis Dóminus omnípotens. Amen.",
                translatedText = "${compline.concludingPrayer.get(currentLanguage)}\n\n${compline.blessing.get(currentLanguage)}"
            )
        }

        // 8. Antífona Mariana
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "8. ANTÍFONA MARIANA FINAL",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(compline.marianAntiphons) { antiphon ->
                            val isSelected = antiphon.id == currentAntiphon.id
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    val idx = compline.marianAntiphons.indexOf(antiphon)
                                    selectedMarianAntiphonIndex = idx
                                },
                                label = { Text(antiphon.titleLatin, fontSize = 12.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = currentAntiphon.titleTranslation.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = currentAntiphon.seasonTag.get(currentLanguage),
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentAntiphon.latinText,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentAntiphon.translatedText.get(currentLanguage),
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 13.sp,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ElevatedButton(
                        onClick = {
                            ttsManager.speakSingle(currentAntiphon.translatedText.get(currentLanguage))
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Escuchar ${currentAntiphon.titleLatin}", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun ComplineCard(
    stepLabel: String,
    latinTitle: String,
    latinText: String,
    translatedText: String,
    isHighlighted: Boolean = false
) {
    var showLatin by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) MaterialTheme.colorScheme.primary.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        ),
        border = if (isHighlighted) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.secondary)) else null
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stepLabel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    letterSpacing = 1.sp
                )

                Text(
                    text = if (showLatin) "Ocultar Latín" else "Ver Latín",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { showLatin = !showLatin }
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = latinTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )

            AnimatedVisibility(visible = showLatin) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = latinText,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = translatedText,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = 13.sp,
                lineHeight = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun IgnatianExamenView(
    steps: List<IgnatianExamenStep>,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage
) {
    var completedSteps by remember { mutableStateOf(setOf<Int>()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ElevatedCard(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Examen de Conciencia Nocturno (Método de San Ignacio)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "5 pasos para revisar el día con sinceridad ante Dios antes de dormir y recibir la paz de Cristo.",
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val progressPercent = (completedSteps.size.toFloat() / steps.size.toFloat()) * 100f
                    Text(
                        text = "Progreso del examen: ${completedSteps.size} de ${steps.size} pasos completados (${progressPercent.toInt()}%)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        items(steps) { step ->
            val isStepDone = completedSteps.contains(step.stepNumber)

            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = if (isStepDone) MaterialTheme.colorScheme.primary.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface
                ),
                border = if (isStepDone) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.secondary)) else CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = step.title.get(currentLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f)
                        )

                        FilledTonalButton(
                            onClick = {
                                completedSteps = if (isStepDone) completedSteps - step.stepNumber else completedSteps + step.stepNumber
                            },
                            shape = CircleShape
                        ) {
                            if (isStepDone) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            } else {
                                Text("Hecho", fontSize = 11.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = step.description.get(currentLanguage),
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Oración sugerida:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = step.guidedPrayer.get(currentLanguage),
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ElevatedButton(
                            onClick = {
                                ttsManager.speakSingle(step.guidedPrayer.get(currentLanguage))
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Escuchar", fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        if (completedSteps.size == steps.size) {
            item {
                ElevatedCard(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("¡Examen Completado!", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Descansa en la paz y el perdón de Nuestro Señor Jesucristo. Que la Virgen María y los santos Ángeles custodien tu sueño.",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.9f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
