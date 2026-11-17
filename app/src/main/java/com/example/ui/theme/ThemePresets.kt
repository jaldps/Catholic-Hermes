package com.example.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.data.models.LocalizedText

enum class AppThemePreset(val id: String, val label: LocalizedText, val previewPrimary: Color) {
    LITURGICAL_DYNAMIC(
        "LITURGICAL_DYNAMIC",
        LocalizedText("Dinámico Litúrgico (Tiempo de la Iglesia)", "Liturgical Dynamic (Church Seasons)", "Dinâmico Litúrgico (Tempo da Igreja)"),
        Color(0xFF2C6B3E)
    ),
    MONASTIC_MINIMALIST(
        "MONASTIC_MINIMALIST",
        LocalizedText("Monástico Minimalista (Sobriedad y Piedra)", "Monastic Minimalist (Sober Slate & Ivory)", "Monástico Minimalista (Ardósia e Marfim)"),
        Color(0xFF334155)
    ),
    SACRED_BURGUNDY(
        "SACRED_BURGUNDY",
        LocalizedText("Borgoña Sagrado y Oro (Tradicional)", "Sacred Burgundy & Gold (Imperial)", "Borgonha Sagrado e Ouro (Imperial)"),
        Color(0xFF6B1D2F)
    ),
    MARIAN_AZURE(
        "MARIAN_AZURE",
        LocalizedText("Azul Mariano Celestial (Nuestra Señora)", "Marian Azure & Celestial (Our Lady)", "Azul Mariano Celeste (Nossa Senhora)"),
        Color(0xFF0284C7)
    )
}
