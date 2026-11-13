package com.example.ui.screens

import android.content.Context
import com.example.data.models.RosaryMysteryType

/** Durable unfinished prayer state, independent of activity and tab lifetimes. */
internal object RosarySessionStore {
    private fun preferences(context: Context) = context.applicationContext.getSharedPreferences("unfinished_prayer_session", Context.MODE_PRIVATE)
    fun load(context: Context): RosarySession? {
        val p = preferences(context)
        if (!p.contains("rosary_step")) return null
        val mystery = p.getString("mystery", null)?.let { value -> RosaryMysteryType.values().firstOrNull { it.name == value } }
        return RosarySession(
            devotion = Devotion.fromTab(p.getInt("devotion", 0)),
            mysterySelection = mystery?.let { MysterySelection.Picked(it) } ?: MysterySelection.ByDay,
            rosaryStep = p.getInt("rosary_step", 0).coerceAtLeast(0),
            mercyStep = p.getInt("mercy_step", 0).coerceAtLeast(0),
            instructionsOn = p.getBoolean("instructions", true)
        )
    }
    fun save(context: Context, session: RosarySession, today: RosaryMysteryType) {
        preferences(context).edit()
            .putInt("devotion", session.devotion.tab)
            .putString("mystery", session.mysterySet(today).name)
            .putInt("rosary_step", session.rosaryStep)
            .putInt("mercy_step", session.mercyStep)
            .putBoolean("instructions", session.instructionsOn)
            .apply()
    }
}
