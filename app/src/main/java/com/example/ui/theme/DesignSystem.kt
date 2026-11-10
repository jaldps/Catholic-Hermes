package com.example.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.data.models.LocalizedText

/**
 * The two visual identities the app can wear. Each one re-specifies the whole
 * surface — type, shape, spacing, depth, motion, navigation pattern, list
 * idiom, iconography and ornament — not merely the palette.
 */
enum class DesignLanguage(
    val id: String,
    val label: LocalizedText,
    val tagline: LocalizedText
) {
    /** Apple Human Interface Guidelines: inset grouped lists, hairlines, large titles. */
    APPLE(
        "APPLE",
        LocalizedText("Cupertino", "Cupertino", "Cupertino"),
        LocalizedText(
            "Listas agrupadas, tipografía nítida y jerarquía serena al estilo de iOS.",
            "Inset grouped lists, crisp type and the calm hierarchy of iOS.",
            "Listas agrupadas, tipografia nítida e hierarquia serena ao estilo iOS."
        )
    ),

    /** Material 3: tonal surfaces, stadium buttons, pill navigation indicator. */
    /**
     * Translucency, iOS 7 and on: frosted surfaces at ~90% opacity, vibrant
     * full-saturation accent, hairlines instead of shadows, SF-like type.
     * Surfaces let the wallpaper breathe through; ink stays opaque.
     */
    IVE(
        "IVE",
        LocalizedText("Ive", "Ive", "Ive"),
        LocalizedText(
            "Papel cálido, tarjetas frosted, títulos serenos y verde litúrgico al estilo de la referencia iOS.",
            "Warm paper, frosted cards, serene titles and liturgical green in the iOS reference style.",
            "Papel quente, cartões foscos, títulos serenos e verde litúrgico no estilo da referência iOS."
        )
    );

    companion object {
        fun fromId(id: String?): DesignLanguage =
            entries.firstOrNull { it.id == id } ?: APPLE
    }
}

enum class HeaderStyle { LARGE_TITLE, TONAL_TITLE, CREST }
enum class NavStyle { TAB_BAR, NAV_BAR, FOLIO_RAIL }
enum class ListStyle { INSET_GROUPED, ELEVATED_CARDS, RULED_FOLIO }
enum class DividerStyle { HAIRLINE, SPARSE, ENGRAVED_RULE }
enum class BackgroundStyle { GROUPED, TONAL, PARCHMENT }
enum class IconVariant { OUTLINED, ROUNDED, SHARP }

@Immutable
data class AppShapes(
    val card: CornerBasedShape,
    val cardLarge: CornerBasedShape,
    val button: CornerBasedShape,
    val buttonSmall: CornerBasedShape,
    val field: CornerBasedShape,
    val chip: CornerBasedShape,
    val sheet: CornerBasedShape,
    val dialog: CornerBasedShape,
    val thumbnail: CornerBasedShape,
    val badge: CornerBasedShape,
    /** Shape of a whole grouped list block, not of the rows inside it. */
    val listGroup: CornerBasedShape
)

@Immutable
data class AppSpacing(
    val gutter: Dp,
    val sectionGap: Dp,
    val cardPadding: Dp,
    val cardGap: Dp,
    val rowMinHeight: Dp,
    val rowPaddingVertical: Dp,
    val listItemGap: Dp,
    val headerTopGap: Dp,
    val contentBottomGap: Dp,
    val iconLabelGap: Dp,
    val insetGroupGap: Dp,
    /** Width the reading column is clamped to, so scripture never over-runs. */
    val readingMaxWidth: Dp
)

@Immutable
data class AppDepth(
    val cardElevation: Dp,
    val cardBorderWidth: Dp,
    val appBarElevation: Dp,
    val navElevation: Dp,
    val sheetElevation: Dp,
    val usesTonalFill: Boolean,
    val usesHairlines: Boolean
)

@Immutable
data class AppMotion(
    val fastMillis: Int,
    val mediumMillis: Int,
    val slowMillis: Int,
    val standardEasing: Easing,
    val emphasizedEasing: Easing
)

@Immutable
data class AppDecor(
    val headerStyle: HeaderStyle,
    val navStyle: NavStyle,
    val listStyle: ListStyle,
    val dividerStyle: DividerStyle,
    val backgroundStyle: BackgroundStyle,
    val iconVariant: IconVariant,
    val navIconSize: Dp,
    val rowIconSize: Dp,
    val usesChevrons: Boolean,
    val usesDropCaps: Boolean,
    val usesAllCapsLabels: Boolean,
    val usesOrnaments: Boolean,
    val titleAlignment: Alignment.Horizontal
)

/**
 * Colour roles the Material [androidx.compose.material3.ColorScheme] has no
 * slot for but that these identities depend on — the grouped canvas behind
 * inset lists, hairline separators, gold leaf, and the three text ranks.
 */
@Immutable
data class AppPalette(
    val canvas: Color,
    val groupedSurface: Color,
    val raised: Color,
    val hairline: Color,
    val accent: Color,
    val accentMuted: Color,
    val gold: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val scrim: Color,
    val selectionTint: Color,
    // ---- Lumen surface roles. Defaults keep older constructors compiling.
    /** Top and bottom of the full-screen canvas gradient. */
    val canvasTop: Color = canvas,
    val canvasBottom: Color = canvas,
    /** Translucent frosted fill of cards, tiles, groups and chips. */
    val glass: Color = groupedSurface,
    /** Denser glass for floating controls and the segmented thumb. */
    val glassStrong: Color = raised,
    /** The lit edge of a glass surface, drawn top to bottom. */
    val glassEdgeTop: Color = hairline,
    val glassEdgeBottom: Color = hairline,
    /** Colour of the soft lift shadow under glass. */
    val shadow: Color = Color.Black.copy(alpha = 0.08f),
    /** Inset hairline between rows. */
    val separator: Color = hairline,
    /** Primary capsule, selected chip: charcoal in light, ivory in dark. */
    val action: Color = textPrimary,
    val onAction: Color = canvas,
    /** Completed habit checks. */
    val success: Color = accent,
    /** Progress ring gradient. */
    val ringStart: Color = accentMuted,
    val ringEnd: Color = accent,
    /** Decorative-only ink (placeholders, disabled glyphs); never body text. */
    val inkQuiet: Color = textTertiary
)

@Immutable
data class AppDesign(
    val language: DesignLanguage,
    val isDark: Boolean,
    val shapes: AppShapes,
    val spacing: AppSpacing,
    val depth: AppDepth,
    val motion: AppMotion,
    val decor: AppDecor,
    val palette: AppPalette
)

val LocalAppDesign = staticCompositionLocalOf {
    DesignTokens.of(DesignLanguage.APPLE, isDark = false)
}

object DesignTokens {

    // ---------------------------------------------------------------- shapes

    private val AppleShapes = AppShapes(
        card = RoundedCornerShape(12.dp),
        cardLarge = RoundedCornerShape(16.dp),
        button = RoundedCornerShape(12.dp),
        buttonSmall = RoundedCornerShape(12.dp),
        field = RoundedCornerShape(10.dp),
        // One visual language for every button-like control: 12dp.
        chip = RoundedCornerShape(12.dp),
        sheet = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        dialog = RoundedCornerShape(14.dp),
        thumbnail = RoundedCornerShape(10.dp),
        badge = RoundedCornerShape(percent = 50),
        // Same radius as a card: groups and cards share one column on every
        // screen, and a 10/12 mismatch side by side reads as two systems.
        listGroup = RoundedCornerShape(12.dp)
    )

    /**
     * Ive: the reference's geometry — 20dp frosted cards, 12dp buttons/chips
     * (one straight, slightly rounded language for every button), 16dp tiles,
     * generous 20dp screen gutters.
     */
    private val IveShapes = AppShapes(
        card = RoundedCornerShape(20.dp),
        cardLarge = RoundedCornerShape(20.dp),
        button = RoundedCornerShape(12.dp),
        buttonSmall = RoundedCornerShape(12.dp),
        field = RoundedCornerShape(16.dp),
        chip = RoundedCornerShape(12.dp),
        sheet = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        dialog = RoundedCornerShape(20.dp),
        thumbnail = RoundedCornerShape(16.dp),
        badge = RoundedCornerShape(percent = 50),
        listGroup = RoundedCornerShape(20.dp)
    )

    private val MaterialShapes = AppShapes(
        // M3 shape scale: extraSmall 4, small 8, medium 12, large 16,
        // extraLarge 28, full. card = medium (12) for filled/modest cards,
        // cardLarge = large→extraLarge (16/28): metric tiles and banners use
        // 16/28. Buttons and chips share the one 12dp language; fields use
        // the text-field corner (4dp) — the *search* stadium is shaped at
        // use site.
        card = RoundedCornerShape(12.dp),
        cardLarge = RoundedCornerShape(16.dp),
        button = RoundedCornerShape(12.dp),
        buttonSmall = RoundedCornerShape(12.dp),
        field = RoundedCornerShape(4.dp),
        chip = RoundedCornerShape(12.dp),
        sheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        dialog = RoundedCornerShape(28.dp),
        thumbnail = RoundedCornerShape(12.dp),
        badge = RoundedCornerShape(percent = 50),
        listGroup = RoundedCornerShape(16.dp)
    )


    // --------------------------------------------------------------- spacing

    private val AppleSpacing = AppSpacing(
        gutter = 16.dp,
        // 24, not 28: the prominent section header brings its own 22 above
        // it, and a group followed by a full gap and then a header opened a
        // 50dp hole between groups that no inset grouped table has.
        sectionGap = 24.dp,
        cardPadding = 16.dp,
        cardGap = 12.dp,
        rowMinHeight = 44.dp,
        rowPaddingVertical = 11.dp,
        listItemGap = 0.dp,
        headerTopGap = 8.dp,
        contentBottomGap = 32.dp,
        iconLabelGap = 12.dp,
        insetGroupGap = 22.dp,
        readingMaxWidth = 640.dp
    )

    private val MaterialSpacing = AppSpacing(
        gutter = 16.dp,
        // 16, not 24: every section break is this gap *plus* the section
        // header's own 16dp top, and 24 put 40dp of dead canvas between a
        // segmented group and the next header — twice Settings' rhythm.
        sectionGap = 16.dp,
        cardPadding = 16.dp,
        // 8dp grid: card spacing uses the spacing scale, not 12dp.
        cardGap = 8.dp,
        // 72dp is M3's two-line list item; 56dp is the one-line height and was
        // squeezing every row that carries a subtitle.
        rowMinHeight = 72.dp,
        rowPaddingVertical = 8.dp,
        listItemGap = 8.dp,
        headerTopGap = 8.dp,
        contentBottomGap = 24.dp,
        iconLabelGap = 8.dp,
        insetGroupGap = 16.dp,
        readingMaxWidth = 680.dp
    )


    // ----------------------------------------------------------------- depth

    private val AppleDepth = AppDepth(
        cardElevation = 0.dp,
        cardBorderWidth = 0.dp,
        appBarElevation = 0.dp,
        navElevation = 0.dp,
        sheetElevation = 0.dp,
        usesTonalFill = false,
        usesHairlines = true
    )

    private val MaterialDepth = AppDepth(
        // A filled M3 card sits at 0dp; 1dp is the *elevated* card. Tone
        // carries the separation here, not shadow.
        cardElevation = 0.dp,
        cardBorderWidth = 0.dp,
        appBarElevation = 0.dp,
        navElevation = 3.dp,
        sheetElevation = 1.dp,
        usesTonalFill = true,
        usesHairlines = false
    )


    // ---------------------------------------------------------------- motion

    private val AppleMotion = AppMotion(
        fastMillis = 180,
        mediumMillis = 300,
        slowMillis = 420,
        standardEasing = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f),
        emphasizedEasing = CubicBezierEasing(0.32f, 0.72f, 0f, 1f)
    )

    private val MaterialMotion = AppMotion(
        fastMillis = 200,
        mediumMillis = 350,
        slowMillis = 500,
        standardEasing = CubicBezierEasing(0.2f, 0f, 0f, 1f),
        emphasizedEasing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1f)
    )


    // ----------------------------------------------------------------- decor

    private val AppleDecor = AppDecor(
        headerStyle = HeaderStyle.LARGE_TITLE,
        navStyle = NavStyle.TAB_BAR,
        listStyle = ListStyle.INSET_GROUPED,
        dividerStyle = DividerStyle.HAIRLINE,
        backgroundStyle = BackgroundStyle.GROUPED,
        iconVariant = IconVariant.OUTLINED,
        navIconSize = 26.dp,
        rowIconSize = 22.dp,
        usesChevrons = true,
        usesDropCaps = false,
        usesAllCapsLabels = false,
        usesOrnaments = false,
        titleAlignment = Alignment.Start
    )

    private val MaterialDecor = AppDecor(
        headerStyle = HeaderStyle.TONAL_TITLE,
        navStyle = NavStyle.NAV_BAR,
        listStyle = ListStyle.ELEVATED_CARDS,
        dividerStyle = DividerStyle.SPARSE,
        backgroundStyle = BackgroundStyle.TONAL,
        iconVariant = IconVariant.ROUNDED,
        navIconSize = 24.dp,
        rowIconSize = 24.dp,
        usesChevrons = false,
        usesDropCaps = false,
        usesAllCapsLabels = false,
        usesOrnaments = false,
        titleAlignment = Alignment.Start
    )


    // --------------------------------------------------------------- palette

    // iOS system colours.
    private val ApplePaletteLight = AppPalette(
        canvas = Color(0xFFF2F2F7),
        groupedSurface = Color(0xFFFFFFFF),
        raised = Color(0xFFFFFFFF),
        hairline = Color(0xFFC6C6C8),
        accent = Color(0xFF0066CC),
        accentMuted = Color(0xFFE5F0FF),
        gold = Color(0xFFB8860B),
        textPrimary = Color(0xFF000000),
        // secondaryLabel at 60% measures 3.5:1 on white and reads as a
        // ghost in captions; struck firmer to clear 4.5:1 on the grouped
        // canvas. Tertiary is the opaque iOS gray 6E6E73 (4.54:1 on canvas,
        // 5.07:1 on groupedSurface): the old 3C3C43 at 55% composited to
        // 2.92:1 on canvas, unreadable as caption/meta text.
        textSecondary = Color(0xC23C3C43),
        textTertiary = Color(0xFF6E6E73),
        scrim = Color(0x66000000),
        selectionTint = Color(0xFFD1D1D6)
    )

    private val ApplePaletteDark = AppPalette(
        canvas = Color(0xFF000000),
        groupedSurface = Color(0xFF1C1C1E),
        raised = Color(0xFF2C2C2E),
        hairline = Color(0xFF38383A),
        accent = Color(0xFF409CFF),
        accentMuted = Color(0xFF13243B),
        gold = Color(0xFFD4AF37),
        textPrimary = Color(0xFFFFFFFF),
        textSecondary = Color(0xADEBEBF5),
        textTertiary = Color(0x80EBEBF5),
        scrim = Color(0x99000000),
        selectionTint = Color(0xFF3A3A3C)
    )

    // Translucency, iOS 7+: frosted surfaces over the canvas. Inks stay
    // opaque (contrast), surfaces sit at ~88-95% so the ground reads
    // through like iOS frosted glass.
    private val MaterialPaletteLight = AppPalette(
        canvas = Color(0xFFF7F3ED),
        groupedSurface = Color(0xD9FFFFFF),
        raised = Color(0xE6FFFFFF),
        hairline = Color(0x14000000),
        accent = Color(0xFF35751A),
        accentMuted = Color(0xFFE7F2DC),
        gold = Color(0xFFC9A86A),
        textPrimary = Color(0xFF1C1C1E),
        // Ive light secondary ink: darkened 5F5A52 -> 56524C so captions
        // stop reading washed-out; still clears 4.5:1 on canvas/raised.
        textSecondary = Color(0xFF56524C),
        // Darkened from 717A74 (4.22:1 on canvas / 4.40:1 grouped) to
        // clear 4.5:1 on every tonal surface (5.30:1 canvas, 4.90:1 raised).
        textTertiary = Color(0xFF6B665E),
        scrim = Color(0x52000000),
        selectionTint = Color(0xFFDAE8DE)
    )

    private val MaterialPaletteDark = AppPalette(
        canvas = Color(0xFF171512),
        groupedSurface = Color(0xD92A2723),
        raised = Color(0xE6332F2A),
        hairline = Color(0xFF3A352F),
        accent = Color(0xFF9ADE6B),
        accentMuted = Color(0xFF2C3A22),
        gold = Color(0xFFD4B78F),
        textPrimary = Color(0xFFF5F1EA),
        textSecondary = Color(0xFFC2CCC5),
        textTertiary = Color(0xFFA8B0A6),
        scrim = Color(0x7A000000),
        selectionTint = Color(0xFF34453A)
    )

    // Parchment, ink and gold leaf.


    // ----------------------------------------------------------------- Lumen

    /**
     * Lumen: one radius per element type — cards 24, groups and tiles 20,
     * image cards 22, sheets 28, and every button-like control a capsule.
     */
    private val LumenShapes = AppShapes(
        card = RoundedCornerShape(24.dp),
        cardLarge = RoundedCornerShape(24.dp),
        button = RoundedCornerShape(percent = 50),
        buttonSmall = RoundedCornerShape(percent = 50),
        field = RoundedCornerShape(percent = 50),
        chip = RoundedCornerShape(percent = 50),
        sheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        dialog = RoundedCornerShape(28.dp),
        thumbnail = RoundedCornerShape(16.dp),
        badge = RoundedCornerShape(percent = 50),
        listGroup = RoundedCornerShape(20.dp)
    )

    private val LumenSpacing = AppSpacing(
        gutter = 20.dp,
        sectionGap = 28.dp,
        cardPadding = 20.dp,
        cardGap = 14.dp,
        rowMinHeight = 52.dp,
        rowPaddingVertical = 14.dp,
        listItemGap = 0.dp,
        headerTopGap = 12.dp,
        contentBottomGap = 40.dp,
        iconLabelGap = 14.dp,
        insetGroupGap = 28.dp,
        readingMaxWidth = 640.dp
    )

    private val LumenDepth = AppDepth(
        cardElevation = 0.dp,
        cardBorderWidth = 1.dp,
        appBarElevation = 0.dp,
        navElevation = 0.dp,
        sheetElevation = 0.dp,
        usesTonalFill = false,
        usesHairlines = true
    )

    private val LumenMotion = AppMotion(
        fastMillis = 200,
        mediumMillis = 300,
        slowMillis = 420,
        standardEasing = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1f),
        emphasizedEasing = CubicBezierEasing(0.32f, 0.72f, 0f, 1f)
    )

    /**
     * Warm daylight. Glass is drawn translucent ([AppPalette.glass]); the
     * opaque [AppPalette.groupedSurface]/[AppPalette.raised] are that glass
     * composited over the canvas, for code that needs a solid colour and for
     * contrast checks. Every ink clears 4.5:1 on canvas and glass.
     */
    private val LumenLight = AppPalette(
        canvas = Color(0xFFF2F0ED),
        groupedSurface = Color(0xFFFBFAF9),
        raised = Color(0xFFFFFFFF),
        hairline = Color(0x0F000000),
        accent = Color(0xFF2F7A3E),
        accentMuted = Color(0xFFE3EEDF),
        gold = Color(0xFF8A6A1F),
        textPrimary = Color(0xFF1D1C1A),
        textSecondary = Color(0xFF6B6661),
        textTertiary = Color(0xFF706B66),
        scrim = Color(0x52000000),
        selectionTint = Color(0xFFE8E4DE),
        canvasTop = Color(0xFFF6F4F1),
        canvasBottom = Color(0xFFECE9E5),
        glass = Color(0xB8FFFFFF),
        glassStrong = Color(0xE0FFFFFF),
        glassEdgeTop = Color(0xF2FFFFFF),
        glassEdgeBottom = Color(0x59FFFFFF),
        shadow = Color(0x143B2F22),
        separator = Color(0x0F000000),
        action = Color(0xFF2B2A28),
        onAction = Color(0xFFFFFFFF),
        success = Color(0xFF43A047),
        ringStart = Color(0xFFCFE6C6),
        ringEnd = Color(0xFF4E9F4A),
        inkQuiet = Color(0xFF9C968F)
    )

    /** Night chapel: warm charcoal, quiet glass, nothing glows. */
    private val LumenDark = AppPalette(
        canvas = Color(0xFF131211),
        groupedSurface = Color(0xFF242321),
        raised = Color(0xFF2E2D2B),
        hairline = Color(0x14FFFFFF),
        accent = Color(0xFF7FC48A),
        accentMuted = Color(0xFF223326),
        gold = Color(0xFFD9BC7A),
        textPrimary = Color(0xFFF3F0EB),
        textSecondary = Color(0xFFA8A29B),
        textTertiary = Color(0xFF9A958F),
        scrim = Color(0x8C000000),
        selectionTint = Color(0xFF34322F),
        canvasTop = Color(0xFF171614),
        canvasBottom = Color(0xFF0E0D0C),
        glass = Color(0x12FFFFFF),
        glassStrong = Color(0x1FFFFFFF),
        glassEdgeTop = Color(0x24FFFFFF),
        glassEdgeBottom = Color(0x0AFFFFFF),
        shadow = Color(0x73000000),
        separator = Color(0x14FFFFFF),
        action = Color(0xFFF3F0EB),
        onAction = Color(0xFF171614),
        success = Color(0xFF5DB762),
        ringStart = Color(0xFF2C4A2E),
        ringEnd = Color(0xFF7FC48A),
        inkQuiet = Color(0xFF77726C)
    )

    /**
     * Both stored identities now render Lumen. The enum stays so saved
     * preferences keep resolving; the identity picker is hidden.
     */
    fun of(language: DesignLanguage, isDark: Boolean): AppDesign = AppDesign(
        language = language,
        isDark = isDark,
        shapes = LumenShapes,
        spacing = LumenSpacing,
        depth = LumenDepth,
        motion = LumenMotion,
        decor = AppleDecor.copy(navIconSize = 24.dp, rowIconSize = 22.dp),
        palette = if (isDark) LumenDark else LumenLight
    )

    /** Material [androidx.compose.material3.Shapes] derived from an identity. */
    fun materialShapes(shapes: AppShapes) = androidx.compose.material3.Shapes(
        extraSmall = shapes.buttonSmall, // 12dp — M3 defaults can read this
        small = shapes.button,           // 12dp — M3 Button/chips read this
        medium = shapes.card,          // 12dp cards
        large = shapes.cardLarge,      // 16dp large cards
        extraLarge = RoundedCornerShape(28.dp) // 28dp prominent cards / dialogs
    )
}
