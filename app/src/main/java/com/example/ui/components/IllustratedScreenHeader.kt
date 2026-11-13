package com.example.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.example.ui.design.AppScreenTitle
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign

/** One purposeful illustration, composed differently by each visual identity. */
@Composable
fun IllustratedScreenHeader(
    title: String,
    subject: SacredSubject,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val compactArt = LocalDensity.current.fontScale > 1.3f
    val narrow = LocalConfiguration.current.screenWidthDp < 360
    val artSize = if (compactArt) 48.dp else if (narrow) 64.dp else if (design.isSanctuary) 104.dp else 88.dp
    when (design.language) {
        DesignLanguage.SANCTUARY -> Row(
            modifier.fillMaxWidth().padding(end = design.spacing.gutter, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppScreenTitle(title, subtitle, Modifier.weight(1f))
            // Clipped, inset frame: the art's glow/flame can never paint to
            // the screen's right edge.
            Box(Modifier.clipToBounds().padding(end = 4.dp), contentAlignment = Alignment.Center) {
                SacredArt(subject, Modifier.size(artSize).testTag("screen_header_illustration"), transparentBackground = true)
            }
        }
        DesignLanguage.APPLE, DesignLanguage.IVE -> {
            // One flexible bar owns the title, date and illustration together.
            // Search and reading follow immediately instead of another banner.
            AppScreenTitle(title, subtitle, modifier, trailingContent = {
                // Scroll-safe frame inside the bar's gutter: clipped to its
                // bounds with an end inset, so flame/art stay fully inside
                // the frame instead of clipping at the right edge.
                Box(
                    Modifier.padding(start = 12.dp, end = 4.dp).clipToBounds(),
                    contentAlignment = Alignment.Center
                ) {
                    SacredArt(
                        subject,
                        Modifier.size(artSize).testTag("screen_header_illustration"),
                        transparentBackground = true
                    )
                }
            })
        }
    }
}
