package com.samsruti.kotlin30days.entities.covid19data

import com.google.gson.annotations.SerializedName

data class CasesTimeSeries (

	@SerializedName("dailyconfirmed") val dailyConfirmed : Int,
	@SerializedName("dailydeceased") val dailyDeceased : Int,
	@SerializedName("dailyrecovered") val dailyRecovered : Int,
	@SerializedName("date") val date : String,
	@SerializedName("totalconfirmed") val totalConfirmed : Int,
	@SerializedName("totaldeceased") val totalDeceased : Int,
	@SerializedName("totalrecovered") val totalRecovered : Int
)