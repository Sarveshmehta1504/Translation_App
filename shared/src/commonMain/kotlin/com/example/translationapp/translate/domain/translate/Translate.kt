package com.example.translationapp.translate.domain.translate

import com.example.translationapp.core.domain.history.HistoryDataSource
import com.example.translationapp.core.domain.history.HistoryItem
import com.example.translationapp.core.domain.language.Language
import com.example.translationapp.core.domain.util.Resources

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resources<String>{
        return try {
            val translatedText = client.translate(
                fromLanguage,
                fromText,
                toLanguage
            )
            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText
                )
            )
            Resources.Success(translatedText)
        }catch (e:TranslateException){
            e.printStackTrace()
            Resources.Error(e)
        }
    }
}