# -*- coding: utf-8 -*-
"""Lote 6: CD-24, CD-25, CD-26, CD-28, CD-29, CD-30, CD-32, CD-58."""
from lib import L, B, P, T, V

CHAPLETS=[]

CHAPLETS.append({
 "id":"san-judas-coronilla","sourceId":"CD-24","group":"saints",
 "title":L("Coronilla de San Judas","Chaplet of Saint Jude","Coronilha de São Judas"),
 "summary":L("Cruz, nueve cuentas y medalla, con Avemarías e invocaciones a San Judas.",
   "Cross, nine beads and medal, with Hail Marys and invocations to St Jude.",
   "Cruz, nove contas e medalha, com Ave Marias e invocações a São Judas."),
 "instrument":L("Cruz, 9 cuentas y medalla de San Judas.","Cross, 9 beads and St Jude medal.","Cruz, 9 contas e medalha de São Judas."),
 "when":L("Uso libre; causas desesperadas.","At any time; desperate cases.","Uso livre; causas desesperadas."),"weekdays":[],"variables":["intention"],
 "beads":[B("cross")]+[B("small") for _ in range(9)]+[B("medal")],
 "steps":[
   T(0,L("Saint Jude, glorious Apostle, faithful servant and friend of Jesus, the name of the traitor has caused you to be forgotten by many, but the true Church invokes you universally as the Patron of things despaired of; pray for me, that finally I may receive the consolations and the succor of Heaven in all my necessities, tribulations and sufferings, particularly... (State your intention here.) and that I may bless God with the Elect throughout Eternity. Amen.",
     "San Judas, apóstol glorioso, siervo fiel y amigo de Jesús, el nombre del traidor ha hecho que muchos te olviden, pero la Iglesia verdadera te invoca universalmente como patrono de las causas desesperadas; ruega por mí para que reciba finalmente los consuelos y el auxilio del cielo en todas mis necesidades, tribulaciones y sufrimientos, especialmente... (expón aquí tu intención), y para que bendiga a Dios con los elegidos por toda la eternidad. Amén.",
     "São Judas, apóstolo glorioso, servo fiel e amigo de Jesus, o nome do traidor fez com que muitos te esquecessem, mas a verdadeira Igreja te invoca universalmente como padroeiro das causas desesperadas; roga por mim para que receba finalmente os consolos e o auxílio do céu em todas as minhas necessidades, tribulações e sofrimentos, especialmente... (aqui a intenção), e para que abençoe a Deus com os eleitos por toda a eternidade. Amém.")),
   *[s for i in range(9) for s in (
      P(1+i,"hail_mary"),
      T(1+i,L("Saint Jude, Apostle martyr and relative of Our Lord Jesus Christ, Mary and of Joseph, intercede for us.",
        "San Judas, apóstol mártir y pariente de nuestro Señor Jesucristo, de María y de José, intercede por nosotros.",
        "São Judas, apóstolo mártir e parente do nosso Senhor Jesus Cristo, de Maria e de José, intercede por nós.")))],
   T(10,L("May the Most Sacred Heart of Jesus be adored, and loved in all the tabernacles until the end of time. Amen. May the most Sacred Heart of Jesus be praised and glorified now and forever. Amen Saint Jude pray for us and hear our prayers. Amen. Blessed be the Sacred Heart of Jesus. Blessed be the Immaculate Heart of Mary. Blessed be Saint Jude Thaddaeus, in all the world and for all Eternity.",
     "Que el Sagradísimo Corazón de Jesús sea adorado y amado en todos los sagrarios hasta el fin de los tiempos. Amén. Que el Sagradísimo Corazón de Jesús sea alabado y glorificado ahora y siempre. Amén. San Judas, ruega por nosotros y escucha nuestras plegarias. Amén. Bendito sea el Sagrado Corazón de Jesús. Bendito sea el Inmaculado Corazón de María. Bendito sea San Judas Tadeo, en todo el mundo y por toda la eternidad.",
     "Que o Sacratíssimo Coração de Jesus seja adorado e amado em todos os tabernáculos até ao fim dos tempos. Amém. Que o Sacratíssimo Coração de Jesus seja louvado e glorificado agora e sempre. Amém. São Judas, roga por nós e ouve as nossas preces. Amém. Bendito seja o Sagrado Coração de Jesus. Bendito seja o Imaculado Coração de Maria. Bendito seja São Judas Tadeu, em todo o mundo e por toda a eternidade.")),
 ],
 "notes":"Texto literal de Catholic Doors.",
})

CHAPLETS.append({
 "id":"kateri-coronilla","sourceId":"CD-25","group":"saints",
 "title":L("Coronilla de Santa Kateri Tekakwitha","Chaplet of Saint Kateri Tekakwitha","Coronilha de Santa Kateri Tekakwitha"),
 "summary":L("24 cuentas por los 24 años de la santa: Padre Nuestro, Avemaría y Gloria según el color.",
   "24 beads for the 24 years of the saint: Our Father, Hail Mary and Glory according to the colour.",
   "24 contas pelos 24 anos da santa: Pai-Nosso, Ave Maria e Glória conforme a cor."),
 "instrument":L("8 cuentas marrones, 8 rojas y 8 transparentes.","8 brown, 8 red and 8 crystal beads.","8 contas marrons, 8 vermelhas e 8 cristalinas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":[B("small",8,color="#8B5A2B"),B("small",8,color="#B22222"),B("small",8,color="#B0E0E6")],
 "steps":[
   P(0,"our_father",8,med=L("Brown beads, the colour of the earth.","Cuentas marrones, color de la tierra.","Contas marrons, cor da terra.")),
   P(1,"hail_mary",8,med=L("Red beads, the colour of blood.","Cuentas rojas, color de la sangre.","Contas vermelhas, cor do sangue.")),
   P(2,"glory_be",8,med=L("Crystal beads, clear like water.","Cuentas transparentes, claras como el agua.","Contas cristalinas, claras como a água.")),
 ],
 "notes":"Texto literal de Catholic Doors; también llamada Kateri Indian Rosary.",
})

CHAPLETS.append({
 "id":"maximiliano-coronilla","sourceId":"CD-26","group":"saints",
 "title":L("Coronilla de San Maximiliano Kolbe","Chaplet of Saint Maximilian Kolbe","Coronilha de São Maximiliano Kolbe"),
 "summary":L("Medalla, una cuenta con el Memorare, medalla de la Virgen y 14 cuentas con oración y jaculatoria.",
   "Medal, one bead for the Memorare, Marian medal and 14 beads with a prayer and invocation.",
   "Medalha, uma conta para o Memorare, medalha de Maria e 14 contas com oração e jaculatória."),
 "instrument":L("Medalla de San Maximiliano + 1 cuenta + medalla de la Virgen + 14 cuentas.",
   "St Maximilian medal + 1 bead + Marian medal + 14 beads.",
   "Medalha de São Maximiliano + 1 conta + medalha de Maria + 14 contas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":[B("medal"),B("small"),B("medal"),B("small",14)],
 "steps":[
   P(1,"memorare"),
   T(3,L("O Mary, conceived without sin, pray for us who have recourse to Thee, and for all those who do not have recourse to Thee, especially for the enemies of Holy Mother Church, and all those in most need of Thy mercy.",
     "Oh María, concebida sin pecado, ruega por nosotros que recurrimos a ti, y por todos los que no recurren a ti, especialmente por los enemigos de la Santa Madre Iglesia y por cuantos más necesitan de tu misericordia.",
     "Ó Maria, concebida sem pecado, roga por nós que recorremos a ti, e por todos os que não recorrem a ti, especialmente pelos inimigos da Santa Mãe Igreja e por quantos mais precisam da tua misericórdia."),rep=14),
   T(None,L("St. Maximillian, pray for us!","San Maximiliano, ruega por nosotros.","São Maximiliano, roga por nós."),rep=14),
 ],
 "notes":"Texto literal de Catholic Doors; se mantiene la grafía «Maximillian» de la fuente.",
})

CHAPLETS.append({
 "id":"padre-pio-coronilla","sourceId":"CD-28","group":"saints",
 "title":L("Coronilla de San Pío de Pietrelcina","Chaplet of Saint Padre Pio","Coronilha de São Pio de Pietrelcina"),
 "summary":L("Medalla, crucifijo y nueve cuentas marrones con una oración de petición.",
   "Medal, crucifix and nine brown beads with a prayer of petition.",
   "Medalha, crucifixo e nove contas marrons com uma oração de petição."),
 "instrument":L("Medalla, crucifijo y 3 grupos de 3 cuentas marrones.",
   "Medal, crucifix and 3 sets of 3 brown beads.","Medalha, crucifixo e 3 grupos de 3 contas marrons."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],"variables":["intention"],
 "beads":[B("medal"),B("cross")]+[B("small",3,color="#8B5A2B") for _ in range(3)],
 "steps":[
   T(2,L("Oh Jesus, full of grace and charity, victim for sinners, so impelled by Your love of us that You will to die on the cross, I humbly entreat Thee to glorify in heaven and on earth, the servant of God, Padre Pio of Pietrelcina, who generously participated in Your sufferings, who loved Thee so much and laboured so faithfully for the glory of Your heavenly Father and for the good of souls. With confidence, I beseech Thee to grant me, through his intercession, (Mention your request here...) which I ardently desire.",
     "Oh Jesús, lleno de gracia y caridad, víctima por los pecadores, tan movido por tu amor hacia nosotros que quisiste morir en la cruz, te ruego humildemente que glorifiques en el cielo y en la tierra al siervo de Dios, Padre Pío de Pietrelcina, que participó generosamente en tus sufrimientos, que te amó tanto y trabajó tan fielmente por la gloria de tu Padre celestial y por el bien de las almas. Con confianza te pido que me concedas, por su intercesión, (menciona aquí tu petición) que deseo ardientemente.",
     "Ó Jesus, cheio de graça e caridade, vítima pelos pecadores, tão impelido pelo teu amor por nós que quiseste morrer na cruz, humildemente te suplico que glorifiques no céu e na terra o servo de Deus, Padre Pio de Pietrelcina, que participou generosamente nos teus sofrimentos, que tanto te amou e trabalhou tão fielmente pela glória do teu Pai celestial e pelo bem das almas. Com confiança te peço que me concedas, pela sua intercessão, (aqui a petição) que ardentemente desejo."),rep=9),
   P(None,"our_father",3),P(None,"hail_mary",3),P(None,"glory_be",3),
 ],
 "notes":"Texto literal de Catholic Doors. El marcador de petición se deja como en la fuente, sin variable declarada.",
})

CHAPLETS.append({
 "id":"san-patricio-coronilla","sourceId":"CD-29","group":"saints",
 "title":L("Coronilla de San Patricio","Chaplet of Saint Patrick","Coronilha de São Patrício"),
 "summary":L("Medalla y 12 cuentas verdes con Gloria, más la Coraza de San Patricio.",
   "Medal and 12 green beads with the Glory, plus the Breastplate of St Patrick.",
   "Medalha e 12 contas verdes com a Glória, mais a Couraça de São Patrício."),
 "instrument":L("Medalla + 12 cuentas verdes.","Medal + 12 green beads.","Medalha + 12 contas verdes."),
 "when":L("Uso libre; para fortalecer la fe.","At any time; to strengthen faith.","Uso livre; para fortalecer a fé."),"weekdays":[],
 "beads":[B("medal")]+[B("small",12,color="#2E8B57")],
 "steps":[
   P(0,"creed"),
   P(1,"glory_be",12),
   T(None,L("Christ as a light, Illumine and guide me! Christ as a shield, overshadow and cover me! Christ be under me! Christ be over me! Christ be beside me, On the left hand and right! Christ be before me, behind me, about me; Christ this day, be within and without me!",
     "Cristo como luz, ilumíname y guíame. Cristo como escudo, cúbreme y protégeme. Cristo debajo de mí, Cristo encima de mí, Cristo a mi lado, a mi izquierda y a mi derecha. Cristo delante de mí, detrás de mí, a mi alrededor; Cristo, hoy, dentro y fuera de mí.",
     "Cristo como luz, ilumina e guia-me! Cristo como escudo, cobre e protege-me! Cristo debaixo de mim, Cristo sobre mim, Cristo ao meu lado, à esquerda e à direita! Cristo diante de mim, atrás de mim, à minha volta; Cristo, hoje, dentro e fora de mim!"),med=L("Coraza de San Patricio","Breastplate of St Patrick","Couraça de São Patrício")),
 ],
 "notes":"Texto literal de Catholic Doors.",
})

CHAPLETS.append({
 "id":"santa-filomena-coronilla","sourceId":"CD-30","group":"saints",
 "title":L("Coronilla de Santa Filomena","Chaplet of Saint Philomena","Coronilha de Santa Filomena"),
 "summary":L("Medalla con Credo, 3 cuentas blancas y 13 rojas por los años de la santa.",
   "Medal with the Creed, 3 white beads and 13 red ones for the years of the saint.",
   "Medalha com o Credo, 3 contas brancas e 13 vermelhas pelos anos da santa."),
 "instrument":L("Medalla + 3 cuentas blancas + 13 rojas.","Medal + 3 white + 13 red beads.","Medalha + 3 contas brancas + 13 vermelhas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],"variables":["intention"],
 "beads":[B("medal"),B("small",3,color="#FFFFFF"),B("small",13,color="#B22222")],
 "steps":[
   P(0,"creed"),
   P(1,"our_father",3,med=L("In honour of the three Divine Persons of the Blessed Trinity.","En honor de las tres Personas divinas de la Santísima Trinidad.","Em honra das três Pessoas divinas da Santíssima Trindade.")),
   T(2,L("Hail, O holy St. Philomena, whom I acknowledge, after Mary, as my advocate with the Divine Spouse, intercede for me now and at the hour of my death. St. Philomena, beloved daughter of Jesus and Mary, pray for us who have recourse to thee. Amen.",
     "Dios te salve, santa Filomena, a quien reconozco, después de María, como mi abogada ante el Esposo divino; intercede por mí ahora y en la hora de mi muerte. Santa Filomena, hija amada de Jesús y de María, ruega por nosotros que recurrimos a ti. Amén.",
     "Salve, ó santa Filomena, a quem reconheço, depois de Maria, como minha advogada junto do Esposo divino; intercede por mim agora e na hora da minha morte. Santa Filomena, filha amada de Jesus e de Maria, roga por nós que recorremos a ti. Amém."),rep=13),
   T(None,L("Hail, O illustrious St. Philomena, who shed so courageously your blood for Christ! I bless the Lord for all the graces He has bestowed upon thee during thy life, and especially at thy death. I praise and glorify Him for the honour and power with which He has crowned thee, and I beg thee to obtain for me from God the graces I ask through thy intercession.",
     "Dios te salve, ilustre santa Filomena, que derramaste tu sangre por Cristo con tanto valor. Bendigo al Señor por todas las gracias que te concedió durante tu vida y especialmente en tu muerte. Le alabo y glorifico por el honor y el poder con que te coronó, y te ruego que obtengas de Dios para mí las gracias que pido por tu intercesión.",
     "Salve, ó ilustre santa Filomena, que derramaste corajosamente o teu sangue por Cristo! Bendigo o Senhor por todas as graças que te concedeu durante a tua vida e especialmente na tua morte. Louvo e glorifico-o pela honra e poder com que te coroou, e peço-te que obtenhas de Deus para mim as graças que peço pela tua intercessão.")),
 ],
 "notes":"Texto literal de Catholic Doors; la intención final se deja sin completar, como en la fuente.",
})

CHAPLETS.append({
 "id":"santa-rita-coronilla","sourceId":"CD-32","group":"saints",
 "title":L("Coronilla de Santa Rita","Chaplet of Saint Rita","Coronilha de Santa Rita"),
 "summary":L("Medalla, tres grupos de tres cuentas azules y cruz, con un Padre Nuestro, tres Avemarías y tres Glorias.",
   "Medal, three sets of three blue beads and a cross, with an Our Father, three Hail Marys and three Glories.",
   "Medalha, três grupos de três contas azuis e uma cruz, com um Pai-Nosso, três Ave Marias e três Glórias."),
 "instrument":L("Medalla + 3 + 3 + 3 cuentas azules + cruz.","Medal + 3 + 3 + 3 blue beads + cross.","Medalha + 3 + 3 + 3 contas azuis + cruz."),
 "when":L("Uso libre; patrona de lo imposible.","At any time; patroness of impossible causes.","Uso livre; padroeira das causas impossíveis."),"weekdays":[],
 "beads":[B("medal")]+[B("small",3,color="#4169E1") for _ in range(3)]+[B("cross")],
 "steps":[
   T(0,L("O Holy St. Rita, exemplary Augustinian Sister, we honour you for your devotion to the Passion of Christ. Although your early life was filled with disappointment, frustration, and unceasing tragedy, you never lost faith and trust in God. For this you are the patroness of the impossible, and our inspiration and advocate in desperate circumstances.",
     "Oh santa Rita, ejemplar hermana agustina, te honramos por tu devoción a la Pasión de Cristo. Aunque tu primera vida estuvo llena de decepciones, frustraciones y tragedias incesantes, nunca perdiste la fe y la confianza en Dios. Por eso eres la patrona de lo imposible y nuestro aliento y abogada en las circunstancias desesperadas.",
     "Ó santa Rita, exemplar irmã agostiniana, honramo-te pela tua devoção à Paixão de Cristo. Embora a tua primeira vida tenha sido cheia de decepções, frustrações e tragédias incessantes, nunca perdeste a fé e a confiança em Deus. Por isso és a padroeira do impossível e o nosso incentivo e advogada nas circunstâncias desesperadas.")),
   P(0,"our_father",3),P(0,"hail_mary",3),P(0,"glory_be",3),
 ],
 "notes":"Texto literal de Catholic Doors.",
})

CHAPLETS.append({
 "id":"familia-franciscana-coronilla","sourceId":"CD-58","group":"saints",
 "title":L("Coronilla de la Familia Franciscana","Franciscan Family Chaplet","Coronilha da Família Franciscana"),
 "summary":L("Tres decenas por las tres Órdenes franciscanas con la jaculatoria «Dios mío y todo mío».",
   "Three decades for the three Franciscan Orders with the invocation 'My God and my all'.",
   "Três décadas pelas três Ordens franciscanas com a jaculatória 'Meu Deus e tudo meu'."),
 "instrument":L("Cruz de San Damián; no se especifica el número de cuentas.",
   "San Damiano cross; the number of beads is not specified.",
   "Cruz de São Damião; não se especifica o número de contas."),
 "when":L("Uso libre.","At any time.","Uso livre."),"weekdays":[],
 "beads":[B("cross")]+[b for _ in range(3) for b in (B("large"),B("small",10))],
 "steps":[
   P(0,"sign_of_the_cross"),P(0,"creed"),P(0,"hail_mary",3),
   *[s for i in range(3) for s in (
      T(1+2*i,[
        L("St. Francis of Assisi, through your intercession, I pray that the First Order increases in vocations and that its members continue to grow in holiness.",
          "San Francisco de Asís, por tu intercesión te pido que la Primera Orden aumente en vocaciones y que sus miembros sigan creciendo en santidad.",
          "São Francisco de Assis, pela tua intercessão peço que a Primeira Ordem cresça em vocações e que os seus membros continuem a crescer em santidade."),
        L("St. Francis of Assisi, through your intercession, I pray that the Second Order increases in vocations and that its members continue to grow in holiness.",
          "San Francisco de Asís, por tu intercesión te pido que la Segunda Orden aumente en vocaciones y que sus miembros sigan creciendo en santidad.",
          "São Francisco de Assis, pela tua intercessão peço que a Segunda Ordem cresça em vocações e que os seus membros continuem a crescer em santidade."),
        L("St. Francis of Assisi, through your intercession, I pray that the Third Order, both Regular and Secular, increases in vocations and that its members continue to grow in holiness.",
          "San Francisco de Asís, por tu intercesión te pido que la Tercera Orden, tanto Regular como Seglar, aumente en vocaciones y que sus miembros sigan creciendo en santidad.",
          "São Francisco de Assis, pela tua intercessão peço que a Terceira Ordem, tanto Regular como Segular, cresça em vocações e que os seus membros continuem a crescer em santidade.")][i]),
      P(1+2*i,"our_father"),
      T(2+2*i,L("My God and my all!","Dios mío y todo mío.","Meu Deus e tudo meu."),rep=10))],
   T(None,L("We adore You, O Lord Jesus Christ, because by Your holy Cross, You have redeemed the world.",
     "Te adoramos, Señor Jesucristo, porque por tu santa Cruz redimiste al mundo.",
     "Adoramos-vos, Senhor Jesus Cristo, porque pela vossa santa Cruz resgatastes o mundo.")),
   P(None,"glory_be",3,med=L("For the intentions of the Pope and his continued holiness.","Por las intenciones del Papa y su santidad.","Pelas intenções do Papa e a sua santidade.")),
   P(None,"sign_of_the_cross"),
 ],
 "notes":"Texto literal de Catholic Doors (autor Nicholas H. Kovacs, OFS). La fuente no da número de cuentas; se modelan tres decenas según el texto.",
})
