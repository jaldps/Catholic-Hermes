package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.local.NovenaProgressEntity
import com.example.data.local.SanctaFidesDao
import com.example.data.models.AppLanguage
import com.example.data.models.Novena
import com.example.data.models.VaticanPrayer
import com.example.data.repository.CatholicDataRepository
import androidx.activity.compose.BackHandler
import com.example.ui.components.InlineBackLink
import com.example.ui.components.MenuPickerRow
import com.example.ui.design.AppBanner
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
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.ListStyle
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.text.DateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

/** The chaplet: a lead bead and ten small ones, five times over. */
private const val ChapletDecades = 5
private const val ChapletBeadsPerDecade = 10

/** The lead bead of a decade is cut larger than the ten that follow it. */
private const val ChapletLeadBeadScale = 1.6f

/** Ring weight where the identity rules its bead edges rather than filling them. */
private val BeadRingHairline = 0.5.dp

/**
 * How many entries of the novena index share one list group.
 *
 * There are 246 novenas, so the index has to stay lazy; but a group is what
 * gives Cupertino its inset block and Vatican its head rule, and neither can
 * straddle two lazy items. Blocking the index is the compromise: it scrolls
 * cheaply and still reads as grouped rows rather than 246 loose ones.
 */
private const val NovenaIndexBlock = 20

/** The frontispiece plate at the head of a novena that ships with an image. */
private val NovenaPlateHeight = 176.dp

/**
 * The Vatican devotional, the chaplet of Divine Mercy, the 246 official novenas
 * and the night office, behind one selector.
 *
 * Almost all of it is reading rather than operating: a prayer, a meditation and
 * a promise are set in the identity's reading face and held to its reading
 * measure, exactly as scripture is. The novenas are an index of 246 entries,
 * not a menu, so they are ruled rows carrying their own progress — and the day
 * of a novena opens as a page, with the nine days listed under it.
 */
@Composable
fun PrayersAndNovenasScreen(
    repository: CatholicDataRepository,
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    dao: SanctaFidesDao? = null
) {
    val design = LocalAppDesign.current
    val scope = rememberCoroutineScope()

    var selectedTab by remember { mutableIntStateOf(0) }

    // Devotional
    var prayerSearchQuery by remember { mutableStateOf("") }
    var selectedThematicTag by remember { mutableStateOf<String?>(null) }
    // A prayer opens as its own page; the list carries only a line of each.
    var openPrayerId by remember { mutableStateOf<String?>(null) }
    var showChaplet by remember { mutableStateOf(false) }

    // Novenas
    var selectedNovena by remember { mutableStateOf<Novena?>(null) }
    var novenaSearchQuery by remember { mutableStateOf("") }
    var selectedNovenaTag by remember { mutableStateOf<String?>(null) }
    var activeNovenaDay by remember { mutableIntStateOf(1) }
    // Keyed by novena id: a single shared set of day numbers reported the same
    // progress for all 246 of them, so every row read "day 1 of 9".
    // This is the session's own record: the whole record when there is no DAO
    // (previews, the screenshot test), and the optimistic echo of a write
    // until the database flow hands the row back when there is one.
    var sessionDaysPrayed by remember { mutableStateOf(emptyMap<String, Set<Int>>()) }
    // The intention `NovenaProgressEntity` carries alongside the day count. No
    // control writes it yet; it is held here so that wiring has one home.
    var personalIntention by remember { mutableStateOf("") }

    val persistedProgress by remember(dao) {
        dao?.getAllNovenaProgress() ?: flowOf(emptyList<NovenaProgressEntity>())
    }.collectAsState(initial = emptyList())
    val startedOn = remember(persistedProgress) {
        persistedProgress.associate { it.novenaId to it.startDate }
    }
    // Days only ever get added, so the union of the stored rows and this
    // session's marks is the truth in both the persisted and fallback modes.
    val daysPrayed = remember(persistedProgress, sessionDaysPrayed) {
        val stored = persistedProgress.associate { it.novenaId to decodeNovenaDays(it.currentDay) }
        (stored.keys + sessionDaysPrayed.keys).associateWith {
            stored[it].orEmpty() + sessionDaysPrayed[it].orEmpty()
        }
    }

    val currentlySpeaking by ttsManager.currentlySpeaking.collectAsState()

    val tabsInset = segmentedTabsInset()

    // One title for the screen, then the control that picks the view. Both
    // are the first rows of whichever list is showing, so they scroll away.
    val header: LazyListScope.() -> Unit = {
        item(key = "prayers_screen_title") {
            AppScreenTitle(title = currentLanguage.pick("Oraciones", "Prayers", "Orações"))
        }
        item(key = "prayers_screen_tabs") {
            AppSegmentedTabs(
                // The chaplet is a devotion, opened from the Prayers list,
                // and Compline goes by the name a newcomer knows.
                options = listOf(
                    currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                    currentLanguage.pick("Novenas", "Novenas", "Novenas"),
                    currentLanguage.pick("Noche", "Night prayer", "Noite")
                ),
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it },
                modifier = Modifier
                    .padding(horizontal = tabsInset)
                    .padding(bottom = design.spacing.cardGap / 2)
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("prayers_and_novenas_screen")
    ) {
        val openPrayer = openPrayerId?.let { id -> repository.vaticanPrayers.firstOrNull { it.id == id } }
        when {
            selectedTab == 0 && openPrayer != null -> {
                BackHandler { openPrayerId = null }
                PrayerDetailView(
                    prayer = openPrayer,
                    currentLanguage = currentLanguage,
                    currentlySpeaking = currentlySpeaking,
                    onBack = { openPrayerId = null },
                    onPlayAudio = {
                        ttsManager.speakDialogue(
                            openPrayer.leaderText.get(currentLanguage),
                            openPrayer.responseText?.get(currentLanguage) ?: ""
                        )
                    }
                )
            }

            selectedTab == 0 && showChaplet -> {
                BackHandler { showChaplet = false }
                DivineMercyChapletView(
                    ttsManager = ttsManager,
                    currentLanguage = currentLanguage,
                    onBack = { showChaplet = false },
                    onOpenNovena = {
                        showChaplet = false
                        selectedTab = 1
                        selectedNovena = repository.officialNovenas
                            .firstOrNull { it.id == "divine_mercy" }
                            ?: repository.officialNovenas.firstOrNull()
                        activeNovenaDay = selectedNovena
                            ?.let { nextNovenaDay(it, daysPrayed[it.id].orEmpty()) }
                            ?: 1
                    }
                )
            }

            selectedTab == 0 -> VaticanDevotionalView(
                header = header,
                repository = repository,
                currentLanguage = currentLanguage,
                searchQuery = prayerSearchQuery,
                onSearchQueryChange = { prayerSearchQuery = it },
                selectedTag = selectedThematicTag,
                onSelectTag = { selectedThematicTag = it },
                onOpenPrayer = { openPrayerId = it.id },
                onOpenChaplet = { showChaplet = true }
            )

            selectedTab == 1 -> {
                val novena = selectedNovena
                if (novena == null) {
                    NovenaIndexView(
                        header = header,
                        novenas = repository.officialNovenas,
                        currentLanguage = currentLanguage,
                        searchQuery = novenaSearchQuery,
                        onSearchQueryChange = { novenaSearchQuery = it },
                        selectedTag = selectedNovenaTag,
                        onSelectTag = { selectedNovenaTag = it },
                        daysPrayed = daysPrayed,
                        startedOn = startedOn,
                        onOpenNovena = {
                            selectedNovena = it
                            activeNovenaDay = nextNovenaDay(it, daysPrayed[it.id].orEmpty())
                        }
                    )
                } else {
                    NovenaDayView(
                        novena = novena,
                        currentLanguage = currentLanguage,
                        activeDay = activeNovenaDay,
                        onSelectDay = { activeNovenaDay = it },
                        daysPrayed = daysPrayed[novena.id].orEmpty(),
                        currentlySpeaking = currentlySpeaking,
                        onListen = { ttsManager.speakSingle(it) },
                        onMarkDayPrayed = {
                            val day = activeNovenaDay
                            val prayed = daysPrayed[novena.id].orEmpty() + day
                            sessionDaysPrayed = sessionDaysPrayed + (novena.id to prayed)
                            if (dao != null) {
                                scope.launch {
                                    // Read back rather than trust the flow: a mark
                                    // must never drop a day another write just added.
                                    val existing = dao.getNovenaProgress(novena.id)
                                    val days = prayed + decodeNovenaDays(existing?.currentDay ?: 0)
                                    val now = System.currentTimeMillis()
                                    dao.saveNovenaProgress(
                                        NovenaProgressEntity(
                                            novenaId = novena.id,
                                            currentDay = encodeNovenaDays(days),
                                            personalIntention = existing?.personalIntention
                                                ?: personalIntention,
                                            startDate = existing?.startDate ?: now,
                                            lastUpdated = now,
                                            isCompleted = isNovenaFinished(novena, days)
                                        )
                                    )
                                }
                            }
                            if (activeNovenaDay < novena.days.size) activeNovenaDay++
                        },
                        onBack = { selectedNovena = null }
                    )
                }
            }

            else -> NightPrayersScreen(
                repository = repository,
                ttsManager = ttsManager,
                currentLanguage = currentLanguage,
                header = header
            )
        }
    }
}

// ----------------------------------------------------------------- devotional

@Composable
private fun VaticanDevotionalView(
    header: LazyListScope.() -> Unit,
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedTag: String?,
    onSelectTag: (String?) -> Unit,
    onOpenPrayer: (VaticanPrayer) -> Unit,
    onOpenChaplet: () -> Unit
) {
    val design = LocalAppDesign.current

    val themes = listOf(
        null to currentLanguage.pick("Todas", "All", "Todas"),
        "health" to currentLanguage.pick("Salud y sanación", "Health & healing", "Saúde e cura"),
        "peace" to currentLanguage.pick("Paz interior", "Peace of mind", "Paz interior"),
        "family" to currentLanguage.pick("Familia", "Family", "Família"),
        "protection" to currentLanguage.pick("Protección", "Protection", "Proteção"),
        "forgiveness" to currentLanguage.pick("Perdón", "Forgiveness", "Perdão")
    )

    val prayers = repository.searchPrayers(query = searchQuery, tag = selectedTag)
    val filtering = searchQuery.isNotBlank() || selectedTag != null

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = currentLanguage.pick(
                    "Buscar una oración",
                    "Search prayers",
                    "Buscar uma oração"
                ),
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("prayer_search_input")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }

        // The theme is one row with a menu rather than a cloud of chips
        // under the screen's own selector.
        item {
            AppListGroup {
                MenuPickerRow(
                    title = currentLanguage.pick("Intención", "Intention", "Intenção"),
                    options = themes,
                    selected = selectedTag,
                    onSelect = onSelectTag,
                    testTag = "prayer_theme_picker"
                )
            }
        }

        if (prayers.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Ninguna oración coincide", "No matching prayers", "Nenhuma oração corresponde"),
                    body = currentLanguage.pick(
                        "Prueba otra palabra o elige otra intención.",
                        "Try another word or choose another intention.",
                        "Tente outra palavra ou escolha outra intenção."
                    ),
                    icon = Icons.Default.SearchOff
                )
                AppSecondaryButton(
                    label = currentLanguage.pick("Ver todas", "Show all prayers", "Ver todas"),
                    onClick = {
                        onSearchQueryChange("")
                        onSelectTag(null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("prayer_clear_filters")
                )
            }
        } else {
            // Seven prayers in six categories: one list reads better than six
            // headers over a row each.
            item(key = "prayer_list") {
                Spacer(Modifier.height(design.spacing.sectionGap / 2))
                AppListGroup {
                    prayers.forEach { prayer ->
                        AppListRow(
                            title = prayer.title.get(currentLanguage),
                            subtitle = prayerPreview(prayer, currentLanguage),
                            onClick = { onOpenPrayer(prayer) },
                            modifier = Modifier.testTag("prayer_row_${prayer.id}")
                        )
                    }
                    if (!filtering) {
                        AppListRow(
                            title = currentLanguage.pick(
                                "Coronilla de la Divina Misericordia",
                                "Chaplet of Divine Mercy",
                                "Terço da Divina Misericórdia"
                            ),
                            subtitle = currentLanguage.pick(
                                "Coronilla · para las tres de la tarde",
                                "Chaplet · for three in the afternoon",
                                "Terço · para as três da tarde"
                            ),
                            onClick = onOpenChaplet,
                            modifier = Modifier.testTag("open_chaplet_row")
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

/** One line of a prayer — its Latin incipit, or the opening of its text. */
private fun prayerPreview(prayer: VaticanPrayer, language: AppLanguage): String {
    prayer.latinTitle?.takeIf { it.isNotBlank() }?.let { return it }
    val text = prayer.leaderText.get(language).replace('\n', ' ').trim()
    return if (text.length > 64) text.take(64).substringBeforeLast(' ') + "…" else text
}

/** A prayer as its own page: title, the text set to be prayed, and its notes. */
@Composable
private fun PrayerDetailView(
    prayer: VaticanPrayer,
    currentLanguage: AppLanguage,
    currentlySpeaking: String?,
    onBack: () -> Unit,
    onPlayAudio: () -> Unit
) {
    LazyColumn(Modifier.fillMaxSize().testTag("prayer_detail_${prayer.id}")) {
        item {
            InlineBackLink(
                label = currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                onClick = onBack,
                testTag = "back_to_prayers_button"
            )
        }
        item {
            // The Latin incipit is set in true italic inside the card where
            // the identity prints Latin so; elsewhere it is the subtitle.
            AppScreenTitle(
                title = prayer.title.get(currentLanguage),
                subtitle = prayer.latinTitle.takeUnless { LocalAppDesign.current.language == DesignLanguage.VATICAN }
            )
        }
        item {
            ReadingColumn {
                VaticanPrayerCard(
                    prayer = prayer,
                    currentLanguage = currentLanguage,
                    currentlySpeaking = currentlySpeaking,
                    onPlayAudio = onPlayAudio
                )
            }
        }
        item { Spacer(Modifier.height(LocalAppDesign.current.spacing.contentBottomGap)) }
    }
}

@Composable
private fun VaticanPrayerCard(
    prayer: VaticanPrayer,
    currentLanguage: AppLanguage,
    currentlySpeaking: String?,
    onPlayAudio: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
        val latin = prayer.latinTitle?.takeIf { it.isNotBlank() }
        if (latin != null && design.language == DesignLanguage.VATICAN) {
            Text(
                text = latin,
                style = styles.rowTitle.copy(fontStyle = FontStyle.Italic),
                color = design.palette.accent
            )
            Spacer(Modifier.height(design.spacing.cardGap))
        }
        VersicleAndResponse(
            leaderText = prayer.leaderText.get(currentLanguage),
            responseText = prayer.responseText?.get(currentLanguage),
            spokenNow = currentlySpeaking
        )

        Spacer(Modifier.height(design.spacing.cardPadding))
        AppDivider()
        Spacer(Modifier.height(design.spacing.cardGap))

        NoteBlock(
            label = currentLanguage.pick("Cuándo rezarla", "When to pray it", "Quando rezá-la"),
            text = prayer.whenToPray.get(currentLanguage)
        )

        prayer.indulgenceNote?.let { note ->
            Spacer(Modifier.height(design.spacing.cardGap))
            NoteBlock(
                label = currentLanguage.pick("Indulgencia", "Indulgence", "Indulgência"),
                text = note.get(currentLanguage),
                textColor = design.palette.accent
            )
        }

        Spacer(Modifier.height(design.spacing.cardPadding))

        AppSecondaryButton(
            label = currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
            onClick = onPlayAudio,
            leadingIcon = Icons.Default.VolumeUp,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("play_prayer_audio_${prayer.id}")
        )
    }
}

// --------------------------------------------------------------- the chaplet

@Composable
private fun DivineMercyChapletView(
    ttsManager: LiturgicalTtsManager,
    currentLanguage: AppLanguage,
    onBack: () -> Unit,
    onOpenNovena: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    // 0 = opening prayers, 1 = the five decades, 2 = conclusion, 3 = 3:00 PM
    var chapletStep by remember { mutableIntStateOf(1) }
    var currentDecade by remember { mutableIntStateOf(1) }
    // 0 = the lead bead (Eternal Father), 1..10 = the small ones (His Passion)
    var currentBead by remember { mutableIntStateOf(0) }


    val openingPrayer = when (currentLanguage) {
        AppLanguage.ES -> "En el nombre del Padre, y del Hijo, y del Espíritu Santo. Amén.\n\n«Expiraste, Jesús, pero la fuente de vida brotó para las almas y el mar de misericordia se abrió para el mundo entero. ¡Oh fuente de vida, insondable Misericordia Divina!, abarca al mundo entero y derrámate sobre nosotros.\n\n¡Oh Sangre y Agua que brotaste del Corazón de Jesús como una fuente de misericordia para nosotros, en Ti confío! (repetir 3 veces)»\n\n[Padre Nuestro, Ave María, Credo de los Apóstoles]"
        AppLanguage.EN -> "In the name of the Father, and of the Son, and of the Holy Spirit. Amen.\n\n«You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.\n\nO Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You! (repeat 3 times)»\n\n[Our Father, Hail Mary, Apostles' Creed]"
        AppLanguage.PT -> "Em nome do Pai, e do Filho, e do Espírito Santo. Amém.\n\n«Expirastes, Jesus, mas a fonte da vida brotou para as almas e o mar de misericórdia abriu-se para o mundo inteiro. Ó fonte de vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-vos sobre nós.\n\nÓ Sangue e Água que brotastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós! (repetir 3 vezes)»\n\n[Pai Nosso, Ave Maria, Credo dos Apóstolos]"
    }

    val largeBeadPrayer = when (currentLanguage) {
        AppLanguage.ES -> "Padre Eterno, te ofrezco el Cuerpo y la Sangre, el Alma y la Divinidad de tu amadísimo Hijo, nuestro Señor Jesucristo, como propiciación de nuestros pecados y los del mundo entero."
        AppLanguage.EN -> "Eternal Father, I offer you the Body and Blood, Soul and Divinity of Your Dearly Beloved Son, Our Lord, Jesus Christ, in atonement for our sins and those of the whole world."
        AppLanguage.PT -> "Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade de Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro."
    }

    val smallBeadPrayer = when (currentLanguage) {
        AppLanguage.ES -> "Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero."
        AppLanguage.EN -> "For the sake of His sorrowful Passion, have mercy on us and on the whole world."
        AppLanguage.PT -> "Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro."
    }

    val closingPrayer = when (currentLanguage) {
        AppLanguage.ES -> "«Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.» (Repetir 3 veces)\n\nOh Dios Eterno, en quien la misericordia es infinita y el tesoro de compasión inagotable, vuelve a nosotros Tu mirada bondadosa y aumenta Tu misericordia en nosotros, para que en los momentos difíciles no desesperemos ni nos desalentemos, sino que con gran confianza nos sometamos a Tu santa voluntad, que es el Amor y la Misericordia mismos. Amén.\n\n¡Jesús, en Ti confío! (x3)"
        AppLanguage.EN -> "«Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.» (Repeat 3 times)\n\nEternal God, in whom mercy is endless and the treasury of compassion inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself. Amen.\n\nJesus, I trust in You! (x3)"
        AppLanguage.PT -> "«Santo Deus, Santo Forte, Santo Imortal, tende piedade de nós e do mundo inteiro.» (Repetir 3 vezes)\n\nÓ Deus Eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai-nos com bondade e aumentai a Vossa misericórdia em nós, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a Misericórdia. Amém.\n\nJesus, eu confio em Vós! (x3)"
    }

    val hourOfMercyText = when (currentLanguage) {
        AppLanguage.ES -> "«A las tres de la tarde implora Mi misericordia, especialmente para los pecadores; y aunque sea por un brevísimo momento, sumérgete en Mi Pasión, particularmente en Mi abandono en el momento de la agonía. Esta es la hora de la gran misericordia para todo el mundo. En esta hora no negaré nada al alma que Me pida por los méritos de Mi Pasión» (Diario de Santa Faustina Kowalska, 1320)."
        AppLanguage.EN -> "«At three o'clock, implore My mercy, especially for sinners; and, if only for a brief moment, immerse yourself in My Passion, particularly in My abandonment at the moment of agony. This is the hour of great mercy for the whole world. In this hour I will refuse nothing to the soul that makes a request of Me in virtue of My Passion» (Diary, 1320)."
        AppLanguage.PT -> "«Às três horas da tarde implora a Minha misericórdia, especialmente para os pecadores e, ao menos por um breve momento, aprofunda-te na Minha Paixão, particularmente no Meu desamparo no momento da agonia. Esta é a hora da grande misericórdia para o mundo inteiro. Nesta hora nada negarei à alma que Me pedir pelos méritos da Minha Paixão» (Diário, 1320)."
    }

    val stepNames = when (currentLanguage) {
        AppLanguage.ES -> listOf("Inicio", "Decenas", "Cierre", "15:00")
        AppLanguage.EN -> listOf("Opening", "Decades", "Closing", "3:00 PM")
        AppLanguage.PT -> listOf("Início", "Dezenas", "Final", "15:00")
    }

    val beadPrayer = if (currentBead == 0) largeBeadPrayer else smallBeadPrayer
    val stepText = when (chapletStep) {
        0 -> openingPrayer
        1 -> beadPrayer
        2 -> closingPrayer
        else -> hourOfMercyText
    }
    val listenLabel = currentLanguage.pick("Escuchar", "Listen", "Ouvir")

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            InlineBackLink(
                label = currentLanguage.pick("Oraciones", "Prayers", "Orações"),
                onClick = onBack,
                testTag = "back_from_chaplet_button"
            )
        }
        item {
            AppScreenTitle(
                title = currentLanguage.pick(
                    "Coronilla de la Divina Misericordia",
                    "Chaplet of Divine Mercy",
                    "Terço da Divina Misericórdia"
                ),
                subtitle = currentLanguage.pick(
                    "Revelada a santa Faustina en Vilna, 1935",
                    "Revealed to St. Faustina in Vilnius, 1935",
                    "Revelado a santa Faustina em Vilna, 1935"
                )
            )
        }

        item {
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        text = currentLanguage.pick(
                            "Promesa de Nuestro Señor: «Quienquiera que la rece recibirá gran misericordia a la hora de la muerte... Defenderé como Mi propia gloria a cada alma que rece esta coronilla» (Diario, 687).",
                            "Our Lord's Promise: «Whoever will recite it will receive great mercy at the hour of death... I will defend as My own glory every soul that will say this chaplet» (Diary, 687).",
                            "Promessa de Nosso Senhor: «Quem quer que a recite receberá grande misericórdia na hora da morte... Defenderei como Minha própria glória cada alma que recitar este terço» (Diário, 687)."
                        ),
                        style = styles.quote,
                        color = design.palette.textPrimary,
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )
                    Spacer(Modifier.height(design.spacing.cardPadding))
                    AppSecondaryButton(
                        label = listenLabel,
                        onClick = { ttsManager.speakSingle(stepText) },
                        leadingIcon = Icons.Default.VolumeUp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("chaplet_tts_button")
                    )
                }
            }
        }

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Partes de la coronilla", "Parts of the chaplet", "Partes do terço")
            )
            AppSegmentedTabs(
                options = stepNames,
                selectedIndex = chapletStep,
                onSelect = { chapletStep = it },
                modifier = Modifier.padding(horizontal = segmentedTabsInset())
            )
        }

        when (chapletStep) {
            0 -> item {
                Spacer(Modifier.height(design.spacing.cardGap))
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        BlockHeading(
                            eyebrow = currentLanguage.pick(
                                "Oraciones iniciales",
                                "Opening prayers",
                                "Orações iniciais"
                            ),
                            detail = null
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = openingPrayer,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppPrimaryButton(
                            label = currentLanguage.pick(
                                "Comenzar la 1ª decena",
                                "Begin the 1st decade",
                                "Começar a 1ª dezena"
                            ),
                            onClick = {
                                chapletStep = 1
                                currentDecade = 1
                                currentBead = 0
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            1 -> {
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        BlockHeading(
                            eyebrow = if (currentBead == 0) {
                                currentLanguage.pick(
                                    "Cuenta grande · Padre Eterno",
                                    "Lead bead · Eternal Father",
                                    "Conta grande · Eterno Pai"
                                )
                            } else {
                                currentLanguage.pick(
                                    "Cuenta pequeña · Dolorosa Pasión",
                                    "Small bead · His sorrowful Passion",
                                    "Conta pequena · Dolorosa Paixão"
                                )
                            },
                            detail = if (currentBead == 0) {
                                currentLanguage.pick(
                                    "Decena $currentDecade de $ChapletDecades",
                                    "Decade $currentDecade of $ChapletDecades",
                                    "Dezena $currentDecade de $ChapletDecades"
                                )
                            } else {
                                currentLanguage.pick(
                                    "Decena $currentDecade de $ChapletDecades · cuenta $currentBead de $ChapletBeadsPerDecade",
                                    "Decade $currentDecade of $ChapletDecades · bead $currentBead of $ChapletBeadsPerDecade",
                                    "Dezena $currentDecade de $ChapletDecades · conta $currentBead de $ChapletBeadsPerDecade"
                                )
                            }
                        )

                        Spacer(Modifier.height(design.spacing.cardGap))

                        DevotionRail(
                            count = ChapletBeadsPerDecade + 1,
                            activeIndex = currentBead,
                            isTold = { it < currentBead },
                            hasLeadBead = true,
                            onSelect = { currentBead = it }
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        Text(
                            text = beadPrayer,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )

                        Spacer(Modifier.height(design.spacing.cardPadding))

                        AppSecondaryButton(
                            label = listenLabel,
                            onClick = { ttsManager.speakSingle(beadPrayer) },
                            leadingIcon = Icons.Default.VolumeUp,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(design.spacing.cardGap / 2))

                        val atLastBead = currentBead == ChapletBeadsPerDecade &&
                            currentDecade == ChapletDecades
                        val canGoBack = currentBead > 0 || currentDecade > 1

                        // Two abreast at most, and only words short enough to
                        // survive Roman capitals at 360dp in all three languages.
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(design.spacing.cardGap / 2)
                        ) {
                            if (canGoBack) {
                                AppSecondaryButton(
                                    label = currentLanguage.pick("Atrás", "Back", "Voltar"),
                                    onClick = {
                                        if (currentBead > 0) {
                                            currentBead--
                                        } else {
                                            currentDecade--
                                            currentBead = ChapletBeadsPerDecade
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (!atLastBead) {
                                AppPrimaryButton(
                                    label = currentLanguage.pick("Avanzar", "Next", "Avançar"),
                                    onClick = {
                                        if (currentBead < ChapletBeadsPerDecade) {
                                            currentBead++
                                        } else {
                                            currentDecade++
                                            currentBead = 0
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        if (atLastBead) {
                            Spacer(Modifier.height(design.spacing.cardGap / 2))
                            AppPrimaryButton(
                                label = currentLanguage.pick(
                                    "Ir a la conclusión",
                                    "Go to the conclusion",
                                    "Ir à conclusão"
                                ),
                                onClick = { chapletStep = 2 },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            2 -> item {
                Spacer(Modifier.height(design.spacing.cardGap))
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        BlockHeading(
                            eyebrow = currentLanguage.pick(
                                "Doxología trinitaria y conclusión",
                                "Trinitarian doxology and conclusion",
                                "Doxologia trinitária e conclusão"
                            ),
                            detail = null
                        )
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = closingPrayer,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppPrimaryButton(
                            label = currentLanguage.pick(
                                "Rezar la novena de 9 días",
                                "Pray the 9-day novena",
                                "Rezar a novena de 9 dias"
                            ),
                            onClick = onOpenNovena,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            3 -> {
                item {
                    Spacer(Modifier.height(design.spacing.sectionGap / 2))
                    AppBanner(
                        title = currentLanguage.pick("15:00", "3:00 PM", "15:00"),
                        body = currentLanguage.pick(
                            "La Hora de la Misericordia",
                            "The Hour of Mercy",
                            "A Hora da Misericórdia"
                        )
                    )
                }
                item {
                    Spacer(Modifier.height(design.spacing.cardGap))
                    ReadingColumn {
                        AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                            Text(
                                text = hourOfMercyText,
                                style = styles.quote,
                                color = design.palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                            Spacer(Modifier.height(design.spacing.cardPadding))
                            AppSecondaryButton(
                                label = listenLabel,
                                onClick = { ttsManager.speakSingle(hourOfMercyText) },
                                leadingIcon = Icons.Default.VolumeUp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ----------------------------------------------------------- novena index

@Composable
private fun NovenaIndexView(
    header: LazyListScope.() -> Unit,
    novenas: List<Novena>,
    currentLanguage: AppLanguage,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedTag: String?,
    onSelectTag: (String?) -> Unit,
    daysPrayed: Map<String, Set<Int>>,
    startedOn: Map<String, Long>,
    onOpenNovena: (Novena) -> Unit
) {
    val design = LocalAppDesign.current

    // Drawn from the whole catalogue, not the filtered index: what someone is
    // praying should not disappear because a search is still typed in.
    val begun = remember(novenas.size, daysPrayed) {
        novenas.filter { daysPrayed[it.id].orEmpty().isNotEmpty() }
    }
    val inProgress = begun
        .filterNot { isNovenaFinished(it, daysPrayed[it.id].orEmpty()) }
        .sortedByDescending { startedOn[it.id] ?: 0L }
    val finished = begun
        .filter { isNovenaFinished(it, daysPrayed[it.id].orEmpty()) }
        .sortedByDescending { startedOn[it.id] ?: 0L }
    val dateFormat = remember(currentLanguage) {
        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale(currentLanguage.code))
    }

    val tagLabels = mapOf(
        "impossible" to currentLanguage.pick("Casos imposibles/difíciles", "Impossible/difficult cases", "Casos impossíveis/difíceis"),
        "blessings" to currentLanguage.pick("Bendiciones", "Blessings", "Bênçãos"),
        "love" to currentLanguage.pick("Amor", "Love", "Amor"),
        "money" to currentLanguage.pick("Dinero/Trabajo", "Money/Work", "Dinheiro/Trabalho"),
        "comfort" to currentLanguage.pick("Consuelo", "Comfort", "Consolo"),
        "health" to currentLanguage.pick("Salud/Cura", "Health/Healing", "Saúde/Cura"),
        "family" to currentLanguage.pick("Familia", "Family", "Família"),
        "peace" to currentLanguage.pick("Paz", "Peace", "Paz")
    )

    // Keyed on the size, not on the list: the catalogue is a singleton, and
    // comparing 246 novenas by value costs more than the work being cached.
    val availableTags = remember(novenas.size) {
        listOf("impossible", "blessings", "love", "money", "comfort", "health", "family", "peace")
            .filter { tag -> novenas.any { it.tags.contains(tag) } }
    }

    // 246 novenas, each with nine days of prayer and meditation to scan: far
    // too much to redo on every recomposition of the list.
    val filtered = remember(novenas.size, searchQuery, selectedTag, currentLanguage) {
        novenas.filter { novena ->
            val matchesSearch = searchQuery.isBlank() || listOf(
                novena.title.get(currentLanguage),
                novena.patron.get(currentLanguage),
                novena.description.get(currentLanguage)
            ).any { it.contains(searchQuery, ignoreCase = true) } ||
                novena.days.any {
                    it.prayer.get(currentLanguage).contains(searchQuery, ignoreCase = true) ||
                        it.meditation.get(currentLanguage).contains(searchQuery, ignoreCase = true)
                }
            val matchesTag = selectedTag == null || novena.tags.contains(selectedTag)
            matchesSearch && matchesTag
        }
    }

    val blockGap = if (design.decor.listStyle == ListStyle.INSET_GROUPED) {
        design.spacing.insetGroupGap
    } else {
        design.spacing.listItemGap
    }

    LazyColumn(Modifier.fillMaxSize()) {
        header()

        item {
            AppSearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = currentLanguage.pick(
                    "Buscar novena...",
                    "Search novena...",
                    "Buscar novena..."
                ),
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .testTag("novena_search_input")
            )
            Spacer(Modifier.height(design.spacing.cardGap))
            AppListGroup {
                MenuPickerRow(
                    title = currentLanguage.pick("Intención", "Intention", "Intenção"),
                    options = listOf<Pair<String?, String>>(null to currentLanguage.pick("Todas", "All", "Todas")) +
                        availableTags.map { it to (tagLabels[it] ?: it) },
                    selected = selectedTag,
                    onSelect = onSelectTag,
                    testTag = "novena_intention_picker"
                )
            }
        }

        if (inProgress.isNotEmpty()) {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick("En curso", "In progress", "Em andamento")
                )
                AppListGroup(modifier = Modifier.testTag("novenas_in_progress")) {
                    inProgress.forEach { novena ->
                        val next = nextNovenaDay(novena, daysPrayed[novena.id].orEmpty())
                        val started = startedOn[novena.id]?.let { dateFormat.format(Date(it)) }
                        AppListRow(
                            title = novena.title.get(currentLanguage),
                            subtitle = started?.let {
                                currentLanguage.pick(
                                    "Comenzada el $it",
                                    "Started $it",
                                    "Iniciada em $it"
                                )
                            } ?: novena.patron.get(currentLanguage),
                            trailingText = novenaDayOf(currentLanguage, next, novena.days.size),
                            onClick = { onOpenNovena(novena) }
                        )
                    }
                }
            }
        }

        if (finished.isNotEmpty()) {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick("Completadas", "Completed", "Concluídas")
                )
                AppListGroup(modifier = Modifier.testTag("novenas_completed")) {
                    finished.forEach { novena ->
                        AppListRow(
                            title = novena.title.get(currentLanguage),
                            subtitle = novena.patron.get(currentLanguage),
                            trailingText = novenaFinishedLabel(currentLanguage),
                            onClick = { onOpenNovena(novena) }
                        )
                    }
                }
            }
        }

        if (filtered.isEmpty()) {
            item {
                AppEmptyState(
                    title = currentLanguage.pick("Ninguna novena coincide", "No matching novenas", "Nenhuma novena corresponde"),
                    body = currentLanguage.pick(
                        "Prueba otra palabra o elige otra intención.",
                        "Try another word or choose another intention.",
                        "Tente outra palavra ou escolha outra intenção."
                    ),
                    icon = Icons.Default.SearchOff
                )
                AppSecondaryButton(
                    label = currentLanguage.pick("Ver todas", "Show all novenas", "Ver todas"),
                    onClick = {
                        onSearchQueryChange("")
                        onSelectTag(null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                )
            }
        } else {
            item {
                // The count is said only when a search has narrowed the index.
                AppSectionHeader(
                    title = currentLanguage.pick("Todas las novenas", "All novenas", "Todas as novenas"),
                    subtitle = if (filtered.size < novenas.size) currentLanguage.pick(
                        "${filtered.size} de ${novenas.size}",
                        "${filtered.size} of ${novenas.size}",
                        "${filtered.size} de ${novenas.size}"
                    ) else null
                )
            }

            items(filtered.chunked(NovenaIndexBlock)) { block ->
                AppListGroup {
                    block.forEach { novena ->
                        val prayed = daysPrayed[novena.id].orEmpty()
                        AppListRow(
                            title = novena.title.get(currentLanguage),
                            subtitle = novena.patron.get(currentLanguage),
                            trailingText = when {
                                prayed.isEmpty() -> currentLanguage.pick(
                                    "${novena.days.size} días",
                                    "${novena.days.size} days",
                                    "${novena.days.size} dias"
                                )
                                isNovenaFinished(novena, prayed) -> novenaFinishedLabel(currentLanguage)
                                else -> novenaDayOf(
                                    currentLanguage,
                                    nextNovenaDay(novena, prayed),
                                    novena.days.size
                                )
                            },
                            onClick = { onOpenNovena(novena) }
                        )
                    }
                }
                Spacer(Modifier.height(blockGap))
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ------------------------------------------------------------- novena page

@Composable
private fun NovenaDayView(
    novena: Novena,
    currentLanguage: AppLanguage,
    activeDay: Int,
    onSelectDay: (Int) -> Unit,
    daysPrayed: Set<Int>,
    currentlySpeaking: String?,
    onListen: (String) -> Unit,
    onMarkDayPrayed: () -> Unit,
    onBack: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    val dayData = novena.days.firstOrNull { it.dayNumber == activeDay } ?: novena.days.first()
    val prayerText = dayData.prayer.get(currentLanguage)
    // The source data stores each day's prayer in both fields, so the
    // meditation is shown only when it says something the prayer does not —
    // otherwise every day displayed its prayer twice.
    val meditation = dayData.meditation.get(currentLanguage)
        .takeUnless { it.trim() == prayerText.trim() }
        .orEmpty()
    val guidance = dayData.intentionGuidance.get(currentLanguage)
    val description = novena.description.get(currentLanguage)

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = design.spacing.cardGap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    Modifier
                        .weight(1f)
                        .clickable(onClick = onBack)
                        .padding(vertical = design.spacing.rowPaddingVertical)
                        .testTag("back_to_novenas_button"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = design.palette.accent,
                        modifier = Modifier.size(design.decor.rowIconSize)
                    )
                    Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
                    Text(
                        text = caps(
                            currentLanguage.pick(
                                "Lista de Novenas",
                                "All Novenas",
                                "Todas as Novenas"
                            )
                        ),
                        style = styles.buttonLabel,
                        color = design.palette.accent,
                        maxLines = 1
                    )
                }
                Spacer(Modifier.width(design.spacing.cardGap))
                Text(
                    text = caps(novena.category.get(currentLanguage)),
                    style = styles.sectionEyebrow,
                    color = design.palette.gold,
                    maxLines = 1
                )
            }
        }

        item {
            AppScreenTitle(
                title = novena.title.get(currentLanguage),
                subtitle = novena.patron.get(currentLanguage)
            )
        }

        novena.imageUrl?.let { url ->
            item {
                AsyncImage(
                    model = url,
                    contentDescription = novena.title.get(currentLanguage),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .height(NovenaPlateHeight)
                        .clip(design.shapes.cardLarge)
                )
                Spacer(Modifier.height(design.spacing.sectionGap / 2))
            }
        }

        if (description.isNotBlank()) {
            item {
                ReadingColumn {
                    Text(
                        text = description,
                        style = styles.quote,
                        color = design.palette.textSecondary,
                        modifier = Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .widthIn(max = design.spacing.readingMaxWidth)
                    )
                }
                Spacer(Modifier.height(design.spacing.sectionGap / 2))
            }
        }

        item {
            AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                BlockHeading(
                    eyebrow = currentLanguage.pick(
                        "Progreso: día $activeDay de ${novena.days.size}",
                        "Progress: day $activeDay of ${novena.days.size}",
                        "Progresso: dia $activeDay de ${novena.days.size}"
                    ),
                    detail = currentLanguage.pick(
                        "${daysPrayed.size} rezados",
                        "${daysPrayed.size} prayed",
                        "${daysPrayed.size} rezados"
                    )
                )
                Spacer(Modifier.height(design.spacing.cardGap))
                DevotionRail(
                    count = novena.days.size,
                    activeIndex = activeDay - 1,
                    isTold = { daysPrayed.contains(it + 1) },
                    label = { "${it + 1}" },
                    onSelect = { onSelectDay(it + 1) }
                )
            }
        }

        item {
            Spacer(Modifier.height(design.spacing.cardGap))
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    BlockHeading(
                        eyebrow = currentLanguage.pick(
                            "Día $activeDay",
                            "Day $activeDay",
                            "Dia $activeDay"
                        ),
                        detail = dayData.scripture.ifBlank { null }
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    Text(
                        text = dayData.theme.get(currentLanguage),
                        style = styles.sectionTitle,
                        color = design.palette.textPrimary
                    )

                    if (meditation.isNotBlank()) {
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Text(
                            text = meditation,
                            style = styles.quote,
                            color = design.palette.textPrimary,
                            modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                        )
                    }

                    if (guidance.isNotBlank()) {
                        Spacer(Modifier.height(design.spacing.cardPadding))
                        AppDivider()
                        Spacer(Modifier.height(design.spacing.cardGap))
                        Row(Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = design.palette.textTertiary,
                                modifier = Modifier.size(design.decor.rowIconSize)
                            )
                            Spacer(Modifier.width(design.spacing.iconLabelGap))
                            NoteBlock(
                                label = currentLanguage.pick(
                                    "Tu intención",
                                    "Your intention",
                                    "Sua intenção"
                                ),
                                text = guidance,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(design.spacing.cardGap))
            ReadingColumn {
                AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                    BlockHeading(
                        eyebrow = currentLanguage.pick(
                            "Oración del Día $activeDay",
                            "Prayer for Day $activeDay",
                            "Oração do Dia $activeDay"
                        ),
                        detail = null
                    )
                    Spacer(Modifier.height(design.spacing.cardGap))
                    Text(
                        text = prayerText,
                        style = styles.scripture,
                        color = if (currentlySpeaking == prayerText) {
                            design.palette.accent
                        } else {
                            design.palette.textPrimary
                        },
                        modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                    )

                    Spacer(Modifier.height(design.spacing.cardPadding))

                    // Stacked rather than set two-across: "Completar día" in
                    // tracked Roman capitals needs the full measure at 360dp.
                    AppSecondaryButton(
                        label = currentLanguage.pick("Escuchar", "Listen", "Ouvir"),
                        onClick = { onListen(prayerText) },
                        leadingIcon = Icons.Default.VolumeUp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("novena_listen_button")
                    )
                    Spacer(Modifier.height(design.spacing.cardGap / 2))
                    AppPrimaryButton(
                        label = if (daysPrayed.contains(activeDay)) {
                            currentLanguage.pick("Día rezado", "Day prayed", "Dia rezado")
                        } else {
                            currentLanguage.pick("Completar día", "Complete day", "Completar dia")
                        },
                        onClick = onMarkDayPrayed,
                        leadingIcon = Icons.Default.Check,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("complete_novena_day_button")
                    )
                }
            }
        }

        item { AppOrnament() }

        item {
            AppSectionHeader(
                title = currentLanguage.pick("Los nueve días", "The nine days", "Os nove dias")
            )
            AppListGroup {
                novena.days.forEach { day ->
                    val prayed = daysPrayed.contains(day.dayNumber)
                    AppListRow(
                        title = day.theme.get(currentLanguage),
                        subtitle = day.scripture.ifBlank { null },
                        trailingText = if (prayed) "✓ ${day.dayNumber}" else "${day.dayNumber}",
                        showsNavigationAffordance = false,
                        selected = day.dayNumber == activeDay,
                        onClick = { onSelectDay(day.dayNumber) }
                    )
                }
            }
        }

        val promises = novena.promises.get(currentLanguage)
        val history = novena.miraculousHistory.get(currentLanguage)
        if (promises.isNotBlank() || history.isNotBlank()) {
            item {
                AppSectionHeader(
                    title = currentLanguage.pick(
                        "Promesas y milagros registrados",
                        "Recorded promises and miracles",
                        "Promessas e milagres registrados"
                    )
                )
                ReadingColumn {
                    AppCard(modifier = Modifier.padding(horizontal = design.spacing.gutter)) {
                        if (promises.isNotBlank()) {
                            Text(
                                text = promises,
                                style = styles.quote,
                                color = design.palette.textPrimary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                        }
                        if (promises.isNotBlank() && history.isNotBlank()) {
                            Spacer(Modifier.height(design.spacing.cardPadding))
                            AppDivider()
                            Spacer(Modifier.height(design.spacing.cardGap))
                        }
                        if (history.isNotBlank()) {
                            Text(
                                text = history,
                                style = styles.quote,
                                color = design.palette.textSecondary,
                                modifier = Modifier.widthIn(max = design.spacing.readingMaxWidth)
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(design.spacing.contentBottomGap)) }
    }
}

// ------------------------------------------------------------------ pieces

/**
 * A run of beads or of days: the nine days of a novena, or the lead bead and
 * ten small ones of a decade.
 *
 * No kit component can express a decade, so this is drawn here — but from
 * tokens, so the bead is a filled circle on glass and an unfilled ring of leaf
 * on paper, and nothing has to know which identity is in force.
 */
@Composable
private fun DevotionRail(
    count: Int,
    activeIndex: Int,
    isTold: (Int) -> Boolean,
    onSelect: (Int) -> Unit,
    label: ((Int) -> String)? = null,
    hasLeadBead: Boolean = false,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val palette = design.palette
    val styles = LocalAppTextStyles.current

    val engraved = design.depth.cardBorderWidth > 0.dp
    val ringWidth = maxOf(
        design.depth.cardBorderWidth,
        if (design.depth.usesHairlines) BeadRingHairline else 0.dp
    )

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val gap = design.spacing.cardGap / 3
        val slots = count + (if (hasLeadBead) ChapletLeadBeadScale - 1f else 0f)
        val bead = ((maxWidth - gap * (count - 1)) / slots)
            .coerceIn(0.dp, design.spacing.rowMinHeight / 2)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(gap, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(count) { index ->
                val isActive = index == activeIndex
                val told = isTold(index)
                val fill by animateColorAsState(
                    targetValue = when {
                        isActive -> palette.accent
                        engraved -> Color.Transparent
                        told -> palette.accent.copy(alpha = 0.32f)
                        else -> palette.selectionTint
                    },
                    animationSpec = tween(
                        durationMillis = design.motion.fastMillis,
                        easing = design.motion.standardEasing
                    ),
                    label = "beadFill"
                )
                val ring = when {
                    engraved && (isActive || told) -> palette.gold
                    engraved -> palette.gold.copy(alpha = 0.45f)
                    design.depth.usesHairlines && !isActive -> palette.hairline
                    else -> Color.Transparent
                }
                Box(
                    Modifier
                        .size(if (hasLeadBead && index == 0) bead * ChapletLeadBeadScale else bead)
                        .clip(CircleShape)
                        .background(fill)
                        .border(ringWidth, ring, CircleShape)
                        .clickable { onSelect(index) },
                    contentAlignment = Alignment.Center
                ) {
                    if (label != null) {
                        Text(
                            text = label(index),
                            style = styles.verseNumber,
                            color = if (isActive) palette.raised else palette.textSecondary,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

/** Leader and response, with whichever line the voice is on set in rubric. */
@Composable
private fun VersicleAndResponse(
    leaderText: String,
    responseText: String?,
    spokenNow: String?
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val rubric = rubricSigil()

    // Some prayers (the Angelus) already carry their own sigla line by line;
    // those are set as they stand rather than given a second sign.
    fun signed(sign: String, text: String) =
        if (SiglaPattern.containsMatchIn(text)) withLiturgicalSigla(text, rubric)
        else buildAnnotatedString {
            withStyle(rubric) { append(sign) }
            append("\u2002")
            append(text)
        }

    Column(Modifier.widthIn(max = design.spacing.readingMaxWidth)) {
        Text(
            text = signed(VersicleSign, leaderText),
            style = styles.quote,
            color = if (spokenNow == leaderText) design.palette.accent else design.palette.textPrimary
        )
        if (responseText != null) {
            Spacer(Modifier.height(design.spacing.cardGap / 2))
            Text(
                text = signed(ResponseSign, responseText),
                style = styles.quote,
                color = if (spokenNow == responseText) design.palette.accent else design.palette.textSecondary
            )
        }
    }
}

/** ℣ — the versicle, as the liturgical books print it. */
internal const val VersicleSign = "\u2123"

/** ℟ — the response. */
internal const val ResponseSign = "\u211F"

/** The typed-out sigla ("V/." and "R/.") the bundled texts carry. */
internal val SiglaPattern = Regex("""(?<!\p{L})([VR])/\.""")

/** The sigla's rubric: the identity's accent, which is Vatican's missal red. */
@Composable
internal fun rubricSigil(): SpanStyle = SpanStyle(
    color = LocalAppDesign.current.palette.accent,
    fontWeight = FontWeight.SemiBold
)

/**
 * The text with each typed-out "V/." and "R/." set as ℣ and ℟ in [rubric], at
 * render time, so the bundled data and the text-to-speech feed stay as they are.
 */
internal fun withLiturgicalSigla(text: String, rubric: SpanStyle): AnnotatedString =
    buildAnnotatedString {
        var last = 0
        SiglaPattern.findAll(text).forEach { match ->
            append(text.substring(last, match.range.first))
            withStyle(rubric) {
                append(if (match.groupValues[1] == "V") VersicleSign else ResponseSign)
            }
            last = match.range.last + 1
        }
        append(text.substring(last))
    }

/** An eyebrow over a card, with its reference or count on the line below. */
@Composable
private fun BlockHeading(eyebrow: String, detail: String?) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column {
        Text(
            text = caps(eyebrow),
            style = styles.sectionEyebrow,
            color = design.palette.textSecondary
        )
        if (detail != null) {
            Spacer(Modifier.height(design.spacing.cardGap / 3))
            Text(
                text = detail,
                style = styles.rowSubtitle,
                color = design.palette.textTertiary
            )
        }
    }
}

/** A small label with its line of apparatus under it. */
@Composable
private fun NoteBlock(
    label: String,
    text: String,
    textColor: Color? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current

    Column(modifier.fillMaxWidth()) {
        Text(
            text = caps(label),
            style = styles.sectionEyebrow,
            color = design.palette.textTertiary
        )
        Spacer(Modifier.height(design.spacing.cardGap / 3))
        Text(
            text = text,
            style = styles.rowSubtitle,
            color = textColor ?: design.palette.textSecondary
        )
    }
}

/**
 * Holds long-form text to the identity's reading measure and centres it, so a
 * wide screen reads as the column of a book instead of a wall of type. The two
 * gutters are added back because the card inside carries them itself.
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

/**
 * No kit insets its own segmented control, so the gutter is the caller's to
 * give. Vatican is the exception: its strip is a ruled band whose hairline runs
 * the full width of the leaf, and inset it stops meeting the edges.
 */
@Composable
private fun segmentedTabsInset(): Dp {
    val design = LocalAppDesign.current
    return if (design.language == DesignLanguage.VATICAN) 0.dp else design.spacing.gutter
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

// ------------------------------------------------------- novena progress

/*
 * `NovenaProgressEntity.currentDay` is stored as a bitmask of the days prayed:
 * bit (n - 1) set means day n is done. A single "current day" cannot say which
 * days are done once someone prays them out of order, which the rail allows,
 * and a mask holds that in the existing column with no schema change. Nothing
 * else reads the column; the entity's own "1 to 9" note predates this.
 */
private fun encodeNovenaDays(days: Set<Int>): Int =
    days.filter { it in 1..31 }.fold(0) { mask, day -> mask or (1 shl (day - 1)) }

private fun decodeNovenaDays(mask: Int): Set<Int> =
    (1..31).filterTo(mutableSetOf()) { mask and (1 shl (it - 1)) != 0 }

private fun isNovenaFinished(novena: Novena, prayed: Set<Int>): Boolean =
    novena.days.isNotEmpty() && novena.days.all { prayed.contains(it.dayNumber) }

/** The first day not yet prayed, or the first day once the novena is finished. */
private fun nextNovenaDay(novena: Novena, prayed: Set<Int>): Int =
    novena.days.firstOrNull { !prayed.contains(it.dayNumber) }?.dayNumber
        ?: novena.days.firstOrNull()?.dayNumber
        ?: 1

private fun novenaDayOf(language: AppLanguage, day: Int, of: Int): String = language.pick(
    "Día $day de $of",
    "Day $day of $of",
    "Dia $day de $of"
)

private fun novenaFinishedLabel(language: AppLanguage): String = language.pick(
    "✓ Completada",
    "✓ Completed",
    "✓ Concluída"
)
