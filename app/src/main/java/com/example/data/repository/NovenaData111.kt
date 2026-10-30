package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData111 {
    val novena: Novena = Novena(
        id = """novena-a-san-luis-rey-de-francia""",
        title = LocalizedText("""Novena a San Luis Rey de Francia""", """St Louis King of France Novena""", """Novena a São Luís IX, Rei de França"""),
        patron = LocalizedText("""Santo patrón de Francia, la Tercera Orden de San Francisco, los barberos, los destiladores y los soldados""", """Patron saint of France, the Third Order of St. Francis, barbers, distillers, and soldiers""", """Novena a São Luís IX, Rei de França"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""San Luis IX fue rey de Francia de 1226 a 1270. Reza esta novena por Francia, la Tercera Orden de San Francisco, los barberos, los destiladores y los soldados.""", """St. Louis IX was King of France from 1226 to 1270. Pray this novena for France, the Third Order of St. Francis, barbers, distillers, and soldiers.""", """Santo padroeiro de a França, a Ordem Terceira de São Francisco, os barbeiros, os destiladores e os soldados"""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

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

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Alcánzame la gracia de seguir tus pasos, de ser siempre un hijo fiel de Jesús y de guardar, como lo hizo San Luis, las enseñanzas de la Santa Iglesia de Cristo.

Sé mi guía y protector, para que nunca me aparte del camino de la virtud, sino que crezca cada día en santidad y perfección, y merezca por fin ser contado entre los Santos del Cielo.

Amén.

Oh Dios, que elevaste a tu Confesor, el bienaventurado Luis, de un reino terrenal a la gloria de tu Reino celestial, concédenos, te rogamos, que por sus méritos e intercesión seamos herederos del Rey de reyes, tu Hijo nuestro Señor Jesucristo, que vive y reina por los siglos de los siglos.

Amén.

San Luis, alcánzame especialmente el favor que pido en esta novena,

(menciona tu petición aquí…)

Letanía de San Luis de Francia

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Dios Padre celestial,

ten piedad de nosotros.

Dios Hijo, Redentor del mundo,

ten piedad de nosotros.

Dios Espíritu Santo,

ten piedad de nosotros.

Santísima Trinidad, un solo Dios,

ten piedad de nosotros.

Santa María,

ruega por nosotros. *

Santa Madre de Dios, *

Santa Virgen de las vírgenes, *

San Luis de Francia, *

San Luis, descendiente de padres piadosos, *

San Luis, protector constante de los hijos de Dios, *

San Luis, maestro firme de la piedad, *

San Luis, verdadero modelo de virtud cristiana, *

San Luis, fiel confesor de Cristo vivo, *

San Luis, rey que soportaste las humillaciones, *

San Luis, defensor decidido de Cristo glorificado, *

San Luis, verdadero mártir de la carne por la mortificación, *

San Luis, que aborreciste el orgullo y los honores del mundo, *

San Luis, salvador de almas, *

San Luis, amante ardiente de Dios, *

San Luis, amigo bondadoso de tus enemigos, *

San Luis, absorto en la oración a Dios, *

San Luis, esperanza de los pecadores, *

San Luis, dador de dones, *

San Luis, fundador de instituciones de caridad para los afligidos, *

San Luis, generoso dador de limosnas, *

San Luis, dispensador pródigo de riquezas, *

San Luis, guardián de los santos lugares de peregrinación, *

San Luis, que aborreciste a los inmoderados, *

San Luis, protector de las viudas y los huérfanos, *

San Luis, defensor del sepulcro de nuestro Señor Jesucristo, *

San Luis, vencedor de los sarracenos, *

San Luis, protector de los cautivos de los paganos, *

San Luis, que convertiste a los incrédulos a la fe cristiana, *

San Luis, que visitabas los hospitales y repartías favores a los enfermos, *

San Luis, sanador de los enfermos, *

San Luis, intercesor y patrono del rey de Francia, *

San Luis, por quien los que acuden a ti obtienen el auxilio infalible de Dios, *

San Luis, por cuya intercesión se curan milagrosamente diversas enfermedades, *

Cordero de Dios, que quitas el pecado del mundo,

perdónanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

escúchanos, Señor.

Cordero de Dios, que quitas el pecado del mundo,

ten piedad de nosotros.

Cristo, óyenos.

Cristo, escúchanos.

Señor, ten piedad de nosotros.

Cristo, ten piedad de nosotros.

Señor, ten piedad de nosotros.

Padre nuestro.

Ave María.

Gloria al Padre.

V. Ruega por nosotros, San Luis,

R. Para que seamos dignos de alcanzar las promesas de Cristo.

Oremos:

Oh Señor, Rey de reyes, Jesucristo, que amaste a San Luis y lo llevaste al Reino celestial, concédenos que, por su intercesión y sus buenas obras, participemos de su gloria por toda la eternidad. Tú que vives y reinas por los siglos de los siglos. Amén.

San Luis, Rey de Francia, ruega por nosotros.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Obtain for me the grace to follow in thy footsteps, to be always a dutiful child of Jesus, and to observe as St. Louis did the teachings of Christ’s Holy Church.

Be my guide and protector, so that I may never stray from the path of virtue but increase daily in holiness and perfection, and finally merit to be numbered among the Saints in Heaven.

Amen.

O God, Who didst exalt blessed Louis Thy Confessor from an earthly realm to the glory of Thy Heavenly kingdom, grant, we pray Thee, that by his merits and intercession we may be made heirs of the King of Kings, Thy Son our Lord Jesus Christ, Who liveth and reigneth for ever and ever.

Amen.

St. Louis, please especially obtain the favor I ask in this novena,

(mention request here…)

Litany of Saint Louis of France

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Christ hear us.

Christ, graciously hear us.

God the Father of Heaven,

Have mercy on us.

God the Son, Redeemer of the world,

Have mercy on us.

God the Holy Ghost,

Have mercy on us.

Holy Trinity, one God,

Have mercy on us.

Holy Mary,

Pray for us. *

Holy Mother of God, *

Holy Virgin of virgins, *

Saint Louis of France, *

St. Louis, scion of devout parents, *

St. Louis, constant protector of the children of God, *

St. Louis, steadfast teacher of piety, *

St. Louis, true model of Christian virtue, *

St. Louis, faithful confessor of the living Christ, *

St. Louis, kingly bearer of humiliations, *

St. Louis, staunch defender of the glorified Christ, *

St. Louis, true martyr of the flesh by mortification, *

St. Louis, detester of worldly pride and honor, *

St. Louis, saviour of souls, *

St. Louis, ardent lover of God, *

St. Louis, kind friend of enemies, *

St. Louis, rapt in prayer to God, *

St. Louis, hope of sinners, *

St. Louis, giver of gifts, *

St. Louis, founder of charitable institutions for the afflicted, *

St. Louis, generous giver of alms, *

St. Louis, lavish dispenser of riches, *

St. Louis, guard of the holy places of pilgrimage, *

St. Louis, detester of immoderate people, *

St Louis, protector of widows and orphans, *

St. Louis, defender of the sepulchre of Our Lord Jesus Christ, *

St. Louis, victor over the Saracens, *

St. Louis, protector of those in pagan slavery, *

St. Louis, converter of unbelievers to the Christian faith, *

St. Louis, visitor of hospitals and dispenser of favors to the infirm, *

St. Louis, healer of the sick, *

St. Louis, intercessor and patron of the French King, *

St. Louis, from whom those who flee to thee obtain the infallible help God, *

St. Louis, at whose request divers diseases are miraculously cured, *

Lamb of God, Who takest away the sins of the world,

Spare us, O Lord.

Lamb of God, Who takest away the sins of the world,

Graciously hear us, O Lord.

Lamb of God, Who takest away the sins of the world,

Have mercy on us.

Christ hear us.

Christ, graciously hear us.

Lord have mercy on us.

Christ have mercy on us.

Lord have mercy on us.

Our Father

Hail Mary

Glory Be

V. Pray for us, Saint Louis,

R. That we may be made worthy of the promises of Christ.

Let us pray:

O Lord, King of kings, Jesus Christ, Who didst love Saint Louis and didst lead him into the heavenly Kingdom: grant that by his intercession and good works, we may participate in his glory for all eternity. Who livest and reignest world without end. Amen.

St Louis, King of France, pray for us.

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Alcançai-me a graça de seguir os vossos passos, de ser sempre um filho obediente de Jesus e de observar, como fez São Luís, os ensinamentos da Santa Igreja de Cristo.

Sede o meu guia e protetor, para que eu nunca me desvie do caminho da virtude, mas cresça cada dia em santidade e perfeição e mereça, finalmente, ser contado entre os Santos no Céu.

Amém.

Ó Deus, que elevastes o bem-aventurado Luís, Vosso Confessor, de um reino terreno à glória do Vosso Reino Celeste, concedei-nos, Vos pedimos, que pelos seus méritos e intercessão sejamos feitos herdeiros do Rei dos reis, Vosso Filho, Nosso Senhor Jesus Cristo, que vive e reina para todo o sempre.

Amém.

São Luís, alcançai-me especialmente o favor que peço nesta novena,

(mencione o pedido aqui…)

Ladainha de São Luís de França

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Deus Pai do Céu,

tende piedade de nós.

Deus Filho, Redentor do mundo,

tende piedade de nós.

Deus Espírito Santo,

tende piedade de nós.

Santíssima Trindade, que sois um só Deus,

tende piedade de nós.

Santa Maria,

rogai por nós.

Santa Mãe de Deus, rogai por nós.

Santa Virgem das virgens, rogai por nós.

São Luís de França, rogai por nós.

São Luís, rebento de pais piedosos, rogai por nós.

São Luís, constante protetor dos filhos de Deus, rogai por nós.

São Luís, firme mestre de piedade, rogai por nós.

São Luís, verdadeiro modelo de virtude cristã, rogai por nós.

São Luís, fiel confessor de Cristo vivo, rogai por nós.

São Luís, que suportastes as humilhações como rei, rogai por nós.

São Luís, firme defensor de Cristo glorificado, rogai por nós.

São Luís, verdadeiro mártir da carne pela mortificação, rogai por nós.

São Luís, que detestastes o orgulho e as honras do mundo, rogai por nós.

São Luís, salvador de almas, rogai por nós.

São Luís, ardente amante de Deus, rogai por nós.

São Luís, amigo bondoso dos inimigos, rogai por nós.

São Luís, arrebatado em oração a Deus, rogai por nós.

São Luís, esperança dos pecadores, rogai por nós.

São Luís, doador de dons, rogai por nós.

São Luís, fundador de instituições de caridade para os aflitos, rogai por nós.

São Luís, generoso dador de esmolas, rogai por nós.

São Luís, pródigo dispensador de riquezas, rogai por nós.

São Luís, guarda dos santos lugares de peregrinação, rogai por nós.

São Luís, que detestastes os imoderados, rogai por nós.

São Luís, protetor das viúvas e dos órfãos, rogai por nós.

São Luís, defensor do sepulcro de Nosso Senhor Jesus Cristo, rogai por nós.

São Luís, vencedor dos sarracenos, rogai por nós.

São Luís, protetor dos que estão na escravidão pagã, rogai por nós.

São Luís, que convertestes os incrédulos à fé cristã, rogai por nós.

São Luís, visitador de hospitais e dispensador de favores aos enfermos, rogai por nós.

São Luís, que curais os doentes, rogai por nós.

São Luís, intercessor e patrono do Rei de França, rogai por nós.

São Luís, por quem os que a vós recorrem obtêm o auxílio infalível de Deus, rogai por nós.

São Luís, a cujo pedido diversas doenças são milagrosamente curadas, rogai por nós.

Cordeiro de Deus, que tirais o pecado do mundo,

perdoai-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

ouvi-nos, Senhor.

Cordeiro de Deus, que tirais o pecado do mundo,

tende piedade de nós.

Jesus Cristo, ouvi-nos.

Jesus Cristo, atendei-nos.

Senhor, tende piedade de nós.

Jesus Cristo, tende piedade de nós.

Senhor, tende piedade de nós.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

V. Rogai por nós, São Luís,

R. Para que sejamos dignos das promessas de Cristo.

Oremos:

Ó Senhor Jesus Cristo, Rei dos reis, que amastes São Luís e o conduzistes ao Reino celeste, concedei que, pela sua intercessão e boas obras, participemos da sua glória por toda a eternidade. Vós que viveis e reinais pelos séculos dos séculos. Amém.

São Luís, Rei de França, rogai por nós.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/st-louis-king-of-france-novena.png""",
        tags = listOf("health")
    )
}