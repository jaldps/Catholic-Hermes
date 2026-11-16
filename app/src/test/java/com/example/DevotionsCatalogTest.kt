package com.example

import com.example.data.repository.DevotionsAndHistoryData
import com.example.data.repository.DevotionsCatalog
import java.io.FileNotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

internal val MARIAN_DETAILS_JSON = """[
 {"id":"guadalupe","recognition":{"es":"Aprobada","en":"Approved","pt":"Aprovada"},
  "promises":{"text":{"es":"Promesa es","en":"Promise en","pt":"Promessa pt"},
    "attribution":{"es":"A Juan Diego","en":"To Juan Diego","pt":"A Juan Diego"},"source":"Nican Mopohua"},
  "miracles":{"text":{"es":"Milagro es","en":"Miracle en","pt":"Milagre pt"}},
  "prayer":{"remove":true},
  "practice":{"es":"Peregrinación","en":"Pilgrimage","pt":"Peregrinação"},
  "sources":["Fuente A","Fuente B"]},
 {"id":"aparecida","recognition":{"es":"Coronada","en":"Crowned","pt":"Coroada"},"promises":null,"miracles":null,"prayer":null,
  "sources":["Fuente C"]},
 {"id":"no_existe","recognition":{"es":"x","en":"x","pt":"x"}}
]"""
internal val CHRIST_DETAILS_JSON = """[
 {"id":"sacred_heart_jesus","recognition":{"es":"Fiesta universal","en":"Universal feast","pt":"Festa universal"},
  "promises":{"remove":true},"miracles":null,
  "prayer":{"text":{"es":"Oración es","en":"Prayer en","pt":"Oração pt"},"source":"Raccolta"},
  "practice":{"es":"Primer viernes","en":"First Friday","pt":"Primeira sexta"},"sources":["Fuente D","Fuente E"]}
]"""
internal val MARIAN_NEW_JSON = """[
 {"id":"virgen_nueva","title":{"es":"Virgen Nueva","en":"New Virgin","pt":"Virgem Nova"},
  "feastDay":{"es":"1 ene","en":"1 Jan","pt":"1 jan"},"year":"1588",
  "location":{"es":"Quito","en":"Quito","pt":"Quito"},"primarySanctuary":{"es":"Santuario","en":"Shrine","pt":"Santuário"},
  "history":{"es":"Historia es","en":"History en","pt":"História pt"},"promises":null,
  "miracles":{"es":"Favores","en":"Favours","pt":"Favores"},"prayer":null,"region":"Americas",
  "country":{"es":"Ecuador","en":"Ecuador","pt":"Equador"},"patronageOf":{"es":"Quito","en":"Quito","pt":"Quito"},
  "recognition":{"es":"Culto local","en":"Local cult","pt":"Culto local"},"practice":{"es":"Novena","en":"Novena","pt":"Novena"},
  "sources":["Fuente N1","Fuente N2"]},
 {"id":"guadalupe","title":{"es":"Dup","en":"Dup","pt":"Dup"},"history":{"es":"x","en":"x","pt":"x"}},
 {"id":"sin_titulo","history":{"es":"x","en":"x","pt":"x"}}
]"""
internal val CHRIST_NEW_JSON = """[
 {"id":"cristo_nuevo","title":{"es":"Cristo Nuevo","en":"New Christ","pt":"Cristo Novo"},
  "feastDay":{"es":"Dom","en":"Sun","pt":"Dom"},"year":"1700","primarySanctuary":{"es":"Iglesia","en":"Church","pt":"Igreja"},
  "history":{"es":"Origen es","en":"Origin en","pt":"Origem pt"},"theologicalMeaning":{"es":"Sentido es","en":"Meaning en","pt":"Sentido pt"},
  "promises":null,"miracles":{"es":"","en":"","pt":""},"prayer":{"text":{"es":"Oración nueva","en":"New prayer","pt":"Oração nova"},"source":"Misal"},
  "region":"Europe","country":{"es":"España","en":"Spain","pt":"Espanha"},
  "recognition":{"es":"Culto","en":"Cult","pt":"Culto"},"practice":{"es":"Misa","en":"Mass","pt":"Missa"},"sources":["F1","F2"]}
]"""

internal fun devotionsFixtureCatalog(
    marianDetails: String? = MARIAN_DETAILS_JSON, christDetails: String? = CHRIST_DETAILS_JSON,
    marianNew: String? = MARIAN_NEW_JSON, christNew: String? = CHRIST_NEW_JSON
) = DevotionsCatalog { name ->
    val text = when (name) {
        DevotionsCatalog.MARIAN_DETAILS -> marianDetails
        DevotionsCatalog.CHRIST_DETAILS -> christDetails
        DevotionsCatalog.MARIAN_NEW -> marianNew
        DevotionsCatalog.CHRIST_NEW -> christNew
        else -> null
    } ?: throw FileNotFoundException(name)
    text.byteInputStream()
}

class DevotionsCatalogTest {
    private val baseMarian = DevotionsAndHistoryData.marianApparitions
    private val baseChrist = DevotionsAndHistoryData.christManifestations

    @Test fun replacementSetsTextAndCredit() {
        val g = devotionsFixtureCatalog().marian.first { it.id == "guadalupe" }
        assertEquals("Promesa es", g.promises.es)
        assertEquals("To Juan Diego", g.promiseAttribution?.en)
        assertEquals("Nican Mopohua", g.promiseSource)
        assertEquals("Milagro es", g.recordedMiracles.es)
        assertEquals("Aprobada", g.recognition?.es)
        assertEquals("Peregrinación", g.practice?.es)
        assertEquals(listOf("Fuente A", "Fuente B"), g.sources)
        assertEquals(baseMarian.first { it.id == "guadalupe" }.historyAndApparition, g.historyAndApparition)
    }

    @Test fun removeEmptiesPrayerAndPromises() {
        val g = devotionsFixtureCatalog().marian.first { it.id == "guadalupe" }
        assertEquals("", g.venerationPrayer.es)
        assertNull(g.prayerSource)
        val h = devotionsFixtureCatalog().christ.first { it.id == "sacred_heart_jesus" }
        assertEquals("", h.promises.es)
        assertNull(h.promiseSource)
        assertEquals("Prayer en", h.officialPrayer.en)
        assertEquals("Raccolta", h.prayerSource)
    }

    @Test fun nullKeepsCurrentText() {
        val a = devotionsFixtureCatalog().marian.first { it.id == "aparecida" }
        val base = baseMarian.first { it.id == "aparecida" }
        assertEquals(base.promises, a.promises)
        assertEquals(base.recordedMiracles, a.recordedMiracles)
        assertEquals(base.venerationPrayer, a.venerationPrayer)
        assertEquals("Coroada", a.recognition?.pt)
    }

    @Test fun newEntriesAreAppendedAndDuplicatesOrIncompleteIgnored() {
        val marian = devotionsFixtureCatalog().marian
        assertEquals(baseMarian.size + 1, marian.size)
        val n = marian.last()
        assertEquals("virgen_nueva", n.id)
        assertEquals("Historia es", n.historyAndApparition.es)
        assertEquals("Favours", n.recordedMiracles.en)
        assertEquals("", n.venerationPrayer.es)
        assertEquals("", n.promises.es)
        assertEquals(baseMarian.first { it.id == "guadalupe" }.title, marian.first { it.id == "guadalupe" }.title)
        val christ = devotionsFixtureCatalog().christ
        assertEquals(baseChrist.size + 1, christ.size)
        val c = christ.last()
        assertEquals("1700", c.year)
        assertEquals("Meaning en", c.theologicalMeaning.en)
        assertEquals("Misal", c.prayerSource)
        assertEquals("Origin en", c.originHistory.en)
    }

    @Test fun missingFilesLeaveBaseData() {
        val c = devotionsFixtureCatalog(null, null, null, null)
        assertEquals(baseMarian, c.marian)
        assertEquals(baseChrist, c.christ)
    }

    @Test fun malformedFilesAreIgnored() {
        val c = devotionsFixtureCatalog("{not json", "[1,2", "oops", "[{\"id\":5}]")
        assertEquals(baseMarian, c.marian)
        assertEquals(baseChrist, c.christ)
        assertTrue(c.marian.all { it.recognition == null })
    }
}
