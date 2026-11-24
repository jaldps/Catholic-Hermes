package com.example.ui.screens

import com.example.data.models.AppLanguage
import com.example.data.repository.Chaplet

/**
 * What differs between the devotions of the guided-prayer screen, as data: the stable test tags, the
 * names the screen and the notification use, and whether the devotion has mysteries (paintings per
 * decade, the mysteries picker, «Ver el misterio») or is a plain sequence. The Rosary and the Divine
 * Mercy chaplet keep exactly their original tags; every other chaplet shares one set.
 */
internal class DevotionSpec(
    val devotion: Devotion,
    val heroTitleTag: String,
    val heroPhotoTag: String,
    val stepCounterTag: String,
    val reelTag: String,
    val prevTag: String,
    val nextTag: String,
    val audioTag: String,
    /** The Rosary: mysteries, a painting per decade, the mysteries picker. */
    val hasMysteries: Boolean,
    /** The map in Options draws the rosary's loop (false) or one closed ring (true). */
    val ringMap: Boolean,
    val notificationName: (AppLanguage) -> String,
    val optionsTitle: (AppLanguage) -> String,
    val partsLabel: (AppLanguage) -> String
) {
    val isRosary: Boolean get() = devotion == Devotion.ROSARY
    val isMercy: Boolean get() = devotion == Devotion.MERCY
    val isOther: Boolean get() = devotion == Devotion.OTHER

    companion object {
        private fun AppLanguage.pick(es: String, en: String, pt: String) = when (this) {
            AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt
        }

        val Rosary = DevotionSpec(
            Devotion.ROSARY, "rosary_hero_title", "rosary_hero_photo", "rosary_hero_step_counter",
            "prayer_bead_counter", "prev_bead_button", "next_bead_button", "toggle_prayer_audio_button",
            hasMysteries = true, ringMap = false,
            notificationName = { it.pick("Santo Rosario", "Holy Rosary", "Santo Terço") },
            optionsTitle = { it.pick("Santo Rosario", "Holy Rosary", "Santo Terço") },
            partsLabel = { it.pick("Partes del Rosario", "Parts of the Rosary", "Partes do Rosário") }
        )

        val Mercy = DevotionSpec(
            Devotion.MERCY, "mercy_hero_title", "mercy_hero_photo", "mercy_hero_step_counter",
            "mercy_step_counter", "prev_mercy_step_button", "next_mercy_step_button", "toggle_mercy_audio_button",
            hasMysteries = false, ringMap = false,
            notificationName = { it.pick("Coronilla de la Divina Misericordia", "Chaplet of Divine Mercy", "Terço da Divina Misericórdia") },
            optionsTitle = { it.pick("Coronilla", "Chaplet", "Coroinha") },
            partsLabel = { it.pick("Pasos de la coronilla", "Steps of the chaplet", "Passos da coroinha") }
        )

        /** Any chaplet of the catalogue; [chaplet] gives the notification and sheet their name. */
        fun other(chaplet: Chaplet, ring: Boolean = true) = DevotionSpec(
            Devotion.OTHER, "chaplet_hero_title", "chaplet_hero_photo", "chaplet_step_counter",
            "chaplet_bead_counter", "prev_chaplet_step_button", "next_chaplet_step_button", "toggle_chaplet_audio_button",
            hasMysteries = false, ringMap = ring,
            notificationName = { chaplet.title.get(it) },
            optionsTitle = { chaplet.title.get(it) },
            partsLabel = { if (chaplet.hasBeads) it.pick("Partes de la coronilla", "Parts of the chaplet", "Partes da coroinha")
                else it.pick("Pasos de la coronilla", "Steps of the chaplet", "Passos da coroinha") }
        )
    }
}
