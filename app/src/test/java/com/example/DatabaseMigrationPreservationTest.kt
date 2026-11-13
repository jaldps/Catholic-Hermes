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
        .addMigrations(SanctaFidesDatabase.MIGRATION_3_4).allowMainThreadQueries().build()

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
