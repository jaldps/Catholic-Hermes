package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData225 {
    val novena: Novena = Novena(
        id = """novena-de-la-asuncion""",
        title = LocalizedText("""Novena a la Asunción de la Virgen María""", """Assumption Novena""", """Novena da Assunção de Nossa Senhora"""),
        patron = LocalizedText("""Santo patrón de la salvación, la gracia de una buena muerte""", """Patron saint of salvation, grace of a happy death""", """Novena da Assunção de Nossa Senhora"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza la Novena de la Asunción y prepárate con María para la vida eterna. Pide la gracia de una muerte santa y honra a la Reina coronada en cuerpo y alma en el Cielo.""", """Prepare for eternal life with Mary. Pray the Assumption Novena now and receive the grace of a holy death. Honor the Queen crowned body and soul in Heaven.""", """Santo padroeiro de a salvação, a graça de uma boa morte"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

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

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Señor Jesucristo, tú destruiste el poder de la muerte y nos diste la esperanza de la vida eterna para el cuerpo y el alma.

Concediste a tu Madre un lugar especial en tu gloria, y no permitiste que la corrupción tocara su cuerpo.

Al alegrarnos por la Asunción de María, concédenos una confianza renovada en la victoria de la vida sobre la muerte.

Amén.

Santa Reina, me alegro de tu Asunción al Cielo.

Después de tu vida de amor y sufrimiento en la tierra, Dios te llevó a estar con él en la gloria para siempre.

Por tu Asunción, ayúdame a vencer el pecado y a llegar a ser el santo que Dios quiso que yo fuera.

Graba en mí la verdad de que la muerte es la puerta a la vida eterna con tu Hijo, y de que en la Resurrección mi cuerpo y mi alma se reunirán y serán glorificados en la alegría pura del Cielo.

Hasta ese gran día, mientras peregrino por esta vida, te pido tu poderosa intercesión en esta novena, especialmente por:

(menciona tu petición aquí…)

Cuando llegue la hora de mi muerte, sé mi intercesora y mi madre ante Jesús, para que él me lleve a ese inefable banquete contigo en la visión beatífica.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

Oh Reina asunta al Cielo, ruega por nosotros. Amén.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Lord Jesus Christ, You have destroyed the power of death and given the hope of eternal life for body and soul.

You granted Your Mother a special place in your glory, and did not allow decay to touch her body.

As we rejoice in the Assumption of Mary, grant us renewed confidence in the victory of life over death.

Amen.

Holy Queen, I rejoice in your Assumption to Heaven.

After your life of love and suffering on earth, God took you to be with Him in glory forever.

Through your Assumption, help me to overcome sin and become the saint that God has made me to be.

Impress upon me the truth that death is the gateway to eternal life with your Son, and that at the Resurrection my body and soul shall reunite and be glorified in the unalloyed joy of Heaven.

Until that great day, while on my pilgrimage of life, I ask for your powerful intercession in this novena, especially for,

(mention request here…)

When I face the hour of my death, be my intercessor and mother before Jesus, that He may bring me to that ineffable banquet with you in the Beatific Vision.

Our Father

Hail Mary

Glory Be

O Queen Assumed into Heaven, pray for us. Amen

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Senhor Jesus Cristo, Vós destruístes o poder da morte e nos destes a esperança da vida eterna para o corpo e a alma.

Concedestes à Vossa Mãe um lugar especial na Vossa glória e não permitistes que a corrupção tocasse o seu corpo.

Enquanto nos alegramos com a Assunção de Maria, concedei-nos uma confiança renovada na vitória da vida sobre a morte.

Amém.

Santa Rainha, eu me alegro com a vossa Assunção ao Céu.

Depois da vossa vida de amor e sofrimento na terra, Deus vos levou para estar com Ele na glória para sempre.

Pela vossa Assunção, ajudai-me a vencer o pecado e a tornar-me o santo que Deus me criou para ser.

Gravai em mim a verdade de que a morte é a porta para a vida eterna com o vosso Filho, e de que, na Ressurreição, o meu corpo e a minha alma se reunirão e serão glorificados na alegria pura do Céu.

Até esse grande dia, enquanto estou na peregrinação desta vida, peço a vossa poderosa intercessão nesta novena, especialmente por

(mencione o pedido aqui…)

Quando eu enfrentar a hora da minha morte, sede minha intercessora e mãe diante de Jesus, para que Ele me conduza àquele inefável banquete convosco na Visão Beatífica.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Ó Rainha elevada ao Céu, rogai por nós. Amém.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/assumption-novena.png""",
        tags = listOf("love")
    )
}