package com.example.data.diagnostics

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

object ErrorReportShare {
    fun intent(context:Context,file:File):Intent {
        val uri=FileProvider.getUriForFile(context,"${context.packageName}.errorreports",file)
        return Intent(Intent.ACTION_SEND).apply {
            type="text/plain"
            putExtra(Intent.EXTRA_STREAM,uri)
            clipData=ClipData.newRawUri("Catholic Hermes error report",uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }
}
