package com.zgy.kotlinapplication.logic

import android.os.Build
import android.util.Log
import com.zgy.kotlinapplication.extension.screenPixel
import com.zgy.kotlinapplication.logic.api.MainPageService
import com.zgy.kotlinapplication.utils.GlobalUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.Date
import kotlin.jvm.Throws

object ServiceCreator {
    const val BASE_URL = "http://baobab.kaiyanapp.com/"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(BasicParamsInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originRequest = chain.request()
            val t1 = System.nanoTime();
            Log.i(TAG, "Send request: ${originRequest.url()}, \n ${originRequest.headers()}")

            val response = chain.proceed(originRequest);
            val t2 = System.nanoTime();

            Log.i(TAG, "Received response for ${response.request().url()} in ${(t2 - t1) / 1e6} ms \n ${response.headers()}")
            return response;
        }

        companion object {
            const val TAG = "LoggingInterceptor"
        }
    }

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originRequest = chain.request();
            val request = originRequest.newBuilder().apply {
                header("model", "Android")
                header("If-Modified-Since", "${Date()}")
                header("User-Agent", System.getProperty("http.agent") ?: "unknown")
            }.build()
            return chain.proceed(request)
        }
    }

    class BasicParamsInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originRequest = chain.request()
            val originHttpUrl = originRequest.url()
            val url = originHttpUrl.newBuilder().apply {
                addQueryParameter("udid", GlobalUtil.getDeviceSerial())
                //针对开眼官方【首页推荐 】api 变动， 需要单独做处理。原因：附加 vc、vn 这两个字段后，请求接口无响应。
                if (!originHttpUrl.toString().contains(MainPageService.HOMEPAGE_RECOMMEND_URL)) {
                    addQueryParameter("vc", GlobalUtil.eyepetizerVersionCode.toString())
                    addQueryParameter("vn", GlobalUtil.eyepetizerVersionName)
                }
                addQueryParameter("size", screenPixel())
                addQueryParameter("deviceModel", GlobalUtil.deviceModel)
                addQueryParameter("first_channel", GlobalUtil.deviceBrand)
                addQueryParameter("last_channel", GlobalUtil.deviceBrand)
                addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
            }.build()
            val request = originRequest.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    }
}