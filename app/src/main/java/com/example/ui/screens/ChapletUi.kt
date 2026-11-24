package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.repository.Chaplet
import com.example.data.repository.ChapletCatalog
import com.example.data.repository.ChapletGroup
import com.example.data.repository.ChapletVariable
import com.example.ui.design.AppChip
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSheet
import com.example.ui.design.AppWindowClass
import com.example.ui.design.appWindowClass
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberAssetBitmap
import com.example.ui.design.rememberHeroMeta
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.text.Normalizer

// ---------------------------------------------------------------------------
// «Otras coronillas»: the catalogue sheet, the questions sheet and the
// information sheet. They are slot content of the Rosary screen; nothing here
// owns a session.
// ---------------------------------------------------------------------------

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/**
 * Orar can ask the Rosary screen to open a given chaplet (the St Michael row). The request is read once,
 * when the screen is entered, and nothing is stored.
 */
internal object ChapletLaunch {
    @Volatile private var pending: String? = null
    fun request(id: String) { pending = id }
    fun take(): String? = pending.also { pending = null }

    /** Test hook: pins the weekday «Para hoy» uses (1 = Monday … 7 = Sunday). */
    @Volatile internal var isoTodayOverride: Int? = null
    fun isoToday(): Int = isoTodayOverride ?: java.time.LocalDate.now().dayOfWeek.value
}

internal fun groupTitle(group: ChapletGroup, lang: AppLanguage): String = when (group) {
    ChapletGroup.GOD_CHRIST -> lang.pick("Dios y Cristo", "God and Christ", "Deus e Cristo")
    ChapletGroup.MARY -> lang.pick("María", "Mary", "Maria")
    ChapletGroup.SAINTS -> lang.pick("Santos", "Saints", "Santos")
    ChapletGroup.ANGELS -> lang.pick("Ángeles", "Angels", "Anjos")
    ChapletGroup.DEPARTED -> lang.pick("Difuntos", "The departed", "Defuntos")
    ChapletGroup.INTENTION -> lang.pick("Por una intención", "For an intention", "Por uma intenção")
}

/** «Imagen: …» or «Imagen referencial: …», from the credits file; the rights line when the file reserves them. */
internal fun chapletCreditLine(chaplet: Chaplet, lang: AppLanguage): String {
    val credit = chaplet.credit ?: return ""
    // «Imagen ya presente en la app (devotions)» is a production note, not a source for the reader.
    val shown = if (credit.source.startsWith("Imagen ya presente", ignoreCase = true)) credit.copy(source = "") else credit
    return imageCreditLine(shown, lang)
}

/** Lower-case, accent-free text for searching. */
internal fun searchKey(text: String): String =
    Normalizer.normalize(text, Normalizer.Form.NFD).replace(Regex("\\p{M}+"), "").lowercase()

/** The chaplets whose title, summary, occasion or group match every word of [query]. */
internal fun searchChaplets(all: List<Chaplet>, query: String, lang: AppLanguage): List<Chaplet> {
    val words = searchKey(query).split(' ').filter { it.isNotBlank() }
    if (words.isEmpty()) return all
    return all.filter { c ->
        val hay = searchKey(listOf(c.title.get(lang), c.summary.get(lang), c.whenToPray.get(lang), groupTitle(c.group, lang)).joinToString(" "))
        words.all { it in hay }
    }
}

/** One catalogue row: the picture as a thumbnail, the name and when it is prayed. */
@Composable
private fun ChapletRow(chaplet: Chaplet, lang: AppLanguage, selected: Boolean, tag: String, onClick: () -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Row(
        Modifier
            .fillMaxWidth()
            .lumenPress(onClick, 0.99f)
            .semantics { this.selected = selected }
            .testTag(tag)
            .heightIn(min = 64.dp)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArtThumb(chaplet.imagePath, Modifier.size(48.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(chaplet.title.get(lang), style = styles.body, color = p.textPrimary, maxLines = 2)
            val whenText = chaplet.whenToPray.get(lang)
            if (whenText.isNotBlank()) {
                Spacer(Modifier.height(2.dp))
                Text(whenText, style = styles.footnote, color = p.textSecondary, maxLines = 2)
            }
        }
        if (selected) {
            Spacer(Modifier.width(10.dp))
            Icon(Icons.Rounded.Check, null, tint = p.accent, modifier = Modifier.size(22.dp))
        }
    }
}

@Composable
private fun CatalogHeader(text: String, tag: String) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.eyebrow,
        color = design.palette.textSecondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter + 4.dp)
            .padding(top = 12.dp, bottom = 6.dp)
            .testTag(tag)
            .semantics { heading() }
    )
}

/**
 * The catalogue: a search field, «Para hoy» (the chaplets suggested for the day of the week) and the six groups.
 * A search shows the matching chaplets in one list.
 */
@Composable
internal fun ChapletCatalogSheet(
    lang: AppLanguage,
    catalog: ChapletCatalog,
    currentId: String?,
    isoToday: Int,
    onPick: (Chaplet) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val landscape = appWindowClass() == AppWindowClass.LandscapePhone
    var query by rememberSaveable { mutableStateOf("") }
    val shape = RoundedCornerShape(20.dp)
    AppSheet(
        onDismiss = onDismiss,
        title = lang.pick("Otras coronillas", "Other chaplets", "Outras coroinhas"),
        tag = "chaplet_catalog",
        wide = landscape
    ) {
        AppSearchField(
            value = query, onValueChange = { query = it },
            placeholder = lang.pick("Buscar coronilla", "Search chaplets", "Buscar coroinha"),
            modifier = Modifier.padding(horizontal = design.spacing.gutter).testTag("chaplet_search")
        )
        Spacer(Modifier.height(4.dp))
        val list: @Composable (List<Chaplet>, String) -> Unit = { items, prefix ->
            Column(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).lumenGlass(shape, lifted = false)) {
                items.forEachIndexed { i, c ->
                    ChapletRow(c, lang, c.id == currentId, "$prefix${c.id}") { onPick(c) }
                    if (i < items.lastIndex) Box(Modifier.fillMaxWidth().padding(start = 74.dp).height(0.5.dp).background(p.separator))
                }
            }
        }
        if (query.isNotBlank()) {
            val found = searchChaplets(catalog.all, query, lang)
            if (found.isEmpty()) {
                Text(
                    lang.pick("Ninguna coronilla coincide.", "No chaplet matches.", "Nenhuma coroinha corresponde."),
                    style = styles.subhead, color = p.textSecondary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp, vertical = 16.dp)
                )
            } else list(found, "chaplet_row_")
        } else {
            val today = catalog.forWeekday(isoToday)
            if (today.isNotEmpty()) {
                CatalogHeader(lang.pick("Para hoy", "For today", "Para hoje"), "chaplet_group_today")
                list(today, "chaplet_today_row_")
            }
            ChapletGroup.entries.forEach { group ->
                val items = catalog.inGroup(group)
                if (items.isNotEmpty()) {
                    CatalogHeader(groupTitle(group, lang), "chaplet_group_${group.key}")
                    list(items, "chaplet_row_")
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

/** A single-line answer field in the Lumen materials. The text is requested without spelling suggestions. */
@Composable
private fun AnswerField(label: String, value: String, onValueChange: (String) -> Unit, tag: String, singleLine: Boolean = true) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(if (design.isSanctuary) 8.dp else 14.dp)
    Column(Modifier.fillMaxWidth()) {
        Text(label, style = styles.footnote, color = p.textSecondary)
        Spacer(Modifier.height(6.dp))
        BasicTextField(
            value = value, onValueChange = onValueChange,
            textStyle = styles.body.copy(color = p.textPrimary),
            cursorBrush = SolidColor(p.accent),
            singleLine = singleLine, minLines = if (singleLine) 1 else 2, maxLines = if (singleLine) 1 else 4,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, autoCorrectEnabled = false),
            modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).lumenGlass(shape, lifted = false)
                .padding(horizontal = 14.dp, vertical = 12.dp).testTag(tag).semantics { contentDescription = label }
        )
    }
}

/**
 * What the chaplet needs to know before the first prayer: the deceased's name and gender, or an intention. The
 * answers go back to the caller, which keeps them in memory only.
 */
@Composable
internal fun ChapletVariablesSheet(
    lang: AppLanguage,
    chaplet: Chaplet,
    initial: ChapletVariables,
    onConfirm: (ChapletVariables) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    var name by rememberSaveable { mutableStateOf(initial.deceasedName) }
    var gender by rememberSaveable { mutableStateOf(initial.deceasedGender?.name) }
    var intention by rememberSaveable { mutableStateOf(initial.intention) }
    val landscape = appWindowClass() == AppWindowClass.LandscapePhone
    AppSheet(
        onDismiss = onDismiss,
        title = lang.pick("Antes de empezar", "Before you begin", "Antes de começar"),
        tag = "chaplet_variables_sheet",
        wide = landscape,
        footer = {
            AppPrimaryButton(
                lang.pick("Empezar", "Begin", "Começar"),
                onClick = {
                    onConfirm(ChapletVariables(name, gender?.let { DeceasedGender.valueOf(it) }, intention))
                },
                modifier = Modifier.weight(1f).testTag("chaplet_variables_confirm")
            )
        }
    ) {
        Column(Modifier.padding(horizontal = design.spacing.gutter), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(chaplet.title.get(lang), style = styles.callout.copy(fontWeight = FontWeight.Medium), color = p.textPrimary)
            if (ChapletVariable.DECEASED_NAME in chaplet.variables) {
                AnswerField(lang.pick("Nombre de la persona difunta", "Name of the deceased", "Nome da pessoa falecida"), name, { name = it }, "chaplet_answer_name")
            }
            if (ChapletVariable.DECEASED_GENDER in chaplet.variables) {
                Column {
                    Text(lang.pick("La oración dirá", "The prayer will say", "A oração dirá"), style = styles.footnote, color = p.textSecondary)
                    Spacer(Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.testTag("chaplet_gender_picker")) {
                        AppChip(lang.pick("Él", "Him", "Ele"), gender == DeceasedGender.MALE.name, { gender = DeceasedGender.MALE.name })
                        AppChip(lang.pick("Ella", "Her", "Ela"), gender == DeceasedGender.FEMALE.name, { gender = DeceasedGender.FEMALE.name })
                    }
                }
            }
            if (ChapletVariable.INTENTION in chaplet.variables) {
                AnswerField(lang.pick("Intención", "Intention", "Intenção"), intention, { intention = it }, "chaplet_answer_intention", singleLine = false)
            }
            Text(
                lang.pick(
                    "Estos datos solo se usan en esta oración y no se guardan: al cerrar la aplicación se olvidan.",
                    "These answers are only used in this prayer and are not saved: they are forgotten when you close the app.",
                    "Estes dados são usados só nesta oração e não são guardados: são esquecidos ao fechar o aplicativo."
                ),
                style = styles.footnote, color = p.textSecondary
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

/**
 * About the chaplet: its picture with the credit, what it is, how the string is made, when it is prayed, the
 * sources and rights, and (only here, never in the prayer) the historical note and the promise the source reports.
 */
@Composable
internal fun ChapletInfoSheet(
    lang: AppLanguage,
    chaplet: Chaplet,
    hasAnswers: Boolean,
    onChangeAnswers: () -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val landscape = appWindowClass() == AppWindowClass.LandscapePhone
    val bitmap = rememberAssetBitmap(chaplet.imagePath, 1080)
    val meta = rememberHeroMeta(chaplet.imagePath)
    val credit = chapletCreditLine(chaplet, lang)
    val body: @Composable () -> Unit = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(groupTitle(chaplet.group, lang), style = styles.eyebrow, color = p.textSecondary)
            Text(chaplet.summary.get(lang), style = styles.reading, color = p.textPrimary)
            if (chaplet.hasBeads) Text(chaplet.instrument.get(lang), style = styles.subhead, color = p.textSecondary)
            chaplet.whenToPray.get(lang).takeIf { it.isNotBlank() }?.let {
                Text(lang.pick("Cuándo: ", "When: ", "Quando: ") + it, style = styles.subhead, color = p.textPrimary)
            }
            chaplet.historicalNote?.let { n ->
                Text(lang.pick("Nota histórica", "Historical note", "Nota histórica"), style = styles.footnote.copy(fontWeight = FontWeight.Medium), color = p.textSecondary)
                Text(n.text.get(lang), style = styles.subhead, color = p.textPrimary)
                if (n.source.isNotBlank()) Text(n.source, style = styles.caption, color = p.textSecondary)
            }
            chaplet.promise?.let { n ->
                Text(lang.pick("Promesa que refiere la fuente", "Promise the source reports", "Promessa que a fonte refere"), style = styles.footnote.copy(fontWeight = FontWeight.Medium), color = p.textSecondary)
                Text(n.text.get(lang), style = styles.subhead, color = p.textPrimary)
                if (n.source.isNotBlank()) Text(n.source, style = styles.caption, color = p.textSecondary)
            }
            if (chaplet.sources.isNotEmpty()) {
                Text(
                    lang.pick("Fuente: ", "Source: ", "Fonte: ") + chaplet.sources.joinToString("; ") { it.title.ifBlank { it.url } },
                    style = styles.caption, color = p.textSecondary
                )
            }
            chaplet.rights?.takeIf { it.isNotBlank() }?.let { Text(it, style = styles.caption, color = p.textSecondary, modifier = Modifier.testTag("chaplet_rights")) }
            if (hasAnswers) AppSecondaryButton(
                lang.pick("Cambiar los datos", "Change the answers", "Alterar os dados"), onChangeAnswers,
                modifier = Modifier.testTag("chaplet_change_answers")
            )
        }
    }
    val picture: @Composable () -> Unit = {
        Column {
            Box(Modifier.fillMaxWidth().height(if (landscape) 150.dp else 220.dp).clip(RoundedCornerShape(18.dp)).background(p.glass)) {
                if (bitmap != null) Image(bitmap, null, contentScale = ContentScale.Crop, alignment = meta.alignment, modifier = Modifier.fillMaxWidth().height(if (landscape) 150.dp else 220.dp))
            }
            if (credit.isNotBlank()) {
                Spacer(Modifier.height(6.dp))
                Text(credit, style = styles.caption, color = p.textSecondary, modifier = Modifier.testTag("chaplet_image_credit_sheet"))
            }
        }
    }
    AppSheet(onDismiss = onDismiss, title = chaplet.title.get(lang), tag = "chaplet_info_sheet", wide = landscape) {
        if (landscape) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(Modifier.weight(1f)) { picture() }
                Box(Modifier.weight(1f)) { body() }
            }
        } else {
            Column(Modifier.padding(horizontal = design.spacing.gutter), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                picture(); body(); Spacer(Modifier.height(8.dp))
            }
        }
    }
}
