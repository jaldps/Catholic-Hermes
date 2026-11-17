package com.example.ui.diagnostics

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.diagnostics.*
import com.example.data.models.AppLanguage
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

@Composable
fun ErrorReportSheet(language:AppLanguage,onDismiss:()->Unit) {
    val context=LocalContext.current
    val scope=rememberCoroutineScope()
    var busy by remember {mutableStateOf(false)}
    var failed by remember {mutableStateOf(false)}
    val p=LocalAppDesign.current.palette
    val styles=LocalAppTextStyles.current
    fun pick(es:String,en:String,pt:String)=when(language){AppLanguage.ES->es;AppLanguage.EN->en;AppLanguage.PT->pt}
    val title=pick("Enviar informe de errores","Send error report","Enviar relatório de erros")
    AppSheet(onDismiss={if(!busy)onDismiss()},title=title,tag="error_report_sheet") {
        Column(Modifier.fillMaxWidth().padding(20.dp),verticalArrangement=Arrangement.spacedBy(16.dp)) {
            Text(pick("Incluye fechas y detalles técnicos de los fallos. No incluye diario, notas, contraseñas ni contenido de copias de seguridad.",
                "Includes dates and technical details about errors. It does not include your journal, notes, passwords or backup contents.",
                "Inclui datas e detalhes técnicos dos erros. Não inclui diário, notas, senhas nem conteúdo das cópias de segurança."),style=styles.body,color=p.textPrimary)
            Text(pick("Tú eliges la aplicación y decides si enviarlo. No se envía automáticamente.","You choose the app and decide whether to send it. Nothing is sent automatically.","Você escolhe o aplicativo e decide se deseja enviar. Nada é enviado automaticamente."),style=styles.footnote,color=p.textSecondary)
            if(failed) AppBanner(pick("No se pudo compartir","Could not share report","Não foi possível compartilhar"),
                pick("Vuelve a intentarlo y comprueba que tengas una aplicación para compartir archivos.","Try again and check that an app for sharing files is available.","Tente novamente e verifique se há um aplicativo para compartilhar arquivos."),modifier=Modifier.testTag("error_report_error"))
            AppPrimaryButton(if(busy)pick("Preparando…","Preparing…","Preparando…") else pick("Compartir informe","Share report","Compartilhar relatório"),{
                busy=true;failed=false
                scope.launch {
                    try {
                        val file=AppLog.createReport(context)
                        val chooser=Intent.createChooser(ErrorReportShare.intent(context,file),title)
                        context.startActivity(chooser)
                    } catch(e:CancellationException) {throw e}
                    catch(e:Exception) {AppLog.failure(LogEvent.REPORT_SHARE,e);failed=true}
                    finally {busy=false}
                }
            },enabled=!busy,modifier=Modifier.fillMaxWidth().testTag("error_report_share_button"))
            AppSecondaryButton(pick("Cancelar","Cancel","Cancelar"),onDismiss,enabled=!busy,modifier=Modifier.fillMaxWidth())
        }
    }
}
