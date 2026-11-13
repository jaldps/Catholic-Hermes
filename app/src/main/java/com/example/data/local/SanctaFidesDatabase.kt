package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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
    exportSchema = true
)
abstract class SanctaFidesDatabase : RoomDatabase() {
    abstract fun dao(): SanctaFidesDao

    companion object {
        // The initial repository revision (edeab98) is schema 3. Revision
        // 84c6419 added only these two tables. No v1/v2 schemas are known;
        // unsupported upgrades fail without deleting personal records.
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""CREATE TABLE IF NOT EXISTS `app_preferences` (
                    `id` TEXT NOT NULL, `themePreset` TEXT NOT NULL,
                    `fontFamilyChoice` TEXT NOT NULL, `fontSizeScale` REAL NOT NULL,
                    `darkModeSetting` TEXT NOT NULL, `audioNarrationSpeed` REAL NOT NULL,
                    `activeBibleTranslationId` TEXT NOT NULL, `isFullCccDownloaded` INTEGER NOT NULL,
                    PRIMARY KEY(`id`))""")
                db.execSQL("""CREATE TABLE IF NOT EXISTS `bible_downloads` (
                    `versionId` TEXT NOT NULL, `name` TEXT NOT NULL, `language` TEXT NOT NULL,
                    `sizeDisplay` TEXT NOT NULL, `isDownloaded` INTEGER NOT NULL,
                    `downloadProgress` INTEGER NOT NULL, `isDownloading` INTEGER NOT NULL,
                    PRIMARY KEY(`versionId`))""")
            }
        }

        @Volatile
        private var INSTANCE: SanctaFidesDatabase? = null

        fun getInstance(context: Context): SanctaFidesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SanctaFidesDatabase::class.java,
                    "sancta_fides_db"
                ).addMigrations(MIGRATION_3_4).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
