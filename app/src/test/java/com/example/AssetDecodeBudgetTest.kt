package com.example

import com.example.ui.design.MaxAssetDecodeWidth
import com.example.ui.design.assetSampleSize
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** 2.0.1: images shrink by powers of two but never below the requested width (no blurry, upscaled heroes). */
class AssetDecodeBudgetTest {
    @Test fun sampleKeepsAtLeastTheRequestedWidthWithoutWaste() {
        val assets = listOf(1080, 1350, 1600, 900, 727, 240, 1)
        val budgets = listOf(144, 240, 720, 1080)
        for (width in assets) for (budget in budgets) {
            val sample = assetSampleSize(width, budget)
            val decoded = width / sample
            assertTrue("decoded $decoded >= budget $budget (or the asset is smaller)", decoded >= budget || sample == 1)
            assertTrue("sampling is not wasteful: $decoded < 2 x $budget", decoded < budget * 2 || sample == 1)
        }
    }

    @Test fun heroDecodeIsCappedAtPhoneWidth() {
        assertEquals(1080, MaxAssetDecodeWidth)
        assertEquals(1, assetSampleSize(1600, 1080))
        assertEquals(2, assetSampleSize(1600, 720))
    }
}
