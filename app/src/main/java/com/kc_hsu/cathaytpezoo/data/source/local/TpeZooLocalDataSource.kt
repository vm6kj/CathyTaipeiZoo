package com.kc_hsu.cathaytpezoo.data.source.local

import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.data.source.TpeZooDataSource
import io.reactivex.Single

// TODO Need to be implemented
object TpeZooLocalDataSource : TpeZooDataSource {
    override fun loadZooAreaData(): Single<List<ZooAreaResponseBody.Result.ResultItem>> {
        TODO("Not yet implemented")
    }

    override fun loadPlantByArea(zooAreaName: String): Single<List<AreaPlantResponseBody.Result.ResultItem>> {
        TODO("Not yet implemented")
    }
}