package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.ReligiousOrdersData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Religious orders compendium + Church offices guide in ES/EN/PT. */
class ReligiousOrdersDataTest {

    private val languages = listOf(AppLanguage.ES, AppLanguage.EN, AppLanguage.PT)

    @Test
    fun ordersCoverMenAndWomen() {
        val orders = ReligiousOrdersData.orders
        assertTrue("orders >= 35", orders.size >= 35)
        assertTrue(
            "all named male orders present",
            listOf("ofm", "sj", "op", "osb", "oc", "osa", "ocso", "sdb", "cssr",
                "ofmcap", "ocist", "ocart", "cp", "mi", "fms", "omi", "svd", "cmf", "cssp", "ssp")
                .all { abbr -> orders.any { it.abbreviation.equals(abbr, ignoreCase = true) } }
        )
        for (order in orders) {
            for (lang in languages) {
                assertTrue("order ${order.id} ${lang.code} name", order.name.get(lang).isNotBlank())
                assertTrue("order ${order.id} ${lang.code} desc", order.description.get(lang).length > 150)
                assertTrue("order ${order.id} ${lang.code} charism", order.charism.get(lang).isNotBlank())
            }
            assertTrue("order ${order.id} abbr", order.abbreviation.isNotBlank())
            assertTrue("order ${order.id} year", order.foundationYear.isNotBlank())
        }
    }

    @Test
    fun officesRunFromAltarServerToPope() {
        val offices = ReligiousOrdersData.offices
        assertTrue("offices >= 24", offices.size >= 24)
        val ids = offices.map { it.id }
        for (required in listOf("monaguillo", "diacono", "sacerdote", "parroco", "obispo", "cardenal", "papa", "monja", "madre-superiora")) {
            assertTrue("missing office $required", ids.any { it.contains(required.split("-")[0]) })
        }
        for (office in offices) {
            for (lang in languages) {
                assertTrue("office ${office.id} ${lang.code}", office.description.get(lang).length > 150)
            }
        }
    }

    @Test
    fun idsUnique() {
        assertEquals(37, ReligiousOrdersData.orders.map { it.id }.distinct().size)
        assertEquals(26, ReligiousOrdersData.offices.map { it.id }.distinct().size)
    }
}
