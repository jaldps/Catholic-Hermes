package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData237 {
    val novena: Novena = Novena(
        id = """novena-del-acordaos""",
        title = LocalizedText("""Novena del Acordaos (la novena de emergencia de la Madre Teresa)""", """The Memorare Novena (Mother Teresa's Emergency Novena)""", """Novena do Lembrai-vos (a novena de emergência de Madre Teresa)"""),
        patron = LocalizedText("""Santo patrón de las necesidades urgentes y cualquier intención especial""", """Patron saint of urgent needs and any special intention""", """Novena do Lembrai-vos (a novena de emergência de Madre Teresa)"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""El Acordaos (Memorare) es una de las oraciones más queridas a la Santísima Virgen María, atribuida desde hace mucho a san Bernardo de Claraval. Santa Teresa de Calcuta la hizo famosa como su novena volante: nueve Acordaos por una necesidad urgente y un décimo en acción de gracias. Reza esta novena por cualquier necesidad, confiando en que jamás se ha oído decir que alguien que acudió a la protección de Nuestra Señora haya sido abandonado.""", """The Memorare is one of the best loved prayers to the Blessed Virgin Mary, long attributed to St. Bernard of Clairvaux. St. Teresa of Calcutta made it famous as her flying novena: nine Memorares for an urgent need and a tenth in thanksgiving. Pray this novena for any need, trusting that never was it known that anyone who fled to Our Lady’s protection was left unaided.""", """Santo padroeiro de as necessidades urgentes e qualquer intenção especial"""),
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

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

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

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

(menciona tu petición aquí…)

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

(mention request here…)

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

(mencione o pedido aqui…)

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
            NovenaDay(
                dayNumber = 10,
                theme = LocalizedText("""Día 10""", """Day 10""", """Dia 10"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Hoy reza el Acordaos en acción de gracias por el favor que has pedido a Nuestra Señora.

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Today pray the Memorare in thanksgiving for the favor you have asked of Our Lady.

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Hoje reze o Lembrai-vos em ação de graças pela graça que você pediu a Nossa Senhora.

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Hoy reza el Acordaos en acción de gracias por el favor que has pedido a Nuestra Señora.

Acordaos, oh piadosísima Virgen María,

que jamás se ha oído decir

que ninguno de los que han acudido a vuestra protección,

implorado vuestra asistencia

y reclamado vuestro socorro,

haya sido abandonado de vos.

Animado con esta confianza,

a vos también acudo, oh Madre, Virgen de las vírgenes,

y aunque gimiendo bajo el peso de mis pecados,

me atrevo a comparecer ante vuestra presencia soberana.

No desechéis, oh Madre de Dios, mis humildes súplicas,

antes bien escuchadlas y acogedlas benignamente.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Today pray the Memorare in thanksgiving for the favor you have asked of Our Lady.

Remember, O most gracious Virgin Mary,

that never was it known

that any one who fled to thy protection,

implored thy help

or sought thy intercession,

was left unaided.

Inspired by this confidence,

I fly unto thee, O Virgin of virgins, my Mother;

to thee do I come, before thee I stand, sinful and sorrowful;

O Mother of the Word Incarnate,

despise not my petitions,

but in thy mercy, hear and answer me.

Amen.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Hoje reze o Lembrai-vos em ação de graças pela graça que você pediu a Nossa Senhora.

Lembrai-vos, ó piíssima Virgem Maria,

que nunca se ouviu dizer

que algum daqueles que têm recorrido à vossa proteção,

implorado a vossa assistência

e reclamado o vosso socorro,

fosse por vós desamparado.

Animado eu, pois, com igual confiança,

a vós, ó Virgem entre todas singular, como a Mãe recorro,

de vós me valho e, gemendo sob o peso dos meus pecados,

me prostro aos vossos pés.

Não rejeiteis as minhas súplicas, ó Mãe do Filho de Deus humanado,

mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/memorare-novena.png""",
        tags = emptyList()
    )
}