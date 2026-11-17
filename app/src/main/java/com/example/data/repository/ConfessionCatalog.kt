package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import com.example.data.diagnostics.AppLog
import com.example.data.diagnostics.LogEvent
import com.example.data.models.LocalizedText
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

// The reviewed assets are the sole source of the examination and prayer texts.
data class ConfessionSection(val id: String, val title: LocalizedText, val basis: String, val questions: List<LocalizedText>)
data class ConfessionProfile(val id: String, val title: LocalizedText, val intro: LocalizedText, val sections: List<ConfessionSection>)
data class ConfessionStep(val id: String, val title: LocalizedText, val basis: String, val body: LocalizedText)
data class ContritionPrayer(val id: String, val title: LocalizedText, val source: LocalizedText, val text: LocalizedText)
data class ConfessionFaq(val id: String, val question: LocalizedText, val answer: LocalizedText, val basis: String)
data class ConfessionGuide(val steps: List<ConfessionStep>, val contrition: List<ContritionPrayer>, val faq: List<ConfessionFaq>)
data class ConfessionContent(val profiles: List<ConfessionProfile>, val guide: ConfessionGuide) {
    val questions: List<ConfessionQuestion> get() = profiles.flatMap { profile -> profile.sections.flatMap { section ->
        section.questions.mapIndexed { index, text -> ConfessionQuestion("${profile.id}:${section.id}:$index", text, section.basis, profile.title) }
    } }
}
data class ConfessionQuestion(val id: String, val text: LocalizedText, val basis: String, val profile: LocalizedText)
internal data class ConfessionExamen(val profiles: List<ConfessionProfile>)

class ConfessionCatalog(private val open: (String) -> InputStream) {
    companion object {
        @Volatile private var assets: AssetManager? = null
        @Volatile internal var override: ConfessionCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled = ConfessionCatalog { file ->
            assets?.open("confession/$file")
                ?: ConfessionCatalog::class.java.classLoader!!.getResourceAsStream(file)
                ?: error("Confession asset unavailable")
        }
        val default: ConfessionCatalog get() = override ?: bundled
    }
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private fun <T> read(file: String, type: Class<T>): T = open(file).bufferedReader().use {
        requireNotNull(moshi.adapter(type).fromJson(it.readText()))
    }
    /** Called on IO by the screen; failure remains retryable, without caching an empty catalog. */
    fun load(): ConfessionContent? = try {
        val profiles = read("examen.json", ConfessionExamen::class.java).profiles
        val guide = read("guide.json", ConfessionGuide::class.java)
        require(profiles.isNotEmpty() && profiles.map { it.id }.distinct().size == profiles.size)
        require(profiles.all { p -> p.sections.isNotEmpty() && p.sections.map { it.id }.distinct().size == p.sections.size })
        require(guide.steps.isNotEmpty() && guide.contrition.isNotEmpty() && guide.faq.isNotEmpty())
        ConfessionContent(profiles, guide)
    } catch (e: Exception) { AppLog.failure(LogEvent.CONFESSION_CATALOG, e); null }
}
