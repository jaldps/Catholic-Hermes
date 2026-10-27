package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        JournalEntryEntity::class,
        NovenaProgressEntity::class,
        MemorizedVerseEntity::class,
        UserPrayerStatsEntity::class,
        SpiritualHabitEntity::class,
        HabitCompletionLogEntity::class,
        SinOvercomeLogEntity::class,
        LiturgicalDayEntity::class,
        ScriptureStudyProgressEntity::class,
        AppPreferencesEntity::class,
        BibleDownloadEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class SanctaFidesDatabase : RoomDatabase() {
    abstract fun dao(): SanctaFidesDao

    companion object {
        @Volatile
        private var INSTANCE: SanctaFidesDatabase? = null

        fun getInstance(context: Context): SanctaFidesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SanctaFidesDatabase::class.java,
                    "sancta_fides_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
