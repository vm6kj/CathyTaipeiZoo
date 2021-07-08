package com.kc_hsu.cathaytpezoo.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.kc_hsu.cathaytpezoo.R
import com.kc_hsu.cathaytpezoo.base.BaseActivity
import com.kc_hsu.cathaytpezoo.databinding.TpeZooMainActivityBinding
import com.kc_hsu.cathaytpezoo.preferences.UserPreferences
import com.kc_hsu.cathaytpezoo.ui.zooarea.ZooAreaListFragment
import com.kc_hsu.cathaytpezoo.utils.ExceptionHandler
import com.kc_hsu.cathaytpezoo.utils.replaceFragmentInActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class TpeZooMainActivity : BaseActivity<TpeZooMainActivityBinding>() {

    private lateinit var exceptionHandler: ExceptionHandler
    private var exceptionHandlerDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.tpe_zoo_main_activity)

        setUpNavigationView()

        ZooAreaListFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        exceptionHandler = ExceptionHandler(this.application)
    }

    private fun setUpNavigationView() {
        val ivNavHeader =
            binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.iv_nav_header)
        val menuItem = binding.navView.menu.findItem(R.id.switch_theme)
        initNavHeader(menuItem, ivNavHeader)
        val btnSwitchTheme = menuItem.actionView.findViewById<MaterialButton>(R.id.btn_switch_theme)
        btnSwitchTheme.setOnClickListener {
            switchTheme(menuItem, ivNavHeader)
        }
    }

    private fun initNavHeader(menuItem: MenuItem, ivNavHeader: ImageView) {
        val currentTheme = UserPreferences.getTheme(this)
        if (AppCompatDelegate.MODE_NIGHT_YES == currentTheme) {
            setTheme(AppCompatDelegate.MODE_NIGHT_YES, ivNavHeader, menuItem)
        } else {
            setTheme(AppCompatDelegate.MODE_NIGHT_NO, ivNavHeader, menuItem)
        }
    }

    private fun setTheme(
        @AppCompatDelegate.NightMode theme: Int,
        ivNavHeader: ImageView,
        menuItem: MenuItem
    ) {
        if (AppCompatDelegate.MODE_NIGHT_YES == theme) {
            ivNavHeader.setImageResource(R.drawable.tpe_zoo_logo_dark)
            menuItem.title = getString(R.string.theme_dark_mode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            UserPreferences.setTheme(this, AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            ivNavHeader.setImageResource(R.drawable.tpe_zoo_logo_light)
            menuItem.title = getString(R.string.theme_light_mode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            UserPreferences.setTheme(this, AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun switchTheme(menuItem: MenuItem, ivNavHeader: ImageView) {

        val currentNightMode = UserPreferences.getTheme(this)
        if (AppCompatDelegate.MODE_NIGHT_YES == currentNightMode) {
            Timber.d("MODE_NIGHT_YES, currentNightMode: $currentNightMode")
            setTheme(AppCompatDelegate.MODE_NIGHT_NO, ivNavHeader, menuItem)
        } else {
            Timber.d("MODE_NIGHT_NO, currentNightMode: $currentNightMode")
            setTheme(AppCompatDelegate.MODE_NIGHT_YES, ivNavHeader, menuItem)
        }
    }

    override fun onStart() {
        super.onStart()
        if (null == exceptionHandlerDisposable || false == exceptionHandlerDisposable?.isDisposed) {
            exceptionHandlerDisposable = exceptionHandler.errorMessageToDisplay
                .filter { it.isNotBlank() }
                .throttleFirst(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { handleErrorMessage(it) }
                .subscribe()
        }
    }

    override fun onStop() {
        exceptionHandlerDisposable?.dispose()
        super.onStop()
    }

    override fun handleErrorMessage(message: String) {
        super.handleErrorMessage(message)
        showCustomMessageOnSnackbar(message)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCustomMessageOnSnackbar(message: String) {
        if (message.isNotEmpty()) {
            Snackbar.make(binding.clSnackbarview, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}