# Catholic Hermes 1.8.1 — edición independiente

[Descargar Catholic Hermes 1.8.1](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/Catholic-Hermes-1.8.1.apk).

Se instala junto a 1.8 y conserva esa instalación con sus datos. La nueva edición comienza con su propio progreso; no lo importa automáticamente de 1.8.

## Cambios

- El selector Santo Rosario / Divina Misericordia utiliza un material Liquid Glass propio de Compose: lente deslizante, borde luminoso y refracción sobre la ilustración en Android 13 o posterior con lienzo acelerado. Conserva el carácter de Lumen y Sanctuary. Las versiones anteriores y los lienzos de software emplean vidrio esmerilado; contraste alto y transparencia reducida emplean superficies opacas.
- La ficha del Sagrado Corazón recupera las doce promesas tradicionales en español, inglés y portugués, incluidas la paz, el consuelo y la Gran Promesa. Distingue la atribución, las fuentes y las condiciones de la devoción. La recopilación es una síntesis propia, no una cita literal ni una concesión de indulgencia. Véase [SOURCES.md](SOURCES.md).

El selector está inspirado en QWEA0/Liquid-Glass-Android. No integra esa biblioteca completa: no incluye reflejos gobernados por la inclinación del teléfono ni fusión de formas. El resto de controles conserva el material de cada identidad.

## Verificación

13 pruebas pasan, sin fallos, errores ni omisiones: dos de disponibilidad del shader/fallback, seis de navegación del Rosario/Coronilla y texto ampliado, una del contenido trilingüe y cuatro de capturas. Las capturas cubren seis estados por cada combinación de identidad y modo: 24 imágenes. Se revisaron visualmente Rosario y Coronilla en las cuatro combinaciones.

Las capturas utilizan un lienzo de software y verifican el acabado esmerilado de respaldo. La compilación del shader se comprobó en Android simulado; no se ha verificado la refracción en una GPU física, la instalación ni la actualización en un teléfono. Los resultados de este cambio no sustituyen una batería completa de la aplicación.

[Capturas del material de respaldo: Lumen y Sanctuary, claro y oscuro](glass-software-fallback.png).

## Firma e instalación

La edición utiliza `com.aistudio.sanctafides.qvmrpx.hermespreview`, nombre de versión `1.8.1` y código `10`. Reutiliza la clave de preview disponible, cuyo certificado SHA-256 es `b634b6b8a98206f9f5a7251b892103e5ddc504ef38a7cc84a0330e4e612c83d9`. El script verifica ese certificado antes de empaquetar. Metadatos del archivo: [APK.json](APK.json).

La clave privada de 1.8 (`07efd6db…`) no está disponible en este entorno. Firmar con otra clave no permite actualizar una instalación con el mismo identificador; por eso se publica esta edición independiente, autorizada por el usuario. Los APK históricos se conservan. No se ha incorporado el trabajo M2 de novenas.

La clave privada permanece fuera de Git. Para utilizar un respaldo privado de la misma firma se puede indicar `-PhermesSigningKeystore=/ruta/privada/clave.keystore`.
