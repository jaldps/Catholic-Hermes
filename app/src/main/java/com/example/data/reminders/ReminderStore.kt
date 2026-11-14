@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package com.example.data.reminders

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.LocalDate

private val Context.reminderDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "devotion_reminders"
)

/**
 * Persists reminder toggles/times and the active novena in DataStore.
 * No database, no account, no network — the data never leaves the device.
 */
data class CalendarEventSnapshot(val available: Boolean, val events: Map<String, Long>)

data class ReminderPreferenceSnapshot(val available: Boolean, val reminders: Map<String, ReminderPref>)

class ReminderStore(
    private val context: Context,
    private val dataStore: DataStore<Preferences> = context.reminderDataStore,
) {
    private object Keys {
        val READ_FAILED = booleanPreferencesKey("internal_read_failed")
        val NOVENA_NAME = stringPreferencesKey("novena_name")
        val NOVENA_START = longPreferencesKey("novena_start_epoch_day")
    }

    private fun enabledKey(id: String) = booleanPreferencesKey("rem_enabled_$id")
    private fun timeKey(id: String) = stringPreferencesKey("rem_time_$id")
    private fun calendarEventKey(id: String) = longPreferencesKey("rem_gcal_event_$id")

    private val _readFailure = MutableStateFlow(false)
    val readFailure = _readFailure.asStateFlow()
    private val refresh = MutableStateFlow(0)
    fun retryRead() { refresh.update { it + 1 } }

    private val safeData = refresh.flatMapLatest {
        dataStore.data.onEach { _readFailure.value = false }.catch { cause ->
            if (cause !is IOException) throw cause
            _readFailure.value = true
            emit(emptyPreferences().toMutablePreferences().apply { this[Keys.READ_FAILED] = true })
        }
    }

    /** A failed read is unavailable, never an instruction to re-enable default reminders. */
    val reminderSnapshot: Flow<ReminderPreferenceSnapshot> = safeData
        .map { prefs ->
            val available = prefs[Keys.READ_FAILED] != true
            val values = ReminderCatalog.DEFAULTS.associate { r ->
                // Midday Angelus (12:00) and Divine Mercy Chaplet (15:00) are
                // scheduled by default; the evening Angelus (18:00) stays off
                // until the user opts in.
                val enabled = if (prefs[Keys.READ_FAILED] == true) false else prefs[enabledKey(r.id)] ?: (r.id in DEFAULT_ON)
                val time = prefs[timeKey(r.id)]
                val parsed = time?.split(":")?.takeIf { it.size == 2 }?.map { it.toIntOrNull() }?.takeIf { it.all { value -> value != null } }
                val hour = parsed?.getOrNull(0) ?: r.defaultHour
                val minute = parsed?.getOrNull(1) ?: r.defaultMinute
                r.id to ReminderPref(enabled, hour.coerceIn(0, 23), minute.coerceIn(0, 59))
            }
            ReminderPreferenceSnapshot(available,values)
        }
    val reminders: Flow<Map<String, ReminderPref>> = reminderSnapshot.map { it.reminders }

    suspend fun setEnabled(id: String, enabled: Boolean) {
        dataStore.edit { it[enabledKey(id)] = enabled }
    }

    suspend fun setTime(id: String, hour: Int, minute: Int) {
        require(hour in 0..23 && minute in 0..59)
        dataStore.edit { it[timeKey(id)] = "%02d:%02d".format(java.util.Locale.ROOT, hour, minute) }
    }

    /**
     * Google Calendar event written for each reminder (`reminder id -> event
     * id`). Kept so a second "Add to Google Calendar" updates that event
     * instead of inserting a duplicate daily event.
     */
    val calendarEventSnapshot: Flow<CalendarEventSnapshot> = safeData
        .map { prefs ->
            val events = ReminderCatalog.DEFAULTS.mapNotNull { r ->
                prefs[calendarEventKey(r.id)]?.let { r.id to it }
            }.toMap()
            CalendarEventSnapshot(prefs[Keys.READ_FAILED] != true,events)
        }
    val calendarEvents: Flow<Map<String,Long>> = calendarEventSnapshot.map {it.events}

    suspend fun setCalendarEvent(id: String, eventId: Long?) {
        dataStore.edit {
            if (eventId == null) it.remove(calendarEventKey(id)) else it[calendarEventKey(id)] = eventId
        }
    }

    /** Reminders scheduled by default (evening Angelus excluded). */
    private companion object DEFAULT_ON_IDS {
        val DEFAULT_ON: Set<String> = setOf("angelus_midday", "coronilla")
    }

    val novena: Flow<NovenaState?> = safeData
        .map { prefs ->
            val name = prefs[Keys.NOVENA_NAME]?.trim().orEmpty()
            val start = prefs[Keys.NOVENA_START]
            if (name.isNotEmpty() && start != null) NovenaState(name, start) else null
        }

    /** Starts (or restarts) a 9-day novena today. */
    suspend fun startNovena(name: String) {
        dataStore.edit {
            it[Keys.NOVENA_NAME] = name.trim()
            it[Keys.NOVENA_START] = LocalDate.now().toEpochDay()
        }
    }

    suspend fun clearNovena() {
        dataStore.edit {
            it.remove(Keys.NOVENA_NAME)
            it.remove(Keys.NOVENA_START)
        }
    }
}
