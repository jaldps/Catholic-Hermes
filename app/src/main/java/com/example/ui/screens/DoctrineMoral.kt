package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.liturgy.FoundationRef
import com.example.data.liturgy.parseFoundation
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.ui.components.TextFormat
import com.example.ui.design.AppChevron
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppStepList
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

// -----------------------------------------------------------------------------
// Doctrine: the Decalogue, the precepts, the capital vices, sin and confession,
// and the Didache.
// -----------------------------------------------------------------------------

private fun gravityTitles(language: AppLanguage) = listOf(
    language.tr("Pecado venial y pecado mortal", "Venial and mortal sin", "Pecado venial e pecado mortal"),
    language.tr("Las tres condiciones del pecado mortal", "The three conditions of mortal sin", "As três condições do pecado mortal"),
    language.tr("Cómo prepararse para la confesión", "Preparing for confession", "Como preparar-se para a confissão")
)

@Composable
internal fun MoralDoctrineRoot(
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav,
    spec: RootSpec
) {
    val state = rememberLazyListState()
    val styles = LocalAppTextStyles.current
    val design = LocalAppDesign.current
    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = LocalBottomPad.current)
    ) {
        item(key = "hero", contentType = "hero") { RootHeader(spec) }

        item(key = "commandments") {
            SectionHeader(language.tr("Los Diez Mandamientos", "The Ten Commandments", "Os Dez Mandamentos"), topGap = 4.dp)
            AppListGroup {
                repository.theTenCommandments.forEachIndexed { i, cmd ->
                    val citation = scriptureCitation(cmd.biblicalWording.get(language))?.let { localizedCitation(it, language) }
                    LeadRow(
                        title = TextFormat.withoutOrdinal(cmd.title.get(language)),
                        subtitle = citation,
                        leading = { NumeralCircle(TextFormat.roman(cmd.number)) },
                        onClick = { nav.open("cmd:$i") },
                        tag = "commandment_row_${cmd.number}"
                    )
                }
            }
        }

        item(key = "precepts") {
            SectionHeader(language.tr("Los preceptos de la Iglesia", "The Precepts of the Church", "Os Mandamentos da Igreja"))
            AppListGroup {
                repository.churchPrecepts.forEachIndexed { i, prc ->
                    LeadRow(
                        title = TextFormat.withoutOrdinal(prc.title.get(language)),
                        leading = { NumeralCircle(TextFormat.roman(prc.number)) },
                        onClick = { nav.open("prc:$i") },
                        tag = "precept_row_${prc.number}"
                    )
                }
            }
        }

        item(key = "vices") {
            SectionHeader(language.tr("Vicios capitales y virtudes", "Capital Vices and Virtues", "Vícios capitais e virtudes"))
            AppListGroup {
                repository.capitalSinsVsVirtues.forEachIndexed { i, v ->
                    LeadRow(
                        title = v.capitalSin.get(language),
                        subtitle = language.tr(
                            "Virtud: ${v.opposingVirtue.get(language)}",
                            "Virtue: ${v.opposingVirtue.get(language)}",
                            "Virtude: ${v.opposingVirtue.get(language)}"
                        ),
                        onClick = { nav.open("vice:$i") },
                        tag = "vice_row_$i"
                    )
                }
            }
        }

        item(key = "gravity") {
            SectionHeader(language.tr("El pecado y la confesión", "Sin and Confession", "O pecado e a confissão"))
            AppListGroup {
                gravityTitles(language).forEachIndexed { i, title ->
                    LeadRow(title = title, onClick = { nav.open("grav:$i") }, tag = "sin_row_$i")
                }
            }
        }

        item(key = "didache") {
            SectionHeader(language.tr("Didaché", "Didache", "Didaqué"))
            val intro = repository.didacheIntro.get(language)
            GlassCard(onClick = { nav.open("didache_toc:0") }, modifier = Modifier.testTag("didache_card")) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            language.tr(
                                "Siglo I · ${repository.didacheChapters.size} capítulos",
                                "1st century · ${repository.didacheChapters.size} chapters",
                                "Século I · ${repository.didacheChapters.size} capítulos"
                            ),
                            style = styles.eyebrow,
                            color = design.palette.textSecondary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            language.tr("Didaché", "The Didache", "A Didaqué"),
                            style = styles.title2,
                            color = design.palette.textPrimary,
                            modifier = Modifier.semantics { heading() }
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            firstSentence(splitParagraphs(intro).firstOrNull().orEmpty()),
                            style = styles.callout,
                            color = design.palette.textSecondary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    AppChevron()
                }
            }
        }
    }
}

/** "Rezar ahora" with the name of the prayer it opens under it. */
@Composable
internal fun PrayNow(language: AppLanguage, prayerName: String?, onClick: () -> Unit) {
    val design = LocalAppDesign.current
    Column(
        Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppPrimaryCapsule(
            label = language.tr("Rezar ahora", "Pray now", "Rezar agora"),
            onClick = onClick,
            tag = "pray_now"
        )
        if (prayerName != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                prayerName,
                style = LocalAppTextStyles.current.footnote,
                color = design.palette.textSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
internal fun MoralEntry(
    kind: String,
    index: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    when (kind) {
        "cmd" -> CommandmentPage(index, repository, language, nav)
        "prc" -> PreceptPage(index, repository, language, nav)
        "vice" -> VicePage(index, repository, language, nav)
        "grav" -> SinPage(index, repository, language, nav)
        "didache_toc" -> DidacheContents(repository, language, nav)
        else -> DidachePage(index, repository, language, nav)
    }
}

// ------------------------------------------------------------ commandments

@Composable
private fun CommandmentPage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val list = repository.theTenCommandments
    val index = rawIndex.coerceIn(0, list.lastIndex)
    val cmd = list[index]
    val title = TextFormat.withoutOrdinal(cmd.title.get(language))
    AppInlineTitle(title)
    val wording = cmd.biblicalWording.get(language)
    val citation = scriptureCitation(wording)?.let { localizedCitation(it, language) }
    val bibleRef = remember(wording, language) {
        scriptureCitation(wording)
            ?.let { parseFoundation(it, language, repository.catholicBooks) }
            ?.filterIsInstance<FoundationRef.BibleRef>()?.firstOrNull()
    }
    ReaderPage(
        title = title,
        eyebrow = language.tr(
            "${TextFormat.ordinalWord(cmd.number, language)} mandamiento",
            "${TextFormat.ordinalWord(cmd.number, language)} commandment",
            "${TextFormat.ordinalWord(cmd.number, language)} mandamento"
        )
    ) {
        item(key = "quote") {
            PullQuote(withoutCitation(wording))
            if (citation != null) {
                Spacer(Modifier.height(14.dp))
                val gutter = gutter()
                if (bibleRef != null) {
                    RefChip(
                        label = citation,
                        icon = Icons.Outlined.MenuBook,
                        selected = false,
                        onClick = { nav.openBible(bibleRef.reference.bookId, bibleRef.reference.chapter, bibleRef.reference.verseStart) },
                        modifier = Modifier.padding(start = gutter),
                        tag = "commandment_citation"
                    )
                } else {
                    Text(
                        citation,
                        style = LocalAppTextStyles.current.footnote,
                        color = LocalAppDesign.current.palette.textSecondary,
                        modifier = Modifier.padding(start = gutter + 18.dp)
                    )
                }
            }
            BlockGap(24.dp)
        }
        item(key = "explanation") {
            ReadingParagraphs(cmd.moralExplanation.get(language))
            BlockGap(28.dp)
        }
        item(key = "commands") {
            NoteCard(language.tr("Lo que manda", "What it asks", "O que pede"), cmd.virtuesCommanded.get(language))
            Spacer(Modifier.height(12.dp))
            NoteCard(language.tr("Lo que prohíbe", "What it forbids", "O que proíbe"), cmd.sinsForbidden.get(language))
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = list.getOrNull(index + 1)?.let { TextFormat.withoutOrdinal(it.title.get(language)) },
                prevTitle = list.getOrNull(index - 1)?.let { TextFormat.withoutOrdinal(it.title.get(language)) },
                onNext = { nav.replace("cmd:${index + 1}") },
                onPrev = { nav.replace("cmd:${index - 1}") },
                nextTag = "commandment_next",
                prevTag = "commandment_prev"
            )
        }
    }
}

@Composable
private fun PreceptPage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val list = repository.churchPrecepts
    val index = rawIndex.coerceIn(0, list.lastIndex)
    val prc = list[index]
    val title = TextFormat.withoutOrdinal(prc.title.get(language))
    AppInlineTitle(title)
    ReaderPage(
        title = title,
        eyebrow = language.tr(
            "Precepto ${TextFormat.roman(prc.number)}",
            "Precept ${TextFormat.roman(prc.number)}",
            "Preceito ${TextFormat.roman(prc.number)}"
        )
    ) {
        item(key = "explanation") {
            ReadingParagraphs(prc.explanation.get(language))
            BlockGap(28.dp)
        }
        item(key = "practice") {
            NoteCard(language.tr("En la práctica", "In practice", "Na prática"), prc.applicationInLife.get(language))
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = list.getOrNull(index + 1)?.let { TextFormat.withoutOrdinal(it.title.get(language)) },
                prevTitle = list.getOrNull(index - 1)?.let { TextFormat.withoutOrdinal(it.title.get(language)) },
                onNext = { nav.replace("prc:${index + 1}") },
                onPrev = { nav.replace("prc:${index - 1}") },
                nextTag = "precept_next",
                prevTag = "precept_prev"
            )
        }
    }
}

@Composable
private fun VicePage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val list = repository.capitalSinsVsVirtues
    val index = rawIndex.coerceIn(0, list.lastIndex)
    val v = list[index]
    val title = v.capitalSin.get(language)
    AppInlineTitle(title)
    val styles = LocalAppTextStyles.current
    val design = LocalAppDesign.current
    ReaderPage(title = title, eyebrow = language.tr("Vicio capital", "Capital vice", "Vício capital")) {
        item(key = "meaning") {
            ReadingParagraphs(v.sinMeaning.get(language))
            BlockGap(28.dp)
        }
        item(key = "virtue") {
            GlassCard {
                Text(
                    language.tr("La virtud contraria", "The opposing virtue", "A virtude contrária"),
                    style = styles.footnote,
                    color = design.palette.textSecondary
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    v.opposingVirtue.get(language),
                    style = styles.title2,
                    color = design.palette.textPrimary,
                    modifier = Modifier.semantics { heading() }
                )
            }
            Spacer(Modifier.height(12.dp))
            NoteCard(language.tr("Remedio", "Remedy", "Remédio"), v.virtueRemedy.get(language))
            Spacer(Modifier.height(12.dp))
            NoteCard(language.tr("En la práctica", "In practice", "Na prática"), v.practicalAdvice.get(language))
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = list.getOrNull(index + 1)?.capitalSin?.get(language),
                prevTitle = list.getOrNull(index - 1)?.capitalSin?.get(language),
                onNext = { nav.replace("vice:${index + 1}") },
                onPrev = { nav.replace("vice:${index - 1}") },
                nextTag = "vice_next",
                prevTag = "vice_prev"
            )
        }
    }
}

// --------------------------------------------------------------------- sin

@Composable
private fun SinPage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val titles = gravityTitles(language)
    val index = rawIndex.coerceIn(0, 2)
    val st = repository.sinTheology
    AppInlineTitle(titles[index])
    ReaderPage(
        title = titles[index],
        eyebrow = language.tr("El pecado y la confesión", "Sin and Confession", "O pecado e a confissão")
    ) {
        when (index) {
            0 -> item(key = "body") {
                NoteCard(language.tr("Pecado venial", "Venial sin", "Pecado venial"), st.venialExplanation.get(language))
                Spacer(Modifier.height(12.dp))
                NoteCard(language.tr("Pecado mortal", "Mortal sin", "Pecado mortal"), st.mortalExplanation.get(language))
                BlockGap(28.dp)
            }
            1 -> item(key = "body") {
                ReadingParagraphs(
                    language.tr(
                        "Un pecado es mortal solo cuando se dan a la vez estas tres condiciones:",
                        "A sin is mortal only when all three conditions are met at once:",
                        "Um pecado é mortal só quando se reúnem ao mesmo tempo estas três condições:"
                    )
                )
                Spacer(Modifier.height(16.dp))
                AppStepList(steps = st.mortalThreeConditions.map { it.get(language) })
                BlockGap(28.dp)
            }
            else -> item(key = "body") {
                val block = parseNumbered(st.confessionExamenGuide.get(language))
                if (block.lead.isNotEmpty()) {
                    ReadingParagraphs(block.lead.joinToString("\n\n"))
                    Spacer(Modifier.height(16.dp))
                }
                if (block.steps.isNotEmpty()) {
                    AppStepList(steps = block.steps)
                } else {
                    ReadingParagraphs(st.confessionExamenGuide.get(language))
                }
                BlockGap(28.dp)
            }
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = titles.getOrNull(index + 1),
                prevTitle = titles.getOrNull(index - 1),
                onNext = { nav.replace("grav:${index + 1}") },
                onPrev = { nav.replace("grav:${index - 1}") },
                nextTag = "sin_next",
                prevTag = "sin_prev"
            )
        }
    }
}

// ----------------------------------------------------------------- Didache

@Composable
private fun DidacheContents(repository: CatholicDataRepository, language: AppLanguage, nav: DoctrineNav) {
    val title = language.tr("Didaché", "The Didache", "A Didaqué")
    AppInlineTitle(title)
    ReaderPage(
        title = title,
        eyebrow = language.tr(
            "Siglo I · ${repository.didacheChapters.size} capítulos",
            "1st century · ${repository.didacheChapters.size} chapters",
            "Século I · ${repository.didacheChapters.size} capítulos"
        )
    ) {
        item(key = "intro") {
            ReadingParagraphs(repository.didacheIntro.get(language), lead = true)
        }
        item(key = "chapters") {
            SectionHeader(language.tr("Capítulos", "Chapters", "Capítulos"))
            AppListGroup {
                repository.didacheChapters.forEachIndexed { i, chapter ->
                    LeadRow(
                        title = chapter.heading.get(language),
                        leading = { NumeralCircle(TextFormat.roman(chapter.number), size = 36.dp) },
                        leadingWidth = 36.dp,
                        onClick = { nav.open("didache:$i") },
                        tag = "didache_chapter_${chapter.number}"
                    )
                }
            }
        }
    }
}

@Composable
private fun DidachePage(
    rawIndex: Int,
    repository: CatholicDataRepository,
    language: AppLanguage,
    nav: DoctrineNav
) {
    val chapters = repository.didacheChapters
    if (chapters.isEmpty()) return
    val index = rawIndex.coerceIn(0, chapters.lastIndex)
    val chapter = chapters[index]
    val total = chapters.size
    val heading = chapter.heading.get(language)
    AppInlineTitle(heading)
    val styles = LocalAppTextStyles.current
    val design = LocalAppDesign.current
    val body = chapter.body.get(language)
    val intro = repository.didacheIntro.get(language)
    val speak = buildString {
        append(heading)
        if (index == 0 && intro.isNotBlank()) {
            append("\n\n"); append(intro)
        }
        append("\n\n"); append(body)
    }
    val share = heading + "\n\n" + body
    val progressLabel = language.tr(
        "Capítulo ${index + 1} de $total", "Chapter ${index + 1} of $total", "Capítulo ${index + 1} de $total"
    )
    val prayerId = didacheRelatedPrayerId(chapter.number)
    val prayerName = repository.vaticanPrayers.firstOrNull { it.id == prayerId }?.title?.get(language)
    androidx.compose.runtime.LaunchedEffect(Unit) { nav.voice.prepare() }

    ReaderPage(
        title = heading,
        eyebrow = language.tr(
            "Capítulo ${TextFormat.roman(chapter.number)} de ${TextFormat.roman(total)}",
            "Chapter ${TextFormat.roman(chapter.number)} of ${TextFormat.roman(total)}",
            "Capítulo ${TextFormat.roman(chapter.number)} de ${TextFormat.roman(total)}"
        ),
        progress = (index + 1).toFloat() / total,
        progressLabel = progressLabel,
        progressTag = "didache_progress"
    ) {
        item(key = "actions") {
            ReaderActionRow(language, nav.voice, speak, share, "didache_listen_button", "didache_share")
            BlockGap(24.dp)
        }
        if (index == 0 && intro.isNotBlank()) {
            item(key = "intro") {
                var open by rememberSaveable { mutableStateOf(false) }
                GlassCard(onClick = { open = !open }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            language.tr("Introducción", "Introduction", "Introdução"),
                            style = styles.headline,
                            color = design.palette.textPrimary,
                            modifier = Modifier.weight(1f).semantics { heading() }
                        )
                        Text(
                            if (open) language.tr("Ocultar", "Hide", "Ocultar") else language.tr("Mostrar", "Show", "Mostrar"),
                            style = styles.footnote,
                            color = design.palette.textSecondary
                        )
                    }
                    if (open) {
                        splitParagraphs(intro).forEach { paragraph ->
                            Spacer(Modifier.height(12.dp))
                            Text(paragraph, style = styles.callout, color = design.palette.textPrimary)
                        }
                    }
                }
                BlockGap(24.dp)
            }
        }
        item(key = "text") {
            ReadingParagraphs(body, modifier = Modifier.testTag("didache_reader"))
            BlockGap(32.dp)
        }
        item(key = "pray") {
            PrayNow(language, prayerName) { nav.openPrayer(prayerId) }
            BlockGap(28.dp)
        }
        item(key = "footer") {
            SequenceFooter(
                language,
                nextTitle = chapters.getOrNull(index + 1)?.heading?.get(language),
                prevTitle = chapters.getOrNull(index - 1)?.heading?.get(language),
                onNext = { nav.replace("didache:${index + 1}") },
                onPrev = { nav.replace("didache:${index - 1}") },
                nextTag = "didache_next_button",
                prevTag = "didache_prev_button"
            )
        }
    }
}

/**
 * The prayer related to a Didache chapter, by number. Chapter 8 carries the
 * Lord's Prayer in the Didache itself.
 */
internal fun didacheRelatedPrayerId(chapterNumber: Int): String = when (chapterNumber) {
    7 -> "sign_of_the_cross"
    8 -> "our_father"
    9, 10 -> "adoro_te_devote"
    11 -> "come_holy_spirit"
    12, 13 -> "act_of_charity"
    14 -> "te_deum"
    15, 16 -> "creed"
    else -> "act_of_contrition"
}

/** The prayer related to a 101-Questions entry, by question number. */
internal fun qaRelatedPrayerId(questionNumber: Int): String = when (questionNumber) {
    in 1..10 -> "act_of_hope"
    in 11..20 -> "come_holy_spirit"
    in 21..30 -> "sign_of_the_cross"
    in 31..40 -> "memorare"
    in 41..50 -> "creed"
    in 51..60 -> "act_of_contrition"
    in 61..70 -> "eternal_rest"
    in 71..80 -> "st_joseph"
    in 81..90 -> "peace_prayer"
    in 91..100 -> "suscipe_prayer"
    else -> "our_father"
}
