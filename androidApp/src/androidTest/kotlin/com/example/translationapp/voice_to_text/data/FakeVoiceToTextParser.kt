package com.example.translationapp.voice_to_text.data

import com.example.translationapp.core.domain.util.CommonStateFlow
import com.example.translationapp.core.domain.util.toCommonFlow
import com.example.translationapp.core.domain.util.toCommonStateFlow
import com.example.translationapp.voice_to_text.domain.VoiceTextParserState
import com.example.translationapp.voice_to_text.domain.VoiceToTextParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeVoiceToTextParser : VoiceToTextParser {
    private val _state = MutableStateFlow(VoiceTextParserState())


    override val state: CommonStateFlow<VoiceTextParserState>
        get() = _state.toCommonStateFlow()

    var voiceResult = "test Result"

    override fun startListening(languageCode: String) {
        _state.update { it.copy(
            result = "",
            isSpeaking = true
        ) }
    }

    override fun stopListening() {
        _state.update {it.copy(
            result = voiceResult,
            isSpeaking = false
        )

        }
    }

    override fun cancel() = Unit

    override fun reset() {
        _state.update { VoiceTextParserState() }
    }
}