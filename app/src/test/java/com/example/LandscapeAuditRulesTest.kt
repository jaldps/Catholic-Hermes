package com.example

import androidx.compose.ui.geometry.Rect
import org.junit.Assert.*
import org.junit.Test

class LandscapeAuditRulesTest {
    @Test fun everyDetectorRecognizesBothAnActualViolationAndAValidCase() {
        assertTrue(LandscapeAuditRules.headerTooTall(236f, 360f)); assertFalse(LandscapeAuditRules.headerTooTall(108f, 360f))
        val safe = Rect(32f, 24f, 752f, 344f)
        assertTrue(LandscapeAuditRules.underInset(Rect(10f, 32f, 70f, 76f), safe)); assertFalse(LandscapeAuditRules.underInset(Rect(48f, 32f, 92f, 76f), safe))
        assertTrue(LandscapeAuditRules.hiddenByIme(Rect(400f, 240f, 600f, 284f), 360f, 202f)); assertFalse(LandscapeAuditRules.hiddenByIme(Rect(400f, 40f, 600f, 84f), 360f, 202f))
        assertTrue(LandscapeAuditRules.outsideMeasure(100f)); assertFalse(LandscapeAuditRules.outsideMeasure(66f))
        val sheet = Rect(392f, 32f, 792f, 336f)
        assertTrue(LandscapeAuditRules.wrongSheet(Rect(0f, 24f, 800f, 360f), sheet)); assertFalse(LandscapeAuditRules.wrongSheet(sheet, sheet))
        assertTrue(LandscapeAuditRules.obscures(Rect(100f, 100f, 200f, 144f), Rect(120f, 120f, 300f, 160f)))
        assertFalse(LandscapeAuditRules.obscures(Rect(100f, 100f, 200f, 144f), Rect(220f, 120f, 300f, 160f)))
        assertEquals(listOf("next"), LandscapeAuditRules.belowFold(listOf("next", "back"), setOf("back")))
        assertTrue(LandscapeAuditRules.belowFold(emptyList(), emptySet()).isEmpty())
    }
}
