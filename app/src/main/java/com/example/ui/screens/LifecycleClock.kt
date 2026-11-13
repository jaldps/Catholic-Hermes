package com.example.ui.screens

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay
import java.time.ZonedDateTime

/** Refreshes minute/hour/day boundaries and immediately after returning to the app. */
@Composable
internal fun rememberLifecycleClock(now: () -> ZonedDateTime = { ZonedDateTime.now() }): ZonedDateTime {
    var value by remember { mutableStateOf(now()) }
    val latestNow by rememberUpdatedState(now)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event -> if (event == Lifecycle.Event.ON_RESUME) value = latestNow() }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    if (!LocalInspectionMode.current) LaunchedEffect(lifecycle) {
        while (true) {
            delay(60_000L - (System.currentTimeMillis() % 60_000L))
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) value = latestNow()
        }
    }
    return value
}
