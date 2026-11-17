package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.CatechismLabels
import com.example.data.repository.CatechismNodeKind
import com.example.data.repository.CatechismOutline
import com.example.data.repository.CatechismStructure
import com.example.data.repository.CatechismStructureCatalog
import com.example.data.repository.ReadingPlansCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.ByteArrayInputStream
import java.io.FileNotFoundException

/** The reviewed hierarchy of the Catechism: it loads, tiles the book, locates every paragraph, and degrades to the outline. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class CatechismStructureCatalogTest {
    private val structure: CatechismStructure by lazy { CatechismStructureCatalog.default.structure }

    private fun path(paragraph: Int) = structure.breadcrumb(paragraph).map { it.id }

    @Test fun theBundledAssetLoadsWithAll123Nodes() {
        assertTrue(structure.fromAsset)
        assertEquals(123, structure.all.size)
        assertEquals(
            listOf(CatechismNodeKind.PROLOGUE) + List(4) { CatechismNodeKind.PART },
            structure.nodes.map { it.kind }
        )
        assertEquals(4, structure.nodesOfKind(CatechismNodeKind.PART).size)
        assertEquals(5, structure.nodesAtDepth(0).size)
    }

    @Test fun theTopLevelTilesParagraphs1To2865WithoutGaps() {
        var expected = 1
        structure.nodes.forEach { assertEquals(expected, it.first); expected = it.last + 1 }
        assertEquals(2866, expected)
        // Children stay inside their parent, in reading order, without overlap.
        structure.all.forEach { n ->
            n.children.forEach { c -> assertTrue("${c.id} inside ${n.id}", c.first >= n.first && c.last <= n.last) }
            n.children.zipWithNext().forEach { (a, b) -> assertTrue("${a.id} before ${b.id}", a.last < b.first) }
        }
    }

    @Test fun everyParagraphHasABreadcrumbStartingAtAPartOrThePrologue() {
        for (n in 1..2865) {
            val crumb = structure.breadcrumb(n)
            assertTrue("§$n has a path", crumb.isNotEmpty())
            assertTrue(crumb.first().kind == CatechismNodeKind.PROLOGUE || crumb.first().kind == CatechismNodeKind.PART)
            assertTrue(crumb.all { n in it.range })
            assertEquals(crumb.last(), structure.deepestNode(n))
        }
        assertNull(structure.deepestNode(0))
        assertNull(structure.deepestNode(2866))
    }

    @Test fun breadcrumbsAreRightForKnownParagraphs() {
        assertEquals(listOf("prologue"), path(1))
        assertEquals(listOf("p2", "p2s2", "p2s2c1", "p2s2c1a1"), path(1213))
        assertEquals(listOf("p3", "p3s2", "p3s2c1", "p3s2c1a1"), path(2084))
        assertEquals(listOf("p4", "p4s1", "p4s1c3", "p4s1c3a3"), path(2746))
        assertEquals(listOf("p4", "p4s2", "p4s2a4"), path(2855))
        assertEquals("La oración de la Hora de Jesús", structure.deepestNode(2746)!!.title.es)
        assertEquals("La doxología final", structure.deepestNode(2855)!!.title.es)
        assertEquals("El primer mandamiento", structure.deepestNode(2084)!!.title.es)
        // The opening paragraphs of a node belong to the node itself; the closing "Amén" is a heading of its own.
        assertEquals(listOf("p2", "p2s2"), path(1210))
        assertEquals(CatechismNodeKind.HEADING, structure.deepestNode(1063)!!.kind)
        // Titles exist in all three languages.
        structure.all.forEach { assertTrue(it.id, it.title.es.isNotBlank() && it.title.en.isNotBlank() && it.title.pt.isNotBlank()) }
    }

    @Test fun labelsAreComposedFromKindAndPositionInEachLanguage() {
        fun label(id: String, lang: AppLanguage) = CatechismLabels.label(structure.node(id)!!, lang)
        assertEquals("Segunda parte", label("p2", AppLanguage.ES))
        assertEquals("Part Two", label("p2", AppLanguage.EN))
        assertEquals("Segunda parte", label("p2", AppLanguage.PT))
        assertEquals("Sección segunda", label("p2s2", AppLanguage.ES))
        assertEquals("Section Two", label("p2s2", AppLanguage.EN))
        assertEquals("Secção segunda", label("p2s2", AppLanguage.PT))
        assertEquals("Capítulo tercero", label("p1s1c3", AppLanguage.ES))
        assertEquals("Chapter Three", label("p1s1c3", AppLanguage.EN))
        assertEquals("Capítulo terceiro", label("p1s1c3", AppLanguage.PT))
        assertEquals("Artículo 9", label("p1s2c3a9", AppLanguage.ES))
        assertEquals("Article 9", label("p1s2c3a9", AppLanguage.EN))
        assertEquals("Artigo 9", label("p1s2c3a9", AppLanguage.PT))
        assertEquals("Párrafo 2", label("p1s2c3a9p2", AppLanguage.ES))
        assertEquals("Paragraph 2", label("p1s2c3a9p2", AppLanguage.EN))
        assertEquals("Parágrafo 2", label("p1s2c3a9p2", AppLanguage.PT))
        assertNull(label("prologue", AppLanguage.ES))
        assertNull(label("p1s2h1", AppLanguage.ES))
        // The breadcrumb form: "Parte 3 › Sección 2 › Capítulo 2 › Artículo 6".
        assertEquals(
            "Parte 3 › Sección 2 › Capítulo 2 › Artículo 6",
            structure.breadcrumb(2331).joinToString(" › ") { CatechismLabels.shortLabel(it, AppLanguage.ES)!! }
        )
        assertEquals("CIC\u00A01213–1284", CatechismLabels.rangeLabel(1213..1284, AppLanguage.ES))
        assertEquals("CCC\u00A01213–1284", CatechismLabels.rangeLabel(1213..1284, AppLanguage.EN))
        assertEquals("CIC\u00A01213–1284", CatechismLabels.rangeLabel(1213..1284, AppLanguage.PT))
    }

    @Test fun planDaysAreNamedByTheDivisionsTheyHold() {
        fun names(range: IntRange) = CatechismLabels.planSubtitle(structure, range, AppLanguage.ES)
        assertEquals("Prólogo", names(1..8))
        assertEquals("El sacramento del bautismo", names(1217..1224))
        // A day crossing from one article into the next names both, nothing more.
        assertEquals("El sacramento del bautismo · El sacramento de la confirmación", names(1281..1288))
        // A day in a chapter's opening and its first article names the outer one.
        assertEquals("Los sacramentos de la iniciación cristiana", names(1212..1214))
        val days = ReadingPlansCatalog.default.load()!!.first { it.isCatechism }.days
        days.forEach { day ->
            val nodes = structure.titleNodes(day.paragraphs!!)
            assertTrue("day ${day.day}", nodes.size in 1..2)
            assertNotNull(CatechismLabels.planSubtitle(structure, day.paragraphs!!, AppLanguage.EN))
        }
    }

    @Test fun neighbouringReadingsFollowTheSameLevelOfTheBook() {
        assertEquals(1285..1321, structure.adjacent(1213, 1284, forward = true))
        assertEquals(1213..1284, structure.adjacent(1285, 1321, forward = false))
        // From the last article of a chapter, onward to the next chapter.
        assertEquals(1420..1532, structure.adjacent(1322, 1419, forward = true))
        // A node's own opening paragraphs are a stretch of their own.
        assertEquals(1212..1212, structure.adjacent(1213, 1284, forward = false))
        assertNull(structure.adjacent(1, 25, forward = false))
        assertNull(structure.adjacent(2855, 2865, forward = true))
        assertEquals(26..1065, structure.adjacent(1, 25, forward = true))
    }

    @Test fun aMissingAssetFallsBackToTheOutlineWithoutInventingTitles() {
        val catalog = CatechismStructureCatalog { throw FileNotFoundException("no asset") }
        assertNull(catalog.load())
        val fallback = catalog.structure
        assertFalse(fallback.fromAsset)
        var expected = 1
        fallback.nodes.forEach { assertEquals(expected, it.first); expected = it.last + 1 }
        assertEquals(CatechismOutline.totalParagraphs + 1, expected)
        // Every title is one the outline already carries.
        val known = CatechismOutline.sections.flatMap { listOf(it.title, it.partTitle) }.toSet()
        fallback.all.forEach { assertTrue(it.id, it.title in known) }
        assertNull(CatechismLabels.label(fallback.nodes[1], AppLanguage.ES))
        assertEquals(fallback.nodes[1].id, fallback.breadcrumb(30).first().id)
        assertEquals("El hombre es «capaz» de Dios", fallback.deepestNode(30)!!.title.es)
    }

    @Test fun brokenAssetsAreRejectedNotRepaired() {
        fun load(json: String) = CatechismStructureCatalog { ByteArrayInputStream(json.toByteArray()) }.load()
        assertNull(load("{}"))
        assertNull(load("not json"))
        val t = """{"es":"A","en":"A","pt":"A"}"""
        // Gap between the prologue and the next part.
        assertNull(load("""{"nodes":[{"id":"prologue","kind":"PROLOGUE","title":$t,"range":[1,25],"children":[]},
            {"id":"p1","kind":"PART","title":$t,"range":[27,2865],"children":[]}]}"""))
        // Whole book covered, but a child leaves its parent.
        assertNull(load("""{"nodes":[{"id":"p1","kind":"PART","title":$t,"range":[1,2865],"children":[
            {"id":"p1s1","kind":"SECTION","title":$t,"range":[1,3000],"children":[]}]}]}"""))
        // Empty title.
        assertNull(load("""{"nodes":[{"id":"p1","kind":"PART","title":{"es":"","en":"A","pt":"A"},"range":[1,2865],"children":[]}]}"""))
        // Valid, minimal.
        assertNotNull(load("""{"nodes":[{"id":"p1","kind":"PART","title":$t,"range":[1,2865],"children":[]}]}"""))
    }
}
