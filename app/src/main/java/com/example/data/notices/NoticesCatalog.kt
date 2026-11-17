package com.example.data.notices

import android.content.Context
import android.content.res.AssetManager
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.liturgy.LiturgicalSeason
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

/** One reviewed notice text (T-035): a short title, a body and, optionally, the screen a tap opens. */
data class NoticeText(val title: LocalizedText, val body: LocalizedText, val action: String? = null, val id: String? = null)

data class SeasonNotices(val start: NoticeText, val weekly: List<NoticeText>)

data class CelebrationNotices(val eve: NoticeText, val day: NoticeText)

/** The whole of `assets/notices/notices.json`, validated. */
data class Notices(
    val seasons: Map<LiturgicalSeason, SeasonNotices>,
    val celebrations: Map<String, CelebrationNotices>,
    val reminders: Map<String, NoticeText>
)

internal data class RawNoticeLT(val es: String? = null, val en: String? = null, val pt: String? = null)
internal data class RawNotice(val id: String? = null, val title: RawNoticeLT? = null, val body: RawNoticeLT? = null, val action: String? = null)
internal data class RawSeasonNotices(val start: RawNotice? = null, val weekly: List<RawNotice>? = null)
internal data class RawCelebrationNotices(val eve: RawNotice? = null, val day: RawNotice? = null)
internal data class RawNotices(
    val seasons: Map<String, RawSeasonNotices>? = null,
    val celebrations: Map<String, RawCelebrationNotices>? = null,
    val reminders: Map<String, RawNotice>? = null
)

/**
 * The reviewed notice texts are the only source of what a liturgical notice says; nothing is composed here. Same
 * pattern as the other catalogs: [install] at application start, [default] for callers, [override] for tests. A file
 * that fails validation is logged ([LogEvent.NOTICE_CATALOG]) and yields no notices, never half a catalog.
 */
class NoticesCatalog(private val open: () -> InputStream) {
    companion object {
        /** The destinations a notice may open (T-035 list); MY_PATH is used by the widget only. */
        val ACTIONS = setOf("HOME", "BIBLE", "CATECHISM", "ROSARY", "PRAYERS", "SAINTS", "SPIRITUAL_LIFE",
            "CONFESSION", "PLANS", "HABITS_VIRTUES")
        val REMINDER_KEYS = setOf("sunday_mass", "holy_day_of_obligation", "novena_day", "plan_day",
            "rosary_evening", "examen_night", "confession_gentle")
        private val PLACEHOLDERS = mapOf("novena_day" to listOf("{{novena}}", "{{day}}"), "plan_day" to listOf("{{plan}}", "{{day}}"))

        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: NoticesCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled = NoticesCatalog {
            assets?.open("notices/notices.json")
                ?: NoticesCatalog::class.java.classLoader!!.getResourceAsStream("notices.json")
                ?: error("Notice asset unavailable")
        }
        val default: NoticesCatalog get() = override ?: bundled
    }

    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RawNotices::class.java)
    @Volatile private var cached: Notices? = null

    private fun RawNoticeLT?.lt(where: String): LocalizedText {
        val text = LocalizedText(this?.es.orEmpty().trim(), this?.en.orEmpty().trim(), this?.pt.orEmpty().trim())
        require(text.es.isNotEmpty() && text.en.isNotEmpty() && text.pt.isNotEmpty()) { "$where: missing language" }
        return text
    }

    private fun RawNotice?.notice(where: String, needsAction: Boolean): NoticeText {
        requireNotNull(this) { "$where: missing" }
        require(action == null || action in ACTIONS) { "$where: unknown action $action" }
        require(!needsAction || action != null) { "$where: missing action" }
        return NoticeText(title.lt("$where.title"), body.lt("$where.body"), action, id)
    }

    private fun parse(): Notices {
        val raw = open().bufferedReader().use { requireNotNull(adapter.fromJson(it.readText())) }
        val seasons = LiturgicalSeason.entries.associateWith { season ->
            val entry = requireNotNull(raw.seasons?.get(season.name)) { "season ${season.name} missing" }
            val weekly = requireNotNull(entry.weekly).mapIndexed { i, w -> w.notice("${season.name}.weekly[$i]", needsAction = false) }
            require(weekly.size in 4..6) { "${season.name}: 4-6 weekly notices" }
            SeasonNotices(entry.start.notice("${season.name}.start", needsAction = false), weekly)
        }
        val celebrations = requireNotNull(raw.celebrations).mapValues { (key, value) ->
            require(key.matches(Regex("[a-z][a-z0-9_]*"))) { "bad celebration key $key" }
            CelebrationNotices(value.eve.notice("$key.eve", needsAction = false), value.day.notice("$key.day", needsAction = false))
        }
        require(NoticeCelebrations.ALL.all { it in celebrations }) {
            "celebrations missing: " + NoticeCelebrations.ALL.filter { it !in celebrations }
        }
        val reminders = REMINDER_KEYS.associateWith { key -> raw.reminders?.get(key).notice("reminders.$key", needsAction = false) }
        PLACEHOLDERS.forEach { (key, markers) ->
            AppLanguage.entries.forEach { lang -> markers.forEach { m -> require(m in reminders.getValue(key).body.get(lang)) { "$key: $m" } } }
        }
        val ids = seasons.values.flatMap { s -> s.weekly.mapNotNull { it.id } }
        require(ids.distinct().size == ids.size) { "duplicate weekly ids" }
        return Notices(seasons, celebrations, reminders)
    }

    /** Parsed once and kept; a failure is logged and retried on the next call. */
    fun load(): Notices? = cached ?: try { parse().also { cached = it } }
        catch (e: Exception) { AppLog.failure(LogEvent.NOTICE_CATALOG, e); null }
}
