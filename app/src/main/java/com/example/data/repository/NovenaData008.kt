package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData008 {
    val novena: Novena = Novena(
        id = """novena-a-la-santa-cruz""",
        title = LocalizedText("""Novena a la Santa Cruz""", """Holy Cross Novena""", """Novena à Santa Cruz"""),
        patron = LocalizedText("""Santo patrón de la Cruz""", """Patron saint of the Cross""", """Novena à Santa Cruz"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""La Exaltación de la Santa Cruz celebra el hallazgo de la Vera Cruz de Cristo por santa Elena. Reza esta Novena a la Santa Cruz para acercarte a Cristo Crucificado.""", """The Exaltation of the Holy Cross celebrates the discovery by St Helena of the True Cross of Christ. Pray this Holy Cross Novena to draw closer to Christ Crucified.""", """A Exaltação da Santa Cruz celebra a descoberta da verdadeira Cruz de Cristo por Santa Helena. Reze esta Novena à Santa Cruz para se aproximar de Cristo Crucificado."""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

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

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

Jesús, que por Tu ardiente amor a nosotros quisiste ser crucificado y derramar Tu Preciosísima Sangre para la redención y salvación de nuestras almas, míranos y concédenos la petición que te hacemos por

(menciona tu petición aquí…)

Confiamos plenamente en Tu Misericordia.

Límpianos del pecado con Tu Gracia, santifica nuestro trabajo, danos a nosotros y a todos nuestros seres queridos el pan de cada día, alivia el peso de nuestros sufrimientos, bendice a nuestras familias y concede a las naciones, tan duramente afligidas, Tu Paz, que es la única paz verdadera, para que, obedeciendo Tus Mandamientos, lleguemos por fin a la gloria del Cielo.

Señor, exaltamos Tu Santa Cruz, en la que redimiste al mundo. Concédenos participar en Tus sufrimientos, uniendo nuestras cruces a la Tuya, para que compartamos para siempre Tu gloria celestial. Amén.

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

Jesus, Who because of Your burning love for us willed to be crucified and to shed Your Most Precious Blood for the redemption and salvation of our souls, look down upon us and grant the petition we ask for

(mention request here…)

We trust completely in Your Mercy.

Cleanse us from sin by Your Grace, sanctify our work, give us and all those who are dear to us our daily bread, lighten the burden of our sufferings, bless our families, and grant to the nations, so sorely afflicted, Your Peace, which is the only true peace, so that by obeying Your Commandments we may come at last to the glory of Heaven.

Lord, we exalt Your Holy Cross, upon which You redeemed the world. Grant us a share in Your sufferings, uniting our crosses to Yours, so that we may share in Your Heavenly glory forever. Amen.

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Jesus, que pelo Vosso ardente amor por nós quisestes ser crucificado e derramar o Vosso preciosíssimo Sangue para a redenção e salvação das nossas almas, olhai para nós e concedei-nos a graça que pedimos

(mencione o pedido aqui…)

Confiamos plenamente na Vossa Misericórdia.

Purificai-nos do pecado pela Vossa Graça, santificai o nosso trabalho, dai a nós e a todos os que nos são queridos o pão de cada dia, aliviai o peso dos nossos sofrimentos, abençoai as nossas famílias e concedei às nações, tão duramente provadas, a Vossa Paz, que é a única paz verdadeira, para que, obedecendo aos Vossos Mandamentos, cheguemos por fim à glória do Céu.

Senhor, exaltamos a Vossa Santa Cruz, na qual redimistes o mundo. Concedei-nos participar dos Vossos sofrimentos, unindo as nossas cruzes à Vossa, para que participemos da Vossa glória celeste para sempre. Amém.

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/exaltation-of-the-holy-cross-novena.png""",
        tags = listOf("love", "money", "family", "peace")
    )
}