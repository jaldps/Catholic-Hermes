package com.example

import com.example.data.persistence.PersonalDataCommands
import com.example.data.repository.BibleEditionSelection
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class BibleEditionSelectionTest {
    @Test fun selectionValidationRunsOffTheCallingThread() = runBlocking {
        val caller = Thread.currentThread().id
        var validatorThread = caller
        var saved = ""
        val selection = BibleEditionSelection({ validatorThread = Thread.currentThread().id; it }, { saved = it })
        selection.request("downloaded")
        withTimeout(5_000) { PersonalDataCommands.awaitIdle() }
        assertNotEquals(caller, validatorThread)
        assertEquals("downloaded", saved)
        assertEquals(saved, selection.selected.value)
    }

    @Test fun anOlderPreferenceEmissionCannotUndoANewerIncludedBibleRequest() = runBlocking {
        val entered = CompletableDeferred<Unit>()
        val release = CompletableDeferred<Unit>()
        val saved = mutableListOf<String>()
        lateinit var selection: BibleEditionSelection
        selection = BibleEditionSelection({ it }, { id ->
            if (id == "downloaded") { entered.complete(Unit); release.await() }
            saved += id
            selection.acceptStored(id)
        })
        selection.request("downloaded")
        withTimeout(5_000) { entered.await() }
        selection.request("bundled")
        assertEquals("bundled", selection.selected.value)
        release.complete(Unit)
        withTimeout(5_000) { PersonalDataCommands.awaitIdle() }
        assertEquals(listOf("downloaded", "bundled"), saved)
        assertEquals("bundled", selection.selected.value)
    }

    @Test fun failedSelectionCanRetryTheSameChoiceWithoutPretendingItWasSaved() = runBlocking {
        var fail = true
        val selection = BibleEditionSelection({ it }, { if (fail) throw IOException("injected") })
        selection.request("downloaded")
        PersonalDataCommands.awaitIdle()
        assertEquals("bundled", selection.selected.value)
        assertEquals(PersonalDataCommands.Status.ERROR, PersonalDataCommands.states.value["bible-edition"]?.status)
        fail = false
        assertTrue(PersonalDataCommands.retry("bible-edition"))
        PersonalDataCommands.awaitIdle()
        assertEquals("downloaded", selection.selected.value)
    }
}
