package com.example

import com.example.data.repository.NovenaCatalog
import com.example.data.repository.ProvenanceFiles
import com.squareup.moshi.Moshi
import org.junit.Assert.assertTrue
import org.junit.Test

class NovenaProvenanceTest {
    private fun res(name: String) = NovenaProvenanceTest::class.java.classLoader!!.getResourceAsStream(name)
    private val novenas by lazy { NovenaCatalog { res(it)!! }.allNovenas }
    private val indexIds by lazy { novenas.map { it.id } }
    private val langs = listOf<(com.example.data.models.LocalizedText) -> String>({ it.es }, { it.en }, { it.pt })

    @Suppress("UNCHECKED_CAST")
    private fun rawEntries(f: String): List<Map<String, Any?>> =
        res("provenance/$f")?.bufferedReader()?.use { Moshi.Builder().build().adapter(Any::class.java).fromJson(it.readText()) as List<Map<String, Any?>> }.orEmpty()

    private fun rawIds(): List<String> = ProvenanceFiles.flatMap { f -> rawEntries(f).map { it["id"] as String } }

    private fun check(message: String, bad: List<String>) =
        assertTrue("$message: ${bad.distinct()}", bad.isEmpty())

    @Test fun everyIndexIdHasExactlyOneProvenanceEntryAndNoStrays() {
        val ids = rawIds()
        check("Index ids without provenance", indexIds.filter { it !in ids })
        check("Provenance ids not in index", ids.filter { it !in indexIds })
        check("Duplicate provenance ids", ids.groupBy { it }.filter { it.value.size > 1 }.keys.toList())
    }

    @Test fun provenanceContentIsCompleteAndConsistent() {
        val bad = mutableMapOf<String, MutableList<String>>()
        fun fail(rule: String, id: String) { bad.getOrPut(rule) { mutableListOf() }.add(id) }
        novenas.forEach { n ->
            val p = n.provenance ?: return@forEach
            if (langs.any { it(p.origin).isBlank() }) fail("origin blank", n.id)
            if (langs.any { it(p.history).isBlank() }) fail("history blank", n.id)
            when (p.promise.kind) {
                com.example.data.models.PromiseKind.SOURCED, com.example.data.models.PromiseKind.ECCLESIAL_GRANT ->
                    if (langs.any { it(p.promise.source).isBlank() }) fail("promise source blank", n.id)
                com.example.data.models.PromiseKind.NONE ->
                    if (listOf(p.promise.text, p.promise.attribution, p.promise.source, p.promise.conditions)
                            .any { t -> langs.any { it(t).isNotBlank() } }) fail("NONE promise not empty", n.id)
                else -> Unit
            }
            if (p.status == com.example.data.models.NovenaStatus.PASTORAL_REVIEW &&
                langs.any { it(p.pastoralNote).isBlank() }) fail("pastoral_review without note", n.id)
            if (p.sources.isEmpty() || p.sources.any { it.isBlank() }) fail("sources empty", n.id)
        }
        assertTrue("Provenance problems: $bad", bad.isEmpty())
    }

    @Test fun rawKindAndStatusValuesAreValid() {
        val kinds = setOf("SOURCED", "ECCLESIAL_GRANT", "TRADITIONAL", "NONE")
        val statuses = setOf("historical", "traditional", "modern", "pastoral_review")
        val bad = mutableListOf<String>()
        ProvenanceFiles.forEach { f ->
            rawEntries(f).forEach { o ->
                val id = o["id"] as String
                if (o["status"] !in statuses) bad += "$id(status)"
                if ((o["promise"] as? Map<*, *>)?.get("kind")?.toString()?.uppercase() !in kinds) bad += "$id(kind)"
            }
        }
        check("Invalid kind/status", bad)
    }
}
