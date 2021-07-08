package com.kc_hsu.cathaytpezoo

import com.kc_hsu.cathaytpezoo.data.TpeZooApi
import com.kc_hsu.cathaytpezoo.data.TpeZooApiClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TpeZooApiTest {
    private lateinit var tpeZooApi: TpeZooApi

    @Before
    fun setUp() {
        tpeZooApi = TpeZooApiClient.getApiClient()
    }

    @Test
    fun testGetZooArea() = runBlocking {
        tpeZooApi.getZooArea().run {
            assertTrue(isSuccessful)
            println("getZooArea: ${body()}")
        }
    }

    @Test
    fun testGetAreaPlant() = runBlocking {
        tpeZooApi.getAreaPlant().run {
            assertTrue(isSuccessful)
            println("testGetAreaPlant: ${body()}")
        }
    }
}