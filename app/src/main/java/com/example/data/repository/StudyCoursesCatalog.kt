package com.example.data.repository

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.data.liturgy.ScriptureReference
import com.example.data.models.LocalizedText
import com.example.data.models.ScriptureCourseTrack
import com.example.data.models.ScriptureLesson
import com.example.data.models.StudyCourseLevel
import com.example.data.models.StudyReviewQuestion
import com.example.data.models.StudySourceReference
import com.example.data.models.StudyTeachingSection
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.io.InputStream

internal data class RawCourseSection(val title: RawLT? = null, val body: RawLT? = null)
internal data class RawCourseQuestion(val prompt: RawLT? = null, val answer: RawLT? = null)
internal data class RawCourseSource(
    val id: String? = null, val title: RawLT? = null,
    val catechismStart: Int? = null, val catechismEnd: Int? = null, val url: RawLT? = null
)
internal data class RawCourseLesson(
    val id: String? = null, val lessonNumber: Int? = null, val title: RawLT? = null,
    val scriptureReferences: List<String>? = null, val estimatedMinutes: Int? = null,
    val objectives: List<RawLT>? = null, val summary: RawLT? = null, val historicalContext: RawLT? = null,
    val teachingSections: List<RawCourseSection>? = null,
    val lectio: RawLT? = null, val meditatio: RawLT? = null, val oratio: RawLT? = null, val contemplatio: RawLT? = null,
    val actionStep: RawLT? = null, val reviewQuestions: List<RawCourseQuestion>? = null,
    val sourceReferences: List<RawCourseSource>? = null
)
internal data class RawCourse(
    val id: String? = null, val level: String? = null, val estimatedMinutes: Int? = null, val iconName: String? = null,
    val title: RawLT? = null, val description: RawLT? = null, val objectives: List<RawLT>? = null,
    val lessons: List<RawCourseLesson>? = null
)

/**
 * Optional Bible-study courses shipped as `assets/courses/<track_id>.json`, appended after the built-in tracks.
 * Missing or malformed files, and courses whose ids collide with existing ones, are ignored (and logged).
 */
class StudyCoursesCatalog(
    private val list: () -> List<String>,
    private val open: (String) -> InputStream
) {
    companion object {
        /** Display order of the planned M8 courses; any other course follows, sorted by id. */
        val KNOWN_ORDER = listOf(
            "track_jesus_paschal", "track_salvation_history", "track_psalms_prayer", "track_paul_life_in_christ"
        )

        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: StudyCoursesCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }

        private fun classpathCourseFiles(): List<String> {
            val loader = StudyCoursesCatalog::class.java.classLoader ?: return emptyList()
            return runCatching {
                loader.getResources("").toList().filter { it.protocol == "file" }.flatMap { url ->
                    File(url.toURI()).list().orEmpty().filter { it.startsWith("track_") && it.endsWith(".json") }
                }.distinct()
            }.getOrDefault(emptyList())
        }

        private val bundled by lazy {
            StudyCoursesCatalog(
                list = {
                    assets?.let { am -> am.list("courses").orEmpty().filter { it.endsWith(".json") } }
                        ?: classpathCourseFiles()
                },
                open = { file ->
                    assets?.open("courses/$file")
                        ?: StudyCoursesCatalog::class.java.classLoader!!.getResourceAsStream(file)
                        ?: error("Course asset unavailable")
                }
            )
        }
        val default: StudyCoursesCatalog get() = override ?: bundled
    }

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter(RawCourse::class.java)

    private fun warn(name: String, why: String) { runCatching { Log.w("StudyCoursesCatalog", "Ignoring $name: $why") } }

    private fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
    private fun RawLT?.ltOrNull() = lt().takeIf { it.es.isNotBlank() || it.en.isNotBlank() || it.pt.isNotBlank() }

    private fun fileId(name: String) = name.removeSuffix(".json")
    private fun order(name: String): Pair<Int, String> {
        val i = KNOWN_ORDER.indexOf(fileId(name))
        return (if (i >= 0) i else KNOWN_ORDER.size) to fileId(name)
    }

    private fun parse(name: String): RawCourse? = try {
        open(name).bufferedReader().use { adapter.fromJson(it.readText()) }
    } catch (e: Exception) {
        AppLog.failure(LogEvent.COURSE_CATALOG,e)
        if (e !is java.io.FileNotFoundException && e.message?.contains("unavailable") != true) warn(name, e.message.orEmpty())
        null
    }

    private fun RawCourseLesson.toLesson(courseId: String): ScriptureLesson? {
        val lessonId = id?.trim()?.takeIf { it.isNotEmpty() } ?: return null
        val number = lessonNumber ?: return null
        val title = title.ltOrNull() ?: return null
        val refs = scriptureReferences.orEmpty().map { it.trim() }.filter { it.isNotEmpty() }
        if (refs.any { ScriptureReference.parse(it) == null }) return null
        return ScriptureLesson(
            id = lessonId, trackId = courseId, lessonNumber = number, title = title,
            biblicalReference = refs.joinToString(" · "),
            biblicalPassageText = LocalizedText("", "", ""),
            vaticanContext = summary.lt(),
            lectioStep = lectio.lt(), meditatioStep = meditatio.lt(), oratioStep = oratio.lt(),
            contemplatioStep = contemplatio.lt(),
            scriptureReferences = refs,
            historicalContext = historicalContext.ltOrNull(),
            teachingSections = teachingSections.orEmpty().map { StudyTeachingSection(it.title.lt(), it.body.lt()) },
            objectives = objectives.orEmpty().mapNotNull { it.ltOrNull() },
            reviewQuestions = reviewQuestions.orEmpty().map { StudyReviewQuestion(it.prompt.lt(), it.answer.lt()) },
            actionStep = actionStep.ltOrNull(),
            estimatedMinutes = estimatedMinutes,
            sourceReferences = sourceReferences.orEmpty().mapNotNull { s ->
                val sid = s.id?.trim()?.takeIf { it.isNotEmpty() } ?: return@mapNotNull null
                StudySourceReference(sid, s.title.lt(), s.catechismStart, s.catechismEnd, s.url.ltOrNull())
            }
        )
    }

    private fun RawCourse.toTrack(name: String, takenTracks: Set<String>, takenLessons: Set<String>): ScriptureCourseTrack? {
        val courseId = id?.trim()?.takeIf { it.isNotEmpty() }
        if (courseId == null) { warn(name, "missing id"); return null }
        if (courseId in takenTracks) { warn(name, "track id $courseId already exists"); return null }
        val level = runCatching { StudyCourseLevel.valueOf(level.orEmpty()) }.getOrNull()
            ?.takeIf { it != StudyCourseLevel.UNSPECIFIED }
        if (level == null) { warn(name, "invalid level"); return null }
        val title = title.ltOrNull()
        if (title == null) { warn(name, "missing title"); return null }
        val raw = lessons.orEmpty()
        if (raw.isEmpty()) { warn(name, "no lessons"); return null }
        val mapped = raw.map { it.toLesson(courseId) }
        if (mapped.any { it == null }) { warn(name, "invalid lesson (id, number, title or scripture reference)"); return null }
        val lessons = mapped.filterNotNull()
        if (lessons.map { it.lessonNumber } != (1..lessons.size).toList()) { warn(name, "lessonNumber must be 1..${lessons.size} in order"); return null }
        val ids = lessons.map { it.id }
        if (ids.toSet().size != ids.size || ids.any { it in takenLessons }) { warn(name, "duplicate lesson id"); return null }
        return ScriptureCourseTrack(
            id = courseId, title = title, description = description.lt(),
            iconName = iconName?.takeIf { it.isNotBlank() } ?: "AutoStories",
            lessons = lessons, level = level,
            objectives = objectives.orEmpty().mapNotNull { it.ltOrNull() },
            estimatedMinutes = estimatedMinutes
        )
    }

    /** Valid courses in stable order, rejecting any whose ids collide with [builtIn] or earlier courses. */
    fun tracks(builtIn: List<ScriptureCourseTrack>): List<ScriptureCourseTrack> {
        val names = runCatching { list() }.getOrDefault(emptyList()).filter { it.endsWith(".json") }.distinct().sortedWith(compareBy({ order(it).first }, { order(it).second }))
        val trackIds = builtIn.map { it.id }.toMutableSet()
        val lessonIds = builtIn.flatMap { it.lessons }.map { it.id }.toMutableSet()
        return names.mapNotNull { name ->
            val course = parse(name)?.toTrack(name, trackIds, lessonIds) ?: return@mapNotNull null
            trackIds += course.id
            lessonIds += course.lessons.map { it.id }
            course
        }
    }
}
