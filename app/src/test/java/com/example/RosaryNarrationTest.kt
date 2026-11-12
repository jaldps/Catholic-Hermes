package com.example

import com.example.data.audio.RecitationVoiceMode
import com.example.data.audio.SpeechTracker
import com.example.data.models.AppLanguage
import com.example.data.models.RosaryMysteryType
import com.example.ui.components.ReelGesture
import com.example.ui.screens.Devotion
import com.example.ui.screens.NarrationCommand
import com.example.ui.screens.NarrationController
import com.example.ui.screens.NarrationEvent
import com.example.ui.screens.NarrationSink
import com.example.ui.screens.NarrationState
import com.example.ui.screens.NarrationTarget
import com.example.ui.screens.reduceNarration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Pure JVM tests for pray-aloud mode, the speech tracker and the bead reel's gestures. */
class RosaryNarrationTest {
    private fun target(
        step: Int = 0,
        devotion: Devotion = Devotion.ROSARY,
        set: RosaryMysteryType? = RosaryMysteryType.JOYFUL,
        lang: AppLanguage = AppLanguage.ES,
        voice: RecitationVoiceMode = RecitationVoiceMode.DUAL_DIALOGUE
    ) = NarrationTarget(devotion, set, step, lang, voice)

    // ------------------------------------------------------------ state machine

    @Test fun playStartsSpeakingAndPauseStopsIt() {
        val t = target(3)
        val play = reduceNarration(NarrationState.Idle, NarrationEvent.Toggle(t))
        assertEquals(NarrationState.Speaking(t), play.state)
        assertEquals(NarrationCommand.Speak(t), play.command)
        val pause = reduceNarration(play.state, NarrationEvent.Toggle(t))
        assertEquals(NarrationState.Idle, pause.state)
        assertEquals(NarrationCommand.Stop, pause.command)
    }

    @Test fun idleIgnoresEverythingExceptPlay() {
        for (e in listOf(NarrationEvent.TargetChanged(target(1)), NarrationEvent.EngineReady, NarrationEvent.ScreenLeft)) {
            val r = reduceNarration(NarrationState.Idle, e)
            assertEquals(NarrationState.Idle, r.state)
            assertNull("no command in Idle for $e", r.command)
        }
    }

    @Test fun everyStepChangeSpeaksWhileTheModeIsOn() {
        var s: NarrationState = NarrationState.Speaking(target(0))
        for (step in 1..4) {
            val r = reduceNarration(s, NarrationEvent.TargetChanged(target(step)))
            assertEquals(NarrationCommand.Speak(target(step)), r.command)
            s = r.state
        }
        assertEquals(NarrationState.Speaking(target(4)), s)
    }

    @Test fun narrationSurvivesChangingMysterySetDevotionLanguageAndVoice() {
        val base = target(5)
        val changes = listOf(
            base.copy(mysterySet = RosaryMysteryType.GLORIOUS, step = 0),
            base.copy(devotion = Devotion.MERCY, mysterySet = null, step = 0),
            base.copy(lang = AppLanguage.EN),
            base.copy(voice = RecitationVoiceMode.MALE)
        )
        for (next in changes) {
            val r = reduceNarration(NarrationState.Speaking(base), NarrationEvent.TargetChanged(next))
            assertEquals(NarrationState.Speaking(next), r.state)
            assertEquals(NarrationCommand.Speak(next), r.command)
        }
    }

    @Test fun anEqualTargetIsNotSpokenTwice() {
        val r = reduceNarration(NarrationState.Speaking(target(2)), NarrationEvent.TargetChanged(target(2)))
        assertNull(r.command)
    }

    @Test fun leavingTheScreenStopsTheVoiceAndGoesIdle() {
        val r = reduceNarration(NarrationState.Speaking(target(2)), NarrationEvent.ScreenLeft)
        assertEquals(NarrationState.Idle, r.state)
        assertEquals(NarrationCommand.Stop, r.command)
    }

    @Test fun engineReadyRespeaksTheCurrentPrayer() {
        // Play pressed before the speech engine had started: nothing was heard.
        val r = reduceNarration(NarrationState.Speaking(target(2)), NarrationEvent.EngineReady)
        assertEquals(NarrationCommand.Speak(target(2)), r.command)
    }

    private class Recorder : NarrationSink {
        val log = mutableListOf<String>()
        override fun speak(target: NarrationTarget) { log += "speak ${target.step}" }
        override fun stop() { log += "stop" }
    }

    @Test fun controllerRunsCommandsInOrder() {
        val rec = Recorder()
        val c = NarrationController(rec)
        assertFalse(c.isSpeaking)
        c.dispatch(NarrationEvent.TargetChanged(target(0)))   // idle: ignored
        c.dispatch(NarrationEvent.Toggle(target(0)))
        assertTrue(c.isSpeaking)
        c.dispatch(NarrationEvent.TargetChanged(target(1)))
        c.dispatch(NarrationEvent.TargetChanged(target(1)))   // duplicate
        c.dispatch(NarrationEvent.ScreenLeft)
        c.dispatch(NarrationEvent.ScreenLeft)                 // already idle
        assertFalse(c.isSpeaking)
        c.dispatch(NarrationEvent.TargetChanged(target(2)))   // idle after leaving: silent
        assertEquals(listOf("speak 0", "speak 1", "stop"), rec.log)
    }

    // ----------------------------------------------------------- speech tracker

    @Test fun singlePartPlaysThenGoesSilent() {
        val t = SpeechTracker()
        t.begin("Hail Mary")
        t.register("g1:single:0", "Hail Mary")
        assertTrue(t.playing)
        assertEquals("Hail Mary", t.speaking)
        t.onStart("g1:single:0")
        t.onDone("g1:single:0")
        assertFalse(t.playing)
        assertNull(t.speaking)
    }

    @Test fun dialogueHighlightMovesFromLeaderToResponseAndEndsOnLastChunk() {
        val t = SpeechTracker()
        t.begin("leader")
        t.register("g1:leader:0", "leader")
        t.register("g1:leader:1", "leader")
        t.register("g1:response:0", "response")
        t.onStart("g1:leader:0")
        assertEquals("leader", t.speaking)
        t.onDone("g1:leader:0")
        t.onDone("g1:leader:1")
        // Still the leader's text and still playing during the breath.
        assertTrue(t.playing)
        assertEquals("leader", t.speaking)
        t.onStart("g1:response:0")
        assertEquals("response", t.speaking)
        t.onDone("g1:response:0")
        assertFalse(t.playing)
        assertNull(t.speaking)
    }

    @Test fun staleCallbacksFromAStoppedRecitationAreIgnored() {
        val t = SpeechTracker()
        t.begin("old")
        t.register("g1:single:0", "old")
        t.stop()
        t.begin("new")
        t.register("g2:single:0", "new")
        // Late callbacks of the first recitation arrive after the second began.
        t.onStart("g1:single:0")
        t.onDone("g1:single:0")
        t.onError("g1:single:0")
        assertTrue(t.playing)
        assertEquals("new", t.speaking)
        t.onDone("g2:single:0")
        assertFalse(t.playing)
    }

    @Test fun stopClearsEverythingAndLateDoneCannotRevive() {
        val t = SpeechTracker()
        t.begin("x")
        t.register("g1:single:0", "x")
        t.stop()
        assertFalse(t.playing)
        assertNull(t.speaking)
        t.onStart("g1:single:0")
        assertFalse(t.playing)
        assertNull(t.speaking)
    }

    @Test fun anErrorInAMiddleChunkDoesNotCutTheRestOfTheQueue() {
        val t = SpeechTracker()
        t.begin("leader")
        t.register("g1:leader:0", "leader")
        t.register("g1:response:0", "response")
        t.onError("g1:leader:0")
        assertTrue(t.playing)
        t.onStart("g1:response:0")
        assertEquals("response", t.speaking)
        t.onError("g1:response:0")   // the last part failing ends it
        assertFalse(t.playing)
    }

    @Test fun refusedRequestForTheOnlyPartEndsPlaying() {
        val t = SpeechTracker()
        t.begin("x")
        t.register("g1:single:0", "x")
        t.onError("g1:single:0")
        assertFalse(t.playing)
        assertNull(t.speaking)
    }

    @Test fun blankInitialTextMeansNothingHighlighted() {
        val t = SpeechTracker()
        t.begin("  ")
        assertTrue(t.playing)
        assertNull(t.speaking)
    }

    // -------------------------------------------------------------- bead reel

    @Test fun dragEndSelectsTheLandedBeadOrSnapsBack() {
        assertEquals(ReelGesture.DragEnd.Select(5), ReelGesture.onDragEnd(5.4f, 3, 60))
        assertEquals(ReelGesture.DragEnd.Select(0), ReelGesture.onDragEnd(-2f, 3, 60))
        assertEquals(ReelGesture.DragEnd.Select(60), ReelGesture.onDragEnd(99f, 3, 60))
        assertEquals(ReelGesture.DragEnd.SnapBack, ReelGesture.onDragEnd(3.3f, 3, 60))
    }

    @Test fun tapHitsTheNearestBeadWithinSlopOnly() {
        // Beads 30dp apart; strand centred on bead 1 (30dp); 2x density, 800px wide.
        val along = floatArrayOf(0f, 30f, 60f)
        val centreX = 400f
        assertEquals(1, ReelGesture.hit(along, 30f, 2f, 800f, centreX + 5f))
        assertEquals(2, ReelGesture.hit(along, 30f, 2f, 800f, centreX + 62f))
        // Between beads (30px from each) is still a hit on the nearer one.
        assertEquals(1, ReelGesture.hit(along, 30f, 2f, 800f, centreX + 29f))
        // Far from every bead (beyond the 22dp = 44px slop) misses.
        assertNull(ReelGesture.hit(along, 30f, 2f, 800f, centreX - 200f))
        assertNull(ReelGesture.hit(floatArrayOf(), 0f, 2f, 800f, 10f))
    }
}
