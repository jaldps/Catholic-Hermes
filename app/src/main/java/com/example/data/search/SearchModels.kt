package com.example.data.search

/** The kinds of result the global search groups by. [tag] is the segment used in the stable test tags. */
enum class SearchType(val tag: String) {
    /** A Bible or Catechism reference typed as the query ("Jn 3,16", "Sal 23", "CIC 1030"). */
    REFERENCE("reference"),
    PRAYER("prayer"),
    NOVENA("novena"),
    SAINT("saint"),
    DEVOTION("devotion"),
    QUESTION("question"),
    COURSE("course"),
    CATECHISM("catechism"),
    BIBLE("bible"),
    ORDER("order"),
    PLAN("plan"),
    SECTION("section")
}

/** Where a result opens. Always an existing screen or detail; the UI layer maps each to the app's navigation. */
sealed interface SearchTarget {
    data class Prayer(val id: String) : SearchTarget
    data class Novena(val id: String) : SearchTarget
    data class Saint(val key: String) : SearchTarget
    data class Devotion(val id: String) : SearchTarget
    /** Index into the orders list. */
    data class Order(val index: Int) : SearchTarget
    /** Index into the offices list. */
    data class Office(val index: Int) : SearchTarget
    /** 0-based index into the 101 questions. */
    data class Question(val index: Int) : SearchTarget
    data class Lesson(val lessonId: String) : SearchTarget
    data class Catechism(val paragraph: Int) : SearchTarget
    /** Chapter and verse in the numbering of the edition being read. */
    data class Bible(val bookId: String, val chapter: Int, val verse: Int?) : SearchTarget
    data class Plan(val planId: String) : SearchTarget
    /** A whole screen, named by its `AppDestination` constant. */
    data class Section(val destination: String) : SearchTarget
}

data class SearchResult(
    val type: SearchType,
    val id: String,
    val title: String,
    /** A short descriptor (saint's feast, lesson's course…). */
    val subtitle: String?,
    /** An excerpt around the match, when the match is in the body. */
    val snippet: String?,
    val target: SearchTarget
)

/** One group: the [total] matches (up to the retrieval cap) and the best [results] to show. */
data class SearchGroup(val type: SearchType, val total: Int, val results: List<SearchResult>, val capped: Boolean = false)

/** A screen of the app the search can open by title. [destination] is an `AppDestination` name. */
data class SearchSection(val destination: String, val title: String)
