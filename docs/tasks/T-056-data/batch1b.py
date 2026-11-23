# -*- coding: utf-8 -*-
"""Lote 1b: IVE-09, IVE-10, IVE-11, IVE-12."""
from lib import L, B, P, T, V, rosary59, DL, DS

# ---------- IVE-09 ----------
MIST = [
 ("PRIMER MISTERIO. Nuestro amabilísimo Redentor derramó por primera vez su preciosa sangre el octavo día de su nacimiento, cuando por cumplir la ley de Moisés fue circuncidado.",
  "FIRST MYSTERY. Our most loving Redeemer first shed his precious blood on the eighth day after his birth, when he was circumcised in fulfilment of the law of Moses.",
  "PRIMEIRO MISTÉRIO. O nosso amabilíssimo Redentor derramou pela primeira vez o seu precioso sangue no oitavo dia do seu nascimento, quando foi circuncidado para cumprir a lei de Moisés."),
 ("SEGUNDO MISTERIO. Jesús derramó sangre en el huerto de los Olivos, y en tanta cantidad, que el suelo quedó a su alrededor bañado de ella.",
  "SECOND MYSTERY. Jesus shed blood in the garden of Olives, and in such quantity that the ground around him was bathed in it.",
  "SEGUNDO MISTÉRIO. Jesus derramou sangue no horto das Oliveiras, e em tanta quantidade que o solo à sua volta ficou banhado dele."),
 ("TERCERA MISTERIO. El Señor derramó sangre en su cruel flagelación, cuando rasgada la piel y laceradas sus carnes, brotó por todas partes a ríos aquella Preciosa Sangre.",
  "THIRD MYSTERY. The Lord shed blood in his cruel scourging, when his skin was torn and his flesh lacerated and that Precious Blood flowed everywhere in streams.",
  "TERCEIRO MISTÉRIO. O Senhor derramou sangue na sua cruel flagelação, quando, rasgada a pele e laceradas as carnes, aquele Precioso Sangue jorrou por toda a parte em rios."),
 ("CUARTO MISTERIO. Brotó sangre de la sacratísima cabeza de Jesús cuando fue coronado de espinas, en pena de nuestra soberbia y de nuestros malos pensamientos.",
  "FOURTH MYSTERY. Blood flowed from the most holy head of Jesus when he was crowned with thorns, in atonement for our pride and our evil thoughts.",
  "QUARTO MISTÉRIO. Brotou sangue da sacratíssima cabeça de Jesus quando foi coroado de espinhos, em pena da nossa soberba e dos nossos maus pensamentos."),
 ("QUINTO MISTERIO. ¡Oh! Cuánta sangre derramó de sus venas nuestro amable Jesús en el dolorosísimo camino que hizo al Calvario cargado con el pesado leño de la cruz!",
  "FIFTH MYSTERY. Oh, how much blood our loving Jesus shed from his veins on the most painful journey he made to Calvary carrying the heavy wood of the cross!",
  "QUINTO MISTÉRIO. Ó, quanto sangue derramou das suas veias o nosso amável Jesus no caminho dolorosíssimo que fez ao Calvário carregado com o pesado madeiro da cruz!"),
 ("SEXTO MISTERIO. El Redentor derramó la sangre sobre todo en su bárbara crucifixión, cuando rotas venas y arterias, brotó de sus manos y de sus pies como un torrente.",
  "SIXTH MYSTERY. The Redeemer shed blood above all in his barbarous crucifixion, when veins and arteries were broken and it flowed from his hands and feet like a torrent.",
  "SEXTO MISTÉRIO. O Redentor derramou sangue sobretudo na sua bárbara crucifixão, quando, rotas as veias e artérias, jorrou das suas mãos e dos seus pés como uma torrente."),
 ("SÉPTIMO MISTERIO. Finalmente, Jesús derramó sangre después de su muerte, cuando con una lanza le abrieron el costado, e hirieron su amabilísimo corazón.",
  "SEVENTH MYSTERY. Finally, Jesus shed blood after his death, when they opened his side with a lance and wounded his most loving heart.",
  "SÉTIMO MISTÉRIO. Finalmente, Jesus derramou sangue depois da sua morte, quando com uma lança lhe abriram o lado e feriram o seu amabilíssimo coração."),
]
JAC = L("Te rogamos, pues que vengas en ayuda de tus siervos, a quienes redimiste con tu preciosa sangre.",
        "We beseech you, Lord, help your servants, whom you redeemed with your precious blood.",
        "Rogamo-vos, Senhor, que venhais em auxílio dos vossos servos, que resgatastes com o vosso precioso sangue.")
CLOSE = L("¡Oh sangre preciosísima de vida eterna, precio y rescate de todo el universo bebida y purificación de nuestras almas, que defiendes continuamente la causa de los hombres ante el trono de la suprema misericordia! Os adoro profundamente.",
  "O most precious blood of eternal life, price and ransom of the whole universe, drink and purification of our souls, you who continually defend the cause of men before the throne of supreme mercy! I adore you profoundly.",
  "Ó sangue preciosíssimo de vida eterna, preço e resgate de todo o universo, bebida e purificação das nossas almas, que defendeis continuamente a causa dos homens ante o trono da suprema misericórdia! Adoro-vos profundamente.")

beads9 = [B("medal")]
for i in range(7):
    beads9 += [B("small",3 if i==6 else 5), B("large")]
steps9 = [P(0,"sign_of_the_cross")]
for i,(es,en,pt) in enumerate(MIST):
    small = 1+2*i; large = 2+2*i
    steps9.append(P(small,"our_father", rep=(3 if i==6 else 5), med=L(es,en,pt)))
    steps9.append(P(large,"glory_be"))
    steps9.append(T(None,JAC))
steps9.append(T(None,CLOSE, med=L("Oración final","Final prayer","Oração final")))

CHAPLETS = [{
 "id":"sangre-preciosa-corona","sourceId":"IVE-09","group":"god_christ",
 "title":L("Corona a la Sangre Preciosa de Jesús","Chaplet of the Precious Blood of Jesus","Coroa do Precioso Sangue de Jesus"),
 "summary":L("Siete misterios sobre las efusiones de la Preciosa Sangre, con 33 Padres Nuestros en total.",
   "Seven mysteries on the sheddings of the Precious Blood, with 33 Our Fathers in all.",
   "Sete mistérios sobre as efusões do Precioso Sangue, com 33 Pai-Nossos no total."),
 "instrument":L("Corona de siete misterios: cinco Padres Nuestros por misterio y tres en el último.",
   "Chaplet of seven mysteries: five Our Fathers per mystery and three in the last.",
   "Coroa de sete mistérios: cinco Pai-Nossos por mistério e três no último."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],
 "beads":beads9,
 "steps":steps9,
 "historicalNote":{"text":L("La fuente atribuye a Pío VII rescriptos de 31 de mayo de 1809 y 18 de octubre de 1815 con indulgencias; normativa vigente: Enchiridion Indulgentiarum, 4.ª ed., 1999.",
   "The source attributes to Pius VII rescripts of 31 May 1809 and 18 October 1815 with indulgences; current norms: Enchiridion Indulgentiarum, 4th ed., 1999.",
   "A fonte atribui a Pio VII rescritos de 31 de maio de 1809 e 18 de outubro de 1815 com indulgências; norma vigente: Enchiridion Indulgentiarum, 4.ª ed., 1999."),
   "source":"https://iveinarabic.org/es/rosarios-y-coronas/"},
 "notes":"Texto literal de IVE. Errata corregida: «TERCERA MISTERIO» → «TERCER MISTERIO» se conserva como en la fuente. El último misterio lleva 3 Padres Nuestros y Gloria, completando 33.",
}]

# ---------- IVE-10 ----------
M10 = [
 ("La Encarnación de Jesús en el seno de María. «El Espíritu Santo vendrá sobre ti y el poder del Altísimo te cubrirá con su sombra». (Lc. 1,26-28).",
  "The incarnation of Jesus in the womb of Mary. 'The Holy Spirit will come upon you and the power of the Most High will overshadow you.' (Luke 1:26-28).",
  "A Encarnação de Jesus no seio de Maria. «O Espírito Santo virá sobre ti e o poder do Altíssimo te cobrirá com a sua sombra». (Lc 1,26-28)."),
 ("Bautismo de Jesús en el Jordán. «Bautizado Jesús, bajó sobre él, el Espíritu Santo en forma de paloma y una voz del cielo: Tú eres mi Hijo». (Lc. 3,21-22).",
  "The baptism of Jesus in the Jordan. 'When Jesus was baptised, the Holy Spirit descended upon him in the form of a dove, and a voice from heaven said: You are my Son.' (Luke 3:21-22).",
  "Batismo de Jesus no Jordão. «Batizado Jesus, desceu sobre ele o Espírito Santo em forma de pomba e uma voz do céu: Tu és o meu Filho». (Lc 3,21-22)."),
 ("Jesús muere en la cruz. «Inclinando la cabeza entregó el Espíritu» (In. 19,30).",
  "Jesus dies on the cross. 'Bowing his head, he gave up the Spirit' (John 19:30).",
  "Jesus morre na cruz. «Inclinando a cabeça, entregou o Espírito» (Jo 19,30)."),
 ("Jesús resucitado se aparece a sus discípulos. «Sopló sobre ellos y dijo: Reciban el Espíritu Santo». (Jn. 20,22).",
  "The risen Jesus appears to his disciples. 'He breathed on them and said: Receive the Holy Spirit.' (John 20:22).",
  "Jesus ressuscitado aparece aos seus discípulos. «Soprou sobre eles e disse: Recebei o Espírito Santo». (Jo 20,22)."),
 ("La venida del Espíritu Santo sobre los apóstoles y María. «El día de Pentecostés, estaban todos reunidos orando; quedaron llenos del Espíritu Santo y se pusieron a hablar en lenguas». (Hch. 2,1-13)",
  "The coming of the Holy Spirit upon the apostles and Mary. 'On the day of Pentecost they were all gathered in prayer; they were filled with the Holy Spirit and began to speak in tongues.' (Acts 2:1-13)",
  "A vinda do Espírito Santo sobre os apóstolos e Maria. «No dia de Pentecostes estavam todos reunidos em oração; ficaram cheios do Espírito Santo e começaram a falar em línguas». (At 2,1-13)"),
]
CHAPLETS.append({
 "id":"rosario-espiritu-santo","sourceId":"IVE-10","group":"god_christ",
 "title":L("El Rosario del Espíritu Santo","The Rosary of the Holy Spirit","O Terço do Espírito Santo"),
 "summary":L("Cinco misterios bíblicos del Espíritu Santo, con lectura y Padre Nuestro, diez Avemarías y Gloria en cada uno.",
   "Five biblical mysteries of the Holy Spirit, each with a scripture reading, an Our Father, ten Hail Marys and a Glory.",
   "Cinco mistérios bíblicos do Espírito Santo, cada um com leitura bíblica, Pai-Nosso, dez Ave Marias e Glória."),
 "instrument":L("Rosario de cinco misterios.","A rosary of five mysteries.","Terço de cinco mistérios."),
 "when":L("Se sugiere el domingo, en que se recuerda la Pascua del Señor y el envío del Espíritu; o cuando se sienta el llamado.",
   "Sunday is suggested, recalling the Pascha of the Lord and the sending of the Spirit; or whenever one feels called to it.",
   "Sugere-se o domingo, em que se recorda a Páscoa do Senhor e o envio do Espírito; ou quando se sentir o chamado."),
 "weekdays":[7],
 "beads":rosary59(),
 "steps":[
   T(None,L("Lave lo que esté manchado, Riegue lo que esté reseco, Sane lo que esté enfermo, Doblegue lo que esté rígido, Caliente lo que esté frío, Enderece lo que esté desviado, y dé los Siete Santos Dones…",
     "Wash what is stained, water what is dry, heal what is sick, bend what is rigid, warm what is cold, straighten what is crooked, and give the Seven Holy Gifts…",
     "Lava o que está manchado, rega o que está seco, sana o que está doente, dobra o que está rígido, aquece o que está frio, endireita o que está desviado, e dá os Sete Santos Dons…"), med=L("Introducción","Introduction","Introdução")),
   *[s for i,(es,en,pt) in enumerate(M10) for s in (
      P(DL[i],"our_father",med=L(es,en,pt)),
      P(DS[i],"hail_mary",rep=10),
      P(DL[i] if i<4 else 3,"glory_be"))],
   T(None,L("Ven, Espíritu Santo, a renovar la faz de la tierra.",
     "Come, Holy Spirit, to renew the face of the earth.",
     "Vem, Espírito Santo, renovar a face da terra."), med=L("Conclusión","Conclusion","Conclusão")),
 ],
 "notes":"Texto literal de IVE. Cada misterio enuncia el título, lee la cita bíblica y reza Padre Nuestro, 10 Avemarías y Gloria.",
})

# ---------- IVE-11 ----------
D11 = [
 ("1º Dolor: La profecía de Simeón en la presentación del Niño Jesús.",
  "1st Sorrow: The prophecy of Simeon at the presentation of the Child Jesus.",
  "1.ª Dor: A profecia de Simeão na apresentação do Menino Jesus."),
 ("2º Dolor: La huida a Egipto con Jesús y José.",
  "2nd Sorrow: The flight into Egypt with Jesus and Joseph.",
  "2.ª Dor: A fuga para o Egito com Jesus e José."),
 ("3º Dolor: La pérdida de Jesús.",
  "3rd Sorrow: The loss of Jesus.",
  "3.ª Dor: A perda de Jesus."),
 ("4º Dolor: El encuentro de Jesús con la cruz a cuestas camino del calvario.",
  "4th Sorrow: Jesus meets his cross on the way to Calvary.",
  "4.ª Dor: O encontro de Jesus com a cruz a caminho do Calvário."),
 ("5º Dolor: La crucifixión y la agonía de Jesús.",
  "5th Sorrow: The crucifixion and agony of Jesus.",
  "5.ª Dor: A crucificação e a agonia de Jesus."),
 ("6º Dolor: La lanzada y el recibir en brazos a Jesús ya muerto.",
  "6th Sorrow: The piercing with the lance and receiving the dead Jesus in one's arms.",
  "6.ª Dor: A lanceada e receber nos braços Jesus já morto."),
 ("7º Dolor: El entierro de Jesús y la soledad de María.",
  "7th Sorrow: The burial of Jesus and the loneliness of Mary.",
  "7.ª Dor: O sepultamento de Jesus e a solidão de Maria."),
]
beads11=[]
for _ in range(7):
    beads11 += [B("large"), B("small",7), B("large")]
steps11=[T(None,L("El camino para penetrar en los sufrimientos del Hijo es penetrar en los sufrimientos de la Madre. Cardenal J. H. Newman",
    "The way to enter into the sufferings of the Son is to enter into the sufferings of the Mother. Cardinal J. H. Newman",
    "O caminho para penetrar nos sofrimentos do Filho é penetrar nos sofrimentos da Mãe. Cardeal J. H. Newman"), med=L("Cita inicial","Opening quote","Citação inicial"))]
for i,(es,en,pt) in enumerate(D11):
    steps11 += [P(3*i,"our_father",med=L(es,en,pt)),
                P(3*i+1,"hail_mary",rep=7),
                P(3*i+2,"glory_be")]
CHAPLETS.append({
 "id":"siete-dolores-corona","sourceId":"IVE-11","group":"mary",
 "title":L("Corona de los Siete Dolores","Chaplet of the Seven Sorrows","Coroa das Sete Dores"),
 "summary":L("Siete dolores de María: un Padre Nuestro, siete Avemarías y un Gloria en cada uno.",
   "Seven sorrows of Mary: an Our Father, seven Hail Marys and a Glory for each.",
   "Sete dores de Maria: um Pai-Nosso, sete Ave Marias e uma Glória para cada."),
 "instrument":L("Corona de siete grupos de siete cuentas, con cuentas grandes separadoras.",
   "Chaplet of seven groups of seven beads, with separating large beads.",
   "Coroa de sete grupos de sete contas, com contas grandes separadoras."),
 "when":L("Uso libre; rezar despacio.","At any time; pray slowly.","Uso livre; rezar devagar."),
 "weekdays":[],
 "beads":beads11,
 "steps":steps11,
 "historicalNote":{"text":L("La fuente refiere que la Santísima Virgen manifestó a Santa Brígida siete gracias para quienes honren sus lágrimas y dolores rezando siete Avemarías al día.",
   "The source reports that the Blessed Virgin revealed to Saint Bridget seven graces for those who honour her tears and sorrows by praying seven Hail Marys a day.",
   "A fonte relata que a Santíssima Virgem manifestou a Santa Brígida sete graças para quem honra as suas lágrimas e dores rezando sete Ave Marias por dia."),
   "source":"https://iveinarabic.org/es/rosarios-y-coronas/"},
 "notes":"Texto literal de IVE. Se modelan los títulos de los siete dolores; las meditaciones largas de la fuente no se convierten en pasos para no parafrasearlas.",
})

# ---------- IVE-12 ----------
ALAB = [
 ("Bienaventurada eres, Virgen María, que llevaste en tu seno al Señor y creador del mundo, engendraste al que te formó permaneciendo siempre Virgen.",
  "Blessed are you, Virgin Mary, who bore in your womb the Lord and creator of the world; you begot the one who formed you, remaining ever a virgin.",
  "Bem-aventurada és, Virgem Maria, que levaste no teu seio o Senhor e criador do mundo, geraste aquele que te formou, permanecendo sempre Virgem."),
 ("Oh Virgen Santa e Inmaculada, no sé con alabanzas honrarte dignamente, porque llevaste en tu seno al que no pueden contener los cielos.",
  "O holy and immaculate Virgin, I know not how to honour you worthily with praises, for you bore in your womb the one whom the heavens cannot contain.",
  "Ó Virgem santa e imaculada, não sei como louvar-te dignamente, porque levaste no teu seio aquele que os céus não podem conter."),
 ("Muy hermosa eres oh María No hay en ti mancha alguna.",
  "You are very beautiful, O Mary; there is no stain in you.",
  "Muito bela és, ó Maria; não há em ti mácula alguma."),
 ("Hay más virtudes en ti Virgen María Que estrellas en el cielo.",
  "There are more virtues in you, Virgin Mary, than stars in heaven.",
  "Há mais virtudes em ti, Virgem Maria, do que estrelas no céu."),
 ("Gloria a ti Reina del universo condúcenos contigo a la felicidad del cielo.",
  "Glory to you, Queen of the universe; lead us with you to the happiness of heaven.",
  "Glória a ti, Rainha do universo; conduz-nos contigo à felicidade do céu."),
 ("Gloria a ti, Tesorera de las gracias del Señor, danos participar en los dones de Dios.",
  "Glory to you, treasurer of the Lord's graces; grant us a share in the gifts of God.",
  "Glória a ti, tesoureira das graças do Senhor; dá-nos participar nos dons de Deus."),
 ("Gloria a ti mediadora entre Dios y los hombres haz que sea más intimo nuestro encuentro con Cristo.",
  "Glory to you, mediator between God and men; make our encounter with Christ more intimate.",
  "Glória a ti, mediadora entre Deus e os homens; faze mais íntimo o nosso encontro com Cristo."),
 ("Gloria a ti triunfadora sobre las fuerzas del mal sé nuestra piadosa guía en el camino del Evangelio.",
  "Glory to you, triumphant over the forces of evil; be our loving guide on the way of the Gospel.",
  "Glória a ti, triunfante sobre as forças do mal; sê a nossa guia piedosa no caminho do Evangelho."),
 ("Gloria a ti, refugio de los pecadores, Intercede por nosotros ante el Señor.",
  "Glory to you, refuge of sinners; intercede for us before the Lord.",
  "Glória a ti, refúgio dos pecadores; intercede por nós diante do Senhor."),
 ("Gloria a ti, Madre de los hombres, Enséñanos a vivir como hijos de Dios.",
  "Glory to you, Mother of men; teach us to live as children of God.",
  "Glória a ti, Mãe dos homens; ensina-nos a viver como filhos de Deus."),
 ("Gloria a ti, Alegría de los justos condúcenos contigo a las alegrías del cielo.",
  "Glory to you, joy of the just; lead us with you to the joys of heaven.",
  "Glória a ti, alegria dos justos; conduz-nos contigo às alegrias do céu."),
 ("Gloria a ti, prestísima ayuda nuestra en la vida y en la muerte, llévanos contigo al reino de los cielos.",
  "Glory to you, our most ready help in life and in death; take us with you to the kingdom of heaven.",
  "Glória a ti, auxílio nosso prontíssimo na vida e na morte; leva-nos contigo ao reino dos céus."),
]
VR12 = V(None, L("V/ Regocíjate, Virgen María","V/ Rejoice, Virgin Mary","V/ Regozija-te, Virgem Maria"),
               L("R/ Regocíjate mil veces","R/ Rejoice a thousand times","R/ Regozija-te mil vezes"))
steps12=[V(None,L("V/ Dígnate aceptar mis alabanzas Virgen Santísima","V/ Be pleased to accept my praises, most holy Virgin","V/ Digna-te aceitar os meus louvores, Virgem Santíssima"),
                L("R/ Dame fuerzas contra tus enemigos","R/ Give me strength against your enemies","R/ Dá-me forças contra os teus inimigos")),
         P(None,"creed")]
for k,(es,en,pt) in enumerate(ALAB):
    if k in (0,4,8):
        steps12 += [T(None,L("1) CORONA DE EXCELENCIA","1) CROWN OF EXCELLENCE","1) COROA DE EXCELÊNCIA") if k==0 else
                        (L("2) CORONA DE PODER","2) CROWN OF POWER","2) COROA DE PODER") if k==4 else
                         L("3) CORONA DE BONDAD","3) CROWN OF GOODNESS","3) COROA DE BONDADE"))),
                    P(None,"our_father"), P(None,"hail_mary")]
    steps12 += [T(None,L(es,en,pt)), VR12, P(None,"hail_mary")]
    if k in (3,7,11): steps12.append(P(None,"glory_be"))
steps12.append(T(None,L("Dios te salve, María Hija de Dios Padre, Madre de Dios Hijo, Esposa del Espíritu Santo, Templo de la Santísima Trinidad. Dios te salve, María, Señora mía, Mi tesoro, mi Belleza, Reina de mi corazón, Madre, vida, dulzura y esperanza mía queridísima más aun, mi corazón y mi alma. Soy todo tuyo, oh Virgen benditísima, y todo lo mío es tuyo. More en mí tu alma para engrandecer al Señor, more en mí tu espíritu para regocijarse en Dios. Oh Virgen fidelísima, ponte como un sello sobre mi corazón para que en ti y por ti permanezca fiel en el Señor. Concédeme, por tu bondad la gracia de contarme en el número de los que amas, enseñas, diriges, nutres y proteges como hijos. Has que, despreciando por tu amor todos los consuelos terrenos, aspire continuamente a los bienes celestiales, Hasta que por medio del Espíritu Santo, Tu esposo fidelísimo, y de ti esposa suya fidelísima, Sea formado en mi Jesucristo, tu Hijo Para la gloria del Padre Celestial. Amén.",
  "Hail Mary, daughter of God the Father, Mother of God the Son, Spouse of the Holy Spirit, Temple of the Most Holy Trinity. Hail Mary, my Lady, my treasure, my beauty, Queen of my heart, Mother, life, sweetness and my most beloved hope, my heart and my soul. I am all yours, O most blessed Virgin, and all that I have is yours. Let your soul dwell in me to magnify the Lord, let your spirit dwell in me to rejoice in God. O most faithful Virgin, set yourself as a seal upon my heart, that in you and through you I may remain faithful in the Lord. Grant me, in your goodness, the grace to be counted among those you love, teach, guide, nourish and protect as children. Make me, despising all earthly consolations for your love, aspire continually to heavenly goods, until by the Holy Spirit, your most faithful spouse, and through you his most faithful spouse, Jesus Christ your Son is formed in me for the glory of the heavenly Father. Amen.",
  "Salve, Maria, Filha de Deus Pai, Mãe de Deus Filho, Esposa do Espírito Santo, Templo da Santíssima Trindade. Salve, Maria, minha Senhora, meu tesouro, minha beleza, Rainha do meu coração, Mãe, vida, doçura e esperança minha queridíssima, meu coração e minha alma. Sou todo teu, ó Virgem benditíssima, e tudo o que tenho é teu. More em mim a tua alma para engrandecer o Senhor, more em mim o teu espírito para se regozijar em Deus. Ó Virgem fidelíssima, coloca-te como selo sobre o meu coração, para que em ti e por ti eu permaneça fiel no Senhor. Concede-me, pela tua bondade, a graça de ser contado entre os que amas, ensinas, diriges, nutres e proteges como filhos. Faz que, desprezando por teu amor todos os consolos terrenos, eu aspire continuamente aos bens celestiais, até que, pelo Espírito Santo, teu esposo fidelíssimo, e por ti, esposa sua fidelíssima, Jesus Cristo, teu Filho, seja formado em mim para a glória do Pai celestial. Amém."), med=L("4) Oremos","4) Let us pray","4) Oremos")))
CHAPLETS.append({
 "id":"alabanzas-maria","sourceId":"IVE-12","group":"mary",
 "title":L("Coronilla de Alabanzas a María","Chaplet of Praises to Mary","Coronilha de Louvores a Maria"),
 "summary":L("Tres coronas —de excelencia, poder y bondad— con alabanzas a María y el responsorio «Regocíjate, Virgen María».",
   "Three crowns —of excellence, power and goodness— with praises to Mary and the response 'Rejoice, Virgin Mary'.",
   "Três coroas —de excelência, poder e bondade— com louvores a Maria e o responsório «Regozija-te, Virgem Maria»."),
 "instrument":L("Tres coronas de cinco alabanzas, con Padre Nuestro, Avemaría y Gloria.",
   "Three crowns of five praises, with an Our Father, Hail Mary and Glory.",
   "Três coroas de cinco louvores, com Pai-Nosso, Ave Maria e Glória."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],
 "beads":[],
 "steps":steps12,
 "notes":"Texto literal de IVE; la fuente no describe un hilo de cuentas, por lo que beads está vacío y las oraciones se cuentan como pasos. Erratas de puntuación conservadas.",
})
