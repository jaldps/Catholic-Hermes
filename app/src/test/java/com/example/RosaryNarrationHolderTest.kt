package com.example

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.data.audio.LiturgicalTtsManager
import com.example.data.audio.RecitationVoiceMode
import com.example.data.models.AppLanguage
import com.example.data.models.RosaryMysteryType
import com.example.ui.screens.*
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class RosaryNarrationHolderTest {
    private val engines = mutableListOf<LiturgicalTtsManager>()
    private fun engine() = readyNarrator(ApplicationProvider.getApplicationContext<Application>(), AppLanguage.ES).also { engines += it }
    @After fun close() { engines.forEach { it.release() } }
    private val target = NarrationTarget(Devotion.ROSARY, RosaryMysteryType.JOYFUL, 40, AppLanguage.ES, RecitationVoiceMode.DUAL_DIALOGUE)

    @Test fun activityShutdownWaitsForTheLastHolderAndClosingALeaseTwiceIsHarmless() {
        val audio = engine()
        val one = audio.retainForRosaryHolder(); val two = audio.retainForRosaryHolder()
        audio.speakSingle("Ave María", title = "Rosario")
        val stopped = audio.stopCount.value
        audio.release()
        assertTrue(audio.isReady.value); assertTrue(audio.isPlaying.value)
        assertEquals(stopped, audio.stopCount.value)
        one.close(); one.close()
        assertTrue(audio.isReady.value)
        two.close()
        assertFalse(audio.isReady.value); assertFalse(audio.isPlaying.value)
    }
    @Test fun rotatingRebindsTheSameEngineWithoutStoppingOrRepeatingItsPrayer() {
        val holder = RosaryNarrationHolder()
        val original = holder.bind(engine())
        var spoken = 0
        fun sink() = object : NarrationSink {
            override fun speak(target: NarrationTarget) { spoken++; original.speakSingle("Ave María", title = "Rosario") }
            override fun stop() = original.stop()
        }
        holder.connect(sink()); holder.engineReady(true)
        holder.controller.dispatch(NarrationEvent.Toggle(target))
        assertEquals(1, spoken)
        val stopped = original.stopCount.value
        holder.detach(true)
        original.release() // The old MainActivity's onDestroy.
        val replacement = engine() // The recreated MainActivity's new, idle engine.
        assertSame(original, holder.bind(replacement))
        holder.connect(sink()); holder.engineReady(true)
        holder.controller.dispatch(NarrationEvent.TargetChanged(target))
        assertEquals(1, spoken); assertEquals(stopped, original.stopCount.value)
        assertSame(original, LiturgicalTtsManager.active)
        assertTrue(original.isPlaying.value)
        assertTrue(holder.controller.state is NarrationState.Speaking)
        holder.detach(false)
        assertFalse(original.isPlaying.value); assertFalse(original.isReady.value)
        assertEquals(NarrationState.Idle, holder.controller.state)
    }
}
