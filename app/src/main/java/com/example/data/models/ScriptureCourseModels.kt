package com.example.data.models

data class ScriptureCourseTrack(
    val id: String,
    val title: LocalizedText,
    val description: LocalizedText,
    val iconName: String,
    val lessons: List<ScriptureLesson>,
    val level: StudyCourseLevel = StudyCourseLevel.UNSPECIFIED,
    val objectives: List<LocalizedText> = emptyList(),
    /** Suggested time for the complete course, including prayer and reflection. */
    val estimatedMinutes: Int? = null
)

data class ScriptureLesson(
    val id: String,
    val trackId: String,
    val lessonNumber: Int,
    val title: LocalizedText,
    val biblicalReference: String,
    val biblicalPassageText: LocalizedText,
    val vaticanContext: LocalizedText,
    val lectioStep: LocalizedText,
    val meditatioStep: LocalizedText,
    val oratioStep: LocalizedText,
    val contemplatioStep: LocalizedText,
    val catechismRefs: List<String> = emptyList(),
    /** Canonical ScriptureReference inputs, one book per entry; never translated labels. */
    val scriptureReferences: List<String> = emptyList(),
    val historicalContext: LocalizedText? = null,
    val teachingSections: List<StudyTeachingSection> = emptyList(),
    val objectives: List<LocalizedText> = emptyList(),
    val reviewQuestions: List<StudyReviewQuestion> = emptyList(),
    /** A practical response, distinct from silent contemplatio. */
    val actionStep: LocalizedText? = null,
    val estimatedMinutes: Int? = null,
    val sourceReferences: List<StudySourceReference> = emptyList()
)

enum class StudyCourseLevel {
    UNSPECIFIED,
    FOUNDATION,
    INTERMEDIATE
}

/** An authored explanation, not a quotation from a magisterial document. */
data class StudyTeachingSection(
    val title: LocalizedText,
    val body: LocalizedText
)

/** A passage-specific understanding/reflection prompt and its explanatory answer. */
data class StudyReviewQuestion(
    val prompt: LocalizedText,
    val answer: LocalizedText
)

/**
 * A precise source target. Catechism targets use inclusive paragraph numbers;
 * other documents provide localized official URLs and a precise locator in title.
 * This metadata identifies a source; it does not imply editorial approval by it.
 */
data class StudySourceReference(
    val id: String,
    val title: LocalizedText,
    val catechismStart: Int? = null,
    val catechismEnd: Int? = null,
    val url: LocalizedText? = null
)
