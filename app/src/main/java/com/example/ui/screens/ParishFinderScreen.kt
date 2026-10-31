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
import androidx.compose.material.icons.filled.Map
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.location.DeviceLocationProvider
import com.example.data.location.ParishFinder
import com.example.data.location.ParishLookupResult
import com.example.data.models.AppLanguage
import com.example.data.models.NearbyParish
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.withTimeoutOrNull

private enum class SearchState { IDLE, LOADING, DONE }

private val RadiusOptionsKm = listOf(2, 5, 10, 25, 50)

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf.
 */
@Composable
private fun segmentedTabsInset(): Dp {
    val design = LocalAppDesign.current
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}

/** The longest the screen will show its spinner before giving up. */
private const val UI_TIMEOUT_MS = 22_000L

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

    fun openNearbyInMaps(near: String? = null) {
        val what = strings("parroquia católica", "catholic church", "igreja católica")
        val query = Uri.encode(if (near.isNullOrBlank()) what else "$what $near")
        val opened = runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query")))
        }.isSuccess || runCatching {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/$query"))
            )
        }.isSuccess
        if (!opened) {
            message = strings(
                "No se pudo abrir Google Maps.",
                "Could not open Google Maps.",
                "Não foi possível abrir o Google Maps."
            )
        }
    }

    fun runLookup(latitude: Double, longitude: Double, label: String?) {
        state = SearchState.LOADING
        message = null
        searchedPlace = label
        scope.launch {
            // Whatever happens below — success, failure, an exception — the
            // spinner must end. It previously stayed on "Searching" for good if
            // anything threw before the last line.
            val outcome = try {
                withTimeoutOrNull(UI_TIMEOUT_MS) { finder.findNearby(latitude, longitude, radiusKm) }
                    ?: ParishLookupResult.Failure("timed out")
            } catch (e: CancellationException) {
                state = SearchState.DONE
                throw e
            } catch (e: Exception) {
                ParishLookupResult.Failure(e.message ?: "error")
            }
            when (outcome) {
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
                        "El directorio en línea no respondió. Usa «Parroquias cerca de mí» para buscar en Google Maps.",
                        "The online directory did not answer. Use “Parishes near me” to search in Google Maps.",
                        "O diretório online não respondeu. Use «Paróquias perto de mim» para buscar no Google Maps."
                    )
                }
            }
            state = SearchState.DONE
        }
    }

    fun useDeviceLocation() {
        // Set immediately rather than inside runLookup: reading the last known
        // fix can itself take a moment, and without this the button looked
        // dead until the network call started.
        state = SearchState.LOADING
        message = null
        scope.launch {
            val location = runCatching { locationProvider.lastKnownLocation() }.getOrNull()
            if (location == null) {
                message = strings(
                    "El teléfono aún no tiene tu ubicación. Activa la ubicación, o usa «Parroquias cerca de mí», que la obtiene a través de Google Maps.",
                    "The phone has no location fix yet. Turn on location, or use “Parishes near me”, which gets it through Google Maps.",
                    "O telefone ainda não tem a sua localização. Ative a localização, ou use «Paróquias perto de mim», que a obtém pelo Google Maps."
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
            val place = runCatching { finder.geocode(placeQuery) }.getOrNull()
            if (place == null) {
                state = SearchState.DONE
                openNearbyInMaps(placeQuery)
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
                // Google Maps already knows where the phone is and has the most
                // complete church listings, so handing it the search is the
                // reliable way to find a parish nearby. The in-app list below
                // depends on community-run OpenStreetMap servers, which are often
                // slow to answer from a phone.
                AppPrimaryButton(
                    label = strings(
                        "Parroquias cerca de mí",
                        "Parishes near me",
                        "Paróquias perto de mim"
                    ),
                    onClick = { openNearbyInMaps() },
                    leadingIcon = Icons.Default.Map,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("gps_nearby_maps_button")
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                AppSecondaryButton(
                    label = strings("Ver lista aquí", "List them here", "Ver lista aqui"),
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
                // Only once there is a place to search: a disabled button
                // under an empty field reads as a second field.
                if (placeQuery.isNotBlank()) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppSecondaryButton(
                        label = strings(
                            "Buscar «${placeQuery.trim()}»",
                            "Search “${placeQuery.trim()}”",
                            "Buscar «${placeQuery.trim()}»"
                        ),
                        onClick = { searchPlace() },
                        leadingIcon = Icons.Default.Search,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("parish_search_place_button")
                    )
                }
            }
        }

        item {
            AppSectionHeader(
                title = strings("Radio de búsqueda", "Search radius", "Raio de busca"),
                subtitle = strings(
                    "Hasta dónde buscar alrededor del lugar.",
                    "How far around the place to look.",
                    "Até onde buscar em volta do lugar."
                )
            )
            // Five short options: a segmented control holds them abreast in
            // every identity, where a chip row ran its last chip off the edge.
            AppSegmentedTabs(
                options = RadiusOptionsKm.map { "$it km" },
                selectedIndex = RadiusOptionsKm.indexOf(radiusKm).coerceAtLeast(0),
                onSelect = { radiusKm = RadiusOptionsKm[it] },
                modifier = Modifier
                    .padding(horizontal = segmentedTabsInset())
                    .testTag("parish_radius_selector")
            )
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
                        color = design.palette.accent
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
