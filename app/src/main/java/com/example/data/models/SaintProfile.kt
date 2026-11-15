package com.example.data.models

/** Per-language list, e.g. patronages. */
data class LocalizedList(val es: List<String>, val en: List<String>, val pt: List<String>) {
    fun get(lang: AppLanguage): List<String> = when (lang) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
}

/** A verified quotation; shown only with its [source]. */
data class SaintQuote(val text: LocalizedText, val source: String)

/** Editorial profile of a General Roman Calendar celebration (see docs/content/M5-SANTOS-SPEC.md). */
data class SaintProfile(
    val key: String,
    val month: Int,
    val day: Int,
    val bio: LocalizedText,
    val patronages: LocalizedList,
    val quote: SaintQuote?,
    val sources: List<String>
)
