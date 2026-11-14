package com.example.ui.components

import android.net.Uri
import android.webkit.WebSettings
import java.util.Locale

/** Network pages stay in HTTPS; local files and executable/custom schemes never load. */
internal object WebNavigationPolicy {
    enum class Destination { EMBEDDED, EXTERNAL, BLOCKED }

    fun destination(url: String, userGesture: Boolean = false, mainFrame: Boolean = true): Destination {
        val uri = runCatching { Uri.parse(url) }.getOrNull() ?: return Destination.BLOCKED
        return when (uri.scheme?.lowercase(Locale.ROOT)) {
            "https" -> if (!uri.host.isNullOrBlank() && uri.userInfo == null && uri.port in listOf(-1,443)) Destination.EMBEDDED else Destination.BLOCKED
            "mailto", "tel", "geo" -> if (userGesture && mainFrame) Destination.EXTERNAL else Destination.BLOCKED
            else -> Destination.BLOCKED
        }
    }

    fun javascriptAllowed(url: String): Boolean {
        if (destination(url) != Destination.EMBEDDED) return false
        val uri = Uri.parse(url)
        val host = uri.host.orEmpty().lowercase(Locale.ROOT)
        return host == "maps.google.com" || (host == "www.google.com" && uri.path.orEmpty().startsWith("/maps"))
    }

    fun configure(settings: WebSettings, url: String) {
        settings.allowFileAccess = false
        settings.allowContentAccess = false
        @Suppress("DEPRECATION")
        settings.allowFileAccessFromFileURLs = false
        @Suppress("DEPRECATION")
        settings.allowUniversalAccessFromFileURLs = false
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
        settings.javaScriptCanOpenWindowsAutomatically = false
        settings.setSupportMultipleWindows(false)
        settings.mediaPlaybackRequiresUserGesture = true
        settings.javaScriptEnabled = javascriptAllowed(url)
        settings.domStorageEnabled = javascriptAllowed(url)
    }
}
