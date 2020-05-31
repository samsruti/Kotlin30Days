package com.samsruti.kotlin30days.entities.covid19data

import com.google.gson.annotations.SerializedName

data class Statewise (

	@SerializedName("active") val active : Int,
	@SerializedName("confirmed") val confirmed : Int,
	@SerializedName("deaths") val deaths : Int,
	@SerializedName("deltaconfirmed") val deltaConfirmed : Int,
	@SerializedName("deltadeaths") val deltaDeaths : Int,
	@SerializedName("deltarecovered") val deltaRecovered : Int,
	@SerializedName("lastupdatedtime") val lastUpdatedTime : String,
	@SerializedName("recovered") val recovered : Int,
	@SerializedName("state") val state : String,
	@SerializedName("statecode") val stateCode : String,
	@SerializedName("statenotes") val stateNotes : String
)