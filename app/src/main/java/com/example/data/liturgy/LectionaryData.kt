package com.example.data.liturgy

/**
 * The readings appointed for one celebration.
 *
 * Citations are [ScriptureReference] strings — canonical book id, chapter, and
 * an optional verse range — resolved against the bundled Douay-Rheims text.
 * Two consequences follow from that translation, and they shape the whole table:
 *
 *  - Psalms carry **Vulgate** numbers, which for Psalms 10–146 run one behind the
 *    Hebrew numbers the modern lectionary prints (its Ps 23 is `psa 22` here).
 *    Psalms are therefore cited whole, without a verse range: the Vulgate counts
 *    a psalm's title as a verse inconsistently, so a range copied from the
 *    lectionary would silently land on the wrong lines, while a whole psalm
 *    always resolves to the right text.
 *  - A reading that the lectionary spreads over two chapters is narrowed to the
 *    chapter that carries it, and a reading made of several fragments is given as
 *    the span that covers them, since a reference holds one chapter and one range.
 */
data class LectionaryEntry(
    val firstReading: String,
    val responsorialPsalm: String,
    val secondReading: String?,   // null on most weekdays
    val gospel: String
)

/**
 * The Sunday and Solemnity readings of the *Lectionary for Mass* (Ordinary Form,
 * General Roman Calendar), keyed by the `lectionaryKey` that
 * [LiturgicalDayInfo] carries.
 *
 * Only celebrations whose readings are proper to the day are listed; a weekday
 * with no entry resolves to null and the caller decides what to show.
 */
object LectionaryData {

    /** Keyed by "<lectionaryKey>" for celebrations that do not vary by cycle. */
    val fixed: Map<String, LectionaryEntry> = mapOf(
        // Christmas and its octave
        "christmas_day" to LectionaryEntry("isa 52:7-10", "psa 97", "heb 1:1-6", "joh 1:1-18"),
        "st_stephen" to LectionaryEntry("act 7:54-59", "psa 30", null, "mat 10:17-22"),
        "st_john" to LectionaryEntry("1jo 1:1-4", "psa 96", null, "joh 20:2-8"),
        "holy_innocents" to LectionaryEntry("1jo 1:5-10", "psa 123", null, "mat 2:13-18"),
        "mary_mother_of_god" to LectionaryEntry("num 6:22-27", "psa 66", "gal 4:4-7", "luk 2:16-21"),
        "epiphany" to LectionaryEntry("isa 60:1-6", "psa 71", "eph 3:2-6", "mat 2:1-12"),
        // The Baptism takes the Year A set; its Gospel alone varies by cycle.
        "baptism_of_the_lord" to LectionaryEntry("isa 42:1-7", "psa 28", "act 10:34-38", "mat 3:13-17"),

        // Lent and the Triduum
        "ash_wednesday" to LectionaryEntry("joe 2:12-18", "psa 50", "2co 5:20-21", "mat 6:1-18"),
        // The Passion runs John 18:1—19:42; the chapter of the Crucifixion is given.
        "good_friday" to LectionaryEntry("isa 53:1-12", "psa 30", "heb 4:14-16", "joh 19"),
        "easter_sunday" to LectionaryEntry("act 10:34-43", "psa 117", "col 3:1-4", "joh 20:1-9"),

        // Solemnities and Feasts of the Lord and of the Blessed Virgin Mary
        "presentation" to LectionaryEntry("mal 3:1-4", "psa 23", "heb 2:14-18", "luk 2:22-40"),
        "st_joseph" to LectionaryEntry("2sa 7:4-16", "psa 88", "rom 4:13-22", "mat 1:18-24"),
        "annunciation" to LectionaryEntry("isa 7:10-14", "psa 39", "heb 10:4-10", "luk 1:26-38"),
        "sacred_heart" to LectionaryEntry("deu 7:6-11", "psa 102", "1jo 4:7-16", "mat 11:25-30"),
        "immaculate_heart" to LectionaryEntry("isa 61:9-11", "1sa 2:1-8", null, "luk 2:41-51"),
        "john_baptist_nativity" to LectionaryEntry("isa 49:1-6", "psa 138", "act 13:22-26", "luk 1:57-80"),
        "peter_and_paul" to LectionaryEntry("act 12:1-11", "psa 33", "2ti 4:6-18", "mat 16:13-19"),
        // The Transfiguration keeps one set of readings; its Gospel varies by cycle.
        "transfiguration" to LectionaryEntry("dan 7:9-14", "psa 96", "2pe 1:16-19", "mat 17:1-9"),
        "assumption" to LectionaryEntry("rev 12:1-6", "psa 44", "1co 15:20-27", "luk 1:39-56"),
        "holy_cross" to LectionaryEntry("num 21:4-9", "psa 77", "phi 2:6-11", "joh 3:13-17"),
        "all_saints" to LectionaryEntry("rev 7:2-14", "psa 23", "1jo 3:1-3", "mat 5:1-12"),
        // All Souls offers a wide choice; the first option of each slot is given.
        "all_souls" to LectionaryEntry("wis 3:1-9", "psa 22", "rom 5:5-11", "mat 5:1-12"),
        "immaculate_conception" to LectionaryEntry("gen 3:9-20", "psa 97", "eph 1:3-12", "luk 1:26-38")
    )

    /** Keyed by cycle, then by "<lectionaryKey>". */
    val byCycle: Map<SundayCycle, Map<String, LectionaryEntry>> = mapOf(
        SundayCycle.A to cycleA,
        SundayCycle.B to cycleB,
        SundayCycle.C to cycleC
    )

    /** Cycle-specific entry if present, else the fixed one, else null. */
    fun entryFor(lectionaryKey: String, cycle: SundayCycle): LectionaryEntry? =
        byCycle[cycle]?.get(lectionaryKey) ?: fixed[lectionaryKey]
}

private val cycleA: Map<String, LectionaryEntry> = mapOf(
    // Advent
    "advent_1_sunday" to LectionaryEntry("isa 2:1-5", "psa 121", "rom 13:11-14", "mat 24:37-44"),
    "advent_2_sunday" to LectionaryEntry("isa 11:1-10", "psa 71", "rom 15:4-9", "mat 3:1-12"),
    "advent_3_sunday" to LectionaryEntry("isa 35:1-10", "psa 145", "jam 5:7-10", "mat 11:2-11"),
    "advent_4_sunday" to LectionaryEntry("isa 7:10-14", "psa 23", "rom 1:1-7", "mat 1:18-24"),

    // Christmas
    "holy_family" to LectionaryEntry("sir 3:3-7", "psa 127", "col 3:12-21", "mat 2:13-23"),

    // Lent
    "lent_1_sunday" to LectionaryEntry("gen 3:1-7", "psa 50", "rom 5:12-19", "mat 4:1-11"),
    "lent_2_sunday" to LectionaryEntry("gen 12:1-4", "psa 32", "2ti 1:8-10", "mat 17:1-9"),
    "lent_3_sunday" to LectionaryEntry("exo 17:3-7", "psa 94", "rom 5:1-8", "joh 4:5-42"),
    "lent_4_sunday" to LectionaryEntry("1sa 16:1-13", "psa 22", "eph 5:8-14", "joh 9:1-41"),
    "lent_5_sunday" to LectionaryEntry("eze 37:12-14", "psa 129", "rom 8:8-11", "joh 11:1-45"),
    // Palm Sunday: the short form of the Passion, Matthew 27:11-54.
    "holy_week_0_sunday" to LectionaryEntry("isa 50:4-7", "psa 21", "phi 2:6-11", "mat 27:11-54"),

    // Easter
    "easter_2_sunday" to LectionaryEntry("act 2:42-47", "psa 117", "1pe 1:3-9", "joh 20:19-31"),
    "divine_mercy" to LectionaryEntry("act 2:42-47", "psa 117", "1pe 1:3-9", "joh 20:19-31"),
    "easter_3_sunday" to LectionaryEntry("act 2:14-33", "psa 15", "1pe 1:17-21", "luk 24:13-35"),
    "easter_4_sunday" to LectionaryEntry("act 2:36-41", "psa 22", "1pe 2:20-25", "joh 10:1-10"),
    "easter_5_sunday" to LectionaryEntry("act 6:1-7", "psa 32", "1pe 2:4-9", "joh 14:1-12"),
    "easter_6_sunday" to LectionaryEntry("act 8:5-17", "psa 65", "1pe 3:15-18", "joh 14:15-21"),
    "easter_7_sunday" to LectionaryEntry("act 1:12-14", "psa 26", "1pe 4:13-16", "joh 17:1-11"),
    "ascension" to LectionaryEntry("act 1:1-11", "psa 46", "eph 1:17-23", "mat 28:16-20"),
    "pentecost" to LectionaryEntry("act 2:1-11", "psa 103", "1co 12:3-13", "joh 20:19-23"),

    // Solemnities of the Lord in Ordinary Time
    "trinity" to LectionaryEntry("exo 34:4-9", "dan 3:52-56", "2co 13:11-13", "joh 3:16-18"),
    "corpus_christi" to LectionaryEntry("deu 8:2-16", "psa 147", "1co 10:16-17", "joh 6:51-58"),
    "christ_the_king" to LectionaryEntry("eze 34:11-17", "psa 22", "1co 15:20-28", "mat 25:31-46"),

    // Ordinary Time
    "ot_2_sunday" to LectionaryEntry("isa 49:3-6", "psa 39", "1co 1:1-3", "joh 1:29-34"),
    "ot_3_sunday" to LectionaryEntry("isa 9:1-4", "psa 26", "1co 1:10-17", "mat 4:12-23"),
    "ot_4_sunday" to LectionaryEntry("zep 3:12-13", "psa 145", "1co 1:26-31", "mat 5:1-12"),
    "ot_5_sunday" to LectionaryEntry("isa 58:7-10", "psa 111", "1co 2:1-5", "mat 5:13-16"),
    "ot_6_sunday" to LectionaryEntry("sir 15:16-21", "psa 118", "1co 2:6-10", "mat 5:17-37"),
    "ot_7_sunday" to LectionaryEntry("lev 19:1-18", "psa 102", "1co 3:16-23", "mat 5:38-48"),
    "ot_8_sunday" to LectionaryEntry("isa 49:14-15", "psa 61", "1co 4:1-5", "mat 6:24-34"),
    "ot_9_sunday" to LectionaryEntry("deu 11:18-32", "psa 30", "rom 3:21-28", "mat 7:21-27"),
    "ot_10_sunday" to LectionaryEntry("hos 6:3-6", "psa 49", "rom 4:18-25", "mat 9:9-13"),
    "ot_11_sunday" to LectionaryEntry("exo 19:2-6", "psa 99", "rom 5:6-11", "mat 10:1-8"),
    "ot_12_sunday" to LectionaryEntry("jer 20:10-13", "psa 68", "rom 5:12-15", "mat 10:26-33"),
    "ot_13_sunday" to LectionaryEntry("2ki 4:8-16", "psa 88", "rom 6:3-11", "mat 10:37-42"),
    "ot_14_sunday" to LectionaryEntry("zec 9:9-10", "psa 144", "rom 8:9-13", "mat 11:25-30"),
    "ot_15_sunday" to LectionaryEntry("isa 55:10-11", "psa 64", "rom 8:18-23", "mat 13:1-23"),
    "ot_16_sunday" to LectionaryEntry("wis 12:13-19", "psa 85", "rom 8:26-27", "mat 13:24-43"),
    "ot_17_sunday" to LectionaryEntry("1ki 3:5-12", "psa 118", "rom 8:28-30", "mat 13:44-52"),
    "ot_18_sunday" to LectionaryEntry("isa 55:1-3", "psa 144", "rom 8:35-39", "mat 14:13-21"),
    "ot_19_sunday" to LectionaryEntry("1ki 19:9-13", "psa 84", "rom 9:1-5", "mat 14:22-33"),
    "ot_20_sunday" to LectionaryEntry("isa 56:1-7", "psa 66", "rom 11:13-32", "mat 15:21-28"),
    "ot_21_sunday" to LectionaryEntry("isa 22:19-23", "psa 137", "rom 11:33-36", "mat 16:13-20"),
    "ot_22_sunday" to LectionaryEntry("jer 20:7-9", "psa 62", "rom 12:1-2", "mat 16:21-27"),
    "ot_23_sunday" to LectionaryEntry("eze 33:7-9", "psa 94", "rom 13:8-10", "mat 18:15-20"),
    "ot_24_sunday" to LectionaryEntry("sir 28:1-7", "psa 102", "rom 14:7-9", "mat 18:21-35"),
    "ot_25_sunday" to LectionaryEntry("isa 55:6-9", "psa 144", "phi 1:20-27", "mat 20:1-16"),
    "ot_26_sunday" to LectionaryEntry("eze 18:25-28", "psa 24", "phi 2:1-11", "mat 21:28-32"),
    "ot_27_sunday" to LectionaryEntry("isa 5:1-7", "psa 79", "phi 4:6-9", "mat 21:33-43"),
    "ot_28_sunday" to LectionaryEntry("isa 25:6-10", "psa 22", "phi 4:12-20", "mat 22:1-14"),
    "ot_29_sunday" to LectionaryEntry("isa 45:1-6", "psa 95", "1th 1:1-5", "mat 22:15-21"),
    "ot_30_sunday" to LectionaryEntry("exo 22:21-27", "psa 17", "1th 1:5-10", "mat 22:34-40"),
    "ot_31_sunday" to LectionaryEntry("mal 2:1-10", "psa 130", "1th 2:7-13", "mat 23:1-12"),
    "ot_32_sunday" to LectionaryEntry("wis 6:12-16", "psa 62", "1th 4:13-17", "mat 25:1-13"),
    "ot_33_sunday" to LectionaryEntry("pro 31:10-31", "psa 127", "1th 5:1-6", "mat 25:14-30"),
    "ot_34_sunday" to LectionaryEntry("eze 34:11-17", "psa 22", "1co 15:20-28", "mat 25:31-46")
)

private val cycleB: Map<String, LectionaryEntry> = mapOf(
    // Advent
    "advent_1_sunday" to LectionaryEntry("isa 64:1-8", "psa 79", "1co 1:3-9", "mar 13:33-37"),
    "advent_2_sunday" to LectionaryEntry("isa 40:1-11", "psa 84", "2pe 3:8-14", "mar 1:1-8"),
    // The response is the Magnificat rather than a psalm.
    "advent_3_sunday" to LectionaryEntry("isa 61:1-11", "luk 1:46-55", "1th 5:16-24", "joh 1:6-28"),
    "advent_4_sunday" to LectionaryEntry("2sa 7:1-16", "psa 88", "rom 16:25-27", "luk 1:26-38"),

    // Christmas
    "holy_family" to LectionaryEntry("gen 15:1-6", "psa 104", "heb 11:8-19", "luk 2:22-40"),

    // Lent
    "lent_1_sunday" to LectionaryEntry("gen 9:8-15", "psa 24", "1pe 3:18-22", "mar 1:12-15"),
    "lent_2_sunday" to LectionaryEntry("gen 22:1-18", "psa 115", "rom 8:31-34", "mar 9:2-10"),
    "lent_3_sunday" to LectionaryEntry("exo 20:1-17", "psa 18", "1co 1:22-25", "joh 2:13-25"),
    "lent_4_sunday" to LectionaryEntry("2ch 36:14-23", "psa 136", "eph 2:4-10", "joh 3:14-21"),
    "lent_5_sunday" to LectionaryEntry("jer 31:31-34", "psa 50", "heb 5:7-9", "joh 12:20-33"),
    // Palm Sunday: the short form of the Passion, Mark 15:1-39.
    "holy_week_0_sunday" to LectionaryEntry("isa 50:4-7", "psa 21", "phi 2:6-11", "mar 15:1-39"),

    // Easter
    "easter_2_sunday" to LectionaryEntry("act 4:32-35", "psa 117", "1jo 5:1-6", "joh 20:19-31"),
    "divine_mercy" to LectionaryEntry("act 4:32-35", "psa 117", "1jo 5:1-6", "joh 20:19-31"),
    "easter_3_sunday" to LectionaryEntry("act 3:13-19", "psa 4", "1jo 2:1-5", "luk 24:35-48"),
    "easter_4_sunday" to LectionaryEntry("act 4:8-12", "psa 117", "1jo 3:1-2", "joh 10:11-18"),
    "easter_5_sunday" to LectionaryEntry("act 9:26-31", "psa 21", "1jo 3:18-24", "joh 15:1-8"),
    "easter_6_sunday" to LectionaryEntry("act 10:25-48", "psa 97", "1jo 4:7-10", "joh 15:9-17"),
    "easter_7_sunday" to LectionaryEntry("act 1:15-26", "psa 102", "1jo 4:11-16", "joh 17:11-19"),
    "ascension" to LectionaryEntry("act 1:1-11", "psa 46", "eph 1:17-23", "mar 16:15-20"),
    "pentecost" to LectionaryEntry("act 2:1-11", "psa 103", "gal 5:16-25", "joh 16:12-15"),

    // Solemnities of the Lord in Ordinary Time
    "trinity" to LectionaryEntry("deu 4:32-40", "psa 32", "rom 8:14-17", "mat 28:16-20"),
    "corpus_christi" to LectionaryEntry("exo 24:3-8", "psa 115", "heb 9:11-15", "mar 14:12-26"),
    "christ_the_king" to LectionaryEntry("dan 7:13-14", "psa 92", "rev 1:5-8", "joh 18:33-37"),

    // Ordinary Time
    "ot_2_sunday" to LectionaryEntry("1sa 3:3-19", "psa 39", "1co 6:13-20", "joh 1:35-42"),
    "ot_3_sunday" to LectionaryEntry("jon 3:1-10", "psa 24", "1co 7:29-31", "mar 1:14-20"),
    "ot_4_sunday" to LectionaryEntry("deu 18:15-20", "psa 94", "1co 7:32-35", "mar 1:21-28"),
    "ot_5_sunday" to LectionaryEntry("job 7:1-7", "psa 146", "1co 9:16-23", "mar 1:29-39"),
    "ot_6_sunday" to LectionaryEntry("lev 13:1-46", "psa 31", "1co 10:31-33", "mar 1:40-45"),
    "ot_7_sunday" to LectionaryEntry("isa 43:18-25", "psa 40", "2co 1:18-22", "mar 2:1-12"),
    "ot_8_sunday" to LectionaryEntry("hos 2:14-20", "psa 102", "2co 3:1-6", "mar 2:18-22"),
    "ot_9_sunday" to LectionaryEntry("deu 5:12-15", "psa 80", "2co 4:6-11", "mar 2:23-28"),
    "ot_10_sunday" to LectionaryEntry("gen 3:9-15", "psa 129", "2co 4:13-18", "mar 3:20-35"),
    "ot_11_sunday" to LectionaryEntry("eze 17:22-24", "psa 91", "2co 5:6-10", "mar 4:26-34"),
    "ot_12_sunday" to LectionaryEntry("job 38:1-11", "psa 106", "2co 5:14-17", "mar 4:35-40"),
    "ot_13_sunday" to LectionaryEntry("wis 1:13-15", "psa 29", "2co 8:7-15", "mar 5:21-43"),
    "ot_14_sunday" to LectionaryEntry("eze 2:2-5", "psa 122", "2co 12:7-10", "mar 6:1-6"),
    "ot_15_sunday" to LectionaryEntry("amo 7:12-15", "psa 84", "eph 1:3-14", "mar 6:7-13"),
    "ot_16_sunday" to LectionaryEntry("jer 23:1-6", "psa 22", "eph 2:13-18", "mar 6:30-34"),
    "ot_17_sunday" to LectionaryEntry("2ki 4:42-44", "psa 144", "eph 4:1-6", "joh 6:1-15"),
    "ot_18_sunday" to LectionaryEntry("exo 16:2-15", "psa 77", "eph 4:17-24", "joh 6:24-35"),
    "ot_19_sunday" to LectionaryEntry("1ki 19:4-8", "psa 33", "eph 4:30-32", "joh 6:41-51"),
    "ot_20_sunday" to LectionaryEntry("pro 9:1-6", "psa 33", "eph 5:15-20", "joh 6:51-58"),
    "ot_21_sunday" to LectionaryEntry("jos 24:1-18", "psa 33", "eph 5:21-32", "joh 6:60-69"),
    "ot_22_sunday" to LectionaryEntry("deu 4:1-8", "psa 14", "jam 1:17-27", "mar 7:1-23"),
    "ot_23_sunday" to LectionaryEntry("isa 35:4-7", "psa 145", "jam 2:1-5", "mar 7:31-37"),
    "ot_24_sunday" to LectionaryEntry("isa 50:5-9", "psa 114", "jam 2:14-18", "mar 8:27-35"),
    "ot_25_sunday" to LectionaryEntry("wis 2:12-20", "psa 53", "jam 3:16-18", "mar 9:30-37"),
    "ot_26_sunday" to LectionaryEntry("num 11:25-29", "psa 18", "jam 5:1-6", "mar 9:38-48"),
    "ot_27_sunday" to LectionaryEntry("gen 2:18-24", "psa 127", "heb 2:9-11", "mar 10:2-16"),
    "ot_28_sunday" to LectionaryEntry("wis 7:7-11", "psa 89", "heb 4:12-13", "mar 10:17-30"),
    "ot_29_sunday" to LectionaryEntry("isa 53:10-11", "psa 32", "heb 4:14-16", "mar 10:35-45"),
    "ot_30_sunday" to LectionaryEntry("jer 31:7-9", "psa 125", "heb 5:1-6", "mar 10:46-52"),
    "ot_31_sunday" to LectionaryEntry("deu 6:2-6", "psa 17", "heb 7:23-28", "mar 12:28-34"),
    "ot_32_sunday" to LectionaryEntry("1ki 17:10-16", "psa 145", "heb 9:24-28", "mar 12:38-44"),
    "ot_33_sunday" to LectionaryEntry("dan 12:1-3", "psa 15", "heb 10:11-18", "mar 13:24-32"),
    "ot_34_sunday" to LectionaryEntry("dan 7:13-14", "psa 92", "rev 1:5-8", "joh 18:33-37")
)

private val cycleC: Map<String, LectionaryEntry> = mapOf(
    // Advent
    "advent_1_sunday" to LectionaryEntry("jer 33:14-16", "psa 24", "1th 3:12-13", "luk 21:25-36"),
    "advent_2_sunday" to LectionaryEntry("bar 5:1-9", "psa 125", "phi 1:4-11", "luk 3:1-6"),
    // The response is the canticle of Isaiah rather than a psalm.
    "advent_3_sunday" to LectionaryEntry("zep 3:14-18", "isa 12:2-6", "phi 4:4-7", "luk 3:10-18"),
    "advent_4_sunday" to LectionaryEntry("mic 5:2-5", "psa 79", "heb 10:5-10", "luk 1:39-45"),

    // Christmas
    "holy_family" to LectionaryEntry("1sa 1:20-28", "psa 83", "1jo 3", "luk 2:41-52"),

    // Lent
    "lent_1_sunday" to LectionaryEntry("deu 26:4-10", "psa 90", "rom 10:8-13", "luk 4:1-13"),
    "lent_2_sunday" to LectionaryEntry("gen 15:5-18", "psa 26", "phi 3:17-21", "luk 9:28-36"),
    "lent_3_sunday" to LectionaryEntry("exo 3:1-15", "psa 102", "1co 10:1-12", "luk 13:1-9"),
    "lent_4_sunday" to LectionaryEntry("jos 5:9-12", "psa 33", "2co 5:17-21", "luk 15:1-32"),
    "lent_5_sunday" to LectionaryEntry("isa 43:16-21", "psa 125", "phi 3:8-14", "joh 8:1-11"),
    // Palm Sunday: the short form of the Passion, Luke 23:1-49.
    "holy_week_0_sunday" to LectionaryEntry("isa 50:4-7", "psa 21", "phi 2:6-11", "luk 23:1-49"),

    // Easter
    "easter_2_sunday" to LectionaryEntry("act 5:12-16", "psa 117", "rev 1:9-19", "joh 20:19-31"),
    "divine_mercy" to LectionaryEntry("act 5:12-16", "psa 117", "rev 1:9-19", "joh 20:19-31"),
    "easter_3_sunday" to LectionaryEntry("act 5:27-41", "psa 29", "rev 5:11-14", "joh 21:1-19"),
    "easter_4_sunday" to LectionaryEntry("act 13:43-52", "psa 99", "rev 7:9-17", "joh 10:27-30"),
    "easter_5_sunday" to LectionaryEntry("act 14:21-27", "psa 144", "rev 21:1-5", "joh 13:31-35"),
    "easter_6_sunday" to LectionaryEntry("act 15:22-29", "psa 66", "rev 21:10-23", "joh 14:23-29"),
    "easter_7_sunday" to LectionaryEntry("act 7:55-59", "psa 96", "rev 22:12-20", "joh 17:20-26"),
    "ascension" to LectionaryEntry("act 1:1-11", "psa 46", "eph 1:17-23", "luk 24:46-53"),
    "pentecost" to LectionaryEntry("act 2:1-11", "psa 103", "rom 8:8-17", "joh 14:15-26"),

    // Solemnities of the Lord in Ordinary Time
    "trinity" to LectionaryEntry("pro 8:22-31", "psa 8", "rom 5:1-5", "joh 16:12-15"),
    "corpus_christi" to LectionaryEntry("gen 14:18-20", "psa 109", "1co 11:23-26", "luk 9:11-17"),
    "christ_the_king" to LectionaryEntry("2sa 5:1-3", "psa 121", "col 1:12-20", "luk 23:35-43"),

    // Ordinary Time
    "ot_2_sunday" to LectionaryEntry("isa 62:1-5", "psa 95", "1co 12:4-11", "joh 2:1-11"),
    "ot_3_sunday" to LectionaryEntry("neh 8:2-10", "psa 18", "1co 12:12-30", "luk 4:14-21"),
    "ot_4_sunday" to LectionaryEntry("jer 1:4-19", "psa 70", "1co 13:1-13", "luk 4:21-30"),
    "ot_5_sunday" to LectionaryEntry("isa 6:1-8", "psa 137", "1co 15:1-11", "luk 5:1-11"),
    "ot_6_sunday" to LectionaryEntry("jer 17:5-8", "psa 1", "1co 15:12-20", "luk 6:17-26"),
    "ot_7_sunday" to LectionaryEntry("1sa 26:2-23", "psa 102", "1co 15:45-49", "luk 6:27-38"),
    "ot_8_sunday" to LectionaryEntry("sir 27:5-7", "psa 91", "1co 15:54-58", "luk 6:39-45"),
    "ot_9_sunday" to LectionaryEntry("1ki 8:41-43", "psa 116", "gal 1:1-10", "luk 7:1-10"),
    "ot_10_sunday" to LectionaryEntry("1ki 17:17-24", "psa 29", "gal 1:11-19", "luk 7:11-17"),
    "ot_11_sunday" to LectionaryEntry("2sa 12:7-13", "psa 31", "gal 2:16-21", "luk 7:36-50"),
    "ot_12_sunday" to LectionaryEntry("zec 12:10-11", "psa 62", "gal 3:26-29", "luk 9:18-24"),
    "ot_13_sunday" to LectionaryEntry("1ki 19:16-21", "psa 15", "gal 5:1-18", "luk 9:51-62"),
    "ot_14_sunday" to LectionaryEntry("isa 66:10-14", "psa 65", "gal 6:14-18", "luk 10:1-20"),
    "ot_15_sunday" to LectionaryEntry("deu 30:10-14", "psa 68", "col 1:15-20", "luk 10:25-37"),
    "ot_16_sunday" to LectionaryEntry("gen 18:1-10", "psa 14", "col 1:24-28", "luk 10:38-42"),
    "ot_17_sunday" to LectionaryEntry("gen 18:20-32", "psa 137", "col 2:12-14", "luk 11:1-13"),
    "ot_18_sunday" to LectionaryEntry("ecc 2:21-23", "psa 89", "col 3:1-11", "luk 12:13-21"),
    "ot_19_sunday" to LectionaryEntry("wis 18:6-9", "psa 32", "heb 11:1-19", "luk 12:32-48"),
    "ot_20_sunday" to LectionaryEntry("jer 38:4-10", "psa 39", "heb 12:1-4", "luk 12:49-53"),
    "ot_21_sunday" to LectionaryEntry("isa 66:18-21", "psa 116", "heb 12:5-13", "luk 13:22-30"),
    "ot_22_sunday" to LectionaryEntry("sir 3:19-24", "psa 67", "heb 12:18-24", "luk 14:1-14"),
    "ot_23_sunday" to LectionaryEntry("wis 9:13-18", "psa 89", "phm 1:9-17", "luk 14:25-33"),
    "ot_24_sunday" to LectionaryEntry("exo 32:7-14", "psa 50", "1ti 1:12-17", "luk 15:1-32"),
    "ot_25_sunday" to LectionaryEntry("amo 8:4-7", "psa 112", "1ti 2:1-8", "luk 16:1-13"),
    "ot_26_sunday" to LectionaryEntry("amo 6:1-7", "psa 145", "1ti 6:11-16", "luk 16:19-31"),
    "ot_27_sunday" to LectionaryEntry("hab 2:2-4", "psa 94", "2ti 1:6-14", "luk 17:5-10"),
    "ot_28_sunday" to LectionaryEntry("2ki 5:14-17", "psa 97", "2ti 2:8-13", "luk 17:11-19"),
    "ot_29_sunday" to LectionaryEntry("exo 17:8-13", "psa 120", "2ti 3:14-17", "luk 18:1-8"),
    "ot_30_sunday" to LectionaryEntry("sir 35:15-21", "psa 33", "2ti 4:6-18", "luk 18:9-14"),
    "ot_31_sunday" to LectionaryEntry("wis 11:22-26", "psa 144", "2th 1:11-12", "luk 19:1-10"),
    "ot_32_sunday" to LectionaryEntry("2ma 7:1-14", "psa 16", "2th 3:1-5", "luk 20:27-38"),
    // Malachi 3:19-20 of the modern numbering is Malachi 4:1-2 in the Vulgate.
    "ot_33_sunday" to LectionaryEntry("mal 4:1-2", "psa 97", "2th 3:7-12", "luk 21:5-19"),
    "ot_34_sunday" to LectionaryEntry("2sa 5:1-3", "psa 121", "col 1:12-20", "luk 23:35-43")
)
