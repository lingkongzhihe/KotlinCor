package com.zgy.kotlinapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        Thread.currentThread().uncaughtExceptionHandler = ExceptionHandler()
    }
}