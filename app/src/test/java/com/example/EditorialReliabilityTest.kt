package com.example

import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.news.DailySaint
import com.example.data.news.DailySaintsDay
import com.example.data.news.VaticanSaint
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.GuidedReviewData
import com.example.data.repository.NovenaCatalog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class EditorialReliabilityTest {
    private fun repository() = CatholicDataRepository(scope = CoroutineScope(Job().apply { cancel() }))
    @Test fun descriptiveSaintFeedFieldsNeverBecomeInventedQuotations() {
        val repo = repository()
        try {
            val bundled = repo.saintOfTheDay.copy(id = "saint_day_fixture", iconicQuote = LocalizedText("Old quote", "Old quote", "Old quote"))
            val vatican = repo.mapVaticanSaint(VaticanSaint("An unmatched saint", "A biographical description."), bundled, AppLanguage.EN)
            assertEquals("A biographical description.", vatican.biography.en)
            assertTrue(vatican.iconicQuote.en.isEmpty())
            assertNull(vatican.quoteSource)
            val feed = DailySaintsDay("11-14", "November 14", listOf(DailySaint("A different saint", "November 14", "Third century", emptyList(), "A legacy summary, not spoken words", "A biography", null, null)))
            val fallback = repo.mapDailySaints(feed, bundled)
            assertTrue(fallback.iconicQuote.en.isEmpty())
            assertTrue(fallback.patronages.en.isEmpty())
        } finally { repo.close() }
    }
    @Test fun reviewedCatalogMetadataKeepsAllDaysAndMarksPastoralContext() {
        val novenas = NovenaCatalog.default.allNovenas
        val rosary = novenas.first { it.id == "novena-del-rosario-de-54-dias" }
        assertEquals(54, rosary.days.size)
        assertFalse(rosary.description.en.contains("most powerful", true))
        val review = novenas.first { it.id == "novena-de-liberacion-de-maldiciones-ancestrales" }
        assertEquals(144, review.days.size)
        assertTrue("pastoral_review" in review.tags)
        assertTrue(review.description.en.contains("405"))
        assertEquals(246, novenas.size)
    }
    @Test fun itineraryChecksBelongToStudiedTopicsAndUseNewStableAnswerIdentities() {
        val ranges = listOf(1..40, 41..60, 61..101)
        val all = GuidedReviewData.groups.values.flatten()
        assertEquals(9, all.size)
        assertEquals(9, all.map { it.item.id }.distinct().size)
        for ((track, checks) in GuidedReviewData.groups) for (review in checks) {
            assertTrue(review.questionNumber in ranges[track])
            assertTrue(review.item.correctIndex in review.item.options.indices)
            for (language in AppLanguage.entries) {
                assertTrue(review.item.question.get(language).isNotBlank())
                assertTrue(review.item.doctrinalExplanation.get(language).contains(Regex("\\d{2,4}")))
                assertTrue(review.item.options.all { it.get(language).isNotBlank() })
            }
        }
    }
}
