package com.example.data.xref

import android.content.Context
import com.example.data.models.AppLanguage
import java.io.DataInputStream
import java.io.File
import java.io.InputStream

/** One verse in the Catholic canon, in a given edition's own numbering. */
data class VerseRef(val bookId: String, val chapter: Int, val verse: Int)

/** A cross-reference from a verse to a verse or a run of verses. */
data class CrossReference(
    val target: VerseRef,
    /** Last verse of the run, when the reference spans more than one. */
    val end: VerseRef?,
    /** OpenBible.info readers' votes for the connection: its weight. */
    val votes: Int
)

/**
 * The cross-references of the whole Bible, from OpenBible.info (CC BY 4.0),
 * largely the public-domain Treasury of Scripture Knowledge.
 *
 * The source is numbered like the King James Bible; the bundled assets
 * (assets/xref/xref_<lang>.bin, built by tools/xref) are already re-keyed to
 * each bundled edition's Vulgate-tradition numbering, so a Psalm, a Daniel or
 * an Exodus 8 resolves to the verse the reader actually sees. The
 * deuterocanonical books have no references in the source.
 *
 * A downloaded edition may carry its own table (xref.bin, same layout) re-keyed to that edition's numbering
 * (tools/xref/build_assets.py --editions); [editionXref] finds it, and without one the bundled language table is used.
 *
 * File layout: "XRF1", count, then four columns sorted by source verse and
 * descending weight: source, target start, target end (Int each), votes (Short).
 * A verse is packed as book index << 16 | chapter << 8 | verse.
 */
class CrossReferenceIndex(
    private val context: Context,
    private val canon: List<String>,
    private val editionXref: (String) -> File? = { null }
) {

    private class Table(val from: IntArray, val start: IntArray, val end: IntArray, val votes: ShortArray)

    private var loaded: Pair<String, Table>? = null

    private fun parse(stream: InputStream): Table = DataInputStream(stream.buffered(1 shl 16)).use { input ->
        val magic = ByteArray(4).also { input.readFully(it) }
        require(String(magic) == "XRF1")
        val n = input.readInt()
        val from = IntArray(n) { input.readInt() }
        val start = IntArray(n) { input.readInt() }
        val end = IntArray(n) { input.readInt() }
        val votes = ShortArray(n) { input.readShort() }
        Table(from, start, end, votes)
    }

    /** The edition's own table when [editionId] has one that parses, else the bundled table of [language]. */
    @Synchronized
    private fun table(language: AppLanguage, editionId: String?): Table? {
        val file = editionId?.takeIf { it != "bundled" }?.let { editionXref(it) }
        val editionKey = "edition:$editionId"
        val bundledKey = "lang:${language.code}"
        loaded?.let { (k, t) -> if (k == bundledKey && file == null || k == editionKey && file != null) return t }
        val own = file?.let { f -> runCatching { parse(f.inputStream()) }.getOrNull() }
        if (own != null) { loaded = editionKey to own; return own }
        loaded?.let { (k, t) -> if (k == bundledKey) return t }
        val bundled = runCatching { parse(context.assets.open("xref/xref_${language.code}.bin")) }.getOrNull() ?: return null
        loaded = bundledKey to bundled
        return bundled
    }

    private fun pack(ref: VerseRef): Int? {
        val book = canon.indexOf(ref.bookId).takeIf { it >= 0 } ?: return null
        return (book shl 16) or (ref.chapter shl 8) or ref.verse
    }

    private fun unpack(code: Int): VerseRef? {
        val book = canon.getOrNull(code ushr 16) ?: return null
        return VerseRef(book, (code ushr 8) and 0xFF, code and 0xFF)
    }

    /** References from [ref], strongest first. */
    fun referencesFrom(ref: VerseRef, language: AppLanguage, limit: Int = Int.MAX_VALUE, editionId: String? = null): List<CrossReference> {
        val t = table(language, editionId) ?: return emptyList()
        val key = pack(ref) ?: return emptyList()
        var i = lowerBound(t.from, key)
        val out = ArrayList<CrossReference>()
        while (i < t.from.size && t.from[i] == key && out.size < limit) {
            val target = unpack(t.start[i])
            if (target != null) {
                val end = if (t.end[i] != t.start[i]) unpack(t.end[i]) else null
                out += CrossReference(target, end, t.votes[i].toInt())
            }
            i++
        }
        return out
    }

    /** Number of references from each verse of a chapter, by verse number. */
    fun countsForChapter(bookId: String, chapter: Int, language: AppLanguage, editionId: String? = null): Map<Int, Int> {
        val t = table(language, editionId) ?: return emptyMap()
        val first = pack(VerseRef(bookId, chapter, 0)) ?: return emptyMap()
        var i = lowerBound(t.from, first)
        val out = HashMap<Int, Int>()
        while (i < t.from.size && (t.from[i] ushr 8) == (first ushr 8)) {
            val v = t.from[i] and 0xFF
            out[v] = (out[v] ?: 0) + 1
            i++
        }
        return out
    }

    /**
     * Every connection as a pair of packed verses, for drawing the whole
     * Bible at once. [minVotes] thins the picture to its strongest links.
     */
    fun allConnections(language: AppLanguage, minVotes: Int = 0, editionId: String? = null): Sequence<Pair<VerseRef, VerseRef>> {
        val t = table(language, editionId) ?: return emptySequence()
        return (0 until t.from.size).asSequence()
            .filter { t.votes[it] >= minVotes }
            .mapNotNull { i -> unpack(t.from[i])?.let { a -> unpack(t.start[i])?.let { b -> a to b } } }
    }

    private fun lowerBound(a: IntArray, key: Int): Int {
        var lo = 0
        var hi = a.size
        while (lo < hi) {
            val mid = (lo + hi) ushr 1
            if (a[mid] < key) lo = mid + 1 else hi = mid
        }
        return lo
    }
}

/**
 * Lays every verse of the canon on one line, in canonical order, so a verse
 * and its references can be drawn as arcs over the whole Bible.
 */
class BibleLine(counts: Map<String, IntArray>, canon: List<String>) {
    private val bookStart = HashMap<String, Int>()
    private val chapterStart = HashMap<String, IntArray>()
    /** Each book's first position and its testament side, for the baseline. */
    val books: List<Triple<String, Int, Int>>
    val total: Int

    init {
        var pos = 0
        val spans = ArrayList<Triple<String, Int, Int>>()
        for (id in canon) {
            val chapters = counts[id] ?: continue
            bookStart[id] = pos
            val starts = IntArray(chapters.size)
            for (c in chapters.indices) {
                starts[c] = pos
                pos += chapters[c]
            }
            chapterStart[id] = starts
            spans += Triple(id, bookStart.getValue(id), pos)
        }
        books = spans
        total = pos.coerceAtLeast(1)
    }

    /** Position of [ref] on the line, 0 until [total]. */
    fun position(ref: VerseRef): Int? {
        val starts = chapterStart[ref.bookId] ?: return null
        val c = starts.getOrNull(ref.chapter - 1) ?: return null
        return c + (ref.verse - 1).coerceAtLeast(0)
    }

    fun fraction(ref: VerseRef): Float? = position(ref)?.let { it.toFloat() / total }
}
