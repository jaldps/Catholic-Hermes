package com.example.data.repository

import com.example.data.models.LocalizedText

object CatechismFullData {
    const val sourceRetrievedOn = "2026-11-12"
    const val officialEnglishSource = "https://www.vatican.va/archive/ENG0015/_INDEX.HTM"
    const val officialSpanishSource = "https://www.vatican.va/archive/catechism_sp/index_sp.html"
    const val officialPortugueseSource = "https://www.vatican.va/archive/cathechism_po/index_new/prima-pagina-cic_po.html"
    val sourceNotice = LocalizedText(
        "Texto del Catecismo de la Iglesia Católica publicado por la Santa Sede, consultado el 12 de noviembre de 2026; incluye la revisión de 2018 del §2267. Se normalizó el formato HTML. Las notas completas se consultan en la fuente oficial.",
        "Catechism of the Catholic Church text published by the Holy See, retrieved 12 November 2026; includes the 2018 revision of §2267. HTML formatting has been normalized. Consult the official source for full footnotes.",
        "Texto do Catecismo da Igreja Católica publicado pela Santa Sé, consultado em 12 de novembro de 2026; inclui a revisão de 2018 do §2267. A formatação HTML foi normalizada. Consulte a fonte oficial para as notas completas."
    )

    val contentByNumber: Map<Int, LocalizedText> = buildMap {
        putAll(CatechismFullData00.entries)
        putAll(CatechismFullData01.entries)
        putAll(CatechismFullData02.entries)
        putAll(CatechismFullData03.entries)
        putAll(CatechismFullData04.entries)
        putAll(CatechismFullData05.entries)
        putAll(CatechismFullData06.entries)
        putAll(CatechismFullData07.entries)
        putAll(CatechismFullData08.entries)
        putAll(CatechismFullData09.entries)
        putAll(CatechismFullData10.entries)
        putAll(CatechismFullData11.entries)
        putAll(CatechismFullData12.entries)
        putAll(CatechismFullData13.entries)
        putAll(CatechismFullData14.entries)
        putAll(CatechismFullData15.entries)
        putAll(CatechismFullData16.entries)
        putAll(CatechismFullData17.entries)
        putAll(CatechismFullData18.entries)
        putAll(CatechismFullData19.entries)
        putAll(CatechismFullData20.entries)
        putAll(CatechismFullData21.entries)
        putAll(CatechismFullData22.entries)
        putAll(CatechismFullData23.entries)
        putAll(CatechismFullData24.entries)
        putAll(CatechismFullData25.entries)
        putAll(CatechismFullData26.entries)
        putAll(CatechismFullData27.entries)
        putAll(CatechismFullData28.entries)
    }
}