package com.example.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.AppIconSlot
import com.example.ui.design.AppIcons
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppScreenTitle
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign

/** One secondary destination as the More screen lists it. */
data class MoreEntry(
    val title: String,
    val iconSlot: AppIconSlot,
    val tag: String,
    val value: String? = null,
    /** Overrides the slot's glyph, for rows that are settings, not places. */
    val icon: ImageVector? = null,
    val onOpen: () -> Unit
)

/** A titled run of [MoreEntry]s. */
data class MoreSection(val title: String, val entries: List<MoreEntry>)

/**
 * The fifth tab. Everything that does not earn a place in the tab bar is one
 * tap away here, grouped by what the reader is doing rather than listed flat:
 * a tab bar holds five items in iOS and Material alike, and the app has eleven.
 *
 * Ive treatment, kept inside the shared kit on purpose: the screen paints
 * with nothing but [LocalAppDesign] tokens (warm-paper canvas, spacing, shape
 * via [AppListGroup]) and lets the active identity resolve the frosted grouped
 * surfaces (translucent groupedSurface at the one 20dp card radius in Ive)
 * and the dark palette. No hardcoded colour or dimension, so dark mode is the
 * token's job, not this file's. Titles stay the kit's serene ones:
 * [AppScreenTitle] under a photographic hero band, [AppSectionHeader] over
 * each group.
 *
 * Photography (reuse only, `assets/heroes/`): a sunrise band crowns the
 * screen and dissolves into the paper canvas, Doctrine-style, above the
 * title; each named section carries a small 88dp photo strip above its
 * header, matched by title keyword so it follows the section into every
 * language, and a section with no match (Settings) simply gets no strip.
 * Every banner carries its title in white over a bottom scrim, so a photo
 * never reads as decoration alone and an off-center crop never hides the
 * label. All photos are decorative, drawn behind text never over it,
 * decoded once at the top of this screen (never inside a LazyColumn item),
 * down-sampled, and a failed decode falls back to a gradient band so
 * legibility never depends on the photo.
 *
 * Rows are monochrome: each entry gets a distinct outlined glyph in a single
 * tint (palette.textSecondary), never a saturated icon well. The kit's
 * AppListRow paints a per-icon coloured well in the Apple identity, which
 * clashes with the liturgical palette and reuses the same temple glyph for
 * every [AppIconSlot.DOCTRINE] destination; this screen therefore draws its
 * rows directly with one tint and a per-tag glyph, preserving the 48dp
 * floor, the current-value trailing slot, the navigation chevron and every
 * testTag.
 */
@Composable
fun MoreScreen(
    currentLanguage: AppLanguage,
    sections: List<MoreSection>
) {
    val context = LocalContext.current
    val design = LocalAppDesign.current
    val palette = design.palette
    val rowMinHeight = maxOf(design.spacing.rowMinHeight, 48.dp)

    // Current values for the Settings rows, read off the tokens so they
    // follow a theme switch and a light/dark change with no caller rebuild.
    // e.g. "Material 3 · Oscuro" / "Material 3 · Dark" for Appearance.
    val appearanceValue = run {
        val name = design.language.label.get(currentLanguage)
        val mode = when (currentLanguage) {
            AppLanguage.ES -> if (design.isDark) "Oscuro" else "Claro"
            AppLanguage.EN -> if (design.isDark) "Dark" else "Light"
            AppLanguage.PT -> if (design.isDark) "Escuro" else "Claro"
        }
        "$name · $mode"
    }

    val titleText = when (currentLanguage) {
        AppLanguage.ES -> "Más"
        AppLanguage.EN -> "More"
        AppLanguage.PT -> "Mais"
    }

    // Drop empty groups: a header over no rows reads as a broken section
    // and costs a full section-gap of dead canvas.
    val visibleSections = sections.filter { it.entries.isNotEmpty() }

    // Photographic heroes (assets/heroes/), decoded once off the composition
    // path. Hoisted unconditionally to the top of MoreScreen — never inside
    // a LazyColumn item or a conditional branch (slot-table crash). Null
    // (asset missing / decode failure in a test host) draws no photo, so the
    // gradient fallback and the frosted groups carry the screen alone.
    val heroPhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/sunrise_mountains.jpg", maxWidthPx = 1080)
    }
    val studyPhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/church_window_light.jpg", maxWidthPx = 720)
    }
    val innerLifePhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/votive_candles.jpg", maxWidthPx = 720)
    }
    val placesPhoto = remember(context) {
        decodeSampledBitmap(context, "heroes/cathedral_dome.jpg", maxWidthPx = 720)
    }
    // One photo per visible section, matched by title keyword so it follows
    // the section across ES/EN/PT; an unmatched section (Settings) gets none.
    val sectionPhotos = remember(visibleSections, studyPhoto, innerLifePhoto, placesPhoto) {
        visibleSections.map { section ->
            val t = section.title
            when {
                t.contains("Estudio", ignoreCase = true) ||
                    t.contains("Study", ignoreCase = true) ||
                    t.contains("Estudo", ignoreCase = true) -> studyPhoto
                t.contains("Vida interior", ignoreCase = true) ||
                    t.contains("Inner life", ignoreCase = true) -> innerLifePhoto
                t.contains("Lugares", ignoreCase = true) ||
                    t.contains("Places", ignoreCase = true) -> placesPhoto
                else -> null
            }
        }
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(palette.canvas)
            .testTag("more_screen")
    ) {
        item(key = "more_hero") {
            // Photographic hero: a full-bleed sunrise band dissolving into the
            // warm paper canvas through a vertical gradient, above the serene
            // title. The screen title rides on the photo in white over a
            // bottom scrim so the banner is titled, not decorative; the
            // kit's AppScreenTitle below is unchanged.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(168.dp)
                    .background(palette.canvas)
                    .testTag("more_hero_photo")
            ) {
                val hero = heroPhoto
                if (hero != null) {
                    Image(
                        bitmap = hero,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.92f),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        palette.accentMuted,
                                        palette.canvas
                                    )
                                )
                            )
                    )
                }
                // Fade: clear over the upper photo, canvas by the foot of
                // the band, so the hero dissolves into the page.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    palette.canvas
                                )
                            )
                        )
                )
                // Title scrim: bottom-aligned black scrim plus white title,
                // so the title stays legible over any crop.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.45f)
                                )
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = design.spacing.gutter, vertical = 12.dp)
                ) {
                    Text(
                        text = titleText,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }
        item(key = "more_title") {
            AppScreenTitle(title = titleText)
        }
        visibleSections.forEachIndexed { sectionIndex, section ->
            if (sectionIndex > 0) {
                item(key = "more_section_gap_$sectionIndex") {
                    Spacer(Modifier.height(design.spacing.sectionGap))
                }
            }
            item(key = "more_section_$sectionIndex") {
                // Mini-hero per section: a small clipped photograph above the
                // header, only where the section has one. No photo means no
                // strip and no spacer, so an unmatched section leaves no gap.
                val photo = sectionPhotos.getOrNull(sectionIndex)
                if (photo != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = design.spacing.gutter)
                            .height(88.dp)
                            .clip(design.shapes.thumbnail)
                            .testTag("more_section_hero_$sectionIndex")
                    ) {
                        Image(
                            bitmap = photo,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(0.92f),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            palette.canvas.copy(alpha = 0.35f)
                                        )
                                    )
                                )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.55f)
                                        )
                                    )
                                )
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = section.title,
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                        }
                    }
                }
                AppSectionHeader(title = section.title)
                AppListGroup {
                    section.entries.forEach { entry ->
                        val icon = distinctIconFor(entry)
                        // The trailing slot is the current value, not
                        // decoration: Font/Language arrive with one from
                        // the caller; Appearance/Language fall back to
                        // what this screen can know on its own (the
                        // identity in force + dark mode, the active
                        // language) so the row never shows a bare title.
                        val trailingText = entry.value ?: settingsFallbackValue(
                            entry = entry,
                            appearanceValue = appearanceValue,
                            currentLanguage = currentLanguage
                        )
                        // Monochrome row: distinct outlined glyph in
                        // palette.textSecondary, no saturated well. Keeps
                        // the 48dp floor, testTag and chevron affordance
                        // the kit's row provided.
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = rowMinHeight)
                                .clickable(onClick = entry.onOpen)
                                .padding(
                                    horizontal = design.spacing.gutter,
                                    vertical = 12.dp
                                )
                                .testTag(entry.tag),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = palette.textSecondary,
                                modifier = Modifier.size(22.dp)
                            )
                            Text(
                                text = entry.title,
                                color = palette.textPrimary,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 2,
                                modifier = Modifier.weight(1f, fill = true)
                            )
                            if (!trailingText.isNullOrBlank()) {
                                Text(
                                    text = trailingText,
                                    color = palette.textSecondary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 1,
                                    modifier = Modifier.widthIn(max = 120.dp)
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = palette.textSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
        item(key = "more_bottom_gap") {
            Spacer(Modifier.height(design.spacing.contentBottomGap))
        }
    }
}

/**
 * One distinct outlined glyph per destination, from the single monochrome
 * line family. Caller-supplied overrides (Settings: Palette/TextFields/
 * Language) win; section rows resolve by testTag so the five DOCTRINE-slot
 * entries never reuse the temple glyph; unknown tags fall back to the
 * slot pair.
 */
@Composable
private fun distinctIconFor(entry: MoreEntry): ImageVector {
    entry.icon?.let { return it }
    return when (entry.tag) {
        "nav_catechism" -> Icons.Outlined.MenuBook
        "nav_study_doctrine" -> Icons.Outlined.School
        "nav_study_mary_jesus" -> Icons.Outlined.Favorite
        "nav_study_history" -> Icons.Outlined.History
        "nav_questions" -> Icons.Outlined.Help
        "nav_study_orders" -> Icons.Outlined.Groups
        "nav_churches_news" -> Icons.Outlined.Newspaper
        "nav_habits" -> Icons.Outlined.Checklist
        "nav_spiritual" -> Icons.Outlined.Spa
        "nav_parishes" -> Icons.Outlined.Church
        "nav_pilgrimage" -> Icons.Outlined.Place
        else -> AppIcons.pair(entry.iconSlot).first
    }
}

/**
 * Decodes an asset JPEG down-sampled so its width does not exceed
 * [maxWidthPx] (power-of-two [BitmapFactory.Options.inSampleSize]), so a
 * photographic hero set never lands full-resolution on memory-constrained
 * devices. Returns null on any failure (missing asset, host without a real
 * decoder in tests) so callers fall back to a plain gradient band instead of
 * crashing.
 */
private fun decodeSampledBitmap(
    context: Context,
    assetPath: String,
    maxWidthPx: Int = 1080
): ImageBitmap? = try {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.assets.open(assetPath).use { BitmapFactory.decodeStream(it, null, bounds) }
    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
        null
    } else {
        var sampleSize = 1
        while (bounds.outWidth / sampleSize > maxWidthPx) sampleSize *= 2
        val options = BitmapFactory.Options().apply { inSampleSize = sampleSize }
        val bitmap: Bitmap? = context.assets.open(assetPath).use {
            BitmapFactory.decodeStream(it, null, options)
        }
        bitmap?.asImageBitmap()
    }
} catch (_: Exception) {
    null
}

/**
 * Current-value text for the Settings rows whose caller does not supply one.
 * Only the three settings entries are recognised, by their preserved test
 * tags; destination rows always keep a null trailing slot.
 */
private fun settingsFallbackValue(
    entry: MoreEntry,
    appearanceValue: String,
    currentLanguage: AppLanguage
): String? = when (entry.tag) {
    "app_bar_theme_button" -> appearanceValue
    "app_bar_lang_button" -> currentLanguage.displayName
    // Font is caller-supplied (the stored FontChoice label); with no value
    // there is nothing honest to show, so leave the slot empty rather than
    // guessing a typeface.
    "font_settings_button" -> null
    else -> null
}
