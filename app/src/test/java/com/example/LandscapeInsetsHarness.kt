package com.example

import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.AbstractComposeView
import androidx.core.graphics.Insets
import androidx.core.view.DisplayCutoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import org.robolectric.shadows.ShadowDialog

/** Dispatches real platform insets to Compose's WindowInsetsHolder, not a fake UI local. */
object LandscapeInsetsHarness {
    fun insets(density: Float, heightDp: Int, ime: Boolean = false, cutout: String? = null, navbar: String? = null): WindowInsetsCompat {
        fun px(dp: Int) = (dp * density).toInt()
        val left = if (cutout == "left") px(32) else 0
        val right = if (cutout == "right") px(32) else 0
        return WindowInsetsCompat.Builder()
            .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(24), 0, 0))
            .setVisible(WindowInsetsCompat.Type.statusBars(), true)
            .setInsets(WindowInsetsCompat.Type.navigationBars(), when (navbar) {
                "left" -> Insets.of(px(48), 0, 0, 0)
                "right" -> Insets.of(0, 0, px(48), 0)
                else -> Insets.of(0, 0, 0, px(16))
            }).setVisible(WindowInsetsCompat.Type.navigationBars(), true)
            .setInsets(WindowInsetsCompat.Type.systemGestures(), Insets.of(px(32), 0, px(32), px(16)))
            .setDisplayCutout(if (left + right == 0) null else DisplayCutoutCompat(Insets.of(left, 0, right, 0),
                if (left > 0) android.graphics.Rect(0, px(heightDp / 2 - 16), left, px(heightDp / 2 + 16)) else null,
                null, if (right > 0) android.graphics.Rect(0, px(heightDp / 2 - 16), right, px(heightDp / 2 + 16)) else null, null, Insets.NONE))
            .setInsets(WindowInsetsCompat.Type.displayCutout(), Insets.of(left, 0, right, 0))
            .setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, if (ime) (heightDp * 0.56f * density).toInt() else 0))
            .setVisible(WindowInsetsCompat.Type.ime(), ime).build()
    }

    fun dispatch(activity: ComponentActivity, heightDp: Int, ime: Boolean = false, cutout: String? = null, navbar: String? = null) {
        // AndroidX's @TestOnly hook prevents device-specific erasure during the
        // platform/compat round trip. Insets still travel through onApplyWindowInsets.
        val holder = Class.forName("androidx.compose.foundation.layout.WindowInsetsHolder")
        val companion = holder.getField("Companion").get(null)
        companion.javaClass.getMethod("setUseTestInsets", Boolean::class.javaPrimitiveType).invoke(companion, true)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        val value = insets(activity.resources.displayMetrics.density, heightDp, ime, cutout, navbar)
        fun apply(view: View) {
            // Robolectric can request another (empty) system dispatch after the
            // manual one. Supply our real WindowInsetsCompat at the host, while
            // leaving AndroidComposeView's WindowInsetsHolder listener intact.
            if (view is AbstractComposeView || view === activity.window.decorView || view === ShadowDialog.getLatestDialog()?.window?.decorView)
                ViewCompat.setOnApplyWindowInsetsListener(view) { _, _ -> value }
            ViewCompat.dispatchApplyWindowInsets(view, value)
            if (view is ViewGroup && view !is AbstractComposeView) for (i in 0 until view.childCount) apply(view.getChildAt(i))
        }
        apply(activity.window.decorView)
        ShadowDialog.getLatestDialog()?.window?.decorView?.let(::apply)
    }
}
