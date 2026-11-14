package com.example.data.repository

import com.example.data.persistence.PersonalDataCommands
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Serializes edition validation/persistence and keeps old stored emissions from undoing a newer choice. */
internal class BibleEditionSelection(
    private val validate: (String) -> String,
    private val persist: suspend (String) -> Unit
) {
    private val lock = Any()
    private var revision = 0L
    private var pending: String? = null
    private val state = MutableStateFlow("bundled")
    val selected = state.asStateFlow()

    fun request(id: String) = enqueue(id, verified = null)

    /** For existing non-UI callers that explicitly need immediate validation. */
    fun selectBlocking(id: String) {
        val verified = if (id == "bundled") id else validate(id)
        enqueue(verified, verified)
    }

    private fun enqueue(id: String, verified: String?) {
        val ticket = synchronized(lock) {
            pending = id
            if (id == "bundled" || verified != null) state.value = verified ?: id
            ++revision
        }
        PersonalDataCommands.submit("bible-edition") {
            if (!isCurrent(ticket)) return@submit
            val choice = verified ?: if (id == "bundled") id else validate(id)
            if (!isCurrent(ticket)) return@submit
            persist(choice)
            synchronized(lock) {
                if (revision == ticket) {
                    state.value = choice
                    pending = null
                }
            }
        }
    }

    /** Called by the repository's IO preference collector, including on initial restore. */
    fun acceptStored(id: String) {
        val ticket = synchronized(lock) {
            if (pending != null && pending != id) return
            revision
        }
        val verified = if (id == "bundled") id else validate(id)
        synchronized(lock) {
            if (revision == ticket && (pending == null || pending == id)) state.value = verified
        }
    }

    private fun isCurrent(ticket: Long) = synchronized(lock) { revision == ticket }
}
