package com.example.data.models

enum class Testament { OLD, NEW }

enum class BibleCategory(val displayName: LocalizedText) {
    PENTATEUCH(LocalizedText("Pentateuco", "Pentateuch", "Pentateuco")),
    HISTORICAL(LocalizedText("Libros Históricos", "Historical Books", "Livros Históricos")),
    WISDOM(LocalizedText("Sapienciales y Poéticos", "Wisdom & Poetry", "Sapienciais e Poéticos")),
    PROPHETS(LocalizedText("Proféticos", "Prophetic Books", "Proféticos")),
    GOSPELS(LocalizedText("Evangelios y Hechos", "Gospels & Acts", "Evangelhos e Atos")),
    EPISTLES(LocalizedText("Cartas Apostólicas", "Epistles", "Epístolas Apostólicas")),
    REVELATION(LocalizedText("Profecía", "Apocalyptic", "Profecia"))
}

data class BibleVerse(
    val number: Int,
    val text: LocalizedText,
    val commentary: LocalizedText? = null,
    val commentaryAuthor: String? = null
)

data class BibleChapter(
    val bookId: String,
    val chapterNumber: Int,
    val verses: List<BibleVerse>
)

data class BibleBook(
    val id: String,
    val name: LocalizedText,
    val abbreviation: String,
    val testament: Testament,
    val category: BibleCategory,
    val isDeuterocanonical: Boolean,
    val chaptersCount: Int,
    val summary: LocalizedText
)

data class BibleVersionInfo(
    val id: String,
    val name: String,
    val abbreviation: String,
    val language: String,
    val isCatholicCanon: Boolean,
    val description: LocalizedText,
    val canonicalDifferenceNote: LocalizedText
)

data class DailyMassReading(
    val dateDisplay: String,
    val liturgicalSeason: LocalizedText,
    val liturgicalColorHex: Long,
    val feastTitle: LocalizedText,
    val firstReadingRef: String,
    val firstReadingText: LocalizedText,
    val responsorialPsalmRef: String,
    val responsorialPsalmText: LocalizedText,
    val secondReadingRef: String?,
    val secondReadingText: LocalizedText?,
    val gospelRef: String,
    val gospelText: LocalizedText,
    val homilyReflection: LocalizedText,
    val saintOfFeast: LocalizedText,
    val sourceName: String = "Vatican News",
    val sourceUrl: String = "https://www.vaticannews.va/es/evangelio-de-hoy.html"
)
