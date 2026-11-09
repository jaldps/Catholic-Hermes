package com.example.screenshots

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.models.AppLanguage
import com.example.data.news.NewsResult
import com.example.data.news.VaticanNewsItem
import com.example.data.repository.CatholicDataRepository
import com.example.ui.design.AppNavItem
import com.example.ui.design.AppScaffold
import com.example.ui.design.LocalScreenFrame
import com.example.ui.design.ScreenFrame
import com.example.ui.screens.DoctrineAndHistoryScreen
import com.example.ui.theme.SanctaFidesTheme
import kotlinx.coroutines.awaitCancellation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

/**
 * The Doctrine & Study area's reading pages and states the full-app render
 * cannot reach, each in the real Lumen scaffold (with its glass back button).
 *
 * Output: build/screenshots/doctrine/<state>_<light|dark>[_<lang>].png
 * Run with -Pshots.lang=es|pt; -Pshots.only=qa_first,order_opus,… narrows it.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w411dp-h891dp-xhdpi")
class DoctrineAreaScreenshotTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private data class State(
        val name: String,
        val tab: Int,
        val entry: String? = null,
        val churches: Int = 0,
        val news: String = "ok"
    )

    private val states = listOf(
        State("doctrine", 0),
        State("commandment", 0, "cmd:4"),
        State("vice", 0, "vice:0"),
        State("sin_conditions", 0, "grav:1"),
        State("sin_confession", 0, "grav:2"),
        State("didache_toc", 0, "didache_toc:0"),
        State("didache_chapter", 0, "didache:6"),
        State("questions", 3),
        State("qa_first", 3, "qa:0"),
        State("qa_last", 3, "qa:100"),
        State("theme", 3, "theme:9"),
        State("sources", 3, "sources:0"),
        State("history", 2),
        State("epoch", 2, "epoch:0"),
        State("schism", 2, "schism:1"),
        State("orders", 4),
        State("order_detail", 4, "order:2"),
        State("office_detail", 4, "office:9"),
        State("mary", 1),
        State("devotion_marian", 1, "devotion:fatima"),
        State("devotion_christ", 1, "devotion:sacred_heart_jesus"),
        State("news", 5),
        State("news_loading", 5, news = "loading"),
        State("news_failed", 5, news = "fail"),
        State("churches", 5, churches = 1),
        State("parish", 5, "parish:", churches = 1)
    )

    private val outputDir = File("build/screenshots/doctrine").also { it.mkdirs() }
    private val language: AppLanguage = System.getProperty("shots.lang")
        ?.let { AppLanguage.fromCode(it.trim().lowercase()) } ?: AppLanguage.ES
    private val only: Set<String>? = System.getProperty("shots.only")
        ?.split(',')?.map { it.trim() }?.filter { it.isNotEmpty() }?.toSet()
    private val heightDp = System.getProperty("shots.height")?.toIntOrNull()?.coerceIn(640, 2400) ?: 891

    private val sampleNews = NewsResult.Success(
        listOf(
            VaticanNewsItem(
                "El Papa pide oraciones por la paz en Oriente Medio y recuerda a las víctimas de la guerra",
                "León XIV pidió este domingo, tras el Ángelus, que la comunidad internacional no abandone a las poblaciones civiles.",
                "https://www.vaticannews.va/a", "Fri, 30 Oct 2026 13:55:00 +0100", null
            ),
            VaticanNewsItem(
                "Audiencia general: la esperanza que no defrauda",
                "En su catequesis, el Papa invitó a los fieles a mirar el futuro con confianza.",
                "https://www.vaticannews.va/b", "Fri, 30 Oct 2026 09:10:00 +0100", null
            ),
            VaticanNewsItem(
                "Jubileo de las Familias: miles de peregrinos llegan a Roma",
                "Las parroquias organizan jornadas de oración y de acogida.",
                "https://www.vaticannews.va/c", "Thu, 29 Oct 2026 18:00:00 +0100", null
            ),
            VaticanNewsItem(
                "Nuevo documento sobre la catequesis en el mundo digital",
                "El dicasterio publica orientaciones para los catequistas.",
                "https://www.vaticannews.va/d", "Wed, 28 Oct 2026 12:00:00 +0100", null
            )
        )
    )

    private fun render(dark: Boolean) {
        val context = ApplicationProvider.getApplicationContext<android.app.Application>()
        val repository = CatholicDataRepository(context).apply { setLanguage(language) }
        val wanted = states.filter { only == null || it.name in only!! }
        var current by mutableStateOf<State?>(null)
        val nav = listOf(
            AppNavItem("Inicio", Icons.Outlined.Home, tag = "nav_home"),
            AppNavItem("Biblia", Icons.Outlined.AutoStories, tag = "nav_bible"),
            AppNavItem("Rosario", Icons.Outlined.Spa, tag = "nav_rosary"),
            AppNavItem("Oraciones", Icons.Outlined.WbSunny, tag = "nav_prayers"),
            AppNavItem("Hábitos", Icons.Outlined.Shield, tag = "nav_habits"),
            AppNavItem("Más", Icons.Outlined.MoreHoriz, tag = "nav_more")
        )
        try {
            compose.setContent {
                val state = current ?: return@setContent
                SanctaFidesTheme(darkModeSetting = if (dark) "DARK" else "LIGHT") {
                    CompositionLocalProvider(LocalInspectionMode provides true) {
                        key(state) {
                            AppScaffold(title = "Estudio", navItems = nav, selectedNavIndex = 5, onNavSelected = {}, onBack = {}) { inner ->
                                val frame = remember { ScreenFrame() }
                                if (frame.insets != inner) frame.insets = inner
                                CompositionLocalProvider(LocalScreenFrame provides frame) {
                                    Box(
                                        Modifier.fillMaxSize().layout { m, c ->
                                            val top = if (frame.edgeToEdge) 0 else inner.calculateTopPadding().roundToPx()
                                            val bottom = if (frame.edgeToEdge) 0 else inner.calculateBottomPadding().roundToPx()
                                            val p = m.measure(c.copy(minHeight = 0, maxHeight = (c.maxHeight - top - bottom).coerceAtLeast(0)))
                                            layout(c.maxWidth, c.maxHeight) { p.place(0, top) }
                                        }
                                    ) {
                                        val entry = state.entry?.let {
                                            if (it == "parish:") "parish:" + repository.nearbyParishes.first().id else it
                                        }
                                        DoctrineAndHistoryScreen(
                                            repository = repository,
                                            currentLanguage = language,
                                            initialTopTab = state.tab,
                                            initialChurchesSection = state.churches,
                                            initialEntry = entry,
                                            newsLoader = {
                                                when (state.news) {
                                                    "loading" -> awaitCancellation()
                                                    "fail" -> NewsResult.Failure("offline")
                                                    else -> sampleNews
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            val w = 411 * 2
            val h = heightDp * 2
            for (state in wanted) {
                compose.runOnUiThread { current = state }
                compose.waitForIdle()
                compose.runOnIdle {
                    val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
                    view.layoutParams = view.layoutParams.apply { width = w; height = h }
                    view.measure(View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY))
                    view.layout(0, 0, w, h)
                }
                compose.waitForIdle()
                val bitmap = compose.runOnIdle {
                    val view = compose.activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ComposeView
                    Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888).also { view.draw(Canvas(it)) }
                }
                val suffix = "_${language.code}"
                val file = File(outputDir, "${state.name}_${if (dark) "dark" else "light"}$suffix${if (heightDp != 891) "_h$heightDp" else ""}.png")
                file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                println("WROTE ${file.absolutePath}")
            }
        } finally {
            compose.runOnUiThread { current = null }
            compose.waitForIdle()
        }
    }

    @Test fun light() = render(false)
    @Test fun dark() = render(true)
}
