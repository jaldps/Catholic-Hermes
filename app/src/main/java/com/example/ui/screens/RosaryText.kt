package com.example.ui.screens

import com.example.data.models.AppLanguage

/** A short prayer (Sign of the Cross, a chaplet decade) is set larger and centred like a lyric. */
internal fun isShortPrayer(step: GuideStep): Boolean =
    step.meditation == null && step.detail == null && step.leader.length + step.response.length <= 150

/** The prayer's name says exactly what its first line says ("Por su dolorosa Pasión"). */
internal fun nameRepeatsText(step: GuideStep): Boolean {
    fun norm(t: String) = t.trim().trimEnd(',', '.', ';', ':', '!', ' ').lowercase()
    return norm(step.name) == norm(step.leader)
}

private val ProperWords = setOf(
    "dios", "jesús", "jesucristo", "cristo", "maría", "señor", "espíritu", "él",
    "deus", "jesus", "maria", "senhor", "espírito", "ele"
)
private val Punct = charArrayOf('\'', '"', '«', '»', '“', '”', '‘', '’', ',', '.', ';', ':', '!', '?', '(', ')')

/**
 * Spanish and Portuguese write a fruit in sentence case ("Fruto: el dolor por
 * nuestros pecados y la conformidad con la voluntad de Dios"); the data has it
 * in Title Case. Everything after the colon is lowered except the names of God
 * and of the Virgin ("Espíritu Santo" keeps its second capital). English is
 * left as written.
 */
internal fun sentenceCaseFruit(text: String, lang: AppLanguage): String {
    if (lang == AppLanguage.EN) return text
    val colon = text.indexOf(':')
    if (colon < 0) return text
    val head = text.substring(0, colon + 1)
    val words = text.substring(colon + 1).trim().split(' ')
    var previous = ""
    val out = words.mapIndexed { i, w ->
        val core = w.trim(*Punct)
        val lower = core.lowercase()
        val keep = lower in ProperWords || (lower == "santo" && previous in setOf("espíritu", "espírito"))
        previous = lower
        when {
            core.isEmpty() || !core[0].isUpperCase() || keep -> w
            else -> w.replaceFirst(core, lower)
        }
    }
    val body = out.joinToString(" ").let { b -> if (b.isNotEmpty() && b.first().isUpperCase() && words.first().trim(*Punct).lowercase() !in ProperWords) b.replaceFirstChar { it.lowercaseChar() } else b }
    return "$head $body"
}
