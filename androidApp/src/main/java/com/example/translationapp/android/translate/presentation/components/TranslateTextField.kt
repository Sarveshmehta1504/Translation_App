package com.example.translationapp.android.translate.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.translationapp.core.presentation.UiLanguage

@Composable
fun TranslateTextField(
    fromText: String,
    toText: String?,
    isTranslating: Boolean,
    fromLanguage: UiLanguage,
    toLanguage: UiLanguage,
    onTranslateClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onCopyClick: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSpeakerClick: () -> Unit,
    onTextFieldClick:() -> Unit,
    modifier: Modifier = Modifier
){

}

