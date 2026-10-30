package com.example.data.repository

import com.example.data.models.LocalizedText

/**
 * One contiguous stretch of the Catechism, as the book itself divides it.
 */
data class CatechismOutlineSection(
    val partTitle: LocalizedText,
    val title: LocalizedText,
    val firstNumber: Int,
    val lastNumber: Int,
    val biblicalReferences: String
) {
    val numbers: IntRange get() = firstNumber..lastNumber
    val paragraphCount: Int get() = lastNumber - firstNumber + 1
}

/**
 * A browsable index over the whole Catechism.
 *
 * All 2,865 paragraphs are bundled in `CatechismFullData*`, but the hand-written
 * tree in [CatechismData.catechismParts] names only twenty-six of them, so
 * browsing reached a fraction of the book and the rest could be found only by
 * typing a number. These sections tile 1..2865 without gap or overlap, so a
 * screen can walk the entire Catechism from the index alone.
 */
object CatechismOutline {

    val sections: List<CatechismOutlineSection> = listOf(
        s("Prólogo", "Prologue", "Prólogo",
          "La vida del hombre: conocer y amar a Dios",
          "Man's life: to know and love God",
          "A vida do homem: conhecer e amar a Deus",
          1, 25, "Jn 17, 3; 1 Tim 2, 4"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "El hombre es «capaz» de Dios", "Man's capacity for God", "O homem é «capaz» de Deus",
          26, 49, "Hch 17, 26; Rom 1, 19"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "Dios al encuentro del hombre (Revelación)",
          "God comes to meet man (Revelation)",
          "Deus ao encontro do homem (Revelação)",
          50, 141, "Hb 1, 1-2; Mt 11, 27"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "La respuesta del hombre a Dios: la fe",
          "Man's response to God: faith",
          "A resposta do homem a Deus: a fé",
          142, 184, "Rom 1, 5; Hb 11, 1"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "Creo en Dios Padre Todopoderoso",
          "I believe in God the Father Almighty",
          "Creio em Deus Pai Todo-Poderoso",
          185, 421, "Gn 1, 1; Is 45, 5"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "Creo en Jesucristo, Hijo único de Dios",
          "I believe in Jesus Christ, the only Son of God",
          "Creio em Jesus Cristo, Filho único de Deus",
          422, 682, "Flp 2, 5-11; 1 Co 15, 3-4"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "Creo en el Espíritu Santo", "I believe in the Holy Spirit", "Creio no Espírito Santo",
          683, 747, "Jn 14, 26; Rm 8, 14"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "Creo en la Santa Iglesia Católica",
          "I believe in the Holy Catholic Church",
          "Creio na Santa Igreja Católica",
          748, 975, "Ef 5, 25-27; Mt 16, 18"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "El perdón de los pecados y la resurrección",
          "The forgiveness of sins and the resurrection",
          "O perdão dos pecados e a ressurreição",
          976, 1019, "Jn 20, 22-23; 1 Co 15, 20"),
        s("Primera Parte", "Part One", "Primeira Parte",
          "Creo en la vida eterna. Amén", "I believe in life everlasting. Amen", "Creio na vida eterna. Ámen",
          1020, 1065, "Ap 21, 1-4; 1 Co 2, 9"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "La liturgia y el misterio pascual",
          "The liturgy and the paschal mystery",
          "A liturgia e o mistério pascal",
          1066, 1209, "2 Co 13, 13; Lc 24, 26"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento del Bautismo", "The sacrament of Baptism", "O sacramento do Baptismo",
          1210, 1284, "Mt 28, 19; Rm 6, 3-4"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento de la Confirmación", "The sacrament of Confirmation", "O sacramento da Confirmação",
          1285, 1321, "Hch 8, 14-17; Hb 6, 2"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento de la Eucaristía", "The sacrament of the Eucharist", "O sacramento da Eucaristia",
          1322, 1419, "1 Co 11, 23-26; Jn 6, 51"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento de la Penitencia y la Reconciliación",
          "The sacrament of Penance and Reconciliation",
          "O sacramento da Penitência e da Reconciliação",
          1420, 1498, "Jn 20, 21-23; 2 Co 5, 18"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento de la Unción de los enfermos",
          "The sacrament of the Anointing of the Sick",
          "O sacramento da Unção dos enfermos",
          1499, 1532, "St 5, 14-15; Mc 6, 13"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento del Orden Sagrado", "The sacrament of Holy Orders", "O sacramento da Ordem",
          1533, 1600, "1 Tm 4, 14; 2 Tm 1, 6"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "El sacramento del Matrimonio", "The sacrament of Matrimony", "O sacramento do Matrimónio",
          1601, 1666, "Ef 5, 31-32; Mt 19, 6"),
        s("Segunda Parte", "Part Two", "Segunda Parte",
          "Los sacramentales y las exequias cristianas",
          "Sacramentals and Christian funerals",
          "Os sacramentais e as exéquias cristãs",
          1667, 1690, "1 Tm 4, 5; Sal 23"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "La dignidad de la persona humana y las virtudes",
          "The dignity of the human person and the virtues",
          "A dignidade da pessoa humana e as virtudes",
          1691, 1876, "Mt 5, 48; Ga 5, 22"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "La comunidad humana y la justicia social",
          "The human community and social justice",
          "A comunidade humana e a justiça social",
          1877, 1948, "Lc 10, 29-37; 1 Co 12, 12"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "La salvación de Dios: ley y gracia",
          "God's salvation: law and grace",
          "A salvação de Deus: lei e graça",
          1949, 2051, "Rm 3, 24; Jn 1, 17"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Del primer al tercer mandamiento del Decálogo",
          "The first to the third commandment",
          "Do primeiro ao terceiro mandamento",
          2052, 2195, "Ex 20, 2-11; Dt 5, 6-15"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Cuarto mandamiento: honra a tu padre y a tu madre",
          "Fourth commandment: honour your father and mother",
          "Quarto mandamento: honra teu pai e tua mãe",
          2196, 2257, "Ex 20, 12; Ef 6, 1-3"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Quinto mandamiento: no matarás",
          "Fifth commandment: you shall not kill",
          "Quinto mandamento: não matarás",
          2258, 2330, "Ex 20, 13; Gn 9, 6"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Sexto mandamiento: pureza y santidad del amor",
          "Sixth commandment: the purity and holiness of love",
          "Sexto mandamento: pureza e santidade do amor",
          2331, 2400, "Ex 20, 14; 1 Co 6, 18"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Séptimo mandamiento: justicia y destino de los bienes",
          "Seventh commandment: justice and the destination of goods",
          "Sétimo mandamento: justiça e destino dos bens",
          2401, 2463, "Ex 20, 15; Lc 19, 8"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Octavo mandamiento: la verdad y el testimonio",
          "Eighth commandment: truth and witness",
          "Oitavo mandamento: a verdade e o testemunho",
          2464, 2513, "Ex 20, 16; Jn 8, 32"),
        s("Tercera Parte", "Part Three", "Terceira Parte",
          "Noveno y décimo mandamiento: pureza de corazón",
          "Ninth and tenth commandments: purity of heart",
          "Nono e décimo mandamentos: pureza de coração",
          2514, 2557, "Ex 20, 17; Mt 5, 8"),
        s("Cuarta Parte", "Part Four", "Quarta Parte",
          "La oración en la vida cristiana y los santos",
          "Prayer in the Christian life and the saints",
          "A oração na vida cristã e os santos",
          2558, 2758, "Lc 11, 1; 1 Ts 5, 17"),
        s("Cuarta Parte", "Part Four", "Quarta Parte",
          "El Padrenuestro: las siete peticiones",
          "The Our Father: the seven petitions",
          "O Pai-Nosso: as sete petições",
          2759, 2865, "Mt 6, 9-13; Lc 11, 2-4")
    )

    /** The four parts plus the Prologue, in the order the book gives them. */
    val parts: List<LocalizedText> = sections.map { it.partTitle }.distinctBy { it.es }

    fun sectionsOf(partTitle: LocalizedText): List<CatechismOutlineSection> =
        sections.filter { it.partTitle.es == partTitle.es }

    fun sectionFor(number: Int): CatechismOutlineSection? =
        sections.firstOrNull { number in it.numbers }

    /** Total paragraphs the index reaches; must be 2,865. */
    val totalParagraphs: Int get() = sections.sumOf { it.paragraphCount }

    private fun s(
        partEs: String, partEn: String, partPt: String,
        titleEs: String, titleEn: String, titlePt: String,
        first: Int, last: Int, refs: String
    ) = CatechismOutlineSection(
        partTitle = LocalizedText(partEs, partEn, partPt),
        title = LocalizedText(titleEs, titleEn, titlePt),
        firstNumber = first,
        lastNumber = last,
        biblicalReferences = refs
    )
}
