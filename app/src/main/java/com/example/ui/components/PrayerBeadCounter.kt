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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.RecitationVoiceMode

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
                    color = MaterialTheme.colorScheme.secondary,
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

            // Beads visualization row: Large bead + 10 small beads
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Large Our Father bead
                val isLargeActive = currentBeadInDecade == 0
                val largeBeadBg by animateColorAsState(
                    targetValue = if (isLargeActive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    label = "largeBead"
                )

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(largeBeadBg)
                        .border(
                            width = if (isLargeActive) 2.dp else 1.dp,
                            color = if (isLargeActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        )
                        .clickable {
                            performHaptic()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PN",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isLargeActive) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                // 10 Hail Mary beads
                (1..10).forEach { beadNumber ->
                    val isActive = currentBeadInDecade == beadNumber
                    val isCompleted = currentBeadInDecade > beadNumber
                    val beadBg by animateColorAsState(
                        targetValue = when {
                            isActive -> MaterialTheme.colorScheme.primary
                            isCompleted -> MaterialTheme.colorScheme.secondary
                            else -> MaterialTheme.colorScheme.surface
                        },
                        label = "bead_$beadNumber"
                    )

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(beadBg)
                            .border(
                                width = if (isActive) 2.dp else 1.dp,
                                color = if (isActive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                            .clickable {
                                performHaptic()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$beadNumber",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                isActive -> Color.White
                                isCompleted -> Color.Black
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
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
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
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
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
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
                Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.secondary)
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
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .border(
                1.dp,
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
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
