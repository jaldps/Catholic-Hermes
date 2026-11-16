# T-018 · Revisión del coordinador

## Revisión intermedia 1 (`d844a01`, auditoría de las 30 marianas) · INTEGRADA CON UN AJUSTE

- **Validador:** 30 entradas y 0 errores.
- **Muestreo:** el `recognition` es riguroso y prudente en Guadalupe (*Ecclesia in America* 11), Fátima (carta pastoral del 13-X-1930), Rosa Mística (*nihil obstat* del 8-VII-2024), Akita (solo diocesano, 1984, sin atribuirle normas de 2024) y Kibeho (29-VI-2001). Bien.
- **Balance:** 13 oraciones sustituidas por textos con fuente y 17 retiradas; 25 promesas retiradas y 4 sustituidas. Es coherente con la regla del usuario.

### Ajuste del coordinador

Las promesas sustituidas de **Guadalupe**, **Fátima** y **Medalla Milagrosa** se presentaban como «paráfrasis» de palabras de María. La regla del usuario no admite texto propio puesto en boca de nadie. Se integran de momento como `{"remove": true}`.

**Para recuperarlas:** entrega la **cita literal** de una edición fuente concreta, con edición, página o número. Por ejemplo:
- *Nican Mopohua*, en la traducción de Primo Feliciano Velázquez (1926) u otra edición identificada;
- *Memorias de la Hermana Lucía*, con edición y página;
- el relato de santa Catalina Labouré, con edición y página.

Si no hay cita literal verificable, déjalas retiradas.

La promesa del **Carmen** se mantiene: describe la tradición con su atribución y fuente, sin poner palabras en boca de María.

### Siguiente

Completa `marian-new.json` (~25 nuevas) con el mismo rigor.

### Segundo ajuste: texto dirigido al editor

En `miracles` de **Guadalupe**, **Lepanto**, **Altagracia** y **Akita** había frases dirigidas al editor y no al lector, como «Se retiran los números de conversiones…» o «Se retira el relato…». Se quitaron esas frases en ES/EN/PT, y se conserva solo la parte informativa.

Lo que retires o por qué lo retiras va en el REPORT, nunca en el texto que lee el usuario.
