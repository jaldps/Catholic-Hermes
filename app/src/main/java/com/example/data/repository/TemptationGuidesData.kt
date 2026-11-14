package com.example.data.repository

import com.example.data.models.TemptationGuide

/** Registry of the long-form temptation guides ("Más"), keyed by persisted sinKey. */
object TemptationGuidesData {
    val guides: List<TemptationGuide> by lazy { TemptationGuidesPartA.guides + TemptationGuidesPartB.guides }

    fun forKey(sinKey: String): TemptationGuide? = guides.firstOrNull { it.sinKey == sinKey }
}
