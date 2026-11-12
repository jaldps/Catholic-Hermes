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
class ReminderStore(
    private val context: Context,
    private val dataStore: DataStore<Preferences> = context.reminderDataStore,
) {
    private object Keys {
        val NOVENA_NAME = stringPreferencesKey("novena_name")
        val NOVENA_START = longPreferencesKey("novena_start_epoch_day")
    }

    private fun enabledKey(id: String) = booleanPreferencesKey("rem_enabled_$id")
    private fun timeKey(id: String) = stringPreferencesKey("rem_time_$id")
    private fun calendarEventKey(id: String) = longPreferencesKey("rem_gcal_event_$id")

    /** Full preference map, defaulting to the catalog defaults. */
    // Any DataStore read failure (corrupt preferences among them) falls back
    // to defaults instead of rethrowing into the screen's collector, which
    // would crash the reminders screen instead of opening it.
    val reminders: Flow<Map<String, ReminderPref>> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs ->
            ReminderCatalog.DEFAULTS.associate { r ->
                // Midday Angelus (12:00) and Divine Mercy Chaplet (15:00) are
                // scheduled by default; the evening Angelus (18:00) stays off
                // until the user opts in.
                val enabled = prefs[enabledKey(r.id)] ?: (r.id in DEFAULT_ON)
                val time = prefs[timeKey(r.id)]
                val parsed = time?.split(":")?.mapNotNull { it.toIntOrNull() }
                val hour = parsed?.getOrNull(0) ?: r.defaultHour
                val minute = parsed?.getOrNull(1) ?: r.defaultMinute
                r.id to ReminderPref(enabled, hour.coerceIn(0, 23), minute.coerceIn(0, 59))
            }
        }

    suspend fun setEnabled(id: String, enabled: Boolean) {
        dataStore.edit { it[enabledKey(id)] = enabled }
    }

    suspend fun setTime(id: String, hour: Int, minute: Int) {
        dataStore.edit { it[timeKey(id)] = "%02d:%02d".format(hour, minute) }
    }

    /**
     * Google Calendar event written for each reminder (`reminder id -> event
     * id`). Kept so a second "Add to Google Calendar" updates that event
     * instead of inserting a duplicate daily event.
     */
    val calendarEvents: Flow<Map<String, Long>> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs ->
            ReminderCatalog.DEFAULTS.mapNotNull { r ->
                prefs[calendarEventKey(r.id)]?.let { r.id to it }
            }.toMap()
        }

    suspend fun setCalendarEvent(id: String, eventId: Long?) {
        dataStore.edit {
            if (eventId == null) it.remove(calendarEventKey(id)) else it[calendarEventKey(id)] = eventId
        }
    }

    /** Reminders scheduled by default (evening Angelus excluded). */
    private companion object DEFAULT_ON_IDS {
        val DEFAULT_ON: Set<String> = setOf("angelus_midday", "coronilla")
    }

    val novena: Flow<NovenaState?> = dataStore.data
        .catch { emit(emptyPreferences()) }
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
