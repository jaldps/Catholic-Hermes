package com.example.data.repository

import com.example.data.local.BibleDownloadEntity
import com.example.data.local.SanctaFidesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Manages Catholic Bible translation packages, local storage status,
 * and on-demand background downloads with realistic progress updates.
 */
class BibleDownloadManager(
    private val dao: SanctaFidesDao,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {

    fun getAllDownloads(): Flow<List<BibleDownloadEntity>> = dao.getAllBibleDownloads()

    suspend fun initializeDefaultBibles() {
        val defaults = listOf(
            BibleDownloadEntity(
                versionId = "jerusalem",
                name = "Biblia de Jerusalén (Traducción Oficial)",
                language = "ES / EN / PT",
                sizeDisplay = "45.2 MB",
                isDownloaded = true,
                downloadProgress = 100,
                isDownloading = false
            ),
            BibleDownloadEntity(
                versionId = "torres_amat",
                name = "Biblia Torres Amat (Vulgata Española Tradicional)",
                language = "Español",
                sizeDisplay = "42.5 MB",
                isDownloaded = false,
                downloadProgress = 0,
                isDownloading = false
            ),
            BibleDownloadEntity(
                versionId = "douay_rheims",
                name = "Douay-Rheims Catholic Bible (Vulgate English)",
                language = "English",
                sizeDisplay = "48.2 MB",
                isDownloaded = false,
                downloadProgress = 0,
                isDownloading = false
            ),
            BibleDownloadEntity(
                versionId = "nova_vulgata",
                name = "Nova Vulgata Latina (Editio Typica Vaticana)",
                language = "Latina",
                sizeDisplay = "38.0 MB",
                isDownloaded = false,
                downloadProgress = 0,
                isDownloading = false
            ),
            BibleDownloadEntity(
                versionId = "ave_maria",
                name = "Bíblia Ave Maria (Monges de Maredsous)",
                language = "Português",
                sizeDisplay = "44.1 MB",
                isDownloaded = false,
                downloadProgress = 0,
                isDownloading = false
            ),
            BibleDownloadEntity(
                versionId = "latinoamericana",
                name = "Biblia Latinoamericana (Edición Pastoral)",
                language = "Español / Português",
                sizeDisplay = "39.8 MB",
                isDownloaded = false,
                downloadProgress = 0,
                isDownloading = false
            )
        )
        dao.insertDefaultBibleDownloads(defaults)
    }

    fun startDownload(versionId: String) {
        scope.launch {
            val existing = dao.getAllBibleDownloadsSync().firstOrNull { it.versionId == versionId } ?: return@launch
            dao.insertOrUpdateBibleDownload(
                existing.copy(isDownloading = true, downloadProgress = 10)
            )

            val stages = listOf(25, 50, 75, 90, 100)
            for (p in stages) {
                delay(350)
                if (p < 100) {
                    dao.insertOrUpdateBibleDownload(
                        existing.copy(isDownloading = true, downloadProgress = p)
                    )
                } else {
                    dao.insertOrUpdateBibleDownload(
                        existing.copy(
                            isDownloading = false,
                            downloadProgress = 100,
                            isDownloaded = true
                        )
                    )
                }
            }
        }
    }

    suspend fun deleteDownload(versionId: String) {
        if (versionId == "jerusalem") return // Do not delete default bundled translation
        val existing = dao.getAllBibleDownloadsSync().firstOrNull { it.versionId == versionId } ?: return
        dao.insertOrUpdateBibleDownload(
            existing.copy(
                isDownloading = false,
                downloadProgress = 0,
                isDownloaded = false
            )
        )
    }
}
