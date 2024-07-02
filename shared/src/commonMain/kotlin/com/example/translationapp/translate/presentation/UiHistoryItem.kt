package com.example.translationapp.translate.presentation

import com.example.translationapp.core.presentation.UiLanguage

data class UiHistoryItem(
    val id: Long,
    val fromText: String,
    val fromLanguage: UiLanguage,
    val toText: String,
    val toLanguage: UiLanguage
)
