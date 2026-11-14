package com.example.data.persistence

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

/** Application-owned writes survive a composable leaving the screen. Nothing private is logged. */
object PersonalDataCommands {
    enum class Status { SAVING, SAVED, ERROR }
    data class State(val revision: Long, val status: Status)
    private data class Command(val key: String, val revision: Long, val write: suspend () -> Unit)
    private val revisions = AtomicLong()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val queue = Channel<suspend () -> Unit>(Channel.UNLIMITED)
    private val _states = MutableStateFlow<Map<String, State>>(emptyMap())
    val states = _states.asStateFlow()

    init { scope.launch { for (work in queue) work() } }

    fun submit(key: String, write: suspend () -> Unit) {
        val command = Command(key, revisions.incrementAndGet(), write)
        _states.update { it + (key to State(command.revision, Status.SAVING)) }
        check(queue.trySend {
            val status = try { command.write(); Status.SAVED } catch (cancelled: CancellationException) { throw cancelled } catch (_: Exception) { Status.ERROR }
            _states.update { current ->
                if (current[key]?.revision != command.revision) current
                else (current + (key to State(command.revision, status))).entries.toList()
                    .takeLast(128).associate { it.toPair() }
            }
        }.isSuccess)
    }

    /** A barrier for lifecycle-independent writes, also used by persistence tests. */
    suspend fun awaitIdle() {
        val done = CompletableDeferred<Unit>()
        queue.send { done.complete(Unit) }
        done.await()
    }
}
