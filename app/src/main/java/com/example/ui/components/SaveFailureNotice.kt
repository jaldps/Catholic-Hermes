package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.persistence.PersonalDataCommands
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

@Composable
fun SaveFailureNotice(commandKey: String, language: AppLanguage) {
    val states by PersonalDataCommands.states.collectAsState()
    val p = LocalAppDesign.current.palette
    val styles = LocalAppTextStyles.current
    fun pick(es:String,en:String,pt:String)=when(language){AppLanguage.ES->es;AppLanguage.EN->en;AppLanguage.PT->pt}
    AnimatedVisibility(states[commandKey]?.status == PersonalDataCommands.Status.ERROR) {
        Column(Modifier.fillMaxWidth().padding(16.dp).semantics { liveRegion=LiveRegionMode.Polite }) {
            Text(pick("No se pudieron aplicar los últimos cambios.","Could not apply the latest changes.","Não foi possível aplicar as últimas alterações."),style=styles.body,color=p.textPrimary)
            Spacer(Modifier.height(8.dp))
            AppSecondaryCapsule(pick("Reintentar","Retry","Tentar novamente"),{PersonalDataCommands.retry(commandKey)})
        }
    }
}
