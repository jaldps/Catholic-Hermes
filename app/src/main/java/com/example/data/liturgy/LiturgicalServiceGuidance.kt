package com.example.data.liturgy

import com.example.data.models.LocalizedText

/** Civil dates do not imply that an evening liturgy has already begun. */
object LiturgicalServiceGuidance {
    fun forDate(date: CalendarDate): LocalizedText? {
        val easter = LiturgicalCalendar.easter(date.year)
        return when (date) {
            easter.plusDays(-3) -> LocalizedText(
                "La Misa vespertina de la Cena del Señor inaugura el Triduo Pascual. La Misa Crismal tiene lecturas propias y puede celebrarse en otra fecha. Comprueba la celebración y el horario de tu parroquia.",
                "The evening Mass of the Lord’s Supper begins the Paschal Triduum. The Chrism Mass has its own readings and may take place on another date. Check your parish’s celebration and schedule.",
                "A Missa vespertina da Ceia do Senhor inicia o Tríduo Pascal. A Missa Crismal tem leituras próprias e pode ocorrer em outra data. Confira a celebração e o horário de sua paróquia."
            )
            easter.plusDays(-1) -> LocalizedText(
                "Sábado Santo: no se celebra Misa durante el día. La Vigilia Pascual, con vestiduras blancas, se celebra después de caer la noche; sus lecturas corresponden a esa celebración nocturna. Comprueba el horario parroquial.",
                "Holy Saturday: no Mass is celebrated during the day. The Easter Vigil, with white vestments, takes place after nightfall; its readings belong to that night’s celebration. Check your parish schedule.",
                "Sábado Santo: não se celebra Missa durante o dia. A Vigília Pascal, com paramentos brancos, acontece depois de anoitecer; suas leituras pertencem a essa celebração noturna. Confira o horário paroquial."
            )
            else -> null
        }
    }
}
