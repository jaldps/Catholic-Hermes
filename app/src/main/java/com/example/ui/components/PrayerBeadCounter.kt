package com.example.ui.components

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.RecitationVoiceMode
import com.example.ui.theme.LocalAppDesign

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PrayerBeadCounter(
    currentDecade: Int, // 1 to 5
    currentBeadInDecade: Int, // 0 = Our Father (Large bead), 1..10 = Hail Mary beads
    isPlayingAudio: Boolean,
    voiceMode: RecitationVoiceMode,
    onPreviousBead: () -> Unit,
    onNextBead: () -> Unit,
    onToggleAudio: () -> Unit,
    onVoiceModeChange: (RecitationVoiceMode) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val palette = LocalAppDesign.current.palette

    fun performHaptic() {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            if (vibrator != null && vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(35, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(35)
                }
            }
        } catch (_: Exception) {}
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("prayer_bead_counter"),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Decade indicator and Bead Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "MISTERIO / DÉCADA $currentDecade DE 5",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = palette.gold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = if (currentBeadInDecade == 0) "Padre Nuestro (Cuenta Mayor)" else "Ave María: $currentBeadInDecade / 10",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Beads visualization row: Large bead + 10 small beads. Each
            // bead is a 48dp touch target with the visual bead centred
            // inside, so the run wraps instead of overflowing one row.
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Large Our Father bead
                val isLargeActive = currentBeadInDecade == 0
                // Pending beads use textSecondary as a solid fill (full
                // opacity, no alpha wash) so the bead reads against the
                // card in both modes; the active bead keeps the gold accent.
                val largeBeadBg by animateColorAsState(
                    targetValue = if (isLargeActive) palette.gold else palette.textSecondary,
                    label = "largeBead"
                )

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .semantics {
                            stateDescription = "Padre Nuestro, cuenta mayor, " +
                                "década $currentDecade de 5, " +
                                if (isLargeActive) "cuenta actual" else "cuenta pendiente"
                        }
                        .clickable(
                            role = Role.Button,
                            onClickLabel = "Sentir cuenta mayor"
                        ) {
                            performHaptic()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(largeBeadBg)
                            .border(
                                width = 2.dp,
                                color = if (isLargeActive) palette.accent else palette.textPrimary,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "PN",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            // surfaceVariant is the card colour: it inverts
                            // textSecondary in both modes (dark on light,
                            // light on dark) for >=4.5:1 on the bead fill.
                            color = if (isLargeActive) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.width(6.dp))

                // 10 Hail Mary beads
                (1..10).forEach { beadNumber ->
                    val isActive = currentBeadInDecade == beadNumber
                    val isCompleted = currentBeadInDecade > beadNumber
                    val beadBg by animateColorAsState(
                        targetValue = when {
                            isActive -> palette.accent
                            isCompleted -> palette.gold
                            else -> palette.textSecondary
                        },
                        label = "bead_$beadNumber"
                    )

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .semantics {
                                stateDescription = "Ave María $beadNumber de 10, " +
                                    "década $currentDecade de 5, " +
                                    when {
                                        isActive -> "cuenta actual"
                                        isCompleted -> "cuenta completada"
                                        else -> "cuenta pendiente"
                                    }
                            }
                            .clickable(
                                role = Role.Button,
                                onClickLabel = "Sentir esta cuenta"
                            ) {
                                performHaptic()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(beadBg)
                                .border(
                                    width = 2.dp,
                                    // Active keeps the gold accent; pending and
                                    // completed beads get a full-opacity
                                    // textPrimary ring instead of outline @ 50%.
                                    color = if (isActive) palette.gold else palette.textPrimary,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$beadNumber",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = when {
                                    isActive -> MaterialTheme.colorScheme.onPrimary
                                    isCompleted -> MaterialTheme.colorScheme.onTertiary
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation and Recitation Action Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous Bead Button
                OutlinedButton(
                    onClick = {
                        performHaptic()
                        onPreviousBead()
                    },
                    modifier = Modifier.testTag("prev_bead_button")
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous Bead", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Anterior", fontSize = 12.sp)
                }

                // Audio Play/Pause Recitation Button
                ElevatedButton(
                    onClick = {
                        performHaptic()
                        onToggleAudio()
                    },
                    modifier = Modifier.testTag("toggle_prayer_audio_button"),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = palette.accent,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = if (isPlayingAudio) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Audio Recitation",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isPlayingAudio) "Pausar Voz" else "Rezar Audio",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Next Bead Button
                FilledTonalButton(
                    onClick = {
                        performHaptic()
                        onNextBead()
                    },
                    modifier = Modifier.testTag("next_bead_button"),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = palette.gold,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Text(text = "Siguiente", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next Bead", modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Voice Selector row (Dual Dialogue, Male, Female)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = null, modifier = Modifier.size(14.dp), tint = palette.gold)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Modo de Voz:", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(8.dp))

                VoiceChip(
                    label = "Diálogo Dual",
                    isSelected = voiceMode == RecitationVoiceMode.DUAL_DIALOGUE,
                    onClick = { onVoiceModeChange(RecitationVoiceMode.DUAL_DIALOGUE) }
                )
                Spacer(modifier = Modifier.width(6.dp))
                VoiceChip(
                    label = "Voz Guía",
                    isSelected = voiceMode == RecitationVoiceMode.MALE,
                    onClick = { onVoiceModeChange(RecitationVoiceMode.MALE) }
                )
                Spacer(modifier = Modifier.width(6.dp))
                VoiceChip(
                    label = "Respuesta",
                    isSelected = voiceMode == RecitationVoiceMode.FEMALE,
                    onClick = { onVoiceModeChange(RecitationVoiceMode.FEMALE) }
                )
            }
        }
    }
}

@Composable
private fun VoiceChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val palette = LocalAppDesign.current.palette
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .border(
                1.5.dp,
                if (isSelected) MaterialTheme.colorScheme.primary else palette.textSecondary,
                RoundedCornerShape(8.dp)
            )
            .heightIn(min = 48.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
