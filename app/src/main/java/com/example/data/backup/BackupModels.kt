package com.example.data.backup

import com.example.data.local.*

/** Portable records, never a copy of SQLite files, cache tables or migration markers. */
data class BackupData(
    val formatVersion: Int,
    val databaseVersion: Int,
    val createdAt: Long,
    val journalEntries: List<JournalEntryEntity>,
    val novenaProgress: List<NovenaProgressEntity>,
    val novenaDays: List<NovenaDayCompletionEntity>,
    val memorizedVerses: List<MemorizedVerseEntity>,
    val prayerStats: List<UserPrayerStatsEntity>,
    val habits: List<SpiritualHabitEntity>,
    val habitLogs: List<HabitCompletionLogEntity>,
    val sinLogs: List<SinOvercomeLogEntity>,
    val studyProgress: List<ScriptureStudyProgressEntity>,
    val preferences: List<BackupPreferenceGroup>
)

/** Explicit primitive types preserve DataStore Int/Long/Float values without JSON coercion. */
data class BackupPreference(
    val key: String,
    val type: String,
    val value: String? = null,
    val values: List<String>? = null
)
data class BackupPreferenceGroup(val name: String, val entries: List<BackupPreference>)

enum class RestoreMode { MERGE, REPLACE }
class PreparedBackup internal constructor(internal val data: BackupData) {
    private val prayedNovenas = data.novenaDays.mapTo(hashSetOf()) { it.novenaId }
    val summary = BackupSummary(data.createdAt, data.journalEntries.size,
        data.novenaProgress.count { !it.isCompleted && (it.currentDay != 0 || it.startDate != 0L || it.personalIntention.isNotBlank() || it.novenaId in prayedNovenas) },
        data.novenaProgress.size, data.novenaDays.size, data.memorizedVerses.size,
        data.prayerStats.size, data.habits.size, data.habitLogs.size, data.sinLogs.size,
        data.studyProgress.size, data.studyProgress.count { it.personalNotes.isNotBlank() },
        data.preferences.sumOf { it.entries.size },data.prayerStats.sumOf { it.rosariesPrayed },data.prayerStats.sumOf { it.chapletsPrayed })
}
data class BackupSummary(
    val createdAt: Long, val journalEntries: Int, val activeNovenas: Int, val novenas: Int,
    val novenaDays: Int, val memorizedVerses: Int, val prayerStats: Int, val habits: Int,
    val habitLogs: Int, val sinLogs: Int, val studyLessons: Int, val studyNotes: Int, val preferences: Int,
    val rosariesPrayed: Int = 0, val chapletsPrayed: Int = 0
)

enum class BackupError { INVALID_FILE, UNSUPPORTED_VERSION, WRONG_PASSWORD_OR_DAMAGED, TOO_LARGE, PASSWORD_REQUIRED, STORAGE }
class BackupException(val reason: BackupError, cause: Throwable? = null) : Exception(reason.name, cause)
