package com.example.data.liturgy

import com.example.data.models.AppLanguage
import com.example.data.models.BibleVerse

/** A contiguous verse range, optionally spanning chapters. */
data class ScriptureRange(
    val chapter: Int,
    val verseStart: Int? = null,
    val verseEnd: Int? = null,
    val endChapter: Int = chapter
)

/**
 * Canonical book id plus verse ranges. Examples: `isa 7:10-14; 8:10`,
 * `mat 6:1-6,16-18`, `joh 18:1-19:42`. Commas separate verses within
 * one chapter; semicolons introduce another chapter. Verse-part letters are
 * deliberately unsupported: the bundled editions contain whole verses only.
 */
data class ScriptureReference(
    val bookId: String,
    val chapter: Int,
    val verseStart: Int? = null,
    val verseEnd: Int? = null,
    val endChapter: Int = chapter,
    val additionalRanges: List<ScriptureRange> = emptyList()
) {
    val ranges: List<ScriptureRange>
        get() = listOf(ScriptureRange(chapter, verseStart, verseEnd, endChapter)) + additionalRanges

    companion object {
        private val bookPattern = Regex("""^\s*([0-9a-z]{2,4})\s+(.+?)\s*$""")
        private val chapterPattern = Regex("""^(\d+)(?::(.+))?$""")
        private val versePattern = Regex("""^(\d+)(?:-(?:(\d+):)?(\d+))?$""")

        fun parse(raw: String): ScriptureReference? {
            val match = bookPattern.matchEntire(raw.lowercase()) ?: return null
            val ranges = mutableListOf<ScriptureRange>()
            for (part in match.groupValues[2].split(';')) {
                val chapterMatch = chapterPattern.matchEntire(part.trim()) ?: return null
                val chapter = chapterMatch.groupValues[1].toIntOrNull()?.takeIf { it > 0 } ?: return null
                val verseText = chapterMatch.groupValues[2]
                if (verseText.isEmpty()) {
                    ranges += ScriptureRange(chapter)
                    continue
                }
                for (versePart in verseText.split(',')) {
                    val verseMatch = versePattern.matchEntire(versePart.trim()) ?: return null
                    val start = verseMatch.groupValues[1].toIntOrNull()?.takeIf { it > 0 } ?: return null
                    val endChapter = verseMatch.groupValues[2].let {
                        if (it.isEmpty()) chapter else it.toIntOrNull() ?: return null
                    }
                    val end = verseMatch.groupValues[3].let {
                        if (it.isEmpty()) start else it.toIntOrNull() ?: return null
                    }
                    if (endChapter < chapter || end <= 0 || (endChapter == chapter && end < start)) return null
                    ranges += ScriptureRange(chapter, start, end, endChapter)
                }
            }
            val first = ranges.firstOrNull() ?: return null
            return ScriptureReference(match.groupValues[1], first.chapter, first.verseStart,
                first.verseEnd, first.endChapter, ranges.drop(1))
        }
    }
}

/** Resolves the cited ranges in the selected bundled edition, without language substitution. */
class ScriptureResolver(
    private val bookName: (bookId: String, AppLanguage) -> String?,
    private val versesOf: (bookId: String, chapter: Int, AppLanguage) -> List<BibleVerse>
) {
    fun display(reference: ScriptureReference, language: AppLanguage): String {
        val name = bookName(reference.bookId, language) ?: reference.bookId.uppercase()
        val separator = if (language == AppLanguage.EN) ":" else ", "
        val ranges = reference.ranges.joinToString("; ") { range ->
            val verses = when {
                range.verseStart == null -> ""
                range.endChapter != range.chapter -> "$separator${range.verseStart}–${range.endChapter}$separator${range.verseEnd}"
                range.verseEnd == null || range.verseEnd == range.verseStart -> "$separator${range.verseStart}"
                else -> "$separator${range.verseStart}-${range.verseEnd}"
            }
            "${range.chapter}$verses"
        }
        return "$name $ranges"
    }

    fun display(raw: String, language: AppLanguage): String =
        ScriptureReference.parse(raw)?.let { display(it, language) } ?: raw

    /**
     * Resolve every range completely, or return no passage. Silent clipping and
     * English substitution would conceal an unavailable or mismatched edition.
     * Current bundled Daniel/Esther additions exist in all three languages.
     */
    fun verses(reference: ScriptureReference, language: AppLanguage): List<BibleVerse> {
        val result = mutableListOf<BibleVerse>()
        for (range in reference.ranges) {
            // Bound malformed programmatic ranges before requesting any chapter.
            if (range.chapter <= 0 || range.endChapter < range.chapter || range.endChapter - range.chapter > 150) return emptyList()
            for (chapterNumber in range.chapter..range.endChapter) {
                val chapter = versesOf(reference.bookId, chapterNumber, language)
                if (chapter.isEmpty()) return emptyList()
                val start = if (chapterNumber == range.chapter) range.verseStart ?: chapter.first().number else chapter.first().number
                val end = if (chapterNumber == range.endChapter) range.verseEnd ?: range.verseStart ?: chapter.last().number else chapter.last().number
                if (start <= 0 || end < start) return emptyList()
                val selected = chapter.filter { it.number in start..end }
                if (selected.size.toLong() != end.toLong() - start + 1) return emptyList()
                result += selected
            }
        }
        return result
    }

    fun passage(raw: String, language: AppLanguage): String? {
        val reference = ScriptureReference.parse(raw) ?: return null
        val verses = verses(reference, language)
        if (verses.isEmpty() || verses.any { it.text.get(language).isBlank() }) return null
        return verses.joinToString(" ") { it.text.get(language).trim() }
    }
}
