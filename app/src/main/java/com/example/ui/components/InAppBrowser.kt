package com.example.ui.components

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.models.AppLanguage
import com.example.ui.design.AppGlassIconButton
import com.example.ui.design.AppPrimaryCapsule
import com.example.ui.design.AppProgressBar
import com.example.ui.design.AppSecondaryCapsule
import com.example.ui.design.lumenCanvas
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * A web page shown inside the app (the Vatican's Catechism pages, the parish
 * map): a glass bar with close, the page's own title over its host, and
 * "open in browser"; a hairline progress bar while loading; back steps
 * through the page's history before it closes; and a designed error state
 * instead of the WebView's grey dinosaur.
 *
 * [readerMode] keeps the legibility pass old Vatican pages need (larger
 * type, wrapped tables). Embeds that lay themselves out — a map — pass false.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InAppBrowserDialog(
    url: String,
    onDismiss: () -> Unit,
    currentLanguage: AppLanguage = AppLanguage.ES,
    readerMode: Boolean = true
) {
    fun pick(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val context = LocalContext.current

    var webView by remember { mutableStateOf<WebView?>(null) }
    var pageTitle by remember { mutableStateOf("") }
    var currentUrl by remember { mutableStateOf(url) }
    var progress by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(true) }
    var failed by remember { mutableStateOf(false) }
    var blocked by remember { mutableStateOf(false) }

    val host = remember(currentUrl) {
        runCatching { Uri.parse(currentUrl).host.orEmpty().removePrefix("www.") }.getOrDefault("")
    }
    val secure = currentUrl.startsWith("https://")

    fun openExternally() {
        if (WebNavigationPolicy.destination(currentUrl) != WebNavigationPolicy.Destination.EMBEDDED) return
        runCatching {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    Dialog(
        // Back walks the page's own history first, then closes.
        onDismissRequest = {
            val view = webView
            if (view != null && view.canGoBack() && !failed) view.goBack() else onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .lumenCanvas()
                .testTag("in_app_browser")
        ) {
            // ---------------------------------------------------- glass bar
            Column(Modifier.fillMaxWidth().background(p.canvasTop)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = design.spacing.gutter - 6.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppGlassIconButton(
                        icon = Icons.Rounded.Close,
                        contentDescription = pick("Cerrar", "Close", "Fechar"),
                        onClick = onDismiss,
                        tag = "in_app_browser_close"
                    )
                    Column(
                        Modifier.weight(1f).padding(horizontal = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = pageTitle.ifBlank {
                                if (loading) pick("Cargando…", "Loading…", "Carregando…") else host
                            },
                            style = styles.subhead.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold),
                            color = p.textPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.semantics { heading() }
                        )
                        if (host.isNotBlank()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (secure) {
                                    Icon(
                                        Icons.Outlined.Lock,
                                        contentDescription = pick("Conexión segura", "Secure connection", "Conexão segura"),
                                        tint = p.textSecondary,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Spacer(Modifier.size(4.dp))
                                }
                                Text(
                                    host,
                                    style = styles.footnote,
                                    color = p.textSecondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                    AppGlassIconButton(
                        icon = Icons.AutoMirrored.Outlined.OpenInNew,
                        contentDescription = pick("Abrir en el navegador", "Open in browser", "Abrir no navegador"),
                        onClick = { openExternally() },
                        tag = "in_app_browser_external"
                    )
                }
                // Hairline progress while loading; the separator at rest.
                Box(Modifier.fillMaxWidth().height(2.dp)) {
                    FadeVisibility(
                        visible = loading && !failed
                    ) {
                        AppProgressBar(
                            progress = (progress.coerceAtLeast(8)) / 100f,
                            height = 2.dp,
                            color = p.textPrimary.copy(alpha = 0.7f),
                            track = p.separator,
                            modifier = Modifier.testTag("in_app_browser_progress")
                        )
                    }
                    if (!loading || failed) {
                        Box(Modifier.fillMaxWidth().height(0.5.dp).align(Alignment.BottomStart).background(p.separator))
                    }
                }
            }

            // -------------------------------------------------------- page
            Box(Modifier.fillMaxWidth().weight(1f).navigationBarsPadding()) {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            setBackgroundColor(p.canvas.toArgb())
                            WebNavigationPolicy.configure(settings, url)
                            if (readerMode) settings.textZoom = 150
                            if (Build.VERSION.SDK_INT >= 33) {
                                settings.isAlgorithmicDarkeningAllowed = design.isDark
                            }
                            webChromeClient = object : WebChromeClient() {
                                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                    progress = newProgress
                                    if (newProgress >= 100) loading = false
                                }

                                override fun onReceivedTitle(view: WebView?, title: String?) {
                                    pageTitle = title.orEmpty().takeUnless { it.startsWith("http") }.orEmpty()
                                }
                            }
                            webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean {
                                    return when (WebNavigationPolicy.destination(request.url.toString(), request.hasGesture(), request.isForMainFrame)) {
                                        WebNavigationPolicy.Destination.EMBEDDED -> false
                                        WebNavigationPolicy.Destination.EXTERNAL -> {
                                            runCatching { ctx.startActivity(Intent(Intent.ACTION_VIEW, request.url).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
                                            true
                                        }
                                        WebNavigationPolicy.Destination.BLOCKED -> {
                                            if (request.isForMainFrame) { blocked = true; failed = true; loading = false }
                                            true
                                        }
                                    }
                                }

                                @Deprecated("Legacy WebView callback")
                                override fun shouldOverrideUrlLoading(view: WebView?, target: String): Boolean =
                                    WebNavigationPolicy.destination(target) != WebNavigationPolicy.Destination.EMBEDDED

                                override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, response: android.webkit.WebResourceResponse?) {
                                    if (request?.isForMainFrame == true) { failed = true; loading = false }
                                }
                                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                    if (url == null || WebNavigationPolicy.destination(url) != WebNavigationPolicy.Destination.EMBEDDED) {
                                        view?.stopLoading(); blocked = true; failed = true; loading = false
                                        return
                                    }
                                    view?.settings?.let { WebNavigationPolicy.configure(it, url) }
                                    loading = true
                                    blocked = false
                                    failed = false
                                    currentUrl = url
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)
                                    loading = false
                                    url?.let { currentUrl = it }
                                    if (readerMode) {
                                        view?.evaluateJavascript(
                                            """
                                            (function() {
                                                var style = document.createElement('style');
                                                style.innerHTML = 'body { font-size: 18px !important; line-height: 1.6 !important; word-wrap: break-word !important; overflow-wrap: break-word !important; padding: 12px !important; } img { max-width: 100% !important; height: auto !important; } table { max-width: 100% !important; } pre, code { white-space: pre-wrap !important; }';
                                                document.head.appendChild(style);
                                            })();
                                            """.trimIndent(),
                                            null
                                        )
                                    }
                                }

                                override fun onReceivedError(
                                    view: WebView?,
                                    request: WebResourceRequest?,
                                    error: WebResourceError?
                                ) {
                                    if (request?.isForMainFrame == true) {
                                        failed = true
                                        loading = false
                                    }
                                }
                            }
                            if (WebNavigationPolicy.destination(url) == WebNavigationPolicy.Destination.EMBEDDED) loadUrl(url)
                            else { blocked = true; failed = true; loading = false }
                            webView = this
                        }
                    },
                    onRelease = { view ->
                        view.stopLoading()
                        view.destroy()
                        webView = null
                    },
                    modifier = Modifier.fillMaxSize()
                )

                FadeVisibility(
                    visible = failed
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .lumenCanvas()
                            .padding(horizontal = design.spacing.gutter + 12.dp)
                            .testTag("in_app_browser_error"),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Outlined.CloudOff,
                            contentDescription = null,
                            tint = p.textSecondary,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(Modifier.height(18.dp))
                        Text(
                            pick("No se pudo abrir la página", "Couldn't open the page", "Não foi possível abrir a página"),
                            style = styles.title3,
                            color = p.textPrimary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            if (blocked) pick("Este enlace no se puede abrir aquí. Solo se permiten páginas HTTPS.", "This link cannot open here. Only HTTPS pages are allowed.", "Este link não pode ser aberto aqui. Apenas páginas HTTPS são permitidas.") else pick(
                                "Comprueba tu conexión e inténtalo de nuevo.",
                                "Check your connection and try again.",
                                "Verifique sua conexão e tente de novo."
                            ),
                            style = styles.subhead,
                            color = p.textSecondary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(28.dp))
                        AppPrimaryCapsule(
                            label = pick("Reintentar", "Try again", "Tentar de novo"),
                            onClick = {
                                failed = false
                                loading = true
                                progress = 0
                                webView?.reload()
                            },
                            fillWidth = false,
                            modifier = Modifier.widthIn(min = 200.dp),
                            tag = "in_app_browser_retry"
                        )
                        Spacer(Modifier.height(12.dp))
                        AppSecondaryCapsule(
                            label = pick("Abrir en el navegador", "Open in browser", "Abrir no navegador"),
                            onClick = { openExternally() },
                            modifier = Modifier.widthIn(min = 200.dp)
                        )
                    }
                }
            }
        }
    }
}

/** Cross-fade in/out, callable from any scope (no ColumnScope/RowScope overload). */
@Composable
private fun FadeVisibility(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(LocalAppDesign.current.motion.mediumMillis)),
        exit = fadeOut(tween(LocalAppDesign.current.motion.fastMillis))
    ) { content() }
}
