package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.OnboardingCatalog
import com.example.data.repository.OnboardingOrder
import com.example.data.repository.OnboardingSuggestion
import com.example.data.models.LocalizedText
import com.example.ui.AppDestination
import com.example.ui.screens.forYouItems
import com.example.ui.screens.forYouOnHome
import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayInputStream

/** The reviewed welcome profiles (T-033) and the pure ordering that «Para ti hoy» applies to them. */
class OnboardingCatalogTest {
    private val catalog get() = OnboardingCatalog { javaClass.classLoader!!.getResourceAsStream(OnboardingCatalog.FILE)!! }
    private fun profiles() = requireNotNull(catalog.load())
    private fun ids(list: List<OnboardingSuggestion>) = list.map { it.id }

    @Test fun reviewedAssetLoadsThreeProfilesWithValidDestinationsInThreeLanguages() {
        val data = profiles()
        assertEquals(listOf("exploring", "returning", "practicing"), data.map { it.id })
        assertEquals(listOf(0, 1, 2), data.map { it.index })
        val destinations = AppDestination.entries.map { it.name }.toSet()
        data.forEach { p ->
            assertTrue(p.suggestions.size in 6..7)
            assertEquals(setOf(2, 5, 10), p.suggestions.map { it.minutes }.toSet())
            AppLanguage.entries.forEach { l ->
                assertTrue(p.welcome.get(l).isNotBlank() && p.description.get(l).isNotBlank())
                assertTrue(p.suggestions.all { it.title.get(l).isNotBlank() && it.why.get(l).isNotBlank() })
            }
            p.suggestions.forEach { assertTrue("${it.id}: ${it.destination}", it.destination in destinations) }
        }
        assertEquals(data.flatMap { it.suggestions }.size, data.flatMap { it.suggestions }.map { it.id }.distinct().size)
    }

    @Test fun absorbedDestinationsStillResolveToTheirMyPathSection() {
        val all = profiles().flatMap { it.suggestions }.map { AppDestination.valueOf(it.destination) }
        val absorbed = all.filter { AppDestination.myPathSectionFor(it) != null }
        assertTrue("HABITS_VIRTUES and SPIRITUAL_LIFE are used by the practicing profile", absorbed.containsAll(listOf(AppDestination.HABITS_VIRTUES, AppDestination.SPIRITUAL_LIFE)))
    }

    @Test fun malformedOrUnavailableContentReturnsNullWithoutInventedText() {
        assertNull(OnboardingCatalog { ByteArrayInputStream("{}".toByteArray()) }.load())
        assertNull(OnboardingCatalog { ByteArrayInputStream("not json".toByteArray()) }.load())
        assertNull(OnboardingCatalog { throw java.io.IOException("unavailable") }.load())
        val badMinutes = String(javaClass.classLoader!!.getResourceAsStream(OnboardingCatalog.FILE)!!.readBytes()).replaceFirst("\"minutes\": 2", "\"minutes\": 3")
        assertNull(OnboardingCatalog { ByteArrayInputStream(badMinutes.toByteArray()) }.load())
        val missingLanguage = String(javaClass.classLoader!!.getResourceAsStream(OnboardingCatalog.FILE)!!.readBytes()).replaceFirst(Regex("\"pt\": \"[^\"]+\""), "\"pt\": \"\"")
        assertNull(OnboardingCatalog { ByteArrayInputStream(missingLanguage.toByteArray()) }.load())
    }

    @Test fun aSuccessfulLoadIsKeptAndAFailedOneStaysRetryable() {
        var broken = true
        val retry = OnboardingCatalog { if (broken) throw java.io.IOException("not yet") else javaClass.classLoader!!.getResourceAsStream(OnboardingCatalog.FILE)!! }
        assertNull(retry.load()); assertNull(retry.loaded)
        broken = false
        assertEquals(3, retry.load()?.size); assertEquals(3, retry.loaded?.size)
    }

    private fun s(id: String, minutes: Int) = OnboardingSuggestion(id, "HOME", minutes, LocalizedText("a", "a", "a"), LocalizedText("b", "b", "b"))

    @Test fun orderingPutsTheChosenTimeFirstThenShorterThenLongerWithoutDroppingAny() {
        val input = listOf(s("a2", 2), s("b2", 2), s("c5", 5), s("d5", 5), s("e10", 10), s("f10", 10))
        assertEquals(listOf("e10", "f10", "c5", "d5", "a2", "b2"), ids(OnboardingOrder.order(input, 10)))
        assertEquals(listOf("c5", "d5", "a2", "b2", "e10", "f10"), ids(OnboardingOrder.order(input, 5)))
        assertEquals(listOf("a2", "b2", "c5", "d5", "e10", "f10"), ids(OnboardingOrder.order(input, 2)))
        // A time nobody offers: the nearest shorter ones lead, then the longer ones.
        assertEquals(listOf("c5", "d5", "a2", "b2", "e10", "f10"), ids(OnboardingOrder.order(input, 7)))
        assertEquals(input.size, OnboardingOrder.order(input, 10).size)
        assertTrue(OnboardingOrder.order(emptyList(), 5).isEmpty())
    }

    @Test fun everyProfileKeepsAllItsSuggestionsForEveryTime() {
        profiles().forEach { p -> listOf(2, 5, 10).forEach { m ->
            val ordered = OnboardingOrder.order(p.suggestions, m)
            assertEquals(p.suggestions.map { it.id }.toSet(), ids(ordered).toSet())
            val matching = p.suggestions.count { it.minutes == m }
            assertTrue(ordered.take(matching).all { it.minutes == m })
        } }
    }

    @Test fun returningWithTenMinutesLeadsWithTenThenFiveThenTwo() {
        val returning = profiles().single { it.id == "returning" }
        assertEquals(
            listOf("returning_home", "returning_saint", "returning_confession", "returning_rosary", "returning_gospel", "returning_our_father"),
            ids(OnboardingOrder.order(returning.suggestions, 10))
        )
    }

    @Test fun homeShowsAtMostThreeAndNeverPointsAtItself() {
        val returning = forYouItems(profiles().single { it.id == "returning" }, 10)
        assertEquals(6, returning.size)
        assertEquals(listOf("returning_saint", "returning_confession", "returning_rosary"), forYouOnHome(returning).map { it.suggestion.id })
        val practicing = forYouItems(profiles().single { it.id == "practicing" }, 5)
        assertEquals(listOf("practicing_rosary", "practicing_catechism", "practicing_habits"), forYouOnHome(practicing).map { it.suggestion.id })
        assertEquals(7, practicing.size)
        assertTrue(forYouItems(null, 5).isEmpty())
    }
}
