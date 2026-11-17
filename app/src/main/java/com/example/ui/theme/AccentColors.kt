package com.example.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import com.example.data.models.LocalizedText
import com.example.ui.design.LensGain
import com.example.ui.design.tintedGlassSpec

/** A fixed non-ecclesial accent swatch: stored as [id], inked per identity from [base]. */
enum class NonEcclesialAccent(val id: String, val label: LocalizedText, val base: Color) {
    TEAL("NE_TEAL", LocalizedText("Turquesa", "Teal", "Turquesa"), Color(0xFF14958F)),
    OCEAN("NE_OCEAN", LocalizedText("Océano", "Ocean", "Oceano"), Color(0xFF1B78C8)),
    INDIGO("NE_INDIGO", LocalizedText("Índigo", "Indigo", "Índigo"), Color(0xFF4A57D0)),
    VIOLET("NE_VIOLET", LocalizedText("Violeta", "Violet", "Violeta"), Color(0xFF8A4CCB)),
    ROSE("NE_ROSE", LocalizedText("Rosa", "Rose", "Rosa"), Color(0xFFD64C87)),
    CORAL("NE_CORAL", LocalizedText("Coral", "Coral", "Coral"), Color(0xFFE5624B)),
    AMBER("NE_AMBER", LocalizedText("Ámbar", "Amber", "Âmbar"), Color(0xFFE59A1C)),
    OLIVE("NE_OLIVE", LocalizedText("Oliva", "Olive", "Oliva"), Color(0xFF7D8A2B)),
    FOREST("NE_FOREST", LocalizedText("Bosque", "Forest", "Floresta"), Color(0xFF2E7D4F)),
    GRAPHITE("NE_GRAPHITE", LocalizedText("Grafito", "Graphite", "Grafite"), Color(0xFF59606E));
}

/**
 * Accent colours beyond the four ecclesiastical presets: the ten fixed swatches
 * and `CUSTOM_RRGGBB` colours from the wheel. The stored colour is only a base;
 * [ink] derives what each identity actually draws.
 */
object AccentColors {
    const val CUSTOM_PREFIX = "CUSTOM_"
    const val CUSTOM_ID = "CUSTOM"
    /** Minimum contrast the accent ink keeps on every surface it is drawn on. */
    const val MinContrast = 4.5f
    private const val Margin = 0.05f

    fun customId(color: Color): String =
        CUSTOM_PREFIX + "%06X".format(color.toArgb24())

    fun isCustom(preset: String) = parseCustom(preset) != null

    fun parseCustom(preset: String): Color? {
        if (!preset.startsWith(CUSTOM_PREFIX)) return null
        val hex = preset.removePrefix(CUSTOM_PREFIX)
        if (hex.length != 6) return null
        val v = hex.toLongOrNull(16) ?: return null
        return Color(0xFF000000L or v)
    }

    /** The base colour of a non-ecclesial or custom preset; null for the four ecclesiastical ones. */
    fun baseOf(preset: String): Color? =
        NonEcclesialAccent.entries.firstOrNull { it.id == preset }?.base ?: parseCustom(preset)

    private fun Color.toArgb24(): Int =
        (Math.round(red * 255) shl 16) or (Math.round(green * 255) shl 8) or Math.round(blue * 255)

    fun contrast(a: Color, b: Color): Float {
        val x = a.luminance(); val y = b.luminance()
        return (maxOf(x, y) + 0.05f) / (minOf(x, y) + 0.05f)
    }

    // ------------------------------------------------------------------ HSL

    class Hsl(val h: Float, val s: Float, val l: Float)

    fun toHsl(c: Color): Hsl {
        val r = c.red; val g = c.green; val b = c.blue
        val mx = maxOf(r, g, b); val mn = minOf(r, g, b)
        val l = (mx + mn) / 2f
        val d = mx - mn
        if (d < 1e-6f) return Hsl(0f, 0f, l)
        val s = d / (1f - Math.abs(2f * l - 1f)).coerceAtLeast(1e-6f)
        val h = when (mx) {
            r -> ((g - b) / d).mod(6f)
            g -> (b - r) / d + 2f
            else -> (r - g) / d + 4f
        } * 60f
        return Hsl(h, s.coerceIn(0f, 1f), l)
    }

    fun fromHsl(h: Float, s: Float, l: Float): Color {
        val hh = h.mod(360f)
        val c = (1f - Math.abs(2f * l - 1f)) * s
        val x = c * (1f - Math.abs((hh / 60f).mod(2f) - 1f))
        val m = l - c / 2f
        val (r, g, b) = when {
            hh < 60f -> Triple(c, x, 0f)
            hh < 120f -> Triple(x, c, 0f)
            hh < 180f -> Triple(0f, c, x)
            hh < 240f -> Triple(0f, x, c)
            hh < 300f -> Triple(x, 0f, c)
            else -> Triple(c, 0f, x)
        }
        return Color((r + m).coerceIn(0f, 1f), (g + m).coerceIn(0f, 1f), (b + m).coerceIn(0f, 1f))
    }

    // ------------------------------------------------------------ surfaces

    /**
     * Every opaque colour accent ink can sit on in [identity]: canvas, cards,
     * and for the liquid identities each pane over every pool of light.
     */
    internal fun surfaces(identity: DesignLanguage, isDark: Boolean, pools: List<Color>): List<Color> {
        val p = DesignTokens.of(identity, isDark).palette
        val flat = listOf(p.canvas, p.canvasTop, p.canvasBottom, p.groupedSurface, p.raised)
        if (pools.isEmpty()) return flat
        val lens = if (identity == DesignLanguage.GLASS) LensGain else 1f
        val grounds = listOf(p.canvasTop, p.canvasBottom).flatMap { ground ->
            listOf(ground) + pools.flatMap { pool ->
                listOf(pool, pool.copy(alpha = (pool.alpha * lens).coerceAtMost(1f))).map { it.compositeOver(ground) }
            }
        }
        val panes = grounds.flatMap { g -> listOf(p.pane, p.glass, p.glassStrong).map { it.compositeOver(g) } }
        return flat + grounds + panes
    }

    /**
     * The ambient pools of light for [base]: the accent's own light top-left,
     * candle gold top-right, a violet shade, and a cool return — the same
     * recipe as the ecclesiastical presets.
     */
    fun ambient(base: Color, isDark: Boolean): List<Color> {
        val hsl = toHsl(base)
        val s = hsl.s.coerceIn(0.35f, 0.85f)
        return if (isDark) listOf(
            fromHsl(hsl.h, s * 0.8f, 0.30f).copy(alpha = 0.30f),
            Color(0x2EE08A3C),
            fromHsl(hsl.h + 40f, 0.55f, 0.50f).mixToward(Color(0xFF5A43D6), 0.6f).copy(alpha = 0.22f),
            Color(0x331E5C8C)
        ) else listOf(
            fromHsl(hsl.h, s, 0.80f).copy(alpha = 0.58f),
            Color(0x80FFDD9C),
            Color(0x5CCFBCFF),
            Color(0x6BAAD6F8)
        )
    }

    private fun Color.mixToward(o: Color, t: Float) = androidx.compose.ui.graphics.lerp(this, o, t)

    // ------------------------------------------------------------------ ink

    /**
     * The accent ink for [base] in [identity]: [base]'s hue and saturation, with
     * HSL lightness moved (darker in light mode, lighter in dark) just far enough
     * to hold [MinContrast] on every surface of the identity, and — where the
     * accent fills a button — for the action label on that fill's gradient.
     */
    fun ink(base: Color, identity: DesignLanguage, isDark: Boolean): Color {
        val pools = if (identity == DesignLanguage.SANCTUARY || identity == DesignLanguage.CLASSIC) emptyList()
            else ambient(base, isDark).let { if (identity == DesignLanguage.GLASS) glassAmbient(it) else it }
        val surfaces = surfaces(identity, isDark, pools)
        val design = DesignTokens.of(identity, isDark)
        val fillsButton = identity == DesignLanguage.APPLE || identity == DesignLanguage.GLASS || identity == DesignLanguage.IVE
        val label = liquidOnAction(isDark)
        val need = MinContrast + Margin
        fun ok(c: Color): Boolean {
            if (surfaces.any { contrast(c, it) < need }) return false
            if (fillsButton) {
                val spec = design.tintedGlassSpec(c)
                if (contrast(spec.fillTop, label) < need || contrast(spec.fillBottom, label) < need) return false
            }
            return true
        }
        val hsl = toHsl(base)
        var l = hsl.l
        val step = if (isDark) 0.004f else -0.004f
        var candidate = fromHsl(hsl.h, hsl.s, l)
        var guard = 0
        while (!ok(candidate) && guard++ < 260) {
            l = (l + step).coerceIn(0f, 1f)
            candidate = fromHsl(hsl.h, hsl.s, l)
        }
        return candidate
    }

    /** Label colour on an accent-filled button of the liquid identities. */
    fun liquidOnAction(isDark: Boolean): Color = if (isDark) Color(0xFF070B16) else Color.White
}
