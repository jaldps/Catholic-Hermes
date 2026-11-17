package com.example.data.notices

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.data.reminders.ReminderStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/** One notice type's switch and time. */
data class NoticePref(val enabled: Boolean, val hour: Int, val minute: Int)

/** [available] false means the stored choices could not be read: nothing is treated as switched on. */
data class NoticeSettings(
    val available: Boolean,
    val prefs: Map<NoticeType, NoticePref>,
    val obligationDays: Set<String>
) {
    fun pref(type: NoticeType): NoticePref = prefs[type] ?: NoticePref(false, type.defaultHour, type.defaultMinute)
    val anyEnabled: Boolean get() = prefs.values.any { it.enabled }
}

/**
 * Notice choices live in the reminders DataStore, next to the daily reminders, so the backup's «reminders» group
 * carries them with no extra wiring. Everything defaults to OFF.
 */
class NoticeStore(
    context: Context,
    private val dataStore: DataStore<Preferences> = ReminderStore(context.applicationContext).backupDataStore
) {
    companion object {
        fun enabledKey(type: NoticeType) = booleanPreferencesKey("notice_enabled_${type.id}")
        fun timeKey(type: NoticeType) = stringPreferencesKey("notice_time_${type.id}")
        val OBLIGATION_DAYS = stringSetPreferencesKey("notice_obligation_days")

        fun parse(prefs: Preferences, available: Boolean = true): NoticeSettings = NoticeSettings(
            available = available,
            prefs = NoticeType.entries.associateWith { type ->
                val parsed = prefs[timeKey(type)]?.split(":")?.takeIf { it.size == 2 }?.map { it.toIntOrNull() }
                    ?.takeIf { it.all { v -> v != null } }
                NoticePref(
                    enabled = available && prefs[enabledKey(type)] == true,
                    hour = (parsed?.getOrNull(0) ?: type.defaultHour).coerceIn(0, 23),
                    minute = (parsed?.getOrNull(1) ?: type.defaultMinute).coerceIn(0, 59)
                )
            },
            obligationDays = prefs[OBLIGATION_DAYS].orEmpty().filter { it in ObligationDays.KEYS }.toSet()
        )
    }

    val settings: Flow<NoticeSettings> = dataStore.data
        .map { parse(it) }
        .catch { cause ->
            if (cause !is IOException) throw cause
            emit(NoticeSettings(false, emptyMap(), emptySet()))
        }

    suspend fun setEnabled(type: NoticeType, enabled: Boolean) {
        dataStore.edit { it[enabledKey(type)] = enabled }
    }

    suspend fun setTime(type: NoticeType, hour: Int, minute: Int) {
        require(hour in 0..23 && minute in 0..59)
        dataStore.edit { it[timeKey(type)] = "%02d:%02d".format(java.util.Locale.ROOT, hour, minute) }
    }

    suspend fun setObligationDays(days: Set<String>) {
        dataStore.edit { it[OBLIGATION_DAYS] = days.filter { d -> d in ObligationDays.KEYS }.toSet() }
    }
}
