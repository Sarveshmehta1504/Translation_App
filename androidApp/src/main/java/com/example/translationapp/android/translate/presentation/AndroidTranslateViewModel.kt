package com.example.translationapp.android.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.translationapp.core.domain.history.HistoryDataSource
import com.example.translationapp.translate.domain.translate.Translate
import com.example.translationapp.translate.presentation.TranslateEvent
import com.example.translationapp.translate.presentation.TranslateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource
): ViewModel(){
    private val viewModel by lazy {
        TranslateViewModel(
            translate = translate,
            historyDataSource = historyDataSource,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    fun onEvent(event: TranslateEvent){
        viewModel.onEvent(event)
    }

}