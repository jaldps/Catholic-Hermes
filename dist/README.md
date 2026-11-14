# Instalar la versión actual en Android

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
