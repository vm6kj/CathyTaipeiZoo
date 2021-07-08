package com.kc_hsu.cathaytpezoo.data

import com.kc_hsu.cathaytpezoo.data.source.TpeZooRepository
import com.kc_hsu.cathaytpezoo.data.source.remote.TpeZooRemoteDataSource

// Better for testing
object TpeZooRepositoryProvider {
    fun provide(): TpeZooRepository {
        val remoteDataSource = TpeZooRemoteDataSource.getInstance()
        return TpeZooRepository(remoteDataSource)
    }
}