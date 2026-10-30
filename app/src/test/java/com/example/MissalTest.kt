package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.liturgy.CalendarDate
import com.example.data.liturgy.LectionaryData
import com.example.data.liturgy.LiturgicalCalendar
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleData
import com.example.data.repository.BibleTextProvider
import com.example.data.repository.CatholicDataRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * The daily missal used to be one hard-coded reading. These check that it now
 * follows the date, and that the citations resolve to real scripture rather
 * than to empty strings.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class MissalTest {

    private val repository =
        CatholicDataRepository(ApplicationProvider.getApplicationContext())

    @Test
    fun christmasShowsTheChristmasGospel() {
        val mass = repository.getMassReading(CalendarDate(2026, 12, 25))
        assertTrue("feast title: ${mass.feastTitle.en}", mass.feastTitle.en.contains("Nativity"))
        assertTrue("gospel ref: ${mass.gospelRef}", mass.gospelRef.contains("John"))
        assertTrue(
            "gospel text: ${mass.gospelText.en.take(80)}",
            mass.gospelText.en.startsWith("In the beginning was the Word")
        )
        assertEquals(0xFFD4AF37, mass.liturgicalColorHex)
    }

    @Test
    fun easterShowsTheEasterGospel() {
        val mass = repository.getMassReading(CalendarDate(2026, 4, 5))
        assertTrue(mass.feastTitle.en.contains("Easter"))
        assertTrue("gospel ref: ${mass.gospelRef}", mass.gospelRef.contains("John"))
        assertTrue("gospel text empty", mass.gospelText.en.isNotBlank())
    }

    @Test
    fun differentDaysGiveDifferentReadings() {
        val christmas = repository.getMassReading(CalendarDate(2026, 12, 25))
        val easter = repository.getMassReading(CalendarDate(2026, 4, 5))
        val pentecost = repository.getMassReading(CalendarDate(2026, 5, 24))

        assertNotEquals(christmas.gospelText.en, easter.gospelText.en)
        assertNotEquals(easter.gospelText.en, pentecost.gospelText.en)
        assertNotEquals(christmas.dateDisplay, easter.dateDisplay)
        assertNotEquals(christmas.liturgicalColorHex, pentecost.liturgicalColorHex)
    }

    @Test
    fun readingsFollowTheSelectedLanguage() {
        repository.applyPersistedLanguage(AppLanguage.EN)
        val english = repository.getMassReading(CalendarDate(2026, 12, 25)).gospelText.en

        repository.applyPersistedLanguage(AppLanguage.ES)
        val spanish = repository.getMassReading(CalendarDate(2026, 12, 25)).gospelText.es

        repository.applyPersistedLanguage(AppLanguage.PT)
        val portuguese = repository.getMassReading(CalendarDate(2026, 12, 25)).gospelText.pt

        repository.applyPersistedLanguage(AppLanguage.EN)

        assertTrue("Spanish gospel is English", spanish != english)
        assertTrue("Portuguese gospel is English", portuguese != english)
        assertTrue("Spanish gospel unexpected: ${spanish.take(60)}", spanish.contains("Verbo"))
    }

    /**
     * Every citation in the whole lectionary must resolve to text in all three
     * languages. A wrong book id or an out-of-range chapter would silently
     * render an empty reading, which is exactly the class of defect this work
     * set out to remove.
     */
    /**
     * Every citation in the whole lectionary must render text in all three
     * languages, through the same resolver the UI uses. A wrong book id or an
     * out-of-range chapter would silently render an empty reading, which is
     * exactly the class of defect this work set out to remove.
     */
    @Test
    fun everyLectionaryCitationRendersInEveryLanguage() {
        val provider = BibleTextProvider(ApplicationProvider.getApplicationContext())
        val resolver = ScriptureResolver(
            bookName = { bookId, language ->
                BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.name?.get(language)
            },
            versesOf = { bookId, chapter, language -> provider.getVerses(bookId, chapter, language) }
        )

        val citations = buildList {
            LectionaryData.fixed.values.forEach { entry ->
                addAll(listOfNotNull(entry.firstReading, entry.responsorialPsalm, entry.secondReading, entry.gospel))
            }
            LectionaryData.byCycle.values.forEach { cycleMap ->
                cycleMap.values.forEach { entry ->
                    addAll(listOfNotNull(entry.firstReading, entry.responsorialPsalm, entry.secondReading, entry.gospel))
                }
            }
        }.distinct()

        assertTrue("lectionary looks empty: ${citations.size} citations", citations.size > 300)

        val bookIds = BibleData.catholicCanonBooks.map { it.id }.toSet()
        val broken = mutableListOf<String>()
        for (raw in citations) {
            val reference = ScriptureReference.parse(raw)
            if (reference == null) {
                broken += "$raw -> unparseable"
                continue
            }
            if (reference.bookId !in bookIds) {
                broken += "$raw -> unknown book id '${reference.bookId}'"
                continue
            }
            for (language in listOf(AppLanguage.EN, AppLanguage.ES, AppLanguage.PT)) {
                if (resolver.passage(raw, language).isNullOrBlank()) {
                    broken += "$raw -> renders nothing in ${language.code}"
                }
            }
        }
        assertTrue(
            "citations that render nothing (${broken.size}):\n${broken.take(30).joinToString("\n")}",
            broken.isEmpty()
        )
    }

    /**
     * The Spanish and Portuguese texts rest on translations that omit two
     * deuterocanonical passages. This records exactly where, so the gap is a
     * known and bounded fact rather than a surprise: the resolver covers them
     * by falling back to English.
     */
    @Test
    fun knownDeuterocanonicalGapsAreCoveredByFallback() {
        val provider = BibleTextProvider(ApplicationProvider.getApplicationContext())
        val resolver = ScriptureResolver(
            bookName = { bookId, language ->
                BibleData.catholicCanonBooks.firstOrNull { it.id == bookId }?.name?.get(language)
            },
            versesOf = { bookId, chapter, language -> provider.getVerses(bookId, chapter, language) }
        )

        // The Canticle of the Three Young Men is absent from the Spanish and
        // Portuguese Daniel 3...
        assertEquals(30, provider.getVerses("dan", 3, AppLanguage.ES).size)
        assertEquals(100, provider.getVerses("dan", 3, AppLanguage.EN).size)

        // ...but the reading still renders, via the English fallback.
        for (language in listOf(AppLanguage.EN, AppLanguage.ES, AppLanguage.PT)) {
            assertTrue(
                "Daniel 3:52-56 renders nothing in ${language.code}",
                !resolver.passage("dan 3:52-56", language).isNullOrBlank()
            )
        }
    }

    @Test
    fun liturgicalDayFollowsTheDate() {
        val ordinary = repository.getLiturgicalDay(CalendarDate(2026, 10, 25))
        assertTrue(ordinary.celebrationTitle.en.contains("Ordinary Time"))
        assertEquals(25, ordinary.dayOfMonth)

        val ashWednesday = repository.getLiturgicalDay(CalendarDate(2026, 2, 18))
        assertTrue(ashWednesday.isFastOrAbstinence)

        val christmas = repository.getLiturgicalDay(CalendarDate(2026, 12, 25))
        assertTrue(christmas.celebrationTitle.en.contains("Nativity"))
    }

    @Test
    fun todayResolvesWithoutError() {
        val today = repository.dailyMassReading
        assertTrue("date display empty", today.dateDisplay.isNotBlank())
        assertTrue("feast title empty", today.feastTitle.en.isNotBlank())
        assertTrue("season empty", today.liturgicalSeason.en.isNotBlank())
        println(
            "today: ${today.dateDisplay} | ${today.feastTitle.en} | " +
                "gospel=${today.gospelRef.ifBlank { "(ferial, not in bundled lectionary)" }}"
        )
    }
}
