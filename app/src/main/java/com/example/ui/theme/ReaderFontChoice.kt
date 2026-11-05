package com.example.ui.theme

import androidx.compose.ui.text.font.FontFamily
import com.example.data.models.AppLanguage

/** A reader follows the app font until the reader explicitly chooses an override. */
enum class ReaderFontChoice {
    FOLLOW_APP, SERIF, SANS;

    fun next(): ReaderFontChoice = when (this) {
        FOLLOW_APP -> SERIF
        SERIF -> SANS
        SANS -> FOLLOW_APP
    }

    fun label(language: AppLanguage): String = when (this) {
        FOLLOW_APP -> when (language) {
            AppLanguage.ES -> "Por defecto"
            AppLanguage.EN -> "Default"
            AppLanguage.PT -> "Padrão"
        }
        SERIF -> if (language == AppLanguage.PT) "Serifa" else "Serif"
        SANS -> if (language == AppLanguage.PT) "Sem serifa" else "Sans"
    }

    fun family(appFamily: FontFamily?, identity: DesignLanguage): FontFamily? = when (this) {
        FOLLOW_APP -> appFamily
        SERIF -> AppFonts.EbGaramond
        SANS -> if (identity == DesignLanguage.APPLE) AppFonts.Roboto else AppFonts.Inter
    }
}
