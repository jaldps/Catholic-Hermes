package com.example.ui.components

import androidx.compose.runtime.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal data class IoContent<T>(val data: T?, val loading: Boolean, val failed: Boolean, val retry: () -> Unit)
private data class IoSnapshot<T>(val key: Any?, val revision: Any?, val data: T?, val failed: Boolean)

/** A read belongs to its captured key; an old edition/language cannot publish into a new reader. */
@Composable
internal fun <T> rememberIoContent(key: Any?, revision: Any? = Unit, retainOnReload: Boolean = false, read: suspend () -> T): IoContent<T> {
    var attempt by remember(key) { mutableIntStateOf(0) }
    val request = revision to attempt
    val snapshot by produceState<IoSnapshot<T>?>(null, key, request) {
        val previous = value?.takeIf { it.key == key }?.data
        value = try {
            IoSnapshot(key, request, withContext(Dispatchers.IO) { read() }, false)
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            IoSnapshot(key, request, previous.takeIf { retainOnReload }, true)
        }
    }
    val matching = snapshot?.takeIf { it.key == key }
    val current = matching?.revision == request
    return IoContent(
        data = matching?.data?.takeIf { current || retainOnReload },
        loading = !current,
        failed = current && matching?.failed == true,
        retry = { attempt++ }
    )
}
