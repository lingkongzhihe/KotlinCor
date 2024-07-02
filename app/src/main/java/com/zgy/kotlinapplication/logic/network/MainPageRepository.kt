package com.zgy.kotlinapplication.logic.network

import com.zgy.kotlinapplication.logic.EyepetizerNetwork
import com.zgy.kotlinapplication.logic.api.MainPageService
import com.zgy.kotlinapplication.logic.model.Daily

class MainPageRepository {
    suspend fun getDaily(): Daily {
        return EyepetizerNetwork.getInstance().mainPageService.getDaily(MainPageService.DAILY_URL)
    }
}