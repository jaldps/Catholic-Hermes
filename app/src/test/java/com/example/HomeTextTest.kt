package com.example

import com.example.data.models.AppLanguage
import com.example.ui.screens.cleanScripture
import com.example.ui.screens.clauseCut
import com.example.ui.screens.hintClause
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Home's text helpers: one real ellipsis character, no doubled guillemets, one-line hints. */
class HomeTextTest {

    @Test fun threeDotsBecomeTheRealEllipsis() {
        val t = cleanScripture("«Quién de ustedes pastorea los rebaños...»", AppLanguage.ES)
        assertTrue(t.contains("…"))
        assertFalse(t.contains("..."))
    }

    @Test fun clauseCutUsesTheRealEllipsisAndNeverEndsMidWord() {
        val cut = clauseCut("En aquel tiempo, Jesús dijo a sus apóstoles: quién de ustedes tiene un siervo", 40)
        assertTrue(cut.endsWith("…"))
        assertFalse(cut.contains("..."))
        assertTrue(cut.dropLast(1).let { it.isEmpty() || it.last().let { c -> c.isLetter() || c == ':' || c == ',' || c == ';' } })
    }

    @Test fun cleanScriptureCollapsesDoubledGuillemetsAndSpaces() {
        assertEquals("En aquel tiempo del esposo.", cleanScripture("««En aquel tiempo del esposo .»", AppLanguage.ES))
    }

    @Test fun hintFitsOneLineWithoutEllipsis() {
        val h = hintClause("Hoy relee este versículo despacio tres veces, deteniéndote entre cada lectura, y rézalo como tuyo.")
        assertEquals("Hoy relee este versículo despacio tres veces.", h)
    }
}
