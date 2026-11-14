package com.example

import com.example.data.repository.NovenaCatalog
import com.example.data.repository.dayNumbers
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class NovenaCatalogLoadingTest {
    @Test fun indexAndProgressChecksDoNotLoadAnyPrayerBody() {
        val opened=mutableListOf<String>()
        val catalog=NovenaCatalog { name -> opened += name
            NovenaCatalogLoadingTest::class.java.classLoader!!.getResourceAsStream(name)!! }
        val entries=catalog.allNovenas
        assertEquals(246,entries.size)
        assertEquals(3068,entries.sumOf {it.dayNumbers().size})
        assertTrue(entries.all {it.days.size == it.dayNumbers().size})
        assertEquals(listOf("index.json"),opened)
        val selected=catalog.preload(entries.first().id)
        assertEquals(2,opened.size)
        assertTrue(selected.days.first().prayer.es.isNotBlank())
        assertEquals(1,catalog.cachedBodyCount())
        // The reader owns its snapshot even if the catalog later evicts its body.
        val prayer=selected.days.first().prayer
        catalog.preload(entries.last().id)
        assertEquals(prayer,selected.days.first().prayer)
    }
}
