package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.location.DeviceLocationProvider
import com.example.data.location.ParishFinder
import com.example.data.location.ParishLookupResult
import com.example.data.models.AppLanguage
import com.example.data.models.NearbyParish
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import kotlinx.coroutines.launch

private enum class SearchState { IDLE, LOADING, DONE }

/**
 * Finds Catholic parishes, either around the device or around a place the user
 * names. The bundled directory is shown as a starting point and remains the
 * fallback when there is no network or no location.
 */
@Composable
fun ParishFinderScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val scope = rememberCoroutineScope()

    val finder = remember { ParishFinder() }
    val locationProvider = remember { DeviceLocationProvider(context) }

    var placeQuery by remember { mutableStateOf("") }
    var radiusKm by remember { mutableStateOf(10) }
    var state by remember { mutableStateOf(SearchState.IDLE) }
    var results by remember { mutableStateOf<List<NearbyParish>>(emptyList()) }
    var message by remember { mutableStateOf<String?>(null) }
    var searchedPlace by remember { mutableStateOf<String?>(null) }

    fun strings(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    fun runLookup(latitude: Double, longitude: Double, label: String?) {
        state = SearchState.LOADING
        message = null
        searchedPlace = label
        scope.launch {
            when (val outcome = finder.findNearby(latitude, longitude, radiusKm)) {
                is ParishLookupResult.Success -> {
                    results = outcome.parishes
                    message = null
                }
                is ParishLookupResult.Empty -> {
                    results = emptyList()
                    message = strings(
                        "No se encontraron parroquias en ${outcome.searchedRadiusKm} km. Prueba un radio mayor.",
                        "No parishes found within ${outcome.searchedRadiusKm} km. Try a wider radius.",
                        "Nenhuma paróquia encontrada em ${outcome.searchedRadiusKm} km. Tente um raio maior."
                    )
                }
                is ParishLookupResult.Failure -> {
                    results = emptyList()
                    message = strings(
                        "No se pudo consultar el directorio (${outcome.reason}). Mostrando el directorio incluido.",
                        "Could not reach the directory (${outcome.reason}). Showing the bundled directory.",
                        "Não foi possível consultar o diretório (${outcome.reason}). Mostrando o diretório incluído."
                    )
                }
            }
            state = SearchState.DONE
        }
    }

    fun useDeviceLocation() {
        scope.launch {
            val location = locationProvider.lastKnownLocation()
            if (location == null) {
                message = strings(
                    "Sin ubicación disponible. Activa la ubicación o busca un lugar por nombre.",
                    "No location available. Turn on location or search for a place by name.",
                    "Sem localização disponível. Ative a localização ou busque um lugar pelo nome."
                )
                state = SearchState.DONE
                return@launch
            }
            runLookup(
                location.latitude,
                location.longitude,
                strings("Tu ubicación", "Your location", "Sua localização")
            )
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { granted ->
        if (granted.values.any { it }) {
            useDeviceLocation()
        } else {
            message = strings(
                "Permiso de ubicación denegado. Puedes buscar un lugar por nombre.",
                "Location permission denied. You can search for a place by name instead.",
                "Permissão de localização negada. Você pode buscar um lugar pelo nome."
            )
            state = SearchState.DONE
        }
    }

    fun searchPlace() {
        if (placeQuery.isBlank()) return
        state = SearchState.LOADING
        message = null
        scope.launch {
            val place = finder.geocode(placeQuery)
            if (place == null) {
                message = strings(
                    "No se encontró ese lugar. Prueba «Ciudad, Provincia, País».",
                    "That place was not found. Try “City, State, Country”.",
                    "Lugar não encontrado. Tente “Cidade, Estado, País”."
                )
                state = SearchState.DONE
                return@launch
            }
            runLookup(place.latitude, place.longitude, place.displayName)
        }
    }

    val shown = if (results.isNotEmpty()) results else repository.nearbyParishes

    LazyColumn(Modifier.fillMaxWidth().testTag("parish_finder_screen")) {
        item {
            AppScreenTitle(
                title = strings("Parroquias", "Parishes", "Paróquias"),
                subtitle = strings(
                    "Encuentra una iglesia católica cerca de ti o en cualquier lugar del mundo.",
                    "Find a Catholic church near you, or anywhere in the world.",
                    "Encontre uma igreja católica perto de você, ou em qualquer lugar."
                )
            )
        }

        item {
            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                AppPrimaryButton(
                    label = strings("Usar mi ubicación", "Use my location", "Usar minha localização"),
                    onClick = {
                        if (locationProvider.hasPermission()) {
                            useDeviceLocation()
                        } else {
                            permissionLauncher.launch(DeviceLocationProvider.PERMISSIONS)
                        }
                    },
                    leadingIcon = Icons.Default.MyLocation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("use_my_location_button")
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSearchField(
                    value = placeQuery,
                    onValueChange = { placeQuery = it },
                    placeholder = strings(
                        "Ciudad, provincia, país",
                        "City, state, country",
                        "Cidade, estado, país"
                    ),
                    modifier = Modifier.testTag("parish_place_field")
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSecondaryButton(
                    label = strings("Buscar este lugar", "Search this place", "Buscar este lugar"),
                    onClick = { searchPlace() },
                    enabled = placeQuery.isNotBlank(),
                    leadingIcon = Icons.Default.Search,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            AppSectionHeader(title = strings("Radio", "Radius", "Raio"))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(2, 5, 10, 25, 50).forEach { km ->
                    AppChip(
                        label = "$km km",
                        selected = radiusKm == km,
                        onClick = { radiusKm = km }
                    )
                }
            }
        }

        if (state == SearchState.LOADING) {
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(design.spacing.gutter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.size(12.dp))
                    Text(
                        text = strings("Buscando…", "Searching…", "Buscando…"),
                        style = MaterialTheme.typography.bodyMedium,
                        color = design.palette.textSecondary
                    )
                }
            }
        }

        message?.let { text ->
            item {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = design.palette.textSecondary
                    )
                }
            }
        }

        item {
            AppSectionHeader(
                title = if (results.isNotEmpty()) {
                    strings("Resultados", "Results", "Resultados")
                } else {
                    strings("Directorio incluido", "Bundled directory", "Diretório incluído")
                },
                subtitle = searchedPlace?.takeIf { results.isNotEmpty() }
            )
        }

        if (shown.isEmpty()) {
            item {
                AppEmptyState(
                    title = strings("Sin parroquias", "No parishes", "Sem paróquias"),
                    body = strings(
                        "Busca por ubicación o por nombre de lugar.",
                        "Search by location or by place name.",
                        "Busque por localização ou por nome de lugar."
                    ),
                    icon = Icons.Default.Church
                )
            }
        } else {
            items(shown, key = { it.id }) { parish ->
                AppListGroup {
                    AppListRow(
                        title = parish.name,
                        subtitle = listOf(parish.diocese, parish.address)
                            .filter { it.isNotBlank() }
                            .joinToString(" · ")
                            .ifBlank { null },
                        leadingIcon = Icons.Default.Church,
                        trailingText = parish.distanceDisplay.ifBlank { null },
                        onClick = {
                            val uri = Uri.parse(
                                "geo:${parish.latitude},${parish.longitude}?q=" +
                                    Uri.encode(parish.name)
                            )
                            runCatching {
                                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                            }
                        }
                    )
                    if (parish.sundayMasses.isNotBlank()) {
                        AppListRow(
                            title = strings("Misas", "Mass times", "Missas"),
                            subtitle = parish.sundayMasses,
                            leadingIcon = Icons.Default.LocationOn,
                            showsNavigationAffordance = false
                        )
                    }
                    if (parish.contactPhone.isNotBlank()) {
                        AppListRow(
                            title = parish.contactPhone,
                            leadingIcon = Icons.Default.Phone,
                            showsNavigationAffordance = false,
                            onClick = {
                                runCatching {
                                    context.startActivity(
                                        Intent(Intent.ACTION_DIAL, Uri.parse("tel:${parish.contactPhone}"))
                                    )
                                }
                            }
                        )
                    }
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}
