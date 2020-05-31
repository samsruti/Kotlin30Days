package com.samsruti.kotlin30days.entities.bigdatacloud

import com.google.gson.annotations.SerializedName

data class Informative (

	@SerializedName("order") val order : Int,
	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String,
	@SerializedName("isoCode") val isoCode : String,
	@SerializedName("wikidataId") val wikidataId : String
)