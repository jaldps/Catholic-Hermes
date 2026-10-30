@file:Suppress("DEPRECATION")

package com.example.ui.design

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.Church
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material.icons.sharp.AutoStories
import androidx.compose.material.icons.sharp.Church
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.MenuBook
import androidx.compose.material.icons.sharp.Newspaper
import androidx.compose.material.icons.sharp.Place
import androidx.compose.material.icons.sharp.SelfImprovement
import androidx.compose.material.icons.sharp.Shield
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material.icons.sharp.VolunteerActivism
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ui.theme.IconVariant
import com.example.ui.theme.LocalAppDesign

/** The navigation glyphs the app needs, independent of any one icon style. */
enum class AppIconSlot {
    HOME, HABITS, BIBLE, CATECHISM, ROSARY, PRAYERS, PILGRIMAGE, DOCTRINE, NEWS, JOURNAL
}

/**
 * Resolves a slot to the icon style the active identity calls for.
 *
 * The idioms differ in more than line weight. iOS pairs an outlined glyph in
 * the resting state with a filled one when selected; Material 3 does the same
 * but with its rounded cut; the Vatican identity uses sharp, unrounded glyphs
 * throughout, because nothing else in that identity has a rounded corner.
 */
object AppIcons {

    @Composable
    @ReadOnlyComposable
    fun pair(slot: AppIconSlot): Pair<ImageVector, ImageVector> {
        val variant = LocalAppDesign.current.decor.iconVariant
        val resting = when (variant) {
            IconVariant.OUTLINED -> outlined(slot)
            IconVariant.ROUNDED -> outlined(slot)
            IconVariant.SHARP -> sharp(slot)
        }
        val active = when (variant) {
            IconVariant.OUTLINED -> filled(slot)
            IconVariant.ROUNDED -> rounded(slot)
            IconVariant.SHARP -> sharp(slot)
        }
        return resting to active
    }

    private fun outlined(slot: AppIconSlot): ImageVector = when (slot) {
        AppIconSlot.HOME -> Icons.Outlined.Home
        AppIconSlot.HABITS -> Icons.Outlined.Shield
        AppIconSlot.BIBLE -> Icons.Outlined.AutoStories
        AppIconSlot.CATECHISM -> Icons.Outlined.MenuBook
        AppIconSlot.ROSARY -> Icons.Outlined.SelfImprovement
        AppIconSlot.PRAYERS -> Icons.Outlined.VolunteerActivism
        AppIconSlot.PILGRIMAGE -> Icons.Outlined.Place
        AppIconSlot.DOCTRINE -> Icons.Outlined.Church
        AppIconSlot.NEWS -> Icons.Outlined.Newspaper
        AppIconSlot.JOURNAL -> Icons.Outlined.Star
    }

    private fun rounded(slot: AppIconSlot): ImageVector = when (slot) {
        AppIconSlot.HOME -> Icons.Rounded.Home
        AppIconSlot.HABITS -> Icons.Rounded.Shield
        AppIconSlot.BIBLE -> Icons.Rounded.AutoStories
        AppIconSlot.CATECHISM -> Icons.Rounded.MenuBook
        AppIconSlot.ROSARY -> Icons.Rounded.SelfImprovement
        AppIconSlot.PRAYERS -> Icons.Rounded.VolunteerActivism
        AppIconSlot.PILGRIMAGE -> Icons.Rounded.Place
        AppIconSlot.DOCTRINE -> Icons.Rounded.Church
        AppIconSlot.NEWS -> Icons.Rounded.Newspaper
        AppIconSlot.JOURNAL -> Icons.Rounded.Star
    }

    private fun sharp(slot: AppIconSlot): ImageVector = when (slot) {
        AppIconSlot.HOME -> Icons.Sharp.Home
        AppIconSlot.HABITS -> Icons.Sharp.Shield
        AppIconSlot.BIBLE -> Icons.Sharp.AutoStories
        AppIconSlot.CATECHISM -> Icons.Sharp.MenuBook
        AppIconSlot.ROSARY -> Icons.Sharp.SelfImprovement
        AppIconSlot.PRAYERS -> Icons.Sharp.VolunteerActivism
        AppIconSlot.PILGRIMAGE -> Icons.Sharp.Place
        AppIconSlot.DOCTRINE -> Icons.Sharp.Church
        AppIconSlot.NEWS -> Icons.Sharp.Newspaper
        AppIconSlot.JOURNAL -> Icons.Sharp.Star
    }

    private fun filled(slot: AppIconSlot): ImageVector = when (slot) {
        AppIconSlot.HOME -> Icons.Filled.Home
        AppIconSlot.HABITS -> Icons.Filled.Shield
        AppIconSlot.BIBLE -> Icons.Filled.AutoStories
        AppIconSlot.CATECHISM -> Icons.Filled.MenuBook
        AppIconSlot.ROSARY -> Icons.Filled.SelfImprovement
        AppIconSlot.PRAYERS -> Icons.Filled.VolunteerActivism
        AppIconSlot.PILGRIMAGE -> Icons.Filled.Place
        AppIconSlot.DOCTRINE -> Icons.Filled.Church
        AppIconSlot.NEWS -> Icons.Filled.Newspaper
        AppIconSlot.JOURNAL -> Icons.Filled.Star
    }
}
