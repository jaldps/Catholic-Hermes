package com.example.data.models

enum class PromiseKind { SOURCED, ECCLESIAL_GRANT, TRADITIONAL, NONE;
    companion object {
        fun parse(raw: String?): PromiseKind? =
            entries.firstOrNull { it.name.equals(raw?.trim()?.replace('-', '_')?.replace(' ', '_'), ignoreCase = true) }
    }
}

enum class NovenaStatus { HISTORICAL, TRADITIONAL, MODERN, PASTORAL_REVIEW;
    companion object {
        fun parse(raw: String?): NovenaStatus? =
            entries.firstOrNull { it.name.equals(raw?.trim()?.replace('-', '_')?.replace(' ', '_'), ignoreCase = true) }
    }
}

data class NovenaPromise(
    val kind: PromiseKind,
    val text: LocalizedText,
    val attribution: LocalizedText,
    val source: LocalizedText,
    val conditions: LocalizedText
)

data class NovenaProvenance(
    val id: String,
    val status: NovenaStatus,
    val origin: LocalizedText,
    val history: LocalizedText,
    val approval: LocalizedText,
    val promise: NovenaPromise,
    val commercialAdditions: LocalizedText,
    val pastoralNote: LocalizedText,
    val sources: List<String>
)
