package com.example

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.*
import com.example.data.persistence.PersonalDataCommands
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk=[34])
class AtomicPersonalProgressTest {
    private fun database()=Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext<Application>(),SanctaFidesDatabase::class.java).allowMainThreadQueries().build()

    @Test fun importPreservesIntentionDatesAndLongDaysAndResetDoesNotResurrectThem()=runBlocking {
        val db=database()
        try {
            val dao=db.dao()
            dao.saveNovenaProgress(NovenaProgressEntity("long",5,"Private intention",123,456,false))
            dao.importLegacyNovenaDays(mapOf("long" to setOf(32,54,275)))
            assertEquals(setOf(1,3,32,54,275),dao.novenaDaysOnce("long").map{it.dayNumber}.toSet())
            assertEquals("Private intention",dao.getNovenaProgress("long")!!.personalIntention)
            assertEquals(123L,dao.getNovenaProgress("long")!!.startDate)
            assertEquals(456L,dao.getNovenaProgress("long")!!.lastUpdated)
            assertTrue(dao.novenaDaysOnce("long").all {it.completedAt==0L})
            assertTrue(dao.novenaDaySnapshot().first().all {it.migrationKey=="novena-days-v1"})
            dao.resetNovenaDays("long")
            dao.importLegacyNovenaDays(mapOf("long" to setOf(32,54,275)))
            assertTrue(dao.novenaDaysOnce("long").isEmpty())
            assertEquals("Private intention",dao.getNovenaProgress("long")!!.personalIntention)
            assertEquals(0L,dao.getNovenaProgress("long")!!.startDate)
        }finally{db.close()}
    }
    @Test fun idempotentDayWritesKeepAllDaysAndCompletionMetadataInOneTransaction()=runBlocking {
        val db=database()
        try {
            val dao=db.dao();dao.importLegacyNovenaDays(emptyMap())
            val required=(1..54).toSet()
            for(day in required) PersonalDataCommands.submit("novena-long") {dao.setNovenaDay("long",day,true,required)}
            PersonalDataCommands.submit("novena-long") {dao.setNovenaDay("long",54,true,required)}
            PersonalDataCommands.awaitIdle()
            assertEquals(54,dao.novenaDaysOnce("long").size)
            assertTrue(dao.getNovenaProgress("long")!!.isCompleted)
            val timestamp=dao.novenaDaysOnce("long").first {it.dayNumber==54}.completedAt
            dao.setNovenaDay("long",54,true,required)
            assertEquals(timestamp,dao.novenaDaysOnce("long").first {it.dayNumber==54}.completedAt)
            dao.setNovenaDay("long",32,false,required)
            assertFalse(dao.getNovenaProgress("long")!!.isCompleted)
            assertEquals(53,dao.novenaDaysOnce("long").size)
        }finally{PersonalDataCommands.awaitIdle();db.close()}
    }
    @Test fun concurrentHabitRequestsCannotDuplicateAHabitDate()=runBlocking {
        val db=database()
        try {
            val dao=db.dao()
            coroutineScope {repeat(40){launch(Dispatchers.IO){dao.setHabitCompletion("habit","2026-11-13",true)}}}
            assertEquals(1,dao.getAllCompletions().first().size)
            dao.setHabitCompletion("habit","2026-11-14",true)
            dao.setHabitCompletion("habit","2026-11-13",false)
            assertEquals("2026-11-14",dao.getAllCompletions().first().single().completionDate)
        }finally{db.close()}
    }
    @Test fun failedCommandCanBeRetriedWithoutCancellingTheQueue()=runBlocking {
        var attempts=0
        PersonalDataCommands.submit("retry-test") {attempts++;if(attempts==1)throw java.io.IOException("injected")}
        PersonalDataCommands.awaitIdle()
        assertEquals(PersonalDataCommands.Status.ERROR,PersonalDataCommands.states.value["retry-test"]!!.status)
        assertTrue(PersonalDataCommands.retry("retry-test"))
        PersonalDataCommands.awaitIdle()
        assertEquals(2,attempts)
        assertEquals(PersonalDataCommands.Status.SAVED,PersonalDataCommands.states.value["retry-test"]!!.status)
    }
}
