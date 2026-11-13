package com.example

import com.example.data.models.FontChoice
import com.example.ui.theme.AppFonts
import com.example.ui.theme.AppTypographyFactory
import com.example.ui.theme.DesignLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class FontTypographyTest {
    @Test fun explicitFamiliesChangeReadingAndChromeInEveryIdentityWithoutChangingScale() {
        for (identity in DesignLanguage.selectable) {
            for (choice in listOf(FontChoice.INTER, FontChoice.ROBOTO, FontChoice.EB_GARAMOND)) {
                val (type, app) = AppTypographyFactory.build(identity, true, 1.3f, choice.id)
                val expected = AppFonts.family(choice)
                val slots = listOf(type.displayLarge, type.displayMedium, type.displaySmall,
                    type.headlineLarge, type.headlineMedium, type.headlineSmall,
                    type.titleLarge, type.titleMedium, type.titleSmall,
                    type.bodyLarge, type.bodyMedium, type.bodySmall,
                    type.labelLarge, type.labelMedium, type.labelSmall,
                    app.screenTitle, app.screenSubtitle, app.sectionEyebrow, app.sectionTitle,
                    app.scripture, app.scriptureDropCap, app.verseNumber, app.rowTitle,
                    app.rowSubtitle, app.navLabel, app.buttonLabel, app.metric, app.quote,
                    app.display, app.largeTitle, app.title1, app.title2, app.title3,
                    app.headline, app.body, app.callout, app.subhead, app.footnote,
                    app.eyebrow, app.caption, app.reading)
                slots.forEach { assertEquals("${identity.id}/${choice.id}", expected, it.fontFamily) }
                val base = AppTypographyFactory.build(identity, true, 1.3f)
                assertEquals(base.first.bodyLarge.fontSize, type.bodyLarge.fontSize)
                assertEquals(base.second.scripture.lineHeight, app.scripture.lineHeight)
            }
        }
    }

    @Test fun identityDefaultsRetainDistinctEditorialHierarchyAndLegacyFontPreferencesWork() {
        for (identity in DesignLanguage.selectable) {
            assertEquals(AppTypographyFactory.build(identity, true, 1f),
                AppTypographyFactory.build(identity, true, 1f, "IDENTITY_DEFAULT"))
        }
        val defaults = AppTypographyFactory.build(DesignLanguage.APPLE, true, 1f, "IDENTITY_DEFAULT")
        assertEquals(AppFonts.Inter, defaults.second.screenTitle.fontFamily)
        val sanctuary = AppTypographyFactory.build(DesignLanguage.SANCTUARY, true, 1f, "IDENTITY_DEFAULT")
        assertEquals(AppFonts.EbGaramond, sanctuary.second.screenTitle.fontFamily)
        assertEquals(AppFonts.Inter, sanctuary.second.buttonLabel.fontFamily)
        assertEquals(AppFonts.EbGaramond, sanctuary.second.scripture.fontFamily)
        assertEquals(AppFonts.EbGaramond, defaults.second.scripture.fontFamily)
        assertEquals(AppFonts.Inter,
            AppTypographyFactory.build(DesignLanguage.IVE, true, 1f, "SANS").second.scripture.fontFamily)
        assertEquals(AppFonts.EbGaramond,
            AppTypographyFactory.build(DesignLanguage.APPLE, false, 1f, "SERIF").second.navLabel.fontFamily)
    }
}
