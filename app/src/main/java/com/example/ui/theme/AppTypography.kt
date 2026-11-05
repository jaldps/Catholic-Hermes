package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Extra styles the Material type scale has no slot for: the oversized display
 * line each identity opens a screen with, the scripture body, the verse number
 * and the small-caps eyebrow above a section.
 */
data class AppTextStyles(
    val screenTitle: TextStyle,
    val screenSubtitle: TextStyle,
    val sectionEyebrow: TextStyle,
    val sectionTitle: TextStyle,
    val scripture: TextStyle,
    val scriptureDropCap: TextStyle,
    val verseNumber: TextStyle,
    val rowTitle: TextStyle,
    val rowSubtitle: TextStyle,
    val navLabel: TextStyle,
    val buttonLabel: TextStyle,
    val metric: TextStyle,
    val quote: TextStyle
)

/** Identity defaults retain their own typography. An explicit user family applies
 * to every text slot, including chrome and reading text. */
object AppTypographyFactory {

    fun build(
        language: DesignLanguage,
        serifPreferred: Boolean,
        scale: Float,
        fontFamilyChoice: String? = null
    ): Pair<Typography, AppTextStyles> {
        val readingSerif = if (fontFamilyChoice == null) serifPreferred else true
        val base = when (language) {
            DesignLanguage.APPLE -> apple(readingSerif, scale)
            DesignLanguage.IVE -> material(readingSerif, scale)
        }
        // Null retains the legacy Boolean API for existing callers.
        val family = fontFamilyChoice?.let {
            AppFonts.family(com.example.data.models.FontChoice.fromId(it))
        } ?: return base
        val (type, extras) = base
        fun TextStyle.withFamily() = copy(fontFamily = family)
        return type.copy(
            displayLarge = type.displayLarge.withFamily(),
            displayMedium = type.displayMedium.withFamily(),
            displaySmall = type.displaySmall.withFamily(),
            headlineLarge = type.headlineLarge.withFamily(),
            headlineMedium = type.headlineMedium.withFamily(),
            headlineSmall = type.headlineSmall.withFamily(),
            titleLarge = type.titleLarge.withFamily(),
            titleMedium = type.titleMedium.withFamily(),
            titleSmall = type.titleSmall.withFamily(),
            bodyLarge = type.bodyLarge.withFamily(),
            bodyMedium = type.bodyMedium.withFamily(),
            bodySmall = type.bodySmall.withFamily(),
            labelLarge = type.labelLarge.withFamily(),
            labelMedium = type.labelMedium.withFamily(),
            labelSmall = type.labelSmall.withFamily()
        ) to extras.copy(
            screenTitle = extras.screenTitle.withFamily(),
            screenSubtitle = extras.screenSubtitle.withFamily(),
            sectionEyebrow = extras.sectionEyebrow.withFamily(),
            sectionTitle = extras.sectionTitle.withFamily(),
            scripture = extras.scripture.withFamily(),
            scriptureDropCap = extras.scriptureDropCap.withFamily(),
            verseNumber = extras.verseNumber.withFamily(),
            rowTitle = extras.rowTitle.withFamily(),
            rowSubtitle = extras.rowSubtitle.withFamily(),
            navLabel = extras.navLabel.withFamily(),
            buttonLabel = extras.buttonLabel.withFamily(),
            metric = extras.metric.withFamily(),
            quote = extras.quote.withFamily()
        )
    }

    private val tightLineHeight = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )

    private fun sp(value: Double, scale: Float): TextUnit = (value * scale).sp

    // ------------------------------------------------------------- Cupertino

    /**
     * The iOS text styles, at their documented sizes, with San Francisco's
     * optical tracking: negative on the large display sizes, opening up
     * slightly below body size.
     */
    private fun apple(serifPreferred: Boolean, s: Float): Pair<Typography, AppTextStyles> {
        val sans = AppFonts.Inter
        val reading = if (serifPreferred) AppFonts.EbGaramond else AppFonts.Inter

        fun style(
            size: Double,
            lineHeight: Double,
            weight: FontWeight = FontWeight.Normal,
            tracking: Double = 0.0,
            family: FontFamily = sans
        ) = TextStyle(
            fontFamily = family,
            fontWeight = weight,
            fontSize = sp(size, s),
            lineHeight = sp(lineHeight, s),
            letterSpacing = sp(tracking, s),
            lineHeightStyle = tightLineHeight
        )

        val typography = Typography(
            displayLarge = style(34.0, 41.0, FontWeight.Bold, -0.6),
            displayMedium = style(28.0, 34.0, FontWeight.Bold, -0.4),
            displaySmall = style(22.0, 28.0, FontWeight.Bold, -0.3),
            headlineLarge = style(28.0, 34.0, FontWeight.Bold, -0.4),
            headlineMedium = style(22.0, 28.0, FontWeight.SemiBold, -0.3),
            headlineSmall = style(20.0, 25.0, FontWeight.SemiBold, -0.2),
            titleLarge = style(17.0, 22.0, FontWeight.SemiBold, -0.4),
            titleMedium = style(16.0, 21.0, FontWeight.SemiBold, -0.3),
            titleSmall = style(15.0, 20.0, FontWeight.Medium, -0.2),
            bodyLarge = style(17.0, 22.0, FontWeight.Normal, -0.4),
            bodyMedium = style(15.0, 20.0, FontWeight.Normal, -0.2),
            bodySmall = style(13.0, 18.0, FontWeight.Normal, 0.0),
            labelLarge = style(17.0, 22.0, FontWeight.SemiBold, -0.4),
            labelMedium = style(13.0, 18.0, FontWeight.Medium, 0.0),
            labelSmall = style(11.0, 13.0, FontWeight.Medium, 0.1)
        )

        val extras = AppTextStyles(
            screenTitle = style(34.0, 41.0, FontWeight.Bold, -0.6),
            screenSubtitle = style(15.0, 20.0, FontWeight.Normal, -0.2),
            sectionEyebrow = style(13.0, 18.0, FontWeight.Normal, 0.0),
            sectionTitle = style(22.0, 28.0, FontWeight.Bold, -0.3),
            scripture = style(18.0, 29.0, FontWeight.Normal, -0.2, reading),
            scriptureDropCap = style(18.0, 29.0, FontWeight.Normal, -0.2, reading),
            verseNumber = style(11.0, 14.0, FontWeight.SemiBold, 0.1),
            rowTitle = style(17.0, 22.0, FontWeight.Normal, -0.4),
            rowSubtitle = style(13.0, 18.0, FontWeight.Normal, 0.0),
            navLabel = style(10.0, 12.0, FontWeight.Medium, 0.1),
            buttonLabel = style(17.0, 22.0, FontWeight.SemiBold, -0.4),
            metric = style(28.0, 32.0, FontWeight.Bold, -0.5),
            quote = style(17.0, 26.0, FontWeight.Normal, -0.3, reading)
        )
        return typography to extras
    }

    // -------------------------------------------------------------- Material

    /** The Material 3 type scale, at its published sizes and tracking. */
    private fun material(serifPreferred: Boolean, s: Float): Pair<Typography, AppTextStyles> {
        val sans = AppFonts.Roboto
        val reading = if (serifPreferred) AppFonts.EbGaramond else AppFonts.Roboto

        fun style(
            size: Double,
            lineHeight: Double,
            weight: FontWeight = FontWeight.Normal,
            tracking: Double = 0.0,
            family: FontFamily = sans
        ) = TextStyle(
            fontFamily = family,
            fontWeight = weight,
            fontSize = sp(size, s),
            lineHeight = sp(lineHeight, s),
            letterSpacing = sp(tracking, s),
            lineHeightStyle = tightLineHeight
        )

        val typography = Typography(
            displayLarge = style(57.0, 64.0, FontWeight.Normal, -0.25),
            displayMedium = style(45.0, 52.0),
            displaySmall = style(36.0, 44.0),
            headlineLarge = style(32.0, 40.0),
            headlineMedium = style(28.0, 36.0),
            headlineSmall = style(24.0, 32.0),
            titleLarge = style(22.0, 28.0),
            titleMedium = style(16.0, 24.0, FontWeight.Medium, 0.15),
            titleSmall = style(14.0, 20.0, FontWeight.Medium, 0.1),
            bodyLarge = style(16.0, 24.0, FontWeight.Normal, 0.5),
            bodyMedium = style(14.0, 20.0, FontWeight.Normal, 0.25),
            bodySmall = style(12.0, 16.0, FontWeight.Normal, 0.4),
            labelLarge = style(14.0, 20.0, FontWeight.Medium, 0.1),
            labelMedium = style(12.0, 16.0, FontWeight.Medium, 0.5),
            labelSmall = style(11.0, 16.0, FontWeight.Medium, 0.5)
        )

        val extras = AppTextStyles(
            // The large flexible app bar's headline, in M3 Expressive's
            // emphasised headline (the same size, at weight 500).
            screenTitle = style(30.0, 38.0, FontWeight.Medium, -0.2),
            screenSubtitle = style(14.0, 20.0, FontWeight.Normal, 0.25),
            sectionEyebrow = style(11.0, 16.0, FontWeight.Medium, 0.5),
            sectionTitle = style(22.0, 28.0, FontWeight.Normal, 0.0),
            scripture = style(16.0, 28.0, FontWeight.Normal, 0.4, reading),
            scriptureDropCap = style(16.0, 28.0, FontWeight.Normal, 0.4, reading),
            verseNumber = style(11.0, 16.0, FontWeight.Medium, 0.5),
            rowTitle = style(16.0, 24.0, FontWeight.Normal, 0.15),
            rowSubtitle = style(14.0, 20.0, FontWeight.Normal, 0.25),
            navLabel = style(12.0, 16.0, FontWeight.Medium, 0.5),
            buttonLabel = style(14.0, 20.0, FontWeight.Medium, 0.1),
            // headlineMedium, not displaySmall: 36sp does not fit a figure
            // like "1,328" in a third-width stat tile.
            metric = style(28.0, 36.0, FontWeight.Normal, 0.0),
            quote = style(16.0, 26.0, FontWeight.Normal, 0.3, reading)
        )
        return typography to extras
    }

}
