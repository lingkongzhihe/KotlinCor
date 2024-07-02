package com.zgy.kotlinapplication.logic.network

import com.zgy.kotlinapplication.logic.EyepetizerNetwork

class VideoRepository {
    private val videoService = EyepetizerNetwork.getInstance().videoService

    suspend fun getVideoDetail(videoId: Long) = videoService.getVideoDetail(videoId)

    suspend fun getVideoRelated(videoId: Long) = videoService.getVideoRelated(videoId)

    suspend fun getVideoReplies(url: String) = videoService.getVideoReplies(url)

    companion object {
        @Volatile
        private var INSTANCE: VideoRepository? = null
        fun getInstance(): VideoRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = VideoRepository()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}