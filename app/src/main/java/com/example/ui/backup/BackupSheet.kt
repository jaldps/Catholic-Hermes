package com.example.ui.backup

import android.view.WindowManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.backup.*
import com.example.data.models.AppLanguage
import com.example.data.prefs.UserPreferencesStore
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

internal fun AppLanguage.backup(es:String,en:String,pt:String)=when(this){AppLanguage.ES->es;AppLanguage.EN->en;AppLanguage.PT->pt}

@Composable
fun BackupSheet(store:UserPreferencesStore,language:AppLanguage,service:BackupService?=null,onDismiss:()->Unit,onRestored:()->Unit={}) {
    val context=LocalContext.current
    val factory=remember(store,service) { object:ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T:ViewModel> create(modelClass:Class<T>):T = BackupViewModel(service ?: BackupService.create(context,store),context.contentResolver) as T
    } }
    val model:BackupViewModel=viewModel(key="personal-backup",factory=factory)
    val state by model.state.collectAsState()
    val create=rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/octet-stream"),model::save)
    val open=rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument(),model::imported)
    fun dismiss() {
        if(!state.busy) {val restored=state.restored;model.reset();onDismiss();if(restored)onRestored()}
    }
    LaunchedEffect(state.exportReady) {
        if(state.exportReady&&model.claimExportPicker()) {
            try {create.launch("Catholic-Hermes-${LocalDate.now()}.chbackup")}
            catch(_:Exception) {model.pickerFailed()}
        }
    }
    AppSheet(onDismiss=::dismiss,title=language.backup("Copia de seguridad","Backup","Cópia de segurança"),tag="backup_sheet") {
        // Protect the real dialog window; screenshot tests use synthetic fixtures and a Canvas.
        val view=LocalView.current
        DisposableEffect(view) {
            val window=(view.parent as? DialogWindowProvider)?.window
            val secure=window?.attributes?.flags?.and(WindowManager.LayoutParams.FLAG_SECURE)!=0
            window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
            onDispose {if(!secure)window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)}
        }
        BackupContent(state,language,model::startExport,{try {open.launch(arrayOf("*/*"))}catch(_:Exception){model.pickerFailed()}},
            model::export,model::preview,model::selectMode,model::apply,::dismiss,model::reset)
    }
}

/** Same production content is used in interaction tests and captures. */
@Composable
internal fun BackupContent(
    state:BackupUiState,language:AppLanguage,onExport:()->Unit,onImport:()->Unit,
    onCreate:(CharArray)->Unit,onPreview:(CharArray)->Unit,onMode:(RestoreMode)->Unit,onApply:()->Unit,
    onDone:()->Unit,onBack:()->Unit
) {
    val p=LocalAppDesign.current.palette;val styles=LocalAppTextStyles.current
    fun pick(es:String,en:String,pt:String)=language.backup(es,en,pt)
    var password by remember(state.page) {mutableStateOf("")}
    var confirmation by remember(state.page) {mutableStateOf("")}
    var confirmReplace by remember {mutableStateOf(false)}
    Column(Modifier.fillMaxWidth().padding(horizontal=20.dp).padding(bottom=20.dp),verticalArrangement=Arrangement.spacedBy(16.dp)) {
        if(state.error!=null) AppBanner(pick("No se completó la operación","The operation could not be completed","Não foi possível concluir a operação"),errorMessage(state.error,language),modifier=Modifier.testTag("backup_error"))
        when(state.page) {
            BackupPage.HOME -> {
                AppCard {
                    Icon(Icons.Outlined.Shield,null,tint=p.accent,modifier=Modifier.size(32.dp))
                    Spacer(Modifier.height(12.dp))
                    Text(pick("Tu historia, contigo","Your journey, with you","Sua caminhada, com você"),style=styles.title2,color=p.textPrimary)
                    Spacer(Modifier.height(8.dp))
                    Text(pick("Guarda tu diario, prácticas, novenas y estudio en una copia cifrada para recuperarlos o llevarlos a otro teléfono.","Keep your journal, practices, novenas and study in an encrypted copy to restore them or move them to another phone.","Guarde seu diário, práticas, novenas e estudo numa cópia criptografada para recuperá-los ou levá-los a outro celular."),style=styles.body,color=p.textPrimary)
                }
                AppPrimaryButton(pick("Crear copia","Create backup","Criar cópia"),onExport,leadingIcon=Icons.Outlined.SaveAlt,modifier=Modifier.fillMaxWidth().testTag("backup_export_button"))
                AppSecondaryButton(pick("Abrir una copia","Open a backup","Abrir uma cópia"),onImport,leadingIcon=Icons.Outlined.FolderOpen,modifier=Modifier.fillMaxWidth().testTag("backup_import_button"))
                Text(pick("Tú eliges dónde guardarla. No se envía a nuestros servidores. Si olvidas la contraseña, no podremos abrir el archivo.","You choose where to save it. It is not sent to our servers. If you forget the password, we cannot open the file.","Você escolhe onde salvar. A cópia não é enviada aos nossos servidores. Se esquecer a senha, não poderemos abrir o arquivo."),style=styles.footnote,color=p.textSecondary)
            }
            BackupPage.EXPORT,BackupPage.IMPORT -> {
                val exporting=state.page==BackupPage.EXPORT
                Text(if(exporting)pick("Protege tu copia","Protect your backup","Proteja sua cópia") else pick("Abre tu copia","Unlock your backup","Abra sua cópia"),style=styles.title2,color=p.textPrimary)
                Text(if(exporting)pick("Elige una contraseña de al menos 10 caracteres y guárdala en un lugar seguro. La necesitarás para restaurarla. Si la olvidas, no podrás abrir la copia.","Choose a password of at least 10 characters and keep it somewhere safe. You will need it to restore the backup. If you forget it, the backup cannot be unlocked.","Escolha uma senha com pelo menos 10 caracteres e guarde-a num lugar seguro. Você precisará dela para restaurá-la. Se esquecer a senha, não será possível abrir a cópia.") else pick("Escribe la contraseña con la que se creó el archivo. Podrás revisar su contenido antes de cambiar tus datos.","Enter the password used to create this file. You can review its contents before changing your data.","Digite a senha usada para criar o arquivo. Você poderá revisar seu conteúdo antes de alterar seus dados."),style=styles.body,color=p.textPrimary)
                PasswordField(password,{password=it},pick("Contraseña","Password","Senha"),"backup_password_field",!state.busy)
                if(exporting) PasswordField(confirmation,{confirmation=it},pick("Repetir contraseña","Repeat password","Repetir senha"),"backup_password_confirm_field",!state.busy)
                if(exporting&&confirmation.isNotEmpty()&&password!=confirmation) Text(pick("Las contraseñas deben coincidir.","Passwords must match.","As senhas devem coincidir."),style=styles.footnote,color=p.textSecondary)
                AppPrimaryButton(if(state.busy)pick("Preparando…","Preparing…","Preparando…") else if(exporting)pick("Guardar copia","Save backup","Salvar cópia") else pick("Ver resumen","Review backup","Ver resumo"),{
                    val secret=password.toCharArray();password="";confirmation=""
                    if(exporting)onCreate(secret) else onPreview(secret)
                },enabled=!state.busy&&password.isNotEmpty()&&(!exporting||(password.length>=10&&password==confirmation)),modifier=Modifier.fillMaxWidth().testTag("backup_unlock_button"))
                Text(pick("La contraseña no se guarda en la app.","The password is not saved in the app.","A senha não é salva no aplicativo."),style=styles.footnote,color=p.textSecondary)
                AppSecondaryButton(pick("Volver","Back","Voltar"),onBack,enabled=!state.busy,modifier=Modifier.fillMaxWidth())
            }
            BackupPage.SUMMARY -> {
                state.summary?.let {summary ->
                    Text(pick("Revisa lo que vas a recuperar","Review what you will restore","Revise o que será recuperado"),style=styles.title2,color=p.textPrimary)
                    val locale=when(language){AppLanguage.ES->Locale.forLanguageTag("es");AppLanguage.EN->Locale.ENGLISH;AppLanguage.PT->Locale.forLanguageTag("pt-BR")}
                    val date=DateTimeFormatter.ofPattern("d MMM yyyy",locale).format(Instant.ofEpochMilli(summary.createdAt).atZone(ZoneId.systemDefault()))
                    Text(pick("Copia del $date","Backup from $date","Cópia de $date"),style=styles.footnote,color=p.textSecondary)
                    AppListGroup(Modifier.testTag("backup_summary")) {
                        AppListRow(pick("Entradas de diario","Journal entries","Entradas do diário"),trailingText=summary.journalEntries.toString(),showsNavigationAffordance=false)
                        AppListRow(pick("Novenas","Novenas","Novenas"),subtitle=pick("${summary.activeNovenas} en curso · ${summary.novenaDays} días rezados","${summary.activeNovenas} in progress · ${summary.novenaDays} completed days","${summary.activeNovenas} em andamento · ${summary.novenaDays} dias rezados"),trailingText=summary.novenas.toString(),showsNavigationAffordance=false)
                        AppListRow(pick("Prácticas","Practices","Práticas"),subtitle=pick("${summary.habitLogs} cumplimientos registrados","${summary.habitLogs} recorded completions","${summary.habitLogs} cumprimentos registrados"),trailingText=summary.habits.toString(),showsNavigationAffordance=false)
                        AppListRow(pick("Vencimientos registrados","Recorded victories","Vitórias registradas"),trailingText=summary.sinLogs.toString(),showsNavigationAffordance=false)
                        AppListRow(pick("Lecciones de estudio","Study lessons","Lições de estudo"),subtitle=pick("${summary.studyNotes} con notas personales","${summary.studyNotes} with personal notes","${summary.studyNotes} com notas pessoais"),trailingText=summary.studyLessons.toString(),showsNavigationAffordance=false)
                        AppListRow(pick("Rosarios y coronillas","Rosaries and chaplets","Rosários e terços"),trailingText="${summary.rosariesPrayed} · ${summary.chapletsPrayed}",showsNavigationAffordance=false)
                        AppListRow(pick("Versículos guardados","Saved verses","Versículos salvos"),trailingText=summary.memorizedVerses.toString(),showsNavigationAffordance=false)
                        AppListRow(pick("Ajustes y otros avances","Settings and other progress","Ajustes e outros avanços"),trailingText=summary.preferences.toString(),showsNavigationAffordance=false)
                    }
                }
                // Full-width choices keep both modes visible with large text on a narrow phone.
                AppListGroup(Modifier.testTag("backup_mode_picker")) {
                    listOf(RestoreMode.MERGE to pick("Combinar","Merge","Combinar"),RestoreMode.REPLACE to pick("Reemplazar","Replace","Substituir")).forEach { (mode,label) ->
                        val selected=state.mode==mode
                        AppListRow(label,leadingIcon=if(selected)Icons.Outlined.RadioButtonChecked else Icons.Outlined.RadioButtonUnchecked,
                            selected=selected,showsNavigationAffordance=false,onClick=if(state.busy)null else {{onMode(mode)}},
                            modifier=Modifier.semantics {this.selected=selected;role=Role.RadioButton})
                    }
                }
                Text(if(state.mode==RestoreMode.MERGE)pick("Conserva tus registros y añade los de la copia sin duplicarlos. Los ajustes de la copia se aplicarán también.","Keep your records and add the backup’s records without duplicates. The backup’s settings will also be applied.","Mantenha seus registros e acrescente os da cópia sem duplicá-los. Os ajustes da cópia também serão aplicados.") else pick("Sustituye los datos personales de este teléfono por los de la copia. Antes de continuar, conviene guardar una copia de tus datos actuales.","Replace this phone’s personal data with the backup’s data. Consider backing up your current data before continuing.","Substitua os dados pessoais deste celular pelos da cópia. Antes de continuar, considere salvar uma cópia dos dados atuais."),style=styles.body,color=p.textPrimary)
                AppPrimaryButton(if(state.busy)pick("Restaurando…","Restoring…","Restaurando…") else if(state.mode==RestoreMode.MERGE)pick("Combinar datos","Merge data","Combinar dados") else pick("Reemplazar datos","Replace data","Substituir dados"),{
                    if(state.mode==RestoreMode.REPLACE)confirmReplace=true else onApply()
                },enabled=!state.busy,modifier=Modifier.fillMaxWidth().testTag("backup_apply_button"))
                AppSecondaryButton(pick("Cancelar","Cancel","Cancelar"),onBack,enabled=!state.busy,modifier=Modifier.fillMaxWidth())
            }
            BackupPage.SUCCESS -> {
                AppCard {
                    Icon(Icons.Outlined.CheckCircle,null,tint=p.accent,modifier=Modifier.size(32.dp))
                    Spacer(Modifier.height(12.dp))
                    Text(if(state.restored)pick("Tus datos están restaurados","Your data is restored","Seus dados foram restaurados") else pick("Tu copia está guardada","Your backup is saved","Sua cópia foi salva"),style=styles.title2,color=p.textPrimary)
                    Spacer(Modifier.height(8.dp))
                    Text(if(state.restored)pick("Al continuar, la app se actualizará con los datos y ajustes recuperados.","When you continue, the app will refresh with your restored data and settings.","Ao continuar, o aplicativo será atualizado com os dados e ajustes recuperados.") else pick("Guarda el archivo y su contraseña por separado. Puedes abrir esta copia en otro teléfono desde Ajustes.","Keep the file and its password separately. You can open this backup on another phone from Settings.","Guarde o arquivo e sua senha separadamente. Você pode abrir esta cópia em outro celular pelos Ajustes."),style=styles.body,color=p.textPrimary)
                }
                AppPrimaryButton(pick("Listo","Done","Concluir"),onDone,modifier=Modifier.fillMaxWidth().testTag("backup_done_button"))
            }
        }
        if(state.busy) Text(pick("Espera a que termine la operación.","Please wait for the operation to finish.","Aguarde a conclusão da operação."),style=styles.footnote,color=p.textSecondary,modifier=Modifier.semantics {liveRegion=LiveRegionMode.Polite})
    }
    if(confirmReplace) AppConfirmDialog(pick("¿Reemplazar tus datos?","Replace your data?","Substituir seus dados?"),pick("Se quitarán los datos personales actuales de este teléfono y se restaurará la copia elegida. Esta acción no se puede deshacer desde la app.","This phone’s current personal data will be removed and the selected backup restored. This action cannot be undone within the app.","Os dados pessoais atuais deste celular serão removidos e a cópia escolhida será restaurada. Esta ação não pode ser desfeita pelo aplicativo."),pick("Reemplazar","Replace","Substituir"),pick("Cancelar","Cancel","Cancelar"),{confirmReplace=false;onApply()},{confirmReplace=false},destructive=true,tag="backup_replace_confirmation")
}

/** AppCard supplies identity geometry/material; its child uses the platform's secure text transformation. */
@Composable
private fun PasswordField(value:String,onChange:(String)->Unit,label:String,tag:String,enabled:Boolean) {
    val p=LocalAppDesign.current.palette;val styles=LocalAppTextStyles.current
    AppCard {
        Text(label,style=styles.footnote,color=p.textSecondary)
        Spacer(Modifier.height(8.dp))
        BasicTextField(value=value,onValueChange={if(it.length<=1024)onChange(it)},enabled=enabled,singleLine=true,
            visualTransformation=PasswordVisualTransformation(),keyboardOptions=KeyboardOptions(keyboardType=KeyboardType.Password,autoCorrectEnabled=false),
            textStyle=styles.body.copy(color=p.textPrimary),cursorBrush=SolidColor(p.accent),
            modifier=Modifier.fillMaxWidth().heightIn(min=40.dp).testTag(tag).semantics {contentDescription=label})
    }
}

private fun errorMessage(reason:BackupError,l:AppLanguage):String = when(reason) {
    BackupError.WRONG_PASSWORD_OR_DAMAGED->l.backup("La contraseña es incorrecta o la copia está dañada. Tus datos no se han cambiado.","The password is incorrect or the backup is damaged. Your data has not changed.","A senha está incorreta ou a cópia está danificada. Seus dados não foram alterados.")
    BackupError.UNSUPPORTED_VERSION->l.backup("Esta copia usa un formato más reciente. Actualiza la app para abrirla.","This backup uses a newer format. Update the app to open it.","Esta cópia usa um formato mais recente. Atualize o aplicativo para abri-la.")
    BackupError.INVALID_FILE->l.backup("No es una copia compatible o el archivo está incompleto. Elige un archivo .chbackup.","This is not a compatible backup, or the file is incomplete. Choose a .chbackup file.","Esta cópia não é compatível ou o arquivo está incompleto. Escolha um arquivo .chbackup.")
    BackupError.TOO_LARGE->l.backup("La copia supera el límite de 32 MB y no se puede abrir en esta versión.","The backup exceeds the 32 MB limit and cannot be opened in this version.","A cópia excede o limite de 32 MB e não pode ser aberta nesta versão.")
    BackupError.PASSWORD_REQUIRED->l.backup("Escribe la contraseña de la copia.","Enter the backup password.","Digite a senha da cópia.")
    BackupError.STORAGE->l.backup("No se pudo acceder a los archivos o guardar los cambios. Comprueba el espacio disponible y vuelve a intentarlo.","Files could not be accessed or changes saved. Check available space and try again.","Não foi possível acessar os arquivos ou salvar as alterações. Verifique o espaço disponível e tente novamente.")
}

@Composable
fun BackupRecoverySheet(language:AppLanguage,onRetry:()->Unit,onExit:()->Unit) {
    val p=LocalAppDesign.current.palette;val styles=LocalAppTextStyles.current
    AppSheet(onDismiss=onExit,title=language.backup("Restauración pendiente","Restore needs attention","Restauração pendente"),tag="backup_recovery") {
        Column(Modifier.padding(20.dp),verticalArrangement=Arrangement.spacedBy(16.dp)) {
            Text(language.backup("No pudimos recuperar los ajustes de la restauración anterior. Comprueba el espacio disponible y vuelve a intentarlo.","We could not recover settings from the previous restore. Check available space and try again.","Não foi possível recuperar os ajustes da restauração anterior. Verifique o espaço disponível e tente novamente."),style=styles.body,color=p.textPrimary)
            AppPrimaryButton(language.backup("Reintentar","Retry","Tentar novamente"),onRetry,modifier=Modifier.fillMaxWidth().testTag("backup_recovery_retry"))
            AppSecondaryButton(language.backup("Salir","Exit","Sair"),onExit,modifier=Modifier.fillMaxWidth())
        }
    }
}
