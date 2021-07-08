package com.kc_hsu.cathaytpezoo.base

interface BasePresenter<T : BaseView> {
    fun subscribeWithView(view: T)
    fun unsubscribe()
}