package com.example

import com.example.data.repository.ConfessionCatalog
import com.example.data.models.AppLanguage
import com.example.ui.confession.ConfessionDraft
import androidx.compose.runtime.saveable.SaverScope
import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayInputStream

class ConfessionCatalogTest {
    @Test fun reviewedAssetsLoadAllProfilesAndEveryGuidePartInThreeLanguages() {
        val data = requireNotNull(ConfessionCatalog { javaClass.classLoader!!.getResourceAsStream(it)!! }.load())
        assertEquals(setOf("children", "youth", "married", "single", "consecrated"), data.profiles.map { it.id }.toSet())
        assertEquals(244, data.questions.size)
        assertEquals(244, data.questions.map { it.id }.distinct().size)
        assertTrue(data.profiles.all { it.sections.size == 12 })
        assertEquals(8, data.guide.steps.size); assertEquals(3, data.guide.contrition.size); assertEquals(9, data.guide.faq.size)
        AppLanguage.entries.forEach { l ->
            assertTrue(data.questions.all { it.text.get(l).isNotBlank() && it.basis.startsWith("CIC ") })
            assertTrue(data.guide.contrition.all { it.text.get(l).isNotBlank() && it.source.get(l).isNotBlank() })
            assertTrue(data.guide.steps.all { it.body.get(l).isNotBlank() })
            assertTrue(data.guide.faq.all { it.answer.get(l).isNotBlank() })
        }
    }
    @Test fun malformedOrUnavailableContentReturnsFailureWithoutInventedFallbackText() {
        assertNull(ConfessionCatalog { ByteArrayInputStream("{}".toByteArray()) }.load())
        assertNull(ConfessionCatalog { throw java.io.IOException("unavailable") }.load())
    }
    @Test fun privateDraftSaverSerializesOnlyAConstantAndRestoresAnEmptyDraft() {
        val draft = ConfessionDraft().apply { toggle("single:c1:0"); notes = "private sentinel" }
        val marker = with(ConfessionDraft.Saver) { object : SaverScope {
            override fun canBeSaved(value: Any) = true
        }.save(draft) }
        assertEquals(false, marker)
        val restored = requireNotNull(ConfessionDraft.Saver.restore(false))
        assertTrue(restored.selected.isEmpty()); assertEquals("", restored.notes)
        draft.clear(); assertTrue(draft.selected.isEmpty()); assertEquals("", draft.notes)
    }
}
