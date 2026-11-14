package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

@Composable
internal fun PreferencesRecoverySheet(store: UserPreferencesStore, available: Boolean, failedKeys: List<String>, language: AppLanguage, onDismiss: () -> Unit, saving: Boolean = false) {
    fun pick(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    val styles = LocalAppTextStyles.current
    val palette = LocalAppDesign.current.palette
    AppSheet(onDismiss = onDismiss, tag = "preferences_recovery_sheet") {
        LumenSheetHeader(pick("Datos y ajustes", "Data and settings", "Dados e ajustes"), pick("Cerrar", "Close", "Fechar"), onDismiss, "close_preferences_recovery")
        Column(Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            if (!available) {
                Text(pick("No se pudieron leer tus ajustes. La app usa los últimos valores disponibles o ajustes temporales. Puedes volver a intentar la lectura.", "Your settings could not be read. The app uses the last available values or temporary settings. You can try reading them again.", "Não foi possível ler seus ajustes. O app usa os últimos valores disponíveis ou ajustes temporários. Você pode tentar ler novamente."), style = styles.body, color = palette.textPrimary)
                AppSecondaryCapsule(pick("Reintentar lectura", "Read again", "Ler novamente"), store::retryRead, tag = "retry_preferences_read")
            }
            if (failedKeys.isNotEmpty()) {
                Text(pick("Uno o varios cambios no se pudieron guardar. Puedes volver a intentarlo.", "One or more changes could not be saved. You can retry those changes.", "Não foi possível salvar uma ou mais alterações. Você pode tentar novamente."), style = styles.body, color = palette.textPrimary)
                AppPrimaryCapsule(pick("Reintentar guardado", "Retry saving", "Tentar salvar novamente"), { failedKeys.forEach(PersonalDataCommands::retry) }, tag = "retry_preferences_write")
            } else if (saving) {
                Text(pick("Guardando cambios…", "Saving changes…", "Salvando alterações…"), style = styles.body, color = palette.textSecondary)
            } else if (available) {
                Text(pick("Los ajustes están disponibles. No hay cambios con error de guardado.", "Settings are available. No changes have a save error.", "Os ajustes estão disponíveis. Não há alterações com erro ao salvar."), style = styles.body, color = palette.textPrimary)
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}
