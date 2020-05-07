package com.ugsg.sip.demo_kotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.hjq.toast.ToastUtils

class MyApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "qNZ3V5hvHnFGLxQg"

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        ToastUtils.init(this)
    }
}