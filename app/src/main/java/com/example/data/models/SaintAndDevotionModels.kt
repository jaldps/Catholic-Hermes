package com.example.data.models

data class SaintOfTheDay(
    val id: String,
    val name: LocalizedText,
    val feastDate: String,
    val title: LocalizedText,
    val patronages: LocalizedText,
    val biography: LocalizedText,
    val iconicQuote: LocalizedText,
    val intercessionPrayer: LocalizedText
)

data class MarianApparition(
    val id: String,
    val title: LocalizedText,
    val feastDay: LocalizedText,
    val year: String,
    val location: LocalizedText,
    val primarySanctuary: LocalizedText,
    val historyAndApparition: LocalizedText,
    val promises: LocalizedText,
    val recordedMiracles: LocalizedText,
    val venerationPrayer: LocalizedText,
    val region: String = "Americas", // "Americas", "Europe", "Asia_Africa", "Universal"
    val country: LocalizedText = LocalizedText("Universal", "Universal", "Universal"),
    val patronageOf: LocalizedText = LocalizedText("Devoción y amparo de los fieles", "Devotion and solace of the faithful", "Devoção e amparo dos fiéis")
)

data class ChristManifestation(
    val id: String,
    val title: LocalizedText,
    val feastDay: LocalizedText,
    val originHistory: LocalizedText,
    val promises: LocalizedText,
    val theologicalMeaning: LocalizedText,
    val officialPrayer: LocalizedText,
    val region: String = "Americas", // "Americas", "Europe", "Universal"
    val country: LocalizedText = LocalizedText("Universal", "Universal", "Universal"),
    val primarySanctuary: LocalizedText = LocalizedText("Santuarios y Parroquias Católicas", "Catholic Sanctuaries & Parishes", "Santuários e Paróquias Católicas"),
    val recordedMiracles: LocalizedText = LocalizedText("Incontables milagros de sanación y conversión espiritual.", "Countless miracles of physical healing and spiritual conversion.", "Incontáveis milagres de cura e conversão espiritual.")
)

data class VerseOfTheDay(
    val reference: String,
    val text: LocalizedText,
    val meditation: LocalizedText,
    val spiritualChallenge: LocalizedText
)
