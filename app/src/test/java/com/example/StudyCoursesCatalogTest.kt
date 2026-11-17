package com.example

import com.example.data.models.StudyCourseLevel
import com.example.data.repository.CatholicBibleCourseData
import com.example.data.repository.ScriptureStudyData
import com.example.data.repository.StudyCoursesCatalog
import java.io.FileNotFoundException
import org.junit.Assert.*
import org.junit.Test

private fun lt(s: String) = """{"es":"$s es","en":"$s en","pt":"$s pt"}"""

/** A complete fixture course of five lessons; [lessonPrefix] keeps ids unique between courses. */
internal fun courseFixtureJson(
    id: String = "track_fixture_course", lessonPrefix: String = "fixture", numbers: List<Int> = (1..5).toList(),
    level: String = "FOUNDATION", ids: List<String>? = null
): String {
    val lessons = numbers.mapIndexed { i, n ->
        val lid = ids?.get(i) ?: "${lessonPrefix}_$n"
        """{"id":"$lid","lessonNumber":$n,"title":${lt("Lección $n")},"scriptureReferences":["luk 1:26-38","psa 23"],
        "estimatedMinutes":20,"objectives":[${lt("Objetivo uno")},${lt("Objetivo dos")}],"summary":${lt("Síntesis $n")},
        "historicalContext":${lt("Contexto $n")},
        "teachingSections":[{"title":${lt("Desarrollo A")},"body":${lt("Cuerpo A$n")}},{"title":${lt("Desarrollo B")},"body":${lt("Cuerpo B$n")}},{"title":${lt("Desarrollo C")},"body":${lt("Cuerpo C$n")}}],
        "lectio":${lt("Lectio")},"meditatio":${lt("Meditatio")},"oratio":${lt("Oratio")},"contemplatio":${lt("Contemplatio")},
        "actionStep":${lt("Acción")},
        "reviewQuestions":[{"prompt":${lt("Pregunta 1")},"answer":${lt("Respuesta uno")}},{"prompt":${lt("Pregunta 2")},"answer":${lt("Respuesta dos")}},{"prompt":${lt("Pregunta 3")},"answer":${lt("Respuesta tres")}}],
        "sourceReferences":[{"id":"ccc_456_460","title":${lt("Catecismo")},"catechismStart":456,"catechismEnd":460,"url":null},
          {"id":"dv_11","title":${lt("Dei Verbum 11")},"url":${lt("https://www.vatican.va/dv")}}]}"""
    }
    return """{"id":"$id","level":"$level","estimatedMinutes":100,"iconName":"AutoStories","title":${lt("Curso $id")},
      "description":${lt("Descripción")},"objectives":[${lt("O1")},${lt("O2")},${lt("O3")}],"lessons":[${lessons.joinToString(",")}]}"""
}

internal fun coursesFixtureCatalog(files: Map<String, String>) = StudyCoursesCatalog(
    list = { files.keys.toList() },
    open = { name -> (files[name] ?: throw FileNotFoundException(name)).byteInputStream() }
)

class StudyCoursesCatalogTest {
    private val builtIn = listOf(CatholicBibleCourseData.track) + ScriptureStudyData.tracks

    @Test fun mapsFixtureCourseToTrackAndLessons() {
        val track = coursesFixtureCatalog(mapOf("track_fixture_course.json" to courseFixtureJson())).tracks(builtIn).single()
        assertEquals("track_fixture_course", track.id)
        assertEquals(StudyCourseLevel.FOUNDATION, track.level)
        assertEquals(100, track.estimatedMinutes)
        assertEquals("AutoStories", track.iconName)
        assertEquals(3, track.objectives.size)
        assertEquals("Descripción en", track.description.en)
        assertEquals((1..5).toList(), track.lessons.map { it.lessonNumber })
        val l = track.lessons[1]
        assertEquals("track_fixture_course", l.trackId)
        assertEquals(listOf("luk 1:26-38", "psa 23"), l.scriptureReferences)
        assertEquals("luk 1:26-38 · psa 23", l.biblicalReference)
        assertEquals("", l.biblicalPassageText.es)
        assertEquals("Síntesis 2 pt", l.vaticanContext.pt)
        assertEquals("Contexto 2 es", l.historicalContext!!.es)
        assertEquals("Lectio es", l.lectioStep.es); assertEquals("Meditatio en", l.meditatioStep.en)
        assertEquals("Oratio pt", l.oratioStep.pt); assertEquals("Contemplatio es", l.contemplatioStep.es)
        assertEquals("Acción en", l.actionStep!!.en)
        assertEquals(20, l.estimatedMinutes)
        assertEquals(2, l.objectives.size)
        assertEquals(3, l.teachingSections.size)
        assertEquals("Cuerpo B2 es", l.teachingSections[1].body.es)
        assertEquals(3, l.reviewQuestions.size)
        assertEquals("Respuesta tres en", l.reviewQuestions[2].answer.en)
        assertEquals(listOf("ccc_456_460", "dv_11"), l.sourceReferences.map { it.id })
        assertEquals(456, l.sourceReferences[0].catechismStart); assertNull(l.sourceReferences[0].url)
        assertEquals("https://www.vatican.va/dv en", l.sourceReferences[1].url!!.en)
        assertTrue(l.catechismRefs.isEmpty())
    }

    @Test fun ordersKnownCoursesFirstThenOthersById() {
        fun f(id: String, p: String) = "$id.json" to courseFixtureJson(id, p)
        val files = linkedMapOf(
            f("track_zzz", "z"), f("track_paul_life_in_christ", "p"), f("track_aaa", "a"),
            f("track_jesus_paschal", "j"), f("track_psalms_prayer", "s")
        )
        assertEquals(
            listOf("track_jesus_paschal", "track_psalms_prayer", "track_paul_life_in_christ", "track_aaa", "track_zzz"),
            coursesFixtureCatalog(files).tracks(builtIn).map { it.id }
        )
    }

    @Test fun rejectsDuplicateLessonIdsWithinAndAcrossCourses() {
        val builtInId = builtIn.flatMap { it.lessons }.first().id
        val files = mapOf(
            "track_a.json" to courseFixtureJson("track_a", "a"),
            "track_b.json" to courseFixtureJson("track_b", "a"), // collides with track_a
            "track_c.json" to courseFixtureJson("track_c", "c", ids = listOf(builtInId, "c_2", "c_3", "c_4", "c_5")),
            "track_d.json" to courseFixtureJson("track_d", "d", ids = listOf("d_1", "d_1", "d_3", "d_4", "d_5"))
        )
        assertEquals(listOf("track_a"), coursesFixtureCatalog(files).tracks(builtIn).map { it.id })
    }

    @Test fun rejectsWrongLessonNumbersTrackIdCollisionAndBadLevel() {
        val files = mapOf(
            "track_n.json" to courseFixtureJson("track_n", "n", numbers = listOf(1, 2, 4, 5, 6)),
            "track_catholic_bible.json" to courseFixtureJson("track_catholic_bible", "cb"),
            "track_l.json" to courseFixtureJson("track_l", "l", level = "EXPERT"),
            "track_ok.json" to courseFixtureJson("track_ok", "ok")
        )
        assertEquals(listOf("track_ok"), coursesFixtureCatalog(files).tracks(builtIn).map { it.id })
    }

    @Test fun malformedAndMissingFilesAreIgnored() {
        val files = mapOf(
            "track_bad.json" to "{not json", "track_empty.json" to "",
            "track_nolessons.json" to """{"id":"track_nolessons","level":"FOUNDATION","title":${lt("T")},"lessons":[]}""",
            "track_badref.json" to courseFixtureJson("track_badref", "br").replace("luk 1:26-38", "zzz nonsense"),
            "track_ok.json" to courseFixtureJson("track_ok", "ok")
        )
        val catalog = coursesFixtureCatalog(files)
        assertEquals(listOf("track_ok"), catalog.tracks(builtIn).map { it.id })
        assertTrue(coursesFixtureCatalog(emptyMap()).tracks(builtIn).isEmpty())
        assertTrue(StudyCoursesCatalog(list = { throw FileNotFoundException() }, open = { throw FileNotFoundException() }).tracks(builtIn).isEmpty())
        assertTrue(StudyCoursesCatalog(list = { listOf("track_ghost.json") }, open = { throw FileNotFoundException() }).tracks(builtIn).isEmpty())
    }
}
