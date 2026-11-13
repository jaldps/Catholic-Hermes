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
    /** Source-compatible Lumen identity; stored APPLE migrates to LUMEN. */
    APPLE("LUMEN", LocalizedText("Lumen", "Lumen", "Lumen"),
        LocalizedText("Luz, cristal y calma.", "Light, glass and quiet.", "Luz, vidro e calma.")),
    /** Legacy alias. Old IVE preferences resolve to Lumen. */
    IVE("IVE", LocalizedText("Lumen", "Lumen", "Lumen"),
        LocalizedText("Luz, cristal y calma.", "Light, glass and quiet.", "Luz, vidro e calma.")),
    SANCTUARY("SANCTUARY", LocalizedText("Sanctuary", "Sanctuary", "Sanctuary"),
        LocalizedText("La belleza de un misal personal.", "The beauty of a personal missal.", "A beleza de um missal pessoal."));

    companion object {
        val selectable = listOf(APPLE, SANCTUARY)
        fun fromId(id: String?): DesignLanguage = when (id) {
            "SANCTUARY", "VATICAN" -> SANCTUARY
            else -> APPLE
        }
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
    val palette: AppPalette,
    val reduceMotion: Boolean = false,
    val reduceTransparency: Boolean = false,
    val highContrast: Boolean = false
) {
    val isSanctuary: Boolean get() = language == DesignLanguage.SANCTUARY
}

val LocalAppDesign = staticCompositionLocalOf {
    DesignTokens.of(DesignLanguage.APPLE, isDark = false)
}

object DesignTokens {

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
        accent = Color(0xFF2D743B),
        accentMuted = Color(0xFFE3EEDF),
        gold = Color(0xFF8A6A1F),
        textPrimary = Color(0xFF1D1C1A),
        textSecondary = Color(0xFF6B6661),
        textTertiary = Color(0xFF6C6762),
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
        glass = Color(0xD92C2A27),
        glassStrong = Color(0xF02C2A27),
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

    private val SanctuaryShapes = AppShapes(
        card = RoundedCornerShape(10.dp), cardLarge = RoundedCornerShape(12.dp),
        button = RoundedCornerShape(8.dp), buttonSmall = RoundedCornerShape(8.dp),
        field = RoundedCornerShape(8.dp), chip = RoundedCornerShape(8.dp),
        sheet = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        dialog = RoundedCornerShape(16.dp), thumbnail = RoundedCornerShape(6.dp),
        badge = RoundedCornerShape(6.dp), listGroup = RoundedCornerShape(8.dp)
    )

    private val SanctuaryLight = LumenLight.copy(
        canvas = Color(0xFFF4EEE3), canvasTop = Color(0xFFF8F3EA), canvasBottom = Color(0xFFEFE7D9),
        groupedSurface = Color(0xFFFFFBF3), raised = Color(0xFFFFFCF6),
        hairline = Color(0xFFCEC1AF), separator = Color(0xFFD8CDBC),
        textPrimary = Color(0xFF2B231F), textSecondary = Color(0xFF67594F), textTertiary = Color(0xFF726458),
        accent = Color(0xFF7A293A), accentMuted = Color(0xFFF0DFDF), gold = Color(0xFF87672B),
        selectionTint = Color(0xFFEBDDCD), action = Color(0xFF742A3A), onAction = Color(0xFFFFFBF3),
        glass = Color(0xFFFFFBF3), glassStrong = Color(0xFFFAF4E9),
        glassEdgeTop = Color(0xFFD5C7B5), glassEdgeBottom = Color(0xFFD5C7B5),
        shadow = Color(0x0A493522), success = Color(0xFF356241),
        ringStart = Color(0xFFCFBC94), ringEnd = Color(0xFF7A293A), inkQuiet = Color(0xFF9B8C7A)
    )
    private val SanctuaryDark = LumenDark.copy(
        canvas = Color(0xFF1B1517), canvasTop = Color(0xFF221A1D), canvasBottom = Color(0xFF171214),
        groupedSurface = Color(0xFF2A2225), raised = Color(0xFF33292C),
        hairline = Color(0xFF62504F), separator = Color(0xFF4C3E3E),
        textPrimary = Color(0xFFF6EDDF), textSecondary = Color(0xFFCBBBAE), textTertiary = Color(0xFFB7A697),
        accent = Color(0xFFE5A4AE), accentMuted = Color(0xFF4A2D36), gold = Color(0xFFD7B77E),
        selectionTint = Color(0xFF49343B), action = Color(0xFFE5B8BD), onAction = Color(0xFF321C23),
        glass = Color(0xFF2A2225), glassStrong = Color(0xFF2F2528),
        glassEdgeTop = Color(0xFF62504F), glassEdgeBottom = Color(0xFF62504F),
        shadow = Color.Transparent, success = Color(0xFF98C9A1),
        ringStart = Color(0xFF816A49), ringEnd = Color(0xFFE5A4AE), inkQuiet = Color(0xFF9E8D83)
    )

    fun of(language: DesignLanguage, isDark: Boolean): AppDesign {
        val sanctuary = language == DesignLanguage.SANCTUARY
        return AppDesign(
            language = language, isDark = isDark,
            shapes = if (sanctuary) SanctuaryShapes else LumenShapes,
            spacing = if (sanctuary) LumenSpacing.copy(sectionGap = 24.dp, cardPadding = 20.dp, rowMinHeight = 56.dp) else LumenSpacing,
            depth = if (sanctuary) LumenDepth.copy(cardBorderWidth = 0.5.dp) else LumenDepth,
            motion = if (sanctuary) LumenMotion.copy(fastMillis = 140, mediumMillis = 220, slowMillis = 300) else LumenMotion,
            decor = AppleDecor.copy(navStyle = if (sanctuary) NavStyle.FOLIO_RAIL else NavStyle.TAB_BAR,
                listStyle = if (sanctuary) ListStyle.RULED_FOLIO else ListStyle.INSET_GROUPED,
                usesOrnaments = sanctuary, usesDropCaps = sanctuary, navIconSize = 24.dp, rowIconSize = 22.dp),
            palette = if (sanctuary) { if (isDark) SanctuaryDark else SanctuaryLight } else { if (isDark) LumenDark else LumenLight }
        )
    }

    /** Material [androidx.compose.material3.Shapes] derived from an identity. */
    fun materialShapes(shapes: AppShapes) = androidx.compose.material3.Shapes(
        extraSmall = shapes.buttonSmall, // 12dp — M3 defaults can read this
        small = shapes.button,           // 12dp — M3 Button/chips read this
        medium = shapes.card,          // 12dp cards
        large = shapes.cardLarge,      // 16dp large cards
        extraLarge = RoundedCornerShape(28.dp) // 28dp prominent cards / dialogs
    )
}
