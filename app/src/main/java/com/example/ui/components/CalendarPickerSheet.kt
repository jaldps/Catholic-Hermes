package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.reminders.GoogleCalendarSync
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private sealed interface CalendarPickerState {
    data object Loading : CalendarPickerState
    data object Failed : CalendarPickerState
    data class Ready(val calendars: List<GoogleCalendarSync.WritableCalendar>, val previous: Long?) : CalendarPickerState
}

@Composable
fun CalendarPickerSheet(language:AppLanguage, existingEventId:Long?, onChoose:(Long)->Unit, onDismiss:()->Unit, summary:String? = null) {
    val context=LocalContext.current.applicationContext
    fun pick(es:String,en:String,pt:String)=when(language){AppLanguage.ES->es;AppLanguage.EN->en;AppLanguage.PT->pt}
    var attempt by remember {mutableIntStateOf(0)}
    var selected by rememberSaveable {mutableStateOf<Long?>(null)}
    val result by produceState<CalendarPickerState>(CalendarPickerState.Loading,attempt,existingEventId) {
        value = CalendarPickerState.Loading
        value = try {
            withContext(Dispatchers.IO) {
                CalendarPickerState.Ready(GoogleCalendarSync.writableCalendars(context), existingEventId?.let { GoogleCalendarSync.calendarForEvent(context,it) })
            }
        } catch(cancelled:kotlinx.coroutines.CancellationException) { throw cancelled }
        catch(error:Exception) { CalendarPickerState.Failed }
    }
    val loaded = result
    val p=LocalAppDesign.current.palette
    val styles=LocalAppTextStyles.current
    AppSheet(onDismiss=onDismiss,tag="calendar_picker_sheet") {
        LumenSheetHeader(pick("Elegir calendario","Choose calendar","Escolher calendário"),pick("Cancelar","Cancel","Cancelar"),onDismiss,"close_calendar_picker")
        Column(Modifier.padding(horizontal=20.dp)) {
            if(summary!=null) {
                Text(summary,style=styles.headline,color=p.textPrimary)
                Spacer(Modifier.height(12.dp))
            }
            Text(pick("Elige dónde guardar este recordatorio. Se usará la zona horaria actual del dispositivo.",
                "Choose where to save this reminder. It will use your device's current time zone.",
                "Escolha onde guardar este lembrete. Será usado o fuso horário atual do dispositivo."),style=styles.body,color=p.textPrimary)
            Spacer(Modifier.height(16.dp))
            if(loaded==CalendarPickerState.Loading) Text(pick("Cargando calendarios…","Loading calendars…","Carregando calendários…"),style=styles.body,color=p.textSecondary)
            else if(loaded==CalendarPickerState.Failed) {
                Text(pick("No se pudieron consultar los calendarios. Revisa el permiso de calendario.","Could not read calendars. Check calendar permission.","Não foi possível ler os calendários. Verifique a permissão de calendário."),style=styles.body,color=p.textPrimary)
                AppSecondaryCapsule(pick("Reintentar","Retry","Tentar novamente"),{attempt++})
            }else if (loaded is CalendarPickerState.Ready) {
                val (calendars,previous)=loaded
                if(calendars.isEmpty()) Text(pick("No hay calendarios con permiso de escritura. Añade uno en tu aplicación de calendario y vuelve a intentarlo.","No writable calendars. Add one in your calendar app, then try again.","Não há calendários com permissão de escrita. Adicione um no aplicativo de calendário e tente novamente."),style=styles.body,color=p.textPrimary)
                SheetGroup {
                    calendars.forEachIndexed {index,calendar ->
                        SheetChoiceRow(calendar.name.ifBlank {pick("Sin nombre","Unnamed","Sem nome")},calendar.account.takeIf {it.isNotBlank()},selected==calendar.id,
                            {selected=calendar.id},modifier=Modifier.testTag("calendar_option_${calendar.id}"),showDivider=index<calendars.lastIndex)
                    }
                }
                if(selected!=null && previous!=null && selected!=previous) {
                    Text(pick("El evento del calendario anterior se conservará. Se creará uno en el calendario elegido.",
                        "The event in the previous calendar will be kept. A new one will be created in your chosen calendar.",
                        "O evento no calendário anterior será mantido. Será criado um no calendário escolhido."),style=styles.footnote,color=p.textSecondary,modifier=Modifier.padding(top=12.dp))
                }
                Spacer(Modifier.height(16.dp))
                AppPrimaryCapsule(pick("Guardar recordatorio","Save reminder","Guardar lembrete"),{selected?.let(onChoose)},enabled=calendars.any {it.id==selected},tag="confirm_calendar_choice")
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
