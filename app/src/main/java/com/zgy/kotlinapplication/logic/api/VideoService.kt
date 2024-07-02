package com.zgy.kotlinapplication.logic.api

import com.zgy.kotlinapplication.logic.ServiceCreator
import com.zgy.kotlinapplication.logic.model.VideoBeanForClient
import com.zgy.kotlinapplication.logic.model.VideoRelated
import com.zgy.kotlinapplication.logic.model.VideoReplies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface VideoService {
    @GET("api/v2/video/{id}")
    suspend fun getVideoDetail(@Path("id") videoId: Long) : VideoBeanForClient

    /**
     * 视频详情-推荐列表
     */
    @GET("api/v4/video/related")
    suspend fun getVideoRelated(@Query("id") videoId: Long): VideoRelated

    /**
     * 视频详情-评论列表
     */
    @GET
    suspend fun getVideoReplies(@Url url: String): VideoReplies

    companion object {

        /**
         * 视频详情-评论列表URL
         */
        const val VIDEO_REPLIES_URL = "${ServiceCreator.BASE_URL}api/v2/replies/video?videoId="
    }
}