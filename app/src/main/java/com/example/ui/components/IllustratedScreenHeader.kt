package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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
    when (design.language) {
        DesignLanguage.APPLE -> Row(
            modifier.fillMaxWidth().padding(end = design.spacing.gutter, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppScreenTitle(title, subtitle, Modifier.weight(1f))
            SacredArt(subject, Modifier.size(112.dp), transparentBackground = true)
        }
        DesignLanguage.MATERIAL -> {
            // One flexible bar owns the title, date and illustration together.
            // Search and reading follow immediately instead of another banner.
            AppScreenTitle(title, subtitle, modifier, trailingContent = {
                SacredArt(
                    subject,
                    Modifier.padding(start = 12.dp).size(96.dp).testTag("screen_header_illustration"),
                    transparentBackground = true
                )
            })
        }
        DesignLanguage.VATICAN -> Column(modifier) {
            AppScreenTitle(title, subtitle)
            SacredArt(
                subject,
                Modifier.padding(horizontal = design.spacing.gutter).padding(bottom = 12.dp)
                    .fillMaxWidth().height(112.dp).clipToBounds()
            )
        }
    }
}
