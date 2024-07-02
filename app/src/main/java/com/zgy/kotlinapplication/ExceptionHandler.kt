package com.zgy.kotlinapplication

import android.util.Log
import java.lang.Thread.UncaughtExceptionHandler

class ExceptionHandler : UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e("ExceptionHandler", String.format("t = %1s, e = %2s", t.name, e.message))
    }
}