package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

enum class SoundscapeMode(val label: String, val description: String) {
    SILENCE("Silêncio", "Apenas oração e recolhimento"),
    GREGORIAN("Canto Gregoriano", "Harmonias sacras modais contemplativas"),
    ORGAN("Órgão Sacro", "Acordes litúrgicos de tubos de catedral"),
    MONASTERY("Mosteiro & Sinos", "Ambiente de claustro e badaladas serenas")
}

class SacredSoundscapeSynthesizer(private val coroutineScope: CoroutineScope) {

    private val sampleRate = 22050
    private var audioTrack: AudioTrack? = null
    private var synthesisJob: Job? = null
    private var currentMode: SoundscapeMode = SoundscapeMode.SILENCE
    private var volume: Float = 0.5f

    fun setMode(mode: SoundscapeMode) {
        if (currentMode == mode) return
        currentMode = mode
        stop()
        if (mode != SoundscapeMode.SILENCE) {
            start()
        }
    }

    fun setVolume(newVolume: Float) {
        volume = newVolume.coerceIn(0f, 1f)
        audioTrack?.setVolume(volume)
    }

    fun getCurrentMode(): SoundscapeMode = currentMode
    fun getVolume(): Float = volume

    fun start() {
        if (currentMode == SoundscapeMode.SILENCE) return
        if (synthesisJob?.isActive == true) return

        val minBufferSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        val bufferSize = (minBufferSize * 2).coerceAtLeast(sampleRate / 4)

        try {
            audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(bufferSize * 2)
                .setTransferMode(AudioTrack.MODE_STREAM)
                .build()

            audioTrack?.setVolume(volume)
            audioTrack?.play()

            synthesisJob = coroutineScope.launch(Dispatchers.Default) {
                runSynthesisLoop(bufferSize)
            }
        } catch (e: Exception) {
            Log.e("SacredSoundscape", "Failed to start audio synthesizer", e)
        }
    }

    private fun runSynthesisLoop(chunkSize: Int) {
        val shortBuffer = ShortArray(chunkSize)
        var sampleIndex = 0L

        // Base frequencies for Gregorian mode (D Dorian chord: D3, A3, D4, F4)
        val fD3 = 146.83
        val fA3 = 220.00
        val fD4 = 293.66
        val fF4 = 349.23

        // Organ chord progression phases
        val organChords = listOf(
            doubleArrayOf(130.81, 164.81, 196.00, 261.63), // C Major
            doubleArrayOf(110.00, 146.83, 174.61, 220.00), // D minor / F
            doubleArrayOf(146.83, 174.61, 220.00, 293.66), // G / D
            doubleArrayOf(130.81, 164.81, 196.00, 261.63)  // C
        )

        var bellTriggerTimer = 0

        while (synthesisJob?.isActive == true) {
            for (i in 0 until chunkSize) {
                val t = sampleIndex.toDouble() / sampleRate
                var sample = 0.0

                when (currentMode) {
                    SoundscapeMode.GREGORIAN -> {
                        // Gentle breath modulation (0.2 Hz)
                        val breath = 0.6 + 0.4 * sin(2.0 * PI * 0.15 * t)
                        val vibrato = 1.0 + 0.006 * sin(2.0 * PI * 4.8 * t)

                        // Additive sacred choir vocal formant harmonics
                        val s1 = sin(2.0 * PI * (fD3 * vibrato) * t) * 0.45
                        val s2 = sin(2.0 * PI * (fA3 * vibrato) * t) * 0.30
                        val s3 = sin(2.0 * PI * (fD4 * vibrato) * t) * 0.20
                        val s4 = sin(2.0 * PI * (fF4 * vibrato) * t) * 0.15
                        val harmonic = sin(2.0 * PI * (fD3 * 3 * vibrato) * t) * 0.08

                        sample = (s1 + s2 + s3 + s4 + harmonic) * breath * 0.55
                    }

                    SoundscapeMode.ORGAN -> {
                        // Slowly cycle through church chord progression every 8 seconds
                        val chordIdx = ((t / 8.0).toInt()) % organChords.size
                        val notes = organChords[chordIdx]
                        var organSum = 0.0
                        for (freq in notes) {
                            // 8' fundamental + 4' octave + 2' super-octave pipe stops
                            val f1 = sin(2.0 * PI * freq * t) * 0.4
                            val f2 = sin(2.0 * PI * (freq * 2.0) * t) * 0.25
                            val f3 = sin(2.0 * PI * (freq * 3.0) * t) * 0.12
                            val f4 = sin(2.0 * PI * (freq * 4.0) * t) * 0.06
                            organSum += (f1 + f2 + f3 + f4)
                        }
                        // Gentle liturgical acoustic swell
                        val swell = 0.75 + 0.25 * sin(2.0 * PI * 0.2 * t)
                        sample = (organSum / notes.size) * swell * 0.45
                    }

                    SoundscapeMode.MONASTERY -> {
                        // Periodic monastery bell toll every 6 seconds
                        val bellInterval = (sampleRate * 6)
                        val bellPhase = (sampleIndex % bellInterval).toDouble() / sampleRate
                        val decay = exp(-bellPhase * 1.8) // Exponential acoustic bell decay

                        // Campana bell strike (strike notes: 440 Hz, 880 Hz, 1220 Hz, 1760 Hz with metallic ratios)
                        val b1 = sin(2.0 * PI * 440.0 * bellPhase) * 0.5
                        val b2 = sin(2.0 * PI * 884.0 * bellPhase) * 0.3
                        val b3 = sin(2.0 * PI * 1326.0 * bellPhase) * 0.15
                        val b4 = sin(2.0 * PI * 1768.0 * bellPhase) * 0.08
                        val bell = (b1 + b2 + b3 + b4) * decay

                        // Ambient quiet sanctuary air tone
                        val ambient = sin(2.0 * PI * 110.0 * t) * 0.04
                        sample = bell * 0.55 + ambient
                    }

                    SoundscapeMode.SILENCE -> {
                        sample = 0.0
                    }
                }

                // Clamping to 16-bit PCM range
                val clamped = (sample.coerceIn(-1.0, 1.0) * 32767.0).toInt().toShort()
                shortBuffer[i] = clamped
                sampleIndex++
            }

            try {
                audioTrack?.write(shortBuffer, 0, chunkSize)
            } catch (e: Exception) {
                break
            }
        }
    }

    fun stop() {
        synthesisJob?.cancel()
        synthesisJob = null
        try {
            audioTrack?.pause()
            audioTrack?.flush()
            audioTrack?.stop()
            audioTrack?.release()
        } catch (e: Exception) {
            // Ignored on teardown
        } finally {
            audioTrack = null
        }
    }

    fun release() {
        stop()
    }
}
