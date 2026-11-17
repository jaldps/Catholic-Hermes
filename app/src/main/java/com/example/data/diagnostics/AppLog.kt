package com.example.data.diagnostics

import android.content.Context
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/** Only fixed events are accepted: callers cannot accidentally log prayer text, a URL or a password. */
enum class LogEvent(val tag: String, val description: String) {
    BIBLE_EDITIONS("Bible", "Edition catalog could not be read"),
    BIBLE_BOOK_LIST("Bible", "Edition book list could not be read"),
    BIBLE_BOOK("Bible", "Edition book could not be read"),
    BIBLE_BUNDLED("Bible", "Bundled Bible could not be read"),
    PRAYER_CATALOG("Assets", "Prayer catalog could not be read"),
    QUESTION_CATALOG("Assets", "Question catalog could not be read"),
    SAINT_CATALOG("Assets", "Saint catalog could not be read"),
    ORDER_CATALOG("Assets", "Order catalog could not be read"),
    DEVOTION_CATALOG("Assets", "Devotion catalog could not be read"),
    COURSE_CATALOG("Assets", "Study course catalog could not be read"),
    NOVENA_PROVENANCE("Assets", "Novena provenance could not be read"),
    NOVENA_CATALOG("Assets", "Novena catalog could not be loaded"),
    HTTP_REQUEST("Network", "HTTP request failed"),
    HTTP_BODY("Network", "HTTP response could not be read"),
    NEWS_FEED("Network", "News feed could not be loaded"),
    READINGS_FEED("Network", "Readings feed could not be loaded"),
    READINGS_PARSE("Network", "Readings feed could not be parsed"),
    READINGS_CACHE_WRITE("Cache", "Readings cache could not be written"),
    READINGS_CACHE_READ("Cache", "Readings cache could not be read"),
    VERSE_FETCH("Network", "Daily verse could not be loaded"),
    VERSE_PARSE("Network", "Daily verse response could not be parsed"),
    PERSONAL_WRITE("Database", "Personal data write failed"),
    PREFERENCES_READ("Preferences", "Stored preferences could not be read"),
    BACKUP_RECOVERY("Backup", "Pending restore could not be recovered"),
    BACKUP_WRITE("Backup", "Restore could not be completed"),
    BACKUP_ROLLBACK("Backup", "Restore preferences could not be rolled back"),
    REMINDER_EXACT("Reminders", "Exact alarm permission unavailable; using fallback"),
    REMINDER_SCHEDULE("Reminders", "Reminders could not be scheduled"),
    TTS_RECEIVER("Audio", "Audio interruption receiver could not be registered"),
    TTS_SERVICE("Audio", "Narration service operation failed"),
    TTS_TONE("Audio", "Narration tone could not be initialized"),
    TTS_PREFERENCES("Audio", "Narration preferences could not be read"),
    TTS_LANGUAGE("Audio", "Narration language could not be applied"),
    TTS_ENGINE("Audio", "Speech engine initialization failed"),
    TTS_PLAYBACK("Audio", "Speech engine refused or failed an utterance"),
    REPORT_SHARE("Diagnostics", "Error report could not be shared")
}

enum class LogLevel { WARN, ERROR }

internal object SafeLogFormat {
    private val identifier=Regex("[A-Za-z0-9_.$<>-]{1,180}")
    private val source=Regex("[A-Za-z0-9_$-]{1,100}\\.(kt|java)")
    private fun symbol(value:String)=value.takeIf(identifier::matches) ?: "redacted"

    /** Never uses message, localizedMessage, toString, cause or suppressed exceptions. */
    fun render(event:LogEvent, error:Throwable?, time:Instant, level:LogLevel):String = buildString {
        val prefix="$time ${level.name} [${event.tag}] "
        append(prefix).append(event.description)
        error?.let {append("; ").append(symbol(it.javaClass.name))}
        append('\n')
        error?.stackTrace?.take(4)?.forEach { frame ->
            val file=frame.fileName?.takeIf(source::matches) ?: "source"
            append(prefix).append("at ").append(symbol(frame.className)).append('.')
                .append(symbol(frame.methodName)).append('(').append(file).append(':')
                .append(frame.lineNumber.coerceAtLeast(0)).append(")\n")
        }
    }
}

/** Two private files, each bounded to 256 KiB. The same lock protects appends and report snapshots. */
internal class RollingAppLog(private val dir:File, private val limit:Int=256*1024) {
    val current get()=File(dir,"current.log")
    val previous get()=File(dir,"previous.log")

    @Synchronized fun append(entry:String) {
        val bytes=entry.toByteArray(Charsets.UTF_8)
        require(bytes.size<=limit) {"Diagnostic entry exceeds file limit"}
        if(!dir.isDirectory&&!dir.mkdirs())throw IOException("Diagnostic directory unavailable")
        if(current.length()+bytes.size>limit) {
            if(previous.exists()&&!previous.delete())throw IOException("Diagnostic rotation failed")
            if(current.exists()&&!current.renameTo(previous))throw IOException("Diagnostic rotation failed")
        }
        FileOutputStream(current,true).use {it.write(bytes)}
    }

    @Synchronized fun snapshot(reports:File):File {
        if(!reports.isDirectory&&!reports.mkdirs())throw IOException("Report directory unavailable")
        val file=File(reports,"error-report-${UUID.randomUUID()}.txt")
        try {
            file.outputStream().use { output ->
                output.write("Catholic Hermes — local technical error report\nNo exception messages, URLs, personal text or backup content.\n\n".toByteArray(Charsets.UTF_8))
                listOf(previous,current).filter {it.isFile}.forEach {log -> log.inputStream().use {it.copyTo(output)}}
            }
        } catch(e:Exception) {file.delete();throw e}
        // Retain a few stable snapshots for the recipient; never expose the live log files.
        reports.listFiles()?.filter {it.name.startsWith("error-report-")&&it.extension=="txt"&&it!=file}
            ?.sortedByDescending {it.lastModified()}?.drop(2)?.forEach {it.delete()}
        return file
    }
}

object AppLog {
    @Volatile private var destination:RollingAppLog?=null
    @Volatile private var directory:File?=null
    private val worker=ThreadPoolExecutor(1,1,0,TimeUnit.MILLISECONDS,ArrayBlockingQueue<Runnable>(128),
        {work -> Thread(work,"hermes-diagnostics").apply {isDaemon=true}},ThreadPoolExecutor.AbortPolicy())

    @Synchronized fun initialize(context:Context) {
        try {
            val dir=File(context.applicationContext.filesDir,"diagnostics")
            if(directory!=dir) {directory=dir;destination=RollingAppLog(dir)}
        } catch(_:Exception) {destination=null;directory=null}
    }

    /** Best effort, off the caller thread; a failed log must never change the operation's behavior. */
    fun failure(event:LogEvent, error:Throwable?=null, level:LogLevel=LogLevel.ERROR) {
        if(error is CancellationException)return
        val log=destination ?: return
        try {
            val entry=SafeLogFormat.render(event,error,Instant.now(),level)
            worker.execute {try {log.append(entry)} catch(_:Exception) { /* No recursion or logcat payloads. */ }}
        } catch(_:Exception) { /* Queue full or diagnostic unavailable: leave app behavior unchanged. */ }
    }

    /** Queued behind earlier events so the shared snapshot contains all accepted writes. */
    suspend fun createReport(context:Context):File=withContext(Dispatchers.IO) {
        initialize(context)
        val log=checkNotNull(destination)
        val reports=File(context.applicationContext.cacheDir,"error-reports")
        val future=worker.submit(Callable {log.snapshot(reports)})
        try {future.get()} catch(e:ExecutionException) {throw(e.cause ?: e)}
    }
}
