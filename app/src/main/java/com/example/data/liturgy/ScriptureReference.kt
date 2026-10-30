package com.example.data.liturgy

import com.example.data.models.AppLanguage
import com.example.data.models.BibleVerse

/**
 * A scripture citation: one book, one chapter, and optionally a verse range.
 *
 * References are written in the lectionary with canonical book ids rather than
 * human abbreviations — `"isa 7:10-14"`, not `"Is 7,10-14"` — because the same
 * abbreviation means different books across Spanish, English and Portuguese,
 * and the id is unambiguous. The human form is generated for display instead.
 */
data class ScriptureReference(
    val bookId: String,
    val chapter: Int,
    val verseStart: Int? = null,
    val verseEnd: Int? = null
) {
    companion object {
        private val pattern = Regex("""^\s*([0-9a-z]{2,4})\s+(\d+)(?::(\d+)(?:-(\d+))?)?\s*$""")

        /** Parses `"book chapter[:start[-end]]"`; null when malformed. */
        fun parse(raw: String): ScriptureReference? {
            val match = pattern.matchEntire(raw.lowercase()) ?: return null
            val (book, chapter, start, end) = match.destructured
            return ScriptureReference(
                bookId = book,
                chapter = chapter.toIntOrNull() ?: return null,
                verseStart = start.toIntOrNull(),
                verseEnd = end.toIntOrNull()
            )
        }
    }
}

/**
 * Turns references into readable citations and into actual scripture text,
 * drawn from the bundled Bible so the missal shows the same translation the
 * reader uses elsewhere in the app.
 */
class ScriptureResolver(
    private val bookName: (bookId: String, AppLanguage) -> String?,
    private val versesOf: (bookId: String, chapter: Int, AppLanguage) -> List<BibleVerse>
) {

    /** e.g. `"Isaías 7, 10-14"` in Spanish, `"Isaiah 7:10-14"` in English. */
    fun display(reference: ScriptureReference, language: AppLanguage): String {
        val name = bookName(reference.bookId, language) ?: reference.bookId.uppercase()
        val separator = if (language == AppLanguage.EN) ":" else ", "
        val verses = when {
            reference.verseStart == null -> ""
            reference.verseEnd == null -> "$separator${reference.verseStart}"
            else -> "$separator${reference.verseStart}-${reference.verseEnd}"
        }
        return "$name ${reference.chapter}$verses"
    }

    fun display(raw: String, language: AppLanguage): String =
        ScriptureReference.parse(raw)?.let { display(it, language) } ?: raw

    /**
     * The verses a reference covers, clipped to what the chapter actually has.
     *
     * Falls back to English when the requested range is absent in [language].
     * The Spanish and Portuguese texts are built on translations that omit two
     * deuterocanonical passages — the Canticle of the Three Young Men at Daniel
     * 3:52-90 and part of the Greek additions to Esther — so a citation of
     * those would otherwise render blank. Showing the passage in English is
     * better than showing nothing where the Church appoints a reading.
     */
    fun verses(reference: ScriptureReference, language: AppLanguage): List<BibleVerse> {
        val chapter = versesOf(reference.bookId, reference.chapter, language)
        if (chapter.isEmpty()) return emptyList()
        val start = reference.verseStart ?: return chapter
        val end = reference.verseEnd ?: start
        val selected = chapter.filter { it.number in start..end }
        if (selected.isNotEmpty() || language == AppLanguage.EN) return selected
        return versesOf(reference.bookId, reference.chapter, AppLanguage.EN)
            .filter { it.number in start..end }
    }

    /**
     * The passage as running text, verse numbers omitted. Returns null rather
     * than a placeholder when the reference cannot be resolved, so callers can
     * decide what to show instead of displaying invented scripture.
     */
    fun passage(raw: String, language: AppLanguage): String? {
        val reference = ScriptureReference.parse(raw) ?: return null
        val verses = verses(reference, language)
        if (verses.isEmpty()) return null
        return verses.joinToString(" ") { it.text.get(language).trim() }
    }
}
