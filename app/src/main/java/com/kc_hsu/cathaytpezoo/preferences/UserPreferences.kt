package com.kc_hsu.cathaytpezoo.preferences

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatDelegate
import com.kc_hsu.cathaytpezoo.TpeZooApplication

@SuppressLint("StaticFieldLeak")
object UserPreferences {

    private const val PREF_NAME = "tpeZooPref"
    private const val PREF_KEY_THEME = "prefTheme"
    private const val PREF_KEY_USER_ALLOW_UNSAFE_CONN = "prefUserAllowUnsafe_Conn"

    private val context = TpeZooApplication.applicationContext()
    private val prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    @AppCompatDelegate.NightMode
    fun getTheme(): Int {
        return prefs.getInt(PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun setTheme(@AppCompatDelegate.NightMode nightMode: Int) {
        prefs.edit().putInt(PREF_KEY_THEME, nightMode).apply()
    }

    fun getUserAllowUnsafeConn(): Boolean {
        return prefs.getBoolean(PREF_KEY_USER_ALLOW_UNSAFE_CONN, false)
    }

    fun setUserAllowUnsafeConn(allow: Boolean) {
        prefs.edit().putBoolean(PREF_KEY_USER_ALLOW_UNSAFE_CONN, allow).apply()
    }
}
