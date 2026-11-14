package com.example.data.liturgy

import com.example.data.models.LocalizedText

data class SeasonalMarianPrayer(val id: String, val title: LocalizedText) {
    companion object {
        fun forDate(date: CalendarDate): SeasonalMarianPrayer =
            if (LiturgicalCalendar.seasonOf(date) == LiturgicalSeason.EASTER)
                SeasonalMarianPrayer("regina_caeli", LocalizedText("Regina Caeli", "Regina Caeli", "Regina Caeli"))
            else SeasonalMarianPrayer("angelus", LocalizedText("El Ángelus", "The Angelus", "O Angelus"))
    }
}
