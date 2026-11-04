package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.ChristManifestation
import com.example.data.models.MarianApparition
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** Sentinel for "every region", so the chip's label can be translated freely. */
private const val EVERY_REGION = "ALL"

/** Minimum touch target for every control this screen owns (AAA). */
private val MinTouchTarget = 48.dp

/**
 * The Marian advocations and the manifestations of Christ, each one set as a
 * folio entry: a ruled index line carrying the name, the place and the year,
 * and beneath it the account itself held to the reading measure.
 *
 * This is a pane of [DoctrineAndHistoryScreen], not a destination — the parent
 * owns the tab strip, so the two bodies of devotion are chosen with chips and
 * the pane opens straight into its own running head rather than a second strip.
 */
@Composable
fun DevotionsExplorerScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    onSpeakText: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier,
    header: LazyListScope.() -> Unit = {}
) {
    val design = LocalAppDesign.current

    // 0 = Marian advocations, 1 = devotions to Christ.
    var selectedCategoryTab by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedRegionFilter by remember { mutableStateOf(EVERY_REGION) }

    // Held outside the list: a rail that scrolled off screen would otherwise
    // come back rewound, with the chosen chip hidden off to the right.
    val marianList = repository.marianApparitions
    val christList = repository.christManifestations

    val categories = listOf(
        currentLanguage.pick("Nuestra Señora", "Our Lady", "Nossa Senhora"),
        currentLanguage.pick("Cristo", "Christ", "Cristo")
    )
    // One entry open at a time: the list carries a line of each, the open one
    // its feast, sanctuary and history.
    var expandedId by remember { mutableStateOf<String?>(null) }

    val regions = listOf(
        EVERY_REGION to currentLanguage.pick("Todas", "All", "Todas"),
        "Americas" to currentLanguage.pick("Américas", "Americas", "Américas"),
        "Europe" to currentLanguage.pick("Europa", "Europe", "Europa"),
        "Universal" to currentLanguage.pick("Universales", "Universal", "Universais")
    )

    val query = searchQuery.trim().lowercase()

    fun matches(region: String, vararg fields: String): Boolean {
        val inRegion = selectedRegionFilter == EVERY_REGION || region == selectedRegionFilter
        return inRegion && (query.isEmpty() || fields.any { it.lowercase().contains(query) })
    }

    val filteredMarian = marianList.filter { item ->
        matches(
            item.region,
            item.title.get(currentLanguage),
            item.country.get(currentLanguage),
            item.location.get(currentLanguage),
            item.patronageOf.get(currentLanguage),
            item.primarySanctuary.get(currentLanguage),
            item.recordedMiracles.get(currentLanguage)
        )
    }

    val filteredChrist = christList.filter { item ->
        matches(
            item.region,
            item.title.get(currentLanguage),
            item.country.get(currentLanguage),
            item.primarySanctuary.get(currentLanguage),
            item.originHistory.get(currentLanguage),
            item.recordedMiracles.get(currentLanguage)
        )
    }

    val showingMarian = selectedCategoryTab == 0
    val shownCount = if (showingMarian) filteredMarian.size else filteredChrist.size

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("devotions_explorer_screen")
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            header()

            item {
                ReadingColumn {
                    AppSearchField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = currentLanguage.pick(
                            "Nombre, país o santuario",
                            "Name, country or shrine",
                            "Nome, país ou santuário"
                        ),
                        modifier = Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .heightIn(min = MinTouchTarget)
                            .testTag("devotions_search_input")
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                    // Devotion and region are visible filter chips rather than
                    // collapsed menus: every choice is on screen at once, each
                    // chip is a 48dp target, and the kit draws the selected
                    // state from tokens, so this screen carries no colour.
                    FilterChips(
                        label = currentLanguage.pick("Devoción", "Devotion", "Devoção"),
                        options = categories.mapIndexed { i, label -> i to label },
                        selected = selectedCategoryTab,
                        onSelect = {
                            selectedCategoryTab = it
                            expandedId = null
                        },
                        testTag = "devotions_category_picker"
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    FilterChips(
                        label = currentLanguage.pick("Región", "Region", "Região"),
                        options = regions,
                        selected = selectedRegionFilter,
                        onSelect = { selectedRegionFilter = it },
                        testTag = "devotions_region_picker"
                    )
                }
            }

            item {
                ReadingColumn {
                    val countLine = currentLanguage.pick(
                        "$shownCount resultados",
                        "$shownCount results",
                        "$shownCount resultados"
                    )
                    if (showingMarian) {
                        AppSectionHeader(
                            title = currentLanguage.pick(
                                "Advocaciones Marianas",
                                "Marian Advocations",
                                "Advocações Marianas"
                            ),
                            subtitle = countLine
                        )
                    } else {
                        AppSectionHeader(
                            title = currentLanguage.pick(
                                "Devociones a Cristo",
                                "Devotions to Christ",
                                "Devoções a Cristo"
                            ),
                            subtitle = countLine
                        )
                    }
                }
            }

            if (shownCount == 0) {
                item {
                    ReadingColumn {
                        AppEmptyState(
                            title = currentLanguage.pick(
                                "Sin resultados",
                                "Nothing found",
                                "Sem resultados"
                            ),
                            body = currentLanguage.pick(
                                "Prueba otro nombre o elige otra región.",
                                "Try another name, or choose a different region.",
                                "Tente outro nome ou escolha outra região."
                            ),
                            icon = Icons.Default.Search
                        )
                    }
                }
            } else if (showingMarian) {
                items(filteredMarian, key = { it.id }) { apparition ->
                    ApparitionEntry(
                        item = apparition,
                        currentLanguage = currentLanguage,
                        onSpeakText = onSpeakText,
                        expanded = expandedId == apparition.id,
                        onToggle = { expandedId = if (expandedId == apparition.id) null else apparition.id }
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                }
            } else {
                items(filteredChrist, key = { it.id }) { manifestation ->
                    ManifestationEntry(
                        item = manifestation,
                        currentLanguage = currentLanguage,
                        onSpeakText = onSpeakText,
                        expanded = expandedId == manifestation.id,
                        onToggle = { expandedId = if (expandedId == manifestation.id) null else manifestation.id }
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                }
            }

            item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
        }
    }
}

/**
 * One filter group as a wrapping row of chips under its caption: every option
 * visible at once (no clipped label behind a dropdown), each chip a 48dp
 * target. The caption keeps the old menu row's title, so the group keeps its
 * spoken context for assistive technology.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun <T> FilterChips(
    label: String,
    options: List<Pair<T, String>>,
    selected: T,
    onSelect: (T) -> Unit,
    testTag: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
    ) {
        Text(
            text = caps(label),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary,
            modifier = Modifier.semantics { heading() }
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag),
            horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2),
            verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
        ) {
            // The selection is carried by the chip's own selected state; the
            // value is compared by equality so Int and String groups share
            // this composable.
            options.forEach { (value, optionLabel) ->
                AppChip(
                    label = optionLabel,
                    selected = value == selected,
                    onClick = { onSelect(value) },
                    modifier = Modifier.heightIn(min = MinTouchTarget)
                )
            }
        }
    }
}

// ------------------------------------------------------- the Marian advocations

@Composable
private fun ApparitionEntry(
    item: MarianApparition,
    currentLanguage: AppLanguage,
    onSpeakText: ((String) -> Unit)?,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    ReadingColumn {
        DevotionCard(
            title = item.title.get(currentLanguage),
            subtitle = item.location.get(currentLanguage),
            trailingText = item.year,
            expanded = expanded,
            onToggle = onToggle,
            currentLanguage = currentLanguage,
            testTag = "devotion_card_${item.id}"
        ) {
            // The feast and the country are a line each, so they read as the
            // two columns of a printed header rather than as two more notes.
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap)
            ) {
                Note(
                    label = currentLanguage.pick("Fiesta", "Feast day", "Festa"),
                    body = item.feastDay.get(currentLanguage),
                    labelColor = design.palette.gold,
                    modifier = Modifier.weight(1f)
                )
                Note(
                    label = currentLanguage.pick("País", "Country", "País"),
                    body = item.country.get(currentLanguage),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = item.historyAndApparition.get(currentLanguage),
                style = styles.scripture,
                color = design.palette.textPrimary
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Patronazgo", "Patronage", "Padroado"),
                body = item.patronageOf.get(currentLanguage)
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Santuario", "Shrine", "Santuário"),
                body = item.primarySanctuary.get(currentLanguage),
                labelColor = design.palette.accent
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Promesas", "Promises", "Promessas"),
                body = item.promises.get(currentLanguage),
                bodyStyle = styles.quote
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick(
                    "Milagros destacados",
                    "Recorded miracles",
                    "Milagres registados"
                ),
                body = item.recordedMiracles.get(currentLanguage)
            )

            PrayerDisclosure(
                title = currentLanguage.pick(
                    "Oración de Veneración e Intercesión",
                    "Prayer of veneration and intercession",
                    "Oração de Veneração e Intercessão"
                ),
                prayer = item.venerationPrayer.get(currentLanguage),
                currentLanguage = currentLanguage,
                onSpeakText = onSpeakText
            )
        }
    }
}

// ------------------------------------------------- the manifestations of Christ

@Composable
private fun ManifestationEntry(
    item: ChristManifestation,
    currentLanguage: AppLanguage,
    onSpeakText: ((String) -> Unit)?,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    ReadingColumn {
        DevotionCard(
            title = item.title.get(currentLanguage),
            subtitle = item.country.get(currentLanguage),
            trailingText = null,
            expanded = expanded,
            onToggle = onToggle,
            currentLanguage = currentLanguage,
            testTag = "devotion_card_${item.id}"
        ) {
            Note(
                label = currentLanguage.pick("Fiesta", "Feast day", "Festa"),
                body = item.feastDay.get(currentLanguage),
                labelColor = design.palette.gold
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = item.originHistory.get(currentLanguage),
                style = styles.scripture,
                color = design.palette.textPrimary
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick(
                    "Sentido teológico",
                    "Theological meaning",
                    "Sentido teológico"
                ),
                body = item.theologicalMeaning.get(currentLanguage)
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick(
                    "Santuario Principal",
                    "Principal shrine",
                    "Santuário principal"
                ),
                body = item.primarySanctuary.get(currentLanguage),
                labelColor = design.palette.accent
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Promesas", "Promises", "Promessas"),
                body = item.promises.get(currentLanguage),
                bodyStyle = styles.quote
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Milagros", "Miracles", "Milagres"),
                body = item.recordedMiracles.get(currentLanguage)
            )

            PrayerDisclosure(
                title = currentLanguage.pick("Oración Oficial", "Official prayer", "Oração Oficial"),
                prayer = item.officialPrayer.get(currentLanguage),
                currentLanguage = currentLanguage,
                onSpeakText = onSpeakText
            )
        }
    }
}

/**
 * One devotion as an M3 card: name, place and year on the header line, the
 * account folded beneath it. The header is a single 48dp button role that
 * announces its expanded state; the arrow is decoration, so it carries no
 * description of its own. The body is not part of the toggle, so the prayer
 * and listen controls inside are never nested inside another tap target.
 */
@Composable
private fun DevotionCard(
    title: String,
    subtitle: String,
    trailingText: String?,
    expanded: Boolean,
    onToggle: () -> Unit,
    currentLanguage: AppLanguage,
    testTag: String,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val motion = design.motion

    AppCard(
        modifier = Modifier
            .padding(horizontal = design.spacing.gutter)
            .testTag(testTag)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = MinTouchTarget)
                .clickable(role = Role.Button, onClick = onToggle)
                .semantics(mergeDescendants = true) {
                    stateDescription = if (expanded) {
                        currentLanguage.pick("Abierta", "Expanded", "Expandida")
                    } else {
                        currentLanguage.pick("Cerrada", "Collapsed", "Recolhida")
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = design.palette.textPrimary,
                    modifier = Modifier.semantics { heading() }
                )
                Spacer(Modifier.height(design.spacing.cardGap / 4))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = design.palette.textSecondary
                )
            }
            if (trailingText != null) {
                Spacer(Modifier.width(design.spacing.cardGap / 2))
                Text(
                    text = trailingText,
                    style = MaterialTheme.typography.labelLarge,
                    color = design.palette.textSecondary
                )
            }
            Spacer(Modifier.width(design.spacing.cardGap / 2))
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = design.palette.textSecondary,
                modifier = Modifier.size(design.decor.rowIconSize)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(tween(motion.mediumMillis, easing = motion.standardEasing)) +
                expandVertically(tween(motion.mediumMillis, easing = motion.standardEasing)),
            exit = fadeOut(tween(motion.fastMillis)) +
                shrinkVertically(tween(motion.fastMillis))
        ) {
            Column {
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                AppDivider()
                Spacer(Modifier.height(design.spacing.cardGap))
                content()
            }
        }
    }
}

// ------------------------------------------------------------------- fittings

/**
 * The prayer each devotion closes with, kept folded until asked for. The labels
 * are one word apiece: at 360dp, in tracked capitals, anything longer breaks.
 */
@Composable
private fun PrayerDisclosure(
    title: String,
    prayer: String,
    currentLanguage: AppLanguage,
    onSpeakText: ((String) -> Unit)?
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    var open by remember { mutableStateOf(false) }

    Spacer(Modifier.height(design.spacing.cardGap))
    AppSecondaryButton(
        label = if (open) {
            currentLanguage.pick("Ocultar", "Hide", "Ocultar")
        } else {
            currentLanguage.pick("Oración", "Prayer", "Oração")
        },
        onClick = { open = !open },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = MinTouchTarget)
    )

    AnimatedVisibility(
        visible = open,
        enter = fadeIn(tween(design.motion.mediumMillis, easing = design.motion.standardEasing)) +
            expandVertically(
                tween(design.motion.mediumMillis, easing = design.motion.standardEasing)
            ),
        exit = fadeOut(tween(design.motion.fastMillis)) +
            shrinkVertically(tween(design.motion.fastMillis))
    ) {
        Column(Modifier.padding(top = design.spacing.cardGap)) {
            AppCard {
                Text(
                    text = caps(title),
                    style = styles.sectionEyebrow,
                    color = design.palette.gold
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
                Text(
                    text = prayer,
                    style = styles.quote,
                    color = design.palette.textPrimary
                )
                if (onSpeakText != null) {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppSecondaryButton(
                        label = currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                        onClick = { onSpeakText(prayer) },
                        leadingIcon = Icons.Default.VolumeUp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = MinTouchTarget)
                    )
                }
            }
        }
    }
}

/** An eyebrow label over the passage it introduces — an entry's unit of content. */
@Composable
private fun Note(
    label: String,
    body: String,
    modifier: Modifier = Modifier,
    labelColor: Color? = null,
    bodyStyle: TextStyle? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(modifier.fillMaxWidth()) {
        Text(
            text = caps(label),
            style = styles.sectionEyebrow,
            color = labelColor ?: design.palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 4))
        Text(
            text = body,
            style = bodyStyle ?: MaterialTheme.typography.bodyLarge,
            color = design.palette.textPrimary
        )
    }
}

/**
 * Holds long-form text to the identity's reading measure and centres it, so a
 * wide screen reads as the column of a book instead of a wall of type. The two
 * gutters are added back because the content inside carries them itself.
 */
@Composable
private fun ReadingColumn(content: @Composable ColumnScope.() -> Unit) {
    val design = LocalAppDesign.current
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier
                .widthIn(max = design.spacing.readingMaxWidth + design.spacing.gutter * 2)
                .fillMaxWidth(),
            content = content
        )
    }
}

/** Vatican sets its labels as inscriptional capitals; the other two do not. */
@Composable
private fun caps(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}
