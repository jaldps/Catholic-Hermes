package com.example.data.audio

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Build
import android.os.IBinder
import com.example.MainActivity
import com.example.data.models.AppLanguage

/**
 * Foreground media-playback service that keeps spoken recitations alive with
 * the screen off and gives lock-screen / notification controls.
 *
 * It owns no speech: [LiturgicalTtsManager] starts it when a recitation
 * begins, pushes state with [ACTION_UPDATE] and stops it when narration
 * ends. The notification buttons call back into the manager (the
 * framework [MediaSession] is used so no extra library is needed).
 */
class NarrationService : Service() {
    private var session: MediaSession? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        session = runCatching {
            MediaSession(this, "CatholicGPT narration").apply {
                setCallback(object : MediaSession.Callback() {
                    override fun onPlay() { LiturgicalTtsManager.active?.resume() }
                    override fun onPause() { LiturgicalTtsManager.active?.pauseNarration() }
                    override fun onStop() { LiturgicalTtsManager.active?.stop() }
                })
                isActive = true
            }
        }.getOrNull()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val manager = LiturgicalTtsManager.active
        when (intent?.action) {
            ACTION_TOGGLE -> manager?.let { if (it.interrupted.value) it.resume() else it.pauseNarration() }
            ACTION_STOP -> { manager?.stop(); stopSelf(); return START_NOT_STICKY }
        }
        val state = manager?.narrationState()
        if (state == null) {
            // Stale start (process was recreated): satisfy the foreground
            // contract, then leave.
            enterForeground(build(NarrationState("", "", paused = false, AppLanguage.ES)))
            stopSelf()
            return START_NOT_STICKY
        }
        val notification = build(state)
        enterForeground(notification)
        if (state.paused) {
            // Paused: let the user swipe it away, the system may reclaim us.
            if (Build.VERSION.SDK_INT >= 24) stopForeground(STOP_FOREGROUND_DETACH) else @Suppress("DEPRECATION") stopForeground(false)
            getSystemService(NotificationManager::class.java)?.notify(NOTIFICATION_ID, notification)
        }
        session?.setPlaybackState(
            PlaybackState.Builder()
                .setActions(PlaybackState.ACTION_PLAY or PlaybackState.ACTION_PAUSE or PlaybackState.ACTION_PLAY_PAUSE or PlaybackState.ACTION_STOP)
                .setState(if (state.paused) PlaybackState.STATE_PAUSED else PlaybackState.STATE_PLAYING, PlaybackState.PLAYBACK_POSITION_UNKNOWN, 1f)
                .build()
        )
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        session?.run { isActive = false; release() }
        session = null
        super.onDestroy()
    }

    private fun enterForeground(n: Notification) {
        if (Build.VERSION.SDK_INT >= 29) startForeground(NOTIFICATION_ID, n, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        else startForeground(NOTIFICATION_ID, n)
    }

    private fun build(state: NarrationState): Notification {
        ensureChannel()
        val l = state.language
        fun label(es: String, en: String, pt: String) = when (l) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }
        val toggle = if (state.paused) label("Continuar", "Continue", "Continuar") else label("Pausar", "Pause", "Pausar")
        val builder = if (Build.VERSION.SDK_INT >= 26) Notification.Builder(this, CHANNEL_ID)
        else @Suppress("DEPRECATION") Notification.Builder(this)
        val open = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        fun action(name: String, title: String, icon: Int, code: Int): Notification.Action {
            val pi = PendingIntent.getService(
                this, code, Intent(this, NarrationService::class.java).setAction(name),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            return Notification.Action.Builder(android.graphics.drawable.Icon.createWithResource(this, icon), title, pi).build()
        }
        builder
            .setSmallIcon(android.R.drawable.ic_lock_silent_mode_off)
            .setContentTitle(state.title.ifBlank { label("Narración", "Narration", "Narração") })
            .setContentText(state.text)
            .setContentIntent(open)
            .setOngoing(!state.paused)
            .setOnlyAlertOnce(true)
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .addAction(action(ACTION_TOGGLE, toggle, if (state.paused) android.R.drawable.ic_media_play else android.R.drawable.ic_media_pause, 1))
            .addAction(action(ACTION_STOP, label("Detener", "Stop", "Parar"), android.R.drawable.ic_menu_close_clear_cancel, 2))
        val style = Notification.MediaStyle().setShowActionsInCompactView(0, 1)
        session?.sessionToken?.let { style.setMediaSession(it) }
        builder.setStyle(style)
        return builder.build()
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT < 26) return
        val nm = getSystemService(NotificationManager::class.java) ?: return
        if (nm.getNotificationChannel(CHANNEL_ID) == null) {
            nm.createNotificationChannel(
                NotificationChannel(CHANNEL_ID, "Narración / Narration", NotificationManager.IMPORTANCE_LOW)
                    .apply { setShowBadge(false) }
            )
        }
    }

    companion object {
        const val ACTION_UPDATE = "com.example.narration.UPDATE"
        const val ACTION_TOGGLE = "com.example.narration.TOGGLE"
        const val ACTION_STOP = "com.example.narration.STOP"
        const val CHANNEL_ID = "narration_playback"
        const val NOTIFICATION_ID = 7401

        internal fun intent(context: Context, action: String = ACTION_UPDATE) =
            Intent(context, NarrationService::class.java).setAction(action)
    }
}

/** What the notification shows: current prayer title, a text snippet, whether paused. */
internal data class NarrationState(val title: String, val text: String, val paused: Boolean, val language: AppLanguage)
