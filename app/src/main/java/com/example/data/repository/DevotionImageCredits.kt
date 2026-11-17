package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

/** One entry of `devotions/images/credits.json`. */
data class DevotionImageCredit(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val license: String = "",
    val source: String = "",
    val url: String = "",
    /** Shows a related image, not the devotion's own. */
    val referential: Boolean = false,
    val note: String = ""
) {
    /** A photo the app owner supplied, with unknown rights. */
    val userSupplied: Boolean get() = !referential && source.contains("por el usuario", ignoreCase = true)

    /** The link to open, only when it is https. */
    val safeUrl: String? get() = url.trim().takeIf { it.startsWith("https://", ignoreCase = true) }
}

private data class RawCredits(val images: List<DevotionImageCredit?>? = null)

/**
 * The attributions of the bundled devotion pictures, plus where each picture lives.
 * Missing or malformed files give an empty catalog, so the page simply shows no credit.
 */
class DevotionImageCredits(
    private val open: (String) -> InputStream,
    private val exists: (String) -> Boolean = { false },
    private val folder: String = FOLDER
) {
    companion object {
        const val FOLDER = "devotions/images"
        const val FILE = "credits.json"

        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: DevotionImageCredits? = null
        fun install(context: Context) { assets = context.applicationContext.assets }

        private fun assetExists(path: String) = runCatching { assets?.open(path)?.close() != null }.getOrDefault(false)

        private val bundled by lazy {
            DevotionImageCredits(
                open = {
                    assets?.open("$FOLDER/$it")
                        ?: DevotionImageCredits::class.java.classLoader!!.getResourceAsStream("$FOLDER/$it")
                        ?: error("Devotion image credits unavailable")
                },
                exists = ::assetExists
            )
        }
        val default: DevotionImageCredits get() = override ?: bundled

        /** The saint pictures (T-034) use the same credits format in their own folder. */
        const val SAINTS_FOLDER = "saints/images"
        /** Test hook: replaces [saints] while set. */
        @Volatile internal var saintsOverride: DevotionImageCredits? = null
        private val bundledSaints by lazy {
            DevotionImageCredits(
                open = {
                    assets?.open("$SAINTS_FOLDER/$it")
                        ?: DevotionImageCredits::class.java.classLoader!!.getResourceAsStream("$SAINTS_FOLDER/$it")
                        ?: error("Saint image credits unavailable")
                },
                exists = ::assetExists,
                folder = SAINTS_FOLDER
            )
        }
        val saints: DevotionImageCredits get() = saintsOverride ?: bundledSaints
    }

    private val credits: Map<String, DevotionImageCredit> by lazy {
        try {
            val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter(RawCredits::class.java)
            open(FILE).bufferedReader().use { adapter.fromJson(it.readText()) }?.images.orEmpty()
                .filterNotNull().filter { it.id.isNotBlank() }.associateBy { it.id }
        } catch (e: Exception) {
            emptyMap()
        }
    }

    /** The credit for [id], or null for an unknown devotion. */
    fun credit(id: String): DevotionImageCredit? = credits[id]

    /** Asset path of the devotion's picture when it is credited and the file exists, else null. */
    fun imagePath(id: String): String? =
        if (credits.containsKey(id)) "$folder/$id.jpg".takeIf(exists) else null
}
