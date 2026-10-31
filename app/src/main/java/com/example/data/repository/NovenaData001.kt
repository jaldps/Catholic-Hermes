package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData001 {
    val novena: Novena = Novena(
        id = """novena-a-la-beata-chiara-badano""",
        title = LocalizedText("""Novena a la Beata Chiara Luce Badano""", """Blessed Chiara Badano Novena""", """Novena à Beata Chiara Luce Badano"""),
        patron = LocalizedText("""Santo patrón de los jóvenes, los enfermos de cáncer, los enfermos terminales y quienes los cuidan, y todos los que deben aceptar lo que no eligieron""", """Patron saint of young people, those suffering from cancer, the terminally ill and those who nurse them, and all who must accept what they did not choose""", """Novena à Beata Chiara Luce Badano"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a la Beata Chiara Badano, llamada Chiara Luce (1971-1990), una chica italiana normal que amaba el tenis, el canto y a sus amigos, hasta que a los diecisiete años un dolor en el hombro resultó ser un cáncer de huesos. Rechazó la morfina para mantenerse lúcida y ofrecer su sufrimiento, y afrontó cada etapa de la enfermedad con las mismas palabras: Si tú lo quieres, Jesús, yo también lo quiero. Pidió ser enterrada con un vestido de novia, porque su funeral iba a ser el día de su boda, y le dijo a su madre: Sé feliz, porque yo lo soy. Murió a los dieciocho años y fue beatificada en 2010. Esta novena tiene una oración distinta para cada uno de los nueve días. Rézala por los jóvenes, por los enfermos y quienes se enfrentan al cáncer, por la esperanza en el sufrimiento y por el favor que más necesitas.""", """Blessed Chiara Badano, called Chiara Luce (1971-1990), was an ordinary Italian girl who loved tennis and singing and her friends, until at seventeen a pain in her shoulder proved to be bone cancer. She refused morphine so that she could stay lucid and offer her suffering, and she met each stage of the illness with the same words, If you want it Jesus, so do I. She asked to be buried in a wedding dress, because her funeral was to be her wedding day, and she told her mother, Be happy, because I am. She died at eighteen and was beatified in 2010. This novena has a distinct prayer for each of the nine days. Pray it for young people, for the sick and those facing cancer, for hope in suffering, and for the favor you most need.""", """Santo padroeiro de os jovens, os que sofrem de câncer, os doentes terminais e os que deles cuidam, e todos os que precisam aceitar aquilo que não escolheram"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre de inmensa bondad, te agradezco el gran don de la fe. Consciente de mis imperfecciones, te ruego que me concedas la gracia de imitar más de cerca los ejemplos de tu Hijo Jesús, siguiendo la vida de los santos.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father of immense goodness, I am grateful for the great gift of faith. Aware of my imperfections, I beg you to grant me the grace to imitate more closely the examples of your Son Jesus, in accordance with the lives of the Saints.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai de imensa bondade, eu Vos agradeço o grande dom da fé. Consciente das minhas imperfeições, peço-Vos que me concedais a graça de imitar mais de perto os exemplos do Vosso Filho Jesus, de acordo com a vida dos Santos.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre de inmensa bondad, te agradezco el gran don de la fe. Consciente de mis imperfecciones, te ruego que me concedas la gracia de imitar más de cerca los ejemplos de tu Hijo Jesús, siguiendo la vida de los santos.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father of immense goodness, I am grateful for the great gift of faith. Aware of my imperfections, I beg you to grant me the grace to imitate more closely the examples of your Son Jesus, in accordance with the lives of the Saints.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai de imensa bondade, eu Vos agradeço o grande dom da fé. Consciente das minhas imperfeições, peço-Vos que me concedais a graça de imitar mais de perto os exemplos do Vosso Filho Jesus, de acordo com a vida dos Santos.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios, Padre nuestro, fuente de todo bien, te doy gracias por amarnos inmensamente, hasta el punto de darnos el don de tu Hijo. Ayúdame, te lo ruego, a corresponderte amándote con todas mis fuerzas, obedeciendo tu voluntad y aceptando con humildad las disposiciones de tu Providencia, según el ejemplo de la Beata Chiara: “Si tú lo quieres, Jesús, yo también lo quiero”.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God our Father, source of all good, I thank you for loving us immensely to the point of giving us the gift of your Son. Help me, please, to love you back with all my strength, obeying your will and humbly accepting the provisions of your Providence, according to the example of Blessed Chiara: “If you want it Jesus, so do I”.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus, nosso Pai, fonte de todo o bem, eu Vos agradeço por nos terdes amado imensamente, a ponto de nos dardes o Vosso Filho. Ajudai-me, por favor, a corresponder ao Vosso amor com todas as minhas forças, obedecendo à Vossa vontade e aceitando humildemente as disposições da Vossa Providência, segundo o exemplo da Beata Chiara: “Se Tu queres, Jesus, eu também quero”.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios, Padre nuestro, fuente de todo bien, te doy gracias por amarnos inmensamente, hasta el punto de darnos el don de tu Hijo. Ayúdame, te lo ruego, a corresponderte amándote con todas mis fuerzas, obedeciendo tu voluntad y aceptando con humildad las disposiciones de tu Providencia, según el ejemplo de la Beata Chiara: “Si tú lo quieres, Jesús, yo también lo quiero”.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God our Father, source of all good, I thank you for loving us immensely to the point of giving us the gift of your Son. Help me, please, to love you back with all my strength, obeying your will and humbly accepting the provisions of your Providence, according to the example of Blessed Chiara: “If you want it Jesus, so do I”.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus, nosso Pai, fonte de todo o bem, eu Vos agradeço por nos terdes amado imensamente, a ponto de nos dardes o Vosso Filho. Ajudai-me, por favor, a corresponder ao Vosso amor com todas as minhas forças, obedecendo à Vossa vontade e aceitando humildemente as disposições da Vossa Providência, segundo o exemplo da Beata Chiara: “Se Tu queres, Jesus, eu também quero”.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre santo, tanto amaste a la humanidad que nos diste a tu Hijo, que con el sacrificio de su vida nos ha dado el bien supremo de la salvación. Concédeme la gracia de imitar la generosidad de su amor fraterno, dócil a su mandato: “Así como yo los he amado, ámense también ustedes los unos a los otros” (Jn 13,34). Hazme, pues, atento a las necesidades y al sufrimiento de mi prójimo, dispuesto y deseoso de escuchar, consolar y ayudar según mis posibilidades.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Holy Father, you so loved humanity that you gave us your Son, who by the sacrifice of his life has given us the greatest good of salvation. Grant me the grace to imitate the generosity of his brotherly love, docile to his request: “As I have loved you, so you must love one another” (Jn 13:34). Make me therefore attentive to the needs and suffering of my neighbours, ready and willing to listen, comfort and help according to my possibilities.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai Santo, amastes tanto a humanidade que nos destes o Vosso Filho, que pelo sacrifício da sua vida nos deu o bem maior da salvação. Concedei-me a graça de imitar a generosidade do seu amor fraterno, dócil ao seu pedido: “Como eu vos amei, assim também vós deveis amar-vos uns aos outros” (Jo 13,34). Tornai-me, pois, atento às necessidades e aos sofrimentos do meu próximo, pronto e disposto a escutar, consolar e ajudar segundo as minhas possibilidades.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre santo, tanto amaste a la humanidad que nos diste a tu Hijo, que con el sacrificio de su vida nos ha dado el bien supremo de la salvación. Concédeme la gracia de imitar la generosidad de su amor fraterno, dócil a su mandato: “Así como yo los he amado, ámense también ustedes los unos a los otros” (Jn 13,34). Hazme, pues, atento a las necesidades y al sufrimiento de mi prójimo, dispuesto y deseoso de escuchar, consolar y ayudar según mis posibilidades.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Holy Father, you so loved humanity that you gave us your Son, who by the sacrifice of his life has given us the greatest good of salvation. Grant me the grace to imitate the generosity of his brotherly love, docile to his request: “As I have loved you, so you must love one another” (Jn 13:34). Make me therefore attentive to the needs and suffering of my neighbours, ready and willing to listen, comfort and help according to my possibilities.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai Santo, amastes tanto a humanidade que nos destes o Vosso Filho, que pelo sacrifício da sua vida nos deu o bem maior da salvação. Concedei-me a graça de imitar a generosidade do seu amor fraterno, dócil ao seu pedido: “Como eu vos amei, assim também vós deveis amar-vos uns aos outros” (Jo 13,34). Tornai-me, pois, atento às necessidades e aos sofrimentos do meu próximo, pronto e disposto a escutar, consolar e ajudar segundo as minhas possibilidades.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios santo y todopoderoso, soy consciente de todas las imperfecciones que afectan a mi conocimiento y a mis capacidades, a mi voluntad y a mis actos. Por eso me encomiendo a tu bondad infinita, para que me concedas la gracia de obedecer tus mandamientos y aceptar las decisiones de tu Providencia, a imitación de la docilidad de tu Hijo Jesús: “No se haga mi voluntad, sino la tuya” (Lc 22,42).

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God, holy and almighty, I am aware of all the imperfections affecting my knowledge and my abilities, my will and my acts. I therefore commend myself to your infinite goodness so that you might grant me the grace to obey your commandments and accept the decisions of your Providence, in imitation of the docility of your Son Jesus: “Not my will, but yours be done” (Lk 22:42).

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus santo e todo-poderoso, tenho consciência de todas as imperfeições que afetam o meu conhecimento e as minhas capacidades, a minha vontade e os meus atos. Por isso me confio à Vossa bondade infinita, para que me concedais a graça de obedecer aos Vossos mandamentos e aceitar as decisões da Vossa Providência, imitando a docilidade do Vosso Filho Jesus: “Não se faça a minha vontade, mas a Vossa” (Lc 22,42).

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios santo y todopoderoso, soy consciente de todas las imperfecciones que afectan a mi conocimiento y a mis capacidades, a mi voluntad y a mis actos. Por eso me encomiendo a tu bondad infinita, para que me concedas la gracia de obedecer tus mandamientos y aceptar las decisiones de tu Providencia, a imitación de la docilidad de tu Hijo Jesús: “No se haga mi voluntad, sino la tuya” (Lc 22,42).

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God, holy and almighty, I am aware of all the imperfections affecting my knowledge and my abilities, my will and my acts. I therefore commend myself to your infinite goodness so that you might grant me the grace to obey your commandments and accept the decisions of your Providence, in imitation of the docility of your Son Jesus: “Not my will, but yours be done” (Lk 22:42).

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus santo e todo-poderoso, tenho consciência de todas as imperfeições que afetam o meu conhecimento e as minhas capacidades, a minha vontade e os meus atos. Por isso me confio à Vossa bondade infinita, para que me concedais a graça de obedecer aos Vossos mandamentos e aceitar as decisões da Vossa Providência, imitando a docilidade do Vosso Filho Jesus: “Não se faça a minha vontade, mas a Vossa” (Lc 22,42).

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre, fuente de todo bien, me siento humillado por mis infidelidades. En lugar de corresponder a tu inmenso amor, sucumbí al atractivo del egoísmo y del orgullo, a los impulsos del placer y a la presión de los malos ejemplos. Dolido por esas faltas, acudo a tu misericordia infinita para ser perdonado gracias al sacrificio de Jesús. Y como el arrepentimiento sincero exige el compromiso de la conversión, imploro tu ayuda para hacer más intensa y más ferviente la oración y la unión habitual contigo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father, source of all good, I am humbled by my infidelities. Instead of returning your immense love, I succumbed to the allure of selfishness and pride, the urges of pleasure, the pressure of bad examples. Pained by those faults, I turn to your infinite mercy to be forgiven thanks to Jesus’ sacrifice. Because sincere repentance requires the commitment of conversion, I implore your help in making prayer and habitual union with you more intense and more fervent.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai, fonte de todo o bem, sinto-me humilhado pelas minhas infidelidades. Em vez de corresponder ao Vosso imenso amor, cedi à sedução do egoísmo e do orgulho, aos impulsos do prazer, à pressão dos maus exemplos. Pesaroso por essas faltas, volto-me para a Vossa infinita misericórdia, para ser perdoado graças ao sacrifício de Jesus. Porque o arrependimento sincero exige o compromisso da conversão, imploro a Vossa ajuda para tornar mais intensa e mais fervorosa a oração e a união habitual convosco.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre, fuente de todo bien, me siento humillado por mis infidelidades. En lugar de corresponder a tu inmenso amor, sucumbí al atractivo del egoísmo y del orgullo, a los impulsos del placer y a la presión de los malos ejemplos. Dolido por esas faltas, acudo a tu misericordia infinita para ser perdonado gracias al sacrificio de Jesús. Y como el arrepentimiento sincero exige el compromiso de la conversión, imploro tu ayuda para hacer más intensa y más ferviente la oración y la unión habitual contigo.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father, source of all good, I am humbled by my infidelities. Instead of returning your immense love, I succumbed to the allure of selfishness and pride, the urges of pleasure, the pressure of bad examples. Pained by those faults, I turn to your infinite mercy to be forgiven thanks to Jesus’ sacrifice. Because sincere repentance requires the commitment of conversion, I implore your help in making prayer and habitual union with you more intense and more fervent.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai, fonte de todo o bem, sinto-me humilhado pelas minhas infidelidades. Em vez de corresponder ao Vosso imenso amor, cedi à sedução do egoísmo e do orgulho, aos impulsos do prazer, à pressão dos maus exemplos. Pesaroso por essas faltas, volto-me para a Vossa infinita misericórdia, para ser perdoado graças ao sacrifício de Jesus. Porque o arrependimento sincero exige o compromisso da conversão, imploro a Vossa ajuda para tornar mais intensa e mais fervorosa a oração e a união habitual convosco.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios de bondad paternal, tú nos exhortas a escuchar a tu Hijo, admirable ejemplo de generosidad al dedicar mucho tiempo al diálogo contigo. Es justo que yo también lo imite, para darte gracias por tu inmenso amor, meditar tu Palabra, implorar luz y apoyo para cumplir mis deberes e interceder por las necesidades de mis hermanos: “Señor, enséñanos a orar” (Lc 11,1).

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God of paternal benevolence, you exhort us to listen to your Son, admirable example of generosity in dedicating plenty of time to dialogue with you. It is right that I also imitate him in order to thank you for your immense love, to meditate on your Word, to implore for light and support to fulfill my duties and to intercede for the needs of my brothers: “Lord, teach us to pray” (Luke 11:1).

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus de paterna benevolência, Vós nos exortais a escutar o Vosso Filho, admirável exemplo de generosidade em dedicar muito tempo ao diálogo convosco. É justo que eu também O imite, para Vos agradecer o Vosso imenso amor, meditar a Vossa Palavra, implorar luz e amparo para cumprir os meus deveres e interceder pelas necessidades dos meus irmãos: “Senhor, ensina-nos a orar” (Lc 11,1).

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios de bondad paternal, tú nos exhortas a escuchar a tu Hijo, admirable ejemplo de generosidad al dedicar mucho tiempo al diálogo contigo. Es justo que yo también lo imite, para darte gracias por tu inmenso amor, meditar tu Palabra, implorar luz y apoyo para cumplir mis deberes e interceder por las necesidades de mis hermanos: “Señor, enséñanos a orar” (Lc 11,1).

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God of paternal benevolence, you exhort us to listen to your Son, admirable example of generosity in dedicating plenty of time to dialogue with you. It is right that I also imitate him in order to thank you for your immense love, to meditate on your Word, to implore for light and support to fulfill my duties and to intercede for the needs of my brothers: “Lord, teach us to pray” (Luke 11:1).

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus de paterna benevolência, Vós nos exortais a escutar o Vosso Filho, admirável exemplo de generosidade em dedicar muito tempo ao diálogo convosco. É justo que eu também O imite, para Vos agradecer o Vosso imenso amor, meditar a Vossa Palavra, implorar luz e amparo para cumprir os meus deveres e interceder pelas necessidades dos meus irmãos: “Senhor, ensina-nos a orar” (Lc 11,1).

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre de bondad infinita, te pido la gracia de aprender a dirigirme a ti a menudo a lo largo de mis días, para confiarte con espontaneidad mi estado de ánimo: pensamientos y sentimientos, intenciones y decisiones, alegrías y tristezas. Enséñame a consultarte, de vez en cuando, para recibir la luz necesaria sobre el camino que debo seguir; enséñame a reconocer con prontitud mis errores y faltas, y a darte gracias por tu amor y tus dones.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father of infinite benevolence, I beseech your grace to learn how to turn often to you in the course of my days, in order to confide spontaneously my moods to you: thoughts and feelings, intentions and decisions, joys and sorrows. Teach me to consult you, from time to time, for proper enlightenment on the way forward; teach me to promptly recognize mistakes and faults; to thank you for your love and your gifts.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai de infinita benevolência, imploro a Vossa graça para aprender a voltar-me muitas vezes para Vós no decorrer dos meus dias, a fim de Vos confiar espontaneamente os meus estados de ânimo: pensamentos e sentimentos, intenções e decisões, alegrias e tristezas. Ensinai-me a consultar-Vos, de vez em quando, para receber a devida luz sobre o caminho a seguir; ensinai-me a reconhecer prontamente os erros e as faltas; a agradecer-Vos o Vosso amor e os Vossos dons.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre de bondad infinita, te pido la gracia de aprender a dirigirme a ti a menudo a lo largo de mis días, para confiarte con espontaneidad mi estado de ánimo: pensamientos y sentimientos, intenciones y decisiones, alegrías y tristezas. Enséñame a consultarte, de vez en cuando, para recibir la luz necesaria sobre el camino que debo seguir; enséñame a reconocer con prontitud mis errores y faltas, y a darte gracias por tu amor y tus dones.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father of infinite benevolence, I beseech your grace to learn how to turn often to you in the course of my days, in order to confide spontaneously my moods to you: thoughts and feelings, intentions and decisions, joys and sorrows. Teach me to consult you, from time to time, for proper enlightenment on the way forward; teach me to promptly recognize mistakes and faults; to thank you for your love and your gifts.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai de infinita benevolência, imploro a Vossa graça para aprender a voltar-me muitas vezes para Vós no decorrer dos meus dias, a fim de Vos confiar espontaneamente os meus estados de ânimo: pensamentos e sentimentos, intenções e decisões, alegrias e tristezas. Ensinai-me a consultar-Vos, de vez em quando, para receber a devida luz sobre o caminho a seguir; ensinai-me a reconhecer prontamente os erros e as faltas; a agradecer-Vos o Vosso amor e os Vossos dons.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre, fortaleza de los que esperan en ti, tú has revelado la perfección de tu santidad y de tu amor, para que yo tenga una confianza firme e inquebrantable en tu fidelidad a tus promesas. Me has dado la certeza de que no seré probado por encima de mis fuerzas y de que, junto con Jesús, siempre estarás conmigo para sostenerme en cualquier dificultad. Imploro, en particular, tu gracia para esperar firmemente la vida eterna: esa comunión plena, perenne y feliz contigo. Con tu ayuda, pues, hago mía la exhortación de la Beata Chiara: “¡Confía en Dios!”.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father, fortress of those who hope in you, you have revealed the perfection of your holiness and love, so that I have a firm and unwavering confidence in your faithfulness to promises. You have given me the assurance that I will not be tried beyond my strength and that, together with Jesus, you will always be with me to support me in any trouble. I implore, in particular, your grace to hope firmly in eternal life: that full, perennial and happy communion with you. With your help, therefore, I join the exhortation of Blessed Chiara: “Trust in God!”.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai, fortaleza dos que esperam em Vós, revelastes a perfeição da Vossa santidade e do Vosso amor, para que eu tenha uma confiança firme e inabalável na Vossa fidelidade às promessas. Destes-me a certeza de que não serei provado acima das minhas forças e de que, juntamente com Jesus, estareis sempre comigo para me amparar em qualquer dificuldade. Imploro, em particular, a Vossa graça para esperar firmemente a vida eterna: aquela comunhão plena, perene e feliz convosco. Com a Vossa ajuda, portanto, uno-me à exortação da Beata Chiara: “Confia em Deus!”.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Padre, fortaleza de los que esperan en ti, tú has revelado la perfección de tu santidad y de tu amor, para que yo tenga una confianza firme e inquebrantable en tu fidelidad a tus promesas. Me has dado la certeza de que no seré probado por encima de mis fuerzas y de que, junto con Jesús, siempre estarás conmigo para sostenerme en cualquier dificultad. Imploro, en particular, tu gracia para esperar firmemente la vida eterna: esa comunión plena, perenne y feliz contigo. Con tu ayuda, pues, hago mía la exhortación de la Beata Chiara: “¡Confía en Dios!”.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

Father, fortress of those who hope in you, you have revealed the perfection of your holiness and love, so that I have a firm and unwavering confidence in your faithfulness to promises. You have given me the assurance that I will not be tried beyond my strength and that, together with Jesus, you will always be with me to support me in any trouble. I implore, in particular, your grace to hope firmly in eternal life: that full, perennial and happy communion with you. With your help, therefore, I join the exhortation of Blessed Chiara: “Trust in God!”.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Pai, fortaleza dos que esperam em Vós, revelastes a perfeição da Vossa santidade e do Vosso amor, para que eu tenha uma confiança firme e inabalável na Vossa fidelidade às promessas. Destes-me a certeza de que não serei provado acima das minhas forças e de que, juntamente com Jesus, estareis sempre comigo para me amparar em qualquer dificuldade. Imploro, em particular, a Vossa graça para esperar firmemente a vida eterna: aquela comunhão plena, perene e feliz convosco. Com a Vossa ajuda, portanto, uno-me à exortação da Beata Chiara: “Confia em Deus!”.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

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

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios, fuente de todo bien, tu Hijo Jesús nos pide que participemos en su misión: “Vayan, pues, y hagan discípulos a todos los pueblos, enseñándoles a cumplir todo lo que yo les he mandado” (Mt 28,19). Debo tener en el corazón el bien de cada persona; por eso me pides que ayude a los demás, con mi ejemplo y mis palabras, a conocer a Jesús y su enseñanza, para que puedan acoger los dones de tu gracia, compartir tu amistad y alcanzar la salvación. En particular, me atrevo a pedirte una gracia que llevo muy dentro del corazón, por la intercesión de los santos y en particular de la Beata Chiara Badano.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God, source of all good, your Son Jesus asks us to take part in his mission: “Go therefore and make disciples of all nations, teaching them to observe all that I have commanded you” (Mt 28:19). I must have at heart the good of every person: therefore, you ask me to help others with my example and words in getting to know Jesus and his teaching, so that I can accept the gifts of your grace, share your friendship and attain salvation. In particular, I dare to ask you for a grace that is so close to my heart, through the intercession of the Saints and in particular of Blessed Chiara Badano.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus, fonte de todo o bem, o Vosso Filho Jesus pede-nos que participemos da sua missão: “Ide, pois, fazei discípulos de todas as nações, ensinando-as a observar tudo o que vos ordenei” (Mt 28,19). Devo ter no coração o bem de cada pessoa: por isso, Vós me pedis que ajude os outros, com o meu exemplo e as minhas palavras, a conhecer Jesus e o seu ensinamento, para que possam acolher os dons da Vossa graça, partilhar a Vossa amizade e alcançar a salvação. Em particular, ouso pedir-Vos uma graça que me é tão cara ao coração, pela intercessão dos Santos e em particular da Beata Chiara Badano.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Padre, fuente de todo bien, te damos gracias por el admirable testimonio de la Beata Chiara Badano. Llena del Espíritu Santo y guiada por la luz radiante de Jesús, creyó firmemente en tu amor infinito y quiso corresponder a él con todas sus fuerzas, abandonándose con plena confianza a tu voluntad paternal.

Te suplicamos humildemente que nos concedas también a nosotros el don de vivir contigo y para ti, y te pedimos, si es tu voluntad, la gracia

(menciona tu petición aquí…)

por nuestro Señor Jesucristo.

Amén.

Dios, fuente de todo bien, tu Hijo Jesús nos pide que participemos en su misión: “Vayan, pues, y hagan discípulos a todos los pueblos, enseñándoles a cumplir todo lo que yo les he mandado” (Mt 28,19). Debo tener en el corazón el bien de cada persona; por eso me pides que ayude a los demás, con mi ejemplo y mis palabras, a conocer a Jesús y su enseñanza, para que puedan acoger los dones de tu gracia, compartir tu amistad y alcanzar la salvación. En particular, me atrevo a pedirte una gracia que llevo muy dentro del corazón, por la intercesión de los santos y en particular de la Beata Chiara Badano.

Padre nuestro.
 Dios te salve, María.
 Gloria al Padre.

Beata Chiara Badano, ¡ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Father, fount of everything good, we give You thanks for the wonderful testimony of Blessed Chiara Badano. Filled with the Holy Spirit and guided by the radiant light of Jesus, she believed firmly in Your infinite love, and wished to return it with all her strength, surrendering herself in complete trust to Your paternal will.

We humbly beseech You that You may also grant us the gift to live with You and for You, and ask You, if it be Your will, for the grace

(mention request here…)

through Our Lord Jesus Christ.

Amen.

God, source of all good, your Son Jesus asks us to take part in his mission: “Go therefore and make disciples of all nations, teaching them to observe all that I have commanded you” (Mt 28:19). I must have at heart the good of every person: therefore, you ask me to help others with my example and words in getting to know Jesus and his teaching, so that I can accept the gifts of your grace, share your friendship and attain salvation. In particular, I dare to ask you for a grace that is so close to my heart, through the intercession of the Saints and in particular of Blessed Chiara Badano.

Our Father
 Hail Mary
 Glory Be

Blessed Chiara Badano, pray for us!

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Pai, fonte de todo o bem, nós Vos damos graças pelo maravilhoso testemunho da Beata Chiara Badano. Cheia do Espírito Santo e guiada pela luz radiante de Jesus, ela acreditou firmemente no Vosso amor infinito e desejou corresponder a ele com todas as suas forças, abandonando-se com total confiança à Vossa vontade paterna.

Humildemente Vos suplicamos que nos concedais também o dom de viver convosco e para Vós, e Vos pedimos, se for da Vossa vontade, a graça

(mencione o pedido aqui…)

por Nosso Senhor Jesus Cristo.

Amém.

Deus, fonte de todo o bem, o Vosso Filho Jesus pede-nos que participemos da sua missão: “Ide, pois, fazei discípulos de todas as nações, ensinando-as a observar tudo o que vos ordenei” (Mt 28,19). Devo ter no coração o bem de cada pessoa: por isso, Vós me pedis que ajude os outros, com o meu exemplo e as minhas palavras, a conhecer Jesus e o seu ensinamento, para que possam acolher os dons da Vossa graça, partilhar a Vossa amizade e alcançar a salvação. Em particular, ouso pedir-Vos uma graça que me é tão cara ao coração, pela intercessão dos Santos e em particular da Beata Chiara Badano.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Beata Chiara Badano, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/blessed-chiara-badano-novena.png""",
        tags = listOf("love", "health")
    )
}