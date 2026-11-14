package com.example.data.models

/**
 * Long-form formation guide behind the "Más" button of each temptation.
 * Keyed by [SinAvoiderOption.sinKey] (LUST, ANGER, GLUTTONY, SLOTH, ENVY, PRIDE, GREED),
 * which is a persisted key and must never change.
 */
data class TemptationGuide(
    val sinKey: String,
    /** Two or three sentences: what this vice is and the virtue that heals it. */
    val summary: LocalizedText,
    /** How the temptation works: occasions, stages (suggestion, delight, consent), self-deception. */
    val howItWorks: LocalizedText,
    /** Why it is hard to overcome: habit, wounds, culture, the false goods it promises. */
    val whyItIsHard: LocalizedText,
    /** Concrete signs that the temptation is gaining ground (4–6). */
    val warningSigns: List<LocalizedText>,
    /** What to do in the moment of temptation (5–7 short, actionable steps). */
    val inTheMoment: List<LocalizedText>,
    /** Daily rule of life: morning, during the day, evening examen (5–7 items). */
    val dailyRoutine: List<LocalizedText>,
    /** A finite plan to uproot the habit (about 40 days, in 4–6 phases). */
    val plan: List<GuidePhase>,
    /** Scripture passages with why they help; reference only, no copyrighted translation. */
    val scripture: List<GuideScripture>,
    /** Teaching of saints and doctors, each with an identifiable source. */
    val saintsWisdom: List<GuideCitation>,
    /** Sacraments, sacramentals and devotions that help. */
    val sacramentalHelps: LocalizedText,
    /** When and whom to ask for help (confessor, spiritual director, professionals). */
    val whenToSeekHelp: LocalizedText,
    /** Bibliography lines (Catechism paragraphs, works with chapter). */
    val sources: List<LocalizedText>
)

data class GuidePhase(
    val title: LocalizedText,
    /** Day range shown as-is, e.g. "1–7". */
    val days: String,
    val focus: LocalizedText,
    val practices: List<LocalizedText>
)

data class GuideScripture(
    /** Localized reference, e.g. ES "1 Co 10,13" · EN "1 Cor 10:13" · PT "1Cor 10,13". */
    val reference: LocalizedText,
    /** Why this passage helps against the temptation (own words). */
    val note: LocalizedText
)

data class GuideCitation(
    /** Author as commonly named, e.g. "San Juan Casiano" / "St John Cassian" / "São João Cassiano". */
    val author: LocalizedText,
    /** The teaching. A direct quotation only when [isDirectQuote] and [source] locates it exactly. */
    val text: LocalizedText,
    /** Work and place, e.g. "Instituciones V, 14". */
    val source: LocalizedText,
    val isDirectQuote: Boolean = false
)
