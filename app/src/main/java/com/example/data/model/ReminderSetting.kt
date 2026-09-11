package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_settings")
data class ReminderSetting(
    @PrimaryKey
    val id: String,
    val title: String,
    val subtitle: String,
    val hour: Int,
    val minute: Int,
    val isEnabled: Boolean,
    val type: String // "ANGELUS", "CANONICAL_HOUR", "ROSARY"
)
