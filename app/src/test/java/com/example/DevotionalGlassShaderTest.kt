package com.example

import com.example.ui.design.DevotionalGlassRenderer
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class DevotionalGlassShaderTest {
    @Test @Config(sdk=[34]) fun theRefractiveShaderCompilesOnSupportedAndroid() {
        val renderer = DevotionalGlassRenderer()
        assertTrue(renderer.refractionFailure, renderer.refractionAvailable)
    }
    @Test @Config(sdk=[28]) fun olderAndroidUsesTheSupportedFallback() {
        assertFalse(DevotionalGlassRenderer().refractionAvailable)
    }
}
