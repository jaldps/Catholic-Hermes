# T-019 · Devociones de Cristo: auditoría y 18 nuevas

**Asignado:** Hermes · **Rama:** `ext/T-019` (fork) · **Estado:** ABIERTO, pendiente de revisión.

## Entrega

| Archivo | Contenido |
|---|---|
| `app/src/main/assets/devotions/christ-details.json` | Auditoría de las 20 devociones de Cristo existentes, en el orden del Kotlin |
| `app/src/main/assets/devotions/christ-new.json` | 18 devociones de Cristo nuevas |

No se tocó ningún otro archivo (`DevotionsAndHistoryData.kt` queda intacto).

## Validación

```bash
python docs/tasks/tools/validate_devotions.py details christ app/src/main/assets/devotions/christ-details.json
# 20 entries · 0 errors · 0 warnings
python docs/tasks/tools/validate_devotions.py new christ app/src/main/assets/devotions/christ-new.json
# 18 entries · 0 errors · 0 warnings
```

## Regla de contenido aplicada

Ninguna oración nueva ha sido redactada por mí. En la auditoría, **17 de las 20 oraciones actuales se retiraron** (`prayer: {"remove": true}`) porque son composiciones genéricas sin fuente identificable; solo se mantuvieron `null` (sin cambio) las dos oraciones que son texto tradicional con procedencia:

- `sacred_heart_jesus`: «Jesús, manso y humilde de Corazón…» y «Sagrado Corazón de Jesús, en Ti confío» (oración tradicional).
- `divina_misericordia`: «¡Oh Sangre y Agua que brotaste del Corazón de Jesús…!» (oración de la Divina Misericordia, del *Diario* de santa Faustina).
- `nino_jesus_de_praga`: la promesa «Cuanto más me honréis, tanto más os favoreceré» es el texto atribuido en la tradición de P. Cirilo, y se conserva en `promises` (`null`).

Las `promises` genéricas («sanación de…», «fortaleza ante…») se retiraron (`{"remove": true}`) salvo las dos indicadas. Las `miracles` genéricas se sustituyeron por textos honestos que citan solo lo que la tradición local afirma y dejan constancia de que la Iglesia no ha declarado milagros concretos con documentación canónica.

## Auditoría: resumen por devoción

| id | oración actual | promesas | milagros | recognition (documento y año) |
|---|---|---|---|---|
| `sacred_heart_jesus` | **mantenida** | mantenidas | mantenidos | Pío IX, S. Congr. de Ritos (23-VIII-1856); Pío XI, *Miserentissimus Redemptor* (1928); Pío XII, *Haurietis Aquas* (1956) |
| `divina_misericordia` | **mantenida** | mantenidas | mantenidos | Decreto *Misericors et miserator*, Congr. para el Culto Divino (5-V-2000); canonización de santa Faustina (30-IV-2000) |
| `divino_nino` | retirada | retiradas | sustituidas | P. Juan del Rizzo (1935); parroquia erigida por el arzobispo Perdomo (12-III-1942) |
| `nino_de_atocha` | retirada | retiradas | sustituidas | Sin documento pontificio localizado; santuario de Plateros (Zacatecas) |
| `nino_jesus_de_praga` | retirada | **mantenidas** | sustituidas | Aprobación del culto (1639); Orden Carmelita (24-X-1648); coronación (1651); León XIII (1896); Pío X (1913); Benedicto XVI (2009) |
| `senor_milagros` | retirada | retiradas | sustituidas | Terremoto del 13-XI-1655; Hermandad de Nazarenas y Arzobispado de Lima |
| `nazareno_de_san_pablo` | retirada | retiradas | sustituidas | Procesión documentada desde 1696; Basílica de Santa Teresa (Caracas) |
| `cristo_negro_esquipulas` | retirada | retiradas | sustituidas | Imagen de Quirio Cataño (1595); basílica por el arzobispo Pardo de Figueroa tras su curación (1735-1758) |
| `cristo_rey` | retirada | retiradas | mantenidos | Pío XI, encíclica *Quas Primas* (11-XII-1925) |
| `cristo_milagros` | retirada | retiradas | mantenidos | Devoción chilena sin verificación documental (la ficha misma lo advierte) |
| `santo_rostro` | retirada | retiradas | sustituidas | Devoción decimónica; imagen de Manoppello |
| `cristo_redentor` | retirada | retiradas | sustituidas | Estatua inaugurada en 1931 |
| `buen_pastor` | retirada | retiradas | sustituidas | IV Domingo de Pascua (Jornada de oraciones por las vocaciones); catacumbas s. II-III |
| `cristo_veracruz` | retirada | retiradas | sustituidas | Ermita de la Vera Cruz (La Laguna, Tenerife) |
| `senor_columna` | retirada | retiradas | sustituidas | Sin documento de aprobación específico |
| `cristo_yacente` | retirada | retiradas | sustituidas | Liturgia del Viernes Santo y del Santo Entierro |
| `cristo_camino` | retirada | retiradas | sustituidas | Devoción inspirada en Lc 24 |
| `cristo_paciencia` | retirada | retiradas | sustituidas | Sin documento de aprobación específico |
| `cristo_humildad` | retirada | retiradas | sustituidas | Devoción popular con arraigo en México |
| `cristo_sentenciado` | retirada | retiradas | sustituidas | Sin documento de aprobación específico |

## Nuevas devociones (18)

`preciosa_sangre`, `santisimo_sacramento`, `cinco_llagas`, `via_crucis`, `santisimo_nombre_de_jesus`, `senor_del_gran_poder`, `cristo_de_chalma`, `senor_de_los_temblores`, `santo_nino_de_cebu`, `jesus_de_ocotlan`, `bom_jesus_de_matosinhos`, `bom_jesus_da_lapa`, `cristo_de_medinaceli`, `cristo_de_limpias`, `divino_salvador_del_mundo`, `nino_jesus_de_belen`, `cristo_de_la_agonia`, `santo_cristo_de_la_victoria`.

Todas llevan `promises: null` y `prayer: null` (no se incluye oración nueva; no se ha encontrado una oración real y verificable que no fuera ya la que corresponde al rito o al texto litúrgico). `miracles` solo recoge lo que la tradición local afirma, con la salvedad de que la Iglesia no ha declarado milagros concretos con documentación canónica.

**Descartada:** `Señor de Sipán` — verificado: es un antiguo gobernador moche del siglo III, no una devoción cristiana. No se incluye.

## Fuentes (URL)

| Fuente | URL |
|---|---|
| Pío XI, *Miserentissimus Redemptor* (1928) | https://www.vatican.va/content/pius-xi/en/encyclicals/documents/hf_p-xi_enc_19280508_miserentissimus-redemptor.html |
| Pío XII, *Haurietis Aquas* (1956) | https://www.vatican.va/content/pius-xii/en/encyclicals/documents/hf_p-xii_enc_15051956_haurietis-aquas.html |
| Juan XXIII, motu proprio *Inde a primis* (30-VI-1960): Pío IX extendió la fiesta de la Preciosísima Sangre | http://www.vatican.va/content/john-xxiii/es/apost_letters/1960/documents/hf_j-xxiii_apl_19600630_indeaprimis.html |
| Decreto *Misericors et miserator* (5-V-2000) | https://thedivinemercy.org/message/tradition/pronouncements (texto del decreto) |
| Divino Niño del 20 de Julio (Bogotá) | https://www.ninojesus20dejulio.org/santuario |
| Iglesia del 20 de Julio (Bogotá) | https://www.sancristobal.gov.co/milocalidad/iglesia-del-20-julio |
| Cronología del Niño Jesús de Praga | https://www.gesubambino.org/es/el-nino-jesus/la-historia-del-nino-jesus/cronologia-de-praga/ |
| Señor de los Milagros (Lima), crónica | https://english.religion.info/2008/11/21/peru-the-festival-of-the-seor-de-los-milagros-lord-of-miracles |
| Nazareno de San Pablo (Caracas) | https://en.ultimasnoticias.com.ve/life/Nazarene-of-San-Pablo-arrived-at-the-altar-and-goes-out-into-the-street-today |
| Contrato de Quirio Cataño (1594) | https://muse.jhu.edu/article/713637 |
| Señor de los Temblores (Cusco) | https://andina.pe/agencia/noticia-senor-los-temblores-por-se-llama-asi-y-tradiciones-acoge-esta-festividad-1048716.aspx |
| Chalma: tradición del Cristo moreno | https://mexicocity.cdmx.gob.mx/venues/capilla-del-senor-de-chalma?lang=en |
| Santo Niño de Cebú: crónica de la coronación canónica (bula *Cunabula religionis*) | https://santoninodecebubasilica.org/chronicles/the-canonical-coronation-of-the-senor-sto-nino-de-cebu |
| Santo Niño de Cebú: desarrollo de la devoción | https://santoninodecebubasilica.org/chronicles/development-of-the-santo-nino-devotion |
| El Vía Crucis: origen y Clemente XII (1731) | https://concatedralalicante.com/el-via-crucis-origen-y-significado.html |
| Los prodigios del Santo Cristo de Limpias (1919) | https://cristodelimpias.org/prodigios-del-santo-cristo/ |
| *El Cristo de Limpias* (Biblioteca Digital de Castilla y León) | http://bibliotecadigital.jcyl.es/i18n/consulta/registro.cmd?id=11300 |
| Unesco, Santuario de Bom Jesus do Congonhas | https://whc.unesco.org/en/list/334 |
| Prefeitura de Congonhas, patrimônio histórico | https://www.congonhas.mg.gov.br/index.php/patrimonio-historico |
| IPAC Bahia, Romaria do Senhor Bom Jesus da Lapa | https://www.ba.gov.br/ipac/romaria-do-senhor-bom-jesus-da-lapa |
| Santuário do Bom Jesus da Lapa, historia | https://santuariodobomjesusdalapa.com/santuario/a-historia-do-santuario-do-bom-jesus-da-lapa/ |
| Fundación Medinaceli, historia de la imagen | https://fundacionmedinaceli.org/en/intangible-heritage/jesus-medinaceli/history-and-vocation/prototype-jesus-medinaceli |
| Calendario romano (Corpus Christi, Santísimo Nombre, Cinco Llagas) | https://www.vatican.va/roman_curia/congregations/ccdds/index.htm |
| Concilio de Trento, doctrina de la presencia real | https://www.vatican.va/archive/hist_councils/iv_trent.htm |

Fuentes institucionales sin URL pública citadas en las fichas: santuarios, cofradías y diócesis de Limpias, Esquipulas, Ocotlán, Medinaceli, Chalma, Cebú, San Francisco de La Paz, la Basílica del Santo Volto de Manoppello y la Arquidiócesis de San Salvador. Ya no se usa Wikipedia ni webs comerciales como fuente.

## Lo que no se pudo verificar

- **`cristo_milagros` (Chile)**: la ficha actual ya advertía que la fecha y el origen requerían verificación documental. Se mantiene con esa salvedad y sin reconocer un estatus eclesial.
- **`cristo_de_limpias` y `santo_cristo_de_la_victoria`**: no se localizó documento pontificio de aprobación; se describen como devociones diocesanas y populares.
- **`jesus_de_ocotlan`**: la tradición de la aparición de la imagen en el encino se presenta como tradición; los datos del santuario y del «palo de Ocotlán» se describen sin pretender un documento pontificio.
- **`cristo_de_la_agonia`**, **`cristo_camino`**, **`cristo_paciencia`**, **`cristo_humildad`**, **`cristo_sentenciado`**, **`senor_columna`**: devociones sin imagen histórica ni documento de aprobación; se describen como devociones inspiradas en los relatos evangélicos o en la piedad popular, sin afirmaciones de culto específico.
- **`santo_nino_de_cebu`**: la coronación canónica quedó precisada con la bula *Cunabula religionis* (27-II-1964) y la ceremonia del 28-IV-1965, tomadas de la Basílica del Santo Niño de Cebú.

## Correcciones de la revisión intermedia 1 aplicadas

> **Nota:** estas correcciones quedaron redactadas en este informe antes de aplicarse a los
> datos. Se aplicaron a `christ-new.json` y `christ-details.json` en el commit que cierra esta
> entrega; el validador da `0 errors · 0 warnings` en los dos ficheros.

- **`preciosa_sangre`**: Pío IX extendió la fiesta a la Iglesia universal por el decreto *Redempti sumus* (10-VIII-1849), no Pío V; se suprimió del Calendario Romano General en 1969 y hoy se conserva como misa votiva y devoción del mes de julio; se quitó la referencia a la Sangre de san Genaro y la invocación «Señor, ten piedad»; se corrigió «las/archicofradías». `feastDay` pasa a «Devoción del mes de julio; misa votiva».
- **`senor_del_gran_poder`**: año `1660` sin fuente → `""`; eliminadas las frases repetidas (la devoción «reúne a miles…» aparecía dos veces).
- **`cristo_de_limpias`**: ría del **Asón** (no del Pas); año `1400` sin fuente → `""`; eliminadas las repeticiones de «el santuario…»; se añadieron los fenómenos de 1919 como **relatos de testigos** («se decía que la imagen movía los ojos»), sin declararlos milagros.
- **`via_crucis`**: eliminada la frase sin fuente sobre san Francisco de Asís; eliminada la frase repetida sobre las catorce estaciones; se cita la fijación de las estaciones: **Clemente XII (1731)** y **Benedicto XIV (1742)**.
- **`santo_nino_de_cebu`**: sustituida Wikipedia por la Basílica del Santo Niño, la bula *Cunabula religionis* (27-II-1964), la coronación canónica (28-IV-1965) y el documento *Ut clarificetur* (1-IV-1965).
- **Wikipedia fuera de las fuentes**: sustituida en las 5 entradas con ella (`cristo_de_chalma`, `senor_de_los_temblores`, `santo_nino_de_cebu`, `jesus_de_ocotlan`, `cristo_de_medinaceli`) y también en las 5 de la auditoría (`nino_de_atocha`, `nino_jesus_de_praga`, `cristo_negro_esquipulas`, `santo_rostro`, `cristo_redentor`, esta última por IPHAN). Fuera también las webs comerciales («Mexico Unexplored», «taytachatemblores.com», «El Salvador Travel») y la fuente que no correspondía (`Romaria do Senhor Bom Jesus da Lapa` en la ficha de Matosinhos).
- **Frases repetidas**: revisadas y quitadas en `senor_del_gran_poder`, `cristo_de_limpias`, `via_crucis`, `santo_cristo_de_la_victoria` y `cristo_de_la_agonia`.
- **Las 20 existentes**: repasadas también sus fuentes; el contenido de `christ-details.json` no cambiaba en esta ronda salvo las fuentes.
- **Ortografía**: repasada antes del push.

## Validación después de corregir

```bash
python docs/tasks/tools/validate_devotions.py details christ app/src/main/assets/devotions/christ-details.json
# 20 entries · 0 errors · 0 warnings
python docs/tasks/tools/validate_devotions.py new christ app/src/main/assets/devotions/christ-new.json
# 18 entries · 0 errors · 0 warnings
```

## Frecuencia de push

`fork/ext/T-019`: `8d312db` (auditoría, 20), `821b1ef` (nuevas, 18), `e9c4db4` (REPORT),
más el `merge` de `origin/CatholicGPT` con `T-019-REVIEW.md`. El commit que sigue a esta línea
aplica las correcciones de la revisión intermedia 1 a los dos JSON y deja ambos validadores
a 0 errores y 0 avisos.
