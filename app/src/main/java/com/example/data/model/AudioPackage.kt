package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio_packages")
data class AudioPackage(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val sizeMb: Int,
    val isDownloaded: Boolean = false,
    val downloadProgress: Float = 0f,
    val isDownloading: Boolean = false
)
