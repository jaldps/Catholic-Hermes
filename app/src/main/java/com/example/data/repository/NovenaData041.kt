package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData041 {
    val novena: Novena = Novena(
        id = """novena-a-nuestra-senora-del-buen-remedio""",
        title = LocalizedText("""Novena a Nuestra Señora del Buen Remedio""", """Our Lady of Good Remedy Novena""", """Novena a Nossa Senhora do Bom Remédio"""),
        patron = LocalizedText("""Santo patrón de cautivos, causas imposibles, dificultades económicas, la Orden Trinitaria""", """Patron saint of those held captive, impossible causes, financial difficulties, the Trinitarian Order""", """Novena a Nossa Senhora do Bom Remédio"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Recordatorios diarios + intenciones + oraciones grupales""", """Daily reminders + prayer intentions + group prayers""", """Santo padroeiro de os cativos, as causas impossíveis, as dificuldades financeiras e a Ordem Trinitária"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

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

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh Reina del Cielo y de la Tierra, Santísima Virgen, te veneramos. Tú eres la Hija amada del Dios Altísimo, la Madre elegida del Verbo Encarnado, la Esposa Inmaculada del Espíritu Santo y el Vaso Sagrado de la Santísima Trinidad.

Oh Madre del Divino Redentor, que bajo el título de Nuestra Señora del Buen Remedio acudes en auxilio de todos los que te invocan, extiende tu protección maternal sobre nosotros. Dependemos de ti, Madre querida, como hijos indefensos y necesitados dependen de una madre tierna y cariñosa.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Oh Señora del Buen Remedio, fuente de ayuda infalible, concédenos que podamos recurrir a tu tesoro de gracias en nuestro momento de necesidad.

Toca los corazones de los pecadores, para que busquen la reconciliación y el perdón. Lleva consuelo a los afligidos y a los solitarios; ayuda a los pobres y a los desesperanzados; socorre a los enfermos y a los que sufren. Que sean sanados en el cuerpo y fortalecidos en el espíritu para soportar sus sufrimientos con resignación paciente y fortaleza cristiana.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, fuente de ayuda infalible, tu corazón compasivo conoce un remedio para cada aflicción y miseria que encontramos en la vida. Ayúdame con tus oraciones e intercesión a encontrar un remedio para mis problemas y necesidades, especialmente para

(menciona tu petición aquí…)

Por mi parte, oh Madre amorosa, me comprometo a adoptar un estilo de vida más intensamente cristiano, a una observancia más cuidadosa de las leyes de Dios, a ser más consciente en el cumplimiento de las obligaciones de mi estado de vida, y a esforzarme por ser una fuente de sanación en este mundo quebrantado que es el nuestro.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

Querida Señora del Buen Remedio, estate siempre presente conmigo, y por tu intercesión, que pueda yo gozar de salud corporal y paz mental, y crecer más fuerte en la fe y en el amor de tu Hijo, Jesús.

Dios te salve, María, llena eres de gracia, el Señor es contigo. Bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús. Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén.

V. Ruega por nosotros, oh Santa Madre del Buen Remedio,

R. Para que profundicemos nuestra dedicación a tu Hijo, y hagamos que el mundo viva con Su Espíritu.

Padre nuestro, que estás en el cielo,

santificado sea tu nombre;

venga a nosotros tu reino;

hágase tu voluntad en la tierra como en el cielo.

Danos hoy nuestro pan de cada día;

perdona nuestras ofensas,

como también nosotros perdonamos a los que nos ofenden;

no nos dejes caer en tentación,

y líbranos del mal.

Amén.

Dios te salve, María, llena eres de gracia,

el Señor es contigo.

Bendita tú eres entre todas las mujeres,

y bendito es el fruto de tu vientre, Jesús.

Santa María, Madre de Dios,

ruega por nosotros, pecadores,

ahora y en la hora de nuestra muerte.

Amén.

Gloria al Padre, al Hijo y al Espíritu Santo.

Como era en el principio, ahora y siempre, por los siglos de los siglos.

Amén.

¡Nuestra Señora del Buen Remedio, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Queen of Heaven and Earth, Most Holy Virgin, we venerate thee. Thou art the beloved Daughter of the Most High God, the chosen Mother of the Incarnate Word, the Immaculate Spouse of the Holy Spirit, and the Sacred Vessel of the Most Holy Trinity.

O Mother of the Divine Redeemer, who under the title of Our Lady of Good Remedy comes to the aid of all who call upon thee, extend thy maternal protection to us. We depend on thee, Dear Mother, as helpless and needy children depend on a tender and caring mother.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

O Lady of Good Remedy, source of unfailing help, grant that we may draw from thy treasury of graces in our time of need.

Touch the hearts of sinners, that they may seek reconciliation and forgiveness. Bring comfort to the afflicted and the lonely; help the poor and the hopeless; aid the sick and the suffering. May they be healed in body and strengthened in spirit to endure their sufferings with patient resignation and Christian fortitude.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, source of unfailing help, thy compassionate heart knows a remedy for every affliction and misery we encounter in life. Help me with thy prayers and intercession to find a remedy for my problems and needs, especially for

(mention request here…)

On my part, O loving Mother, I pledge to adopt a more intensely Christian lifestyle, to a more careful observance of the laws of God, to be more conscientious in fulfilling the obligations of my state in life, and to strive to be a source of healing in this broken world of ours.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Dear Lady of Good Remedy, be ever present to me, and through thy intercession, may I enjoy health of body and peace of mind, and grow stronger in the faith and in the love of thy Son, Jesus.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

V. Pray for us, O Holy Mother of Good Remedy,

R. That we may deepen our dedication to thy Son, and make the world alive with His Spirit.

Our Father, who art in heaven, hallowed be thy name; thy kingdom come, thy will be done on earth as it is in heaven. Give us this day our daily bread, and forgive us our trespasses, as we forgive those who trespass against us; and lead us not into temptation, but deliver us from evil. Amen.

Hail Mary, full of grace, the Lord is with thee. Blessed art thou among women, and blessed is the fruit of thy womb, Jesus. Holy Mary, Mother of God, pray for us sinners, now and at the hour of our death. Amen.

Glory be to the Father, and to the Son, and to the Holy Spirit. As it was in the beginning, is now, and ever shall be, world without end. Amen.

Our Lady of Good Remedy, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Rainha do Céu e da Terra, Santíssima Virgem, nós vos veneramos. Vós sois a Filha amada do Deus Altíssimo, a Mãe escolhida do Verbo Encarnado, a Esposa Imaculada do Espírito Santo e o Vaso Sagrado da Santíssima Trindade.

Ó Mãe do Divino Redentor, que sob o título de Nossa Senhora do Bom Remédio vindes em auxílio de todos os que vos invocam, estendei sobre nós a vossa proteção maternal. Dependemos de vós, querida Mãe, como filhos indefesos e necessitados dependem de uma mãe terna e carinhosa.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Ó Senhora do Bom Remédio, fonte de auxílio infalível, concedei-nos que possamos haurir do vosso tesouro de graças nos momentos de necessidade.

Tocai o coração dos pecadores, para que busquem a reconciliação e o perdão. Levai consolo aos aflitos e aos solitários; ajudai os pobres e os desesperançados; socorrei os doentes e os que sofrem. Que sejam curados no corpo e fortalecidos no espírito, para suportarem os seus sofrimentos com paciente resignação e fortaleza cristã.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, fonte de auxílio infalível, o vosso coração compassivo conhece um remédio para cada aflição e miséria que encontramos na vida. Ajudai-me com as vossas orações e a vossa intercessão a encontrar um remédio para os meus problemas e necessidades, especialmente para

(mencione o pedido aqui…)

Da minha parte, ó Mãe amorosa, comprometo-me a adotar um estilo de vida mais intensamente cristão, a observar com mais cuidado as leis de Deus, a ser mais consciencioso no cumprimento das obrigações do meu estado de vida e a esforçar-me por ser uma fonte de cura neste nosso mundo ferido.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Querida Senhora do Bom Remédio, estai sempre presente junto de mim e, pela vossa intercessão, que eu possa gozar de saúde do corpo e paz de espírito, e crescer na fé e no amor ao vosso Filho, Jesus.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

V. Rogai por nós, ó Santa Mãe do Bom Remédio,

R. Para que aprofundemos a nossa dedicação ao vosso Filho e enchamos o mundo da vida do Seu Espírito.

Pai nosso que estais nos céus, santificado seja o vosso nome; venha a nós o vosso reino; seja feita a vossa vontade, assim na terra como no céu. O pão nosso de cada dia nos dai hoje; perdoai-nos as nossas ofensas, assim como nós perdoamos a quem nos tem ofendido; e não nos deixeis cair em tentação, mas livrai-nos do mal. Amém.

Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres, e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós, pecadores, agora e na hora da nossa morte. Amém.

Glória ao Pai e ao Filho e ao Espírito Santo. Como era no princípio, agora e sempre, por todos os séculos dos séculos. Amém.

Nossa Senhora do Bom Remédio, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-good-remedy-novena.png""",
        tags = listOf("impossible", "love", "comfort", "health", "peace")
    )
}