package com.example.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.R

/**
 * Bundled OFL families, one per visual identity. They ship with the APK rather
 * than being fetched at runtime so the type is identical offline, and the
 * latin-ext subsets cover every accent Spanish and Portuguese need.
 */
object AppFonts {

    /** Inter — the open grotesque closest to San Francisco. */
    val Inter = FontFamily(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_bold, FontWeight.Bold)
    )

    /** Roboto — the Material Design type family. */
    val Roboto = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_medium, FontWeight.SemiBold),
        Font(R.font.roboto_bold, FontWeight.Bold)
    )

    /** Cinzel — Roman inscriptional capitals, for Vatican display type. */
    val Cinzel = FontFamily(
        Font(R.font.cinzel_regular, FontWeight.Normal),
        Font(R.font.cinzel_semibold, FontWeight.SemiBold),
        Font(R.font.cinzel_bold, FontWeight.Bold),
        Font(R.font.cinzel_black, FontWeight.Black)
    )

    /** EB Garamond — a Garamond revival for scripture and running text. */
    val EbGaramond = FontFamily(
        Font(R.font.ebgaramond_regular, FontWeight.Normal),
        Font(R.font.ebgaramond_medium, FontWeight.Medium),
        Font(R.font.ebgaramond_semibold, FontWeight.SemiBold),
        Font(R.font.ebgaramond_bold, FontWeight.Bold),
        // True italics, so Latin titles and rubric glosses are cut, not slanted.
        Font(R.font.ebgaramond_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.ebgaramond_semibold_italic, FontWeight.SemiBold, FontStyle.Italic)
    )
}
