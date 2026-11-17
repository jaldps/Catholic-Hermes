package com.example.data.search

import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * What the search screen talks to: the shared index, the reference reader and the Bible text search, all
 * bound to one repository. Stateless apart from [SearchIndexHolder]; nothing a person types is kept.
 */
class GlobalSearch(
    private val repository: CatholicDataRepository,
    private val sections: List<SearchSection>
) {
    /** The index for [language], built on IO the first time and reused afterwards. */
    suspend fun index(language: AppLanguage): SearchIndex =
        SearchIndexHolder.get(language) { SearchSources.build(repository, language, sections) }

    private fun edition(language: AppLanguage): String =
        repository.resolvedEditionId(repository.activeBibleEditionId.value, language)

    /** The query read as a Bible or Catechism reference, or null. */
    fun reference(query: String, language: AppLanguage): SearchResult? =
        SearchSources.reference(query, language, edition(language))

    /** Up to ~50 verses of the edition being read, searched on IO. */
    suspend fun bible(query: String, language: AppLanguage, limit: Int = 50): SearchGroup? = withContext(Dispatchers.IO) {
        SearchSources.bibleText(repository, query, language, edition(language), limit)
    }
}
