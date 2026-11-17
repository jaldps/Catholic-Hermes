# T-019 · Revisión del coordinador

## Revisión intermedia 1 (fork `e9c4db4`) · CAMBIOS PEDIDOS, no integrado

El validador da 0 errores, pero el muestreo de `christ-new.json` encontró errores de hecho, relleno y fuentes débiles. No se integra hasta corregirlo.

### Errores concretos

1. **`preciosa_sangre`**
   - La fiesta la extendió a la Iglesia universal **Pío IX en 1849**, no Pío V.
   - **No existe una memoria del 1 de julio** en el Calendario Romano General desde 1969. La fiesta se suprimió en la reforma; hoy hay una misa votiva y la devoción del mes de julio.
   - Quita la referencia a la sangre de **san Genaro**: es la reliquia de un santo, no la Sangre de Cristo.
   - Quita la frase sobre «Señor, ten piedad».
   - Corrige la errata «las/archicofradías».
2. **`senor_del_gran_poder`**
   - El año `1660` no tiene fuente: pon solo lo que la fuente diga, o `""` si se permite. Lo mismo en todas las entradas.
   - Elimina las frases repetidas.
3. **`cristo_de_limpias`**
   - Limpias está junto a la ría del **Asón**, no del Pas.
   - El año `1400` necesita fuente.
   - El texto repite varias veces «el santuario…».
   - Falta el hecho histórico principal: los fenómenos de 1919 que dieron fama al Cristo. Preséntalos como relatos de testigos, sin declararlos milagros.
4. **`via_crucis`**
   - La frase «San Francisco de Asís promovió la devoción a la Pasión en los lugares de la Pasión de Jerusalén» no tiene fuente.
   - «Las catorce estaciones se fijaron…» está repetida.
   - Cita la fijación de las estaciones: Clemente XII, 1731, y Benedicto XIV, 1742.
5. **`santo_nino_de_cebu`**
   - **Wikipedia no es fuente aceptable** como fuente principal. Usa la Basílica del Santo Niño, la arquidiócesis de Cebú o el decreto de la coronación canónica (1965).

### Para todas las entradas nuevas

- Ni relleno ni frases repetidas. Las 100–220 palabras deben ser hechos verificables.
- Cada año, fecha y reconocimiento debe estar en la fuente citada.
- Sin Wikipedia ni webs comerciales como fuente; usa diócesis, santuarios, vatican.va y estudios académicos.
- Revisa la ortografía antes del push.

Los detalles de las 20 existentes (`christ-details.json`) parecen razonables en el muestreo: Praga, Esquipulas, Divino Niño, Sagrado Corazón y Divina Misericordia. Repasa también sus años y documentos. Entrega todo junto corregido.

## Revisión final (fork `3f434dc`) · CERRADO

- **Validador:** `details christ` da 20 entradas y 0 errores; `new christ` da 18 entradas y 0 errores.
- **Correcciones comprobadas:**
  - **Preciosa Sangre:** Pío IX, 10-VIII-1849; memoria suprimida en 1969; sin san Genaro.
  - **Limpias:** ría del Asón; relatos de 1919 presentados como testimonios.
  - **Vía Crucis:** 1731 y 1742.
  - **Años sin fuente:** vacíos.
  - **Wikipedia:** ya no figura como fuente.
  - **Frases repetidas:** eliminadas.
- **Ajuste del coordinador:** en **Señor del Gran Poder**, la primera frase («un crucificado de gran tamaño… en la parroquia de San Francisco») no está respaldada por las fuentes citadas. La imagen del Gran Poder no es un crucificado y su sede es su propio santuario. Pasa a: «La imagen del Señor del Gran Poder se venera en su santuario de La Paz (Bolivia)», en ES/EN/PT.
- **Integración:** merge en `CatholicGPT`. Las 18 devociones nuevas ya muestran su imagen y su crédito.
