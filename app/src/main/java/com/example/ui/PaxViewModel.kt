package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.RosaryState
import com.example.audio.RosaryType
import com.example.audio.SacredAudioEngine
import com.example.audio.SoundscapeMode
import com.example.audio.VoiceStyle
import com.example.data.FreiGalvaoData
import com.example.data.PaxDatabase
import com.example.data.PaxRepository
import com.example.data.model.AudioPackage
import com.example.data.model.FranciscanSaint
import com.example.data.model.FreiGalvaoIntention
import com.example.data.model.FreiGalvaoMiracle
import com.example.data.model.FreiGalvaoNovenaDay
import com.example.data.model.FreiGalvaoPrayer
import com.example.data.model.LiturgicalDay1962
import com.example.data.model.ReminderSetting
import com.example.data.model.SeraphicPrayer
import com.example.notifications.PaxNotificationHelper
import com.example.ui.theme.FontSizeScale
import com.example.ui.theme.ReadingThemeMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

enum class PaxTab(val label: String) {
    FREI_GALVAO("São Frei Galvão"),
    LITURGY_1962("Liturgia 1962"),
    ROSARY_AUDIO("Terços & Áudio"),
    SETTINGS("Lembretes & Ajustes");

    companion object {
        val FRANCISCAN: PaxTab get() = FREI_GALVAO
    }
}

enum class BilingualViewMode(val label: String) {
    SIDE_BY_SIDE("Lado a Lado"),
    INTERLINEAR("Interlinear"),
    LATIN_ONLY("Apenas Latim"),
    PORTUGUESE_ONLY("Apenas Português")
}

data class PaxUiState(
    val currentTab: PaxTab = PaxTab.FREI_GALVAO,
    val readingTheme: ReadingThemeMode = ReadingThemeMode.PARCHMENT,
    val fontSizeScale: FontSizeScale = FontSizeScale.MEDIUM,
    val bilingualMode: BilingualViewMode = BilingualViewMode.INTERLINEAR,
    val highContrast: Boolean = false,
    // Frei Galvão Section ("Rezando com São Frei Galvão")
    val freiGalvaoSubTab: Int = 0, // 0 = Novena, 1 = As Pílulas, 2 = Devocionário, 3 = Vida & Milagres, 4 = Preces & Intenções
    val selectedNovenaDayNumber: Int = 1,
    val selectedFreiGalvaoPrayer: FreiGalvaoPrayer? = null,
    val selectedFreiGalvaoMiracle: FreiGalvaoMiracle? = null,
    val completedNovenaDays: Set<Int> = emptySet(),
    val devoteeIntentions: List<FreiGalvaoIntention> = emptyList(),
    // Franciscan Section
    val franciscanSubTab: Int = 0,
    val selectedOrderFilter: String = "Todas",
    val selectedMonth: Int = 10,
    val selectedDay: Int = 4,
    val selectedSaint: FranciscanSaint? = null,
    val selectedPrayer: SeraphicPrayer? = null,
    // Liturgy 1962 Section
    val selectedDateCode: String = "10-04",
    val liturgyClassFilter: String = "Todas",
    // Info snackbar message
    val userNotice: String? = null
)

class PaxViewModel(application: Application) : AndroidViewModel(application) {

    private val database = PaxDatabase.getDatabase(application, viewModelScope)
    private val repository = PaxRepository(database.paxDao())
    val audioEngine = SacredAudioEngine(application, viewModelScope)

    private val _uiState = MutableStateFlow(PaxUiState())
    val uiState: StateFlow<PaxUiState> = _uiState.asStateFlow()

    val rosaryState: StateFlow<RosaryState> = audioEngine.state

    val saints: StateFlow<List<FranciscanSaint>> = repository.allSaints
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val prayers: StateFlow<List<SeraphicPrayer>> = repository.allPrayers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val liturgicalDays: StateFlow<List<LiturgicalDay1962>> = repository.allLiturgicalDays
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val reminders: StateFlow<List<ReminderSetting>> = repository.allReminders
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val audioPackages: StateFlow<List<AudioPackage>> = repository.allAudioPackages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.ensureDataSeeded()
            setTodayDate()
            loadFreiGalvaoState()
        }
    }

    private fun loadFreiGalvaoState() {
        val app = getApplication<Application>().applicationContext
        val completed = FreiGalvaoData.getCompletedNovenaDays(app)
        val intentions = FreiGalvaoData.loadIntentions(app)
        _uiState.value = _uiState.value.copy(
            completedNovenaDays = completed,
            devoteeIntentions = intentions
        )
    }

    private fun setTodayDate() {
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val code = String.format("%02d-%02d", month, day)
        _uiState.value = _uiState.value.copy(
            selectedMonth = month,
            selectedDay = day,
            selectedDateCode = code
        )
    }

    fun selectTab(tab: PaxTab) {
        _uiState.value = _uiState.value.copy(currentTab = tab)
    }

    // São Frei Galvão methods
    fun setFreiGalvaoSubTab(index: Int) {
        _uiState.value = _uiState.value.copy(freiGalvaoSubTab = index)
    }

    fun selectNovenaDay(day: Int) {
        _uiState.value = _uiState.value.copy(selectedNovenaDayNumber = day)
    }

    fun toggleNovenaDay(day: Int) {
        val app = getApplication<Application>().applicationContext
        val updated = FreiGalvaoData.toggleNovenaDayCompleted(app, day)
        _uiState.value = _uiState.value.copy(completedNovenaDays = updated)
        if (updated.contains(day)) {
            showNotice("Dia $day da Novena marcado como rezado com as bênçãos de Frei Galvão!")
        }
    }

    fun resetNovena() {
        val app = getApplication<Application>().applicationContext
        FreiGalvaoData.resetNovena(app)
        _uiState.value = _uiState.value.copy(completedNovenaDays = emptySet())
        showNotice("Novena de Frei Galvão reiniciada com devoção renovada!")
    }

    fun selectFreiGalvaoPrayer(prayer: FreiGalvaoPrayer?) {
        _uiState.value = _uiState.value.copy(selectedFreiGalvaoPrayer = prayer)
    }

    fun selectFreiGalvaoMiracle(miracle: FreiGalvaoMiracle?) {
        _uiState.value = _uiState.value.copy(selectedFreiGalvaoMiracle = miracle)
    }

    fun addDevoteeIntention(name: String, type: String, description: String) {
        val app = getApplication<Application>().applicationContext
        val updated = FreiGalvaoData.addIntention(app, name, type, description)
        _uiState.value = _uiState.value.copy(devoteeIntentions = updated)
        showNotice("Prece confiada à intercessão de São Frei Galvão!")
    }

    fun prayForIntention(id: String) {
        val app = getApplication<Application>().applicationContext
        val updated = FreiGalvaoData.incrementPrayerCount(app, id)
        _uiState.value = _uiState.value.copy(devoteeIntentions = updated)
        showNotice("Oração oferecida por esta intenção com a antífona de Frei Galvão!")
    }

    fun toggleIntentionGrace(id: String) {
        val app = getApplication<Application>().applicationContext
        val updated = FreiGalvaoData.toggleGraceReceived(app, id)
        _uiState.value = _uiState.value.copy(devoteeIntentions = updated)
        showNotice("Graça testemunhada em honra a São Frei Galvão! Louvado seja Deus!")
    }

    fun deleteIntention(id: String) {
        val app = getApplication<Application>().applicationContext
        val updated = FreiGalvaoData.deleteIntention(app, id)
        _uiState.value = _uiState.value.copy(devoteeIntentions = updated)
        showNotice("Prece removida.")
    }

    fun setFranciscanSubTab(index: Int) {
        _uiState.value = _uiState.value.copy(franciscanSubTab = index)
    }

    fun setOrderFilter(filter: String) {
        _uiState.value = _uiState.value.copy(selectedOrderFilter = filter)
    }

    fun selectSaintDate(month: Int, day: Int) {
        _uiState.value = _uiState.value.copy(
            selectedMonth = month,
            selectedDay = day
        )
    }

    fun selectSaint(saint: FranciscanSaint?) {
        _uiState.value = _uiState.value.copy(selectedSaint = saint)
    }

    fun selectPrayer(prayer: SeraphicPrayer?) {
        _uiState.value = _uiState.value.copy(selectedPrayer = prayer)
    }

    fun togglePrayerFavorite(prayer: SeraphicPrayer) {
        viewModelScope.launch {
            repository.togglePrayerFavorite(prayer)
        }
    }

    fun selectDateCode(dateCode: String) {
        _uiState.value = _uiState.value.copy(selectedDateCode = dateCode)
    }

    fun setReadingTheme(mode: ReadingThemeMode) {
        _uiState.value = _uiState.value.copy(readingTheme = mode)
    }

    fun setFontSizeScale(scale: FontSizeScale) {
        _uiState.value = _uiState.value.copy(fontSizeScale = scale)
    }

    fun setBilingualMode(mode: BilingualViewMode) {
        _uiState.value = _uiState.value.copy(bilingualMode = mode)
    }

    fun toggleHighContrast() {
        _uiState.value = _uiState.value.copy(highContrast = !_uiState.value.highContrast)
    }

    // Audio Rosary Controls
    fun setRosaryType(type: RosaryType) {
        audioEngine.loadRosary(type)
    }

    fun togglePlayPauseRosary() {
        audioEngine.togglePlayPause()
    }

    fun nextRosaryBead() {
        audioEngine.advanceToNextStep(autoPlay = false)
    }

    fun prevRosaryBead() {
        audioEngine.previousStep()
    }

    fun jumpToRosaryBead(index: Int) {
        audioEngine.jumpToStep(index)
    }

    fun setVoiceStyle(voiceStyle: VoiceStyle) {
        audioEngine.setVoiceStyle(voiceStyle)
    }

    fun setSoundscapeMode(mode: SoundscapeMode) {
        audioEngine.setSoundscape(mode)
    }

    fun setSoundscapeVolume(volume: Float) {
        audioEngine.setSoundscapeVolume(volume)
    }

    fun setUseLatinAudio(useLatin: Boolean) {
        audioEngine.setLatinAudio(useLatin)
    }

    // Reminders
    fun toggleReminder(reminder: ReminderSetting) {
        viewModelScope.launch {
            repository.toggleReminder(reminder)
            if (!reminder.isEnabled) {
                PaxNotificationHelper.scheduleReminder(
                    getApplication(),
                    reminder.id,
                    reminder.hour,
                    reminder.minute,
                    reminder.title,
                    reminder.subtitle
                )
                showNotice("Lembrete ativado para às ${String.format("%02d:%02d", reminder.hour, reminder.minute)}")
            } else {
                PaxNotificationHelper.cancelReminder(getApplication(), reminder.id)
                showNotice("Lembrete desativado: ${reminder.title}")
            }
        }
    }

    fun updateReminderTime(reminder: ReminderSetting, hour: Int, minute: Int) {
        viewModelScope.launch {
            repository.updateReminderTime(reminder, hour, minute)
            if (reminder.isEnabled) {
                PaxNotificationHelper.scheduleReminder(
                    getApplication(),
                    reminder.id,
                    hour,
                    minute,
                    reminder.title,
                    reminder.subtitle
                )
            }
            showNotice("Horário de ${reminder.title} ajustado para ${String.format("%02d:%02d", hour, minute)}")
        }
    }

    fun testReminderNotification(reminder: ReminderSetting) {
        PaxNotificationHelper.sendNotification(
            getApplication(),
            reminder.id.hashCode(),
            "Pax et Bonum • ${reminder.title}",
            reminder.subtitle
        )
        showNotice("Notificação de teste emitida para ${reminder.title}!")
    }

    // Audio Package Download Management
    fun toggleDownloadPackage(pkg: AudioPackage) {
        viewModelScope.launch {
            if (pkg.isDownloaded) {
                // Delete / uninstall package to free storage
                val updated = pkg.copy(isDownloaded = false, downloadProgress = 0f, isDownloading = false)
                repository.updateAudioPackage(updated)
                showNotice("Pacote '${pkg.title}' removido do armazenamento local.")
            } else {
                // Simulate downloading on-demand package
                repository.updateAudioPackage(pkg.copy(isDownloading = true, downloadProgress = 0.2f))
                delay(600)
                repository.updateAudioPackage(pkg.copy(isDownloading = true, downloadProgress = 0.65f))
                delay(600)
                repository.updateAudioPackage(pkg.copy(isDownloading = false, downloadProgress = 1.0f, isDownloaded = true))
                showNotice("Pacote '${pkg.title}' (${pkg.sizeMb} MB) baixado com sucesso para uso offline!")
            }
        }
    }

    fun showNotice(msg: String) {
        _uiState.value = _uiState.value.copy(userNotice = msg)
    }

    fun clearNotice() {
        _uiState.value = _uiState.value.copy(userNotice = null)
    }

    override fun onCleared() {
        super.onCleared()
        audioEngine.release()
    }
}
