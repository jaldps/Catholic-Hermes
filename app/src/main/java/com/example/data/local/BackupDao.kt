package com.example.data.local

import androidx.room.*

/** Queries only: no new tables, columns, migrations or schema version. */
@Dao
interface BackupDao {
    @Query("SELECT * FROM journal_entries")
    suspend fun journalEntries(): List<JournalEntryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putJournalEntries(rows: List<JournalEntryEntity>)

    @Query("DELETE FROM journal_entries")
    suspend fun clearJournalEntries()

    @Query("SELECT * FROM novena_progress")
    suspend fun novenaProgress(): List<NovenaProgressEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putNovenaProgress(rows: List<NovenaProgressEntity>)

    @Query("DELETE FROM novena_progress")
    suspend fun clearNovenaProgress()

    @Query("SELECT * FROM novena_day_completions")
    suspend fun novenaDays(): List<NovenaDayCompletionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putNovenaDays(rows: List<NovenaDayCompletionEntity>)

    @Query("DELETE FROM novena_day_completions")
    suspend fun clearNovenaDays()

    @Query("SELECT * FROM memorized_verses")
    suspend fun memorizedVerses(): List<MemorizedVerseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putMemorizedVerses(rows: List<MemorizedVerseEntity>)

    @Query("DELETE FROM memorized_verses")
    suspend fun clearMemorizedVerses()

    @Query("SELECT * FROM user_prayer_stats")
    suspend fun prayerStats(): List<UserPrayerStatsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putPrayerStats(rows: List<UserPrayerStatsEntity>)

    @Query("DELETE FROM user_prayer_stats")
    suspend fun clearPrayerStats()

    @Query("SELECT * FROM spiritual_habits")
    suspend fun habits(): List<SpiritualHabitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putHabits(rows: List<SpiritualHabitEntity>)

    @Query("DELETE FROM spiritual_habits")
    suspend fun clearHabits()

    @Query("SELECT * FROM habit_completion_logs")
    suspend fun habitLogs(): List<HabitCompletionLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putHabitLogs(rows: List<HabitCompletionLogEntity>)

    @Query("DELETE FROM habit_completion_logs")
    suspend fun clearHabitLogs()

    @Query("SELECT * FROM sin_overcome_logs")
    suspend fun sinLogs(): List<SinOvercomeLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putSinLogs(rows: List<SinOvercomeLogEntity>)

    @Query("DELETE FROM sin_overcome_logs")
    suspend fun clearSinLogs()

    @Query("SELECT * FROM scripture_study_progress")
    suspend fun studyProgress(): List<ScriptureStudyProgressEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putStudyProgress(rows: List<ScriptureStudyProgressEntity>)

    @Query("DELETE FROM scripture_study_progress")
    suspend fun clearStudyProgress()

    @Query("SELECT EXISTS(SELECT 1 FROM personal_data_migrations WHERE migrationKey = :key)")
    suspend fun committed(key: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun mark(row: PersonalDataMigrationEntity)

    @Query("DELETE FROM personal_data_migrations WHERE migrationKey = :key")
    suspend fun unmark(key: String)
}
