package com.example.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Directions
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.data.models.AppLanguage
import com.example.data.models.NearbyParish
import com.example.data.news.NewsResult
import com.example.data.news.VaticanNewsItem
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ParishScheduleText
import com.example.ui.components.MenuPickerRow
import com.example.ui.components.TextFormat
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppFilterChips
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppImageCard
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppSearchBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSegmentedTabs
import com.example.ui.design.AppSeasonDot
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

// -----------------------------------------------------------------------------
// Churches & News: the Vatican feed with photos, and the parish directory.
// -----------------------------------------------------------------------------

/** Sentinel for "do not narrow at this level". */
private const val ANY_PLACE = "__any__"

@Composable
internal fun ChurchesNewsRoot(
    repository: CatholicDataRepository,
    language: AppLanguage,
    spec: RootSpec,
    newsLoader: suspend (AppLanguage) -> NewsResult,
    nav: DoctrineNav
) {
    val design = LocalAppDesign.current
    val state = rememberLazyListState()
    val context = LocalContext.current
    var retry by rememberSaveable { mutableStateOf(0) }

    val news by produceState<NewsResult?>(initialValue = null, language, retry) {
        value = null
        value = newsLoader(language)
    }

    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "hero", contentType = "hero") { RootHeader(spec) }
        item(key = "news_top") { Spacer(Modifier.height(16.dp)) }
        newsItems(news, language, onRetry = { retry++ }, onOpenSite = { context.openUri("https://www.vaticannews.va") }) { link ->
            context.openUri(link)
        }
    }
}

// -------------------------------------------------------------------- news

private fun androidx.compose.foundation.lazy.LazyListScope.newsItems(
    news: NewsResult?,
    language: AppLanguage,
    onRetry: () -> Unit,
    onOpenSite: () -> Unit,
    onOpenLink: (String) -> Unit
) {
    when (news) {
        null -> {
            item(key = "news_loading") {
                Column(Modifier.testTag("news_loading")) {
                    SkeletonBlock(220.dp)
                    Spacer(Modifier.height(16.dp))
                    SkeletonBlock(88.dp)
                    Spacer(Modifier.height(8.dp))
                    SkeletonBlock(88.dp)
                    Spacer(Modifier.height(16.dp))
                    FootnoteText(
                        language.tr(
                            "Cargando noticias del Vaticano…",
                            "Loading news from the Vatican…",
                            "A carregar notícias do Vaticano…"
                        )
                    )
                }
            }
        }

        is NewsResult.Failure -> item(key = "news_failed") {
            Column(Modifier.fillMaxWidth().testTag("news_failed"), horizontalAlignment = Alignment.CenterHorizontally) {
                AppEmptyState(
                    title = language.tr("Noticias no disponibles", "News unavailable", "Notícias indisponíveis"),
                    body = language.tr(
                        "Comprueba tu conexión y vuelve a intentarlo.",
                        "Check your connection and try again.",
                        "Verifique a ligação e tente de novo."
                    ),
                    icon = Icons.Outlined.Newspaper
                )
                AppSecondaryCapsule(
                    label = language.tr("Reintentar", "Try again", "Tentar de novo"),
                    icon = Icons.Outlined.Refresh,
                    onClick = onRetry,
                    tag = "news_retry"
                )
                Spacer(Modifier.height(10.dp))
                AppSecondaryCapsule(
                    label = language.tr("Abrir Vatican News", "Open Vatican News", "Abrir o Vatican News"),
                    icon = Icons.Outlined.OpenInNew,
                    onClick = onOpenSite,
                    tag = "news_open_site"
                )
            }
        }

        is NewsResult.Success -> {
            val items = news.items
            if (items.isNotEmpty()) {
                item(key = "news_featured") {
                    NewsFeature(items.first(), language) { onOpenLink(items.first().link) }
                    Spacer(Modifier.height(20.dp))
                }
                val rest = items.drop(1)
                itemsIndexed(rest, key = { _, a -> "n_" + a.link }, contentType = { _, _ -> "news" }) { i, article ->
                    NewsRow(article, language, groupPosition(i, rest.size)) { onOpenLink(article.link) }
                }
            }
        }
    }
}

@Composable
private fun SkeletonBlock(height: androidx.compose.ui.unit.Dp) {
    val design = LocalAppDesign.current
    val shape = design.shapes.card
    Box(
        Modifier
            .padding(horizontal = design.spacing.gutter)
            .fillMaxWidth()
            .height(height)
            .alpha(0.55f)
            .lumenGlass(shape, lifted = false)
    )
}

private fun metaLine(article: VaticanNewsItem, language: AppLanguage): String {
    val date = newsDate(article.publishedAt, language)
    return if (date.isNotEmpty()) "Vatican News · $date" else "Vatican News"
}

@Composable
private fun NewsFeature(article: VaticanNewsItem, language: AppLanguage, onClick: () -> Unit) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val meta = metaLine(article, language)
    if (article.imageUrl != null) {
        AppImageCard(
            title = article.title,
            onClick = onClick,
            modifier = Modifier
                .padding(horizontal = design.spacing.gutter)
                .fillMaxWidth()
                .semantics { contentDescription = article.title }
                .testTag("news_featured"),
            imageUrl = article.imageUrl,
            meta = meta,
            aspectRatio = 16f / 9f
        )
    } else {
        GlassCard(
            onClick = onClick,
            modifier = Modifier.semantics { contentDescription = article.title }.testTag("news_featured")
        ) {
            Text(meta, style = styles.eyebrow, color = p.textSecondary)
            Spacer(Modifier.height(8.dp))
            Text(article.title, style = styles.title2, color = p.textPrimary, modifier = Modifier.semantics { heading() })
            if (article.summary.isNotBlank()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    article.summary,
                    style = styles.callout,
                    color = p.textSecondary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun NewsRow(
    article: VaticanNewsItem,
    language: AppLanguage,
    position: com.example.ui.design.GroupPosition,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val thumb = RoundedCornerShape(16.dp)
    Column(
        Modifier
            .groupedItem(position)
            .semantics { contentDescription = article.title }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.99f)
                .heightIn(min = 88.dp)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    article.title,
                    style = styles.headline,
                    color = p.textPrimary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.semantics { heading() }
                )
                Spacer(Modifier.height(4.dp))
                Text(metaLine(article, language), style = styles.footnote, color = p.textSecondary, maxLines = 1)
            }
            if (article.imageUrl != null) {
                Spacer(Modifier.width(14.dp))
                Box(Modifier.size(72.dp).clip(thumb).background(p.glassStrong)) {
                    AsyncImage(
                        model = article.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    if (design.isDark) Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.06f)))
                }
            }
        }
        if (position == com.example.ui.design.GroupPosition.First || position == com.example.ui.design.GroupPosition.Middle) {
            Box(Modifier.fillMaxWidth().padding(start = 18.dp).height(0.5.dp).background(p.separator))
        }
    }
}

// ------------------------------------------------------------ parish detail

@Composable
internal fun ParishDetail(
    id: String,
    repository: CatholicDataRepository,
    language: AppLanguage
) {
    val parish: NearbyParish = repository.nearbyParishes.firstOrNull { it.id == id } ?: return
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppInlineTitle(parish.name)
    fun hours(text: String) = TextFormat.times(text, language)

    ReaderPage(
        title = parish.name,
        eyebrow = "${parish.city} · ${ParishScheduleText.country(parish.country, language)}",
        pageTag = "parish_detail_${parish.id}"
    ) {
        if (parish.isPerpetualAdoration) item(key = "adoration") {
            Row(
                Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .lumenGlass(RoundedCornerShape(percent = 50), lifted = false)
                    .padding(horizontal = 14.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppSeasonDot(p.accent, size = 8.dp)
                Spacer(Modifier.width(8.dp))
                Text(
                    language.tr("Adoración perpetua", "Perpetual adoration", "Adoração perpétua"),
                    style = styles.footnote.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
                    color = p.textPrimary
                )
            }
            Spacer(Modifier.height(16.dp))
        }
        item(key = "schedule") {
            FactsGroup(
                listOf(
                    language.tr("Misas del domingo", "Sunday Mass", "Missas de domingo") to
                        hours(ParishScheduleText.masses(parish.sundayMasses, language)),
                    language.tr("Misas entre semana", "Weekday Mass", "Missas durante a semana") to
                        if (parish.weekdayMasses.isNotBlank()) hours(ParishScheduleText.masses(parish.weekdayMasses, language)) else "",
                    language.tr("Confesiones", "Confessions", "Confissões") to
                        hours(ParishScheduleText.note(parish.confessionTimes, language)),
                    language.tr("Adoración", "Adoration", "Adoração") to
                        hours(ParishScheduleText.note(parish.adorationTimes, language)),
                    language.tr("Diócesis", "Diocese", "Diocese") to parish.diocese
                )
            )
            BlockGap(24.dp)
        }
        item(key = "actions") {
            AppListGroup {
                LeadRow(
                    title = language.tr("Cómo llegar", "Directions", "Como chegar"),
                    subtitle = parish.address.ifBlank { null },
                    leading = { Icon(Icons.Outlined.Directions, null, tint = p.textPrimary, modifier = Modifier.size(22.dp)) },
                    leadingWidth = 24.dp,
                    onClick = {
                        val target = Uri.encode("${parish.name}, ${parish.address}")
                        if (!context.openUri("geo:0,0?q=$target")) {
                            Toast.makeText(context, parish.address, Toast.LENGTH_LONG).show()
                        }
                    },
                    tag = "map_parish_${parish.id}"
                )
                if (parish.contactPhone.isNotBlank()) {
                    LeadRow(
                        title = language.tr("Llamar", "Call", "Ligar"),
                        subtitle = parish.contactPhone,
                        leading = { Icon(Icons.Outlined.Call, null, tint = p.textPrimary, modifier = Modifier.size(22.dp)) },
                        leadingWidth = 24.dp,
                        onClick = {
                            val phone = parish.contactPhone.replace(" ", "")
                            val dialled = runCatching {
                                context.startActivity(
                                    android.content.Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                                )
                            }.isSuccess
                            if (!dialled) Toast.makeText(context, parish.contactPhone, Toast.LENGTH_SHORT).show()
                        },
                        tag = "call_parish_${parish.id}"
                    )
                }
            }
        }
    }
}
