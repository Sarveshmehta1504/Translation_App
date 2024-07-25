package com.example.translationapp.di

import com.example.translationapp.core.domain.history.HistoryDataSource
import com.example.translationapp.translate.data.local.FakeHistoryDataSource
import com.example.translationapp.translate.domain.translate.Translate
import com.example.translationapp.translate.domain.translate.TranslateClient
import com.example.translationapp.voice_to_text.data.FakeVoiceToTextParser
import com.example.translationapp.voice_to_text.domain.VoiceToTextParser
import com.google.android.datatransport.runtime.dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun FakeTranslateClient(): TranslateClient {
        return FakeTranslateClient()
    }

    @Provides
    @Singleton
    fun provideFakeHistoryDataSource(): HistoryDataSource {
        return FakeHistoryDataSource()
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
        ): Translate{
        return Translate(client,dataSource)
    }

    @Provides
    @Singleton
    fun provideFakeVoiceToTextParser(): VoiceToTextParser {
        return FakeVoiceToTextParser()
    }


}