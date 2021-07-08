package com.kc_hsu.cathaytpezoo.ui.areaplant

import com.kc_hsu.cathaytpezoo.base.BasePresenter
import com.kc_hsu.cathaytpezoo.base.BaseView
import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody

interface AreaPlantContract {

    interface View : BaseView {
        fun showZooAreaDetailInfo(zooArea: ZooAreaResponseBody.Result.ResultItem)
        fun showRelatedPlantList(list: List<AreaPlantResponseBody.Result.ResultItem>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadAreaPlant()
    }
}