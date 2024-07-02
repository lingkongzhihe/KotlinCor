package com.zgy.kotlinapplication.ui.home

import androidx.lifecycle.ViewModel
import com.zgy.kotlinapplication.logic.model.Daily
import com.zgy.kotlinapplication.logic.network.DailyPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeViewModel : ViewModel() {
    fun getData(): Flow<List<Daily.Item>> = flow {
        val list: List<Daily.Item> = DailyPagingSource().load().filter { item -> item.type == "followCard" }
        emit(list)
    }
}