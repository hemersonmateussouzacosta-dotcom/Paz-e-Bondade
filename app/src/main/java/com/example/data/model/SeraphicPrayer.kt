package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seraphic_prayers")
data class SeraphicPrayer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val latinTitle: String = "",
    val category: String, // "Paz", "Louvor e Bênção", "Intercessão", "Trânsito", "Ladainha", "Maria Santíssima"
    val portugueseText: String,
    val latinText: String = "",
    val attribution: String,
    val notes: String = "",
    val isFavorite: Boolean = false
)
