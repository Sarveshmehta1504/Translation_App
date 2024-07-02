package com.example.translationapp.core.presentation

import com.example.translationapp.core.domain.language.Language

expect class UiLanguage {
    val language: Language
    companion object{
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}