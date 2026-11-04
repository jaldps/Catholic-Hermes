package com.example

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.FontChoice
import com.example.data.prefs.UserPreferencesStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class FontPreferencesTest {
    @get:Rule val folder = TemporaryFolder()

    @Test fun fontAndSizeSurviveReopeningAndAppearanceDoesNotOverwriteFont() = runBlocking {
        withTimeout(10000) {
            val context = ApplicationProvider.getApplicationContext<Context>()
            val file = folder.newFolder().resolve("settings.preferences_pb")
            val firstJob = SupervisorJob()
            val firstData = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + firstJob)) { file }
            val firstStore = UserPreferencesStore(context, firstData)
            assertEquals("IDENTITY_DEFAULT", firstStore.preferences.first().fontFamilyChoice)
            firstStore.setFontFamilyChoice("ROBOTO")
            firstStore.setFontSizeScale(1.3f)
            firstJob.cancelAndJoin()

            val secondJob = SupervisorJob()
            val secondData = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + secondJob)) { file }
            try {
                val reopened = UserPreferencesStore(context, secondData)
                assertEquals("ROBOTO", reopened.preferences.first().fontFamilyChoice)
                assertEquals(1.3f, reopened.preferences.first().fontSizeScale, 0f)
                // An appearance sheet opened earlier must not restore its stale font draft.
                reopened.saveAppearance("MATERIAL", "LITURGICAL_DYNAMIC", "DARK", "INTER", 1.3f, 1f, updateFontFamily = false)
                assertEquals("ROBOTO", reopened.preferences.first().fontFamilyChoice)
                reopened.setFontFamilyChoice("EB_GARAMOND")
                assertEquals("EB_GARAMOND", reopened.preferences.first().fontFamilyChoice)
            } finally { secondJob.cancelAndJoin() }
        }
    }

    @Test fun legacyStoredIdsRemainReadableAndNormalizeWhenSelected() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val job = SupervisorJob()
        val data = PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO + job)) {
            folder.newFolder().resolve("legacy.preferences_pb")
        }
        try {
            val store = UserPreferencesStore(context, data)
            data.edit { it[stringPreferencesKey("font_family_choice")] = "SERIF" }
            assertEquals(FontChoice.EB_GARAMOND, FontChoice.fromId(store.preferences.first().fontFamilyChoice))
            store.setFontFamilyChoice("SANS")
            assertEquals("INTER", store.preferences.first().fontFamilyChoice)
            store.setFontFamilyChoice("UNKNOWN_FUTURE_FONT")
            assertEquals("IDENTITY_DEFAULT", store.preferences.first().fontFamilyChoice)
        } finally { job.cancelAndJoin() }
    }
}
