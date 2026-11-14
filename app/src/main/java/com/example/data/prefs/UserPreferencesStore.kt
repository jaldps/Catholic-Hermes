package com.example.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.models.AppLanguage
import com.example.data.liturgy.CalendarRules
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import com.example.data.persistence.PersonalDataCommands
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "sancta_fides_preferences"
)

/**
 * Every user-facing preference, persisted with DataStore so it survives process
 * death, independently of Room schema upgrades.
 */
data class UserPreferences(
    val language: AppLanguage,
    val designLanguage: String,
    val themePreset: String,
    val darkModeSetting: String,
    val fontFamilyChoice: String,
    val fontSizeScale: Float,
    val audioNarrationSpeed: Float,
    val activeBibleTranslationId: String,
    val isFullCccDownloaded: Boolean,
    /** False until the user has picked a language themselves. */
    val hasChosenLanguage: Boolean,
    val reduceMotion: Boolean = false,
    val reduceTransparency: Boolean = false,
    val highContrast: Boolean = false,
    val calendarRules: CalendarRules = CalendarRules(),
    /** False means preferences could not be read, not that the user reset them. */
    val storageAvailable: Boolean = true
) {
    companion object {
        const val DEFAULT_DESIGN_LANGUAGE = "LUMEN"
        const val DEFAULT_THEME_PRESET = "LITURGICAL_DYNAMIC"
        const val DEFAULT_DARK_MODE = "SYSTEM"
        const val DEFAULT_FONT_FAMILY = "IDENTITY_DEFAULT"
    }
}

class UserPreferencesStore(
    private val context: Context,
    private val dataStore: DataStore<Preferences> = context.preferencesDataStore
) {

    private data class ReadSnapshot(val available: Boolean, val data: Preferences)
    private val readRevision = MutableStateFlow(0)
    @Volatile private var lastGood: Preferences? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    private val snapshots: Flow<ReadSnapshot> = readRevision.flatMapLatest {
        dataStore.data.map { ReadSnapshot(true, it) }.catch { error ->
            if (error is IOException) emit(ReadSnapshot(false, lastGood ?: emptyPreferences()))
            else throw error
        }
    }.onEach { if (it.available) lastGood = it.data }

    fun retryRead() { readRevision.value++ }

    private suspend fun editPreference(key: String, transform: suspend (MutablePreferences) -> Unit) {
        PersonalDataCommands.execute("preferences:$key") { dataStore.edit(transform); retryRead() }
    }

    private object Keys {
        val EPIPHANY_SUNDAY = booleanPreferencesKey("calendar_epiphany_sunday")
        val ASCENSION_SUNDAY = booleanPreferencesKey("calendar_ascension_sunday")
        val CORPUS_SUNDAY = booleanPreferencesKey("calendar_corpus_sunday")
        val LANGUAGE = stringPreferencesKey("language")
        val HAS_CHOSEN_LANGUAGE = booleanPreferencesKey("has_chosen_language")
        val DESIGN_LANGUAGE = stringPreferencesKey("design_language")
        val THEME_PRESET = stringPreferencesKey("theme_preset")
        val REDUCE_MOTION = booleanPreferencesKey("reduce_motion")
        val REDUCE_TRANSPARENCY = booleanPreferencesKey("reduce_transparency")
        val HIGH_CONTRAST = booleanPreferencesKey("high_contrast")
        val DARK_MODE = stringPreferencesKey("dark_mode_setting")
        val FONT_FAMILY = stringPreferencesKey("font_family_choice")
        val FONT_SCALE = floatPreferencesKey("font_size_scale")
        val NARRATION_SPEED = floatPreferencesKey("audio_narration_speed")
        val BIBLE_TRANSLATION = stringPreferencesKey("active_bible_translation_id")
        val FULL_CCC_DOWNLOADED = booleanPreferencesKey("is_full_ccc_downloaded")
        /** Guided learning tracks: highest reached question number per track,
         *  encoded "trackIndex:questionNumber,trackIndex:questionNumber". */
        val TRACK_PROGRESS = stringPreferencesKey("track_progress")
        val READ_QUESTIONS = stringSetPreferencesKey("read_questions_v2")
        val LAST_QUESTION = intPreferencesKey("last_read_question")
    }

    /** Track index for a 1-based question number in the three guided tracks
     *  (Fundamentos Q1-40, Iglesia y sacramentos Q41-60, Vida y moral Q61-101);
     *  null when the question is outside every track. */
    fun trackIndexForQuestion(questionNumber: Int): Int? = when (questionNumber) {
        in 1..40 -> 0
        in 41..60 -> 1
        in 61..101 -> 2
        else -> null
    }

    fun parseTrackProgress(raw: String?): Map<Int, Int> =
        raw.orEmpty().split(',').mapNotNull { entry ->
            val parts = entry.split(':')
            if (parts.size != 2) return@mapNotNull null
            val track = parts[0].trim().toIntOrNull() ?: return@mapNotNull null
            val reached = parts[1].trim().toIntOrNull() ?: return@mapNotNull null
            track to reached
        }.toMap()

    /** Highest reached question number per guided track (1-based; 0/absent = not started). */
    val trackProgress: Flow<Map<Int, Int>> = snapshots.map { parseTrackProgress(it.data[Keys.TRACK_PROGRESS]) }

    /** Legacy high-water marks prove their endpoint was opened, not all earlier questions. */
    private fun readQuestions(prefs: Preferences): Set<Int> =
        prefs[Keys.READ_QUESTIONS]?.mapNotNull { it.toIntOrNull()?.takeIf { n -> n in 1..101 } }?.toSet()
            ?: parseTrackProgress(prefs[Keys.TRACK_PROGRESS]).values.filter { it in 1..101 }.toSet()

    val visitedQuestions: Flow<Set<Int>> = snapshots.map { readQuestions(it.data) }
    val lastReadQuestion: Flow<Int?> = snapshots.map {
        it.data[Keys.LAST_QUESTION] ?: parseTrackProgress(it.data[Keys.TRACK_PROGRESS]).values.maxOrNull()
    }

    /** Records [questionNumber] (1-based) as reached for its track, never lowering it. */
    suspend fun recordTrackProgress(questionNumber: Int) {
        val track = trackIndexForQuestion(questionNumber) ?: return
        editPreference("recordTrackProgress") { prefs ->
            prefs[Keys.READ_QUESTIONS] = (readQuestions(prefs) + questionNumber).map { it.toString() }.toSet()
            prefs[Keys.LAST_QUESTION] = questionNumber
            val current = parseTrackProgress(prefs[Keys.TRACK_PROGRESS])
            val merged = current.toMutableMap()
            merged[track] = maxOf(merged[track] ?: 0, questionNumber)
            prefs[Keys.TRACK_PROGRESS] = merged.entries
                .sortedBy { it.key }
                .joinToString(",") { "${it.key}:${it.value}" }
        }
    }

    /** Direct setter for one track's reached question number (never lowers it). */
    suspend fun setTrackProgress(trackIndex: Int, questionNumber: Int) {
        require(trackIndexForQuestion(questionNumber) == trackIndex)
        editPreference("setTrackProgress") { prefs ->
            prefs[Keys.READ_QUESTIONS] = (readQuestions(prefs) + questionNumber).map { it.toString() }.toSet()
            prefs[Keys.LAST_QUESTION] = questionNumber
            val current = parseTrackProgress(prefs[Keys.TRACK_PROGRESS])
            val merged = current.toMutableMap()
            merged[trackIndex] = maxOf(merged[trackIndex] ?: 0, questionNumber)
            prefs[Keys.TRACK_PROGRESS] = merged.entries
                .sortedBy { it.key }
                .joinToString(",") { "${it.key}:${it.value}" }
        }
    }

    /**
     * Emits the stored preferences, falling back to defaults for anything unset.
     * A failed read keeps the last known values (or temporary defaults) and
     * explicitly marks storage unavailable. Reading never resets the file.
     */
    val preferences: Flow<UserPreferences> = snapshots
        .map { snapshot ->
            val stored = snapshot.data
            val code = stored[Keys.LANGUAGE]
            UserPreferences(
                // AppLanguage.fromCode falls back to the device locale when unset.
                language = AppLanguage.fromCode(code ?: ""),
                storageAvailable = snapshot.available,
                hasChosenLanguage = stored[Keys.HAS_CHOSEN_LANGUAGE] ?: (code != null),
                calendarRules = CalendarRules(stored[Keys.EPIPHANY_SUNDAY] ?: false,
                    stored[Keys.ASCENSION_SUNDAY] ?: false, stored[Keys.CORPUS_SUNDAY] ?: false),
                designLanguage = canonicalDesign(stored[Keys.DESIGN_LANGUAGE]),
                reduceMotion = stored[Keys.REDUCE_MOTION] ?: false,
                reduceTransparency = stored[Keys.REDUCE_TRANSPARENCY] ?: false,
                highContrast = stored[Keys.HIGH_CONTRAST] ?: false,
                themePreset = stored[Keys.THEME_PRESET]
                    ?: UserPreferences.DEFAULT_THEME_PRESET,
                darkModeSetting = stored[Keys.DARK_MODE]
                    ?: UserPreferences.DEFAULT_DARK_MODE,
                fontFamilyChoice = stored[Keys.FONT_FAMILY]
                    ?: UserPreferences.DEFAULT_FONT_FAMILY,
                fontSizeScale = stored[Keys.FONT_SCALE] ?: 1.0f,
                audioNarrationSpeed = stored[Keys.NARRATION_SPEED] ?: 1.0f,
                activeBibleTranslationId = stored[Keys.BIBLE_TRANSLATION] ?: "jerusalem",
                isFullCccDownloaded = stored[Keys.FULL_CCC_DOWNLOADED] ?: false
            )
        }

    suspend fun setCalendarRules(rules: CalendarRules) {
        editPreference("setCalendarRules") {
            it[Keys.EPIPHANY_SUNDAY] = rules.epiphanyOnSunday
            it[Keys.ASCENSION_SUNDAY] = rules.ascensionOnSunday
            it[Keys.CORPUS_SUNDAY] = rules.corpusChristiOnSunday
        }
    }

    suspend fun setCalendarTransfer(celebration: String, onSunday: Boolean) {
        val key = when (celebration) {
            "epiphany" -> Keys.EPIPHANY_SUNDAY
            "ascension" -> Keys.ASCENSION_SUNDAY
            "corpus" -> Keys.CORPUS_SUNDAY
            else -> error("Unknown calendar transfer")
        }
        editPreference("setCalendarTransfer") { it[key] = onSunday }
    }

    suspend fun setLanguage(language: AppLanguage) {
        editPreference("setLanguage") {
            it[Keys.LANGUAGE] = language.code
            it[Keys.HAS_CHOSEN_LANGUAGE] = true
        }
    }

    suspend fun setDesignLanguage(id: String) {
        editPreference("setDesignLanguage") { it[Keys.DESIGN_LANGUAGE] = canonicalDesign(id) }
    }

    private fun canonicalDesign(id: String?): String = when (id) {
        "SANCTUARY", "VATICAN" -> "SANCTUARY"
        else -> UserPreferences.DEFAULT_DESIGN_LANGUAGE
    }

    suspend fun setReduceMotion(value: Boolean) {
        editPreference("setReduceMotion") { it[Keys.REDUCE_MOTION] = value }
    }

    suspend fun setReduceTransparency(value: Boolean) {
        editPreference("setReduceTransparency") { it[Keys.REDUCE_TRANSPARENCY] = value }
    }

    suspend fun setHighContrast(value: Boolean) {
        editPreference("setHighContrast") { it[Keys.HIGH_CONTRAST] = value }
    }

    suspend fun setThemePreset(id: String) {
        editPreference("setThemePreset") { it[Keys.THEME_PRESET] = id }
    }

    suspend fun setDarkModeSetting(setting: String) {
        editPreference("setDarkModeSetting") { it[Keys.DARK_MODE] = setting }
    }

    suspend fun setFontFamilyChoice(choice: String) {
        editPreference("setFontFamilyChoice") { it[Keys.FONT_FAMILY] = com.example.data.models.FontChoice.fromId(choice).id }
    }

    suspend fun setFontSizeScale(scale: Float) {
        editPreference("setFontSizeScale") { it[Keys.FONT_SCALE] = scale }
    }

    suspend fun setAudioNarrationSpeed(speed: Float) {
        editPreference("setAudioNarrationSpeed") { it[Keys.NARRATION_SPEED] = speed }
    }

    suspend fun setActiveBibleTranslationId(id: String) {
        editPreference("setActiveBibleTranslationId") { it[Keys.BIBLE_TRANSLATION] = id }
    }

    suspend fun setFullCccDownloaded(downloaded: Boolean) {
        editPreference("setFullCccDownloaded") { it[Keys.FULL_CCC_DOWNLOADED] = downloaded }
    }

    /** Writes an entire appearance selection in one transaction. */
    suspend fun saveAppearance(
        designLanguage: String,
        themePreset: String,
        darkModeSetting: String,
        fontFamilyChoice: String,
        fontSizeScale: Float,
        audioNarrationSpeed: Float,
        updateFontFamily: Boolean = true
    ) {
        editPreference("saveAppearance") {
            it[Keys.DESIGN_LANGUAGE] = canonicalDesign(designLanguage)
            it[Keys.THEME_PRESET] = themePreset
            it[Keys.DARK_MODE] = darkModeSetting
            if (updateFontFamily) it[Keys.FONT_FAMILY] = com.example.data.models.FontChoice.fromId(fontFamilyChoice).id
            it[Keys.FONT_SCALE] = fontSizeScale
            it[Keys.NARRATION_SPEED] = audioNarrationSpeed
        }
    }
}
