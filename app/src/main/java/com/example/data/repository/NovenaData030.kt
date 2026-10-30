package com.example.data.repository

import com.example.data.models.LocalizedText
import com.example.data.models.Novena
import com.example.data.models.NovenaDay

object NovenaData030 {
    val novena: Novena = Novena(
        id = """novena-a-nuestra-senora-de-la-buena-salud""",
        title = LocalizedText("""Novena a Nuestra Señora de la Buena Salud (Vailankanni)""", """Our Lady of Good Health Novena""", """Novena a Nossa Senhora da Saúde (Velankanni)"""),
        patron = LocalizedText("""Santo patrón de la salud""", """Patron saint of health""", """Novena a Nossa Senhora da Saúde (Velankanni)"""),
        category = LocalizedText("""Novenas""", """Novenas""", """Novenas"""),
        description = LocalizedText("""Nuestra Señora de la Buena Salud se apareció a dos niños en la India en el siglo XVI y salvó un barco mercante portugués en el siglo XVII, y allí se construyó una capilla en su honor. Reza esta novena por la curación y la protección de la salud.""", """Our Lady of Good Health appeared to two boys in India the 16th century and saved a Portuguese merchant ship in the 17th century in India, where a chapel was then built in her honor. Pray this novena for healing and protection of health.""", """Nossa Senhora da Saúde apareceu a dois meninos na Índia no século XVI e salvou um navio mercante português no século XVII, e ali foi construída uma capela em sua honra. Reze esta novena pela cura e pela proteção da saúde."""),
        miraculousHistory = LocalizedText("", "", ""),
        promises = LocalizedText("", "", ""),
        days = listOf(
            NovenaDay(
                dayNumber = 1,
                theme = LocalizedText("""Día 1""", """Day 1""", """Dia 1"""),
                scripture = "",
                meditation = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

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

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                prayer = LocalizedText("""En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.

¡Oh Virgen Santísima!

Fuiste elegida desde toda la eternidad por la adorabilísima Trinidad para ser la purísima Madre de Jesús.

Permíteme, a mí, tu humilde y devoto servidor, recordarte el gozo que recibiste en el momento de la sacratísima Encarnación de nuestro Divino Señor y durante los nueve meses en que lo llevaste en tu casto seno.

Deseo de todo corazón poder renovar, e incluso aumentar, ese gozo con el fervor de mis oraciones.

¡Oh tierna Madre de los afligidos! Concédeme en mis necesidades presentes aquella protección especial que has prometido a quienes conmemoran devotamente este gozo inefable.

Confiando en las infinitas misericordias de tu Divino Hijo, seguro de que quien pide recibe, y lleno de confianza en tus poderosas oraciones, te suplico humildemente que intercedas por mí.

Te ruego que me obtengas los favores que pido en esta novena, si es la Santa Voluntad de Dios concedérmelos; y si no, que pidas para mí las gracias que más necesito.

(menciona tu petición aquí…)

Deseo, por medio de esta novena que ahora ofrezco en tu honor, demostrar la viva confianza que tengo en tu intercesión.

Acéptala, te lo suplico, en honor de aquel amor sobrenatural y de aquel gozo de que se llenó tu Inmaculado Corazón mientras tu Divino Hijo moraba en tu seno, en veneración del cual te ofrezco los sentimientos de mi corazón.

Oh Madre de Dios, acepta estos saludos unidos al respeto y a la veneración con que el Ángel Gabriel te saludó por primera vez llena de gracia. Deseo de todo corazón que se conviertan en otras tantas joyas de la corona de tu gloria, que aumentará en brillo hasta el fin del mundo.

Te suplico, oh Consoladora de los afligidos, por el gozo que recibiste cuando el Verbo se hizo carne, que me obtengas los favores y las gracias que ahora imploro por tu poderosa intercesión.

Con este fin te ofrezco todas las buenas obras que jamás se han hecho en tu honor.

Te suplico humildemente, por amor al amable Corazón de Jesús, del que el tuyo estuvo siempre tan inflamado, que escuches mis humildes oraciones y me obtengas lo que pido.

Amén.

¡Nuestra Señora de la Buena Salud, ruega por nosotros!

Padre nuestro.

Dios te salve, María.

Gloria al Padre.

En el nombre del Padre, y del Hijo, y del Espíritu Santo.

Amén.""", """In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.

O Most Holy Virgin!

You were chosen by the most Adorable Trinity from all eternity to be the most pure Mother of Jesus.

Permit me, your humble and devoted client, to remind you of the joy you received at the instance of the most Sacred Incarnation of our Divine Lord and during the nine months you carried Him in your chaste womb.

I wish most sincerely that I could renew, or even increase, that joy by the fervor of my prayers.

O Tender Mother of the Afflicted! grant me under my present necessities that special protection you have promised to those who devoutly commemorate this ineffable joy.

I beg you to obtain for me the favors which I petition for in this novena, if it be the Holy Will of God to grant them; and if not, to ask for me whatever graces I most stand in need of.

(mention request here…)

I desire by this Novena, which I now offer in your honor, to prove the lively confidence I have in your intercession.

Accept it, I beseech you, in honor of that supernatural love and joy with which your Immaculate Heart was replenished during the abode of your Divine Son in your womb, in veneration of which, I offer you the sentiments of my heart.

O Mother of God, accept these salutations in union with the respect and veneration with which the Angel Gabriel first hailed you Full of Grace. I wish most sincerely that they may become so many gems in the crown of your glory, which will increase in brightness to the end of the world.

I beseech you, O Comfortress of the Afflicted, by the joy you received when the Word was made flesh, to obtain for me the favors and graces which I now implore through your powerful intercession.

For this end I offer you all the good works which have ever been performed in your honor.

I most humbly entreat you, for the love of the amiable Heart of Jesus with which yours was ever so inflamed, to hear my humble prayers and obtain my requests.

Amen.

Our Lady of Good Health, pray for us!

Our Father

Hail Mary

Glory Be

In the Name of the Father, and of the Son, and of the Holy Spirit.

Amen.""", """Continue esta novena no app

Configure lembretes para não perder nenhum dia

Em nome do Pai, do Filho e do Espírito Santo.

Amém.

Ó Virgem Santíssima!

Fostes escolhida pela adorabilíssima Trindade desde toda a eternidade para ser a puríssima Mãe de Jesus.

Permiti a mim, vosso humilde e devoto servo, recordar-vos a alegria que recebestes no instante da sacratíssima Encarnação do nosso divino Senhor e durante os nove meses em que O trouxestes no vosso casto seio.

Desejo sinceramente poder renovar, ou até aumentar, essa alegria com o fervor das minhas orações.

Ó terna Mãe dos aflitos! Concedei-me nas minhas presentes necessidades aquela proteção especial que prometestes aos que comemoram devotamente esta alegria inefável.

Confiando nas infinitas misericórdias do vosso divino Filho, certo de que quem pede recebe, e cheio de confiança nas vossas poderosas orações, suplico-vos humildemente que intercedais por mim.

Peço-vos que me alcanceis as graças que peço nesta novena, se for da santa vontade de Deus concedê-las; e, se não, que peçais para mim as graças de que mais preciso.

(mencione o pedido aqui…)

Desejo, por esta novena que agora ofereço em vossa honra, provar a viva confiança que tenho na vossa intercessão.

Aceitai-a, eu vos suplico, em honra daquele amor sobrenatural e daquela alegria de que se encheu o vosso Imaculado Coração durante a permanência do vosso divino Filho no vosso seio, em veneração dos quais vos ofereço os sentimentos do meu coração.

Ó Mãe de Deus, aceitai estas saudações unidas ao respeito e à veneração com que o Anjo Gabriel vos saudou pela primeira vez cheia de graça. Desejo sinceramente que se tornem outras tantas joias na coroa da vossa glória, que crescerá em brilho até o fim do mundo.

Suplico-vos, ó Consoladora dos aflitos, pela alegria que recebestes quando o Verbo se fez carne, que me alcanceis os favores e as graças que agora imploro pela vossa poderosa intercessão.

Para isso vos ofereço todas as boas obras que já foram feitas em vossa honra.

Peço-vos humildemente, por amor do amável Coração de Jesus, do qual o vosso sempre esteve tão inflamado, que ouçais as minhas humildes orações e me alcanceis os meus pedidos.

Amém.

Nossa Senhora da Saúde, rogai por nós!

Pai-Nosso.

Ave-Maria.

Glória ao Pai.

Em nome do Pai, do Filho e do Espírito Santo.

Amém."""),
                intentionGuidance = LocalizedText("""""", """""", """""")
            ),
        ),
        imageUrl = """https://catholicnovenaapp.com/assets/images/our-lady-of-good-health-novena.png""",
        tags = listOf("love", "health")
    )
}