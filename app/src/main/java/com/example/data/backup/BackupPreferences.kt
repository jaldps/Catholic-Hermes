package com.example.data.backup

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.example.data.prefs.UserPreferencesStore
import com.example.data.reminders.ReminderStore
import com.example.data.temptation.TemptationStore
import kotlinx.coroutines.flow.first
import java.io.IOException

/** Includes portable reading/prayer state too. Remote calendar event ids and verse caches are device-local. */
class BackupPreferences(
    context: Context,
    private val settings: UserPreferencesStore,
    temptation: TemptationStore = TemptationStore(context),
    reminder: ReminderStore = ReminderStore(context)
) : BackupPreferenceStorage {
    companion object {
        private val SHARED = listOf("reading_position", "bible_reader", "catechism_reader", "beginner_journey", "unfinished_prayer_session")
        val NAMES = setOf("settings", "temptations", "reminders") + SHARED.map { "shared:$it" }
        private fun portable(key: String) = !key.startsWith("rem_gcal_event_") && key != "internal_read_failed"
        private fun encode(key: String, value: Any): BackupPreference = when (value) {
            is String -> BackupPreference(key,"STRING",value)
            is Boolean -> BackupPreference(key,"BOOLEAN",value.toString())
            is Int -> BackupPreference(key,"INT",value.toString())
            is Long -> BackupPreference(key,"LONG",value.toString())
            is Float -> BackupPreference(key,"FLOAT",value.toString())
            is Double -> BackupPreference(key,"DOUBLE",value.toString())
            is Set<*> -> BackupPreference(key,"STRING_SET",values=value.map { it as String }.sorted())
            else -> throw IOException("Unsupported preference value")
        }
        private fun progress(raw: String?): Map<Int,Int> = raw.orEmpty().split(',').mapNotNull { item ->
            val p = item.split(':'); if (p.size != 2) null else p[0].toIntOrNull()?.let { k -> p[1].toIntOrNull()?.let { k to it } }
        }.toMap()
        fun merge(local: List<BackupPreferenceGroup>, incoming: List<BackupPreferenceGroup>): List<BackupPreferenceGroup> {
            val groups = local.associateByTo(linkedMapOf()) { it.name }
            incoming.forEach { group ->
                val previous = groups[group.name]
                if (group.name == "shared:unfinished_prayer_session" && previous?.entries?.any { it.key == "rosary_step" } == true) return@forEach
                val a = previous?.entries.orEmpty().associateBy { it.key }
                val result = a.toMutableMap()
                group.entries.forEach { p ->
                    val old = a[p.key]
                    result[p.key] = when {
                        p.type == "STRING_SET" && old?.type == "STRING_SET" -> p.copy(values=(old.values.orEmpty()+p.values.orEmpty()).distinct().sorted())
                        group.name == "temptations" && p.type == "INT" && old?.type == "INT" -> p.copy(value=maxOf(old.value!!.toInt(),p.value!!.toInt()).toString())
                        group.name == "shared:beginner_journey" && p.key == "step" && old?.type == "INT" -> p.copy(value=maxOf(old.value!!.toInt(),p.value!!.toInt()).toString())
                        group.name == "settings" && p.key == "track_progress" -> p.copy(value=(progress(old?.value).toMutableMap().apply {
                            progress(p.value).forEach { (k,v) -> this[k]=maxOf(this[k] ?: 0,v) }
                        }).entries.sortedBy { it.key }.joinToString(",") { "${it.key}:${it.value}" })
                        else -> p
                    }
                }
                if (group.name == "settings") {
                    val visited = listOf(a,group.entries.associateBy { it.key }).flatMap { entries ->
                        entries["read_questions_v2"]?.values.orEmpty() + progress(entries["track_progress"]?.value).values.map { it.toString() }
                    }.distinct().filter { it.toIntOrNull() in 1..101 }
                    if (visited.isNotEmpty()) result["read_questions_v2"]=BackupPreference("read_questions_v2","STRING_SET",values=visited.sorted())
                }
                groups[group.name]=group.copy(entries=result.values.sortedBy { it.key })
            }
            return groups.values.toList()
        }
    }
    private val app = context.applicationContext
    private val stores: Map<String, DataStore<Preferences>> = mapOf("settings" to settings.backupDataStore,
        "temptations" to temptation.backupDataStore, "reminders" to reminder.backupDataStore)

    override suspend fun snapshot(): List<BackupPreferenceGroup> = stores.map { (name,store) ->
        BackupPreferenceGroup(name,store.data.first().asMap().entries.filter { portable(it.key.name) }
            .map { encode(it.key.name,it.value) }.sortedBy { it.key })
    } + SHARED.map { name ->
        BackupPreferenceGroup("shared:$name",app.getSharedPreferences(name,Context.MODE_PRIVATE).all.entries
            .mapNotNull { (k,v) -> v?.let { encode(k,it) } }.sortedBy { it.key })
    }

    /** Called within the coordinated restore; each DataStore write is itself atomic. */
    override suspend fun write(groups: List<BackupPreferenceGroup>) {
        require(groups.all { it.name in NAMES })
        val incoming=groups.associateBy { it.name }
        NAMES.forEach { name ->
            val group=incoming[name] ?: BackupPreferenceGroup(name,emptyList())
            val store = stores[group.name]
            if (store != null) store.edit { prefs ->
                prefs.asMap().keys.filter { portable(it.name) }.forEach { prefs.remove(it) }
                group.entries.filter { portable(it.key) }.forEach { p ->
                    when(p.type) {
                        "STRING" -> prefs[stringPreferencesKey(p.key)]=p.value!!
                        "BOOLEAN" -> prefs[booleanPreferencesKey(p.key)]=p.value!!.toBooleanStrict()
                        "INT" -> prefs[intPreferencesKey(p.key)]=p.value!!.toInt()
                        "LONG" -> prefs[longPreferencesKey(p.key)]=p.value!!.toLong()
                        "FLOAT" -> prefs[floatPreferencesKey(p.key)]=p.value!!.toFloat()
                        "DOUBLE" -> prefs[doublePreferencesKey(p.key)]=p.value!!.toDouble()
                        "STRING_SET" -> prefs[stringSetPreferencesKey(p.key)]=p.values!!.toSet()
                    }
                }
            } else {
                require(group.name in NAMES)
                val editor = app.getSharedPreferences(group.name.removePrefix("shared:"),Context.MODE_PRIVATE).edit().clear()
                group.entries.forEach { p ->
                    when(p.type) {
                        "STRING" -> editor.putString(p.key,p.value)
                        "BOOLEAN" -> editor.putBoolean(p.key,p.value!!.toBooleanStrict())
                        "INT" -> editor.putInt(p.key,p.value!!.toInt())
                        "LONG" -> editor.putLong(p.key,p.value!!.toLong())
                        "FLOAT" -> editor.putFloat(p.key,p.value!!.toFloat())
                        "STRING_SET" -> editor.putStringSet(p.key,p.values!!.toSet())
                        else -> throw IOException("Unsupported shared preference value")
                    }
                }
                if (!editor.commit()) throw IOException("Unable to restore local preferences")
            }
        }
        settings.retryRead()
    }
}
