package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData208 {
    val novena: Novena = Novena(
        id = """novena-al-beato-miguel-pro""",
        title = LocalizedText("""Novena al Beato Miguel Agustín Pro""", """Bl Miguel Agustin Pro Novena""", """Novena ao Beato Miguel Agustín Pro"""),
        patron = LocalizedText("""Santo patrón de los trabajadores, los enfermos y quienes sufren depresión""", """Patron saint of laborers, the sick, and for those suffering from depression.""", """Novena ao Beato Miguel Agustín Pro"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""El Beato Miguel Agustín Pro fue un sacerdote jesuita mexicano fusilado bajo Plutarco Elías Calles por acusaciones falsas. Reza esta novena por los trabajadores, por los enfermos y por quienes sufren depresión.""", """Blessed Miguel Agustin Pro was a Mexican Jesuit priest executed under Plutarco Elías Calles on false charges. Pray this novena for those who labor, those who are sick, and for those suffering from depression.""", """Santo padroeiro de os trabalhadores, os doentes e os que sofrem de depressão"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

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

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Beato Miguel, antes de tu muerte le dijiste a tu amigo que te pidiera favores cuando estuvieras en el cielo.

Te suplico que intercedas por mí, en unión con Nuestra Señora y todos los ángeles y santos, para pedir a Nuestro Señor que me conceda mi petición, siempre que sea voluntad de Dios

(menciona tu petición aquí…)

Honramos y adoramos a Dios uno y trino al rezar el Gloria.

Gloria al Padre.

Pedimos al Espíritu Santo que nos guíe. Ven, Espíritu Santo.

Oramos como Jesús nos enseñó a orar.

Padre nuestro.

Veneramos con amor a la Virgen María.

Dios te salve, María.

Todos los ángeles, bendigan al Señor por siempre.

San José y todos los santos, rueguen por nosotros.

Beato Miguel, joven de gran ánimo, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, hijo y hermano amoroso, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, novicio paciente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, desterrado de tu patria, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, religioso de oración, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, enfermo y sufriente, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, defensor de los obreros, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, valiente sacerdote en la clandestinidad, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, prisionero en la cárcel, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, que perdonaste a tus perseguidores, ruega por nosotros.

Viva Cristo Rey.

Beato Miguel, santo mártir, ruega por nosotros.

Viva Cristo Rey.

Oración del Beato Miguel Agustín Pro

¿Se nos hace la vida de día en día más penosa, más agobiante, más llena de aflicciones?

Bendito sea mil veces Aquel que así lo quiere.

Si la vida es más dura, el amor la hace también más fuerte, y solo este amor, fundado en el sufrimiento, puede llevar la Cruz de mi Señor Jesucristo.

Amor sin egoísmo, sin apoyarse en uno mismo, sino encendiendo en lo hondo del corazón una sed ardiente de amar y de sufrir por todos los que nos rodean: una sed que ni la desgracia ni el desprecio pueden apagar…

Creo, Señor; pero fortalece mi fe.

Corazón de Jesús, te amo; pero aumenta mi amor.

Corazón de Jesús, en ti confío; pero da mayor vigor a mi confianza.

Corazón de Jesús, te doy mi corazón; pero enciérralo de tal modo en ti que nunca se separe de ti.

Corazón de Jesús, soy todo tuyo; pero cuida de mi promesa para que pueda ponerla en práctica hasta el sacrificio completo de mi vida.

Amén.

Acuérdate de mí, Beato Miguel, porque yo me acuerdo de ti.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Blessed Miguel, prior to your death, you told your friend to ask you for favors when you were in Heaven.

(mention request here…)

We honor and adore the triune God as we pray the Gloria

(Pray the Glory Be here.)

We ask the Holy Spirit for guidance. Come Holy Ghost.

We pray as Jesus taught us to pray.

(Pray the Our Father here.)

We venerate with love the Virgin Mary.

(Pray the Hail Mary here.)

All you angels, bless the Lord forever.

Saint Joseph and all the saints, pray for us.

Blessed Miguel, high spirited youth, pray for us.

Viva Christo Rey.

Blessed Miguel, loving son and brother, pray for us.

Viva Christo Rey.

Blessed Miguel, patient novice, pray for us.

Viva Christo Rey.

Blessed Miguel, exile from your homeland, pray for us.

Viva Christo Rey.

Blessed Miguel, prayerful religious, pray for us.

Viva Christo Rey.

Blessed Miguel, sick and suffering, pray for us.

Viva Christo Rey.

Blessed Miguel, defender of workers, pray for us.

Viva Christo Rey.

Blessed Miguel, courageous priest in hiding, pray for us.

Viva Christo Rey.

Blessed Miguel, prisoner in jail, pray for us.

Viva Christo Rey.

Blessed Miguel, forgiver of persecutors, pray for us.

Viva Christo Rey.

Blessed Miguel, holy martyr, pray for us.

Viva Christo Rey.

Pray the Introduction Prayer above.

Prayer of Blessed Miguel Agustin Pro

Does our life become from day to day more painful, more oppressive, more replete with afflictions?

Blessed be He a thousand times who desires it so.

If life be harder, love makes it also stronger, and only this love, grounded on suffering, can carry the Cross of my Lord Jesus Christ.

Love without egotism, without relying on self, but enkindling in the depth of the heart an ardent thirst to love and suffer for all those around us: a thirst that neither misfortune nor contempt can extinguish …

I believe, O Lord; but strengthen my faith.

Heart of Jesus, I love Thee; but increase my love.

Heart of Jesus, I trust in Thee; but give greater vigor to my confidence.

Heart of Jesus, I give my heart to Thee; but so enclose it in Thee that it may never be separated from Thee.

Heart of Jesus, I am all Thine; but take care of my promise so that I may be able to put it in practice even unto the complete sacrifice of my life.

Amen.

Remember me, Blessed Miguel, for I remember you.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Beato Miguel, antes da vossa morte dissestes ao vosso amigo que vos pedisse graças quando estivésseis no céu.

Suplico-vos que intercedais por mim, em união com Nossa Senhora e todos os anjos e santos, pedindo a Nosso Senhor que me conceda o meu pedido, desde que seja a vontade de Deus

(mencione o pedido aqui…)

Honramos e adoramos o Deus uno e trino ao rezar o Glória.

Glória ao Pai.

Pedimos ao Espírito Santo que nos guie. Vinde, Espírito Santo.

Rezamos como Jesus nos ensinou a rezar.

Pai-Nosso.

Veneramos com amor a Virgem Maria.

Ave-Maria.

Anjos todos, bendizei o Senhor para sempre.

São José e todos os santos, rogai por nós.

Beato Miguel, jovem cheio de ânimo, rogai por nós.

Viva Cristo Rey.

Beato Miguel, filho e irmão amoroso, rogai por nós.

Viva Cristo Rey.

Beato Miguel, noviço paciente, rogai por nós.

Viva Cristo Rey.

Beato Miguel, exilado da vossa pátria, rogai por nós.

Viva Cristo Rey.

Beato Miguel, religioso de oração, rogai por nós.

Viva Cristo Rey.

Beato Miguel, doente e sofredor, rogai por nós.

Viva Cristo Rey.

Beato Miguel, defensor dos trabalhadores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, corajoso padre na clandestinidade, rogai por nós.

Viva Cristo Rey.

Beato Miguel, prisioneiro no cárcere, rogai por nós.

Viva Cristo Rey.

Beato Miguel, que perdoastes os vossos perseguidores, rogai por nós.

Viva Cristo Rey.

Beato Miguel, santo mártir, rogai por nós.

Viva Cristo Rey.

Oração do Beato Miguel Agustín Pro

A nossa vida se torna dia após dia mais penosa, mais opressiva, mais cheia de aflições?

Bendito seja mil vezes Aquele que assim o quer.

Se a vida é mais dura, o amor a torna também mais forte, e só este amor, fundado no sofrimento, pode carregar a Cruz de meu Senhor Jesus Cristo.

Um amor sem egoísmo, sem apoiar-se em si mesmo, mas acendendo no fundo do coração uma sede ardente de amar e de sofrer por todos os que nos rodeiam: uma sede que nem a desgraça nem o desprezo podem apagar…

Creio, Senhor; mas fortalecei a minha fé.

Coração de Jesus, eu Vos amo; mas aumentai o meu amor.

Coração de Jesus, confio em Vós; mas dai maior vigor à minha confiança.

Coração de Jesus, dou-Vos o meu coração; mas encerrai-o de tal modo em Vós que nunca se separe de Vós.

Coração de Jesus, sou todo Vosso; mas guardai a minha promessa, para que eu possa cumpri-la até o sacrifício completo da minha vida.

Amém.

Lembrai-vos de mim, Beato Miguel, pois eu me lembro de vós.

Viva Cristo Rey!

Viva la Virgen de Guadalupe!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/miguel-agustin-pro-novena.png""",
        tags = listOf("love")
    )
}