package com.example.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.AppSheet

/**
 * Language as a Lumen sheet: each language under its own name, with its
 * name in the current language beneath (iOS's language list), an ink check
 * on the active one. No flags: a flag names a country, not a language.
 * A tap applies the language and closes the sheet, as before.
 */
@Composable
fun LanguageSelectorDialog(
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    onDismissRequest: () -> Unit
) {
    fun pick(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    AppSheet(onDismiss = onDismissRequest, tag = "language_sheet") {
        LumenSheetHeader(
            title = pick("Idioma", "Language", "Idioma"),
            doneLabel = pick("Listo", "Done", "Concluir"),
            onDone = onDismissRequest,
            doneTag = "close_language_dialog"
        )
        SheetGroup(Modifier.selectableGroup()) {
            val options = AppLanguage.entries
            options.forEachIndexed { i, lang ->
                val localized = localizedName(lang, currentLanguage)
                SheetChoiceRow(
                    title = lang.displayName,
                    // The active language's row already reads in that language.
                    subtitle = localized.takeIf { it != lang.displayName },
                    selected = lang == currentLanguage,
                    onClick = {
                        onLanguageSelected(lang)
                        onDismissRequest()
                    },
                    showDivider = i < options.lastIndex,
                    modifier = Modifier.testTag("lang_option_${lang.code}")
                )
            }
        }
        SheetNote(
            pick(
                "Las lecturas, las oraciones y el Catecismo usan traducciones católicas oficiales.",
                "Readings, prayers and the Catechism use official Catholic translations.",
                "As leituras, as orações e o Catecismo usam traduções católicas oficiais."
            )
        )
        Spacer(Modifier.height(8.dp))
    }
}

/** [lang]'s name as written in [ui]. */
private fun localizedName(lang: AppLanguage, ui: AppLanguage): String = when (ui) {
    AppLanguage.ES -> when (lang) {
        AppLanguage.ES -> "Español"
        AppLanguage.EN -> "Inglés"
        AppLanguage.PT -> "Portugués"
    }
    AppLanguage.EN -> when (lang) {
        AppLanguage.ES -> "Spanish"
        AppLanguage.EN -> "English"
        AppLanguage.PT -> "Portuguese"
    }
    AppLanguage.PT -> when (lang) {
        AppLanguage.ES -> "Espanhol"
        AppLanguage.EN -> "Inglês"
        AppLanguage.PT -> "Português"
    }
}
