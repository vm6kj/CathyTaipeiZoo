package com.kc_hsu.cathaytpezoo.data.source

import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.data.source.remote.TpeZooRemoteDataSource
import io.reactivex.Single

object TpeZooRepository : TpeZooDataSource {

    // TODO Add some logic to fetch data from TpeZooLocalDataSource as well.
    private val tpeZooRemoteDataSource = TpeZooRemoteDataSource

    override fun loadZooAreaData(): Single<List<ZooAreaResponseBody.Result.ResultItem>> {
        return tpeZooRemoteDataSource.loadZooAreaData()
    }

    override fun loadPlantByArea(zooAreaName: String): Single<List<AreaPlantResponseBody.Result.ResultItem>> {
        return tpeZooRemoteDataSource.loadPlantByArea(zooAreaName)
    }
}