package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData080 {
    val novena: Novena = Novena(
        id = """novena-a-san-gregorio-magno""",
        title = LocalizedText("""Novena a San Gregorio Magno""", """St Gregory the Great Novena""", """Novena a São Gregório Magno"""),
        patron = LocalizedText("""Santo patrón de los músicos, los cantores, los estudiantes y los maestros""", """Patron saint of musicians, singers, students, and teachers""", """Novena a São Gregório Magno"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a San Gregorio Magno, papa medieval de finales del siglo VI, por los músicos, los cantores, los estudiantes y los maestros.""", """St Gregory the Great was a medieval Pope in the late 500s. Pray this novena for musicians, singers, students, and teachers.""", """Santo padroeiro de músicos, cantores, estudantes e professores"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

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

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

San Gregorio, te llaman Magno por tu fidelidad a Cristo y a su Iglesia y por tu amor a la sagrada liturgia.

Ayudaste a los necesitados y buscaste la conversión de Inglaterra a Cristo.

Por tu intercesión, alcánzanos las gracias que necesitamos para ser firmes en la fe como lo fuiste tú.

Te pedimos especialmente tus oraciones por nuestra parroquia, nuestros sacerdotes y el Papa.

En particular, te pido la gracia de

(menciona tu petición aquí…)

Ayúdame a vivir una vida santa y a tener una muerte feliz, para que pueda gozar de la felicidad eterna en el cielo. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

San Gregorio Magno,

ruega por nosotros.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

St Gregory, you are called the Great because of your faithfulness to Christ and His Church and love of the sacred liturgy.

You helped those in need and sought the conversion of England for Christ.

Through your intercession, please obtain for us the graces we need to be stalwart in faith the way you were.

We especially ask for your prayers for our parish, our priests, and the Pope.

In particular, I ask for the grace of

(mention request here…)

Help me to live a holy life, die a happy death, so that I might enjoy eternal happiness in Heaven. Amen.

Our Father

Hail Mary

Glory Be

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

St. Gregory the Great,

pray for us.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

São Gregório, sois chamado Magno por causa da vossa fidelidade a Cristo e à Sua Igreja e do vosso amor à sagrada liturgia.

Ajudastes os necessitados e procurastes a conversão da Inglaterra para Cristo.

Pela vossa intercessão, alcançai-nos as graças de que precisamos para sermos firmes na fé como vós fostes.

Pedimos especialmente as vossas orações pela nossa paróquia, pelos nossos sacerdotes e pelo Papa.

Em particular, peço a graça de

(mencione o pedido aqui…)

Ajudai-me a viver uma vida santa e a ter uma morte feliz, para que eu possa gozar da felicidade eterna no Céu. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

São Gregório Magno,

rogai por nós.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/pope-st-gregory-the-great-novena.png""",
        tags = listOf("love")
    )
}