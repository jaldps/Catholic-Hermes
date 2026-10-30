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
 * The three visual identities the app can wear. Each one re-specifies the whole
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
    MATERIAL(
        "MATERIAL",
        LocalizedText("Material 3", "Material 3", "Material 3"),
        LocalizedText(
            "Superficies tonales, formas generosas y movimiento enfático de Material Design 3.",
            "Tonal surfaces, generous shapes and the emphasised motion of Material Design 3.",
            "Superfícies tonais, formas generosas e movimento enfático do Material Design 3."
        )
    ),

    /** Vatican: Roman capitals, parchment, gold rules, centred folio composition. */
    VATICAN(
        "VATICAN",
        LocalizedText("Vaticano", "Vatican", "Vaticano"),
        LocalizedText(
            "Capitales romanas, pergamino, filetes de oro y composición centrada de folio.",
            "Roman capitals, parchment, gold rules and centred folio composition.",
            "Capitais romanas, pergaminho, filetes de ouro e composição centrada de fólio."
        )
    );

    companion object {
        fun fromId(id: String?): DesignLanguage =
            entries.firstOrNull { it.id == id } ?: VATICAN
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
    val selectionTint: Color
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
    DesignTokens.of(DesignLanguage.VATICAN, isDark = false)
}

object DesignTokens {

    // ---------------------------------------------------------------- shapes

    private val AppleShapes = AppShapes(
        card = RoundedCornerShape(12.dp),
        cardLarge = RoundedCornerShape(16.dp),
        button = RoundedCornerShape(12.dp),
        buttonSmall = RoundedCornerShape(8.dp),
        field = RoundedCornerShape(10.dp),
        chip = RoundedCornerShape(8.dp),
        sheet = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        dialog = RoundedCornerShape(14.dp),
        thumbnail = RoundedCornerShape(10.dp),
        badge = RoundedCornerShape(percent = 50),
        listGroup = RoundedCornerShape(10.dp)
    )

    private val MaterialShapes = AppShapes(
        card = RoundedCornerShape(16.dp),
        cardLarge = RoundedCornerShape(28.dp),
        button = RoundedCornerShape(percent = 50),
        buttonSmall = RoundedCornerShape(percent = 50),
        field = RoundedCornerShape(16.dp),
        chip = RoundedCornerShape(8.dp),
        sheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        dialog = RoundedCornerShape(28.dp),
        thumbnail = RoundedCornerShape(12.dp),
        badge = RoundedCornerShape(percent = 50),
        listGroup = RoundedCornerShape(16.dp)
    )

    private val VaticanShapes = AppShapes(
        card = RoundedCornerShape(3.dp),
        cardLarge = RoundedCornerShape(4.dp),
        button = RoundedCornerShape(2.dp),
        buttonSmall = RoundedCornerShape(2.dp),
        field = RoundedCornerShape(2.dp),
        chip = RoundedCornerShape(2.dp),
        sheet = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        dialog = RoundedCornerShape(4.dp),
        thumbnail = RoundedCornerShape(2.dp),
        badge = RoundedCornerShape(2.dp),
        listGroup = RoundedCornerShape(3.dp)
    )

    // --------------------------------------------------------------- spacing

    private val AppleSpacing = AppSpacing(
        gutter = 16.dp,
        sectionGap = 28.dp,
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
        sectionGap = 24.dp,
        cardPadding = 20.dp,
        cardGap = 12.dp,
        rowMinHeight = 56.dp,
        rowPaddingVertical = 12.dp,
        listItemGap = 8.dp,
        headerTopGap = 12.dp,
        contentBottomGap = 28.dp,
        iconLabelGap = 16.dp,
        insetGroupGap = 16.dp,
        readingMaxWidth = 680.dp
    )

    private val VaticanSpacing = AppSpacing(
        gutter = 24.dp,
        sectionGap = 40.dp,
        cardPadding = 24.dp,
        cardGap = 20.dp,
        rowMinHeight = 52.dp,
        rowPaddingVertical = 14.dp,
        listItemGap = 0.dp,
        headerTopGap = 20.dp,
        contentBottomGap = 48.dp,
        iconLabelGap = 16.dp,
        insetGroupGap = 32.dp,
        readingMaxWidth = 600.dp
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
        cardElevation = 1.dp,
        cardBorderWidth = 0.dp,
        appBarElevation = 0.dp,
        navElevation = 3.dp,
        sheetElevation = 1.dp,
        usesTonalFill = true,
        usesHairlines = false
    )

    private val VaticanDepth = AppDepth(
        cardElevation = 0.dp,
        cardBorderWidth = 1.dp,
        appBarElevation = 0.dp,
        navElevation = 0.dp,
        sheetElevation = 0.dp,
        usesTonalFill = false,
        usesHairlines = true
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

    private val VaticanMotion = AppMotion(
        fastMillis = 240,
        mediumMillis = 420,
        slowMillis = 620,
        standardEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f),
        emphasizedEasing = CubicBezierEasing(0.33f, 0f, 0.15f, 1f)
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

    private val VaticanDecor = AppDecor(
        headerStyle = HeaderStyle.CREST,
        navStyle = NavStyle.FOLIO_RAIL,
        listStyle = ListStyle.RULED_FOLIO,
        dividerStyle = DividerStyle.ENGRAVED_RULE,
        backgroundStyle = BackgroundStyle.PARCHMENT,
        iconVariant = IconVariant.SHARP,
        navIconSize = 22.dp,
        rowIconSize = 20.dp,
        usesChevrons = false,
        usesDropCaps = true,
        usesAllCapsLabels = true,
        usesOrnaments = true,
        titleAlignment = Alignment.CenterHorizontally
    )

    // --------------------------------------------------------------- palette

    // iOS system colours.
    private val ApplePaletteLight = AppPalette(
        canvas = Color(0xFFF2F2F7),
        groupedSurface = Color(0xFFFFFFFF),
        raised = Color(0xFFFFFFFF),
        hairline = Color(0xFFC6C6C8),
        accent = Color(0xFF007AFF),
        accentMuted = Color(0xFFE5F0FF),
        gold = Color(0xFFB8860B),
        textPrimary = Color(0xFF000000),
        textSecondary = Color(0x993C3C43),
        textTertiary = Color(0x4D3C3C43),
        scrim = Color(0x66000000),
        selectionTint = Color(0xFFD1D1D6)
    )

    private val ApplePaletteDark = AppPalette(
        canvas = Color(0xFF000000),
        groupedSurface = Color(0xFF1C1C1E),
        raised = Color(0xFF2C2C2E),
        hairline = Color(0xFF38383A),
        accent = Color(0xFF0A84FF),
        accentMuted = Color(0xFF13243B),
        gold = Color(0xFFD4AF37),
        textPrimary = Color(0xFFFFFFFF),
        textSecondary = Color(0x99EBEBF5),
        textTertiary = Color(0x4DEBEBF5),
        scrim = Color(0x99000000),
        selectionTint = Color(0xFF3A3A3C)
    )

    // Material 3 tonal surfaces.
    private val MaterialPaletteLight = AppPalette(
        canvas = Color(0xFFF7F2F9),
        groupedSurface = Color(0xFFFFFBFE),
        raised = Color(0xFFF3EDF7),
        hairline = Color(0xFFCAC4D0),
        accent = Color(0xFF6750A4),
        accentMuted = Color(0xFFEADDFF),
        gold = Color(0xFF8A6B12),
        textPrimary = Color(0xFF1D1B20),
        textSecondary = Color(0xFF49454F),
        textTertiary = Color(0xFF79747E),
        scrim = Color(0x52000000),
        selectionTint = Color(0xFFE8DEF8)
    )

    private val MaterialPaletteDark = AppPalette(
        canvas = Color(0xFF141218),
        groupedSurface = Color(0xFF211F26),
        raised = Color(0xFF2B2930),
        hairline = Color(0xFF49454F),
        accent = Color(0xFFD0BCFF),
        accentMuted = Color(0xFF4F378B),
        gold = Color(0xFFE5C158),
        textPrimary = Color(0xFFE6E0E9),
        textSecondary = Color(0xFFCAC4D0),
        textTertiary = Color(0xFF938F99),
        scrim = Color(0x7A000000),
        selectionTint = Color(0xFF4A4458)
    )

    // Parchment, ink and gold leaf.
    private val VaticanPaletteLight = AppPalette(
        canvas = Color(0xFFF4EDDF),
        groupedSurface = Color(0xFFFCF8F0),
        raised = Color(0xFFFFFDF8),
        hairline = Color(0xFFD9C9A3),
        accent = Color(0xFF7B1B2A),
        accentMuted = Color(0xFFF0E2D5),
        gold = Color(0xFFB08A2E),
        textPrimary = Color(0xFF1F1A17),
        textSecondary = Color(0xFF5B4F42),
        textTertiary = Color(0xFF8A7B68),
        scrim = Color(0x801F1A17),
        selectionTint = Color(0xFFEADFC6)
    )

    private val VaticanPaletteDark = AppPalette(
        canvas = Color(0xFF14110E),
        groupedSurface = Color(0xFF1E1915),
        raised = Color(0xFF26201A),
        hairline = Color(0xFF4A3F2E),
        accent = Color(0xFFC75B6A),
        accentMuted = Color(0xFF2E221C),
        gold = Color(0xFFD4AF37),
        textPrimary = Color(0xFFEDE4D3),
        textSecondary = Color(0xFFBFB09A),
        textTertiary = Color(0xFF8E8070),
        scrim = Color(0xB3000000),
        selectionTint = Color(0xFF332A20)
    )

    fun of(language: DesignLanguage, isDark: Boolean): AppDesign = when (language) {
        DesignLanguage.APPLE -> AppDesign(
            language = language,
            isDark = isDark,
            shapes = AppleShapes,
            spacing = AppleSpacing,
            depth = AppleDepth,
            motion = AppleMotion,
            decor = AppleDecor,
            palette = if (isDark) ApplePaletteDark else ApplePaletteLight
        )

        DesignLanguage.MATERIAL -> AppDesign(
            language = language,
            isDark = isDark,
            shapes = MaterialShapes,
            spacing = MaterialSpacing,
            depth = MaterialDepth,
            motion = MaterialMotion,
            decor = MaterialDecor,
            palette = if (isDark) MaterialPaletteDark else MaterialPaletteLight
        )

        DesignLanguage.VATICAN -> AppDesign(
            language = language,
            isDark = isDark,
            shapes = VaticanShapes,
            spacing = VaticanSpacing,
            depth = VaticanDepth,
            motion = VaticanMotion,
            decor = VaticanDecor,
            palette = if (isDark) VaticanPaletteDark else VaticanPaletteLight
        )
    }

    /** Material [androidx.compose.material3.Shapes] derived from an identity. */
    fun materialShapes(shapes: AppShapes) = androidx.compose.material3.Shapes(
        extraSmall = shapes.buttonSmall,
        small = shapes.chip,
        medium = shapes.card,
        large = shapes.cardLarge,
        extraLarge = shapes.dialog
    )
}
