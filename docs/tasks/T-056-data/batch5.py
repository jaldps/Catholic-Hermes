# -*- coding: utf-8 -*-
"""Lote 5: CD-37, CD-40, CD-44, CD-45, CD-54, CD-17, CD-19, CD-20, CD-22."""
from lib import L, B, P, T, V, rosary59, DL, DS

CHAPLETS=[]

RISE=L("Arise, O Lord, and let Thy enemies be scattered, and let them that hate Thee flee before Thy Face!",
 "Levántate, Señor, y sean dispersados tus enemigos, y huyan de tu presencia los que te odian.",
 "Levai-vos, Senhor, e sejam dispersados os vossos inimigos, e fujam da vossa face os que vos odeiam.")
MERCY=L("My Jesus, mercy.","Jesús mío, misericordia.","Meu Jesus, misericórdia.")
beads37=[B("medal")]+[b for _ in range(5) for b in (B("large"),B("small",6))]+[B("large"),B("small",3)]
steps37=[P(0,"sign_of_the_cross"),
 V(None,L("O God, incline unto my aid.","Oh Dios, inclínate a auxiliarme.","Ó Deus, inclinai-vos a auxiliar-me."),
      L("O Lord, make haste to help me.","Señor, date prisa en socorrerme.","Senhor, apressai-vos em socorrer-me.")),
 P(0,"glory_be")]
for i in range(5):
    steps37 += [P(1+2*i,"glory_be"),T(1+2*i,MERCY,med=L("Sentido %d"%(i+1),"Sense %d"%(i+1),"Sentido %d"%(i+1))),
                T(2+2*i,RISE,rep=6)]
steps37 += [P(11,"glory_be",7),T(12,RISE,rep=3),
 T(0,L("O God, our Protector, look down upon us and cast Thine eyes upon the Face of Thy Christ!",
   "Oh Dios, nuestro Protector, míranos y vuelve tus ojos al rostro de tu Cristo.",
   "Ó Deus, nosso Protetor, olhai por nós e voltai os vossos olhos para a face do vosso Cristo."))]
CHAPLETS.append({
 "id":"santa-faz-coronilla","sourceId":"CD-37","group":"god_christ",
 "title":L("Coronilla de la Santa Faz","Chaplet of the Holy Face","Coronilha da Sagrada Faz"),
 "summary":L("39 cuentas en honor de los cinco sentidos de Cristo y de las llagas de su rostro.",
   "39 beads in honour of the five senses of Christ and the wounds of his face.",
   "39 contas em honra dos cinco sentidos de Cristo e das feridas do seu rosto."),
 "instrument":L("Medalla y 39 cuentas: 6 grandes y 33 pequeñas.",
   "Medal and 39 beads: 6 large and 33 small.","Medalha e 39 contas: 6 grandes e 33 pequenas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":beads37,"steps":steps37,
 "notes":"Texto literal de Catholic Doors. 30 cuentas = vida oculta y cinco sentidos; 3 = vida pública; 7 Glorias finales por las últimas palabras y los siete dolores.",
})

INCLINE=V(None,L("Incline unto my aid, O God.","Inclínate a auxiliarme, oh Dios.","Inclinai-vos a auxiliar-me, ó Deus."),
              L("O Lord, make haste to help me.","Señor, date prisa en socorrerme.","Senhor, apressai-vos em socorrer-me."))
NAMES=[
 ("Lord, Thou hast said: 'Ask and ye shall receive; seek, and ye shall find; knock, and it shall be opened unto you,' I seek, I knock, I ask this favor [name your intention].",
  "Señor, dijiste: «Pedid y recibiréis; buscad y hallaréis; llamad y se os abrirá». Busco, llamo, pido esta gracia [nombra tu intención].",
  "Senhor, disseste: «Pedi e recebereis; procurai e achareis; batei e vos será aberto». Procuro, bato, peço esta graça [nomeia a intenção]."),
 ("Amen, I say unto you, if ye ask the Father anything in My Name it shall be given unto you. It is of the Father and in Thy Name, Lord, I ask this favor.",
  "Amén os digo: si pedís al Padre algo en mi nombre, os será concedido. Al Padre y en tu nombre, Señor, pido esta gracia.",
  "Amém vos digo: se pedirdes ao Pai algo em meu nome, vos será concedido. Ao Pai e em teu nome, Senhor, peço esta graça."),
 ("Lord, Thou hast said: 'Heaven and earth shall pass away, but My Word shall not pass away,' Thou wilt grant me this favor because Thou hast said it and Thy word is true.",
  "Señor, dijiste: «El cielo y la tierra pasarán, pero mis palabras no pasarán». Tú me concederás esta gracia, porque lo dijiste y tu palabra es verdadera.",
  "Senhor, disseste: «O céu e a terra passarão, mas as minhas palavras não passarão». Tu me concederás esta graça, porque o disseste e a tua palavra é verdadeira."),
]
steps40=[P(None,"act_of_contrition"),INCLINE]
for i in range(3):
    steps40 += [T(2*i,L(*NAMES[i])),V(2*i+1,INCLINE["vr"]["v"],INCLINE["vr"]["r"],rep=10)]
CHAPLETS.append({
 "id":"santo-nombre-coronilla","sourceId":"CD-40","group":"god_christ",
 "title":L("Coronilla del Santo Nombre","Chaplet of the Holy Name","Coronilha do Santo Nome"),
 "summary":L("33 cuentas en tres decenas, con tres promesas de Cristo y la jaculatoria de auxilio.",
   "33 beads in three decades, with three promises of Christ and the prayer for help.",
   "33 contas em três décadas, com três promessas de Cristo e a jaculatória de auxílio."),
 "instrument":L("Coronilla de 33 cuentas (3 decenas de 1 grande + 10 pequeñas); también chotki de 33 nudos.",
   "Chaplet of 33 beads (3 decades of 1 large + 10 small); a 33-knot chotki may also be used.",
   "Coronilha de 33 contas (3 décadas de 1 grande + 10 pequenas); também se pode usar um chotki de 33 nós."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":[b for _ in range(3) for b in (B("large"),B("small",10))],
 "steps":steps40,
 "notes":"Texto literal de Catholic Doors. El marcador de intención se deja entre corchetes como en la fuente; no se declara variable porque la fuente no da un marcador explícito.",
})

WORD=L("And the Word was made flesh and dwelt amongst us.","Y el Verbo se hizo carne y habitó entre nosotros.","E o Verbo se fez carne e habitou entre nós.")
steps44=[T(0,L("Divine Infant Jesus, I adore Thy Cross and I accept all the cross Thou wilt be pleased to send me. Adorable Trinity, I offer Thee for the glory of Thy Holy Name of God, all the adorations of the Sacred Heart of the Holy Infant Jesus.",
 "Divino Niño Jesús, adoro tu Cruz y acepto todas las cruces que quieras enviarme. Adorable Trinidad, te ofrezco para la gloria de tu santo Nombre de Dios todas las adoraciones del Sagrado Corazón del Niño Jesús.",
 "Divino Menino Jesus, adoro a vossa Cruz e aceito todas as cruzes que queirais enviar-me. Adorável Trindade, ofereço-vos para a glória do vosso santo Nome de Deus todas as adorações do Sagrado Coração do Menino Jesus."))]
for i in range(3):
    steps44 += [T(1+i,WORD),P(1+i,"our_father")]
steps44 += [T(4,WORD),P(4,"hail_mary")]
for i in range(5,16): steps44.append(P(i,"hail_mary"))
steps44.append(T(None,L("Holy Infant Jesus, bless and protect us.","Niño Jesús santo, bendícenos y protégenos.","Menino Jesus santo, abençoai-nos e protegei-nos.")))
CHAPLETS.append({
 "id":"jesus-praga-coronilla","sourceId":"CD-44","group":"god_christ",
 "title":L("Coronilla del Niño Jesús de Praga","Chaplet of the Infant Jesus of Prague","Coronilha do Menino Jesus de Praga"),
 "summary":L("15 cuentas: 3 Padres Nuestros por la Sagrada Familia y 12 Avemarías por los años de la infancia.",
   "15 beads: 3 Our Fathers for the Holy Family and 12 Hail Marys for the years of the infancy.",
   "15 contas: 3 Pai-Nossos pela Sagrada Família e 12 Ave Marias pelos anos da infância."),
 "instrument":L("Coronilla de 15 cuentas.","Chaplet of 15 beads.","Coronilha de 15 contas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":[B("medal")]+[B("large") for _ in range(3)]+[B("small") for _ in range(12)],
 "steps":steps44,
 "notes":"Texto literal de Catholic Doors; la frase «And the Word was made flesh» precede a cada una de las 15 oraciones.",
})

CHAPLETS.append({
 "id":"santisima-trinidad-coronilla","sourceId":"CD-45","group":"god_christ",
 "title":L("Coronilla de la Santísima Trinidad","Chaplet of the Most Holy Trinity","Coronilha da Santíssima Trindade"),
 "summary":L("Rosario ofrecido por las cinco llagas en reparación, con alabanzas trinitarias.",
   "Rosary offered through the five wounds in reparation, with Trinitarian praises.",
   "Terço oferecido pelas cinco chagas em reparação, com louvores trinitários."),
 "instrument":L("Rosario normal.","Ordinary rosary.","Terço comum."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":rosary59(),
 "steps":[
   T(0,L("Incline unto mine aid, O God, O Lord make hast to help me.","Inclínate a auxiliarme, oh Dios; Señor, date prisa en socorrerme.","Inclinai-vos a auxiliar-me, ó Deus; Senhor, apressai-vos em socorrer-me.")),
   T(2,L("Glory be to Thee, O most sweet, most noble, resplendent, peaceful, ineffable Trinity. Amen",
     "Gloria a ti, dulcísima, nobilísima, resplandeciente, pacífica e inefable Trinidad. Amén.",
     "Glória a ti, docíssima, nobilíssima, resplandecente, pacífica e inefável Trindade. Amém."),rep=3),
   *[s for i in range(5) for s in (
      T(DS[i],L("Glory, honor and praise be to the great Eternal God. Amen.","Gloria, honor y alabanza al gran Dios Eterno. Amén.","Glória, honra e louvor ao grande Deus Eterno. Amém."),rep=10),
      P(DL[i],"glory_be"))],
   P(None,"glory_be",3),
   T(None,L("Blessed be God! Blessed be his Holy Name! Blessed be Jesus Christ, true God and true man! Blessed be the Name of Jesus! Blessed be his Most Sacred Heart! Blessed be his Most Precious Blood! Blessed be Jesus in the Most Holy Sacrament of the Altar! Blessed be the Holy Spirit, the Paraclete! Blessed be the great Mother of God, Mary most Holy! Blessed be her holy and Immaculate Conception! Blessed be her glorious Assumption! Blessed be the name of Mary, Virgin and Mother! Blessed be St. Joseph, her most chaste spouse! Blessed be God in his Angels and his Saints!",
     "Bendito sea Dios. Bendito sea su santo Nombre. Bendito sea Jesucristo, verdadero Dios y verdadero hombre. Bendito sea el Nombre de Jesús. Bendito sea su Sagrado Corazón. Bendita sea su Preciosa Sangre. Bendito sea Jesús en el Santísimo Sacramento del altar. Bendito sea el Espíritu Santo, el Paráclito. Bendita sea la gran Madre de Dios, María Santísima. Bendita sea su santa e Inmaculada Concepción. Bendita sea su gloriosa Asunción. Bendito sea el nombre de María, Virgen y Madre. Bendito sea san José, su esposo castísimo. Bendito sea Dios en sus ángeles y sus santos.",
     "Bendito seja Deus! Bendito seja o seu santo Nome! Bendito seja Jesus Cristo, verdadeiro Deus e verdadeiro homem! Bendito seja o Nome de Jesus! Bendito seja o seu Sagrado Coração! Bendita seja o seu Precioso Sangue! Bendito seja Jesus no Santíssimo Sacramento do altar! Bendito seja o Espírito Santo, o Paráclito! Bendita seja a grande Mãe de Deus, Maria Santíssima! Bendita seja a sua santa e Imaculada Conceição! Bendita seja a sua gloriosa Assunção! Bendito seja o nome de Maria, Virgem e Mãe! Bendito seja São José, seu esposo castíssimo! Bendito seja Deus nos seus anjos e nos seus santos!"),med=L("Alabanzas divinas","Divine Praises","Louvores divinos")),
 ],
 "notes":"Texto literal de Catholic Doors; se conserva la errata «make hast». Las alabanzas divinas van como texto continuo.",
})

STATIONS=["Jesus is condemned to death.","Jesus is made to bear His Cross.","Jesus Falls the First Time.","Jesus is met by His Blessed Mother.","The cross is laid on Simon of Cyrene.","Veronica wipes the face of Jesus.","Jesus falls the second time.","Jesus speaks to the women of Jerusalem.","Jesus falls the third time.","Jesus is stripped and receives gall to drink.","Jesus is nailed to the cross.","Jesus dies on the cross.","Jesus is taken down from the cross.","Jesus is laid in the sepulchre."]
STATIONS_ES=["Jesús es condenado a muerte.","Jesús carga con la cruz.","Jesús cae por primera vez.","Jesús encuentra a su Madre.","La cruz es puesta sobre Simón de Cireneo.","Verónica enjuga el rostro de Jesús.","Jesús cae por segunda vez.","Jesús habla a las mujeres de Jerusalén.","Jesús cae por tercera vez.","Jesús es despojado y recibe hiel.","Jesús es clavado en la cruz.","Jesús muere en la cruz.","Jesús es bajado de la cruz.","Jesús es puesto en el sepulcro."]
STATIONS_PT=["Jesus é condenado à morte.","Jesus carrega a cruz.","Jesus cai pela primeira vez.","Jesus encontra a sua Mãe.","A cruz é posta sobre Simão de Cireneu.","Verónica enxuga o rosto de Jesus.","Jesus cai pela segunda vez.","Jesus fala às mulheres de Jerusalém.","Jesus cai pela terceira vez.","Jesus é despojado e recebe fel.","Jesus é cravado na cruz.","Jesus morre na cruz.","Jesus é descido da cruz.","Jesus é colocado no sepulcro."]
steps54=[P(0,"sign_of_the_cross")]
for i in range(14):
    steps54 += [T(None,L("We adore You, O Christ, and we bless You because by Your Holy Cross You have redeemed the World.",
      "Te adoramos, oh Cristo, y te bendecimos, porque por tu santa Cruz redimiste al mundo.",
      "Adoramos-vos, ó Cristo, e vos bendizemos, porque pela vossa Santa Cruz resgatastes o mundo."),
      med=L(STATIONS_ES[i],STATIONS[i],STATIONS_PT[i])),
      P(1+i,"our_father"),P(1+i,"hail_mary"),P(1+i,"glory_be")]
CHAPLETS.append({
 "id":"via-crucis-coronilla","sourceId":"CD-54","group":"god_christ",
 "title":L("Coronilla del Vía Crucis","Chaplet of the Way of the Cross","Coronilha da Via Crucis"),
 "summary":L("Catorce estaciones con la oración de adoración y Padre Nuestro, Avemaría y Gloria.",
   "Fourteen stations with the prayer of adoration and an Our Father, Hail Mary and Glory.",
   "Catorze estações com a oração de adoração e um Pai-Nosso, Ave Maria e Glória."),
 "instrument":L("Coronilla con grupos de tres cuentas y medallas de las estaciones, más 6 cuentas y crucifijo.",
   "Chaplet with groups of three beads and medals of the stations, plus 6 beads and a crucifix.",
   "Coronilha com grupos de três contas e medalhas das estações, mais 6 contas e crucifixo."),
 "when":L("Uso libre; Vía Crucis.","At any time; Way of the Cross.","Uso livre; Via Crucis."),"weekdays":[],
 "beads":[B("cross")]+[B("small",3) for _ in range(14)]+[B("small",6)],
 "steps":steps54,
 "notes":"Incoherencia de la fuente resuelta con el texto real: anuncia 15 grupos de 3 cuentas pero enumera solo 14 estaciones; se modelan 14. La fuente añade que el privó el uso de indulgencias por el Crucifijo en 1912; no se reproduce como vigente.",
})

CHAPLETS.append({
 "id":"santa-ana-coronilla","sourceId":"CD-17","group":"saints",
 "title":L("Coronilla de Santa Ana","Chaplet of Saint Anne","Coronilha de Santa Ana"),
 "summary":L("Tres bloques de un Padre Nuestro y cinco Avemarías en honor de Jesús, María y Santa Ana.",
   "Three blocks of an Our Father and five Hail Marys in honour of Jesus, Mary and St Anne.",
   "Três blocos de um Pai-Nosso e cinco Ave Marias em honra de Jesus, Maria e Santa Ana."),
 "instrument":L("Sin instrumento: se cuentan oraciones, no cuentas.","No instrument: prayers are counted, not beads.","Sem instrumento: contam-se orações, não contas."),
 "when":L("Uso libre; se le prometen favores espirituales y temporales.","At any time; spiritual and temporal favours are promised.","Uso livre; prometem-se favores espirituais e temporais."),"weekdays":[],"variables":["intention"],
 "beads":[],
 "steps":[
   P(None,"our_father",med=L("In honour of Jesus.","En honor de Jesús.","Em honra de Jesus.")),
   P(None,"hail_mary",5),
   T(None,L("Jesus, Mary and St. Anne, grant the favour I ask.","Jesús, María y Santa Ana, concededme el favor que pido.","Jesus, Maria e Santa Ana, concedei-me o favor que peço.")),
   P(None,"our_father",med=L("In honour of Mary.","En honor de María.","Em honra de Maria.")),
   P(None,"hail_mary",5),
   T(None,L("Jesus, Mary and St. Anne, grant the favour I ask.","Jesús, María y Santa Ana, concededme el favor que pido.","Jesus, Maria e Santa Ana, concedei-me o favor que peço.")),
   P(None,"our_father",med=L("In honour of Saint Anne.","En honor de Santa Ana.","Em honra de Santa Ana.")),
   P(None,"hail_mary",5),
   T(None,L("Jesus, and St. Anne, grant the favour I ask.","Jesús y Santa Ana, concededme el favor que pido.","Jesus e Santa Ana, concedei-me o favor que peço.")),
 ],
 "historicalNote":{"text":L("La fuente afirma que la coronilla promete gran número de favores espirituales y temporales, sin citar documento ni autoridad.",
   "The source states that the chaplet promises many spiritual and temporal favours, without citing a document or authority.",
   "A fonte afirma que a coronilha promete grande número de favores espirituais e temporais, sem citar documento nem autoridade."),
   "source":"https://www.catholicdoors.com/prayers/chaplets/chap12.htm"},
 "notes":"Texto literal de Catholic Doors. La fuente no describe cuentas: beads vacío, como exige la spec.",
})

CHAPLETS.append({
 "id":"santa-dimpna-coronilla","sourceId":"CD-19","group":"saints",
 "title":L("Coronilla de Santa Dimpna","Chaplet of Saint Dymphna","Coronilha de Santa Dimpna"),
 "summary":L("Medalla, dos cuentas y quince Glorias en colores por los 15 años de la santa.",
   "Medal, two beads and fifteen Glories in colours for the fifteen years of the saint.",
   "Medalha, duas contas e quinze Glórias em cores pelos 15 anos da santa."),
 "instrument":L("Medalla + 2 cuentas + 5 rojas, 5 blancas y 5 verdes.",
   "Medal + 2 beads + 5 red, 5 white and 5 green.","Medalha + 2 contas + 5 vermelhas, 5 brancas e 5 verdes."),
 "when":L("Uso libre; intenciones personales al inicio o al final.","At any time; personal intentions at the start or end.","Uso livre; intenções pessoais no início ou no fim."),"weekdays":[],
 "beads":[B("medal"),B("large"),B("large"),B("small",5,color="#A52A2A"),B("small",5,color="#FFFFFF"),B("small",5,color="#2E8B57")],
 "steps":[
   P(1,"our_father"),P(2,"hail_mary",med=L("For the Holy Father's intentions.","Por las intenciones del Papa.","Pelas intenções do Papa.")),
   P(3,"glory_be",5,med=L("The red beads honour the martyrdom of St Dymphna.","Las cuentas rojas honran el martirio de Santa Dimpna.","As contas vermelhas honram o martírio de Santa Dimpna.")),
   P(4,"glory_be",5,med=L("The white beads honour her virginity.","Las cuentas blancas honran su virginidad.","As contas brancas honram a sua virgindade.")),
   P(5,"glory_be",5,med=L("The green beads are for the hope of relief of emotional disorders.","Las cuentas verdes representan la esperanza de alivio de los trastornos emocionales.","As contas verdes representam a esperança de alívio dos transtornos emocionais.")),
 ],
 "notes":"Texto literal de Catholic Doors. No se afirma curación ni garantía: la fuente habla solo de «esperanza de alivio».",
})

CHAPLETS.append({
 "id":"cabrini-coronilla","sourceId":"CD-20","group":"saints",
 "title":L("Coronilla de Santa Francisca Javier Cabrini","Chaplet of Saint Frances Xavier Cabrini","Coronilha de Santa Francesca Xavier Cabrini"),
 "summary":L("25 cuentas en cinco grupos de cinco, con Avemarías y una oración al final de cada grupo.",
   "25 beads in five sets of five, with Hail Marys and a prayer at the end of each set.",
   "25 contas em cinco grupos de cinco, com Ave Marias e uma oração no fim de cada grupo."),
 "instrument":L("25 cuentas, 5 grupos de 5, y medalla de Mother Cabrini.",
   "25 beads, 5 sets of 5, and a medal of Mother Cabrini.","25 contas, 5 grupos de 5, e medalha de Mother Cabrini."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":[B("medal")]+[b for _ in range(5) for b in (B("large"),B("small",5))],
 "steps":[P(0,"sign_of_the_cross")]+[s for i in range(5) for s in (
   P(2+2*i,"hail_mary",5),
   T(1+2*i,L("O My Saviour, hear Saint Frances Xavier Cabrini plead for me.",
     "Oh Salvador mío, escucha a Santa Francisca Javier Cabrini que ruega por mí.",
     "Ó meu Salvador, ouve Santa Francesca Xavier Cabrini que roga por mim.")))],
 "notes":"Texto literal de Catholic Doors.",
})

LONG=L("Eternal Father, I offer Thee the Most Precious Blood of Thy Divine Son, Jesus, in union with the Masses said throughout the world today, for all the holy souls in Purgatory, for sinners everywhere, for sinners in the universal Church, those in my own home, and within my family. Amen.",
 "Padre Eterno, te ofrezco la Preciosísima Sangre de tu Hijo divino, Jesús, en unión con las Misas celebradas hoy en todo el mundo, por todas las almas santas del Purgatorio, por los pecadores de todas partes, por los pecadores de la Iglesia universal, por los de mi propia casa y por los de mi familia. Amén.",
 "Pai Eterno, ofereço-vos o Preciosíssimo Sangue do vosso Filho divino, Jesus, em união com as Missas ditas hoje em todo o mundo, por todas as almas santas do Purgatório, pelos pecadores de toda parte, pelos pecadores da Igreja universal, pelos da minha casa e pelos da minha família. Amém.")
CHAPLETS.append({
 "id":"santa-gertrudis-coronilla","sourceId":"CD-22","group":"saints",
 "title":L("Coronilla de Santa Gertrudis","Chaplet of Saint Gertrude","Coronilha de Santa Gertrudes"),
 "summary":L("Rosario con ofrenda de la Preciosa Sangre y dos oraciones al final de cada decena.",
   "Rosary with an offering of the Precious Blood and two prayers at the end of each decade.",
   "Terço com oferta do Precioso Sangue e duas orações no fim de cada década."),
 "instrument":L("Rosario de 59 cuentas.","59-bead rosary.","Terço de 59 contas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":rosary59(),
 "steps":[
   P(0,"creed"),P(1,"our_father"),P(2,"hail_mary",3),P(3,"glory_be"),
   *[s for i in range(5) for s in (
      P(DL[i],"our_father"),
      T(DS[i],LONG,rep=10),
      T(DL[i],L("Most Sacred Heart of Jesus, open the hearts and minds of sinners to the truth and light of God, the Father.",
        "Sagrado Corazón de Jesús, abre los corazones y las mentes de los pecadores a la verdad y a la luz de Dios Padre.",
        "Sagrado Coração de Jesus, abre os corações e as mentes dos pecadores à verdade e à luz de Deus Pai.")),
      T(DL[i],L("Immaculate Heart of Mary, pray for the conversion of sinners and the world.",
        "Corazón Inmaculado de María, ruega por la conversión de los pecadores y del mundo.",
        "Coração Imaculado de Maria, roga pela conversão dos pecadores e do mundo.")),
      P(DL[i],"glory_be"))],
 ],
 "notes":"Texto literal de Catholic Doors. La oración de las cuentas pequeñas es larga: conviene mostrarla completa en pantalla.",
})
