package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.news.NewsResult
import com.example.data.news.VaticanNewsFetcher
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppInlineTitle

private val defaultNewsLoader: suspend (AppLanguage) -> NewsResult =
    { VaticanNewsFetcher().latest(it, limit = 25) }

/** What a destination's list page is called and pictured as. */
internal data class RootSpec(val asset: String, val title: String, val eyebrow: String)

internal fun rootSpec(topTab: Int, language: AppLanguage, repository: CatholicDataRepository): RootSpec = when (topTab) {
    1 -> RootSpec(
        "heroes/lumen/mary_jesus.jpg",
        language.tr("María y Jesús", "Mary & Jesus", "Maria e Jesus"),
        language.tr(
            "${repository.marianApparitions.size} advocaciones · ${repository.christManifestations.size} devociones",
            "${repository.marianApparitions.size} titles of Our Lady · ${repository.christManifestations.size} devotions",
            "${repository.marianApparitions.size} advocações · ${repository.christManifestations.size} devoções"
        )
    )
    2 -> RootSpec(
        "heroes/lumen/history.jpg",
        language.tr("Historia", "History", "História"),
        language.tr("Veinte siglos de la Iglesia", "Twenty centuries of the Church", "Vinte séculos da Igreja")
    )
    3 -> RootSpec(
        "heroes/lumen/questions.jpg",
        language.tr("101 preguntas sobre la fe católica", "101 Questions on the Catholic Faith", "101 Perguntas sobre a fé católica"),
        language.tr("11 temas · respuestas con fuentes", "11 themes · sourced answers", "11 temas · respostas com fontes")
    )
    4 -> RootSpec(
        "heroes/lumen/orders.jpg",
        language.tr("Órdenes y oficios", "Orders & Offices", "Ordens e ofícios"),
        language.tr(
            "${repository.religiousOrders.size} órdenes · ${repository.churchOffices.size} oficios",
            "${repository.religiousOrders.size} orders · ${repository.churchOffices.size} offices",
            "${repository.religiousOrders.size} ordens · ${repository.churchOffices.size} ofícios"
        )
    )
    5 -> RootSpec(
        "heroes/lumen/news.jpg",
        language.tr("Iglesias y Noticias", "Churches & News", "Igrejas e Notícias"),
        language.tr("Vatican News · iglesias", "Vatican News · churches", "Vatican News · igrejas")
    )
    else -> RootSpec(
        "heroes/lumen/doctrine.jpg",
        language.tr("Doctrina", "Doctrine", "Doutrina"),
        language.tr("La enseñanza moral de la Iglesia", "The Church's moral teaching", "O ensino moral da Igreja")
    )
}

/** The list page's art header, with the destination's own title. */
@Composable
internal fun RootHeader(spec: RootSpec) {
    Box(Modifier.testTag("doctrine_hero_photo")) {
        AppDetailHeader(
            title = spec.title,
            assetPath = spec.asset,
            eyebrow = spec.eyebrow,
            imageHeight = 280.dp,
            titleTag = "doctrine_title"
        )
    }
}

/**
 * Doctrine, Mary & Jesus, History, 101 Questions, Orders and the Churches &
 * News feed. One screen, seven destinations: [initialTopTab] says which list
 * page this one opens on (0 doctrine, 1 Mary & Jesus, 2 history, 3 questions,
 * 4 orders, 5 churches & news). Entries (a commandment, a question, an order…)
 * open as reading pages on an in-screen stack that survives rotation; the
 * single glass back button and system back pop it, and the destination's own
 * back leaves the area.
 */
@Composable
fun DoctrineAndHistoryScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    initialTopTab: Int = 0,
    @Suppress("UNUSED_PARAMETER") showTabs: Boolean = false,
    modifier: Modifier = Modifier,
    onOpenBible: (bookId: String, chapter: Int, verseStart: Int?) -> Unit = { _, _, _ -> },
    onOpenCatechism: (paragraph: Int) -> Unit = {},
    onOpenPrayer: (prayerId: String) -> Unit = {},
    /**
     * 0-based index into the 101 questions (the same index Home's "Pregunta
     * del día" uses). When set, the screen opens on that answer.
     */
    initialQuestionIndex: Int? = null,
    /** On Churches & News: 0 opens the news, 1 the church directory. */
    initialChurchesSection: Int = 0,
    /** Opens straight on an entry such as "qa:12" (deep links, previews, tests). */
    initialEntry: String? = null,
    newsLoader: suspend (AppLanguage) -> NewsResult = defaultNewsLoader
) {
    val insets = AppEdgeToEdge()
    val context = LocalContext.current

    // One speech engine for the whole area, created when a reader first needs it.
    val voice = remember { ReaderVoice(context) }
    voice.language = currentLanguage
    DisposableEffect(voice) { onDispose { voice.release() } }

    val deepIndex = initialQuestionIndex?.takeIf { it in repository.catholicQuestions.indices }
    var stack by rememberSaveable { mutableStateOf(initialEntry ?: deepIndex?.let { "qa:$it" } ?: "") }
    var handledDeepLink by rememberSaveable { mutableStateOf<Int?>(deepIndex) }
    LaunchedEffect(deepIndex) {
        if (deepIndex != null && deepIndex != handledDeepLink) {
            stack = "qa:$deepIndex"
            handledDeepLink = deepIndex
        }
    }

    val entries = stack.split('|').filter { it.isNotEmpty() }
    val top = entries.lastOrNull()
    AppBackHandler(enabled = entries.isNotEmpty()) {
        stack = stack.split('|').filter { it.isNotEmpty() }.dropLast(1).joinToString("|")
    }
    LaunchedEffect(top) { voice.stop() }

    val nav = DoctrineNav(
        open = { stack = if (stack.isEmpty()) it else "$stack|$it" },
        replace = { stack = (stack.split('|').filter { e -> e.isNotEmpty() }.dropLast(1) + it).joinToString("|") },
        openBible = onOpenBible,
        openCatechism = onOpenCatechism,
        openPrayer = onOpenPrayer,
        voice = voice
    )

    val saveable = rememberSaveableStateHolder()
    val spec = rootSpec(initialTopTab, currentLanguage, repository)

    CompositionLocalProvider(LocalBottomPad provides insets.calculateBottomPadding() + 24.dp) {
        Box(modifier.fillMaxSize().testTag("doctrine_and_history_screen")) {
            AnimatedContent(
                targetState = stack,
                transitionSpec = {
                    val deeper = targetState.count { it == '|' } + (if (targetState.isEmpty()) 0 else 1) >=
                        initialState.count { it == '|' } + (if (initialState.isEmpty()) 0 else 1)
                    val dx = if (deeper) 1 else -1
                    (fadeIn(tween(250)) + slideInHorizontally(tween(250)) { dx * it / 8 }) togetherWith
                        fadeOut(tween(150))
                },
                label = "doctrinePage"
            ) { s ->
                saveable.SaveableStateProvider(key = s) {
                    val entry = s.substringAfterLast('|').takeIf { it.isNotEmpty() }
                    if (entry == null) {
                        AppInlineTitle(spec.title)
                        when (initialTopTab) {
                            0 -> MoralDoctrineRoot(repository, currentLanguage, nav, spec)
                            1 -> DevotionsExplorerScreen(repository, currentLanguage, nav, spec)
                            2 -> HistoryRoot(repository, currentLanguage, nav, spec)
                            3 -> QuestionsRoot(repository, currentLanguage, nav, spec)
                            4 -> OrdersRoot(repository, currentLanguage, nav, spec)
                            else -> ChurchesNewsRoot(
                                repository, currentLanguage, spec,
                                initialSection = initialChurchesSection,
                                newsLoader = newsLoader,
                                nav = nav
                            )
                        }
                    } else {
                        EntryPage(entry, repository, currentLanguage, nav) {
                            stack = stack.split('|').filter { e -> e.isNotEmpty() }.dropLast(1).joinToString("|")
                        }
                    }
                }
            }
        }
    }
}

/** Routes "kind:argument" to the page that reads it. */
@Composable
private fun EntryPage(
    entry: String,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav,
    pop: () -> Unit
) {
    val kind = entry.substringBefore(':')
    val arg = entry.substringAfter(':', "")
    val index = arg.toIntOrNull() ?: 0
    when (kind) {
        "cmd", "prc", "vice", "grav", "didache_toc", "didache" -> MoralEntry(kind, index, repository, language, nav)
        "epoch", "schism" -> HistoryEntry(kind, index, repository, language, nav)
        "theme", "qa", "sources" -> QuestionsEntry(kind, index, repository, language, nav)
        "order", "office" -> OrdersEntry(kind, index, repository, language, nav)
        "devotion" -> DevotionDetail(arg, repository, language, nav)
        "parish" -> ParishDetail(arg, repository, language)
        else -> LaunchedEffect(entry) { pop() }
    }
}
