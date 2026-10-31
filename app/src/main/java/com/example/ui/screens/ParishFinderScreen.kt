package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Schedule
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
import com.example.data.repository.ParishScheduleText
import com.example.ui.components.TextFormat
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

private val RadiusOptionsKm = listOf(5, 10, 25)

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
 * names, each with its distance and Sunday Mass where known. The bundled
 * directory of great churches lives in Churches & News and is linked, not
 * repeated; Google Maps is the fallback when the online lookup fails.
 */
@Composable
fun ParishFinderScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpenDirectory: () -> Unit = {}
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
    var expandedParishId by remember { mutableStateOf<String?>(null) }

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
                        "El directorio en línea no respondió. Google Maps puede buscar por ti.",
                        "The online directory did not answer. Google Maps can search for you.",
                        "O diretório online não respondeu. O Google Maps pode buscar por você."
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
                    "El teléfono aún no tiene tu ubicación. Activa la ubicación o busca en Google Maps.",
                    "The phone has no location fix yet. Turn on location, or search in Google Maps.",
                    "O telefone ainda não tem a sua localização. Ative a localização ou busque no Google Maps."
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

    fun nearMe() {
        if (locationProvider.hasPermission()) {
            useDeviceLocation()
        } else {
            permissionLauncher.launch(DeviceLocationProvider.PERMISSIONS)
        }
    }

    LazyColumn(Modifier.fillMaxWidth().testTag("parish_finder_screen")) {
        item {
            AppScreenTitle(
                title = strings("Parroquias", "Parishes", "Paróquias"),
                subtitle = strings(
                    "Misa cerca de ti o en cualquier lugar",
                    "Mass near you, or anywhere",
                    "Missa perto de você ou em qualquer lugar"
                )
            )
        }

        // Search sits in the gutter like every other search in the app, not
        // nested in a card; the one location action follows it.
        item {
            AppSearchField(
                value = placeQuery,
                onValueChange = { placeQuery = it },
                placeholder = strings(
                    "Buscar una ciudad o un lugar",
                    "Search a city or place",
                    "Buscar uma cidade ou lugar"
                ),
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("parish_place_field")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                if (placeQuery.isNotBlank()) {
                    AppPrimaryButton(
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
                } else {
                    // One "near me": the phone's own fix when it has one, with
                    // Google Maps offered only if that fails.
                    AppPrimaryButton(
                        label = strings(
                            "Parroquias cerca de mí",
                            "Parishes near me",
                            "Paróquias perto de mim"
                        ),
                        onClick = { nearMe() },
                        leadingIcon = Icons.Default.MyLocation,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("gps_nearby_maps_button")
                    )
                }
            }
        }

        item {
            AppSectionHeader(
                title = strings("Distancia", "Distance", "Distância")
            )
            AppSegmentedTabs(
                options = RadiusOptionsKm.map { "$it km" },
                selectedIndex = RadiusOptionsKm.indexOf(radiusKm).coerceAtLeast(0),
                onSelect = { radiusKm = RadiusOptionsKm[it] },
                modifier = Modifier
                    .padding(horizontal = segmentedTabsInset())
                    .testTag("parish_radius_selector")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
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
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppSecondaryButton(
                        label = strings("Abrir Google Maps", "Open Google Maps", "Abrir o Google Maps"),
                        onClick = { openNearbyInMaps(placeQuery.takeIf { it.isNotBlank() }) },
                        leadingIcon = Icons.Default.Map,
                        modifier = Modifier.testTag("use_my_location_button")
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        if (results.isEmpty()) {
            if (state != SearchState.LOADING && message == null) {
                item {
                    AppEmptyState(
                        title = strings(
                            "Encuentra misa cerca",
                            "Find Mass nearby",
                            "Encontre missa por perto"
                        ),
                        body = strings(
                            "Cada parroquia aparece con su distancia y, cuando se conoce, su próxima misa.",
                            "Each parish is listed with its distance and, where known, its next Mass.",
                            "Cada paróquia aparece com a sua distância e, quando conhecida, a próxima missa."
                        ),
                        icon = Icons.Default.Church
                    )
                }
            }
            // The bundled directory of cathedrals and basilicas lives in
            // Churches & News; it is linked here rather than listed twice.
            item {
                AppListGroup {
                    AppListRow(
                        title = strings(
                            "Directorio de iglesias",
                            "Church directory",
                            "Diretório de igrejas"
                        ),
                        subtitle = strings(
                            "Catedrales, basílicas y santuarios por país",
                            "Cathedrals, basilicas and shrines by country",
                            "Catedrais, basílicas e santuários por país"
                        ),
                        leadingIcon = Icons.Default.Public,
                        onClick = onOpenDirectory,
                        modifier = Modifier.testTag("parish_open_directory")
                    )
                }
            }
        } else {
            item {
                AppSectionHeader(
                    title = strings("Cerca de ti", "Nearby", "Perto de você"),
                    subtitle = searchedPlace
                )
            }
            // Each parish is its name, its street and its next Mass, with the
            // distance trailing; a tap opens Mass times, telephone and
            // directions in place.
            item(key = "parish_list") {
                AppListGroup(Modifier.testTag("parish_list")) {
                    results.forEach { parish ->
                        val open = expandedParishId == parish.id
                        val masses = parish.sundayMasses.takeIf { it.isNotBlank() }?.let {
                            strings("Misa del domingo", "Sunday Mass", "Missa de domingo") + " · " +
                                TextFormat.times(ParishScheduleText.masses(it, currentLanguage), currentLanguage)
                        }
                        AppListRow(
                            title = parish.name,
                            subtitle = listOfNotNull(
                                streetOf(parish.address).ifBlank { parish.city }.ifBlank { null },
                                masses
                            ).joinToString("\n").ifBlank { null },
                            trailingText = parish.distanceDisplay.takeIf { it.any(Char::isDigit) },
                            selected = open,
                            onClick = { expandedParishId = if (open) null else parish.id },
                            modifier = Modifier.testTag("parish_row_${parish.id}")
                        )
                        AnimatedVisibility(
                            visible = open,
                            enter = expandVertically(
                                animationSpec = tween(design.motion.mediumMillis, easing = design.motion.emphasizedEasing)
                            ) + fadeIn(tween(design.motion.mediumMillis)),
                            exit = shrinkVertically(
                                animationSpec = tween(design.motion.fastMillis, easing = design.motion.standardEasing)
                            ) + fadeOut(tween(design.motion.fastMillis))
                        ) {
                            Column {
                                if (parish.contactPhone.isNotBlank()) {
                                    AppListRow(
                                        title = strings("Llamar", "Call", "Ligar"),
                                        subtitle = parish.contactPhone,
                                        leadingIcon = Icons.Default.Phone,
                                        onClick = {
                                            runCatching {
                                                context.startActivity(
                                                    Intent(
                                                        Intent.ACTION_DIAL,
                                                        Uri.parse("tel:${parish.contactPhone.replace(" ", "")}")
                                                    )
                                                )
                                            }
                                        },
                                        modifier = Modifier.testTag("parish_call_${parish.id}")
                                    )
                                }
                                AppListRow(
                                    title = strings("Cómo llegar", "Directions", "Como chegar"),
                                    subtitle = parish.address.ifBlank { null },
                                    leadingIcon = Icons.Default.LocationOn,
                                    onClick = {
                                        val uri = Uri.parse(
                                            "geo:${parish.latitude},${parish.longitude}?q=" +
                                                Uri.encode(parish.name)
                                        )
                                        runCatching {
                                            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                                        }
                                    },
                                    modifier = Modifier.testTag("parish_directions_${parish.id}")
                                )
                            }
                        }
                    }
                    AppListRow(
                        title = strings("Ver más en Google Maps", "More in Google Maps", "Ver mais no Google Maps"),
                        leadingIcon = Icons.Default.Map,
                        onClick = { openNearbyInMaps(searchedPlace) }
                    )
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/** "Calle de Bailén, 10, Centro, 28013 Madrid" → "Calle de Bailén, 10". */
private fun streetOf(address: String): String =
    address.split(',').map { it.trim() }.filter { it.isNotEmpty() }.take(2).joinToString(", ")
