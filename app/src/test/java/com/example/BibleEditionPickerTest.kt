package com.example

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.repository.BibleDownloadManager
import com.example.data.repository.BibleDownloadManifest
import com.example.ui.screens.OfflineBibleDownloadsView
import com.example.ui.theme.SanctaFidesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The edition picker groups by language and shows only editions the manifest lists. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class BibleEditionPickerTest {
    @get:Rule val compose = createComposeRule()
    private val context: Context = ApplicationProvider.getApplicationContext()

    private fun manifest(vararg ids: String) = BibleDownloadManifest("c", ids.associateWith {
        listOf(BibleDownloadManifest.Book("gen", "01-gen.json", "VPGN", "00", 1L, listOf(1)))
    })

    private fun show(manager: BibleDownloadManager, language: AppLanguage = AppLanguage.EN) = compose.setContent {
        SanctaFidesTheme { OfflineBibleDownloadsView(manager = manager, currentLanguage = language) }
    }

    @Test fun unlistedEditionsAndEmptyGroupsStayInvisible() {
        show(BibleDownloadManager(context, manifest("es.lbl", "la.nvbse")))
        compose.onNodeWithTag("bible_edition_group_es").assertExists()
        compose.onNodeWithTag("bible_edition_group_la").assertExists()
        compose.onNodeWithTag("bible_edition_group_en").assertDoesNotExist()
        compose.onNodeWithTag("bible_edition_group_pt").assertDoesNotExist()
        compose.onNodeWithTag("bible_download_es.lbl").assertExists()
        compose.onNodeWithTag("bible_download_la.nvbse").assertExists()
        compose.onNodeWithTag("bible_download_es.lsb").assertDoesNotExist()
    }

    @Test fun nothingIsOfferedWhileTheManifestListsNothing() {
        show(BibleDownloadManager(context, manifest()))
        for (lang in listOf("es", "en", "pt", "la")) compose.onNodeWithTag("bible_edition_group_$lang").assertDoesNotExist()
    }

    @Test fun latinIsSelectableFromAnyUiLanguage() {
        for (language in AppLanguage.entries) {
            val setup = BibleDownloadManager(context, manifest("la.nvbse"))
            compose.setContent { SanctaFidesTheme { OfflineBibleDownloadsView(manager = setup, currentLanguage = language) } }
            compose.onNodeWithTag("bible_edition_group_la").assertExists()
            compose.onNodeWithTag("bible_download_la.nvbse").assertExists()
            break
        }
    }
}
