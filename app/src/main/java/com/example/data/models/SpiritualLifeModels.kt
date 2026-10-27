package com.example.data.models

data class TriviaQuestion(
    val id: String,
    val category: LocalizedText,
    val question: LocalizedText,
    val options: List<LocalizedText>,
    val correctIndex: Int,
    val doctrinalExplanation: LocalizedText
)

data class FaithCourseDay(
    val dayNumber: Int,
    val title: LocalizedText,
    val scriptureRef: String,
    val scripturePassage: LocalizedText,
    val theologicalTeaching: LocalizedText,
    val reflectionQuestion: LocalizedText,
    val dailySpiritualAction: LocalizedText
)

data class FaithCourse(
    val id: String,
    val title: LocalizedText,
    val totalDays: Int,
    val description: LocalizedText,
    val category: LocalizedText,
    val days: List<FaithCourseDay>
)

data class NearbyParish(
    val id: String,
    val name: String,
    val diocese: String,
    val address: String,
    val country: String = "España",
    val stateOrRegion: String = "Madrid",
    val city: String = "Madrid",
    val latitude: Double = 40.4168,
    val longitude: Double = -3.7038,
    val distanceDisplay: String,
    val sundayMasses: String,
    val weekdayMasses: String,
    val confessionTimes: String,
    val adorationTimes: String,
    val contactPhone: String,
    val isPerpetualAdoration: Boolean = false
)

data class ViaCrucisStation(
    val stationNumber: Int,
    val title: LocalizedText,
    val scripture: LocalizedText,
    val meditation: LocalizedText,
    val prayer: LocalizedText
)

data class GregorianChantItem(
    val id: String,
    val title: String,
    val liturgicalSeason: LocalizedText,
    val description: LocalizedText,
    val latinLyrics: String,
    val translatedLyrics: LocalizedText,
    val pitchBaseHz: Float = 220f
)
