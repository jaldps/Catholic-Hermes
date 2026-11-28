# T-070 · Informe de entrega · revisión 1

Se actualizan los 18 SVG maestros, 36 PNG de 288/384 px y las cuatro láminas del [README](../design/catholic1/illustrations/v2/README.md). Día y Noche comparten geometría.

- H1: filetes oro en paths de trazo, posteriores a las sombras. Medidos en los PNG de 384 px, con tolerancia RGB total ≤12 frente al oro de cada tema y conversión a unidades de 512: corazón y=233, 10,7 px a ambos lados; lupa y=245, 10,7 px a ambos lados; diario y=265, 10,7 px a ambos lados; libro x=176, 10,7 px arriba y abajo; nube x=309, 10,7 px en base. Los bordes suavizados completan el ancho nominal de 12 px. Las cinco medidas de H1 son iguales en ambos temas.
- H2: halo r52 en (256,160), detrás del cuerpo; mecha de 12 px, marfil cálido en Noche, desde y195 hasta 231, solapada 18 px por la llama.
- H3: cinco cuentas por lado de r15 sin borde, extremos y cuenta central de r22; separación mínima de centros respecto a radios sumados ≥6 px; hilo de 8 px, metal oscuro en Noche; cruz hasta y431, a 25 px del borde inferior del medallón.
- M1: opción b del dueño: cuerpo `#EDE6D8`, sombra `#C9BFAE`, detalles `#10345C`, oro `#E3B65A` y medallón `#1D2D44`. Contrastes 11,19:1 cuerpo/medallón, 10,14:1 detalle/cuerpo, 6,92:1 detalle/sombra.
- M2: los 18 XML tienen grupos monocolor; cero grupos con múltiples colores. Los grupos adicionales o sufijados identifican el rol y el orden de dibujo.
- M3–M6: mango acortado; nimbo cálido y filete oro, sombra derecha, sin cuña; paloma sin aro exterior, tallo y hojas rellenas; corazón con llama asimétrica de base ancha y cruz de 16 px separada 8 px.
- Pulido: halo y cuerpo de vela separados, barra de nube acortada y estrechada, contornos del oro simplificados. La vela conserva más de seis paths para separar los colores y filetes; la cifra de seis formas se interpreta como elementos visuales. La paloma y los detalles dorados de Día son intencionalmente claros; pendientes de nueva revisión del crítico.

Revisión visual de las cuatro láminas a tamaño nominal: [Día](../design/catholic1/illustrations/v2/_contact-day.png), [Noche](../design/catholic1/illustrations/v2/_contact-night.png), [marca Día](../design/catholic1/illustrations/v2/_brand-day.png), [marca Noche](../design/catholic1/illustrations/v2/_brand-night.png). Sin modificación de app, sin tests ni verificación en dispositivo. Referencias: T-070-REVIEW.md, T-070-CRITICA-1.md, símbolo Catholic1.

## Revisión 2

Según el mensaje actual de INBOX-Chaty y T-070-REVIEW.md, revisión 2:

- Paloma: cuerpo en el grupo navy, marino #10345C de Día y marfil #EDE6D8 de Noche. Ala en warm, ojo y línea del ala en dove-details (marfil #FCF4E0 / marino #10345C). Contraste cuerpo/medallón: 11,49:1 Día y 11,19:1 Noche.
- Cruz: cuerpo y sombra de Noche en #10345C y #0C2848; se quita details-cross en ambos temas. Contraste cuerpo/nimbo 10,35:1.
- Sin conexión: barra de Noche en #10345C con filete de 12 px oro #E3B65A. Se acortan ambos extremos en las dos variantes, hasta quedar prácticamente contenidos en la nube. Contraste barra/cuerpo de Noche 10,14:1.
- Rosario: oro limpio de Día #DCA83C conforme a la decisión (a). Extremos a y167 (+12 px), desplazados 11 px hacia dentro para conservar aire de 6,84 px respecto a la cuenta vecina; se ajustan los extremos del hilo en ambos temas.
- Reexportadas e inspeccionadas las cuatro láminas y los PNG. Validación: grupos monocolor, dimensiones y alfa de esquina, cero incidencias. Se conservan las otras cinco ilustraciones aprobadas sin retoques opcionales. No se ha probado en dispositivo; queda pendiente la ronda 3 del crítico y la integración (T-073 punto 5).
