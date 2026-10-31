package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.CapitalSinVsVirtue
import com.example.data.models.ChurchHistoryEpoch
import com.example.data.models.ChurchSchismHistory
import com.example.data.models.NearbyParish
import com.example.data.news.NewsResult
import com.example.data.news.VaticanNewsFetcher
import com.example.data.news.VaticanNewsItem
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppCard
import com.example.ui.design.AppChip
import com.example.ui.design.AppDivider
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppOrnament
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSearchField
import com.example.ui.design.AppSecondaryButton
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.theme.AppDesign
import com.example.ui.theme.AppTextStyles
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * Sentinel for "do not narrow at this level". The three geographic menus are
 * compared against it, so the label the reader sees can be translated without
 * any filter silently ceasing to match.
 */
private const val ANY_PLACE = "__any__"

/**
 * Doctrine, history and the directory, behind four panes: the moral teaching of
 * the Catechism (the Decalogue, the precepts, the capital vices against the
 * virtues that cure them, and the gravity of sin), the Marian and
 * Christological devotions, the Church's own timeline with the schisms that
 * mark it, and the parish directory beside the Vatican's live feed.
 */
@Composable
fun DoctrineAndHistoryScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    initialTopTab: Int = 0,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current

    // 0 = Catechism & doctrine, 1 = Mary & Christ, 2 = history & schisms,
    // 3 = parishes & news.
    var selectedTopTab by remember { mutableIntStateOf(initialTopTab) }
    var selectedSubDoctrineTab by remember { mutableIntStateOf(0) }

    // Opened from "Churches & News" the directory is a destination of its own:
    // a title over three doctrinal tabs it was not asked for would mislead.
    val directoryOnly = initialTopTab == 3

    // Short enough that all four sit abreast on a 360dp phone in every
    // identity; the full names head each pane as its section header.
    val topTabs = listOf(
        currentLanguage.pick("Doctrina", "Doctrine", "Doutrina"),
        currentLanguage.pick("María y Jesús", "Mary & Jesus", "Maria e Jesus"),
        currentLanguage.pick("Historia", "History", "História"),
        currentLanguage.pick("Iglesias", "Churches", "Igrejas")
    )
    val tabsInset = tabStripInset()

    // The large title, then the selector — the order every identity's own
    // platform uses. Both scroll away with the pane beneath them.
    val header: LazyListScope.() -> Unit = {
        item(key = "doctrine_title") {
            if (directoryOnly) {
                AppScreenTitle(
                    title = currentLanguage.pick(
                        "Iglesias y Noticias",
                        "Churches & News",
                        "Igrejas e Notícias"
                    ),
                    subtitle = currentLanguage.pick(
                        "El directorio de parroquias y las noticias del Vaticano.",
                        "The parish directory and the latest from the Vatican.",
                        "O diretório de paróquias e as notícias do Vaticano."
                    )
                )
            } else {
                AppScreenTitle(
                    title = currentLanguage.pick(
                        "Doctrina e Historia",
                        "Doctrine & History",
                        "Doutrina e História"
                    ),
                    subtitle = currentLanguage.pick(
                        "La enseñanza moral, las devociones y dos mil años de Iglesia.",
                        "Moral teaching, devotions and two thousand years of the Church.",
                        "O ensino moral, as devoções e dois mil anos de Igreja."
                    )
                )
            }
        }
        if (!directoryOnly) {
            item(key = "doctrine_tabs") {
                AppSegmentedTabs(
                    options = topTabs,
                    selectedIndex = selectedTopTab,
                    onSelect = { selectedTopTab = it },
                    modifier = Modifier.padding(horizontal = tabsInset)
                )
                Spacer(Modifier.height(design.spacing.cardGap / 2))
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("doctrine_and_history_screen")
    ) {
        when (selectedTopTab) {
            0 -> MoralDoctrinePane(
                repository = repository,
                currentLanguage = currentLanguage,
                selectedTab = selectedSubDoctrineTab,
                onSelectTab = { selectedSubDoctrineTab = it },
                header = header
            )

            1 -> DevotionsExplorerScreen(
                repository = repository,
                currentLanguage = currentLanguage,
                header = header
            )

            2 -> ChurchHistoryPane(
                repository = repository,
                currentLanguage = currentLanguage,
                header = header
            )

            else -> ParishesAndNewsPane(
                repository = repository,
                currentLanguage = currentLanguage,
                header = header
            )
        }
    }
}

// ------------------------------------------------- the Catechism's moral part

@Composable
private fun MoralDoctrinePane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    val sections = listOf(
        currentLanguage.pick("Mandamientos", "Commandments", "Mandamentos"),
        currentLanguage.pick("Preceptos", "Precepts", "Preceitos"),
        currentLanguage.pick("Vicios y virtudes", "Vices & virtues", "Vícios e virtudes"),
        currentLanguage.pick("Gravedad", "Gravity of sin", "Gravidade")
    )

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item(key = "doctrine_sections") {
            ChipFlow {
                sections.forEachIndexed { index, label ->
                    AppChip(
                        label = label,
                        selected = selectedTab == index,
                        onClick = { onSelectTab(index) }
                    )
                }
            }
        }

        when (selectedTab) {
            0 -> commandments(repository, currentLanguage, design, styles)
            1 -> precepts(repository, currentLanguage, design)
            2 -> sinsAndVirtues(repository, currentLanguage, design)
            else -> sinGravity(repository, currentLanguage, design)
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

private fun LazyListScope.commandments(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    design: AppDesign,
    styles: AppTextStyles
) {
    item {
        AppSectionHeader(
            title = currentLanguage.pick(
                "Los Diez Mandamientos",
                "The Ten Commandments",
                "Os Dez Mandamentos"
            ),
            subtitle = currentLanguage.pick(
                "La ley moral entregada en el Sinaí y confirmada por Cristo.",
                "The moral law given at Sinai and confirmed by Christ.",
                "A lei moral entregue no Sinai e confirmada por Cristo."
            )
        )
    }

    items(repository.theTenCommandments) { cmd ->
        GutterCard {
            CardHeading(title = cmd.title.get(currentLanguage))
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = cmd.biblicalWording.get(currentLanguage),
                style = styles.quote,
                color = design.palette.textSecondary
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = cmd.moralExplanation.get(currentLanguage),
                style = MaterialTheme.typography.bodyMedium,
                color = design.palette.textPrimary
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick(
                    "Pecados prohibidos",
                    "Sins forbidden",
                    "Pecados proibidos"
                ),
                body = cmd.sinsForbidden.get(currentLanguage),
                labelColor = design.palette.accent
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick(
                    "Virtudes mandadas",
                    "Virtues commanded",
                    "Virtudes ordenadas"
                ),
                body = cmd.virtuesCommanded.get(currentLanguage)
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
    }
}

private fun LazyListScope.precepts(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    design: AppDesign
) {
    item {
        AppSectionHeader(
            title = currentLanguage.pick(
                "Los Cinco Preceptos",
                "The Five Precepts",
                "Os Cinco Preceitos"
            ),
            subtitle = currentLanguage.pick(
                "El mínimo que la Iglesia pide a todo fiel bautizado.",
                "The minimum the Church asks of every baptised faithful.",
                "O mínimo que a Igreja pede a todo fiel batizado."
            )
        )
    }

    items(repository.churchPrecepts) { prc ->
        GutterCard {
            CardHeading(title = prc.title.get(currentLanguage))
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = prc.explanation.get(currentLanguage),
                style = MaterialTheme.typography.bodyMedium,
                color = design.palette.textPrimary
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Obligación", "Obligation", "Obrigação"),
                body = prc.applicationInLife.get(currentLanguage)
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
    }
}

private fun LazyListScope.sinsAndVirtues(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    design: AppDesign
) {
    item {
        AppSectionHeader(
            title = currentLanguage.pick(
                "Vicios Capitales y Virtudes",
                "Capital Vices and Virtues",
                "Vícios Capitais e Virtudes"
            ),
            subtitle = currentLanguage.pick(
                "Siete raíces del pecado y la virtud que cura cada una.",
                "Seven roots of sin and the virtue that heals each one.",
                "Sete raízes do pecado e a virtude que cura cada uma."
            )
        )
    }

    items(repository.capitalSinsVsVirtues) { item ->
        CapitalSinCard(item = item, currentLanguage = currentLanguage)
        Spacer(Modifier.height(design.spacing.cardGap))
    }
}

private fun LazyListScope.sinGravity(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    design: AppDesign
) {
    val st = repository.sinTheology

    item {
        AppSectionHeader(
            title = currentLanguage.pick(
                "La Gravedad del Pecado",
                "The Gravity of Sin",
                "A Gravidade do Pecado"
            ),
            subtitle = currentLanguage.pick(
                "Pecado venial y mortal, y cómo prepararse para la confesión.",
                "Venial and mortal sin, and how to prepare for confession.",
                "Pecado venial e mortal, e como preparar-se para a confissão."
            )
        )
    }

    item {
        GutterCard {
            CardHeading(
                title = currentLanguage.pick(
                    "Pecado Venial vs Pecado Mortal",
                    "Venial Sin vs Mortal Sin",
                    "Pecado Venial vs Pecado Mortal"
                )
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Pecado Venial", "Venial sin", "Pecado Venial"),
                body = st.venialExplanation.get(currentLanguage),
                labelColor = design.palette.gold
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Note(
                label = currentLanguage.pick("Pecado Mortal", "Mortal sin", "Pecado Mortal"),
                body = st.mortalExplanation.get(currentLanguage),
                labelColor = design.palette.accent
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
    }

    item {
        GutterCard {
            CardHeading(
                title = currentLanguage.pick(
                    "Las 3 Condiciones del Pecado Mortal",
                    "The 3 Conditions of Mortal Sin",
                    "As 3 Condições do Pecado Mortal"
                )
            )
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = currentLanguage.pick(
                    "Para que un pecado sea mortal se requieren simultáneamente tres condiciones:",
                    "A sin is mortal only when three conditions are met at the same time:",
                    "Para que um pecado seja mortal exigem-se simultaneamente três condições:"
                ),
                style = MaterialTheme.typography.bodySmall,
                color = design.palette.textSecondary
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            st.mortalThreeConditions.forEachIndexed { index, cond ->
                if (index > 0) Spacer(Modifier.height(design.spacing.cardGap / 2))
                NumberedLine(index + 1, cond.get(currentLanguage))
            }
        }
        Spacer(Modifier.height(design.spacing.cardGap))
    }

    item {
        GutterCard {
            CardHeading(
                title = currentLanguage.pick(
                    "Guía para la Confesión y Examen de Conciencia",
                    "Guide to Confession and the Examination of Conscience",
                    "Guia para a Confissão e Exame de Consciência"
                )
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            Text(
                text = st.confessionExamenGuide.get(currentLanguage),
                style = MaterialTheme.typography.bodyMedium,
                color = design.palette.textPrimary
            )
        }
    }
}

@Composable
private fun CapitalSinCard(item: CapitalSinVsVirtue, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current

    GutterCard {
        Note(
            label = currentLanguage.pick("Vicio", "Vice", "Vício"),
            body = item.capitalSin.get(currentLanguage),
            labelColor = design.palette.accent,
            bodyStyle = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = item.sinMeaning.get(currentLanguage),
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.textSecondary
        )

        Spacer(Modifier.height(design.spacing.cardGap))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))

        Note(
            label = currentLanguage.pick("Virtud", "Virtue", "Virtude"),
            body = item.opposingVirtue.get(currentLanguage),
            labelColor = design.palette.gold,
            bodyStyle = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick("Remedio", "Remedy", "Remédio"),
            body = item.virtueRemedy.get(currentLanguage)
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick("Consejo práctico", "In practice", "Conselho prático"),
            body = item.practicalAdvice.get(currentLanguage)
        )
    }
}

// --------------------------------------------------- history and the schisms

@Composable
private fun ChurchHistoryPane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    header: LazyListScope.() -> Unit
) {
    val design = LocalAppDesign.current

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Línea de tiempo de la Iglesia",
                    "Timeline of the Church",
                    "Linha do tempo da Igreja"
                ),
                subtitle = currentLanguage.pick(
                    "Dos mil años de fidelidad al mandato de Cristo, de Pentecostés a hoy.",
                    "Two thousand years of fidelity to Christ's commission, from Pentecost to today.",
                    "Dois mil anos de fidelidade ao mandato de Cristo, do Pentecostes até hoje."
                )
            )
        }

        items(repository.churchHistoryEpochs) { epoch ->
            ChurchEpochCard(epoch = epoch, currentLanguage = currentLanguage)
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Los cismas históricos",
                    "The historic schisms",
                    "Os cismas históricos"
                ),
                subtitle = currentLanguage.pick(
                    "El Cisma de 1054 y la Reforma de 1517, explicados con claridad y respeto.",
                    "The schism of 1054 and the Reformation of 1517, told clearly and with respect.",
                    "O Cisma de 1054 e a Reforma de 1517, explicados com clareza e respeito."
                )
            )
        }

        items(repository.churchSchisms) { schism ->
            SchismCard(schism = schism, currentLanguage = currentLanguage)
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

@Composable
private fun ChurchEpochCard(epoch: ChurchHistoryEpoch, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current

    GutterCard {
        CardHeading(title = epoch.title.get(currentLanguage), meta = epoch.centuryRange)
        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = epoch.narrative.get(currentLanguage),
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick("Papas claves", "Key popes", "Papas principais"),
            body = epoch.keyPopes.get(currentLanguage)
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick("Concilios", "Councils", "Concílios"),
            body = epoch.ecumenicalCouncils.get(currentLanguage)
        )
    }
}

@Composable
private fun SchismCard(schism: ChurchSchismHistory, currentLanguage: AppLanguage) {
    val design = LocalAppDesign.current

    GutterCard {
        CardHeading(title = schism.title.get(currentLanguage), meta = schism.historicalYear)
        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = schism.context.get(currentLanguage),
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.textPrimary
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick(
                "Diferencias doctrinales claves",
                "Key doctrinal differences",
                "Diferenças doutrinais principais"
            ),
            body = schism.theologicalDifferences.get(currentLanguage)
        )
        Spacer(Modifier.height(design.spacing.cardGap))
        Note(
            label = currentLanguage.pick(
                "Perspectiva y diálogo",
                "Perspective and dialogue",
                "Perspetiva e diálogo"
            ),
            body = schism.dialogueAndReconciliation.get(currentLanguage)
        )
    }
}

// ---------------------------------------------------- parishes and the feed

@Composable
private fun ParishesAndNewsPane(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    header: LazyListScope.() -> Unit
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current

    var parishSearchQuery by remember { mutableStateOf("") }
    // 0: all, 1: confessions, 2: adoration, 3: cathedrals.
    var selectedParishFilter by remember { mutableIntStateOf(0) }
    var selectedCountry by remember { mutableStateOf(ANY_PLACE) }
    var selectedRegion by remember { mutableStateOf(ANY_PLACE) }
    var selectedCity by remember { mutableStateOf(ANY_PLACE) }

    // Every level of the cascade is drawn from the directory itself. The
    // country list used to come from Locale.getISOCountries(), offering some
    // 250 countries while the regions and cities beneath it could only ever
    // come from the bundled parishes — so choosing any of the other ~240 gave
    // an empty region list, an empty city list and no results. The Parishes
    // tab is the one that searches the whole world.
    val allCountries = remember(repository) {
        listOf(ANY_PLACE) + repository.nearbyParishes
            .map { it.country }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
    }
    val availableRegions = remember(selectedCountry) {
        val scoped =
            if (selectedCountry == ANY_PLACE) repository.nearbyParishes
            else repository.nearbyParishes.filter { it.country == selectedCountry }
        listOf(ANY_PLACE) + scoped
            .map { it.stateOrRegion }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
    }
    val availableCities = remember(selectedCountry, selectedRegion) {
        var list = repository.nearbyParishes
        if (selectedCountry != ANY_PLACE) {
            list = list.filter { it.country == selectedCountry }
        }
        if (selectedRegion != ANY_PLACE) {
            list = list.filter { it.stateOrRegion == selectedRegion }
        }
        listOf(ANY_PLACE) + list
            .map { it.city }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
    }

    // A narrower choice above must not leave a stale one below it: picking
    // Spain after choosing a Mexican state previously left that state selected
    // and the list empty.
    LaunchedEffect(selectedCountry) {
        if (selectedRegion !in availableRegions) selectedRegion = ANY_PLACE
        if (selectedCity !in availableCities) selectedCity = ANY_PLACE
    }
    LaunchedEffect(selectedRegion) {
        if (selectedCity !in availableCities) selectedCity = ANY_PLACE
    }

    val filteredParishes = repository.nearbyParishes.filter { parish ->
        val matchesQuery = parish.name.contains(parishSearchQuery, ignoreCase = true) ||
                parish.diocese.contains(parishSearchQuery, ignoreCase = true) ||
                parish.address.contains(parishSearchQuery, ignoreCase = true) ||
                parish.city.contains(parishSearchQuery, ignoreCase = true) ||
                parish.country.contains(parishSearchQuery, ignoreCase = true)
        val matchesCountry = selectedCountry == ANY_PLACE || parish.country == selectedCountry
        val matchesRegion = selectedRegion == ANY_PLACE || parish.stateOrRegion == selectedRegion
        val matchesCity = selectedCity == ANY_PLACE || parish.city == selectedCity
        // These read the bundled directory's own Spanish wording, not anything
        // shown to the reader, so they are not translated.
        val matchesFilter = when (selectedParishFilter) {
            1 -> !parish.confessionTimes.contains("No disponible", ignoreCase = true)
            2 -> parish.isPerpetualAdoration ||
                    parish.adorationTimes.contains("24", ignoreCase = true) ||
                    parish.adorationTimes.contains("Perpetua", ignoreCase = true) ||
                    parish.adorationTimes.contains("Jueves", ignoreCase = true)
            3 -> parish.name.contains("Catedral", ignoreCase = true) ||
                    parish.name.contains("Basílica", ignoreCase = true)
            else -> true
        }
        matchesQuery && matchesCountry && matchesRegion && matchesCity && matchesFilter
    }

    // Live from the Vatican's own feed. The bundled articles that used to sit
    // here were two fixed entries that never changed, which is what made this
    // section look broken. Hoisted above the list because LazyListScope is not
    // a composable context.
    val newsState by produceState<NewsResult?>(
        initialValue = null,
        key1 = currentLanguage
    ) {
        value = VaticanNewsFetcher().latest(currentLanguage, limit = 25)
    }

    val parishFilters = listOf(
        currentLanguage.pick(
            "Todas (${filteredParishes.size})",
            "All (${filteredParishes.size})",
            "Todas (${filteredParishes.size})"
        ),
        currentLanguage.pick("Confesiones", "Confessions", "Confissões"),
        currentLanguage.pick("Adoración 24h", "24h Adoration", "Adoração 24h"),
        currentLanguage.pick("Catedrales", "Cathedrals", "Catedrais")
    )

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Buscador de iglesias",
                    "Church Finder",
                    "Buscador de igrejas"
                ),
                subtitle = currentLanguage.pick(
                    "El directorio por país y ciudad. Para buscar cerca de ti, usa Parroquias.",
                    "The directory by country and city. To search near you, use Parishes.",
                    "O diretório por país e cidade. Para buscar perto de você, use Paróquias."
                )
            )
        }

        item {
            AppSearchField(
                value = parishSearchQuery,
                onValueChange = { parishSearchQuery = it },
                placeholder = currentLanguage.pick(
                    "Nombre, dirección o diócesis",
                    "Name, address or diocese",
                    "Nome, endereço ou diocese"
                ),
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("parish_search_input")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // The cascade as a group of pop-up rows — label, current value, and a
        // menu of the choices — which every identity already knows how to set.
        item {
            AppListGroup {
                PlacePickerRow(
                    label = currentLanguage.pick("País", "Country", "País"),
                    selectedValue = selectedCountry,
                    anyLabel = currentLanguage.pick("Todos", "All", "Todos"),
                    options = allCountries,
                    onOptionSelected = {
                        selectedCountry = it
                        selectedRegion = ANY_PLACE
                        selectedCity = ANY_PLACE
                    },
                    leadingIcon = Icons.Default.Public,
                    testTag = "parish_country_dropdown"
                )
                PlacePickerRow(
                    label = currentLanguage.pick("Región", "Region", "Região"),
                    selectedValue = selectedRegion,
                    anyLabel = currentLanguage.pick("Todas", "All", "Todas"),
                    options = availableRegions,
                    onOptionSelected = {
                        selectedRegion = it
                        selectedCity = ANY_PLACE
                    },
                    leadingIcon = Icons.Default.Map,
                    testTag = "parish_region_dropdown"
                )
                PlacePickerRow(
                    label = currentLanguage.pick("Ciudad", "City", "Cidade"),
                    selectedValue = selectedCity,
                    anyLabel = currentLanguage.pick("Todas", "All", "Todas"),
                    options = availableCities,
                    onOptionSelected = { selectedCity = it },
                    leadingIcon = Icons.Default.LocationCity,
                    testTag = "parish_city_dropdown"
                )
                AppListRow(
                    title = currentLanguage.pick(
                        "Ver en Google Maps",
                        "Open in Google Maps",
                        "Ver no Google Maps"
                    ),
                    leadingIcon = Icons.Default.OpenInNew,
                    onClick = {
                        val query = listOf(selectedCity, selectedRegion, selectedCountry)
                            .filter { it != ANY_PLACE }
                            .joinToString(", ")
                        context.openMaps(
                            "https://www.google.com/maps/search/" +
                                Uri.encode("catholic church $query".trim())
                        )
                    },
                    modifier = Modifier.testTag("parish_open_maps")
                )
            }
            Spacer(Modifier.height(design.spacing.cardGap / 2))
        }

        item {
            ChipFlow {
                parishFilters.forEachIndexed { index, label ->
                    AppChip(
                        label = label,
                        selected = selectedParishFilter == index,
                        onClick = { selectedParishFilter = index }
                    )
                }
            }
        }

        if (filteredParishes.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Sin resultados", "No results", "Sem resultados"),
                    body = currentLanguage.pick(
                        "No se encontraron parroquias con el criterio buscado.",
                        "No parishes found matching your search.",
                        "Nenhuma paróquia encontrada com os critérios pesquisados."
                    ),
                    icon = Icons.Default.Church
                )
            }
        } else {
            items(filteredParishes, key = { it.id }) { parish ->
                ParishCard(parish = parish, currentLanguage = currentLanguage)
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick(
                    "Noticias del Vaticano",
                    "Vatican News",
                    "Notícias do Vaticano"
                ),
                subtitle = currentLanguage.pick(
                    "Directo del canal oficial de Vatican News.",
                    "Straight from the official Vatican News feed.",
                    "Direto do canal oficial do Vatican News."
                )
            )
        }

        when (val news = newsState) {
            null -> item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(design.spacing.gutter),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = design.palette.accent
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap))
                    Text(
                        text = currentLanguage.pick(
                            "Cargando noticias del Vaticano…",
                            "Loading news from the Vatican…",
                            "A carregar notícias do Vaticano…"
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = design.palette.textSecondary
                    )
                }
            }

            is NewsResult.Failure -> item {
                AppEmptyState(
                    title = currentLanguage.pick(
                        "Noticias no disponibles",
                        "News unavailable",
                        "Notícias indisponíveis"
                    ),
                    body = currentLanguage.pick(
                        "No se pudieron cargar las noticias (${news.reason}). Comprueba tu conexión.",
                        "Could not load the news (${news.reason}). Check your connection.",
                        "Não foi possível carregar as notícias (${news.reason}). Verifique a ligação."
                    ),
                    icon = Icons.Default.Newspaper
                )
            }

            is NewsResult.Success -> items(news.items) { article ->
                VaticanNewsCard(article = article, currentLanguage = currentLanguage)
                Spacer(Modifier.height(design.spacing.cardGap))
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

@Composable
private fun ParishCard(parish: NearbyParish, currentLanguage: AppLanguage) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    GutterCard(modifier = Modifier.testTag("parish_card_${parish.id}")) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Text(
                text = parish.name,
                style = MaterialTheme.typography.titleMedium,
                color = design.palette.textPrimary,
                modifier = Modifier.weight(1f)
            )
            if (parish.distanceDisplay.isNotBlank()) {
                Spacer(Modifier.width(design.spacing.iconLabelGap))
                Text(
                    text = eyebrow(parish.distanceDisplay),
                    style = styles.sectionEyebrow,
                    color = design.palette.gold,
                    maxLines = 1
                )
            }
        }

        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Text(
            text = "${parish.address} • ${parish.city}, ${parish.country} • ${parish.diocese}",
            style = MaterialTheme.typography.bodySmall,
            color = design.palette.textSecondary
        )

        if (parish.isPerpetualAdoration) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = eyebrow(
                    currentLanguage.pick(
                        "Adoración Perpetua 24 Horas",
                        "Perpetual Adoration, 24 Hours",
                        "Adoração Perpétua 24 Horas"
                    )
                ),
                style = styles.sectionEyebrow,
                color = design.palette.accent
            )
        }

        Spacer(Modifier.height(design.spacing.cardGap))

        Note(
            label = currentLanguage.pick(
                "Misas dominicales",
                "Sunday Mass",
                "Missas dominicais"
            ),
            body = parish.sundayMasses
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Note(
            label = currentLanguage.pick("Confesiones", "Confessions", "Confissões"),
            body = parish.confessionTimes
        )
        Spacer(Modifier.height(design.spacing.cardGap / 2))
        Note(
            label = currentLanguage.pick("Adoración", "Adoration", "Adoração"),
            body = parish.adorationTimes
        )

        Spacer(Modifier.height(design.spacing.cardGap))

        // Two full-width rows rather than two buttons abreast: "Cómo llegar"
        // in tracked capitals has no room in half a card at 360dp.
        AppListRow(
            title = currentLanguage.pick("Llamar", "Call", "Ligar"),
            subtitle = parish.contactPhone.ifBlank { null },
            leadingIcon = Icons.Default.Call,
            onClick = {
                val phone = parish.contactPhone.replace(" ", "")
                val dialled = runCatching {
                    context.startActivity(
                        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                    )
                }.isSuccess
                if (!dialled) {
                    Toast.makeText(context, parish.contactPhone, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.testTag("call_parish_${parish.id}")
        )
        AppListRow(
            title = currentLanguage.pick("Cómo llegar", "Directions", "Como chegar"),
            subtitle = parish.address.ifBlank { null },
            leadingIcon = Icons.Default.Directions,
            onClick = {
                val target = Uri.encode("${parish.name}, ${parish.address}")
                if (!context.openMaps("geo:0,0?q=$target")) {
                    Toast.makeText(context, parish.address, Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.testTag("map_parish_${parish.id}")
        )
    }
}

/**
 * One story from the Vatican's feed. Tapping it opens the article; the feed
 * carries only a summary.
 */
@Composable
private fun VaticanNewsCard(
    article: VaticanNewsItem,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    GutterCard(
        onClick = {
            runCatching {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.link)))
            }
        }
    ) {
        if (article.publishedAt.isNotBlank()) {
            Text(
                text = eyebrow(article.publishedAt),
                style = styles.sectionEyebrow,
                color = design.palette.textTertiary
            )
            Spacer(Modifier.height(design.spacing.cardGap / 2))
        }
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            color = design.palette.textPrimary
        )
        if (article.summary.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = article.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = design.palette.textSecondary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.height(design.spacing.cardGap))
        Text(
            text = currentLanguage.pick(
                "Leer en Vatican News →",
                "Read on Vatican News →",
                "Ler no Vatican News →"
            ),
            style = styles.buttonLabel,
            color = design.palette.accent
        )
    }
}

/**
 * One level of the geographic cascade, set as a list row that names the level
 * and shows the current choice; tapping it drops a menu of the choices. The
 * row is the kit's, so it reads as an iOS pop-up button, an M3 list item or a
 * ruled index line as the identity requires.
 */
@Composable
private fun PlacePickerRow(
    label: String,
    selectedValue: String,
    anyLabel: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    leadingIcon: ImageVector,
    testTag: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    var expanded by remember { mutableStateOf(false) }

    fun display(value: String) = if (value == ANY_PLACE) anyLabel else value

    Box(Modifier.fillMaxWidth()) {
        AppListRow(
            title = label,
            leadingIcon = leadingIcon,
            trailingText = display(selectedValue),
            onClick = { expanded = true },
            modifier = Modifier.testTag(testTag)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(design.spacing.gutter, 0.dp),
            modifier = Modifier
                .heightIn(max = 320.dp)
                .background(design.palette.raised)
        ) {
            options.forEach { option ->
                val isSelected = option == selectedValue
                DropdownMenuItem(
                    text = {
                        Text(
                            text = display(option),
                            style = styles.rowTitle,
                            color = if (isSelected) design.palette.accent
                            else design.palette.textPrimary
                        )
                    },
                    trailingIcon = if (isSelected) {
                        {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = design.palette.accent,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    } else null,
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

/**
 * A short set of chips (four at most here) that wraps instead of scrolling, so
 * no chip is ever guillotined at the right edge of a phone: every choice is in
 * view, inside the gutters, in every identity.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChipFlow(content: @Composable () -> Unit) {
    val design = LocalAppDesign.current
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = design.spacing.gutter,
                vertical = design.spacing.cardGap / 2
            ),
        horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2),
        verticalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
    ) {
        content()
    }
}

// ------------------------------------------------------------------ fittings

/** [AppCard] leaves the screen's inset to its caller; every card here wants it. */
@Composable
private fun GutterCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    AppCard(
        modifier = modifier.padding(horizontal = design.spacing.gutter),
        onClick = onClick,
        content = content
    )
}

/** A card's heading, with the century, year or reference that qualifies it. */
@Composable
private fun CardHeading(title: String, meta: String? = null) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = design.palette.textPrimary
        )
        if (meta != null && meta.isNotBlank()) {
            Spacer(Modifier.height(design.spacing.cardGap / 4))
            Text(
                text = eyebrow(meta),
                style = styles.sectionEyebrow,
                color = design.palette.gold
            )
        }
    }
}

/** An eyebrow label over the passage it introduces — a card's unit of content. */
@Composable
private fun Note(
    label: String,
    body: String,
    labelColor: Color? = null,
    bodyStyle: TextStyle? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = eyebrow(label),
            style = styles.sectionEyebrow,
            color = labelColor ?: design.palette.textSecondary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 4))
        Text(
            text = body,
            style = bodyStyle ?: MaterialTheme.typography.bodyMedium,
            color = design.palette.textPrimary
        )
    }
}

/** One condition of a numbered sequence, the figure hung in the margin. */
@Composable
private fun NumberedLine(number: Int, text: String) {
    val design = LocalAppDesign.current

    Row(Modifier.fillMaxWidth()) {
        Text(
            text = "$number",
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.accent,
            modifier = Modifier.width(design.spacing.iconLabelGap + 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = design.palette.textPrimary,
            modifier = Modifier.weight(1f)
        )
    }
}

/** Running labels are cut as capitals only where the identity sets them so. */
@Composable
private fun eyebrow(text: String): String =
    if (LocalAppDesign.current.decor.usesAllCapsLabels) text.uppercase() else text

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun tabStripInset(): Dp {
    val design = LocalAppDesign.current
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
}

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

/** Opens [uri] in whatever handles maps, reporting whether anything did. */
private fun Context.openMaps(uri: String): Boolean =
    runCatching { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri))) }.isSuccess
