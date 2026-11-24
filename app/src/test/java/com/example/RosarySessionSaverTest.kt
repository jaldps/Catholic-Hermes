package com.example

import android.app.Application
import androidx.compose.runtime.saveable.SaverScope
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.RosaryMysteryType
import com.example.ui.screens.Devotion
import com.example.ui.screens.MysterySelection
import com.example.ui.screens.RosaryEvent
import com.example.ui.screens.RosarySession
import com.example.ui.screens.RosarySessionStore
import com.example.ui.screens.StepMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** Old (Rosary / Mercy) and new (other chaplet) sessions are both read back, from the saver and from storage. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class RosarySessionSaverTest {
    private val scope = SaverScope { true }
    private fun restore(values: List<Any>): RosarySession = RosarySession.Saver.restore(values)!!
    private val steps = object : StepMap { override val stepCount = 30; override fun isOptional(index: Int) = false }

    @Test fun fiveValueSessionsSavedBeforeOtherChapletsStillRestore() {
        val rosary = restore(listOf(0, "SORROWFUL", 14, 3, true))
        assertEquals(RosarySession(Devotion.ROSARY, MysterySelection.Picked(RosaryMysteryType.SORROWFUL), 14, 3, true), rosary)
        val mercy = restore(listOf(1, "", 2, 28, false))
        assertEquals(Devotion.MERCY, mercy.devotion)
        assertEquals(28, mercy.mercyStep)
        assertEquals(MysterySelection.ByDay, mercy.mysterySelection)
        assertNull(mercy.chapletId)
        assertEquals(0, mercy.chapletStep)
    }

    @Test fun otherChapletSessionRoundTrips() {
        val session = RosarySession(Devotion.OTHER, MysterySelection.Picked(RosaryMysteryType.GLORIOUS), 5, 6, true, "siete-dolores-corona", 17)
        val saved = with(RosarySession.Saver) { scope.save(session) }!!
        assertEquals(session, RosarySession.Saver.restore(saved))
    }

    @Test fun tabIndexesZeroAndOneAreUnchangedAndTwoIsOther() {
        assertEquals(Devotion.ROSARY, Devotion.fromTab(0))
        assertEquals(Devotion.MERCY, Devotion.fromTab(1))
        assertEquals(Devotion.OTHER, Devotion.fromTab(2))
        assertEquals(Devotion.OTHER, Devotion.fromTab(9))
        assertEquals(Devotion.ROSARY, Devotion.fromTab(-3))
        assertEquals(0, Devotion.ROSARY.tab); assertEquals(1, Devotion.MERCY.tab); assertEquals(2, Devotion.OTHER.tab)
    }

    @Test fun pickingAChapletStartsItAndRepickingKeepsItsPlace() {
        val today = RosaryMysteryType.JOYFUL
        val started = RosarySession().reduce(RosaryEvent.PickChaplet("coronilla-lagrimas"), steps, today)
        assertEquals(Devotion.OTHER, started.devotion)
        assertEquals("coronilla-lagrimas", started.chapletId)
        val moved = started.reduce(RosaryEvent.GoTo(12), steps, today)
        assertEquals(12, moved.chapletStep)
        assertEquals(0, moved.rosaryStep)
        val backToRosary = moved.reduce(RosaryEvent.SelectDevotion(Devotion.ROSARY), steps, today)
        assertEquals(12, backToRosary.chapletStep)
        assertEquals(12, backToRosary.reduce(RosaryEvent.PickChaplet("coronilla-lagrimas"), steps, today).chapletStep)
        assertEquals(0, backToRosary.reduce(RosaryEvent.PickChaplet("santa-ana-coronilla"), steps, today).chapletStep)
    }

    @Test fun storedSessionsOfBothGenerationsLoad() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val prefs = context.getSharedPreferences("unfinished_prayer_session", 0)
        prefs.edit().clear()
            .putInt("devotion", 1).putString("mystery", "JOYFUL").putInt("rosary_step", 40).putInt("mercy_step", 9)
            .putBoolean("instructions", true).commit()
        val old = RosarySessionStore.load(context)!!
        assertEquals(RosarySession(Devotion.MERCY, MysterySelection.Picked(RosaryMysteryType.JOYFUL), 40, 9, true), old)
        val chaplet = old.copy(devotion = Devotion.OTHER, chapletId = "san-charbel", chapletStep = 7)
        RosarySessionStore.save(context, chaplet, RosaryMysteryType.JOYFUL)
        assertEquals(chaplet, RosarySessionStore.load(context))
    }
}
