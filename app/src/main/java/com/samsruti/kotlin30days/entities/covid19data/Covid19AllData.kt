/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.entities.covid19data

import com.google.gson.annotations.SerializedName

data class Covid19AllData (

    @SerializedName("cases_time_series") val casesTimeSeries: List<CasesTimeSeries>,
    @SerializedName("statewise") val statewise : List<Statewise>
)