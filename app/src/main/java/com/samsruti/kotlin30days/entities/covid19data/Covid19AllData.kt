package com.samsruti.kotlin30days.entities.covid19data

import com.google.gson.annotations.SerializedName
import com.samsruti.kotlin30days.entities.covid19data.CasesTimeSeries
import com.samsruti.kotlin30days.entities.covid19data.Statewise

data class Covid19AllData (

    @SerializedName("cases_time_series") val casesTimeSeries: List<CasesTimeSeries>,
    @SerializedName("statewise") val statewise : List<Statewise>
)