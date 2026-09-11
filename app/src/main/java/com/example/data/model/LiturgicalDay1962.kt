package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liturgy_1962")
data class LiturgicalDay1962(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateCode: String, // "MM-DD" or identifier like "09-11", "10-04", etc.
    val dayTitle: String,
    val latinDayTitle: String,
    val season: String, // Advento, Natal, Epifania, Septuagésima, Quaresma, Paixão, Tempo Pascal, Pentecostes, Tempo depois de Pentecostes
    val rankClass: String, // "Iª Classe", "IIª Classe", "IIIª Classe", "IVª Classe"
    val color: String, // "Branco", "Vermelho", "Verde", "Roxo", "Preto", "Rosa"
    val introitPt: String,
    val introitLat: String,
    val collectPt: String,
    val collectLat: String,
    val epistleRef: String,
    val epistlePt: String,
    val epistleLat: String,
    val gradualPt: String,
    val gradualLat: String,
    val gospelRef: String,
    val gospelPt: String,
    val gospelLat: String,
    val offertoryPt: String,
    val offertoryLat: String,
    val secretPt: String,
    val secretLat: String,
    val communionPt: String,
    val communionLat: String,
    val postCommunionPt: String,
    val postCommunionLat: String,
    val rubrics: String = ""
)
