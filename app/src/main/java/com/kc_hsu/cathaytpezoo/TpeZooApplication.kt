package com.kc_hsu.cathaytpezoo

import android.content.Context
import android.util.Log
import androidx.multidex.MultiDexApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber

class TpeZooApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: TpeZooApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("CathayTpeZoo")
            .methodCount(3)
            .methodOffset(5) // avoid timber internal stack track
            .build()

        val logAdapter = object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        }

        Logger.addLogAdapter(logAdapter)
        Timber.plant(TimberLoggerDebugTree())
    }

    private class TimberLoggerDebugTree : Timber.DebugTree() {

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            when (priority) {
                Log.VERBOSE -> Logger.v(message)
                Log.DEBUG -> Logger.d(message)
                Log.INFO -> Logger.i(message)
                Log.WARN -> Logger.w(message)
                Log.ERROR -> Logger.e(t, message)
                else -> Logger.wtf(message)
            }
        }
    }
}