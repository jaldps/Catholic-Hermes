package com.example

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.core.app.ApplicationProvider
import com.example.data.models.AppLanguage
import com.example.data.verse.DailyVerseFetcher
import com.example.data.verse.DailyVerseStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class DailyVerseEditionTest {
    @Test fun spanishNeverRequestsLatinOrReusesAnOldApiCache() = runBlocking {
        val context: Context = ApplicationProvider.getApplicationContext()
        val date = LocalDate.of(2026, 11, 12)
        val oldCache = preferencesOf(stringPreferencesKey("verse_text_2026-11-12_es") to "In principio erat Verbum")
        val store = object : DataStore<Preferences> {
            private val state = MutableStateFlow(oldCache)
            override val data: Flow<Preferences> = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                transform(state.value).also { state.value = it }
        }
        assertNull(DailyVerseFetcher().urlFor("joh 1:1", AppLanguage.ES))
        assertNull(DailyVerseStore(context, store).cachedText(date, AppLanguage.ES))
    }
}
