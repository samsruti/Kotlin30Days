package com.samsruti.kotlin30days.network

import com.samsruti.kotlin30days.entities.bigdatacloud.BigDataCloudData
import retrofit2.http.GET
import retrofit2.http.Query

interface BigDataCloudApi{

    @GET("data/reverse-geocode-client")
    suspend fun getReverseGeoDecode(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): BigDataCloudData
}