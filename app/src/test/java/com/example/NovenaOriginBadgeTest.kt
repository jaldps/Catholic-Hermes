package com.example

import com.example.data.models.AppLanguage
import com.example.data.models.NovenaStatus
import com.example.ui.screens.NovenaOrigin
import com.example.ui.screens.novenaOriginBadgeLabel
import com.example.ui.screens.novenaOriginFilterLabel
import com.example.ui.screens.novenaOriginOf
import com.example.ui.screens.novenaOriginPrefix
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Provenance status -> the origin badge and the «Origen» filter, in the three languages. */
class NovenaOriginBadgeTest {
    private fun labels(status: NovenaStatus?) = AppLanguage.entries.associateWith { novenaOriginBadgeLabel(status, it) }

    @Test fun historicalAndTraditionalBothReadTradicional() {
        for (status in listOf(NovenaStatus.HISTORICAL, NovenaStatus.TRADITIONAL)) {
            assertEquals(mapOf(AppLanguage.ES to "Tradicional", AppLanguage.EN to "Traditional", AppLanguage.PT to "Tradicional"), labels(status))
        }
    }

    @Test fun modernReadsModerna() {
        assertEquals(mapOf(AppLanguage.ES to "Moderna", AppLanguage.EN to "Modern", AppLanguage.PT to "Moderna"), labels(NovenaStatus.MODERN))
    }

    @Test fun pastoralReviewReadsEnRevisionPastoral() {
        assertEquals(
            mapOf(AppLanguage.ES to "En revisión pastoral", AppLanguage.EN to "Under pastoral review", AppLanguage.PT to "Em revisão pastoral"),
            labels(NovenaStatus.PASTORAL_REVIEW)
        )
    }

    @Test fun noProvenanceShowsNoBadge() {
        assertNull(novenaOriginOf(null))
        AppLanguage.entries.forEach { assertNull(novenaOriginBadgeLabel(null, it)) }
    }

    @Test fun everyStatusMapsToAnOriginWithALabel() {
        NovenaStatus.entries.forEach { status ->
            val origin = novenaOriginOf(status)
            assertNotNull("$status", origin)
            assertFalse(origin == NovenaOrigin.ALL)
            AppLanguage.entries.forEach { assertTrue(novenaOriginBadgeLabel(status, it)!!.isNotBlank()) }
        }
    }

    @Test fun filterMatchesGroupedStatuses() {
        assertTrue(NovenaOrigin.ALL.matches(null))
        assertTrue(NovenaOrigin.ALL.matches(NovenaStatus.MODERN))
        assertTrue(NovenaOrigin.TRADITIONAL.matches(NovenaStatus.HISTORICAL))
        assertTrue(NovenaOrigin.TRADITIONAL.matches(NovenaStatus.TRADITIONAL))
        assertFalse(NovenaOrigin.TRADITIONAL.matches(NovenaStatus.MODERN))
        assertTrue(NovenaOrigin.MODERN.matches(NovenaStatus.MODERN))
        assertFalse(NovenaOrigin.MODERN.matches(NovenaStatus.PASTORAL_REVIEW))
        assertTrue(NovenaOrigin.PASTORAL_REVIEW.matches(NovenaStatus.PASTORAL_REVIEW))
        // A novena without provenance only appears under "Todas".
        listOf(NovenaOrigin.TRADITIONAL, NovenaOrigin.MODERN, NovenaOrigin.PASTORAL_REVIEW).forEach { assertFalse(it.matches(null)) }
    }

    @Test fun filterLabelsInThreeLanguages() {
        fun all(o: NovenaOrigin) = AppLanguage.entries.map { novenaOriginFilterLabel(o, it) }
        assertEquals(listOf("Todas", "All", "Todas"), all(NovenaOrigin.ALL))
        assertEquals(listOf("Tradicionales", "Traditional", "Tradicionais"), all(NovenaOrigin.TRADITIONAL))
        assertEquals(listOf("Modernas", "Modern", "Modernas"), all(NovenaOrigin.MODERN))
        assertEquals(listOf("En revisión", "Under review", "Em revisão"), all(NovenaOrigin.PASTORAL_REVIEW))
        assertEquals(listOf("Origen", "Origin", "Origem"), AppLanguage.entries.map { novenaOriginPrefix(it) })
    }
}
