package com.example.data.models

enum class RosaryMysteryType(val displayName: LocalizedText, val recommendedDays: LocalizedText) {
    JOYFUL(
        LocalizedText("Misterios Gozosos", "Joyful Mysteries", "Mistérios Gozosos"),
        LocalizedText("Lunes y Sábados", "Mondays & Saturdays", "Segundas e Sábados")
    ),
    SORROWFUL(
        LocalizedText("Misterios Dolorosos", "Sorrowful Mysteries", "Mistérios Dolorosos"),
        LocalizedText("Martes y Viernes", "Tuesdays & Fridays", "Terças e Sextas-feiras")
    ),
    GLORIOUS(
        LocalizedText("Misterios Gloriosos", "Glorious Mysteries", "Mistérios Gloriosos"),
        LocalizedText("Miércoles y Domingos", "Wednesdays & Sundays", "Quartas e Domingos")
    ),
    LUMINOUS(
        LocalizedText("Misterios Luminosos", "Luminous Mysteries", "Mistérios Luminosos"),
        LocalizedText("Jueves", "Thursdays", "Quintas-feiras")
    )
}

data class RosaryDecade(
    val number: Int,
    val title: LocalizedText,
    val scriptureRef: String,
    val fruitOfMystery: LocalizedText,
    val meditation: LocalizedText,
    val leaderPrayer: LocalizedText,
    val congregationResponse: LocalizedText
)

data class RosaryMystery(
    val type: RosaryMysteryType,
    val decades: List<RosaryDecade>
)

data class DivineMercyStep(
    val stepIndex: Int,
    val title: LocalizedText,
    val leaderText: LocalizedText,
    val responseText: LocalizedText,
    val repeatCount: Int = 1
)

data class NovenaDay(
    val dayNumber: Int,
    val theme: LocalizedText,
    val scripture: String,
    val meditation: LocalizedText,
    val prayer: LocalizedText,
    val intentionGuidance: LocalizedText
)

data class Novena(
    val id: String,
    val title: LocalizedText,
    val patron: LocalizedText,
    val category: LocalizedText,
    val description: LocalizedText,
    val miraculousHistory: LocalizedText,
    val promises: LocalizedText,
    val days: List<NovenaDay>,
    val imageUrl: String? = null
)

enum class PrayerCategory(val title: LocalizedText) {
    BASIC(LocalizedText("Oraciones Fundamentales", "Fundamental Prayers", "Orações Fundamentais")),
    MORNING_NIGHT(LocalizedText("Rutinas de la Mañana y Noche", "Morning & Night Routines", "Rotinas da Manhã e Noite")),
    MARIAN(LocalizedText("Devoción Mariana", "Marian Devotion", "Devoção Mariana")),
    EUCHARISTIC(LocalizedText("Eucarísticas y Adoración", "Eucharistic & Adoration", "Eucarísticas e Adoração")),
    HOLY_SPIRIT(LocalizedText("Al Espíritu Santo", "To the Holy Spirit", "Ao Espírito Santo")),
    PROTECTION(LocalizedText("Protección y Sanación", "Protection & Healing", "Proteção e Cura")),
    INTERCESSION(LocalizedText("Santos y Peticiones", "Saints & Petitions", "Santos e Pedidos")),
    ANGELUS(LocalizedText("El Ángelus y Regina Caeli", "The Angelus & Regina Caeli", "O Angelus e Regina Caeli"))
}

data class VaticanPrayer(
    val id: String,
    val title: LocalizedText,
    val latinTitle: String? = null,
    val category: PrayerCategory,
    val leaderText: LocalizedText,
    val responseText: LocalizedText? = null,
    val whenToPray: LocalizedText,
    val indulgenceNote: LocalizedText? = null,
    val thematicTags: List<String> = emptyList() // e.g. "health", "peace", "family", "forgiveness", "work"
)
