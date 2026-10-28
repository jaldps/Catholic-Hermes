package com.example.ui.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.AppPreferencesEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.ui.theme.AppThemePreset
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

private data class LocalizedThemeOption(
    val value: String,
    val es: String,
    val en: String,
    val pt: String
) {
    fun label(language: AppLanguage): String = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun GuiCustomizerModal(
    dao: SanctaFidesDao,
    currentLanguage: AppLanguage = AppLanguage.ES,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val savedPreferences by dao.getAppPreferences().collectAsState(initial = null)
    val currentPreferences = savedPreferences ?: AppPreferencesEntity()

    var selectedTheme by remember(savedPreferences?.themePreset) { mutableStateOf(AppThemePreset.normalizeId(currentPreferences.themePreset)) }
    var selectedDarkMode by remember(savedPreferences?.darkModeSetting) { mutableStateOf(currentPreferences.darkModeSetting) }
    var selectedTypography by remember(savedPreferences?.fontFamilyChoice) { mutableStateOf(currentPreferences.fontFamilyChoice) }
    var selectedScale by remember(savedPreferences?.fontSizeScale) { mutableFloatStateOf(currentPreferences.fontSizeScale) }
    var selectedAudioSpeed by remember(savedPreferences?.audioNarrationSpeed) { mutableFloatStateOf(currentPreferences.audioNarrationSpeed) }

    val sheetShape = when (selectedTheme) {
        AppThemePreset.APPLE.id -> RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        AppThemePreset.MATERIAL.id -> RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        else -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = sheetShape,
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("gui_customizer_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = "Personalización",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ES -> "Personalizar Interfaz y Tema"
                            AppLanguage.EN -> "Customize Interface & Theme"
                            AppLanguage.PT -> "Personalizar Interface e Tema"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 1. Theme Presets Selection
            Text(
                text = when (currentLanguage) {
                    AppLanguage.ES -> "Identidad visual"
                    AppLanguage.EN -> "Visual identity"
                    AppLanguage.PT -> "Identidade visual"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            AppThemePreset.values().forEach { preset ->
                val isSelected = selectedTheme == preset.id
                val presetShape = when (preset) {
                    AppThemePreset.APPLE -> RoundedCornerShape(22.dp)
                    AppThemePreset.MATERIAL -> RoundedCornerShape(16.dp)
                    AppThemePreset.VATICAN -> RoundedCornerShape(8.dp)
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { selectedTheme = preset.id }
                        .border(
                            width = if (isSelected) 1.5.dp else 0.5.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.45f),
                            shape = presetShape
                        )
                        .testTag("theme_preset_${preset.id}"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = presetShape
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .background(preset.previewPrimary)
                                    .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = when (currentLanguage) {
                                    AppLanguage.ES -> preset.label.es
                                    AppLanguage.EN -> preset.label.en
                                    AppLanguage.PT -> preset.label.pt
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Seleccionado",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 2. Dark Mode Mode
            Text(
                text = when (currentLanguage) {
                    AppLanguage.ES -> "Modo de Brillo"
                    AppLanguage.EN -> "Brightness Mode"
                    AppLanguage.PT -> "Modo de Brilho"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))

            val modeOptions = listOf(
                LocalizedThemeOption("SYSTEM", "Sistema", "System", "Sistema"),
                LocalizedThemeOption("LIGHT", "Claro", "Light", "Claro"),
                LocalizedThemeOption("DARK", "Oscuro", "Dark", "Escuro")
            )
            if (selectedTheme == AppThemePreset.APPLE.id) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    modeOptions.forEach { option ->
                        val mode = option.value
                        val selected = selectedDarkMode == mode
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { selectedDarkMode = mode },
                            shape = MaterialTheme.shapes.medium,
                            color = if (selected) MaterialTheme.colorScheme.surface else Color.Transparent,
                            shadowElevation = if (selected) 1.dp else 0.dp
                        ) {
                            Text(
                                text = option.label(currentLanguage),
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 10.dp),
                                style = MaterialTheme.typography.labelMedium,
                                textAlign = TextAlign.Center,
                                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    modeOptions.forEach { option ->
                        val mode = option.value
                        val isSelected = selectedDarkMode == mode
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedDarkMode = mode },
                            label = {
                                Text(option.label(currentLanguage))
                            },
                            leadingIcon = if (isSelected) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            if (selectedTheme == AppThemePreset.VATICAN.id) {
            // 3. Vatican editorial typography
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TextFields, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Tipografía Sagrada"
                        AppLanguage.EN -> "Sacred Typography"
                        AppLanguage.PT -> "Tipografia Sagrada"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    LocalizedThemeOption("SERIF", "Serif tradicional (eclesial)", "Traditional serif (ecclesial)", "Serif tradicional (eclesial)"),
                    LocalizedThemeOption("SANS", "Sans contemporánea (limpia)", "Contemporary sans (clean)", "Sans contemporânea (limpa)")
                ).forEach { option ->
                    val style = option.value
                    val isSelected = selectedTypography == style
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedTypography = style },
                        label = {
                            Text(option.label(currentLanguage))
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            }

            // 4. Text Font Scale
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FormatSize, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Tamaño de Lectura: ${(selectedScale * 100).toInt()}%"
                        AppLanguage.EN -> "Reading Size: ${(selectedScale * 100).toInt()}%"
                        AppLanguage.PT -> "Tamanho do Texto: ${(selectedScale * 100).toInt()}%"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    1.0f to LocalizedThemeOption("", "100% (Normal)", "100% (Default)", "100% (Normal)"),
                    1.15f to LocalizedThemeOption("", "115% (Grande)", "115% (Large)", "115% (Grande)"),
                    1.30f to LocalizedThemeOption("", "130% (Muy grande)", "130% (Extra large)", "130% (Muito grande)")
                ).forEach { (scale, label) ->
                    val isSelected = kotlin.math.abs(selectedScale - scale) < 0.05f
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedScale = scale },
                        label = { Text(label.label(currentLanguage)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 5. Audio Speed
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Speed, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Velocidad de Canto y Oración: ${selectedAudioSpeed}x"
                        AppLanguage.EN -> "Chant & Audio Speed: ${selectedAudioSpeed}x"
                        AppLanguage.PT -> "Velocidade do Áudio: ${selectedAudioSpeed}x"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    0.8f to LocalizedThemeOption("", "0,8x (Lento)", "0.8x (Slow)", "0,8x (Lento)"),
                    1.0f to LocalizedThemeOption("", "1,0x (Estándar)", "1.0x (Standard)", "1,0x (Padrão)"),
                    1.2f to LocalizedThemeOption("", "1,2x (Fluido)", "1.2x (Smooth)", "1,2x (Fluido)")
                ).forEach { (speed, label) ->
                    val isSelected = kotlin.math.abs(selectedAudioSpeed - speed) < 0.05f
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedAudioSpeed = speed },
                        label = { Text(label.label(currentLanguage)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Save Button
            Button(
                onClick = {
                    val updated = currentPreferences.copy(
                        themePreset = AppThemePreset.normalizeId(selectedTheme),
                        darkModeSetting = selectedDarkMode,
                        fontFamilyChoice = selectedTypography,
                        fontSizeScale = selectedScale,
                        audioNarrationSpeed = selectedAudioSpeed
                    )
                    scope.launch {
                        dao.saveAppPreferences(updated)
                    }
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("save_preferences_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.ES -> "Aplicar y Guardar Preferencias"
                        AppLanguage.EN -> "Apply & Save Preferences"
                        AppLanguage.PT -> "Aplicar e Salvar Preferências"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
