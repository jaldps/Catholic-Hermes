package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData203 {
    val novena: Novena = Novena(
        id = """novena-al-angel-de-la-guarda""",
        title = LocalizedText("""Novena al Ángel de la Guarda""", """Guardian Angel Novena""", """Novena ao Anjo da Guarda"""),
        patron = LocalizedText("""Santo patrón de toda persona""", """Patron saint of every person""", """Novena ao Anjo da Guarda"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Dios da a cada persona un Ángel de la Guarda, que vela por ella desde su concepción hasta su muerte. Reza esta novena para invocar la ayuda de tu Ángel de la Guarda.""", """God gives every person a Guardian Angel, who watches over them from their conception until death. Pray this novena to invoke the aid of your Guardian Angel.""", """Deus dá a cada pessoa um Anjo da Guarda, que vela por ela desde a concepção até a morte. Reze esta novena para invocar a ajuda do seu Anjo da Guarda."""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Oh poderosísimo ángel, tú que eres mi custodio, te suplico, por el aborrecimiento soberano que tienes al pecado, porque ofende a Dios, a quien amas con amor puro y perfecto, que me obtengas un dolor sincero y duradero de mis pecados y un odio implacable a todo pecado, de cualquier clase que sea, para que nunca más ofenda a mi Dios, hasta el último instante de mi vida.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

O most powerful angel, thou who art my guardian, I beseech thee, by the sovereign hatred thou hast for sin, because it offends God, Whom you love with a pure and perfect love, obtain for me a sincere and lasting sorrow for my sins, and an implacable hatred against all sin, of what kind soever, so that I may never more offend my God, till the last moment of my life.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Ó poderosíssimo anjo, vós que sois o meu guardião, eu vos suplico, pelo ódio soberano que tendes ao pecado, porque ofende a Deus, a quem amais com amor puro e perfeito, alcançai-me uma dor sincera e duradoura dos meus pecados e um ódio implacável a todo pecado, de qualquer espécie, para que eu nunca mais ofenda o meu Deus até o último instante da minha vida.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Oh poderosísimo ángel, tú que eres mi custodio, te suplico, por el aborrecimiento soberano que tienes al pecado, porque ofende a Dios, a quien amas con amor puro y perfecto, que me obtengas un dolor sincero y duradero de mis pecados y un odio implacable a todo pecado, de cualquier clase que sea, para que nunca más ofenda a mi Dios, hasta el último instante de mi vida.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

O most powerful angel, thou who art my guardian, I beseech thee, by the sovereign hatred thou hast for sin, because it offends God, Whom you love with a pure and perfect love, obtain for me a sincere and lasting sorrow for my sins, and an implacable hatred against all sin, of what kind soever, so that I may never more offend my God, till the last moment of my life.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Ó poderosíssimo anjo, vós que sois o meu guardião, eu vos suplico, pelo ódio soberano que tendes ao pecado, porque ofende a Deus, a quem amais com amor puro e perfeito, alcançai-me uma dor sincera e duradoura dos meus pecados e um ódio implacável a todo pecado, de qualquer espécie, para que eu nunca mais ofenda o meu Deus até o último instante da minha vida.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Poderosísimo espíritu, mi ángel custodio, te ruego, por aquella suprema felicidad de que gozas al ver a Dios sin velo, aun mientras velas por mí, que me obtengas la gracia de caminar sin cesar en la presencia de Dios, para que viva como un cristiano perfecto hasta mi último aliento.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most mighty spirit, my angel-guardian, I pray thee, by that supreme felicity thou dost enjoy in seeing God without a veil, even whilst watching over me, obtain for me grace to walk without ceasing in the presence of God, so that I may live as a perfect Christian till my last breath.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Poderosíssimo espírito, meu anjo da guarda, eu vos peço, por aquela suprema felicidade de que gozais vendo Deus sem véu, mesmo enquanto velais por mim, alcançai-me a graça de caminhar sem cessar na presença de Deus, para que eu viva como um cristão perfeito até o meu último suspiro.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Poderosísimo espíritu, mi ángel custodio, te ruego, por aquella suprema felicidad de que gozas al ver a Dios sin velo, aun mientras velas por mí, que me obtengas la gracia de caminar sin cesar en la presencia de Dios, para que viva como un cristiano perfecto hasta mi último aliento.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most mighty spirit, my angel-guardian, I pray thee, by that supreme felicity thou dost enjoy in seeing God without a veil, even whilst watching over me, obtain for me grace to walk without ceasing in the presence of God, so that I may live as a perfect Christian till my last breath.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Poderosíssimo espírito, meu anjo da guarda, eu vos peço, por aquela suprema felicidade de que gozais vendo Deus sem véu, mesmo enquanto velais por mim, alcançai-me a graça de caminhar sem cessar na presença de Deus, para que eu viva como um cristão perfeito até o meu último suspiro.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Perfectísimo ejecutor de la Voluntad de Dios, mi fiel custodio, te ruego, por el celo vigilante y amoroso con que cumples el oficio de mi custodia que Dios te ha confiado, que me obtengas la gracia de desear siempre conocer y cumplir perfectamente, hasta el último instante de mi vida, todo lo que Dios me pide.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most perfect executor of the Will of God, my faithful guardian, I pray thee, by the vigilant and loving eagerness with which thou fulfillest the duty of my guardianship which God has confided to thee, obtain for me the grace of being always anxious to know and accomplish perfectly to the last moment of my life, everything that God asks of me.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Perfeitíssimo executor da vontade de Deus, meu fiel guardião, eu vos peço, pelo zelo vigilante e amoroso com que cumpris o ofício da minha guarda que Deus vos confiou, alcançai-me a graça de desejar sempre conhecer e cumprir perfeitamente, até o último instante da minha vida, tudo o que Deus me pede.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Perfectísimo ejecutor de la Voluntad de Dios, mi fiel custodio, te ruego, por el celo vigilante y amoroso con que cumples el oficio de mi custodia que Dios te ha confiado, que me obtengas la gracia de desear siempre conocer y cumplir perfectamente, hasta el último instante de mi vida, todo lo que Dios me pide.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most perfect executor of the Will of God, my faithful guardian, I pray thee, by the vigilant and loving eagerness with which thou fulfillest the duty of my guardianship which God has confided to thee, obtain for me the grace of being always anxious to know and accomplish perfectly to the last moment of my life, everything that God asks of me.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Perfeitíssimo executor da vontade de Deus, meu fiel guardião, eu vos peço, pelo zelo vigilante e amoroso com que cumpris o ofício da minha guarda que Deus vos confiou, alcançai-me a graça de desejar sempre conhecer e cumprir perfeitamente, até o último instante da minha vida, tudo o que Deus me pede.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Mi celosísimo protector y querido ángel custodio, te ruego, por el encargo que Dios te ha dado de guardarme en todos mis caminos, como una madre lleva en brazos a su hijo amado, que apartes de mí toda ocasión de pecado y me preserves de todos los peligros que podrían hacerme ofender a Dios; y para ello reprime las tentaciones del enemigo infernal y mis propias pasiones, para que, vencidos los enemigos de mi salvación, camine con facilidad, por la gracia de Dios, por el camino de Sus mandamientos hasta la muerte.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

My most zealous protector and dear angel guardian, I pray, thee, by the charge which God hath given thee to guard me in all my ways, as a mother carries in her arms her beloved child, avert from me all occasions of sin, and preserve me from all perils which could make me offend God, and for that, repress the temptations of the infernal enemy and my own passions, so that having conquered the enemies of my salvation, I may walk with ease, by the grace of God, in the way of His commandments, till I die.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Meu zelosíssimo protetor e querido anjo da guarda, eu vos peço, pelo encargo que Deus vos deu de me guardar em todos os meus caminhos, como uma mãe carrega nos braços o seu filho amado, afastai de mim todas as ocasiões de pecado e preservai-me de todos os perigos que poderiam me fazer ofender a Deus; e para isso reprimi as tentações do inimigo infernal e as minhas próprias paixões, para que, vencidos os inimigos da minha salvação, eu caminhe com facilidade, pela graça de Deus, no caminho dos Seus mandamentos até a morte.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Mi celosísimo protector y querido ángel custodio, te ruego, por el encargo que Dios te ha dado de guardarme en todos mis caminos, como una madre lleva en brazos a su hijo amado, que apartes de mí toda ocasión de pecado y me preserves de todos los peligros que podrían hacerme ofender a Dios; y para ello reprime las tentaciones del enemigo infernal y mis propias pasiones, para que, vencidos los enemigos de mi salvación, camine con facilidad, por la gracia de Dios, por el camino de Sus mandamientos hasta la muerte.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

My most zealous protector and dear angel guardian, I pray, thee, by the charge which God hath given thee to guard me in all my ways, as a mother carries in her arms her beloved child, avert from me all occasions of sin, and preserve me from all perils which could make me offend God, and for that, repress the temptations of the infernal enemy and my own passions, so that having conquered the enemies of my salvation, I may walk with ease, by the grace of God, in the way of His commandments, till I die.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Meu zelosíssimo protetor e querido anjo da guarda, eu vos peço, pelo encargo que Deus vos deu de me guardar em todos os meus caminhos, como uma mãe carrega nos braços o seu filho amado, afastai de mim todas as ocasiões de pecado e preservai-me de todos os perigos que poderiam me fazer ofender a Deus; e para isso reprimi as tentações do inimigo infernal e as minhas próprias paixões, para que, vencidos os inimigos da minha salvação, eu caminhe com facilidade, pela graça de Deus, no caminho dos Seus mandamentos até a morte.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Fidelísimo guía, mi ángel custodio, te ruego, por la misión que Dios te ha confiado de conducirme por el camino del cielo, que me obtengas la gracia de seguir fiel y constantemente la luz que me has dado sobre el mal que debo evitar y el bien que debo hacer, y no dejes de animarme a la práctica de toda virtud hasta el último instante de mi vida.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most faithful guide, my angel-guardian, I pray thee, by the mission which God has confided to thee to lead me on the road to heaven, obtain, for me grace to follow faithfully and constantly the light which thou hast shown me, of the evil which I must avoid, and of the good which I must do, and do not cease to excite me to the practice of all virtue, till the last moment of my life.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Fidelíssimo guia, meu anjo da guarda, eu vos peço, pela missão que Deus vos confiou de me conduzir no caminho do céu, alcançai-me a graça de seguir fiel e constantemente a luz que me mostrastes sobre o mal que devo evitar e o bem que devo fazer, e não deixeis de me estimular à prática de toda virtude até o último instante da minha vida.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Fidelísimo guía, mi ángel custodio, te ruego, por la misión que Dios te ha confiado de conducirme por el camino del cielo, que me obtengas la gracia de seguir fiel y constantemente la luz que me has dado sobre el mal que debo evitar y el bien que debo hacer, y no dejes de animarme a la práctica de toda virtud hasta el último instante de mi vida.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most faithful guide, my angel-guardian, I pray thee, by the mission which God has confided to thee to lead me on the road to heaven, obtain, for me grace to follow faithfully and constantly the light which thou hast shown me, of the evil which I must avoid, and of the good which I must do, and do not cease to excite me to the practice of all virtue, till the last moment of my life.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Fidelíssimo guia, meu anjo da guarda, eu vos peço, pela missão que Deus vos confiou de me conduzir no caminho do céu, alcançai-me a graça de seguir fiel e constantemente a luz que me mostrastes sobre o mal que devo evitar e o bem que devo fazer, e não deixeis de me estimular à prática de toda virtude até o último instante da minha vida.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Queridísimo amigo, mi buen ángel, te ruego, por el gran amor que me tienes, tú que amas a Dios tan ardientemente y que ves cuánto me amó y me sigue amando, que me obtengas el consuelo de rezar siempre, y de rezar bien, en mis dificultades, para que alcance Su misericordia hasta el último instante de mi existencia.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Dearest friend, my good angel, I pray thee, by the great love thou hast for me, thou who lovest God so ardently, and who seest how much He loved me and still loves me, obtain for me the consolation of always praying and praying well in my troubles, so that I may obtain His mercy, till the last moment of my existence.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Queridíssimo amigo, meu bom anjo, eu vos peço, pelo grande amor que me tendes, vós que amais tão ardentemente a Deus e vedes quanto Ele me amou e ainda me ama, alcançai-me o consolo de rezar sempre, e de rezar bem, nas minhas dificuldades, para que eu alcance a Sua misericórdia até o último instante da minha existência.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Queridísimo amigo, mi buen ángel, te ruego, por el gran amor que me tienes, tú que amas a Dios tan ardientemente y que ves cuánto me amó y me sigue amando, que me obtengas el consuelo de rezar siempre, y de rezar bien, en mis dificultades, para que alcance Su misericordia hasta el último instante de mi existencia.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Dearest friend, my good angel, I pray thee, by the great love thou hast for me, thou who lovest God so ardently, and who seest how much He loved me and still loves me, obtain for me the consolation of always praying and praying well in my troubles, so that I may obtain His mercy, till the last moment of my existence.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Queridíssimo amigo, meu bom anjo, eu vos peço, pelo grande amor que me tendes, vós que amais tão ardentemente a Deus e vedes quanto Ele me amou e ainda me ama, alcançai-me o consolo de rezar sempre, e de rezar bem, nas minhas dificuldades, para que eu alcance a Sua misericórdia até o último instante da minha existência.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Poderosísimo intercesor, mi fiel custodio, te ruego, por el celo que Dios te ha dado por la salvación eterna de mi alma, que me obtengas la gracia de tener también un celo ardiente y prudente por la salvación de mi prójimo, para que merezca la salvación eterna.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most powerful intercessor, my faithful guardian, I pray thee, by the zeal which God hath given thee for the eternal salvation of my soul, obtain for me the grace of having also an ardent and prudent zeal for the salvation of my neighbour, so that I may merit eternal salvation.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Poderosíssimo intercessor, meu fiel guardião, eu vos peço, pelo zelo que Deus vos deu pela salvação eterna da minha alma, alcançai-me a graça de ter também um zelo ardente e prudente pela salvação do meu próximo, para que eu mereça a salvação eterna.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Poderosísimo intercesor, mi fiel custodio, te ruego, por el celo que Dios te ha dado por la salvación eterna de mi alma, que me obtengas la gracia de tener también un celo ardiente y prudente por la salvación de mi prójimo, para que merezca la salvación eterna.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

Most powerful intercessor, my faithful guardian, I pray thee, by the zeal which God hath given thee for the eternal salvation of my soul, obtain for me the grace of having also an ardent and prudent zeal for the salvation of my neighbour, so that I may merit eternal salvation.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Poderosíssimo intercessor, meu fiel guardião, eu vos peço, pelo zelo que Deus vos deu pela salvação eterna da minha alma, alcançai-me a graça de ter também um zelo ardente e prudente pela salvação do meu próximo, para que eu mereça a salvação eterna.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

¡Oh purísimo espíritu, querido ángel custodio! Te ruego, por el amor ardiente que tienes a la Inmaculada Virgen Madre de Dios, Reina de los Ángeles, que me obtengas una gran pureza de alma y de cuerpo, para que a mi muerte sea hallado digno de unirme a ti en el Paraíso.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

O! most pure spirit, dear angel-guardian, I pray thee, by the burning love thou hast for the Immaculate Virgin-Mother of God, the Queen of Angels, to obtain for me great purity of soul and body, that at my death I may be found worthy to associate with thee in Paradise.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Ó puríssimo espírito, querido anjo da guarda, eu vos peço, pelo amor ardente que tendes à Imaculada Virgem Mãe de Deus, Rainha dos Anjos, que me alcanceis uma grande pureza de alma e de corpo, para que na minha morte eu seja achado digno de me unir a vós no Paraíso.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

¡Oh purísimo espíritu, querido ángel custodio! Te ruego, por el amor ardiente que tienes a la Inmaculada Virgen Madre de Dios, Reina de los Ángeles, que me obtengas una gran pureza de alma y de cuerpo, para que a mi muerte sea hallado digno de unirme a ti en el Paraíso.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

O! most pure spirit, dear angel-guardian, I pray thee, by the burning love thou hast for the Immaculate Virgin-Mother of God, the Queen of Angels, to obtain for me great purity of soul and body, that at my death I may be found worthy to associate with thee in Paradise.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Ó puríssimo espírito, querido anjo da guarda, eu vos peço, pelo amor ardente que tendes à Imaculada Virgem Mãe de Deus, Rainha dos Anjos, que me alcanceis uma grande pureza de alma e de corpo, para que na minha morte eu seja achado digno de me unir a vós no Paraíso.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

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

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Oh dulcísimo ángel, mi fiel y amoroso custodio, ¿cómo podré agradecerte lo suficiente el amor ardiente y la fiel vigilancia con que siempre has velado, y velarás, por mí, hasta que me hayas llevado seguro a la dulce presencia de Dios? ¡Ah! Ruega por mí, para que te dé gracias con mi obediencia a tus santas inspiraciones, de modo que, amándote cada día más, merezca estar contigo por toda la eternidad.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

O sweetest angel, my faithful and loving guardian, how can I thank thee sufficiently for the ardent love and faithful vigilance with which thou hast ever watched, and will watch over me, till thou hast led me safely into the sweet presence of God. Ah! pray for me, that I may thank thee by my obedience to thy holy inspirations, so that loving thee daily more and more, I may merit to be with thee for all eternity.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Ó dulcíssimo anjo, meu fiel e amoroso guardião, como poderei agradecer-vos suficientemente o amor ardente e a fiel vigilância com que sempre velastes, e velareis, por mim, até me terdes conduzido em segurança à doce presença de Deus? Ah! Rogai por mim, para que eu vos agradeça pela minha obediência às vossas santas inspirações, de modo que, amando-vos cada dia mais, eu mereça estar convosco por toda a eternidade.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh santo ángel, a quien Dios, por efecto de Su bondad y de Su tierno cuidado por mi bien, ha encargado de guiar mi conducta, y que me asistes en todas mis necesidades y me consuelas en todas mis aflicciones, que me sostienes cuando me desanimo y continuamente me obtienes nuevos favores.

Te doy profundas gracias, y te suplico encarecidamente, oh amabilísimo protector, que continúes tu caritativo cuidado y tu defensa de mí contra los malignos ataques de todos mis enemigos.

Te pido especialmente que me apartes de toda ocasión de pecado.

Obtenme la gracia de escuchar atentamente tus santas inspiraciones y de ponerlas fielmente en práctica.

En particular, te suplico que me obtengas el favor que pido en esta novena

(menciona tu petición aquí…)

Protégeme en todas las tentaciones y pruebas de esta vida, pero sobre todo en la hora de mi muerte, y no me dejes hasta que me hayas conducido a la presencia de Dios, en las moradas de la felicidad eterna.

Amén.

Oh dulcísimo ángel, mi fiel y amoroso custodio, ¿cómo podré agradecerte lo suficiente el amor ardiente y la fiel vigilancia con que siempre has velado, y velarás, por mí, hasta que me hayas llevado seguro a la dulce presencia de Dios? ¡Ah! Ruega por mí, para que te dé gracias con mi obediencia a tus santas inspiraciones, de modo que, amándote cada día más, merezca estar contigo por toda la eternidad.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Ángel de Dios, que eres mi custodio, pues la bondad divina me ha encomendado a ti, ilumíname, guárdame, rígeme y gobiérname.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O holy angel, whom God, by the effect of His goodness and His tender regard for my welfare, has charged with the care of my conduct, and who assists me in all my wants and comforts me in all my afflictions, who supports me when I am discouraged and continually obtains for me new favors.

I return thee profound thanks, and I earnestly beseech thee, O most amiable protector, to continue thy charitable care and defense of me against the malignant attacks of all my enemies.

I pray especially that you keep me away from all occasions of sin.

Obtain for me the grace of listening attentively to thy holy inspirations and of faithfully putting them into practice.

In particular, I implore thee to obtain for me the favor which I ask for by this novena

(mention request here…)

Protect me in all the temptations and trials of this life, but more especially at the hour of my death, and do not leave me until thou hast conducted me into the presence of God in the mansions of everlasting happiness.

Amen.

O sweetest angel, my faithful and loving guardian, how can I thank thee sufficiently for the ardent love and faithful vigilance with which thou hast ever watched, and will watch over me, till thou hast led me safely into the sweet presence of God. Ah! pray for me, that I may thank thee by my obedience to thy holy inspirations, so that loving thee daily more and more, I may merit to be with thee for all eternity.

Our Father
 Hail Mary
 Glory Be

Angel of God, my guardian dear, To whom God’s love commits me here, Ever this day be at my side, To light and guard, rule and guide.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó santo anjo, a quem Deus, por efeito da Sua bondade e do Seu terno cuidado pelo meu bem, confiou o cuidado da minha conduta, e que me assistis em todas as minhas necessidades e me consolais em todas as minhas aflições, que me sustentais quando estou desanimado e continuamente me alcançais novos favores.

Eu vos dou profundas graças e vos suplico ardentemente, ó amabilíssimo protetor, que continueis o vosso caridoso cuidado e a vossa defesa de mim contra os ataques malignos de todos os meus inimigos.

Peço-vos especialmente que me afasteis de todas as ocasiões de pecado.

Alcançai-me a graça de ouvir atentamente as vossas santas inspirações e de pô-las fielmente em prática.

Em particular, suplico-vos que me alcanceis a graça que peço nesta novena

(mencione o pedido aqui…)

Protegei-me em todas as tentações e provações desta vida, mas sobretudo na hora da minha morte, e não me deixeis até me terdes conduzido à presença de Deus, nas moradas da felicidade eterna.

Amém.

Ó dulcíssimo anjo, meu fiel e amoroso guardião, como poderei agradecer-vos suficientemente o amor ardente e a fiel vigilância com que sempre velastes, e velareis, por mim, até me terdes conduzido em segurança à doce presença de Deus? Ah! Rogai por mim, para que eu vos agradeça pela minha obediência às vossas santas inspirações, de modo que, amando-vos cada dia mais, eu mereça estar convosco por toda a eternidade.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Santo Anjo do Senhor, meu zeloso guardador, se a ti me confiou a piedade divina, sempre me rege, me guarda, me governa e me ilumina.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/guardian-angel-novena.png""",
        tags = listOf("love", "comfort")
    )
}