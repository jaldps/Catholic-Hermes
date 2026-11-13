# Instalar la versión actual en Android

[Descargar Catholic Hermes Preview 1.5](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-quality-preview.apk).

Abre el archivo `.apk` en tu teléfono Android y permite la instalación desde el navegador si Android lo solicita. Busca **Catholic Hermes Preview** en el teléfono.

Esta versión actualiza el primer quality preview con la misma firma y conserva sus datos. Se instala por separado de CatholicGPT y Sancta Fides, cuyos datos son independientes. Paquete: `com.aistudio.sanctafides.qvmrpx.qualitypreview`; versión `1.5-quality-preview`, código 6.

En Explorar → Apariencia puedes elegir Lumen o Sanctuary, modo claro/oscuro, fuente y opciones de accesibilidad. Inicio → Mis prácticas → Tentaciones y victorias permite registrar y encontrar orientación; Orar destaca Rosario y Coronilla.

To reproduce in the prepared environment:

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=2 --no-configuration-cache -I tools/quality-preview.gradle :app:assembleDebug
```

Los APK históricos (`CatholicGPT-preview.apk`, `sancta-fides-1.3.apk` y los otros previews) se conservan. Véanse [los cambios de esta versión](../docs/quality/DEVOTIONAL-PRIORITIES.md) y [la entrega y sus límites](../docs/quality/DELIVERY.md).
