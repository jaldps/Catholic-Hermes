package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.DoctrineAndSpiritualData
import com.example.data.repository.ParishScheduleText
import com.example.ui.components.TextFormat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** The directory's hours must reach an English or Portuguese reader in their own language. */
class ParishScheduleTextTest {

    private val parishes = DoctrineAndSpiritualData.nearbyParishes

    @Test
    fun everyConfessionAndAdorationNoteIsRestated() {
        parishes.flatMap { listOf(it.confessionTimes, it.adorationTimes) }.forEach {
            assertTrue("no restatement for \"$it\"", ParishScheduleText.knows(it))
        }
    }

    @Test
    fun englishNotesCarryNoSpanish() {
        val spanish = Regex("""\b(Lunes|Martes|Jueves|Viernes|Sábado|Domingo|Capilla|Misa|Durante|Continuo|Cada)\b""")
        parishes.forEach { p ->
            listOf(
                ParishScheduleText.note(p.confessionTimes, AppLanguage.EN),
                ParishScheduleText.note(p.adorationTimes, AppLanguage.EN),
                ParishScheduleText.masses(p.sundayMasses, AppLanguage.EN)
            ).forEach { assertFalse("Spanish left in \"$it\"", spanish.containsMatchIn(it)) }
            assertFalse(ParishScheduleText.country(p.country, AppLanguage.EN) == "España")
        }
    }

    @Test
    fun timesFollowTheReadersClock() {
        assertEquals("7:00 AM, 5:30 PM", TextFormat.times("07:00, 17:30", AppLanguage.EN))
        assertEquals("7:00, 17:30", TextFormat.times("07:00, 17:30", AppLanguage.ES))
        assertEquals("16:00–17:00", TextFormat.times("4:00 PM - 5:00 PM", AppLanguage.PT))
        assertEquals("10:30 AM–1:00 PM", TextFormat.times("10:30 - 13:00", AppLanguage.EN))
        assertEquals("12:00 PM", TextFormat.clock(12, 0, AppLanguage.EN))
    }

    @Test
    fun referencesTakeAnEnDash() {
        assertEquals("Matthew 5:1–12", TextFormat.ranges("Matthew 5:1-12"))
        assertEquals("You shall not kill", TextFormat.withoutOrdinal("5th: You shall not kill"))
        assertEquals("No matarás", TextFormat.withoutOrdinal("5°: No matarás"))
        assertEquals("IX", TextFormat.roman(9))
    }
}
