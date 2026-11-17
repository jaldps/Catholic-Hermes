package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.AppDialog
import com.example.ui.design.AppPrimaryButton
import com.example.ui.design.AppSecondaryButton
import com.example.ui.theme.AccentColors
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

/**
 * Colour wheel dialog: hue around the circle, saturation along the radius and a
 * lightness slider. [onSave] receives the chosen base colour; the theme derives
 * the readable ink from it, so any pick is safe.
 */
@Composable
fun AccentColorWheelDialog(
    initial: Color,
    language: AppLanguage,
    onSave: (Color) -> Unit,
    onDismiss: () -> Unit
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    fun t(es: String, en: String, pt: String) = when (language) {
        AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt
    }
    val start = remember(initial) { AccentColors.toHsl(initial) }
    var hue by remember { mutableFloatStateOf(start.h) }
    var sat by remember { mutableFloatStateOf(start.s) }
    var light by remember { mutableFloatStateOf(start.l.coerceIn(0.15f, 0.85f)) }
    val chosen = AccentColors.fromHsl(hue, sat, light)
    val ink = remember(chosen, design.language, design.isDark) { AccentColors.ink(chosen, design.language, design.isDark) }
    val onInk = if (AccentColors.contrast(ink, Color.White) >= AccentColors.contrast(ink, Color(0xFF0B0B0B))) Color.White else Color(0xFF0B0B0B)

    AppDialog(
        onDismiss = onDismiss,
        title = t("Color personalizado", "Custom colour", "Cor personalizada"),
        tag = "accent_custom_wheel"
    ) {
        val wheelDescription = t("Rueda de color: matiz y saturación", "Colour wheel: hue and saturation", "Roda de cor: matiz e saturação")
        Box(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp), contentAlignment = Alignment.Center) {
            Canvas(
                Modifier.widthIn(max = 260.dp).fillMaxWidth().aspectRatio(1f)
                    .testTag("accent_wheel_canvas")
                    .semantics { contentDescription = wheelDescription }
                    .pointerInput(Unit) {
                        fun pick(pos: Offset) {
                            val c = Offset(size.width / 2f, size.height / 2f)
                            val dx = pos.x - c.x; val dy = pos.y - c.y
                            val radius = size.width / 2f
                            hue = ((Math.toDegrees(atan2(dy, dx).toDouble()).toFloat()) + 360f) % 360f
                            sat = (hypot(dx, dy) / radius).coerceIn(0f, 1f)
                        }
                        awaitEachGesture {
                            val down = awaitFirstDown()
                            pick(down.position)
                            drag(down.id) { change -> pick(change.position); change.consume() }
                        }
                    }
            ) {
                val radius = size.minDimension / 2f
                val center = Offset(size.width / 2f, size.height / 2f)
                val ring = (0..12).map { AccentColors.fromHsl(it * 30f, 1f, light) }
                drawCircle(Brush.sweepGradient(ring, center), radius, center)
                drawCircle(
                    Brush.radialGradient(listOf(AccentColors.fromHsl(0f, 0f, light), Color.Transparent), center, radius),
                    radius, center
                )
                val a = Math.toRadians(hue.toDouble())
                val marker = Offset(center.x + (cos(a) * sat * radius).toFloat(), center.y + (sin(a) * sat * radius).toFloat())
                drawCircle(Color.White, 11.dp.toPx(), marker, style = Stroke(3.dp.toPx()))
                drawCircle(chosen, 8.dp.toPx(), marker)
            }
        }
        Text(
            t("Luminosidad", "Lightness", "Luminosidade"), style = styles.footnote, color = p.textSecondary,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        val sliderDescription = t("Luminosidad", "Lightness", "Luminosidade")
        Slider(
            value = light, onValueChange = { light = it }, valueRange = 0.15f..0.85f,
            colors = SliderDefaults.colors(thumbColor = chosen, activeTrackColor = ink, inactiveTrackColor = p.separator),
            modifier = Modifier.padding(horizontal = 20.dp).testTag("accent_wheel_lightness")
                .semantics { contentDescription = sliderDescription }
        )
        // Live preview: the picked colour and the readable ink the app will draw with it.
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(Modifier.size(40.dp).background(chosen, CircleShape))
            Box(
                Modifier.height(40.dp).background(ink, CircleShape).padding(horizontal = 18.dp).testTag("accent_wheel_preview"),
                contentAlignment = Alignment.Center
            ) {
                Text(t("Vista previa", "Preview", "Pré-visualização"), style = styles.subhead, color = onInk)
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AppSecondaryButton(
                label = t("Cancelar", "Cancel", "Cancelar"), onClick = onDismiss,
                modifier = Modifier.weight(1f).testTag("accent_wheel_cancel")
            )
            AppPrimaryButton(
                label = t("Guardar", "Save", "Guardar"), onClick = { onSave(chosen) },
                modifier = Modifier.weight(1f).testTag("accent_wheel_save")
            )
        }
    }
}
