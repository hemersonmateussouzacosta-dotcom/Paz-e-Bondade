package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.AudioPackage
import com.example.data.model.FranciscanSaint
import com.example.data.model.LiturgicalDay1962
import com.example.data.model.ReminderSetting
import com.example.data.model.SeraphicPrayer
import kotlinx.coroutines.flow.Flow

@Dao
interface PaxDao {
    // Franciscan Saints
    @Query("SELECT * FROM franciscan_saints ORDER BY feastMonth ASC, feastDay ASC")
    fun getAllSaints(): Flow<List<FranciscanSaint>>

    @Query("SELECT * FROM franciscan_saints WHERE feastMonth = :month AND feastDay = :day LIMIT 1")
    fun getSaintForDate(month: Int, day: Int): Flow<FranciscanSaint?>

    @Query("SELECT * FROM franciscan_saints WHERE id = :id")
    suspend fun getSaintById(id: Int): FranciscanSaint?

    @Query("SELECT * FROM franciscan_saints WHERE orderType LIKE '%' || :orderType || '%'")
    fun getSaintsByOrder(orderType: String): Flow<List<FranciscanSaint>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaints(saints: List<FranciscanSaint>)

    @Update
    suspend fun updateSaint(saint: FranciscanSaint)

    // Seraphic Prayers
    @Query("SELECT * FROM seraphic_prayers ORDER BY id ASC")
    fun getAllPrayers(): Flow<List<SeraphicPrayer>>

    @Query("SELECT * FROM seraphic_prayers WHERE category = :category ORDER BY id ASC")
    fun getPrayersByCategory(category: String): Flow<List<SeraphicPrayer>>

    @Query("SELECT * FROM seraphic_prayers WHERE isFavorite = 1")
    fun getFavoritePrayers(): Flow<List<SeraphicPrayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayers(prayers: List<SeraphicPrayer>)

    @Update
    suspend fun updatePrayer(prayer: SeraphicPrayer)

    // Liturgy 1962
    @Query("SELECT * FROM liturgy_1962 ORDER BY dateCode ASC")
    fun getAllLiturgicalDays(): Flow<List<LiturgicalDay1962>>

    @Query("SELECT * FROM liturgy_1962 WHERE dateCode = :dateCode LIMIT 1")
    fun getLiturgicalDayByCode(dateCode: String): Flow<LiturgicalDay1962?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLiturgicalDays(days: List<LiturgicalDay1962>)

    // Reminders
    @Query("SELECT * FROM reminder_settings ORDER BY hour ASC, minute ASC")
    fun getAllReminders(): Flow<List<ReminderSetting>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminders(reminders: List<ReminderSetting>)

    @Update
    suspend fun updateReminder(reminder: ReminderSetting)

    // Audio Packages
    @Query("SELECT * FROM audio_packages ORDER BY id ASC")
    fun getAllAudioPackages(): Flow<List<AudioPackage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudioPackages(packages: List<AudioPackage>)

    @Update
    suspend fun updateAudioPackage(audioPackage: AudioPackage)
}
