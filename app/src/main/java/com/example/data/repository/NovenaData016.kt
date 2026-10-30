package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData016 {
    val novena: Novena = Novena(
        id = """novena-a-la-virgen-del-rosario""",
        title = LocalizedText("""Novena a la Virgen del Rosario""", """Our Lady of the Rosary Novena""", """Novena a Nossa Senhora do Rosário (Lepanto)"""),
        patron = LocalizedText("""Santo patrón de Malaga, Toledo, Rosario, Santa Fe, Cavite, San Nicolás de los Arroyos, Santos, Melilla, Trujillo, Cáceres, Colombia, Manizales, Puyo, Pastaza, North Carolina, Bohol, Guatemala, Surigao del Norte, Manila, Quezon City, West Virginia, Seseña, Ontígola, Olías del Rey, Montearagón, Lagartera, Huerta de Valdecarábanos, Brenes, Palma Cuata, Zacatecas Lima, Peru""", """Patron saint of Malaga, Toledo, Rosario, Santa Fe, Cavite, San Nicolás de los Arroyos, Santos, Melilla, Trujillo, Cáceres, Colombia, Manizales, Puyo, Pastaza, North Carolina, Bohol, Guatemala, Surigao del Norte, Manila, Quezon City, West Virginia, Seseña, Ontígola, Olías del Rey, Montearagón, Lagartera, Huerta de Valdecarábanos, Brenes, Palma Cuata, Zacatecas Lima, Peru""", """Novena a Nossa Senhora do Rosário (Lepanto)"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Nuestra Señora del Rosario (originalmente Nuestra Señora de la Victoria) celebra la milagrosa derrota naval de los invasores islámicos turcos en 1571 en la Batalla de Lepanto.""", """Our Lady of the Rosary (originally Our Lady of Victory) celebrates the miraculous naval defeat of the Turkish Islamic invaders in 1571 at the Battle of Lepanto.""", """Santo padroeiro de Málaga, Toledo, Rosário, Santa Fé, Cavite, San Nicolás de los Arroyos, Santos, Melilla, Trujillo, Cáceres, Colômbia, Manizales, Puyo, Pastaza, Carolina do Norte, Bohol, Guatemala, Surigao del Norte, Manila, Quezon City, Virgínia Ocidental, Seseña, Ontígola, Olías del Rey, Montearagón, Lagartera, Huerta de Valdecarábanos, Brenes, Palma Cuata, Zacatecas, Lima (Peru)"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Dios te salve. ¡Cuánto mi alma se alegra, amantísima Virgen, con los dulces recuerdos que en mí despierta esta salutación! Se llena de gozo mi corazón al decir el Ave María, para acompañar el gozo que llenó tu espíritu al escuchar de boca del Ángel, alegrándome de la elección que de ti hizo el omnipotente para darnos el Señor. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Dios te salve. ¡Cuánto mi alma se alegra, amantísima Virgen, con los dulces recuerdos que en mí despierta esta salutación! Se llena de gozo mi corazón al decir el Ave María, para acompañar el gozo que llenó tu espíritu al escuchar de boca del Ángel, alegrándome de la elección que de ti hizo el omnipotente para darnos el Señor. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 2,
                theme = LocalizedText("""Día 2""", """Day 2""", """Dia 2"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

¡María nombre santo! Dígnate, amabilísima Madre, sellar con tu nombre el memorial de las súplicas nuestras, dándonos el consuelo de que lo atienda benignamente tu Hijo Jesús, para que alcancemos aburrimiento grande a todas las vanidades del mundo, firme afición a la virtud, y ansias continuas de nuestra eterna salvación. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

¡María nombre santo! Dígnate, amabilísima Madre, sellar con tu nombre el memorial de las súplicas nuestras, dándonos el consuelo de que lo atienda benignamente tu Hijo Jesús, para que alcancemos aburrimiento grande a todas las vanidades del mundo, firme afición a la virtud, y ansias continuas de nuestra eterna salvación. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 3,
                theme = LocalizedText("""Día 3""", """Day 3""", """Dia 3"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Llena eres de gracia. ¡Dulce Madre! Dios te salve, María, sagrario riquísimo en que descansó corporalmente la plenitud de la Divinidad: a tus pies se presenta desnuda mi pobre alma, pidiendo la gracia y amor de Dios, con el que fuiste enriquecida, haciéndote llena de virtud, llena de santidad, y llena de gracia. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Llena eres de gracia. ¡Dulce Madre! Dios te salve, María, sagrario riquísimo en que descansó corporalmente la plenitud de la Divinidad: a tus pies se presenta desnuda mi pobre alma, pidiendo la gracia y amor de Dios, con el que fuiste enriquecida, haciéndote llena de virtud, llena de santidad, y llena de gracia. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 4,
                theme = LocalizedText("""Día 4""", """Day 4""", """Dia 4"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

El Señor es contigo. ¡Oh Santísima Virgen! Aquel inmenso Señor, que por su esencia se halla con todas las cosas, está en ti y contigo por modo muy superior. Madre mía venga por ti a nosotros. Pero ¿cómo ha de venir a un corazón de tan poca limpieza, aquel Señor, que para hacernos habitación suya, quiso con tal prodigio, que no se perdiese, siendo Madre tu virginidad? ¡Oh! muera en nosotros toda impureza para que habite en nuestra alma el Señor. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

El Señor es contigo. ¡Oh Santísima Virgen! Aquel inmenso Señor, que por su esencia se halla con todas las cosas, está en ti y contigo por modo muy superior. Madre mía venga por ti a nosotros. Pero ¿cómo ha de venir a un corazón de tan poca limpieza, aquel Señor, que para hacernos habitación suya, quiso con tal prodigio, que no se perdiese, siendo Madre tu virginidad? ¡Oh! muera en nosotros toda impureza para que habite en nuestra alma el Señor. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 5,
                theme = LocalizedText("""Día 5""", """Day 5""", """Dia 5"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Bendita tú eres entre todas las mujeres. Tú eres la gloria de Jerusalén: tú la alegría de Israel, tú el honor del pueblo santo de Dios. Obtenga por tu intercesión nuestro espíritu la más viva fe, para considerar y adorar con tu santo Rosario las misericordias que en ti y por ti hizo el Hijo de Dios. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Bendita tú eres entre todas las mujeres. Tú eres la gloria de Jerusalén: tú la alegría de Israel, tú el honor del pueblo santo de Dios. Obtenga por tu intercesión nuestro espíritu la más viva fe, para considerar y adorar con tu santo Rosario las misericordias que en ti y por ti hizo el Hijo de Dios. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 6,
                theme = LocalizedText("""Día 6""", """Day 6""", """Dia 6"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Bendito es el fruto de tu vientre Jesús. Lloro, oh Madre mía, que haya yo hecho tantos pecados, sabiendo que ellos hicieron morir en cruz a tu Hijo. Sea el fruto de mi oración, que no termine nunca de llorarlos, hasta poder bendecir eternamente aquel purísimo fruto de tu vientre. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Bendito es el fruto de tu vientre Jesús. Lloro, oh Madre mía, que haya yo hecho tantos pecados, sabiendo que ellos hicieron morir en cruz a tu Hijo. Sea el fruto de mi oración, que no termine nunca de llorarlos, hasta poder bendecir eternamente aquel purísimo fruto de tu vientre. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 7,
                theme = LocalizedText("""Día 7""", """Day 7""", """Dia 7"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Santa María, Madre de Dios. No permitas se pierda mi alma comprada con el inestimable precio de la sangre de Jesús. Dame un corazón digno de ti, para que amando el recogimiento, sean mis delicias obsequiártelo con el santo Rosario, adorando con él a tu Hijo, por lo mucho que hizo para nuestra redención, y por lo que te ensalzó, haciéndote Madre suya. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Santa María, Madre de Dios. No permitas se pierda mi alma comprada con el inestimable precio de la sangre de Jesús. Dame un corazón digno de ti, para que amando el recogimiento, sean mis delicias obsequiártelo con el santo Rosario, adorando con él a tu Hijo, por lo mucho que hizo para nuestra redención, y por lo que te ensalzó, haciéndote Madre suya. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 8,
                theme = LocalizedText("""Día 8""", """Day 8""", """Dia 8"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Ruega por nosotros pecadores. ¡Madre de piedad! A ti solo dijo aquel Rey soberano de la gloria, Tú eres mi Madre. Alcánzame humildad y plena confianza, dispuesto de este modo, con el auxilio de Dios, a recibir los favores de la Divina misericordia, por los méritos de tu Hijo y Redentor nuestro. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Ruega por nosotros pecadores. ¡Madre de piedad! A ti solo dijo aquel Rey soberano de la gloria, Tú eres mi Madre. Alcánzame humildad y plena confianza, dispuesto de este modo, con el auxilio de Dios, a recibir los favores de la Divina misericordia, por los méritos de tu Hijo y Redentor nuestro. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 9,
                theme = LocalizedText("""Día 9""", """Day 9""", """Dia 9"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Ahora, y en la hora de nuestra muerte, estamos siempre expuestos a perder la gracia de Dios. Haz que no se aparte de mi memoria al último momento de la vida, que habrá de ser decisivo de mi eterna suerte. ¡Oh Madre de piedad! concédeme el consuelo de morir bajo tu protección y en el amor de mi Jesús. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acto de contrición

¡Señor mío, Jesucristo!

Dios y Hombre verdadero,

Creador, Padre y Redentor mío; por ser Vos quien sois, Bondad infinita,

y porque os amo sobre todas las cosas,

me pesa de todo corazón de haberos ofendido;

también me pesa porque podéis castigarme con las penas del infierno.

Ayudado de vuestra divina gracia

propongo firmemente nunca más pecar, confesarme y cumplir la penitencia que me fuere impuesta.

Amén.

Oración inicial

¡Oh Madre y clementísima Virgen del Rosario! Tú que plantaste en la Iglesia, por medio de tu privilegiado hijo Domingo, el místico árbol del Santo Rosario, haz que abracemos todos tu santa devoción y gocemos su verdadero espíritu; de suerte que aquellas místicas rosas sean en nuestros labios y corazón, por los pecadores medicina y por los justos aumento de gracia.

(menciona tu petición aquí…)

Ahora, y en la hora de nuestra muerte, estamos siempre expuestos a perder la gracia de Dios. Haz que no se aparte de mi memoria al último momento de la vida, que habrá de ser decisivo de mi eterna suerte. ¡Oh Madre de piedad! concédeme el consuelo de morir bajo tu protección y en el amor de mi Jesús. Amén.

Súplica a la Virgen

Madre, una gracia te pido, que me sanes en cuerpo y alma. Sé que debo despojarme de mi orgullo y de todos mis pecados, que lejos estaba de ti, que un negro velo cubría mi alma. Hoy te descubro y quiero vivir. Detén tu mano y pósala en mi corazón. Amén.

Se dicen las intenciones de la novena y se rezan tres padrenuestros, avemarías y glorias.

Consagración a la Virgen

¡Oh Madre, quiero consagrarme a ti!

Virgen María, hoy consagro mi vida a ti.

Siento necesidad constante de tu presencia en mi vida

para que me protejas, me guíes y me consueles.

Sé que en ti mi alma encontrará reposo

y la angustia en mí no entrará.

Mi derrota se convertirá en victoria,

mi fatiga en ti fortaleza es. Amén.

Oración final

Se sugiere también rezar el Santo Rosario o al menos una decena, correspondiente al día.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

My dearest Mother Mary, behold me, your child, in prayer at your feet.

Accept this Holy Rosary, which I offer you in accordance with your requests at Fatima, as a proof of my tender love for you, for the intentions of the Sacred Heart of Jesus, in atonement for the offenses committed against your Immaculate Heart, and for this special favor which I earnestly request in this novena (mention request here…)

I beg you to present my petition to your Divine Son.

If you will pray for me, I know that He will not refuse to answer.

I know, dearest Mother, that you want me to seek God’s holy Will concerning my request.

If what I ask for should not be granted, pray that I may receive that which will be of greater benefit to my soul.

I offer you this spiritual Bouquet of Roses because I love you.

I put all my confidence in you, since your prayers before God are most powerful.

For the greater glory of God and for the sake of Jesus, your loving Son, hear and grant my prayer.

Sweet Heart of Mary, be my salvation.

(Now pray the Rosary.)

The Holy Rosary

Which set of mysteries to pray today depends on the day of the week:

Monday, Saturday: Joyful mysteries (Annunciation, Visitation, Nativity, Presentation, Finding of the Child Jesus in the Temple)

Tuesday, Friday: Sorrowful mysteries (Agony in the garden, Scourging, Crowning with thorns, the carrying of the Cross, the Crucifixion)

Wednesday, Sunday: Glorious mysteries (Resurrection, Ascension, Descent of the Holy Spirit, Assumption, Coronation of our Lady)

Thursday: Luminous mysteries (Baptism in the Jordan, Wedding at Cana, Proclamation of the Kingdom/Call to conversion, Transfiguration, Institution of the Eucharist)

The Rosary begins with:

The Apostles’ Creed

The Our Father

Three Hail Marys

The Glory Be

The Apostles’ Creed:

I believe in God, the Father Almighty, Creator of heaven and earth; and in Jesus Christ, His only Son, our Lord: Who was conceived by the Holy Spirit, born of the Virgin Mary; suffered under Pontius Pilate, was crucified, died and was buried.

He descended into hell; the third day He rose again from the dead; He ascended into heaven, is seated at the right hand of God the Father Almighty; from thence He shall come to judge the living and the dead.

I believe in the Holy Spirit, the Holy Catholic Church, the communion of Saints, the forgiveness of sins, the resurrection of the body, and life everlasting. Amen.

The Our Father:

Our Father, Who art in Heaven,

Hallowed be Thy Name.

Thy Kingdom come,

They will be done, on earth as it is in Heaven.

Give us this day our daily bread,

and forgive us our trespasses,

as we forgive those who trespass against us.

And lead us not into temptation,

but deliver us from evil,

Amen.

The Hail Mary:

Hail Mary, full of grace, the Lord is with thee.

Blessed art thou among women, and blessed is the fruit of they womb, Jesus.

Holy Mary, Mother of God, pray for us sinners now, and at the hour of our death, amen.

The Glory Be:

Glory be to the Father, and to the Son, and to the Holy Spirit,

as it was in the beginning, is now, and ever shall be, world without end, amen.

Then begin the first decade of the set of mysteries you are praying today.

Our Father

Ten Hail Marys

Glory Be

Fatima Prayer:

“Oh my Jesus, forgive us our sins, save us from the fires of hell, and lead all souls to Heaven, especially those in most need of They mercy.”

Repeat for decades 2 through 5.

End the Rosary with the Hail, Holy Queen:

Hail, Holy Queen, Mother of Mercy, our life, our sweetness and our hope! To thee do we cry, poor banished children of Eve. To thee do we send up our sighs, mourning and weeping in this valley of tears! Turn, then, O most gracious Advocate, thine eyes of mercy toward us, and after this, our exile, show unto us the blessed fruit of thy womb, Jesus. O clement, O loving, O sweet Virgin Mary.

V. Pray for us, O holy Mother of God.

R. That we may be made worthy of the promises of Christ.

Let us Pray. O God, whose only-begotten Son, by His life, death and resurrection, has purchased for us the rewards of eternal life, grant, we beseech Thee, that meditating on these mysteries of the most holy Rosary of the Blessed Virgin Mary, we may imitate what they contain, and obtain what they promise, through the same Christ our Lord. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Minha queridíssima Mãe Maria, eis-me aqui, vosso filho, em oração a vossos pés.

Aceitai este Santo Rosário, que vos ofereço segundo os vossos pedidos em Fátima, como prova do meu terno amor por vós, pelas intenções do Sagrado Coração de Jesus, em reparação das ofensas cometidas contra o vosso Imaculado Coração, e por esta graça especial que peço ardentemente nesta novena (mencione o pedido aqui…)

Peço-vos que apresenteis o meu pedido ao vosso Divino Filho.

Se rezardes por mim, sei que Ele não deixará de responder.

Sei, queridíssima Mãe, que quereis que eu busque a santa vontade de Deus a respeito do meu pedido.

Se o que peço não me for concedido, rogai para que eu receba o que for de maior proveito para a minha alma.

Ofereço-vos este ramalhete espiritual de rosas porque vos amo.

Ponho toda a minha confiança em vós, pois as vossas orações diante de Deus são poderosíssimas.

Para a maior glória de Deus e por amor de Jesus, vosso amoroso Filho, ouvi e atendei a minha oração.

Doce Coração de Maria, sede a minha salvação.

(Agora reze o Rosário.)

O Santo Rosário

O conjunto de mistérios a rezar depende do dia da semana:

Segunda-feira, sábado: mistérios gozosos (Anunciação, Visitação, Nascimento, Apresentação, Perda e Encontro do Menino Jesus no Templo)

Terça-feira, sexta-feira: mistérios dolorosos (Agonia no Horto, Flagelação, Coroação de espinhos, Jesus carregando a cruz, Crucifixão)

Quarta-feira, domingo: mistérios gloriosos (Ressurreição, Ascensão, Vinda do Espírito Santo, Assunção, Coroação de Nossa Senhora)

Quinta-feira: mistérios luminosos (Batismo no Jordão, Bodas de Caná, Anúncio do Reino e convite à conversão, Transfiguração, Instituição da Eucaristia)

O Rosário começa com:

O Credo dos Apóstolos

O Pai-Nosso

Três Ave-Marias

O Glória ao Pai

O Credo dos Apóstolos:

Creio em Deus Pai todo-poderoso, criador do céu e da terra; e em Jesus Cristo, seu único Filho, nosso Senhor, que foi concebido pelo poder do Espírito Santo; nasceu da Virgem Maria; padeceu sob Pôncio Pilatos, foi crucificado, morto e sepultado.

Desceu à mansão dos mortos; ressuscitou ao terceiro dia; subiu aos céus, está sentado à direita de Deus Pai todo-poderoso, donde há de vir a julgar os vivos e os mortos.

Creio no Espírito Santo, na santa Igreja Católica, na comunhão dos santos, na remissão dos pecados, na ressurreição da carne, na vida eterna. Amém.

O Pai-Nosso:

Pai nosso que estais nos céus,

santificado seja o vosso nome;

venha a nós o vosso reino;

seja feita a vossa vontade

assim na terra como no céu.

O pão nosso de cada dia nos dai hoje;

perdoai-nos as nossas ofensas

assim como nós perdoamos a quem nos tem ofendido;

e não nos deixeis cair em tentação,

mas livrai-nos do mal.

Amém.

A Ave-Maria:

Ave Maria, cheia de graça, o Senhor é convosco,

bendita sois vós entre as mulheres,

e bendito é o fruto do vosso ventre, Jesus.

Santa Maria, Mãe de Deus,

rogai por nós, pecadores,

agora e na hora da nossa morte.

Amém.

O Glória ao Pai:

Glória ao Pai e ao Filho e ao Espírito Santo.

Como era no princípio, agora e sempre.

Amém.

Depois comece a primeira dezena do conjunto de mistérios que reza hoje.

Pai-Nosso

Dez Ave-Marias

Glória ao Pai

Oração de Fátima:

“Ó meu Jesus, perdoai-nos, livrai-nos do fogo do inferno; levai as almas todas para o céu e socorrei principalmente as que mais precisarem.”

Repita para as dezenas da segunda à quinta.

Termine o Rosário com a Salve Rainha:

Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva; a vós suspiramos, gemendo e chorando neste vale de lágrimas. Eia, pois, advogada nossa, esses vossos olhos misericordiosos a nós volvei; e, depois deste desterro, mostrai-nos Jesus, bendito fruto do vosso ventre, ó clemente, ó piedosa, ó doce sempre Virgem Maria.

V. Rogai por nós, santa Mãe de Deus.

R. Para que sejamos dignos das promessas de Cristo.

Oremos. Ó Deus, cujo Filho Unigênito, por sua vida, morte e ressurreição, nos alcançou o prêmio da salvação eterna, concedei-nos, Vos pedimos, que, meditando estes mistérios do santíssimo Rosário da Bem-aventurada Virgem Maria, imitemos o que contêm e alcancemos o que prometem. Pelo mesmo Cristo, nosso Senhor. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-the-rosary-novena.png""",
        tags = listOf("blessings", "love", "comfort")
    )
}