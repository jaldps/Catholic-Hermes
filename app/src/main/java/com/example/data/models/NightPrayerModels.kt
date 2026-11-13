package com.example.data.models

enum class NightPrayerType(val displayName: LocalizedText) {
    ROMAN_COMPLINE(
        LocalizedText(
            "Oración de la noche inspirada en Completas",
            "Night prayer inspired by Compline",
            "Oração da noite inspirada nas Completas"
        )
    ),
    IGNATIAN_EXAMEN(
        LocalizedText(
            "Examen de la Noche y Antífona Mariana",
            "Evening Examen & Marian Anthem",
            "Exame da Noite e Antífona Mariana"
        )
    )
}

data class ComplineOffice(
    val title: LocalizedText,
    val openingVerseLatin: String,
    val openingVerseTranslation: LocalizedText,
    val confiteorTitle: LocalizedText,
    val confiteorText: LocalizedText,
    val hymnLatinTitle: String,
    val hymnLatin: String,
    val hymnTranslation: LocalizedText,
    val psalmTitle: LocalizedText,
    val psalmReference: String,
    val psalmAntiphon: LocalizedText,
    val psalmText: LocalizedText,
    val readingReference: String,
    val readingText: LocalizedText,
    val responsoryLatin: String,
    val responsoryTranslation: LocalizedText,
    val canticleAntiphon: LocalizedText,
    val canticleOfSimeonLatin: String,
    val canticleOfSimeonTranslation: LocalizedText,
    val concludingPrayer: LocalizedText,
    val blessing: LocalizedText,
    val marianAntiphons: List<MarianAntiphonItem>,
    val selectionNote: LocalizedText
)

data class MarianAntiphonItem(
    val id: String,
    val titleLatin: String,
    val titleTranslation: LocalizedText,
    val seasonTag: LocalizedText,
    val latinText: String,
    val translatedText: LocalizedText
)

data class IgnatianExamenStep(
    val stepNumber: Int,
    val title: LocalizedText,
    val description: LocalizedText,
    val guidedPrayer: LocalizedText
)
