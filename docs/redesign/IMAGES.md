# Image provenance (licence record)

All redesign art was sourced from Wikimedia Commons. Each file's licence was read from the Commons API (`extmetadata.LicenseShortName` / `License`) at download time, and only **Public domain** or **CC0** files were accepted (CC BY, CC BY-SA, PDM-owner and other licences were rejected). Files were downloaded as the Commons thumbnail rendition (no originals), then cropped where noted, resized to a longest side of 1400 px or less, converted to sRGB and saved as JPEG (quality 76–84, metadata stripped).

Paintings are public domain because the artists died more than 100 years ago, and faithful reproductions of 2D public-domain works are not copyrightable (Commons PD-Art). The Kazimirowski *Divine Mercy* (1934) is tagged public domain on Commons: the painter died in 1939, so the term of 70 years after death has expired.

Per-image focus point and tone (for cropping and scrims) live in each folder's `manifest.json`. A labelled contact sheet is in `docs/redesign/images_sheet.jpg`.


## Section heroes: `app/src/main/assets/heroes/lumen/`

| File | Title | Artist | Year | Licence | Source (Commons) | Notes |
|---|---|---|---|---|---|---|
| `home.jpg` | Morning in the Riesengebirge | Caspar David Friedrich | 1810–1811 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Caspar_David_Friedrich,_Morgen_im_Riesengebirge.jpg) |  |
| `prayers_lily.jpg` | Lilium candidum (Les Liliacées) | Pierre-Joseph Redouté | 1802–1816 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Lilium_candidum_in_Les_liliacees.jpg) | cropped to remove the engraved caption and credits |
| `mary.jpg` | The Virgin in Prayer | Giovanni Battista Salvi da Sassoferrato | 1640–1650 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:SASSOFERRATO_-_Virgen_rezando_(National_Gallery,_Londres,_1640-50).jpg) |  |
| `christ_thorns.jpg` | Ecce Homo (Christ with the Reed) | Guido Reni | c. 1639–1640 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Reni_-_Le_Christ_au_roseau,_dit_aussi_Ecce_Homo,_INV_528_;_MR_280.jpg) | cropped to remove the grey scan border |
| `rosary.jpg` | Rosary of Queen Marie-Amélie | Anonymous (French jeweller) | 19th century | CC0 | [Commons](https://commons.wikimedia.org/wiki/File:Chapelet_de_la_reine_Marie-Am%C3%A9lie,_2013.11.1.jpg) |  |
| `bible.jpg` | Old Woman Reading (Rembrandt's Mother) | Gerrit Dou | c. 1631–1632 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Gerard_Dou_005.jpg) |  |
| `mass.jpg` | The Last Supper | Leonardo da Vinci | 1495–1498 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Leonardo_da_Vinci_(1452-1519)_-_The_Last_Supper_(1495-1498).jpg) | cropped above the later doorway cut into the fresco |
| `angelus.jpg` | The Angelus | Jean-François Millet | 1857–1859 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Jean-Fran%C3%A7ois_Millet_-_The_Angelus_-_Google_Art_Project.jpg) |  |
| `night.jpg` | The Cross beside the Baltic | Caspar David Friedrich | c. 1815 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Caspar_David_Friedrich_-_Kreuz_an_der_Ostsee_-_Schloss_Carlottenburg,_Neuer_Pavillon.jpg) |  |
| `habits.jpg` | Morning (Times of Day cycle) | Caspar David Friedrich | 1821–1822 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Caspar_David_Friedrich_-_Tageszeitenzyklus,_Der_Morgen_(1821-22).jpg) |  |
| `catechism.jpg` | Disputation of the Holy Sacrament | Raphael | 1509–1510 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Disputa_del_Sacramento_(Rafael).jpg) | cropped above the doorway at lower right |
| `doctrine.jpg` | Interior of St. Peter's, Rome | Giovanni Paolo Panini | 1750 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Giovanni_Paolo_Panini_-_Interior_of_St._Peter%27s,_Rome_-_56.43_-_Detroit_Institute_of_Arts.jpg) |  |
| `history.jpg` | Interior of the Pantheon, Rome | Giovanni Paolo Panini | c. 1734 | CC0 | [Commons](https://commons.wikimedia.org/wiki/File:Giovanni_Paolo_Panini,_Interior_of_the_Pantheon,_Rome,_c._1734,_NGA_165.jpg) |  |
| `mary_jesus.jpg` | Immaculate Conception of Los Venerables (Soult) | Bartolomé Esteban Murillo | c. 1660–1665 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Murillo_-_Inmaculada_Concepci%C3%B3n_de_los_Venerables_o_de_Soult_(Museo_del_Prado,_1678).jpg) |  |
| `questions.jpg` | Norham Castle, Sunrise | J. M. W. Turner | c. 1845 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Joseph_Mallord_William_Turner_-_Norham_Castle,_Sunrise_-_WGA23182.jpg) |  |
| `orders.jpg` | Monks in the Cloister of the Church of Gesù e Maria, Rome | François Marius Granet | 1808 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Monks_in_the_Cloister_of_the_Church_of_Ges%C3%B9_e_Maria,_Rome.jpg) |  |
| `spiritual.jpg` | The Sermon on the Mount | Carl Bloch | 1877 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Bloch-SermonOnTheMount.jpg) |  |
| `pilgrimage.jpg` | Wanderer above the Sea of Fog | Caspar David Friedrich | c. 1818 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Caspar_David_Friedrich_-_Wanderer_above_the_Sea_of_Fog.jpeg) |  |
| `parishes.jpg` | Chartres Cathedral | Jean-Baptiste Camille Corot | 1830 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Corot_-_La_cath%C3%A9drale_de_Chartres._RF_1614.jpg) | cropped to remove canvas edges and signature |
| `news.jpg` | View of Rome: The Bridge and Castel Sant'Angelo with the Cupola of St. Peter's | Jean-Baptiste Camille Corot | 1826–1827 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:View_of_Rome_-_The_Bridge_and_Castel_Sant%27Angelo_with_the_Cupola_of_St._Peter%27s.jpg) |  |
| `explore.jpg` | Meadows near Greifswald | Caspar David Friedrich | c. 1821–1822 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Wiesen_bei_Greifswald_(1821-1822)_-_Caspar_David_Friedrich_(Hamburger_Kunsthalle).jpg) |  |

## Rosary mysteries: `app/src/main/assets/heroes/mysteries/`

| File | Title | Artist | Year | Licence | Source (Commons) | Notes |
|---|---|---|---|---|---|---|
| `joyful_1_annunciation.jpg` | The Annunciation | Carl Bloch | c. 1865–1879 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Carl_Heinrich_Bloch_-_The_Annunciation.jpg) |  |
| `joyful_2_visitation.jpg` | The Visitation | Mariotto Albertinelli | 1503 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Albertinelli_-_Visitazione_(nella_predella_Episodi_dell%E2%80%99Infanzia_di_Cristo),_1503,_1890_n.1587_(cropped).jpg) |  |
| `joyful_3_nativity.jpg` | The Adoration of the Shepherds | Bartolomé Esteban Murillo | c. 1650 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Adoration_of_the_Shepherds,_Murillo_(Prado_Museum).jpg) |  |
| `joyful_4_presentation.jpg` | The Presentation in the Temple | Fra Bartolomeo | 1516 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Baccio_della_Porta,_gen._Fra_Bartolomeo,_Kunsthistorisches_Museum_Wien,_Gem%C3%A4ldegalerie_-_Darbringung_Christi_im_Tempel_-_GG_207_-_Kunsthistorisches_Museum.jpg) |  |
| `joyful_5_finding.jpg` | Jesus among the Doctors | Jean-Auguste-Dominique Ingres | 1842–1862 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Mus%C3%A9e_Ingres-Bourdelle_-_J%C3%A9sus_parmi_les_docteurs_-_Ingres_-_Joconde06070001450.jpg) |  |
| `luminous_1_baptism.jpg` | The Baptism of Christ | Piero della Francesca | c. 1450s | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Piero_della_Francesca_-_Battesimo_di_Cristo_(National_Gallery,_London).jpg) |  |
| `luminous_2_cana.jpg` | The Wedding at Cana | Carl Bloch | c. 1870 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:CarlBloch_weddingatCana.jpg) |  |
| `luminous_3_proclamation.jpg` | Let the Little Children Come unto Me | Carl Bloch | c. 1865–1879 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Carl_Heinrich_Bloch_-_Suffer_the_Children.jpg) |  |
| `luminous_4_transfiguration.jpg` | The Transfiguration | Raphael | 1516–1520 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Rapha%C3%ABl,_La_Transfiguration,_1518%E2%80%931520,_huile_sur_bois,_405_%C3%97_278_cm,_Mus%C3%A9es_du_Vatican,_Pinacoth%C3%A8que,_inv._4.jpg) |  |
| `luminous_5_eucharist.jpg` | The Last Supper | Juan de Juanes | c. 1562 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:La_%C3%9Altima_Cena_(Juan_de_Juanes)_(restaurada).jpg) | cropped to remove the gilt frame |
| `sorrowful_1_agony.jpg` | Christ in Gethsemane | Carl Bloch | 1873 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Gethsemane_Carl_Bloch.jpg) |  |
| `sorrowful_2_scourging.jpg` | The Flagellation of Our Lord Jesus Christ | William-Adolphe Bouguereau | 1880 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:William-Adolphe_Bouguereau_(1825-1905)_-_The_Flagellation_of_Our_Lord_Jesus_Christ_(1880).jpg) |  |
| `sorrowful_3_crowning.jpg` | Christ Crowned with Thorns | Anthony van Dyck | c. 1620 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Anthonis_van_Dyck_004.jpg) |  |
| `sorrowful_4_carrying.jpg` | Christ Falls on the Way to Calvary (Lo Spasimo) | Raphael | 1517 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Raphael_SpasimoFXD.jpg) |  |
| `sorrowful_5_crucifixion.jpg` | Christ Crucified | Diego Velázquez | c. 1632 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Cristo_crucificado.jpg) |  |
| `glorious_1_resurrection.jpg` | The Resurrection | Carl Bloch | 1875 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Carl_Heinrich_Bloch_-_The_Resurrection.jpg) |  |
| `glorious_2_ascension.jpg` | The Ascension | John Singleton Copley | 1775 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Jesus_ascending_to_heaven.jpg) |  |
| `glorious_3_pentecost.jpg` | Pentecost | Juan Bautista Maíno | 1620–1625 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Maino_Pentecost%C3%A9s,_1620-1625._Museo_del_Prado.jpg) |  |
| `glorious_4_assumption.jpg` | The Assumption of the Virgin | Nicolas Poussin | c. 1630–1632 | CC0 | [Commons](https://commons.wikimedia.org/wiki/File:Nicolas_Poussin,_The_Assumption_of_the_Virgin,_c._1630-1632,_NGA_46470.jpg) |  |
| `glorious_5_coronation.jpg` | The Coronation of the Virgin | Diego Velázquez | 1635–1636 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Diego_Vel%C3%A1zquez_-_Coronation_of_the_Virgin_-_Prado.jpg) |  |
| `divine_mercy.jpg` | Divine Mercy (Jesus, I trust in You) | Eugeniusz Kazimirowski | 1934 | Public domain | [Commons](https://commons.wikimedia.org/wiki/File:Divina_Misericordia_(Eugeniusz_Kazimirowski,_1934).jpg) |  |

## Not delivered

Nothing was skipped: every requested slot has a public-domain or CC0 image.

