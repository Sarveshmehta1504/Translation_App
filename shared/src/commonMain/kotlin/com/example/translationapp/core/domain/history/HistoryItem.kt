package com.example.translationapp.core.domain.history

data class HistoryItem(
    val id:Long?,
    val fromLanguageCode:String,
    val fromText:String,
    val toLanguageCode:String,
    val toText:String,

)
