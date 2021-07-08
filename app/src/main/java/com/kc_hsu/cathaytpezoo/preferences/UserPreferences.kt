package com.kc_hsu.cathaytpezoo.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

@SuppressLint("StaticFieldLeak")
object UserPreferences {

    private const val PREF_NAME = "tpeZooPref"
    private const val PREF_THEME_KEY = "prefTheme"

    @AppCompatDelegate.NightMode
    fun getTheme(context: Context): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        Timber.d("Current style: ${prefs.getInt(PREF_THEME_KEY, AppCompatDelegate.MODE_NIGHT_YES)}")
        return prefs.getInt(PREF_THEME_KEY, AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun setTheme(context: Context, @AppCompatDelegate.NightMode nightMode: Int) {
        Timber.d("setTheme: $nightMode")
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit()
            .putInt(PREF_THEME_KEY, nightMode).apply()
    }
}
