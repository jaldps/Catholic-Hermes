# T-007 · Informe de Codex

Entrega parcial: **3 de 12** entradas, en el orden del encargo. Trabajo limitado a `text-overrides.json`, las fichas correspondientes de `details-b.json` y este informe. Las otras nueve entradas siguen pendientes.

## Fuentes y cambios por id

### `chaplet_of_st_michael`

- **Fuente textual:** *The Raccolta*, Burns & Oates / Benziger Brothers, edición inglesa de 1910, n.º 291, «Angelical Crown», pp. impresas 261–263. [Facsímil completo](https://archive.org/details/theraccoltaorcol00unknuoft); [PDF](https://archive.org/download/theraccoltaorcol00unknuoft/theraccoltaorcol00unknuoft.pdf), páginas PDF 281–283. Leídas visualmente las tres páginas, además de consultar su OCR: por ejemplo, el comienzo es **At the intercession**, no el «By» que una transcripción secundaria sugería.
- Inglés de esa edición de dominio público, con normalización tipográfica. Traducciones propias y fieles ES/PT. Las rúbricas mantienen los nueve grupos de un padrenuestro y tres avemarías, el Gloria inicial y los cuatro padrenuestros finales; no se inventa una expansión de las oraciones abreviadas en la fuente.
- Se restituyen las nueve peticiones distintas, desde serafines hasta ángeles, antífona, V/R y oración. Sin fórmula de exorcismo. Antífona y oración continúan en `responseText`.
- Actualizada la ficha histórica y práctica. La mención impresa a Pío IX, 8-08-1851, se identifica como referencia del devocionario, **no** como decreto original examinado. No se reproducen como actuales las indulgencias ni las fechas festivas de 1910. No se acredita aparición ni promesa privada.

### `adoro_te_devote`

- **Fuente textual:** la misma *Raccolta*, 1910, n.º 106, pp. 86–88, texto latino. Cotejadas visualmente las páginas PDF 106–108.
- Traducciones propias y fieles ES/EN/PT de las siete estrofas completas, dispuestas en líneas. No se atribuyen a Caswall o Hopkins. La séptima estrofa continúa en `responseText`; se elimina así el cierre ajeno que se conservaría si ese campo fuese `null`.
- Se retira el aviso de transcripción alterada y se conserva la prudencia sobre la autoría tradicional. Contexto previamente comprobado en T-002: [Catholic Encyclopedia, Adoro Te Devote](https://www.newadvent.org/cathen/01154b.htm). El [Enchiridion de 1999](https://www.vatican.va/roman_curia/tribunals/apost_penit/documents/rc_trib_appen_doc_20020826_enchiridion-indulgentiarum_lt.html), concesión 7, **menciona** el himno, no imprime aquí su texto íntegro. La fuente textual es la edición de 1910.

### `litany_of_loreto`

- **Base antigua:** *The Raccolta*, 1910, n.º 189, pp. 168–170, formulario latino de dominio público.
- **Comprobación de la serie actual completa:** [Santa Sede, The Litany of Loreto](https://www.vatican.va/special/rosary/documents/litanie-lauretane_en.html), contrastada también con su [página italiana](https://www.vatican.va/special/rosary/documents/litanie-lauretane_it.html). Estas páginas están actualizadas e incluyen las tres invocaciones de 2020; no se las fecha ficticiamente en 1910. Se cuentan **54 títulos marianos**, sin duplicados.
- **Disposición y lugares exactos de las tres incorporaciones:** [carta de la Congregación para el Culto Divino, 20-06-2020](https://press.vatican.va/content/salastampa/es/bollettino/pubblico/2020/06/20/letan.html): *Mater misericordiae* después de *Mater Ecclesiae*; *Mater spei* después de *Mater divinae gratiae*; *Solacium migrantium* después de *Refugium peccatorum*.
- Traducciones propias del formulario tradicional, con los títulos añadidos y cotejados. No se copia una traducción litúrgica nacional moderna. Para la oración final se utiliza el **Concede nos famulos tuos** tradicional: latín completo comprobado en [Movimento Apostolico, Litanie Lauretane, sección «Preghiamo»](https://www.movimentoapostolico.org/formazione/parola-commentata/meditazioni-preghiere/litanie-lauretane.pdf); su contenido coincide con la conclusión publicada por la Santa Sede. Se traduce el latín antiguo, sin reproducir el comentario moderno del PDF.
- Se restauran las peticiones iniciales, respuestas en cada título, tres *Agnus Dei*, V/R y colecta. La colecta continúa en `responseText`. Se actualiza la ficha, eliminando los avisos de omisiones y duplicados.

## Validación de esta entrega

```text
python3 docs/tasks/tools/validate_prayers.py overrides app/src/main/assets/prayers/text-overrides.json --partial
3 entries · 0 errors · 0 warnings

python3 docs/tasks/tools/validate_prayers.py details app/src/main/assets/prayers/details-b.json docs/tasks/T-002.ids
34 entries · 0 errors · 0 warnings
```

Las 31 fichas no correspondientes a esta entrega se conservan sin cambios de contenido. No se ha compilado Android: el protocolo deja la compilación al coordinador.

## Precauciones técnicas y límites

- El contrato de la app conserva el valor anterior cuando `responseText` es `null`. Por eso se distribuye cada formulario completo entre texto y continuación auténtica: no se deja sobrevivir un añadido anterior ni se compone una respuesta nueva. La división de un himno entre campos **no** significa que tenga un estribillo litúrgico.
- No se afirma que estas traducciones propias sean versiones litúrgicas aprobadas. Para uso litúrgico se remite a los libros aprobados de la comunidad.
- No se ha inspeccionado el original del decreto de 1851 citado por la *Raccolta*, ni se revalidan concesiones antiguas.
- Pendientes: las nueve entradas restantes, incluido el ajuste de ubicación del `confiteor`.

## Preguntas para el coordinador

Ninguna que impida continuar esta entrega parcial.
