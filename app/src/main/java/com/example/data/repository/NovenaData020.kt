package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData020 {
    val novena: Novena = Novena(
        id = """novena-a-los-santos-cosme-y-damian""",
        title = LocalizedText("""Novena a los Santos Cosme y Damián, médicos mártires""", """Sts Cosmas and Damian Novena""", """Novena aos Santos Cosme e Damião"""),
        patron = LocalizedText("""Santo patrón de los cirujanos, los médicos, los gemelos idénticos, los gemelos, los dentistas, los protectores de los niños, los barberos, los farmacéuticos, los veterinarios, los orfanatos, las guarderías, los confiteros, contra la hernia y contra la peste""", """Patron saint of surgeons, physicians, identical twins, twins, dentists, protectors of children, barbers, pharmacists, veterinarians, orphanages, day-care centers, confectioners, against hernia, against the plague""", """Novena aos Santos Cosme e Damião"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Recordatorios diarios + intenciones + oraciones grupales""", """Daily reminders + prayer intentions + group prayers""", """Santo padroeiro de os cirurgiões, os médicos, os gêmeos idênticos, os gêmeos, os dentistas, os protetores das crianças, os barbeiros, os farmacêuticos, os veterinários, os orfanatos, as creches, os confeiteiros, contra a hérnia, contra a peste"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

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

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Oh gloriosos mártires, Santos Cosme y Damián, médicos de las almas y de los cuerpos, que durante vuestra vida solo buscasteis hacer el bien a todos los que acudían a vosotros, escuchad mi humilde oración.

Vosotros ejercisteis la medicina no solo para sanar el cuerpo, sino para ganar almas para Cristo.

Por vuestra intercesión, que yo reciba la salud del alma y del cuerpo, y llegue a compartir con vosotros la gloria eterna en el cielo.

Vosotros, que servisteis a Dios sirviendo a los demás con vuestros conocimientos médicos, que rechazasteis todo pago por vuestros servicios y que finalmente disteis la vida por vuestra fe en Cristo, escuchad mis peticiones,

(menciona tu petición aquí…)

Ayudadme a imitar vuestra caridad, vuestro desprendimiento y vuestra fe inquebrantable. Rogad para que yo sea sanado de todas las enfermedades del cuerpo y del alma, según la santa voluntad de Dios.

Amén.

Oh Dios, te damos gracias por el admirable ejemplo de los Santos Cosme y Damián, que te sirvieron fielmente hasta la muerte.

Por su intercesión, concédenos la salud de la mente y del cuerpo, para que con corazón puro te glorifiquemos a ti, supremo médico y Salvador de las almas. Por Cristo nuestro Señor. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

¡Santos Cosme y Damián, rogad por nosotros!

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O glorious martyrs, Saints Cosmas and Damian, physicians of souls and bodies, who during life sought only to do good to all who came to you, hear my humble prayer.

You practiced medicine not merely to heal the body but to win souls for Christ.

Through your intercession, may I receive health of soul and body, and come to share eternal glory with you in Heaven.

You who served God by serving others through your medical skills, who refused payment for your services, and who ultimately gave your lives for your faith in Christ, please hear my petitions,

(mention request here…)

Help me to imitate your charity, your selflessness, and your unwavering faith. Pray that I may be healed of all illnesses of body and soul according to God’s holy will.

Amen

O God, we thank you for the wonderful example of Saints Cosmas and Damian, who served you faithfully even unto death.

Through their intercession, grant us health of mind and body, that with pure hearts we may glorify You, the supreme physician and Savior of souls. Through Christ our Lord. Amen.

Our Father

Hail Mary

Glory Be

Sts Cosmas and Damian, pray for us!

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó gloriosos mártires, Santos Cosme e Damião, médicos das almas e dos corpos, que durante a vida procurastes somente fazer o bem a todos os que vinham a vós, ouvi a minha humilde oração.

Vós exercestes a medicina não apenas para curar o corpo, mas para ganhar almas para Cristo.

Pela vossa intercessão, possa eu receber a saúde da alma e do corpo e chegar a partilhar convosco a glória eterna no Céu.

Vós que servistes a Deus servindo aos outros por meio da vossa arte médica, que recusastes pagamento pelos vossos serviços e que, por fim, destes a vida pela vossa fé em Cristo, ouvi os meus pedidos,

(mencione o pedido aqui…)

Ajudai-me a imitar a vossa caridade, o vosso desprendimento e a vossa fé inabalável. Rogai para que eu seja curado de todas as doenças do corpo e da alma, segundo a santa vontade de Deus.

Amém.

Ó Deus, nós Vos agradecemos o admirável exemplo dos Santos Cosme e Damião, que Vos serviram fielmente até a morte.

Pela sua intercessão, concedei-nos a saúde da mente e do corpo, para que, com o coração puro, possamos glorificar-Vos, supremo médico e Salvador das almas. Por Cristo, nosso Senhor. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Santos Cosme e Damião, rogai por nós!

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/sts-cosmas-and-damian-novena.png""",
        tags = listOf("health")
    )
}