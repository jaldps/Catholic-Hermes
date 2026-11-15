package com.example

import com.example.data.repository.QuestionDeeperCatalog
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Test

class QuestionDeeperContentTest {
    private fun exists(f: String) = QuestionDeeperContentTest::class.java.classLoader!!.getResource(f) != null

    @Test fun coversQuestions1To101ExactlyOnce() {
        assumeTrue(exists("deeper-a.json") && exists("deeper-b.json"))
        val cl = QuestionDeeperContentTest::class.java.classLoader!!
        val numbers = listOf("deeper-a.json", "deeper-b.json").flatMap { f ->
            Regex("\"number\"\\s*:\\s*(\\d+)").findAll(cl.getResource(f)!!.readText()).map { it.groupValues[1].toInt() }.toList()
        }
        assertEquals("duplicates", numbers.size, numbers.toSet().size)
        assertEquals((1..101).toSet(), numbers.toSet())
        QuestionDeeperCatalog.default.byNumber.values.forEach { d ->
            listOf(d.explanation, d.scripture, d.tradition, d.magisterium, d.objection, d.analogy, d.synthesis).forEachIndexed { i, t ->
                assertTrue("${d.number}#$i es", t.es.isNotBlank())
                assertTrue("${d.number}#$i en", t.en.isNotBlank())
                assertTrue("${d.number}#$i pt", t.pt.isNotBlank())
            }
            assertTrue("${d.number} sources", d.sources.isNotEmpty())
        }
    }
}
