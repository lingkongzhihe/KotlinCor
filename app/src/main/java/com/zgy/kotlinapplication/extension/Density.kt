package com.zgy.kotlinapplication.extension

import com.zgy.kotlinapplication.App

fun screenPixel(): String {
    App.context.resources.displayMetrics.run {
        return "${widthPixels}x${heightPixels}"
    }
}