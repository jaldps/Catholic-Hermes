package com.example

import com.example.data.models.PrayerCategory
import com.example.data.models.PromiseKind
import com.example.data.models.VaticanPrayer
import com.example.data.models.LocalizedText
import com.example.data.repository.PrayerDetailsCatalog
import org.junit.Assert.*
import org.junit.Test
import java.io.FileNotFoundException

class PrayerDetailsCatalogTest {
    private val lt = """{"es":"e","en":"n","pt":"p"}"""
    private fun detail(id: String, promise: String = "null") =
        """{"prayerId":"$id","history":$lt,"instructions":$lt,"source":$lt,"approval":{"es":"","en":"","pt":""},"promise":$promise,"textNote":{}}"""
    private fun catalog(files: Map<String, String>) = PrayerDetailsCatalog { name ->
        (files[name] ?: throw FileNotFoundException(name)).byteInputStream()
    }

    @Test fun parsesAllFieldsAndPromiseKinds() {
        val kinds = listOf("SOURCED", "ECCLESIAL_GRANT", "TRADITIONAL", "NONE")
        val a = kinds.mapIndexed { i, k ->
            detail("p$i", """{"kind":"$k","text":$lt,"attribution":$lt,"source":$lt,"conditions":$lt}""")
        }.joinToString(prefix = "[", postfix = "]", separator = ",")
        val c = catalog(mapOf("details-a.json" to a, "details-b.json" to "[${detail("other")}]"))
        assertEquals(5, c.detailsById.size)
        assertEquals(kinds.map { PromiseKind.valueOf(it) }, (0..3).map { c.detailsById["p$it"]!!.promise!!.kind })
        val d = c.detailsById["other"]!!
        assertEquals("n", d.history.en); assertEquals("p", d.instructions.pt); assertEquals("e", d.source.es)
        assertEquals("", d.approval.es); assertNull(d.promise); assertEquals("", d.textNote.en)
        assertTrue(c.extraPrayers.isEmpty())
    }

    @Test fun missingAndMalformedFilesAreIgnored() {
        assertTrue(catalog(emptyMap()).detailsById.isEmpty())
        val c = catalog(mapOf("details-a.json" to "{not json", "details-b.json" to "[${detail("ok")}]", "extra.json" to "[1,2"))
        assertEquals(setOf("ok"), c.detailsById.keys)
        assertTrue(c.extraPrayers.isEmpty())
    }

    private val extra = """[{"id":"new1","title":$lt,"latinTitle":"Lat","category":"MARIAN","leaderText":$lt,"responseText":null,
        "whenToPray":$lt,"indulgenceNote":null,"thematicTags":["family"],"history":$lt,"instructions":$lt,"source":{},"approval":{},"promise":null,"textNote":{}},
        {"id":"angelus","title":$lt,"category":"WHATEVER","leaderText":$lt,"whenToPray":$lt,"responseText":$lt,"indulgenceNote":$lt}]"""

    @Test fun extraPrayersMapFieldsAndCollisionsAreSkipped() {
        val c = catalog(mapOf("extra.json" to extra))
        assertEquals(2, c.extraPrayers.size)
        val n = c.extraPrayers.first { it.id == "new1" }
        assertEquals(PrayerCategory.MARIAN, n.category); assertEquals(listOf("family"), n.thematicTags)
        assertNull(n.responseText); assertNull(n.indulgenceNote); assertEquals("Lat", n.latinTitle)
        assertEquals(PrayerCategory.INTERCESSION, c.extraPrayers.first { it.id == "angelus" }.category)
        assertNotNull(c.detailsById["new1"])
        val l = LocalizedText("a", "b", "c")
        val existing = listOf(VaticanPrayer("angelus", l, null, PrayerCategory.ANGELUS, l, null, l))
        val merged = c.mergeInto(existing)
        assertEquals(listOf("angelus", "new1"), merged.map { it.id })
        assertEquals(PrayerCategory.ANGELUS, merged[0].category)
    }

    private val l0 = LocalizedText("a", "b", "c")
    private fun prayer(id: String) = VaticanPrayer(id, l0, "Lat", PrayerCategory.MARIAN, l0, l0, l0)
    private val ov = """[{"id":"p1","title":null,"latinTitle":null,"leaderText":{"es":"E2","en":"N2","pt":"P2"},"responseText":null,"whenToPray":null,"textNote":{"es":"ne","en":"nn","pt":"np"}},
        {"id":"p2","title":{"es":"T","en":"U","pt":"V"},"latinTitle":"L2","leaderText":$lt,"responseText":{"es":"r","en":"s","pt":"t"},"whenToPray":{"es":"w","en":"x","pt":"y"},"textNote":$lt},
        {"id":"ghost","leaderText":$lt,"textNote":$lt}]"""

    @Test fun textOverridesReplaceTextAndKeepNullFields() {
        val c = catalog(mapOf("details-a.json" to "[${detail("p1")}]", "text-overrides.json" to ov))
        val m = c.mergeInto(listOf(prayer("p1"), prayer("p2"), prayer("other")))
        assertEquals(listOf("p1", "p2", "other"), m.map { it.id })
        val p1 = m[0]
        assertEquals(LocalizedText("E2", "N2", "P2"), p1.leaderText)
        assertEquals(l0, p1.title); assertEquals("Lat", p1.latinTitle); assertEquals(l0, p1.responseText); assertEquals(l0, p1.whenToPray)
        val p2 = m[1]
        assertEquals(LocalizedText("T", "U", "V"), p2.title); assertEquals("L2", p2.latinTitle)
        assertEquals(LocalizedText("r", "s", "t"), p2.responseText); assertEquals(LocalizedText("w", "x", "y"), p2.whenToPray)
        assertEquals(prayer("other"), m[2])
        assertEquals(LocalizedText("ne", "nn", "np"), c.detailsById["p1"]!!.textNote)
        assertNull(c.detailsById["p2"]); assertNull(c.detailsById["ghost"])
    }

    @Test fun missingOrMalformedOverridesLeaveExistingUntouched() {
        val existing = listOf(prayer("p1"))
        assertEquals(existing, catalog(emptyMap()).mergeInto(existing))
        assertEquals(existing, catalog(mapOf("text-overrides.json" to "[{oops")).mergeInto(existing))
    }
}
