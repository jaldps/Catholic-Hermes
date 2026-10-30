package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData106 {
    val novena: Novena = Novena(
        id = """novena-a-san-leon-magno""",
        title = LocalizedText("""Novena al papa San León Magno""", """Pope St Leo the Great Novena""", """Novena ao Papa São Leão Magno"""),
        patron = LocalizedText("""Santo patrón de los papas, el clero docto, los pastores de la Iglesia que buscan sabiduría y valentía, quienes enfrentan amenazas a la paz""", """Patron saint of popes, learned clergy, Church leaders seeking wisdom and courage, those facing threats to peace""", """Novena ao Papa São Leão Magno"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena al papa San León Magno (León I), papa del siglo V que combatió las herejías del pelagianismo, el maniqueísmo y el monofisismo, por la unidad y la ortodoxia en la Iglesia.""", """Pope St Leo the Great (Pope Leo I) was a fifth century pope who combated the heresies of Pelagianism, Manichaeism, and Monophysitism. Pray this novena for unity and orthodoxy in the Church.""", """Santo padroeiro de os papas, o clero douto, os pastores da Igreja que buscam sabedoria e coragem, os que enfrentam ameaças à paz"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

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

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Gloria a ti, oh Jesús, León de la tribu de Judá, que suscitaste en tu Iglesia un León para defenderla en aquellos tiempos oscuros, cuando la santa fe estaba más expuesta al peligro!

Tú encargaste a Pedro confirmar a sus hermanos (San Lucas 22, 32), y hemos visto a León, en quien Pedro vivía, cumplir este oficio con autoridad soberana. Hemos oído la aclamación del santo Concilio, que, admirado ante las enseñanzas celestiales de León, proclamó el insigne favor que concediste a tu rebaño cuando mandaste a Pedro apacentar tanto las ovejas como los corderos.

¡Oh santo Pontífice León! Tú representaste dignamente a Pedro en su Cátedra, desde donde tu enseñanza apostólica no dejó de fluir, siempre hermosa en su verdad y majestad. La Iglesia de tu tiempo te honró como el gran Maestro de la fe, y la Iglesia de todas las épocas posteriores te ha reconocido como uno de los más sabios Doctores y predicadores de la Palabra divina.

Desde tu trono en el cielo, donde ahora reinas, derrama sobre nosotros la comprensión del gran Misterio que fuiste llamado a defender. Bajo tu pluma inspirada, este misterio se hace claro; vemos cuán sublimemente armoniza con todos los demás misterios, y la fe se deleita al contemplar tan de cerca el objeto divino de su creencia. ¡Oh, fortalece esta fe en nosotros!

El Verbo encarnado es blasfemado en estos nuestros tiempos; venga su gloria enviándonos hombres de tu celo y de tu sabiduría.

(menciona tu petición aquí…)

Tú triunfaste sobre los invasores bárbaros: Atila reconoció el influjo de tu santidad y de tu elocuencia al retirar sus tropas de la tierra cristiana que asolaban.

En estos nuestros días se han levantado nuevos bárbaros, bárbaros civilizados, que quieren persuadirnos de que la religión debe ser eliminada de la educación, y de que el Estado, en sus leyes e instituciones, debe simplemente ignorar a nuestro Señor Jesucristo, el Rey a quien se le ha dado todo poder, no solo en el cielo sino también en la tierra (San Mateo 28, 18).

¡Oh, ayúdanos con tu poderosa intercesión, porque nuestro peligro es extremo! Muchos son seducidos y son apóstatas, mientras se jactan de seguir siendo cristianos.

Ruega para que la luz que aún queda en nosotros no se extinga, y para que los escándalos públicos que ahora existen lleguen a su fin. Atila no era más que un pagano; nuestros estadistas y gobiernos modernos son cristianos, o al menos se llaman así: ten piedad de ellos y alcánzales luz para ver el precipicio hacia el cual están arrastrando a la sociedad.

Estos días del Tiempo Pascual deben recordarte, oh santo Pontífice, las Pascuas que pasaste un día aquí en la tierra, cuando, rodeado de los neófitos, les dabas el alimento de tus magníficos sermones: ruega por los fieles que en esta Pascua han resucitado a una vida nueva con Cristo.

Lo que más necesitan es un conocimiento más pleno y mejor de este su Salvador, para que se unan más estrechamente a él y perseveren en su santo servicio. Tus oraciones deben obtenerles este conocimiento; con tus oraciones debes enseñarles lo que él es, tanto en su naturaleza divina como en su naturaleza humana: que, como Dios, es su fin último y su Juez después de la muerte; como hombre, su Hermano, su Redentor, su Modelo.

¡Bendice, oh León, y ayuda al Pontífice que ahora es tu sucesor en la Cátedra de Pedro! Muestra ahora tu amor por esa Roma cuyos sagrados y eternos destinos fueron tan a menudo tema de tu ardiente y celestial elocuencia.

Te pedimos también, San León, que ruegues por nosotros en nuestra intención (menciona tu petición aquí…)

¡Papa San León Magno, ruega por nosotros!

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Glory be to thee, O Jesus, Lion of the Tribe of Judah! that hast raised up in thy Church a Lion to defend her in those dark times, when holy Faith was most exposed to danger.

Thou charged Peter with confirming his Brethren (St. Luke, xxii. 32.); and we have seen Leo, in whom Peter lived, fulfill this office with sovereign authority. We have heard the acclamation of the holy Council, which, in admiration at the heavenly teachings of Leo, proclaimed the signal favour thou conferred on thy Flock, when thou commanded Peter to feed both Sheep and Lambs.

O holy Pontiff Leo! thou worthily represented Peter in his Chair, whence thy apostolic teaching ceased not to flow, ever beautiful in its truth and majesty. The Church of thine own day honored thee as the great Teacher of Faith; and the Church of every succeeding age has recognized thee as one of the most learned Doctors and preachers of the divine Word.

From thy throne in heaven, where now thou reign, pour forth upon us the understanding of the great Mystery which thou was called on to defend. Under thy inspired pen, this mystery grows clear; we see how sublimely it harmonizes with all other mysteries; and Faith delights at gaining so close a view of the divine object of its belief. Oh! strengthen this Faith within us.

The Incarnate Word is blasphemed in these our own times; avenge his glory, by sending us men of thy zeal and learning.

(mention request here…)

Thou triumphed over barbarian invaders: Attila acknowledged the influence of thy sanctity and eloquence, by withdrawing his troops from the Christian land they infested.

In these our days, there have risen up new barbarians–civilized barbarians, who would persuade us that religion should be eliminated from Education–and that the State, in its laws and institutions, should simply ignore our Lord Jesus Christ, the King to whom all power has been given, not only in heaven but on Earth also (St. Matth. xxviii. 18).

Oh! help us by thy powerful intercession, for our danger is extreme. Many are seduced, and are apostates whilst flattering themselves that they are still Christians.

Pray that the light, that is left within us, may not be extinguished, and that the public scandals, which now exist, may be brought to an end. Attila was but a pagan; our modern statesmen and Governments are, or, at least, call themselves, Christians: have pity on them, and gain for them light to see the precipice to which they are hurrying society.

These days of Paschal Time must remind thee, O holy Pontiff! of the Easters thou didst once spend, here on earth, when, surrounded by the Neophytes, thou gave them the nourishment of thy magnificent Discourses:–pray for the Faithful, who have this Easter, risen to a new life with Christ.

What they most stand in need of is, a fuller and better knowledge of this their Saviour, in order that they may cling more closely to him, and persevere in his holy service. Thy prayers must get them this knowledge; by thy prayers, thou must teach them what he is both in his Divine and Human Nature: that, as God, He is their Last End, and their Judge after death; as Man, their Brother, their Redeemer, their Model.

Bless, O Leo! and help the Pontiff who is now thy successor on the Chair of Peter. Show now thy love for that Rome, whose sacred and eternal destinies were so frequently the subject of thy glowing and heavenly eloquence.

We also ask, St Leo, that you pray for us in our intention (mention request here…)

Pope St Leo the Great, pray for us!

Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Glória a Vós, ó Jesus, Leão da Tribo de Judá, que suscitastes em Vossa Igreja um Leão para defendê-la naqueles tempos sombrios, quando a santa Fé estava mais exposta ao perigo!

Vós encarregastes Pedro de confirmar os seus irmãos (São Lucas 22,32); e vimos Leão, em quem Pedro vivia, cumprir este ofício com soberana autoridade. Ouvimos a aclamação do santo Concílio que, admirado com os ensinamentos celestes de Leão, proclamou o insigne favor que concedestes ao Vosso rebanho quando ordenastes a Pedro que apascentasse tanto as ovelhas como os cordeiros.

Ó santo Pontífice Leão, representastes dignamente Pedro na sua Cátedra, de onde não cessou de fluir o vosso ensinamento apostólico, sempre belo em sua verdade e majestade. A Igreja do vosso tempo honrou-vos como o grande Mestre da Fé; e a Igreja de todos os séculos seguintes reconheceu-vos como um dos mais doutos Doutores e pregadores da Palavra divina.

Do vosso trono no céu, onde agora reinais, derramai sobre nós a compreensão do grande Mistério que fostes chamado a defender. Sob a vossa pena inspirada, este mistério se torna claro; vemos quão sublimemente ele se harmoniza com todos os outros mistérios; e a Fé se alegra ao contemplar tão de perto o objeto divino da sua crença. Oh, fortalecei esta Fé em nós!

O Verbo Encarnado é blasfemado nestes nossos tempos; vingai a Sua glória, enviando-nos homens com o vosso zelo e a vossa sabedoria.

(mencione o pedido aqui…)

Triunfastes sobre os invasores bárbaros: Átila reconheceu a força da vossa santidade e da vossa eloquência, retirando as suas tropas da terra cristã que infestavam.

Nestes nossos dias, surgiram novos bárbaros, bárbaros civilizados, que querem convencer-nos de que a religião deve ser eliminada da educação e de que o Estado, nas suas leis e instituições, deve simplesmente ignorar Nosso Senhor Jesus Cristo, o Rei a quem foi dado todo o poder, não só no céu, mas também na terra (São Mateus 28,18).

Oh, ajudai-nos com a vossa poderosa intercessão, pois o nosso perigo é extremo. Muitos são seduzidos e tornam-se apóstatas, enquanto se iludem pensando que ainda são cristãos.

Rogai para que a luz que ainda resta em nós não se apague e para que os escândalos públicos que agora existem tenham fim. Átila era apenas um pagão; os nossos estadistas e governos modernos são, ou pelo menos se dizem, cristãos: tende compaixão deles e alcançai-lhes luz para verem o precipício para o qual estão arrastando a sociedade.

Estes dias do Tempo Pascal devem recordar-vos, ó santo Pontífice, as Páscoas que outrora passastes aqui na terra, quando, rodeado pelos neófitos, lhes dáveis o alimento dos vossos magníficos Sermões: rogai pelos fiéis que, nesta Páscoa, ressuscitaram para uma vida nova com Cristo.

Aquilo de que eles mais precisam é um conhecimento mais pleno e mais profundo deste seu Salvador, para que se unam mais intimamente a Ele e perseverem no Seu santo serviço. As vossas orações devem alcançar-lhes este conhecimento; pelas vossas orações, deveis ensinar-lhes quem Ele é, tanto na Sua Natureza Divina quanto na Humana: que, como Deus, Ele é o seu Fim Último e o seu Juiz depois da morte; como Homem, o seu Irmão, o seu Redentor, o seu Modelo.

Abençoai, ó Leão, e ajudai o Pontífice que é agora o vosso sucessor na Cátedra de Pedro. Mostrai agora o vosso amor por esta Roma, cujos destinos sagrados e eternos foram tantas vezes o tema da vossa ardente e celestial eloquência.

Pedimos também, São Leão, que rogueis por nós na nossa intenção (mencione o pedido aqui…)

Papa São Leão Magno, rogai por nós!

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/pope-st-leo-the-great-novena.png""",
        tags = listOf("love", "peace")
    )
}