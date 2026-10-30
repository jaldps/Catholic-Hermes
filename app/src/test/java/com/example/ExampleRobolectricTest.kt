package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Sancta Fides", appName)
  }

  @Test
  fun `verify full catholic 73 books and real chapter text`() {
    // Built with a Context so the bundled Bible assets are read. Without one
    // there is no text at all: the synthetic placeholder verses this test was
    // originally written against have been removed.
    val repo = com.example.data.repository.CatholicDataRepository(
      androidx.test.core.app.ApplicationProvider.getApplicationContext()
    )
    assertEquals(73, repo.catholicBooks.size)

    val genesis = repo.catholicBooks.first { it.id == "gen" }
    assertEquals(50, genesis.chaptersCount)

    val ch1 = repo.getChapter("gen", 1)
    assertEquals(1, ch1.chapterNumber)
    org.junit.Assert.assertTrue(
      "Genesis 1:1 was: ${ch1.verses.firstOrNull()?.text?.en}",
      ch1.verses.first().text.en.startsWith("In the beginning God created heaven")
    )

    val ch15 = repo.getChapter("gen", 15)
    assertEquals(15, ch15.chapterNumber)
    org.junit.Assert.assertTrue(ch15.verses.size > 5)

    // The last chapter of the last book must be reachable too — the defect
    // this guards against made every book after Esther return another's text.
    val revelation = repo.getChapter("rev", 22)
    org.junit.Assert.assertTrue(
      "Revelation 22 was: ${revelation.verses.firstOrNull()?.text?.en}",
      revelation.verses.isNotEmpty()
    )
  }

  @Test
  fun `verify catechism 4 pillars and article lookup`() {
    val repo = com.example.data.repository.CatholicDataRepository()
    assertEquals(4, repo.catechismParts.size)

    val eucharistArticle = repo.findCatechismArticle(1324)
    org.junit.Assert.assertNotNull(eucharistArticle)
    org.junit.Assert.assertEquals(1324, eucharistArticle?.cccNumber)
  }

  @Test
  fun `verify parishes, pilgrimages, and habits data`() {
    val repo = com.example.data.repository.CatholicDataRepository()
    org.junit.Assert.assertTrue(repo.nearbyParishes.isNotEmpty())
    org.junit.Assert.assertTrue(repo.globalPilgrimages.isNotEmpty())
    org.junit.Assert.assertTrue(repo.spiritualHabitsList.isNotEmpty())
    org.junit.Assert.assertTrue(repo.liturgicalCalendarDays.isNotEmpty())

    val todayLiturgicalDay = repo.getCurrentLiturgicalDay()
    org.junit.Assert.assertNotNull(todayLiturgicalDay.sundayCycle)
    org.junit.Assert.assertNotNull(todayLiturgicalDay.weekdayCycle)
    org.junit.Assert.assertEquals(0xFF2C6B3E, todayLiturgicalDay.liturgicalColorHex)
  }

  @Test
  fun `verify room habit seeding and sin overcome logging`() = kotlinx.coroutines.runBlocking {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val database = androidx.room.Room.inMemoryDatabaseBuilder(
      context,
      com.example.data.local.SanctaFidesDatabase::class.java
    ).allowMainThreadQueries().build()
    val dao = database.dao()

    val repo = com.example.data.repository.CatholicDataRepository()
    dao.insertDefaultHabits(repo.defaultHabitsToSeed)

    val habits = dao.getAllHabits().first()
    org.junit.Assert.assertTrue(habits.size >= 7)

    // Log completion
    dao.insertCompletion(
      com.example.data.local.HabitCompletionLogEntity(
        habitId = habits.first().id,
        completionDate = "2026-10-25"
      )
    )
    val completions = dao.getCompletionsForDate("2026-10-25").first()
    org.junit.Assert.assertEquals(1, completions.size)

    // Log sin overcome micro-effort
    dao.insertSinOvercomeLog(
      com.example.data.local.SinOvercomeLogEntity(
        sinKey = "LUST",
        counterVirtue = "Castidad",
        prayerUsed = "HAIL_MARY",
        note = "Vencí la tentación con el Ave María"
      )
    )
    val totalVictories = dao.getTotalVictoriesCount().first()
    org.junit.Assert.assertEquals(1, totalVictories)

    val lustCount = dao.getSinOvercomeCount("LUST").first()
    org.junit.Assert.assertEquals(1, lustCount)

    // Verify Liturgical Day Room entity insertion and query
    dao.insertLiturgicalDays(repo.defaultLiturgicalDaysToSeed)
    val todayEntity = dao.getLiturgicalDay("2026-10-25").first()
    org.junit.Assert.assertNotNull(todayEntity)
    org.junit.Assert.assertEquals("Ciclo B", todayEntity?.sundayCycle)
    org.junit.Assert.assertEquals("Año II", todayEntity?.weekdayCycle)
    org.junit.Assert.assertTrue(todayEntity?.gospelText?.isNotEmpty() == true)

    // Verify Scripture Study progress Room entity
    dao.saveScriptureProgress(
      com.example.data.local.ScriptureStudyProgressEntity(
        lessonId = "sac_3_eucharist",
        trackId = "track_sacraments",
        isCompleted = true,
        personalNotes = "Meditación sobre Juan 6 y la Eucaristía como fuente y cumbre."
      )
    )
    val completedCount = dao.getCompletedLessonCountForTrack("track_sacraments").first()
    org.junit.Assert.assertEquals(1, completedCount)

    val progressItem = dao.getScriptureProgress("sac_3_eucharist").first()
    org.junit.Assert.assertNotNull(progressItem)
    org.junit.Assert.assertTrue(progressItem?.isCompleted == true)
    org.junit.Assert.assertEquals("track_sacraments", progressItem?.trackId)

    database.close()
  }

  @Test
  fun `verify compline night office and marian antiphons`() {
    val repo = com.example.data.repository.CatholicDataRepository()
    val compline = repo.complineOffice
    org.junit.Assert.assertNotNull(compline)
    org.junit.Assert.assertTrue(compline.psalmText.es.contains("habitas al amparo del Altísimo"))
    org.junit.Assert.assertTrue(compline.canticleOfSimeonLatin.contains("Nunc dimíttis"))

    val examenSteps = repo.ignatianExamenSteps
    org.junit.Assert.assertEquals(5, examenSteps.size)

    org.junit.Assert.assertTrue(compline.marianAntiphons.size >= 3)
    val salveRegina = compline.marianAntiphons.first { it.id == "salve_regina" }
    org.junit.Assert.assertTrue(salveRegina.latinText.contains("Salve, Regína"))
  }

  @Test
  fun `verify vatican scripture study course tracks and lectio divina`() {
    val repo = com.example.data.repository.CatholicDataRepository()
    val tracks = repo.scriptureStudyTracks
    org.junit.Assert.assertEquals(3, tracks.size)

    val sacramentsTrack = tracks.first { it.id == "track_sacraments" }
    org.junit.Assert.assertTrue(sacramentsTrack.lessons.size >= 6)

    val eucharistLesson = sacramentsTrack.lessons.first { it.id == "sac_3_eucharist" }
    org.junit.Assert.assertEquals("Juan 6, 51-58 y 1 Corintios 11, 23-26", eucharistLesson.biblicalReference)
    org.junit.Assert.assertTrue(eucharistLesson.lectioStep.es.isNotEmpty())
    org.junit.Assert.assertTrue(eucharistLesson.meditatioStep.es.isNotEmpty())
    org.junit.Assert.assertTrue(eucharistLesson.oratioStep.es.isNotEmpty())
    org.junit.Assert.assertTrue(eucharistLesson.contemplatioStep.es.isNotEmpty())
    org.junit.Assert.assertTrue(eucharistLesson.catechismRefs.any { it.contains("1322") || it.contains("CIC") })
  }

  @Test
  fun `verify complete 9-day novenas have all nine authentic days`() {
    val sacredHeart = com.example.data.repository.CompleteNovenasData.sacredHeartNovena
    org.junit.Assert.assertEquals(9, sacredHeart.days.size)
    sacredHeart.days.forEachIndexed { idx, day ->
      org.junit.Assert.assertEquals(idx + 1, day.dayNumber)
      org.junit.Assert.assertTrue(day.scripture.isNotEmpty())
      org.junit.Assert.assertTrue(day.meditation.es.isNotEmpty())
      org.junit.Assert.assertTrue(day.prayer.es.isNotEmpty())
    }

    val divineMercy = com.example.data.repository.CompleteNovenasData.divineMercyNovena
    org.junit.Assert.assertEquals(9, divineMercy.days.size)
    divineMercy.days.forEachIndexed { idx, day ->
      org.junit.Assert.assertEquals(idx + 1, day.dayNumber)
      org.junit.Assert.assertTrue(day.meditation.es.isNotEmpty())
      org.junit.Assert.assertTrue(day.prayer.es.isNotEmpty())
    }
  }

  @Test
  fun `verify extensive marian apparitions and christ devotions directory`() {
    val repo = com.example.data.repository.CatholicDataRepository()
    val marianList = repo.marianApparitions
    val christList = repo.christManifestations

    // Verify extensive presence (dozens of devotions)
    org.junit.Assert.assertTrue(marianList.size >= 12)
    org.junit.Assert.assertTrue(christList.size >= 8)

    // Verify key Marian apparitions
    val virgenValle = marianList.firstOrNull { it.id == "virgen_del_valle" }
    org.junit.Assert.assertNotNull(virgenValle)
    org.junit.Assert.assertEquals("Venezuela", virgenValle?.country?.es)
    org.junit.Assert.assertTrue(virgenValle?.patronageOf?.es?.isNotEmpty() == true)
    org.junit.Assert.assertTrue(virgenValle?.primarySanctuary?.es?.isNotEmpty() == true)

    val caridadCobre = marianList.firstOrNull { it.id == "caridad_del_cobre" }
    org.junit.Assert.assertNotNull(caridadCobre)
    org.junit.Assert.assertEquals("Cuba", caridadCobre?.country?.es)

    val aparecida = marianList.firstOrNull { it.id == "aparecida" }
    org.junit.Assert.assertNotNull(aparecida)
    org.junit.Assert.assertEquals("Brasil", aparecida?.country?.es)

    // Verify key Christological devotions
    val ninoAtocha = christList.firstOrNull { it.id == "nino_de_atocha" }
    org.junit.Assert.assertNotNull(ninoAtocha)
    org.junit.Assert.assertTrue(ninoAtocha?.originHistory?.es?.isNotEmpty() == true)

    val nazarenoSanPablo = christList.firstOrNull { it.id == "nazareno_de_san_pablo" }
    org.junit.Assert.assertNotNull(nazarenoSanPablo)
    org.junit.Assert.assertTrue(nazarenoSanPablo?.recordedMiracles?.es?.isNotEmpty() == true)
  }
}


