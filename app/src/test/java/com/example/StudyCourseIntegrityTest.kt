package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.liturgy.ScriptureReference
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicBibleCourseData
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.CatechismFullData
import com.example.data.repository.ScriptureStudyData
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class StudyCourseIntegrityTest {
    @Test fun everyCitationResolvesAndSourcesExistInEveryLanguage() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext<Application>())
        try {
            val tracks = repository.scriptureStudyTracks
            val lessons = tracks.flatMap { it.lessons }
            assertEquals(lessons.size, lessons.map { it.id }.toSet().size)
            // Existing Room records must still point to the exact inherited lessons.
            assertTrue(lessons.containsAll(ScriptureStudyData.tracks.flatMap { it.lessons }))
            for (lesson in CatholicBibleCourseData.track.lessons) {
                assertEquals(CatholicBibleCourseData.track.id, lesson.trackId)
                lesson.scriptureReferences.forEach { assertNotNull(it, ScriptureReference.parse(it)) }
                for (language in AppLanguage.entries) {
                    val text = repository.studyScriptureText(lesson, language)
                    assertTrue("${lesson.id}/${language.code} has exact Scripture", text.length > 100)
                    assertTrue(lesson.historicalContext!!.get(language).isNotBlank())
                    assertTrue(lesson.teachingSections.all { it.body.get(language).length > 200 })
                    assertTrue(lesson.reviewQuestions.all { it.prompt.get(language).isNotBlank() && it.answer.get(language).isNotBlank() })
                }
                for (source in lesson.sourceReferences) {
                    val start = source.catechismStart!!
                    val end = source.catechismEnd!!
                    assertTrue(end >= start)
                    (start..end).forEach { assertNotNull("Missing CCC $it", CatechismFullData.contentByNumber[it]) }
                }
            }
        } finally { repository.close() }
    }

    @Test fun shepherdPsalmUsesIncludedEditionNumbering() {
        val repository = CatholicDataRepository(ApplicationProvider.getApplicationContext<Application>())
        try {
            val lesson = CatholicBibleCourseData.track.lessons.first { it.id == "bible_3_genre_context" }
            val expected = mapOf(AppLanguage.ES to "Yahvé es mi pastor", AppLanguage.EN to "The Lord ruleth me", AppLanguage.PT to "O Senhor me apascenta")
            expected.forEach { (language, word) ->
                assertTrue(repository.studyScriptureText(lesson, language).contains(word, ignoreCase = true))
            }
        } finally { repository.close() }
    }
}
