package com.example

import com.example.data.repository.DevotionImageCredits
import com.example.ui.screens.imageCreditLine
import com.example.data.models.AppLanguage
import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DevotionImageCreditsTest {
    private val dir = File("src/main/assets/devotions/images")
    private val real = DevotionImageCredits(
        open = { File(dir, it).inputStream() },
        exists = { File("src/main/assets/$it").isFile }
    )

    @Test fun parsesBundledFileAndFindsGuadalupe() {
        val g = real.credit("guadalupe")!!
        assertEquals("Public domain", g.license)
        assertTrue(g.safeUrl!!.startsWith("https://"))
        assertEquals("devotions/images/guadalupe.jpg", real.imagePath("guadalupe"))
        assertTrue(imageCreditLine(g, AppLanguage.ES).startsWith("Imagen: Public domain"))
    }

    @Test fun flagsReferentialAndUserSupplied() {
        val s = real.credit("cristo_sentenciado")!!
        assertTrue(s.referential)
        assertTrue(imageCreditLine(s, AppLanguage.ES).startsWith("Imagen referencial:"))
        assertTrue(imageCreditLine(s, AppLanguage.EN).startsWith("Reference image:"))
        assertTrue(imageCreditLine(s, AppLanguage.PT).startsWith("Imagem de referência:"))
        val o = real.credit("jesus_de_ocotlan")!!
        assertTrue(o.userSupplied)
        assertEquals("Foto aportada por el usuario", imageCreditLine(o, AppLanguage.ES))
        assertFalse(real.credit("guadalupe")!!.referential)
    }

    @Test fun ignoresUnknownIdsAndMissingFiles() {
        assertNull(real.credit("no_existe"))
        assertNull(real.imagePath("no_existe"))
        val broken = DevotionImageCredits(open = { "not json".byteInputStream() })
        assertNull(broken.credit("guadalupe"))
        val noFile = DevotionImageCredits(open = { throw java.io.FileNotFoundException(it) })
        assertNull(noFile.imagePath("guadalupe"))
    }

    @Test fun omitsEmptyAuthorAndBlocksNonHttps() {
        val c = DevotionImageCredits(open = {
            """{"images":[{"id":"x","author":"","license":"CC0","source":"Commons","url":"http://a.b"}]}""".byteInputStream()
        }, exists = { true }).credit("x")!!
        assertEquals("Image: CC0 · Commons", imageCreditLine(c, AppLanguage.EN))
        assertNull(c.safeUrl)
        assertNotNull(real.credit("virgen_del_carmen")!!.author.takeIf { it.isNotBlank() })
    }
}
