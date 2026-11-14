package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.BuildConfig
import com.example.data.models.AppLanguage
import com.example.data.repository.CatechismOutline
import com.example.ui.components.ImageCredits
import com.example.ui.components.ImageCreditsSheet
import com.example.ui.design.AppChevron
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppHeroHeader
import com.example.ui.design.AppIconSlot
import com.example.ui.design.AppIcons
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.design.rememberAssetBitmap
import com.example.ui.design.rememberFirstAsset
import com.example.ui.design.rememberHeroMeta
import com.example.ui.design.softShadow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import java.text.NumberFormat
import java.util.Locale

/** One secondary destination as the More screen lists it. */
data class MoreEntry(
    val title: String,
    val iconSlot: AppIconSlot,
    val tag: String,
    val value: String? = null,
    /** Overrides the slot's glyph, for rows that are settings, not places. */
    val icon: ImageVector? = null,
    /** Optional one-line description; known destinations carry their own. */
    val subtitle: String? = null,
    val onOpen: () -> Unit
)

/** A titled run of [MoreEntry]s. */
data class MoreSection(val title: String, val entries: List<MoreEntry>)

// Destinations by role. The screen lays itself out from these tags, so it is
// right whatever grouping the caller sends; anything it does not know keeps
// its caller's section and still renders (nothing is ever dropped).
private val StudyTags = listOf(
    "nav_catechism", "nav_study_doctrine", "nav_study_mary_jesus",
    "nav_study_history", "nav_questions", "nav_study_orders"
)
private val InnerLifeTags = listOf("nav_spiritual", "nav_habits")
private val ChurchTodayTags = listOf("nav_parishes", "nav_pilgrimage", "nav_churches_news")
private val SettingsTags = listOf("app_bar_theme_button", "font_settings_button", "calendar_rules_settings", "app_bar_lang_button")

/**
 * Explore — the sixth tab: everything that does not earn a place in the tab
 * bar, one tap away, with a clear order of weight:
 *
 * 1. a quiet hero (Friedrich's meadows) with the "Explorar" title;
 * 2. **Study** as six art-led cards — the reference's misty card: the
 *    painting on the trailing side dissolving into glass, the title and a
 *    one-line promise of what is inside on the leading side, in ink;
 * 3. **Inner life** and **The Church today** as quiet grouped lists with
 *    outline glyphs and a footnote of what each holds;
 * 4. **Settings** (appearance, font, language, image credits) with their
 *    current values, and the version.
 *
 * Every entry keeps its testTag on its clickable node; the scroll container
 * keeps `more_screen`.
 */
@Composable
fun MoreScreen(
    currentLanguage: AppLanguage,
    sections: List<MoreSection>,
    onOpenBeginner: () -> Unit = {}
) {
    val insets = AppEdgeToEdge()
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    fun pick(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }

    val all = sections.flatMap { it.entries }
    val byTag = all.associateBy { it.tag }
    val known = (StudyTags + InnerLifeTags + ChurchTodayTags + SettingsTags).toSet()
    val study = StudyTags.mapNotNull { byTag[it] }
    val innerLife = InnerLifeTags.mapNotNull { byTag[it] }
    val churchToday = ChurchTodayTags.mapNotNull { byTag[it] }
    val settings = SettingsTags.mapNotNull { byTag[it] }
    val leftovers = sections
        .map { s -> s.copy(entries = s.entries.filter { it.tag !in known }) }
        .filter { it.entries.isNotEmpty() }

    var showCredits by remember { mutableStateOf(false) }
    val creditCount = remember { ImageCredits.count(context) }
    val heroAsset = rememberFirstAsset("heroes/lumen/explore.jpg", "heroes/sunrise_mountains.jpg")

    // Appearance value when the caller sends none: the mode in force.
    val modeFallback = if (design.isDark) pick("Oscuro", "Dark", "Escuro") else pick("Claro", "Light", "Claro")

    LazyColumn(
        Modifier
            .fillMaxSize()
            .testTag("more_screen"),
        contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 24.dp)
    ) {
        item(key = "more_hero") {
            AppHeroHeader(
                assetPath = heroAsset,
                title = pick("Explorar", "Explore", "Explorar"),
                height = 232.dp,
                titleTag = "more_title",
                modifier = Modifier.testTag("more_hero_photo")
            )
        }

        var sectionIndex = 0
        fun header(key: String, title: String) {
            val index = sectionIndex++
            item(key = "more_header_$key") {
                if (index > 0) Spacer(Modifier.height(design.spacing.sectionGap - 8.dp))
                AppSectionHeader(title = title, modifier = Modifier.testTag("more_section_$index"))
            }
        }

        item(key = "more_beginner") {
            com.example.ui.design.AppCard(Modifier.padding(horizontal = design.spacing.gutter).testTag("explore_start_here")) {
                Text(pick("Empieza aquí", "Start here", "Comece aqui"), style = styles.title2, color = p.textPrimary)
                Spacer(Modifier.height(8.dp))
                Text(pick("Jesús, oración, primera Misa y confesión. Siete pasos a tu ritmo.", "Jesus, prayer, first Mass and confession. Seven steps at your pace.", "Jesus, oração, primeira Missa e confissão. Sete passos no seu ritmo."), style = styles.callout, color = p.textSecondary)
                Spacer(Modifier.height(12.dp))
                com.example.ui.design.AppPrimaryCapsule(pick("Dar un primer paso", "Take a first step", "Dar um primeiro passo"), onClick = onOpenBeginner, tag = "nav_beginner")
            }
        }
        if (churchToday.isNotEmpty()) {
            header("church_first", pick("Misa y comunidad", "Mass & community", "Missa e comunidade"))
            item(key = "more_church_first") { EntryGroup(churchToday, currentLanguage, modeFallback) }
        }
        header("settings_first", pick("Ajustes", "Settings", "Ajustes"))
        item(key = "more_settings_first") { EntryGroup(settings, currentLanguage, modeFallback) }

        if (study.isNotEmpty()) {
            header("study", pick("Estudio", "Study", "Estudo"))
            item(key = "more_study_cards") {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = design.spacing.gutter)
                        .testTag("more_section_hero_0"),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    study.forEach { entry ->
                        val info = studyCardInfo(entry.tag, currentLanguage)
                        StudyCard(
                            title = info?.title ?: entry.title,
                            meta = info?.meta ?: entry.subtitle,
                            spokenTitle = entry.title,
                            assetPath = info?.art,
                            onClick = entry.onOpen,
                            tag = entry.tag
                        )
                    }
                }
            }
        }

        if (innerLife.isNotEmpty()) {
            header("inner", pick("Vida interior", "Inner life", "Vida interior"))
            item(key = "more_inner") { EntryGroup(innerLife, currentLanguage, modeFallback) }
        }
        leftovers.forEachIndexed { i, section ->
            header("other_$i", section.title)
            item(key = "more_other_$i") { EntryGroup(section.entries, currentLanguage, modeFallback) }
        }

        header("settings", pick("Ajustes", "Settings", "Ajustes"))
        item(key = "more_settings") {
            val creditsEntry = MoreEntry(
                title = pick("Créditos de las imágenes", "Image credits", "Créditos das imagens"),
                iconSlot = AppIconSlot.MORE,
                tag = "image_credits_button",
                value = creditCount.takeIf { it > 0 }?.toString(),
                icon = Icons.Outlined.Collections,
                onOpen = { showCredits = true }
            )
            EntryGroup(listOf(creditsEntry), currentLanguage, modeFallback)
        }
        item(key = "more_footer") {
            Text(
                text = "Sancta Fides · ${pick("Versión", "Version", "Versão")} ${BuildConfig.VERSION_NAME}",
                style = styles.footnote,
                color = p.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = design.spacing.gutter, vertical = 20.dp)
                    .testTag("more_version")
            )
        }
    }

    if (showCredits) {
        ImageCreditsSheet(currentLanguage = currentLanguage, onDismiss = { showCredits = false })
    }
}

// ---------------------------------------------------------------- study cards

private data class StudyCardInfo(val art: String, val title: String, val meta: String)

private fun studyCardInfo(tag: String, language: AppLanguage): StudyCardInfo? {
    fun pick(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    return when (tag) {
        "nav_catechism" -> {
            val n = CatechismOutline.totalParagraphs
            // Spanish and Portuguese leave four-digit numbers unseparated.
            val count = if (language == AppLanguage.EN) NumberFormat.getIntegerInstance(Locale.US).format(n) else n.toString()
            StudyCardInfo(
                "heroes/lumen/catechism.jpg",
                pick("Catecismo", "Catechism", "Catecismo"),
                pick("El texto completo: $count puntos", "The full text: $count paragraphs", "O texto completo: $count parágrafos")
            )
        }
        "nav_study_doctrine" -> StudyCardInfo(
            "heroes/lumen/doctrine.jpg",
            pick("Doctrina", "Doctrine", "Doutrina"),
            pick("Mandamientos, virtudes y la Didaché", "Commandments, virtues and the Didache", "Mandamentos, virtudes e a Didaché")
        )
        "nav_study_mary_jesus" -> StudyCardInfo(
            "heroes/lumen/mary_jesus.jpg",
            pick("María y Jesús", "Mary & Jesus", "Maria e Jesus"),
            pick("Devociones a Nuestra Señora y a Cristo", "Devotions to Our Lady and to Christ", "Devoções a Nossa Senhora e a Cristo")
        )
        "nav_study_history" -> StudyCardInfo(
            "heroes/lumen/history.jpg",
            pick("Historia", "History", "História"),
            pick("Concilios, papas y los grandes cismas", "Councils, popes and the great schisms", "Concílios, papas e os grandes cismas")
        )
        "nav_questions" -> StudyCardInfo(
            "heroes/lumen/questions.jpg",
            pick("101 preguntas", "101 Questions", "101 perguntas"),
            pick("Sobre la fe católica, con sus fuentes", "On the Catholic faith, with sources", "Sobre a fé católica, com as fontes")
        )
        "nav_study_orders" -> StudyCardInfo(
            "heroes/lumen/orders.jpg",
            pick("Órdenes y oficios", "Orders & Offices", "Ordens e ofícios"),
            pick("Órdenes religiosas y oficios de la Iglesia", "Religious orders and Church offices", "Ordens religiosas e ofícios da Igreja")
        )
        else -> null
    }
}

/**
 * The reference's misty art card: the painting fills the trailing ~70%,
 * dissolving into opaque glass on the leading side where the title and its
 * promise sit in ink — legible on any painting, in light and dark.
 */
@Composable
private fun StudyCard(
    title: String,
    meta: String?,
    spokenTitle: String,
    assetPath: String?,
    onClick: () -> Unit,
    tag: String
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val shape = RoundedCornerShape(22.dp)
    val bitmap = rememberAssetBitmap(assetPath, 900)
    val focus = rememberHeroMeta(assetPath)
    // Opaque glass (the translucent glass composited over the canvas), so the
    // text never depends on what the painting does underneath.
    val mist = p.groupedSurface

    Box(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 118.dp)
            .lumenPress(onClick, 0.97f)
            .softShadow(shape, p.shadow, blur = if (design.isDark) 20.dp else 26.dp, offsetY = if (design.isDark) 4.dp else 8.dp)
            .clip(shape)
            .background(mist)
            .semantics { contentDescription = listOfNotNull(spokenTitle, meta).joinToString(". ") }
            .testTag(tag)
    ) {
        Box(Modifier.matchParentSize()) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = focus.alignment,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.86f) }),
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.70f)
                        .align(Alignment.CenterEnd)
                )
                // Night: settle the painting so it never glows on the dark canvas.
                if (design.isDark) {
                    Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.16f)))
                } else {
                    Box(Modifier.fillMaxSize().background(p.canvasTop.copy(alpha = 0.08f)))
                }
            }
            Box(
                Modifier.fillMaxSize().background(
                    Brush.horizontalGradient(
                        0f to mist,
                        0.42f to mist,
                        0.56f to mist.copy(alpha = 0.82f),
                        0.70f to mist.copy(alpha = 0.32f),
                        0.86f to mist.copy(alpha = 0f)
                    )
                )
            )
        }
        Column(
            Modifier
                .fillMaxWidth(0.58f)
                .align(Alignment.CenterStart)
                .padding(start = design.spacing.cardPadding, end = 4.dp, top = 18.dp, bottom = 18.dp)
        ) {
            Text(title, style = styles.title2, color = p.textPrimary, maxLines = 2)
            if (meta != null) {
                Spacer(Modifier.height(4.dp))
                Text(meta, style = styles.subhead, color = p.textSecondary, maxLines = 3)
            }
        }
        // The lit glass edge, drawn over the art.
        Box(
            Modifier
                .matchParentSize()
                .border(1.dp, Brush.verticalGradient(listOf(p.glassEdgeTop, p.glassEdgeBottom)), shape)
        )
    }
}

// ----------------------------------------------------------------- list rows

private data class RowInfo(val title: String, val subtitle: String?)

private fun rowInfo(tag: String, language: AppLanguage): RowInfo? {
    fun pick(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    return when (tag) {
        "nav_spiritual" -> RowInfo(
            pick("Vida espiritual", "Spiritual Life", "Vida espiritual"),
            pick("Diario, Vía Crucis y canto gregoriano", "Journal, Stations and Gregorian chant", "Diário, Via-Sacra e canto gregoriano")
        )
        "nav_habits" -> RowInfo(
            pick("Hábitos y virtudes", "Habits & Virtues", "Hábitos e virtudes"),
            pick("Tu regla de vida, día a día", "Your rule of life, day by day", "Sua regra de vida, dia a dia")
        )
        "nav_parishes" -> RowInfo(
            pick("Parroquias", "Parishes", "Paróquias"),
            pick("Misas y confesiones cerca de ti", "Masses and confession near you", "Missas e confissões perto de você")
        )
        "nav_pilgrimage" -> RowInfo(
            pick("Calendario y santuarios", "Calendar & Shrines", "Calendário e santuários"),
            pick("Días santos y peregrinaciones", "Feast days and pilgrimages", "Dias santos e peregrinações")
        )
        "nav_churches_news" -> RowInfo(
            pick("Noticias", "News", "Notícias"),
            pick("Lo último de Vatican News", "The latest from Vatican News", "O mais recente do Vatican News")
        )
        else -> null
    }
}

@Composable
private fun glyphFor(entry: MoreEntry): ImageVector = when (entry.tag) {
    "nav_spiritual" -> Icons.Outlined.FavoriteBorder
    // The same glyph as the Habits tab, so the two read as one place.
    "nav_habits" -> AppIcons.pair(AppIconSlot.HABITS).first
    "nav_parishes" -> Icons.Outlined.Church
    "nav_pilgrimage" -> Icons.Outlined.Place
    "nav_churches_news" -> Icons.Outlined.Newspaper
    else -> entry.icon ?: AppIcons.pair(entry.iconSlot).first
}

/** A glass group of [entries]: outline glyph, title, footnote, value, chevron. */
@Composable
private fun EntryGroup(entries: List<MoreEntry>, language: AppLanguage, modeFallback: String) {
    val design = LocalAppDesign.current
    val shape = design.shapes.listGroup
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .lumenGlass(shape)
            .clip(shape)
    ) {
        entries.forEachIndexed { i, entry ->
            val info = rowInfo(entry.tag, language)
            EntryRow(
                icon = glyphFor(entry),
                title = info?.title ?: entry.title,
                subtitle = info?.subtitle ?: entry.subtitle,
                value = entry.value ?: if (entry.tag == "app_bar_theme_button") modeFallback else null,
                onClick = entry.onOpen,
                tag = entry.tag,
                showDivider = i < entries.lastIndex
            )
        }
    }
}

private val RowInset: Dp = 18.dp
private val GlyphSize: Dp = 22.dp
private val GlyphGap: Dp = 16.dp

@Composable
private fun EntryRow(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    value: String?,
    onClick: () -> Unit,
    tag: String,
    showDivider: Boolean
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val fill by animateColorAsState(
        if (pressed) p.textPrimary.copy(alpha = 0.06f) else Color.Transparent,
        if (pressed) tween(0) else tween(design.motion.mediumMillis),
        label = "exploreRowFill"
    )
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = if (subtitle != null) 64.dp else design.spacing.rowMinHeight)
                .background(fill)
                .clickable(source, indication = null, role = Role.Button, onClick = onClick)
                .testTag(tag)
                .padding(horizontal = RowInset, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = p.textPrimary, modifier = Modifier.size(GlyphSize))
            Spacer(Modifier.width(GlyphGap))
            Column(Modifier.weight(1f)) {
                Text(title, style = styles.body, color = p.textPrimary)
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, style = styles.footnote, color = p.textSecondary)
                }
            }
            if (!value.isNullOrBlank()) {
                Spacer(Modifier.width(12.dp))
                Text(value, style = styles.body, color = p.textSecondary, maxLines = 1)
            }
            Spacer(Modifier.width(10.dp))
            AppChevron()
        }
        if (showDivider) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = RowInset + GlyphSize + GlyphGap)
                    .height(0.5.dp)
                    .background(p.separator)
            )
        }
    }
}
