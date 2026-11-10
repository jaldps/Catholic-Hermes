package com.example

import com.example.data.models.AppLanguage
import com.example.ui.screens.GuideStep
import com.example.ui.screens.nameRepeatsText
import com.example.ui.screens.sentenceCaseFruit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Fruit casing and the "title repeats the text" rule of the Rosary screen. */
class RosaryTextTest {
    @Test fun spanishFruitIsSentenceCase() {
        assertEquals(
            "Fruto: el dolor por nuestros pecados y la conformidad con la voluntad de Dios",
            sentenceCaseFruit("Fruto: El Dolor por nuestros pecados y la Conformidad con la Voluntad de Dios", AppLanguage.ES)
        )
        assertEquals(
            "Fruto: el celo apostólico y los dones del Espíritu Santo",
            sentenceCaseFruit("Fruto: El Celo apostólico y los Dones del Espíritu Santo", AppLanguage.ES)
        )
        assertEquals(
            "Fruto: la gracia de una santa muerte y devoción a María",
            sentenceCaseFruit("Fruto: La Gracia de una Santa Muerte y Devoción a María", AppLanguage.ES)
        )
    }

    @Test fun portugueseFollowsAndEnglishIsUntouched() {
        assertEquals("Fruto: amor a Deus e dons do Espírito Santo", sentenceCaseFruit("Fruto: Amor a Deus e dons do Espírito Santo", AppLanguage.PT))
        assertEquals("Fruit: Humility and Obedience to God", sentenceCaseFruit("Fruit: Humility and Obedience to God", AppLanguage.EN))
    }

    @Test fun nameRepeatingTheFirstLineIsDetected() {
        fun step(name: String, leader: String) = GuideStep(name = name, rubric = "", leader = leader, bead = 0, section = 1)
        assertTrue(nameRepeatsText(step("Por su dolorosa Pasión", "Por su dolorosa Pasión,")))
        assertFalse(nameRepeatsText(step("Ave María", "Dios te salve, María")))
    }
}
