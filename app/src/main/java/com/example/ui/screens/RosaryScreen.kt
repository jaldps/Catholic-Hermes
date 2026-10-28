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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
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
import com.example.data.audio.RecitationVoiceMode
import com.example.data.models.AppLanguage
import com.example.data.models.DivineMercyStep
import com.example.data.models.RosaryDecade
import com.example.data.models.RosaryMysteryType
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.PrayerBeadCounter

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun RosaryScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Santo Rosario, 1 = Coronilla de la Divina Misericordia

    // Rosary State
    var selectedMysteryType by remember { mutableStateOf(repository.getTodayRosaryMystery().type) }
    var currentDecadeIndex by remember { mutableIntStateOf(0) } // 0..4 (Decade 1..5)
    var currentBeadInDecade by remember { mutableIntStateOf(0) } // 0 = Our Father (Large bead), 1..10 = Hail Mary beads

    // Divine Mercy State
    var mercyStepIndex by remember { mutableIntStateOf(0) }

    val isPlayingAudio by ttsManager.isPlaying.collectAsState()
    val voiceMode by ttsManager.voiceMode.collectAsState()
    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    val currentMystery = repository.getRosaryMystery(selectedMysteryType)
    val currentDecade = currentMystery.decades[currentDecadeIndex]

    fun triggerAudioForCurrentBead() {
        if (currentBeadInDecade == 0) {
            // Our Father (Leader and response)
            val leader = when (currentLanguage) {
                AppLanguage.ES -> "Padre nuestro, que estás en el cielo, santificado sea tu Nombre; venga a nosotros tu Reino; hágase tu voluntad en la tierra como en el cielo."
                AppLanguage.EN -> "Our Father, who art in heaven, hallowed be thy name; thy kingdom come; thy will be done on earth as it is in heaven."
                AppLanguage.PT -> "Pai nosso, que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade assim na terra como no céu."
            }
            val response = when (currentLanguage) {
                AppLanguage.ES -> "Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación, y líbranos del mal. Amén."
                AppLanguage.EN -> "Give us this day our daily bread; and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen."
                AppLanguage.PT -> "O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém."
            }
            ttsManager.speakDialogue(leader, response)
        } else {
            // Hail Mary (Leader and response)
            val leader = currentDecade.leaderPrayer.get(currentLanguage)
            val response = currentDecade.congregationResponse.get(currentLanguage)
            ttsManager.speakDialogue(leader, response)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("rosary_screen")
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "El Santo Rosario"
                            AppLanguage.EN -> "The Holy Rosary"
                            AppLanguage.PT -> "O Santo Terço"
                        },
                        fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 13.sp
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Divina Misericordia"
                            AppLanguage.EN -> "Divine Mercy"
                            AppLanguage.PT -> "Divina Misericórdia"
                        },
                        fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 13.sp
                    )
                }
            )
        }

        when (selectedTab) {
            0 -> {
                // Holy Rosary Recitation
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Mystery Selector Chips
                    item {
                        Column {
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> "SELECCIONA EL MISTERIO"
                                    AppLanguage.EN -> "SELECT THE MYSTERY"
                                    AppLanguage.PT -> "SELECIONE O MISTÉRIO"
                                },
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary,
                                letterSpacing = 1.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                RosaryMysteryType.values().forEach { mType ->
                                    val isSelected = mType == selectedMysteryType
                                    val isToday = mType == repository.getTodayRosaryMystery().type

                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(MaterialTheme.shapes.small)
                                            .clickable {
                                                selectedMysteryType = mType
                                                currentDecadeIndex = 0
                                                currentBeadInDecade = 0
                                            },
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                                        border = CardDefaults.outlinedCardBorder().copy(width = if (isSelected) 1.5.dp else 0.5.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = when (mType) {
                                                    RosaryMysteryType.JOYFUL -> "Gozosos"
                                                    RosaryMysteryType.SORROWFUL -> "Dolorosos"
                                                    RosaryMysteryType.GLORIOUS -> "Gloriosos"
                                                    RosaryMysteryType.LUMINOUS -> "Luminosos"
                                                },
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                                maxLines = 1
                                            )
                                            if (isToday) {
                                                Text(
                                                    text = "Hoy",
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Interactive PrayerBeadCounter
                    item {
                        PrayerBeadCounter(
                            currentDecade = currentDecadeIndex + 1,
                            currentBeadInDecade = currentBeadInDecade,
                            isPlayingAudio = isPlayingAudio,
                            voiceMode = voiceMode,
                            onPreviousBead = {
                                if (currentBeadInDecade > 0) {
                                    currentBeadInDecade--
                                } else if (currentDecadeIndex > 0) {
                                    currentDecadeIndex--
                                    currentBeadInDecade = 10
                                }
                            },
                            onNextBead = {
                                if (currentBeadInDecade < 10) {
                                    currentBeadInDecade++
                                } else if (currentDecadeIndex < 4) {
                                    currentDecadeIndex++
                                    currentBeadInDecade = 0
                                }
                            },
                            onToggleAudio = {
                                if (isPlayingAudio) {
                                    ttsManager.stop()
                                } else {
                                    triggerAudioForCurrentBead()
                                }
                            },
                            onVoiceModeChange = { newMode ->
                                ttsManager.setVoiceMode(newMode)
                            }
                        )
                    }

                    // Current Decade Meditation & Fruit Card
                    item {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${currentDecade.number}° Misterio: ${currentDecade.title.get(currentLanguage)}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = currentDecade.scriptureRef,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(MaterialTheme.shapes.small)
                                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f))
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = currentDecade.fruitOfMystery.get(currentLanguage),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = currentDecade.meditation.get(currentLanguage),
                                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                    style = MaterialTheme.typography.bodyMedium,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }

                    // Prayer Dialogue Display
                    item {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = if (currentBeadInDecade == 0) "Oración del Padre Nuestro" else "Oración del Ave María",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "V/. " + currentDecade.leaderPrayer.get(currentLanguage),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "R/. " + currentDecade.congregationResponse.get(currentLanguage),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            1 -> {
                // Divine Mercy Chaplet View
                val mercySteps = repository.divineMercySteps
                val activeStep = mercySteps[mercyStepIndex]

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 3:00 PM Callout
                    item {
                        Surface(
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colorScheme.primary,
                            tonalElevation = 4.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.AccessTime, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "3:00 PM — La Hora de la Gran Misericordia",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Text(
                                        text = "«A las tres de la tarde implora mi misericordia... En esta hora nada le será negado al alma que lo pida» (Diario de Santa Faustina).",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f),
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }

                    // Progress indicator across steps
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "PASO ${mercyStepIndex + 1} DE ${mercySteps.size}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                mercySteps.indices.forEach { idx ->
                                    Box(
                                        modifier = Modifier
                                            .size(if (idx == mercyStepIndex) 10.dp else 6.dp)
                                            .clip(CircleShape)
                                            .background(if (idx == mercyStepIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
                                    )
                                }
                            }
                        }
                    }

                    // Active Step Card
                    item {
                        ElevatedCard(
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(18.dp)) {
                                Text(
                                    text = activeStep.title.get(currentLanguage),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "V/. " + activeStep.leaderText.get(currentLanguage),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                    lineHeight = 24.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "R/. " + activeStep.responseText.get(currentLanguage),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.secondary,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }

                    // Divine Mercy Action Controls
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ElevatedButton(
                                onClick = {
                                    if (mercyStepIndex > 0) mercyStepIndex--
                                },
                                enabled = mercyStepIndex > 0
                            ) {
                                Text("Paso Anterior")
                            }

                            ElevatedButton(
                                onClick = {
                                    if (isPlayingAudio) {
                                        ttsManager.stop()
                                    } else {
                                        ttsManager.speakDialogue(
                                            activeStep.leaderText.get(currentLanguage),
                                            activeStep.responseText.get(currentLanguage)
                                        )
                                    }
                                },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                Icon(if (isPlayingAudio) Icons.Default.Pause else Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(if (isPlayingAudio) "Pausar" else "Rezar")
                            }

                            ElevatedButton(
                                onClick = {
                                    if (mercyStepIndex < mercySteps.size - 1) mercyStepIndex++
                                },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {
                                Text("Siguiente Paso")
                            }
                        }
                    }
                }
            }
        }
    }
}
