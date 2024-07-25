package com.example.translationapp.translate.data.local

import com.example.translationapp.core.domain.history.HistoryDataSource
import com.example.translationapp.core.domain.history.HistoryItem
import com.example.translationapp.core.domain.util.CommonFlow
import com.example.translationapp.core.domain.util.toCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

class FakeHistoryDataSource: HistoryDataSource {
    private val _data = MutableStateFlow<List<HistoryItem>> (emptyList())
    override fun getHistory(context: CoroutineContext): CommonFlow<List<HistoryItem>> {
        return _data.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
         _data.value += item
    }

}