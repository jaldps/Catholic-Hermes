package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData011 {
    val novena: Novena = Novena(
        id = """novena-a-la-virgen-de-la-caridad-de-cobre""",
        title = LocalizedText("""Novena a la Virgen de la Caridad del Cobre""", """Our Lady of Charity Novena""", """Novena a Nossa Senhora da Caridade do Cobre"""),
        patron = LocalizedText("""Santo patrón de Cuba, el pueblo cubano y los mineros de sal y cobre.""", """Patron saint of Cuba, Cuban peoples, salt and copper miners""", """Novena a Nossa Senhora da Caridade do Cobre"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Nuestra Señora de la Caridad, también conocida como la Virgen de la Caridad del Cobre, es una advocación e imagen de Nuestra Señora en Cuba. Recen esta novena por Cuba, el pueblo cubano y los mineros de sal y cobre.""", """Our Lady of Charity, known also as La Virgen de la Caridad del Cobre, is a title and image of Our Lady in Cuba. Pray this novena for Cuba, Cuban peoples, salt and copper miners.""", """Santo padroeiro de Cuba, o povo cubano e os mineiros de sal e de cobre"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

¡Dios te salve! ¡Cuánto se alegra mi alma, amantísima Virgen, con los dulces recuerdos que en mí despierta esta salutación! Llénase de júbilo mi corazón al pronunciar el Ave María, para acompañar el gozo que llenó tu espíritu al escucharla de boca del Ángel, congratulándose así de la elección que de tí hizo el Omnipotente para darnos al Señor.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

¡Dios te salve! ¡Cuánto se alegra mi alma, amantísima Virgen, con los dulces recuerdos que en mí despierta esta salutación! Llénase de júbilo mi corazón al pronunciar el Ave María, para acompañar el gozo que llenó tu espíritu al escucharla de boca del Ángel, congratulándose así de la elección que de tí hizo el Omnipotente para darnos al Señor.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

¡María, nombre santo! Dígnate, amabilísima Madre, sellar con tu nombre el memorial de nuestras súplicas, dándonos el consuelo de que tu Hijo, Jesús, las atienda benignamente para alcanzar pleno convencimiento en la práctica de nuestros deberes religiosos, sólida confirmación en las virtudes cristianas y continuas ansias de nuestra eterna salvación.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

¡María, nombre santo! Dígnate, amabilísima Madre, sellar con tu nombre el memorial de nuestras súplicas, dándonos el consuelo de que tu Hijo, Jesús, las atienda benignamente para alcanzar pleno convencimiento en la práctica de nuestros deberes religiosos, sólida confirmación en las virtudes cristianas y continuas ansias de nuestra eterna salvación.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Llena de Gracia, ¡Oh dulce Madre! Dios te salve, María, sagrario riquísimo en que descansó corporalmente la plenitud de la Divinidad: a tus pies nos presentamos hoy para que la gracia de Dios se difunda abundantemente en nuestras pobres almas, las purifique, las engrandezca y cada día aumente más en ellos el verdadero amor a Dios y a nuestros hermanos.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Llena de Gracia, ¡Oh dulce Madre! Dios te salve, María, sagrario riquísimo en que descansó corporalmente la plenitud de la Divinidad: a tus pies nos presentamos hoy para que la gracia de Dios se difunda abundantemente en nuestras pobres almas, las purifique, las engrandezca y cada día aumente más en ellos el verdadero amor a Dios y a nuestros hermanos.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

El Señor es contigo, ¡Oh Santísima Virgen! Aquel inmenso Señor, que por su esencia está en todas las cosas, está en tí y contigo de un modo muy superior. Madre mía, venga por tí a nosotros. Pero ¿cómo ha de venir a un corazón lleno de tanta suciedad. Aquel Señor que para hacerte habitación suya quiso, con tal prodigio, que no perdieses, siendo madre, tu virginidad? ¡Oh muera en nosotros toda impureza!

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

El Señor es contigo, ¡Oh Santísima Virgen! Aquel inmenso Señor, que por su esencia está en todas las cosas, está en tí y contigo de un modo muy superior. Madre mía, venga por tí a nosotros. Pero ¿cómo ha de venir a un corazón lleno de tanta suciedad. Aquel Señor que para hacerte habitación suya quiso, con tal prodigio, que no perdieses, siendo madre, tu virginidad? ¡Oh muera en nosotros toda impureza!

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Bendita tú eres entre todas las mujeres. Tú eres, oh Santísima Virgen María, la gloria de Jerusalén, tú eres la alegría de Israel, tú eres el honor de nuestro pueblo. Si por una mujer, Eva, tantas lágrimas se derramó en el mundo, por ti nos llegó la redención. Por esto, tú serás siempre bendita. Alcánzanos una fe viva y operante para considerar e imitar las grandes obras que en ti y por ti obró Dios.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Bendita tú eres entre todas las mujeres. Tú eres, oh Santísima Virgen María, la gloria de Jerusalén, tú eres la alegría de Israel, tú eres el honor de nuestro pueblo. Si por una mujer, Eva, tantas lágrimas se derramó en el mundo, por ti nos llegó la redención. Por esto, tú serás siempre bendita. Alcánzanos una fe viva y operante para considerar e imitar las grandes obras que en ti y por ti obró Dios.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Bendito es el fruto de tu vientre, Jesús. Deploramos grandemente, purísima Virgen y amantísima Madre, que hayamos cometido tantos pecados, sabiendo que ellos hicieron morir en tu cruz a tu Hijo. Sea el fruto de nuestra oración, que no cesamos de llorarlos hasta poder bendecir eternamente a Jesús, fruto bendito de tu vientre virginal.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Bendito es el fruto de tu vientre, Jesús. Deploramos grandemente, purísima Virgen y amantísima Madre, que hayamos cometido tantos pecados, sabiendo que ellos hicieron morir en tu cruz a tu Hijo. Sea el fruto de nuestra oración, que no cesamos de llorarlos hasta poder bendecir eternamente a Jesús, fruto bendito de tu vientre virginal.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Santa María, Madre de Dios. Tu mayor título de grandeza, tu mayor dignidad, oh María es haber sido elegida para Madre de Jesucristo, Hijo de Dios. De esta elección divina proceden todas tus gracias y prerrogativas. No olvides nunca que también fuiste designada por tu Divino Hijo, al pie de la cruz, como Madre espiritual nuestra. Que nunca nos falten fuerzas para mostrarnos como dignos hijos tuyos.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Santa María, Madre de Dios. Tu mayor título de grandeza, tu mayor dignidad, oh María es haber sido elegida para Madre de Jesucristo, Hijo de Dios. De esta elección divina proceden todas tus gracias y prerrogativas. No olvides nunca que también fuiste designada por tu Divino Hijo, al pie de la cruz, como Madre espiritual nuestra. Que nunca nos falten fuerzas para mostrarnos como dignos hijos tuyos.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Ruega por nosotros, pecadores. En ti Virgen María, como en alcázar nos refugiamos. Aunque el vértigo de la vida y los enemigos del alma nos hayan despojado o puedan despojarnos de las preciosas vestiduras de la gracia, alejándonos de ti y de tu amado Hijo, nunca nos cierres las puertas de Sagrado Corazón.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Ruega por nosotros, pecadores. En ti Virgen María, como en alcázar nos refugiamos. Aunque el vértigo de la vida y los enemigos del alma nos hayan despojado o puedan despojarnos de las preciosas vestiduras de la gracia, alejándonos de ti y de tu amado Hijo, nunca nos cierres las puertas de Sagrado Corazón.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

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

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Ahora y en la hora de nuestra muerte . Siempre estamos expuestos a perder la gracia de Dios y condenarnos. Haced, Santísima Virgen María, que por vuestra intercesión nunca perdamos el favor de Dios; que en esta difícil lucha por la vida encontremos en ti la protección maternal que tanto necesitamos y una Abogada en la hora de nuestra muerte.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Ruega por nosotros,
 Santa Madre de Dios,
 para que seamos
 dignos de alcanzar las promesas
 de Nuestro Señor Jesucristo.

Amén

Acto de Contrición

Yo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión: por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los Ángeles, a los Santos y a vosotros, hermanos, que intercedáis por mi ante Dios, nuestro Señor.

Oración para todos los días

Acordaos, oh piadosísima Virgen María!, que jamás se ha oído decir que ninguno de los que haya acudido a Vos, implorado vuestra asistencia y reclamado vuestro socorro, haya sido abandonado de Vos. Animado con esta confianza, a Vos también acudo, oh Virgen, Madre de la vírgenes, y aunque gimiendo bajo el peso de mis pecados me atrevo a comparecer ante Vuestra Santísima presencia soberana. No desechéis oh purísima Madre de Dios mis humildes súplicas, antes bien, escuchadlas favorablemente.

Así sea.

(menciona tu petición aquí…)

Ahora y en la hora de nuestra muerte . Siempre estamos expuestos a perder la gracia de Dios y condenarnos. Haced, Santísima Virgen María, que por vuestra intercesión nunca perdamos el favor de Dios; que en esta difícil lucha por la vida encontremos en ti la protección maternal que tanto necesitamos y una Abogada en la hora de nuestra muerte.

Pídase el favor que se desea conseguir.

Oración Final para recitar todos los días:Oh, Señora mía, Oh Madre mía, yo me entrego del todo a tí; Y en prueba de mi filial afecto, te consagro en este día mis ojos, mis oídos, mi lengua, mi corazón; en una palabra, todo mi ser. Ya que soy tuyo, Oh Madre de piedad, guárdame y defiéndeme como cosa y posesión tuya.

Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Holy Virgin, Our Lady of Charity, with happiness and humility I come to your feet!

Virgin of Miracles, you cure the sick, give hope where there is despair, give strength to the afflicted, preserve from disgrace our families, protect the youth, and guard our children.

No one can explain all the miracles and fortitude you give to the souls that come to you.

We, your children, thank you for all your graces.

(mention request here…)

Amen.

Memorare

Remember, O most gracious Virgin Mary, that never was it known that anyone who fled to thy protection, implored thy help, or sought thy intercession was left unaided.

Inspired with this confidence, I fly to thee, O Virgin of virgins, my Mother. To thee do I come, before thee I stand, sinful and sorrowful. O Mother of the Word Incarnate, despise not my petitions, but in thy mercy hear and answer me.

Amen.

Our Father
 Hail Mary
 Glory Be

Our Lady of Charity, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Santa Virgem, Nossa Senhora da Caridade, com alegria e humildade venho aos vossos pés!

Virgem dos Milagres, vós curais os enfermos, dais esperança onde há desespero, dais força aos aflitos, preservais as nossas famílias da desgraça, protegeis a juventude e guardais os nossos filhos.

Ninguém pode explicar todos os milagres e toda a fortaleza que dais às almas que recorrem a vós.

Nós, vossos filhos, vos agradecemos todas as vossas graças.

(mencione o pedido aqui…)

Amém.

Lembrai-vos

Lembrai-vos, ó piíssima Virgem Maria, que nunca se ouviu dizer que algum daqueles que têm recorrido à vossa proteção, implorado a vossa assistência e reclamado o vosso socorro fosse por vós desamparado.

Animado eu, pois, com igual confiança, a vós, ó Virgem entre todas singular, como a Mãe recorro, de vós me valho e, gemendo sob o peso dos meus pecados, me prostro aos vossos pés. Não desprezeis as minhas súplicas, ó Mãe do Verbo encarnado, mas dignai-vos de as ouvir propícia e de me alcançar o que vos rogo.

Amém.

Pai-Nosso.
 Ave-Maria.
 Glória ao Pai.

Nossa Senhora da Caridade, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-charity-novena.png""",
        tags = listOf("blessings", "love", "comfort")
    )
}