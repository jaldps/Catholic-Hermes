package com.example.ui.screens

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.CalendarRules
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.models.AppLanguage
import com.example.data.notices.NoticeContext
import com.example.data.notices.NoticeGroup
import com.example.data.notices.NoticeMessage
import com.example.data.notices.NoticeScheduler
import com.example.data.notices.NoticeSelector
import com.example.data.notices.NoticeSettings
import com.example.data.notices.NoticeStore
import com.example.data.notices.NoticeType
import com.example.data.notices.NoticesCatalog
import com.example.data.notices.NovenaNoticeData
import com.example.data.notices.ObligationDays
import com.example.data.notices.PlanNoticeData
import com.example.data.persistence.PersonalDataCommands
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppSheet
import com.example.ui.design.AppSwitch
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

/** Name and cadence of each notice, as the Reminders screen lists them. */
internal fun noticeTitle(type: NoticeType, lang: AppLanguage): String = when (type) {
    NoticeType.SEASON_START -> lang.pick3("Comienzo de cada tiempo", "Start of each season", "Início de cada tempo")
    NoticeType.SEASON_WEEKLY -> lang.pick3("Propuesta de la semana", "This week's practice", "Proposta da semana")
    NoticeType.FEAST_EVE -> lang.pick3("Víspera de la fiesta", "Eve of the feast", "Véspera da festa")
    NoticeType.FEAST_DAY -> lang.pick3("Día de la fiesta", "Day of the feast", "Dia da festa")
    NoticeType.SUNDAY_MASS -> lang.pick3("Misa del domingo", "Sunday Mass", "Missa do domingo")
    NoticeType.HOLY_DAY_OF_OBLIGATION -> lang.pick3("Días de precepto", "Holy days of obligation", "Dias de preceito")
    NoticeType.NOVENA_DAY -> lang.pick3("Día de la novena", "Novena day", "Dia da novena")
    NoticeType.PLAN_DAY -> lang.pick3("Día del plan de lectura", "Reading plan day", "Dia do plano de leitura")
    NoticeType.ROSARY_EVENING -> lang.pick3("Rosario de la tarde", "Evening Rosary", "Terço da tarde")
    NoticeType.EXAMEN_NIGHT -> lang.pick3("Examen de la noche", "Night examination", "Exame da noite")
    NoticeType.CONFESSION_GENTLE -> lang.pick3("Un gesto de vuelta", "A gesture of return", "Um gesto de volta")
}

internal fun noticeCadence(type: NoticeType, lang: AppLanguage): String = when (type) {
    NoticeType.SEASON_START -> lang.pick3("El primer día", "On the first day", "No primeiro dia")
    NoticeType.SEASON_WEEKLY -> lang.pick3("Los domingos", "On Sundays", "Aos domingos")
    NoticeType.FEAST_EVE -> lang.pick3("La tarde anterior", "The evening before", "Na tarde anterior")
    NoticeType.FEAST_DAY -> lang.pick3("Ese mismo día", "On the day", "No próprio dia")
    NoticeType.SUNDAY_MASS -> lang.pick3("Los sábados", "On Saturdays", "Aos sábados")
    NoticeType.HOLY_DAY_OF_OBLIGATION -> lang.pick3("La víspera de los días que elijas", "The eve of the days you choose", "Na véspera dos dias que escolher")
    NoticeType.NOVENA_DAY -> lang.pick3("Cada día, si hay una novena en curso", "Daily, while a novena is in progress", "Cada dia, se houver uma novena em curso")
    NoticeType.PLAN_DAY -> lang.pick3("Cada día, si hay un plan en curso", "Daily, while a plan is in progress", "Cada dia, se houver um plano em curso")
    NoticeType.ROSARY_EVENING, NoticeType.EXAMEN_NIGHT -> lang.pick3("Cada día", "Every day", "Todos os dias")
    NoticeType.CONFESSION_GENTLE -> lang.pick3("Una vez al mes, el primer sábado", "Once a month, on the first Saturday", "Uma vez por mês, no primeiro sábado")
}

private fun groupTitle(group: NoticeGroup, lang: AppLanguage) = when (group) {
    NoticeGroup.SEASON -> lang.pick3("Tiempo litúrgico", "Liturgical season", "Tempo litúrgico")
    NoticeGroup.FEASTS -> lang.pick3("Fiestas", "Feasts", "Festas")
    NoticeGroup.REMINDERS -> lang.pick3("Recordatorios", "Reminders", "Lembretes")
}

private fun groupNote(group: NoticeGroup, lang: AppLanguage) = when (group) {
    NoticeGroup.SEASON -> lang.pick3(
        "Adviento, Navidad, Cuaresma, Semana Santa, Triduo, Pascua y Tiempo Ordinario, con una práctica sencilla.",
        "Advent, Christmas, Lent, Holy Week, the Triduum, Easter and Ordinary Time, each with a simple practice.",
        "Advento, Natal, Quaresma, Semana Santa, Tríduo, Páscoa e Tempo Comum, com uma prática simples.")
    NoticeGroup.FEASTS -> lang.pick3(
        "Solemnidades y fiestas del Calendario Romano General, con tus traslados. Si la liturgia impide una fiesta, no hay aviso.",
        "Solemnities and feasts of the General Roman Calendar, with your transfers. If the liturgy impedes a feast, there is no notice.",
        "Solenidades e festas do Calendário Romano Geral, com as suas transferências. Se a liturgia impede uma festa, não há aviso.")
    NoticeGroup.REMINDERS -> lang.pick3(
        "Los avisos no muestran tus intenciones ni datos personales.",
        "Notices never show your intentions or personal details.",
        "Os avisos não mostram as suas intenções nem dados pessoais.")
}

/** An example of what [type] says, for the sheet's preview: its next occurrence from today. */
private fun example(type: NoticeType, settings: NoticeSettings, lang: AppLanguage): NoticeMessage? {
    val notices = NoticesCatalog.default.load() ?: return null
    val sample = NoticeContext(CalendarRules(), settings.obligationDays.ifEmpty { ObligationDays.KEYS },
        NovenaNoticeData(com.example.data.models.LocalizedText("San José", "Saint Joseph", "São José"), 3),
        PlanNoticeData(com.example.data.models.LocalizedText("La Biblia en un año", "The Bible in a year", "A Bíblia em um ano"), 12))
    val from = LiturgicalCalendar.today()
    val date = NoticeSelector.nextOccurrence(type, from, notices, sample) ?: return null
    return NoticeSelector.select(type, date, notices, sample, lang)
}

/**
 * The liturgical notices of the Reminders screen, in three groups (Tiempo litúrgico · Fiestas · Recordatorios). Each
 * type has its switch and its time; all start OFF. Turning one on asks for the notification permission through
 * [onFirstEnable] (Android 13+) and schedules its single alarm. The holy-days row asks which days apply, because the
 * app never decides them.
 */
@Composable
internal fun NoticeSettingsSection(
    lang: AppLanguage,
    onFirstEnable: () -> Unit,
    onSaved: (String) -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val app = context.applicationContext
    val store = remember(app) { NoticeStore(app) }
    val settings by store.settings.collectAsState(initial = null)
    val is24h = remember(context) { DateFormat.is24HourFormat(context) }
    var sheetFor by remember { mutableStateOf<NoticeType?>(null) }
    var obligationSheet by remember { mutableStateOf(false) }

    fun reschedule(write: suspend () -> Unit, message: String) {
        PersonalDataCommands.submit("reminders") {
            write()
            withTimeout(10_000) { NoticeScheduler.scheduleEnabled(app, store) }
            withContext(Dispatchers.Main) { onSaved(message) }
        }
    }

    fun toggle(type: NoticeType, enabled: Boolean) {
        val current = settings ?: return
        if (!current.available) return
        if (enabled) onFirstEnable()
        if (enabled && type == NoticeType.HOLY_DAY_OF_OBLIGATION && current.obligationDays.isEmpty()) obligationSheet = true
        reschedule({ store.setEnabled(type, enabled) }, lang.pick3("Aviso actualizado.", "Notice updated.", "Aviso atualizado."))
    }

    Column(Modifier.fillMaxWidth().testTag("notice_settings")) {
        NoticeGroup.entries.forEach { group ->
            Text(groupTitle(group, lang), style = styles.title3, color = p.textPrimary,
                modifier = Modifier.padding(start = design.spacing.gutter, end = design.spacing.gutter, top = 8.dp, bottom = 10.dp))
            val types = NoticeType.entries.filter { it.group == group }
            AppListGroup(Modifier.testTag("notice_group_${group.name.lowercase()}")) {
                types.forEachIndexed { i, type ->
                    val pref = settings?.pref(type)
                    val time = clock(pref?.hour ?: type.defaultHour, pref?.minute ?: type.defaultMinute, is24h, lang)
                    val cadence = if (type == NoticeType.HOLY_DAY_OF_OBLIGATION) {
                        val chosen = settings?.obligationDays.orEmpty().size
                        (if (chosen == 0) lang.pick3("Elige los días de tu país", "Choose the days in your country", "Escolha os dias do seu país")
                        else lang.pick3("$chosen ${if (chosen == 1) "día elegido" else "días elegidos"}", "$chosen ${if (chosen == 1) "day" else "days"} chosen",
                            "$chosen ${if (chosen == 1) "dia escolhido" else "dias escolhidos"}")) + " · " +
                            lang.pick3("depende de tu conferencia episcopal", "set by your bishops' conference", "depende da sua conferência episcopal")
                    } else noticeCadence(type, lang)
                    NoticeRow(
                        title = noticeTitle(type, lang),
                        subtitle = "$time · $cadence",
                        enabled = pref?.enabled == true,
                        loaded = settings?.available == true,
                        lang = lang,
                        tag = type.id,
                        onOpen = {
                            if (settings?.available != true) return@NoticeRow
                            if (type == NoticeType.HOLY_DAY_OF_OBLIGATION && pref?.enabled == true) obligationSheet = true else sheetFor = type
                        },
                        onToggle = { toggle(type, it) },
                        showDivider = i < types.lastIndex
                    )
                }
            }
            Text(groupNote(group, lang), style = styles.footnote, color = p.textSecondary,
                modifier = Modifier.padding(start = design.spacing.gutter + 18.dp, end = design.spacing.gutter + 8.dp, top = 8.dp,
                    bottom = design.spacing.sectionGap - 8.dp))
        }
    }

    val open = sheetFor
    val current = settings
    if (open != null && current != null) {
        val pref = current.pref(open)
        var hour by remember(open) { mutableIntStateOf(pref.hour) }
        var minute by remember(open) { mutableIntStateOf(pref.minute) }
        // The catalog is parsed once and cached (normally already by the scheduler at start), so this is cheap.
        val preview = remember(open, lang) { runCatching { example(open, current, lang) }.getOrNull() }
        AppSheet(onDismiss = { sheetFor = null }, tag = "notice_sheet") {
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Text(noticeTitle(open, lang), style = styles.title2, color = p.textPrimary)
                Spacer(Modifier.height(2.dp))
                Text(noticeCadence(open, lang), style = styles.footnote, color = p.textSecondary)
                Spacer(Modifier.height(14.dp))
                TimeWheel(hour = pref.hour, minute = pref.minute, is24h = is24h, lang = lang,
                    onChange = { h, m -> hour = h; minute = m }, modifier = Modifier.testTag("notice_time_wheel"))
                preview?.let { message ->
                    Spacer(Modifier.height(18.dp))
                    Text(lang.pick3("Así se verá", "Preview", "Pré-visualização"), style = styles.footnote, color = p.textSecondary)
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.testTag("notice_preview")) { NotificationPreview(message.title, message.body, clock(hour, minute, is24h, lang)) }
                }
                Spacer(Modifier.height(18.dp))
                AppPrimaryCapsule(
                    label = lang.pick3("Guardar", "Save", "Salvar"),
                    onClick = {
                        val (h, m) = hour to minute
                        sheetFor = null
                        reschedule({ store.setTime(open, h, m) }, lang.pick3("Horario actualizado.", "Time updated.", "Horário atualizado."))
                    },
                    tag = "notice_save"
                )
                Spacer(Modifier.height(4.dp))
            }
        }
    }

    if (obligationSheet && current != null) {
        var chosen by remember { mutableStateOf(current.obligationDays) }
        AppSheet(onDismiss = { obligationSheet = false }, tag = "obligation_sheet",
            title = noticeTitle(NoticeType.HOLY_DAY_OF_OBLIGATION, lang)) {
            Text(
                lang.pick3(
                    "Qué días son de precepto lo decide la conferencia episcopal de cada país, y puede trasladarlos al domingo. " +
                        "Marca solo los que se guardan donde vives; el aviso llega la víspera. Los domingos ya tienen su aviso.",
                    "Each bishops' conference decides which days are of obligation, and may move them to Sunday. " +
                        "Tick only those kept where you live; the notice comes the evening before. Sundays already have their own notice.",
                    "Cada conferência episcopal decide quais dias são de preceito, e pode transferi-los para o domingo. " +
                        "Marque só os que se guardam onde vive; o aviso chega na véspera. Os domingos já têm o seu aviso."
                ),
                style = styles.subhead, color = p.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 14.dp).testTag("obligation_note")
            )
            AppListGroup {
                ObligationDays.ALL.forEachIndexed { i, day ->
                    NoticeRow(
                        title = day.title.get(lang), subtitle = null, enabled = day.key in chosen, loaded = true, lang = lang,
                        tag = "obligation_${day.key}",
                        onOpen = { chosen = if (day.key in chosen) chosen - day.key else chosen + day.key },
                        onToggle = { on -> chosen = if (on) chosen + day.key else chosen - day.key },
                        showDivider = i < ObligationDays.ALL.lastIndex
                    )
                }
            }
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                Spacer(Modifier.height(18.dp))
                AppPrimaryCapsule(
                    label = lang.pick3("Guardar", "Save", "Salvar"),
                    onClick = {
                        val days = chosen
                        obligationSheet = false
                        reschedule({ store.setObligationDays(days) }, lang.pick3("Días de precepto guardados.", "Holy days saved.", "Dias de preceito guardados."))
                    },
                    tag = "obligation_save"
                )
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun NoticeRow(
    title: String,
    subtitle: String?,
    enabled: Boolean,
    loaded: Boolean,
    lang: AppLanguage,
    tag: String,
    onOpen: () -> Unit,
    onToggle: (Boolean) -> Unit,
    showDivider: Boolean
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onOpen, 0.985f)
                .heightIn(min = 60.dp)
                .padding(start = 18.dp, end = 14.dp, top = 10.dp, bottom = 10.dp)
                .testTag("notice_row_$tag"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = p.textPrimary)
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, style = styles.footnote, color = p.textSecondary)
                }
            }
            Spacer(Modifier.width(12.dp))
            Box(Modifier.size(width = 56.dp, height = 48.dp), contentAlignment = Alignment.Center) {
                if (loaded) AppSwitch(
                    checked = enabled,
                    onCheckedChange = onToggle,
                    modifier = Modifier.semantics {
                        contentDescription = (if (enabled) lang.pick3("Desactivar", "Turn off", "Desativar")
                            else lang.pick3("Activar", "Turn on", "Ativar")) + ": " + title
                    },
                    tag = "notice_switch_$tag"
                )
            }
        }
        if (showDivider) Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
    }
}
