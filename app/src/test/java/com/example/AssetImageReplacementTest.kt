package com.example

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.ui.design.rememberAssetBitmap
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.Config

/** Same call site, changing keys: catches retaining non-null A when B is requested. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class AssetImageReplacementTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private data class RenderedAsset(val path: String?, val bitmap: ImageBitmap?)

    @Test fun changingAssetAtTheSameCompositionSiteLoadsTheNewBitmapAndClearsMissingArt() {
        var path by mutableStateOf<String?>("heroes/lumen/home.jpg")
        // SideEffect runs on the UI thread; waitUntil reads from the test thread.
        // Publish both the request and its result with an actual visibility guarantee.
        val rendered = AtomicReference<RenderedAsset>()
        compose.setContent {
            val requestedPath = path
            val image = rememberAssetBitmap(requestedPath, maxWidthPx = 120)
            SideEffect {
                rendered.set(RenderedAsset(requestedPath, image))
            }
            Box {}
        }
        compose.waitUntil(timeoutMillis = 10000) { rendered.get()?.bitmap != null }
        val first = requireNotNull(rendered.get().bitmap)
        compose.runOnIdle { path = "heroes/lumen/bible.jpg" }
        compose.waitForIdle()
        compose.waitUntil(timeoutMillis = 10000) {
            rendered.get()?.let {
                it.path == "heroes/lumen/bible.jpg" && it.bitmap != null && it.bitmap !== first
            } == true
        }
        val second = requireNotNull(rendered.get().bitmap)
        assertNotSame(first, second)
        compose.runOnIdle { path = "heroes/lumen/home.jpg" }
        compose.waitForIdle()
        compose.waitUntil(timeoutMillis = 10000) {
            rendered.get()?.let { it.path == "heroes/lumen/home.jpg" && it.bitmap === first } == true
        }
        assertSame(first, rendered.get().bitmap)
        compose.runOnIdle { path = "heroes/lumen/does-not-exist.webp" }
        compose.waitForIdle()
        compose.waitUntil(timeoutMillis = 10000) { rendered.get()?.path == path }
        assertNull(rendered.get().bitmap)
        compose.runOnIdle { path = null }
        compose.waitForIdle()
        compose.waitUntil(timeoutMillis = 10000) { rendered.get()?.path == null }
        assertNull(rendered.get().bitmap)
    }
}
