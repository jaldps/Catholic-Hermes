package com.example

import android.content.Context
import android.content.ContextWrapper
import androidx.datastore.preferences.core.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.backup.*
import com.example.data.local.*
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.example.data.models.AppLanguage
import com.example.data.reminders.ReminderStore
import com.example.data.temptation.TemptationStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class BackupCoreTest {
    @get:Rule val folder = TemporaryFolder()
    private val fixtures = mutableListOf<Device>()
    private val password = "Una contraseña única 42"
    private class DeviceContext(base: Context, private val prefix: String): ContextWrapper(base) {
        override fun getApplicationContext(): Context = this
        override fun getSharedPreferences(name: String, mode: Int) = super.getSharedPreferences("$prefix.$name",mode)
    }
    private inner class Device(name: String) {
        val context = DeviceContext(ApplicationProvider.getApplicationContext(),name)
        val directory = folder.newFolder(name)
        val job = SupervisorJob()
        private fun data(name: String) = PreferenceDataStoreFactory.create(scope=CoroutineScope(Dispatchers.IO+job)) { File(directory,"$name.preferences_pb") }
        val settingsData = data("settings")
        val store = UserPreferencesStore(context,settingsData)
        val temptation = TemptationStore(context,data("temptation"))
        val reminders = ReminderStore(context,data("reminders"))
        val prefs = BackupPreferences(context,store,temptation,reminders)
        val db = Room.inMemoryDatabaseBuilder(context,SanctaFidesDatabase::class.java).allowMainThreadQueries().build()
        val dao get() = db.backupDao()
        val journal = File(directory,"restore.json")
        val service = BackupService(context,db,prefs,journal)
        suspend fun seed(label: String) {
            dao.putJournalEntries(listOf(JournalEntryEntity(7,"Private title","Intention","Private diary $label",100,false,"Answer $label")))
            dao.putNovenaProgress(listOf(NovenaProgressEntity("mercy",3,"Intention $label",100,200,false)))
            dao.putNovenaDays(listOf(NovenaDayCompletionEntity("mercy",1,100),NovenaDayCompletionEntity("mercy",2,200),NovenaDayCompletionEntity("mercy",54,300)))
            dao.putMemorizedVerses(listOf(MemorizedVerseEntity("joh-3-16","John 3:16","Sample personal memorized text",true,300)))
            dao.putPrayerStats(listOf(UserPrayerStatsEntity(1,if(label=="source") 4 else 9,3,2,5,1)))
            dao.putHabits(listOf(SpiritualHabitEntity("habit","Prayer","Prayer","Oração","A daily practice","PRAYER","Morning","Favorite",false,1,2,"2026-11-17",3)))
            dao.putHabitLogs(listOf(HabitCompletionLogEntity(5,"habit","2026-11-17",300)))
            dao.putSinLogs(listOf(SinOvercomeLogEntity(4,"ANGER","PATIENCE","HAIL_MARY",300,"Private note $label")))
            dao.putStudyProgress(listOf(ScriptureStudyProgressEntity("lesson","track",true,123,"Study notes $label")))
            store.setLanguage(AppLanguage.PT);store.setDesignLanguage("SANCTUARY");store.setFontFamilyChoice("EB_GARAMOND")
            store.setFontSizeScale(1.3f);store.setReduceTransparency(true);store.setNarrationVoice("pt","leader","chosen-voice")
            store.recordTrackProgress(12)
            temptation.recordFall(LocalDate.of(2026,11,17));temptation.recordVictory(LocalDate.of(2026,11,17))
            reminders.setEnabled("coronilla",false);reminders.setTime("coronilla",16,30);reminders.setCalendarEvent("coronilla",77)
            context.getSharedPreferences("reading_position",0).edit().putString("book_id","joh").putInt("chapter",3).commit()
        }
    }
    private fun device(name: String) = Device(name).also { fixtures+=it }
    @After fun release() = runBlocking {
        PersonalDataCommands.awaitIdle()
        fixtures.forEach { it.job.cancelAndJoin();it.db.close() }
    }
    private fun stable(data: BackupData) = data.copy(createdAt=0)
    private suspend fun expect(reason: BackupError, block: suspend () -> Unit) {
        try { block();fail("Expected ${reason.name}") } catch (e: BackupException) { assertEquals(reason,e.reason) }
    }

    @Test fun everyTableAndPortablePreferenceRoundTripsWithoutCachesOrSecrets() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source")
        target.db.dao().insertLiturgicalDay(LiturgicalDayEntity("2026-11-17","Keep cache","Ordinary","Ferial",0,"A","II"))
        target.dao.mark(PersonalDataMigrationEntity("keep-local-marker"))
        target.reminders.setCalendarEvent("coronilla",99)
        val chars=password.toCharArray();val bytes=source.service.export(chars)
        assertTrue(chars.all { it=='\u0000' })
        assertFalse(String(bytes,Charsets.ISO_8859_1).contains("Private diary"))
        val opened=target.service.preview(bytes,password.toCharArray())
        assertEquals(1,opened.summary.journalEntries);assertEquals(1,opened.summary.studyNotes)
        assertEquals(3,opened.summary.novenaDays)
        assertTrue(target.dao.journalEntries().isEmpty()) // Preview is read-only.
        target.service.apply(opened,RestoreMode.REPLACE)
        val expected=source.service.snapshot();val actual=target.service.snapshot()
        assertEquals(stable(expected),stable(actual))
        assertEquals(5,target.db.openHelper.readableDatabase.version)
        assertEquals("Keep cache",target.db.dao().getLiturgicalDay("2026-11-17").first()!!.celebrationTitle)
        assertTrue(target.dao.committed("keep-local-marker"))
        assertEquals(99L,target.reminders.calendarEvents.first()["coronilla"])
        val prefs=target.store.preferences.first()
        assertEquals(AppLanguage.PT,prefs.language);assertEquals("SANCTUARY",prefs.designLanguage)
        assertEquals("EB_GARAMOND",prefs.fontFamilyChoice);assertEquals(1.3f,prefs.fontSizeScale,0f)
        assertEquals("chosen-voice",prefs.narrationVoices["pt.leader"])
        assertTrue(prefs.reduceTransparency);assertEquals(setOf(12),target.store.visitedQuestions.first())
        assertEquals(1,target.temptation.fellCounts.first()["2026-11-17"])
        assertFalse(target.journal.exists())
    }

    @Test fun wrongPasswordAndDamagedFileNeverChangeData() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source");target.seed("target")
        val original=stable(target.service.snapshot());val file=source.service.export(password.toCharArray())
        expect(BackupError.WRONG_PASSWORD_OR_DAMAGED) { target.service.preview(file,"wrong password".toCharArray()) }
        expect(BackupError.WRONG_PASSWORD_OR_DAMAGED) { target.service.preview(file.copyOf().also { it[it.lastIndex]=(it.last().toInt() xor 1).toByte() },password.toCharArray()) }
        expect(BackupError.INVALID_FILE) { target.service.preview(file.copyOf(12),password.toCharArray()) }
        assertEquals(original,stable(target.service.snapshot()));assertFalse(target.journal.exists())
    }

    @Test fun replacingRemovesOldRowsAndRestoresNotesAndPreferences() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source");target.seed("target")
        target.dao.putJournalEntries(listOf(JournalEntryEntity(99,"Old","Grace","Remove me",999)))
        target.store.setLanguage(AppLanguage.EN);target.store.setFontFamilyChoice("ROBOTO")
        val prepared=target.service.preview(source.service.export(password.toCharArray()),password.toCharArray())
        target.service.apply(prepared,RestoreMode.REPLACE)
        assertEquals(stable(source.service.snapshot()),stable(target.service.snapshot()))
        assertTrue(target.dao.committed("novena-days-v1"))
    }

    @Test fun replaceClearsPortablePreferenceGroupsAbsentFromTheFile() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source");target.seed("target")
        val data=source.service.snapshot().let { d -> d.copy(preferences=d.preferences.filter { it.name=="settings" }) }
        val prepared=target.service.preview(BackupCodec().encrypt(data,password.toCharArray()),password.toCharArray())
        target.service.apply(prepared,RestoreMode.REPLACE)
        assertTrue(target.temptation.fellCounts.first().isEmpty())
        assertFalse(target.context.getSharedPreferences("reading_position",0).contains("book_id"))
        assertEquals(AppLanguage.PT,target.store.preferences.first().language)
        assertEquals(77L,target.reminders.calendarEvents.first()["coronilla"]) // This phone's calendar ids remain local.
    }

    @Test fun combiningCollidingIdsKeepsBothNotesAndIsIdempotent() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source");target.seed("target")
        target.store.recordTrackProgress(54)
        target.dao.putStudyProgress(listOf(ScriptureStudyProgressEntity("lesson","track",true,99,"Existing note")))
        val prepared=target.service.preview(source.service.export(password.toCharArray()),password.toCharArray())
        target.service.apply(prepared,RestoreMode.MERGE)
        val once=stable(target.service.snapshot())
        target.service.apply(prepared,RestoreMode.MERGE)
        val twice=stable(target.service.snapshot())
        assertEquals(once,twice)
        assertEquals(2,twice.journalEntries.size);assertEquals(2,twice.sinLogs.size)
        assertEquals(1,twice.habitLogs.size);assertEquals(3,twice.novenaDays.size)
        assertEquals(9,twice.prayerStats.single().rosariesPrayed)
        val notes=twice.studyProgress.single();assertEquals(99,notes.completedTimestamp)
        assertTrue(notes.personalNotes.contains("Existing note"));assertTrue(notes.personalNotes.contains("Study notes source"))
        assertEquals(setOf(12,54),target.store.visitedQuestions.first())
        assertTrue(twice.novenaProgress.single().personalIntention.contains("Intention target"))
        assertTrue(twice.novenaProgress.single().personalIntention.contains("Intention source"))
    }

    @Test fun collidingCustomHabitsAreRemappedTogetherWithTheirLogsOnlyOnce() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source");target.seed("target")
        source.dao.putHabits(source.dao.habits().map { it.copy(isCustom=true,titleEs="Source custom") })
        target.dao.putHabits(target.dao.habits().map { it.copy(isCustom=true,titleEs="Target custom") })
        val prepared=target.service.preview(source.service.export(password.toCharArray()),password.toCharArray())
        repeat(2) { target.service.apply(prepared,RestoreMode.MERGE) }
        assertEquals(2,target.dao.habits().size);assertEquals(2,target.dao.habitLogs().size)
        assertTrue(target.dao.habitLogs().all { log -> target.dao.habits().any { it.id==log.habitId } })
    }

    @Test fun preferenceFailureRollsBackRoomAndAllPreviouslyWrittenPreferences() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source");target.seed("target")
        target.store.setLanguage(AppLanguage.EN)
        val before=stable(target.service.snapshot())
        var failOnce=true
        val failing=object: BackupPreferenceStorage {
            override suspend fun snapshot()=target.prefs.snapshot()
            override suspend fun write(groups: List<BackupPreferenceGroup>) {
                target.prefs.write(groups)
                if(failOnce) { failOnce=false;throw IOException("Simulated storage failure after preference write") }
            }
        }
        val service=BackupService(target.context,target.db,failing,target.journal)
        val prepared=service.preview(source.service.export(password.toCharArray()),password.toCharArray())
        expect(BackupError.STORAGE) { service.apply(prepared,RestoreMode.REPLACE) }
        assertEquals(before,stable(target.service.snapshot()));assertFalse(target.journal.exists())
    }

    @Test fun interruptedCrossStoreCommitRecoversOldPreferencesUnlessRoomCommitted() = runBlocking {
        val target=device("target");target.seed("target")
        val old=target.prefs.snapshot()
        val adapter=Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RestoreJournal::class.java)
        val marker="backup-restore:fixture"
        target.store.setLanguage(AppLanguage.EN)
        target.journal.writeText(adapter.toJson(RestoreJournal(marker,old)))
        target.service.recoverPendingRestore()
        assertEquals(AppLanguage.PT,target.store.preferences.first().language)
        assertFalse(target.journal.exists())
        target.store.setLanguage(AppLanguage.EN)
        target.dao.mark(PersonalDataMigrationEntity(marker))
        target.journal.writeText(adapter.toJson(RestoreJournal(marker,old)))
        target.service.recoverPendingRestore()
        assertEquals(AppLanguage.EN,target.store.preferences.first().language)
        assertFalse(target.journal.exists())
    }

    @Test fun legacyBitmaskAndDaysBeyond31RemainPortableWithoutExportingMigrationTables() = runBlocking {
        val source=device("source");val target=device("target")
        source.dao.putNovenaProgress(listOf(NovenaProgressEntity("54-day",1,"",0,0,false)))
        source.context.getSharedPreferences("novena_extra_days",0).edit().putString("54-day","32,54").commit()
        val prepared=target.service.preview(source.service.export(password.toCharArray()),password.toCharArray())
        assertEquals(1,prepared.summary.activeNovenas)
        target.service.apply(prepared,RestoreMode.REPLACE)
        assertEquals(setOf(1,32,54),target.dao.novenaDays().map { it.dayNumber }.toSet())
        assertTrue(target.dao.novenaDays().all { it.completedAt==0L })
    }

    @Test fun formatAndWorkFactorAreBoundedBeforeKeyDerivationAndMetadataIsAuthenticated() = runBlocking {
        val source=device("source");source.seed("source")
        val file=source.service.export(password.toCharArray())
        val version=file.copyOf();ByteBuffer.wrap(version).putInt(8,2)
        expect(BackupError.UNSUPPORTED_VERSION) { source.service.preview(version,password.toCharArray()) }
        val abusive=file.copyOf();ByteBuffer.wrap(abusive).putInt(12,Int.MAX_VALUE)
        expect(BackupError.INVALID_FILE) { source.service.preview(abusive,password.toCharArray()) }
        val changed=file.copyOf();ByteBuffer.wrap(changed).putInt(12,310_001)
        expect(BackupError.WRONG_PASSWORD_OR_DAMAGED) { source.service.preview(changed,password.toCharArray()) }
        val data=source.service.snapshot();val codec=BackupCodec()
        val a=codec.encrypt(data,password.toCharArray());val b=codec.encrypt(data,password.toCharArray())
        assertFalse(a.copyOfRange(16,44).contentEquals(b.copyOfRange(16,44)))
    }

    @Test fun portablePbkdf2MatchesPublishedVectorAndJcaWithUnicodePasswords() {
        fun hex(a:ByteArray)=a.joinToString("") { "%02x".format(it) }
        assertEquals("120fb6cffcf8b32c43e7225256c4f837a86548c92ccc35480805987cb70be17b",hex(BackupCodec.derive("password".toCharArray(),"salt".toByteArray(),1,true)))
        val chars="niño · oração · שלום".toCharArray();val salt=ByteArray(16) { it.toByte() }
        assertArrayEquals(BackupCodec.derive(chars,salt,1000),BackupCodec.derive(chars,salt,1000,true))
    }
    @Test fun answeredJournalUpdatesMergeIntoTheSameNaturalEntryAndMalformedPreferenceTypesAreRejected() = runBlocking {
        val source=device("source");val target=device("target");source.seed("source")
        val original=source.dao.journalEntries().single()
        target.dao.putJournalEntries(listOf(original.copy(id=42,isAnswered=true,answeredNotes="A received answer")))
        val opened=target.service.preview(source.service.export(password.toCharArray()),password.toCharArray())
        target.service.apply(opened,RestoreMode.MERGE)
        target.service.apply(opened,RestoreMode.MERGE)
        val kept=target.dao.journalEntries().single()
        assertEquals(42L,kept.id);assertTrue(kept.isAnswered);assertTrue(kept.answeredNotes.contains("A received answer"));assertTrue(kept.answeredNotes.contains(original.answeredNotes))
        val invalid=source.service.snapshot().let { data -> data.copy(preferences=data.preferences.map { group ->
            if(group.name!="settings")group else group.copy(entries=group.entries.filter { it.key!="language" }+BackupPreference("language","INT","42"))
        }) }
        expect(BackupError.INVALID_FILE) { BackupCodec().encrypt(invalid,password.toCharArray()) }
        assertEquals(kept,target.dao.journalEntries().single())
    }

}
