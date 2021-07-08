package com.kc_hsu.cathaytpezoo.data.source

import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import io.reactivex.Single

interface TpeZooDataSource {
    fun loadZooAreaData(): Single<List<ZooAreaResponseBody.Result.ResultItem>>

    fun loadPlantByArea(zooAreaName: String): Single<List<AreaPlantResponseBody.Result.ResultItem>>
}