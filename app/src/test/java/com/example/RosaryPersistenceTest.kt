package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.RosaryMysteryType
import com.example.ui.screens.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class RosaryPersistenceTest {
    @Test fun freshScreenRestoresExactDevotionMysteryAndBothPrayerPositions() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        context.getSharedPreferences("unfinished_prayer_session", 0).edit().clear().commit()
        val mystery = RosaryMysteryType.values().last()
        val before = RosarySession(Devotion.MERCY, MysterySelection.Picked(mystery), rosaryStep = 73, mercyStep = 28, instructionsOn = true)
        RosarySessionStore.save(context, before, mystery)
        assertEquals(before, RosarySessionStore.load(context))
    }
    @Test fun weekdayChoiceIsFrozenForAnInterruptedSession() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val mystery = RosaryMysteryType.values().first()
        RosarySessionStore.save(context, RosarySession(rosaryStep = 9), mystery)
        val reloaded = requireNotNull(RosarySessionStore.load(context))
        assertEquals(MysterySelection.Picked(mystery), reloaded.mysterySelection)
        assertEquals(9, reloaded.rosaryStep)
    }
}
