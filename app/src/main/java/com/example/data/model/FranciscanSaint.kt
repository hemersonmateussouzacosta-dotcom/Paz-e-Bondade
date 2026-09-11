package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "franciscan_saints")
data class FranciscanSaint(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val feastMonth: Int, // 1 to 12
    val feastDay: Int,   // 1 to 31
    val orderType: String, // "OFM (1ª Ordem)", "OFM Cap (Capuchinho)", "OFM Conv (Conventual)", "Clarissa (2ª Ordem)", "OFS (3ª Ordem Secular)"
    val years: String,
    val title: String,
    val biography: String,
    val quote: String,
    val patronage: String,
    val isFavorite: Boolean = false
)
