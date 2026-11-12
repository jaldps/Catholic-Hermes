package com.example.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.lumenGlass
import com.example.ui.design.lumenPress
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * The way back from a detail page a screen opens inside itself (a prayer, a
 * parish, a commandment): a glass capsule with a chevron and the list's
 * name, in ink, on the gutter above the page's title.
 *
 * Lumen screens should prefer [com.example.ui.design.AppBackHandler], which
 * routes the scaffold's single glass back button to the in-screen close; this
 * capsule stays for screens that still draw their own way back, so it must
 * never compete in colour (no accent) or shape (capsule, like every control).
 */
@Composable
fun InlineBackLink(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String? = null,
    /** When given, the spoken action is localised ("Back to …"); otherwise Spanish, as before. */
    language: AppLanguage? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val actionLabel = when (language) {
        AppLanguage.EN -> "Back to $label"
        AppLanguage.PT -> "Voltar a $label"
        else -> "Volver a $label"
    }
    val shape = RoundedCornerShape(percent = 50)

    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = design.spacing.gutter)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 48dp touch target around a 40dp glass capsule.
        Box(
            Modifier
                .heightIn(min = 48.dp)
                .lumenPress(onClick, 0.95f)
                .semantics { onClick(label = actionLabel) { onClick(); true } }
                .let { if (testTag != null) it.testTag(testTag) else it },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                Modifier
                    .heightIn(min = 40.dp)
                    .lumenGlass(shape, strong = true)
                    .padding(start = 8.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = p.textPrimary,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = label,
                    style = styles.subhead,
                    color = p.textPrimary,
                    maxLines = 1
                )
            }
        }
    }
}
