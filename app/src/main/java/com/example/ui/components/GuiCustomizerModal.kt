package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppSheet
import com.example.ui.design.lumenPress
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch

/**
 * Appearance, as a Lumen sheet: every choice applies the moment it is made
 * (no Save button). Mode, tint, text size and narration speed — the font
 * lives in its own picker under More, so it is not repeated here.
 */
@Composable
fun GuiCustomizerModal(
    preferencesStore: UserPreferencesStore,
    preferences: UserPreferences,
    currentLanguage: AppLanguage = AppLanguage.ES,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    fun t(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    @Composable
    fun Label(text: String) {
        Text(
            text,
            style = styles.footnote,
            color = p.textSecondary,
            modifier = Modifier.padding(start = design.spacing.gutter + 4.dp, end = design.spacing.gutter, top = 18.dp, bottom = 8.dp)
        )
    }

    AppSheet(onDismiss = onDismiss, title = t("Apariencia", "Appearance", "Aparência"), tag = "gui_customizer_sheet") {
        Label(t("Modo", "Mode", "Modo"))
        val modes = listOf("SYSTEM", "LIGHT", "DARK")
        AppSegmentedTabs(
            options = listOf(t("Automático", "Automatic", "Automático"), t("Claro", "Light", "Claro"), t("Oscuro", "Dark", "Escuro")),
            selectedIndex = modes.indexOf(preferences.darkModeSetting).coerceAtLeast(0),
            onSelect = { i -> scope.launch { preferencesStore.setDarkModeSetting(modes[i]) } },
            modifier = Modifier.testTag("appearance_mode")
        )

        Label(t("Color de acento", "Accent colour", "Cor de destaque"))
        Row(
            Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AppThemePreset.entries.forEach { preset ->
                val selected = preferences.themePreset == preset.id
                val swatch = when (preset.id) {
                    "MONASTIC_MINIMALIST" -> if (design.isDark) Color(0xFFAEB8C6) else Color(0xFF465163)
                    "SACRED_BURGUNDY" -> if (design.isDark) Color(0xFFE59AA6) else Color(0xFF8A2D3B)
                    "MARIAN_AZURE" -> if (design.isDark) Color(0xFF8FC2EC) else Color(0xFF2A6597)
                    else -> if (design.isDark) Color(0xFF7FC48A) else Color(0xFF2F7A3E)
                }
                Box(
                    Modifier
                        .size(52.dp)
                        .lumenPress({ scope.launch { preferencesStore.setThemePreset(preset.id) } }, 0.9f)
                        .semantics {
                            this.selected = selected
                            contentDescription = preset.label.get(currentLanguage)
                        }
                        .testTag("theme_preset_${preset.id}"),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .size(40.dp)
                            .border(if (selected) 2.dp else 0.dp, if (selected) p.textPrimary else Color.Transparent, CircleShape)
                            .padding(if (selected) 4.dp else 0.dp)
                            .background(swatch, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selected) Icon(Icons.Filled.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        Label(t("Tamaño del texto", "Text size", "Tamanho do texto"))
        val scales = listOf(0.9f, 1.0f, 1.15f, 1.3f)
        AppSegmentedTabs(
            options = listOf("A−", "A", "A+", "A++"),
            selectedIndex = scales.indexOfFirst { kotlin.math.abs(it - preferences.fontSizeScale) < 0.03f }.coerceAtLeast(1),
            onSelect = { i -> scope.launch { preferencesStore.setFontSizeScale(scales[i]) } },
            modifier = Modifier.testTag("appearance_text_size")
        )

        Label(t("Velocidad de la voz", "Narration speed", "Velocidade da voz"))
        val speeds = listOf(0.75f, 1.0f, 1.25f, 1.5f)
        AppSegmentedTabs(
            options = listOf("0.75×", "1×", "1.25×", "1.5×"),
            selectedIndex = speeds.indexOfFirst { kotlin.math.abs(it - preferences.audioNarrationSpeed) < 0.03f }.coerceAtLeast(1),
            onSelect = { i -> scope.launch { preferencesStore.setAudioNarrationSpeed(speeds[i]) } },
            modifier = Modifier.testTag("appearance_narration_speed")
        )
        Spacer(Modifier.height(20.dp))
    }
}
