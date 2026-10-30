package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData140 {
    val novena: Novena = Novena(
        id = """novena-a-santa-angela-merici""",
        title = LocalizedText("""Novena a Santa Ángela Merici""", """St Angela Merici Novena""", """Novena a Santa Ângela Merici"""),
        patron = LocalizedText("""Santo patrón de los enfermos, las personas con discapacidad, la pérdida de los padres, el valor, la fortaleza y la determinación""", """Patron saint of sickness, handicapped people, loss of parents, courage, strength, and determination""", """Novena a Santa Ângela Merici"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Reza esta novena a Santa Ángela Merici, santa italiana que fundó las Ursulinas y dedicó su vida a educar a las niñas, por los enfermos, las personas con discapacidad, la pérdida de los padres, el valor, la fortaleza y la determinación.""", """St Angela Merici was an Italian saint who founded the Ursulines and dedicated her life to educating girls. Pray this novena for sickness, handicapped people, loss of parents, courage, strength, and determination.""", """Santo padroeiro de doentes, pessoas com deficiência, perda dos pais, coragem, força e determinação"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

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

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Que la santa virgen Santa Ángela Merici nos encomiende siempre a tu gracia, oh Señor, te lo pedimos, para que, siguiendo el ejemplo de su caridad y prudencia, permanezcamos firmes en tu enseñanza. Por nuestro Señor Jesucristo, tu Hijo, que vive y reina contigo en la unidad del Espíritu Santo, y es Dios, por los siglos de los siglos.

Amén.

Santa Ángela Merici, tú respondiste con generosidad y alegría a la llamada de Dios en tu vida para formar a las niñas en la santa fe católica y darles las habilidades que necesitarían en su vida.

Serviste con bondad a los necesitados y a los pobres, y mostraste un amor heroico a todas las personas confiadas a tu cuidado.

Ruega por nosotros, para que tengamos la gracia de servir a Dios tan fielmente como tú lo hiciste, y alcánzanos las gracias que pedimos en esta novena, especialmente (menciona tu petición aquí…)

Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santa Ángela Merici, ruega por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

May the holy Virgin Saint Angela Merici always commend us to your grace, O Lord, we pray, that, following the example of her charity and prudence, we may hold fast to Thy teaching, through our Lord Jesus Christ, Thy Son, who lives and reigns with Thee in the unity of the Holy Spirit, one God, for ever and ever.

Amen.

St Angela Merici, you generously and joyfully answered God’s call in your life to form girls in the holy Catholic Faith and equip them with skills they would need in their lives.

You graciously served the needy and those in poverty, and showed heroic love to all people put under your care.

Pray for us, for the grace to serve God as faithfully as you did, and obtain for us the graces we ask in this novena, especially (mention request here…)

Amen.

Our Father

Hail Mary

Glory Be

St Angela Merici, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Nós Vos pedimos, Senhor, que a santa virgem Ângela Merici nos recomende sempre à Vossa graça, para que, seguindo o exemplo da sua caridade e prudência, guardemos fielmente o Vosso ensinamento, por nosso Senhor Jesus Cristo, Vosso Filho, que convosco vive e reina na unidade do Espírito Santo, um só Deus, por todos os séculos dos séculos.

Amém.

Santa Ângela Merici, vós respondestes com generosidade e alegria ao chamado de Deus em vossa vida para formar as meninas na santa fé católica e prepará-las com as habilidades de que precisariam em suas vidas.

Servistes com bondade os necessitados e os pobres, e mostrastes um amor heroico a todas as pessoas confiadas aos vossos cuidados.

Rogai por nós, para que alcancemos a graça de servir a Deus tão fielmente como vós o servistes, e obtende-nos as graças que pedimos nesta novena, especialmente (mencione o pedido aqui…)

Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santa Ângela Merici, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-angela-merici-novena.png""",
        tags = listOf("love")
    )
}