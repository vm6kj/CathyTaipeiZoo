package com.kc_hsu.cathaytpezoo.data.source

import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.data.source.remote.TpeZooRemoteDataSource
import io.reactivex.Single

// TODO TpeZooLocalDataSource
class TpeZooRepository(private val tpeZooRemoteDataSource: TpeZooRemoteDataSource) :
    TpeZooDataSource {

    override fun loadZooAreaData(): Single<List<ZooAreaResponseBody.Result.ResultItem>> {
        return tpeZooRemoteDataSource.loadZooAreaData()
    }

    override fun loadPlantByArea(zooAreaName: String): Single<List<AreaPlantResponseBody.Result.ResultItem>> {
        return tpeZooRemoteDataSource.loadPlantByArea(zooAreaName)
    }
}