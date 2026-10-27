package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entries")
data class JournalEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val category: String, // "Intention", "Gratitude", "Examen", "Grace"
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isAnswered: Boolean = false,
    val answeredNotes: String = ""
)

@Entity(tableName = "novena_progress")
data class NovenaProgressEntity(
    @PrimaryKey val novenaId: String,
    val currentDay: Int, // 1 to 9
    val personalIntention: String,
    val startDate: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)

@Entity(tableName = "memorized_verses")
data class MemorizedVerseEntity(
    @PrimaryKey val id: String,
    val reference: String,
    val text: String,
    val isMastered: Boolean = false,
    val lastReviewed: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_prayer_stats")
data class UserPrayerStatsEntity(
    @PrimaryKey val id: Int = 1,
    val rosariesPrayed: Int = 0,
    val chapletsPrayed: Int = 0,
    val novenasCompleted: Int = 0,
    val triviaScore: Int = 0,
    val triviaStreak: Int = 0
)

@Entity(tableName = "spiritual_habits")
data class SpiritualHabitEntity(
    @PrimaryKey val id: String,
    val titleEs: String,
    val titleEn: String,
    val titlePt: String,
    val description: String,
    val category: String, // "PRAYER", "FASTING", "SACRAMENT", "CHARITY", "VIRTUE"
    val timeSuggestion: String,
    val iconName: String,
    val isCustom: Boolean = false,
    val currentStreak: Int = 0,
    val bestStreak: Int = 0,
    val lastCompletedDate: String = "", // YYYY-MM-DD
    val totalCompletions: Int = 0
)

@Entity(tableName = "habit_completion_logs")
data class HabitCompletionLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val habitId: String,
    val completionDate: String, // YYYY-MM-DD
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "sin_overcome_logs")
data class SinOvercomeLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sinKey: String, // "LUST", "ANGER", "GLUTTONY", etc.
    val counterVirtue: String,
    val prayerUsed: String = "HAIL_MARY", // "HAIL_MARY", "JESUS_PRAYER", "ST_MICHAEL"
    val timestamp: Long = System.currentTimeMillis(),
    val note: String = ""
)

@Entity(tableName = "liturgical_days")
data class LiturgicalDayEntity(
    @PrimaryKey val date: String, // YYYY-MM-DD
    val celebrationTitle: String,
    val seasonName: String,
    val rank: String, // "Solemnity", "Feast", "Memorial", "Ferial"
    val liturgicalColorHex: Long,
    val sundayCycle: String, // "Year A", "Year B", "Year C", ""
    val weekdayCycle: String, // "Year I", "Year II", ""
    val isFastOrAbstinence: Boolean = false,
    val fastingRule: String = "",
    val firstReadingRef: String = "",
    val firstReadingText: String = "",
    val responsorialPsalmRef: String = "",
    val responsorialPsalmText: String = "",
    val secondReadingRef: String = "",
    val secondReadingText: String = "",
    val gospelAcclamation: String = "",
    val gospelRef: String = "",
    val gospelText: String = "",
    val missalMeditation: String = "",
    val saintOfFeast: String = ""
)

@Entity(tableName = "scripture_study_progress")
data class ScriptureStudyProgressEntity(
    @PrimaryKey val lessonId: String,
    val trackId: String,
    val isCompleted: Boolean = false,
    val completedTimestamp: Long = 0L,
    val personalNotes: String = ""
)

@Entity(tableName = "app_preferences")
data class AppPreferencesEntity(
    @PrimaryKey val id: String = "default_preferences",
    val themePreset: String = "LITURGICAL_DYNAMIC",
    val fontFamilyChoice: String = "SERIF",
    val fontSizeScale: Float = 1.0f,
    val darkModeSetting: String = "SYSTEM", // "SYSTEM", "DARK", "LIGHT"
    val audioNarrationSpeed: Float = 1.0f,
    val activeBibleTranslationId: String = "jerusalem",
    val isFullCccDownloaded: Boolean = false
)

@Entity(tableName = "bible_downloads")
data class BibleDownloadEntity(
    @PrimaryKey val versionId: String,
    val name: String,
    val language: String,
    val sizeDisplay: String,
    val isDownloaded: Boolean = false,
    val downloadProgress: Int = 0,
    val isDownloading: Boolean = false
)

