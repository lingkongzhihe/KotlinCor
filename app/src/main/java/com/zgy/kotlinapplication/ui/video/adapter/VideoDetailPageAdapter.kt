package com.zgy.kotlinapplication.ui.video.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zgy.kotlinapplication.ui.video.VideoIntroductionFragment
import com.zgy.kotlinapplication.ui.video.VideoReplyFragment

class VideoDetailPageAdapter(activity: FragmentActivity, private val videoId: Long) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return VideoIntroductionFragment.newInstance(videoId)
        }
        if (position == 1) {
            return VideoReplyFragment.newInstance(videoId)
        }
        return Fragment()
    }
}