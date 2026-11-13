package com.example

import com.example.ui.screens.verseIndexForVisibleItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BiblePositionMappingTest {
    @Test fun openingAndFirstVerseBothResumeAtFirstVerse() {
        assertEquals(0, verseIndexForVisibleItem(0, 1, 20))
        assertEquals(0, verseIndexForVisibleItem(1, 1, 20))
    }
    @Test fun middleAndFinalVerseKeepTheirOwnPosition() {
        assertEquals(9, verseIndexForVisibleItem(10, 1, 20))
        assertEquals(19, verseIndexForVisibleItem(20, 1, 20))
        assertEquals(19, verseIndexForVisibleItem(21, 1, 20))
    }
    @Test fun unavailableChapterDoesNotSaveAnInventedVerse() {
        assertNull(verseIndexForVisibleItem(0, 1, 0))
    }
}
