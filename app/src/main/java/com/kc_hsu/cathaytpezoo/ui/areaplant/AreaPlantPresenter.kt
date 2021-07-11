package com.kc_hsu.cathaytpezoo.ui.areaplant

import com.kc_hsu.cathaytpezoo.data.TpeZooRepositoryProvider
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AreaPlantPresenter(
    private val areaPlantView: AreaPlantContract.View,
    private val zooAreaItem: ZooAreaResponseBody.Result.ResultItem
) :
    AreaPlantContract.Presenter {
    private val tpeZooRepository = TpeZooRepositoryProvider.provide()
    private val disposables = CompositeDisposable()

    override fun loadAreaPlant() {
        areaPlantView.showZooAreaDetailInfo(zooAreaItem)
        val disposable = tpeZooRepository.loadPlantByArea(zooAreaItem.eName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                areaPlantView.setLoadDataErrorView(false)
                areaPlantView.setLoadingView(true)
            }
            .doOnSuccess { list ->
                areaPlantView.setLoadingView(false)
                areaPlantView.showRelatedPlantList(list)
            }.doOnError {
                areaPlantView.setLoadDataErrorView(true)
                areaPlantView.setLoadingView(false)
            }
            .subscribe()
        disposables.add(disposable)
    }

    override fun subscribe(view: AreaPlantContract.View) {
        loadAreaPlant()
    }

    override fun unsubscribe() {
        disposables.clear()
    }
}