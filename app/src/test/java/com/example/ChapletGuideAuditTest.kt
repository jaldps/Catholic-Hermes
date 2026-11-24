package com.example

import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import com.example.data.repository.ChapletCatalog
import com.example.data.repository.ChapletPrayers
import com.example.ui.screens.ChapletVariables
import com.example.ui.screens.buildChapletGuide
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** 1.9.3 audit: every bundled chaplet must build a guide with real assets and prayers. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ChapletGuideAuditTest {
    @Test fun allFiftyFiveChapletsBuildWithoutAnException() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        ChapletCatalog.install(context)
        val catalog = ChapletCatalog.default
        assertEquals(55, catalog.all.size)
        val repository = CatholicDataRepository(context)
        val prayers = ChapletPrayers.of(repository.vaticanPrayers)
        val failures = mutableListOf<String>()
        catalog.all.forEach { chaplet ->
            val guide = runCatching { buildChapletGuide(chaplet, AppLanguage.ES, prayers, ChapletVariables()) }
                .onFailure { failures += "${chaplet.id}: ${it.javaClass.simpleName}" }.getOrNull()
            assertNotNull("guide for ${chaplet.id}", guide)
            assertTrue("guide has steps for ${chaplet.id}", guide!!.guide.steps.isNotEmpty())
        }
        assertTrue("guide failures: $failures", failures.isEmpty())
    }
}
