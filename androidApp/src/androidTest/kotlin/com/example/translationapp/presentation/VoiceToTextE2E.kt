package com.example.translationapp.presentation

import android.Manifest
import android.app.Application
import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import com.example.translationapp.android.MainActivity
import com.example.translationapp.android.R
import com.example.translationapp.android.di.AppModule
import com.example.translationapp.android.voice_to_text.di.VoiceToTextModule
import com.example.translationapp.translate.data.remote.FakeTranslateClient
import com.example.translationapp.translate.domain.translate.TranslateClient
import com.example.translationapp.voice_to_text.data.FakeVoiceToTextParser
import com.example.translationapp.voice_to_text.domain.VoiceToTextParser
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.ktor.http.ContentType
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class, VoiceToTextModule::class)
class VoiceToTextE2E {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val permissionRule = GrantPermissionRule.grant(
        Manifest.permission.RECORD_AUDIO
    )

    @Inject
    lateinit var fakeVoiceParser: VoiceToTextParser

    @Inject
    lateinit var fakeClient: TranslateClient

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun recordAndTranslate() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val parser = fakeVoiceParser as FakeVoiceToTextParser
        val client = fakeClient as FakeTranslateClient

        composeRule
            .onNodeWithContentDescription(context.getString(R.string.record_audio))
            .performClick()
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.record_audio))
            .performClick()
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.stop_recording))
            .performClick()
        composeRule
            .onNodeWithText(parser.voiceResult)
            .assertIsDisplayed()
        composeRule
            .onNodeWithContentDescription(context.getString(R.string.apply))
            .performClick()
        composeRule
            .onNodeWithText(parser.voiceResult)
            .assertIsDisplayed()
        composeRule
            .onNodeWithText(context.getString(R.string.translate), ignoreCase = true)
            .performClick()
        composeRule
            .onNodeWithText(parser.voiceResult)
            .assertIsDisplayed()


        composeRule
            .onNodeWithText(client.translatedText)
            .assertIsDisplayed()

    }
}