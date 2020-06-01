/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.repository

import com.samsruti.kotlin30days.entities.bigdatacloud.BigDataCloudData
import com.samsruti.kotlin30days.network.BigDataCloudApi
import com.samsruti.kotlin30days.network.CovidApiService

interface DataCloudRepositoryContract{
    suspend fun getGeoCordinates(lat: String, lon: String): BigDataCloudData?
}

class DataCloudRepository : DataCloudRepositoryContract {

    private val dataCloudApi: BigDataCloudApi by lazy {
        CovidApiService.getBigDataCloudApiService()
    }

    override suspend fun getGeoCordinates(lat: String, lon: String): BigDataCloudData? {
        return dataCloudApi.getReverseGeoDecode(latitude = lat, longitude = lon)
    }
}