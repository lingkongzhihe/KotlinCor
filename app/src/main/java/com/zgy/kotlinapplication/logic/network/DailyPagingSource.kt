package com.zgy.kotlinapplication.logic.network

import com.zgy.kotlinapplication.logic.model.Daily

class DailyPagingSource {
    suspend fun load(): List<Daily.Item> {
        return MainPageRepository().getDaily().itemList
    }
}