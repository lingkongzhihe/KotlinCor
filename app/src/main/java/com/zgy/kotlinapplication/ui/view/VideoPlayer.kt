package com.zgy.kotlinapplication.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.zgy.kotlinapplication.R

class VideoPlayer : StandardGSYVideoPlayer {
    constructor(context: Context) : super(context)
    constructor(context: Context, flag: Boolean) : super(context, flag)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun getLayoutId(): Int {
        return R.layout.video_player_layout
    }

    fun setCoverImageView(resId: Int) {
        val coverImageView = ImageView(context)
        coverImageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        coverImageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        coverImageView.setImageResource(resId)
        thumbImageView = coverImageView
    }

    fun showThumb(isShow: Boolean) {
        thumbImageView.visibility = if (isShow) VISIBLE else INVISIBLE
        mThumbImageViewLayout.visibility = if (isShow) VISIBLE else INVISIBLE
    }

    override fun setThumbImageView(view: View?) {
        super.setThumbImageView(view)
    }
}