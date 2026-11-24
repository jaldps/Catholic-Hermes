package com.example

import com.example.ui.design.MaxAssetDecodeWidth
import com.example.ui.design.assetSampleSize
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** 1.9.3: an image must never decode wider than the caller asked, however large the asset is. */
class AssetDecodeBudgetTest {
    @Test fun sampleNeverExceedsTheRequestedWidth() {
        val assets = listOf(1080, 1350, 1600, 900, 727, 240, 1)
        val budgets = listOf(144, 240, 720, 1080)
        for (width in assets) for (budget in budgets) {
            val sample = assetSampleSize(width, budget)
            val decoded = width / sample
            assertTrue("decoded $decoded <= budget $budget", decoded <= budget)
            assertTrue("sampling is not wasteful", decoded * 2 >= budget || sample == 1)
        }
    }

    @Test fun heroDecodeIsCappedToThePhoneBudget() {
        val requested = 1080
        val effective = requested.coerceAtMost(MaxAssetDecodeWidth)
        assertEquals(720, effective)
        assertEquals(2, assetSampleSize(1080, effective))
    }
}
