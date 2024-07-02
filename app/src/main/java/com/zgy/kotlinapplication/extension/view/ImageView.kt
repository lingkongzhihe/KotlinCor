package com.zgy.kotlinapplication.extension.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zgy.kotlinapplication.R

fun ImageView.roundImage(url: String, radius: Int) {
    val requestOptions = RequestOptions.bitmapTransform(RoundedCorners(radius))
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.circleImage(url: String) {
    val requestOptions = RequestOptions.bitmapTransform(CircleCrop())
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.circleHeader(url: String) {
    val requestOptions = RequestOptions.bitmapTransform(CircleCrop())
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .placeholder(R.drawable.icon_avator)
        .error(R.drawable.icon_avator)
        .into(this)
}

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}