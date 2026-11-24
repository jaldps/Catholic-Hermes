package com.example

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.LogLevel
import com.example.data.diagnostics.SafeLogFormat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Instant

/** A crash entry names the classes and frames needed to find the failing line, and never a message. */
class CrashRecorderFormatTest {
    @Test fun crashEntryKeepsCausesAndFramesButNoMessages() {
        val root = IllegalStateException("secret prayer text")
        val top = RuntimeException("personal note", root)
        val entry = SafeLogFormat.render(LogEvent.CRASH, top, Instant.EPOCH, LogLevel.ERROR, frames = 40)
        assertTrue(entry.contains("[Crash]"))
        assertTrue(entry.contains("caused by java.lang.IllegalStateException"))
        assertTrue(entry.contains("at com.example.CrashRecorderFormatTest.crashEntryKeepsCausesAndFramesButNoMessages"))
        assertFalse(entry.contains("secret"))
        assertFalse(entry.contains("personal"))
    }
}
