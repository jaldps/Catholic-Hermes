package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.NovenaCatalog
import com.example.data.search.SearchGroup
import com.example.data.search.SearchIndex
import com.example.data.search.SearchSection
import com.example.data.search.SearchSources
import com.example.data.search.SearchTarget
import com.example.data.search.SearchText
import com.example.data.search.SearchType
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** The global search index: one query per kind of content, accents, references and the build cost. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class GlobalSearchIndexTest {
    private val app get() = ApplicationProvider.getApplicationContext<Application>()
    private var repo: CatholicDataRepository? = null
    private val sections = listOf(
        SearchSection("CONFESSION", "Confesión"), SearchSection("PLANS", "Planes de lectura"), SearchSection("ROSARY", "Rosario")
    )

    @After fun close() { repo?.close() }

    private fun repository(language: AppLanguage = AppLanguage.ES) =
        CatholicDataRepository(app).apply { setLanguage(language) }.also { repo = it }

    private fun build(language: AppLanguage = AppLanguage.ES, titles: List<SearchSection> = sections): SearchIndex =
        SearchSources.build(repository(language), language, titles)

    private fun List<SearchGroup>.group(type: SearchType) = firstOrNull { it.type == type }
    private fun List<SearchGroup>.ids(type: SearchType) = group(type)?.results?.map { it.id }.orEmpty()

    @Test fun oneQueryPerTypeFindsItsItem() {
        val index = build()
        fun find(query: String, type: SearchType, id: String? = null) {
            val groups = index.search(query)
            val group = groups.group(type)
            assertNotNull("'$query' should have a $type group; got ${groups.map { it.type }}", group)
            if (id != null) assertTrue("'$query' should list $type $id; got ${group!!.results.map { it.id }}", id in group.results.map { it.id })
        }
        find("padre nuestro", SearchType.PRAYER, "our_father")
        find("misericordia", SearchType.NOVENA, "novena-a-la-divina-misericordia")
        find("Inés", SearchType.SAINT, "agnes_of_rome_virgin")
        find("guadalupe", SearchType.DEVOTION)
        find("compañía de jesús", SearchType.ORDER)
        find("sufrimiento inocente", SearchType.QUESTION, "1")
        find("lectio divina", SearchType.COURSE)
        find("pecado original", SearchType.CATECHISM)
        find("Biblia en un año", SearchType.PLAN, "bible_year")
        find("confesion", SearchType.SECTION, "confession")
    }

    @Test fun mariaMatchesMariaWithAccentsAndAnyCase() {
        val index = build()
        val plain = index.search("maria")
        assertTrue(plain.isNotEmpty())
        val titles = plain.flatMap { it.results }.map { it.title }
        assertTrue("some title contains María: ${titles.take(8)}", titles.any { it.contains("María") })
        assertEquals(plain.map { it.type to it.total }, index.search("MARÍA").map { it.type to it.total })
        assertEquals(plain.map { it.type to it.total }, index.search("  María ").map { it.type to it.total })
    }

    @Test fun wordsMatchFromTheirStartNotInTheMiddle() {
        assertFalse(SearchText.containsWord(SearchText.fold("la perfumaría"), "maria"))
        assertTrue(SearchText.containsWord(SearchText.fold("la Virgen María"), "maria"))
        assertTrue(SearchText.containsWord(SearchText.fold("la Virgen María"), "mar"))
        assertTrue(SearchText.containsWordFolded("«María», madre", "maria"))
        assertFalse(SearchText.containsWordFolded("perfumaría", "maria"))
        // Whole Bible: every hit has the word at its start (Exodus 37:29 mentions "perfumaria", not María).
        val verses = repository().searchBibleText(listOf("maria"), 400, AppLanguage.PT, "bundled-pt")
        assertTrue(verses.isNotEmpty())
        assertTrue(verses.all { (_, _, v) -> SearchText.containsWord(SearchText.fold(v.text.pt), "maria") })
    }

    @Test fun titleMatchesRankAboveBodyMatchesAndSnippetsShowTheMatch() {
        val index = build()
        val prayers = index.search("ave maria").group(SearchType.PRAYER)!!
        assertTrue(prayers.results.first().title.contains("Ave María", ignoreCase = true))
        val catechism = index.search("pecado original").group(SearchType.CATECHISM)!!
        val snippet = catechism.results.first().snippet
        assertNotNull(snippet)
        assertTrue(SearchText.fold(snippet!!).contains("pecado") || SearchText.fold(snippet).contains("original"))
        assertTrue(snippet.length <= 140)
    }

    @Test fun shortQueriesFindNothingAndEachGroupIsCapped() {
        val index = build()
        assertTrue(index.search("a").isEmpty())
        assertTrue(index.search(" ").isEmpty())
        assertTrue(index.search("dios").all { it.results.size <= SearchIndex.DEFAULT_CAP })
        assertTrue(index.search("zzzxqv").isEmpty())
    }

    @Test fun psalmReferenceResolvesToTheVulgateChapterOfTheBundledEdition() {
        val r = SearchSources.reference("Sal 23", AppLanguage.ES, "bundled-es")!!
        assertEquals(SearchType.REFERENCE, r.type)
        assertEquals(SearchTarget.Bible("psa", 22, null), r.target)
        assertTrue(r.title, r.title.contains("23") && r.title.contains("22"))
        // A Hebrew-numbered edition keeps the typed number.
        assertEquals(SearchTarget.Bible("psa", 23, null), SearchSources.reference("Salmo 23", AppLanguage.ES, "en.web")!!.target)
        assertEquals(SearchTarget.Bible("psa", 22, 1), SearchSources.reference("Ps 23:1", AppLanguage.EN, "bundled-en")!!.target)
    }

    @Test fun otherReferencesAreRecognised() {
        fun target(q: String, lang: AppLanguage = AppLanguage.ES) = SearchSources.reference(q, lang, "bundled-es")?.target
        assertEquals(SearchTarget.Bible("joh", 3, 16), target("Jn 3,16"))
        assertEquals(SearchTarget.Bible("joh", 3, 16), target("juan 3:16-18"))
        assertEquals(SearchTarget.Bible("luk", 1, 1), target("Luke 1:1-4", AppLanguage.EN))
        assertEquals(SearchTarget.Bible("gen", 1, null), target("gen 1"))
        assertEquals(SearchTarget.Bible("1co", 13, null), target("1 Cor 13"))
        assertEquals(SearchTarget.Catechism(1030), target("CIC 1030"))
        assertEquals(SearchTarget.Catechism(1030), target("ccc 1030", AppLanguage.EN))
        assertEquals(SearchTarget.Catechism(2865), target("2865"))
        assertNull(target("Sal 151"))
        assertNull(target("CIC 9999"))
        assertNull(target("maria"))
        assertNull(target("padre nuestro"))
    }

    @Test fun bibleTextSearchIsAccentInsensitiveAndLimited() {
        val repository = repository()
        val group = SearchSources.bibleText(repository, "principio creo dios", AppLanguage.ES, "bundled-es", limit = 50)!!
        assertEquals(SearchType.BIBLE, group.type)
        assertTrue(group.results.size in 1..50)
        assertEquals(SearchTarget.Bible("gen", 1, 1), group.results.first().target)
        assertTrue(group.results.first().snippet!!.contains("creó", ignoreCase = true))
        val many = SearchSources.bibleText(repository, "dios", AppLanguage.ES, "bundled-es", limit = 50)!!
        assertEquals(50, many.results.size)
        assertTrue(many.capped)
        assertNull(SearchSources.bibleText(repository, "zzzxqv", AppLanguage.ES, "bundled-es"))
    }

    @Test fun indexIsBuiltInOtherLanguagesAndNeverLoadsNovenaBodies() {
        val en = build(AppLanguage.EN)
        assertTrue(en.search("our father").ids(SearchType.PRAYER).contains("our_father"))
        assertTrue(en.search("purgatory").ids(SearchType.CATECHISM).contains("1031"))
        assertEquals("CCC 1031", en.search("purgatory").group(SearchType.CATECHISM)!!.results.first { it.id == "1031" }.title)
        val pt = build(AppLanguage.PT, listOf(SearchSection("CONFESSION", "Confissão")))
        assertTrue(pt.search("confissao").ids(SearchType.SECTION).contains("confession"))
        assertTrue(pt.search("pai nosso").group(SearchType.PRAYER) != null)
        assertEquals(0, NovenaCatalog.default.cachedBodyCount())
    }

    @Test fun buildTimeIsReported() {
        val first = build()
        println("T-030 search index: ${first.size} entries in ${first.buildMillis} ms (ES, Robolectric JVM)")
        val counts = SearchType.entries.associateWith { first.countOf(it) }.filterValues { it > 0 }
        println("T-030 entries by type: $counts")
        assertTrue(first.size > 3000)
        val started = System.nanoTime()
        repeat(20) { first.search("misericordia de dios") }
        println("T-030 search: ${(System.nanoTime() - started) / 20 / 1_000_000.0} ms per query")
    }
}
