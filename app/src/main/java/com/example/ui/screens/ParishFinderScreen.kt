package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.OpenInNew
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppKitChip
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppSearchBar
import com.example.ui.design.appDockInset
import com.example.ui.design.rememberFirstAsset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * Parishes is an honest launcher: there is no in-app directory, so the search
 * field, the "near me" button and the quick chips all open Google Maps with a
 * Catholic-church query (Maps resolves "near me" with its own location access,
 * so the app asks for no location permission here).
 */
@Composable
fun ParishFinderScreen(
    @Suppress("UNUSED_PARAMETER") repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    AppEdgeToEdge()
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val focus = LocalFocusManager.current
    val dock = appDockInset()
    val hero = rememberFirstAsset("heroes/lumen/parishes.jpg", "heroes/church_window_light.jpg")

    var placeQuery by rememberSaveable { mutableStateOf("") }
    var failed by remember { mutableStateOf(false) }

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    /** Opens Google Maps (geo: intent first, then the web URL) on [what], optionally near [place]. */
    fun openMaps(what: String, place: String) {
        val text = if (place.isBlank()) what else "$what $place"
        val q = Uri.encode(text)
        failed = !(runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$q")))
        }.isSuccess || runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/$q")))
        }.isSuccess)
    }

    val parishQuery = strings("parroquia católica", "catholic church", "igreja católica")
    val quick = listOf(
        strings("Misa hoy", "Mass today", "Missa hoje") to
            strings("misa católica hoy", "catholic mass today", "missa católica hoje"),
        strings("Confesiones", "Confessions", "Confissões") to
            strings("confesiones iglesia católica", "catholic church confession", "confissões igreja católica"),
        strings("Adoración", "Adoration", "Adoração") to
            strings("adoración eucarística", "eucharistic adoration", "adoração eucarística")
    )

    LazyColumn(
        Modifier.fillMaxSize().testTag("parish_finder_screen"),
        contentPadding = PaddingValues(bottom = dock + 24.dp)
    ) {
        item(key = "parish_header") {
            AppDetailHeader(
                title = strings("Parroquias", "Parishes", "Paróquias"),
                assetPath = hero,
                imageHeight = 248.dp
            )
        }
        item(key = "parish_intro") {
            Text(
                strings(
                    "Encuentra misas y parroquias cercanas en Google Maps.",
                    "Find Mass times and nearby parishes in Google Maps.",
                    "Encontre missas e paróquias próximas no Google Maps."
                ),
                style = styles.body,
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = design.spacing.gutter, vertical = 4.dp)
            )
            Spacer(Modifier.height(16.dp))
        }
        item(key = "parish_search") {
            Column {
                AppSearchBar(
                    value = placeQuery,
                    onValueChange = { placeQuery = it; failed = false },
                    placeholder = strings(
                        "Ciudad, barrio o dirección",
                        "City, neighbourhood or address",
                        "Cidade, bairro ou endereço"
                    ),
                    onSubmit = { focus.clearFocus(); openMaps(parishQuery, placeQuery.trim()) },
                    tag = "parish_place_field"
                )
                Spacer(Modifier.height(12.dp))
                AppPrimaryCapsule(
                    label = strings("Parroquias cerca de mí", "Parishes near me", "Paróquias perto de mim"),
                    onClick = { focus.clearFocus(); openMaps(parishQuery, "") },
                    modifier = Modifier.padding(horizontal = design.spacing.gutter),
                    icon = Icons.Outlined.NearMe,
                    tag = "gps_nearby_maps_button"
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    Modifier.padding(horizontal = design.spacing.gutter + 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.OpenInNew, null, tint = p.textSecondary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(
                        strings("Se abre en Google Maps", "Opens in Google Maps", "Abre no Google Maps"),
                        style = styles.footnote,
                        color = p.textSecondary
                    )
                }
                if (failed) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        strings("No se pudo abrir Google Maps.", "Could not open Google Maps.", "Não foi possível abrir o Google Maps."),
                        style = styles.footnote,
                        color = p.textSecondary,
                        modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp).testTag("parish_maps_error")
                    )
                }
                Spacer(Modifier.height(design.spacing.sectionGap))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("parish_quick_chips"),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    quick.forEach { (label, query) ->
                        AppKitChip(
                            label = "$label ↗",
                            selected = false,
                            onClick = { focus.clearFocus(); openMaps(query, placeQuery.trim()) }
                        )
                    }
                }
            }
        }
    }
}
