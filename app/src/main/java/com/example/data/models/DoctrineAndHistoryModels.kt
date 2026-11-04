package com.example.data.models

data class CatechismPillar(
    val pillarNumber: Int,
    val title: LocalizedText,
    val description: LocalizedText,
    val keyDoctrines: List<CatechismArticle>
)

data class CatechismArticle(
    val id: String,
    val cccNumberRef: String,
    val title: LocalizedText,
    val summary: LocalizedText,
    val fullExplanation: LocalizedText,
    val biblicalBasis: String
)

data class CommandmentExploration(
    val number: Int,
    val title: LocalizedText,
    val biblicalWording: LocalizedText,
    val moralExplanation: LocalizedText,
    val sinsForbidden: LocalizedText,
    val virtuesCommanded: LocalizedText
)

data class ChurchPrecept(
    val number: Int,
    val title: LocalizedText,
    val explanation: LocalizedText,
    val applicationInLife: LocalizedText
)

data class CapitalSinVsVirtue(
    val capitalSin: LocalizedText,
    val sinMeaning: LocalizedText,
    val opposingVirtue: LocalizedText,
    val virtueRemedy: LocalizedText,
    val practicalAdvice: LocalizedText
)

data class SinTheologyExplanation(
    val venialExplanation: LocalizedText,
    val mortalExplanation: LocalizedText,
    val mortalThreeConditions: List<LocalizedText>,
    val confessionExamenGuide: LocalizedText
)

data class ChurchHistoryEpoch(
    val epochId: String,
    val title: LocalizedText,
    val centuryRange: String,
    val narrative: LocalizedText,
    val keyPopes: LocalizedText,
    val ecumenicalCouncils: LocalizedText,
    val historicalSaints: LocalizedText
)

data class ChurchSchismHistory(
    val id: String,
    val title: LocalizedText,
    val historicalYear: String,
    val context: LocalizedText,
    val theologicalDifferences: LocalizedText,
    val catholicPerspective: LocalizedText,
    val dialogueAndReconciliation: LocalizedText
)

data class CatholicNewsArticle(
    val id: String,
    val title: LocalizedText,
    val source: String,
    val date: String,
    val category: LocalizedText,
    val summary: LocalizedText,
    val readTimeMinutes: Int
)

/** One chapter (1-16) of the Didache, in all three languages. */
data class DidacheChapter(
    val number: Int,
    val heading: LocalizedText,
    val body: LocalizedText
)
