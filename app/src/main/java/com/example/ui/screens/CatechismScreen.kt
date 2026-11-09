package com.example.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.FormatListBulleted
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.prefs.UserPreferences
import com.example.data.repository.CatechismData
import com.example.data.repository.CatechismOutlineSection
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.CompendiumQA
import com.example.ui.components.InAppBrowserDialog
import com.example.ui.design.AppActionBar
import com.example.ui.design.AppActionBarItem
import com.example.ui.design.AppBackHandler
import com.example.ui.design.AppChevron
import com.example.ui.design.AppDetailHeader
import com.example.ui.design.AppEdgeToEdge
import com.example.ui.design.AppEmptyState
import com.example.ui.design.AppFilterChips
import com.example.ui.design.AppGlassSurface
import com.example.ui.design.AppHideTabBar
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppReadingSettingsSheet
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.AppSeasonDot
import com.example.ui.design.AppSheet
import com.example.ui.design.GroupPosition
import com.example.ui.design.LocalPreferencesStore
import com.example.ui.design.ReadingSheetLabels
import com.example.ui.design.appDockInset
import com.example.ui.design.groupedItem
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import java.text.Normalizer

/**
 * The Catechism of the Catholic Church, set as a book.
 *
 * It opens on the book itself: a cover (Raphael's *Disputation*), one search
 * field that takes a topic or a paragraph number, "Continue reading", and the
 * contents. Every paragraph is two taps away, or one search. The reader is one
 * calm column with the § numbers set inline as the printed text sets them,
 * citations in a quieter ink, and nothing on the page that is not the text.
 *
 * Pages live on a small stack kept inside this screen (saved across rotation);
 * the scaffold's glass back button and the system back both pop it, and at the
 * root they leave the screen, returning to whoever opened the Catechism.
 */
@Composable
fun CatechismScreen(
    repository: CatholicDataRepository,
    currentLanguage: AppLanguage,
    /** Deep link: a CCC paragraph (§) to open on entry, or null for the book's cover. */
    initialParagraph: Int? = null,
    modifier: Modifier = Modifier
) {
    val insets = AppEdgeToEdge()
    val context = LocalContext.current
    val lang = currentLanguage

    var stack by rememberSaveable(initialParagraph) {
        mutableStateOf(initialStack(initialParagraph, repository))
    }
    val pages = remember(stack) { decodeStack(stack) }
    val top: CccPage = pages.lastOrNull() ?: CccPage.Index

    fun push(page: CccPage) { stack = encodeStack(pages + page) }
    fun pop() { stack = encodeStack(pages.dropLast(1)) }
    fun replaceTop(page: CccPage) { stack = encodeStack(pages.dropLast(1) + page) }
    fun backToIndex() { stack = "" }
    fun openReader(section: CatechismOutlineSection, focus: Int?) =
        push(CccPage.Reader(section.firstNumber, focus ?: -1))

    // The page being left keeps its state: the contents list, the queries and
    // the Compendium filters live here, above the page switch.
    val indexState = rememberLazyListState()
    var query by rememberSaveable { mutableStateOf("") }
    var compendiumQuery by rememberSaveable { mutableStateOf("") }
    var compendiumPart by rememberSaveable { mutableIntStateOf(0) }
    var lastRead by remember { mutableStateOf(readLastParagraph(context)) }
    LaunchedEffect(stack) { lastRead = readLastParagraph(context) }
    var webUrl by remember { mutableStateOf<String?>(null) }

    AppBackHandler(enabled = pages.isNotEmpty()) { pop() }

    // The scaffold's scroll-edge bar follows accumulated scroll; reset it to
    // the new page's own position whenever a page is pushed or popped.
    val dispatcher = remember { NestedScrollDispatcher() }
    val noScroll = remember { object : NestedScrollConnection {} }
    LaunchedEffect(stack) {
        val scrolledRoot = top == CccPage.Index &&
            (indexState.firstVisibleItemIndex > 1 || indexState.firstVisibleItemScrollOffset > 600)
        dispatcher.dispatchPostScroll(
            consumed = Offset(0f, if (scrolledRoot) -1_000_000f else 1_000_000f),
            available = Offset.Zero,
            source = NestedScrollSource.UserInput
        )
    }

    Box(
        modifier
            .fillMaxSize()
            .nestedScroll(noScroll, dispatcher)
            .testTag("catechism_screen")
    ) {
        AnimatedContent(
            targetState = top,
            transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(180)) },
            label = "catechismPage"
        ) { page ->
            when (page) {
                CccPage.Index -> CccIndexPage(
                    repository = repository,
                    lang = lang,
                    insets = insets,
                    listState = indexState,
                    query = query,
                    onQueryChange = { query = it },
                    lastRead = lastRead,
                    onOpenReader = { section, focus -> openReader(section, focus) },
                    onOpenPart = { push(CccPage.Part(it)) },
                    onOpenCompendium = { push(CccPage.Compendium) },
                    onOpenFormulas = { push(CccPage.Formulas) },
                    onOpenUrl = { webUrl = it }
                )

                is CccPage.Part -> CccPartPage(
                    repository = repository,
                    lang = lang,
                    insets = insets,
                    part = page.part,
                    lastRead = lastRead,
                    onOpenReader = { section, focus -> openReader(section, focus) }
                )

                is CccPage.Reader -> {
                    val section = repository.catechismOutline.firstOrNull { it.firstNumber == page.section }
                        ?: repository.catechismOutline.first()
                    CccReaderPage(
                        repository = repository,
                        lang = lang,
                        insets = insets,
                        section = section,
                        focus = page.focus.takeIf { it > 0 },
                        onOpenSection = { next, focus ->
                            replaceTop(CccPage.Reader(next.firstNumber, focus ?: -1))
                        },
                        onOpenPart = { part -> replaceTop(CccPage.Part(part)) },
                        onIndex = { backToIndex() }
                    )
                }

                CccPage.Compendium -> CccCompendiumPage(
                    repository = repository,
                    lang = lang,
                    insets = insets,
                    query = compendiumQuery,
                    onQueryChange = { compendiumQuery = it },
                    partFilter = compendiumPart,
                    onPartFilter = { compendiumPart = it },
                    onOpenAnswer = { push(CccPage.Answer(it)) }
                )

                is CccPage.Answer -> CccAnswerPage(
                    repository = repository,
                    lang = lang,
                    insets = insets,
                    first = page.first,
                    onOpenAnswer = { replaceTop(CccPage.Answer(it)) },
                    onOpenParagraph = { n ->
                        repository.catechismSectionFor(n)?.let { openReader(it, n) }
                    }
                )

                CccPage.Formulas -> CccFormulasPage(
                    repository = repository,
                    lang = lang,
                    insets = insets
                )
            }
        }
    }

    webUrl?.let { url ->
        InAppBrowserDialog(url = url, onDismiss = { webUrl = null }, currentLanguage = lang)
    }
}

// ----------------------------------------------------------------- pages

private sealed interface CccPage {
    data object Index : CccPage
    data class Part(val part: Int) : CccPage
    data class Reader(val section: Int, val focus: Int) : CccPage
    data object Compendium : CccPage
    data class Answer(val first: Int) : CccPage
    data object Formulas : CccPage
}

private fun encodePage(page: CccPage): String = when (page) {
    CccPage.Index -> "i"
    is CccPage.Part -> "p:${page.part}"
    is CccPage.Reader -> "r:${page.section}:${page.focus}"
    CccPage.Compendium -> "c"
    is CccPage.Answer -> "a:${page.first}"
    CccPage.Formulas -> "f"
}

private fun decodePage(token: String): CccPage? {
    val parts = token.split(':')
    return when (parts[0]) {
        "p" -> parts.getOrNull(1)?.toIntOrNull()?.let { CccPage.Part(it) }
        "r" -> {
            val s = parts.getOrNull(1)?.toIntOrNull() ?: return null
            CccPage.Reader(s, parts.getOrNull(2)?.toIntOrNull() ?: -1)
        }
        "c" -> CccPage.Compendium
        "a" -> parts.getOrNull(1)?.toIntOrNull()?.let { CccPage.Answer(it) }
        "f" -> CccPage.Formulas
        else -> null
    }
}

private fun encodeStack(pages: List<CccPage>): String = pages.joinToString("|") { encodePage(it) }

private fun decodeStack(stack: String): List<CccPage> =
    stack.split('|').filter { it.isNotEmpty() }.mapNotNull { decodePage(it) }

private fun initialStack(initialParagraph: Int?, repository: CatholicDataRepository): String {
    val n = initialParagraph?.takeIf { it in 1..2865 } ?: return ""
    val section = repository.catechismSectionFor(n) ?: return ""
    return encodePage(CccPage.Reader(section.firstNumber, n))
}

// ------------------------------------------------------- book structure

/** One division of the real CCC (the outline sections nest inside these). */
internal data class CccBookSection(val part: Int, val first: Int, val last: Int, val title: LocalizedText)

/**
 * The Catechism's own structure, which the data's outline lacks: Prologue and
 * the four parts, each part in two sections. Titles are in sentence case.
 */
internal object CccStructure {
    val sections: List<CccBookSection> = listOf(
        CccBookSection(0, 1, 25, LocalizedText("Prólogo", "Prologue", "Prólogo")),
        CccBookSection(
            1, 26, 184,
            LocalizedText("«Creo» — «Creemos»", "“I believe” — “We believe”", "«Eu creio» — «Nós cremos»")
        ),
        CccBookSection(
            1, 185, 1065,
            LocalizedText(
                "La profesión de la fe cristiana",
                "The profession of the Christian faith",
                "A profissão da fé cristã"
            )
        ),
        CccBookSection(
            2, 1066, 1209,
            LocalizedText("La economía sacramental", "The sacramental economy", "A economia sacramental")
        ),
        CccBookSection(
            2, 1210, 1690,
            LocalizedText(
                "Los siete sacramentos de la Iglesia",
                "The seven sacraments of the Church",
                "Os sete sacramentos da Igreja"
            )
        ),
        CccBookSection(
            3, 1691, 2051,
            LocalizedText(
                "La vocación del hombre: la vida en el Espíritu",
                "Man’s vocation: life in the Spirit",
                "A vocação do homem: a vida no Espírito"
            )
        ),
        CccBookSection(
            3, 2052, 2557,
            LocalizedText("Los diez mandamientos", "The Ten Commandments", "Os dez mandamentos")
        ),
        CccBookSection(
            4, 2558, 2758,
            LocalizedText(
                "La oración en la vida cristiana",
                "Prayer in the Christian life",
                "A oração na vida cristã"
            )
        ),
        CccBookSection(
            4, 2759, 2865,
            LocalizedText(
                "La oración del Señor: «Padre nuestro»",
                "The Lord’s Prayer: “Our Father”",
                "A oração do Senhor: «Pai nosso»"
            )
        )
    )

    /** Part 0 is the Prologue; 1..4 are the four parts. */
    val partTitles: List<LocalizedText> = listOf(
        LocalizedText("Prólogo", "Prologue", "Prólogo"),
        LocalizedText("La profesión de la fe", "The profession of faith", "A profissão da fé"),
        LocalizedText(
            "La celebración del misterio cristiano",
            "The celebration of the Christian mystery",
            "A celebração do mistério cristão"
        ),
        LocalizedText("La vida en Cristo", "Life in Christ", "A vida em Cristo"),
        LocalizedText("La oración cristiana", "Christian prayer", "A oração cristã")
    )

    private val ordinals = listOf(
        LocalizedText("Prólogo", "Prologue", "Prólogo"),
        LocalizedText("Primera parte", "Part One", "Primeira parte"),
        LocalizedText("Segunda parte", "Part Two", "Segunda parte"),
        LocalizedText("Tercera parte", "Part Three", "Terceira parte"),
        LocalizedText("Cuarta parte", "Part Four", "Quarta parte")
    )

    private val sectionOrdinals = listOf(
        LocalizedText("Sección primera", "Section One", "Primeira secção"),
        LocalizedText("Sección segunda", "Section Two", "Segunda secção")
    )

    fun ordinal(part: Int, lang: AppLanguage): String = ordinals[part.coerceIn(0, 4)].get(lang)
    fun sectionOrdinal(index: Int, lang: AppLanguage): String = sectionOrdinals[index.coerceIn(0, 1)].get(lang)

    fun sectionFor(number: Int): CccBookSection? = sections.firstOrNull { number in it.first..it.last }

    fun sectionsOfPart(part: Int): List<CccBookSection> = sections.filter { it.part == part }

    fun partRange(part: Int): IntRange {
        val list = sectionsOfPart(part)
        return list.first().first..list.last().last
    }
}

// ------------------------------------------------------ text for display

private val HARD_WRAP = Regex("""[\r\n]+""")
private val MANY_SPACES = Regex("""[ \t ]{2,}""")
private val STRAY_OPEN = Regex("""\(\s+""")
private val STRAY_CLOSE = Regex("""\s+\)""")
private val SPACE_BEFORE_PUNCT = Regex("""\s+([,;])""")
private val SPACE_AFTER_CLOSER = Regex("""(?<=[)»”"])\s+([.,;:])""")
private val PT_NOTE_MARKER = Regex("""\s?\(\d{1,3}\)""")
private val FOOTNOTE_DIGITS = Regex("""((?:["”’)][.,;:!?]?|[.!?]["”’)]?))\s+(\d{1,3})(?=\s+[A-Za-z]|\s*$)""")
private val ABBREVIATION_BEFORE = Regex("""(?i)(?:^|[\s(])(?:cf|st|sts|no|nos|n|nn|art|arts|vol|p|pp|ch|chap|sess|sec|ibid)\.$""")
private val NOTES_START = Regex("""\s1\.\s+(?=\p{Lu})""")
private val NOTE_NUMBER = Regex("""\s\d{1,3}\.\s""")

/**
 * The text of one paragraph as it is set on the page.
 *
 * The bundled text is hard-wrapped at ~72 characters (so a line break is never
 * a real break), carries the printed footnote numbers as dangling digits (EN)
 * or "(n)" markers (PT), stray spaces inside parentheses, and for the last
 * paragraph of a section, the whole footnote apparatus of the section appended
 * (PT). All of that is cleaned here, at display time; the data is untouched.
 */
internal fun cccDisplayText(raw: String, language: AppLanguage): String {
    var t = stripAppendedNotes(HARD_WRAP.replace(raw, " ")).trim()
    t = MANY_SPACES.replace(t, " ")
    if (language == AppLanguage.EN) t = removeFootnoteDigits(t)
    if (language == AppLanguage.PT) t = PT_NOTE_MARKER.replace(t, "")
    t = STRAY_OPEN.replace(t, "(")
    t = STRAY_CLOSE.replace(t, ")")
    t = SPACE_AFTER_CLOSER.replace(t) { it.groupValues[1] }
    t = SPACE_BEFORE_PUNCT.replace(t) { it.groupValues[1] }
    return MANY_SPACES.replace(t, " ").trim()
}

private fun removeFootnoteDigits(text: String): String =
    FOOTNOTE_DIGITS.replace(text) { m ->
        val closer = m.groupValues[1]
        val before = text.substring(0, m.range.first + closer.length)
        if (closer.startsWith(".") && ABBREVIATION_BEFORE.containsMatchIn(before)) m.value else closer
    }

/** A section's last paragraph may carry its notes ("1. Cf. …  2. …"); cut them. */
private fun stripAppendedNotes(text: String): String {
    val start = NOTES_START.find(text) ?: return text
    val at = start.range.first
    val tail = text.substring(at)
    return if (at > 80 && tail.length > 300 && NOTE_NUMBER.findAll(tail).count() >= 6) {
        text.substring(0, at).trimEnd()
    } else {
        text
    }
}

private val CITATION = Regex("""\(([^()]{1,100})\)""")

/**
 * "1324 text…": the § number as a quiet inline lead-in, then the paragraph with
 * its parenthetical citations (those that contain a digit) in the quieter ink.
 */
internal fun buildParagraphText(
    number: Int,
    text: String,
    numberStyle: SpanStyle,
    citationStyle: SpanStyle
): AnnotatedString = buildAnnotatedString {
    withStyle(numberStyle) { append(number.toString()) }
    append(" ")
    var last = 0
    for (m in CITATION.findAll(text)) {
        if (m.groupValues[1].none(Char::isDigit)) continue
        append(text.substring(last, m.range.first))
        withStyle(citationStyle) { append(m.value) }
        last = m.range.last + 1
    }
    append(text.substring(last))
}

/** Lower-case, accent-free form used to match query words against text. */
internal fun cccFold(text: String): String =
    COMBINING_MARKS.replace(Normalizer.normalize(text.lowercase(), Normalizer.Form.NFD), "")

private val COMBINING_MARKS = Regex("""\p{Mn}+""")
private val NON_WORD = Regex("""[^\p{L}\p{N}]+""")

internal data class SearchSnippet(val text: String, val highlights: List<IntRange>)

private val WORD = Regex("""[\p{L}\p{N}][\p{L}\p{N}'’-]*""")

/** About [maxChars] of [clean] around the first matching word, matches marked. */
internal fun buildSnippet(clean: String, queryTokens: List<String>, maxChars: Int = 170): SearchSnippet {
    val matches = WORD.findAll(clean).filter { w ->
        val folded = cccFold(w.value)
        queryTokens.any { t -> folded == t || (t.length >= 3 && folded.startsWith(t)) }
    }.toList()
    var start = 0
    if (matches.isNotEmpty()) {
        val first = matches.first().range.first
        start = (first - 55).coerceAtLeast(0)
        if (start > 0) {
            val space = clean.indexOf(' ', start)
            if (space in 0 until first) start = space + 1
        }
    }
    var end = (start + maxChars).coerceAtMost(clean.length)
    if (end < clean.length) {
        val space = clean.lastIndexOf(' ', end)
        if (space > start + maxChars / 2) end = space
    }
    val prefix = if (start > 0) "…" else ""
    val suffix = if (end < clean.length) "…" else ""
    val text = prefix + clean.substring(start, end) + suffix
    val highlights = matches
        .filter { it.range.first >= start && it.range.last < end }
        .map { (it.range.first - start + prefix.length)..(it.range.last - start + prefix.length) }
    return SearchSnippet(text, highlights)
}

internal fun queryTokens(query: String): List<String> =
    cccFold(query).split(NON_WORD).filter { it.length >= 2 }.distinct()

/** "1324", "§1324" or "#1324" → 1324; anything else → null. */
internal fun parseParagraphQuery(query: String): Int? {
    val digits = query.trim().trimStart('§', '#', ' ').trim()
    if (digits.isEmpty() || digits.length > 6 || !digits.all(Char::isDigit)) return null
    return digits.toIntOrNull()
}

// ------------------------------------------------------------ compendium

internal data class CompendiumGroup(val first: Int, val last: Int, val entry: CompendiumQA) {
    val size: Int get() = last - first + 1
}

private val QUESTION_PREFIX = Regex("""^(?:Pregunta|Question|Pergunta)\s+\d+:\s*""")

internal fun String.withoutQuestionPrefix(): String = replaceFirst(QUESTION_PREFIX, "")

/**
 * The Compendium as the data really is: consecutive numbers that carry the
 * same question and answer are one entry, so 51 identical cards become one row
 * "50–100". Every number 1..598 remains in exactly one group.
 */
internal fun compendiumGroups(entries: List<CompendiumQA>): List<CompendiumGroup> {
    val out = ArrayList<CompendiumGroup>()
    var current: CompendiumGroup? = null
    var currentKey = ""
    for (e in entries.distinctBy { it.number }.sortedBy { it.number }) {
        val key = "${e.part}|${e.question.es.withoutQuestionPrefix()}|${e.answer.es}|${e.cccReference}"
        val c = current
        if (c != null && key == currentKey && e.number == c.last + 1) {
            current = c.copy(last = e.number)
        } else {
            if (c != null) out += c
            current = CompendiumGroup(e.number, e.number, e)
            currentKey = key
        }
    }
    current?.let { out += it }
    return out
}

/** The first CCC range in a reference such as "CCC 31-36, 46-47" → (31, 36). */
internal fun firstCccRange(reference: String): Pair<Int, Int?>? {
    val m = Regex("""(\d{1,4})(?:\s*[-–]\s*(\d{1,4}))?""").find(reference) ?: return null
    val a = m.groupValues[1].toInt()
    if (a !in 1..2865) return null
    val b = m.groupValues[2].toIntOrNull()?.takeIf { it in a..2865 }
    return a to b
}

private fun compendiumMatches(group: CompendiumGroup, query: String): Boolean {
    val q = query.trim().lowercase()
    if (q.isEmpty()) return true
    val number = q.toIntOrNull()
    val e = group.entry
    return (number != null && number in group.first..group.last) ||
        e.cccReference.lowercase().contains(q) ||
        listOf(e.question.es, e.question.en, e.question.pt, e.answer.es, e.answer.en, e.answer.pt,
            e.section.es, e.section.en, e.section.pt).any { it.withoutQuestionPrefix().lowercase().contains(q) }
}

private fun rangeLabel(first: Int, last: Int): String = if (last > first) "$first–$last" else "$first"

// ---------------------------------------------------------- last position

private const val READER_PREFS = "catechism_reader"
private const val KEY_LAST = "last_paragraph"

private fun readLastParagraph(context: Context): Int? =
    runCatching {
        context.getSharedPreferences(READER_PREFS, Context.MODE_PRIVATE).getInt(KEY_LAST, 0)
    }.getOrDefault(0).takeIf { it in 1..2865 }

private fun saveLastParagraph(context: Context, number: Int) {
    runCatching {
        context.getSharedPreferences(READER_PREFS, Context.MODE_PRIVATE).edit().putInt(KEY_LAST, number).apply()
    }
}

// ------------------------------------------------------------------ copy

private fun AppLanguage.pick(es: String, en: String, pt: String): String = when (this) {
    AppLanguage.ES -> es
    AppLanguage.EN -> en
    AppLanguage.PT -> pt
}

private fun Int.paragraphs(lang: AppLanguage): String = lang.pick("$this párrafos", "$this paragraphs", "$this parágrafos")

// ---------------------------------------------------------------- pieces

/** Long-form text is held to the reading measure and centred on wide screens. */
@Composable
private fun MeasureColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier
                .widthIn(max = design.spacing.readingMaxWidth + design.spacing.gutter * 2)
                .fillMaxWidth(),
            content = content
        )
    }
}

/** A section header: `headline`, with an optional quiet eyebrow line above it. */
@Composable
private fun CatSectionHeader(text: String, modifier: Modifier = Modifier, eyebrow: String? = null) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(bottom = 10.dp)
    ) {
        if (eyebrow != null) {
            Text(eyebrow, style = styles.footnote, color = p.textSecondary)
            Spacer(Modifier.height(2.dp))
        }
        Text(text, style = styles.headline, color = p.textPrimary, modifier = Modifier.semantics { heading() })
    }
}

/**
 * The shell of one row inside a glass group (the group supplies the glass and
 * the rounded corners): [content] on the left, optional leading and trailing
 * pieces, a chevron, and an inset hairline.
 */
@Composable
private fun CatRowShell(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    leading: (@Composable () -> Unit)? = null,
    leadingWidth: Dp = 0.dp,
    trailing: (@Composable () -> Unit)? = null,
    chevron: Boolean = true,
    divider: Boolean = true,
    minHeight: Dp? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .lumenPress(onClick, 0.99f)
                .heightIn(min = minHeight ?: design.spacing.rowMinHeight)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, null, tint = p.textPrimary, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(16.dp))
            } else if (leading != null) {
                Box(Modifier.width(leadingWidth), contentAlignment = Alignment.CenterStart) { leading() }
            }
            Column(Modifier.weight(1f), content = content)
            if (trailing != null) {
                Spacer(Modifier.width(10.dp))
                trailing()
            }
            if (chevron && onClick != null) {
                Spacer(Modifier.width(10.dp))
                AppChevron()
            }
        }
        if (divider) {
            val inset = 18.dp + when {
                leadingIcon != null -> 38.dp
                leading != null -> leadingWidth
                else -> 0.dp
            }
            Box(Modifier.fillMaxWidth().padding(start = inset).height(0.5.dp).background(p.separator))
        }
    }
}

/** A two-line row: `body` title that wraps in full, `footnote` subtitle. */
@Composable
private fun CatRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
    leadingIcon: ImageVector? = null,
    leading: (@Composable () -> Unit)? = null,
    leadingWidth: Dp = 0.dp,
    trailingText: String? = null,
    trailing: (@Composable () -> Unit)? = null,
    chevron: Boolean = true,
    divider: Boolean = true,
    minHeight: Dp? = null
) {
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    CatRowShell(
        modifier = modifier,
        onClick = onClick,
        leadingIcon = leadingIcon,
        leading = leading,
        leadingWidth = leadingWidth,
        trailing = when {
            trailing != null -> trailing
            trailingText != null -> ({
                Text(trailingText, style = styles.subhead, color = p.textSecondary, maxLines = 1)
            })
            else -> null
        },
        chevron = chevron,
        divider = divider,
        minHeight = minHeight
    ) {
        Text(title, style = styles.body, color = p.textPrimary)
        if (subtitle != null) {
            Spacer(Modifier.height(2.dp))
            Text(subtitle, style = styles.footnote, color = p.textSecondary)
        }
    }
}

/** Rows of a glass group split across lazy items: glass, corners, no shadow. */
private fun catGroupPosition(index: Int, count: Int): GroupPosition = when {
    count <= 1 -> GroupPosition.Single
    index == 0 -> GroupPosition.First
    index == count - 1 -> GroupPosition.Last
    else -> GroupPosition.Middle
}

/** The search capsule. Same glass as the kit's, with the Catechism's tags and spoken names. */
@Composable
private fun CatSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    clearLabel: String,
    fieldTag: String,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val focus = LocalFocusManager.current
    val shape = RoundedCornerShape(percent = 50)
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .heightIn(min = 48.dp)
            .lumenGlass(shape, lifted = false)
            .semantics { contentDescription = placeholder }
            .padding(start = 16.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.Search, null, tint = p.textSecondary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Box(Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    placeholder,
                    style = styles.body,
                    color = p.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = styles.body.copy(color = p.textPrimary),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focus.clearFocus() }),
                modifier = Modifier.fillMaxWidth().testTag(fieldTag)
            )
        }
        if (value.isNotEmpty()) {
            Box(
                Modifier
                    .size(44.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        role = Role.Button,
                        onClickLabel = clearLabel
                    ) { onValueChange("") }
                    .semantics { contentDescription = clearLabel }
                    .testTag("search_clear_button"),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Cancel, null, tint = p.inkQuiet, modifier = Modifier.size(18.dp))
            }
        } else {
            Spacer(Modifier.width(12.dp))
        }
    }
}

// ----------------------------------------------------------------- index

private sealed interface CccSearch {
    data object Idle : CccSearch
    data object Busy : CccSearch
    data class Number(val n: Int) : CccSearch
    data object OutOfRange : CccSearch
    data class Results(val total: Int, val numbers: List<Int>) : CccSearch
}

private const val SEARCH_SHOWN = 50

@Composable
private fun CccIndexPage(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    insets: PaddingValues,
    listState: LazyListState,
    query: String,
    onQueryChange: (String) -> Unit,
    lastRead: Int?,
    onOpenReader: (CatechismOutlineSection, Int?) -> Unit,
    onOpenPart: (Int) -> Unit,
    onOpenCompendium: () -> Unit,
    onOpenFormulas: () -> Unit,
    onOpenUrl: (String) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val focus = LocalFocusManager.current
    val density = LocalDensity.current
    val topInset = insets.calculateTopPadding()
    val outline = repository.catechismOutline

    // Debounced, and off the main thread: the first query builds the trilingual
    // index, which would otherwise stall typing.
    val search by produceState<CccSearch>(CccSearch.Idle, query) {
        val q = query.trim()
        val number = parseParagraphQuery(q)
        when {
            q.isEmpty() -> value = CccSearch.Idle
            number != null -> value = if (number in 1..2865) CccSearch.Number(number) else CccSearch.OutOfRange
            else -> {
                delay(200)
                if (value is CccSearch.Idle || value is CccSearch.Number || value is CccSearch.OutOfRange) {
                    value = CccSearch.Busy
                }
                val scored = withContext(Dispatchers.Default) {
                    CatechismData.searchCatechismHybrid(q, cap = Int.MAX_VALUE)
                }
                value = CccSearch.Results(scored.size, scored.take(SEARCH_SHOWN).map { it.number })
            }
        }
    }

    // Typing the first letter brings the field to the top of the page, under
    // the bar, so the results have the room.
    val searching = query.isNotBlank()
    LaunchedEffect(searching) {
        if (searching && listState.firstVisibleItemIndex < 1) {
            listState.animateScrollToItem(1, -with(density) { (topInset + 8.dp).roundToPx() })
        }
    }

    val bottomPad = insets.calculateBottomPadding() + 24.dp
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = bottomPad)
    ) {
        item(key = "catechism_hero") {
            AppDetailHeader(
                title = lang.pick("Catecismo", "Catechism", "Catecismo"),
                assetPath = "heroes/lumen/catechism.jpg",
                meta = lang.pick("de la Iglesia Católica", "of the Catholic Church", "da Igreja Católica"),
                imageHeight = 300.dp,
                modifier = Modifier.testTag("catechism_hero_photo")
            )
        }
        item(key = "ccc_search_row") {
            Column {
                Spacer(Modifier.height(20.dp))
                CatSearchField(
                    value = query,
                    onValueChange = onQueryChange,
                    placeholder = lang.pick(
                        "Buscar un tema o un número",
                        "Search a topic or number",
                        "Pesquisar tema ou número"
                    ),
                    clearLabel = lang.pick("Limpiar", "Clear", "Limpar"),
                    fieldTag = "ccc_search_input"
                )
            }
        }

        if (searching) {
            when (val s = search) {
                CccSearch.Idle -> Unit
                CccSearch.Busy -> item(key = "ccc_searching") {
                    Column(
                        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter, vertical = 28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            lang.pick("Buscando…", "Searching…", "A pesquisar…"),
                            style = styles.subhead,
                            color = p.textSecondary
                        )
                        Spacer(Modifier.height(12.dp))
                        AppProgressBar(0.35f, Modifier.widthIn(max = 120.dp), height = 3.dp)
                    }
                }

                is CccSearch.Number -> item(key = "ccc_search_number") {
                    Column(Modifier.padding(top = 20.dp)) {
                        NumberHitCard(repository, lang, s.n) { section ->
                            focus.clearFocus()
                            onOpenReader(section, s.n)
                        }
                    }
                }

                CccSearch.OutOfRange -> item(key = "ccc_search_range") {
                    EmptyHint(
                        title = lang.pick(
                            "Los párrafos van del 1 al 2865.",
                            "Paragraphs run from 1 to 2865.",
                            "Os parágrafos vão de 1 a 2865."
                        ),
                        body = null
                    )
                }

                is CccSearch.Results -> {
                    if (s.total == 0) {
                        item(key = "ccc_search_empty") {
                            AppEmptyState(
                                title = lang.pick(
                                    "Sin resultados para «${query.trim()}»",
                                    "No results for “${query.trim()}”",
                                    "Sem resultados para «${query.trim()}»"
                                ),
                                body = lang.pick(
                                    "Prueba con otra palabra o con un número del 1 al 2865.",
                                    "Try another word, or a number from 1 to 2865.",
                                    "Tente outra palavra ou um número de 1 a 2865."
                                ),
                                icon = Icons.Outlined.Search,
                                modifier = Modifier.testTag("ccc_search_empty")
                            )
                        }
                    } else {
                        item(key = "ccc_search_results_header") {
                            val shown = s.numbers.size
                            val label = if (s.total > shown) {
                                lang.pick(
                                    "${s.total} resultados · se muestran $shown",
                                    "${s.total} results · showing $shown",
                                    "${s.total} resultados · mostrando $shown"
                                )
                            } else {
                                lang.pick(
                                    "${s.total} ${if (s.total == 1) "resultado" else "resultados"}",
                                    "${s.total} ${if (s.total == 1) "result" else "results"}",
                                    "${s.total} ${if (s.total == 1) "resultado" else "resultados"}"
                                )
                            }
                            Text(
                                label,
                                style = styles.footnote,
                                color = p.textSecondary,
                                modifier = Modifier
                                    .padding(horizontal = design.spacing.gutter)
                                    .padding(top = 20.dp, bottom = 10.dp)
                                    .testTag("catechism_search_count")
                            )
                        }
                        item(key = "ccc_search_paragraph_numbers") {
                            val tokens = remember(query) { queryTokens(query) }
                            AppListGroup(Modifier.testTag("catechism_search_paragraph_numbers")) {
                                s.numbers.forEachIndexed { i, n ->
                                    SearchResultRow(
                                        repository = repository,
                                        lang = lang,
                                        number = n,
                                        tokens = tokens,
                                        divider = i < s.numbers.lastIndex,
                                        onClick = {
                                            focus.clearFocus()
                                            repository.catechismSectionFor(n)?.let { onOpenReader(it, n) }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // ---- continue reading
            val last = lastRead?.let { n -> repository.catechismSectionFor(n)?.let { n to it } }
            if (last != null) {
                item(key = "catechism_continue") {
                    Column(Modifier.padding(top = 28.dp)) {
                        ContinueCard(lang, last.first, last.second) { onOpenReader(last.second, last.first) }
                    }
                }
            }

            // ---- contents
            item(key = "contents_header") {
                Spacer(Modifier.height(28.dp))
                CatSectionHeader(lang.pick("Contenido", "Contents", "Conteúdo"))
            }
            item(key = "contents_group") {
                val lastPart = lastRead?.let { CccStructure.sectionFor(it)?.part }
                AppListGroup {
                    (0..4).forEach { part ->
                        val range = CccStructure.partRange(part)
                        val title = CccStructure.partTitles[part].get(lang)
                        val subtitle = if (part == 0) {
                            "§§ ${range.first}–${range.last}"
                        } else {
                            "${CccStructure.ordinal(part, lang)} · §§ ${range.first}–${range.last}"
                        }
                        CatRow(
                            title = title,
                            subtitle = subtitle,
                            minHeight = 64.dp,
                            divider = part < 4,
                            trailing = if (lastPart == part) ({
                                AppSeasonDot(p.accent, size = 8.dp)
                            }) else null,
                            onClick = {
                                if (part == 0) onOpenReader(outline.first(), null) else onOpenPart(part)
                            },
                            modifier = Modifier.testTag("catechism_part_$part")
                        )
                    }
                }
            }

            // ---- compendium and formulas
            item(key = "study_header") {
                Spacer(Modifier.height(28.dp))
                CatSectionHeader(
                    lang.pick("Compendio y fórmulas", "Compendium and formulas", "Compêndio e fórmulas")
                )
            }
            item(key = "study_group") {
                AppListGroup {
                    CatRow(
                        title = lang.pick("Compendio", "Compendium", "Compêndio"),
                        subtitle = lang.pick("Preguntas y respuestas", "Questions and answers", "Perguntas e respostas"),
                        leadingIcon = Icons.Outlined.QuestionAnswer,
                        minHeight = 64.dp,
                        onClick = onOpenCompendium,
                        modifier = Modifier.testTag("catechism_open_compendium")
                    )
                    CatRow(
                        title = lang.pick(
                            "Fórmulas de la doctrina católica",
                            "Formulas of Catholic doctrine",
                            "Fórmulas da doutrina católica"
                        ),
                        subtitle = lang.pick(
                            "Mandamientos, preceptos, dones, virtudes, obras de misericordia",
                            "Commandments, precepts, gifts, virtues, works of mercy",
                            "Mandamentos, preceitos, dons, virtudes, obras de misericórdia"
                        ),
                        leadingIcon = Icons.Outlined.Checklist,
                        minHeight = 64.dp,
                        divider = false,
                        onClick = onOpenFormulas,
                        modifier = Modifier.testTag("catechism_open_formulas")
                    )
                }
            }

            // ---- official text
            item(key = "official_header") {
                Spacer(Modifier.height(28.dp))
                CatSectionHeader(lang.pick("Texto oficial", "Official text", "Texto oficial"))
            }
            item(key = "official_group") {
                OfficialTexts(repository, lang, onOpenUrl)
            }

            item(key = "offline_note") {
                Column(
                    Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter).padding(top = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        lang.pick(
                            "Texto íntegro de los 2865 párrafos, disponible sin conexión.",
                            "The complete text of all 2865 paragraphs, available offline.",
                            "Texto integral dos 2865 parágrafos, disponível offline."
                        ),
                        style = styles.footnote,
                        color = p.textSecondary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        lang.pick(
                            "Ilustración: «La disputa del Santísimo Sacramento», Rafael (1509–1510).",
                            "Illustration: “Disputation of the Holy Sacrament,” Raphael (1509–1510).",
                            "Ilustração: «A Disputa do Santíssimo Sacramento», Rafael (1509–1510)."
                        ),
                        style = styles.caption,
                        color = p.textSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyHint(title: String, body: String?) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    Column(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = styles.subhead, color = p.textSecondary, textAlign = TextAlign.Center)
        if (body != null) {
            Spacer(Modifier.height(6.dp))
            Text(body, style = styles.footnote, color = p.textSecondary, textAlign = TextAlign.Center)
        }
    }
}

/** The paragraph text for a § in the reader's language, as displayed. */
private fun cleanParagraph(repository: CatholicDataRepository, number: Int, lang: AppLanguage): String? =
    repository.catechismParagraphText(number, lang)?.let { cccDisplayText(it, lang) }?.takeIf { it.isNotBlank() }

@Composable
private fun NumberHitCard(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    number: Int,
    onOpen: (CatechismOutlineSection) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val section = repository.catechismSectionFor(number) ?: return
    val snippet = remember(number, lang) {
        cleanParagraph(repository, number, lang)?.let { buildSnippet(it, emptyList(), 130).text }
    }
    AppGlassSurface(
        Modifier
            .padding(horizontal = design.spacing.gutter)
            .fillMaxWidth()
            .testTag("catechism_search_result_$number"),
        onClick = { onOpen(section) },
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    lang.pick("Ir al § $number", "Go to § $number", "Ir para § $number"),
                    style = styles.headline,
                    color = p.textPrimary
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    section.title.get(lang) + " · " + section.partTitle.get(lang),
                    style = styles.footnote,
                    color = p.textSecondary
                )
                if (snippet != null) {
                    Spacer(Modifier.height(10.dp))
                    Text(snippet, style = styles.callout, color = p.textSecondary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
            Spacer(Modifier.width(12.dp))
            AppChevron()
        }
    }
}

@Composable
private fun SearchResultRow(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    number: Int,
    tokens: List<String>,
    divider: Boolean,
    onClick: () -> Unit
) {
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    val section = repository.catechismSectionFor(number)
    val snippet = remember(number, lang, tokens) {
        cleanParagraph(repository, number, lang)?.let { buildSnippet(it, tokens) }
    }
    val inkStyle = SpanStyle(color = p.textPrimary, fontWeight = FontWeight.SemiBold)
    val text = remember(snippet, p.textPrimary) {
        snippet?.let { s ->
            buildAnnotatedString {
                append(s.text)
                s.highlights.forEach { addStyle(inkStyle, it.first, it.last + 1) }
            }
        }
    }
    CatRowShell(
        onClick = onClick,
        divider = divider,
        minHeight = 64.dp,
        modifier = Modifier.testTag("catechism_search_result_$number")
    ) {
        Text(
            buildString {
                append("§ $number")
                if (section != null) append(" · ").append(section.title.get(lang))
            },
            style = styles.caption,
            color = p.textSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (text != null) {
            Spacer(Modifier.height(4.dp))
            Text(text, style = styles.callout, color = p.textSecondary, maxLines = 4, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun ContinueCard(
    lang: AppLanguage,
    number: Int,
    section: CatechismOutlineSection,
    onClick: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val part = CccStructure.sectionFor(number)?.part ?: 0
    val progress = (number - section.firstNumber + 1f) / section.paragraphCount.coerceAtLeast(1)
    AppGlassSurface(
        Modifier
            .padding(horizontal = design.spacing.gutter)
            .fillMaxWidth()
            .testTag("catechism_continue"),
        onClick = onClick,
        contentPadding = PaddingValues(design.spacing.cardPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.MenuBook, null, tint = p.textPrimary, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    lang.pick("Continuar leyendo", "Continue reading", "Continuar a ler"),
                    style = styles.caption,
                    color = p.textSecondary
                )
                Spacer(Modifier.height(2.dp))
                Text(section.title.get(lang), style = styles.headline, color = p.textPrimary)
                Spacer(Modifier.height(2.dp))
                Text(
                    "§ $number · " + CccStructure.ordinal(part, lang),
                    style = styles.footnote,
                    color = p.textSecondary
                )
                Spacer(Modifier.height(12.dp))
                AppProgressBar(progress, height = 3.dp)
            }
            Spacer(Modifier.width(12.dp))
            AppChevron()
        }
    }
}

@Composable
private fun OfficialTexts(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    onOpenUrl: (String) -> Unit
) {
    val p = LocalAppDesign.current.palette
    val ownName = lang.pick("Español", "English", "Português")
    val links = repository.officialVaticanLinks
    val own = links.firstOrNull { it.language == ownName } ?: links.firstOrNull() ?: return
    val others = links.filter { it !== own }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val outward: @Composable () -> Unit = {
        Icon(Icons.AutoMirrored.Outlined.OpenInNew, null, tint = p.textSecondary, modifier = Modifier.size(18.dp))
    }
    AppListGroup {
        CatRow(
            title = lang.pick("Catecismo en vatican.va", "Catechism on vatican.va", "Catecismo em vatican.va"),
            subtitle = lang.pick("Texto completo · §§ 1–2865", "Complete text · §§ 1–2865", "Texto completo · §§ 1–2865"),
            trailing = outward,
            chevron = false,
            onClick = { onOpenUrl(own.catechismUrl) },
            modifier = Modifier.testTag("catechism_source_ccc")
        )
        CatRow(
            title = lang.pick("Compendio en vatican.va", "Compendium on vatican.va", "Compêndio em vatican.va"),
            subtitle = lang.pick(
                "598 preguntas y respuestas",
                "598 questions and answers",
                "598 perguntas e respostas"
            ),
            trailing = outward,
            chevron = false,
            onClick = { onOpenUrl(own.compendiumUrl) },
            modifier = Modifier.testTag("catechism_source_compendium")
        )
        CatRow(
            title = lang.pick("Otros idiomas", "Other languages", "Outros idiomas"),
            trailing = {
                Icon(
                    if (expanded) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                    null,
                    tint = p.textSecondary,
                    modifier = Modifier.size(22.dp)
                )
            },
            chevron = false,
            divider = expanded,
            onClick = { expanded = !expanded },
            modifier = Modifier.testTag("catechism_source_languages")
        )
        if (expanded) {
            others.forEachIndexed { i, link ->
                val code = when (link.language) {
                    "Español" -> "es"
                    "English" -> "en"
                    else -> "pt"
                }
                CatRow(
                    title = link.language,
                    subtitle = lang.pick(
                        "Catecismo, texto completo",
                        "Catechism, complete text",
                        "Catecismo, texto completo"
                    ),
                    trailing = outward,
                    chevron = false,
                    divider = i < others.lastIndex,
                    onClick = { onOpenUrl(link.catechismUrl) },
                    modifier = Modifier.testTag("catechism_source_lang_$code")
                )
            }
        }
    }
}

// ------------------------------------------------------------------ part

@Composable
private fun CccPartPage(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    insets: PaddingValues,
    part: Int,
    lastRead: Int?,
    onOpenReader: (CatechismOutlineSection, Int?) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val safePart = part.coerceIn(1, 4)
    val title = CccStructure.partTitles[safePart].get(lang)
    val range = CccStructure.partRange(safePart)
    AppInlineTitle(title)

    val description = repository.catechismParts.getOrNull(safePart - 1)?.description?.get(lang)
    val key = remember(safePart) {
        repository.catechismParts.getOrNull(safePart - 1)
            ?.sections?.flatMap { it.chapters }?.flatMap { it.articles }.orEmpty()
    }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = insets.calculateTopPadding() + 4.dp,
            bottom = insets.calculateBottomPadding() + 24.dp
        )
    ) {
        item(key = "part_title") {
            MeasureColumn {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        "${CccStructure.ordinal(safePart, lang)} · §§ ${range.first}–${range.last}",
                        style = styles.eyebrow,
                        color = p.textSecondary
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        title,
                        style = styles.title1,
                        color = p.textPrimary,
                        modifier = Modifier.semantics { heading() }
                    )
                    if (!description.isNullOrBlank()) {
                        Spacer(Modifier.height(12.dp))
                        Text(description, style = styles.callout, color = p.textSecondary)
                    }
                }
            }
        }

        CccStructure.sectionsOfPart(safePart).forEachIndexed { sectionIndex, book ->
            val sections = repository.catechismOutline.filter { it.firstNumber in book.first..book.last }
            item(key = "book_${book.first}") {
                Spacer(Modifier.height(32.dp))
                CatSectionHeader(
                    text = book.title.get(lang),
                    eyebrow = CccStructure.sectionOrdinal(sectionIndex, lang)
                )
                AppListGroup {
                    sections.forEachIndexed { i, section ->
                        val isLast = lastRead != null && lastRead in section.numbers
                        CatRow(
                            title = section.title.get(lang),
                            subtitle = "§§ ${section.firstNumber}–${section.lastNumber} · " +
                                section.paragraphCount.paragraphs(lang),
                            trailingText = if (isLast) "§ $lastRead" else null,
                            minHeight = 64.dp,
                            divider = i < sections.lastIndex,
                            onClick = { onOpenReader(section, null) },
                            modifier = Modifier.testTag("catechism_section_${section.firstNumber}")
                        )
                    }
                }
            }
        }

        if (key.isNotEmpty()) {
            item(key = "key_paragraphs") {
                Spacer(Modifier.height(32.dp))
                CatSectionHeader(lang.pick("Párrafos clave", "Key paragraphs", "Parágrafos-chave"))
                AppListGroup {
                    key.forEachIndexed { i, article ->
                        val n = article.cccNumber
                        val opening = remember(n, lang) {
                            cleanParagraph(repository, n, lang)?.let { buildSnippet(it, emptyList(), 90).text }
                        }
                        CatRow(
                            title = article.title.get(lang),
                            subtitle = "§ $n" + (opening?.let { " · $it" } ?: ""),
                            minHeight = 64.dp,
                            divider = i < key.lastIndex,
                            onClick = {
                                repository.catechismSectionFor(n)?.let { onOpenReader(it, n) }
                            },
                            modifier = Modifier.testTag("catechism_key_$n")
                        )
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------------- reader

@Composable
private fun CccReaderPage(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    insets: PaddingValues,
    section: CatechismOutlineSection,
    focus: Int?,
    onOpenSection: (CatechismOutlineSection, Int?) -> Unit,
    onOpenPart: (Int) -> Unit,
    onIndex: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val context = LocalContext.current
    val density = LocalDensity.current
    val outline = repository.catechismOutline
    val sectionIndex = outline.indexOf(section).coerceAtLeast(0)
    val book = CccStructure.sectionFor(section.firstNumber)
    val count = section.paragraphCount
    val sectionTitle = section.title.get(lang)

    AppHideTabBar(true)
    AppInlineTitle(sectionTitle)

    val listState = rememberLazyListState()
    var ready by remember(section.firstNumber, focus) { mutableStateOf(focus == null) }
    LaunchedEffect(section.firstNumber, focus) {
        if (focus != null && focus in section.numbers) {
            listState.scrollToItem(focus - section.firstNumber + 1)
            listState.scrollBy(-with(density) { 24.dp.toPx() })
        }
        ready = true
    }

    // Item 0 is the section header; paragraphs are items 1..count.
    val currentNumber by remember(section.firstNumber) {
        derivedStateOf {
            (section.firstNumber + listState.firstVisibleItemIndex - 1)
                .coerceIn(section.firstNumber, section.lastNumber)
        }
    }

    // Where the reader is, kept for "Continue reading": a second after it
    // settles, and at once on leaving.
    val latestNumber by rememberUpdatedState(currentNumber)
    LaunchedEffect(currentNumber) {
        delay(1000)
        saveLastParagraph(context, currentNumber)
    }
    DisposableEffect(section.firstNumber) {
        onDispose { saveLastParagraph(context, latestNumber) }
    }

    var sheet by remember { mutableIntStateOf(0) }
    val dockInset = appDockInset()
    val topInset = insets.calculateTopPadding()

    val progressLabel = "§ $currentNumber · " + lang.pick(
        "${currentNumber - section.firstNumber + 1} de $count · §§ ${section.firstNumber}–${section.lastNumber}",
        "${currentNumber - section.firstNumber + 1} of $count · §§ ${section.firstNumber}–${section.lastNumber}",
        "${currentNumber - section.firstNumber + 1} de $count · §§ ${section.firstNumber}–${section.lastNumber}"
    )
    val progressState = lang.pick("Progreso de la lectura", "Reading progress", "Progresso da leitura")
    val barVisible by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 300 }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize().graphicsLayer { alpha = if (ready) 1f else 0f },
            contentPadding = PaddingValues(top = topInset, bottom = dockInset + 104.dp)
        ) {
            item(key = "paragraph_range_title") {
                ReaderHeader(
                    lang = lang,
                    section = section,
                    book = book,
                    onOpenPart = onOpenPart
                )
            }
            items(count, key = { "paragraph_${section.firstNumber + it}" }) { offset ->
                val number = section.firstNumber + offset
                ParagraphBlock(
                    repository = repository,
                    lang = lang,
                    number = number,
                    focused = focus == number
                )
            }
            item(key = "paragraph_navigation") {
                ReaderFooter(
                    lang = lang,
                    previous = outline.getOrNull(sectionIndex - 1),
                    next = outline.getOrNull(sectionIndex + 1),
                    onOpenSection = { onOpenSection(it, null) },
                    onIndex = onIndex
                )
            }
        }

        // The reading line along the bottom edge of the bar, once the page
        // has scrolled under it.
        Box(
            Modifier
                .align(Alignment.TopStart)
                .padding(top = topInset)
                .fillMaxWidth()
                .height(2.dp)
                .graphicsLayer { alpha = if (barVisible) 1f else 0f }
                .semantics {
                    contentDescription = progressLabel
                    stateDescription = progressState
                }
                .testTag("catechism_reading_progress")
                .background(p.separator)
        ) {
            Box(
                Modifier
                    .fillMaxWidth(((currentNumber - section.firstNumber + 1f) / count).coerceIn(0f, 1f))
                    .height(2.dp)
                    .background(p.accent)
                    .testTag("catechism_chapter_progress")
            )
        }

        AppActionBar(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dockInset + 12.dp)
        ) {
            AppActionBarItem(
                icon = Icons.AutoMirrored.Outlined.FormatListBulleted,
                contentDescription = lang.pick("Contenido", "Contents", "Conteúdo"),
                onClick = { sheet = 1 },
                tag = "catechism_contents_button"
            )
            AppActionBarItem(
                icon = Icons.Outlined.TextFields,
                contentDescription = lang.pick("Tamaño del texto", "Text size", "Tamanho do texto"),
                onClick = { sheet = 2 },
                tag = "catechism_appearance_button"
            )
        }
    }

    when (sheet) {
        1 -> ContentsSheet(
            repository = repository,
            lang = lang,
            current = section,
            onDismiss = { sheet = 0 },
            onOpenSection = { sheet = 0; onOpenSection(it, null) },
            onGoTo = { n ->
                sheet = 0
                repository.catechismSectionFor(n)?.let { onOpenSection(it, n) }
            },
            onIndex = { sheet = 0; onIndex() }
        )

        2 -> ReaderAppearanceSheet(lang) { sheet = 0 }
    }
}

/** The title block that scrolls with the text. */
@Composable
private fun ReaderHeader(
    lang: AppLanguage,
    section: CatechismOutlineSection,
    book: CccBookSection?,
    onOpenPart: (Int) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val part = book?.part ?: 0
    val crumb = buildString {
        append(CccStructure.ordinal(part, lang))
        if (book != null && part != 0) append(" · ").append(book.title.get(lang))
    }
    val refsLabel = lang.pick("Referencias cruzadas", "Cross-references", "Referências cruzadas")
    MeasureColumn {
        Column(Modifier.padding(horizontal = design.spacing.gutter).padding(top = 8.dp, bottom = 32.dp)) {
            Text(
                crumb,
                style = styles.eyebrow,
                color = p.textSecondary,
                modifier = Modifier
                    .then(
                        if (part != 0) Modifier
                            .heightIn(min = 44.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                role = Role.Button,
                                onClickLabel = lang.pick(
                                    "Volver al índice del Catecismo",
                                    "Back to the Catechism index",
                                    "Voltar ao índice do Catecismo"
                                )
                            ) { onOpenPart(part) }
                            .testTag("back_to_catechism_outline")
                        else Modifier
                    )
            )
            Spacer(Modifier.height(6.dp))
            Text(
                section.title.get(lang),
                style = styles.title1,
                color = p.textPrimary,
                modifier = Modifier.semantics { heading() }
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "§§ ${section.firstNumber}–${section.lastNumber} · " + section.paragraphCount.paragraphs(lang),
                style = styles.footnote,
                color = p.textSecondary
            )
            if (section.biblicalReferences.isNotBlank()) {
                Spacer(Modifier.height(4.dp))
                Box(Modifier.testTag("catechism_cross_references_${section.firstNumber}")) {
                    Text(
                        lang.pick("Escritura clave: ", "Key Scripture: ", "Escritura-chave: ") + section.biblicalReferences,
                        style = styles.footnote,
                        color = p.textSecondary,
                        modifier = Modifier
                            .semantics {
                                contentDescription = "$refsLabel: ${section.biblicalReferences}"
                            }
                            .testTag("catechism_cross_references")
                    )
                }
            }
        }
    }
}

/**
 * One paragraph: the § number set inline as a lead-in, the text in the
 * reader's font, citations in the quieter ink. The focused paragraph (deep
 * link, search hit) carries an accent bar in the gutter and a wash that fades.
 */
@Composable
private fun ParagraphBlock(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    number: Int,
    focused: Boolean
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val density = LocalDensity.current
    val accent = p.accent
    val wash = remember(number) { Animatable(if (focused) 1f else 0f) }
    LaunchedEffect(focused) {
        if (focused) {
            delay(1200)
            wash.animateTo(0f, tween(300))
        }
    }
    val clean = remember(number, lang) { cleanParagraph(repository, number, lang) }
    val numberStyle = SpanStyle(
        fontSize = styles.footnote.fontSize,
        fontWeight = FontWeight.SemiBold,
        color = p.textSecondary,
        fontFeatureSettings = "tnum"
    )
    val citationStyle = SpanStyle(color = p.textSecondary)
    val text = remember(clean, p.textSecondary, styles.footnote.fontSize) {
        clean?.let { buildParagraphText(number, it, numberStyle, citationStyle) }
    }
    val currentLabel = lang.pick(
        "Párrafo actual § $number",
        "Current paragraph § $number",
        "Parágrafo atual § $number"
    )
    MeasureColumn {
        Box(
            Modifier
                .fillMaxWidth()
                .drawBehind {
                    if (focused) {
                        val a = wash.value
                        if (a > 0f) {
                            drawRoundRect(
                                color = p.textPrimary.copy(alpha = 0.07f * a),
                                topLeft = Offset(8.dp.toPx(), 2.dp.toPx()),
                                size = Size(size.width - 16.dp.toPx(), size.height - 4.dp.toPx()),
                                cornerRadius = CornerRadius(14.dp.toPx())
                            )
                        }
                        drawRoundRect(
                            color = accent,
                            topLeft = Offset(6.dp.toPx(), 8.dp.toPx()),
                            size = Size(3.dp.toPx(), size.height - 16.dp.toPx()),
                            cornerRadius = CornerRadius(1.5.dp.toPx())
                        )
                    }
                }
                .semantics { if (focused) contentDescription = currentLabel }
                .testTag("catechism_article_$number")
                .padding(horizontal = design.spacing.gutter, vertical = 10.dp)
        ) {
            if (text != null) {
                Text(text, style = styles.reading, color = p.textPrimary)
            } else {
                Text(
                    "§ $number · " + lang.pick(
                        "Texto no disponible en este idioma",
                        "Text not available in this language",
                        "Texto não disponível neste idioma"
                    ),
                    style = styles.footnote,
                    color = p.textSecondary
                )
            }
        }
    }
}

/** Previous and next section, like the end of a chapter in a book. */
@Composable
private fun ReaderFooter(
    lang: AppLanguage,
    previous: CatechismOutlineSection?,
    next: CatechismOutlineSection?,
    onOpenSection: (CatechismOutlineSection) -> Unit,
    onIndex: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    MeasureColumn {
        Column(Modifier.padding(horizontal = design.spacing.gutter).padding(top = 28.dp)) {
            if (previous != null) {
                val label = lang.pick("Anterior", "Previous", "Anterior") + ": " + previous.title.get(lang)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .heightIn(min = 44.dp)
                        .lumenPress({ onOpenSection(previous) }, 0.98f)
                        .semantics {
                            contentDescription = lang.pick(
                                "Ir a la sección anterior: ${previous.title.get(lang)}",
                                "Go to the previous section: ${previous.title.get(lang)}",
                                "Ir para a secção anterior: ${previous.title.get(lang)}"
                            )
                        }
                        .testTag("paragraph_prev_button"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBackIos,
                        null,
                        tint = p.textSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(label, style = styles.subhead, color = p.textSecondary, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(12.dp))
            }
            if (next != null) {
                AppGlassSurface(
                    Modifier
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = lang.pick(
                                "Ir a la sección siguiente: ${next.title.get(lang)}",
                                "Go to the next section: ${next.title.get(lang)}",
                                "Ir para a secção seguinte: ${next.title.get(lang)}"
                            )
                        }
                        .testTag("paragraph_next_button"),
                    onClick = { onOpenSection(next) },
                    contentPadding = PaddingValues(design.spacing.cardPadding)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                lang.pick("Siguiente", "Next", "Seguinte"),
                                style = styles.caption,
                                color = p.textSecondary
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(next.title.get(lang), style = styles.headline, color = p.textPrimary)
                            Spacer(Modifier.height(2.dp))
                            Text(
                                "§§ ${next.firstNumber}–${next.lastNumber}",
                                style = styles.footnote,
                                color = p.textSecondary
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        AppChevron()
                    }
                }
            } else {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        lang.pick("Fin del Catecismo", "End of the Catechism", "Fim do Catecismo"),
                        style = styles.headline,
                        color = p.textPrimary
                    )
                    Spacer(Modifier.height(14.dp))
                    AppSecondaryCapsule(
                        label = lang.pick("Volver al índice", "Back to the index", "Voltar ao índice"),
                        onClick = onIndex,
                        tag = "catechism_back_to_index"
                    )
                }
            }
        }
    }
}

/** The shared "Aa" sheet (size and font), fed with the stored preferences. */
@Composable
private fun ReaderAppearanceSheet(lang: AppLanguage, onDismiss: () -> Unit) {
    val store = LocalPreferencesStore.current
    val prefs by remember(store) { store?.preferences ?: flowOf(null) }
        .collectAsState(initial = null as UserPreferences?)
    AppReadingSettingsSheet(
        currentScale = prefs?.fontSizeScale ?: 1f,
        currentFontId = prefs?.fontFamilyChoice ?: "",
        onDismiss = onDismiss,
        labels = ReadingSheetLabels(
            title = lang.pick("Texto", "Text", "Texto"),
            size = lang.pick("Tamaño", "Size", "Tamanho"),
            font = lang.pick("Tipografía", "Typeface", "Tipografia"),
            defaultFont = lang.pick("Predeterminada", "Default", "Padrão")
        )
    )
}

/** Contents of the book: a part at a time, plus "go to §". */
@Composable
private fun ContentsSheet(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    current: CatechismOutlineSection,
    onDismiss: () -> Unit,
    onOpenSection: (CatechismOutlineSection) -> Unit,
    onGoTo: (Int) -> Unit,
    onIndex: () -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val currentPart = CccStructure.sectionFor(current.firstNumber)?.part ?: 0
    var chosen by remember { mutableIntStateOf(currentPart) }
    var goTo by remember { mutableStateOf("") }
    var invalid by remember { mutableStateOf(false) }
    val sections = repository.catechismOutline.filter {
        (CccStructure.sectionFor(it.firstNumber)?.part ?: 0) == chosen
    }
    fun submit() {
        val n = goTo.toIntOrNull()
        if (n != null && n in 1..2865) onGoTo(n) else invalid = true
    }

    AppSheet(
        onDismiss = onDismiss,
        title = lang.pick("Contenido", "Contents", "Conteúdo"),
        tag = "catechism_contents_sheet"
    ) {
        AppFilterChips(
            options = listOf(lang.pick("Prólogo", "Prologue", "Prólogo"), "I", "II", "III", "IV"),
            selectedIndex = chosen,
            onSelect = { chosen = it }
        )
        Spacer(Modifier.height(10.dp))
        if (chosen != 0) {
            Text(
                CccStructure.partTitles[chosen].get(lang),
                style = styles.footnote,
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
            )
        }
        AppListGroup {
            sections.forEachIndexed { i, section ->
                val isCurrent = section.firstNumber == current.firstNumber
                CatRow(
                    title = section.title.get(lang),
                    subtitle = "§§ ${section.firstNumber}–${section.lastNumber}",
                    chevron = false,
                    divider = i < sections.lastIndex,
                    trailing = if (isCurrent) ({
                        Icon(Icons.Filled.Check, null, tint = p.textPrimary, modifier = Modifier.size(18.dp))
                    }) else null,
                    onClick = { onOpenSection(section) },
                    modifier = Modifier.testTag("catechism_contents_${section.firstNumber}")
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Text(
            lang.pick("Ir al §", "Go to §", "Ir ao §"),
            style = styles.footnote,
            color = p.textSecondary,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
        )
        Row(
            Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val shape = RoundedCornerShape(percent = 50)
            Box(
                Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp)
                    .lumenGlass(shape, lifted = false)
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (goTo.isEmpty()) {
                    Text("1–2865", style = styles.body, color = p.textSecondary)
                }
                BasicTextField(
                    value = goTo,
                    onValueChange = { v ->
                        goTo = v.filter(Char::isDigit).take(4)
                        invalid = false
                    },
                    singleLine = true,
                    textStyle = styles.body.copy(color = p.textPrimary),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Go),
                    keyboardActions = KeyboardActions(onGo = { submit() }),
                    modifier = Modifier.fillMaxWidth().testTag("catechism_goto_input")
                )
            }
            AppSecondaryCapsule(
                label = lang.pick("Ir", "Go", "Ir"),
                onClick = { submit() },
                tag = "catechism_goto_button"
            )
        }
        if (invalid) {
            Text(
                lang.pick("Del 1 al 2865", "From 1 to 2865", "De 1 a 2865"),
                style = styles.footnote,
                color = p.textSecondary,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }
        Spacer(Modifier.height(20.dp))
        AppListGroup {
            CatRow(
                title = lang.pick("Índice completo", "Full index", "Índice completo"),
                chevron = true,
                divider = false,
                onClick = onIndex,
                modifier = Modifier
                    .semantics {
                        contentDescription = lang.pick(
                            "Volver al índice del Catecismo",
                            "Back to the Catechism index",
                            "Voltar ao índice do Catecismo"
                        )
                    }
                    .testTag("back_to_catechism_index")
            )
        }
        Spacer(Modifier.height(8.dp))
    }
}

// ------------------------------------------------------------ compendium

private fun compendiumPartChips(lang: AppLanguage): List<String> = when (lang) {
    AppLanguage.ES -> listOf("Todas", "Credo", "Sacramentos", "Vida en Cristo", "Oración")
    AppLanguage.EN -> listOf("All", "Creed", "Sacraments", "Life in Christ", "Prayer")
    AppLanguage.PT -> listOf("Todas", "Credo", "Sacramentos", "Vida em Cristo", "Oração")
}

@Composable
private fun CccCompendiumPage(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    insets: PaddingValues,
    query: String,
    onQueryChange: (String) -> Unit,
    partFilter: Int,
    onPartFilter: (Int) -> Unit,
    onOpenAnswer: (Int) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppInlineTitle(lang.pick("Compendio", "Compendium", "Compêndio"))

    val groups = remember(repository) { compendiumGroups(repository.compendiumQAEntries) }
    val totalNumbers = remember(groups) { groups.sumOf { it.size } }
    // A search runs over every question in every language; the part chips
    // narrow only when nothing is being searched, so a stale chip never hides
    // a match.
    val shown = remember(groups, query, partFilter) {
        if (query.isNotBlank()) groups.filter { compendiumMatches(it, query) }
        else if (partFilter in 1..4) groups.filter { it.entry.part == partFilter }
        else groups
    }
    val shownNumbers = shown.sumOf { it.size }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = insets.calculateTopPadding() + 4.dp,
            bottom = insets.calculateBottomPadding() + 24.dp
        )
    ) {
        item(key = "compendium_title") {
            MeasureColumn {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        lang.pick("Compendio", "Compendium", "Compêndio"),
                        style = styles.title1,
                        color = p.textPrimary,
                        modifier = Modifier.semantics { heading() }
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        lang.pick(
                            "Preguntas y respuestas del Catecismo",
                            "Questions and answers from the Catechism",
                            "Perguntas e respostas do Catecismo"
                        ),
                        style = styles.subhead,
                        color = p.textSecondary
                    )
                }
            }
        }
        item(key = "compendium_search_row") {
            Column {
                Spacer(Modifier.height(20.dp))
                CatSearchField(
                    value = query,
                    onValueChange = onQueryChange,
                    placeholder = lang.pick(
                        "Buscar en el Compendio",
                        "Search the Compendium",
                        "Pesquisar no Compêndio"
                    ),
                    clearLabel = lang.pick("Limpiar", "Clear", "Limpar"),
                    fieldTag = "compendium_search_input"
                )
                Spacer(Modifier.height(10.dp))
            }
        }
        item(key = "compendium_filters") {
            AppFilterChips(
                options = compendiumPartChips(lang),
                selectedIndex = partFilter,
                onSelect = onPartFilter,
                modifier = Modifier.testTag("compendium_part_picker")
            )
        }
        item(key = "compendium_count") {
            Text(
                if (query.isNotBlank()) {
                    lang.pick(
                        "$shownNumbers preguntas coinciden",
                        "$shownNumbers questions match",
                        "$shownNumbers perguntas correspondem"
                    )
                } else if (partFilter in 1..4) {
                    lang.pick("$shownNumbers preguntas", "$shownNumbers questions", "$shownNumbers perguntas")
                } else {
                    lang.pick("$totalNumbers preguntas", "$totalNumbers questions", "$totalNumbers perguntas")
                },
                style = styles.footnote,
                color = p.textSecondary,
                modifier = Modifier
                    .padding(horizontal = design.spacing.gutter)
                    .padding(top = 14.dp, bottom = 12.dp)
                    .testTag("compendium_count")
            )
        }

        if (shown.isEmpty()) {
            item(key = "compendium_empty") {
                AppEmptyState(
                    title = lang.pick("Sin resultados", "No results", "Sem resultados"),
                    body = lang.pick(
                        "Ninguna pregunta coincide con esa búsqueda. Prueba un número del 1 al 598 o otra palabra.",
                        "No question matches that search. Try a number from 1 to 598, or another word.",
                        "Nenhuma pergunta corresponde a essa pesquisa. Tente um número de 1 a 598 ou outra palavra."
                    ),
                    icon = Icons.Outlined.Search
                )
            }
        } else {
            val byPart = shown.groupBy { it.entry.part }
            val withHeaders = partFilter !in 1..4 || query.isNotBlank()
            byPart.forEach { (part, list) ->
                if (withHeaders) {
                    item(key = "compendium_part_$part") {
                        Spacer(Modifier.height(16.dp))
                        CatSectionHeader(
                            text = CccStructure.partTitles[part.coerceIn(1, 4)].get(lang),
                            eyebrow = CccStructure.ordinal(part.coerceIn(1, 4), lang)
                        )
                    }
                }
                items(list, key = { "compendium_q_${it.first}" }) { group ->
                    val index = list.indexOf(group)
                    val position = catGroupPosition(index, list.size)
                    Box(Modifier.groupedItem(position)) {
                        CatRow(
                            title = group.entry.question.get(lang).withoutQuestionPrefix(),
                            leading = {
                                Text(
                                    rangeLabel(group.first, group.last),
                                    style = styles.footnote.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontFeatureSettings = "tnum"
                                    ),
                                    color = p.textSecondary,
                                    maxLines = 1
                                )
                            },
                            leadingWidth = 64.dp,
                            minHeight = 56.dp,
                            divider = position == GroupPosition.First || position == GroupPosition.Middle,
                            onClick = { onOpenAnswer(group.first) },
                            modifier = Modifier.testTag("compendium_q_${group.first}")
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CccAnswerPage(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    insets: PaddingValues,
    first: Int,
    onOpenAnswer: (Int) -> Unit,
    onOpenParagraph: (Int) -> Unit
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val groups = remember(repository) { compendiumGroups(repository.compendiumQAEntries) }
    val index = groups.indexOfFirst { first in it.first..it.last }.coerceAtLeast(0)
    val group = groups.getOrNull(index)
    val question = group?.entry?.question?.get(lang)?.withoutQuestionPrefix().orEmpty()
    AppInlineTitle(lang.pick("Compendio", "Compendium", "Compêndio"))

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = insets.calculateTopPadding() + 4.dp,
            bottom = insets.calculateBottomPadding() + 24.dp
        )
    ) {
        if (group == null) return@LazyColumn
        val entry = group.entry
        val ref = firstCccRange(entry.cccReference)
        item(key = "answer") {
            MeasureColumn {
                Column(Modifier.padding(horizontal = design.spacing.gutter)) {
                    Text(
                        lang.pick("Compendio", "Compendium", "Compêndio") + " · " +
                            if (group.size > 1) {
                                lang.pick("Preguntas", "Questions", "Perguntas") + " " + rangeLabel(group.first, group.last)
                            } else {
                                lang.pick("Pregunta", "Question", "Pergunta") + " ${group.first}"
                            },
                        style = styles.eyebrow,
                        color = p.textSecondary
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        question,
                        style = styles.title2,
                        color = p.textPrimary,
                        modifier = Modifier.semantics { heading() }
                    )
                    Spacer(Modifier.height(18.dp))
                    Text(entry.answer.get(lang), style = styles.reading, color = p.textPrimary)
                    Spacer(Modifier.height(18.dp))
                    Text(
                        CccStructure.ordinal(entry.part.coerceIn(1, 4), lang) + " · " +
                            CccStructure.partTitles[entry.part.coerceIn(1, 4)].get(lang),
                        style = styles.footnote,
                        color = p.textSecondary
                    )
                    if (ref != null) {
                        Spacer(Modifier.height(20.dp))
                        val (a, b) = ref
                        AppSecondaryCapsule(
                            label = lang.pick("Leer en el Catecismo", "Read in the Catechism", "Ler no Catecismo") +
                                " · " + (if (b != null) "§§ $a–$b" else "§ $a"),
                            onClick = { onOpenParagraph(a) },
                            tag = "compendium_open_ccc_$a"
                        )
                    }
                }
            }
        }
        groups.getOrNull(index + 1)?.let { next ->
            item(key = "answer_next") {
                MeasureColumn {
                    AppGlassSurface(
                        Modifier
                            .padding(horizontal = design.spacing.gutter)
                            .padding(top = 32.dp)
                            .fillMaxWidth()
                            .testTag("compendium_next_question"),
                        onClick = { onOpenAnswer(next.first) },
                        contentPadding = PaddingValues(design.spacing.cardPadding)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text(
                                    lang.pick("Siguiente pregunta", "Next question", "Pergunta seguinte"),
                                    style = styles.caption,
                                    color = p.textSecondary
                                )
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    next.entry.question.get(lang).withoutQuestionPrefix(),
                                    style = styles.headline,
                                    color = p.textPrimary
                                )
                            }
                            Spacer(Modifier.width(12.dp))
                            AppChevron()
                        }
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------- formulas

private val NUMBERED_ITEM = Regex("""^(\d{1,2})\.\s+(.*)$""", RegexOption.DOT_MATCHES_ALL)
private val LABELLED_ITEM = Regex("""^([^:]{1,40}):\s+(.*)$""", RegexOption.DOT_MATCHES_ALL)

@Composable
private fun CccFormulasPage(
    repository: CatholicDataRepository,
    lang: AppLanguage,
    insets: PaddingValues
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    AppInlineTitle(lang.pick("Fórmulas de la doctrina", "Formulas of doctrine", "Fórmulas da doutrina"))
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = insets.calculateTopPadding() + 4.dp,
            bottom = insets.calculateBottomPadding() + 24.dp
        )
    ) {
        item(key = "formulas_title") {
            MeasureColumn {
                Text(
                    lang.pick(
                        "Fórmulas de la doctrina católica",
                        "Formulas of Catholic doctrine",
                        "Fórmulas da doutrina católica"
                    ),
                    style = styles.title1,
                    color = p.textPrimary,
                    modifier = Modifier
                        .padding(horizontal = design.spacing.gutter)
                        .padding(bottom = 8.dp)
                        .semantics { heading() }
                        .testTag("catechism_formulas_title")
                )
            }
        }
        items(repository.doctrineFormulas, key = { it.title.es }) { formula ->
            MeasureColumn {
                Column(Modifier.padding(top = 24.dp)) {
                    CatSectionHeader(formula.title.get(lang))
                    AppGlassSurface(
                        Modifier.padding(horizontal = design.spacing.gutter).fillMaxWidth(),
                        contentPadding = PaddingValues(design.spacing.cardPadding)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            formula.items.forEach { item ->
                                FormulaItem(item.get(lang))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FormulaItem(text: String) {
    val styles = LocalAppTextStyles.current
    val p = LocalAppDesign.current.palette
    val numbered = NUMBERED_ITEM.find(text)
    val labelled = if (numbered == null) LABELLED_ITEM.find(text) else null
    when {
        numbered != null -> Row(Modifier.fillMaxWidth()) {
            Text(
                numbered.groupValues[1],
                style = styles.subhead.copy(fontWeight = FontWeight.SemiBold, fontFeatureSettings = "tnum"),
                color = p.textSecondary,
                modifier = Modifier.width(30.dp).padding(top = 2.dp)
            )
            Text(numbered.groupValues[2], style = styles.reading, color = p.textPrimary, modifier = Modifier.weight(1f))
        }

        labelled != null -> Text(
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append(labelled.groupValues[1]) }
                append(": ")
                append(labelled.groupValues[2])
            },
            style = styles.reading,
            color = p.textPrimary
        )

        else -> Text(text, style = styles.reading, color = p.textPrimary)
    }
}
