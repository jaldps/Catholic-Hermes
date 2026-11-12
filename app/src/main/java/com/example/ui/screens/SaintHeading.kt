package com.example.ui.screens

import com.example.data.models.AppLanguage

/**
 * A saint's name as a display title, and what the heading says about them.
 *
 * Vatican News headings arrive as "s. León Magno, papa y doctor de la Iglesia":
 * a lowercase abbreviation, then the name, then the role after a comma. As a
 * title that reads as a typo, so the honorific is written out and capitalised
 * ("San León Magno") and the role becomes a line of its own ("Papa y doctor de
 * la Iglesia").
 */
internal data class SaintHeading(val title: String, val role: String?)

internal fun saintHeading(raw: String, language: AppLanguage): SaintHeading {
    val trimmed = raw.trim()
    if (trimmed.isEmpty()) return SaintHeading("", null)
    val cut = topLevelComma(trimmed)
    val head = if (cut >= 0) trimmed.substring(0, cut).trim() else trimmed
    val tail = if (cut >= 0) trimmed.substring(cut + 1).trim().trimEnd('.', ' ') else ""
    val role = tail.takeIf { it.isNotBlank() }?.let { it.replaceFirstChar(Char::titlecase) }
    return SaintHeading(writeOutHonorific(head, language), role)
}

/** Index of the first comma that is not inside parentheses, or -1. */
private fun topLevelComma(text: String): Int {
    var depth = 0
    text.forEachIndexed { i, c ->
        when (c) {
            '(' -> depth++
            ')' -> if (depth > 0) depth--
            ',' -> if (depth == 0) return i
        }
    }
    return -1
}

private val Honorific = Regex("""^(ss|sts|stas|stos|bb|sta|sto|bta|bto|bl|st|s|b)(\.\s*|\s+)(\S.*)$""", RegexOption.IGNORE_CASE)
private val SingleLetterNeedsDot = setOf("s", "b")

/** "s. León" -> "San León", "sta. Isabel" -> "Santa Isabel"; full words are only capitalised. */
internal fun writeOutHonorific(name: String, language: AppLanguage): String {
    val m = Honorific.matchEntire(name)
    if (m != null) {
        val abbreviation = m.groupValues[1].lowercase()
        val hasDot = m.groupValues[2].contains('.')
        if (abbreviation !in SingleLetterNeedsDot || hasDot) {
            val rest = m.groupValues[3]
            return honorific(abbreviation, rest, language) + " " + rest
        }
    }
    return name.replaceFirstChar(Char::titlecase)
}

private fun honorific(abbreviation: String, rest: String, language: AppLanguage): String {
    val first = rest.firstOrNull()?.lowercaseChar() ?: ' '
    return when (language) {
        AppLanguage.EN -> when (abbreviation) {
            "sts", "ss" -> "Sts."
            "bl", "b", "bto", "bta" -> "Blessed"
            "bb" -> "Blesseds"
            else -> "St."
        }
        AppLanguage.ES -> when (abbreviation) {
            "sta" -> "Santa"
            "sto" -> "Santo"
            "ss", "sts" -> "Santos"
            "stas" -> "Santas"
            "stos" -> "Santos"
            "bta" -> "Beata"
            "bb" -> "Beatos"
            "b", "bto", "bl" -> "Beato"
            else -> if (rest.startsWithAny("domingo", "tomás", "tomas", "tomé", "toribio")) "Santo" else "San"
        }
        AppLanguage.PT -> when (abbreviation) {
            "sta", "stas" -> if (abbreviation == "stas") "Santas" else "Santa"
            "sto" -> "Santo"
            "ss", "sts", "stos" -> "Santos"
            "bta" -> "Beata"
            "bb" -> "Beatos"
            "b", "bto", "bl" -> "Beato"
            else -> if (first in "aeiouáéíóúâêô" || rest.startsWithAny("domingos", "tomé", "tomás")) "Santo" else "São"
        }
    }
}

private fun String.startsWithAny(vararg prefixes: String) = prefixes.any { startsWith(it, ignoreCase = true) }
