package com.zgy.kotlinapplication.ui.video

import androidx.lifecycle.ViewModel
import com.zgy.kotlinapplication.logic.api.VideoService
import com.zgy.kotlinapplication.logic.model.VideoDetail
import com.zgy.kotlinapplication.logic.model.VideoReplies
import com.zgy.kotlinapplication.logic.network.VideoDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoViewModel : ViewModel() {
    fun requestVideoRelatedAndVideoReplies(videoId: Long): Flow<VideoDetail> = flow {
        val videoDetail = VideoDataSource.getInstance().requestVideoRelatedAndVideoReplies(videoId, "${VideoService.VIDEO_REPLIES_URL}${videoId}")
        emit(videoDetail)
    }

    fun requestVideoDetail(videoId: Long): Flow<VideoDetail> = flow {
        val videoDetail = VideoDataSource.getInstance().requestVideoDetail(videoId, "${VideoService.VIDEO_REPLIES_URL}${videoId}")
        emit(videoDetail)
    }

    fun requestVideoReplies(videoId: Long): Flow<VideoReplies> = flow {
        val videoReplies = VideoDataSource.getInstance().requestVideoReplies("${VideoService.VIDEO_REPLIES_URL}${videoId}")
        emit(videoReplies)
    }
}