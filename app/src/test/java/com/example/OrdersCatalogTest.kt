package com.example

import com.example.data.repository.OrdersCatalog
import com.example.data.repository.ReligiousOrdersData
import com.example.ui.screens.OfficeGroup
import com.example.ui.screens.officeDisplayOrder
import com.example.ui.screens.officeGroup
import java.io.FileNotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

internal val ORDERS_NEW = """[
 {"id":"mercedarios-odem","name":{"es":"Orden de la Merced","en":"Order of Mercy","pt":"Ordem da Mercê"},
  "abbreviation":"O. de M.","foundationYear":"1218","founder":{"es":"San Pedro Nolasco","en":"St. Peter Nolasco","pt":"São Pedro Nolasco"},
  "description":{"es":"Descripción es","en":"Description en","pt":"Descrição pt"},
  "charism":{"es":"Redención","en":"Redemption","pt":"Redenção"},"gender":"men"},
 {"id":"franciscanos-ofm","name":{"es":"Duplicado","en":"Dup","pt":"Dup"}}
]"""
internal val ORDERS_DETAILS = """[
 {"id":"franciscanos-ofm","kind":"order","motto":{"es":"Paz y bien","en":"Peace and good","pt":"Paz e bem"},
  "approval":{"es":"Honorio III, 1223","en":"Honorius III, 1223","pt":"Honório III, 1223"},
  "notableSaints":{"es":["San Antonio de Padua"],"en":["St. Anthony of Padua"],"pt":["Santo Antônio de Pádua"]},
  "presence":{"es":"Presentes en todo el mundo.","en":"Present worldwide.","pt":"Presentes no mundo todo."},
  "sources":["Annuario Pontificio 2025","vatican.va"]},
 {"id":"mercedarios-odem","kind":"congregation","motto":null,"approval":{"es":"","en":"","pt":""},"sources":["Fuente X"]}
]"""
internal val OFFICES_NEW = """[
 {"id":"office_obispo_auxiliar","name":{"es":"Obispo auxiliar","en":"Auxiliary bishop","pt":"Bispo auxiliar"},
  "description":{"es":"Descripción es","en":"Description en","pt":"Descrição pt"},
  "group":"clergy","after":"office_obispo","sources":["CIC cc. 403-411"]},
 {"id":"office_secretario_estado","name":{"es":"Secretario de Estado","en":"Secretary of State","pt":"Secretário de Estado"},
  "description":{"es":"d","en":"d","pt":"d"},"group":"governance","after":null,"sources":["Praedicate Evangelium"]},
 {"id":"office_prefecto","name":{"es":"Prefecto","en":"Prefect","pt":"Prefeito"},
  "description":{"es":"d","en":"d","pt":"d"},"group":"governance","after":"office_no_existe","sources":[]}
]"""

internal fun ordersFixtureCatalog(
    orders: String? = ORDERS_NEW, details: String? = ORDERS_DETAILS, offices: String? = OFFICES_NEW
) = OrdersCatalog { name ->
    val text = when (name) {
        OrdersCatalog.NEW_ORDERS -> orders
        OrdersCatalog.DETAILS -> details
        OrdersCatalog.NEW_OFFICES -> offices
        else -> null
    } ?: throw FileNotFoundException(name)
    text.byteInputStream()
}

class OrdersCatalogTest {
    @Test fun mergesDetailsIntoExistingOrders() {
        val ofm = ordersFixtureCatalog().orders.first { it.id == "franciscanos-ofm" }
        assertEquals("order", ofm.kind)
        assertEquals("Paz y bien", ofm.motto?.es)
        assertEquals("Honorius III, 1223", ofm.approval?.en)
        assertEquals(1, ofm.notableSaints.size)
        assertEquals("Santo Antônio de Pádua", ofm.notableSaints[0].pt)
        assertEquals(listOf("Annuario Pontificio 2025", "vatican.va"), ofm.sources)
        assertEquals(ReligiousOrdersData.orders.first { it.id == "franciscanos-ofm" }.name, ofm.name)
    }

    @Test fun newOrdersAreAppendedAndDuplicatesIgnored() {
        val orders = ordersFixtureCatalog().orders
        assertEquals(38, orders.size)
        assertEquals(ReligiousOrdersData.orders.map { it.id }, orders.take(37).map { it.id })
        val m = orders.last()
        assertEquals("mercedarios-odem", m.id)
        assertEquals("men", m.gender)
        assertEquals("congregation", m.kind)
        assertNull(m.motto)
        assertNull(m.approval)
        assertEquals(1, orders.count { it.id == "franciscanos-ofm" })
    }

    @Test fun officesInsertedAfterTheRightId() {
        val offices = ordersFixtureCatalog().offices
        assertEquals(29, offices.size)
        val ids = offices.map { it.id }
        assertEquals(ids.indexOf("office_obispo") + 1, ids.indexOf("office_obispo_auxiliar"))
        // null and unknown "after" append at the end, in file order.
        assertEquals(listOf("office_secretario_estado", "office_prefecto"), ids.takeLast(2))
        assertEquals(listOf("CIC cc. 403-411"), offices.first { it.id == "office_obispo_auxiliar" }.sources)
    }

    @Test fun governanceGroup() {
        val offices = ordersFixtureCatalog().offices
        val gov = offices.first { it.id == "office_secretario_estado" }
        assertEquals(OfficeGroup.Governance, officeGroup(gov.id, gov.group))
        assertEquals(OfficeGroup.Clergy, officeGroup("office_obispo_auxiliar", "clergy"))
        assertEquals(OfficeGroup.Other, officeGroup("office_x", "bogus"))
        val order = officeDisplayOrder(offices.map { it.id }, offices.map { it.group })
        val govPositions = order.filter { offices[it].group == "governance" }
        assertEquals(2, govPositions.size)
        val clergyLast = order.indexOfLast { officeGroup(offices[it].id, offices[it].group) == OfficeGroup.Clergy }
        assertTrue(order.indexOf(govPositions.first()) > clergyLast)
    }

    @Test fun missingOrMalformedFilesAreIgnored() {
        val none = ordersFixtureCatalog(null, null, null)
        assertEquals(ReligiousOrdersData.orders, none.orders)
        assertEquals(ReligiousOrdersData.offices, none.offices)
        val bad = ordersFixtureCatalog("{not json", "[1,2", "oops")
        assertEquals(37, bad.orders.size)
        assertEquals(26, bad.offices.size)
        val partial = ordersFixtureCatalog(orders = "garbage")
        assertEquals(37, partial.orders.size)
        assertEquals(29, partial.offices.size)
    }
}
