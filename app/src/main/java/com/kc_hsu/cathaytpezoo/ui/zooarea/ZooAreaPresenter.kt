package com.kc_hsu.cathaytpezoo.ui.zooarea

import com.kc_hsu.cathaytpezoo.data.TpeZooRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ZooAreaPresenter(private val zooAreaView: ZooAreaContract.View) : ZooAreaContract.Presenter {

    private val tpeZooRepository = TpeZooRepositoryProvider.provide()
    private val disposables = CompositeDisposable()

    override fun loadZooArea() {
        val disposable = tpeZooRepository.loadZooAreaData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                zooAreaView.setLoadDataErrorView(false)
                zooAreaView.setLoadingView(true)
            }
            .doOnSuccess { list ->
                zooAreaView.setLoadingView(false)
                zooAreaView.showZooAreaList(list)
            }
            .doOnError {
                zooAreaView.setLoadDataErrorView(true)
                zooAreaView.setLoadingView(false)
            }
            .subscribe()
        disposables.add(disposable)
    }

    override fun subscribe(view: ZooAreaContract.View) {
        loadZooArea()
    }

    override fun unsubscribe() {
        disposables.clear()
    }
}