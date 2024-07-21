package com.example.translationapp.android.voice_to_text.data

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.SpeechRecognizer.ERROR_CLIENT
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.translationapp.android.R
import com.example.translationapp.core.domain.util.CommonStateFlow
import com.example.translationapp.core.domain.util.toCommonStateFlow
import com.example.translationapp.voice_to_text.domain.VoiceTextParserState
import com.example.translationapp.voice_to_text.domain.VoiceToTextParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.GregorianCalendar

class AndroidVoiceToTextParser(
    private val app: Application
): VoiceToTextParser, RecognitionListener {
    private val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    private val _state = MutableStateFlow(VoiceTextParserState())

    override val state: CommonStateFlow<VoiceTextParserState> =
        _state.toCommonStateFlow()


    override fun startListening(languageCode: String) {
        _state.update{
            VoiceTextParserState()
        }
        if(!SpeechRecognizer.isRecognitionAvailable(app)){
            _state.update { it.copy(
                error = app.getString(R.string.error_speech_not_available))
            }
            return
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
        }
        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)
        _state.update {it.copy(
                isSpeaking = true
            )

        }
    }

    override fun stopListening() {
        _state.update { VoiceTextParserState() }
        recognizer.stopListening()
    }

    override fun cancel() {
        recognizer.cancel()
    }

    override fun reset() {
        _state.value = VoiceTextParserState()
    }

    override fun onReadyForSpeech(params: Bundle?) {
        _state.update { it.copy(error = null) }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) {
        _state.update { it.copy(
            powerRatio = rmsdB * (1f/(12f - (-2f)))
        )
        }
    }

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update { it.copy(isSpeaking = false) }
    }

    override fun onError(code: Int) {
        if(code == ERROR_CLIENT){
            return
        }
        _state.update { it.copy(error = "Error: $code") }
    }

    override fun onResults(results: Bundle?) {
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { text ->
                _state.update {
                    it.copy(result = text)
                }
            }
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?)= Unit

}