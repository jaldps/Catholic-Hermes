# Catholic Hermes 1.8.1 — cambios preparados

Estado: código y comprobaciones completados; APK pendiente de recuperar la clave privada que firmó la versión 1.8. Este documento no anuncia una descarga publicada de 1.8.1.

## Cambios

- El selector Santo Rosario / Divina Misericordia utiliza un material Liquid Glass propio de Compose: lente deslizante, borde luminoso y refracción sobre la ilustración en Android 13 o posterior con lienzo acelerado. Conserva el carácter de Lumen y Sanctuary. Las versiones anteriores y los lienzos de software emplean vidrio esmerilado; contraste alto y transparencia reducida emplean superficies opacas.
- La ficha del Sagrado Corazón recupera las doce promesas tradicionales en español, inglés y portugués, incluidas la paz, el consuelo y la Gran Promesa. Distingue la atribución, las fuentes y las condiciones de la devoción. La recopilación es una síntesis propia, no una cita literal ni una concesión de indulgencia. Véase [SOURCES.md](SOURCES.md).

## Verificación

13 pruebas pasan, sin fallos, errores ni omisiones: dos de disponibilidad del shader/fallback, seis de navegación del Rosario/Coronilla y texto ampliado, una del contenido trilingüe y cuatro de capturas. Las capturas cubren seis estados por cada combinación de identidad y modo: 24 imágenes. Se revisaron visualmente Rosario y Coronilla en las cuatro combinaciones.

Las capturas utilizan un lienzo de software y verifican el acabado esmerilado de respaldo. La compilación del shader se comprobó en Android simulado; no se ha verificado la refracción en una GPU física, la instalación ni la actualización en un teléfono. Los resultados de este cambio no sustituyen una batería completa de la aplicación.

## Firma y publicación pendientes

La actualización mantiene `com.aistudio.sanctafides.qvmrpx.hermes`, con nombre de versión `1.8.1` y código `10`. Para actualizar 1.8 necesita el certificado SHA-256 `07efd6dbd2c9005998114aba185ae5c7230e9637c3b300e221ba413b6be47908`. El script de esta versión impide empaquetar con un certificado distinto.

La clave privada se proporciona desde fuera del repositorio mediante `-PhermesSigningKeystore=/ruta/privada/clave.keystore`. Una vez recuperada, faltan la generación y verificación de la APK, sus metadatos y la publicación de la descarga. No se ha incorporado el trabajo M2 de novenas.
