package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.ui.design.AppListRow
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * A secondary choice set as one list row — label, current value and a menu of
 * the alternatives — instead of a second row of chips under a screen's own
 * selector. Each screen keeps a single visible filter layer; everything finer
 * lives behind a row like this one, which the kit draws in its own idiom.
 *
 * Must sit inside an `AppListGroup`, like any other row.
 */
@Composable
fun <T> MenuPickerRow(
    title: String,
    options: List<Pair<T, String>>,
    selected: T,
    onSelect: (T) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    /** When set, the row shows this instead of the selected option's label. */
    trailingOverride: String? = null,
    testTag: String? = null
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    var expanded by remember { mutableStateOf(false) }
    val selectedLabel = options.firstOrNull { it.first == selected }?.second.orEmpty()

    Box(modifier.fillMaxWidth()) {
        AppListRow(
            title = title,
            subtitle = subtitle,
            leadingIcon = leadingIcon,
            trailingText = trailingOverride ?: selectedLabel,
            onClick = { expanded = true },
            modifier = if (testTag != null) Modifier.testTag(testTag) else Modifier
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(design.spacing.gutter, 0.dp),
            modifier = Modifier
                .heightIn(max = 360.dp)
                .background(design.palette.raised)
        ) {
            options.forEach { (key, label) ->
                val isSelected = key == selected
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            style = styles.rowTitle,
                            color = if (isSelected) design.palette.accent else design.palette.textPrimary
                        )
                    },
                    trailingIcon = if (isSelected) {
                        {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = design.palette.accent,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    } else null,
                    onClick = {
                        onSelect(key)
                        expanded = false
                    }
                )
            }
        }
    }
}
