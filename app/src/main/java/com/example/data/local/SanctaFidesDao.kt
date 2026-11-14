package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface SanctaFidesDao {
    // Journal Entries
    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournalEntry(entry: JournalEntryEntity): Long

    @Update
    suspend fun updateJournalEntry(entry: JournalEntryEntity)

    @Delete
    suspend fun deleteJournalEntry(entry: JournalEntryEntity)

    // Novena Progress
    @Query("SELECT * FROM novena_progress")
    fun getAllNovenaProgress(): Flow<List<NovenaProgressEntity>>

    @Query("SELECT * FROM novena_progress WHERE novenaId = :novenaId LIMIT 1")
    suspend fun getNovenaProgress(novenaId: String): NovenaProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNovenaProgress(progress: NovenaProgressEntity)

    @Query("DELETE FROM novena_progress WHERE novenaId = :novenaId")
    suspend fun deleteNovenaProgress(novenaId: String)

    @Query("SELECT * FROM novena_progress")
    suspend fun novenaProgressOnce(): List<NovenaProgressEntity>

    @Query("SELECT * FROM novena_day_completions")
    fun novenaDays(): Flow<List<NovenaDayCompletionEntity>>

    @Query("SELECT m.migrationKey, d.novenaId, d.dayNumber FROM personal_data_migrations m LEFT JOIN novena_day_completions d ON 1 = 1 WHERE m.migrationKey = 'novena-days-v1'")
    fun novenaDaySnapshot(): Flow<List<NovenaDaySnapshotRow>>

    @Query("SELECT * FROM novena_day_completions WHERE novenaId = :id")
    suspend fun novenaDaysOnce(id: String): List<NovenaDayCompletionEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNovenaDays(days: List<NovenaDayCompletionEntity>)

    @Query("DELETE FROM novena_day_completions WHERE novenaId = :id AND dayNumber = :day")
    suspend fun deleteNovenaDay(id: String, day: Int)

    @Query("DELETE FROM novena_day_completions WHERE novenaId = :id")
    suspend fun clearNovenaDays(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM personal_data_migrations WHERE migrationKey = :key)")
    fun migrationApplied(key: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM personal_data_migrations WHERE migrationKey = :key)")
    suspend fun migrationAppliedOnce(key: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun markMigration(migration: PersonalDataMigrationEntity)

    /** Read-only legacy preferences are imported once, atomically with the durable marker. */
    @Transaction
    suspend fun importLegacyNovenaDays(extras: Map<String, Set<Int>>) {
        if (migrationAppliedOnce("novena-days-v1")) return
        val progress = novenaProgressOnce().associateBy { it.novenaId }
        for (id in progress.keys + extras.keys) {
            val previous = progress[id]
            val first = (1..31).filter { day -> (previous?.currentDay ?: 0) and (1 shl (day-1)) != 0 }.toSet()
            val days = first + extras[id].orEmpty().filter { it > 0 }
            insertNovenaDays(days.map { NovenaDayCompletionEntity(id,it,0L) })
            val mask = days.filter { it in 1..31 }.fold(0) { value, d -> value or (1 shl(d-1)) }
            if (previous == null) saveNovenaProgress(NovenaProgressEntity(id,mask,"",0L,0L,false))
            else if (mask != previous.currentDay) saveNovenaProgress(previous.copy(currentDay=mask))
        }
        markMigration(PersonalDataMigrationEntity("novena-days-v1"))
    }

    @Transaction
    suspend fun setNovenaDay(id: String, day: Int, completed: Boolean, required: Set<Int>) {
        require(day in required && day > 0)
        val hadDays = novenaDaysOnce(id).isNotEmpty()
        if (completed) insertNovenaDays(listOf(NovenaDayCompletionEntity(id,day))) else deleteNovenaDay(id,day)
        val days = novenaDaysOnce(id).map { it.dayNumber }.toSet()
        val previous = getNovenaProgress(id)
        val now = System.currentTimeMillis()
        saveNovenaProgress((previous ?: NovenaProgressEntity(id,0,"",now,now,false)).copy(
            currentDay = days.filter { it in 1..31 }.fold(0) { mask, d -> mask or (1 shl (d-1)) },
            startDate = previous?.startDate?.takeIf { it > 0L } ?: if (!hadDays) now else 0L,
            lastUpdated = now, isCompleted = required.isNotEmpty() && required.all { it in days }
        ))
    }

    @Transaction
    suspend fun resetNovenaDays(id: String) {
        clearNovenaDays(id)
        getNovenaProgress(id)?.let { saveNovenaProgress(it.copy(currentDay=0,startDate=0L,lastUpdated=System.currentTimeMillis(),isCompleted=false)) }
    }

    // Memorized Verses
    @Query("SELECT * FROM memorized_verses")
    fun getAllMemorizedVerses(): Flow<List<MemorizedVerseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMemorizedVerse(verse: MemorizedVerseEntity)

    @Delete
    suspend fun deleteMemorizedVerse(verse: MemorizedVerseEntity)

    // User Stats
    @Query("SELECT * FROM user_prayer_stats WHERE id = 1 LIMIT 1")
    fun getUserStats(): Flow<UserPrayerStatsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserStats(stats: UserPrayerStatsEntity)

    // Spiritual Habits
    @Query("SELECT * FROM spiritual_habits ORDER BY isCustom ASC, id ASC")
    fun getAllHabits(): Flow<List<SpiritualHabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: SpiritualHabitEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDefaultHabits(habits: List<SpiritualHabitEntity>)

    @Update
    suspend fun updateHabit(habit: SpiritualHabitEntity)

    @Delete
    suspend fun deleteHabit(habit: SpiritualHabitEntity)

    // Habit Completions
    @Query("SELECT * FROM habit_completion_logs WHERE completionDate = :date")
    fun getCompletionsForDate(date: String): Flow<List<HabitCompletionLogEntity>>

    @Query("SELECT * FROM habit_completion_logs")
    fun getAllCompletions(): Flow<List<HabitCompletionLogEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompletion(completion: HabitCompletionLogEntity): Long

    @Query("DELETE FROM habit_completion_logs WHERE habitId = :habitId AND completionDate = :date")
    suspend fun deleteCompletionForDate(habitId: String, date: String)

    @Transaction
    suspend fun setHabitCompletion(habitId: String, date: String, completed: Boolean) {
        if (completed) insertCompletion(HabitCompletionLogEntity(habitId=habitId,completionDate=date))
        else deleteCompletionForDate(habitId,date)
    }

    // Sin Overcome Micro-efforts (Grace Antidote)
    @Query("SELECT * FROM sin_overcome_logs ORDER BY timestamp DESC")
    fun getAllSinOvercomeLogs(): Flow<List<SinOvercomeLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSinOvercomeLog(log: SinOvercomeLogEntity): Long

    @Query("SELECT COUNT(*) FROM sin_overcome_logs WHERE sinKey = :sinKey")
    fun getSinOvercomeCount(sinKey: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM sin_overcome_logs")
    fun getTotalVictoriesCount(): Flow<Int>

    // Liturgical Calendar & Readings
    @Query("SELECT * FROM liturgical_days WHERE date = :date LIMIT 1")
    fun getLiturgicalDay(date: String): Flow<LiturgicalDayEntity?>

    @Query("SELECT * FROM liturgical_days ORDER BY date ASC")
    fun getAllLiturgicalDays(): Flow<List<LiturgicalDayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLiturgicalDay(day: LiturgicalDayEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLiturgicalDays(days: List<LiturgicalDayEntity>)

    // Scripture Study Progress
    @Query("SELECT * FROM scripture_study_progress WHERE lessonId = :lessonId LIMIT 1")
    fun getScriptureProgress(lessonId: String): Flow<ScriptureStudyProgressEntity?>

    @Query("SELECT * FROM scripture_study_progress")
    fun getAllScriptureProgress(): Flow<List<ScriptureStudyProgressEntity>>

    @Query("SELECT COUNT(*) FROM scripture_study_progress WHERE trackId = :trackId AND isCompleted = 1")
    fun getCompletedLessonCountForTrack(trackId: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveScriptureProgress(progress: ScriptureStudyProgressEntity)

    @Query("SELECT * FROM scripture_study_progress WHERE lessonId = :lessonId LIMIT 1")
    suspend fun scriptureProgressOnce(lessonId: String): ScriptureStudyProgressEntity?

    @Transaction
    suspend fun saveScriptureNotes(lessonId: String, trackId: String, notes: String) {
        val previous = scriptureProgressOnce(lessonId)
        saveScriptureProgress((previous ?: ScriptureStudyProgressEntity(lessonId, trackId)).copy(personalNotes = notes))
    }

    @Transaction
    suspend fun setScriptureCompletion(lessonId: String, trackId: String, notes: String, completed: Boolean) {
        val previous = scriptureProgressOnce(lessonId)
        saveScriptureProgress((previous ?: ScriptureStudyProgressEntity(lessonId, trackId)).copy(
            personalNotes = notes, isCompleted = completed,
            completedTimestamp = if (!completed) 0L else previous?.completedTimestamp?.takeIf { it > 0L } ?: System.currentTimeMillis()
        ))
    }

    // App Preferences
    @Query("SELECT * FROM app_preferences WHERE id = :id LIMIT 1")
    fun getAppPreferences(id: String = "default_preferences"): Flow<AppPreferencesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAppPreferences(prefs: AppPreferencesEntity)

    // Bible Downloads
    @Query("SELECT * FROM bible_downloads")
    fun getAllBibleDownloads(): Flow<List<BibleDownloadEntity>>

    @Query("SELECT * FROM bible_downloads")
    suspend fun getAllBibleDownloadsSync(): List<BibleDownloadEntity>

    @Query("SELECT * FROM bible_downloads WHERE versionId = :versionId LIMIT 1")
    fun getBibleDownload(versionId: String): Flow<BibleDownloadEntity?>

    @Query("SELECT * FROM bible_downloads WHERE versionId = :versionId LIMIT 1")
    suspend fun getBibleDownloadSync(versionId: String): BibleDownloadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateBibleDownload(download: BibleDownloadEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDefaultBibleDownloads(downloads: List<BibleDownloadEntity>)
}

