package com.example.translationapp.translate.domain.translate

import com.example.translationapp.core.domain.language.Language

interface TranslateClient{
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}