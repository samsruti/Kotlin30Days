package com.samsruti.kotlin30days.entities.bigdatacloud

import com.google.gson.annotations.SerializedName

data class BigDataCloudData (

	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("localityLanguageRequested") val localityLanguageRequested : String,
	@SerializedName("continent") val continent : String,
	@SerializedName("continentCode") val continentCode : String,
	@SerializedName("countryName") val countryName : String,
	@SerializedName("countryCode") val countryCode : String,
	@SerializedName("principalSubdivision") val principalSubdivision : String,
	@SerializedName("locality") val locality : String,
	@SerializedName("localityInfo") val localityInfo : LocalityInfo?
)