package com.example.data.repository

import com.example.data.diagnostics.LogEvent
import com.example.data.diagnostics.AppLog
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.data.models.LocalizedText
import com.example.data.models.QuestionDeeper
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

internal data class RawQuestionDeeper(
    val number: Int? = null,
    val explanation: RawLT? = null, val scripture: RawLT? = null, val tradition: RawLT? = null,
    val magisterium: RawLT? = null, val objection: RawLT? = null, val analogy: RawLT? = null,
    val synthesis: RawLT? = null, val sources: List<String>? = null
)

internal val QuestionDeeperFiles = listOf("deeper-a.json", "deeper-b.json")

/** Loads optional deeper question content from the `questions` asset folder. Missing or malformed files are ignored. */
class QuestionDeeperCatalog(private val open: (String) -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        val default by lazy {
            QuestionDeeperCatalog { file ->
                assets?.open("questions/$file")
                    ?: QuestionDeeperCatalog::class.java.classLoader!!.getResourceAsStream(file)
                    ?: error("Question asset unavailable")
            }
        }
    }

    private val adapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build().adapter<List<RawQuestionDeeper>>(
        Types.newParameterizedType(List::class.java, RawQuestionDeeper::class.java)
    )

    private fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())

    private fun read(name: String): List<RawQuestionDeeper>? = try {
        open(name).bufferedReader().use { adapter.fromJson(it.readText()) }
    } catch (e: Exception) {
        AppLog.failure(LogEvent.QUESTION_CATALOG,e)
        if (e !is java.io.FileNotFoundException && e.message?.contains("unavailable") != true)
            runCatching { Log.w("QuestionDeeperCatalog", "Ignoring $name: ${e.message}") }
        null
    }

    val byNumber: Map<Int, QuestionDeeper> by lazy {
        val out = LinkedHashMap<Int, QuestionDeeper>()
        for (f in QuestionDeeperFiles) read(f)?.forEach { e ->
            val n = e?.number ?: return@forEach
            out[n] = QuestionDeeper(
                n, e.explanation.lt(), e.scripture.lt(), e.tradition.lt(), e.magisterium.lt(),
                e.objection.lt(), e.analogy.lt(), e.synthesis.lt(), e.sources.orEmpty().filter { it.isNotBlank() }
            )
        }
        out
    }
}
