package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.models.LocalizedText
import com.example.data.models.VaticanPrayer
import com.squareup.moshi.Moshi
import java.io.InputStream

// ---------------------------------------------------------------------------
// «Otras coronillas»: the 55 curated chaplets of assets/chaplets/*.json
// (contract: docs/content/CORONILLAS-SPEC.md). Read-only data; nothing here
// is generated or paraphrased. The prayer texts that the files cite by id
// (`prayerRef`) are resolved against the app's own prayers.
// ---------------------------------------------------------------------------

/** Where a chaplet is listed in the catalogue. */
enum class ChapletGroup(val key: String) {
    GOD_CHRIST("god_christ"), MARY("mary"), SAINTS("saints"), ANGELS("angels"), DEPARTED("departed"), INTENTION("intention");

    companion object {
        fun parse(key: String?): ChapletGroup? = entries.firstOrNull { it.key == key }
    }
}

enum class ChapletBeadKind { CROSS, MEDAL, LARGE, SMALL, SEPARATOR, KNOT;

    companion object {
        fun parse(key: String?): ChapletBeadKind? = entries.firstOrNull { it.name.equals(key, ignoreCase = true) }
    }
}

/** A run of [count] equal beads of the instrument, in order along the strand. */
data class ChapletBeadGroup(val kind: ChapletBeadKind, val count: Int = 1, val color: Long? = null, val label: LocalizedText? = null)

/** What a step says: a prayer of the app by id, the chaplet's own text, or versicle and response. */
sealed interface ChapletContent {
    data class PrayerRef(val id: String) : ChapletContent
    data class Text(val text: LocalizedText) : ChapletContent
    data class Vr(val v: LocalizedText, val r: LocalizedText) : ChapletContent
}

/**
 * One line of the sequence. [bead] indexes the chaplet's bead groups, or is null when the step goes
 * off the strand. Every one of its [repeat] sayings advances one bead if the group has several.
 */
data class ChapletStep(
    val bead: Int?,
    val content: ChapletContent,
    val repeat: Int = 1,
    val meditation: LocalizedText? = null,
    val rubric: LocalizedText? = null
)

/** `promise` / `historicalNote`: only ever shown in the information sheet, with the source. */
data class ChapletNote(val text: LocalizedText, val source: String)

data class ChapletSource(val title: String, val url: String)

enum class ChapletVariable(val key: String) {
    DECEASED_NAME("deceased_name"), DECEASED_GENDER("deceased_gender"), INTENTION("intention");

    companion object {
        fun parse(key: String?): ChapletVariable? = entries.firstOrNull { it.key == key }
    }
}

data class Chaplet(
    val id: String,
    val sourceId: String,
    val group: ChapletGroup,
    val title: LocalizedText,
    val summary: LocalizedText,
    val instrument: LocalizedText,
    /** Key of `assets/chaplets/images/<image>.jpg`. */
    val image: String,
    val whenToPray: LocalizedText,
    /** ISO weekdays (1 = Monday … 7 = Sunday) the chaplet is suggested on. */
    val weekdays: List<Int>,
    val season: String?,
    val variables: List<ChapletVariable>,
    val beads: List<ChapletBeadGroup>,
    val steps: List<ChapletStep>,
    val promise: ChapletNote?,
    val historicalNote: ChapletNote?,
    val sources: List<ChapletSource>,
    val rights: String?,
    val credit: DevotionImageCredit?
) {
    val imagePath: String get() = "chaplets/images/$image.jpg"
    /** Chaplets with no string (Saint Anne's) count prayers instead of beads. */
    val hasBeads: Boolean get() = beads.isNotEmpty()
}

/** The 55 curated ids, in catalogue order. A test keeps this list equal to the folder. */
internal val ChapletIds = listOf(
    "sagrado-corazon-jesus", "sangre-preciosa-corona", "rosario-espiritu-santo", "pasiones-cristo-rosario", "llagas-cristo-coronilla",
    "santisimo-meditar-adorar", "sagrada-eucaristia-33", "santa-faz-coronilla", "santo-nombre-coronilla", "jesus-praga-coronilla",
    "santisima-trinidad-coronilla", "via-crucis-coronilla", "coronilla-liberacion",
    "inmaculado-corazon-maria", "siete-dolores-corona", "alabanzas-maria", "desagravio-maria", "lourdes-rosario", "czestochowa-coronilla",
    "guadalupe-coronilla", "carmen-coronilla", "perpetuo-socorro-rosario", "estrella-mar-coronilla", "coronilla-lagrimas",
    "inmaculada-concepcion-coronilla", "dos-corazones-coronilla", "reina-luz-coronilla", "madre-eucaristia-coronilla",
    "coronilla-san-jose-hozana", "teresita-24-glorias", "san-charbel", "san-antonio-padua-coronilla", "santa-ana-coronilla",
    "santa-dimpna-coronilla", "cabrini-coronilla", "santa-gertrudis-coronilla", "san-judas-coronilla", "kateri-coronilla",
    "maximiliano-coronilla", "padre-pio-coronilla", "san-patricio-coronilla", "santa-filomena-coronilla", "santa-rita-coronilla",
    "familia-franciscana-coronilla",
    "corona-san-miguel-raccolta", "san-gabriel-coronilla", "san-rafael-coronilla",
    "almas-purgatorio-coronilla", "rosario-difuntos-hozana", "coronilla-difuntos-45",
    "confianza-tribulaciones", "conversion-coronilla", "fe-coronilla", "misericordia-perdon", "unidad-coronilla"
)

/** The chaplet that now opens from Orar's «Corona de San Miguel» row. */
const val StMichaelChapletId = "corona-san-miguel-raccolta"

/** Loads the chaplets of the `chaplets` asset folder. A missing or malformed file is skipped, never fatal. */
class ChapletCatalog(private val ids: List<String> = ChapletIds, private val open: (String) -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: ChapletCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled by lazy {
            ChapletCatalog { file ->
                assets?.open("chaplets/$file")
                    ?: ChapletCatalog::class.java.classLoader!!.getResourceAsStream(file)
                    ?: error("Chaplet asset unavailable")
            }
        }
        val default: ChapletCatalog get() = override ?: bundled
    }

    private val any = Moshi.Builder().build().adapter(Any::class.java)

    private fun read(name: String): Any? = try {
        open(name).bufferedReader().use { any.fromJson(it.readText()) }
    } catch (e: Exception) {
        AppLog.failure(LogEvent.CHAPLET_CATALOG, e)
        null
    }

    private val credits: Map<String, DevotionImageCredit> by lazy {
        val root = read("images/credits.json") as? Map<*, *>
        (root?.get("images") as? List<*>).orEmpty().mapNotNull { e ->
            val m = e as? Map<*, *> ?: return@mapNotNull null
            val id = m.str("id") ?: return@mapNotNull null
            id to DevotionImageCredit(
                id = id, title = m.str("title").orEmpty(), author = m.str("author").orEmpty(),
                license = m.str("license").orEmpty(), source = m.str("source").orEmpty(), url = m.str("url").orEmpty(),
                referential = m["referential"] == true, note = m.str("note").orEmpty()
            )
        }.toMap()
    }

    /** All chaplets that loaded, in catalogue order. */
    val all: List<Chaplet> by lazy { ids.mapNotNull { id -> runCatching { parse(id) }.onFailure { AppLog.failure(LogEvent.CHAPLET_CATALOG, it) }.getOrNull() } }
    val byId: Map<String, Chaplet> by lazy { all.associateBy { it.id } }
    fun get(id: String?): Chaplet? = id?.let(byId::get)
    fun inGroup(group: ChapletGroup): List<Chaplet> = all.filter { it.group == group }
    /** The chaplets suggested for [isoWeekday] (1 = Monday … 7 = Sunday). */
    fun forWeekday(isoWeekday: Int): List<Chaplet> = all.filter { isoWeekday in it.weekdays }

    private fun parse(id: String): Chaplet? {
        val d = read("$id.json") as? Map<*, *> ?: return null
        require(d.str("id") == id)
        fun lt(key: String, from: Map<*, *> = d) = from.lt(key)
        val beads = (d["beads"] as? List<*>).orEmpty().map { b ->
            val m = b as Map<*, *>
            ChapletBeadGroup(
                kind = requireNotNull(ChapletBeadKind.parse(m.str("kind"))),
                count = (m["count"] as? Number)?.toInt()?.coerceAtLeast(1) ?: 1,
                color = m.str("color")?.removePrefix("#")?.toLongOrNull(16)?.let { 0xFF000000L or it },
                label = (m["label"] as? Map<*, *>)?.let { l -> LocalizedText(l.str("es").orEmpty(), l.str("en").orEmpty(), l.str("pt").orEmpty()) }
            )
        }
        val steps = (d["steps"] as? List<*>).orEmpty().map { s ->
            val m = s as Map<*, *>
            val content = when {
                m.str("prayerRef") != null -> ChapletContent.PrayerRef(m.str("prayerRef")!!)
                m["text"] != null -> ChapletContent.Text(m.lt("text"))
                else -> (m["vr"] as Map<*, *>).let { ChapletContent.Vr(it.lt("v"), it.lt("r")) }
            }
            ChapletStep(
                bead = (m["bead"] as? Number)?.toInt(),
                content = content,
                repeat = (m["repeat"] as? Number)?.toInt()?.coerceAtLeast(1) ?: 1,
                meditation = if (m["meditation"] != null) m.lt("meditation") else null,
                rubric = if (m["rubric"] != null) m.lt("rubric") else null
            )
        }
        fun note(key: String) = (d[key] as? Map<*, *>)?.let { ChapletNote(it.lt("text"), it.str("source").orEmpty()) }
        val image = d.str("image").orEmpty()
        return Chaplet(
            id = id, sourceId = d.str("sourceId").orEmpty(),
            group = requireNotNull(ChapletGroup.parse(d.str("group"))),
            title = lt("title"), summary = lt("summary"), instrument = lt("instrument"),
            image = image, whenToPray = lt("when"),
            weekdays = (d["weekdays"] as? List<*>).orEmpty().mapNotNull { (it as? Number)?.toInt() },
            season = d.str("season"),
            variables = (d["variables"] as? List<*>).orEmpty().mapNotNull { ChapletVariable.parse(it as? String) },
            beads = beads, steps = steps,
            promise = note("promise"), historicalNote = note("historicalNote"),
            sources = (d["sources"] as? List<*>).orEmpty().mapNotNull { s ->
                (s as? Map<*, *>)?.let { ChapletSource(it.str("title").orEmpty(), it.str("url").orEmpty()) }
            },
            rights = d.str("rights"),
            credit = credits[image]
        )
    }
}

private fun Map<*, *>.str(key: String): String? = (this[key] as? String)?.takeIf { it.isNotBlank() }
private fun Map<*, *>.lt(key: String): LocalizedText = (this[key] as? Map<*, *>).let {
    LocalizedText(it?.str("es").orEmpty(), it?.str("en").orEmpty(), it?.str("pt").orEmpty())
}

/** Finds a prayer of the app by the id a chaplet cites. */
fun interface ChapletPrayers {
    fun find(id: String): VaticanPrayer?

    companion object {
        fun of(prayers: List<VaticanPrayer>): ChapletPrayers {
            val byId = prayers.associateBy { it.id }
            return ChapletPrayers { byId[it] }
        }
    }
}
