package com.kc_hsu.cathaytpezoo.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import timber.log.Timber

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VDB

    protected fun bindView(@LayoutRes layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    open fun handleErrorMessage(message: String) {
        Timber.d(message)
    }
}