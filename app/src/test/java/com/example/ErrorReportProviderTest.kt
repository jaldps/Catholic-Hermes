package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.test.core.app.ApplicationProvider
import com.example.data.diagnostics.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(sdk=[34])
class ErrorReportProviderTest {
    @Test fun safePublicHelperExcludesPersonalExceptionDataFromTheSharedSnapshot() = runBlocking {
        val context=ApplicationProvider.getApplicationContext<Context>()
        AppLog.initialize(context)
        val secret="personal@example.com contraseña: secreta; diario privado; copia.chbackup"
        AppLog.failure(LogEvent.PERSONAL_WRITE,IllegalStateException(secret))
        val report=AppLog.createReport(context)
        val content=report.readText()
        assertFalse(content.contains(secret));assertFalse(content.contains("personal@example.com"))
        assertTrue(content.contains("java.lang.IllegalStateException"))
    }
    @Test fun sharedReportUsesAReadableContentUriWithReadPermissionOnly()=runBlocking {
        val context=ApplicationProvider.getApplicationContext<Context>()
        AppLog.initialize(context)
        AppLog.failure(LogEvent.BIBLE_BOOK,java.io.IOException("password must not appear"))
        val file=AppLog.createReport(context)
        val intent=ErrorReportShare.intent(context,file)
        val uri=intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)!!
        assertEquals(Intent.ACTION_SEND,intent.action);assertEquals("text/plain",intent.type)
        assertEquals("content",uri.scheme);assertEquals("${context.packageName}.errorreports",uri.authority)
        assertEquals(uri,intent.clipData!!.getItemAt(0).uri)
        assertTrue(intent.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        assertEquals(0,intent.flags and Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        val text=context.contentResolver.openInputStream(uri)!!.bufferedReader().use {it.readText()}
        assertTrue(text.contains("Edition book could not be read"));assertFalse(text.contains("password must not appear"))
    }
    @Test fun providerCannotExposeOtherPersonalFiles() {
        val context=ApplicationProvider.getApplicationContext<Context>()
        val file=File(context.filesDir,"journal-private.txt").apply {writeText("Private journal")}
        try {FileProvider.getUriForFile(context,"${context.packageName}.errorreports",file);fail("Provider must be restricted to report snapshots")}
        catch(_:IllegalArgumentException) { }
        assertFalse(context.packageManager.resolveContentProvider("${context.packageName}.errorreports",0)!!.exported)
    }
}
