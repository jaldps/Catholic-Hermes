# T-017 · Cargos y ministerios de la Iglesia (34 nuevos)

**Asignado:** Hermes · **Rama:** `ext/T-017` (fork) · **Estado:** ABIERTO, pendiente de revisión.

## Entrega

| Archivo | Contenido |
|---|---|
| `app/src/main/assets/orders/offices-new.json` | 34 cargos y ministerios nuevos, con `name`, `description` (es/en/pt), `group`, `after` y `sources` |

No se tocó ningún otro archivo (`ReligiousOrdersData.kt` queda intacto, como pide la spec).

## Validación

```bash
python docs/tasks/tools/validate_orders.py offices app/src/main/assets/orders/offices-new.json
# 34 entries · 0 errors · 7 warnings
```

Los 7 avisos son todos `name: identical text in two languages` y son **correctos**: son títulos que se escriben igual en español y portugués (`Administrador apostólico`, `Delegado apostólico`, `Camarlengo/Camerlengo`, `Prelado territorial`, `Diácono permanente`, `Prior`, `Superior provincial`). No hay descripciones duplicadas ni campos vacíos.

## Fuentes (URL)

| Fuente | URL |
|---|---|
| Código de Derecho Canónico (edición española, vatican.va) | https://www.vatican.va/archive/cod-iuris-canonici/cic_index_sp.html |
| Código de los Cánones de las Iglesias Orientales (CCEO, latino) | http://vatican.va/holy_father/john_paul_ii/apost_constitutions/documents/hf_jp-ii_apc_19901018_codex-can-eccl-orient-1_lt.html |
| Praedicate Evangelium (constitución apostólica, 19-III-2022) | https://www.vatican.va/content/francesco/es/apost_constitutions/documents/20220319-costituzione-ap-praedicate-evangelium.html |
| Spirituali militum curae (constitución apostólica, 21-IV-1986) | https://www.vatican.va/content/john-paul-ii/es/apost_constitutions/documents/hf_jp-ii_apc_19860421_spirituali-militum-curae.html |
| Ut sit (constitución apostólica, 28-XI-1982) | https://www.vatican.va/content/john-paul-ii/es/apost_constitutions/documents/hf_jp-ii_apc_19821128_ut-sit.html |
| Pontificalis Domus (motu proprio, 28-III-1968) | http://www.vatican.va/content/paul-vi/la/motu_proprio/documents/hf_p-vi_motu-proprio_19680328_pontificalis-domus.html |
| Antiquum ministerium (motu proprio, 10-V-2021) | https://www.vatican.va/content/francesco/es/motu_proprio/documents/papa-francesco-motu-proprio-20210510_antiquum-ministerium.html |
| Carta sobre el rito de institución de los catequistas (3-XII-2021) | https://www.vatican.va/roman_curia/congregations/ccdds/documents/rc_con_ccdds_doc_20211203_lettera-rito-istituzione-catechisti_sp.html |
| Spiritus Domini (motu proprio, 10-I-2021) | https://www.vatican.va/content/francesco/es/motu_proprio/documents/papa-francesco-motu-proprio-20210110_spiritus-domini.html |
| Instrucción Ecclesiae Sponsae Imago sobre el Ordo virginum (8-VI-2018) | https://www.vatican.va/roman_curia/congregations/ccscrlife/documents/rc_con_ccscrlife_doc_20180608_istruzione-ecclesiaesponsaeimago_sp.html |
| Ordo consecrationis virginum (editio typica, 31-V-1970) | citado en Ecclesiae Sponsae Imago, n. 9 (ed. Typis Polyglottis Vaticanis, 1970) |
| Pontificale Romanum, Benedictio viduarum | rito propio distinto de la consagración de vírgenes; atestado en los pontificios (p. ej. colección usuarium.elte.hu). Sin página oficial en vatican.va. |

## Entradas por grupo

### governance (20)

| id | cargo | base canónica |
|---|---|---|
| `office_obispo_auxiliar` | Obispo auxiliar | CIC cc. 403-411 |
| `office_obispo_coadjutor` | Obispo coadjutor | CIC cc. 403 y 409 |
| `office_obispo_emperito` | Obispo emérito | CIC cc. 401-402 (el CIC dice «obispo dimisionario») |
| `office_administrador_apostolico` | Administrador apostólico | CIC cc. 368 y 381 §2 |
| `office_vicario_general` | Vicario general | CIC cc. 475-481 |
| `office_vicario_episcopal` | Vicario episcopal | CIC cc. 476-481 |
| `office_vicario_judicial` | Vicario judicial | CIC cc. 1419-1421 |
| `office_canciller_curia` | Canciller de la curia | CIC cc. 482-486 |
| `office_economo_diocesano` | Ecónomo diocesano | CIC c. 494 (y 493) |
| `office_decano_colegio_cardenalicio` | Decano del Colegio Cardenalicio | CIC cc. 350 §4, 352 y 355 |
| `office_nuncio_apostolico` | Nuncio apostólico | CIC cc. 362-367 (365 §1) |
| `office_delegado_apostolico` | Delegado apostólico | CIC cc. 362-367 (363 §2) |
| `office_secretario_de_estado` | Secretario de Estado | Praedicate Evangelium, arts. 44-52 |
| `office_prefecto_dicasterio` | Prefecto de dicasterio | Praedicate Evangelium, arts. 174-188 |
| `office_camarlengo` | Camarlengo | Praedicate Evangelium, arts. 235-237 |
| `office_penitenciario_mayor` | Penitenciario mayor | Praedicate Evangelium, arts. 190-193 |
| `office_ordinario_militar` | Ordinario militar | Spirituali militum curae (1986) |
| `office_prelado_territorial` | Prelado territorial | CIC cc. 368 y 370 |
| `office_abad_territorial` | Abad territorial | CIC cc. 368 y 370 |
| `office_prelado_personal` | Prelado personal | CIC cc. 294-297; Ut sit (1982) |

### clergy (5)

| id | cargo | base canónica |
|---|---|---|
| `office_arzobispo_mayor` | Arzobispo mayor | CCEO cc. 151-154 |
| `office_diacono_permanente` | Diácono permanente | CIC cc. 236 y 288; c. 1031 §2 |
| `office_rector_seminario` | Rector del seminario | CIC cc. 235-264 (238 §2, 261) |
| `office_capellan` | Capellán | CIC cc. 564-569 |
| `office_monsenor` | Monseñor | Pontificalis Domus (1968); título honorífico, no cargo |

### consecrated (7)

| id | cargo | base canónica |
|---|---|---|
| `office_abad` | Abad | CIC cc. 615-617 y 620 |
| `office_prior` | Prior | CIC cc. 620-625; CCEO cc. 418 y 508 (título del derecho propio, no del universal) |
| `office_superior_provincial` | Superior provincial | CIC cc. 620-625 |
| `office_superior_general` | Superior general | CIC cc. 622-625 |
| `office_virgen_consagrada` | Virgen consagrada | CIC c. 604 §1; Ordo consecrationis virginum (1970); Ecclesiae Sponsae Imago (2018) |
| `office_ermiteno` | Ermitaño | CIC c. 603 |
| `office_viuda_consagrada` | Viuda consagrada | CIC c. 604 (no la menciona); Ordo consecrationis virginum (1970); Benedictio viduarum |

### altar / mission (2)

| id | cargo | base canónica |
|---|---|---|
| `office_catequista_instituido` | Catequista instituido | Antiquum ministerium (10-V-2021); rito de institución (3-XII-2021) |
| `office_ministro_extraordinario_comunion` | Ministro extraordinario de la comunión | CIC cc. 230 §3 y 910 §2; Spiritus Domini (10-I-2021) |

## Orden (`after`)

La cadena respeta la jerarquía: los obispos auxiliar/coadjutor/emérito van tras `office_obispo`; los vicarios y la curia tras ellos; los cargos de la Curia Romana (secretario de Estado, prefecto, camarlengo, penitenciario) van en ese orden; `office_monsenor` va tras `office_capellan`; `office_arzobispo_mayor` tras `office_monsenor`; `office_catequista_instituido` tras `office_misionero_laico`; `office_ministro_extraordinario_comunion` tras `office_acolito`.

## Decisiones y avisos

- **`office_obispo_emperito`**: el CIC de 1983 usa el tecnicismo «obispo dimisionario» (c. 402 §1), no «emérito». La ficha usa el nombre común y lo aclara en la descripción.
- **`office_prior`**: no es una figura del derecho universal; el CIC y el CCEO regulan «superiores mayores» y «provincia». Se cita el derecho propio del instituto como fuente de la denominación.
- **`office_viuda_consagrada`**: el CIC de 1983 **no** la menciona (c. 604 solo habla del orden de las vírgenes) y el *Ordo consecrationis virginum* de 1970 no la incluye. La liturgia distingue la *consecratio virginum* de la *benedictio viduarum*, rito propio atestado en los pontificios. La ficha lo explica con precisión en vez de equipararla a la virgen consagrada.
- **`office_monsenor`**: se explica que es un título honorífico, no un cargo ni un sacramento.
- **`office_ordinario_militar`**: el CIC de 1983 no contempla el ordinariato militar; su base es *Spirituali militum curae* (1986). Se indica que es una circunscripción de carácter personal, no una diócesis.
- **No verificable:** la fecha exacta del establecimiento de la *benedictio viduarum* en el Pontifical Romano no se ha podido confirmar en una fuente primaria oficial; por eso no se da ninguna fecha y se describe solo la distinción entre ambos ritos.

## Frecuencia de push

`fork/ext/T-017`: `60966a2` (1-10), `19562da` (11-20), `eaaf4e3` (21-25), `d06c5d0` (26-34).
