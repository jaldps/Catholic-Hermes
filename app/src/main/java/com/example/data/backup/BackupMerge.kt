package com.example.data.backup

import com.example.data.local.*
import java.security.MessageDigest

/** Conservative/idempotent union. No counter is blindly added to a copy of itself. */
internal object BackupMerge {
    private fun earliest(a: Long, b: Long): Long = listOf(a,b).filter { it > 0 }.minOrNull() ?: 0L
    private fun text(a: String, b: String): String = when {
        b.isBlank() || a == b || a.contains(b) -> a
        a.isBlank() -> b
        b.contains(a) -> b
        else -> "$a\n\n$b"
    }
    private fun <T, K> union(a: List<T>, b: List<T>, key: (T) -> K, merge: (T,T) -> T): List<T> {
        val map = a.associateByTo(linkedMapOf(), key)
        b.forEach { item -> val k = key(item); map[k] = map[k]?.let { merge(it,item) } ?: item }
        return map.values.toList()
    }

    /** Old bitmasks (days 1–31) become portable completion rows; dates remain unknown, never invented. */
    fun canonicalDays(data: BackupData): BackupData {
        val days = data.novenaDays.associateByTo(linkedMapOf()) { it.novenaId to it.dayNumber }
        data.novenaProgress.forEach { progress ->
            (1..31).filter { progress.currentDay and (1 shl (it-1)) != 0 }.forEach { day ->
                days.putIfAbsent(progress.novenaId to day, NovenaDayCompletionEntity(progress.novenaId,day,0L))
            }
        }
        val grouped = days.values.groupBy { it.novenaId }
        val masks = days.values.filter { it.dayNumber <= 31 }.groupBy { it.novenaId }.mapValues { (_,rows) ->
            rows.fold(0) { value,d -> value or (1 shl (d.dayNumber-1)) }
        }
        return data.copy(novenaDays = days.values.toList(), novenaProgress = data.novenaProgress.map { p ->
            val mask = grouped[p.novenaId].orEmpty().filter { it.dayNumber <= 31 }
                .fold(0) { value, d -> value or (1 shl (d.dayNumber-1)) }
            p.copy(currentDay=mask)
        })
    }

    fun combine(local: BackupData, incoming: BackupData): BackupData {
        val a = canonicalDays(local); val original = canonicalDays(incoming)
        // Two independently created custom habits can share a primary key. Keep both, with a stable remap.
        val localHabits = a.habits.associateBy { it.id }
        val remap = original.habits.filter { h ->
            val l = localHabits[h.id]
            l != null && l.isCustom && h.isCustom &&
                listOf(l.titleEs,l.titleEn,l.titlePt,l.description,l.category) != listOf(h.titleEs,h.titleEn,h.titlePt,h.description,h.category)
        }.associate { h ->
            val identity = listOf(h.id,h.titleEs,h.titleEn,h.titlePt,h.description,h.category).joinToString("\u0000")
            h.id to ("backup_" + MessageDigest.getInstance("SHA-256").digest(identity.toByteArray()).joinToString("") { "%02x".format(it) })
        }
        val b = original.copy(habits=original.habits.map { it.copy(id=remap[it.id] ?: it.id) },
            habitLogs=original.habitLogs.map { it.copy(habitId=remap[it.habitId] ?: it.habitId) })
        val journal = union(a.journalEntries,b.journalEntries.map { it.copy(id=0) },
            { it.copy(id=0,isAnswered=false,answeredNotes="") }) { x,y ->
            x.copy(isAnswered=x.isAnswered||y.isAnswered,answeredNotes=text(x.answeredNotes,y.answeredNotes))
        }
        val sinKeys = a.sinLogs.mapTo(hashSetOf()) { it.copy(id=0) }
        val sins = a.sinLogs + b.sinLogs.filter { sinKeys.add(it.copy(id=0)) }.map { it.copy(id=0) }
        val logs = union(a.habitLogs,b.habitLogs.map { it.copy(id=0) },{ it.habitId to it.completionDate }) { x,y -> x.copy(timestamp=earliest(x.timestamp,y.timestamp)) }
        val counts = logs.groupingBy { it.habitId }.eachCount()
        val habits = union(a.habits,b.habits,{it.id}) { x,y ->
            val newer = if (y.lastCompletedDate > x.lastCompletedDate) y else x
            x.copy(currentStreak=newer.currentStreak, bestStreak=maxOf(x.bestStreak,y.bestStreak),
                lastCompletedDate=newer.lastCompletedDate,totalCompletions=maxOf(x.totalCompletions,y.totalCompletions))
        }.map { it.copy(totalCompletions=maxOf(it.totalCompletions,counts[it.id] ?: 0)) }
        return canonicalDays(a.copy(
            journalEntries=journal, sinLogs=sins, habits=habits, habitLogs=logs,
            novenaProgress=union(a.novenaProgress,b.novenaProgress,{it.novenaId}) { x,y -> x.copy(
                currentDay=x.currentDay or y.currentDay, personalIntention=text(x.personalIntention,y.personalIntention),
                startDate=earliest(x.startDate,y.startDate),lastUpdated=maxOf(x.lastUpdated,y.lastUpdated),isCompleted=x.isCompleted||y.isCompleted) },
            novenaDays=union(a.novenaDays,b.novenaDays,{it.novenaId to it.dayNumber}) { x,y -> x.copy(completedAt=earliest(x.completedAt,y.completedAt)) },
            memorizedVerses=union(a.memorizedVerses,b.memorizedVerses,{it.id}) { x,y ->
                (if(y.lastReviewed>x.lastReviewed)y else x).copy(isMastered=x.isMastered||y.isMastered,lastReviewed=maxOf(x.lastReviewed,y.lastReviewed)) },
            prayerStats=union(a.prayerStats,b.prayerStats,{it.id}) { x,y -> x.copy(
                rosariesPrayed=maxOf(x.rosariesPrayed,y.rosariesPrayed),chapletsPrayed=maxOf(x.chapletsPrayed,y.chapletsPrayed),
                novenasCompleted=maxOf(x.novenasCompleted,y.novenasCompleted),triviaScore=maxOf(x.triviaScore,y.triviaScore),triviaStreak=maxOf(x.triviaStreak,y.triviaStreak)) },
            studyProgress=union(a.studyProgress,b.studyProgress,{it.lessonId}) { x,y -> x.copy(
                isCompleted=x.isCompleted||y.isCompleted,completedTimestamp=if(x.isCompleted&&x.completedTimestamp>0)x.completedTimestamp else if(y.isCompleted)y.completedTimestamp else 0,
                personalNotes=text(x.personalNotes,y.personalNotes)) },
            preferences=BackupPreferences.merge(a.preferences,b.preferences)
        ))
    }
}
