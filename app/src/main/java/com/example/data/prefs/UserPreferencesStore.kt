package com.example.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.models.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "sancta_fides_preferences"
)

/**
 * Every user-facing preference, persisted with DataStore so it survives process
 * death and the Room database's destructive migrations.
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
    val hasChosenLanguage: Boolean
) {
    companion object {
        const val DEFAULT_DESIGN_LANGUAGE = "VATICAN"
        const val DEFAULT_THEME_PRESET = "LITURGICAL_DYNAMIC"
        const val DEFAULT_DARK_MODE = "SYSTEM"
        const val DEFAULT_FONT_FAMILY = "SERIF"
    }
}

class UserPreferencesStore(private val context: Context) {

    private object Keys {
        val LANGUAGE = stringPreferencesKey("language")
        val HAS_CHOSEN_LANGUAGE = booleanPreferencesKey("has_chosen_language")
        val DESIGN_LANGUAGE = stringPreferencesKey("design_language")
        val THEME_PRESET = stringPreferencesKey("theme_preset")
        val DARK_MODE = stringPreferencesKey("dark_mode_setting")
        val FONT_FAMILY = stringPreferencesKey("font_family_choice")
        val FONT_SCALE = floatPreferencesKey("font_size_scale")
        val NARRATION_SPEED = floatPreferencesKey("audio_narration_speed")
        val BIBLE_TRANSLATION = stringPreferencesKey("active_bible_translation_id")
        val FULL_CCC_DOWNLOADED = booleanPreferencesKey("is_full_ccc_downloaded")
    }

    /**
     * Emits the stored preferences, falling back to defaults for anything unset.
     * A read failure yields defaults rather than propagating, so a corrupt file
     * cannot keep the app from starting.
     */
    val preferences: Flow<UserPreferences> = context.preferencesDataStore.data
        .catch { cause ->
            if (cause is IOException) emit(emptyPreferences()) else throw cause
        }
        .map { stored ->
            val code = stored[Keys.LANGUAGE]
            UserPreferences(
                // AppLanguage.fromCode falls back to the device locale when unset.
                language = AppLanguage.fromCode(code ?: ""),
                hasChosenLanguage = stored[Keys.HAS_CHOSEN_LANGUAGE] ?: (code != null),
                designLanguage = stored[Keys.DESIGN_LANGUAGE]
                    ?: UserPreferences.DEFAULT_DESIGN_LANGUAGE,
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

    suspend fun setLanguage(language: AppLanguage) {
        context.preferencesDataStore.edit {
            it[Keys.LANGUAGE] = language.code
            it[Keys.HAS_CHOSEN_LANGUAGE] = true
        }
    }

    suspend fun setDesignLanguage(id: String) {
        context.preferencesDataStore.edit { it[Keys.DESIGN_LANGUAGE] = id }
    }

    suspend fun setThemePreset(id: String) {
        context.preferencesDataStore.edit { it[Keys.THEME_PRESET] = id }
    }

    suspend fun setDarkModeSetting(setting: String) {
        context.preferencesDataStore.edit { it[Keys.DARK_MODE] = setting }
    }

    suspend fun setFontFamilyChoice(choice: String) {
        context.preferencesDataStore.edit { it[Keys.FONT_FAMILY] = choice }
    }

    suspend fun setFontSizeScale(scale: Float) {
        context.preferencesDataStore.edit { it[Keys.FONT_SCALE] = scale }
    }

    suspend fun setAudioNarrationSpeed(speed: Float) {
        context.preferencesDataStore.edit { it[Keys.NARRATION_SPEED] = speed }
    }

    suspend fun setActiveBibleTranslationId(id: String) {
        context.preferencesDataStore.edit { it[Keys.BIBLE_TRANSLATION] = id }
    }

    suspend fun setFullCccDownloaded(downloaded: Boolean) {
        context.preferencesDataStore.edit { it[Keys.FULL_CCC_DOWNLOADED] = downloaded }
    }

    /** Writes an entire appearance selection in one transaction. */
    suspend fun saveAppearance(
        designLanguage: String,
        themePreset: String,
        darkModeSetting: String,
        fontFamilyChoice: String,
        fontSizeScale: Float,
        audioNarrationSpeed: Float
    ) {
        context.preferencesDataStore.edit {
            it[Keys.DESIGN_LANGUAGE] = designLanguage
            it[Keys.THEME_PRESET] = themePreset
            it[Keys.DARK_MODE] = darkModeSetting
            it[Keys.FONT_FAMILY] = fontFamilyChoice
            it[Keys.FONT_SCALE] = fontSizeScale
            it[Keys.NARRATION_SPEED] = audioNarrationSpeed
        }
    }
}
