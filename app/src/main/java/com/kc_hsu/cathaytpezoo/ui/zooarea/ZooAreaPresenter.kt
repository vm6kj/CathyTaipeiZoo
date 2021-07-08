package com.kc_hsu.cathaytpezoo.ui.zooarea

import android.content.Context
import com.kc_hsu.cathaytpezoo.data.TpeZooRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ZooAreaPresenter(private val context: Context): ZooAreaContract.Presenter {

    private val tpeZooRepository = TpeZooRepositoryProvider.provide()
    private val disposables = CompositeDisposable()

    private lateinit var zooAreaView: ZooAreaContract.View

    override fun loadZooArea() {
        val disposable = tpeZooRepository.loadZooAreaData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                zooAreaView.setLoadDataErrorView(false)
                zooAreaView.setLoadingView(true)
            }
            .doOnSuccess{ list ->
                zooAreaView.setLoadingView(false)
                zooAreaView.showZooAreaList(list)
            }
            .doOnError{
                zooAreaView.setLoadDataErrorView(true)
                zooAreaView.setLoadingView(false)
            }
            .subscribe()
        disposables.add(disposable)
    }

    override fun subscribeWithView(view: ZooAreaContract.View) {
        zooAreaView = view
        loadZooArea()
    }

    override fun unsubscribe() {
        disposables.clear()
    }
}