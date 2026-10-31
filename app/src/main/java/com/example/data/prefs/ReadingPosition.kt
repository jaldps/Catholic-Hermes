package com.example.data.prefs

import android.content.Context

/**
 * The last place the reader stopped in the Bible: one book and chapter, kept
 * on the device so the Bible and Home can offer "Continue reading".
 */
object ReadingPosition {
    private const val FILE = "reading_position"
    private const val BOOK = "book_id"
    private const val CHAPTER = "chapter"

    data class Position(val bookId: String, val chapter: Int)

    fun load(context: Context): Position? = runCatching {
        val prefs = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
        val book = prefs.getString(BOOK, null) ?: return null
        Position(book, prefs.getInt(CHAPTER, 1).coerceAtLeast(1))
    }.getOrNull()

    fun save(context: Context, bookId: String, chapter: Int) {
        runCatching {
            context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
                .edit()
                .putString(BOOK, bookId)
                .putInt(CHAPTER, chapter)
                .apply()
        }
    }
}
