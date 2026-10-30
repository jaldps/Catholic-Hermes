package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData110 {
    val novena: Novena = Novena(
        id = """novena-a-san-luis-maria-de-montfort""",
        title = LocalizedText("""Novena a San Luis María Grignion de Montfort""", """St. Louis de Montfort Novena""", """Novena a São Luís Maria Grignion de Montfort"""),
        patron = LocalizedText("""Santo patrón de la devoción a Nuestra Señora y varias escuelas para jóvenes""", """Patron saint of Our Lady and various youth schools""", """Novena a São Luís Maria Grignion de Montfort"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""San Luis María de Montfort fue un sacerdote francés que predicó con elocuencia sobre la devoción a Nuestra Señora y el rezo del Rosario. Reza esta novena por una mayor devoción a Nuestra Señora y por los predicadores.""", """St Louis de Montfort was a French priest who eloquently preached on devotion to Our Lady and praying the Rosary. Pray this novena for increased devotion to Our Lady and for preachers.""", """Santo padroeiro de Nossa Senhora e várias escolas para jovens"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

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

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Gran apóstol e hijo de Nuestra Señora, San Luis María de Montfort, tu único deseo fue incendiar el mundo con el amor de Jesús por medio de María.

Te imploramos que nos alcances una devoción perfecta y constante a María, para que participemos de la fe, la esperanza y la caridad de Nuestra Señora y recibamos el favor que te pedimos en esta novena

(menciona tu petición aquí…)

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Luis María de Montfort, ruega por nosotros. (3 veces)

Rezo a Jesús con tus palabras, querido San Luis:

Oh amabilísimo Jesús, permíteme expresarte mi gratitud por la gracia que me has concedido al entregarme a tu santa Madre mediante la devoción de la santa esclavitud, para que ella sea mi abogada ante tu majestad y mi apoyo en mi extrema miseria.

¡Ay, Señor! Soy tan miserable que sin esta querida Madre ciertamente me perdería.

Sí, María me es necesaria ante ti y en todas partes, para que aplaque tu justa ira, porque te he ofendido tantas veces; para que me libre del castigo eterno de tu justicia, que merezco; para que te contemple, te hable, te ruegue, se acerque a ti y te agrade; para que me ayude a salvar mi alma y las almas de los demás; en una palabra, María me es necesaria para que haga siempre tu santa voluntad y busque en todo tu mayor gloria.

¡Ah, si pudiera proclamar por todo el mundo la misericordia que has tenido conmigo!

¡Si todos supieran que ya estaría condenado de no ser por María!

¡Si pudiera darte dignas gracias por tan gran beneficio!

María está en mí.

¡Oh, qué tesoro!

¡Oh, qué consuelo!

¿Y no seré enteramente suyo?

¡Oh, qué ingratitud!

Mi querido Salvador, envíame antes la muerte que semejante desgracia, pues prefiero morir a vivir sin pertenecer enteramente a María.

Con San Juan Evangelista al pie de la Cruz, la he tomado mil veces por mía y otras tantas me he entregado a ella; pero si todavía no lo he hecho como tú, querido Jesús, lo deseas, renuevo ahora esta ofrenda como tú quieres que la renueve.

Y si ves en mi alma o en mi cuerpo algo que no pertenezca a esta augusta princesa, te ruego que lo tomes y lo arrojes lejos de mí, porque todo lo que en mí no pertenece a María es indigno de ti.

Oh Espíritu Santo, concédeme todas estas gracias.

Planta en mi alma el Árbol de la verdadera Vida, que es María; cultívalo y cuídalo para que crezca, florezca y dé fruto de vida en abundancia.

Oh Espíritu Santo, dame una gran devoción a María, tu fiel esposa; dame una gran confianza en su corazón maternal y un refugio permanente en su misericordia, para que por ella formes verdaderamente en mí a Jesucristo, grande y poderoso, hasta la plenitud de su edad perfecta. Amén.

Oh Dios, que por el poder del Espíritu Santo hiciste de San Luis María de Montfort un ardiente apóstol de Cristo crucificado y un hijo fiel de la Virgen María, concédenos que, por su ejemplo e intercesión, seamos renovados en el espíritu de nuestro bautismo y permanezcamos siempre fieles a nuestro Señor Jesucristo, que vive y reina contigo y el Espíritu Santo, un solo Dios, por los siglos de los siglos. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Great Apostle and son of Our Lady, St. Louis de Montfort, your only desire was to set aflame the world with the love of Jesus through Mary.

We implore you to obtain for us a perfect and constant devotion to Mary that we may participate in the faith, hope, and charity of Our Lady and that we may receive the favor we ask of you in this novena

(mention request here…)

Our Father

Hail Mary

Glory Be

St. Louis de Montfort… pray for us (x3)

I pray to Jesus in your words, dear St Louis:

O most loving Jesus, deign to let me pour forth my gratitude before Thee, for the grace Thou hast bestowed upon me in giving me to Thy holy Mother through the devotion of Holy Bondage, that she may be my advocate in the presence of Thy majesty and my support in my extreme misery.

Alas, O Lord! I am so wretched that without this dear Mother I should be certainly lost.

Yes, Mary is necessary for me at Thy side and everywhere that she may appease Thy just wrath, because I have so often offended Thee; that she may save me from the eternal punishment of Thy justice, which I deserve; that she may contemplate Thee, speak to Thee, pray to Thee, approach Thee and please Thee; that she may help me to save my soul and the souls of others; in short, Mary is necessary for me that I may always do Thy holy will and seek Thy greater glory in all things.

Ah, would that I could proclaim throughout the whole world the mercy that Thou hast shown to me!

Would that everyone might know I should be already damned, were it not for Mary!

Would that I might offer worthy thanksgiving for so great a blessing!

Mary is in me.

Oh, what a treasure!

Oh, what a consolation!

And shall I not be entirely hers?

Oh, what ingratitude!

My dear Saviour, send me death rather than such a calamity, for I would rather die than live without belonging entirely to Mary.

With St. John the Evangelist at the foot of the Cross, I have taken her a thousand times for my own and as many times have given myself to her; but if I have not yet done it as Thou, dear Jesus, dost wish, I now renew this offering as Thou dost desire me to renew it.

And if Thou seest in my soul or my body anything that does not belong to this august princess, I pray Thee to take it and cast it far from me, for whatever in me does not belong to Mary is unworthy of Thee.

O Holy Spirit, grant me all these graces.

Plant in my soul the Tree of true Life, which is Mary; cultivate it and tend it so that it may grow and blossom and bring forth the fruit of life in abundance.

O Holy Spirit, give me great devotion to Mary, Thy faithful spouse; give me great confidence in her maternal heart and an abiding refuge in her mercy, so that by her Thou mayest truly form in me Jesus Christ, great and mighty, unto the fullness of His perfect age. Amen.

O God, Who in the power of the Holy Spirit hast made St Louis de Montfort an ardent apostle of Christ Crucified and a faithful son of the Virgin Mary, grant that through his example and intercession we may be renewed in the spirit of our baptism and be always faithful to Our Lord Jesus Christ, Who liveth and reigneth with Thee and the Holy Spirit, one God, forever and ever. Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Grande Apóstolo e filho de Nossa Senhora, São Luís de Montfort, o vosso único desejo era incendiar o mundo com o amor de Jesus por Maria.

Nós vos imploramos que nos alcanceis uma devoção perfeita e constante a Maria, para que participemos da fé, da esperança e da caridade de Nossa Senhora e recebamos a graça que vos pedimos nesta novena

(mencione o pedido aqui…)

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Luís de Montfort, rogai por nós. (3 vezes)

Rezo a Jesus com as vossas palavras, querido São Luís:

Ó amabilíssimo Jesus, permiti que eu derrame diante de Vós a minha gratidão pela graça que me concedestes, entregando-me à Vossa santa Mãe pela devoção da santa Escravidão, para que ela seja a minha advogada diante da Vossa majestade e o meu amparo na minha extrema miséria.

Ai de mim, Senhor! Sou tão miserável que, sem essa querida Mãe, estaria certamente perdido.

Sim, Maria me é necessária junto de Vós e em toda parte, para aplacar a Vossa justa ira, porque Vos ofendi tantas vezes; para me livrar do castigo eterno da Vossa justiça, que mereço; para Vos contemplar, Vos falar, Vos rezar, aproximar-se de Vós e Vos agradar; para me ajudar a salvar a minha alma e a dos outros; numa palavra, Maria me é necessária para que eu faça sempre a Vossa santa vontade e procure em tudo a Vossa maior glória.

Ah, quem me dera poder proclamar pelo mundo inteiro a misericórdia que usastes para comigo!

Quem me dera que todos soubessem que eu já estaria condenado, se não fosse Maria!

Quem me dera poder oferecer dignas ações de graças por tão grande benefício!

Maria está em mim.

Oh, que tesouro!

Oh, que consolação!

E não serei eu inteiramente dela?

Oh, que ingratidão!

Meu querido Salvador, mandai-me antes a morte do que tal desgraça, pois prefiro morrer a viver sem pertencer inteiramente a Maria.

Com São João Evangelista, ao pé da Cruz, mil vezes a tomei por minha e outras tantas me entreguei a ela; mas, se ainda não o fiz como Vós, querido Jesus, desejais, renovo agora esta oferta como desejais que eu a renove.

E se vedes em minha alma ou em meu corpo alguma coisa que não pertença a esta augusta princesa, peço-Vos que a tomeis e a lanceis para longe de mim, pois tudo o que em mim não pertence a Maria é indigno de Vós.

Ó Espírito Santo, concedei-me todas essas graças.

Plantai em minha alma a Árvore da verdadeira Vida, que é Maria; cultivai-a e cuidai dela, para que cresça, floresça e produza em abundância o fruto da vida.

Ó Espírito Santo, dai-me grande devoção a Maria, Vossa fiel esposa; dai-me grande confiança em seu coração materno e um refúgio permanente em sua misericórdia, para que por ela formeis verdadeiramente em mim Jesus Cristo, grande e poderoso, até a plenitude da Sua idade perfeita. Amém.

Ó Deus, que, no poder do Espírito Santo, fizestes de São Luís de Montfort um ardoroso apóstolo de Cristo Crucificado e um filho fiel da Virgem Maria, concedei que, pelo seu exemplo e intercessão, sejamos renovados no espírito do nosso batismo e permaneçamos sempre fiéis a Nosso Senhor Jesus Cristo, que convosco vive e reina, na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-louis-de-montfort-novena.png""",
        tags = listOf("love", "comfort")
    )
}