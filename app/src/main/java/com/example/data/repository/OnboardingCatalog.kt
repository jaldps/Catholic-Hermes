package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.models.LocalizedText
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

/**
 * One reviewed suggestion of the welcome profiles. [destination] is the name of an `AppDestination`; the data layer
 * keeps it as text and the screen resolves it, so a stale name can never crash navigation.
 */
data class OnboardingSuggestion(val id: String, val destination: String, val minutes: Int, val title: LocalizedText, val why: LocalizedText)

/** A welcome profile: [index] is the value `BeginnerJourneyStore` keeps (0 exploring, 1 returning, 2 practicing). */
data class OnboardingProfile(
    val id: String,
    val index: Int,
    val welcome: LocalizedText,
    val description: LocalizedText,
    val suggestions: List<OnboardingSuggestion>
)

internal data class RawOnboardingSuggestion(
    val id: String? = null, val destination: String? = null, val minutes: Int? = null, val title: RawLT? = null, val why: RawLT? = null
)
internal data class RawOnboardingProfile(
    val id: String? = null, val index: Int? = null, val welcome: RawLT? = null, val description: RawLT? = null,
    val suggestions: List<RawOnboardingSuggestion>? = null
)
internal data class RawOnboarding(val profiles: List<RawOnboardingProfile>? = null)

/** The reviewed `onboarding/profiles.json` is the sole source of the welcome texts and suggestions; nothing is generated. */
class OnboardingCatalog(private val open: () -> InputStream) {
    companion object {
        const val FILE = "profiles.json"
        val MINUTES = listOf(2, 5, 10)
        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: OnboardingCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled = OnboardingCatalog {
            assets?.open("onboarding/$FILE")
                ?: OnboardingCatalog::class.java.classLoader!!.getResourceAsStream(FILE)
                ?: error("Onboarding asset unavailable")
        }
        val default: OnboardingCatalog get() = override ?: bundled
    }

    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RawOnboarding::class.java)
    @Volatile private var cached: List<OnboardingProfile>? = null

    /** What a previous [load] kept, so a screen that returns does not wait for the disk again. */
    val loaded: List<OnboardingProfile>? get() = cached

    private fun RawLT?.text(): LocalizedText =
        LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
            .also { require(it.es.isNotBlank() && it.en.isNotBlank() && it.pt.isNotBlank()) }

    private fun parse(): List<OnboardingProfile> {
        val raw = open().bufferedReader().use { requireNotNull(adapter.fromJson(it.readText())) }
        val idPattern = Regex("[a-z0-9_]{1,60}")
        val profiles = requireNotNull(raw.profiles).map { p ->
            val suggestions = requireNotNull(p.suggestions).map { s ->
                OnboardingSuggestion(
                    id = requireNotNull(s.id?.takeIf { it.matches(idPattern) }),
                    destination = requireNotNull(s.destination?.takeIf { it.matches(Regex("[A-Z][A-Z0-9_]{1,40}")) }),
                    minutes = requireNotNull(s.minutes?.takeIf { it in MINUTES }),
                    title = s.title.text(),
                    why = s.why.text()
                )
            }
            require(suggestions.isNotEmpty() && suggestions.map { it.id }.distinct().size == suggestions.size)
            OnboardingProfile(
                id = requireNotNull(p.id?.takeIf { it.matches(idPattern) }),
                index = requireNotNull(p.index?.takeIf { it in 0..2 }),
                welcome = p.welcome.text(), description = p.description.text(), suggestions = suggestions
            )
        }
        require(profiles.map { it.index }.distinct().size == profiles.size && profiles.map { it.id }.distinct().size == profiles.size)
        require(profiles.flatMap { it.suggestions }.map { it.id }.let { it.distinct().size == it.size })
        return profiles.sortedBy { it.index }
    }

    /**
     * Called on IO by the screen. A failure is logged with a fixed event, returns null (no invented content) and
     * stays retryable; only a complete catalog is kept.
     */
    fun load(): List<OnboardingProfile>? = cached ?: try {
        parse().also { cached = it }
    } catch (e: Exception) { AppLog.failure(LogEvent.ONBOARDING_CATALOG, e); null }

    /** The profile stored by `BeginnerJourneyStore` (0..2), or null when the catalog is unavailable. */
    fun profile(index: Int): OnboardingProfile? = load()?.firstOrNull { it.index == index }
}

/** Pure ordering of a profile's suggestions for the time the person chose. Nothing is dropped. */
object OnboardingOrder {
    /**
     * Suggestions whose `minutes` equals [minutes] come first, then the shorter ones (the longest of them first, so
     * the nearest fit leads), then the longer ones (the shortest first). Within one duration the reviewed order holds.
     */
    fun order(suggestions: List<OnboardingSuggestion>, minutes: Int): List<OnboardingSuggestion> =
        suggestions.withIndex().sortedWith(
            compareBy<IndexedValue<OnboardingSuggestion>>(
                { if (it.value.minutes == minutes) 0 else if (it.value.minutes < minutes) 1 else 2 },
                { if (it.value.minutes < minutes) -it.value.minutes else it.value.minutes },
                { it.index }
            )
        ).map { it.value }
}
