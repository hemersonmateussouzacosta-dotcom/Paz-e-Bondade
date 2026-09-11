package com.example.audio

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

enum class VoiceStyle(val label: String, val subtitle: String, val pitch: Float, val speed: Float) {
    MALE_FRIAR("Frade Franciscano", "Voz masculina solene e contemplativa", 0.82f, 0.88f),
    FEMALE_SISTER("Irmã Clarissa", "Voz feminina serena e melodiosa", 1.18f, 0.90f)
}

enum class RosaryType(val title: String, val subtitle: String, val decadesCount: Int) {
    FRANCISCAN_CROWN("Coroa Franciscana (7 Alegrias)", "Tradição Seráfica das Sete Alegrias de Nossa Senhora (72 Ave-Marias)", 7),
    MARIAN_JOYFUL("Terço - Mistérios Gozosos", "Segundas e Quintas (Anunciação ao Encontro no Templo)", 5),
    MARIAN_SORROWFUL("Terço - Mistérios Dolorosos", "Terças e Sextas (Agonia à Crucificação)", 5),
    MARIAN_GLORIOUS("Terço - Mistérios Gloriosos", "Quartas, Sábados e Domingos (Ressurreição à Coroação)", 5)
}

data class RosaryStep(
    val decadeIndex: Int, // 1-based
    val beadIndex: Int,   // 0 = Pai Nosso, 1..10 = Ave Maria, 11 = Glória / Jaculatória
    val title: String,
    val mysteryName: String,
    val meditation: String,
    val prayerLabel: String,
    val spokenTextPt: String,
    val spokenTextLat: String
)

data class RosaryState(
    val rosaryType: RosaryType = RosaryType.FRANCISCAN_CROWN,
    val currentStepIndex: Int = 0,
    val totalSteps: Int = 0,
    val currentStep: RosaryStep? = null,
    val isPlaying: Boolean = false,
    val voiceStyle: VoiceStyle = VoiceStyle.MALE_FRIAR,
    val soundscapeMode: SoundscapeMode = SoundscapeMode.GREGORIAN,
    val useLatinAudio: Boolean = false,
    val soundscapeVolume: Float = 0.45f
)

class SacredAudioEngine(
    private val context: Context,
    private val scope: CoroutineScope
) {
    private var tts: TextToSpeech? = null
    private var isTtsReady = false

    val soundscape = SacredSoundscapeSynthesizer(scope)

    private val _state = MutableStateFlow(RosaryState())
    val state: StateFlow<RosaryState> = _state.asStateFlow()

    private var stepsList: List<RosaryStep> = emptyList()

    init {
        initTts()
        loadRosary(RosaryType.FRANCISCAN_CROWN)
    }

    private fun initTts() {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isTtsReady = true
                applyVoiceSettings(_state.value.voiceStyle)
            } else {
                Log.w("SacredAudioEngine", "TTS initialization returned $status")
            }
        }

        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                _state.value = _state.value.copy(isPlaying = true)
            }

            override fun onDone(utteranceId: String?) {
                // Auto-advance to next bead if playback is active
                if (_state.value.isPlaying) {
                    advanceToNextStep(autoPlay = true)
                }
            }

            override fun onError(utteranceId: String?) {
                _state.value = _state.value.copy(isPlaying = false)
            }
        })
    }

    private fun applyVoiceSettings(voiceStyle: VoiceStyle) {
        tts?.let { engine ->
            val lang = if (_state.value.useLatinAudio) Locale("la") else Locale("pt", "BR")
            val res = engine.setLanguage(lang)
            if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                engine.language = Locale("pt", "BR")
            }
            engine.setPitch(voiceStyle.pitch)
            engine.setSpeechRate(voiceStyle.speed)
        }
    }

    fun setVoiceStyle(voiceStyle: VoiceStyle) {
        _state.value = _state.value.copy(voiceStyle = voiceStyle)
        applyVoiceSettings(voiceStyle)
    }

    fun setLatinAudio(useLatin: Boolean) {
        _state.value = _state.value.copy(useLatinAudio = useLatin)
        applyVoiceSettings(_state.value.voiceStyle)
    }

    fun setSoundscape(mode: SoundscapeMode) {
        _state.value = _state.value.copy(soundscapeMode = mode)
        soundscape.setMode(mode)
    }

    fun setSoundscapeVolume(vol: Float) {
        _state.value = _state.value.copy(soundscapeVolume = vol)
        soundscape.setVolume(vol)
    }

    fun loadRosary(type: RosaryType) {
        stop()
        stepsList = generateRosarySteps(type)
        _state.value = _state.value.copy(
            rosaryType = type,
            currentStepIndex = 0,
            totalSteps = stepsList.size,
            currentStep = stepsList.firstOrNull(),
            isPlaying = false
        )
    }

    fun togglePlayPause() {
        if (_state.value.isPlaying) {
            pause()
        } else {
            playCurrentStep()
        }
    }

    fun playCurrentStep() {
        val step = _state.value.currentStep ?: return
        _state.value = _state.value.copy(isPlaying = true)

        // Ensure ambient soundscape is active if set
        if (_state.value.soundscapeMode != SoundscapeMode.SILENCE) {
            soundscape.start()
        }

        val textToSpeak = if (_state.value.useLatinAudio && step.spokenTextLat.isNotBlank()) {
            step.spokenTextLat
        } else {
            step.spokenTextPt
        }

        val params = Bundle()
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "bead_${_state.value.currentStepIndex}")
        tts?.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, params, "bead_${_state.value.currentStepIndex}")
    }

    fun pause() {
        _state.value = _state.value.copy(isPlaying = false)
        tts?.stop()
    }

    fun stop() {
        _state.value = _state.value.copy(isPlaying = false)
        tts?.stop()
        soundscape.stop()
    }

    fun advanceToNextStep(autoPlay: Boolean = false) {
        val nextIdx = _state.value.currentStepIndex + 1
        if (nextIdx < stepsList.size) {
            _state.value = _state.value.copy(
                currentStepIndex = nextIdx,
                currentStep = stepsList[nextIdx]
            )
            if (autoPlay && _state.value.isPlaying) {
                playCurrentStep()
            }
        } else {
            // Completed
            stop()
        }
    }

    fun previousStep() {
        val prevIdx = (_state.value.currentStepIndex - 1).coerceAtLeast(0)
        _state.value = _state.value.copy(
            currentStepIndex = prevIdx,
            currentStep = stepsList[prevIdx]
        )
        if (_state.value.isPlaying) {
            playCurrentStep()
        }
    }

    fun jumpToStep(index: Int) {
        if (index in stepsList.indices) {
            _state.value = _state.value.copy(
                currentStepIndex = index,
                currentStep = stepsList[index]
            )
            if (_state.value.isPlaying) {
                playCurrentStep()
            }
        }
    }

    private fun generateRosarySteps(type: RosaryType): List<RosaryStep> {
        val steps = mutableListOf<RosaryStep>()

        // Opening step
        steps.add(
            RosaryStep(
                decadeIndex = 0,
                beadIndex = 0,
                title = "Início do Rosário",
                mysteryName = "Sinal da Santa Cruz e Oferecimento",
                meditation = "Pelo sinal da Santa Cruz, livrai-nos, Deus Nosso Senhor, dos nossos inimigos. Em nome do Pai, e do Filho, e do Espírito Santo. Creio em Deus Pai Todo-Poderoso...",
                prayerLabel = "Credo Seráfico",
                spokenTextPt = "Em nome do Pai, e do Filho, e do Espírito Santo. Amém. Creio em Deus Pai Todo-Poderoso, Criador do céu e da terra...",
                spokenTextLat = "In nomine Patris, et Filii, et Spiritus Sancti. Amen. Credo in Deum Patrem omnipotentem, Creatorem caeli et terrae..."
            )
        )

        val mysteries = when (type) {
            RosaryType.FRANCISCAN_CROWN -> listOf(
                "1ª Alegria" to ("A Anunciação do Arcanjo São Gabriel a Nossa Senhora" to "Contemplemos a alegria da Virgem ao acolher a Palavra de Deus em seu seio."),
                "2ª Alegria" to ("A Visitação de Maria a sua prima Santa Isabel" to "Contemplemos a caridade solícita da Virgem partindo apressadamente para servir."),
                "3ª Alegria" to ("O Nascimento de Jesus Menino em Belém" to "Contemplemos a alegria inefável da Virgem ao contemplar e adorar o Menino Deus."),
                "4ª Alegria" to ("A Adoração dos Três Santos Reis Magos" to "Contemplemos a revelação de Cristo aos povos pelas mãos de Maria."),
                "5ª Alegria" to ("O Encontro do Menino Jesus no Templo" to "Contemplemos o alívio e júbilo da Virgem ao reencontrar o Filho na casa do Pai."),
                "6ª Alegria" to ("A Ressurreição Gloriosa de Nosso Senhor" to "Contemplemos o reencontro triunfante de Cristo ressuscitado com sua Santa Mãe."),
                "7ª Alegria" to ("A Assunção e Coroação de Maria no Céu" to "Contemplemos a entrada da Virgem na glória e sua coroação como Rainha do Céu e da Ordem Seráfica.")
            )

            RosaryType.MARIAN_JOYFUL -> listOf(
                "1º Mistério" to ("A Anunciação do Anjo a Maria" to "O Anjo Gabriel saúda Maria: Ave cheia de graça!"),
                "2º Mistério" to ("A Visitação de Nossa Senhora a Santa Isabel" to "A criancinha estremece de alegria no seio de Isabel."),
                "3º Mistério" to ("O Nascimento do Menino Jesus em Belém" to "Nasce o Salvador do mundo na pobreza do presépio."),
                "4º Mistério" to ("A Apresentação de Jesus no Templo" to "Maria e José oferecem o Menino ao Senhor no templo de Jerusalém."),
                "5º Mistério" to ("A Perda e o Encontro de Jesus no Templo" to "Jesus é encontrado entre os doutores escutando-os e interrogando-os.")
            )

            RosaryType.MARIAN_SORROWFUL -> listOf(
                "1º Mistério" to ("A Agonia de Jesus no Horto das Oliveiras" to "Jesus reza no Getsêmani: Pai, faça-se a tua vontade."),
                "2º Mistério" to ("A Flagelação de Nosso Senhor" to "Jesus é cruelmente atado à coluna e açoitado pelos nossos pecados."),
                "3º Mistério" to ("A Coroação de Espinhos" to "Os soldados cravam na fronte de Jesus uma coroa de espinhos pontiagudos."),
                "4º Mistério" to ("Jesus carrega a Pesada Cruz ao Calvário" to "O Salvador sobe a via dolorosa e encontra sua Mãe dolorosa."),
                "5º Mistério" to ("A Crucificação e Morte de Nosso Senhor" to "Jesus entrega sua vida pela redenção da humanidade: Pai, em tuas mãos entrego o meu espírito.")
            )

            RosaryType.MARIAN_GLORIOUS -> listOf(
                "1º Mistério" to ("A Ressurreição Triunfante de Jesus" to "Ao terceiro dia, Cristo vence a morte e surge glorioso do túmulo."),
                "2º Mistério" to ("A Ascensão de Jesus aos Céus" to "Jesus sobe aos céus à vista dos seus apóstolos e de Maria."),
                "3º Mistério" to ("A Descida do Espírito Santo em Pentecostes" to "O Espírito Santo desce em línguas de fogo sobre os Apóstolos e Maria reunidos no Cenáculo."),
                "4º Mistério" to ("A Gloriosa Assunção de Nossa Senhora" to "Maria Santíssima é elevada em corpo e alma à glória do Paraíso."),
                "5º Mistério" to ("A Coroação de Maria como Rainha dos Céus e da Terra" to "A Trindade Santíssima coroa a Virgem Maria acima de todos os coros celestes.")
            )
        }

        mysteries.forEachIndexed { idx, pair ->
            val decNum = idx + 1
            val mysteryTitle = pair.first
            val (name, med) = pair.second

            // Pai Nosso
            steps.add(
                RosaryStep(
                    decadeIndex = decNum,
                    beadIndex = 0,
                    title = "$mysteryTitle: $name",
                    mysteryName = name,
                    meditation = med,
                    prayerLabel = "Pai Nosso",
                    spokenTextPt = "$mysteryTitle: $name. $med. Pai Nosso que estais nos céus, santificado seja o vosso nome, venha a nós o vosso reino, seja feita a vossa vontade assim na terra como no céu...",
                    spokenTextLat = "Pater noster, qui es in caelis: sanctificetur nomen tuum: adveniat regnum tuum: fiat voluntas tua, sicut in caelo, et in terra..."
                )
            )

            // 10 Ave Marias
            for (bead in 1..10) {
                steps.add(
                    RosaryStep(
                        decadeIndex = decNum,
                        beadIndex = bead,
                        title = "$mysteryTitle • Conta $bead / 10",
                        mysteryName = name,
                        meditation = med,
                        prayerLabel = "Ave Maria ($bead/10)",
                        spokenTextPt = "Ave Maria, cheia de graça, o Senhor é convosco, bendita sois vós entre as mulheres e bendito é o fruto do vosso ventre, Jesus. Santa Maria, Mãe de Deus, rogai por nós pecadores, agora e na hora de nossa morte. Amém.",
                        spokenTextLat = "Ave Maria, gratia plena, Dominus tecum; benedicta tu in mulieribus, et benedictus fructus ventris tui, Iesus. Sancta Maria, Mater Dei, ora pro nobis peccatoribus, nunc et in hora mortis nostrae. Amen."
                    )
                )
            }

            // Glória e Jaculatória Seráfica
            steps.add(
                RosaryStep(
                    decadeIndex = decNum,
                    beadIndex = 11,
                    title = "$mysteryTitle • Doxologia",
                    mysteryName = name,
                    meditation = "Glória ao Pai e Jaculatória Seráfica Franciscana",
                    prayerLabel = "Glória & Jaculatória",
                    spokenTextPt = "Glória ao Pai, e ao Filho, e ao Espírito Santo. Como era no princípio, agora e sempre. Amém. Meu Deus e meu Tudo! Santo Pai Francisco, rogai por nós!",
                    spokenTextLat = "Gloria Patri, et Filio, et Spiritui Sancto. Sicut erat in principio, et nunc, et semper, et in saecula saeculorum. Amen. Deus meus et omnia! Sancte Francisce, ora pro nobis!"
                )
            )
        }

        // Closing Salve Regina
        steps.add(
            RosaryStep(
                decadeIndex = type.decadesCount + 1,
                beadIndex = 0,
                title = "Encerramento e Salve Rainha",
                mysteryName = "Salve Regina Seraphica",
                meditation = "Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve!",
                prayerLabel = "Salve Rainha",
                spokenTextPt = "Salve, Rainha, Mãe de misericórdia, vida, doçura e esperança nossa, salve! A vós bradamos, os degredados filhos de Eva. A vós suspiramos, gemendo e chorando neste vale de lágrimas...",
                spokenTextLat = "Salve, Regina, Mater misericordiae, vita, dulcedo, et spes nostra, salve! Ad te clamamus, exsules filii Evae..."
            )
        )

        return steps
    }

    fun release() {
        stop()
        tts?.shutdown()
        tts = null
        soundscape.release()
    }
}
