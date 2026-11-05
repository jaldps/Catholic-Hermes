package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.data.models.AppLanguage
import com.example.data.reminders.DevotionReminder
import com.example.data.reminders.GoogleCalendarSync
import com.example.data.reminders.ReminderCatalog
import com.example.data.reminders.ReminderScheduler
import com.example.data.reminders.ReminderStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

private val MinTouchTarget = 48.dp

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/**
 * Daily devotion reminders + the active 9-day novena.
 *
 * Persistence is [ReminderStore] (DataStore). Toggling a reminder flips the
 * stored preference and (re)schedules / cancels the exact daily alarm through
 * [ReminderScheduler]; the first ON-toggle also asks for POST_NOTIFICATIONS
 * on Android 13+ so the fired alarm can actually show a notification.
 *
 * Each row also offers a "add to Google Calendar" action that writes one
 * daily recurring event through Android's CalendarContract
 * ([GoogleCalendarSync]) after READ/WRITE_CALENDAR are granted at runtime.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    repository: CatholicDataRepository,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val currentLanguage by repository.currentLanguage.collectAsState()
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val store = remember { ReminderStore(context.applicationContext) }
    val prefs by store.reminders.collectAsState(initial = emptyMap())
    val novena by store.novena.collectAsState(initial = null)

    val snackbarHostState = remember { SnackbarHostState() }

    val title = currentLanguage.pick(
        "Recordatorios de oración",
        "Prayer reminders",
        "Lembretes de oração"
    )
    val backDescription = currentLanguage.pick("Atrás", "Back", "Voltar")

    // Reminder whose time picker is open, if any.
    var timePickerFor by remember { mutableStateOf<DevotionReminder?>(null) }
    // Reminder the calendar permission request belongs to, if any.
    var gcalPending by remember { mutableStateOf<DevotionReminder?>(null) }
    // Result of a calendar sync, shown as a dialog.
    var gcalDialog by remember { mutableStateOf<Pair<Boolean, String>?>(null) }
    var novenaName by remember { mutableStateOf("") }

    val languageCode = currentLanguage.code
    val todayEpochDay = LocalDate.now().toEpochDay()

    fun showMessage(message: String) {
        scope.launch { snackbarHostState.showSnackbar(message) }
    }

    fun hasNotificationPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return true
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun hasCalendarPermissions(): Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) ==
            PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) ==
            PackageManager.PERMISSION_GRANTED

    fun syncToCalendar(reminder: DevotionReminder, hour: Int, minute: Int) {
        scope.launch(Dispatchers.IO) {
            val ok = runCatching {
                GoogleCalendarSync.syncReminder(
                    context.applicationContext, reminder, hour, minute, languageCode
                )
            }.getOrDefault(false)
            val message = when {
                ok -> currentLanguage.pick(
                    "«${reminder.title(languageCode)}» añadido a tu calendario.",
                    "\"${reminder.title(languageCode)}\" added to your calendar.",
                    "\"${reminder.title(languageCode)}\" adicionado ao seu calendário."
                )
                else -> currentLanguage.pick(
                    "No se pudo añadir «${reminder.title(languageCode)}». Revisa que tengas un calendario de Google disponible.",
                    "Could not add \"${reminder.title(languageCode)}\". Check that a Google calendar is available.",
                    "Não foi possível adicionar \"${reminder.title(languageCode)}\". Verifique se há um calendário do Google disponível."
                )
            }
            withContext(Dispatchers.Main) {
                if (ok) {
                    showMessage(message)
                    gcalPending = null
                } else {
                    gcalDialog = ok to message
                }
            }
        }
    }

    // Android 13+ notification permission. The alarm is scheduled regardless;
    // the permission only decides whether the fired alarm can show a
    // notification. Requested once per toggle-ON (no-op when already granted).
    val notificationLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* granted or not, the alarm is already scheduled */ }

    fun onToggle(reminder: DevotionReminder, enabled: Boolean) {
        val pref = prefs[reminder.id]
        val hour = pref?.hour ?: reminder.defaultHour
        val minute = pref?.minute ?: reminder.defaultMinute
        scope.launch {
            store.setEnabled(reminder.id, enabled)
            if (enabled) {
                ReminderScheduler.schedule(
                    context.applicationContext, reminder, hour, minute, languageCode
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    !hasNotificationPermission()
                ) {
                    notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                ReminderScheduler.cancel(context.applicationContext, reminder, languageCode)
            }
        }
    }

    // READ + WRITE calendar permission for the Google Calendar action.
    val calendarLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { granted ->
        val pending = gcalPending
        if (pending != null &&
            granted[Manifest.permission.READ_CALENDAR] == true &&
            granted[Manifest.permission.WRITE_CALENDAR] == true
        ) {
            val pref = prefs[pending.id]
            syncToCalendar(
                pending,
                pref?.hour ?: pending.defaultHour,
                pref?.minute ?: pending.defaultMinute
            )
        } else if (pending != null) {
            gcalDialog = false to currentLanguage.pick(
                "Se necesita permiso de calendario para añadir «${pending.title(languageCode)}».",
                "Calendar permission is needed to add \"${pending.title(languageCode)}\".",
                "É necessária a permissão de calendário para adicionar \"${pending.title(languageCode)}\"."
            )
        }
    }

    fun onAddToCalendar(reminder: DevotionReminder) {
        if (hasCalendarPermissions()) {
            val pref = prefs[reminder.id]
            syncToCalendar(
                reminder,
                pref?.hour ?: reminder.defaultHour,
                pref?.minute ?: reminder.defaultMinute
            )
        } else {
            gcalPending = reminder
            calendarLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR
                )
            )
        }
    }

    val novenaSectionLabel = currentLanguage.pick("Novena", "Novena", "Novena")
    val novenaNameLabel = currentLanguage.pick(
        "Nombre de la novena",
        "Novena name",
        "Nome da novena"
    )
    val startNovenaLabel = currentLanguage.pick(
        "Iniciar novena",
        "Start novena",
        "Iniciar novena"
    )
    val finishNovenaLabel = currentLanguage.pick(
        "Terminar/archivar",
        "Finish/archive",
        "Encerrar/arquivar"
    )
    val completedLabel = currentLanguage.pick("Completada", "Completed", "Concluída")
    val remindersHeaderLabel = currentLanguage.pick(
        "Recordatorios diarios",
        "Daily reminders",
        "Lembretes diários"
    )
    val addToCalendarLabel = currentLanguage.pick(
        "Añadir a Google Calendar",
        "Add to Google Calendar",
        "Adicionar ao Google Calendar"
    )
    val changeTimeDescription = currentLanguage.pick(
        "Cambiar la hora del recordatorio",
        "Change the reminder time",
        "Alterar o horário do lembrete"
    )
    val toggleOnDescription = currentLanguage.pick(
        "Activar recordatorio",
        "Enable reminder",
        "Ativar lembrete"
    )
    val toggleOffDescription = currentLanguage.pick(
        "Desactivar recordatorio",
        "Disable reminder",
        "Desativar lembrete"
    )
    val okLabel = currentLanguage.pick("Aceptar", "OK", "OK")
    val cancelLabel = currentLanguage.pick("Cancelar", "Cancel", "Cancelar")
    val closeLabel = currentLanguage.pick("Cerrar", "Close", "Fechar")
    val timePickerTitle = currentLanguage.pick("Hora del recordatorio", "Reminder time", "Horário do lembrete")

    val activeNovena = novena

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("reminders_screen"),
            verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
        ) {
            // ---- Header: back affordance + trilingual title ----
            item(key = "reminders_header") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(MinTouchTarget)
                            .semantics { contentDescription = backDescription }
                            .testTag("reminders_back")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = backDescription
                        )
                    }
                    Text(
                        text = title,
                        style = styles.screenTitle,
                        modifier = Modifier
                            .weight(1f)
                            .semantics { contentDescription = title }
                    )
                }
            }

            // ---- Novena section ----
            item(key = "novena") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("novena_section")
                        .semantics { contentDescription = novenaSectionLabel }
                ) {
                    AppSectionHeader(title = novenaSectionLabel)
                    AppCard(modifier = Modifier.fillMaxWidth()) {
                        if (activeNovena == null) {
                            OutlinedTextField(
                                value = novenaName,
                                onValueChange = { novenaName = it },
                                label = { Text(novenaNameLabel) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = MinTouchTarget)
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            Button(
                                onClick = {
                                    val name = novenaName.trim()
                                    if (name.isNotEmpty()) {
                                        scope.launch {
                                            store.startNovena(name)
                                            novenaName = ""
                                        }
                                    }
                                },
                                enabled = novenaName.trim().isNotEmpty(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("novena_start")
                            ) {
                                Text(startNovenaLabel)
                            }
                        } else {
                            val dayNumber = activeNovena.dayNumber(todayEpochDay)
                            val isComplete = activeNovena.isComplete(todayEpochDay)
                            val dayLabel = currentLanguage.pick(
                                "Día $dayNumber de 9",
                                "Day $dayNumber of 9",
                                "Dia $dayNumber de 9"
                            )
                            Text(text = activeNovena.name, style = styles.sectionTitle)
                            Text(
                                text = if (isComplete) completedLabel else dayLabel,
                                style = styles.rowSubtitle,
                                modifier = Modifier.semantics { stateDescription = dayLabel }
                            )
                            Spacer(Modifier.height(design.spacing.cardGap))
                            LinearProgressIndicator(
                                progress = { (dayNumber / 9f).coerceIn(0f, 1f) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                            )
                            if (isComplete) {
                                Spacer(Modifier.height(design.spacing.cardGap))
                                Text(text = completedLabel, style = styles.rowSubtitle)
                            }
                            Spacer(Modifier.height(design.spacing.cardGap))
                            OutlinedButton(
                                onClick = { scope.launch { store.clearNovena() } },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = MinTouchTarget)
                                    .testTag("novena_finish")
                            ) {
                                Text(finishNovenaLabel)
                            }
                        }
                    }
                }
            }

            // ---- Reminders list ----
            item(key = "reminders_header") {
                AppSectionHeader(
                    title = remindersHeaderLabel,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter)
                )
            }
            items(ReminderCatalog.DEFAULTS, key = { it.id }) { reminder ->
                val pref = prefs[reminder.id]
                val enabled = pref?.enabled ?: false
                val hour = pref?.hour ?: reminder.defaultHour
                val minute = pref?.minute ?: reminder.defaultMinute
                val timeLabel = "%02d:%02d".format(hour, minute)

                AppCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = reminder.title(languageCode), style = styles.rowTitle)
                            Text(text = reminder.body(languageCode), style = styles.rowSubtitle)
                        }
                        TextButton(
                            onClick = { timePickerFor = reminder },
                            modifier = Modifier
                                .heightIn(min = MinTouchTarget)
                                .testTag("reminder_time_${reminder.id}")
                                .semantics { contentDescription = changeTimeDescription }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Schedule,
                                contentDescription = changeTimeDescription,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.size(4.dp))
                            Text(timeLabel)
                        }
                        Switch(
                            checked = enabled,
                            onCheckedChange = { onToggle(reminder, it) },
                            modifier = Modifier
                                .size(MinTouchTarget)
                                .testTag("reminder_switch_${reminder.id}")
                                .semantics {
                                    contentDescription = if (enabled) toggleOffDescription else toggleOnDescription
                                }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = design.spacing.cardGap),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AppSecondaryButton(
                            label = addToCalendarLabel,
                            onClick = { onAddToCalendar(reminder) },
                            modifier = Modifier
                                .heightIn(min = MinTouchTarget)
                                .testTag("gcal_${reminder.id}")
                        )
                    }
                }
            }
        }
    }

    // ---- Time picker dialog ----
    // Hoisted above the conditional: rememberTimePickerState is remembered
    // unconditionally inside key(pickerReminder) so the slot table stays
    // stable, and keying on the reminder recreates it per-reminder so the
    // initial hour/minute match the reminder being edited.
    val pickerReminder = timePickerFor
    key(pickerReminder) {
        val pickerPref = pickerReminder?.let { prefs[it.id] }
        val timePickerState = rememberTimePickerState(
            initialHour = pickerReminder?.let { pickerPref?.hour ?: it.defaultHour } ?: 0,
            initialMinute = pickerReminder?.let { pickerPref?.minute ?: it.defaultMinute } ?: 0,
            is24Hour = true
        )
        if (pickerReminder != null) {
            AlertDialog(
            onDismissRequest = { timePickerFor = null },
            confirmButton = {
                TextButton(
                    onClick = {
                        val hour = timePickerState.hour
                        val minute = timePickerState.minute
                        scope.launch {
                            store.setTime(pickerReminder.id, hour, minute)
                            if ((prefs[pickerReminder.id]?.enabled) == true) {
                                ReminderScheduler.schedule(
                                    context.applicationContext,
                                    pickerReminder,
                                    hour,
                                    minute,
                                    languageCode
                                )
                            }
                            timePickerFor = null
                        }
                    },
                    modifier = Modifier.heightIn(min = MinTouchTarget)
                ) {
                    Text(okLabel)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { timePickerFor = null },
                    modifier = Modifier.heightIn(min = MinTouchTarget)
                ) {
                    Text(cancelLabel)
                }
            },
                text = {
                    Column {
                        Text(
                            text = timePickerTitle,
                            style = styles.sectionTitle,
                            modifier = Modifier.padding(bottom = design.spacing.cardGap)
                        )
                        TimePicker(state = timePickerState)
                    }
                }
            )
        }
    }

    // ---- Calendar result dialog (failure / denied only; success is a snackbar) ----
    val dialog = gcalDialog
    if (dialog != null) {
        AlertDialog(
            onDismissRequest = { gcalDialog = null },
            confirmButton = {
                TextButton(
                    onClick = { gcalDialog = null },
                    modifier = Modifier.heightIn(min = MinTouchTarget)
                ) {
                    Text(closeLabel)
                }
            },
            text = { Text(dialog.second) }
        )
    }
}
