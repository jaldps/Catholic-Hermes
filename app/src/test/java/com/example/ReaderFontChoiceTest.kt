package com.example

import com.example.data.models.AppLanguage
import com.example.data.models.FontChoice
import com.example.ui.theme.AppFonts
import com.example.ui.theme.AppTypographyFactory
import com.example.ui.theme.DesignLanguage
import com.example.ui.theme.ReaderFontChoice
import org.junit.Assert.assertEquals
import org.junit.Test

class ReaderFontChoiceTest {
    @Test fun readerFollowsEveryGlobalFamilyUntilAnExplicitLocalChoice() {
        for (identity in DesignLanguage.entries) {
            for (global in FontChoice.entries) {
                val base = AppTypographyFactory.build(identity, true, 1.3f, global.id).second.scripture
                assertEquals("${identity.id}/${global.id}", base.fontFamily,
                    ReaderFontChoice.FOLLOW_APP.family(base.fontFamily, identity))
                assertEquals(AppFonts.EbGaramond, ReaderFontChoice.SERIF.family(base.fontFamily, identity))
                assertEquals(if (identity == DesignLanguage.MATERIAL) AppFonts.Roboto else AppFonts.Inter,
                    ReaderFontChoice.SANS.family(base.fontFamily, identity))
            }
        }
    }

    @Test fun cycleReturnsToTheAppChoiceAndLabelsAreLocalized() {
        val default = ReaderFontChoice.FOLLOW_APP
        assertEquals(ReaderFontChoice.SERIF, default.next())
        assertEquals(ReaderFontChoice.SANS, default.next().next())
        assertEquals(default, default.next().next().next())
        assertEquals("Default", default.label(AppLanguage.EN))
        assertEquals("Por defecto", default.label(AppLanguage.ES))
        assertEquals("Padrão", default.label(AppLanguage.PT))
        assertEquals("Sem serifa", ReaderFontChoice.SANS.label(AppLanguage.PT))
    }
}
