package com.example.data.model

data class FreiGalvaoNovenaDay(
    val day: Int,
    val title: String,
    val virtue: String,
    val scriptureOrThought: String,
    val meditation: String,
    val prayer: String,
    val isPillDay: Boolean = false, // Day 1, 5, 9
    val pillInstruction: String = ""
)

data class FreiGalvaoPrayer(
    val id: String,
    val title: String,
    val category: String, // "Oficial", "Gestantes e Mães", "Enfermos", "Consagração", "Ladainha"
    val intentionSubtitle: String,
    val portugueseText: String,
    val latinAntiphon: String = "Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis.",
    val historicalContext: String = "",
    val isFavorite: Boolean = false
)

data class FreiGalvaoMiracle(
    val id: String,
    val title: String,
    val subtitle: String,
    val yearAndPlace: String,
    val summary: String,
    val fullStory: String,
    val canonicalSignificance: String
)

data class FreiGalvaoIntention(
    val id: String,
    val devoteeName: String,
    val intentionType: String, // "Gestação e Parto", "Saúde e Cura", "Cálculos Renais", "Família", "Causa Urgente", "Ação de Graças"
    val description: String,
    val dateCreated: String,
    val prayersCount: Int = 1,
    val isGraceReceived: Boolean = false
)
