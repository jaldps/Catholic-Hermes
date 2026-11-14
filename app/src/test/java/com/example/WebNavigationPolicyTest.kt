package com.example

import android.app.Application
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.test.core.app.ApplicationProvider
import com.example.ui.components.WebNavigationPolicy
import com.example.ui.components.WebNavigationPolicy.Destination.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class WebNavigationPolicyTest {
    @Test fun localAndExecutableSchemesNeverLoadEvenWithAGesture() {
        listOf("file:///private.txt", "content://private/file", "javascript:alert(1)", "data:text/html,test", "intent://test", "http://example.org").forEach {
            assertEquals(it, BLOCKED, WebNavigationPolicy.destination(it, true))
        }
        assertEquals(BLOCKED, WebNavigationPolicy.destination("https://user:password@example.org"))
        assertEquals(EMBEDDED, WebNavigationPolicy.destination("https://www.vatican.va/archive/"))
    }
    @Test fun externalActionsRequireATopLevelUserGesture() {
        for (uri in listOf("tel:+1234", "mailto:parish@example.org", "geo:0,0?q=church")) {
            assertEquals(BLOCKED, WebNavigationPolicy.destination(uri))
            assertEquals(BLOCKED, WebNavigationPolicy.destination(uri, true, false))
            assertEquals(EXTERNAL, WebNavigationPolicy.destination(uri, true, true))
        }
    }
    @Test fun scriptsAreLimitedToTheSupportedMapOriginAndRevokedAfterNavigation() {
        val view = WebView(ApplicationProvider.getApplicationContext<Application>())
        WebNavigationPolicy.configure(view.settings, "https://www.google.com/maps/search/church")
        assertTrue(view.settings.javaScriptEnabled)
        WebNavigationPolicy.configure(view.settings, "https://www.vatican.va/archive/")
        assertFalse(view.settings.javaScriptEnabled)
        assertFalse(view.settings.domStorageEnabled)
        assertFalse(view.settings.allowFileAccess)
        assertFalse(view.settings.allowContentAccess)
        assertEquals(WebSettings.MIXED_CONTENT_NEVER_ALLOW, view.settings.mixedContentMode)
        assertFalse(WebNavigationPolicy.javascriptAllowed("https://maps.google.com.evil.test/maps"))
        view.destroy()
    }
}
