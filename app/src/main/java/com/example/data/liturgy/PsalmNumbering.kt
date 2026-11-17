package com.example.data.liturgy

/** The two psalter numbering systems a Bible edition can use. */
enum class PsalmNumbering { HEBREW, VULGATE }

/**
 * Hebrew (modern, Catechism) to Vulgate/LXX psalm chapter numbers.
 *
 * Only chapter numbers are mapped. Verse numbers are never shifted, so for the
 * Hebrew chapters that are split or joined in the Vulgate (9, 10, 114, 115,
 * 116 and 147) a reference with verses only resolves correctly when the cited
 * verses lie in the first Vulgate chapter of the pair at the same numbers
 * (Hebrew 9:1-21, 114:1-8, 116:1-9, 147:1-11). Whole-chapter citations are
 * always exact because both Vulgate chapters are read.
 *
 * Used only on the course/lesson path; lectionary, verse-of-the-day and
 * cross-reference data carry their own (Vulgate) numbers and never go through it.
 */
object PsalmNumberingMap {
    /** Vulgate chapters that hold Hebrew psalm [hebrew] (1..150); one or two entries. */
    fun hebrewToVulgate(hebrew: Int): List<Int> = when (hebrew) {
        in 1..8, in 148..150 -> listOf(hebrew)
        9, 10 -> listOf(9)
        in 11..113 -> listOf(hebrew - 1)
        114, 115 -> listOf(113)
        116 -> listOf(114, 115)
        in 117..146 -> listOf(hebrew - 1)
        147 -> listOf(146, 147)
        else -> listOf(hebrew)
    }

    /** Editions whose psalter follows the Vulgate/LXX count: the three original Bibles and Douay-Rheims (en.drbcr, verified in its text). */
    private val vulgateEditions = setOf("bundled", "bundled-es", "bundled-en", "bundled-pt", "en.drbcr")

    /** Numbering of an edition id; unknown or null ids are treated as Hebrew-numbered (no conversion). */
    fun numberingOf(editionId: String?): PsalmNumbering =
        if (editionId in vulgateEditions) PsalmNumbering.VULGATE else PsalmNumbering.HEBREW
}
