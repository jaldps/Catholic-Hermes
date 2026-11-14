package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.CalendarRules
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** Explicit transfers only: language does not select a jurisdiction or obligation law. */
@Composable
fun CalendarRulesSheet(language: AppLanguage, rules: CalendarRules, store: UserPreferencesStore, onDismiss: () -> Unit) {
    fun pick(es: String,en: String,pt: String) = when(language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    var draft by remember { mutableStateOf(rules) }
    val writes by PersonalDataCommands.states.collectAsState()
    val status = writes["calendar-transfers"]?.status
    fun change(key: String, value: Boolean) {
        draft = when(key) { "epiphany" -> draft.copy(epiphanyOnSunday = value); "ascension" -> draft.copy(ascensionOnSunday = value); else -> draft.copy(corpusChristiOnSunday = value) }
        PersonalDataCommands.submit("calendar-transfers") { store.setCalendarTransfer(key,value) }
    }
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    AppSheet(onDismiss = onDismiss, tag = "calendar_rules_sheet") {
        LumenSheetHeader(pick("Calendario litúrgico", "Liturgical calendar", "Calendário litúrgico"), pick("Listo", "Done", "Concluir"), onDismiss, "close_calendar_rules")
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(pick("Calendario romano general. Activa los traslados al domingo que correspondan a tu diócesis.", "General Roman Calendar. Enable the Sunday transfers observed in your diocese.", "Calendário Romano Geral. Ative as transferências para domingo observadas na sua diocese."),style = styles.body,color = p.textPrimary)
            Spacer(Modifier.height(16.dp))
            AppListGroup {
                AppToggleRow(pick("Epifanía al domingo", "Epiphany on Sunday", "Epifania no domingo"),draft.epiphanyOnSunday,{change("epiphany",it)},subtitle = pick("Desactivado: 6 de enero", "Off: January 6", "Desativado: 6 de janeiro"),switchTag = "transfer_epiphany")
                AppToggleRow(pick("Ascensión al domingo", "Ascension on Sunday", "Ascensão no domingo"),draft.ascensionOnSunday,{change("ascension",it)},subtitle = pick("Desactivado: jueves", "Off: Thursday", "Desativado: quinta-feira"),switchTag = "transfer_ascension")
                AppToggleRow(pick("Corpus Christi al domingo", "Corpus Christi on Sunday", "Corpus Christi no domingo"),draft.corpusChristiOnSunday,{change("corpus",it)},subtitle = pick("Desactivado: jueves", "Off: Thursday", "Desativado: quinta-feira"),showDivider = false,switchTag = "transfer_corpus")
            }
            Spacer(Modifier.height(16.dp))
            Text(pick("Estos ajustes no incluyen todos los propios locales ni determinan los días de precepto. Confirma las normas con tu diócesis. Fuente del santoral general: Romcal (MIT), contrastado con calendarios publicados.", "These settings do not include all local propers or determine holy days of obligation. Confirm local rules with your diocese. General sanctoral source: Romcal (MIT), checked against published calendars.", "Estes ajustes não incluem todos os próprios locais nem determinam os dias de preceito. Confirme as normas com sua diocese. Fonte do santoral geral: Romcal (MIT), conferido com calendários publicados."),style = styles.footnote,color = p.textSecondary)
            if (status == PersonalDataCommands.Status.ERROR) {
                Text(pick("No se pudieron guardar los cambios.","Could not save changes.","Não foi possível guardar as alterações."),style = styles.body,color = p.textPrimary,modifier = Modifier.testTag("calendar_rules_save_error"))
                AppSecondaryCapsule(pick("Reintentar", "Retry", "Tentar novamente"), {
                    val retry = draft
                    PersonalDataCommands.submit("calendar-transfers") { store.setCalendarRules(retry) }
                })
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
