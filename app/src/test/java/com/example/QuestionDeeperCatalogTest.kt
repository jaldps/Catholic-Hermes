package com.example

import com.example.data.repository.QuestionDeeperCatalog
import org.junit.Assert.*
import org.junit.Test
import java.io.FileNotFoundException

class QuestionDeeperCatalogTest {
    private val lt = """{"es":"e","en":"n","pt":"p"}"""
    private fun item(n: Int) =
        """{"number":$n,"explanation":$lt,"scripture":$lt,"tradition":$lt,"magisterium":$lt,"objection":$lt,"analogy":$lt,"synthesis":{"es":"s"},"sources":["CIC 1","","Agustín"]}"""
    private fun catalog(files: Map<String, String>) = QuestionDeeperCatalog { name ->
        (files[name] ?: throw FileNotFoundException(name)).byteInputStream()
    }

    @Test fun parsesBothFilesByNumber() {
        val c = catalog(mapOf("deeper-a.json" to "[${item(1)}]", "deeper-b.json" to "[${item(52)},${item(53)}]"))
        assertEquals(setOf(1, 52, 53), c.byNumber.keys)
        val d = c.byNumber.getValue(1)
        assertEquals("n", d.explanation.en); assertEquals("p", d.analogy.pt); assertEquals("s", d.synthesis.es)
        assertEquals("", d.synthesis.en); assertEquals(listOf("CIC 1", "Agustín"), d.sources)
    }

    @Test fun missingAndMalformedAreIgnored() {
        assertTrue(catalog(emptyMap()).byNumber.isEmpty())
        val c = catalog(mapOf("deeper-a.json" to "{oops", "deeper-b.json" to "[${item(7)},{\"explanation\":$lt}]"))
        assertEquals(setOf(7), c.byNumber.keys)
    }
}
