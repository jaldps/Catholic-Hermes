package com.example.data.search

import java.text.Normalizer
import java.util.concurrent.ConcurrentHashMap

/**
 * Text folding for the global search: accent- and case-insensitive, and
 * length-preserving (one output char per input char) so a match position found
 * in the folded text is the same position in the original, which is what the
 * snippet cuts from.
 */
object SearchText {
    private val folded = ConcurrentHashMap<Char, Char>()

    fun foldChar(c: Char): Char {
        if (c < '\u0080') return if (c in 'A'..'Z') (c + 32) else c
        return folded.getOrPut(c) {
            when (c) {
                '–', '—', '‑' -> '-'
                '’', '‘', '´' -> '\''
                '“', '”', '«', '»' -> '"'
                else -> Normalizer.normalize(c.toString(), Normalizer.Form.NFD)
                    .firstOrNull { Character.getType(it) != Character.NON_SPACING_MARK.toInt() }
                    ?.lowercaseChar() ?: ' '
            }
        }
    }

    fun fold(text: String): String {
        val out = CharArray(text.length)
        for (i in text.indices) out[i] = foldChar(text[i])
        return String(out)
    }

    /** Whitespace-separated tokens of an already folded query. */
    fun tokens(foldedQuery: String): List<String> =
        foldedQuery.split(Regex("\\s+")).filter { it.isNotEmpty() }.distinct()

    /**
     * Index of the first occurrence of [token] in [foldedText] that starts a word (at the start of the text or
     * after a character that is not a letter or digit), or -1. A word-start match finds "María" for "mar"
     * but not "perfumaria" for "maria".
     */
    fun indexOfWord(foldedText: String, token: String, from: Int = 0): Int {
        var at = foldedText.indexOf(token, from)
        while (at > 0 && foldedText[at - 1].isLetterOrDigit()) at = foldedText.indexOf(token, at + 1)
        return at
    }

    fun containsWord(foldedText: String, token: String): Boolean = indexOfWord(foldedText, token) >= 0

    /** [containsWord] on the original [text], folded on the fly (no allocation). */
    fun containsWordFolded(text: String, foldedToken: String): Boolean {
        val n = foldedToken.length
        if (n == 0) return true
        val last = text.length - n
        var i = 0
        while (i <= last) {
            if (foldChar(text[i]) == foldedToken[0] && (i == 0 || !foldChar(text[i - 1]).isLetterOrDigit())) {
                var j = 1
                while (j < n && foldChar(text[i + j]) == foldedToken[j]) j++
                if (j == n) return true
            }
            i++
        }
        return false
    }

    /**
     * A short excerpt of [original] around the first of [tokens] found in [foldedText] (same length as
     * [original]). Null when no token occurs. Cuts on word boundaries and marks the cuts with an ellipsis.
     */
    fun snippet(original: String, foldedText: String, tokens: List<String>, maxChars: Int = 120): String? {
        var pos = -1
        for (t in tokens) {
            val at = indexOfWord(foldedText, t)
            if (at >= 0 && (pos < 0 || at < pos)) pos = at
        }
        if (pos < 0) return null
        var start = (pos - 36).coerceAtLeast(0)
        if (start > 0) {
            val space = original.indexOf(' ', start)
            if (space in start until pos) start = space + 1
        }
        var end = (start + maxChars).coerceAtMost(original.length)
        if (end < original.length) {
            val space = original.lastIndexOf(' ', end)
            if (space > pos) end = space
        }
        val cut = original.substring(start, end).replace(Regex("\\s+"), " ").trim()
        return (if (start > 0) "…" else "") + cut + (if (end < original.length) "…" else "")
    }
}
