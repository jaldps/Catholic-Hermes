package com.example

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.SanctaFidesDatabase
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/** Schema 3 is schema 4 minus the two tables added in git revision 84c6419. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class DatabaseMigrationPreservationTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val name = "migration-preservation.db"
    private fun open() = Room.databaseBuilder(context, SanctaFidesDatabase::class.java, name)
        .addMigrations(SanctaFidesDatabase.MIGRATION_3_4, SanctaFidesDatabase.MIGRATION_4_5).allowMainThreadQueries().build()

    private fun oldDatabase(version: Int) {
        context.deleteDatabase(name)
        open().let { database ->
            try {
            val sql = database.openHelper.writableDatabase
            sql.execSQL("INSERT INTO journal_entries VALUES (7, 'Private intention', 'Intention', 'Preserve me', 123, 0, '')")
            sql.execSQL("INSERT INTO novena_progress VALUES ('mercy', 5, 'Personal intention', 10, 20, 0)")
            sql.execSQL("INSERT INTO sin_overcome_logs VALUES (4, 'ANGER', 'PATIENCE', 'HAIL_MARY', 321, 'Private history')")
            sql.execSQL("INSERT INTO scripture_study_progress VALUES ('lesson', 'track', 1, 123, 'My notes')")
            } finally { database.close() }
        }
        SQLiteDatabase.openDatabase(context.getDatabasePath(name).path, null, SQLiteDatabase.OPEN_READWRITE).use { sql ->
            sql.execSQL("DROP TABLE app_preferences")
            sql.execSQL("DROP TABLE bible_downloads")
            sql.execSQL("DELETE FROM room_master_table")
            sql.version = version
        }
    }

    @Test fun upgradeThreeToFourPreservesPersonalRecordsAndAddsTables() {
        oldDatabase(3)
        open().let { database ->
            try {
            val sql = database.openHelper.writableDatabase
            for ((table, column, expected) in listOf(
                Triple("journal_entries", "content", "Preserve me"),
                Triple("novena_progress", "personalIntention", "Personal intention"),
                Triple("sin_overcome_logs", "note", "Private history"),
                Triple("scripture_study_progress", "personalNotes", "My notes")
            )) {
                sql.query("SELECT $column FROM $table").use { cursor ->
                    assertTrue(cursor.moveToFirst())
                    assertEquals(expected, cursor.getString(0))
                }
            }
            sql.query("SELECT currentDay FROM novena_progress").use { assertTrue(it.moveToFirst()); assertEquals(5, it.getInt(0)) }
            sql.execSQL("INSERT INTO app_preferences VALUES ('default', 'LITURGICAL_DYNAMIC', 'SERIF', 1.0, 'SYSTEM', 1.0, 'jerusalem', 0)")
            sql.execSQL("INSERT INTO bible_downloads VALUES ('drc', 'Douay-Rheims', 'en', '', 0, 0, 0)")
            } finally { database.close() }
        }
        context.deleteDatabase(name)
    }

    @Test fun upgradeFourToFivePreservesPrivateDataAndConsolidatesHabitDates() {
        context.deleteDatabase(name)
        open().let { database ->
            try {
                val sql=database.openHelper.writableDatabase
                sql.execSQL("INSERT INTO journal_entries VALUES (7, 'Private intention', 'Intention', 'Keep this note', 123, 0, '')")
                sql.execSQL("INSERT INTO novena_progress VALUES ('long', 5, 'Preserve intention', 10, 20, 0)")
            } finally { database.close() }
        }
        // Reconstruct the actual schema-4 shape: new tables/index are absent.
        SQLiteDatabase.openDatabase(context.getDatabasePath(name).path,null,SQLiteDatabase.OPEN_READWRITE).use { sql ->
            sql.execSQL("DROP TABLE novena_day_completions")
            sql.execSQL("DROP TABLE personal_data_migrations")
            sql.execSQL("DROP INDEX index_habit_completion_logs_habitId_completionDate")
            sql.execSQL("INSERT INTO habit_completion_logs VALUES (1,'habit','2026-11-13',111)")
            sql.execSQL("INSERT INTO habit_completion_logs VALUES (2,'habit','2026-11-13',222)")
            sql.execSQL("DELETE FROM room_master_table")
            sql.version=4
        }
        open().let { database ->
            try {
                val sql=database.openHelper.writableDatabase
                assertEquals(5,sql.version)
                sql.query("SELECT content FROM journal_entries WHERE id=7").use { assertTrue(it.moveToFirst());assertEquals("Keep this note",it.getString(0)) }
                sql.query("SELECT personalIntention FROM novena_progress WHERE novenaId='long'").use { assertTrue(it.moveToFirst());assertEquals("Preserve intention",it.getString(0)) }
                sql.query("SELECT COUNT(*), MIN(timestamp) FROM habit_completion_logs").use { assertTrue(it.moveToFirst());assertEquals(1,it.getInt(0));assertEquals(111L,it.getLong(1)) }
                kotlinx.coroutines.runBlocking {
                    database.dao().importLegacyNovenaDays(mapOf("long" to setOf(32,54,275)))
                    assertEquals(setOf(1,3,32,54,275),database.dao().novenaDaysOnce("long").map {it.dayNumber}.toSet())
                }
            }finally{database.close()}
        }
        context.deleteDatabase(name)
    }

    @Test fun unknownOldSchemaFailsWithoutDestroyingJournal() {
        oldDatabase(2)
        val database = open()
        try {
            database.openHelper.writableDatabase
            fail("Unknown schema must not be destructively migrated")
        } catch (_: IllegalStateException) { }
        finally { database.close() }
        SQLiteDatabase.openDatabase(context.getDatabasePath(name).path, null, SQLiteDatabase.OPEN_READONLY).use { sql ->
            sql.rawQuery("SELECT content FROM journal_entries", null).use { cursor ->
                assertTrue(cursor.moveToFirst())
                assertEquals("Preserve me", cursor.getString(0))
            }
        }
        context.deleteDatabase(name)
    }
}
