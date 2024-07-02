package com.zgy.kotlinapplication.ui.video

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.NavArgument
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.zgy.kotlinapplication.R
import com.zgy.kotlinapplication.databinding.ActivityVideoPlayerBinding
import com.zgy.kotlinapplication.ui.video.adapter.VideoDetailPageAdapter

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class VideoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding

    private lateinit var orientationUtils: OrientationUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.w(TAG, "onCreate")

        orientationUtils = OrientationUtils(this@VideoDetailActivity, binding.videoPlayer)
        binding.videoPlayer.fullscreenButton.setOnClickListener {
            orientationUtils.resolveByClick()
        }
        init()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        init()
    }

    private fun init() {
        val videoUrl = intent?.getStringExtra("videoUrl")
        val coverUrl = intent?.getStringExtra("coverUrl")
        val title = intent?.getStringExtra("title")
        val videoId = intent.getLongExtra("videoId", -1)
        Log.w(TAG, "videoId=$videoId")

        binding.viewPager2.adapter = VideoDetailPageAdapter(this, videoId)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            if (position == 0) {
                tab.text = "简介"
            } else {
                tab.text = "评论"
            }
        }.attach()

        binding.videoPlayer.backButton.setOnClickListener {
            finish()
        }
        binding.videoPlayer.setUp(videoUrl, true, title)

        binding.videoPlayer.isShowFullAnimation = false

        binding.videoPlayer.setCoverImageView(R.mipmap.video_cover)

        Log.w(TAG, "videoUrl: $videoUrl")
        binding.videoPlayer.startPlayLogic()
    }

    fun setReplyCount(count: Int) {
        val tab: Tab? = binding.tabLayout.getTabAt(1)
        tab?.text = "评论$count"
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.w(TAG, "onConfigurationChanged")
        binding.videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true)
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume")
        GSYVideoManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause")
        GSYVideoManager.onPause()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (orientationUtils.screenType == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            binding.videoPlayer.fullscreenButton.performClick()
            return
        }
        binding.videoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        orientationUtils.releaseListener()
    }

    companion object {
        val TAG = "VideoPlayerActivity"

        fun start(
            context: Context,
            playUrl: String,
            coverUrl: String,
            title: String,
            videoId: Long
        ) {
            val intent = Intent(context, VideoDetailActivity::class.java)
            intent.putExtra("videoUrl", playUrl)
            intent.putExtra("coverUrl", coverUrl)
            intent.putExtra("title", title)
            intent.putExtra("videoId", videoId)
            context.startActivity(intent)
        }
    }
}