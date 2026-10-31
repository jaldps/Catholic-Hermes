package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData002 {
    val novena: Novena = Novena(
        id = """novena-a-la-divina-misericordia""",
        title = LocalizedText("""Novena a la Divina Misericordia""", """Divine Mercy Novena""", """Novena à Divina Misericórdia"""),
        patron = LocalizedText("""Santo patrón de Misericordia (Santa Faustina)""", """Patron saint of Mercy (St. Faustina)""", """Novena à Divina Misericórdia"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Jesús le dio a Santa Faustina la Novena de 9 días a la Divina Misericordia para prepararla para la Fiesta de la Divina Misericordia, donde cada día se reza por un grupo diferente de almas.""", """Trust in Jesus’ infinite mercy today. Pray the Divine Mercy Novena Jesus gave to St. Faustina and experience the ocean of grace poured out for all souls.""", """Santo padroeiro de a Misericórdia (Santa Faustina)"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a toda la humanidad y especialmente a todos los pecadores, y sumérgelos en el mar de Mi Misericordia. De esta forma, Me consolarás de la amarga tristeza en que Me sume la pérdida de las almas. Jesús tan Misericordioso, cuya naturaleza es la de tener compasión de nosotros y de perdonarnos, no mires nuestros pecados, sino la confianza que depositamos en Tu bondad infinita. Acógenos en la morada de Tu muy compasivo Corazón y nunca nos dejes salir de Él. Te lo suplicamos por Tu Amor que Te une al Padre y al Espíritu Santo. Padre Eterno, mira con Misericordia a toda la humanidad, y especialmente a nosotros pobres pecadores que estamos encerrados en el Corazón de Jesús lleno de compasión, y por su dolorosa Pasión muéstranos Tu Misericordia para que alabemos Tu Omnipotencia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me all mankind, especially all sinners, and immerse them in the ocean of My mercy. In this way you will console Me in the bitter grief into which the loss of souls plunges Me.”

Most Merciful Jesus, whose very nature it is to have compassion on us and to forgive us, do not look upon our sins but upon our trust which we place in Your infinite goodness. Receive us all into the abode of Your Most Compassionate Heart, and never let us escape from It. We beg this of You by Your love which unites You to the Father and the Holy Spirit.

Eternal Father, turn Your merciful gaze upon all mankind and especially upon poor sinners, all enfolded in the Most Compassionate Heart of Jesus. For the sake of His sorrowful Passion show us Your mercy, that we may praise the omnipotence of Your mercy for ever and ever. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me toda a humanidade, especialmente todos os pecadores, e mergulha-os no oceano da Minha misericórdia. Desse modo Me consolarás da amarga tristeza em que Me mergulha a perda das almas.”

Ó Jesus misericordiosíssimo, cuja própria natureza é ter compaixão de nós e perdoar-nos, não olheis os nossos pecados, mas a confiança que depositamos na Vossa infinita bondade. Recebei-nos todos na morada do Vosso Compassivíssimo Coração e não deixeis que dele jamais escapemos. Nós Vo-lo pedimos pelo amor que Vos une ao Pai e ao Espírito Santo.

Eterno Pai, olhai com misericórdia para toda a humanidade, especialmente para os pobres pecadores, todos envolvidos no Compassivíssimo Coração de Jesus. Pela Sua dolorosa Paixão, mostrai-nos a Vossa misericórdia, para que louvemos a onipotência da Vossa misericórdia pelos séculos dos séculos. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a toda la humanidad y especialmente a todos los pecadores, y sumérgelos en el mar de Mi Misericordia. De esta forma, Me consolarás de la amarga tristeza en que Me sume la pérdida de las almas. Jesús tan Misericordioso, cuya naturaleza es la de tener compasión de nosotros y de perdonarnos, no mires nuestros pecados, sino la confianza que depositamos en Tu bondad infinita. Acógenos en la morada de Tu muy compasivo Corazón y nunca nos dejes salir de Él. Te lo suplicamos por Tu Amor que Te une al Padre y al Espíritu Santo. Padre Eterno, mira con Misericordia a toda la humanidad, y especialmente a nosotros pobres pecadores que estamos encerrados en el Corazón de Jesús lleno de compasión, y por su dolorosa Pasión muéstranos Tu Misericordia para que alabemos Tu Omnipotencia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me all mankind, especially all sinners, and immerse them in the ocean of My mercy. In this way you will console Me in the bitter grief into which the loss of souls plunges Me.”

Most Merciful Jesus, whose very nature it is to have compassion on us and to forgive us, do not look upon our sins but upon our trust which we place in Your infinite goodness. Receive us all into the abode of Your Most Compassionate Heart, and never let us escape from It. We beg this of You by Your love which unites You to the Father and the Holy Spirit.

Eternal Father, turn Your merciful gaze upon all mankind and especially upon poor sinners, all enfolded in the Most Compassionate Heart of Jesus. For the sake of His sorrowful Passion show us Your mercy, that we may praise the omnipotence of Your mercy for ever and ever. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me toda a humanidade, especialmente todos os pecadores, e mergulha-os no oceano da Minha misericórdia. Desse modo Me consolarás da amarga tristeza em que Me mergulha a perda das almas.”

Ó Jesus misericordiosíssimo, cuja própria natureza é ter compaixão de nós e perdoar-nos, não olheis os nossos pecados, mas a confiança que depositamos na Vossa infinita bondade. Recebei-nos todos na morada do Vosso Compassivíssimo Coração e não deixeis que dele jamais escapemos. Nós Vo-lo pedimos pelo amor que Vos une ao Pai e ao Espírito Santo.

Eterno Pai, olhai com misericórdia para toda a humanidade, especialmente para os pobres pecadores, todos envolvidos no Compassivíssimo Coração de Jesus. Pela Sua dolorosa Paixão, mostrai-nos a Vossa misericórdia, para que louvemos a onipotência da Vossa misericórdia pelos séculos dos séculos. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy tráeme a las almas de los sacerdotes y las almas de los religiosos, y sumérgelas en Mi misericordia insondable. Fueron ellas las que Me dieron fortaleza para soportar Mi amarga Pasión. A través de ellas, como a través de canales, Mi Misericordia fluye hacia la humanidad. Jesús Misericordiosísimo, de quien procede todo bien, aumenta Tu gracia en nosotros para que realicemos dignas obras de misericordia, de manera que todos aquellos que nos vean, glorifiquen al Padre de Misericordia que está en el Cielo. Padre eterno, mira con misericordia al grupo elegido de tu viña, a las almas de los sacerdotes y a las almas de los religiosos; otórgales el poder de tu bendición. Por el amor del Corazón de tu Hijo, en el cual están encerradas, concédeles el poder de tu luz para que puedan guiar a otros en el camino de la salvación y a una sola voz canten alabanzas a tu misericordia sin límite por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls of Priests and Religious, and immerse them in My unfathomable mercy. It was they who gave me strength to endure My bitter Passion. Through them as through channels My mercy flows out upon mankind.”

Most Merciful Jesus, from whom comes all that is good, increase Your grace in men and women consecrated to Your service, that they may perform worthy works of mercy; and that all who see them may glorify the Father of Mercy who is in heaven.

Eternal Father, turn Your merciful gaze upon the company of chosen ones in Your vineyard, upon the souls of priests and religious; and endow them with the strength of Your blessing. For the love of the Heart of Your Son in which they are enfolded, impart to them Your power and light, that they may be able to guide others in the way of salvation and with one voice sing praise to Your boundless mercy for ages without end. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas dos sacerdotes e dos religiosos e mergulha-as na Minha insondável misericórdia. Foram elas que Me deram força para suportar a Minha amarga Paixão. Por meio delas, como por canais, a Minha misericórdia se derrama sobre a humanidade.”

Ó Jesus misericordiosíssimo, de quem procede todo o bem, aumentai a Vossa graça nos homens e mulheres consagrados ao Vosso serviço, para que realizem dignas obras de misericórdia e para que todos os que os virem glorifiquem o Pai de Misericórdia que está nos céus.

Eterno Pai, olhai com misericórdia para o grupo dos eleitos da Vossa vinha, para as almas dos sacerdotes e dos religiosos, e dotai-as da força da Vossa bênção. Pelo amor do Coração do Vosso Filho, no qual estão envolvidas, concedei-lhes o Vosso poder e a Vossa luz, para que possam guiar os outros no caminho da salvação e, a uma só voz, cantar louvores à Vossa misericórdia sem limites pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy tráeme a las almas de los sacerdotes y las almas de los religiosos, y sumérgelas en Mi misericordia insondable. Fueron ellas las que Me dieron fortaleza para soportar Mi amarga Pasión. A través de ellas, como a través de canales, Mi Misericordia fluye hacia la humanidad. Jesús Misericordiosísimo, de quien procede todo bien, aumenta Tu gracia en nosotros para que realicemos dignas obras de misericordia, de manera que todos aquellos que nos vean, glorifiquen al Padre de Misericordia que está en el Cielo. Padre eterno, mira con misericordia al grupo elegido de tu viña, a las almas de los sacerdotes y a las almas de los religiosos; otórgales el poder de tu bendición. Por el amor del Corazón de tu Hijo, en el cual están encerradas, concédeles el poder de tu luz para que puedan guiar a otros en el camino de la salvación y a una sola voz canten alabanzas a tu misericordia sin límite por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls of Priests and Religious, and immerse them in My unfathomable mercy. It was they who gave me strength to endure My bitter Passion. Through them as through channels My mercy flows out upon mankind.”

Most Merciful Jesus, from whom comes all that is good, increase Your grace in men and women consecrated to Your service, that they may perform worthy works of mercy; and that all who see them may glorify the Father of Mercy who is in heaven.

Eternal Father, turn Your merciful gaze upon the company of chosen ones in Your vineyard, upon the souls of priests and religious; and endow them with the strength of Your blessing. For the love of the Heart of Your Son in which they are enfolded, impart to them Your power and light, that they may be able to guide others in the way of salvation and with one voice sing praise to Your boundless mercy for ages without end. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas dos sacerdotes e dos religiosos e mergulha-as na Minha insondável misericórdia. Foram elas que Me deram força para suportar a Minha amarga Paixão. Por meio delas, como por canais, a Minha misericórdia se derrama sobre a humanidade.”

Ó Jesus misericordiosíssimo, de quem procede todo o bem, aumentai a Vossa graça nos homens e mulheres consagrados ao Vosso serviço, para que realizem dignas obras de misericórdia e para que todos os que os virem glorifiquem o Pai de Misericórdia que está nos céus.

Eterno Pai, olhai com misericórdia para o grupo dos eleitos da Vossa vinha, para as almas dos sacerdotes e dos religiosos, e dotai-as da força da Vossa bênção. Pelo amor do Coração do Vosso Filho, no qual estão envolvidas, concedei-lhes o Vosso poder e a Vossa luz, para que possam guiar os outros no caminho da salvação e, a uma só voz, cantar louvores à Vossa misericórdia sem limites pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a todas las almas devotas y fieles, y sumérgelas en el mar de Mi misericordia. Estas almas me consolaron a lo largo del Via Crucis. Fueron una gota de consuelo en medio de un mar de amargura. Jesús infinitamente compasivo, que desde el tesoro de Tu misericordia les concedes a todos Tus gracias en gran abundancia, acógenos en la morada de Tu clementísimo Corazón y nunca nos dejes escapar de El. Te lo suplicamos por el inconcebible amor Tuyo con que Tu corazón arde por el Padre Celestial. Padre eterno, mira con misericordia a las almas fieles como herencia de Tu Hijo y por su dolorosa Pasión, concédeles Tu bendición y rodéalas con Tu protección constante para que no pierdan el amor y el tesoro de la santa fe, sino que con toda la legión de los angeles y los santos, glorifiquen Tu infinita misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me all Devout and Faithful Souls, and immerse them in the ocean of My mercy. These souls brought me consolation on the Way of the Cross. They were a drop of consolation in the midst of an ocean of bitterness.”

Most Merciful Jesus, from the treasury of Your mercy, You impart Your graces in great abundance to each and all. Receive us into the abode of Your Most Compassionate Heart and never let us escape from It. We beg this grace of You by that most wondrous love for the heavenly Father with which Your Heart burns so fiercely.

Eternal Father, turn Your merciful gaze upon faithful souls, as upon the inheritance of Your Son. For the sake of His sorrowful Passion, grant them Your blessing and surround them with Your constant protection. Thus may they never fail in love or lose the treasure of the holy faith, but rather, with all the hosts of Angels and Saints, may they glorify Your boundless mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me todas as almas piedosas e fiéis e mergulha-as no oceano da Minha misericórdia. Estas almas Me consolaram no Caminho da Cruz. Foram uma gota de consolação no meio de um oceano de amargura.”

Ó Jesus misericordiosíssimo, do tesouro da Vossa misericórdia concedeis as Vossas graças em grande abundância a todos e a cada um. Recebei-nos na morada do Vosso Compassivíssimo Coração e não deixeis que dele jamais escapemos. Nós Vos pedimos esta graça por aquele amor admirabilíssimo ao Pai celeste com que o Vosso Coração arde tão intensamente.

Eterno Pai, olhai com misericórdia para as almas fiéis, como para a herança do Vosso Filho. Pela Sua dolorosa Paixão, concedei-lhes a Vossa bênção e envolvei-as com a Vossa constante proteção. Assim nunca faltem no amor nem percam o tesouro da santa fé, mas, com todos os coros dos Anjos e dos Santos, glorifiquem a Vossa misericórdia sem limites pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a todas las almas devotas y fieles, y sumérgelas en el mar de Mi misericordia. Estas almas me consolaron a lo largo del Via Crucis. Fueron una gota de consuelo en medio de un mar de amargura. Jesús infinitamente compasivo, que desde el tesoro de Tu misericordia les concedes a todos Tus gracias en gran abundancia, acógenos en la morada de Tu clementísimo Corazón y nunca nos dejes escapar de El. Te lo suplicamos por el inconcebible amor Tuyo con que Tu corazón arde por el Padre Celestial. Padre eterno, mira con misericordia a las almas fieles como herencia de Tu Hijo y por su dolorosa Pasión, concédeles Tu bendición y rodéalas con Tu protección constante para que no pierdan el amor y el tesoro de la santa fe, sino que con toda la legión de los angeles y los santos, glorifiquen Tu infinita misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me all Devout and Faithful Souls, and immerse them in the ocean of My mercy. These souls brought me consolation on the Way of the Cross. They were a drop of consolation in the midst of an ocean of bitterness.”

Most Merciful Jesus, from the treasury of Your mercy, You impart Your graces in great abundance to each and all. Receive us into the abode of Your Most Compassionate Heart and never let us escape from It. We beg this grace of You by that most wondrous love for the heavenly Father with which Your Heart burns so fiercely.

Eternal Father, turn Your merciful gaze upon faithful souls, as upon the inheritance of Your Son. For the sake of His sorrowful Passion, grant them Your blessing and surround them with Your constant protection. Thus may they never fail in love or lose the treasure of the holy faith, but rather, with all the hosts of Angels and Saints, may they glorify Your boundless mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me todas as almas piedosas e fiéis e mergulha-as no oceano da Minha misericórdia. Estas almas Me consolaram no Caminho da Cruz. Foram uma gota de consolação no meio de um oceano de amargura.”

Ó Jesus misericordiosíssimo, do tesouro da Vossa misericórdia concedeis as Vossas graças em grande abundância a todos e a cada um. Recebei-nos na morada do Vosso Compassivíssimo Coração e não deixeis que dele jamais escapemos. Nós Vos pedimos esta graça por aquele amor admirabilíssimo ao Pai celeste com que o Vosso Coração arde tão intensamente.

Eterno Pai, olhai com misericórdia para as almas fiéis, como para a herança do Vosso Filho. Pela Sua dolorosa Paixão, concedei-lhes a Vossa bênção e envolvei-as com a Vossa constante proteção. Assim nunca faltem no amor nem percam o tesouro da santa fé, mas, com todos os coros dos Anjos e dos Santos, glorifiquem a Vossa misericórdia sem limites pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a los paganos (a los que no creen en Cristo) y aquellos que todavía no Me conocen. También pensaba en ellos durante Mi amarga Pasión y su futuro celo consoló Mi corazón. Sumérgelos en el mar de Mi misericordia. Jesús compasivísimo, que eres la luz del mundo entero. Acoge en la morada de Tu piadosísimo Corazón a las almas de los paganos que todavía no Te conocen. Que los rayos de Tu gracia las iluminen para que también ellas unidas a nosotros, ensalcen Tu misericordia admirable y no las dejes salir de la morada de Tu compasivísimo Corazón. Padre eterno, mira con misericordia a las almas de los paganos y de los que todavía no Te conocen, pero que están encerrados en el muy compasivo Corazón de Jesús. Atraerlas hacia la luz de evangelio. Estas almas desconocen la gran felicidad que es amarte. Concédeles que también ellas ensalcen la generosidad de Tu misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me those who do not believe in God and those who do not know Me, I was thinking also of them during My bitter Passion, and their future zeal comforted My Heart. Immerse them in the ocean of My mercy.”

Most compassionate Jesus, You are the Light of the whole world. Receive into the abode of Your Most Compassionate Heart the souls of those who do not believe in God and of those who as yet do not know You. Let the rays of Your grace enlighten them that they, too, together with us, may extol Your wonderful mercy; and do not let them escape from the abode which is Your Most Compassionate Heart.

Eternal Father, turn Your merciful gaze upon the souls of those who do not believe in You, and of those who as yet do not know You, but who are enclosed in the Most Compassionate Heart of Jesus. Draw them to the light of the Gospel. These souls do not know what great happiness it is to love You. Grant that they, too, may extol the generosity of Your mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me os que não creem em Deus e os que ainda não Me conhecem. Também neles Eu pensava durante a Minha amarga Paixão, e o seu futuro zelo consolava o Meu Coração. Mergulha-os no oceano da Minha misericórdia.”

Ó Jesus compassivíssimo, Vós sois a Luz do mundo inteiro. Recebei na morada do Vosso Compassivíssimo Coração as almas dos que não creem em Deus e dos que ainda não Vos conhecem. Que os raios da Vossa graça os iluminem, para que também eles, juntamente conosco, exaltem a Vossa admirável misericórdia; e não os deixeis escapar da morada que é o Vosso Compassivíssimo Coração.

Eterno Pai, olhai com misericórdia para as almas dos que não creem em Vós e dos que ainda não Vos conhecem, mas que estão encerradas no Compassivíssimo Coração de Jesus. Atraí-as à luz do Evangelho. Estas almas não sabem que grande felicidade é amar-Vos. Concedei que também elas exaltem a generosidade da Vossa misericórdia pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a los paganos (a los que no creen en Cristo) y aquellos que todavía no Me conocen. También pensaba en ellos durante Mi amarga Pasión y su futuro celo consoló Mi corazón. Sumérgelos en el mar de Mi misericordia. Jesús compasivísimo, que eres la luz del mundo entero. Acoge en la morada de Tu piadosísimo Corazón a las almas de los paganos que todavía no Te conocen. Que los rayos de Tu gracia las iluminen para que también ellas unidas a nosotros, ensalcen Tu misericordia admirable y no las dejes salir de la morada de Tu compasivísimo Corazón. Padre eterno, mira con misericordia a las almas de los paganos y de los que todavía no Te conocen, pero que están encerrados en el muy compasivo Corazón de Jesús. Atraerlas hacia la luz de evangelio. Estas almas desconocen la gran felicidad que es amarte. Concédeles que también ellas ensalcen la generosidad de Tu misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me those who do not believe in God and those who do not know Me, I was thinking also of them during My bitter Passion, and their future zeal comforted My Heart. Immerse them in the ocean of My mercy.”

Most compassionate Jesus, You are the Light of the whole world. Receive into the abode of Your Most Compassionate Heart the souls of those who do not believe in God and of those who as yet do not know You. Let the rays of Your grace enlighten them that they, too, together with us, may extol Your wonderful mercy; and do not let them escape from the abode which is Your Most Compassionate Heart.

Eternal Father, turn Your merciful gaze upon the souls of those who do not believe in You, and of those who as yet do not know You, but who are enclosed in the Most Compassionate Heart of Jesus. Draw them to the light of the Gospel. These souls do not know what great happiness it is to love You. Grant that they, too, may extol the generosity of Your mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me os que não creem em Deus e os que ainda não Me conhecem. Também neles Eu pensava durante a Minha amarga Paixão, e o seu futuro zelo consolava o Meu Coração. Mergulha-os no oceano da Minha misericórdia.”

Ó Jesus compassivíssimo, Vós sois a Luz do mundo inteiro. Recebei na morada do Vosso Compassivíssimo Coração as almas dos que não creem em Deus e dos que ainda não Vos conhecem. Que os raios da Vossa graça os iluminem, para que também eles, juntamente conosco, exaltem a Vossa admirável misericórdia; e não os deixeis escapar da morada que é o Vosso Compassivíssimo Coração.

Eterno Pai, olhai com misericórdia para as almas dos que não creem em Vós e dos que ainda não Vos conhecem, mas que estão encerradas no Compassivíssimo Coração de Jesus. Atraí-as à luz do Evangelho. Estas almas não sabem que grande felicidade é amar-Vos. Concedei que também elas exaltem a generosidade da Vossa misericórdia pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a las almas sin fe, de los que se separaron de la Iglesia, y sumérgelas en el mar de Mi misericordia. Durante Mi amarga Pasión, desgarraron Mi cuerpo y Mi corazón, es decir, Mi Iglesia. Según regresan a la Iglesia. Mis llagas cicatrizan y de este modo alivian Mi Pasión. Jesús, sumamente misericordioso, que eres la bondad misma. Tu no niegas la luz a quienes Te la piden. Acoge en la morada de Tu muy compasivo Corazón a las almas de los que no tienen fe y de los que se separaron de la Iglesia y llévalas con Tu Luz a la unidad con la Iglesia; no las dejes alejarse de la morada de Tu compasivo Corazón, sino haz que ellas glorifiquen la generosidad de Tu misericordia. Padre eterno, mira con misericordia a las almas de los que no tienen fe y de los que se separaron de la Iglesia que han malgastado Tus bendiciones y han abusado de Tus gracias por persistir obstinadamentente en sus errores. No mires sus errores, sino el amor de Tu Hijo y su amarga Pasión que sufrió por ellos ya que también ellos están acogidos en el sumamente compasivo Corazón de Jesús. Haz que también ellos glorifiquen Tu gran misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls of those who have separated themselves from My Church, and immerse them in the ocean of My mercy. During My bitter Passion they tore at My Body and Heart, that is, My Church. As they return to unity with the Church My wounds heal and in this way they alleviate My Passion.”

Most Merciful Jesus, Goodness Itself, You do not refuse light to those who seek it of You. Receive into the abode of Your Most Compassionate Heart the souls of those who have separated themselves from Your Church. Draw them by Your light into the unity of the Church, and do not let them escape from the abode of Your Most Compassionate Heart; but bring it about that they, too, come to glorify the generosity of Your mercy.

Eternal Father, turn Your merciful gaze upon the souls of those who have separated themselves from Your Son’s Church, who have squandered Your blessings and misused Your graces by obstinately persisting in their errors. Do not look upon their errors, but upon the love of Your own Son and upon His bitter Passion, which He underwent for their sake, since they, too, are enclosed in His Most Compassionate Heart. Bring it about that they also may glorify Your great mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas dos que se separaram da Minha Igreja e mergulha-as no oceano da Minha misericórdia. Durante a Minha amarga Paixão, elas dilaceravam o Meu Corpo e o Meu Coração, isto é, a Minha Igreja. Quando voltam à unidade da Igreja, as Minhas chagas se curam e assim aliviam a Minha Paixão.”

Ó Jesus misericordiosíssimo, que sois a própria Bondade, não recusais a luz a quem Vo-la pede. Recebei na morada do Vosso Compassivíssimo Coração as almas dos que se separaram da Vossa Igreja. Atraí-as pela Vossa luz à unidade da Igreja e não as deixeis escapar da morada do Vosso Compassivíssimo Coração; mas fazei que também elas venham a glorificar a generosidade da Vossa misericórdia.

Eterno Pai, olhai com misericórdia para as almas dos que se separaram da Igreja do Vosso Filho, que desperdiçaram as Vossas bênçãos e abusaram das Vossas graças, persistindo obstinadamente nos seus erros. Não olheis os seus erros, mas o amor do Vosso próprio Filho e a Sua amarga Paixão, que Ele sofreu também por elas, pois também elas estão encerradas no Seu Compassivíssimo Coração. Fazei que também elas glorifiquem a Vossa grande misericórdia pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a las almas sin fe, de los que se separaron de la Iglesia, y sumérgelas en el mar de Mi misericordia. Durante Mi amarga Pasión, desgarraron Mi cuerpo y Mi corazón, es decir, Mi Iglesia. Según regresan a la Iglesia. Mis llagas cicatrizan y de este modo alivian Mi Pasión. Jesús, sumamente misericordioso, que eres la bondad misma. Tu no niegas la luz a quienes Te la piden. Acoge en la morada de Tu muy compasivo Corazón a las almas de los que no tienen fe y de los que se separaron de la Iglesia y llévalas con Tu Luz a la unidad con la Iglesia; no las dejes alejarse de la morada de Tu compasivo Corazón, sino haz que ellas glorifiquen la generosidad de Tu misericordia. Padre eterno, mira con misericordia a las almas de los que no tienen fe y de los que se separaron de la Iglesia que han malgastado Tus bendiciones y han abusado de Tus gracias por persistir obstinadamentente en sus errores. No mires sus errores, sino el amor de Tu Hijo y su amarga Pasión que sufrió por ellos ya que también ellos están acogidos en el sumamente compasivo Corazón de Jesús. Haz que también ellos glorifiquen Tu gran misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls of those who have separated themselves from My Church, and immerse them in the ocean of My mercy. During My bitter Passion they tore at My Body and Heart, that is, My Church. As they return to unity with the Church My wounds heal and in this way they alleviate My Passion.”

Most Merciful Jesus, Goodness Itself, You do not refuse light to those who seek it of You. Receive into the abode of Your Most Compassionate Heart the souls of those who have separated themselves from Your Church. Draw them by Your light into the unity of the Church, and do not let them escape from the abode of Your Most Compassionate Heart; but bring it about that they, too, come to glorify the generosity of Your mercy.

Eternal Father, turn Your merciful gaze upon the souls of those who have separated themselves from Your Son’s Church, who have squandered Your blessings and misused Your graces by obstinately persisting in their errors. Do not look upon their errors, but upon the love of Your own Son and upon His bitter Passion, which He underwent for their sake, since they, too, are enclosed in His Most Compassionate Heart. Bring it about that they also may glorify Your great mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas dos que se separaram da Minha Igreja e mergulha-as no oceano da Minha misericórdia. Durante a Minha amarga Paixão, elas dilaceravam o Meu Corpo e o Meu Coração, isto é, a Minha Igreja. Quando voltam à unidade da Igreja, as Minhas chagas se curam e assim aliviam a Minha Paixão.”

Ó Jesus misericordiosíssimo, que sois a própria Bondade, não recusais a luz a quem Vo-la pede. Recebei na morada do Vosso Compassivíssimo Coração as almas dos que se separaram da Vossa Igreja. Atraí-as pela Vossa luz à unidade da Igreja e não as deixeis escapar da morada do Vosso Compassivíssimo Coração; mas fazei que também elas venham a glorificar a generosidade da Vossa misericórdia.

Eterno Pai, olhai com misericórdia para as almas dos que se separaram da Igreja do Vosso Filho, que desperdiçaram as Vossas bênçãos e abusaram das Vossas graças, persistindo obstinadamente nos seus erros. Não olheis os seus erros, mas o amor do Vosso próprio Filho e a Sua amarga Paixão, que Ele sofreu também por elas, pois também elas estão encerradas no Seu Compassivíssimo Coração. Fazei que também elas glorifiquem a Vossa grande misericórdia pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a las almas mansas y humildes y a las almas de los niños pequeños, y sumérgelas en Mi misericordia. Estas son las almas más semejantes a Mi Corazón. Ellas me fortalecieron durante Mi amarga agonía. Las veía como ángeles terrestres que velarían al pie de Mis altares. Sobre ellas derramo torrentes enteros de gracias. Solamente el alma humilde es capaz de recibir Mi gracia; concedo Mi confianza a las almas humildes. Jesús, tan misericordioso, Tu Mismo has dicho: “Aprendan de Mi que soy manso y humilde de corazón”. Acoge en la morada de Tu compasivísimo Corazón a las almas mansas y humildes y a las de los niños pequeños. Estas almas llevan a todo al cielo al éxtasis y son las preferidas del Padre Celestial. Son un ramillete perfumado ante el trono de Dios, de cuyo perfume se deleita Dios mismo. Estas almas tienen una morada permanente en Tu compasivísimo Corazón y cantan sin cesar un himno de amor y misericordia por la eternidad. Padre Eterno, mira con misericordia a las almas mansas y humildes y a las almas de los niños pequeños que están encerradas en el muy compasivo Corazón de Jesús. Estas almas son las más semejantes a Tu Hijo. Su fragancia asciende desde la tierra y alcanza Tu trono. Padre de misericordia y de toda bondad, Te suplico por el amor que tienes por estas almas y el gozo que Te proporcionan, bendice al mundo entero para que todas las almas canten juntas las alabanzas de Tu misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Meek and Humble Souls and the Souls of Little Children, and immerse them in My mercy. These souls most closely resemble My Heart. They strengthened Me during My bitter agony. I saw them as earthly Angels, who will keep vigil at My altars. I pour out upon them whole torrents of grace. I favor humble souls with My confidence.”

Most Merciful Jesus, You yourself have said, “Learn from Me for I am meek and humble of heart.” Receive into the abode of Your Most Compassionate Heart all meek and humble souls and the souls of little children. These souls send all heaven into ecstasy and they are the heavenly Father’s favorites. They are a sweet-smelling bouquet before the throne of God; God Himself takes delight in their fragrance. These souls have a permanent abode in Your Most Compassionate Heart, O Jesus, and they unceasingly sing out a hymn of love and mercy.

Eternal Father, turn Your merciful gaze upon meek souls, upon humble souls, and upon little children who are enfolded in the abode which is the Most Compassionate Heart of Jesus. These souls bear the closest resemblance to Your Son. Their fragrance rises from the earth and reaches Your very throne. Father of mercy and of all goodness, I beg You by the love You bear these souls and by the delight You take in them: Bless the whole world, that all souls together may sing out the praises of Your mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas mansas e humildes e as almas das criancinhas e mergulha-as na Minha misericórdia. Estas almas são as mais semelhantes ao Meu Coração. Elas Me fortaleceram durante a Minha amarga agonia. Eu as via como Anjos da terra, que velarão junto aos Meus altares. Derramo sobre elas torrentes inteiras de graças. Favoreço as almas humildes com a Minha confiança.”

Ó Jesus misericordiosíssimo, Vós mesmo dissestes: “Aprendei de Mim, que sou manso e humilde de coração.” Recebei na morada do Vosso Compassivíssimo Coração todas as almas mansas e humildes e as almas das criancinhas. Estas almas enchem de êxtase todo o céu e são as preferidas do Pai celeste. São um ramalhete perfumado diante do trono de Deus; o próprio Deus se deleita no seu perfume. Estas almas têm morada permanente no Vosso Compassivíssimo Coração, ó Jesus, e cantam sem cessar um hino de amor e de misericórdia.

Eterno Pai, olhai com misericórdia para as almas mansas, para as almas humildes e para as criancinhas, que estão envolvidas na morada que é o Compassivíssimo Coração de Jesus. Estas almas são as mais semelhantes ao Vosso Filho. O seu perfume sobe da terra e chega até o Vosso trono. Pai de misericórdia e de toda bondade, eu Vos suplico, pelo amor que tendes a estas almas e pela alegria que elas Vos dão: abençoai o mundo inteiro, para que todas as almas juntas cantem os louvores da Vossa misericórdia pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a las almas mansas y humildes y a las almas de los niños pequeños, y sumérgelas en Mi misericordia. Estas son las almas más semejantes a Mi Corazón. Ellas me fortalecieron durante Mi amarga agonía. Las veía como ángeles terrestres que velarían al pie de Mis altares. Sobre ellas derramo torrentes enteros de gracias. Solamente el alma humilde es capaz de recibir Mi gracia; concedo Mi confianza a las almas humildes. Jesús, tan misericordioso, Tu Mismo has dicho: “Aprendan de Mi que soy manso y humilde de corazón”. Acoge en la morada de Tu compasivísimo Corazón a las almas mansas y humildes y a las de los niños pequeños. Estas almas llevan a todo al cielo al éxtasis y son las preferidas del Padre Celestial. Son un ramillete perfumado ante el trono de Dios, de cuyo perfume se deleita Dios mismo. Estas almas tienen una morada permanente en Tu compasivísimo Corazón y cantan sin cesar un himno de amor y misericordia por la eternidad. Padre Eterno, mira con misericordia a las almas mansas y humildes y a las almas de los niños pequeños que están encerradas en el muy compasivo Corazón de Jesús. Estas almas son las más semejantes a Tu Hijo. Su fragancia asciende desde la tierra y alcanza Tu trono. Padre de misericordia y de toda bondad, Te suplico por el amor que tienes por estas almas y el gozo que Te proporcionan, bendice al mundo entero para que todas las almas canten juntas las alabanzas de Tu misericordia por los siglos de los siglos. Amén.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Meek and Humble Souls and the Souls of Little Children, and immerse them in My mercy. These souls most closely resemble My Heart. They strengthened Me during My bitter agony. I saw them as earthly Angels, who will keep vigil at My altars. I pour out upon them whole torrents of grace. I favor humble souls with My confidence.”

Most Merciful Jesus, You yourself have said, “Learn from Me for I am meek and humble of heart.” Receive into the abode of Your Most Compassionate Heart all meek and humble souls and the souls of little children. These souls send all heaven into ecstasy and they are the heavenly Father’s favorites. They are a sweet-smelling bouquet before the throne of God; God Himself takes delight in their fragrance. These souls have a permanent abode in Your Most Compassionate Heart, O Jesus, and they unceasingly sing out a hymn of love and mercy.

Eternal Father, turn Your merciful gaze upon meek souls, upon humble souls, and upon little children who are enfolded in the abode which is the Most Compassionate Heart of Jesus. These souls bear the closest resemblance to Your Son. Their fragrance rises from the earth and reaches Your very throne. Father of mercy and of all goodness, I beg You by the love You bear these souls and by the delight You take in them: Bless the whole world, that all souls together may sing out the praises of Your mercy for endless ages. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas mansas e humildes e as almas das criancinhas e mergulha-as na Minha misericórdia. Estas almas são as mais semelhantes ao Meu Coração. Elas Me fortaleceram durante a Minha amarga agonia. Eu as via como Anjos da terra, que velarão junto aos Meus altares. Derramo sobre elas torrentes inteiras de graças. Favoreço as almas humildes com a Minha confiança.”

Ó Jesus misericordiosíssimo, Vós mesmo dissestes: “Aprendei de Mim, que sou manso e humilde de coração.” Recebei na morada do Vosso Compassivíssimo Coração todas as almas mansas e humildes e as almas das criancinhas. Estas almas enchem de êxtase todo o céu e são as preferidas do Pai celeste. São um ramalhete perfumado diante do trono de Deus; o próprio Deus se deleita no seu perfume. Estas almas têm morada permanente no Vosso Compassivíssimo Coração, ó Jesus, e cantam sem cessar um hino de amor e de misericórdia.

Eterno Pai, olhai com misericórdia para as almas mansas, para as almas humildes e para as criancinhas, que estão envolvidas na morada que é o Compassivíssimo Coração de Jesus. Estas almas são as mais semelhantes ao Vosso Filho. O seu perfume sobe da terra e chega até o Vosso trono. Pai de misericórdia e de toda bondade, eu Vos suplico, pelo amor que tendes a estas almas e pela alegria que elas Vos dão: abençoai o mundo inteiro, para que todas as almas juntas cantem os louvores da Vossa misericórdia pelos séculos sem fim. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a las almas que verán y glorifican Mi misericordia de modo especial y sumérgelas en Mi misericordia. Estas almas son las que más lamentaron Mi Pasión y penetraron más profundamente en Mi espíritu. Ellas son un reflejo viviente de Mi Corazón compasivo. Estas almas resplandecerán con un resplandor especial en la vida futura. Ninguna de ellas ira al fuego del infierno. Defenderé de modo especial a cada una en la hora de la muerte. Jesús misericordiosisimo, cuyo Corazón es el amor mismo, acoge en la morada de Tu compasivísimo Corazón a las almas que veneran y ensalzan de modo particular la grandeza de Tu misericordia. Estas almas son fuertes con el poder de Dios Mismo. En medio de toda clase de aflicciones y adversidades siguen adelante confiadas en Tu misericordia, y unidas a Ti, cargan sobre sus hombros a toda la humanidad. Estas almas no serán juzgadas severamente, sino que Tu misericordia las protegerá en la hora de la muerte. Padre Eterno, mira con misericordia a aquellas almas que glorifican y veneran Tu mayor atributo, es decir, Tu misericordia insondable y que están encerradas en el compasivísimo Corazón de Jesús. Estas almas son un Evangelio viviente, sus manos están llenas de obras de misericordia y sus corazones, desbordantes de gozo, Te cantan, Oh Altísimo, un cántico de misericordia. Te suplico, Oh Dios, muéstrales Tu misericordia según la esperanza y la confianza que han puesto en Ti. Que se cumplan en ellas la promesa de Jesús quien dijo: “A las almas que veneren esta infinita misericordia Mía, Yo Mismo las defenderé como Mi Gloria durante sus vidas y especialmente en la hora de la muerte.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls who especially venerate and glorify My Mercy, and immerse them in My mercy. These souls sorrowed most over my Passion and entered most deeply into My spirit. They are living images of My Compassionate Heart. These souls will shine with a special brightness in the next life. Not one of them will go into the fire of hell. I shall particularly defend each one of them at the hour of death.”

Most Merciful Jesus, whose Heart is Love Itself, receive into the abode of Your Most Compassionate Heart the souls of those who particularly extol and venerate the greatness of Your mercy. These souls are mighty with the very power of God Himself. In the midst of all afflictions and adversities they go forward, confident of Your mercy; and united to You, O Jesus, they carry all mankind on their shoulders. These souls will not be judged severely, but Your mercy will embrace them as they depart from this life.

Eternal Father, turn Your merciful gaze upon the souls who glorify and venerate Your greatest attribute, that of Your fathomless mercy, and who are enclosed in the Most Compassionate Heart of Jesus. These souls are a living Gospel; their hands are full of deeds of mercy, and their hearts, overflowing with joy, sing a canticle of mercy to You, O Most High! I beg You O God:

Show them Your mercy according to the hope and trust they have placed in You. Let there be accomplished in them the promise of Jesus, who said to them that during their life, but especially at the hour of death, the souls who will venerate this fathomless mercy of His, He, Himself, will defend as His glory. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas que veneram e glorificam de modo especial a Minha misericórdia e mergulha-as na Minha misericórdia. Estas almas foram as que mais sofreram com a Minha Paixão e penetraram mais profundamente no Meu espírito. São imagens vivas do Meu Coração compassivo. Estas almas brilharão com um esplendor especial na vida futura. Nenhuma delas irá para o fogo do inferno. Defenderei especialmente cada uma delas na hora da morte.”

Ó Jesus misericordiosíssimo, cujo Coração é o próprio Amor, recebei na morada do Vosso Compassivíssimo Coração as almas dos que exaltam e veneram de modo especial a grandeza da Vossa misericórdia. Estas almas são fortes com o próprio poder de Deus. No meio de todas as aflições e adversidades, avançam confiantes na Vossa misericórdia; e, unidas a Vós, ó Jesus, carregam sobre os ombros toda a humanidade. Estas almas não serão julgadas com severidade, mas a Vossa misericórdia as envolverá quando partirem desta vida.

Eterno Pai, olhai com misericórdia para as almas que glorificam e veneram o Vosso maior atributo, a Vossa insondável misericórdia, e que estão encerradas no Compassivíssimo Coração de Jesus. Estas almas são um Evangelho vivo; as suas mãos estão cheias de obras de misericórdia e os seus corações, transbordando de alegria, cantam-Vos um cântico de misericórdia, ó Altíssimo! Eu Vos suplico, ó Deus:

Mostrai-lhes a Vossa misericórdia segundo a esperança e a confiança que puseram em Vós. Cumpra-se nelas a promessa de Jesus, que lhes disse que, durante a vida, mas especialmente na hora da morte, Ele mesmo defenderia como Sua glória as almas que venerassem esta Sua insondável misericórdia. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a las almas que verán y glorifican Mi misericordia de modo especial y sumérgelas en Mi misericordia. Estas almas son las que más lamentaron Mi Pasión y penetraron más profundamente en Mi espíritu. Ellas son un reflejo viviente de Mi Corazón compasivo. Estas almas resplandecerán con un resplandor especial en la vida futura. Ninguna de ellas ira al fuego del infierno. Defenderé de modo especial a cada una en la hora de la muerte. Jesús misericordiosisimo, cuyo Corazón es el amor mismo, acoge en la morada de Tu compasivísimo Corazón a las almas que veneran y ensalzan de modo particular la grandeza de Tu misericordia. Estas almas son fuertes con el poder de Dios Mismo. En medio de toda clase de aflicciones y adversidades siguen adelante confiadas en Tu misericordia, y unidas a Ti, cargan sobre sus hombros a toda la humanidad. Estas almas no serán juzgadas severamente, sino que Tu misericordia las protegerá en la hora de la muerte. Padre Eterno, mira con misericordia a aquellas almas que glorifican y veneran Tu mayor atributo, es decir, Tu misericordia insondable y que están encerradas en el compasivísimo Corazón de Jesús. Estas almas son un Evangelio viviente, sus manos están llenas de obras de misericordia y sus corazones, desbordantes de gozo, Te cantan, Oh Altísimo, un cántico de misericordia. Te suplico, Oh Dios, muéstrales Tu misericordia según la esperanza y la confianza que han puesto en Ti. Que se cumplan en ellas la promesa de Jesús quien dijo: “A las almas que veneren esta infinita misericordia Mía, Yo Mismo las defenderé como Mi Gloria durante sus vidas y especialmente en la hora de la muerte.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls who especially venerate and glorify My Mercy, and immerse them in My mercy. These souls sorrowed most over my Passion and entered most deeply into My spirit. They are living images of My Compassionate Heart. These souls will shine with a special brightness in the next life. Not one of them will go into the fire of hell. I shall particularly defend each one of them at the hour of death.”

Most Merciful Jesus, whose Heart is Love Itself, receive into the abode of Your Most Compassionate Heart the souls of those who particularly extol and venerate the greatness of Your mercy. These souls are mighty with the very power of God Himself. In the midst of all afflictions and adversities they go forward, confident of Your mercy; and united to You, O Jesus, they carry all mankind on their shoulders. These souls will not be judged severely, but Your mercy will embrace them as they depart from this life.

Eternal Father, turn Your merciful gaze upon the souls who glorify and venerate Your greatest attribute, that of Your fathomless mercy, and who are enclosed in the Most Compassionate Heart of Jesus. These souls are a living Gospel; their hands are full of deeds of mercy, and their hearts, overflowing with joy, sing a canticle of mercy to You, O Most High! I beg You O God:

Show them Your mercy according to the hope and trust they have placed in You. Let there be accomplished in them the promise of Jesus, who said to them that during their life, but especially at the hour of death, the souls who will venerate this fathomless mercy of His, He, Himself, will defend as His glory. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas que veneram e glorificam de modo especial a Minha misericórdia e mergulha-as na Minha misericórdia. Estas almas foram as que mais sofreram com a Minha Paixão e penetraram mais profundamente no Meu espírito. São imagens vivas do Meu Coração compassivo. Estas almas brilharão com um esplendor especial na vida futura. Nenhuma delas irá para o fogo do inferno. Defenderei especialmente cada uma delas na hora da morte.”

Ó Jesus misericordiosíssimo, cujo Coração é o próprio Amor, recebei na morada do Vosso Compassivíssimo Coração as almas dos que exaltam e veneram de modo especial a grandeza da Vossa misericórdia. Estas almas são fortes com o próprio poder de Deus. No meio de todas as aflições e adversidades, avançam confiantes na Vossa misericórdia; e, unidas a Vós, ó Jesus, carregam sobre os ombros toda a humanidade. Estas almas não serão julgadas com severidade, mas a Vossa misericórdia as envolverá quando partirem desta vida.

Eterno Pai, olhai com misericórdia para as almas que glorificam e veneram o Vosso maior atributo, a Vossa insondável misericórdia, e que estão encerradas no Compassivíssimo Coração de Jesus. Estas almas são um Evangelho vivo; as suas mãos estão cheias de obras de misericórdia e os seus corações, transbordando de alegria, cantam-Vos um cântico de misericórdia, ó Altíssimo! Eu Vos suplico, ó Deus:

Mostrai-lhes a Vossa misericórdia segundo a esperança e a confiança que puseram em Vós. Cumpra-se nelas a promessa de Jesus, que lhes disse que, durante a vida, mas especialmente na hora da morte, Ele mesmo defenderia como Sua glória as almas que venerassem esta Sua insondável misericórdia. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a las almas que estén en la cárcel del purgatorio y sumérgelas en el abismo de Mi misericordia. Que los torrentes de Mi sangre refresquen el ardor del purgatorio. Todas estas almas son muy amadas por Mi. Ellas cumplen con el justo castigo que se debe a Mi justicia. Esta en tu poder llevarles alivio. Haz uso de todas las indulgencias del tesoro de Mi Iglesia y ofrécelas en su nombre… Oh, si conocieras los tormentos que ellas sufren ofrecerías continuamente por ellas las limosnas del espíritu y saldarías las deudas que tienen con Mi justicia. Jesús misericordiosisimo, Tu Mismo has dicho que deseas la misericordia; heme aquí que llevo a la morada de Tu muy compasivo Corazón a las almas de purgatorio, almas que Te son muy queridas, pero que deben pagar su culpa adeudada a Tu justicia. Que los torrentes de Sangre y Agua que brotaron de Tu Corazón, apaguen el fuego del purgatorio para que también allí sea glorificado el poder de Tu misericordia. Padre Eterno, mira con misericordia a las almas que sufren en el purgatorio y que están encerradas en el muy compasivo Corazón de Jesús. Te suplico por la dolorosa Pasión de Jesús, Tu Hijo, y por toda la amargura con la cual su sacratísima alma fue inundada, muestra Tu misericordia a las almas que están bajo Tu justo escrutinio. No las mires sino a través de las heridas de Jesús, Tu amadísimo Hijo, ya que creemos que Tu bondad y Tu compasión no tienen límites.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls who are in the prison of Purgatory, and immerse them in the abyss of My mercy. Let the torrents of My Blood cool down their scorching flames. All these souls are greatly loved by Me. They are making retribution to My justice. It is in your power to bring them relief. Draw all the indulgences from the treasury of My Church and offer them on their behalf. Oh, if you only knew the torments they suffer, you would continually offer for them the alms of the spirit and pay off their debt to My justice.”

Most Merciful Jesus, You Yourself have said that You desire mercy; so I bring into the abode of Your Most Compassionate Heart the souls in Purgatory, souls who are very dear to You, and yet, who must make retribution to Your justice. May the streams of Blood and Water which gushed forth from Your Heart put out the flames of Purgatory, that there, too, the power of Your mercy may be celebrated.

Eternal Father, turn Your merciful gaze upon the souls suffering in Purgatory, who are enfolded in the Most Compassionate Heart of Jesus. I beg You, by the sorrowful Passion of Jesus Your Son, and by all the bitterness with which His most sacred Soul was flooded: Manifest Your mercy to the souls who are under Your just scrutiny. Look upon them in no other way but only through the Wounds of Jesus, Your dearly beloved Son; for we firmly believe that there is no limit to Your goodness and compassion. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas que estão na prisão do Purgatório e mergulha-as no abismo da Minha misericórdia. Que as torrentes do Meu Sangue refresquem as suas chamas ardentes. Todas estas almas são muito amadas por Mim. Estão pagando à Minha justiça. Está em teu poder levar-lhes alívio. Tira todas as indulgências do tesouro da Minha Igreja e oferece-as por elas. Oh, se conhecesses os tormentos que sofrem, oferecerias continuamente por elas a esmola do espírito e pagarias a sua dívida à Minha justiça.”

Ó Jesus misericordiosíssimo, Vós mesmo dissestes que quereis misericórdia; por isso trago para a morada do Vosso Compassivíssimo Coração as almas do Purgatório, almas que Vos são muito queridas e que, no entanto, devem pagar à Vossa justiça. Que as torrentes de Sangue e Água que jorraram do Vosso Coração apaguem as chamas do Purgatório, para que também ali seja celebrado o poder da Vossa misericórdia.

Eterno Pai, olhai com misericórdia para as almas que sofrem no Purgatório e que estão envolvidas no Compassivíssimo Coração de Jesus. Eu Vos suplico, pela dolorosa Paixão de Jesus, Vosso Filho, e por toda a amargura com que foi inundada a Sua sacratíssima Alma: manifestai a Vossa misericórdia às almas que estão sob o Vosso justo olhar. Não as olheis de outro modo senão através das Chagas de Jesus, Vosso Filho muito amado, pois cremos firmemente que a Vossa bondade e compaixão não têm limites. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a las almas que estén en la cárcel del purgatorio y sumérgelas en el abismo de Mi misericordia. Que los torrentes de Mi sangre refresquen el ardor del purgatorio. Todas estas almas son muy amadas por Mi. Ellas cumplen con el justo castigo que se debe a Mi justicia. Esta en tu poder llevarles alivio. Haz uso de todas las indulgencias del tesoro de Mi Iglesia y ofrécelas en su nombre… Oh, si conocieras los tormentos que ellas sufren ofrecerías continuamente por ellas las limosnas del espíritu y saldarías las deudas que tienen con Mi justicia. Jesús misericordiosisimo, Tu Mismo has dicho que deseas la misericordia; heme aquí que llevo a la morada de Tu muy compasivo Corazón a las almas de purgatorio, almas que Te son muy queridas, pero que deben pagar su culpa adeudada a Tu justicia. Que los torrentes de Sangre y Agua que brotaron de Tu Corazón, apaguen el fuego del purgatorio para que también allí sea glorificado el poder de Tu misericordia. Padre Eterno, mira con misericordia a las almas que sufren en el purgatorio y que están encerradas en el muy compasivo Corazón de Jesús. Te suplico por la dolorosa Pasión de Jesús, Tu Hijo, y por toda la amargura con la cual su sacratísima alma fue inundada, muestra Tu misericordia a las almas que están bajo Tu justo escrutinio. No las mires sino a través de las heridas de Jesús, Tu amadísimo Hijo, ya que creemos que Tu bondad y Tu compasión no tienen límites.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls who are in the prison of Purgatory, and immerse them in the abyss of My mercy. Let the torrents of My Blood cool down their scorching flames. All these souls are greatly loved by Me. They are making retribution to My justice. It is in your power to bring them relief. Draw all the indulgences from the treasury of My Church and offer them on their behalf. Oh, if you only knew the torments they suffer, you would continually offer for them the alms of the spirit and pay off their debt to My justice.”

Most Merciful Jesus, You Yourself have said that You desire mercy; so I bring into the abode of Your Most Compassionate Heart the souls in Purgatory, souls who are very dear to You, and yet, who must make retribution to Your justice. May the streams of Blood and Water which gushed forth from Your Heart put out the flames of Purgatory, that there, too, the power of Your mercy may be celebrated.

Eternal Father, turn Your merciful gaze upon the souls suffering in Purgatory, who are enfolded in the Most Compassionate Heart of Jesus. I beg You, by the sorrowful Passion of Jesus Your Son, and by all the bitterness with which His most sacred Soul was flooded: Manifest Your mercy to the souls who are under Your just scrutiny. Look upon them in no other way but only through the Wounds of Jesus, Your dearly beloved Son; for we firmly believe that there is no limit to Your goodness and compassion. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas que estão na prisão do Purgatório e mergulha-as no abismo da Minha misericórdia. Que as torrentes do Meu Sangue refresquem as suas chamas ardentes. Todas estas almas são muito amadas por Mim. Estão pagando à Minha justiça. Está em teu poder levar-lhes alívio. Tira todas as indulgências do tesouro da Minha Igreja e oferece-as por elas. Oh, se conhecesses os tormentos que sofrem, oferecerias continuamente por elas a esmola do espírito e pagarias a sua dívida à Minha justiça.”

Ó Jesus misericordiosíssimo, Vós mesmo dissestes que quereis misericórdia; por isso trago para a morada do Vosso Compassivíssimo Coração as almas do Purgatório, almas que Vos são muito queridas e que, no entanto, devem pagar à Vossa justiça. Que as torrentes de Sangue e Água que jorraram do Vosso Coração apaguem as chamas do Purgatório, para que também ali seja celebrado o poder da Vossa misericórdia.

Eterno Pai, olhai com misericórdia para as almas que sofrem no Purgatório e que estão envolvidas no Compassivíssimo Coração de Jesus. Eu Vos suplico, pela dolorosa Paixão de Jesus, Vosso Filho, e por toda a amargura com que foi inundada a Sua sacratíssima Alma: manifestai a Vossa misericórdia às almas que estão sob o Vosso justo olhar. Não as olheis de outro modo senão através das Chagas de Jesus, Vosso Filho muito amado, pois cremos firmemente que a Vossa bondade e compaixão não têm limites. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

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

(menciona tu petición aquí…)

Hoy, tráeme a las almas tibias y sumérgelas en el abismo de Mi misericordia. Estas almas son las que más dolorosamente hieren Mi Corazón. A causa de las almas tibias, Mi alma experimento la más intensa repugnancia en el Huerto de los Olivos. A causa de ellas dije: Padre, aleja de Mí este cáliz, si es Tu voluntad. Para ellas, la ultima tabla de salvación consiste en recurrir a Mi misericordia. Jesús piadosísimo, que eres la compasión misma, Te traigo a las almas tibias a la morada de Tu piadosísimo Corazón. Que estas almas heladas que se parecen a cadáveres y Te llenan de gran repugnancia se calienten con el fuego de Tu amor puro. Oh Jesús tan compasivo, ejercita la omnipotencia de Tu misericordia y atráelas al mismo ardor de Tu amor y concédeles el amor santo, porque Tú lo puedes todo. Padre Eterno, mira con misericordia a las almas tibias que, sin embargo, están acogidas en el piadosísimo Corazón de Jesús. Padre de la misericordia, te suplico por la amarga Pasión de Tu Hijo y por su agonía de tres horas en la cruz, permite que también ellas glorifiquen el abismo de Tu Misericordia.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls who have become Lukewarm, and immerse them in the abyss of My mercy. These souls wound My Heart most painfully. My soul suffered the most dreadful loathing in the Garden of Olives because of lukewarm souls. They were the reason I cried out: ‘Father, take this cup away from Me, if it be Your will.’ For them, the last hope of salvation is to run to My mercy.”

Most compassionate Jesus, You are Compassion Itself. I bring lukewarm souls into the abode of Your Most Compassionate Heart. In this fire of Your pure love, let these tepid souls who, like corpses, filled You with such deep loathing, be once again set aflame. O Most Compassionate Jesus, exercise the omnipotence of Your mercy and draw them into the very ardor of Your love, and bestow upon them the gift of holy love, for nothing is beyond Your power.

Eternal Father, turn Your merciful gaze upon lukewarm souls who are nonetheless enfolded in the Most Compassionate Heart of Jesus. Father of Mercy, I beg You by the bitter Passion of Your Son and by His three-hour agony on the Cross: Let them, too, glorify the abyss of Your mercy. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas tíbias e mergulha-as no abismo da Minha misericórdia. Estas almas ferem o Meu Coração da maneira mais dolorosa. A Minha alma sentiu a mais terrível repugnância no Jardim das Oliveiras por causa das almas tíbias. Foram elas a razão por que exclamei: ‘Pai, afasta de Mim este cálice, se for da Tua vontade.’ Para elas, a última esperança de salvação é recorrer à Minha misericórdia.”

Ó Jesus compassivíssimo, Vós sois a própria Compaixão. Trago as almas tíbias para a morada do Vosso Compassivíssimo Coração. Que neste fogo do Vosso amor puro se inflamem novamente estas almas mornas, que, como cadáveres, Vos causaram tão profunda repugnância. Ó Jesus compassivíssimo, exercei a onipotência da Vossa misericórdia e atraí-as ao próprio ardor do Vosso amor, e concedei-lhes o dom do santo amor, pois nada é impossível ao Vosso poder.

Eterno Pai, olhai com misericórdia para as almas tíbias que, no entanto, estão envolvidas no Compassivíssimo Coração de Jesus. Pai de Misericórdia, eu Vos suplico pela amarga Paixão do Vosso Filho e pela Sua agonia de três horas na Cruz: que também elas glorifiquem o abismo da Vossa misericórdia. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Hoy, tráeme a las almas tibias y sumérgelas en el abismo de Mi misericordia. Estas almas son las que más dolorosamente hieren Mi Corazón. A causa de las almas tibias, Mi alma experimento la más intensa repugnancia en el Huerto de los Olivos. A causa de ellas dije: Padre, aleja de Mí este cáliz, si es Tu voluntad. Para ellas, la ultima tabla de salvación consiste en recurrir a Mi misericordia. Jesús piadosísimo, que eres la compasión misma, Te traigo a las almas tibias a la morada de Tu piadosísimo Corazón. Que estas almas heladas que se parecen a cadáveres y Te llenan de gran repugnancia se calienten con el fuego de Tu amor puro. Oh Jesús tan compasivo, ejercita la omnipotencia de Tu misericordia y atráelas al mismo ardor de Tu amor y concédeles el amor santo, porque Tú lo puedes todo. Padre Eterno, mira con misericordia a las almas tibias que, sin embargo, están acogidas en el piadosísimo Corazón de Jesús. Padre de la misericordia, te suplico por la amarga Pasión de Tu Hijo y por su agonía de tres horas en la cruz, permite que también ellas glorifiquen el abismo de Tu Misericordia.

Se utiliza un rosario común de cinco decenas.

Padre nuestro, que estás en el cielo, santificado sea tu nombre; venga a nosotros tu reino; hágase tu voluntad en la tierra como en el cielo. Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación y líbranos del mal. Amén.

Dios te salve, María, llena eres de gracia; el Señor es contigo, bendita eres entre todas las mujeres y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros pecadores, ahora y en la hora de nuestra muerte. Amén.

Creo en Dios, Padre todopoderoso, Creador del cielo y de la tierra. Y en Jesucristo, su único Hijo, nuestro Señor. Que fue concebido por obra y gracia del Espíritu Santo. Nacío de Santa María Virgen. Padecío bajo el poder de Poncio Pilato. Fue crucificado, muerto y sepultado. Descendió a los infiernos. Al tercer día resucíto de entre los muertos. Subió a los cielos. Está sentado a la derecha de Dios, Padre todopoderoso. Desde allí ha de venir a juzgar a vivos y muertos. Creoen el Espíritu Santo, la santa Iglesia católica, la comunión de los antos, el perdón de los pecados, la resurrección de la carne y la vida perdurable. Amén.

Al comienzo de cada decena (x1):
 Padre Eterno, te ofrezco el Cuerpo, Sangre, Alma y Divinidad de tu amadísimo Hijo, Nuestro Señor Jesucristo, como propiciación de nuestros pecados y de los del mundo entero.

En cada cuenta de la decena (x10):
 Por su dolorosa Pasión, ten misericordia de nosotros y del mundo entero.

Al finalizar las 5 decenas de la coronilla se repite 3 veces:
 Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

“Today bring to Me the Souls who have become Lukewarm, and immerse them in the abyss of My mercy. These souls wound My Heart most painfully. My soul suffered the most dreadful loathing in the Garden of Olives because of lukewarm souls. They were the reason I cried out: ‘Father, take this cup away from Me, if it be Your will.’ For them, the last hope of salvation is to run to My mercy.”

Most compassionate Jesus, You are Compassion Itself. I bring lukewarm souls into the abode of Your Most Compassionate Heart. In this fire of Your pure love, let these tepid souls who, like corpses, filled You with such deep loathing, be once again set aflame. O Most Compassionate Jesus, exercise the omnipotence of Your mercy and draw them into the very ardor of Your love, and bestow upon them the gift of holy love, for nothing is beyond Your power.

Eternal Father, turn Your merciful gaze upon lukewarm souls who are nonetheless enfolded in the Most Compassionate Heart of Jesus. Father of Mercy, I beg You by the bitter Passion of Your Son and by His three-hour agony on the Cross: Let them, too, glorify the abyss of Your mercy. Amen.

Pray the Divine Mercy Chaplet (you can use regular Rosary beads for this):

Make the Sign of the Cross.

Pray the Optional Opening Prayers

St. Faustina’s Prayer for Sinners

O Jesus, eternal Truth, our Life, I call upon You and I beg Your mercy for poor sinners. O sweetest Heart of my Lord, full of pity and unfathomable mercy, I plead with You for poor sinners. O Most Sacred Heart, Fount of Mercy from which gush forth rays of inconceivable graces upon the entire human race, I beg of You light for poor sinners. O Jesus, be mindful of Your own bitter Passion and do not permit the loss of souls redeemed at so dear a price of Your most precious Blood. O Jesus, when I consider the great price of Your Blood, I rejoice at its immensity, for one drop alone would have been enough for the salvation of all sinners. Although sin is an abyss of wickedness and ingratitude, the price paid for us can never be equaled. Therefore, let every soul trust in the Passion of the Lord, and place its hope in His mercy. God will not deny His mercy to anyone. Heaven and earth may change, but God’s mercy will never be exhausted. Oh, what immense joy burns in my heart when I contemplate Your incomprehensible goodness, O Jesus! I desire to bring all sinners to Your feet that they may glorify Your mercy throughout endless ages (Diary of Saint Maria Faustina Kowalska, 72).

You expired, Jesus, but the source of life gushed forth for souls, and the ocean of mercy opened up for the whole world. O Fount of Life, unfathomable Divine Mercy, envelop the whole world and empty Yourself out upon us.

(Repeat three times)
 O Blood and Water, which gushed forth from the Heart of Jesus as a fount of mercy for us, I trust in You!

Then on the first beads above the crucifix pray
 one Our Father
 one Hail Mary and 
 The Apostles Creed

I believe in God,
 the Father Almighty,
 Creator of heaven and earth,
 and in Jesus Christ, his only Son, our Lord,
 who was conceived by the power of the Holy Spirit,
 born of the Virgin Mary,
 suffered under Pontius Pilate,
 was crucified, died and was buried;
 he descended to the dead;
 on the third day he rose again from the dead;
 he ascended into Heaven,
 and is seated at the right hand of God the Father Almighty;
 from there he will come to judge the living and the dead.

I believe in the Holy Spirit,
 the holy Catholic Church,
 the Communion of Saints,
 the forgiveness of sins,
 the resurrection of the body,
 and life everlasting.

Amen.

Using the Rosary beads, on the Our Father Beads say the following:
 Eternal Father, I offer Thee the Body and Blood, Soul and Divinity of Thy dearly beloved Son, Our Lord Jesus Christ, in atonement for our sins and those of the whole world.

After each Eternal Father prayer, on the ten Hail Mary Beads say the following:
 For the sake of His sorrowful Passion, have mercy on us and on the whole world.

(Repeat step 4 and 5 for all five decades).

Conclude with the Holy God prayer, repeated three times:
 Holy God, Holy Mighty One, Holy Immortal One, have mercy on us and on the whole world.

Pray the Optional Closing Prayers
 Eternal God, in whom mercy is endless and the treasury of compassion — inexhaustible, look kindly upon us and increase Your mercy in us, that in difficult moments we might not despair nor become despondent, but with great confidence submit ourselves to Your holy will, which is Love and Mercy itself.

O Greatly Merciful God, Infinite Goodness, today all mankind calls out from the abyss of its misery to Your mercy — to Your compassion, O God; and it is with its mighty voice of misery that it cries out. Gracious God, do not reject the prayer of this earth’s exiles! O Lord, Goodness beyond our understanding, Who are acquainted with our misery through and through, and know that by our own power we cannot ascend to You, we implore You: anticipate us with Your grace and keep on increasing Your mercy in us, that we may faithfully do Your holy will all through our life and at death’s hour. Let the omnipotence of Your mercy shield us from the darts of our salvation’s enemies, that we may with confidence, as Your children, await Your [Son’s] final coming — that day known to You alone. And we expect to obtain everything promised us by Jesus in spite of all our wretchedness. For Jesus is our Hope: through His merciful Heart, as through an open gate, we pass through to heaven (Diary, 1570).

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

“Hoje traze-Me as almas tíbias e mergulha-as no abismo da Minha misericórdia. Estas almas ferem o Meu Coração da maneira mais dolorosa. A Minha alma sentiu a mais terrível repugnância no Jardim das Oliveiras por causa das almas tíbias. Foram elas a razão por que exclamei: ‘Pai, afasta de Mim este cálice, se for da Tua vontade.’ Para elas, a última esperança de salvação é recorrer à Minha misericórdia.”

Ó Jesus compassivíssimo, Vós sois a própria Compaixão. Trago as almas tíbias para a morada do Vosso Compassivíssimo Coração. Que neste fogo do Vosso amor puro se inflamem novamente estas almas mornas, que, como cadáveres, Vos causaram tão profunda repugnância. Ó Jesus compassivíssimo, exercei a onipotência da Vossa misericórdia e atraí-as ao próprio ardor do Vosso amor, e concedei-lhes o dom do santo amor, pois nada é impossível ao Vosso poder.

Eterno Pai, olhai com misericórdia para as almas tíbias que, no entanto, estão envolvidas no Compassivíssimo Coração de Jesus. Pai de Misericórdia, eu Vos suplico pela amarga Paixão do Vosso Filho e pela Sua agonia de três horas na Cruz: que também elas glorifiquem o abismo da Vossa misericórdia. Amém.

Reze o Terço da Divina Misericórdia (pode usar um terço comum):

Faça o Sinal da Cruz.

Reze as orações iniciais (opcionais)

Oração de Santa Faustina pelos pecadores

Ó Jesus, Verdade eterna, nossa Vida, eu Vos invoco e imploro a Vossa misericórdia pelos pobres pecadores. Ó dulcíssimo Coração do meu Senhor, cheio de piedade e de insondável misericórdia, eu Vos suplico pelos pobres pecadores. Ó Sacratíssimo Coração, Fonte de Misericórdia, de onde jorram raios de graças inconcebíveis sobre todo o gênero humano, eu Vos peço luz para os pobres pecadores. Ó Jesus, lembrai-Vos da Vossa própria amarga Paixão e não permitais a perda das almas resgatadas a tão alto preço do Vosso preciosíssimo Sangue. Ó Jesus, quando considero o grande preço do Vosso Sangue, alegro-me com a sua imensidade, pois uma só gota teria bastado para a salvação de todos os pecadores. Embora o pecado seja um abismo de maldade e de ingratidão, o preço pago por nós jamais poderá ser igualado. Por isso, que toda alma confie na Paixão do Senhor e ponha a sua esperança na Sua misericórdia. Deus não negará a Sua misericórdia a ninguém. O céu e a terra podem mudar, mas a misericórdia de Deus jamais se esgotará. Oh, que imensa alegria arde no meu coração quando contemplo a Vossa incompreensível bondade, ó Jesus! Desejo levar todos os pecadores aos Vossos pés, para que glorifiquem a Vossa misericórdia pelos séculos sem fim (Diário de Santa Maria Faustina Kowalska, 72).

Expirastes, Jesus, mas a fonte de vida jorrou para as almas, e o oceano de misericórdia se abriu para o mundo inteiro. Ó Fonte de Vida, insondável Misericórdia Divina, envolvei o mundo inteiro e derramai-Vos sobre nós.

(Repita três vezes)
 Ó Sangue e Água que jorrastes do Coração de Jesus como fonte de misericórdia para nós, eu confio em Vós!

Depois, nas primeiras contas acima do crucifixo, reze
 um Pai-Nosso,
 uma Ave-Maria e
 o Credo

Creio em Deus Pai todo-poderoso,
 criador do céu e da terra.
 E em Jesus Cristo, seu único Filho, nosso Senhor,
 que foi concebido pelo poder do Espírito Santo,
 nasceu da Virgem Maria,
 padeceu sob Pôncio Pilatos,
 foi crucificado, morto e sepultado,
 desceu à mansão dos mortos,
 ressuscitou ao terceiro dia,
 subiu aos céus,
 está sentado à direita de Deus Pai todo-poderoso,
 donde há de vir a julgar os vivos e os mortos.
 Creio no Espírito Santo,
 na santa Igreja católica,
 na comunhão dos santos,
 na remissão dos pecados,
 na ressurreição da carne,
 na vida eterna.
 Amém.

Usando as contas do terço, nas contas do Pai-Nosso diga:
 Eterno Pai, eu Vos ofereço o Corpo e o Sangue, a Alma e a Divindade do Vosso diletíssimo Filho, Nosso Senhor Jesus Cristo, em expiação dos nossos pecados e dos do mundo inteiro.

Depois de cada oração do Eterno Pai, nas dez contas da Ave-Maria diga:
 Pela Sua dolorosa Paixão, tende misericórdia de nós e do mundo inteiro.

(Repita os passos 4 e 5 nas cinco dezenas.)

Conclua com a oração Deus Santo, repetida três vezes:
 Deus Santo, Deus Forte, Deus Imortal, tende piedade de nós e do mundo inteiro.

Reze as orações finais (opcionais)
 Ó Deus eterno, em quem a misericórdia é infinita e o tesouro da compaixão inesgotável, olhai benigno para nós e aumentai em nós a Vossa misericórdia, para que nos momentos difíceis não desesperemos nem desanimemos, mas com grande confiança nos submetamos à Vossa santa vontade, que é o próprio Amor e a própria Misericórdia.

Ó Deus de grande misericórdia, Bondade infinita, hoje toda a humanidade clama do abismo da sua miséria à Vossa misericórdia, à Vossa compaixão, ó Deus; e é com a voz poderosa da sua miséria que clama. Deus clemente, não rejeiteis a oração dos exilados desta terra! Ó Senhor, Bondade que ultrapassa a nossa compreensão, que conheceis a fundo a nossa miséria e sabeis que pelas nossas próprias forças não podemos subir até Vós, nós Vos imploramos: preveni-nos com a Vossa graça e aumentai continuamente em nós a Vossa misericórdia, para que cumpramos fielmente a Vossa santa vontade durante toda a vida e na hora da morte. Que a onipotência da Vossa misericórdia nos proteja dos dardos dos inimigos da nossa salvação, para que esperemos com confiança, como Vossos filhos, a Vossa [do Vosso Filho] vinda final, aquele dia conhecido só por Vós. E esperamos obter tudo o que Jesus nos prometeu, apesar de toda a nossa miséria. Pois Jesus é a nossa Esperança: pelo Seu Coração misericordioso, como por uma porta aberta, passamos para o céu (Diário, 1570).

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/divine-mercy-teaser.png""",
        tags = listOf("blessings", "love", "comfort", "family", "peace")
    )
}