package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.FontChoice
import com.example.data.prefs.UserPreferences
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.AppFonts
import com.example.ui.theme.DesignLanguage
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
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

/** Stateless selector: the persisted preference is the only selection state. */
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
    val selectionColor = if (design.language == DesignLanguage.VATICAN) {
        design.palette.accent
    } else {
        MaterialTheme.colorScheme.primary
    }
    AlertDialog(
        shape = design.shapes.dialog,
        containerColor = design.palette.raised,
        titleContentColor = design.palette.textPrimary,
        textContentColor = design.palette.textSecondary,
        modifier = Modifier.testTag("font_picker"),
        onDismissRequest = onDismiss,
        title = { Text(pick("Fuente", "Font", "Fonte")) },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()).selectableGroup()) {
                FontChoice.entries.forEach { option ->
                    Row(
                        Modifier.fillMaxWidth().heightIn(min = 48.dp)
                            .testTag("font_choice_${option.id}")
                            .selectable(
                                selected = option == choice,
                                role = Role.RadioButton,
                                onClick = { onSelect(option) }
                            ).padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = option == choice,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = selectionColor,
                                unselectedColor = design.palette.textSecondary
                            )
                        )
                        Text(
                            option.label(language),
                            modifier = Modifier.padding(start = 12.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = AppFonts.family(option)
                        )
                    }
                }
                Text(
                    pick("En el principio era el Verbo.", "In the beginning was the Word.", "No princípio era o Verbo."),
                    modifier = Modifier.padding(top = 16.dp).testTag("font_preview"),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = AppFonts.family(choice)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(contentColor = selectionColor)
            ) { Text(pick("Listo", "Done", "Concluir")) }
        }
    )
}
