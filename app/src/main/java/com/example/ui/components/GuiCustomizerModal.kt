package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.heading
import com.example.ui.theme.AccentColors
import com.example.ui.theme.NonEcclesialAccent
import androidx.compose.ui.platform.LocalContext
import com.example.data.audio.LiturgicalTtsManager
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppMenuChip
import com.example.ui.design.AppMenuItem
import com.example.ui.design.AppSecondaryButton
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
import androidx.compose.ui.graphics.luminance
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
import com.example.ui.theme.DesignLanguage
import com.example.ui.design.AppToggleRow
import com.example.ui.theme.AppThemePreset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch

/** Opens the system's text-to-speech settings, or the voice-data installer as a fallback. */
internal fun openVoiceSettings(context: Context) {
    val attempts = listOf(
        Intent("com.android.settings.TTS_SETTINGS"),
        Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA)
    )
    for (intent in attempts) {
        val opened = runCatching { context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }.isSuccess
        if (opened) return
    }
}

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
    ttsManager: LiturgicalTtsManager? = null,
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
    fun Label(text: String, firstInSheet: Boolean = false) {
        Text(
            text,
            style = styles.footnote,
            color = p.textSecondary,
            modifier = Modifier.padding(start = design.spacing.gutter + 4.dp, end = design.spacing.gutter, top = if (firstInSheet && com.example.ui.design.appHasCompactHeight()) 12.dp else 18.dp, bottom = 8.dp)
        )
    }

    AppSheet(onDismiss = onDismiss, title = t("Apariencia", "Appearance", "Aparência"), tag = "gui_customizer_sheet") {
        // Catholic1 is the single identity (CATHOLIC1.md §0): no style or accent pickers.
        Label(t("Modo", "Mode", "Modo"), firstInSheet = true)
        val modes = listOf("SYSTEM", "LIGHT", "DARK")
        AppSegmentedTabs(
            options = listOf(t("Automático", "Automatic", "Automático"), t("Claro", "Light", "Claro"), t("Oscuro", "Dark", "Escuro")),
            selectedIndex = modes.indexOf(preferences.darkModeSetting).coerceAtLeast(0),
            onSelect = { i -> scope.launch { preferencesStore.setDarkModeSetting(modes[i]) } },
            modifier = Modifier.testTag("appearance_mode")
        )

        Label(t("Color de los iconos de la barra", "Tab bar icon colour", "Cor dos ícones da barra"))
        val tints = listOf("LITURGICAL", "THEME")
        AppSegmentedTabs(
            options = listOf(t("Litúrgico", "Liturgical", "Litúrgico"), t("Del tema", "Theme", "Do tema")),
            selectedIndex = tints.indexOf(preferences.dockIconTint).coerceAtLeast(0),
            onSelect = { i -> scope.launch { preferencesStore.setDockIconTint(tints[i]) } },
            modifier = Modifier.testTag("appearance_dock_icon_tint")
        )
        Text(
            t("Litúrgico: los iconos toman el color litúrgico del día.", "Liturgical: the icons take the liturgical colour of the day.", "Litúrgico: os ícones assumem a cor litúrgica do dia."),
            style = styles.footnote, color = p.textSecondary,
            modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp, vertical = 8.dp)
        )

        Label(t("Tamaño del texto", "Text size", "Tamanho do texto"))
        val scales = listOf(0.9f, 1.0f, 1.15f, 1.3f)
        AppSegmentedTabs(
            options = listOf("A−", "A", "A+", "A++"),
            selectedIndex = scales.indexOfFirst { kotlin.math.abs(it - preferences.fontSizeScale) < 0.03f }.let { if (it < 0) 1 else it },
            onSelect = { i -> scope.launch { preferencesStore.setFontSizeScale(scales[i]) } },
            modifier = Modifier.testTag("appearance_text_size")
        )

        Label(t("Velocidad de la voz", "Narration speed", "Velocidade da voz"))
        val speeds = listOf(0.75f, 1.0f, 1.25f, 1.5f)
        AppSegmentedTabs(
            options = listOf("0.75×", "1×", "1.25×", "1.5×"),
            selectedIndex = speeds.indexOfFirst { kotlin.math.abs(it - preferences.audioNarrationSpeed) < 0.03f }.let { if (it < 0) 1 else it },
            onSelect = { i -> scope.launch { preferencesStore.setAudioNarrationSpeed(speeds[i]) } },
            modifier = Modifier.testTag("appearance_narration_speed")
        )

        Label(t("Voz de lectura", "Reading voice", "Voz de leitura"))
        val langCode = when (currentLanguage) { AppLanguage.ES -> "es"; AppLanguage.EN -> "en"; AppLanguage.PT -> "pt" }
        val voiceOptions = ttsManager?.voiceOptions?.collectAsState()?.value.orEmpty()
        val auto = t("Automática (recomendada)", "Automatic (recommended)", "Automática (recomendada)")
        val sample = t("Dios te salve, María, llena eres de gracia.", "Hail Mary, full of grace.", "Ave Maria, cheia de graça.")
        @Composable
        fun VoicePicker(role: String, title: String, previewDescription: String) {
            val saved = preferences.narrationVoices["$langCode.$role"]
            val selected = voiceOptions.firstOrNull { it.name == saved }
            val isResponse = role == "response"
            Text(
                title, style = styles.subhead, color = p.textPrimary,
                modifier = Modifier.padding(start = design.spacing.gutter + 4.dp, end = design.spacing.gutter, top = 4.dp, bottom = 6.dp)
            )
            Row(
                Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppMenuChip(
                    label = selected?.label ?: auto,
                    items = listOf(AppMenuItem(auto, selected = selected == null) {
                        scope.launch { preferencesStore.setNarrationVoice(langCode, role, null) }
                    }) + voiceOptions.map { option ->
                        AppMenuItem(option.label, selected = option.name == selected?.name) {
                            scope.launch { preferencesStore.setNarrationVoice(langCode, role, option.name) }
                        }
                    },
                    modifier = Modifier.weight(1f, fill = false),
                    tag = "voice_${role}_picker"
                )
                Spacer(Modifier.width(8.dp))
                AppGlassIconButton(
                    icon = Icons.Filled.PlayArrow,
                    contentDescription = previewDescription,
                    onClick = { ttsManager?.preview(isResponse, selected?.name, sample) },
                    tag = "voice_${role}_preview"
                )
            }
        }
        VoicePicker("leader", t("Voz que guía", "Leading voice", "Voz que conduz"),
            t("Escuchar la voz que guía", "Play the leading voice", "Ouvir a voz que conduz"))
        VoicePicker("response", t("Voz que responde", "Responding voice", "Voz que responde"),
            t("Escuchar la voz que responde", "Play the responding voice", "Ouvir a voz que responde"))
        Text(
            t("Temporizador de apagado", "Sleep timer", "Temporizador para desligar"), style = styles.subhead, color = p.textPrimary,
            modifier = Modifier.padding(start = design.spacing.gutter + 4.dp, end = design.spacing.gutter, top = 8.dp, bottom = 6.dp)
        )
        val sleepOff = t("Desactivado", "Off", "Desativado")
        fun sleepLabel(minutes: Int) = if (minutes == 0) sleepOff else t("$minutes min", "$minutes min", "$minutes min")
        Row(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter)) {
            AppMenuChip(
                label = sleepLabel(preferences.narrationSleepMinutes),
                items = UserPreferences.SLEEP_TIMER_OPTIONS.map { minutes ->
                    AppMenuItem(sleepLabel(minutes), selected = minutes == preferences.narrationSleepMinutes) {
                        scope.launch { preferencesStore.setNarrationSleepMinutes(minutes) }
                    }
                },
                tag = "narration_sleep_timer"
            )
        }
        val appContext = LocalContext.current
        AppSecondaryButton(
            label = t("Descargar voces de mejor calidad", "Get better voices", "Baixar vozes melhores"),
            onClick = { openVoiceSettings(appContext) },
            modifier = Modifier.padding(horizontal = design.spacing.gutter, vertical = 8.dp).testTag("voice_get_more")
        )
        Text(
            t(
                "El motor de voz de Google ofrece voces gratuitas sin conexión en español, inglés y portugués; una vez descargadas funcionan sin internet. Otros motores pueden ofrecer las suyas.",
                "Google's speech engine offers free offline voices in Spanish, English and Portuguese; once downloaded they work without internet. Other engines may offer their own.",
                "O motor de voz do Google oferece vozes gratuitas offline em espanhol, inglês e português; depois de baixadas, funcionam sem internet. Outros motores podem oferecer as suas."
            ),
            style = styles.footnote, color = p.textSecondary,
            modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp, vertical = 4.dp)
        )

        Label(t("Accesibilidad", "Accessibility", "Acessibilidade"))
        AppToggleRow(
            title = t("Reducir movimiento", "Reduce motion", "Reduzir movimento"),
            checked = preferences.reduceMotion,
            onCheckedChange = { value -> scope.launch { preferencesStore.setReduceMotion(value) } },
            modifier = Modifier.testTag("appearance_reduce_motion")
        )
        AppToggleRow(
            title = t("Reducir transparencia", "Reduce transparency", "Reduzir transparência"),
            checked = preferences.reduceTransparency,
            onCheckedChange = { value -> scope.launch { preferencesStore.setReduceTransparency(value) } },
            modifier = Modifier.testTag("appearance_reduce_transparency")
        )
        AppToggleRow(
            title = t("Mayor contraste", "Higher contrast", "Maior contraste"),
            checked = preferences.highContrast,
            onCheckedChange = { value -> scope.launch { preferencesStore.setHighContrast(value) } },
            modifier = Modifier.testTag("appearance_high_contrast")
        )
        Spacer(Modifier.height(20.dp))
    }
}

/** One round accent swatch: 52 dp target, check mark when selected. */
@Composable
private fun AccentSwatch(
    fill: Brush, selected: Boolean, tag: String, description: String,
    checkTint: Color, onClick: () -> Unit
) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .size(52.dp)
            .lumenPress(onClick, 0.9f, shape = CircleShape)
            .semantics { this.selected = selected; contentDescription = description }
            .testTag(tag),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(40.dp)
                .border(if (selected) 2.dp else 0.dp, if (selected) p.textPrimary else Color.Transparent, CircleShape)
                .padding(if (selected) 4.dp else 0.dp)
                .background(fill, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (selected) Icon(Icons.Filled.Check, null, tint = checkTint, modifier = Modifier.size(16.dp))
        }
    }
}

/**
 * The accent colour choice in two labelled groups: the four ecclesiastical
 * presets, then ten fixed non-ecclesiastical swatches and a custom colour that
 * opens a wheel. Swatches show the ink the identity will really draw.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AccentPicker(selectedPreset: String, language: AppLanguage, onSelect: (String) -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    fun t(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt
    }
    var wheelOpen by remember { mutableStateOf(false) }
    val custom = AccentColors.parseCustom(selectedPreset)
    fun inkOf(base: Color) = AccentColors.ink(base, design.language, design.isDark)
    fun checkOn(swatch: Color) = if (swatch.luminance() > 0.35f) Color(0xFF211D19) else Color.White
    val gutter = design.spacing.gutter

    @Composable
    fun GroupLabel(text: String, tag: String) {
        Text(
            text, style = styles.subhead, color = p.textPrimary,
            modifier = Modifier.padding(start = gutter + 4.dp, end = gutter, top = 4.dp, bottom = 2.dp)
                .semantics { heading() }.testTag(tag)
        )
    }

    GroupLabel(t("Eclesiásticos", "Ecclesiastical", "Eclesiásticos"), "accent_group_ecclesial")
    FlowRow(
        Modifier.fillMaxWidth().padding(horizontal = gutter),
        horizontalArrangement = Arrangement.spacedBy(4.dp), verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        AppThemePreset.entries.forEach { preset ->
            val swatch = when (preset.id) {
                "MONASTIC_MINIMALIST" -> if (design.isDark) Color(0xFFAEB8C6) else Color(0xFF465163)
                "SACRED_BURGUNDY" -> if (design.isDark) Color(0xFFE59AA6) else Color(0xFF8A2D3B)
                "MARIAN_AZURE" -> if (design.isDark) Color(0xFF9DBDFF) else Color(0xFF2459B5)
                else -> if (design.isSanctuary) (if (design.isDark) Color(0xFFE5A4AE) else Color(0xFF7A293A)) else if (design.isDark) Color(0xFF7FC48A) else Color(0xFF2A6E38)
            }
            AccentSwatch(SolidColor(swatch), selectedPreset == preset.id, "theme_preset_${preset.id}",
                preset.label.get(language), checkOn(swatch)) { onSelect(preset.id) }
        }
    }

    GroupLabel(t("No eclesiásticos", "Other colours", "Não eclesiásticos"), "accent_group_other")
    FlowRow(
        Modifier.fillMaxWidth().padding(horizontal = gutter),
        horizontalArrangement = Arrangement.spacedBy(4.dp), verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        NonEcclesialAccent.entries.forEach { accent ->
            val swatch = remember(accent, design.language, design.isDark) { inkOf(accent.base) }
            AccentSwatch(SolidColor(swatch), selectedPreset == accent.id, "theme_preset_${accent.id}",
                accent.label.get(language), checkOn(swatch)) { onSelect(accent.id) }
        }
        val customSwatch = custom?.let { remember(it, design.language, design.isDark) { inkOf(it) } }
        AccentSwatch(
            fill = if (customSwatch != null) SolidColor(customSwatch) else Brush.sweepGradient(
                listOf(Color(0xFFE5484D), Color(0xFFE5A31C), Color(0xFF5DB762), Color(0xFF14958F), Color(0xFF4A57D0), Color(0xFFD64C87), Color(0xFFE5484D))),
            selected = custom != null, tag = "theme_preset_CUSTOM",
            description = t("Personalizado", "Custom", "Personalizado"),
            checkTint = customSwatch?.let { checkOn(it) } ?: Color.White
        ) { wheelOpen = true }
    }
    if (wheelOpen) {
        AccentColorWheelDialog(
            initial = custom ?: AccentColors.baseOf(selectedPreset) ?: p.accent,
            language = language,
            onSave = { color -> wheelOpen = false; onSelect(AccentColors.customId(color)) },
            onDismiss = { wheelOpen = false }
        )
    }
}
