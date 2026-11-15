# T-015 · Revisión del coordinador

## Revisión final (fork `4037da3`, 8/8) · CERRADO

**Aprobada e integrada.** El catálogo pasa a 262 novenas y 3212 días.

### Comprobaciones

- **Estructura:** las 8 novenas tienen 9 días, `prayer` en ES/EN/PT, y los ids coinciden entre `index-add.json` y `new-d.json`.
- **Fidelidad al original en ES:** comparé las frases con cada fuente, normalizando la puntuación.
  - Suyapa: 274/274.
  - Señor de los Milagros: 256/256.
  - São Benedito: 192/192.
  - Coromoto: 100 %.
  - Divino Niño y San Pancracio: diferencias solo de puntuación o espacios.
- **Derechos:**
  - Con línea «Derechos»: EWTN Inc. (Coromoto, Señor de los Milagros, Divino Niño), Conferencia Episcopal de Honduras (Suyapa) y Biblioteca Católica (Eduviges, con su reserva «Todos direitos reservados» verificada).
  - Sin línea: San Pancracio, que cita «Fuente: Devocionario Católico, www.devocionario.com»; São Benedito y José Gregorio, porque no se halló aviso de derechos.
- **San Pancracio:** las oraciones a la Santísima Trinidad cierran todos los días.
- **São Benedito:** las lecturas se presentan como relatos del texto antiguo. Las prácticas ascéticas no se proponen al lector y llevan `pastoralNote`.
- **José Gregorio Hernández:** el texto conserva «Venerable» o «Beato» tal cual y la canonización figura en `history`. Las oraciones de la página que no forman la novena se omiten y la omisión está declarada.
- **Tests:** los de novenas y `EditorialReliabilityTest` pasan.

Buen trabajo, Hermes. Con esto vuelven a la app las 15 novenas retiradas, todas con su fuente.
