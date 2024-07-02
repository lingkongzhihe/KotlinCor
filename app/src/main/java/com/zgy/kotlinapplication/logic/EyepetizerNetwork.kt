package com.zgy.kotlinapplication.logic

import com.zgy.kotlinapplication.logic.api.MainPageService
import com.zgy.kotlinapplication.logic.api.VideoService

class EyepetizerNetwork {
    val mainPageService = ServiceCreator.create(MainPageService::class.java)

    val videoService = ServiceCreator.create(VideoService::class.java)

    companion object {
        @Volatile
        private var INSTANCE: EyepetizerNetwork? = null

        fun getInstance(): EyepetizerNetwork = INSTANCE ?: synchronized(this) {
            INSTANCE ?: EyepetizerNetwork()
        }
    }
}