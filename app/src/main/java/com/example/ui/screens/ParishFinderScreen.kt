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
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
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
import com.example.data.repository.ParishScheduleText
import com.example.ui.components.TextFormat
import com.example.ui.design.AppPrimaryButton
import com.example.ui.components.InAppBrowserDialog
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min

private enum class SearchState { IDLE, LOADING, DONE }

private val RadiusOptionsKm = listOf(5, 10, 25)

/** Minimum touch target for every control this screen owns (AAA). */
private val MinTouchTarget = 48.dp

/** The longest the screen will show its spinner before giving up. */
private const val UI_TIMEOUT_MS = 22_000L

/**
 * Finds Catholic parishes, either around the device or around a place the user
 * names, each with its distance and Sunday Mass where known. The bundled
 * directory of great churches lives in Churches & News and is linked, not
 * repeated; when the online lookup fails or finds nothing, Google Maps is
 * launched automatically, and when it does find parishes an OpenStreetMap
 * embed (in-app, via [InAppBrowserDialog]) shows them on a map.
 *
 * AAA M3 treatment, kept inside the shared kit on purpose: the screen paints
 * with nothing but [LocalAppDesign] tokens (canvas, spacing, shape via the kit
 * components) and lets the active identity resolve tonal surfaces, icon style
 * and the dark palette. No hardcoded colour or dimension, so dark mode is the
 * token's job, not this file's. Every tappable row, button, chip and the
 * search field carries a 48dp floor — `maxOf(rowMinHeight, 48.dp)` for rows —
 * so a one-line control never shrinks below the AAA touch target even under
 * the Apple identity's 44dp token, and the kit's own 32dp filter chips for
 * the radius are lifted the same way.
 */
@Composable
fun ParishFinderScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpenDirectory: () -> Unit = {}
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val palette = design.palette
    val rowMinHeight = maxOf(design.spacing.rowMinHeight, MinTouchTarget)
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
    // Coordinates/radius of the last lookup, so a failed or empty lookup can
    // hand Google Maps the exact spot and the OSM embed can frame it.
    var lastLatitude by remember { mutableStateOf<Double?>(null) }
    var lastLongitude by remember { mutableStateOf<Double?>(null) }
    var lastRadiusKm by remember { mutableStateOf(10) }
    var showOsmMap by remember { mutableStateOf(false) }

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

    /**
     * Google Maps at exact coordinates: a `geo:` intent with an
     * 'iglesia católica'/'parroquia' query near the point first, then the
     * same query on the web. Returns whether anything opened. When both
     * coordinates are null it falls back to a generic nearby search.
     */
    fun openMapsAt(latitude: Double?, longitude: Double?): Boolean {
        val query = if (latitude != null && longitude != null) {
            Uri.encode("iglesia católica parroquia cerca de $latitude,$longitude")
        } else {
            Uri.encode("iglesia católica parroquia")
        }
        val geo = if (latitude != null && longitude != null) "geo:$latitude,$longitude?q=$query" else "geo:0,0?q=$query"
        return runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(geo)))
        }.isSuccess || runCatching {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/$query"))
            )
        }.isSuccess
    }

    /**
     * OpenStreetMap embed URL framing every result (plus the searched
     * point), with a marker on the nearest parish. Null when there are no
     * OSM results — callers use the Google Maps auto-launch instead.
     * OSM's embed takes a single `marker`, so the full set remains in the
     * list below the map.
     */
    fun osmEmbedUrl(): String? {
        if (results.isEmpty()) return null
        val lat = lastLatitude ?: results.first().latitude
        val lon = lastLongitude ?: results.first().longitude
        val latPad = max(lastRadiusKm / 111.0, 0.02)
        val lonPad = max(lastRadiusKm / (111.0 * max(cos(Math.toRadians(lat)), 0.2)), 0.02)
        val minLat = min(results.minOf { it.latitude }, lat - latPad)
        val maxLat = max(results.maxOf { it.latitude }, lat + latPad)
        val minLon = min(results.minOf { it.longitude }, lon - lonPad)
        val maxLon = max(results.maxOf { it.longitude }, lon + lonPad)
        val first = results.first()
        return "https://www.openstreetmap.org/export/embed.html" +
            "?bbox=$minLon,$minLat,$maxLon,$maxLat&layer=mapnik" +
            "&marker=${first.latitude},${first.longitude}"
    }

    fun callParish(parish: NearbyParish) {
        val number = parish.contactPhone.replace(" ", "")
        val opened = runCatching {
            context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
        }.isSuccess || runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:$number")))
        }.isSuccess
        if (!opened) {
            message = strings(
                "No se pudo abrir el teléfono.",
                "Could not open the phone app.",
                "Não foi possível abrir o telefone."
            )
        }
    }

    fun openDirections(parish: NearbyParish) {
        val geo = Uri.parse(
            "geo:${parish.latitude},${parish.longitude}?q=" + Uri.encode(parish.name)
        )
        val web = Uri.parse(
            "https://www.google.com/maps/search/" +
                Uri.encode("${parish.name} ${parish.address}".trim())
        )
        val opened = runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, geo))
        }.isSuccess || runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, web))
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
        lastLatitude = latitude
        lastLongitude = longitude
        lastRadiusKm = radiusKm
        showOsmMap = false
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
                        "No se encontraron parroquias en ${outcome.searchedRadiusKm} km. Prueba un radio mayor. Se abrió Google Maps para buscar cerca de ti.",
                        "No parishes found within ${outcome.searchedRadiusKm} km. Try a wider radius. Google Maps was opened to search near you.",
                        "Nenhuma paróquia encontrada em ${outcome.searchedRadiusKm} km. Tente um raio maior. O Google Maps foi aberto para buscar perto de você."
                    )
                }
                is ParishLookupResult.Failure -> {
                    results = emptyList()
                    message = strings(
                        "El directorio en línea no respondió. Se abrió Google Maps para buscar por ti.",
                        "The online directory did not answer. Google Maps was opened to search for you.",
                        "O diretório online não respondeu. O Google Maps foi aberto para buscar por você."
                    )
                }
            }
            state = SearchState.DONE
            // The online directory failed or returned nothing: hand over to
            // Google Maps automatically instead of only offering it.
            if (outcome !is ParishLookupResult.Success) {
                openMapsAt(latitude, longitude)
            }
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
                    "El teléfono aún no tiene tu ubicación. Activa la ubicación. Se abrió Google Maps para buscar parroquias.",
                    "The phone has no location fix yet. Turn on location. Google Maps was opened to search for parishes.",
                    "O telefone ainda não tem a sua localização. Ative a localização. O Google Maps foi aberto para buscar paróquias."
                )
                state = SearchState.DONE
                openMapsAt(null, null)
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

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(palette.canvas)
            .testTag("parish_finder_screen")
    ) {
        item(key = "parish_header") {
            // Photographic hero (church window light) with fade; title rides the fade.
            PhotoHeader(
                title = strings("Parroquias", "Parishes", "Paróquias"),
                asset = "heroes/church_window_light.jpg"
            )
        }

        // Search sits in the gutter like every other search in the app, not
        // nested in a card; the one location action follows it.
        item(key = "parish_search") {
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
                    .heightIn(min = MinTouchTarget)
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
                        enabled = state != SearchState.LOADING,
                        leadingIcon = Icons.Default.Search,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
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
                        enabled = state != SearchState.LOADING,
                        leadingIcon = Icons.Default.MyLocation,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .testTag("gps_nearby_maps_button")
                    )
                }
            }
        }

        item(key = "parish_radius") {
            AppSectionHeader(
                title = strings("Distancia", "Distance", "Distância")
            )
            // A radius is a single-select filter value, so M3 draws it as a
            // filter-chip row rather than a segmented control (the kit's own
            // rule for numeric sets) — and the kit chips are 32dp in Material
            // and 44dp in Apple, so each is lifted to the 48dp floor here.
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("parish_radius_selector"),
                horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
            ) {
                RadiusOptionsKm.forEach { option ->
                    AppChip(
                        label = "$option km",
                        selected = option == radiusKm,
                        onClick = { radiusKm = option },
                        modifier = Modifier.heightIn(min = MinTouchTarget)
                    )
                }
            }
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        if (state == SearchState.LOADING) {
            val searchingText = strings("Buscando…", "Searching…", "Buscando…")
            item(key = "parish_loading") {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(design.spacing.gutter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(design.decor.rowIconSize)
                            .semantics { contentDescription = searchingText }
                            .testTag("parish_loading"),
                        color = palette.accent
                    )
                    Spacer(Modifier.size(design.spacing.iconLabelGap))
                    Text(
                        text = searchingText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                }
            }
        }

        message?.let { text ->
            item(key = "parish_message") {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppSecondaryButton(
                        label = strings("Abrir Google Maps", "Open Google Maps", "Abrir o Google Maps"),
                        onClick = {
                            if (lastLatitude != null && lastLongitude != null) {
                                openMapsAt(lastLatitude, lastLongitude)
                            } else {
                                openNearbyInMaps(placeQuery.takeIf { it.isNotBlank() })
                            }
                        },
                        leadingIcon = Icons.Default.Map,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .testTag("use_my_location_button")
                    )
                }
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        // Pre-search empty state, and the no-results state when the lookup
        // itself succeeded with nothing to show (the Empty/Failure outcomes
        // carry their own message card above, so nothing is said twice).
        if (results.isEmpty() && message == null && state != SearchState.LOADING) {
            item(key = "parish_empty_state") {
                AppEmptyState(
                    title = if (state == SearchState.IDLE) {
                        strings(
                            "Encuentra parroquias cerca de ti",
                            "Find parishes near you",
                            "Encontre paróquias perto de você"
                        )
                    } else {
                        strings(
                            "Sin resultados",
                            "No results",
                            "Sem resultados"
                        )
                    },
                    body = if (state == SearchState.IDLE) {
                        strings(
                            "Busca una ciudad o usa tu ubicación para ver las parroquias cercanas y sus Misas del domingo.",
                            "Search a city or use your location to see nearby parishes and their Sunday Masses.",
                            "Busque uma cidade ou use sua localização para ver as paróquias próximas e suas Missas de domingo."
                        )
                    } else {
                        strings(
                            "Prueba un radio mayor o abre Google Maps.",
                            "Try a wider radius or open Google Maps.",
                            "Tente um raio maior ou abra o Google Maps."
                        )
                    },
                    icon = Icons.Default.Church
                )
            }
        }

        if (results.isEmpty()) {
            // The bundled directory of cathedrals and basilicas lives in
            // Churches & News; it is linked here rather than listed twice.
            item(key = "parish_directory") {
                AppListGroup {
                    AppListRow(
                        title = strings(
                            "Directorio de iglesias",
                            "Church directory",
                            "Diretório de igrejas"
                        ),
                        leadingIcon = Icons.Default.Public,
                        onClick = onOpenDirectory,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = rowMinHeight)
                            .testTag("parish_open_directory")
                    )
                }
            }
        } else {
            val countLine = strings(
                "${results.size} parroquias",
                "${results.size} parishes",
                "${results.size} paróquias"
            )
            item(key = "parish_results_header") {
                AppSectionHeader(
                    title = strings("Cerca de ti", "Nearby", "Perto de você"),
                    subtitle = listOfNotNull(searchedPlace, countLine)
                        .joinToString(" · ").ifBlank { null }
                )
            }
            if (osmEmbedUrl() != null) {
                item(key = "parish_osm_map") {
                    AppSecondaryButton(
                        label = strings(
                            "Ver en el mapa (OpenStreetMap)",
                            "View on map (OpenStreetMap)",
                            "Ver no mapa (OpenStreetMap)"
                        ),
                        onClick = {
                            // Results exist, so the embed has markers; if
                            // they somehow vanished, fall back to Maps.
                            if (osmEmbedUrl() != null) showOsmMap = true
                            else openMapsAt(lastLatitude, lastLongitude)
                        },
                        leadingIcon = Icons.Default.Map,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                            .padding(horizontal = design.spacing.gutter)
                            .testTag("parish_osm_map_button")
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                }
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
                            // The tap expands rather than navigates, so the
                            // open/closed state is set as text alongside the
                            // kit's selected fill.
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = rowMinHeight)
                                .semantics {
                                    stateDescription = if (open) {
                                        strings("Expandida", "Expanded", "Expandida")
                                    } else {
                                        strings("Contraída", "Collapsed", "Recolhida")
                                    }
                                }
                                .testTag("parish_row_${parish.id}")
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
                                        showsNavigationAffordance = false,
                                        onClick = { callParish(parish) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .heightIn(min = rowMinHeight)
                                            .testTag("parish_call_${parish.id}")
                                    )
                                }
                                AppListRow(
                                    title = strings("Cómo llegar", "Directions", "Como chegar"),
                                    subtitle = parish.address.ifBlank { null },
                                    leadingIcon = Icons.Default.LocationOn,
                                    showsNavigationAffordance = false,
                                    onClick = { openDirections(parish) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = rowMinHeight)
                                        .testTag("parish_directions_${parish.id}")
                                )
                            }
                        }
                    }
                    AppListRow(
                        title = strings("Ver más en Google Maps", "More in Google Maps", "Ver mais no Google Maps"),
                        leadingIcon = Icons.Default.Map,
                        onClick = { openNearbyInMaps(searchedPlace) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = rowMinHeight)
                    )
                }
            }
        }

        item(key = "parish_bottom_gap") {
            Spacer(Modifier.height(design.spacing.contentBottomGap))
        }
    }

    // In-app OpenStreetMap embed with the parish markers. With no OSM
    // results there is nothing to frame, so that path never reaches here —
    // the Google Maps auto-launch in runLookup covers it instead.
    if (showOsmMap) {
        osmEmbedUrl()?.let { url ->
            InAppBrowserDialog(url = url, onDismiss = { showOsmMap = false })
        }
    }
}

/** "Calle de Bailén, 10, Centro, 28013 Madrid" → "Calle de Bailén, 10". */
private fun streetOf(address: String): String =
    address.split(',').map { it.trim() }.filter { it.isNotEmpty() }.take(2).joinToString(", ")

// ------------------------------------------------------ bundled hero photos

/** Photographic hero band height (Prayers/Doctrine idiom). */
private val PhotoBandHeight = 220.dp

/** Bundled hero photos are decoded at most this many pixels on a side. */
private const val HeroMaxDimension = 1080

/** Ive screen gutter: the identity token already resolves to 20dp. */
private val IveGutter = 20.dp

/**
 * Decodes a bundled assets/heroes photo once per assetPath, sampled down
 * to at most HeroMaxDimension px on either side. Returns null when the
 * asset is missing or unreadable; callers keep their tonal ground.
 */
@Composable
private fun rememberHeroBitmap(assetPath: String): ImageBitmap? {
    val context = LocalContext.current
    return remember(assetPath) {
        try {
            val bytes = context.assets.open(assetPath).use { it.readBytes() }
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bounds)
            if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
                null
            } else {
                var sample = 1
                while (
                    bounds.outWidth / sample > HeroMaxDimension ||
                    bounds.outHeight / sample > HeroMaxDimension
                ) {
                    sample *= 2
                }
                val options = BitmapFactory.Options().apply { inSampleSize = sample }
                BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)?.asImageBitmap()
            }
        } catch (_: Exception) {
            null
        }
    }
}

/**
 * Photographic hero with fade: a full-bleed bundled photo band that melts
 * into the canvas at its foot, with the serene screen title overlaid on the
 * fade so photo and word share one block (Prayers/Home idiom). Replaces
 * the vector header art for every identity: the photo reads in Ive and Apple.
 */
@Composable
private fun PhotoHeader(title: String, asset: String) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current
    val bitmap = rememberHeroBitmap(asset)
    Box(
        Modifier
            .fillMaxWidth()
            .height(PhotoBandHeight)
            .background(palette.canvas)
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("screen_header_illustration")
            )
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(palette.selectionTint)
                    .testTag("screen_header_illustration")
            )
        }
        // Fade: clear over the upper photo, canvas by the foot of the
        // band, so the hero dissolves into the page.
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            palette.canvas
                        )
                    )
                )
        )
        // Light scrim behind the title zone: the title stays
        // palette.textPrimary, so the photo is lightened to the paper
        // canvas under it rather than darkened.
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            palette.canvas.copy(alpha = 0.55f),
                            palette.canvas.copy(alpha = 0.92f),
                            palette.canvas
                        )
                    )
                )
        )
        // Title overlaid at BottomStart with gutter + 24dp vertical
        // padding, Home-style.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(
                    horizontal = design.spacing.gutter,
                    vertical = 24.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = styles.screenTitle.copy(fontWeight = FontWeight.W400),
                color = palette.textPrimary,
                maxLines = 1
            )
        }
    }
}
