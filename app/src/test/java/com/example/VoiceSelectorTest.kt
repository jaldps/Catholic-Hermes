package com.example

import com.example.data.audio.VoiceCandidate
import com.example.data.audio.VoiceSelector
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class VoiceSelectorTest {
    private fun v(name: String, country: String = "ES", q: Int = 400, lat: Int = 200, net: Boolean = false, missing: Boolean = false, lang: String = "es") =
        VoiceCandidate(name, lang, country, q, lat, net, missing)

    private fun choose(vararg c: VoiceCandidate, l: String? = null, r: String? = null) =
        VoiceSelector.choose(c.toList(), "es", "ES", l, r)

    @Test fun prefersOfflineOverNetworkAndSkipsNotInstalled() {
        val c = choose(v("net", q = 500, net = true), v("missing", q = 500, missing = true), v("local", q = 200), v("local2", q = 100))
        assertEquals("local", c.leader); assertEquals("local2", c.response)
    }

    @Test fun prefersHigherQualityThenLowerLatency() {
        val c = choose(v("low", q = 200), v("high", q = 500, lat = 300), v("highFast", q = 500, lat = 100))
        assertEquals("highFast", c.leader); assertEquals("high", c.response)
    }

    @Test fun prefersQualityThenAppLocaleWithinSameLanguage() {
        val c = choose(v("mx", "MX", q = 500), v("es", "ES", q = 300), v("en", "US", q = 500, lang = "en"))
        assertEquals("mx", c.leader); assertEquals("es", c.response)
        val tie = choose(v("mx", "MX", q = 400), v("es", "ES", q = 400))
        assertEquals("es", tie.leader); assertEquals("mx", tie.response)
    }

    @Test fun secondVoiceIsDistinctWithNoPitchShift() {
        val c = choose(v("a"), v("b", q = 300))
        assertEquals("a", c.leader); assertEquals("b", c.response)
        assertEquals(1.0f, c.leaderPitch, 0f); assertEquals(1.0f, c.responsePitch, 0f)
    }

    @Test fun oneVoiceServesBothRolesWithMildPitch() {
        val c = choose(v("only"))
        assertEquals("only", c.leader); assertEquals("only", c.response)
        assertEquals(1.0f, c.leaderPitch, 0f); assertEquals(1.04f, c.responsePitch, 0f)
    }

    @Test fun noVoiceKeepsLanguageDefaultWithMildPitch() {
        val c = choose()
        assertNull(c.leader); assertNull(c.response)
        assertEquals(1.04f, c.responsePitch, 0f)
    }

    @Test fun storedVoiceIsUsedAndMissingStoredNameFallsBackToAuto() {
        val voices = arrayOf(v("a", q = 500), v("b", q = 400), v("c", q = 300))
        val chosen = choose(*voices, l = "c", r = "gone")
        assertEquals("c", chosen.leader); assertEquals("a", chosen.response)
        val missing = choose(*voices, l = "gone", r = "gone")
        assertEquals("a", missing.leader); assertEquals("b", missing.response)
    }

    @Test fun labelsAreReadable() {
        assertEquals("Voz 1 · España · alta calidad", VoiceSelector.label(0, v("a", q = 400), "es"))
        assertEquals("Voz 2 · México", VoiceSelector.label(1, v("b", "MX", q = 300), "es"))
    }
}
