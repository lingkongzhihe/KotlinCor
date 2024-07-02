package com.zgy.kotlinapplication.logic.network

import com.zgy.kotlinapplication.logic.model.VideoDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class VideoDataSource {
    suspend fun requestVideoRelatedAndVideoReplies(videoId: Long, repliesUrl: String) = withContext(Dispatchers.IO) {
        val deferredVideoRelated = async {
            VideoRepository.getInstance().getVideoRelated(videoId)
        }
        val deferredVideoReplies = async {
            VideoRepository.getInstance().getVideoReplies(repliesUrl)
        }
        val videoRelated = deferredVideoRelated.await()
        val videoReplies = deferredVideoReplies.await()
        val videoDetail = VideoDetail(null, videoRelated, videoReplies)
        videoDetail
    }

    suspend fun requestVideoReplies(repliesUrl: String) = withContext(Dispatchers.IO) {
        val videoReplies = async {
            VideoRepository.getInstance().getVideoReplies(repliesUrl)
        }.await()
        videoReplies
    }

    suspend fun requestVideoDetail(videoId: Long, repliesUrl: String) = withContext(Dispatchers.IO) {
        val videoRelatedAndReplies = requestVideoRelatedAndVideoReplies(videoId, repliesUrl)
        val videoBeanForClient = async {
            VideoRepository.getInstance().getVideoDetail(videoId)
        }.await()
        val videoDetail = VideoDetail(videoBeanForClient, videoRelatedAndReplies.videoRelated, videoRelatedAndReplies.videoReplies)
        videoDetail
    }

    companion object {
        @Volatile
        private var INSTANCE: VideoDataSource? = null

        fun getInstance(): VideoDataSource {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = VideoDataSource()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}