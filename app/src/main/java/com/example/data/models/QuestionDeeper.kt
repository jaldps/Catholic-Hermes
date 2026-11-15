package com.example.data.models

/** Optional "go deeper" catechist-level content for one of the 101 questions. */
data class QuestionDeeper(
    val number: Int,
    val explanation: LocalizedText,
    val scripture: LocalizedText,
    val tradition: LocalizedText,
    val magisterium: LocalizedText,
    val objection: LocalizedText,
    val analogy: LocalizedText,
    val synthesis: LocalizedText,
    val sources: List<String>
)
