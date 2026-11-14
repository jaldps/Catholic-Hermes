package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.data.models.LocalizedText
import com.example.data.models.TemptationGuide
import com.example.ui.design.AppCard
import com.example.ui.design.AppInlineTitle
import com.example.ui.design.AppSectionHeader
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/**
 * Long-form guide behind the "Más" button of a temptation. Reading surfaces are the
 * design kit's opaque cards; only design tokens are used so both identities apply.
 */
@Composable
fun TemptationGuidePage(
    lang: AppLanguage,
    guide: TemptationGuide,
    sinTitle: String,
    virtue: String,
    insets: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier
) {
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val gutter = design.spacing.gutter
    AppInlineTitle(virtue)

    @Composable
    fun Body(text: String) = Text(text, style = styles.reading, color = p.textPrimary)

    @Composable
    fun Bullets(items: List<LocalizedText>, numbered: Boolean = false) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items.forEachIndexed { i, item ->
                Row {
                    Text(
                        if (numbered) "${i + 1}." else "•",
                        style = styles.reading, color = p.accent,
                        modifier = Modifier.width(if (numbered) 28.dp else 18.dp)
                    )
                    Text(item.get(lang), style = styles.reading, color = p.textPrimary, modifier = Modifier.weight(1f))
                }
            }
        }
    }

    fun LazyListScope.section(tag: String, title: String, content: @Composable () -> Unit) {
        item(key = tag) {
            Column(Modifier.testTag(tag)) {
                AppSectionHeader(title)
                Column(Modifier.fillMaxWidth().padding(horizontal = gutter)) { content() }
            }
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().testTag("temptation_guide_page"),
        contentPadding = PaddingValues(bottom = insets.calculateBottomPadding() + 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(key = "guide_header") {
            Column(Modifier.fillMaxWidth().padding(horizontal = gutter).padding(top = 12.dp)) {
                Text(virtue, style = styles.title2, color = p.textPrimary)
                Spacer(Modifier.height(2.dp))
                Text(
                    lang.pick3("Frente a: ", "Against: ", "Contra: ") + sinTitle,
                    style = styles.footnote, color = p.textSecondary
                )
                Spacer(Modifier.height(12.dp))
                AppCard(Modifier.fillMaxWidth()) { Body(guide.summary.get(lang)) }
            }
        }
        section("guide_section_how", lang.pick3("Cómo actúa", "How it works", "Como atua")) {
            AppCard(Modifier.fillMaxWidth()) { Body(guide.howItWorks.get(lang)) }
        }
        section("guide_section_hard", lang.pick3("Por qué cuesta tanto", "Why it is so hard", "Por que custa tanto")) {
            AppCard(Modifier.fillMaxWidth()) { Body(guide.whyItIsHard.get(lang)) }
        }
        section("guide_section_signs", lang.pick3("Señales de alerta", "Warning signs", "Sinais de alerta")) {
            AppCard(Modifier.fillMaxWidth()) { Bullets(guide.warningSigns) }
        }
        section("guide_section_moment", lang.pick3("En el momento de la tentación", "In the moment of temptation", "No momento da tentação")) {
            AppCard(Modifier.fillMaxWidth()) { Bullets(guide.inTheMoment, numbered = true) }
        }
        section("guide_section_routine", lang.pick3("Rutina diaria", "Daily routine", "Rotina diária")) {
            AppCard(Modifier.fillMaxWidth()) { Bullets(guide.dailyRoutine) }
        }
        section("guide_section_plan", lang.pick3("Plan para vencerla", "A plan to overcome it", "Plano para vencê-la")) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                guide.plan.forEach { phase ->
                    AppCard(Modifier.fillMaxWidth()) {
                        Text(phase.title.get(lang), style = styles.headline, color = p.textPrimary)
                        Text(
                            lang.pick3("Días ", "Days ", "Dias ") + phase.days,
                            style = styles.footnote, color = p.textSecondary
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(phase.focus.get(lang), style = styles.reading, color = p.textPrimary)
                        Spacer(Modifier.height(10.dp))
                        Bullets(phase.practices)
                    }
                }
            }
        }
        section("guide_section_scripture", lang.pick3("Palabra de Dios", "The Word of God", "Palavra de Deus")) {
            AppCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    guide.scripture.forEach { s ->
                        Column {
                            Text(s.reference.get(lang), style = styles.headline.copy(fontWeight = FontWeight.Bold), color = p.textPrimary)
                            Spacer(Modifier.height(2.dp))
                            Text(s.note.get(lang), style = styles.reading, color = p.textPrimary)
                        }
                    }
                }
            }
        }
        section("guide_section_saints", lang.pick3("Sabiduría de los santos", "Wisdom of the saints", "Sabedoria dos santos")) {
            AppCard(Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    guide.saintsWisdom.forEach { c ->
                        Column {
                            Text(c.author.get(lang), style = styles.headline, color = p.textPrimary)
                            Spacer(Modifier.height(4.dp))
                            val t = c.text.get(lang)
                            Text(if (c.isDirectQuote) "«$t»" else t, style = styles.reading, color = p.textPrimary)
                            Spacer(Modifier.height(4.dp))
                            Text(c.source.get(lang), style = styles.footnote, color = p.textSecondary)
                        }
                    }
                }
            }
        }
        section("guide_section_sacraments", lang.pick3("Sacramentos y ayudas", "Sacraments and aids", "Sacramentos e ajudas")) {
            AppCard(Modifier.fillMaxWidth()) { Body(guide.sacramentalHelps.get(lang)) }
        }
        section("guide_section_help", lang.pick3("Cuándo pedir ayuda", "When to seek help", "Quando pedir ajuda")) {
            com.example.ui.design.AppBanner(
                title = lang.pick3("Cuándo pedir ayuda", "When to seek help", "Quando pedir ajuda"),
                body = guide.whenToSeekHelp.get(lang)
            )
        }
        section("guide_section_sources", lang.pick3("Fuentes", "Sources", "Fontes")) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                guide.sources.forEach { Text(it.get(lang), style = styles.footnote, color = p.textSecondary) }
            }
        }
    }
}
