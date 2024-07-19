package com.example.translationapp.voice_to_text.domain

data class VoiceTextParserState(
    val result: String = "",
    val error:String? = null,
    val powerRatio: Float = 0f,
    val isSpeaking:Boolean = false
)
