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
        out.append(base?.lowercaseChar() ?: ' ')
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
    val rawNames = listOf(book.name.es, book.name.en, book.name.pt, book.abbreviation)
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

private data class BiblePattern(val regex: Regex, val aliasToBook: Map<String, String>)

private fun compileBiblePattern(books: List<BibleBook>): BiblePattern? {
    val aliasToBook = LinkedHashMap<String, String>()
    for (book in books) {
        for (alias in aliasForms(book)) {
            aliasToBook.putIfAbsent(alias, book.id)
        }
    }
    if (aliasToBook.isEmpty()) return null
    // Longest first so "1 juan" wins over "juan" and "san juan" over "juan".
    val alternation = aliasToBook.keys
        .sortedWith(compareByDescending<String> { it.length }.thenBy { it })
        .joinToString("|") { Regex.escape(it).replace("\\ ", "\\s+") }
    return BiblePattern(
        regex = Regex(
            """(?<![a-z0-9])(?<book>$alternation)\s+(?<chap>\d{1,3})\s*(?:,|:)\s*(?<vs>\d{1,3})(?:\s*-\s*(?<ve>\d{1,3}))?(?!\d)"""
        ),
        aliasToBook = aliasToBook
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
            val bookId = pattern.aliasToBook[match.groups["book"]!!.value] ?: return@forEach
            val chapter = match.groups["chap"]!!.value.toIntOrNull() ?: return@forEach
            val verseStart = match.groups["vs"]!!.value.toIntOrNull() ?: return@forEach
            val verseEnd = match.groups["ve"]?.value?.toIntOrNull()
            hits += match.range.first to FoundationRef.BibleRef(
                ScriptureReference(bookId = bookId, chapter = chapter, verseStart = verseStart, verseEnd = verseEnd)
            )
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
            hits += cont.range.first to FoundationRef.CatechismRef(from, to)
            pos = cont.range.last + 1
        }
    }

    return hits.sortedBy { it.first }.map { it.second }.distinct()
}
