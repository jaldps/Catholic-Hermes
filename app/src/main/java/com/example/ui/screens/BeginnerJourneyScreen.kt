package com.example.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.models.AppLanguage
import com.example.ui.design.*
import com.example.ui.theme.LocalAppDesign
import com.example.ui.theme.LocalAppTextStyles

/** A small independent store: journey choices never depend on database migrations. */
internal class BeginnerJourneyStore(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences("beginner_journey", Context.MODE_PRIVATE)
    val welcomed get() = prefs.getBoolean("welcomed", false)
    val minutes get() = prefs.getInt("minutes", 5).takeIf { it in listOf(2, 5, 10) } ?: 5
    val step get() = prefs.getInt("step", 0).coerceIn(0, 6)
    fun welcome(profile: Int, minutes: Int) { prefs.edit().putBoolean("welcomed", true).putInt("profile", profile).putInt("minutes", minutes).putInt("step", when(profile) { 1 -> 4; 2 -> 6; else -> 0 }).apply() }
    fun advance(step: Int) { prefs.edit().putInt("step", step.coerceIn(0, 6)).apply() }
}

private fun AppLanguage.journey(es: String, en: String, pt: String) = when (this) { AppLanguage.ES -> es; AppLanguage.EN -> en; AppLanguage.PT -> pt }

@Composable
internal fun WelcomeChoices(language: AppLanguage, onChooseLanguage: () -> Unit = {}, onClose: () -> Unit) {
    val context = LocalContext.current
    val store = remember { BeginnerJourneyStore(context) }
    var profile by rememberSaveable { mutableIntStateOf(0) }
    var time by rememberSaveable { mutableIntStateOf(store.minutes) }
    val design = LocalAppDesign.current
    val p = design.palette
    val styles = LocalAppTextStyles.current
    val finish = { store.welcome(profile, time); onClose() }
    AppDialog(onDismiss = finish, title = language.journey("Un lugar para comenzar", "A place to begin", "Um lugar para começar"), tag = "welcome_dialog") {
        Text(
            language.journey("Ven como eres. Puedes cambiar de camino en cualquier momento.", "Come as you are. You can choose another path at any time.", "Venha como está. Você pode escolher outro caminho a qualquer momento."),
            style = styles.subhead, color = p.textSecondary, modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(18.dp))
        AppListGroup {
            listOf(
                language.journey("Estoy explorando", "I'm exploring", "Estou explorando"),
                language.journey("Estoy regresando", "I'm returning", "Estou voltando"),
                language.journey("Estoy practicando", "I'm practicing", "Estou praticando")
            ).forEachIndexed { i, label ->
                AppListRow(
                    title = label, selected = profile == i, showsNavigationAffordance = false,
                    trailingText = if (profile == i) "✓" else null,
                    onClick = { profile = i },
                    modifier = Modifier.testTag("welcome_profile_$i").semantics { selected = profile == i }
                )
            }
        }
        Spacer(Modifier.height(18.dp))
        Text(language.journey("Tiempo para hoy", "Time for today", "Tempo para hoje"), style = styles.footnote, color = p.textSecondary,
            modifier = Modifier.padding(horizontal = 24.dp))
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = design.spacing.gutter), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(2, 5, 10).forEach { minutes ->
                AppChip("$minutes min", selected = time == minutes, onClick = { time = minutes },
                    modifier = Modifier.weight(1f).testTag("welcome_minutes_$minutes"))
            }
        }
        Spacer(Modifier.height(14.dp))
        AppListGroup {
            AppListRow(
                title = language.journey("Idioma", "Language", "Idioma"), trailingText = language.displayName,
                leadingIcon = androidx.compose.material.icons.Icons.Outlined.Language,
                onClick = onChooseLanguage, modifier = Modifier.testTag("welcome_language")
            )
        }
        Spacer(Modifier.height(22.dp))
        Column(Modifier.padding(horizontal = design.spacing.gutter), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AppPrimaryCapsule(language.journey("Comenzar", "Begin", "Começar"), onClick = finish, tag = "welcome_begin")
            AppSecondaryCapsule(language.journey("Omitir", "Skip", "Pular"), onClick = finish, fillWidth = true, tag = "welcome_skip")
        }
    }
}

@Composable
fun BeginnerJourneyScreen(currentLanguage: AppLanguage, onOpenBible: () -> Unit, onOpenPrayer: () -> Unit, onOpenParishes: () -> Unit) {
    val context = LocalContext.current
    val store = remember { BeginnerJourneyStore(context) }
    var selected by rememberSaveable { mutableIntStateOf(store.step) }
    val styles = LocalAppTextStyles.current
    val design = LocalAppDesign.current
    val l = currentLanguage
    val titles = listOf(
        l.journey("Conoce a Jesús", "Meet Jesus", "Conheça Jesus"), l.journey("Habla con Dios", "Speak to God", "Fale com Deus"),
        l.journey("Lee un Evangelio", "Read a Gospel", "Leia um Evangelho"), l.journey("Tu primera Misa", "Your first Mass", "Sua primeira Missa"),
        l.journey("Volver a la confesión", "Returning to confession", "Voltar à confissão"), l.journey("Una comunidad de fe", "A community of faith", "Uma comunidade de fé"),
        l.journey("Un ritmo sostenible", "A sustainable rhythm", "Um ritmo sustentável")
    )
    val bodies = listOf(
        l.journey("Lee Juan 1,35–39. Jesús pregunta: «¿Qué buscan?». No necesitas tener todas las respuestas. Quédate un momento con esa pregunta y dile qué buscas.", "Read John 1:35–39. Jesus asks, ‘What are you looking for?’ You do not need every answer. Stay with that question for a moment and tell him what you seek.", "Leia João 1,35–39. Jesus pergunta: ‘Que procurais?’. Você não precisa ter todas as respostas. Fique um momento com essa pergunta e diga o que procura."),
        l.journey("Puedes empezar: «Dios, aquí estoy. Ayúdame a conocerte». Habla con tus propias palabras y deja un momento de silencio. Después, reza el Padre Nuestro despacio; Jesús enseñó esta oración a sus discípulos.", "You can begin: ‘God, here I am. Help me to know you.’ Speak in your own words and leave a little silence. Then pray the Lord’s Prayer slowly; Jesus taught this prayer to his disciples.", "Você pode começar: ‘Deus, aqui estou. Ajuda-me a te conhecer’. Fale com suas palavras e faça um pouco de silêncio. Depois, reze o Pai-Nosso devagar; Jesus ensinou esta oração aos discípulos."),
        l.journey("Evangelio significa buena noticia. Los cuatro Evangelios anuncian la vida, muerte y resurrección de Jesús. Empieza con unos versículos de Juan; pregunta qué hace Jesús y cómo puedes responder hoy.", "Gospel means good news. The four Gospels proclaim Jesus’ life, death and resurrection. Begin with a few verses of John; ask what Jesus does and how you could respond today.", "Evangelho significa boa notícia. Os quatro Evangelhos anunciam a vida, morte e ressurreição de Jesus. Comece com alguns versículos de João; pergunte o que Jesus faz e como responder hoje."),
        l.journey("Esta guía sigue el rito romano. Llega unos minutos antes; puedes sentarte y observar. La Misa reúne escucha de la Escritura, oración, ofrenda y Eucaristía. Sigue a la asamblea al sentarte o ponerte de pie, si puedes. No tienes que recibir la Comunión: permanece en tu lugar si no eres católico o no estás preparado para recibir. Un sacerdote puede orientarte personalmente. Confirma los horarios con la parroquia.", "This guide follows the Roman Rite. Arrive a few minutes early; you are welcome to sit and observe. Mass brings together Scripture, prayer, offering and Eucharist. Follow the congregation’s sitting and standing as you are able. You need not receive Communion: remain in your place if you are not Catholic or are not prepared to receive. A priest can guide you personally. Confirm times with the parish.", "Este guia segue o rito romano. Chegue alguns minutos antes; você pode sentar e observar. A Missa reúne Escritura, oração, oferenda e Eucaristia. Acompanhe a assembleia ao sentar e levantar, conforme puder. Você não precisa comungar: permaneça no lugar se não é católico ou não está preparado. Um sacerdote pode orientar você pessoalmente. Confirme os horários com a paróquia."),
        l.journey("Puedes decir al sacerdote: «Hace mucho que no me confieso; ayúdeme». Antes, revisa tu vida con honestidad, pide perdón y decide cambiar. En la confesión nombra todos los pecados graves que recuerdas, según su especie y número en la medida que puedas; el sacerdote puede guiarte en el acto de contrición y la penitencia. Un pecado grave olvidado sinceramente no exige repetir toda la confesión; menciónalo en tu próxima confesión. La contrición perfecta incluye el propósito de confesarse cuanto antes; no sustituye las normas para recibir la Comunión. Para tu situación, habla con un sacerdote.", "You can tell the priest: ‘It has been a long time; please help me.’ Prepare by honestly reviewing your life, asking forgiveness and resolving to change. Confess all grave sins you remember, giving their kind and number as you are able; the priest can guide the prayer of sorrow and penance. An honestly forgotten grave sin does not require repeating the whole confession; mention it at your next confession. Perfect contrition includes the intention to confess as soon as possible; it does not replace the distinct rules for receiving Communion. Discuss your situation with a priest.", "Você pode dizer ao sacerdote: ‘Faz muito tempo; por favor, me ajude’. Prepare-se revendo sua vida com sinceridade, pedindo perdão e decidindo mudar. Confesse todos os pecados graves de que se lembra, indicando a espécie e o número conforme puder; o sacerdote pode ajudar com o ato de contrição e a penitência. Um pecado grave esquecido sinceramente não exige repetir toda a confissão; mencione-o na próxima confissão. A contrição perfeita inclui a intenção de se confessar quanto antes; não substitui as normas para comungar. Converse com um sacerdote sobre sua situação."),
        l.journey("La fe se vive con otros. Una parroquia es una comunidad local para la Misa, los sacramentos y el servicio. Si deseas hacerte católico, pregunta por el proceso local de iniciación cristiana. Puedes pedir una conversación sin comprometerte a nada.", "Faith is lived with others. A parish is a local community for Mass, the sacraments and service. If you wish to become Catholic, ask about the local Christian initiation process. You can request a conversation without making a commitment.", "A fé é vivida com outros. Uma paróquia é uma comunidade local para a Missa, os sacramentos e o serviço. Se deseja se tornar católico, pergunte pelo processo local de iniciação cristã. Você pode pedir uma conversa sem compromisso."),
        l.journey("Elige algo pequeño: unos minutos de oración, un pasaje del Evangelio y un gesto concreto de caridad. La vida sacramental y la comunidad sostienen este camino. Un día perdido es una invitación a volver, no una medida de tu valor ante Dios.", "Choose something small: a few minutes of prayer, a Gospel passage and a concrete act of charity. Sacramental life and community sustain this path. A missed day is an invitation to return, never a measure of your worth before God.", "Escolha algo pequeno: alguns minutos de oração, uma passagem do Evangelho e um gesto concreto de caridade. A vida sacramental e a comunidade sustentam este caminho. Um dia perdido é um convite a voltar, nunca uma medida de seu valor diante de Deus.")
    )
    val sources = listOf("John 1:35–39", "Matthew 6:9–13; CCC 2759", "CCC 125–127", "CCC 1346–1355, 1385; Code of Canon Law 844 §1, 916", "CCC 1451–1460; Code of Canon Law 916, 988 §1", "CCC 1212, 2179", "CCC 2447, 2697–2699")
    val sourceUrls = listOf("https://bible.usccb.org/bible/john/1", "https://www.vatican.va/archive/ENG0015/_INDEX.HTM", "https://www.vatican.va/archive/ENG0015/_INDEX.HTM", "https://www.usccb.org/prayer-and-worship/the-mass/order-of-mass", "https://www.vatican.va/archive/ENG0015/_INDEX.HTM", "https://www.vatican.va/archive/ENG0015/_INDEX.HTM", "https://www.vatican.va/archive/ENG0015/_INDEX.HTM")
    AppEdgeToEdge()
    val uri = androidx.compose.ui.platform.LocalUriHandler.current
    val gutter = design.spacing.gutter
    val p = design.palette
    LazyColumn(Modifier.fillMaxSize().testTag("beginner_journey_screen"), contentPadding = PaddingValues(bottom = appDockInset() + 24.dp)) {
        item {
            AppHeroHeader(
                assetPath = "heroes/lumen/home.jpg",
                title = l.journey("Primeros pasos", "Start here", "Primeiros passos"),
                meta = l.journey("Siete pasos a tu ritmo. Puedes saltar cualquiera.", "Seven steps at your pace. You may skip any step.", "Sete passos no seu ritmo. Você pode pular qualquer um.")
            )
            AppSegmentedProgress(count = 7, current = selected, fraction = 1f, modifier = Modifier.padding(horizontal = gutter).padding(top = 6.dp, bottom = 18.dp), height = 5.dp)
        }
        item {
            AppCard(Modifier.padding(horizontal = gutter)) {
                Text(l.journey("Paso ${selected + 1} de 7", "Step ${selected + 1} of 7", "Passo ${selected + 1} de 7"), style = styles.eyebrow, color = p.accent)
                Spacer(Modifier.height(4.dp))
                Text(titles[selected], style = styles.title2, color = p.textPrimary, modifier = Modifier.semantics { heading() })
                Spacer(Modifier.height(12.dp))
                Text(bodies[selected], style = styles.reading, color = p.textPrimary)
                Spacer(Modifier.height(18.dp))
                AppPrimaryCapsule(label = when(selected) { 0, 2 -> l.journey("Abrir el Evangelio", "Open the Gospel", "Abrir o Evangelho"); 1, 6 -> l.journey("Rezar", "Pray", "Rezar"); else -> l.journey("Encontrar una parroquia", "Find a parish", "Encontrar uma paróquia") }, onClick = when(selected) { 0,2 -> onOpenBible; 1,6 -> onOpenPrayer; else -> onOpenParishes }, tag = "journey_action")
                if (selected < 6) {
                    Spacer(Modifier.height(8.dp))
                    AppSecondaryCapsule(l.journey("Paso siguiente", "Next step", "Próximo passo"), onClick = { selected++; store.advance(selected) }, fillWidth = true, tag = "journey_next")
                }
                Spacer(Modifier.height(14.dp))
                Text(l.journey("Guía editorial · Fuentes: ", "Editorial guide · Sources: ", "Guia editorial · Fontes: ") + sources[selected], style = styles.footnote, color = p.textSecondary)
                Row(
                    Modifier.heightIn(min = 48.dp).lumenPress({ uri.openUri(sourceUrls[selected]) }, 0.97f).testTag("journey_source"),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(l.journey("Consultar la fuente", "Read the source", "Consultar a fonte"), style = styles.subhead.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold), color = p.accent)
                    Spacer(Modifier.width(6.dp))
                    androidx.compose.material3.Icon(androidx.compose.material.icons.Icons.AutoMirrored.Outlined.OpenInNew, null, tint = p.accent, modifier = Modifier.size(16.dp))
                }
            }
            Spacer(Modifier.height(design.spacing.sectionGap))
        }
        item {
            AppSectionHeader(l.journey("El camino", "The path", "O caminho"))
            AppListGroup {
                titles.forEachIndexed { index, title ->
                    AppListRow(title, trailingText = "${index + 1}", selected = selected == index, showsNavigationAffordance = false,
                        onClick = { selected = index; store.advance(index) }, modifier = Modifier.testTag("journey_step_$index"))
                }
            }
        }
    }
}
