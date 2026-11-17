package com.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.repository.OnboardingCatalog
import com.example.data.repository.OnboardingOrder
import com.example.data.repository.OnboardingProfile
import com.example.data.repository.OnboardingSuggestion
import com.example.ui.AppDestination
import com.example.ui.design.AppListGroup
import com.example.ui.design.AppListRow
import com.example.ui.design.AppSectionHeader
import com.example.ui.design.AppSheet
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Suggestions Home shows at most; «Ver todo» opens the rest. */
internal const val FOR_YOU_ON_HOME = 3

/** A reviewed suggestion whose destination exists in this build. */
internal data class ForYouItem(val suggestion: OnboardingSuggestion, val destination: AppDestination)

/** The reviewed profiles, read once off the main thread; null while loading or when the asset is unusable (nothing is invented). */
@Composable
internal fun rememberOnboardingProfiles(): List<OnboardingProfile>? {
    val catalog = OnboardingCatalog.default
    val profiles by produceState(catalog.loaded, catalog) {
        if (value == null) value = withContext(Dispatchers.IO) { catalog.load() }
    }
    return profiles
}

/** The profile's suggestions in the order for [minutes]; a name this build cannot open is left out. */
internal fun forYouItems(profile: OnboardingProfile?, minutes: Int): List<ForYouItem> =
    OnboardingOrder.order(profile?.suggestions.orEmpty(), minutes).mapNotNull { s ->
        AppDestination.entries.firstOrNull { it.name == s.destination }?.let { ForYouItem(s, it) }
    }

/** What Home lists: the first few, without a pointer to Home itself, which is where the person already is. */
internal fun forYouOnHome(items: List<ForYouItem>): List<ForYouItem> =
    items.filter { it.destination != AppDestination.HOME }.take(FOR_YOU_ON_HOME)

private fun forYouTitle(language: AppLanguage) = language.journey("Para ti hoy", "For you today", "Para você hoje")
private fun seeAll(language: AppLanguage) = language.journey("Ver todo", "See all", "Ver tudo")

@Composable
private fun ForYouRow(language: AppLanguage, item: ForYouItem, tag: String, onOpen: (AppDestination) -> Unit) {
    // Very large text leaves no room beside the text for the minutes: they lead the subtitle instead.
    val compact = LocalAppTextStyles.current.subhead.fontSize.value * LocalDensity.current.fontScale > 18.5f
    val minutes = "${item.suggestion.minutes} min"
    AppListRow(
        title = item.suggestion.title.get(language),
        subtitle = if (compact) "$minutes · ${item.suggestion.why.get(language)}" else item.suggestion.why.get(language),
        trailingText = if (compact) null else minutes,
        onClick = { onOpen(item.destination) },
        modifier = Modifier.testTag(tag)
    )
}

/** Home's «Para ti hoy»: [items] are already ordered and cut. `AppListGroup` applies the screen gutter itself. */
@Composable
internal fun ForYouToday(language: AppLanguage, items: List<ForYouItem>, onOpen: (AppDestination) -> Unit, onSeeAll: () -> Unit) {
    Column(Modifier.testTag("home_for_you")) {
        AppSectionHeader(title = forYouTitle(language))
        AppListGroup {
            items.forEach { ForYouRow(language, it, "for_you_${it.suggestion.id}", onOpen) }
            AppListRow(title = seeAll(language), onClick = onSeeAll, modifier = Modifier.testTag("for_you_all"))
        }
    }
}

/** Every suggestion of the profile, in the same order. */
@Composable
internal fun ForYouSheet(language: AppLanguage, items: List<ForYouItem>, onOpen: (AppDestination) -> Unit, onDismiss: () -> Unit) {
    AppSheet(onDismiss = onDismiss, title = forYouTitle(language), tag = "for_you_sheet") {
        AppListGroup {
            items.forEach { ForYouRow(language, it, "for_you_sheet_${it.suggestion.id}", onOpen) }
        }
        Spacer(Modifier.height(8.dp))
    }
}
