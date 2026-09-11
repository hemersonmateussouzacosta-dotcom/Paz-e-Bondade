package com.example.data

import com.example.data.dao.PaxDao
import com.example.data.model.AudioPackage
import com.example.data.model.FranciscanSaint
import com.example.data.model.LiturgicalDay1962
import com.example.data.model.ReminderSetting
import com.example.data.model.SeraphicPrayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class PaxRepository(private val paxDao: PaxDao) {

    val allSaints: Flow<List<FranciscanSaint>> = paxDao.getAllSaints()
    val allPrayers: Flow<List<SeraphicPrayer>> = paxDao.getAllPrayers()
    val favoritePrayers: Flow<List<SeraphicPrayer>> = paxDao.getFavoritePrayers()
    val allLiturgicalDays: Flow<List<LiturgicalDay1962>> = paxDao.getAllLiturgicalDays()
    val allReminders: Flow<List<ReminderSetting>> = paxDao.getAllReminders()
    val allAudioPackages: Flow<List<AudioPackage>> = paxDao.getAllAudioPackages()

    fun getSaintForDate(month: Int, day: Int): Flow<FranciscanSaint?> {
        return paxDao.getSaintForDate(month, day)
    }

    fun getPrayersByCategory(category: String): Flow<List<SeraphicPrayer>> {
        return paxDao.getPrayersByCategory(category)
    }

    fun getLiturgicalDayByCode(dateCode: String): Flow<LiturgicalDay1962?> {
        return paxDao.getLiturgicalDayByCode(dateCode)
    }

    suspend fun togglePrayerFavorite(prayer: SeraphicPrayer) {
        paxDao.updatePrayer(prayer.copy(isFavorite = !prayer.isFavorite))
    }

    suspend fun toggleReminder(reminder: ReminderSetting) {
        paxDao.updateReminder(reminder.copy(isEnabled = !reminder.isEnabled))
    }

    suspend fun updateReminderTime(reminder: ReminderSetting, hour: Int, minute: Int) {
        paxDao.updateReminder(reminder.copy(hour = hour, minute = minute))
    }

    suspend fun updateAudioPackage(audioPackage: AudioPackage) {
        paxDao.updateAudioPackage(audioPackage)
    }

    suspend fun ensureDataSeeded() {
        val currentSaints = allSaints.firstOrNull()
        if (currentSaints.isNullOrEmpty()) {
            paxDao.insertSaints(PaxInitialData.getInitialSaints())
            paxDao.insertPrayers(PaxInitialData.getInitialPrayers())
            paxDao.insertLiturgicalDays(PaxInitialData.getInitialLiturgicalDays())
            paxDao.insertReminders(PaxInitialData.getInitialReminders())
            paxDao.insertAudioPackages(PaxInitialData.getInitialAudioPackages())
        }
    }
}
