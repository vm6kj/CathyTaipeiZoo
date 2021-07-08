package com.kc_hsu.cathaytpezoo.data.source.remote

import com.kc_hsu.cathaytpezoo.data.TpeZooApiClient
import com.kc_hsu.cathaytpezoo.data.responsebody.AreaPlantResponseBody
import com.kc_hsu.cathaytpezoo.data.responsebody.ZooAreaResponseBody
import com.kc_hsu.cathaytpezoo.data.source.TpeZooDataSource
import io.reactivex.Single

// TODO TpeZooLocalDataSource
class TpeZooRemoteDataSource : TpeZooDataSource {

    companion object {
        private var INSTANCE: TpeZooRemoteDataSource? = null

        @JvmStatic
        fun getInstance(): TpeZooRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(TpeZooRemoteDataSource::javaClass) {
                    INSTANCE = TpeZooRemoteDataSource()
                }
            }
            return INSTANCE!!
        }
    }

    private val tpeZooApi = TpeZooApiClient.getApiClient()

    override fun loadZooAreaData(): Single<List<ZooAreaResponseBody.Result.ResultItem>> {
        return tpeZooApi.getZooArea()
            .map { response ->
                response.result?.resultItems
            }
    }

    override fun loadPlantByArea(zooAreaName: String): Single<List<AreaPlantResponseBody.Result.ResultItem>> {
        return tpeZooApi.getAreaPlant(query = zooAreaName)
            .map { response ->
                response.result?.resultItems
            }
    }
}