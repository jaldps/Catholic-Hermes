package com.example

import com.example.data.models.AppLanguage
import com.example.data.repository.DevotionsAndHistoryData
import org.junit.Assert.*
import org.junit.Test

class SacredHeartPromisesTest {
    @Test fun theTraditionalPromisesAndTheirAttributionRemainAvailableInEveryLanguage() {
        val heart = DevotionsAndHistoryData.christManifestations.first { it.id == "sacred_heart_jesus" }
        for (language in AppLanguage.entries) {
            val text = heart.promises.get(language)
            val numbered = Regex("(?m)^(\\d{1,2})\\.").findAll(text).map { it.groupValues[1].toInt() }.toList()
            assertEquals(language.name, (1..12).toList(), numbered)
            assertTrue(text.contains("1688"))
            assertTrue(text.contains("Saumaise"))
            assertTrue(text.contains("171"))
            assertTrue(text.contains("1385"))
            assertFalse(text.contains("garantía automática"))
        }
        assertTrue(heart.promises.es.contains("Paz en las familias"))
        assertTrue(heart.promises.es.contains("Consuelo en las aflicciones"))
        assertTrue(heart.promises.es.contains("Bendiciones"))
        assertTrue(heart.promises.es.contains("no morir en su desgracia ni sin los sacramentos"))
    }
}
