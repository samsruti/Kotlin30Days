/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.repository

import com.samsruti.kotlin30days.entities.covid19data.Covid19AllData
import com.samsruti.kotlin30days.entities.essential.EssentialResourcesData
import com.samsruti.kotlin30days.network.BigDataCloudApi
import com.samsruti.kotlin30days.network.Covid19Api
import com.samsruti.kotlin30days.network.CovidApiService

interface CovidApiRepositoryInterface {
    suspend fun fetchHomeDetails(): Covid19AllData
    suspend fun fetchEssentialData(): EssentialResourcesData
}

class CovidApiRepository : CovidApiRepositoryInterface {

    private val covid19Service: Covid19Api by lazy {
        CovidApiService.getCovid19ApiService()
    }

    private val dataCloudService: BigDataCloudApi by lazy {
        CovidApiService.getBigDataCloudApiService()
    }

    override suspend fun fetchHomeDetails(): Covid19AllData {
        return covid19Service.getBaseData()
    }

    override suspend fun fetchEssentialData(): EssentialResourcesData {
        return covid19Service.getEssentials()
    }

}