package com.example.data.search

import com.example.data.liturgy.BibleReferenceQuery
import com.example.data.liturgy.GeneralRomanSanctoral
import com.example.data.liturgy.PsalmNumberingMap
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleData
import com.example.data.repository.CatechismOutline
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ReadingPlanLinks
import com.example.data.repository.ReadingPlansCatalog
import com.example.data.repository.SaintsCatalog
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

/**
 * Builds the [SearchIndex] from the catalogs the app already holds. It reads metadata and text that is
 * already bundled; the 246 novena bodies are never loaded (only the index metadata), and nothing touches
 * the network or Room. Call it off the main thread.
 */
object SearchSources {
    private fun join(vararg parts: String?): String = parts.filterNot { it.isNullOrBlank() }.joinToString("\n")

    fun build(repository: CatholicDataRepository, language: AppLanguage, sections: List<SearchSection>): SearchIndex {
        val started = System.nanoTime()
        fun tr(es: String, en: String, pt: String) = when (language) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
        val out = ArrayList<SearchIndex.Entry>(5000)
        fun add(type: SearchType, id: String, title: String, subtitle: String?, body: String, target: SearchTarget) {
            if (title.isBlank()) return
            out += SearchIndex.Entry(type, id, title.trim(), subtitle?.takeIf { it.isNotBlank() }, body, target)
        }

        // Prayers (the Vatican devotional merged with the bundled prayer details).
        for (p in repository.vaticanPrayers) {
            add(SearchType.PRAYER, p.id, p.title.get(language),
                join(p.latinTitle, p.category.title.get(language)).replace("\n", " · "),
                join(p.leaderText.get(language), p.responseText?.get(language), p.whenToPray.get(language),
                    p.thematicTags.joinToString(" ")),
                SearchTarget.Prayer(p.id))
        }

        // Novenas: index metadata only; bodies stay on disk.
        for (n in repository.officialNovenas) {
            add(SearchType.NOVENA, n.id, n.title.get(language),
                join(n.patron.get(language), n.category.get(language)).replace("\n", " · "),
                join(n.description.get(language), n.tags.joinToString(" ")),
                SearchTarget.Novena(n.id))
        }

        // Saints of the General Roman Calendar that have an editorial profile.
        val profiles = SaintsCatalog.default.byKey
        val locale = Locale.forLanguageTag(language.code)
        for (c in GeneralRomanSanctoral.fixed.filter { !it.isLordFeast }.sortedWith(compareBy({ it.month }, { it.day }, { it.rank.ordinal }))) {
            // Only celebrations with a profile have a page to open.
            val profile = profiles[c.key] ?: continue
            val month = Month.of(c.month).getDisplayName(TextStyle.FULL, locale)
            val date = if (language == AppLanguage.EN) "$month ${c.day}" else "${c.day} de $month"
            add(SearchType.SAINT, c.key, c.title.get(language), "$date · ${c.rank.displayName.get(language)}",
                join(profile.bio.get(language), profile.patronages.get(language).joinToString(", ")),
                SearchTarget.Saint(c.key))
        }

        // Marian and Christ devotions.
        for (d in repository.marianApparitions) {
            add(SearchType.DEVOTION, d.id, d.title.get(language),
                join(tr("Advocación mariana", "Marian title", "Advocação mariana"), d.country.get(language)).replace("\n", " · "),
                join(d.historyAndApparition.get(language), d.location.get(language), d.primarySanctuary.get(language), d.patronageOf.get(language)),
                SearchTarget.Devotion(d.id))
        }
        for (d in repository.christManifestations) {
            add(SearchType.DEVOTION, d.id, d.title.get(language),
                join(tr("Devoción a Cristo", "Devotion to Christ", "Devoção a Cristo"), d.country.get(language)).replace("\n", " · "),
                join(d.originHistory.get(language), d.theologicalMeaning.get(language), d.primarySanctuary.get(language)),
                SearchTarget.Devotion(d.id))
        }

        // Religious orders and Church offices.
        repository.religiousOrders.forEachIndexed { i, o ->
            add(SearchType.ORDER, o.id, o.name.get(language),
                join(o.abbreviation, o.founder.get(language)).replace("\n", " · "),
                join(o.description.get(language), o.charism.get(language), o.motto?.get(language),
                    o.notableSaints.joinToString(", ") { it.get(language) }),
                SearchTarget.Order(i))
        }
        repository.churchOffices.forEachIndexed { i, o ->
            add(SearchType.ORDER, o.id, o.name.get(language), tr("Oficio de la Iglesia", "Church office", "Ofício da Igreja"),
                o.description.get(language), SearchTarget.Office(i))
        }

        // The 101 questions.
        repository.catholicQuestions.forEachIndexed { i, q ->
            add(SearchType.QUESTION, q.number.toString(), q.question.get(language),
                tr("Pregunta ${q.number}", "Question ${q.number}", "Pergunta ${q.number}") + " · " + q.section.get(language),
                join(q.answer.get(language), q.clarification.get(language)),
                SearchTarget.Question(i))
        }

        // Scripture courses: one entry per lesson, under its course.
        for (track in repository.scriptureStudyTracks) {
            val trackTitle = track.title.get(language)
            for (l in track.lessons) {
                add(SearchType.COURSE, l.id, l.title.get(language),
                    "$trackTitle · " + tr("lección ${l.lessonNumber}", "lesson ${l.lessonNumber}", "lição ${l.lessonNumber}"),
                    join(l.biblicalReference, l.vaticanContext.get(language), l.historicalContext?.get(language),
                        l.teachingSections.joinToString("\n") { it.title.get(language) + ". " + it.body.get(language) },
                        l.objectives.joinToString("\n") { it.get(language) }),
                    SearchTarget.Lesson(l.id))
            }
        }

        // Reading plans: titles and descriptions only.
        ReadingPlansCatalog.default.load()?.forEach { plan ->
            add(SearchType.PLAN, plan.id, plan.title.get(language),
                tr("${plan.totalDays} días", "${plan.totalDays} days", "${plan.totalDays} dias"),
                plan.description.get(language), SearchTarget.Plan(plan.id))
        }

        // Screens by title (the confession screen is findable by its name only).
        for (s in sections) add(SearchType.SECTION, s.destination.lowercase(), s.title, null, "", SearchTarget.Section(s.destination))

        // The Catechism, paragraph by paragraph, in the interface language.
        val label = if (language == AppLanguage.EN) "CCC" else "CIC"
        val outline = CatechismOutline.sections
        for (n in 1..CatechismOutline.totalParagraphs) {
            val text = repository.catechismParagraphText(n, language) ?: continue
            val section = outline.firstOrNull { n in it.numbers }?.title?.get(language)
            add(SearchType.CATECHISM, n.toString(), "$label $n", section, text, SearchTarget.Catechism(n))
        }

        return SearchIndex(language, out, (System.nanoTime() - started) / 1_000_000)
    }

    // ---------------------------------------------------------------- references

    private val canon by lazy { BibleReferenceQuery(BibleData.catholicCanonBooks) }
    private val cccQuery = Regex("""^(?:(ccc|cic|catecismo|catechism|§)\s*[,.]?\s*)?(\d{1,4})(?:\s*-\s*\d{1,4})?$""")
    private val labelOnly = ScriptureResolver({ id, lang -> BibleData.catholicCanonBooks.firstOrNull { it.id == id }?.name?.get(lang) }) { _, _, _ -> emptyList() }

    /**
     * Reads [query] as a reference: a Bible passage ("Jn 3,16", "Sal 23", "Luke 1:1-4") or a Catechism
     * paragraph ("CIC 1030", "1030"). The psalm number is read as the usual (Hebrew) number and converted to
     * the numbering of [editionId], so "Sal 23" opens chapter 22 of a Vulgate-numbered edition. Null when
     * the query is not a reference.
     */
    fun reference(query: String, language: AppLanguage, editionId: String?): SearchResult? {
        val q = SearchText.fold(query.trim()).replace(Regex("\\s+"), " ")
        if (q.isEmpty()) return null
        cccQuery.matchEntire(q)?.let { m ->
            val n = m.groupValues[2].toIntOrNull()
            // A bare number is only a paragraph when it could be one; a labelled one must be valid too.
            if (n != null && n in 1..CatechismOutline.totalParagraphs) {
                val label = if (language == AppLanguage.EN) "CCC" else "CIC"
                return SearchResult(SearchType.REFERENCE, "ccc_$n", "$label $n",
                    when (language) {
                        AppLanguage.ES -> "Catecismo de la Iglesia Católica"
                        AppLanguage.EN -> "Catechism of the Catholic Church"
                        AppLanguage.PT -> "Catecismo da Igreja Católica"
                    }, null, SearchTarget.Catechism(n))
            }
            if (m.groupValues[1].isNotEmpty()) return null
        }
        val ref = canon.parse(q) ?: return null
        val raw = buildString {
            append(ref.bookId).append(' ').append(ref.chapter)
            ref.verseStart?.let { append(':').append(it); ref.verseEnd?.let { e -> append('-').append(e) } }
        }
        val target = ReadingPlanLinks.bibleTarget(raw, editionId) ?: return null
        val numbering = PsalmNumberingMap.numberingOf(editionId)
        val title = labelOnly.display(ScriptureReference.parse(raw) ?: return null, language, numbering)
        return SearchResult(SearchType.REFERENCE, "bible_${ref.bookId}_${ref.chapter}_${ref.verseStart ?: 0}", title,
            when (language) { AppLanguage.ES -> "Abrir en la Biblia"; AppLanguage.EN -> "Open in the Bible"; AppLanguage.PT -> "Abrir na Bíblia" },
            null, SearchTarget.Bible(target.bookId, target.chapter, target.verse))
    }

    // ---------------------------------------------------------------- Bible text

    /**
     * Verses of the edition being read that contain every word of [query], ignoring accents and case, at most
     * [limit]. Runs on the caller's thread: call it from IO. The group is capped, so "50" means "50 or more".
     */
    fun bibleText(repository: CatholicDataRepository, query: String, language: AppLanguage, editionId: String, limit: Int = 50): SearchGroup? {
        val folded = SearchText.fold(query.trim()).replace(Regex("\\s+"), " ")
        if (folded.length < SearchIndex.MIN_QUERY_LENGTH) return null
        val tokens = SearchText.tokens(folded)
        val hits = repository.searchBibleText(tokens, limit, language, editionId)
        if (hits.isEmpty()) return null
        val results = hits.map { (bookId, chapter, verse) ->
            val book = BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.name?.get(language) ?: bookId.uppercase()
            val text = verse.text.get(language)
            val separator = if (language == AppLanguage.EN) ":" else ", "
            SearchResult(SearchType.BIBLE, "${bookId}_${chapter}_${verse.number}", "$book $chapter$separator${verse.number}", null,
                SearchText.snippet(text, SearchText.fold(text), tokens) ?: text.take(120), SearchTarget.Bible(bookId, chapter, verse.number))
        }
        return SearchGroup(SearchType.BIBLE, results.size, results, capped = results.size >= limit)
    }
}
