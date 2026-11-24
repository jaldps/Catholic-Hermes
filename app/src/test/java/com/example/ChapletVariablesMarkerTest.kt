package com.example

import com.example.data.models.AppLanguage
import com.example.ui.screens.ChapletVariables
import com.example.ui.screens.DeceasedGender
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

/** 1.9.4: marker parsing must not use a static Regex — ICU rejected the old pattern on the phone. */
class ChapletVariablesMarkerTest {
    @Test fun replacesKnownMarkersAndKeepsUnknownOnes() {
        val vars = ChapletVariables("Ana", DeceasedGender.FEMALE, "por la paz")
        assertEquals(
            "Ana, ella, por la paz, {{otro}}",
            vars.apply("{{nombre}}, {{él|ella}}, {{intencion}}, {{otro}}", AppLanguage.ES)
        )
    }

    @Test fun absentAnswersUseTheirNeutralFallbacks() {
        val vars = ChapletVariables()
        assertEquals("N. · él/ella · esta intención", vars.apply("{{nombre}} · {{él|ella}} · {{intencion}}", AppLanguage.ES))
    }

    @Test fun malformedMarkersAreLeftAlone() {
        val vars = ChapletVariables("Ana")
        assertEquals("{{abc y {{nombre}}", vars.apply("{{abc y {{nombre}}", AppLanguage.ES))
    }

    @Test fun noStaticRegexRemainsInTheClass() {
        val fields = ChapletVariables::class.java.declaredFields
        assertFalse("ChapletVariables must not hold a static Regex", fields.any { Regex::class.java.isAssignableFrom(it.type) })
    }
}
