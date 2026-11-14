package com.example.data.repository

import com.example.data.models.Novena

/** Stable legacy facade; content bodies are assets, not executable singletons. */
object CrawledNovenasData {
    val allNovenas: List<Novena> get() = NovenaCatalog.default.allNovenas
}
