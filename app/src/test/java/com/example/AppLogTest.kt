package com.example

import com.example.data.diagnostics.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.time.Instant

class AppLogTest {
    @get:Rule val folder=TemporaryFolder()
    @Test fun rotatesBeforeTheLimitAndKeepsOnlyOnePreviousFile() {
        val dir=folder.newFolder("logs")
        val log=RollingAppLog(dir,100)
        val a="first "+"a".repeat(35)+"\n"
        val b="second "+"b".repeat(35)+"\n"
        val c="third "+"c".repeat(35)+"\n"
        log.append(a);log.append(b);assertFalse(log.previous.exists())
        log.append(c);assertEquals(a+b,log.previous.readText());assertEquals(c,log.current.readText())
        repeat(10){log.append(c)}
        assertTrue(log.current.length()<=100);assertTrue(log.previous.length()<=100)
        assertEquals(setOf("current.log","previous.log"),dir.list()!!.toSet())
    }
    @Test fun everyLineHasIsoTimeLevelTagAndOnlyFourStackFrames() {
        val error=java.io.IOException("not logged").apply {
            stackTrace=Array(8){StackTraceElement("com.example.Reader","read","Reader.kt",it+10)}
        }
        val entry=SafeLogFormat.render(LogEvent.BIBLE_BOOK,error,Instant.parse("2026-11-17T10:12:14Z"),LogLevel.WARN)
        val lines=entry.trimEnd().lines()
        assertEquals(5,lines.size)
        assertTrue(lines.all {it.startsWith("2026-11-17T10:12:14Z WARN [Bible] ")})
        assertTrue(lines[0].contains("java.io.IOException"));assertTrue(lines[1].endsWith("Reader.read(Reader.kt:10)"))
    }
    @Test fun safeHelperDoesNotSerializePersonalTextMessagesCausesOrInjectedStackNames() {
        val privateText="diario privado jose@example.com /private/backup.chbackup contraseña-123"
        val error=IllegalStateException(privateText).apply {
            initCause(IllegalArgumentException(privateText));addSuppressed(java.io.IOException(privateText))
            stackTrace=arrayOf(StackTraceElement(privateText,privateText,privateText,7))
        }
        val log=RollingAppLog(folder.newFolder("private-check"))
        log.append(SafeLogFormat.render(LogEvent.PERSONAL_WRITE,error,Instant.EPOCH,LogLevel.ERROR))
        val text=log.current.readText()
        listOf(privateText,"jose@example.com","contraseña-123","diario privado","backup.chbackup").forEach {assertFalse(text.contains(it))}
        assertTrue(text.contains("java.lang.IllegalStateException"));assertTrue(text.contains("redacted.redacted(source:7)"))
    }
    @Test fun reportIsAStableSnapshotOfBothFilesAndSnapshotCountIsBounded() {
        val log=RollingAppLog(folder.newFolder("logs"),100)
        log.append("a".repeat(70)+"\n");log.append("b".repeat(70)+"\n")
        val reports=folder.newFolder("reports")
        val report=log.snapshot(reports);val text=report.readText()
        assertTrue(text.contains("a".repeat(70)));assertTrue(text.contains("b".repeat(70)))
        log.append("later\n");assertEquals(text,report.readText())
        repeat(6){log.snapshot(reports)}
        assertEquals(3,reports.listFiles()!!.size)
    }
}
