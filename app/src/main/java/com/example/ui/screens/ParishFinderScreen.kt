package com.example.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Directions
import androidx.compose.material.icons.outlined.LocationOff
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.data.location.DeviceLocationProvider
import com.example.data.location.ParishFinder
import com.example.data.location.ParishLookupResult
import com.example.data.models.AppLanguage
import com.example.data.models.NearbyParish
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ParishScheduleText
import com.example.ui.components.InAppBrowserDialog
import com.example.ui.components.TextFormat
import com.example.ui.design.AppChevron
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppKitChip
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.appDockInset
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberFirstAsset
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min

private enum class SearchState { IDLE, LOADING, DONE }

/** What the state area is showing; the crossfade is keyed on it. */
private enum class AreaKind { IDLE, LOADING, NOTICE, RESULTS }

/** Everything that can go wrong or come back empty, each with its own words and actions. */
private sealed interface ParishNotice {
    data class Empty(val radiusKm: Int, val mapsOpened: Boolean) : ParishNotice
    data class Failure(val mapsOpened: Boolean) : ParishNotice
    data class NoFix(val mapsOpened: Boolean) : ParishNotice
    data class PlaceNotFound(val query: String, val mapsOpened: Boolean) : ParishNotice
    data class Denied(val permanent: Boolean) : ParishNotice
}

private val RadiusOptionsKm = listOf(5, 10, 25)

/** The longest the screen will show its spinner before giving up. */
private const val UI_TIMEOUT_MS = 22_000L

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

/**
 * Finds Catholic parishes, either around the device or around a place the user
 * names, each with its distance and Sunday Mass where known. The bundled
 * directory of great churches lives in Churches & News and is linked, not
 * repeated. When the online lookup fails or finds nothing, Google Maps is
 * launched automatically (the owner's choice), and the notice says so.
 *
 * No map tiles are fetched: the preview is drawn from the results themselves,
 * and the real map opens on demand as an OpenStreetMap embed.
 */
@Composable
fun ParishFinderScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onOpenDirectory: () -> Unit = {}
) {
    AppEdgeToEdge()
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val scope = rememberCoroutineScope()
    val focus = LocalFocusManager.current
    val dock = appDockInset()
    val hero = rememberFirstAsset("heroes/lumen/parishes.jpg", "heroes/church_window_light.jpg")

    val finder = remember { ParishFinder() }
    val locationProvider = remember { DeviceLocationProvider(context) }

    var placeQuery by rememberSaveable { mutableStateOf("") }
    var radiusKm by rememberSaveable { mutableStateOf(10) }
    var expandedParishId by rememberSaveable { mutableStateOf<String?>(null) }
    var state by remember { mutableStateOf(SearchState.IDLE) }
    var results by remember { mutableStateOf<List<NearbyParish>>(emptyList()) }
    var notice by remember { mutableStateOf<ParishNotice?>(null) }
    var inlineMessage by remember { mutableStateOf<String?>(null) }
    var searchedPlace by remember { mutableStateOf<String?>(null) }
    var loadingPlace by remember { mutableStateOf<String?>(null) }
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

    fun mapsUnavailable() {
        inlineMessage = strings(
            "No se pudo abrir Google Maps.",
            "Could not open Google Maps.",
            "Não foi possível abrir o Google Maps."
        )
    }

    /** Google Maps on a generic search, optionally near a named place. */
    fun openNearbyInMaps(near: String? = null): Boolean {
        val what = strings("parroquia católica", "catholic church", "igreja católica")
        val query = Uri.encode(if (near.isNullOrBlank()) what else "$what $near")
        return runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query")))
        }.isSuccess || runCatching {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/$query"))
            )
        }.isSuccess
    }

    /**
     * Google Maps at exact coordinates: a `geo:` intent with a church query
     * near the point first, then the same query on the web. Returns whether
     * anything opened. Without coordinates it is a generic nearby search.
     */
    fun openMapsAt(latitude: Double?, longitude: Double?): Boolean {
        val what = strings(
            "iglesia católica parroquia cerca de",
            "catholic church parish near",
            "igreja católica paróquia perto de"
        )
        val query = if (latitude != null && longitude != null) {
            Uri.encode("$what $latitude,$longitude")
        } else {
            Uri.encode(strings("iglesia católica parroquia", "catholic church parish", "igreja católica paróquia"))
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
     * OpenStreetMap embed URL framing every result (plus the searched point),
     * with a marker on the nearest parish. Null when there are no results.
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
        inlineMessage = if (opened) null else strings(
            "No se pudo abrir el teléfono.",
            "Could not open the phone app.",
            "Não foi possível abrir o telefone."
        )
    }

    fun openDirections(parish: NearbyParish) {
        val geo = Uri.parse("geo:${parish.latitude},${parish.longitude}?q=" + Uri.encode(parish.name))
        val web = Uri.parse(
            "https://www.google.com/maps/search/" + Uri.encode("${parish.name} ${parish.address}".trim())
        )
        val opened = runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, geo))
        }.isSuccess || runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, web))
        }.isSuccess
        if (!opened) mapsUnavailable() else inlineMessage = null
    }

    fun runLookup(latitude: Double, longitude: Double, label: String?, autoOpenMaps: Boolean) {
        state = SearchState.LOADING
        notice = null
        inlineMessage = null
        results = emptyList()
        expandedParishId = null
        searchedPlace = label
        loadingPlace = label
        lastLatitude = latitude
        lastLongitude = longitude
        lastRadiusKm = radiusKm
        showOsmMap = false
        val radius = radiusKm
        scope.launch {
            // Whatever happens below — success, failure, an exception — the
            // spinner must end.
            val outcome = try {
                withTimeoutOrNull(UI_TIMEOUT_MS) { finder.findNearby(latitude, longitude, radius) }
                    ?: ParishLookupResult.Failure("timed out")
            } catch (e: CancellationException) {
                state = SearchState.DONE
                throw e
            } catch (e: Exception) {
                ParishLookupResult.Failure(e.message ?: "error")
            }
            // The online directory failed or returned nothing: hand over to
            // Google Maps automatically instead of only offering it.
            val opened = outcome !is ParishLookupResult.Success && autoOpenMaps && openMapsAt(latitude, longitude)
            when (outcome) {
                is ParishLookupResult.Success -> {
                    results = outcome.parishes
                    notice = null
                }
                is ParishLookupResult.Empty -> {
                    results = emptyList()
                    notice = ParishNotice.Empty(outcome.searchedRadiusKm, opened)
                }
                is ParishLookupResult.Failure -> {
                    results = emptyList()
                    notice = ParishNotice.Failure(opened)
                }
            }
            state = SearchState.DONE
        }
    }

    fun useDeviceLocation() {
        // Set immediately: reading the last known fix can itself take a
        // moment, and without this the button looked dead.
        state = SearchState.LOADING
        notice = null
        inlineMessage = null
        results = emptyList()
        loadingPlace = null
        scope.launch {
            val location = runCatching { locationProvider.lastKnownLocation() }.getOrNull()
            if (location == null) {
                val opened = openMapsAt(null, null)
                notice = ParishNotice.NoFix(opened)
                state = SearchState.DONE
                return@launch
            }
            runLookup(
                location.latitude,
                location.longitude,
                strings("Tu ubicación", "Your location", "Sua localização"),
                autoOpenMaps = true
            )
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { granted ->
        if (granted.values.any { it }) {
            useDeviceLocation()
        } else {
            // After a denial the system stops offering the dialog once it will
            // no longer show a rationale; from then on only Settings helps.
            val activity = context.findActivity()
            val permanent = activity != null && !ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.ACCESS_FINE_LOCATION
            )
            results = emptyList()
            notice = ParishNotice.Denied(permanent)
            state = SearchState.DONE
        }
    }

    fun nearMe() {
        focus.clearFocus()
        if (locationProvider.hasPermission()) {
            useDeviceLocation()
        } else {
            permissionLauncher.launch(DeviceLocationProvider.PERMISSIONS)
        }
    }

    fun searchPlace() {
        val query = placeQuery.trim()
        if (query.isEmpty() || state == SearchState.LOADING) return
        focus.clearFocus()
        state = SearchState.LOADING
        notice = null
        inlineMessage = null
        results = emptyList()
        loadingPlace = query
        scope.launch {
            val place = runCatching { finder.geocode(query) }.getOrNull()
            if (place == null) {
                val opened = openNearbyInMaps(query)
                notice = ParishNotice.PlaceNotFound(query, opened)
                state = SearchState.DONE
                return@launch
            }
            runLookup(place.latitude, place.longitude, place.displayName, autoOpenMaps = true)
        }
    }

    fun changeRadius(km: Int) {
        if (km == radiusKm) return
        radiusKm = km
        val lat = lastLatitude
        val lon = lastLongitude
        if (state == SearchState.DONE && lat != null && lon != null &&
            (results.isNotEmpty() || notice is ParishNotice.Empty || notice is ParishNotice.Failure)
        ) {
            runLookup(lat, lon, searchedPlace, autoOpenMaps = false)
        }
    }

    fun retry() {
        val lat = lastLatitude
        val lon = lastLongitude
        if (lat != null && lon != null) runLookup(lat, lon, searchedPlace, autoOpenMaps = true)
        else if (placeQuery.isNotBlank()) searchPlace()
        else nearMe()
    }

    fun openGoogleMaps() {
        val lat = lastLatitude
        val lon = lastLongitude
        val ok = if (lat != null && lon != null) openMapsAt(lat, lon)
        else openNearbyInMaps(placeQuery.takeIf { it.isNotBlank() })
        if (!ok) mapsUnavailable() else inlineMessage = null
    }

    fun openSettings(action: String, withPackage: Boolean) {
        val intent = Intent(action).apply {
            if (withPackage) data = Uri.parse("package:${context.packageName}")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { context.startActivity(intent) }
    }

    val kind = when {
        state == SearchState.LOADING -> AreaKind.LOADING
        results.isNotEmpty() -> AreaKind.RESULTS
        notice != null -> AreaKind.NOTICE
        else -> AreaKind.IDLE
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("parish_finder_screen"),
        contentPadding = PaddingValues(bottom = dock + 24.dp)
    ) {
        item(key = "parish_header") {
            AppDetailHeader(
                title = strings("Parroquias", "Parishes", "Paróquias"),
                assetPath = hero,
                imageHeight = 248.dp
            )
        }

        item(key = "parish_search") {
            Column {
                ParishSearchField(
                    value = placeQuery,
                    onValueChange = { placeQuery = it },
                    placeholder = strings(
                        "Ciudad, barrio o dirección",
                        "City, neighbourhood or address",
                        "Cidade, bairro ou endereço"
                    ),
                    clearLabel = strings("Borrar", "Clear", "Limpar"),
                    submitLabel = strings(
                        "Buscar «${placeQuery.trim()}»",
                        "Search “${placeQuery.trim()}”",
                        "Buscar «${placeQuery.trim()}»"
                    ),
                    onSubmit = { searchPlace() }
                )
                Spacer(Modifier.height(12.dp))
                AppPrimaryCapsule(
                    label = strings("Parroquias cerca de mí", "Parishes near me", "Paróquias perto de mim"),
                    onClick = { nearMe() },
                    modifier = Modifier.padding(horizontal = design.spacing.gutter),
                    icon = Icons.Outlined.NearMe,
                    enabled = state != SearchState.LOADING,
                    tag = "gps_nearby_maps_button"
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    strings(
                        "Tu ubicación solo se usa para esta búsqueda.",
                        "Your location is only used for this search.",
                        "Sua localização só é usada nesta busca."
                    ),
                    style = styles.footnote,
                    color = p.textSecondary,
                    modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp)
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("parish_radius_selector"),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        strings("Radio", "Radius", "Raio"),
                        style = styles.footnote,
                        color = p.textSecondary,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    RadiusOptionsKm.forEach { option ->
                        AppKitChip(
                            label = "$option km",
                            selected = option == radiusKm,
                            onClick = { changeRadius(option) },
                            modifier = Modifier.heightIn(min = 44.dp)
                        )
                    }
                }
                inlineMessage?.let { text ->
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text,
                        style = styles.footnote,
                        color = p.textSecondary,
                        modifier = Modifier.padding(horizontal = design.spacing.gutter + 4.dp)
                    )
                }
                Spacer(Modifier.height(design.spacing.sectionGap))
            }
        }

        // Idle, loading and notices share one slot so the page crossfades
        // between them instead of jumping.
        item(key = "parish_state") {
            Crossfade(targetState = kind, animationSpec = tween(250), label = "parishState") { shown ->
                when (shown) {
                    AreaKind.IDLE -> IdleArea(currentLanguage, onOpenDirectory)
                    AreaKind.LOADING -> LoadingArea(currentLanguage, loadingPlace)
                    AreaKind.NOTICE -> notice?.let { n ->
                        NoticeArea(
                            notice = n,
                            language = currentLanguage,
                            onOpenDirectory = onOpenDirectory,
                            onOpenMaps = { openGoogleMaps() },
                            onRetry = { retry() },
                            onWiden = { km ->
                                radiusKm = km
                                val lat = lastLatitude
                                val lon = lastLongitude
                                if (lat != null && lon != null) runLookup(lat, lon, searchedPlace, autoOpenMaps = false)
                            },
                            onLocationSettings = {
                                openSettings(Settings.ACTION_LOCATION_SOURCE_SETTINGS, withPackage = false)
                            },
                            onAppSettings = {
                                openSettings(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, withPackage = true)
                            },
                            onAskAgain = { nearMe() }
                        )
                    }
                    AreaKind.RESULTS -> Spacer(Modifier.height(1.dp))
                }
            }
        }

        if (kind == AreaKind.RESULTS) {
            item(key = "parish_results_header") {
                val place = shortPlace(searchedPlace)
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = if (place != null) {
                            strings("Cerca de $place", "Near $place", "Perto de $place")
                        } else {
                            strings("Cerca de ti", "Near you", "Perto de você")
                        },
                        style = styles.headline,
                        color = p.textPrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = strings(
                            "${results.size} parroquias · $lastRadiusKm km",
                            "${results.size} parishes · $lastRadiusKm km",
                            "${results.size} paróquias · $lastRadiusKm km"
                        ),
                        style = styles.footnote,
                        color = p.textSecondary
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
            item(key = "parish_osm_map") {
                ParishMapPreview(
                    parishes = results,
                    centerLat = lastLatitude,
                    centerLon = lastLongitude,
                    language = currentLanguage,
                    onClick = {
                        // Results exist, so the embed has markers; if they
                        // somehow vanished, fall back to Maps.
                        if (osmEmbedUrl() != null) showOsmMap = true
                        else if (!openMapsAt(lastLatitude, lastLongitude)) mapsUnavailable()
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
            item(key = "parish_list") {
                AppListGroup(Modifier.testTag("parish_list")) {
                    results.forEachIndexed { index, parish ->
                        ParishRow(
                            number = index + 1,
                            parish = parish,
                            language = currentLanguage,
                            expanded = expandedParishId == parish.id,
                            onToggle = {
                                expandedParishId = if (expandedParishId == parish.id) null else parish.id
                            },
                            onDirections = { openDirections(parish) },
                            onCall = { callParish(parish) }
                        )
                    }
                }
                Spacer(Modifier.height(design.spacing.sectionGap))
            }
            item(key = "parish_more") {
                AppListGroup {
                    AppListRow(
                        title = strings("Ver más en Google Maps", "More in Google Maps", "Ver mais no Google Maps"),
                        leadingIcon = Icons.Outlined.Map,
                        onClick = {
                            if (!openNearbyInMaps(shortPlace(searchedPlace))) mapsUnavailable()
                        },
                        modifier = Modifier.heightIn(min = 56.dp)
                    )
                    AppListRow(
                        title = strings("Directorio de iglesias", "Church directory", "Diretório de igrejas"),
                        subtitle = strings(
                            "Catedrales y basílicas del mundo",
                            "Cathedrals and basilicas of the world",
                            "Catedrais e basílicas do mundo"
                        ),
                        leadingIcon = Icons.Outlined.Public,
                        onClick = onOpenDirectory,
                        modifier = Modifier
                            .heightIn(min = 56.dp)
                            .testTag("parish_open_directory")
                    )
                }
            }
        }
    }

    // The real map, on demand: OpenStreetMap's own embed in the in-app browser
    // (map pages are not articles, so no reader styling is injected).
    if (showOsmMap) {
        osmEmbedUrl()?.let { url ->
            InAppBrowserDialog(
                url = url,
                onDismiss = { showOsmMap = false },
                currentLanguage = currentLanguage,
                readerMode = false
            )
        }
    }
}

/** "Madrid, Comunidad de Madrid, España" → "Madrid"; null for the device's own fix. */
private fun shortPlace(place: String?): String? {
    val first = place?.substringBefore(',')?.trim().orEmpty()
    if (first.isEmpty()) return null
    val own = setOf("Tu ubicación", "Your location", "Sua localização")
    return first.takeIf { it !in own }
}

/** "Calle de Bailén, 10, Centro, 28013 Madrid" → "Calle de Bailén, 10". */
private fun streetOf(address: String): String =
    address.split(',').map { it.trim() }.filter { it.isNotEmpty() }.take(2).joinToString(", ")

// --------------------------------------------------------------------- search

@Composable
private fun ParishSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    clearLabel: String,
    submitLabel: String,
    onSubmit: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val shape = RoundedCornerShape(percent = 50)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .heightIn(min = 52.dp)
            .lumenGlass(shape)
            .padding(start = 18.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.Search, null, tint = p.textSecondary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Box(Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(placeholder, style = styles.body, color = p.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = styles.body.copy(color = p.textPrimary),
                cursorBrush = SolidColor(p.textPrimary),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSubmit() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = placeholder }
                    .testTag("parish_place_field")
            )
        }
        if (value.isNotEmpty()) {
            Box(
                Modifier
                    .size(40.dp)
                    .clickable(remember { MutableInteractionSource() }, null, role = Role.Button) { onValueChange("") }
                    .semantics { contentDescription = clearLabel },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Cancel, null, tint = p.inkQuiet, modifier = Modifier.size(18.dp))
            }
            AppGlassIconButton(
                icon = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = submitLabel,
                onClick = onSubmit,
                tag = "parish_search_place_button"
            )
        } else {
            Spacer(Modifier.width(14.dp))
        }
    }
}

// ---------------------------------------------------------------------- areas

@Composable
private fun IdleArea(language: AppLanguage, onOpenDirectory: () -> Unit) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    Column {
        AppGlassSurface(
            Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            contentPadding = PaddingValues(design.spacing.cardPadding)
        ) {
            Column {
                Icon(Icons.Outlined.Church, null, tint = p.textPrimary, modifier = Modifier.size(26.dp))
                Spacer(Modifier.height(14.dp))
                Text(
                    when (language) {
                        AppLanguage.ES -> "Encuentra parroquias cerca de ti"
                        AppLanguage.EN -> "Find parishes near you"
                        AppLanguage.PT -> "Encontre paróquias perto de você"
                    },
                    style = styles.headline,
                    color = p.textPrimary
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    when (language) {
                        AppLanguage.ES -> "Busca una ciudad o usa tu ubicación para ver las parroquias cercanas y sus Misas del domingo."
                        AppLanguage.EN -> "Search a city or use your location to see nearby parishes and their Sunday Masses."
                        AppLanguage.PT -> "Busque uma cidade ou use sua localização para ver as paróquias próximas e suas Missas de domingo."
                    },
                    style = styles.subhead,
                    color = p.textSecondary
                )
            }
        }
        Spacer(Modifier.height(design.spacing.sectionGap))
        DirectoryGroup(language, onOpenDirectory)
    }
}

@Composable
private fun DirectoryGroup(language: AppLanguage, onOpenDirectory: () -> Unit) {
    AppListGroup {
        AppListRow(
            title = when (language) {
                AppLanguage.ES -> "Directorio de iglesias"
                AppLanguage.EN -> "Church directory"
                AppLanguage.PT -> "Diretório de igrejas"
            },
            subtitle = when (language) {
                AppLanguage.ES -> "Catedrales y basílicas del mundo"
                AppLanguage.EN -> "Cathedrals and basilicas of the world"
                AppLanguage.PT -> "Catedrais e basílicas do mundo"
            },
            leadingIcon = Icons.Outlined.Public,
            onClick = onOpenDirectory,
            modifier = Modifier.heightIn(min = 56.dp).testTag("parish_open_directory")
        )
    }
}

@Composable
private fun LoadingArea(language: AppLanguage, place: String?) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val searching = when (language) {
        AppLanguage.ES -> "Buscando…"
        AppLanguage.EN -> "Searching…"
        AppLanguage.PT -> "Buscando…"
    }
    val near = shortPlace(place)
    val label = when (language) {
        AppLanguage.ES -> "Buscando parroquias cerca de ${near ?: "ti"}…"
        AppLanguage.EN -> "Looking for parishes near ${near ?: "you"}…"
        AppLanguage.PT -> "Buscando paróquias perto de ${near ?: "você"}…"
    }
    Column {
        AppGlassSurface(
            Modifier.padding(horizontal = design.spacing.gutter),
            shape = RoundedCornerShape(percent = 50),
            strong = true,
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(18.dp)
                        .semantics { contentDescription = searching }
                        .testTag("parish_loading"),
                    color = p.textPrimary,
                    strokeWidth = 2.dp
                )
                Spacer(Modifier.width(12.dp))
                Text(label, style = styles.subhead, color = p.textPrimary, maxLines = 2)
            }
        }
        Spacer(Modifier.height(20.dp))
        // Three static rows keep the page the height it will have, so nothing
        // jumps when the answer arrives.
        AppListGroup {
            repeat(3) { i ->
                Row(
                    Modifier.fillMaxWidth().heightIn(min = 72.dp).padding(horizontal = 18.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.size(28.dp).clip(CircleShape).background(p.textPrimary.copy(alpha = 0.07f)))
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Box(
                            Modifier.fillMaxWidth(if (i == 1) 0.55f else 0.7f).height(14.dp)
                                .clip(RoundedCornerShape(7.dp)).background(p.textPrimary.copy(alpha = 0.07f))
                        )
                        Spacer(Modifier.height(8.dp))
                        Box(
                            Modifier.fillMaxWidth(0.4f).height(11.dp)
                                .clip(RoundedCornerShape(6.dp)).background(p.textPrimary.copy(alpha = 0.05f))
                        )
                    }
                }
                Box(Modifier.fillMaxWidth().padding(start = 60.dp).height(0.5.dp).background(p.separator))
            }
        }
    }
}

@Composable
private fun NoticeArea(
    notice: ParishNotice,
    language: AppLanguage,
    onOpenDirectory: () -> Unit,
    onOpenMaps: () -> Unit,
    onRetry: () -> Unit,
    onWiden: (Int) -> Unit,
    onLocationSettings: () -> Unit,
    onAppSettings: () -> Unit,
    onAskAgain: () -> Unit
) {
    val design = LocalAppDesign.current
    fun s(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    val mapsLabel = s("Abrir Google Maps", "Open Google Maps", "Abrir o Google Maps")
    val mapsAction = NoticeAction(mapsLabel, onOpenMaps, "use_my_location_button")

    val content: NoticeContent = when (notice) {
        is ParishNotice.Empty -> {
            val next = RadiusOptionsKm.firstOrNull { it > notice.radiusKm }
            NoticeContent(
                icon = Icons.Outlined.SearchOff,
                title = s(
                    "No hay parroquias a ${notice.radiusKm} km",
                    "No parishes within ${notice.radiusKm} km",
                    "Nenhuma paróquia a ${notice.radiusKm} km"
                ),
                body = if (notice.mapsOpened) s(
                    "Abrimos Google Maps para buscar cerca de ti.",
                    "We opened Google Maps to search near you.",
                    "Abrimos o Google Maps para buscar perto de você."
                ) else s(
                    "Prueba un radio mayor o busca en Google Maps.",
                    "Try a wider radius or search in Google Maps.",
                    "Tente um raio maior ou busque no Google Maps."
                ),
                primary = next?.let {
                    NoticeAction(s("Ampliar a $it km", "Widen to $it km", "Ampliar para $it km"), { onWiden(it) })
                },
                secondary = mapsAction
            )
        }
        is ParishNotice.Failure -> NoticeContent(
            icon = Icons.Outlined.CloudOff,
            title = s("El directorio no respondió", "The directory did not answer", "O diretório não respondeu"),
            body = if (notice.mapsOpened) s(
                "Abrimos Google Maps para buscar por ti.",
                "We opened Google Maps to search for you.",
                "Abrimos o Google Maps para buscar por você."
            ) else s(
                "Comprueba tu conexión o busca en Google Maps.",
                "Check your connection or search in Google Maps.",
                "Verifique sua conexão ou busque no Google Maps."
            ),
            primary = NoticeAction(s("Reintentar", "Try again", "Tentar de novo"), onRetry),
            secondary = mapsAction
        )
        is ParishNotice.NoFix -> NoticeContent(
            icon = Icons.Outlined.LocationOff,
            title = s("Aún no tenemos tu ubicación", "We don't have your location yet", "Ainda não temos sua localização"),
            body = if (notice.mapsOpened) s(
                "Activa la ubicación o busca una ciudad. Abrimos Google Maps.",
                "Turn on location or search a city. We opened Google Maps.",
                "Ative a localização ou busque uma cidade. Abrimos o Google Maps."
            ) else s(
                "Activa la ubicación o busca una ciudad.",
                "Turn on location or search a city.",
                "Ative a localização ou busque uma cidade."
            ),
            primary = NoticeAction(
                s("Ajustes de ubicación", "Location settings", "Ajustes de localização"),
                onLocationSettings
            ),
            secondary = mapsAction
        )
        is ParishNotice.PlaceNotFound -> NoticeContent(
            icon = Icons.Outlined.SearchOff,
            title = s(
                "No encontramos «${notice.query}»",
                "We couldn't find “${notice.query}”",
                "Não encontramos «${notice.query}»"
            ),
            body = if (notice.mapsOpened) s(
                "Abrimos Google Maps para buscar por ti.",
                "We opened Google Maps to search for you.",
                "Abrimos o Google Maps para buscar por você."
            ) else s(
                "Revisa el nombre o busca en Google Maps.",
                "Check the spelling or search in Google Maps.",
                "Revise o nome ou busque no Google Maps."
            ),
            primary = null,
            secondary = mapsAction
        )
        is ParishNotice.Denied -> NoticeContent(
            icon = Icons.Outlined.LocationOff,
            title = s("Sin acceso a tu ubicación", "No access to your location", "Sem acesso à sua localização"),
            body = s(
                "Puedes buscar una ciudad por nombre.",
                "You can search for a city by name.",
                "Você pode buscar uma cidade pelo nome."
            ),
            primary = if (notice.permanent) {
                NoticeAction(s("Abrir ajustes", "Open settings", "Abrir ajustes"), onAppSettings, icon = Icons.Outlined.Settings)
            } else {
                NoticeAction(s("Intentar de nuevo", "Try again", "Tentar de novo"), onAskAgain)
            },
            secondary = null
        )
    }
    Column {
        NoticeCard(content)
        Spacer(Modifier.height(design.spacing.sectionGap))
        DirectoryGroup(language, onOpenDirectory)
    }
}

private data class NoticeAction(
    val label: String,
    val onClick: () -> Unit,
    val tag: String? = null,
    val icon: ImageVector? = null
)

private data class NoticeContent(
    val icon: ImageVector,
    val title: String,
    val body: String,
    val primary: NoticeAction?,
    val secondary: NoticeAction?
)

@Composable
private fun NoticeCard(content: NoticeContent) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    AppGlassSurface(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Column {
            Row(verticalAlignment = Alignment.Top) {
                Icon(content.icon, null, tint = p.textPrimary, modifier = Modifier.size(26.dp))
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(content.title, style = styles.headline, color = p.textPrimary)
                    Spacer(Modifier.height(4.dp))
                    Text(content.body, style = styles.subhead, color = p.textSecondary)
                }
            }
            if (content.primary != null || content.secondary != null) {
                Spacer(Modifier.height(18.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    content.primary?.let { a ->
                        AppPrimaryCapsule(
                            label = a.label,
                            onClick = a.onClick,
                            icon = a.icon,
                            tag = a.tag
                        )
                    }
                    content.secondary?.let { a ->
                        AppSecondaryCapsule(
                            label = a.label,
                            onClick = a.onClick,
                            icon = Icons.Outlined.Map,
                            fillWidth = true,
                            tag = a.tag
                        )
                    }
                }
            }
        }
    }
}

// ------------------------------------------------------------------- results

@Composable
private fun PinBadge(number: Int, size: Int = 28) {
    val p = LocalAppDesign.current.palette
    Box(
        Modifier.size(size.dp).clip(CircleShape).background(p.action),
        contentAlignment = Alignment.Center
    ) {
        Text(
            number.toString(),
            style = LocalAppTextStyles.current.caption,
            color = p.onAction,
            maxLines = 1
        )
    }
}

@Composable
private fun ParishRow(
    number: Int,
    parish: NearbyParish,
    language: AppLanguage,
    expanded: Boolean,
    onToggle: () -> Unit,
    onDirections: () -> Unit,
    onCall: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    fun s(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    val street = streetOf(parish.address).ifBlank { parish.city }.ifBlank { "" }
    val masses = parish.sundayMasses.takeIf { it.isNotBlank() }?.let {
        TextFormat.times(ParishScheduleText.masses(it, language), language)
    }
    val distance = parish.distanceDisplay.takeIf { it.any(Char::isDigit) }
    val chevronTurn = if (expanded) 90f else 0f

    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onToggle, 0.99f)
                .heightIn(min = 64.dp)
                .semantics {
                    stateDescription = if (expanded) s("Expandida", "Expanded", "Expandida")
                    else s("Contraída", "Collapsed", "Recolhida")
                }
                .testTag("parish_row_${parish.id}")
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PinBadge(number)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(parish.name, style = styles.body, color = p.textPrimary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                if (street.isNotBlank()) {
                    Text(street, style = styles.footnote, color = p.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                if (masses != null) {
                    Text(
                        s("Misa del domingo", "Sunday Mass", "Missa de domingo") + " · " + masses,
                        style = styles.footnote,
                        color = p.textSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (distance != null) {
                Spacer(Modifier.width(10.dp))
                Text(distance, style = styles.footnote, color = p.textSecondary, maxLines = 1)
            }
            Spacer(Modifier.width(10.dp))
            AppChevron(Modifier.graphicsLayer { rotationZ = chevronTurn })
        }
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(tween(design.motion.mediumMillis)) + fadeIn(tween(design.motion.mediumMillis)),
            exit = shrinkVertically(tween(design.motion.fastMillis)) + fadeOut(tween(design.motion.fastMillis))
        ) {
            Column(Modifier.fillMaxWidth().padding(start = 18.dp + 28.dp + 14.dp, end = 18.dp, bottom = 16.dp)) {
                if (parish.address.isNotBlank()) {
                    Text(parish.address, style = styles.subhead, color = p.textPrimary)
                    Spacer(Modifier.height(8.dp))
                }
                Text(
                    text = if (masses != null) {
                        s("Misa del domingo", "Sunday Mass", "Missa de domingo") + " · " + masses
                    } else {
                        s(
                            "Horario de misas no publicado. Llama a la parroquia para confirmarlo.",
                            "Mass times not published. Call the parish to confirm.",
                            "Horário das missas não publicado. Ligue para a paróquia para confirmar."
                        )
                    },
                    style = styles.footnote,
                    color = p.textSecondary
                )
                Spacer(Modifier.height(14.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    AppSecondaryCapsule(
                        label = s("Cómo llegar", "Directions", "Como chegar"),
                        onClick = onDirections,
                        icon = Icons.Outlined.Directions,
                        tag = "parish_directions_${parish.id}"
                    )
                    if (parish.contactPhone.isNotBlank()) {
                        AppSecondaryCapsule(
                            label = s("Llamar", "Call", "Ligar"),
                            onClick = onCall,
                            icon = Icons.Outlined.Phone,
                            modifier = Modifier.semantics {
                                contentDescription = s("Llamar", "Call", "Ligar") + " " + parish.contactPhone
                            },
                            tag = "parish_call_${parish.id}"
                        )
                    }
                }
            }
        }
        Box(Modifier.fillMaxWidth().padding(start = 60.dp).height(0.5.dp).background(p.separator))
    }
}

/**
 * A small schematic of the results: numbered pins placed where the parishes
 * are, around the searched point, on a quiet grid. It fetches nothing; a tap
 * opens the real map.
 */
@Composable
private fun ParishMapPreview(
    parishes: List<NearbyParish>,
    centerLat: Double?,
    centerLon: Double?,
    language: AppLanguage,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val measurer = rememberTextMeasurer()
    val shape = RoundedCornerShape(22.dp)
    val pins = parishes.take(9)
    val label = when (language) {
        AppLanguage.ES -> "Ver mapa"
        AppLanguage.EN -> "View map"
        AppLanguage.PT -> "Ver mapa"
    }
    val spoken = when (language) {
        AppLanguage.ES -> "Ver en el mapa"
        AppLanguage.EN -> "View on map"
        AppLanguage.PT -> "Ver no mapa"
    }
    val labelStyle = styles.caption.copy(color = p.onAction)
    val gridColor = p.textPrimary.copy(alpha = if (design.isDark) 0.07f else 0.06f)
    val ringColor = p.textPrimary.copy(alpha = if (design.isDark) 0.16f else 0.12f)
    val pinColor = p.action
    val home = p.textSecondary

    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .height(180.dp)
            .lumenPress(onClick, 0.98f)
            .lumenGlass(shape)
            .clip(shape)
            .semantics { contentDescription = spoken }
            .testTag("parish_osm_map_button")
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val step = 36.dp.toPx()
            var x = step / 2
            while (x < w) { drawLine(gridColor, Offset(x, 0f), Offset(x, h), 1f); x += step }
            var y = step / 2
            while (y < h) { drawLine(gridColor, Offset(0f, y), Offset(w, y), 1f); y += step }

            val lat0 = centerLat ?: pins.map { it.latitude }.average()
            val lon0 = centerLon ?: pins.map { it.longitude }.average()
            val kx = 111.0 * max(cos(Math.toRadians(lat0)), 0.2)
            val offsets = pins.map { (it.longitude - lon0) * kx to (it.latitude - lat0) * 111.0 }
            val padX = 34.dp.toPx()
            val padTop = 30.dp.toPx()
            val padBottom = 62.dp.toPx()
            val areaW = w - padX * 2
            val areaH = h - padTop - padBottom
            val extentX = max(offsets.maxOfOrNull { abs(it.first) } ?: 0.0, 0.3)
            val extentY = max(offsets.maxOfOrNull { abs(it.second) } ?: 0.0, 0.3)
            val scale = min(areaW / (2 * extentX), areaH / (2 * extentY)).toFloat()
            val cx = w / 2
            val cy = padTop + areaH / 2

            // Two quiet rings around the searched point, then the point itself.
            val ringR = min(areaW, areaH) / 2
            drawCircle(ringColor, ringR, Offset(cx, cy), style = Stroke(1.dp.toPx()))
            drawCircle(ringColor, ringR / 2, Offset(cx, cy), style = Stroke(1.dp.toPx()))
            drawCircle(home.copy(alpha = 0.25f), 7.dp.toPx(), Offset(cx, cy))
            drawCircle(home, 7.dp.toPx(), Offset(cx, cy), style = Stroke(2.dp.toPx()))

            offsets.forEachIndexed { i, (dx, dy) ->
                val px = cx + (dx * scale).toFloat()
                val py = cy - (dy * scale).toFloat()
                val r = 11.dp.toPx()
                drawCircle(pinColor, r, Offset(px, py))
                val text = measurer.measure((i + 1).toString(), labelStyle)
                drawText(
                    text,
                    topLeft = Offset(px - text.size.width / 2f, py - text.size.height / 2f)
                )
            }
        }
        Row(
            Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
                .lumenGlass(RoundedCornerShape(percent = 50), strong = true)
                .padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Map, null, tint = p.textPrimary, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(label, style = styles.subhead, color = p.textPrimary)
        }
    }
}
