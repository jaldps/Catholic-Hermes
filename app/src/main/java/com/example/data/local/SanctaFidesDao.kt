package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletion(completion: HabitCompletionLogEntity): Long

    @Query("DELETE FROM habit_completion_logs WHERE habitId = :habitId AND completionDate = :date")
    suspend fun deleteCompletionForDate(habitId: String, date: String)

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

