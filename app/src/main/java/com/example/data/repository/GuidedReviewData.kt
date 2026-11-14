package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.TriviaQuestion

internal data class GuidedReview(val questionNumber: Int, val item: TriviaQuestion)

/** Reviews the actual itinerary topics; each explanation identifies its primary source. */
internal object GuidedReviewData {
    val groups: Map<Int, List<GuidedReview>> = mapOf(
        0 to listOf(
            GuidedReview(13, TriviaQuestion(
                id = "guided_review_q13_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Cuántos libros reconoce el canon católico?","How many books are in the Catholic canon?","Quantos livros há no cânon católico?"),
                options = listOf(LocalizedText("66 libros","66 books","66 livros"),LocalizedText("73 libros","73 books","73 livros"),LocalizedText("Solo los 27 del Nuevo Testamento","Only the 27 New Testament books","Apenas os 27 do Novo Testamento")),
                correctIndex = 1,
                doctrinalExplanation = LocalizedText("El canon católico comprende 46 libros del Antiguo Testamento y 27 del Nuevo. Catecismo 120.","The Catholic canon comprises 46 Old Testament and 27 New Testament books. Catechism 120.","O cânon católico compreende 46 livros do Antigo Testamento e 27 do Novo. Catecismo 120.")
            )),
            GuidedReview(21, TriviaQuestion(
                id = "guided_review_q21_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Qué confiesa la Iglesia sobre la Trinidad?","What does the Church confess about the Trinity?","O que a Igreja confessa sobre a Trindade?"),
                options = listOf(LocalizedText("Tres dioses independientes","Three independent gods","Três deuses independentes"),LocalizedText("Una persona que solo cambia de nombre","One person who only changes names","Uma pessoa que apenas muda de nome"),LocalizedText("Un solo Dios en tres Personas distintas","One God in three distinct Persons","Um só Deus em três Pessoas distintas")),
                correctIndex = 2,
                doctrinalExplanation = LocalizedText("Padre, Hijo y Espíritu Santo son realmente distintos como Personas y comparten la única naturaleza divina. Catecismo 253–255.","Father, Son and Holy Spirit are really distinct as Persons and share the one divine nature. Catechism 253–255.","Pai, Filho e Espírito Santo são realmente distintos como Pessoas e partilham a única natureza divina. Catecismo 253–255.")
            )),
            GuidedReview(40, TriviaQuestion(
                id = "guided_review_q40_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Qué lugar tienen las revelaciones privadas?","What is the place of private revelations?","Qual é o lugar das revelações privadas?"),
                options = listOf(LocalizedText("Pueden ayudar a vivir la fe; no completan la Revelación de Cristo","They can help us live the faith; they do not complete Christ’s Revelation","Podem ajudar a viver a fé; não completam a Revelação de Cristo"),LocalizedText("Todo católico debe aceptar cualquier aparición anunciada","Every Catholic must accept every reported apparition","Todo católico deve aceitar qualquer aparição anunciada"),LocalizedText("Sustituyen al Evangelio cuando son recientes","They replace the Gospel when they are recent","Substituem o Evangelho quando são recentes")),
                correctIndex = 0,
                doctrinalExplanation = LocalizedText("La fe cristiana no acepta revelaciones que pretendan superar o corregir la Revelación definitiva de Cristo. Catecismo 67.","Christian faith does not accept revelations claiming to surpass or correct Christ’s definitive Revelation. Catechism 67.","A fé cristã não aceita revelações que pretendam superar ou corrigir a Revelação definitiva de Cristo. Catecismo 67.")
            )),
        ),
        1 to listOf(
            GuidedReview(41, TriviaQuestion(
                id = "guided_review_q41_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Qué responsabilidad confió Jesús a Pedro?","What responsibility did Jesus entrust to Peter?","Que responsabilidade Jesus confiou a Pedro?"),
                options = listOf(LocalizedText("Cambiar el Evangelio a voluntad","To change the Gospel at will","Mudar o Evangelho à vontade"),LocalizedText("Las llaves del Reino y el cuidado de su rebaño","The keys of the Kingdom and care of his flock","As chaves do Reino e o cuidado de seu rebanho"),LocalizedText("La promesa de no cometer nunca pecados personales","A promise never to commit personal sins","A promessa de nunca cometer pecados pessoais")),
                correctIndex = 1,
                doctrinalExplanation = LocalizedText("La misión de Pedro es un servicio de autoridad confiado por Cristo; no implica impecabilidad personal. Mateo 16,18–19; Catecismo 552–553.","Peter’s mission is a service of authority entrusted by Christ; it does not imply personal sinlessness. Matthew 16:18–19; Catechism 552–553.","A missão de Pedro é um serviço de autoridade confiado por Cristo; não implica ausência de pecados pessoais. Mateus 16,18–19; Catecismo 552–553.")
            )),
            GuidedReview(53, TriviaQuestion(
                id = "guided_review_q53_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Quién está presente en la Eucaristía?","Who is present in the Eucharist?","Quem está presente na Eucaristia?"),
                options = listOf(LocalizedText("Cristo entero: Cuerpo, Sangre, alma y divinidad","The whole Christ: Body, Blood, soul and divinity","Cristo inteiro: Corpo, Sangue, alma e divindade"),LocalizedText("Solo un símbolo de la comunidad","Only a symbol of the community","Apenas um símbolo da comunidade"),LocalizedText("Solo el recuerdo de alguien ausente","Only the memory of someone absent","Apenas a lembrança de alguém ausente")),
                correctIndex = 0,
                doctrinalExplanation = LocalizedText("La presencia eucarística es verdadera, real y sustancial: Cristo entero está presente. Catecismo 1374.","The Eucharistic presence is true, real and substantial: the whole Christ is present. Catechism 1374.","A presença eucarística é verdadeira, real e substancial: Cristo inteiro está presente. Catecismo 1374.")
            )),
            GuidedReview(54, TriviaQuestion(
                id = "guided_review_q54_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Quién ejerce el ministerio de la absolución sacramental?","Who exercises the ministry of sacramental absolution?","Quem exerce o ministério da absolvição sacramental?"),
                options = listOf(LocalizedText("Cualquier persona que escuche con buena voluntad","Anyone who listens with good will","Qualquer pessoa que escute com boa vontade"),LocalizedText("Solo la persona que cometió el pecado","Only the person who committed the sin","Apenas a pessoa que cometeu o pecado"),LocalizedText("Obispos y sacerdotes con la facultad de absolver","Bishops and priests with the faculty to absolve","Bispos e sacerdotes com a faculdade de absolver")),
                correctIndex = 2,
                doctrinalExplanation = LocalizedText("Cristo confió el ministerio de la reconciliación a los Apóstoles; los obispos y sacerdotes lo ejercen en su nombre. El sacerdote necesita la facultad correspondiente. Catecismo 1461–1462.","Christ entrusted the ministry of reconciliation to the Apostles; bishops and priests exercise it in his name. A priest needs the appropriate faculty. Catechism 1461–1462.","Cristo confiou o ministério da reconciliação aos Apóstolos; bispos e sacerdotes o exercem em seu nome. O sacerdote necessita da faculdade correspondente. Catecismo 1461–1462.")
            )),
        ),
        2 to listOf(
            GuidedReview(61, TriviaQuestion(
                id = "guided_review_q61_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Qué es el purgatorio?","What is purgatory?","O que é o purgatório?"),
                options = listOf(LocalizedText("Una segunda oportunidad después de la condenación","A second chance after damnation","Uma segunda chance após a condenação"),LocalizedText("La purificación final de quienes mueren en la gracia de Dios","The final purification of those who die in God’s grace","A purificação final de quem morre na graça de Deus"),LocalizedText("Un lugar donde nadie sabe si podrá salvarse","A place where no one knows whether salvation is possible","Um lugar onde ninguém sabe se poderá se salvar")),
                correctIndex = 1,
                doctrinalExplanation = LocalizedText("Quienes son purificados están seguros de su salvación eterna. Esta purificación es distinta del castigo de los condenados. Catecismo 1030–1032.","Those being purified are assured of eternal salvation. This purification differs from the punishment of the damned. Catechism 1030–1032.","Os que são purificados têm a salvação eterna garantida. Essa purificação é distinta do castigo dos condenados. Catecismo 1030–1032.")
            )),
            GuidedReview(66, TriviaQuestion(
                id = "guided_review_q66_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Qué condiciones deben concurrir para un pecado mortal?","Which conditions must be present for a mortal sin?","Quais condições devem estar presentes para um pecado mortal?"),
                options = listOf(LocalizedText("Cualquier error involuntario","Any involuntary mistake","Qualquer erro involuntário"),LocalizedText("Sentir una tentación sin consentirla","Feeling a temptation without consenting to it","Sentir uma tentação sem consentir nela"),LocalizedText("Materia grave, pleno conocimiento y consentimiento deliberado","Grave matter, full knowledge and deliberate consent","Matéria grave, pleno conhecimento e consentimento deliberado")),
                correctIndex = 2,
                doctrinalExplanation = LocalizedText("Las tres condiciones deben concurrir. La tentación no equivale por sí misma a consentimiento, y la responsabilidad personal requiere discernimiento. Catecismo 1857–1859.","All three conditions must be present. Temptation alone is not consent, and personal responsibility requires discernment. Catechism 1857–1859.","As três condições devem estar presentes. A tentação por si só não é consentimento, e a responsabilidade pessoal requer discernimento. Catecismo 1857–1859.")
            )),
            GuidedReview(90, TriviaQuestion(
                id = "guided_review_q90_v1",
                category = LocalizedText("Repaso guiado","Guided review","Revisão guiada"),
                question = LocalizedText("¿Cómo se expresa la caridad hacia quien necesita ayuda?","How is charity expressed toward someone in need?","Como se expressa a caridade com quem precisa de ajuda?"),
                options = listOf(LocalizedText("En obras de misericordia corporales y espirituales","Through corporal and spiritual works of mercy","Por obras de misericórdia corporais e espirituais"),LocalizedText("Solo con palabras, sin atender necesidades reales","Only through words, without meeting real needs","Apenas com palavras, sem atender necessidades reais"),LocalizedText("Ayudando únicamente a quien puede devolver el favor","By helping only those who can return the favor","Ajudando apenas quem pode retribuir o favor")),
                correctIndex = 0,
                doctrinalExplanation = LocalizedText("Enseñar, consolar, perdonar, alimentar y cuidar al enfermo son expresiones concretas de caridad. La ayuda material no agota la misericordia. Catecismo 2447.","Teaching, comforting, forgiving, feeding and caring for the sick are concrete expressions of charity. Mercy includes more than material aid. Catechism 2447.","Ensinar, consolar, perdoar, alimentar e cuidar dos enfermos são expressões concretas de caridade. A misericórdia inclui mais que a ajuda material. Catecismo 2447.")
            )),
        ),
    )

    fun quiz(trackIndex: Int): List<TriviaQuestion> = groups[trackIndex].orEmpty().map { it.item }
}
