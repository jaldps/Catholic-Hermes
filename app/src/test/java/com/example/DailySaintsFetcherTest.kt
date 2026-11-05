package com.example

import com.example.data.news.DailySaintsFetcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/** Vatican News saint-page parsing must skip per-language chrome headings. */
class DailySaintsFetcherTest {

    @Test
    fun spanishPageSkipsBuscarAndPageTitle() {
        val html = """
            <html><body>
            <h2>Menu</h2><h2>Buscar</h2><h2>Santo del día</h2>
            <h2>s. Leonardo, eremita</h2>
            <p>Nace en Limoges en el siglo VI. Como todos los nobles Leonardo debe enrolarse, pero flanquea al arzobispo de Reims trabajando para liberar a centenares de prisioneros.</p>
            </body></html>
        """.trimIndent()
        val saint = DailySaintsFetcher.parseVatican(html)
        assertNotNull(saint)
        assertEquals("s. Leonardo, eremita", saint!!.name)
        assertEquals(true, saint.biography.startsWith("Nace en Limoges"))
    }

    @Test
    fun portuguesePageSkipsBusca() {
        val html = """
            <html><body>
            <h2>Busca</h2><h2>Santo do dia</h2>
            <h2>SS. Donino, Teótimo e Companheiros, mártires</h2>
            <p>Todos naturais de Cesareia, na Palestina, defenderam a sua fé com suas vidas. Donino era um jovem médico.</p>
            </body></html>
        """.trimIndent()
        val saint = DailySaintsFetcher.parseVatican(html)
        assertNotNull(saint)
        assertEquals("SS. Donino, Teótimo e Companheiros, mártires", saint!!.name)
    }

    @Test
    fun englishPageStillWorks() {
        val html = """
            <html><body>
            <h2>Search</h2><h2>Saint of the day</h2>
            <h2>St. Charles Borromeo, Archbishop of Milan and Cardinal</h2>
            <p>Charles Borromeo was just 22 years old when he was created Cardinal by his uncle, Pope Pius IV.</p>
            </body></html>
        """.trimIndent()
        val saint = DailySaintsFetcher.parseVatican(html)
        assertNotNull(saint)
        assertEquals("St. Charles Borromeo, Archbishop of Milan and Cardinal", saint!!.name)
    }
}
