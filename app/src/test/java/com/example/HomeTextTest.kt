package com.example

import com.example.data.models.AppLanguage
import com.example.ui.screens.cleanScripture
import com.example.ui.screens.clauseCut
import com.example.ui.screens.hintClause
import com.example.ui.screens.saintHeading
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

    @Test fun saintHeadingWritesOutTheAbbreviationAndSplitsTheRole() {
        val h = saintHeading("s. León Magno, papa y doctor de la Iglesia", AppLanguage.ES)
        assertEquals("San León Magno", h.title)
        assertEquals("Papa y doctor de la Iglesia", h.role)
    }

    @Test fun saintHeadingHandlesEveryHonorific() {
        assertEquals("Santa Isabel de Hungría", saintHeading("sta. Isabel de Hungría, religiosa", AppLanguage.ES).title)
        assertEquals("Santo Tomás de Aquino", saintHeading("S. Tomás de Aquino", AppLanguage.ES).title)
        assertEquals("Beato Pier Giorgio Frassati", saintHeading("b. Pier Giorgio Frassati", AppLanguage.ES).title)
        assertEquals("São Leão Magno", saintHeading("s. Leão Magno, papa", AppLanguage.PT).title)
        assertEquals("Santo Agostinho", saintHeading("s. Agostinho", AppLanguage.PT).title)
        assertEquals("St. Leo the Great", saintHeading("s. Leo the Great, pope and doctor", AppLanguage.EN).title)
        assertEquals("San Frumencio de Etiopía y Beatos Mártires", saintHeading("San Frumencio de Etiopía y Beatos Mártires", AppLanguage.ES).title)
        assertEquals("San Juan", saintHeading("san Juan", AppLanguage.ES).title)
    }

    @Test fun saintHeadingKeepsParentheticalsAndEmptyRoles() {
        val h = saintHeading("Santos Simón y Judas Tadeo (Apóstoles, mártires)", AppLanguage.ES)
        assertEquals("Santos Simón y Judas Tadeo (Apóstoles, mártires)", h.title)
        assertEquals(null, h.role)
    }
}
