package com.example.translationapp.android.translate.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.translationapp.android.core.theme.LightBlueGrey
import com.example.translationapp.core.presentation.Colors.LightBlue
import com.example.translationapp.core.presentation.UiLanguage

@Composable
fun LanguageDisplay(
    language: UiLanguage,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        SmallLanguageIcon(language = language)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = language.language.langName,
            color = Color(LightBlue)
        )
    }
}