package com.example.data.models

/** Stable persisted IDs. Families are bundled; choosing one never needs a network. */
enum class FontChoice(val id: String, val familyName: String) {
    IDENTITY_DEFAULT("IDENTITY_DEFAULT", "Identity default"),
    INTER("INTER", "Inter"),
    ROBOTO("ROBOTO", "Roboto"),
    EB_GARAMOND("EB_GARAMOND", "EB Garamond");

    fun label(language: AppLanguage): String = if (this != IDENTITY_DEFAULT) familyName else when (language) {
        AppLanguage.ES -> "Según el estilo"
        AppLanguage.EN -> "Identity default"
        AppLanguage.PT -> "Padrão do estilo"
    }

    companion object {
        /** Old serif/sans selections keep working, including in Vatican. */
        fun fromId(id: String?): FontChoice = when (id) {
            "SERIF" -> EB_GARAMOND
            "SANS" -> INTER
            else -> entries.firstOrNull { it.id == id } ?: IDENTITY_DEFAULT
        }
    }
}
