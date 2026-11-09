package com.example.ui.components

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.AppSheet
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * Credits for the public-domain art the app shows, read from the same
 * `manifest.json` files the heroes use (title, artist, year, licence and the
 * Wikimedia Commons page), so the list can never drift from the bundle.
 * `docs/redesign/IMAGES.md` is the human-readable copy of the same record.
 */
data class ImageCredit(
    val assetPath: String,
    val title: String,
    val artist: String,
    val year: String,
    val license: String,
    val source: String
)

data class ImageCreditGroup(val folder: String, val credits: List<ImageCredit>)

object ImageCredits {
    /** Folders whose art is credited, in the order the sheet lists them. */
    val folders = listOf("heroes/lumen", "heroes/mysteries", "heroes/viacrucis")

    fun load(context: Context): List<ImageCreditGroup> = folders.mapNotNull { folder ->
        val json = runCatching {
            JSONObject(context.assets.open("$folder/manifest.json").bufferedReader().use { it.readText() })
        }.getOrNull() ?: return@mapNotNull null
        val credits = json.keys().asSequence().mapNotNull { file ->
            val e = json.optJSONObject(file) ?: return@mapNotNull null
            val title = e.optString("title").takeIf { it.isNotBlank() } ?: return@mapNotNull null
            ImageCredit(
                assetPath = "$folder/$file",
                title = title,
                artist = e.optString("artist"),
                year = e.optString("year"),
                license = e.optString("license"),
                source = e.optString("source")
            )
        }.toList()
        ImageCreditGroup(folder, credits).takeIf { credits.isNotEmpty() }
    }

    fun count(context: Context): Int = load(context).sumOf { it.credits.size }

    fun groupTitle(folder: String, language: AppLanguage): String = when (folder) {
        "heroes/viacrucis" -> when (language) {
            AppLanguage.ES -> "Vía Crucis"
            AppLanguage.EN -> "Way of the Cross"
            AppLanguage.PT -> "Via Sacra"
        }
        "heroes/mysteries" -> when (language) {
            AppLanguage.ES -> "Misterios del Rosario"
            AppLanguage.EN -> "Mysteries of the Rosary"
            AppLanguage.PT -> "Mistérios do Rosário"
        }
        else -> when (language) {
            AppLanguage.ES -> "Secciones de la app"
            AppLanguage.EN -> "App sections"
            AppLanguage.PT -> "Seções do app"
        }
    }

    fun licenseLabel(license: String, language: AppLanguage): String = when {
        license.equals("Public domain", ignoreCase = true) -> when (language) {
            AppLanguage.ES -> "Dominio público"
            AppLanguage.EN -> "Public domain"
            AppLanguage.PT -> "Domínio público"
        }
        else -> license
    }
}

/**
 * The image-credits sheet: each work with a small thumbnail, its title, the
 * artist, date and licence; a tap opens its Wikimedia Commons page in the
 * in-app browser.
 */
@Composable
fun ImageCreditsSheet(
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit
) {
    fun pick(es: String, en: String, pt: String) = when (currentLanguage) {
        AppLanguage.ES -> es
        AppLanguage.EN -> en
        AppLanguage.PT -> pt
    }
    val context = LocalContext.current
    val groups = remember { ImageCredits.load(context) }
    var openUrl by remember { mutableStateOf<String?>(null) }

    AppSheet(onDismiss = onDismiss, tag = "image_credits_sheet") {
        LumenSheetHeader(
            title = pick("Créditos de las imágenes", "Image credits", "Créditos das imagens"),
            doneLabel = pick("Listo", "Done", "Concluir"),
            onDone = onDismiss,
            doneTag = "image_credits_done"
        )
        SheetNote(
            pick(
                "Todas las obras son de dominio público o CC0, tomadas de Wikimedia Commons. Toca una para ver su ficha.",
                "Every work is public domain or CC0, from Wikimedia Commons. Tap one to see its page.",
                "Todas as obras são de domínio público ou CC0, do Wikimedia Commons. Toque numa para ver sua página."
            )
        )
        groups.forEach { group ->
            SheetLabel(ImageCredits.groupTitle(group.folder, currentLanguage))
            SheetGroup {
                group.credits.forEachIndexed { i, credit ->
                    CreditRow(
                        credit = credit,
                        meta = listOf(credit.artist, credit.year, ImageCredits.licenseLabel(credit.license, currentLanguage))
                            .filter { it.isNotBlank() }
                            .joinToString(" · "),
                        onClick = credit.source.takeIf { it.startsWith("http") }?.let { { openUrl = it } },
                        showDivider = i < group.credits.lastIndex
                    )
                }
            }
        }
        Spacer(Modifier.height(12.dp))
    }

    openUrl?.let { url ->
        InAppBrowserDialog(url = url, onDismiss = { openUrl = null }, currentLanguage = currentLanguage, readerMode = false)
    }
}

@Composable
private fun CreditRow(
    credit: ImageCredit,
    meta: String,
    onClick: (() -> Unit)?,
    showDivider: Boolean
) {
    val design = LocalAppDesign.current
    val styles = LocalAppTextStyles.current
    val p = design.palette
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val fill by animateColorAsState(
        if (pressed) p.textPrimary.copy(alpha = 0.06f) else Color.Transparent,
        if (pressed) tween(0) else tween(design.motion.mediumMillis),
        label = "creditFill"
    )
    val thumbShape = RoundedCornerShape(10.dp)
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .background(fill)
                .then(
                    if (onClick != null) Modifier.clickable(source, indication = null, role = Role.Button, onClick = onClick)
                    else Modifier
                )
                .testTag("image_credit_${credit.assetPath.substringAfterLast('/').substringBeforeLast('.')}")
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                Modifier
                    .size(44.dp)
                    .clip(thumbShape)
                    .background(p.separator)
            ) {
                val thumb = rememberCreditThumb(credit.assetPath)
                if (thumb != null) {
                    Image(thumb, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }
            }
            Column(Modifier.weight(1f)) {
                Text(credit.title, style = styles.subhead, color = p.textPrimary, maxLines = 3)
                Spacer(Modifier.height(2.dp))
                Text(meta, style = styles.footnote, color = p.textSecondary)
            }
            if (onClick != null) {
                Icon(
                    Icons.AutoMirrored.Outlined.OpenInNew,
                    contentDescription = null,
                    tint = p.textSecondary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        if (showDivider) SheetDivider(start = 14.dp + 44.dp + 14.dp)
    }
}

private val thumbCache = object : android.util.LruCache<String, ImageBitmap>(4 * 1024 * 1024) {
    override fun sizeOf(key: String, value: ImageBitmap) = value.width * value.height * 4
}

/** A ~150px thumbnail decoded off the main thread (42 works open at once). */
@Composable
private fun rememberCreditThumb(path: String): ImageBitmap? {
    val context = LocalContext.current
    val state by produceState(thumbCache.get(path), path) {
        if (value == null) {
            value = withContext(Dispatchers.IO) {
                runCatching {
                    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                    context.assets.open(path).use { BitmapFactory.decodeStream(it, null, bounds) }
                    var sample = 1
                    while (minOf(bounds.outWidth, bounds.outHeight) / (sample * 2) >= 150) sample *= 2
                    context.assets.open(path).use {
                        BitmapFactory.decodeStream(it, null, BitmapFactory.Options().apply { inSampleSize = sample })
                    }?.asImageBitmap()?.also { thumbCache.put(path, it) }
                }.getOrNull()
            }
        }
    }
    return state
}
