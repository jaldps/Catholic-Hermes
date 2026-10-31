package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The way back from a detail page that a screen opens inside itself (a prayer,
 * a parish, a commandment): the list's name with the identity's back glyph,
 * set on the gutter above the page's title.
 */
@Composable
fun InlineBackLink(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val text = if (design.decor.usesAllCapsLabels) label.uppercase() else label

    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = design.spacing.cardGap / 2)
            .let { if (testTag != null) it.testTag(testTag) else it },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .clickable(onClick = onClick)
                .padding(vertical = design.spacing.rowPaddingVertical),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (design.language == DesignLanguage.APPLE) {
                    Icons.AutoMirrored.Filled.ArrowBackIos
                } else {
                    Icons.AutoMirrored.Filled.ArrowBack
                },
                contentDescription = null,
                tint = design.palette.accent,
                modifier = Modifier.size(design.decor.rowIconSize * 0.8f)
            )
            Spacer(Modifier.width(design.spacing.iconLabelGap / 2))
            Text(
                text = text,
                style = styles.buttonLabel,
                color = design.palette.accent,
                maxLines = 1
            )
        }
    }
}
