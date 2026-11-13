package com.example.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.AlertDialog
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
    val p = LocalAppDesign.current.palette
    AlertDialog(
        onDismissRequest = { store.welcome(profile, time); onClose() },
        title = { Text(language.journey("Un lugar para comenzar", "A place to begin", "Um lugar para começar")) },
        text = {
            Column {
                Text(language.journey("Ven como eres. Puedes cambiar de camino en cualquier momento.", "Come as you are. You can choose another path at any time.", "Venha como está. Você pode escolher outro caminho a qualquer momento."))
                TextButton(onClick = onChooseLanguage, modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("welcome_language")) { Text(language.journey("Idioma: ", "Language: ", "Idioma: ") + language.displayName) }
                Spacer(Modifier.height(16.dp))
                listOf(language.journey("Estoy explorando", "I'm exploring", "Estou explorando"), language.journey("Estoy regresando", "I'm returning", "Estou voltando"), language.journey("Estoy practicando", "I'm practicing", "Estou praticando")).forEachIndexed { i, label ->
                    TextButton(onClick = { profile = i }, modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("welcome_profile_$i")) { Text((if (profile == i) "✓ " else "") + label, color = p.textPrimary) }
                }
                Text(language.journey("Tiempo para hoy", "Time for today", "Tempo para hoje"))
                Row { listOf(2, 5, 10).forEach { minutes -> TextButton(onClick = { time = minutes }, modifier = Modifier.weight(1f).heightIn(min = 48.dp).testTag("welcome_minutes_$minutes")) { Text((if (time == minutes) "✓ " else "") + "$minutes min") } } }
            }
        },
        confirmButton = { TextButton(onClick = { store.welcome(profile, time); onClose() }, modifier = Modifier.testTag("welcome_begin")) { Text(language.journey("Comenzar", "Begin", "Começar")) } },
        dismissButton = { TextButton(onClick = { store.welcome(profile, time); onClose() }, modifier = Modifier.testTag("welcome_skip")) { Text(language.journey("Omitir", "Skip", "Pular")) } }
    )
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
    val uri = androidx.compose.ui.platform.LocalUriHandler.current
    LazyColumn(Modifier.fillMaxSize().testTag("beginner_journey_screen"), contentPadding = PaddingValues(horizontal = design.spacing.gutter, vertical = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { AppScreenTitle(l.journey("Primeros pasos", "Start here", "Primeiros passos"), l.journey("Siete pasos a tu ritmo. Puedes saltar cualquiera.", "Seven steps at your pace. You may skip any step.", "Sete passos no seu ritmo. Você pode pular qualquer um.")) }
        item { AppCard {
            Text("${selected + 1} · ${titles[selected]}", style = styles.title2, color = design.palette.textPrimary)
            Spacer(Modifier.height(12.dp))
            Text(bodies[selected], style = styles.reading, color = design.palette.textPrimary)
            Spacer(Modifier.height(16.dp))
            AppPrimaryCapsule(label = when(selected) { 0, 2 -> l.journey("Abrir el Evangelio", "Open the Gospel", "Abrir o Evangelho"); 1, 6 -> l.journey("Rezar", "Pray", "Rezar"); else -> l.journey("Encontrar una parroquia", "Find a parish", "Encontrar uma paróquia") }, onClick = when(selected) { 0,2 -> onOpenBible; 1,6 -> onOpenPrayer; else -> onOpenParishes }, tag = "journey_action")
            Spacer(Modifier.height(12.dp))
            Text(l.journey("Guía editorial · Fuentes: ", "Editorial guide · Sources: ", "Guia editorial · Fontes: ") + sources[selected], style = styles.footnote, color = design.palette.textSecondary)
            TextButton(onClick = { uri.openUri(sourceUrls[selected]) }) { Text(l.journey("Consultar la fuente", "Read the source", "Consultar a fonte")) }
            if (selected < 6) AppSecondaryCapsule(l.journey("Paso siguiente", "Next step", "Próximo passo"), onClick = { selected++; store.advance(selected) }, tag = "journey_next")
        } }
        items(7) { index -> AppListRow("${index + 1}. ${titles[index]}", selected = selected == index, onClick = { selected = index; store.advance(index) }, modifier = Modifier.testTag("journey_step_$index")) }
    }
}
