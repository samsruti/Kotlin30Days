/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.network

import com.samsruti.kotlin30days.entities.covid19data.Covid19AllData
import com.samsruti.kotlin30days.entities.essential.EssentialResourcesData
import retrofit2.http.GET

interface Covid19Api {

    @GET("data.json")
    suspend fun getBaseData(): Covid19AllData

    @GET("resources/geoResources.json")
    suspend fun getEssentials(): EssentialResourcesData
}