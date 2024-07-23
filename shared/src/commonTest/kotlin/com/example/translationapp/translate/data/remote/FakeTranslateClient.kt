package com.example.translationapp.translate.data.remote

import com.example.translationapp.core.domain.language.Language
import com.example.translationapp.translate.domain.translate.TranslateClient

class FakeTranslateClient: TranslateClient {
    var translatedText = "test translation"
    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        return translatedText
    }
}