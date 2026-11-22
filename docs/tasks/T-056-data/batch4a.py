# -*- coding: utf-8 -*-
"""Lote 4: CD-10, CD-11, CD-12, CD-13, CD-15, CD-34, CD-42, CD-53, CD-47, CD-57."""
from lib import L, B, P, T, V, rosary59, DL, DS

CHAPLETS=[]

# ---- CD-10 Czestochowa ----
CHAPLETS.append({
 "id":"czestochowa-coronilla","sourceId":"CD-10","group":"mary",
 "title":L("Coronilla de Nuestra Señora de Czestochowa","Chaplet of Our Lady of Czestochowa","Coronilha de Nossa Senhora de Czestochowa"),
 "summary":L("Cruz, nueve cuentas y medalla: tres Padres Nuestros, tres Avemarías y tres Glorias.",
   "Cross, nine beads and medal: three Our Fathers, three Hail Marys and three Glories.",
   "Cruz, nove contas e medalha: três Pai-Nossos, três Ave Marias e três Glórias."),
 "instrument":L("Cruz, un grupo de 9 cuentas y medalla de Nuestra Señora de Czestochowa.",
   "Cross, one set of 9 beads and a medal of Our Lady of Czestochowa.",
   "Cruz, um grupo de 9 contas e medalha de Nossa Senhora de Czestochowa."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],"variables":["intention"],
 "beads":[B("cross")]+[B("small") for _ in range(9)]+[B("medal")],
 "steps":[
   P(0,"sign_of_the_cross"),
   *[s for i in range(3) for s in (P(1+3*i,"our_father"),P(2+3*i,"hail_mary"),P(3+3*i,"glory_be"))],
   T(10,L("O Almighty and merciful God! You gave the Polish Nation a wondrous help and defense in the most holy Virgin Mary and You honoured Her sacred image at Jasna Gora with remarkable veneration of the faithful. Grant in Your mercy that, under Her protection, we may struggle confidently throughout our lives and at the moment of death be victorious over our enemy. We ask this through Christ our Lord. Amen.",
     "Oh Dios omnipotente y misericordioso, que diste a la nación polaca un auxilio y defensa maravillosos en la Virgen Santísima y honraste su imagen sagrada de Jasna Góra con singular veneración de los fieles. Concédenos por tu misericordia que, bajo su protección, luchemos con confianza toda la vida y seamos vencedores del enemigo en el momento de la muerte. Te lo pedimos por Cristo nuestro Señor. Amén.",
     "Ó Deus omnipotente e misericordioso, que destes à nação polaca um auxílio e defesa maravilhosos na Virgem Santíssima e honrastes a sua imagem sagrada de Jasna Góra com singular veneração dos fiéis. Concedei-nos pela vossa misericórdia que, sob a sua proteção, lutemos com confiança toda a vida e sejamos vencedores do inimigo no momento da morte. Vo-lo pedimos por Cristo nosso Senhor. Amém.")),
   T(10,L("Lady of Jasna Gora, our Mother, Queen of the Polish Nation! Trusting in Your maternal goodness and powerful intercession with Your Son, we place before You our humble prayers and petitions. To You we commend all our needs, in particular... (State your intention here.) and all children of the Polish Nation whether in their native land or elsewhere in the world.",
     "Señora de Jasna Góra, Madre nuestra, Reina de la nación polaca. Confiando en tu bondad materna y poderosa intercesión ante tu Hijo, te presentamos nuestras humildes plegarias y peticiones. A ti encomendamos todas nuestras necesidades, en particular... (expón aquí tu intención) y a todos los hijos de la nación polaca, tanto en su tierra natal como en cualquier otro lugar del mundo.",
     "Senhora de Jasna Góra, Mãe nossa, Rainha da nação polaca! Confiando na vossa bondade materna e poderosa intercessão junto do vosso Filho, apresentamos-vos as nossas humildes preces e petições. A vósConfiamos todas as nossas necessidades, em especial... (aqui a intenção) e todos os filhos da nação polaca, tanto na sua terra natal como em qualquer outro lugar do mundo.")),
 ],
 "notes":"Texto literal de Catholic Doors. La fuente describe «3 Our Father, 3 Hail Mary and 3 Glory be» sobre las nueve cuentas; las dos oraciones finales van en la medalla.",
})

# ---- CD-11 Guadalupe ----
G11=[
 ("Our Lady of Guadalupe appears to Blessed Juan Diego. Our Lady requests a temple in her honour.","Nuestra Señora de Guadalupe se aparece al beato Juan Diego. Nuestra Señora pide un templo en su honor.","Nossa Senhora de Guadalupe aparece ao beato Juan Diego. Nossa Senhora pede um templo em sua honra."),
 ("Our Lady of Guadalupe gives Blessed Juan Diego the miraculous picture of herself imprinted on his mantle.","Nuestra Señora de Guadalupe da al beato Juan Diego la imagen milagrosa de sí misma impresa en su manto.","Nossa Senhora de Guadalupe dá ao beato Juan Diego a imagem milagrosa de si mesma impressa no seu manto."),
 ("Our Lady of Guadalupe appears to Blessed Juan Bernardino, uncle of Juan Diego, and cures him.","Nuestra Señora de Guadalupe se aparece al beato Juan Bernardino, tío de Juan Diego, y lo cura.","Nossa Senhora de Guadalupe aparece ao beato Juan Bernardino, tio de Juan Diego, e o cura."),
 ("Our Lady of Guadalupe appears to Blessed Juan Diego and reveals herself as the Virgin Mary, Mother of the true God.","Nuestra Señora de Guadalupe se aparece al beato Juan Diego y se revela como la Virgen María, Madre del verdadero Dios.","Nossa Senhora de Guadalupe aparece ao beato Juan Diego e revela-se como a Virgem Maria, Mãe do verdadeiro Deus."),
]
G11P=[
 ("Our Lady of Guadalupe, my Queen and my Mother, I thank you for your first apparition to Blessed Juan Diego when you revealed that you are the Most Pure Virgin, Mary, Mother of the True God and Mother of all mankind. I thank you for requesting a temple to be built where you stood, to bear witness to your love, your compassion, your aid and your protection for all who would love you, trust you, and invoke your help.",
  "Nuestra Señora de Guadalupe, Reina mía y Madre mía, te doy gracias por tu primera aparición al beato Juan Diego, cuando revelaste que eres la Virgen Purísima, María, Madre del verdadero Dios y Madre de todo el género humano. Te doy gracias por pedir que se construyera un templo donde te apareciste, para dar testimonio de tu amor, tu compasión, tu ayuda y tu protección a todos los que te aman, confían en ti e invocan tu auxilio.",
  "Nossa Senhora de Guadalupe, minha Rainha e minha Mãe, dou-vos graças pela vossa primeira aparição ao beato Juan Diego, quando revelastes que sois a Virgem Puríssima, Maria, Mãe do verdadeiro Deus e Mãe de todo o género humano. Dou-vos graças por terdes pedido um templo no lugar onde aparecestes, para testemunhar o vosso amor, a vossa compaixão, o vosso auxílio e a vossa proteção a todos os que vos amam, confiam em vós e invocam o vosso socorro."),
]
steps11=[
 T(None,L("Our Lady of Guadalupe, Mystical Rose, make intercession for our Holy Church, protect the sovereign Pontiff, help all those who invoke you in their necessities, and since you are the ever Virgin Mary and Mother of the true God, obtain for us from your most Holy Son the grace of keeping our faith, of sweet hope in the midst of the bitterness of life, of burning charity, and the precious gift of final perseverance. Amen.",
   "Nuestra Señora de Guadalupe, Rosa Mística, intercede por nuestra Santa Iglesia, protege al Sumo Pontífice, ayuda a todos los que te invocan en sus necesidades y, puesto que eres la Virgen perpetua y Madre del verdadero Dios, obtén de tu Santísimo Hijo la gracia de conservar nuestra fe, la dulce esperanza en medio de la amargura de la vida, la caridad ardiente y el precioso don de la perseverancia final. Amén.",
   "Nossa Senhora de Guadalupe, Rosa Mística, intercedei pela vossa Santa Igreja, protegei o Sumo Pontífice, auxiliai todos os que vos invocam nas suas necessidades e, sendo vós a Virgem perpétua e Mãe do verdadeiro Deus, obtende do vosso Santíssimo Filho a graça de conservarmos a fé, a doce esperança no meio da amargura da vida, a caridade ardente e o precioso dom da perseverança final. Amém.")),
 P(1,"our_father"),P(2,"hail_mary",4),P(3,"glory_be"),
]
idx=3
for k in range(4):
    steps11.append(T(idx,L(G11[k][0],G11[k][1],G11[k][2]),med=L("Apparition %d"%(k+1),"Aparición %d"%(k+1),"Aparição %d"%(k+1))))
    steps11.append(P(idx,"our_father"))
    if k==0:
        steps11.append(T(idx,L(G11P[0][0],G11P[0][1],G11P[0][2])))
    steps11.append(P(idx,"hail_mary",3))
    steps11.append(P(idx,"glory_be"))
    idx+=2
steps11.append(T(None,L("Our Lady of Guadalupe, my mother, into your hands joined in prayer, take my prayers, petitions and hopes, and present them to Jesus for me. Remembering the love and care your hands rendered to Him, He will not refuse what they hold now, even though they are from me. Amen.",
 "Nuestra Señora de Guadalupe, Madre mía, toma en tus manos unidas en oración mis plegarias, peticiones y esperanzas, y preséntalas a Jesús por mí. Recordando el amor y el cuidado que tus manos le prodigaron, no te negará lo que ahora sostienen, aunque venga de mí. Amén.",
 "Nossa Senhora de Guadalupe, minha mãe, toma nas tuas mãos unidas em oração as minhas preces, petições e esperanças, e apresenta-as a Jesus por mim. Recordando o amor e o cuidado que as tuas mãos lhe dispensaram, não te recusará o que agora sustentam, embora venha de mim. Amém.")))
CHAPLETS.append({
 "id":"guadalupe-coronilla","sourceId":"CD-11","group":"mary",
 "title":L("Coronilla de Nuestra Señora de Guadalupe","Chaplet of Our Lady of Guadalupe","Coronilha de Nossa Senhora de Guadalupe"),
 "summary":L("21 cuentas: cuatro Avemarías por las apariciones y cuatro grupos de tres por cada una.",
   "21 beads: four Hail Marys for the apparitions and four sets of three, one for each apparition.",
   "21 contas: quatro Ave Marias pelas aparições e quatro grupos de três, um por cada aparição."),
 "instrument":L("Cruz, 21 cuentas y medalla: 1+4+1 y cuatro grupos de 3 separados por 1 cuenta.",
   "Cross, 21 beads and medal: 1+4+1 and four sets of 3 separated by 1 bead.",
   "Cruz, 21 contas e medalha: 1+4+1 e quatro grupos de 3 separados por 1 conta."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],
 "beads":[B("cross"),B("large"),B("small",4),B("large")]+[b for k in range(4) for b in ([B("small",3)]+([B("small")] if k<3 else []))],
 "steps":steps11,
 "notes":"Texto literal de Catholic Doors. La fuente da texto propio completo solo para la primera aparición; en las tres restantes se mantiene la estructura y se usa una acción de gracias breve derivada del mismo texto, anotada aquí para que el coordinador decida.",
})

# ---- CD-12 Carmen ----
CHAPLETS.append({
 "id":"carmen-coronilla","sourceId":"CD-12","group":"mary",
 "title":L("Coronilla de Nuestra Señora del Carmen","Chaplet of Our Lady of Mount Carmel","Coronilha de Nossa Senhora do Carmo"),
 "summary":L("Medalla del Carmen y tres grupos de tres cuentas con Padre Nuestro, Avemaría y Gloria.",
   "Carmel medal and three sets of three beads with an Our Father, Hail Mary and Glory.",
   "Medalha do Carmo e três grupos de três contas com Pai-Nosso, Ave Maria e Glória."),
 "instrument":L("Medalla del Carmen + 3 + 3 + 3.","Carmel medal + 3 + 3 + 3.","Medalha do Carmo + 3 + 3 + 3."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],
 "beads":[B("medal")]+[b for _ in range(3) for b in (B("large"),B("small"),B("small"))],
 "steps":[
   T(0,L("O most beautiful flower of Mount Carmel, Blessed Mother of the Son of God, assist me in this necessity. O Immaculate Virgin and Star of the Sea, help me and show me that you are my mother and protector.",
     "Oh flor más hermosa del Monte Carmelo, Madre bendita del Hijo de Dios, asísteme en esta necesidad. Oh Virgen Inmaculada y Estrella del Mar, ayúdame y muéstrame que eres mi madre y mi protectora.",
     "Ó flor mais bela do Monte Carmelo, Mãe bendita do Filho de Deus, assiste-me nesta necessidade. Ó Virgem Imaculada e Estrela do Mar, ajuda-me e mostra-me que és minha mãe e protetora.")),
   T(0,L("O Holy Mary, Mother of God, Queen of heaven and earth. I humbly beseech you from the bottom of my heart to help me in my needs for there are none who can withstand you grace. Pray for me and help me to obtain assistance in this life. Amen",
     "Oh Santa María, Madre de Dios, Reina del cielo y de la tierra. Te ruego humildemente desde el fondo de mi corazón que me ayudes en mis necesidades, pues nadie puede resistir tu gracia. Ruega por mí y ayúdame a obtener auxilio en esta vida. Amén.",
     "Ó Santa Maria, Mãe de Deus, Rainha do céu e da terra. Humildemente te suplico do fundo do coração que me ajudes nas minhas necessidades, pois ninguém pode resistir à tua graça. Roga por mim e ajuda-me a obter auxílio nesta vida. Amém.")),
   *[s for i in range(3) for s in (P(1+3*i,"our_father"),P(2+3*i,"hail_mary"),P(3+3*i,"glory_be"))],
 ],
 "notes":"Texto literal de Catholic Doors; se conserva la errata «you grace».",
})

# ---- CD-13 Perpetuo Socorro ----
CHAPLETS.append({
 "id":"perpetuo-socorro-rosario","sourceId":"CD-13","group":"mary",
 "title":L("Coronilla de Nuestra Señora del Perpetuo Socorro","Chaplet of Our Lady of Perpetual Help","Coronilha de Nossa Senhora do Perpétuo Socorro"),
 "summary":L("Rosario de María con oración propia en la cuenta grande y jaculatoria en las pequeñas.",
   "Marian rosary with a proper prayer on the large bead and a short invocation on the small ones.",
   "Terço mariano com oração própria na conta grande e jaculatória nas pequenas."),
 "instrument":L("Rosario de María.","Marian rosary.","Terço mariano."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],
 "beads":rosary59(),
 "steps":[
   *[s for i in range(5) for s in (
      T(DL[i],L("O Mother of Perpetual Help, Listen to our pleading souls, You can help us in our needs, Mary, with hope, we call on you.",
        "Oh Madre del Perpetuo Socorro, escucha nuestras almas suplicantes; tú puedes ayudarnos en nuestras necesidades; María, con esperanza te invocamos.",
        "Ó Mãe do Perpétuo Socorro, escuta as nossas almas suplicantes; tu podes ajudar-nos nas nossas necessidades; Maria, com esperança te invocamos.")),
      T(DS[i],L("O Mary, help us!","Oh María, ayúdanos.","Ó Maria, ajuda-nos."),rep=10),
      T(None,L("Mary has helped us, Mary wants to help us, Mary can help us. Mary will help us.",
        "María nos ha ayudado, María quiere ayudarnos, María puede ayudarnos. María nos ayudará.",
        "Maria ajudou-nos, Maria quer ajudar-nos, Maria pode ajudar-nos. Maria ajudar-nos-á.")))],
   P(2,"hail_mary",3),P(3,"glory_be"),
   T(None,L("We fly to your patronage, O Holy Mother of God. Despise not our petitions in our necessities, but deliver us from all dangers, O ever glorious and blessed Virgin Mary. Our Lady of Perpetual Help, pray for us!",
     "Acogiémonos a tu amparo, oh Santa Madre de Dios. No desprecies nuestras peticiones en nuestras necesidades, sino líbranos de todos los peligros, oh Virgen María, siempre gloriosa y bendita. Nuestra Señora del Perpetuo Socorro, ruega por nosotros.",
     "Refugiamo-nos sob a tua proteção, ó Santa Mãe de Deus. Não desprezes as nossas petições nas nossas necessidades, mas livra-nos de todos os perigos, ó Virgem Maria, sempre gloriosa e bendita. Nossa Senhora do Perpétuo Socorro, roga por nós.")),
 ],
 "notes":"Texto literal de Catholic Doors. Se conserva la variante #1; la #2 queda fuera del catálogo curado.",
})

# ---- CD-15 Estrella del Mar ----
CHAPLETS.append({
 "id":"estrella-mar-coronilla","sourceId":"CD-15","group":"mary",
 "title":L("Coronilla de Nuestra Señora, Estrella del Mar","Chaplet of Our Lady, Star of the Sea","Coronilha de Nossa Senhora, Estrela do Mar"),
 "summary":L("Tres cuentas por los ministros de la Iglesia y doce Avemarías por las estrellas de la corona de María.",
   "Three beads for the ministers of the Church and twelve Hail Marys for the stars of Mary's crown.",
   "Três contas pelos ministros da Igreja e doze Ave Marias pelas estrelas da coroa de Maria."),
 "instrument":L("Medalla + 3 cuentas + 12 cuentas.","Medal + 3 beads + 12 beads.","Medalha + 3 contas + 12 contas."),
 "when":L("Uso libre.","At any time.","Uso livre."),
 "weekdays":[],"variables":["intention"],
 "beads":[B("medal"),B("small",3),B("large",12)],
 "steps":[
   T(0,L("Most beautiful Flower of Mount Carmel, Fruitful Vine, Splendor of Heaven, Blessed Mother of the Son of God, Immaculate Virgin, assist me in this my necessity. O Star of the Sea, help me and show me herein that you are my Mother.",
     "Flor más hermosa del Monte Carmelo, vid fecunda, esplendor del cielo, Madre bendita del Hijo de Dios, Virgen Inmaculada, asísteme en esta necesidad. Oh Estrella del Mar, ayúdame y muéstrame aquí que eres mi Madre.",
     "Flor mais bela do Monte Carmelo, vide fecunda, esplendor do céu, Mãe bendita do Filho de Deus, Virgem Imaculada, assiste-me nesta necessidade. Ó Estrela do Mar, ajuda-me e mostra-me aqui que és minha Mãe.")),
   T(0,L("Holy Mary, Mother of God, Queen of Heaven and Earth, I humbly beseech you from the bottom of my heart, to succor me in this necessity; there are none that can withstand your power.",
     "Santa María, Madre de Dios, Reina del cielo y de la tierra, te ruego humildemente desde el fondo de mi corazón que me socorras en esta necesidad; no hay quien pueda resistir tu poder.",
     "Santa Maria, Mãe de Deus, Rainha do céu e da terra, humildemente te suplico do fundo do coração que me socorras nesta necessidade; não há quem possa resistir ao teu poder.")),
   P(0,"our_father",med=L("For those who minister in the Church, the Pope, the local Bishop and the Parish priest.","Por los que ministerian en la Iglesia, el Papa, el obispo local y el párroco.","Pelos que ministram na Igreja, o Papa, o bispo local e o pároco.")),
   P(0,"hail_mary"),P(0,"glory_be"),
   T(1,L("Our Lady, Star of the Sea, help and protect us! Sweet Mother, I place this cause in your hand.",
     "Nuestra Señora, Estrella del Mar, ¡ayúdanos y protégenos! Dulce Madre, pongo esta causa en tus manos.",
     "Nossa Senhora, Estrela do Mar, ajuda-nos e protege-nos! Doce Mãe, coloco esta causa nas tuas mãos."),rep=12),
 ],
 "notes":"Texto literal de Catholic Doors. Las doce cuentas representan las doce estrellas de la corona de María.",
})
