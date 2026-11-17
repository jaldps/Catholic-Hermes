package com.example.data.backup

import android.content.Context
import android.util.AtomicFile
import androidx.room.withTransaction
import com.example.data.local.*
import com.example.data.persistence.PersonalDataCommands
import com.example.data.prefs.UserPreferencesStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.util.UUID

interface BackupPreferenceStorage {
    suspend fun snapshot(): List<BackupPreferenceGroup>
    suspend fun write(groups: List<BackupPreferenceGroup>)
}

internal data class RestoreJournal(val marker: String, val preferences: List<BackupPreferenceGroup>)

/** Room + DataStore restore with a private recovery journal for the cross-store commit boundary. */
class BackupService(
    context: Context,
    private val database: SanctaFidesDatabase,
    private val preferences: BackupPreferenceStorage,
    journalFile: File = File(context.noBackupFilesDir,"pending-personal-restore.json"),
    private val codec: BackupCodec = BackupCodec()
) {
    companion object {
        fun create(context: Context, store: UserPreferencesStore) = BackupService(context,
            SanctaFidesDatabase.getInstance(context), BackupPreferences(context,store))
    }
    private val app = context.applicationContext
    private val dao get() = database.backupDao()
    private val journal = AtomicFile(journalFile)
    private val journalAdapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RestoreJournal::class.java).failOnUnknown()

    /** Errors propagate without retaining the decrypted payload in PersonalDataCommands' retry registry. */
    private suspend fun <T> coordinated(action: suspend () -> T): T {
        val result = CompletableDeferred<T>()
        PersonalDataCommands.submit("personal-backup") {
            try { result.complete(action()) } catch (e: Throwable) { result.completeExceptionally(e) }
        }
        return result.await()
    }

    private suspend fun rows(prefs: List<BackupPreferenceGroup>): BackupData = BackupData(1,
        database.openHelper.readableDatabase.version,System.currentTimeMillis(),dao.journalEntries(),dao.novenaProgress(),
        dao.novenaDays(),dao.memorizedVerses(),dao.prayerStats(),dao.habits(),dao.habitLogs(),dao.sinLogs(),dao.studyProgress(),prefs)

    /** Include read-only legacy days even if this phone has not opened Novenas since migration. */
    private suspend fun portableRows(prefs: List<BackupPreferenceGroup>): BackupData {
        var data = rows(prefs)
        if (!dao.committed("novena-days-v1")) {
            val extras = app.getSharedPreferences("novena_extra_days",Context.MODE_PRIVATE).all.mapNotNull { (id,v) ->
                val days = (v as? String).orEmpty().split(',').mapNotNull { it.trim().toIntOrNull()?.takeIf { n -> n in 1..10_000 } }
                if (days.isEmpty()) null else id to days
            }.toMap()
            val known = data.novenaDays.mapTo(hashSetOf()) { it.novenaId to it.dayNumber }
            val extraDays = extras.flatMap { (id,ds) -> ds.filter { known.add(id to it) }.map { NovenaDayCompletionEntity(id,it,0) } }
            val progressIds = data.novenaProgress.mapTo(hashSetOf()) { it.novenaId }
            data = data.copy(novenaDays=data.novenaDays+extraDays,novenaProgress=data.novenaProgress+
                extras.keys.filter { it !in progressIds }.map { NovenaProgressEntity(it,0,"",0,0,false) })
        }
        return BackupMerge.canonicalDays(data)
    }

    /** Never silently export defaults after a failed DataStore read. */
    suspend fun snapshot(): BackupData = coordinated {
        recoverInternal()
        database.withTransaction { portableRows(preferences.snapshot()).also(BackupValidation::validate) }
    }

    suspend fun export(password: CharArray): ByteArray = try {
        val data = snapshot()
        withContext(Dispatchers.Default) { codec.encrypt(data,password) }
    } finally { password.fill('\u0000') }

    suspend fun preview(bytes: ByteArray, password: CharArray): PreparedBackup = try {
        withContext(Dispatchers.Default) { codec.decrypt(bytes,password) }
    } finally { password.fill('\u0000') }

    /** Call on startup before exposing writable UI, and before another backup operation. */
    suspend fun recoverPendingRestore() = coordinated { recoverInternal() }

    private fun readJournal(): RestoreJournal? {
        if (!journal.baseFile.exists() && !File(journal.baseFile.path+".bak").exists()) return null
        return journal.openRead().use { input ->
            val bytes = BackupCodec.read(input)
            try { journalAdapter.fromJson(String(bytes,Charsets.UTF_8)) ?: throw IOException("Invalid restore journal") }
            finally { bytes.fill(0) }
        }.also { if (!it.marker.startsWith("backup-restore:")) throw IOException("Invalid restore marker") }
    }

    private suspend fun recoverInternal() {
        val pending = readJournal() ?: return
        if (!dao.committed(pending.marker)) preferences.write(pending.preferences)
        journal.delete()
        // A leftover marker is harmless; keep it if deleting the journal failed.
        if (!journal.baseFile.exists()) dao.unmark(pending.marker)
    }

    private fun prepareJournal(old: List<BackupPreferenceGroup>): String {
        val marker = "backup-restore:"+UUID.randomUUID().toString()
        val bytes = journalAdapter.toJson(RestoreJournal(marker,old)).toByteArray(Charsets.UTF_8)
        if (bytes.size > BackupCodec.MAX_BYTES) throw BackupException(BackupError.TOO_LARGE)
        var stream: java.io.FileOutputStream? = null
        try {
            stream = journal.startWrite()
            stream.write(bytes)
            journal.finishWrite(stream)
        } catch (e: Exception) {
            stream?.let { journal.failWrite(it) }
            throw e
        } finally { bytes.fill(0) }
        return marker
    }

    suspend fun apply(prepared: PreparedBackup, mode: RestoreMode) = coordinated {
        BackupValidation.validate(prepared.data)
        recoverInternal()
        val oldPreferences = preferences.snapshot()
        val marker = prepareJournal(oldPreferences)
        try {
            database.withTransaction {
                val data = if (mode == RestoreMode.MERGE) BackupMerge.combine(portableRows(oldPreferences),prepared.data)
                    else BackupMerge.canonicalDays(prepared.data)
                if (mode == RestoreMode.REPLACE) clearRows()
                writeRows(data)
                // Do not re-import this phone's pre-Room legacy novena preferences after a replace.
                dao.mark(PersonalDataMigrationEntity("novena-days-v1"))
                preferences.write(data.preferences)
                dao.mark(PersonalDataMigrationEntity(marker))
            }
        } catch (e: Exception) {
            try { preferences.write(oldPreferences); journal.delete() }
            catch (rollback: Exception) { e.addSuppressed(rollback) } // The durable journal remains for startup recovery.
            throw BackupException(BackupError.STORAGE,e)
        }
        journal.delete()
        if (!journal.baseFile.exists()) runCatching { dao.unmark(marker) }
    }

    private suspend fun clearRows() {
        dao.clearNovenaDays();dao.clearNovenaProgress();dao.clearHabitLogs();dao.clearHabits()
        dao.clearJournalEntries();dao.clearMemorizedVerses();dao.clearPrayerStats();dao.clearSinLogs();dao.clearStudyProgress()
    }
    private suspend fun writeRows(d: BackupData) {
        dao.putJournalEntries(d.journalEntries);dao.putNovenaProgress(d.novenaProgress);dao.putNovenaDays(d.novenaDays)
        dao.putMemorizedVerses(d.memorizedVerses);dao.putPrayerStats(d.prayerStats);dao.putHabits(d.habits)
        dao.putHabitLogs(d.habitLogs);dao.putSinLogs(d.sinLogs);dao.putStudyProgress(d.studyProgress)
    }
}
