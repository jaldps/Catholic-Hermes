package com.example.data.liturgy

import com.example.data.models.AppLanguage
import com.example.data.models.BibleBook
import java.text.Normalizer
import java.util.Locale

/** A clickable reference found inside a foundation/grounding text. */
sealed class FoundationRef {
    /** A Bible citation, e.g. Juan 9,1-3. */
    data class BibleRef(val reference: ScriptureReference) : FoundationRef()

    /** A Catechism paragraph range, e.g. Catecismo, 309-314; end null for a single paragraph. */
    data class CatechismRef(val startParagraph: Int, val endParagraph: Int? = null) : FoundationRef()
}

private val HONORIFICS = setOf("san", "sao", "st", "santa", "santo", "sainte", "saint")

private val CCC_WORDS: Map<AppLanguage, List<String>> = mapOf(
    AppLanguage.ES to listOf("catecismo", "ccc"),
    AppLanguage.EN to listOf("catechism", "ccc"),
    AppLanguage.PT to listOf("catecismo", "ccc")
)

/** Accent/case-folded form, one char per input char so match indices stay aligned. */
private fun fold(text: String): String {
    val out = StringBuilder(text.length)
    for (ch in text) {
        val decomposed = Normalizer.normalize(ch.toString(), Normalizer.Form.NFD)
        val base = decomposed.firstOrNull { Character.getType(it) != Character.NON_SPACING_MARK.toInt() }
        out.append(if (ch == '–' || ch == '—') '-' else base?.lowercaseChar() ?: ' ')
    }
    return out.toString()
}

private fun singular(word: String): String? = when {
    word.endsWith("es") && word.length > 3 -> word.dropLast(2)
    word.endsWith("s") && word.length > 2 -> word.dropLast(1)
    else -> null
}

/** All matchable forms of a book in the folded space: every localized name, its
 *  honorific-stripped form, short head-token form, singular, and the abbreviation. */
private fun aliasForms(book: BibleBook): Set<String> {
    val out = mutableSetOf<String>()
    val common = mapOf(
        "psa" to listOf("Ps", "Pss", "Sl", "Sal"), "joh" to listOf("Jn", "Jo"),
        "luk" to listOf("Lk", "Lc"), "mar" to listOf("Mk", "Mc"), "mat" to listOf("Mt"),
        "1co" to listOf("1 Cor"), "2co" to listOf("2 Cor"),
        "1sa" to listOf("1 Sam"), "2sa" to listOf("2 Sam"),
        "1ki" to listOf("1 Kgs"), "2ki" to listOf("2 Kgs"),
        "1ch" to listOf("1 Chr"), "2ch" to listOf("2 Chr"),
        "1th" to listOf("1 Thess"), "2th" to listOf("2 Thess"),
        "1ti" to listOf("1 Tim"), "2ti" to listOf("2 Tim"),
        "1pe" to listOf("1 Pet"), "2pe" to listOf("2 Pet"),
        "1jo" to listOf("1 Jn"), "2jo" to listOf("2 Jn"), "3jo" to listOf("3 Jn"),
        "1ma" to listOf("1 Macc"), "2ma" to listOf("2 Macc"), "heb" to listOf("Heb"),
        "phi" to listOf("Phil"), "rev" to listOf("Rev"), "jam" to listOf("Jas")
    )
    val rawNames = listOf(book.name.es, book.name.en, book.name.pt, book.abbreviation, book.id) + common[book.id].orEmpty()
    for (raw in rawNames) {
        val name = fold(raw).trim()
        if (name.isEmpty()) continue
        val forms = mutableSetOf(name)
        val tokens = name.split(Regex("\\s+")).filter { it.isNotEmpty() }
        val withoutHonorific = tokens.dropWhile { it in HONORIFICS }
        if (withoutHonorific.isNotEmpty() && withoutHonorific.size != tokens.size) {
            forms += withoutHonorific.joinToString(" ")
            forms += withoutHonorific.first()
        }
        // Numbered books: "1 juan" and its head tokens; also first-token short form
        // ("Hechos de los Apostoles" -> "hechos", "Cantar de los Cantares" -> "cantar").
        if (tokens.size > 1 && tokens.first().any { it.isLetter() }) forms += tokens.first()
        // Singular variants so "Salmo 139" matches a book named "Salmos".
        forms.toList().forEach { form ->
            val lastWord = form.substringAfterLast(' ')
            val base = if (lastWord == form) "" else form.removeSuffix(lastWord)
            singular(lastWord)?.let { forms += base + it }
        }
        out += forms
    }
    out.removeAll(String::isEmpty)
    return out
}

private data class BiblePattern(val regex: Regex, val aliasToBook: Map<String, String>, val alternation: String)

private fun compileBiblePattern(books: List<BibleBook>): BiblePattern? {
    val aliasToBook = LinkedHashMap<String, String>()
    for (book in books) {
        for (alias in aliasForms(book)) {
            aliasToBook.putIfAbsent(alias.replace(Regex("^([1-3])\\s*"), "$1"), book.id)
        }
    }
    if (aliasToBook.isEmpty()) return null
    // Longest first so "1 juan" wins over "juan" and "san juan" over "juan".
    val alternation = aliasToBook.keys
        .sortedWith(compareByDescending<String> { it.length }.thenBy { it })
        .joinToString("|") { alias ->
            val numbered = alias.firstOrNull()?.isDigit() == true
            if (numbered) Regex.escape(alias.take(1)) + "\\s*" +
                alias.drop(1).split(' ').joinToString("\\s+") { Regex.escape(it) }
            else alias.split(' ').joinToString("\\s+") { Regex.escape(it) }
        }
    return BiblePattern(
        regex = Regex(
            """(?<![a-z0-9])(?<book>$alternation)\.?\s+(?<citation>\d{1,3}\s*[:,]\s*\d{1,3}[a-d]?(?:\s*[-,:;]\s*\d{1,3}[a-d]?)*)(?!(?:\s*[-,:;]\s*\d)|[a-z0-9:,-])"""
        ),
        aliasToBook = aliasToBook,
        alternation = alternation
    )
}

private fun compileCccPattern(language: AppLanguage): Regex {
    val words = CCC_WORDS.getValue(language).joinToString("|") { Regex.escape(it) }
    return Regex(
        """(?<![a-z0-9])(?:$words)(?:\s*,\s*|\s+)(?<from>\d{1,4})(?:\s*-\s*(?<to>\d{1,4}))?(?!\d)"""
    )
}

private val CCC_CONTINUATION = Regex(
    """(?:\s*,\s*|\s+(?:y|e|and)\s+)(?<from>\d{1,4})(?:\s*-\s*(?<to>\d{1,4}))?(?!\d)"""
)

/**
 * Extracts Bible and Catechism references from a foundation text, in text order,
 * deduplicated. Recognizes ES/PT `Lucas 1,1-4`, EN `Luke 1:1-4`, and CCC forms
 * such as `Catecismo, 309-314` / `Catechism, 309-314` / `Catecismo 1030`.
 * Book names match against [books]' localized names (accent-insensitive,
 * honorifics stripped, singular variants) and abbreviations, longest first.
 */
fun parseFoundation(text: String, language: AppLanguage, books: List<BibleBook>): List<FoundationRef> {
    if (text.isBlank() || books.isEmpty()) return emptyList()
    val folded = fold(text)
    val hits = mutableListOf<Pair<Int, FoundationRef>>()

    compileBiblePattern(books)?.let { pattern ->
        pattern.regex.findAll(folded).forEach { match ->
            val alias = match.groups["book"]!!.value.replace(Regex("\\s+"), " ")
                .replace(Regex("^([1-3])\\s*"), "$1")
            val bookId = pattern.aliasToBook[alias] ?: return@forEach
            val citation = match.groups["citation"]!!.value.replace(Regex("\\s+"), "")
                .replaceFirst(Regex("^(\\d+),"), "$1:")
            val parsed = ScriptureReference.parse("$bookId $citation") ?: return@forEach
            val limit = books.first { it.id == bookId }.chaptersCount
            if (parsed.ranges.any { it.chapter > limit || it.endChapter > limit }) return@forEach
            val reference = if (parsed.endChapter == parsed.chapter && parsed.additionalRanges.isEmpty() &&
                parsed.verseEnd == parsed.verseStart) parsed.copy(verseEnd = null) else parsed
            hits += match.range.first to FoundationRef.BibleRef(reference)
        }
    }

    val ccc = compileCccPattern(language)
    ccc.findAll(folded).forEach { match ->
        val first = match.groups["from"]!!.value.toIntOrNull() ?: return@forEach
        val last = match.groups["to"]?.value?.toIntOrNull()
        hits += match.range.first to FoundationRef.CatechismRef(first, last)
        // Continuations: "Catecismo, 293-294 y 2096-2097" / EN "...and 2096-2097".
        var pos = match.range.last + 1
        while (pos < folded.length) {
            val cont = CCC_CONTINUATION.matchAt(folded, pos) ?: break
            val from = cont.groups["from"]!!.value.toIntOrNull() ?: break
            val to = cont.groups["to"]?.value?.toIntOrNull()
            if (from !in 1..2865 || (to != null && to !in from..2865)) break
            hits += cont.range.first to FoundationRef.CatechismRef(from, to)
            pos = cont.range.last + 1
        }
    }

    return hits.sortedBy { it.first }.map { it.second }.distinct()
}


/**
 * Reads a whole query as one Bible reference, with or without verses: "Jn 3,16", "Luke 1:1-4", "Sal 23",
 * "gen 1", "1 Cor 13". The book is matched the same way [parseFoundation] does it (localized names and
 * abbreviations in ES/EN/PT, accent- and case-insensitive); the chapter must exist in the book. The numbers
 * are returned as typed; converting Hebrew psalm numbers to an edition's numbering is the caller's concern.
 */
class BibleReferenceQuery(private val books: List<BibleBook>) {
    private val pattern = compileBiblePattern(books)
    private val regex: Regex? = pattern?.let {
        Regex(
            """^(?<book>${it.alternation})\.?\s*(?<chapter>\d{1,3})(?:\s*[:,.]\s*(?<v1>\d{1,3})(?:\s*-\s*(?<v2>\d{1,3}))?)?$"""
        )
    }

    fun parse(query: String): ScriptureReference? {
        val pattern = pattern ?: return null
        val match = regex?.matchEntire(fold(query.trim())) ?: return null
        val alias = match.groups["book"]!!.value.replace(Regex("\\s+"), " ").replace(Regex("^([1-3])\\s*"), "$1")
        val bookId = pattern.aliasToBook[alias] ?: return null
        val chapter = match.groups["chapter"]!!.value.toIntOrNull()?.takeIf { it > 0 } ?: return null
        if (chapter > books.first { it.id == bookId }.chaptersCount) return null
        val first = match.groups["v1"]?.value?.toIntOrNull()?.takeIf { it > 0 }
        if (match.groups["v1"] != null && first == null) return null
        val last = match.groups["v2"]?.value?.toIntOrNull()?.takeIf { first != null && it > first }
        return ScriptureReference(bookId, chapter, first, last)
    }
}
