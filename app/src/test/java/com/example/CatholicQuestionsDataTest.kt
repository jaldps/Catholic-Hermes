package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.CatholicQuestionsData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** 101 Catholic Questions: apologetics Q&A in ES/EN/PT. */
class CatholicQuestionsDataTest {

    private val languages = listOf(AppLanguage.ES, AppLanguage.EN, AppLanguage.PT)

    @Test
    fun oneHundredOneQuestionsNumberedInOrder() {
        assertEquals(101, CatholicQuestionsData.questions.size)
        assertEquals((1..101).toList(), CatholicQuestionsData.questions.map { it.number })
    }

    @Test
    fun everyQuestionExistsInEveryLanguage() {
        for (q in CatholicQuestionsData.questions) {
            for (lang in languages) {
                assertTrue("Q${q.number} ${lang.code} question", q.question.get(lang).length > 20)
                assertTrue("Q${q.number} ${lang.code} answer", q.answer.get(lang).length > 200)
                assertTrue("Q${q.number} ${lang.code} foundation", q.foundation.get(lang).isNotBlank())
                assertTrue("Q${q.number} ${lang.code} clarification", q.clarification.get(lang).length > 50)
                assertTrue("Q${q.number} ${lang.code} section", q.section.get(lang).isNotBlank())
            }
        }
    }

    @Test
    fun elevenSectionsPresent() {
        val sections = CatholicQuestionsData.questions
            .map { it.section.es }.distinct()
        assertEquals(11, sections.size)
    }
}
