package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

@Composable
internal fun ContentLoadNotice(language: AppLanguage, failed: Boolean, retry: () -> Unit) {
    fun pick(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
    Column(Modifier.fillMaxWidth().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(if (failed) pick("No se pudo cargar el contenido.", "Content could not load.", "Não foi possível carregar o conteúdo.") else pick("Cargando contenido…", "Loading content…", "Carregando conteúdo…"), style = LocalAppTextStyles.current.body, color = LocalAppDesign.current.palette.textSecondary)
        if (failed) AppSecondaryCapsule(pick("Reintentar", "Retry", "Tentar novamente"), retry)
    }
}
