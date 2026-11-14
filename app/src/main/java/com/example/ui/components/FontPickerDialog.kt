package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.FontChoice
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.design.AppSheet
import com.example.ui.design.lumenGlass
import com.example.ui.theme.AppFonts
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

/** Both settings entry points use this single, immediately persisted selection. */
@Composable
fun FontPickerDialog(
    preferencesStore: UserPreferencesStore,
    preferences: UserPreferences,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit
) {
    val persisted by preferencesStore.preferences.collectAsState(initial = preferences)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var pendingWrite by remember { mutableStateOf<Job?>(null) }
    FontPicker(
        choice = FontChoice.fromId(persisted.fontFamilyChoice),
        language = currentLanguage,
        onSelect = { choice ->
            val previous = pendingWrite
            pendingWrite = scope.launch {
                previous?.join()
                try {
                    preferencesStore.setFontFamilyChoice(choice.id)
                } catch (_: IOException) {
                    val message = when (currentLanguage) {
                        AppLanguage.ES -> "No se pudo guardar la fuente"
                        AppLanguage.EN -> "Couldn't save the font"
                        AppLanguage.PT -> "Não foi possível salvar a fonte"
                    }
                    android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_LONG).show()
                }
            }
        },
        onDismiss = { scope.launch { pendingWrite?.join(); onDismiss() } }
    )
}

/**
 * Stateless font sheet: a live reading preview, then the four bundled
 * families, each set in itself with a one-line description. The persisted
 * preference is the only selection state; a tap applies it at once.
 */
@Composable
fun FontPicker(
    choice: FontChoice,
    language: AppLanguage,
    onSelect: (FontChoice) -> Unit,
    onDismiss: () -> Unit
) {
    fun pick(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppSheet(onDismiss = onDismiss, tag = "font_picker") {
        LumenSheetHeader(
            title = pick("Fuente", "Font", "Fonte"),
            doneLabel = pick("Listo", "Done", "Concluir"),
            onDone = onDismiss
        )

        // Live preview: the reading style in the chosen family, the way
        // scripture and prayers will actually read.
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = design.spacing.gutter)
                .lumenGlass(design.shapes.card, lifted = false)
                .clip(design.shapes.card)
                .padding(horizontal = design.spacing.cardPadding, vertical = 18.dp)
        ) {
            Text(
                pick(
                    "En el principio existía el Verbo, y el Verbo estaba junto a Dios, y el Verbo era Dios.",
                    "In the beginning was the Word, and the Word was with God, and the Word was God.",
                    "No princípio era o Verbo, e o Verbo estava junto de Deus, e o Verbo era Deus."
                ),
                style = styles.reading,
                color = p.textPrimary,
                fontFamily = AppFonts.family(choice),
                modifier = Modifier.testTag("font_preview")
            )
            Spacer(Modifier.height(8.dp))
            Text(
                pick("Juan 1, 1", "John 1:1", "João 1, 1"),
                style = styles.footnote,
                color = p.textSecondary
            )
        }

        SheetLabel(pick("Tipografía", "Typeface", "Tipografia"))
        SheetGroup(Modifier.selectableGroup()) {
            val options = FontChoice.entries
            options.forEachIndexed { i, option ->
                SheetChoiceRow(
                    title = fontName(option, language),
                    subtitle = fontDescription(option, language),
                    selected = option == choice,
                    onClick = { onSelect(option) },
                    titleFamily = AppFonts.family(option),
                    showDivider = i < options.lastIndex,
                    modifier = Modifier.testTag("font_choice_${option.id}")
                )
            }
        }
        SheetNote(
            pick(
                "Se aplica a la lectura y a toda la app. El tamaño del texto está en Apariencia.",
                "Applies to reading and the whole app. Text size lives in Appearance.",
                "Aplica-se à leitura e a todo o app. O tamanho do texto está em Aparência."
            )
        )
        Spacer(Modifier.height(8.dp))
    }
}

private fun fontName(option: FontChoice, language: AppLanguage): String = when (option) {
    FontChoice.IDENTITY_DEFAULT -> when (language) {
        AppLanguage.ES -> "Predeterminada"
        AppLanguage.EN -> "Default"
        AppLanguage.PT -> "Padrão"
    }
    else -> option.familyName
}

private fun fontDescription(option: FontChoice, language: AppLanguage): String = when (option) {
    FontChoice.IDENTITY_DEFAULT -> when (language) {
        AppLanguage.ES -> "Inter en la app, serif para leer"
        AppLanguage.EN -> "Inter in the app, serif for reading"
        AppLanguage.PT -> "Inter no app, serifa para ler"
    }
    FontChoice.INTER -> when (language) {
        AppLanguage.ES -> "Sans moderna y muy legible"
        AppLanguage.EN -> "A modern, very legible sans"
        AppLanguage.PT -> "Sans moderna e muito legível"
    }
    FontChoice.ROBOTO -> when (language) {
        AppLanguage.ES -> "La clásica de Android"
        AppLanguage.EN -> "The Android classic"
        AppLanguage.PT -> "A clássica do Android"
    }
    FontChoice.EB_GARAMOND -> when (language) {
        AppLanguage.ES -> "Serif clásica para leer despacio"
        AppLanguage.EN -> "A classic serif for slow reading"
        AppLanguage.PT -> "Serifa clássica para ler devagar"
    }
}

// -----------------------------------------------------------------------------
// Settings-sheet pieces shared by the font, language and image-credit sheets,
// so the three read as one family inside the foundation's AppSheet.
// -----------------------------------------------------------------------------

/**
 * Sheet title centred like iOS, with "Done" at the trailing edge. A plain
 * ink word, not a coloured button: the sheet has one accent, and it is not here.
 */
@Composable
internal fun LumenSheetHeader(
    title: String,
    doneLabel: String,
    onDone: () -> Unit,
    doneTag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val palette = design.palette
    val density = LocalDensity.current
    val measurer = rememberTextMeasurer()
    val actionStyle = styles.body.copy(fontWeight = FontWeight.SemiBold)
    val actionWidth = maxOf(48.dp, with(density) {
        measurer.measure(AnnotatedString(doneLabel), actionStyle, softWrap = false).size.width.toDp()
    } + 24.dp)
    val titleWidth = with(density) {
        measurer.measure(AnnotatedString(title), styles.headline, softWrap = false).size.width.toDp()
    }
    val action: @Composable (Modifier) -> Unit = { modifier ->
        Box(
            modifier.heightIn(min = 48.dp).widthIn(min = 48.dp)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(percent = 50))
                .clickable(role = Role.Button, onClick = onDone)
                .then(if (doneTag != null) Modifier.testTag(doneTag) else Modifier)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(doneLabel, style = actionStyle, color = palette.textPrimary, maxLines = 1)
        }
    }
    BoxWithConstraints(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter - 8.dp)
            .padding(top = 6.dp, bottom = 10.dp).heightIn(min = 48.dp)
    ) {
        if (titleWidth + actionWidth * 2 + 16.dp <= maxWidth) {
            Box(Modifier.fillMaxWidth().heightIn(min = 48.dp)) {
                Text(title, style = styles.headline, color = palette.textPrimary,
                    textAlign = TextAlign.Center, maxLines = 1,
                    modifier = Modifier.align(Alignment.Center).padding(horizontal = actionWidth + 8.dp)
                        .semantics { heading() })
                action(Modifier.align(Alignment.CenterEnd))
            }
        } else {
            Row(Modifier.fillMaxWidth().heightIn(min = 48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(title, style = styles.headline, color = palette.textPrimary,
                    modifier = Modifier.weight(1f).padding(start = 8.dp).semantics { heading() })
                action(Modifier)
            }
        }
    }
}

/** Small label above a sheet group, aligned with the row text. */
@Composable
internal fun SheetLabel(text: String) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.footnote,
        color = design.palette.textSecondary,
        modifier = Modifier.padding(
            start = design.spacing.gutter + 18.dp,
            end = design.spacing.gutter,
            top = 22.dp,
            bottom = 8.dp
        )
    )
}

/** Footnote under a sheet group. */
@Composable
internal fun SheetNote(text: String) {
    val design = LocalAppDesign.current
    Text(
        text,
        style = LocalAppTextStyles.current.footnote,
        color = design.palette.textSecondary,
        modifier = Modifier.padding(
            start = design.spacing.gutter + 18.dp,
            end = design.spacing.gutter + 18.dp,
            top = 10.dp,
            bottom = 4.dp
        )
    )
}

/** Glass group inside a sheet (no lift: the sheet is already the raised layer). */
@Composable
internal fun SheetGroup(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    val design = LocalAppDesign.current
    val shape = design.shapes.listGroup
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .lumenGlass(shape, strong = true, lifted = false)
            .clip(shape)
            .then(modifier),
        content = content
    )
}

/**
 * One option in a sheet list: title (optionally set in its own typeface),
 * a footnote line, and an ink check on the chosen row. Radio semantics, so
 * TalkBack reads "selected" without a separate label.
 */
@Composable
internal fun SheetChoiceRow(
    title: String,
    subtitle: String?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleFamily: FontFamily? = null,
    showDivider: Boolean = true
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val fill by animateColorAsState(
        if (pressed) p.textPrimary.copy(alpha = 0.06f) else Color.Transparent,
        if (pressed) tween(0) else tween(design.motion.mediumMillis),
        label = "sheetRowFill"
    )
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .background(fill)
                .selectable(
                    selected = selected,
                    interactionSource = source,
                    indication = null,
                    role = Role.RadioButton,
                    onClick = onClick
                )
                .padding(horizontal = 18.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = styles.body,
                    fontFamily = titleFamily,
                    color = p.textPrimary
                )
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, style = styles.footnote, color = p.textSecondary)
                }
            }
            Box(Modifier.size(22.dp), contentAlignment = Alignment.Center) {
                if (selected) {
                    Icon(
                        Icons.Rounded.Check,
                        contentDescription = null,
                        tint = p.textPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
        if (showDivider) SheetDivider()
    }
}

/** Inset hairline between sheet rows. */
@Composable
internal fun SheetDivider(start: androidx.compose.ui.unit.Dp = 18.dp) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = start)
            .height(0.5.dp)
            .background(p.separator)
    )
}
