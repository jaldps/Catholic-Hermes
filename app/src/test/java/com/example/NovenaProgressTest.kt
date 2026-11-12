package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.robolectric.RobolectricTestRunner
import com.example.ui.screens.NovenaExtraDaysStore
import com.example.ui.screens.decodeNovenaDays
import com.example.ui.screens.encodeNovenaDays
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Novena progress: the Room row keeps its bitmask of days 1..31 exactly as
 * before (existing progress is never rewritten), and days beyond 31 for the
 * 54 to 275-day novenas live in a parallel store.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class NovenaProgressTest {

    @Test
    fun bitmaskRoundTripsDaysOneToThirtyOne() {
        val days = setOf(1, 2, 9, 30, 31)
        assertEquals(days, decodeNovenaDays(encodeNovenaDays(days)))
    }

    @Test
    fun legacyMaskKeepsItsMeaning() {
        // Day 1 and day 3 prayed under the original encoding: bits 0 and 2.
        assertEquals(setOf(1, 3), decodeNovenaDays(0b101))
        assertEquals(0b101, encodeNovenaDays(setOf(1, 3)))
        assertEquals(emptySet<Int>(), decodeNovenaDays(0))
    }

    @Test
    fun daysBeyondThirtyOneAreNotLostByTheMask() {
        // The mask stores only 1..31; the rest must go to the extra store.
        assertEquals(setOf(5), decodeNovenaDays(encodeNovenaDays(setOf(5, 32, 275))))
    }

    @Test
    fun extraStorePersistsLongNovenaDays() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val store = NovenaExtraDaysStore(context)
        store.put("novena-largo", setOf(3, 32, 54, 275))
        assertEquals(setOf(32, 54, 275), NovenaExtraDaysStore(context).all()["novena-largo"])
        store.put("novena-largo", setOf(3))
        assertTrue(NovenaExtraDaysStore(context).all()["novena-largo"] == null)
    }
}
