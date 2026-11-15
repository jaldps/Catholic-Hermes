# Instalar la versión actual en Android

## Catholic Hermes 1.8.3

[Descargar Catholic Hermes 1.8.3](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-1.8.3.apk) · paquete `com.aistudio.sanctafides.qvmrpx.hermespreview`, versión `1.8.3`, código 12. **Actualiza 1.8.1/1.8.2** y conserva el progreso.

Novedades:
- Cada oración (68 existentes + 25 nuevas) muestra **Cómo rezarla**, **Historia**, fuente, aprobación o indulgencia (*Enchiridion* 1999) y promesa cuando la hay documentada.
- 25 oraciones nuevas reales y documentadas: letanías de San José, del Santísimo Nombre y de la Preciosísima Sangre, Ave maris stella, Stabat Mater, oraciones de Santo Tomás, Cántico de las criaturas, ediciones latinas y otras.
- Corona de San Miguel sin el bloque tomado del exorcismo de León XIII (no permitido a los fieles, CDF 1985).
- Novena de Esquipulas con su texto auténtico (impreso de 1791, dominio público).
- Se retiraron 15 novenas cuyo texto había sido redactado por agentes; volverán con su texto real de fuente verificada.

[Metadatos](../docs/quality/release-1.8.3/APK.json). No se ha probado en un teléfono físico.

## Catholic Hermes 1.8.2

[Descargar Catholic Hermes 1.8.2](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-1.8.2.apk) · paquete `com.aistudio.sanctafides.qvmrpx.hermespreview`, versión `1.8.2`, código 11. **Actualiza la 1.8.1** (misma firma y paquete) y conserva su progreso.

Novedades:
- Cada novena (262) muestra **Historia** (origen, aprobación verificable, fuentes) y **Promesa** con su tipo (con fuente, concesión de la Iglesia, tradición popular o sin promesa), atribución, fuente y condiciones; se señalan los añadidos comerciales («nunca falla», «publícala») como ajenos a la devoción y se añaden notas pastorales donde hace falta.
- 16 novenas nuevas con texto propio: Aparecida, Luján, Pilar, Chiquinquirá, Coromoto, Altagracia, Copacabana, Suyapa, Señor de los Milagros, Cristo Negro de Esquipulas, Divino Niño del 20 de Julio, San Ramón Nonato, San Pancracio, Santa Eduviges, São Benedito y San José Gregorio Hernández.
- Botones agrupados en desplegables: Fundamentos de las 101 preguntas, Contenido de la lección del curso bíblico, lecturas de la Misa y antífona mariana de Completas.
- Incluye 1.8 (guías de tentaciones) y 1.8.1 (selector Liquid Glass, promesas del Sagrado Corazón).

[Metadatos](../docs/quality/release-1.8.2/APK.json). No se ha probado en un teléfono físico.

## Catholic Hermes 1.8.1 — edición independiente

[Descargar Catholic Hermes 1.8.1](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-1.8.1.apk) · paquete `com.aistudio.sanctafides.qvmrpx.hermespreview`, versión `1.8.1`, código 10.

Abre la APK en Android y permite instalar desde el navegador si se solicita. Busca **Catholic Hermes 1.8.1**. Se instala junto a 1.8: conserva la app anterior y sus datos, y comienza con progreso independiente.

Novedades: selector Rosario/Divina Misericordia con una implementación propia de Liquid Glass inspirada en el repositorio compartido; doce promesas tradicionales del Sagrado Corazón con atribución, fuentes y condiciones en ES/EN/PT. La refracción requiere Android 13 o posterior y aceleración gráfica; las versiones anteriores utilizan vidrio esmerilado. No integra todas las funciones de la biblioteca de referencia. [Cambios y verificaciones](../docs/quality/release-1.8.1/README.md) · [Metadatos de la APK](../docs/quality/release-1.8.1/APK.json). No se ha probado en un teléfono físico.

## Catholic Hermes 1.8 (en desarrollo)

[Descargar Catholic Hermes 1.8](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-1.8.apk) · paquete `com.aistudio.sanctafides.qvmrpx.hermes`, versión `1.8`, código 9.

Es una app aparte, firmada con una clave nueva: se instala junto a la anterior y empieza sin el progreso guardado. Novedades de esta entrega (M1): el filtro «A quién se reza» de las novenas es un desplegable, y cada tentación (Inicio → Mis prácticas → Tentaciones) tiene un botón **«Más: cómo vencerla»** con una guía completa: cómo actúa, por qué cuesta, señales, qué hacer en el momento, rutina diaria, plan de 40 días, Escritura, sabiduría de los santos con fuente, sacramentos, cuándo pedir ayuda y fuentes. Metadatos: [release-1.8/APK.json](../docs/quality/release-1.8/APK.json). No se ha probado la instalación en un dispositivo real.

## Versión anterior

[Descargar Catholic Hermes Preview 1.6](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-quality-preview.apk).

Abre el archivo `.apk` en tu teléfono Android y permite la instalación desde el navegador si Android lo solicita. Busca **Catholic Hermes Preview** en el teléfono.

Esta versión actualiza el quality preview anterior con la misma firma y conserva los identificadores de datos y progreso. Se instala por separado de CatholicGPT y Sancta Fides, cuyos datos son independientes. Paquete: `com.aistudio.sanctafides.qvmrpx.qualitypreview`; versión `1.6-quality-preview`, código 7.

En Explorar → Apariencia puedes elegir Lumen o Sanctuary, modo claro/oscuro, fuente y opciones de accesibilidad. Inicio → Mis prácticas → Tentaciones y victorias permite registrar y encontrar orientación; Orar destaca Rosario y Coronilla.

La formación está en **Leer → Estudio**, o **Explorar → Estudio → Cursos bíblicos**. El curso nuevo «Aprender a leer la Biblia» añade contexto, explicaciones desarrolladas, preguntas con respuesta, fuentes y oración; se conservan los tres cursos anteriores.

To reproduce in the prepared environment:

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=2 --no-configuration-cache -I tools/quality-preview.gradle :app:assembleDebug
```

Los APK históricos (`CatholicGPT-preview.apk`, `sancta-fides-1.3.apk` y los otros previews) se conservan. Véanse [la entrega y sus límites](../docs/quality/DELIVERY-1.6.md) y [el plan de los siguientes incrementos](../docs/quality/CONTENT-DELIVERY-PLAN.md).
