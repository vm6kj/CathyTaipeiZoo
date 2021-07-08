package com.kc_hsu.cathaytpezoo.ui.zooarea

import com.kc_hsu.cathaytpezoo.base.BasePresenter
import com.kc_hsu.cathaytpezoo.base.BaseView
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody

interface ZooAreaContract {

    interface View : BaseView {
        fun showZooAreaList(list: List<ZooAreaResponseBody.Result.ResultItem>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadZooArea()
    }
}