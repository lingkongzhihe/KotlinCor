package com.zgy.kotlinapplication.logic.api

import com.zgy.kotlinapplication.logic.ServiceCreator
import com.zgy.kotlinapplication.logic.model.Daily
import retrofit2.http.GET
import retrofit2.http.Url

interface MainPageService {
    @GET
    suspend fun getDaily(@Url url: String): Daily

    companion object {
        /**
         * 首页-日报列表
         */
        const val DAILY_URL = "${ServiceCreator.BASE_URL}api/v5/index/tab/feed"

        /**
         * 首页-推荐列表
         */
        const val HOMEPAGE_RECOMMEND_URL = "${ServiceCreator.BASE_URL}api/v5/index/tab/allRec"
    }
}