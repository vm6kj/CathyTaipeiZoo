package com.kc_hsu.cathaytpezoo.data.responsebody


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Generated by: https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */

data class ZooAreaResponseBody(
    @SerializedName("result")
    var result: Result? = Result()
) {
    data class Result(
        @SerializedName("count")
        var count: Int? = 0,
        @SerializedName("limit")
        var limit: Int? = 0,
        @SerializedName("offset")
        var offset: Int? = 0,
        @SerializedName("results")
        var resultItems: List<ResultItem> = listOf(),
        @SerializedName("sort")
        var sort: String? = ""
    ) {
        @Parcelize
        data class ResultItem(
            @SerializedName("E_Category")
            var eCategory: String,
            @SerializedName("E_Geo")
            var eGeo: String,
            @SerializedName("E_Info")
            var eInfo: String,
            @SerializedName("E_Memo")
            var eMemo: String,
            @SerializedName("E_Name")
            var eName: String,
            @SerializedName("E_no")
            var eNo: String,
            @SerializedName("E_Pic_URL")
            var ePicURL: String,
            @SerializedName("E_URL")
            var eURL: String,
            @SerializedName("_id")
            var id: Int = 0
        ) : Parcelable
    }
}