package com.example.data.repository

import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText

/**
 * The bundled directory records each parish's confession and adoration hours,
 * and the notes on its Mass times, as the parish publishes them — in Spanish,
 * Portuguese, Italian, French or English. Shown raw, an English reader met
 * "Lunes a Domingo" and "Capilla del Santísimo". This restates every one of
 * those notes in the reader's language. Times stay on the 24-hour clock here;
 * the screen sets them in the reader's convention.
 */
object ParishScheduleText {

    private fun t(es: String, en: String, pt: String) = LocalizedText(es, en, pt)

    /** Whole notes, keyed by the directory's own wording. */
    private val notes: Map<String, LocalizedText> = mapOf(
        "30 min antes de cada Santa Misa" to t("30 min antes de cada misa", "30 min before every Mass", "30 min antes de cada missa"),
        "Adoración Perpetua al Santísimo en Capilla de San Juan de Dios" to t("Adoración perpetua, capilla de San Juan de Dios", "Perpetual adoration, Chapel of St John of God", "Adoração perpétua, capela de São João de Deus"),
        "Adoración permanente ante el Santísimo" to t("Adoración permanente", "Continuous adoration", "Adoração permanente"),
        "Adoração Perpétua ao Santíssimo Sacramento" to t("Adoración perpetua", "Perpetual adoration", "Adoração perpétua"),
        "Cappella Corsini: 09:00 - 18:00" to t("Capilla Corsini: 09:00–18:00", "Corsini Chapel: 09:00–18:00", "Capela Corsini: 09:00–18:00"),
        "Cappella del Santissimo Sacramento: 08:30 - 18:45" to t("Capilla del Santísimo: 08:30–18:45", "Blessed Sacrament Chapel: 08:30–18:45", "Capela do Santíssimo: 08:30–18:45"),
        "Chapelle de la Réconciliation: 08:00 à 19:00" to t("Capilla de la Reconciliación: 08:00–19:00", "Chapel of Reconciliation: 08:00–19:00", "Capela da Reconciliação: 08:00–19:00"),
        "Chapelle du Très Saint-Sacrement: 09:00 - 18:00" to t("Capilla del Santísimo: 09:00–18:00", "Blessed Sacrament Chapel: 09:00–18:00", "Capela do Santíssimo: 09:00–18:00"),
        "Confesionario Central: Continuo de 07:00 a 19:30" to t("Confesionario central: sin interrupción, 07:00–19:30", "Main confessional: continuously, 07:00–19:30", "Confessionário central: contínuo, 07:00–19:30"),
        "Continuo en el Claustro de la Reconciliación" to t("Sin interrupción, en el claustro de la Reconciliación", "Continuously, in the Cloister of Reconciliation", "Contínuo, no claustro da Reconciliação"),
        "Continuo nel braccio confessionale in 10 lingue" to t("Sin interrupción, en 10 idiomas", "Continuously, in 10 languages", "Contínuo, em 10 idiomas"),
        "Daily Adoration in Blessed Sacrament Chapel" to t("Todos los días, en la capilla del Santísimo", "Daily, in the Blessed Sacrament Chapel", "Todos os dias, na capela do Santíssimo"),
        "De 09:00 a 13:00 y de 17:00 a 19:00" to t("09:00–13:00 y 17:00–19:00", "09:00–13:00 and 17:00–19:00", "09:00–13:00 e 17:00–19:00"),
        "De 10:00 a 12:00 y antes de cada Misa" to t("10:00–12:00 y antes de cada misa", "10:00–12:00 and before every Mass", "10:00–12:00 e antes de cada missa"),
        "De 10:00 a 12:30 y de 16:30 a 18:30" to t("10:00–12:30 y 16:30–18:30", "10:00–12:30 and 16:30–18:30", "10:00–12:30 e 16:30–18:30"),
        "Lady Chapel: 08:00 AM - 06:00 PM" to t("Capilla de la Virgen: 08:00–18:00", "Lady Chapel: 08:00–18:00", "Capela de Nossa Senhora: 08:00–18:00"),
        "Martes a Viernes de 09:00 a 11:30" to t("Martes a viernes, 09:00–11:30", "Tuesday to Friday, 09:00–11:30", "Terça a sexta, 09:00–11:30"),
        "Martes y Jueves de 10:00 a 12:00" to t("Martes y jueves, 10:00–12:00", "Tuesdays and Thursdays, 10:00–12:00", "Terças e quintas, 10:00–12:00"),
        "Martes y Jueves de 16:00 a 18:00" to t("Martes y jueves, 16:00–18:00", "Tuesdays and Thursdays, 16:00–18:00", "Terças e quintas, 16:00–18:00"),
        "Monday - Friday: 11:00 AM - 12:00 PM" to t("Lunes a viernes, 11:00–12:00", "Monday to Friday, 11:00–12:00", "Segunda a sexta, 11:00–12:00"),
        "Monday - Friday: 11:30 AM - 12:00 PM; Saturdays 3:30 - 5:30 PM" to t("Lunes a viernes, 11:30–12:00; sábados, 15:30–17:30", "Monday to Friday, 11:30–12:00; Saturdays, 15:30–17:30", "Segunda a sexta, 11:30–12:00; sábados, 15:30–17:30"),
        "Permanente durante todas las Misas y mañanas de 10:00 a 13:00" to t("Durante todas las misas y por la mañana, 10:00–13:00", "During every Mass, and mornings 10:00–13:00", "Durante todas as missas e de manhã, 10:00–13:00"),
        "Perpetual Adoration Chapel (24/7 with Keycode)" to t("Capilla de adoración perpetua (día y noche, con código)", "Perpetual adoration chapel (day and night, with keycode)", "Capela de adoração perpétua (dia e noite, com código)"),
        "Saturdays 4:00 PM - 5:00 PM or by appointment" to t("Sábados, 16:00–17:00, o con cita previa", "Saturdays, 16:00–17:00, or by appointment", "Sábados, 16:00–17:00, ou com marcação"),
        "Sábados y Domingos antes de la Misa" to t("Sábados y domingos, antes de misa", "Saturdays and Sundays, before Mass", "Sábados e domingos, antes da missa"),
        "Tente de l'Adoration: Adoration Perpétua 24h/24" to t("Carpa de la Adoración: adoración perpetua, día y noche", "Adoration Tent: perpetual adoration, day and night", "Tenda da Adoração: adoração perpétua, dia e noite"),
        "Terça a Sexta: 14:30 às 17:00" to t("Martes a viernes, 14:30–17:00", "Tuesday to Friday, 14:30–17:00", "Terça a sexta, 14:30–17:00"),
        "Tous les jours de 10:00 à 12:30 et de 15:00 à 18:30" to t("Todos los días, 10:00–12:30 y 15:00–18:30", "Daily, 10:00–12:30 and 15:00–18:30", "Todos os dias, 10:00–12:30 e 15:00–18:30"),
        "Tutti i giorni dalle 07:30 alle 12:30 e dalle 16:00 alle 18:30" to t("Todos los días, 07:30–12:30 y 16:00–18:30", "Daily, 07:30–12:30 and 16:00–18:30", "Todos os dias, 07:30–12:30 e 16:00–18:30"),
        "Capela da Reconciliação: 06:00 às 18:00 contínuo" to t("Capilla de la Reconciliación: sin interrupción, 06:00–18:00", "Chapel of Reconciliation: continuously, 06:00–18:00", "Capela da Reconciliação: contínuo, 06:00–18:00"),
        "Capela da Reconciliação: 07:30 às 19:30 contínuo" to t("Capilla de la Reconciliación: sin interrupción, 07:30–19:30", "Chapel of Reconciliation: continuously, 07:30–19:30", "Capela da Reconciliação: contínuo, 07:30–19:30"),
        "Capela do Santíssimo Sacramento: 10:00 - 17:30" to t("Capilla del Santísimo: 10:00–17:30", "Blessed Sacrament Chapel: 10:00–17:30", "Capela do Santíssimo: 10:00–17:30"),
        "Capela do Santíssimo Sacramento: Adoração Perpétua (24h)" to t("Capilla del Santísimo: adoración perpetua, día y noche", "Blessed Sacrament Chapel: perpetual adoration, day and night", "Capela do Santíssimo: adoração perpétua, dia e noite"),
        "Capela do Santíssimo Sacramento: Adoração Perpétua" to t("Capilla del Santísimo: adoración perpetua", "Blessed Sacrament Chapel: perpetual adoration", "Capela do Santíssimo: adoração perpétua"),
        "Capela do Santíssimo: 08:00 às 18:00" to t("Capilla del Santísimo: 08:00–18:00", "Blessed Sacrament Chapel: 08:00–18:00", "Capela do Santíssimo: 08:00–18:00"),
        "Capilla Real: 09:00 - 19:00" to t("Capilla Real: 09:00–19:00", "Royal Chapel: 09:00–19:00", "Capela Real: 09:00–19:00"),
        "Capilla de Adoración Franciscana Continua" to t("Capilla franciscana de adoración continua", "Franciscan chapel of continuous adoration", "Capela franciscana de adoração contínua"),
        "Capilla de Adoración Perpetua (24 Horas / 365 Días)" to t("Capilla de adoración perpetua, día y noche, todo el año", "Perpetual adoration chapel, day and night, all year", "Capela de adoração perpétua, dia e noite, o ano todo"),
        "Capilla de la Comunión: Adoración Perpetua" to t("Capilla de la Comunión: adoración perpetua", "Communion Chapel: perpetual adoration", "Capela da Comunhão: adoração perpétua"),
        "Capilla de la Soledad: Continuo de 08:30 a 19:30" to t("Capilla de la Soledad: sin interrupción, 08:30–19:30", "Chapel of Our Lady of Solitude: continuously, 08:30–19:30", "Capela da Soledade: contínuo, 08:30–19:30"),
        "Capilla del Sagrario: 09:00 - 17:00" to t("Capilla del Sagrario: 09:00–17:00", "Tabernacle Chapel: 09:00–17:00", "Capela do Sacrário: 09:00–17:00"),
        "Capilla del Sagrario: 09:00 - 18:00" to t("Capilla del Sagrario: 09:00–18:00", "Tabernacle Chapel: 09:00–18:00", "Capela do Sacrário: 09:00–18:00"),
        "Capilla del Santo Cáliz: 10:00 - 18:00" to t("Capilla del Santo Cáliz: 10:00–18:00", "Chapel of the Holy Chalice: 10:00–18:00", "Capela do Santo Cálice: 10:00–18:00"),
        "Capilla del Santísimo en la Cripta: 09:30 - 19:00" to t("Capilla del Santísimo, en la cripta: 09:30–19:00", "Blessed Sacrament Chapel, in the crypt: 09:30–19:00", "Capela do Santíssimo, na cripta: 09:30–19:00"),
        "Capilla del Santísimo y Adoración Nocturna Permanente" to t("Capilla del Santísimo y adoración nocturna", "Blessed Sacrament Chapel and nocturnal adoration", "Capela do Santíssimo e adoração noturna"),
        "Capilla del Santísimo: 08:00 - 17:00" to t("Capilla del Santísimo: 08:00–17:00", "Blessed Sacrament Chapel: 08:00–17:00", "Capela do Santíssimo: 08:00–17:00"),
        "Capilla del Santísimo: 08:00 a 19:00" to t("Capilla del Santísimo: 08:00–19:00", "Blessed Sacrament Chapel: 08:00–19:00", "Capela do Santíssimo: 08:00–19:00"),
        "Capilla del Santísimo: 09:00 - 18:00" to t("Capilla del Santísimo: 09:00–18:00", "Blessed Sacrament Chapel: 09:00–18:00", "Capela do Santíssimo: 09:00–18:00"),
        "Capilla del Santísimo: 09:00 - 20:00" to t("Capilla del Santísimo: 09:00–20:00", "Blessed Sacrament Chapel: 09:00–20:00", "Capela do Santíssimo: 09:00–20:00"),
        "Capilla del Santísimo: 09:30 - 19:00" to t("Capilla del Santísimo: 09:30–19:00", "Blessed Sacrament Chapel: 09:30–19:00", "Capela do Santíssimo: 09:30–19:00"),
        "Capilla del Santísimo: Adoración Perpetua" to t("Capilla del Santísimo: adoración perpetua", "Blessed Sacrament Chapel: perpetual adoration", "Capela do Santíssimo: adoração perpétua"),
        "Capilla del Santísimo: 08:00 - 20:00" to t("Capilla del Santísimo: 08:00–20:00", "Blessed Sacrament Chapel: 08:00–20:00", "Capela do Santíssimo: 08:00–20:00"),
        "Capilla del Señor de los Temblores" to t("Capilla del Señor de los Temblores", "Chapel of the Lord of the Earthquakes", "Capela do Senhor dos Tremores"),
        "Durante as Missas de meio-dia" to t("Durante las misas del mediodía", "During the midday Masses", "Durante as missas do meio-dia"),
        "Durante las celebraciones matutinas y vespertinas" to t("Durante las misas de la mañana y de la tarde", "During the morning and evening Masses", "Durante as missas da manhã e da tarde"),
        "Durante las misas dominicales" to t("Durante las misas del domingo", "During the Sunday Masses", "Durante as missas de domingo"),
        "Durante las misas matutinas" to t("Durante las misas de la mañana", "During the morning Masses", "Durante as missas da manhã"),
        "Durante todas las Misas y previa cita en despacho" to t("Durante todas las misas, o con cita previa", "During every Mass, or by appointment", "Durante todas as missas, ou com marcação"),
        "Durante todas las misas y de 10:00 a 13:00" to t("Durante todas las misas y 10:00–13:00", "During every Mass, and 10:00–13:00", "Durante todas as missas e 10:00–13:00"),
        "Jueves Eucarísticos: 08:00 - 16:00" to t("Jueves eucarísticos: 08:00–16:00", "Eucharistic Thursdays: 08:00–16:00", "Quintas eucarísticas: 08:00–16:00"),
        "Jueves Eucarísticos: 09:00 - 18:00" to t("Jueves eucarísticos: 09:00–18:00", "Eucharistic Thursdays: 09:00–18:00", "Quintas eucarísticas: 09:00–18:00"),
        "Jueves Eucarísticos: 10:00 - 18:00" to t("Jueves eucarísticos: 10:00–18:00", "Eucharistic Thursdays: 10:00–18:00", "Quintas eucarísticas: 10:00–18:00"),
        "Jueves Eucarísticos: 10:00 - 18:30" to t("Jueves eucarísticos: 10:00–18:30", "Eucharistic Thursdays: 10:00–18:30", "Quintas eucarísticas: 10:00–18:30"),
        "Jueves de 10:00 a 17:00" to t("Jueves, 10:00–17:00", "Thursdays, 10:00–17:00", "Quintas, 10:00–17:00"),
        "Jueves: 09:00 - 18:00" to t("Jueves, 09:00–18:00", "Thursdays, 09:00–18:00", "Quintas, 09:00–18:00"),
        "Jueves: 10:00 - 18:00" to t("Jueves, 10:00–18:00", "Thursdays, 10:00–18:00", "Quintas, 10:00–18:00"),
        "Lunes a Domingo: 08:00 - 11:00 y 16:00 - 18:00" to t("Todos los días, 08:00–11:00 y 16:00–18:00", "Daily, 08:00–11:00 and 16:00–18:00", "Todos os dias, 08:00–11:00 e 16:00–18:00"),
        "Lunes a Domingo: 08:00 - 13:00 y 17:00 - 20:00" to t("Todos los días, 08:00–13:00 y 17:00–20:00", "Daily, 08:00–13:00 and 17:00–20:00", "Todos os dias, 08:00–13:00 e 17:00–20:00"),
        "Lunes a Domingo: 10:30 - 13:00 y 17:30 - 20:00" to t("Todos los días, 10:30–13:00 y 17:30–20:00", "Daily, 10:30–13:00 and 17:30–20:00", "Todos os dias, 10:30–13:00 e 17:30–20:00"),
        "Lunes a Sábado de 10:00 a 12:30 y 18:00 a 19:00" to t("Lunes a sábado, 10:00–12:30 y 18:00–19:00", "Monday to Saturday, 10:00–12:30 and 18:00–19:00", "Segunda a sábado, 10:00–12:30 e 18:00–19:00"),
        "Lunes a Sábado: 10:00 - 12:30 y 17:30 - 19:30" to t("Lunes a sábado, 10:00–12:30 y 17:30–19:30", "Monday to Saturday, 10:00–12:30 and 17:30–19:30", "Segunda a sábado, 10:00–12:30 e 17:30–19:30"),
        "Lunes a Viernes 11:00 - 13:00 y 17:00 - 19:00" to t("Lunes a viernes, 11:00–13:00 y 17:00–19:00", "Monday to Friday, 11:00–13:00 and 17:00–19:00", "Segunda a sexta, 11:00–13:00 e 17:00–19:00"),
        "Lunes a Viernes de 10:00 a 13:00 y 16:30 a 18:30" to t("Lunes a viernes, 10:00–13:00 y 16:30–18:30", "Monday to Friday, 10:00–13:00 and 16:30–18:30", "Segunda a sexta, 10:00–13:00 e 16:30–18:30"),
        "Lunes a Viernes de 10:00 a 14:00 y de 16:00 a 18:00" to t("Lunes a viernes, 10:00–14:00 y 16:00–18:00", "Monday to Friday, 10:00–14:00 and 16:00–18:00", "Segunda a sexta, 10:00–14:00 e 16:00–18:00"),
        "Lunes a Viernes: 10:00 - 12:00 y 16:00 - 18:00" to t("Lunes a viernes, 10:00–12:00 y 16:00–18:00", "Monday to Friday, 10:00–12:00 and 16:00–18:00", "Segunda a sexta, 10:00–12:00 e 16:00–18:00"),
        "Lunes a Viernes: 10:00 - 12:30 y 16:30 - 18:00" to t("Lunes a viernes, 10:00–12:30 y 16:30–18:00", "Monday to Friday, 10:00–12:30 and 16:30–18:00", "Segunda a sexta, 10:00–12:30 e 16:30–18:00"),
        "Lunes a Viernes: 13:00 - 17:45" to t("Lunes a viernes, 13:00–17:45", "Monday to Friday, 13:00–17:45", "Segunda a sexta, 13:00–17:45"),
        "Segunda a Sexta: 08:00 às 17:00" to t("Lunes a viernes, 08:00–17:00", "Monday to Friday, 08:00–17:00", "Segunda a sexta, 08:00–17:00"),
        "Segunda a Sexta: 09:00 às 11:30 e 14:00 às 16:30" to t("Lunes a viernes, 09:00–11:30 y 14:00–16:30", "Monday to Friday, 09:00–11:30 and 14:00–16:30", "Segunda a sexta, 09:00–11:30 e 14:00–16:30"),
        "Segunda a Sábado das 10:00 às 12:30 e 15:00 às 17:00" to t("Lunes a sábado, 10:00–12:30 y 15:00–17:00", "Monday to Saturday, 10:00–12:30 and 15:00–17:00", "Segunda a sábado, 10:00–12:30 e 15:00–17:00"),
        "Cada hora de 06:00 a 20:00 (Altar Mayor)" to t("Cada hora, 06:00–20:00 (altar mayor)", "Every hour, 06:00–20:00 (high altar)", "De hora em hora, 06:00–20:00 (altar-mor)")
    )

    /** The notes in brackets after a Mass time. */
    private val massNotes: Map<String, LocalizedText> = mapOf(
        "Altar Mayor" to t("altar mayor", "high altar", "altar-mor"),
        "Cripta de la Sagrada Familia" to t("cripta de la Sagrada Familia", "Sagrada Família crypt", "cripta da Sagrada Família"),
        "English" to t("en inglés", "in English", "em inglês"),
        "Spanish" to t("en español", "in Spanish", "em espanhol"),
        "Spanish/English" to t("en español e inglés", "in Spanish and English", "em espanhol e inglês"),
        "Grégorien" to t("gregoriano", "Gregorian chant", "gregoriano"),
        "High Mass" to t("misa solemne", "High Mass", "missa solene"),
        "Latino" to t("en latín", "in Latin", "em latim"),
        "Messe Internationale" to t("misa internacional", "international Mass", "missa internacional"),
        "Missa Internacional" to t("misa internacional", "international Mass", "missa internacional"),
        "Misa Internacional en Nave Central" to t("misa internacional, nave central", "international Mass, main nave", "missa internacional, nave central"),
        "Misa Capitular" to t("misa capitular", "chapter Mass", "missa capitular"),
        "Missa Capitular" to t("misa capitular", "chapter Mass", "missa capitular"),
        "Misa Conventual" to t("misa conventual", "conventual Mass", "missa conventual"),
        "Misa Mayor" to t("misa mayor", "High Mass", "missa solene"),
        "Misa Solemne del Cabildo" to t("misa solemne del cabildo", "solemn chapter Mass", "missa solene do cabido"),
        "Misa del Peregrino con Botafumeiro" to t("misa del peregrino, con botafumeiro", "Pilgrims' Mass with the botafumeiro", "missa do peregrino, com botafumeiro"),
        "Rito Mozárabe" to t("rito mozárabe", "Mozarabic Rite", "rito moçárabe")
    )

    private val bracket = Regex("""\(([^()]+)\)""")

    /** A confession or adoration note in [language]; an unknown one passes through. */
    fun note(text: String, language: AppLanguage): String =
        notes[text.trim()]?.get(language) ?: text

    /** A list of Mass times with its bracketed notes restated in [language]. */
    fun masses(text: String, language: AppLanguage): String =
        notes[text.trim()]?.get(language) ?: bracket.replace(text) { m ->
            val note = massNotes[m.groupValues[1].trim()]?.get(language) ?: m.groupValues[1]
            "($note)"
        }

    private val countries: Map<String, LocalizedText> = mapOf(
        "Argentina" to t("Argentina", "Argentina", "Argentina"),
        "Brasil" to t("Brasil", "Brazil", "Brasil"),
        "Chile" to t("Chile", "Chile", "Chile"),
        "Colombia" to t("Colombia", "Colombia", "Colômbia"),
        "España" to t("España", "Spain", "Espanha"),
        "Estados Unidos" to t("Estados Unidos", "United States", "Estados Unidos"),
        "Francia" to t("Francia", "France", "França"),
        "Italia" to t("Italia", "Italy", "Itália"),
        "México" to t("México", "Mexico", "México"),
        "Perú" to t("Perú", "Peru", "Peru"),
        "Portugal" to t("Portugal", "Portugal", "Portugal"),
        "Venezuela" to t("Venezuela", "Venezuela", "Venezuela")
    )

    /** The directory names countries in Spanish; this is the reader's name for one. */
    fun country(name: String, language: AppLanguage): String =
        countries[name]?.get(language) ?: name

    /** Whether the directory's wording has a restatement — for the coverage test. */
    fun knows(text: String): Boolean = notes.containsKey(text.trim())
}
