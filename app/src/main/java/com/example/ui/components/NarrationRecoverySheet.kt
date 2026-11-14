package com.example.ui.components

import android.content.Intent
import android.provider.Settings
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.VoiceAvailability
import com.example.data.models.AppLanguage
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** Appears only after an explicit attempt to listen, never just on app launch. */
@Composable
fun NarrationRecoverySheet(manager: LiturgicalTtsManager, issue: VoiceAvailability, language: AppLanguage) {
    fun pick(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    val context = LocalContext.current
    val palette = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    val message = when (issue) {
        VoiceAvailability.INITIALIZING -> pick("La voz se está iniciando. Espera un momento y vuelve a pulsar escuchar.", "The voice is starting. Wait a moment, then tap Listen again.", "A voz está iniciando. Aguarde um momento e toque em ouvir novamente.")
        VoiceAvailability.MISSING_DATA -> pick("Faltan los datos de voz para el idioma de la app. Puedes instalarlos desde Android.", "Voice data for the app language is missing. You can install it through Android.", "Faltam os dados de voz para o idioma do app. Você pode instalá-los pelo Android.")
        VoiceAvailability.UNSUPPORTED -> pick("El motor de voz no admite el idioma de la app. Revisa las voces o elige otro motor en Android.", "The speech engine does not support the app language. Check voices or choose another engine in Android.", "O motor de voz não suporta o idioma do app. Verifique as vozes ou escolha outro motor no Android.")
        else -> pick("No se pudo reproducir la narración. Revisa el motor de voz y vuelve a intentarlo.", "Narration could not play. Check the speech engine and try again.", "Não foi possível reproduzir a narração. Verifique o motor de voz e tente novamente.")
    }
    AppSheet(onDismiss = manager::dismissPlaybackIssue, tag = "narration_recovery_sheet") {
        LumenSheetHeader(pick("Voz de lectura", "Reading voice", "Voz de leitura"), pick("Cerrar", "Close", "Fechar"), manager::dismissPlaybackIssue, "close_narration_recovery")
        Column(Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(message, style = styles.body, color = palette.textPrimary)
            if (issue != VoiceAvailability.INITIALIZING) {
                AppPrimaryCapsule(pick("Configurar voz en Android", "Android voice settings", "Configurar voz no Android"), {
                    val settings = Intent("com.android.settings.TTS_SETTINGS").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val install = Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val first = if (issue == VoiceAvailability.MISSING_DATA) install else settings
                    runCatching { context.startActivity(first) }.recoverCatching {
                        context.startActivity(settings)
                    }.recoverCatching {
                        context.startActivity(Intent(Settings.ACTION_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                    }
                }, tag = "open_voice_settings")
            }
            AppSecondaryCapsule(pick("Comprobar de nuevo", "Check again", "Verificar novamente"), {
                manager.refreshAvailability()
                manager.dismissPlaybackIssue()
                manager.requestPlayback()
            }, tag = "retry_voice_availability")
            Spacer(Modifier.height(8.dp))
        }
    }
}
