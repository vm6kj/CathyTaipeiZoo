package com.kc_hsu.cathaytpezoo.base

interface BasePresenter<T : BaseView> {
    fun subscribe(view: T)
    fun unsubscribe()
}