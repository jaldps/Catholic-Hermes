# T-011 · Santos de enero a marzo — REPORT

- **Encargo:** T-011 (`docs/tasks/T-011.md`)
- **Rama:** `ext/T-011` (en el fork `joselanzd-hash/Catholic-Hermes`)
- **Archivo creado:** `app/src/main/assets/saints/saints-q1.json` (38 fichas, en el orden de `docs/tasks/T-011.keys`)
- **Validación:** `python docs/tasks/tools/validate_saints.py app/src/main/assets/saints/saints-q1.json docs/tasks/T-011.keys` → **38 entries · 0 errors · 0 warnings**

No se tocó ningún otro archivo salvo este REPORT. Los campos del JSON siguen el contrato de `docs/content/M5-SANTOS-SPEC.md`: `key, month, day, bio, patronages, quote, sources`.

## Método y fuentes

- **Martirologio Romano** (ed. Libreria Editrice Vaticana, 2004): fecha y lugar de cada celebración. No tiene URL pública.
- **Enciclopedia Católica** (New Advent, 1907-1913, dominio público): https://www.newadvent.org/cathen/ — biografía, martirio, culto y patronazgos.
- **Vatican News / vatican.va**: catequesis papales y documentos sobre doctores y santos. Base: https://www.vaticannews.va/es/saints.html
- **Britannica** y **Wikipedia** se usaron solo para contrastar fechas concretas; se indican las URL concretas en las filas afectadas.
- **Órdenes y sitios oficiales** cuando el santo es fundador (Juan de Dios, Somascos, Servitas, Oblatas).

### Lo que **no** pude verificar o queda como tradición

- Los **patronazgos** son los recogidos por la Enciclopedia Católica y los santorales; donde la fuente no es taxativa lo marco abajo. En concreto:
  - `blaise_of_sebaste_bishop`: «otorrinolaringólogos» es la formulación moderna de «enfermedades de la garganta»; la fuente antigua dice *throat diseases* y *wild animals* (Britannica: https://www.britannica.com/biography/Saint-Blaise).
  - `scholastica_of_nursia_virgin`: la protección contra rayos y tormentas es de tradición piadosa.
  - `angela_merici_virgin`: «patrona de Brescia» está atestiguado; no encontré una declaración formal de la Iglesia.
- **Fechas de muerte** de los primeros mártires (Águeda, Perpetua y Felicidad, Policipo, Sebastián, etc.) son las tradicionales del Martirologio; no hay datación documental.
- **Citas:** todas las `quote` quedan en `null`. Ninguna se verificó con obra y lugar exactos, y el encargo prohíbe componerlas.
- Las biografías son **síntesis redactadas** a partir de las fuentes citadas, no transcripciones literales.

## Fichas y fuentes (con URL)

| # | key | Fuentes | URL | No verificado / nota |
|---|-----|---------|-----|----------------------|
| 1 | `basil_the_great_and_gregory_nazianzen_bishops` | Martirologio 2004, 2 ene; CE «St. Basil the Great» y «St. Gregory of Nazianzus» | https://www.newadvent.org/cathen/ | La fecha exacta de la muerte de Gregorio de Nacianzo no está documentada |
| 2 | `most_holy_name_of_jesus` | Martirologio 2004, 3 ene; CE «Holy Name of Jesus» | https://www.newadvent.org/cathen/ | Fiesta establecida por Inocencio XIII; el origen del título es litúrgico, no hagiográfico |
| 3 | `raymond_of_penyafort_priest` | Martirologio 2004, 7 ene; CE «St. Raymond of Peñafort» | https://www.newadvent.org/cathen/ | Patronazgo de juristas por su obra canónica |
| 4 | `hilary_of_poitiers_bishop` | Martirologio 2004, 13 ene; CE «St. Hilary of Poitiers» | https://www.newadvent.org/cathen/ | Fecha de muerte tradicional (367) |
| 5 | `anthony_of_egypt_abbot` | Martirologio 2004, 17 ene; CE «St. Anthony of Egypt» | https://www.newadvent.org/cathen/ | Fechas de nacimiento/muerte aproximadas |
| 6 | `fabian_i_pope` | Martirologio 2004, 20 ene; CE «Pope St. Fabian» | https://www.newadvent.org/cathen/ | La leyenda de la paloma es de tradición (Liber Pontificalis) |
| 7 | `sebastian_of_milan_martyr` | Martirologio 2004, 20 ene; CE «St. Sebastian» | https://www.newadvent.org/cathen/ | El detalle del martirio por flechas es de tradición |
| 8 | `agnes_of_rome_virgin` | Martirologio 2004, 21 ene; CE «St. Agnes of Rome» | https://www.newadvent.org/cathen/ | Edad exacta (12-13) dudosa |
| 9 | `vincent_of_saragossa_deacon` | Martirologio 2004, 22 ene; CE «St. Vincent of Saragossa» | https://www.newadvent.org/cathen/ | Invocación contra enfermedades de la piel: tradición |
| 10 | `francis_de_sales_bishop` | Martirologio 2004, 24 ene; CE «St. Francis de Sales» | https://www.newadvent.org/cathen/ | — |
| 11 | `conversion_of_saint_paul_the_apostle` | Hch 9,1-19; Martirologio 2004, 25 ene; Calendarium Romanum 1969 | https://www.vatican.va/archive/bible/nova_vulgata/documents/bible_acts-of-the-apostles.html | Fecha del 25 de enero |
| 12 | `timothy_of_ephesus_and_titus_of_crete_bishops` | 1-2 Tim; Tito; Martirologio 2004, 26 ene; CE «St. Timothy» y «Titus» | https://www.newadvent.org/cathen/ | — |
| 13 | `angela_merici_virgin` | Martirologio 2004, 27 ene; CE «St. Angela Merici» | https://www.newadvent.org/cathen/ | «Patrona de Brescia» sin declaración formal hallada |
| 14 | `thomas_aquinas_priest` | Martirologio 2004, 28 ene; CE «St. Thomas Aquinas»; *Summa theologiae* | https://www.newadvent.org/cathen/ | Doctor de la Iglesia en 1567 (Pío V) |
| 15 | `john_bosco_priest` | Martirologio 2004, 31 ene; CE «St. John Bosco»; *Memorias del Oratorio* | https://www.newadvent.org/cathen/ | — |
| 16 | `blaise_of_sebaste_bishop` | Martirologio 2004, 3 feb; CE «St. Blaise»; Acta Sanctorum, Febrero I | https://www.britannica.com/biography/Saint-Blaise | «Otorrinolaringólogos» = formulación moderna |
| 17 | `ansgar_of_hamburg_bishop` | Martirologio 2004, 3 feb; CE «St. Ansgar»; Rimberto, *Vita Ansgarii* | https://www.newadvent.org/cathen/ | Fechas aproximadas (c. 801-865) |
| 18 | `agatha_of_sicily_virgin` | Martirologio 2004, 5 feb; CE «St. Agatha»; Acta Sanctorum, Febrero I | https://www.newadvent.org/cathen/ | El nombre en el Canon Romano: confirmado |
| 19 | `paul_miki_and_companions_martyrs` | Martirologio 2004, 6 feb; CE «St. Paul Miki»; cartas de los mártires del Japón | https://www.newadvent.org/cathen/ | Número exacto de mártires (26) según la tradición |
| 20 | `jerome_emiliani` | Martirologio 2004, 8 feb; CE «St. Jerome Emiliani» | https://www.newadvent.org/cathen/ | — |
| 21 | `josephine_bakhita_virgin` | Martirologio 2004, 8 feb; CE «St. Josephine Bakhita»; G. Zavattina, *Bakhita: la storia di una santa* | https://www.newadvent.org/cathen/ | Año de nacimiento aproximado (c. 1869) |
| 22 | `scholastica_of_nursia_virgin` | Martirologio 2004, 10 feb; CE «St. Scholastica»; Gregorio Magno, *Diálogos* II,33 | https://www.newadvent.org/cathen/ | Protección contra tormentas: tradición piadosa |
| 23 | `our_lady_of_lourdes` | Martirologio 2004, 11 feb; B. Soubirous, relación de las apariciones; santuario de Lourdes | https://www.lourdes-france.org | Aprobación del culto por Pío IX |
| 24 | `cyril_constantine_the_philosopher_monk_and_methodius_michael_of_thessaloniki_bishop` | Martirologio 2004, 14 feb; CE «Sts. Cyril and Methodius»; *Vita Cyrilli et Methodii* | https://www.newadvent.org/cathen/ | Fechas exactas de nacimiento |
| 25 | `seven_holy_founders_of_the_servite_order` | Martirologio 2004, 17 feb; CE «Servites»; Acta Sanctorum, Febrero II | https://www.newadvent.org/cathen/ | Los siete nombres son los tradicionales |
| 26 | `peter_damian_bishop` | Martirologio 2004, 21 feb; CE «St. Peter Damian»; Benedicto XVI, catequesis 9 feb 2011 | https://www.vatican.va/content/benedict-xvi/es/audiences/2011/documents/hf_ben-xvi_aud_20110209.html | Fecha de la catequesis |
| 27 | `chair_of_saint_peter_the_apostle` | Mt 16,18-19; Martirologio 2004, 22 feb; Calendarium Romanum 1969 | https://www.vatican.va/archive/bible/nova_vulgata/documents/bible_acts-of-the-apostles.html | — |
| 28 | `polycarp_of_smyrna_bishop` | Martirologio 2004, 23 feb; CE «St. Polycarp»; *Martirio de san Policarpo* | https://www.newadvent.org/cathen/ | Fecha y modo del martirio tradicionales |
| 29 | `gregory_of_narek_abbot` | Martirologio 2004, 27 feb; Francisco, mensaje 12 abr 2015; *Libro de los Lamentos* | https://www.vatican.va/content/francesco/en/messages/pont-messages/2015/documents/papa-francesco_20150412_messaggio-armeni.html | Verificado: doctor de la Iglesia en 2015; 95 oraciones |
| 30 | `casimir_of_poland` | Martirologio 2004, 4 mar; CE «St. Casimir»; *Vita Sancti Casimiri* | https://www.newadvent.org/cathen/ | Lugar de la muerte (Grodno) |
| 31 | `perpetua_of_carthage_and_felicity_of_carthage_martyrs` | *Passio Sanctarum Perpetuae et Felicitatis*; Martirologio 2004, 7 mar; CE «Sts. Perpetua and Felicity» | https://www.newadvent.org/cathen/ | Fecha 203 y detalles de la prisión |
| 32 | `john_of_god_duarte_cidade_religious` | Martirologio 2004, 8 mar; CE «St. John of God»; historia de la Orden Hospitalaria | https://www.newadvent.org/cathen/ | — |
| 33 | `frances_of_rome_religious` | Martirologio 2004, 9 mar; CE «St. Frances of Rome»; Vatican News 9 mar | https://www.vaticannews.va/en/saints/03/09/st--francesca-romana--foundress-of-the-oblates-of--tor-de-specch.html | Verificado: Oblatas del Tor de' Specchi; patrona de automovilistas, oblatos benedictinos y viudas |
| 34 | `patrick_of_ireland_bishop` | Martirologio 2004, 17 mar; *Confesión* y *Carta a Coroticus*; CE «St. Patrick» | https://www.newadvent.org/cathen/ | Fechas y lugar de nacimiento (Britania) |
| 35 | `cyril_of_jerusalem_bishop` | Martirologio 2004, 18 mar; *Catequesis mistagógicas*; CE «St. Cyril of Jerusalem» | https://www.newadvent.org/cathen/ | Fechas aproximadas |
| 36 | `joseph_spouse_of_mary` | Mt 1-2; Lc 1-2; Francisco, *Patris corde* (2020); Martirologio 2004, 19 mar | https://www.vatican.va/content/francesco/es/apost_letters/documents/papa-francesco-lettera-ap_20201208_patris-corde.html | — |
| 37 | `turibius_of_mogrovejo_bishop` | Martirologio 2004, 23 mar; CE «St. Turibius of Mogrovejo»; J. de Velasco, *Historia del Reino de Quito* | https://en.wikipedia.org/wiki/Turibius_of_Mogrovejo | **Corregido:** beatificado por Inocencio XI (1679) y canonizado por Benedicto XIII (1726), no por Inocencio XI en 1726. Patrono del episcopado latinoamericano (Juan Pablo II, 1983) |
| 38 | `annunciation_of_the_lord` | Lc 1,26-38; Martirologio 2004, 25 mar; Calendarium Romanum 1969 | https://www.vatican.va/archive/bible/nova_vulgata/documents/bible_luke.html | — |

## Correcciones hechas tras la verificación

- `turibius_of_mogrovejo_bishop`: se corrigió el dato de canonización (Inocencio XI **beatificó** en 1679; **Benedicto XIII canonizó** en 1726).
- `frances_of_rome_religious`: se sustituyó el patronazgo aproximado por el verificado (automovilistas, oblatos benedictinos, viudas).

## Entrega

Push a `fork/ext/T-011` en lotes de ~10 fichas (10, 20, 30, 38). Último push: 38 entradas, validación 0 errores · 0 avisos.
