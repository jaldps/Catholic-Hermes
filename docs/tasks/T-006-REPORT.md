# T-006 · Informe de Codex

## Entrega parcial 1: preguntas 52–61

Se añaden **10 de las 50 preguntas**, en orden, con siete secciones completas y fuentes en ES/EN/PT-BR. No se modifican las preguntas, respuestas, fundamentos o aclaraciones de Kotlin. La base es `origin/CatholicGPT` (`ffa8e45`). Las preguntas 62–101 siguen pendientes; esta entrega no las presenta como terminadas.

Son explicaciones originales apoyadas en fuentes leídas, no nuevas oraciones. Las alusiones bíblicas y patrísticas son paráfrasis, sin comillas que simulen citas literales. Las analogías incluyen su límite y no se ofrecen como demostraciones de la eficacia sacramental.

## Validación ejecutada

```text
$ python3 docs/tasks/tools/validate_questions.py app/src/main/assets/questions/deeper-b.json 52 101 --partial
WARN  partial delivery: 10/50 questions
10 entries · 0 errors · 1 warnings
```

El único aviso corresponde al carácter parcial de la entrega. No hay avisos de extensión. Se conserva el contrato JSON y los números 52–61, sin huecos.

## Fuentes consultadas

Los números del Catecismo se cotejaron en el [texto oficial del Vaticano](https://www.vatican.va/archive/ENG0015/_INDEX.HTM). Las referencias bíblicas se comprobaron en la [Biblia Douay-Rheims de dominio público](https://www.gutenberg.org/ebooks/8300), atendiendo a las diferencias de numeración; no se copian sus notas polémicas ni traducciones bíblicas modernas.

| Pregunta | Fuentes y comprobación |
|---|---|
| 52 · Sacrificio de la Misa | CIC 1362–1367; Hb 10,10-14; Lc 22,19-20; 1 Co 11,23-26. [Tomás de Aquino, *Suma Teológica* III, q. 83, a. 1, incluido ad 1](https://www.newadvent.org/summa/4083.htm): representación sacramental de la Pasión, participación en sus frutos e identidad de la víctima. Se utiliza esta localización comprobada en lugar de una numeración de homilía que no se pudo fijar con igual precisión. |
| 53 · Transubstanciación | CIC 1376–1377 y 1381; Mt 26,26-28; 1 Co 11,23-29; Jn 6,53-56. [Tomás, III, q. 75, a. 5, ad 2](https://www.newadvent.org/summa/4075.htm): los sentidos perciben propiedades reales y la fe no exige negar esa percepción. |
| 54 · Confesión | CIC 1441–1445 y 1451–1453; Jn 20,21-23; 2 Co 5,18-20; St 5,14-16. [Juan Crisóstomo, *Sobre el sacerdocio* III,5](https://www.newadvent.org/fathers/19223.htm): aplicación ministerial del perdonar y retener, sin convertir al sacerdote en fuente autónoma de misericordia. |
| 55 · Ministro pecador | CIC 1127–1128 y 1584; 1 Co 3,5-7; 2 Co 4,7; Jn 20,21-23. [Agustín, *Tratados sobre Juan* 6,7–8](https://www.newadvent.org/fathers/1701006.htm): Cristo conserva el poder de bautizar aunque actúen distintos ministros. Validez no se confunde con licitud, santidad ni continuidad en un cargo. |
| 56 · Bautismo infantil | CIC 1250–1252 y 1255; Jn 3,5; Col 2,11-12; Hch 16,15.33. [Cipriano, carta a Fido, §2](https://www.newadvent.org/fathers/050658.htm): carta 64 en otra numeración, **58 en ANF**. Se explica esta diferencia, sin inventar niños concretos en los relatos de bautismos de casas enteras. |
| 57 · Niños fallecidos sin bautismo | CIC 1257 y 1261; Mc 10,13-16; Mt 18,14; 1 Tm 2,4. [Gregorio Nacianceno, *Discurso* 40,23](https://www.newadvent.org/fathers/310240.htm): se distingue su opinión antigua de una definición de la Iglesia. La esperanza eclesial no se presenta como condenación ni como información individual revelada. |
| 58 · Domingo | CIC 2180–2183 y 1857–1859; Hch 2,42; Hch 20,7; Hb 10,24-25. [Justino, *Primera Apología* 67](https://www.newadvent.org/fathers/0126.htm): reunión, Palabra, Eucaristía y ayuda a necesitados. |
| 59 · Preparación para comulgar | CIC 1385 y 1395–1396; 1 Co 10,16-17; 1 Co 11,27-29; Mt 5,23-24. Justino, *Primera Apología* 66, en el enlace anterior. [Canon 844 §§3–4](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann834-878_en.html) y [canon 916](https://www.vatican.va/archive/cod-iuris-canonici/eng/documents/cic_lib4-cann879-958_en.html), texto oficial completo. No se convierten sus casos precisos en intercomunión general. |
| 60 · Sacramentales | CIC 1667–1670, 2111 y 1128; Nm 6,24-26; Mc 6,13; Hch 19,11-12. [Tertuliano, *De corona* 3](https://ccel.org/ccel/tertullian/corona/anf03.iv.vi.iii.html): señal de la cruz en actividades cotidianas. Se lo identifica como autor cristiano antiguo, sin atribuirle condición de santo o doctor. No se equipara cada uso de aceite con la unción sacramental. |
| 61 · Base bíblica del purgatorio | CIC 1030–1032; 1 Co 3,11-15; Ap 21,27; 2 M 12,43-46. [Gregorio Magno, *Diálogos* IV,39](https://www.tertullian.org/fathers/gregory_04_dialogues_book4.htm): faltas leves y purificación; también admite una lectura de 1 Co 3 referida a tribulaciones presentes. No se elevan sus imágenes o ejemplos a definición dogmática. |

## Precisiones doctrinales y pastorales

- La Misa hace presente el único sacrificio; no añade otra muerte o una redención independiente.
- La transubstanciación no se demuestra mediante análisis de propiedades que la doctrina afirma conservadas. No se finge una prueba de laboratorio.
- La contrición perfecta requiere amor de Dios y propósito firme de confesión cuanto antes; no se presenta como permiso ordinario para prescindir del sacramento.
- La indignidad de un ministro no invalida por sí sola un sacramento ni exime al ministro de responsabilidad. Se distinguen validez, licitud y fructuosidad.
- Las normas de comunión se explican sin juzgar públicamente conciencias. El canon 916 exige conjuntamente razón grave y falta de oportunidad de confesarse, además de contrición perfecta y propósito de confesión pronta.
- El purgatorio es purificación de quienes mueren en gracia y amistad con Dios. No es segunda conversión después de la muerte ni una cronología física revelada.

## Problemas detectados en el texto existente, sin editarlo

1. **Pregunta 60:** «no producen automáticamente la gracia del mismo modo que los sacramentos» puede dar a entender que los sacramentos sí son automáticos o mágicos. Se recomienda expresar la distinción de CIC 1670 junto con CIC 1128: acción de Cristo y disposición para recibir sus frutos. El nuevo Profundizar lo aclara.
2. **Pregunta 61:** comienza «La respuesta directa es no» ante la pregunta «¿Dónde dice…?». Es un desajuste de pregunta/respuesta: debería decir directamente que no aparece la palabra ni una descripción completa, y después exponer los fundamentos.
3. **Pregunta 61:** «con la culpa ya perdonada» necesita precisión. CIC 1030 habla de morir en gracia y amistad con Dios; CIC 1031 admite purificación de faltas leves. No debe entenderse que no puede quedar ninguna falta venial pendiente. La nueva explicación mantiene esa distinción.

## Datos no establecidos y preguntas para el coordinador

No se atribuyen edades no narradas en los bautismos domésticos, una ubicación o duración física del purgatorio ni una sentencia individual sobre niños fallecidos sin bautismo. No se certifican como dogma las opiniones particulares de Gregorio Nacianceno ni cada detalle de Gregorio Magno.

No se requiere cambiar el contrato. El coordinador puede integrar este prefijo mientras continúan las preguntas restantes y valorar las tres correcciones del texto base señaladas arriba.
