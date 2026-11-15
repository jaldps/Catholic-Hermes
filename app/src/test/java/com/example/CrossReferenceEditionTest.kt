package com.example

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleData
import com.example.data.xref.CrossReferenceIndex
import com.example.data.xref.VerseRef
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.File

/** A downloaded edition's own xref.bin wins over the bundled language table; without one the bundled table is used. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class CrossReferenceEditionTest {
    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()
    private val canon = BibleData.catholicCanonBooks.map { it.id }

    /** One record: Genesis 1:1 -> Psalms 3:5 (book index shl 16 | chapter shl 8 | verse). */
    private fun fixture(): File = File.createTempFile("xref", ".bin").apply {
        deleteOnExit()
        val out = ByteArrayOutputStream()
        DataOutputStream(out).use {
            it.write("XRF1".toByteArray()); it.writeInt(1)
            it.writeInt((0 shl 16) or (1 shl 8) or 1)
            val target = (canon.indexOf("psa") shl 16) or (3 shl 8) or 5
            it.writeInt(target); it.writeInt(target); it.writeShort(9)
        }
        writeBytes(out.toByteArray())
    }

    @Test fun editionTableIsPreferredWhenPresent() {
        val file = fixture()
        val index = CrossReferenceIndex(context, canon) { id -> file.takeIf { id == "es.lbl" } }
        val own = index.referencesFrom(VerseRef("gen", 1, 1), AppLanguage.ES, editionId = "es.lbl")
        assertEquals(listOf(VerseRef("psa", 3, 5)), own.map { it.target })
        assertEquals(mapOf(1 to 1), index.countsForChapter("gen", 1, AppLanguage.ES, "es.lbl"))
        assertEquals(1, index.allConnections(AppLanguage.ES, editionId = "es.lbl").count())
    }

    @Test fun missingOrBundledEditionFallsBackToTheBundledLanguageTable() {
        val file = fixture()
        val index = CrossReferenceIndex(context, canon) { id -> file.takeIf { id == "es.lbl" } }
        for (edition in listOf(null, "bundled", "es.lsb")) {
            val refs = index.referencesFrom(VerseRef("gen", 1, 1), AppLanguage.ES, editionId = edition).map { it.target }
            assertTrue("$edition", VerseRef("joh", 1, 1) in refs && refs.size > 1)
        }
        // A corrupt edition table is ignored too.
        val broken = File.createTempFile("xref", ".bin").apply { deleteOnExit(); writeText("nope") }
        val bad = CrossReferenceIndex(context, canon) { broken }
        assertTrue(bad.referencesFrom(VerseRef("gen", 1, 1), AppLanguage.EN, editionId = "es.lbl").size > 1)
        // Switching back and forth keeps each answer separate.
        val first = index.referencesFrom(VerseRef("gen", 1, 1), AppLanguage.ES, editionId = "es.lbl").size
        index.referencesFrom(VerseRef("gen", 1, 1), AppLanguage.ES)
        assertEquals(first, index.referencesFrom(VerseRef("gen", 1, 1), AppLanguage.ES, editionId = "es.lbl").size)
    }

    @Test fun bundledSpanishGenesisOneTwoReachesPsalm103Verse30() {
        val refs = CrossReferenceIndex(context, canon).referencesFrom(VerseRef("gen", 1, 2), AppLanguage.ES).map { it.target }
        assertTrue(VerseRef("psa", 103, 30) in refs)  // "Cuando envías tu soplo, son creados"
        assertTrue(VerseRef("psa", 103, 31) !in refs)
    }
}
