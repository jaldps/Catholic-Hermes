package com.example.data.search

import com.example.data.models.AppLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * The in-memory index behind the global search: one entry per searchable item of the app, in one language.
 * Built once off the main thread (see [SearchIndexHolder]) and reused; searching it is a linear scan of
 * folded strings, a few milliseconds for the whole app, so it needs no database and no network.
 */
class SearchIndex internal constructor(
    val language: AppLanguage,
    private val entries: List<Entry>,
    /** How long the build took, for the logs and the report. */
    val buildMillis: Long
) {
    internal class Entry(
        val type: SearchType,
        val id: String,
        val title: String,
        val subtitle: String?,
        val body: String,
        val target: SearchTarget
    ) {
        val titleFolded: String = SearchText.fold(title)
        val subtitleFolded: String = subtitle?.let(SearchText::fold).orEmpty()
        val bodyFolded: String = SearchText.fold(body)
    }

    val size: Int get() = entries.size

    fun countOf(type: SearchType): Int = entries.count { it.type == type }

    private class Hit(val entry: Entry, val score: Int)

    /**
     * Groups of results for [rawQuery], best group first. Every word of the query must occur (title,
     * subtitle or body, ignoring accents and case); title matches rank above body matches. Each group keeps
     * its best [perTypeCap] results. A query shorter than [MIN_QUERY_LENGTH] finds nothing.
     */
    fun search(rawQuery: String, perTypeCap: Int = DEFAULT_CAP): List<SearchGroup> {
        val folded = SearchText.fold(rawQuery.trim()).replace(Regex("\\s+"), " ")
        if (folded.length < MIN_QUERY_LENGTH) return emptyList()
        val tokens = SearchText.tokens(folded)
        val byType = HashMap<SearchType, MutableList<Hit>>()
        for (e in entries) {
            val score = score(e, folded, tokens)
            if (score > 0) byType.getOrPut(e.type) { ArrayList() }.add(Hit(e, score))
        }
        return byType.entries
            .map { (type, hits) ->
                val ranked = hits.sortedByDescending { it.score }
                type to ranked
            }
            // Screens by name are a shortcut, not the answer: they rank behind content that matches as well.
            .sortedWith(compareByDescending<Pair<SearchType, List<Hit>>> { it.second.first().score - if (it.first == SearchType.SECTION) 700 else 0 }
                .thenBy { it.first.ordinal })
            .map { (type, ranked) ->
                SearchGroup(
                    type = type, total = ranked.size,
                    results = ranked.take(perTypeCap).map { it.entry.toResult(tokens) }
                )
            }
    }

    private fun score(e: Entry, folded: String, tokens: List<String>): Int {
        var inTitle = 0
        var inSubtitle = 0
        for (t in tokens) {
            when {
                SearchText.containsWord(e.titleFolded, t) -> inTitle++
                SearchText.containsWord(e.subtitleFolded, t) -> inSubtitle++
                SearchText.containsWord(e.bodyFolded, t) -> Unit
                else -> return 0
            }
        }
        return when {
            inTitle == tokens.size -> {
                var s = 1000
                if (e.titleFolded == folded) s += 500
                else if (e.titleFolded.startsWith(folded)) s += 300
                else if (e.titleFolded.contains(" $folded")) s += 100
                s - (e.title.length / 8).coerceAtMost(90)
            }
            inTitle > 0 -> 600 + inTitle * 20
            inTitle + inSubtitle == tokens.size && inSubtitle > 0 -> 400
            else -> 100 + if (tokens.size > 1 && SearchText.containsWord(e.bodyFolded, folded)) 50 else 0
        }
    }

    private fun Entry.toResult(tokens: List<String>): SearchResult {
        val inTitleOrSubtitle = tokens.all { SearchText.containsWord(titleFolded, it) || SearchText.containsWord(subtitleFolded, it) }
        val snippet = if (inTitleOrSubtitle) null else SearchText.snippet(body, bodyFolded, tokens)
        return SearchResult(type, id, title, subtitle, snippet, target)
    }

    companion object {
        const val MIN_QUERY_LENGTH = 2
        const val DEFAULT_CAP = 50
    }
}

/**
 * The one shared index. The first caller builds it on [Dispatchers.IO]; later callers (and callers that
 * arrive during the build) get the same result. A language change builds a new one. Nothing is persisted.
 */
object SearchIndexHolder {
    private val mutex = Mutex()
    @Volatile private var cached: SearchIndex? = null

    /** The ready index for [language], or null while none has been built. */
    fun peek(language: AppLanguage): SearchIndex? = cached?.takeIf { it.language == language }

    suspend fun get(language: AppLanguage, build: () -> SearchIndex): SearchIndex {
        peek(language)?.let { return it }
        return mutex.withLock {
            peek(language) ?: withContext(Dispatchers.IO) { build() }.also { cached = it }
        }
    }

    /** Drops the index (tests, and catalogs replaced at run time). */
    fun reset() { cached = null }
}
