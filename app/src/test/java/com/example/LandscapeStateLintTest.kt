package com.example

import org.junit.Assert.*
import org.junit.Test
import java.io.File

/** Checks named page slots, including nested braces; comments/literals cannot trigger or hide a match. */
class LandscapeStateLintTest {
    private fun masked(source: String): String {
        val out = source.toCharArray()
        fun blank(start: Int, end: Int) { for (i in start until end) if (out[i] != '\n') out[i] = ' ' }
        var i = 0
        while (i < source.length) {
            val start = i
            when {
                source.startsWith("//", i) -> { i = source.indexOf('\n', i).takeIf { it >= 0 } ?: source.length; blank(start, i) }
                source.startsWith("/*", i) -> {
                    i += 2; var depth = 1
                    while (i < source.length && depth > 0) when {
                        source.startsWith("/*", i) -> { depth++; i += 2 }
                        source.startsWith("*/", i) -> { depth--; i += 2 }
                        else -> i++
                    }
                    blank(start, i)
                }
                source.startsWith("\"\"\"", i) -> {
                    i = source.indexOf("\"\"\"", i + 3).takeIf { it >= 0 }?.plus(3) ?: source.length; blank(start, i)
                }
                source[i] == '"' || source[i] == '\'' -> {
                    val quote = source[i++]
                    while (i < source.length) { val c = source[i++]; if (c == '\\') i = (i + 1).coerceAtMost(source.length) else if (c == quote) break }
                    blank(start, i)
                }
                else -> i++
            }
        }
        return String(out)
    }
    private fun closing(s: String, start: Int, open: Char, close: Char): Int {
        var depth = 0
        for (i in start until s.length) {
            if (s[i] == open) depth++
            if (s[i] == close) { depth--; if (depth == 0) return i }
        }
        return s.length
    }
    private fun violations(source: String): List<Int> {
        val code = masked(source)
        val found = mutableListOf<Int>()
        Regex("\\bAppPageLayout\\s*\\(").findAll(code).forEach { call ->
            val end = closing(code, call.range.last, '(', ')')
            Regex("\\b(header|supporting)\\s*=\\s*\\{").findAll(code.substring(call.range.last + 1, end)).forEach { slot ->
                val brace = call.range.last + 1 + slot.range.last
                val bodyEnd = closing(code, brace, '{', '}')
                Regex("\\brememberSaveable\\b").findAll(code.substring(brace + 1, bodyEnd)).forEach { found += brace + 1 + it.range.first }
            }
        }
        return found.distinct()
    }
    @Test fun actualScreenSlotsDoNotOwnSavedState() {
        val root = File("src/main/java/com/example/ui/screens")
        assertTrue("Screen source directory must exist", root.isDirectory)
        val findings = root.walkTopDown().filter { it.extension == "kt" }.flatMap { file ->
            val source = file.readText(); violations(source).map { pos -> "${file.path}:${source.take(pos).count { it == '\n' } + 1}" }
        }.toList()
        assertEquals("Elevate state above AppPageLayout slots", emptyList<String>(), findings)
    }
    @Test fun detectorFindsNestedStateInBothSlotsAndIgnoresOtherContent() {
        assertEquals(2, violations("AppPageLayout(header = { Column { rememberSaveable { x } } }, supporting = { rememberSaveable { y } }) { rememberSaveable { z } }").size)
        assertTrue(violations("val state = rememberSaveable { x }; AppPageLayout(header = { Text(state) }, supporting = { /* rememberSaveable { } */ Text(\"rememberSaveable { }\") }) {}").isEmpty())
        assertTrue(violations("OtherLayout(header = { rememberSaveable { x } })").isEmpty())
    }
}
