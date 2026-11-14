package com.example.data.models

/** Optional editorial details of a prayer. Blank LocalizedText fields mean "absent". */
data class PrayerDetails(
    val prayerId: String,
    val history: LocalizedText,
    val instructions: LocalizedText,
    val source: LocalizedText,
    val approval: LocalizedText,
    val promise: NovenaPromise?,
    val textNote: LocalizedText
)
