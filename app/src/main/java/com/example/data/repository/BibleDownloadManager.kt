package com.example.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

class BibleDownloadManager(private val context: Context) {

    data class VersionSpec(val id: String, val name: String, val language: String, val bookCount: Int, val path: String)

    val specs = listOf(
        VersionSpec("drc", "Douay-Rheims-Challoner (English)", "en", 73, "English/Douay-Rheims-Challoner"),
        VersionSpec("rv1909", "Reina-Valera 1909 (Spanish)", "es", 66, "Spanish/Reina-Valera-1909"),
        VersionSpec("almeida", "Almeida (Portuguese)", "pt", 66, "Portuguese/Almeida-Portuguese")
    )

    private val slugs = listOf(
        "Gen","Exod","Lev","Num","Deut","Josh","Judg","Ruth","1Sam","2Sam","1Kgs","2Kgs","1Chr","2Chr","Ezra","Neh","Tob","Jdt","Esth","Job","Ps","Prov","Eccl","Song","Wis","Sir","Isa","Jer","Lam","Bar","Ezek","Dan","Hos","Joel","Amos","Obad","Jonah","Mic","Nah","Hab","Zeph","Hag","Zech","Mal","Matt","Mark","Luke","John","Acts","Rom","1Cor","2Cor","Gal","Eph","Phil","Col","1Thess","2Thess","1Tim","2Tim","Titus","Phlm","Heb","Jas","1Pet","2Pet","1John","2John","3John","Jude","Rev"
    )

    fun versionDir(versionId: String): File = File(context.filesDir, "bibles/$versionId").also { it.mkdirs() }

    fun isDownloaded(versionId: String): Boolean {
        val dir = versionDir(versionId)
        return dir.exists() && (dir.listFiles()?.size ?: 0) >= 10
    }

    fun downloadUrl(spec: VersionSpec, index: Int): String =
        "https://raw.githubusercontent.com/FordhamRamFan/public-domain-bible-translations/main/${spec.path}/${String.format("%02d", index)}-${slugs[index - 1]}.json"

    suspend fun download(versionId: String, onProgress: (Float, String) -> Unit): Boolean = withContext(Dispatchers.IO) {
        val spec = specs.firstOrNull { it.id == versionId } ?: return@withContext false
        val dir = versionDir(versionId)
        dir.mkdirs()
        try {
            for (i in 1..spec.bookCount) {
                val file = File(dir, "$i.json")
                if (!file.exists()) {
                    val conn = URL(downloadUrl(spec, i)).openConnection() as HttpURLConnection
                    conn.connectTimeout = 15000
                    conn.readTimeout = 30000
                    conn.inputStream.use { input ->
                        file.outputStream().use { output -> input.copyTo(output) }
                    }
                    conn.disconnect()
                }
                onProgress(i.toFloat() / spec.bookCount, "Book $i of ${spec.bookCount}")
            }
            true
        } catch (e: Exception) {
            onProgress(0f, "Error: ${e.message}")
            false
        }
    }
}
