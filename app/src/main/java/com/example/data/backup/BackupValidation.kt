package com.example.data.backup

import java.time.LocalDate

internal object BackupValidation {
    fun validate(data: BackupData) {
        if (data.formatVersion != 1 || data.databaseVersion !in 3..5) throw BackupException(BackupError.UNSUPPORTED_VERSION)
        fun valid(condition: Boolean) { if (!condition) throw BackupException(BackupError.INVALID_FILE) }
        fun <T, K> unique(rows: List<T>, key: (T) -> K) {
            valid(rows.size <= 100_000 && rows.map(key).toSet().size == rows.size)
        }
        fun id(s: String) = s.isNotBlank() && s.length <= 512
        fun date(s: String) = runCatching { LocalDate.parse(s); true }.getOrDefault(false)
        unique(data.journalEntries) { it.id }; valid(data.journalEntries.all { it.id > 0 })
        unique(data.novenaProgress) { it.novenaId }; valid(data.novenaProgress.all { id(it.novenaId) })
        unique(data.novenaDays) { it.novenaId to it.dayNumber }
        valid(data.novenaDays.all { id(it.novenaId) && it.dayNumber in 1..10_000 })
        unique(data.memorizedVerses) { it.id }; valid(data.memorizedVerses.all { id(it.id) })
        unique(data.prayerStats) { it.id }
        valid(data.prayerStats.all { it.id == 1 && listOf(it.rosariesPrayed,it.chapletsPrayed,it.novenasCompleted,it.triviaScore,it.triviaStreak).all { n -> n >= 0 } })
        unique(data.habits) { it.id }
        valid(data.habits.all { id(it.id) && it.currentStreak >= 0 && it.bestStreak >= 0 && it.totalCompletions >= 0 })
        unique(data.habitLogs) { it.habitId to it.completionDate }
        unique(data.habitLogs) { it.id }; valid(data.habitLogs.all { it.id > 0 && id(it.habitId) && date(it.completionDate) })
        unique(data.sinLogs) { it.id }; valid(data.sinLogs.all { it.id > 0 && id(it.sinKey) })
        unique(data.studyProgress) { it.lessonId }; valid(data.studyProgress.all { id(it.lessonId) && id(it.trackId) })
        unique(data.preferences) { it.name }
        valid(data.preferences.any { it.name == "settings" })
        valid(data.preferences.all { it.name in BackupPreferences.NAMES })
        data.preferences.forEach { group ->
            unique(group.entries) { it.key }
            valid(group.entries.size <= 10_000)
            group.entries.forEach { pref ->
                valid(pref.key.length in 1..256 && pref.key.none { it.isISOControl() })
                valid(if (pref.type == "STRING_SET") pref.value == null && pref.values != null && pref.values.size <= 100_000 && pref.values.toSet().size == pref.values.size
                    else pref.values == null && pref.value != null)
                val value = pref.value.orEmpty()
                valid(when (pref.type) {
                    "STRING", "STRING_SET" -> true
                    "BOOLEAN" -> value == "true" || value == "false"
                    "INT" -> value.toIntOrNull() != null
                    "LONG" -> value.toLongOrNull() != null
                    "FLOAT" -> value.toFloatOrNull()?.isFinite() == true
                    "DOUBLE" -> value.toDoubleOrNull()?.isFinite() == true
                    else -> false
                })
                val expected = when (group.name) {
                    "settings" -> when(pref.key) {
                        "language", "design_language", "theme_preset", "dark_mode_setting", "font_family_choice", "active_bible_translation_id", "bible_default_editions", "track_progress" -> "STRING"
                        "has_chosen_language", "reduce_motion", "reduce_transparency", "high_contrast", "is_full_ccc_downloaded", "calendar_epiphany_sunday", "calendar_ascension_sunday", "calendar_corpus_sunday" -> "BOOLEAN"
                        "font_size_scale", "audio_narration_speed" -> "FLOAT"
                        "last_read_question" -> "INT"
                        "read_questions_v2" -> "STRING_SET"
                        else -> if (pref.key.startsWith("narration_voice_")) "STRING" else null
                    }
                    "temptations" -> "INT"
                    "reminders" -> when {
                        pref.key.startsWith("rem_enabled_") -> "BOOLEAN"
                        pref.key.startsWith("rem_time_") || pref.key == "novena_name" -> "STRING"
                        pref.key == "novena_start_epoch_day" || pref.key.startsWith("rem_gcal_event_") -> "LONG"
                        else -> null
                    }
                    "shared:reading_position" -> when(pref.key) { "chapter" -> "INT"; "book_id" -> "STRING"; else -> null }
                    "shared:bible_reader" -> when(pref.key) { "scale" -> "FLOAT"; "spacing", "chapter", "verse" -> "INT"; "font", "book" -> "STRING"; else -> null }
                    "shared:catechism_reader" -> if(pref.key == "last_paragraph") "INT" else null
                    "shared:beginner_journey" -> if(pref.key == "welcomed") "BOOLEAN" else if(pref.key in setOf("step","profile","minutes")) "INT" else null
                    "shared:unfinished_prayer_session" -> when(pref.key) { "instructions" -> "BOOLEAN"; "mystery" -> "STRING"; else -> "INT" }
                    else -> null
                }
                valid(expected == null || pref.type == expected)
                if (group.name.startsWith("shared:")) valid(pref.type != "DOUBLE")
                if (group.name == "temptations") valid(value.toIntOrNull()?.let { it >= 0 } == true)
                if (pref.key == "font_size_scale") valid(pref.type == "FLOAT" && value.toFloat() in 0.5f..3f)
                if (pref.key == "audio_narration_speed") valid(pref.type == "FLOAT" && value.toFloat() in 0.25f..3f)
            }
        }
    }
}
