package com.example.ui.screens

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.format.DateFormat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.NotificationsOff
import androidx.compose.material.icons.outlined.AlarmOff
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.data.models.AppLanguage
import com.example.data.reminders.DevotionReminder
import com.example.data.reminders.GoogleCalendarSync
import com.example.data.reminders.NovenaState
import com.example.data.reminders.ReminderCatalog
import com.example.data.reminders.ReminderPref
import com.example.data.reminders.ReminderScheduler
import com.example.data.reminders.ReminderStore
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppConfirmDialog
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSheet
import com.example.ui.design.AppSwitch
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Prayer reminders, iOS Settings grade: the Angelus art at the top, one
 * glass group of daily reminders sorted by time (large time, title, green
 * switch), a sheet per reminder (time wheel, notification preview, Google
 * Calendar), the active 9-day novena, and a test notification.
 *
 * Persistence is [ReminderStore] (DataStore, on device only). Toggling flips
 * the stored preference and (re)schedules or cancels the exact daily alarm
 * through [ReminderScheduler]; the first ON asks for POST_NOTIFICATIONS on
 * Android 13+. "Add to Google Calendar" writes one daily recurring event via
 * CalendarContract ([GoogleCalendarSync]) after READ/WRITE_CALENDAR, and a
 * second tap updates that event instead of adding a duplicate.
 */
@Composable
fun RemindersScreen(
    repository: CatholicDataRepository,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val insets = AppEdgeToEdge()
    AppBackHandler { onBack() }
    val lang by repository.currentLanguage.collectAsState()
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val app = context.applicationContext
    val scope = rememberCoroutineScope()
    val languageCode = lang.code
    val is24h = remember(context) { DateFormat.is24HourFormat(context) }

    val store = remember(app) { ReminderStore(app) }
    val prefs by store.reminders.collectAsState(initial = null)
    val novenaState by produceState<Pair<Boolean, NovenaState?>>(false to null, store) {
        store.novena.collect { value = true to it }
    }
    val calendarEvents by store.calendarEvents.collectAsState(initial = emptyMap())

    val title = lang.pick3("Recordatorios", "Reminders", "Lembretes")
    val fullTitle = lang.pick3("Recordatorios de oración", "Prayer reminders", "Lembretes de oração")
    AppInlineTitle(title)

    var toast by remember { mutableStateOf<String?>(null) }
    var sheetFor by remember { mutableStateOf<DevotionReminder?>(null) }
    var gcalPending by remember { mutableStateOf<DevotionReminder?>(null) }
    var gcalError by remember { mutableStateOf<Pair<DevotionReminder, String>?>(null) }
    var novenaName by remember { mutableStateOf("") }

    // Reminders that are ON by default must really be scheduled; idempotent.
    LaunchedEffect(languageCode) {
        withContext(Dispatchers.IO) { runCatching { ReminderScheduler.scheduleEnabled(app, languageCode, store) } }
    }

    // ---- permissions, re-checked on every resume (returning from Settings).
    var permissionTick by remember { mutableIntStateOf(0) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) permissionTick++
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    fun hasNotificationPermission(): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    fun hasExactAlarmPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return true
        val alarms = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return alarms.canScheduleExactAlarms()
    }
    fun hasCalendarPermissions(): Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED
    val notificationGranted = remember(permissionTick) { hasNotificationPermission() }
    val exactAlarmGranted = remember(permissionTick) { hasExactAlarmPermission() }

    fun timeOf(r: DevotionReminder): Pair<Int, Int> {
        val pref = prefs?.get(r.id)
        return (pref?.hour ?: r.defaultHour) to (pref?.minute ?: r.defaultMinute)
    }

    fun syncToCalendar(reminder: DevotionReminder, hour: Int, minute: Int) {
        val existing = calendarEvents[reminder.id]
        scope.launch(Dispatchers.IO) {
            val eventId = runCatching {
                GoogleCalendarSync.syncReminder(app, reminder, hour, minute, languageCode, existing)
            }.getOrNull()
            if (eventId != null) store.setCalendarEvent(reminder.id, eventId)
            withContext(Dispatchers.Main) {
                gcalPending = null
                if (eventId != null) {
                    toast = if (existing != null && existing == eventId) lang.pick3(
                        "«${reminder.title(languageCode)}» actualizado en tu calendario.",
                        "“${reminder.title(languageCode)}” updated in your calendar.",
                        "“${reminder.title(languageCode)}” atualizado no seu calendário."
                    ) else lang.pick3(
                        "«${reminder.title(languageCode)}» añadido a tu calendario.",
                        "“${reminder.title(languageCode)}” added to your calendar.",
                        "“${reminder.title(languageCode)}” adicionado ao seu calendário."
                    )
                } else {
                    gcalError = reminder to lang.pick3(
                        "No se pudo añadir «${reminder.title(languageCode)}». Revisa que tengas un calendario de Google disponible.",
                        "Could not add “${reminder.title(languageCode)}”. Check that a Google calendar is available.",
                        "Não foi possível adicionar “${reminder.title(languageCode)}”. Verifique se há um calendário do Google disponível."
                    )
                }
            }
        }
    }

    val notificationLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { permissionTick++ }
    val calendarLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
        val pending = gcalPending
        if (pending != null && granted[Manifest.permission.READ_CALENDAR] == true && granted[Manifest.permission.WRITE_CALENDAR] == true) {
            val (h, m) = timeOf(pending)
            syncToCalendar(pending, h, m)
        } else if (pending != null) {
            gcalError = pending to lang.pick3(
                "Se necesita permiso de calendario para añadir «${pending.title(languageCode)}».",
                "Calendar permission is needed to add “${pending.title(languageCode)}”.",
                "É necessária a permissão de calendário para adicionar “${pending.title(languageCode)}”."
            )
        }
    }

    fun onAddToCalendar(reminder: DevotionReminder, hour: Int, minute: Int) {
        if (hasCalendarPermissions()) {
            syncToCalendar(reminder, hour, minute)
        } else {
            gcalPending = reminder
            calendarLauncher.launch(arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR))
        }
    }

    fun onToggle(reminder: DevotionReminder, enabled: Boolean) {
        val (hour, minute) = timeOf(reminder)
        scope.launch {
            store.setEnabled(reminder.id, enabled)
            if (enabled) {
                ReminderScheduler.schedule(app, reminder, hour, minute, languageCode)
                toast = lang.pick3(
                    "${reminder.title(languageCode)} sonará a las ${clock(hour, minute, is24h, lang)}",
                    "${reminder.title(languageCode)} will ring at ${clock(hour, minute, is24h, lang)}",
                    "${reminder.title(languageCode)} vai tocar às ${clock(hour, minute, is24h, lang)}"
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission()) {
                    notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                ReminderScheduler.cancel(app, reminder, languageCode)
            }
        }
    }

    fun saveTime(reminder: DevotionReminder, hour: Int, minute: Int) {
        scope.launch {
            store.setTime(reminder.id, hour, minute)
            if (prefs?.get(reminder.id)?.enabled == true) {
                ReminderScheduler.schedule(app, reminder, hour, minute, languageCode)
                toast = lang.pick3(
                    "Sonará a las ${clock(hour, minute, is24h, lang)}",
                    "It will ring at ${clock(hour, minute, is24h, lang)}",
                    "Vai tocar às ${clock(hour, minute, is24h, lang)}"
                )
            }
        }
    }

    val sorted = remember(prefs) {
        ReminderCatalog.DEFAULTS.sortedBy { r ->
            val pref = prefs?.get(r.id)
            (pref?.hour ?: r.defaultHour) * 60 + (pref?.minute ?: r.defaultMinute)
        }
    }
    val enabledCount = prefs?.values?.count { it.enabled }
    val next = remember(prefs) { prefs?.let { nextReminder(it) } }
    val summary = when {
        enabledCount == null -> null
        enabledCount == 0 -> lang.pick3("Ningún recordatorio activo", "No reminders on", "Nenhum lembrete ativo")
        else -> {
            val active = lang.pick3(
                if (enabledCount == 1) "1 activo" else "$enabledCount activos",
                "$enabledCount on",
                if (enabledCount == 1) "1 ativo" else "$enabledCount ativos"
            )
            if (next != null) {
                val (r, t) = next
                "$active · " + lang.pick3("Próximo: ", "Next: ", "Próximo: ") +
                    shortTitle(r, languageCode) + ", " + clock(t.hour, t.minute, is24h, lang)
            } else active
        }
    }

    Box(modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().testTag("reminders_screen"),
            contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 24.dp)
        ) {
            item(key = "reminders_header") {
                Box(Modifier.semantics { contentDescription = fullTitle }) {
                    AppDetailHeader(
                        title = title,
                        assetPath = "heroes/lumen/angelus.jpg",
                        meta = summary,
                        imageHeight = 230.dp,
                        titleTag = "reminders_title"
                    )
                }
            }

            if (!notificationGranted || !exactAlarmGranted) {
                item(key = "permissions_status") {
                    Column(
                        Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .padding(bottom = design.spacing.sectionGap - 8.dp)
                            .lumenGlass(design.shapes.listGroup)
                            .testTag("reminders_status")
                    ) {
                        if (!notificationGranted) {
                            PermissionRow(
                                icon = Icons.Outlined.NotificationsOff,
                                title = lang.pick3("Notificaciones desactivadas", "Notifications are off", "Notificações desativadas"),
                                body = lang.pick3(
                                    "Los recordatorios no se verán hasta que las actives.",
                                    "Reminders won't be visible until you allow them.",
                                    "Os lembretes não serão visíveis até você permitir."
                                ),
                                action = lang.pick3("Activar", "Allow", "Ativar"),
                                actionDescription = lang.pick3("Abrir ajustes de notificaciones", "Open notification settings", "Abrir configurações de notificações"),
                                tag = "reminders_open_notification_settings",
                                onAction = {
                                    runCatching {
                                        context.startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                                        })
                                    }
                                }
                            )
                        }
                        if (!notificationGranted && !exactAlarmGranted) {
                            Box(Modifier.fillMaxWidth().padding(start = 56.dp).height(0.5.dp).background(p.separator))
                        }
                        if (!exactAlarmGranted) {
                            PermissionRow(
                                icon = Icons.Outlined.AlarmOff,
                                title = lang.pick3("Alarmas exactas desactivadas", "Exact alarms are off", "Alarmes exatos desativados"),
                                body = lang.pick3(
                                    "El sistema no permite alarmas exactas: la hora puede retrasarse.",
                                    "Exact alarms aren't allowed: the time may drift.",
                                    "Alarmes exatos não são permitidos: o horário pode atrasar."
                                ),
                                action = lang.pick3("Permitir", "Allow", "Permitir"),
                                actionDescription = lang.pick3("Permitir alarmas exactas", "Allow exact alarms", "Permitir alarmes exatos"),
                                tag = "reminders_allow_exact_alarms",
                                onAction = {
                                    runCatching {
                                        context.startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                                            data = Uri.parse("package:" + context.packageName)
                                        })
                                    }
                                }
                            )
                        }
                    }
                }
            }

            item(key = "reminders_list_header") {
                SectionTitle(lang.pick3("Recordatorios diarios", "Daily reminders", "Lembretes diários"))
            }
            item(key = "reminders_list") {
                AppListGroup(Modifier.testTag("reminders_list")) {
                    sorted.forEachIndexed { i, reminder ->
                        val pref = prefs?.get(reminder.id)
                        val (hour, minute) = timeOf(reminder)
                        ReminderRow(
                            reminder = reminder,
                            languageCode = languageCode,
                            time = clock(hour, minute, is24h, lang),
                            enabled = pref?.enabled ?: false,
                            loaded = prefs != null,
                            inCalendar = reminder.id in calendarEvents,
                            lang = lang,
                            onOpen = { sheetFor = reminder },
                            onToggle = { onToggle(reminder, it) },
                            showDivider = i < sorted.lastIndex
                        )
                    }
                }
                Footnote(lang.pick3(
                    "Toca un recordatorio para cambiar la hora o añadirlo a Google Calendar.",
                    "Tap a reminder to change its time or add it to Google Calendar.",
                    "Toque num lembrete para mudar o horário ou adicioná-lo ao Google Calendar."
                ))
            }

            item(key = "novena") {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .semantics { contentDescription = "Novena" }
                        .testTag("novena_section")
                ) {
                    SectionTitle("Novena")
                    val (loaded, active) = novenaState
                    if (loaded && active != null) {
                        ActiveNovena(active, lang, onFinish = { scope.launch { store.clearNovena() } })
                    } else if (loaded) {
                        Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                            GlassField(
                                value = novenaName,
                                onValueChange = { novenaName = it },
                                placeholder = lang.pick3("Nombre de la novena", "Novena name", "Nome da novena"),
                                tag = "novena_name",
                                singleLine = true
                            )
                            Spacer(Modifier.height(12.dp))
                            AppPrimaryCapsule(
                                label = lang.pick3("Iniciar novena", "Start novena", "Iniciar novena"),
                                onClick = {
                                    val name = novenaName.trim()
                                    if (name.isNotEmpty()) scope.launch { store.startNovena(name); novenaName = "" }
                                },
                                enabled = novenaName.isNotBlank(),
                                tag = "novena_start"
                            )
                        }
                        Footnote(lang.pick3(
                            "Lleva la cuenta de los nueve días desde hoy.",
                            "Keeps count of the nine days from today.",
                            "Conta os nove dias a partir de hoje."
                        ))
                    }
                }
            }

            item(key = "test") {
                SectionTitle(lang.pick3("Comprobar", "Check", "Verificar"))
                AppListGroup {
                    AppListRow(
                        title = lang.pick3("Enviar notificación de prueba", "Send test notification", "Enviar notificação de teste"),
                        leadingIcon = Icons.Outlined.Notifications,
                        onClick = {
                            val ok = runCatching {
                                ReminderScheduler.sendTestNotification(
                                    app,
                                    lang.pick3("Notificación de prueba", "Test notification", "Notificação de teste"),
                                    lang.pick3(
                                        "Así se verán tus recordatorios de oración.",
                                        "This is what your prayer reminders look like.",
                                        "É assim que seus lembretes de oração aparecerão."
                                    )
                                )
                            }.getOrDefault(false)
                            toast = if (ok) lang.pick3(
                                "Notificación de prueba enviada: revisa tu panel de notificaciones.",
                                "Test notification sent: check your notification panel.",
                                "Notificação de teste enviada: verifique o painel de notificações."
                            ) else lang.pick3(
                                "No se pudo enviar la notificación de prueba; revisa los permisos.",
                                "Could not send the test notification; check the permissions.",
                                "Não foi possível enviar a notificação de teste; verifique as permissões."
                            )
                        },
                        showsNavigationAffordance = false,
                        modifier = Modifier.testTag("test_notification")
                    )
                }
                Footnote(lang.pick3(
                    "Tus recordatorios se guardan solo en este dispositivo.",
                    "Your reminders are stored only on this device.",
                    "Os seus lembretes ficam guardados só neste dispositivo."
                ))
            }
        }

        AnimatedVisibility(
            visible = toast != null,
            enter = fadeIn(tween(200)) + slideInVertically(tween(260)) { it / 2 },
            exit = fadeOut(tween(200)) + slideOutVertically(tween(260)) { it / 2 },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = insets.calculateBottomPadding() + 16.dp)
        ) { GlassToast(toast.orEmpty()) }
        LaunchedEffect(toast) {
            if (toast != null) { delay(2600); toast = null }
        }
    }

    // ---- reminder sheet: time wheel, preview, calendar.
    val open = sheetFor
    if (open != null) {
        val (h0, m0) = timeOf(open)
        var hour by remember(open) { mutableIntStateOf(h0) }
        var minute by remember(open) { mutableIntStateOf(m0) }
        AppSheet(onDismiss = { sheetFor = null }, tag = "reminder_sheet") {
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(open.title(languageCode), style = styles.title2, color = p.textPrimary)
                Spacer(Modifier.height(2.dp))
                Text(lang.pick3("Cada día", "Every day", "Todos os dias"), style = styles.footnote, color = p.textSecondary)
                Spacer(Modifier.height(14.dp))
                TimeWheel(
                    hour = h0,
                    minute = m0,
                    is24h = is24h,
                    lang = lang,
                    onChange = { h, m -> hour = h; minute = m },
                    modifier = Modifier.testTag("reminder_time_wheel")
                )
                Spacer(Modifier.height(18.dp))
                Text(lang.pick3("Así se verá", "Preview", "Pré-visualização"), style = styles.footnote, color = p.textSecondary)
                Spacer(Modifier.height(8.dp))
                NotificationPreview(open.title(languageCode), open.body(languageCode), clock(hour, minute, is24h, lang))
                Spacer(Modifier.height(18.dp))
            }
            AppListGroup {
                AppListRow(
                    title = if (open.id in calendarEvents) lang.pick3("Actualizar en Google Calendar", "Update in Google Calendar", "Atualizar no Google Calendar")
                    else lang.pick3("Añadir a Google Calendar", "Add to Google Calendar", "Adicionar ao Google Calendar"),
                    subtitle = if (open.id in calendarEvents) lang.pick3("Ya está en tu calendario", "Already in your calendar", "Já está no seu calendário") else null,
                    leadingIcon = Icons.Outlined.CalendarMonth,
                    onClick = {
                        saveTime(open, hour, minute)
                        onAddToCalendar(open, hour, minute)
                    },
                    showsNavigationAffordance = false,
                    modifier = Modifier.testTag("gcal_${open.id}")
                )
            }
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Spacer(Modifier.height(18.dp))
                AppPrimaryCapsule(
                    label = lang.pick3("Guardar", "Save", "Salvar"),
                    onClick = {
                        saveTime(open, hour, minute)
                        sheetFor = null
                    },
                    tag = "reminder_save"
                )
                Spacer(Modifier.height(4.dp))
            }
        }
    }

    // ---- calendar failure / permission denied.
    val error = gcalError
    if (error != null) {
        AppConfirmDialog(
            title = "Google Calendar",
            message = error.second,
            confirmLabel = lang.pick3("Reintentar", "Try again", "Tentar de novo"),
            dismissLabel = lang.pick3("Cerrar", "Close", "Fechar"),
            onConfirm = {
                gcalError = null
                val (h, m) = timeOf(error.first)
                onAddToCalendar(error.first, h, m)
            },
            onDismiss = { gcalError = null },
            tag = "gcal_result"
        )
    }
}

// ------------------------------------------------------------------ rows

@Composable
private fun ReminderRow(
    reminder: DevotionReminder,
    languageCode: String,
    time: String,
    enabled: Boolean,
    loaded: Boolean,
    inCalendar: Boolean,
    lang: AppLanguage,
    onOpen: () -> Unit,
    onToggle: (Boolean) -> Unit,
    showDivider: Boolean
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val ink = if (enabled) p.textPrimary else p.textSecondary
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onOpen, 0.985f)
                .heightIn(min = 76.dp)
                .padding(start = 18.dp, end = 14.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = time,
                    style = styles.title1.copy(fontWeight = FontWeight.Light, fontFeatureSettings = "tnum"),
                    color = ink,
                    modifier = Modifier
                        .lumenPress(onOpen, 0.96f)
                        .semantics {
                            contentDescription = lang.pick3(
                                "Cambiar la hora del recordatorio", "Change the reminder time", "Alterar o horário do lembrete"
                            ) + ", $time"
                        }
                        .testTag("reminder_time_${reminder.id}")
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = reminder.title(languageCode),
                        style = styles.subhead,
                        color = p.textSecondary,
                        maxLines = 2,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    if (inCalendar) {
                        Spacer(Modifier.width(6.dp))
                        Icon(
                            Icons.Outlined.CalendarMonth,
                            contentDescription = lang.pick3("En Google Calendar", "In Google Calendar", "No Google Calendar"),
                            tint = p.textSecondary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.width(12.dp))
            Box(Modifier.size(width = 56.dp, height = 48.dp), contentAlignment = Alignment.Center) {
                if (loaded) {
                    AppSwitch(
                        checked = enabled,
                        onCheckedChange = onToggle,
                        modifier = Modifier.semantics {
                            contentDescription = if (enabled) lang.pick3("Desactivar recordatorio", "Disable reminder", "Desativar lembrete")
                            else lang.pick3("Activar recordatorio", "Enable reminder", "Ativar lembrete")
                        },
                        tag = "reminder_switch_${reminder.id}"
                    )
                }
            }
        }
        if (showDivider) Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
    }
}

@Composable
private fun PermissionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    body: String,
    action: String,
    actionDescription: String,
    tag: String,
    onAction: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        Modifier.fillMaxWidth().padding(start = 18.dp, end = 14.dp, top = 14.dp, bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = p.textPrimary, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = styles.headline, color = p.textPrimary)
            Spacer(Modifier.height(2.dp))
            Text(body, style = styles.footnote, color = p.textSecondary)
        }
        Spacer(Modifier.width(10.dp))
        Box(
            Modifier
                .heightIn(min = 44.dp)
                .lumenPress(onAction, 0.95f)
                .semantics { contentDescription = actionDescription }
                .testTag(tag),
            contentAlignment = Alignment.Center
        ) {
            Text(
                action,
                style = styles.subhead.copy(fontWeight = FontWeight.SemiBold),
                color = p.onAction,
                modifier = Modifier
                    .background(p.action, RoundedCornerShape(percent = 50))
                    .padding(horizontal = 14.dp, vertical = 7.dp)
            )
        }
    }
}

@Composable
private fun ActiveNovena(novena: NovenaState, lang: AppLanguage, onFinish: () -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val todayEpoch = LocalDate.now().toEpochDay()
    val day = novena.dayNumber(todayEpoch)
    val complete = novena.isComplete(todayEpoch)
    val dayLabel = lang.pick3("Día $day de 9", "Day $day of 9", "Dia $day de 9")
    Column(
        Modifier
            .padding(horizontal = design.spacing.gutter)
            .lumenGlass(design.shapes.card)
            .padding(20.dp)
    ) {
        Text(lang.pick3("Novena en curso", "Novena in progress", "Novena em curso"), style = styles.eyebrow, color = p.textSecondary)
        Spacer(Modifier.height(2.dp))
        Text(novena.name, style = styles.title2, color = p.textPrimary)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (complete) {
                Icon(Icons.Outlined.Check, null, tint = p.success, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
            }
            Text(
                text = if (complete) lang.pick3("Completada", "Completed", "Concluída") else dayLabel,
                style = styles.subhead,
                color = p.textSecondary,
                modifier = Modifier.semantics { stateDescription = dayLabel }
            )
        }
        Spacer(Modifier.height(14.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(9) { i ->
                val done = complete || i < day - 1
                val isToday = !complete && i == day - 1
                Box(
                    Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(
                            when {
                                done -> p.success
                                isToday -> p.success.copy(alpha = 0.45f)
                                else -> p.textPrimary.copy(alpha = if (design.isDark) 0.12f else 0.08f)
                            }
                        )
                )
            }
        }
        Spacer(Modifier.height(18.dp))
        AppSecondaryCapsule(
            label = lang.pick3("Terminar novena", "Finish novena", "Encerrar novena"),
            onClick = onFinish,
            fillWidth = true,
            tag = "novena_finish"
        )
    }
}

@Composable
private fun NotificationPreview(title: String, body: String, time: String) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        Modifier
            .fillMaxWidth()
            .lumenGlass(RoundedCornerShape(20.dp), strong = true)
            .padding(14.dp)
            .semantics(mergeDescendants = true) { },
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier.size(34.dp).clip(RoundedCornerShape(9.dp)).background(p.action),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Notifications, null, tint = p.onAction, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Row {
                Text(title, style = styles.subhead.copy(fontWeight = FontWeight.SemiBold), color = p.textPrimary, modifier = Modifier.weight(1f), maxLines = 1)
                Text(time, style = styles.caption, color = p.textSecondary)
            }
            Spacer(Modifier.height(2.dp))
            Text(body, style = styles.subhead, color = p.textSecondary)
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    Text(
        text,
        style = styles.title3,
        color = design.palette.textPrimary,
        modifier = Modifier.padding(start = design.spacing.gutter, end = design.spacing.gutter, top = 8.dp, bottom = 10.dp)
    )
}

@Composable
private fun Footnote(text: String) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.footnote,
        color = design.palette.textSecondary,
        modifier = Modifier.padding(start = design.spacing.gutter + 18.dp, end = design.spacing.gutter + 8.dp, top = 8.dp, bottom = design.spacing.sectionGap - 8.dp)
    )
}

// ------------------------------------------------------------------ wheel

private val WheelItem = 40.dp
private const val WheelRepeat = 200

/**
 * iOS-style time wheel: hour and minute columns (plus AM/PM in 12 h) that
 * snap, over a glass selection band, fading at the edges. Each column is an
 * adjustable value for TalkBack (custom increase / decrease actions).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TimeWheel(
    hour: Int,
    minute: Int,
    is24h: Boolean,
    lang: AppLanguage,
    onChange: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    var h by remember { mutableIntStateOf(hour) }
    var m by remember { mutableIntStateOf(minute) }
    Box(modifier.fillMaxWidth().height(WheelItem * 5), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(WheelItem)
                .lumenGlass(RoundedCornerShape(12.dp), strong = true, lifted = false)
        )
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            if (is24h) {
                WheelColumn(
                    values = (0..23).toList(),
                    initial = hour,
                    label = { "%02d".format(it) },
                    description = lang.pick3("Hora", "Hour", "Hora"),
                    unit = lang.pick3("horas", "hours", "horas"),
                    onSelect = { h = it; onChange(h, m) },
                    lang = lang
                )
            } else {
                val h12 = if (hour % 12 == 0) 12 else hour % 12
                WheelColumn(
                    values = (1..12).toList(),
                    initial = h12,
                    label = { it.toString() },
                    description = lang.pick3("Hora", "Hour", "Hora"),
                    unit = lang.pick3("horas", "hours", "horas"),
                    onSelect = { v ->
                        val pm = h >= 12
                        h = (v % 12) + if (pm) 12 else 0
                        onChange(h, m)
                    },
                    lang = lang
                )
            }
            Text(":", style = LocalAppTextStyles.current.title2, color = p.textPrimary, modifier = Modifier.padding(horizontal = 2.dp))
            WheelColumn(
                values = (0..59).toList(),
                initial = minute,
                label = { "%02d".format(it) },
                description = lang.pick3("Minutos", "Minutes", "Minutos"),
                unit = lang.pick3("minutos", "minutes", "minutos"),
                onSelect = { m = it; onChange(h, m) },
                lang = lang
            )
            if (!is24h) {
                Spacer(Modifier.width(8.dp))
                WheelColumn(
                    values = listOf(0, 1),
                    initial = if (hour >= 12) 1 else 0,
                    label = { if (it == 0) "AM" else "PM" },
                    description = "AM/PM",
                    unit = "",
                    wrap = false,
                    onSelect = { v ->
                        val base = h % 12
                        h = base + if (v == 1) 12 else 0
                        onChange(h, m)
                    },
                    lang = lang
                )
            }
        }
        // Fade the outer rows into the sheet.
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0f to p.canvasTop,
                    0.3f to p.canvasTop.copy(alpha = 0f),
                    0.7f to p.canvasBottom.copy(alpha = 0f),
                    1f to p.canvasBottom
                )
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WheelColumn(
    values: List<Int>,
    initial: Int,
    label: (Int) -> String,
    description: String,
    unit: String,
    onSelect: (Int) -> Unit,
    lang: AppLanguage,
    wrap: Boolean = true,
    width: Dp = 76.dp
) {
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    val n = values.size
    val count = if (wrap) n * WheelRepeat else n
    val startIndex = (if (wrap) n * (WheelRepeat / 2) else 0) + values.indexOf(initial).coerceAtLeast(0)
    val state = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val fling = rememberSnapFlingBehavior(lazyListState = state)
    val scope = rememberCoroutineScope()
    val itemPx = with(LocalDensity.current) { WheelItem.toPx() }
    fun centered(): Int = state.firstVisibleItemIndex + if (state.firstVisibleItemScrollOffset > itemPx / 2) 1 else 0
    var current by remember { mutableIntStateOf(values[startIndex % n]) }
    LaunchedEffect(state) {
        snapshotFlow { centered() }.distinctUntilChanged().collect { idx ->
            val v = values[idx.coerceIn(0, count - 1) % n]
            current = v
            onSelect(v)
        }
    }
    LazyColumn(
        state = state,
        flingBehavior = fling,
        contentPadding = PaddingValues(vertical = WheelItem * 2),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(width)
            .height(WheelItem * 5)
            .semantics {
                contentDescription = description
                stateDescription = "${label(current)} $unit".trim()
                customActions = listOf(
                    CustomAccessibilityAction(lang.pick3("Aumentar", "Increase", "Aumentar")) {
                        scope.launch { state.animateScrollToItem((centered() + 1).coerceAtMost(count - 1)) }; true
                    },
                    CustomAccessibilityAction(lang.pick3("Disminuir", "Decrease", "Diminuir")) {
                        scope.launch { state.animateScrollToItem((centered() - 1).coerceAtLeast(0)) }; true
                    }
                )
            }
    ) {
        items(count) { i ->
            Box(Modifier.height(WheelItem).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = label(values[i % n]),
                    style = styles.title2.copy(fontFeatureSettings = "tnum"),
                    color = p.textPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// ------------------------------------------------------------------ helpers

/** "6:00" / "06:00" in 24 h, "6:00 AM" in 12 h. */
internal fun clock(hour: Int, minute: Int, is24h: Boolean, lang: AppLanguage): String =
    LocalTime.of(hour.coerceIn(0, 23), minute.coerceIn(0, 59))
        .format(DateTimeFormatter.ofPattern(if (is24h) "H:mm" else "h:mm a", calendarLocale(lang)))

/** The next enabled reminder to ring from now, with its time. */
private fun nextReminder(prefs: Map<String, ReminderPref>): Pair<DevotionReminder, LocalTime>? {
    val now = LocalTime.now()
    val enabled = ReminderCatalog.DEFAULTS.mapNotNull { r ->
        val pref = prefs[r.id] ?: return@mapNotNull null
        if (!pref.enabled) null else r to LocalTime.of(pref.hour, pref.minute)
    }
    if (enabled.isEmpty()) return null
    return enabled.filter { it.second.isAfter(now) }.minByOrNull { it.second }
        ?: enabled.minByOrNull { it.second }
}

/** Short form for the header line ("Coronilla" instead of the full title). */
private fun shortTitle(r: DevotionReminder, languageCode: String): String =
    if (r.id == "coronilla") when (languageCode) {
        "en" -> "Chaplet"
        "pt" -> "Coronilha"
        else -> "Coronilla"
    } else r.title(languageCode)
