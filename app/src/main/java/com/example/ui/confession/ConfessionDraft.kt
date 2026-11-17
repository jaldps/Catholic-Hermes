package com.example.ui.confession

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.Saver

/** A session-only draft. Never put question ids or notes into Android's saved-state Bundle. */
internal class ConfessionDraft {
    var selected by mutableStateOf<Set<String>>(emptySet())
        private set
    var notes by mutableStateOf("")
    fun toggle(id: String) { selected = if (id in selected) selected - id else selected + id }
    fun clear() { selected = emptySet(); notes = "" }
    companion object {
        // rememberSaveable participates in the host's state lifecycle, but restores an EMPTY draft.
        // A constant marker is the only saved value, even if the screen is retained in the back stack.
        val Saver = Saver<ConfessionDraft, Boolean>(save = { false }, restore = { ConfessionDraft() })
    }
}
