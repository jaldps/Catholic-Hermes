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
    val quote: TextStyle,
    // ---- Lumen type scale (docs/redesign/DESIGN.md). Defaults keep older
    // constructors compiling; the factory always fills them.
    /** 44/48 hero title on a tab root ("Hoy"). */
    val display: TextStyle = screenTitle,
    /** 34/40 tab-root title without a hero. */
    val largeTitle: TextStyle = screenTitle,
    /** 28/34 detail title ("Misa de hoy"). */
    val title1: TextStyle = screenTitle,
    /** 22/28 medium: step and prayer titles. */
    val title2: TextStyle = sectionTitle,
    /** 20/25 semibold: section headers. */
    val title3: TextStyle = sectionTitle,
    /** 17/22 semibold: card titles, emphasised rows. */
    val headline: TextStyle = rowTitle,
    /** 17/24 regular: rows, body copy. */
    val body: TextStyle = rowTitle,
    /** 16/22: card body. */
    val callout: TextStyle = rowTitle,
    /** 15/20: card subtitles. */
    val subhead: TextStyle = rowSubtitle,
    /** 13/18: meta. */
    val footnote: TextStyle = rowSubtitle,
    /** 13/18 medium: date above a hero title, sentence case. */
    val eyebrow: TextStyle = sectionEyebrow,
    /** 12/16 medium: tile labels, chip meta. */
    val caption: TextStyle = navLabel,
    /** Long-form reading in the user's reader font (scripture, prayers). */
    val reading: TextStyle = scripture
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
        val base = if (language == DesignLanguage.SANCTUARY) sanctuary(readingSerif, scale) else lumen(readingSerif, scale)
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
            quote = extras.quote.withFamily(),
            display = extras.display.withFamily(),
            largeTitle = extras.largeTitle.withFamily(),
            title1 = extras.title1.withFamily(),
            title2 = extras.title2.withFamily(),
            title3 = extras.title3.withFamily(),
            headline = extras.headline.withFamily(),
            body = extras.body.withFamily(),
            callout = extras.callout.withFamily(),
            subhead = extras.subhead.withFamily(),
            footnote = extras.footnote.withFamily(),
            eyebrow = extras.eyebrow.withFamily(),
            caption = extras.caption.withFamily(),
            reading = extras.reading.withFamily()
        )
    }

    private val tightLineHeight = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )

    private fun sp(value: Double, scale: Float): TextUnit = (value * scale).sp

    // ----------------------------------------------------------------- Lumen

    /**
     * Lumen's scale: Inter set the way SF Pro is on iOS 26 — regular-weight
     * display sizes with tight optical tracking, generous leading below, and
     * the reader font (EB Garamond by default) only for long-form text.
     */
    private fun lumen(serifPreferred: Boolean, s: Float): Pair<Typography, AppTextStyles> {
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

        val display = style(44.0, 48.0, FontWeight.Normal, -1.0)
        val largeTitle = style(34.0, 40.0, FontWeight.Normal, -0.6)
        val title1 = style(28.0, 34.0, FontWeight.Normal, -0.4)
        val title2 = style(22.0, 28.0, FontWeight.Medium, -0.2)
        val title3 = style(20.0, 25.0, FontWeight.SemiBold, -0.3)
        val headline = style(17.0, 22.0, FontWeight.SemiBold, -0.2)
        val body = style(17.0, 24.0, FontWeight.Normal, -0.2)
        val callout = style(16.0, 22.0, FontWeight.Normal, -0.1)
        val subhead = style(15.0, 20.0, FontWeight.Normal, 0.0)
        val footnote = style(13.0, 18.0, FontWeight.Normal, 0.0)
        val eyebrow = style(13.0, 18.0, FontWeight.Medium, 0.0)
        val caption = style(12.0, 16.0, FontWeight.Medium, 0.0)
        val readingStyle = if (serifPreferred) {
            style(19.0, 30.0, FontWeight.Normal, 0.0, reading)
        } else {
            style(18.0, 28.0, FontWeight.Normal, -0.1, reading)
        }

        val typography = Typography(
            displayLarge = largeTitle,
            displayMedium = title1,
            displaySmall = title2,
            headlineLarge = title1,
            headlineMedium = title2,
            headlineSmall = title3,
            titleLarge = headline,
            titleMedium = style(16.0, 21.0, FontWeight.SemiBold, -0.2),
            titleSmall = style(15.0, 20.0, FontWeight.Medium, -0.1),
            bodyLarge = body,
            bodyMedium = subhead,
            bodySmall = footnote,
            labelLarge = style(16.0, 22.0, FontWeight.SemiBold, -0.2),
            labelMedium = eyebrow,
            labelSmall = style(11.0, 13.0, FontWeight.Medium, 0.1)
        )

        val extras = AppTextStyles(
            screenTitle = largeTitle,
            screenSubtitle = subhead,
            sectionEyebrow = eyebrow,
            sectionTitle = title3,
            scripture = readingStyle,
            scriptureDropCap = readingStyle,
            verseNumber = style(11.0, 14.0, FontWeight.SemiBold, 0.1),
            rowTitle = body,
            rowSubtitle = footnote,
            navLabel = style(12.0, 16.0, FontWeight.Medium, 0.0).copy(
                fontSize = (12f * s).coerceAtLeast(12f).sp, lineHeight = (16f * s).coerceAtLeast(16f).sp),
            buttonLabel = style(16.0, 22.0, FontWeight.SemiBold, -0.2),
            metric = style(30.0, 34.0, FontWeight.Normal, -0.6),
            quote = style(17.0, 26.0, FontWeight.Normal, -0.2, reading),
            display = display,
            largeTitle = largeTitle,
            title1 = title1,
            title2 = title2,
            title3 = title3,
            headline = headline,
            body = body,
            callout = callout,
            subhead = subhead,
            footnote = footnote,
            eyebrow = eyebrow,
            caption = caption,
            reading = readingStyle
        )
        return typography to extras
    }

    /** A missal hierarchy: literary serif titles, crisp sans controls. */
    private fun sanctuary(serifPreferred: Boolean, s: Float): Pair<Typography, AppTextStyles> {
        val (type, text) = lumen(serifPreferred, s)
        fun TextStyle.editorial(size: Double? = null, leading: Double? = null) = copy(
            fontFamily = AppFonts.EbGaramond, fontWeight = FontWeight.Medium,
            letterSpacing = 0.sp,
            fontSize = size?.let { sp(it, s) } ?: fontSize,
            lineHeight = leading?.let { sp(it, s) } ?: lineHeight
        )
        val display = text.display.editorial(46.0, 50.0)
        val large = text.largeTitle.editorial(38.0, 42.0)
        val title1 = text.title1.editorial(32.0, 36.0)
        val title2 = text.title2.editorial(26.0, 30.0)
        val title3 = text.title3.editorial(24.0, 28.0)
        return type.copy(displayLarge = large, displayMedium = title1, displaySmall = title2,
            headlineLarge = title1, headlineMedium = title2, headlineSmall = title3) to text.copy(
            display = display, largeTitle = large, screenTitle = large, title1 = title1,
            title2 = title2, title3 = title3, sectionTitle = title3,
            metric = text.metric.editorial(34.0, 38.0))
    }

}
