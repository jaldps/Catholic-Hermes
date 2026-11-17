package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import kotlin.math.abs

/** The levels the Catechism itself uses; [HEADING] is an untitled-number division such as "The Creeds" or "Amen". */
enum class CatechismNodeKind { PROLOGUE, PART, SECTION, CHAPTER, ARTICLE, PARAGRAPH, HEADING }

/**
 * One division of the Catechism with the inclusive range of paragraphs it spans.
 *
 * [ordinal] is the number the book prints for the division (Article 9, Chapter 3…), read from the
 * stable id ("p1s2c3a9" -> 9). Titles carry no numbers, so labels are composed from [kind] and [ordinal].
 * It is null for the prologue, for headings and for the degraded outline fallback.
 */
data class CatechismNode(
    val id: String,
    val kind: CatechismNodeKind,
    val title: LocalizedText,
    val range: IntRange,
    val children: List<CatechismNode> = emptyList(),
    val depth: Int = 0,
    val ordinal: Int? = null
) {
    val first: Int get() = range.first
    val last: Int get() = range.last
    val paragraphCount: Int get() = range.last - range.first + 1
}

/** The whole tree plus the lookups the screens need. Immutable. */
class CatechismStructure internal constructor(
    val nodes: List<CatechismNode>,
    /** False when the reviewed asset could not be read and the hand-written [CatechismOutline] stands in. */
    val fromAsset: Boolean
) {
    /** Every node, parents before children, in reading order. */
    val all: List<CatechismNode> = buildList { fun walk(n: CatechismNode) { add(n); n.children.forEach(::walk) }; nodes.forEach(::walk) }
    private val byId: Map<String, CatechismNode> = all.associateBy { it.id }
    private val parents: Map<String, CatechismNode> = buildMap { all.forEach { p -> p.children.forEach { put(it.id, p) } } }

    fun node(id: String): CatechismNode? = byId[id]
    fun parent(node: CatechismNode): CatechismNode? = parents[node.id]

    /** The path of nodes containing paragraph [paragraph], outermost first (Part › Section › Chapter › Article). */
    fun breadcrumb(paragraph: Int): List<CatechismNode> {
        val path = ArrayList<CatechismNode>(5)
        var level = nodes
        while (true) {
            val hit = level.firstOrNull { paragraph in it.range } ?: break
            path += hit
            level = hit.children
        }
        return path
    }

    fun deepestNode(paragraph: Int): CatechismNode? = breadcrumb(paragraph).lastOrNull()

    /** All the nodes at one depth (0 = prologue and parts). */
    fun nodesAtDepth(depth: Int): List<CatechismNode> = all.filter { it.depth == depth }

    fun nodesOfKind(kind: CatechismNodeKind): List<CatechismNode> = all.filter { it.kind == kind }

    /** The outermost node spanning exactly [first]..[last]; an only child can share its parent's range. */
    fun nodeForRange(first: Int, last: Int): CatechismNode? = all.firstOrNull { it.range.first == first && it.range.last == last }

    /**
     * The nodes to name a stretch of reading by: the deepest node that holds the whole stretch, or, when the
     * stretch crosses a boundary, the deepest node at each end (at most two, the outer one alone if one holds the other).
     */
    fun titleNodes(range: IntRange): List<CatechismNode> {
        val a = deepestNode(range.first) ?: return emptyList()
        val b = deepestNode(range.last) ?: return listOf(a)
        return when {
            a.id == b.id -> listOf(a)
            isAncestor(a, b) -> listOf(a)
            isAncestor(b, a) -> listOf(b)
            else -> listOf(a, b)
        }
    }

    private fun isAncestor(candidate: CatechismNode, of: CatechismNode): Boolean {
        var p = parents[of.id]
        while (p != null) { if (p.id == candidate.id) return true; p = parents[p.id] }
        return false
    }

    /**
     * The stretch of reading that follows (or precedes) [first]..[last] at the same level of the book when
     * that range is a node, else the neighbouring division. A node's own opening paragraphs (before its
     * first child) are a stretch of their own.
     */
    fun adjacent(first: Int, last: Int, forward: Boolean): IntRange? {
        val target = if (forward) last + 1 else first - 1
        if (target !in 1..TOTAL_PARAGRAPHS) return null
        val depth = nodeForRange(first, last)?.depth
        val candidates = all.filter { if (forward) it.range.first == target else it.range.last == target }
        val pick = if (depth != null) candidates.minWithOrNull(compareBy<CatechismNode>({ abs(it.depth - depth) }, { it.depth }))
        else candidates.maxByOrNull { it.depth }
        if (pick != null) return pick.range
        val owner = deepestNode(target) ?: return target..target
        var lo = target
        var hi = target
        while (lo > 1 && deepestNode(lo - 1)?.id == owner.id) lo--
        while (hi < TOTAL_PARAGRAPHS && deepestNode(hi + 1)?.id == owner.id) hi++
        return if (forward) target..hi else lo..target
    }

    companion object {
        const val TOTAL_PARAGRAPHS = 2865

        /** The degraded tree: the Prologue plus each Part with the hand-written outline sections beneath it. */
        internal fun fromOutline(): CatechismStructure {
            val sections = CatechismOutline.sections
            val nodes = mutableListOf<CatechismNode>()
            sections.groupBy { it.partTitle.es }.values.forEachIndexed { index, group ->
                val first = group.first().firstNumber
                val last = group.last().lastNumber
                if (index == 0) {
                    nodes += CatechismNode("outline_prologue", CatechismNodeKind.PROLOGUE, group.first().partTitle, first..last)
                } else {
                    nodes += CatechismNode(
                        "outline_part_$index", CatechismNodeKind.PART, group.first().partTitle, first..last,
                        group.map { s ->
                            CatechismNode("outline_${s.firstNumber}", CatechismNodeKind.SECTION, s.title, s.firstNumber..s.lastNumber, depth = 1)
                        }
                    )
                }
            }
            return CatechismStructure(nodes, fromAsset = false)
        }
    }
}

/** Labels composed from a node's kind and position, because the reviewed titles carry no numbers. */
object CatechismLabels {
    private val partOrdinals = listOf(
        LocalizedText("Primera parte", "Part One", "Primeira parte"),
        LocalizedText("Segunda parte", "Part Two", "Segunda parte"),
        LocalizedText("Tercera parte", "Part Three", "Terceira parte"),
        LocalizedText("Cuarta parte", "Part Four", "Quarta parte")
    )
    private val feminine = listOf(
        LocalizedText("primera", "One", "primeira"), LocalizedText("segunda", "Two", "segunda"),
        LocalizedText("tercera", "Three", "terceira"), LocalizedText("cuarta", "Four", "quarta"),
        LocalizedText("quinta", "Five", "quinta")
    )
    private val masculine = listOf(
        LocalizedText("primero", "One", "primeiro"), LocalizedText("segundo", "Two", "segundo"),
        LocalizedText("tercero", "Three", "terceiro"), LocalizedText("cuarto", "Four", "quarto"),
        LocalizedText("quinto", "Five", "quinto")
    )

    /** "Primera parte", "Sección segunda", "Capítulo tercero", "Artículo 9", "Párrafo 2"; null for prologue and headings. */
    fun label(node: CatechismNode, lang: AppLanguage): String? {
        val n = node.ordinal ?: return null
        return when (node.kind) {
            CatechismNodeKind.PART -> partOrdinals.getOrNull(n - 1)?.get(lang)
            CatechismNodeKind.SECTION -> feminine.getOrNull(n - 1)?.let {
                when (lang) {
                    AppLanguage.ES -> "Sección ${it.es}"
                    AppLanguage.EN -> "Section ${it.en}"
                    AppLanguage.PT -> "Secção ${it.pt}"
                }
            }
            CatechismNodeKind.CHAPTER -> masculine.getOrNull(n - 1)?.let {
                when (lang) {
                    AppLanguage.ES -> "Capítulo ${it.es}"
                    AppLanguage.EN -> "Chapter ${it.en}"
                    AppLanguage.PT -> "Capítulo ${it.pt}"
                }
            }
            else -> shortLabel(node, lang)
        }
    }

    /** "Parte 3", "Sección 2", "Capítulo 2", "Artículo 6", "Párrafo 1"; null when the book prints no number. */
    fun shortLabel(node: CatechismNode, lang: AppLanguage): String? {
        val n = node.ordinal ?: return null
        val word = when (node.kind) {
            CatechismNodeKind.PART -> lang.pick("Parte", "Part", "Parte")
            CatechismNodeKind.SECTION -> lang.pick("Sección", "Section", "Secção")
            CatechismNodeKind.CHAPTER -> lang.pick("Capítulo", "Chapter", "Capítulo")
            CatechismNodeKind.ARTICLE -> lang.pick("Artículo", "Article", "Artigo")
            CatechismNodeKind.PARAGRAPH -> lang.pick("Párrafo", "Paragraph", "Parágrafo")
            else -> return null
        }
        return "$word $n"
    }

    /** "CIC 1213–1284" ("CCC" in English), with a non-breaking space so a narrow row never parts the acronym from its number. */
    fun rangeLabel(range: IntRange, lang: AppLanguage): String =
        ReadingPlanLinks.catechismLabel(range, lang == AppLanguage.EN).replaceFirst(' ', '\u00A0')

    /** The titles a plan day sits in, in the language asked for, joined with " · ". */
    fun planSubtitle(structure: CatechismStructure, range: IntRange, lang: AppLanguage): String? =
        structure.titleNodes(range).map { it.title.get(lang) }.distinct().joinToString(" · ").ifBlank { null }

    private fun AppLanguage.pick(es: String, en: String, pt: String) = when (this) {
        AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt
    }
}

internal data class RawCatechismNode(
    val id: String? = null, val kind: String? = null, val title: RawLT? = null,
    val range: List<Int>? = null, val children: List<RawCatechismNode>? = null
)
internal data class RawCatechismTree(val nodes: List<RawCatechismNode>? = null)

/**
 * The reviewed `catechism/structure.json`: the real hierarchy of the Catechism with the range of every
 * node. Titles are never generated; when the asset cannot be read or fails validation the failure is logged
 * and the hand-written [CatechismOutline] stands in.
 */
class CatechismStructureCatalog(private val open: () -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: CatechismStructureCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled = CatechismStructureCatalog {
            assets?.open("catechism/structure.json")
                ?: CatechismStructureCatalog::class.java.classLoader!!.getResourceAsStream("structure.json")
                ?: error("Catechism structure asset unavailable")
        }
        val default: CatechismStructureCatalog get() = override ?: bundled
        private val ID = Regex("[a-z0-9_]{1,40}")
        private val TRAILING_NUMBER = Regex("(\\d+)$")
    }

    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RawCatechismTree::class.java)
    @Volatile private var cached: CatechismStructure? = null

    /** The tree, or the outline fallback; never null. Parsed once. */
    val structure: CatechismStructure
        get() = cached ?: synchronized(this) { cached ?: (load() ?: CatechismStructure.fromOutline()).also { cached = it } }

    /** Parses and validates the asset; null (after logging) when it cannot be used. */
    fun load(): CatechismStructure? = try {
        val raw = open().bufferedReader().use { requireNotNull(adapter.fromJson(it.readText())) }
        val ids = HashSet<String>()
        val nodes = requireNotNull(raw.nodes).map { convert(it, 0, ids) }.sortedBy { it.first }
        // The prologue and the four parts tile the whole book.
        var expected = 1
        nodes.forEach { require(it.first == expected); expected = it.last + 1 }
        require(expected == CatechismStructure.TOTAL_PARAGRAPHS + 1)
        CatechismStructure(nodes, fromAsset = true)
    } catch (e: Exception) { AppLog.failure(LogEvent.CATECHISM_STRUCTURE, e); null }

    private fun convert(raw: RawCatechismNode, depth: Int, ids: MutableSet<String>): CatechismNode {
        val id = requireNotNull(raw.id?.takeIf { it.matches(ID) })
        require(ids.add(id))
        val kind = CatechismNodeKind.valueOf(requireNotNull(raw.kind))
        val title = LocalizedText(raw.title?.es.orEmpty(), raw.title?.en.orEmpty(), raw.title?.pt.orEmpty())
            .also { require(it.es.isNotBlank() && it.en.isNotBlank() && it.pt.isNotBlank()) }
        val bounds = requireNotNull(raw.range)
        require(bounds.size == 2 && bounds[0] in 1..bounds[1] && bounds[1] <= CatechismStructure.TOTAL_PARAGRAPHS)
        val range = bounds[0]..bounds[1]
        val children = raw.children.orEmpty().map { convert(it, depth + 1, ids) }.sortedBy { it.first }
        children.forEach { require(it.first >= range.first && it.last <= range.last) }
        children.zipWithNext().forEach { (a, b) -> require(a.last < b.first) }
        val numbered = kind != CatechismNodeKind.PROLOGUE && kind != CatechismNodeKind.HEADING
        val ordinal = if (numbered) TRAILING_NUMBER.find(id)?.groupValues?.get(1)?.toIntOrNull() else null
        return CatechismNode(id, kind, title, range, children, depth, ordinal)
    }
}
