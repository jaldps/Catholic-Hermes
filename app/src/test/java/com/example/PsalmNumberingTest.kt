package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.liturgy.PsalmNumbering
import com.example.data.liturgy.PsalmNumberingMap
import com.example.data.liturgy.ScriptureReference
import com.example.data.liturgy.ScriptureResolver
import com.example.data.models.AppLanguage
import com.example.data.models.BibleVerse
import com.example.data.models.LocalizedText
import com.example.data.repository.BibleEditionCatalog
import com.example.data.repository.BibleTextProvider
import com.example.data.repository.CatholicBibleCourseData
import com.example.data.repository.CatholicDataRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** Pure Hebrew -> Vulgate mapping, label/convert rules and per-edition resolution of Hebrew-numbered course psalms. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class PsalmNumberingTest {
    @Test fun mappingTable() {
        for (n in (1..8) + (148..150)) assertEquals(listOf(n), PsalmNumberingMap.hebrewToVulgate(n))
        assertEquals(listOf(9), PsalmNumberingMap.hebrewToVulgate(9))
        assertEquals(listOf(9), PsalmNumberingMap.hebrewToVulgate(10))
        for (n in (11..113) + (117..146)) assertEquals(listOf(n - 1), PsalmNumberingMap.hebrewToVulgate(n))
        assertEquals(listOf(113), PsalmNumberingMap.hebrewToVulgate(114))
        assertEquals(listOf(113), PsalmNumberingMap.hebrewToVulgate(115))
        assertEquals(listOf(114, 115), PsalmNumberingMap.hebrewToVulgate(116))
        assertEquals(listOf(146, 147), PsalmNumberingMap.hebrewToVulgate(147))
        assertEquals(listOf(22), PsalmNumberingMap.hebrewToVulgate(23))
        assertEquals(listOf(50), PsalmNumberingMap.hebrewToVulgate(51))
        // Every Vulgate chapter 1..150 is reached exactly by the map.
        val reached = (1..150).flatMap { PsalmNumberingMap.hebrewToVulgate(it) }.toSet()
        assertEquals((1..150).toSet(), reached)
    }

    private val resolver = ScriptureResolver({ _, _ -> "Sal" }) { _, c, _ -> listOf(BibleVerse(1, LocalizedText("v$c", "v$c", "v$c"))) }

    @Test fun convertAndLabel() {
        fun label(raw: String, n: PsalmNumbering?) = resolver.display(raw, AppLanguage.ES, n)
        assertEquals("Sal 22 (21)", label("psa 22", PsalmNumbering.VULGATE))
        assertEquals("Sal 23, 1-6 (22)", label("psa 23:1-6", PsalmNumbering.VULGATE))
        assertEquals("Sal 116 (114-115)", label("psa 116", PsalmNumbering.VULGATE))
        assertEquals("Sal 22", label("psa 22", PsalmNumbering.HEBREW))
        assertEquals("Sal 22", label("psa 22", null))
        assertEquals("Sal 5", label("psa 5", PsalmNumbering.VULGATE))
        assertEquals("Sal 3, 4", resolver.display("mat 3:4", AppLanguage.ES, PsalmNumbering.VULGATE).replace("Sal", "Sal"))
        assertEquals("v114 v115", resolver.passage("psa 116", AppLanguage.EN, PsalmNumbering.VULGATE))
        assertEquals("v22", resolver.passage("psa 23", AppLanguage.EN, PsalmNumbering.VULGATE))
        assertEquals("v23", resolver.passage("psa 23", AppLanguage.EN, null))
        // Verse refs keep verse numbers and stay in the first target chapter.
        val ref = resolver.convert(ScriptureReference.parse("psa 116:1-9")!!, PsalmNumbering.VULGATE)
        assertEquals(114, ref.chapter); assertEquals(114, ref.endChapter); assertEquals(1, ref.verseStart); assertEquals(9, ref.verseEnd)
    }

    @Test fun editionNumberingTable() {
        listOf("bundled", "bundled-es", "bundled-en", "bundled-pt", "en.drbcr").forEach { assertEquals(it, PsalmNumbering.VULGATE, PsalmNumberingMap.numberingOf(it)) }
        listOf("es.eldpdd", "es.lbl", "es.lsb", "en.ccb", "pt.bs", "la.nvbse").forEach { assertEquals(it, PsalmNumbering.HEBREW, PsalmNumberingMap.numberingOf(it)) }
    }

    /** The table is verified against the asset text: where Ps 23 "shepherd" sits tells the numbering. */
    @Test fun editionTableMatchesAssetText() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val catalog = BibleEditionCatalog(context)
        val provider = BibleTextProvider(context, catalog)
        val shepherd = Regex("pastor|shepherd|ruleth me|pascit|apascenta|pastores", RegexOption.IGNORE_CASE)
        for (edition in catalog.editions) {
            val lang = AppLanguage.entries.firstOrNull { it.code == edition.language } ?: AppLanguage.EN
            fun opening(ch: Int) = provider.getVerses("psa", ch, lang, edition.id).take(2).joinToString(" ") { it.text.get(lang) }
            val vulgate = shepherd.containsMatchIn(opening(22)) && !shepherd.containsMatchIn(opening(23))
            val hebrew = shepherd.containsMatchIn(opening(23)) && !shepherd.containsMatchIn(opening(22))
            assertTrue("${edition.id} must be exactly one numbering", vulgate != hebrew)
            val expected = if (vulgate) PsalmNumbering.VULGATE else PsalmNumbering.HEBREW
            assertEquals(edition.id, expected, PsalmNumberingMap.numberingOf(edition.id))
        }
    }

    private fun withRepo(block: (CatholicDataRepository) -> Unit) {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext<Application>())
        try { block(repository) } finally { repository.close() }
    }

    private fun lesson(repo: CatholicDataRepository, id: String) =
        repo.scriptureStudyTracks.flatMap { it.lessons }.first { it.id == id }

    @Test fun shepherdPsalmInBundledEditions() = withRepo { repo ->
        val lesson = lesson(repo, "psalms_1_israel_jesus")
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.ES).contains("pastor", true))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.EN).contains("shepherd", true) || repo.studyScriptureText(lesson, AppLanguage.EN).contains("ruleth me", true))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.PT).contains("pastor", true) || repo.studyScriptureText(lesson, AppLanguage.PT).contains("apascenta", true))
        assertEquals("Salmos 23 (22)".substringAfter(' '), repo.studyScriptureLabel("psa 23", AppLanguage.ES).substringAfter(' '))
        assertTrue(repo.studyScriptureLabel("psa 23", AppLanguage.EN).endsWith("23 (22)"))
        assertTrue(repo.studyScriptureLabel("psa 23", AppLanguage.PT).endsWith("23 (22)"))
    }

    @Test fun miserereInBundledEditions() = withRepo { repo ->
        val lesson = lesson(repo, "psalms_4_penance")
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.ES).contains("misericordia", true))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.EN).contains("Have mercy on me", true))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.PT).contains("Tem piedade", true))
        assertTrue(repo.studyScriptureLabel("psa 51", AppLanguage.EN).endsWith("51 (50)"))
    }

    @Test fun lamentPsalmTwentyTwoIsHebrewTwentyTwo() = withRepo { repo ->
        val lesson = lesson(repo, "psalms_3_lament")
        // Hebrew 22 is Vulgate 21: "Deus, Deus meus, respice in me" / "O God, my God, look upon me".
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.EN).contains("look upon me", true))
        assertTrue(repo.studyScriptureLabel("psa 22", AppLanguage.EN).endsWith("22 (21)"))
    }

    @Test fun hebrewEditionNeedsNoConversion() = withRepo { repo ->
        val lesson = lesson(repo, "psalms_1_israel_jesus")
        val text = repo.studyScriptureText(lesson, AppLanguage.ES, "es.lbl")
        assertTrue(text, text.contains("El Señor es mi pastor"))
        assertFalse(repo.studyScriptureLabel("psa 23", AppLanguage.ES, "es.lbl").contains("("))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.EN, "en.ccb").contains("The Lord is my shepherd"))
        // Douay-Rheims alternate transcription is Vulgate-numbered and converts.
        val drb = repo.studyScriptureText(lesson, AppLanguage.EN, "en.drbcr")
        assertTrue(drb, drb.contains("ruleth me") && drb.contains("23 (22)"))
        val miserere = repo.studyScriptureText(lesson(repo, "psalms_4_penance"), AppLanguage.ES, "es.lbl")
        assertTrue(miserere, miserere.contains("Ten piedad de mí"))
    }

    @Test fun courseALessonThreeIsShepherdPsalm() = withRepo { repo ->
        val lesson = CatholicBibleCourseData.track.lessons.first { it.id == "bible_3_genre_context" }
        assertEquals(listOf("psa 23:1-6"), lesson.scriptureReferences)
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.ES).contains("Yahvé es mi pastor"))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.ES).contains("23, 1-6 (22)"))
        assertTrue(repo.studyScriptureText(lesson, AppLanguage.EN, "es.lbl").isNotEmpty())
    }

    @Test fun everyPsalmsCourseLessonResolvesInEveryLanguage() = withRepo { repo ->
        val lessons = repo.scriptureStudyTracks.first { it.id == "track_psalms_prayer" }.lessons
        for (l in lessons) for (lang in AppLanguage.entries) assertTrue("${l.id}/${lang.code}", repo.studyScriptureText(l, lang).length > 100)
    }
}
