package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData006 {
    val novena: Novena = Novena(
        id = """novena-a-la-preciosa-sangre""",
        title = LocalizedText("""Novena a la Preciosa Sangre de Jesús""", """Precious Blood Novena""", """Novena ao Preciosíssimo Sangue de Jesus"""),
        patron = LocalizedText("""Santo patrón de la salvación""", """Patron saint of Salvation""", """Novena ao Preciosíssimo Sangue de Jesus"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""La Sangre de Jesús, derramada para nuestra salvación, ha sido desde hace mucho un centro de la devoción de la Iglesia. Reza esta novena para crecer en devoción a Cristo y a su Preciosa Sangre.""", """The Blood of Jesus, shed for our salvation, has long been a focal point of the Church’s devotion. Pray this novena to grow in devotion to Christ and His Precious Blood.""", """O Sangue de Jesus, derramado para a nossa salvação, há muito tempo é um ponto central da devoção da Igreja. Reze esta novena para crescer na devoção a Cristo e ao Seu Preciosíssimo Sangue."""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

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

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Por la voz de tu Sangre, oh Jesús, te busco, te pido y te suplico que me ayudes en este momento de necesidad.

Oh Jesús, me arrodillo a tus pies ensangrentados implorando que me escuches.

Muchas gracias y muchas misericordias han brotado de tu Sangre.

Mi esperanza vive en ti, hasta el fin de mis días.

Oh Jesús, por tu Preciosa Sangre que derramaste por nuestras almas, por cada gota que derramaste para nuestra redención y por las lágrimas de tu Madre Inmaculada, te suplico y te pido encarecidamente que escuches mi oración:

(menciona tu petición aquí…)

Oh Jesús, que durante todos los días de tu vida mortal consolaste a tantos que sufrían, sanaste tantas enfermedades y levantaste tantas veces el ánimo decaído, ten piedad de mi alma, que clama a ti desde lo más profundo de su angustia.

Oh Jesús, que de las llagas de tu corazón brote una ola de tu sangre misericordiosa y me conceda la gracia que tan ardientemente deseo.

Oh Jesús, apresura el momento en que cambiarás mis lágrimas en alegría y mis suspiros en acción de gracias.

Santa María, te pido que te unas a mí para pedir ayuda.

Dios, Padre nuestro que estás en el cielo: hágase tu voluntad. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Jesús crucificado, ten misericordia de mí.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

By the Voice of Thy Blood, O Jesus, I seek Thee, ask Thee and beg Thee, to help me at this time of my need.

O Jesus, I kneel at Thy bleeding feet pleading that you will hear me.

Many graces and many mercies have come from Thy Blood.

My hope lives in you, until the end of my time.

O Jesus, by Thy Precious Blood which you shed for our souls, by each drop that you shed for our redemption and by the tears of Thy Immaculate Mother, I beseech Thee and earnestly ask You to listen to my prayer:

(mention request here…)

O Jesus, who during all the days of Thy mortal life, You consoled so many sufferers, You healed so many infirmities, You raised so often a sinking courage; have pity on my soul who cries to Thee from the depths of my anguish.

O Jesus, from the wounds of Thy heart, may a wave of Thy merciful blood flow and grant to me the grace that I so ardently desire.

O Jesus, hasten the moment when You will change my tears to joy and my sighs to thanksgivings.

Holy Mary, I ask you to join with me in seeking help.

God our Father in Heaven: May Thy will be done. Amen.

Our Father

Hail Mary

Glory be

Jesus, Crucified, have mercy on me.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Pela Voz do Vosso Sangue, ó Jesus, eu Vos busco, Vos peço e Vos suplico que me ajudeis neste momento de necessidade.

Ó Jesus, ajoelho-me aos Vossos pés ensanguentados, suplicando que me ouçais.

Muitas graças e muitas misericórdias vieram do Vosso Sangue.

A minha esperança vive em Vós, até o fim dos meus dias.

Ó Jesus, pelo Vosso Precioso Sangue, que derramastes pelas nossas almas, por cada gota que derramastes para a nossa redenção e pelas lágrimas da Vossa Mãe Imaculada, eu Vos suplico e Vos peço ardentemente que escuteis a minha oração:

(mencione o pedido aqui…)

Ó Jesus, que durante todos os dias da Vossa vida mortal consolastes tantos sofredores, curastes tantas enfermidades e reanimastes tantas vezes a coragem abatida, tende piedade da minha alma, que clama a Vós do fundo da sua angústia.

Ó Jesus, que das chagas do Vosso Coração brote uma onda do Vosso Sangue misericordioso e me conceda a graça que tão ardentemente desejo.

Ó Jesus, apressai o momento em que transformareis as minhas lágrimas em alegria e os meus suspiros em ações de graças.

Santa Maria, peço-vos que vos unais a mim nesta busca de auxílio.

Deus, nosso Pai do Céu: seja feita a Vossa vontade. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Jesus Crucificado, tende piedade de mim.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/precious-blood-novena.png""",
        tags = listOf("health")
    )
}