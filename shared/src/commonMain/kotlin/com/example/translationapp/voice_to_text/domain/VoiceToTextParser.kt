package com.example.translationapp.voice_to_text.domain

import com.example.translationapp.core.domain.util.CommonStateFlow

interface VoiceToTextParser {
    val state: CommonStateFlow<VoiceTextParserState>
    fun startListening(languageCode: String)
    fun stopListening()
    fun close()
    fun reset()
}