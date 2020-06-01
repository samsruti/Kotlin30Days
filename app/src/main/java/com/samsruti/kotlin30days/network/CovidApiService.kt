/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.network

import com.samsruti.kotlin30days.constants.CommonConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CovidApiService {
    fun getCovid19ApiService(): Covid19Api =
        Retrofit.Builder()
            .baseUrl(CommonConstants.URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Covid19Api::class.java)

    fun getBigDataCloudApiService(): BigDataCloudApi =
        Retrofit.Builder()
            .baseUrl(CommonConstants.URL.BIG_DATA_CLOUD)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BigDataCloudApi::class.java)
}