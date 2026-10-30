package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData013 {
    val novena: Novena = Novena(
        id = """novena-a-la-virgen-de-los-desamparados""",
        title = LocalizedText("""Novena a la Virgen de los Desamparados""", """Our Lady of the Forsaken Novena""", """Novena a Nossa Senhora dos Desamparados"""),
        patron = LocalizedText("""Santo patrón de enfermedades mentales, personas sin hogar, huérfanos, Valencia, Manila""", """Patron saint of mental illness, homeless, orphans, Valencia, Manila""", """Novena a Nossa Senhora dos Desamparados"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Recordatorios diarios + intenciones + oraciones grupales""", """Daily reminders + prayer intentions + group prayers""", """Santo padroeiro de os doentes mentais, os sem-teto, os órfãos, Valência, Manila"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

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

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Acordaos

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de Vos.

Animado con esta confianza,

a Vos también acudo,

oh Virgen de las vírgenes, Madre mía,

a Vos vengo,

ante Vos me presento pecador y afligido.

¡Oh Madre del Verbo Encarnado!

No desechéis mis súplicas,

sino que, en vuestra misericordia, escuchadme y respondedme.

(menciona tu petición aquí…)

Amén.

Oh Dios, que has tenido a bien bendecirnos con el favor de venerar a la Santísima Virgen María bajo el título de Madre de los Desamparados, y has querido conceder tanto poder a su intercesión que nadie sea abandonado al recurrir a su auxilio maternal.

Concédenos, te rogamos, a nosotros tus siervos, el privilegio de que todos los que nos ponemos bajo la protección de tan excelsa Madre, nunca seamos abandonados por tu Divina Misericordia, por Cristo, Nuestro Señor.

Amén.

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

¡Virgen de los Desamparados, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Memorare

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help,

and sought thy intercession,

was left unaided.

Inspired with this confidence,

I fly unto thee,

O Virgin of virgins, my Mother,

to thee I come,

before thee I stand sinful and sorrowful.

O Mother of the Word Incarnate!

despise not my petitions,

but, in thy mercy, hear and answer me.

(mention request here…)

Amen.

O God, who has deigned to bless us with the favor of venerating the Blessed Virgin Mary under the title of Mother of the Forsaken, and has willed to bestow so much power on her intercession that no one should be abandoned who has recourse to her motherly help!

Grant we beseech you to us, Your servants, the privilege that we all, who put ourselves under the protection of so exalted a Mother, may never be forsaken by your Divine Mercy, through Christ, Our Lord.

Amen.

Our Father

Hail Mary

Glory Be

Our Lady of the Forsaken, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, de igual confiança,

a vós, ó Virgem entre todas singular,

como a Mãe recorro,

de vós me valho

e, gemendo sob o peso dos meus pecados, me prostro a vossos pés.

Não desprezeis as minhas súplicas, ó Mãe do Verbo de Deus humanado,

mas dignai-vos de as ouvir propícia

e de me alcançar o que vos rogo.

(mencione o pedido aqui…)

Amém.

Ó Deus, que Vos dignastes conceder-nos a graça de venerar a Bem-aventurada Virgem Maria sob o título de Mãe dos Desamparados, e quisestes dar tanto poder à sua intercessão que ninguém que recorra ao seu auxílio materno fique abandonado!

Concedei, Vos suplicamos, a nós, Vossos servos, o privilégio de que todos nós, que nos colocamos sob a proteção de tão excelsa Mãe, jamais sejamos desamparados pela Vossa Divina Misericórdia. Por Cristo, nosso Senhor.

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Nossa Senhora dos Desamparados, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-the-forsaken-novena.png""",
        tags = listOf("blessings", "love", "health")
    )
}