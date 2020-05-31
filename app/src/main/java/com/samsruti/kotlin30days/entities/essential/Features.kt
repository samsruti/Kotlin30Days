package com.samsruti.kotlin30days.entities.essential

import com.google.gson.annotations.SerializedName

data class Features (

	@SerializedName("type") val type : String,
	@SerializedName("properties") val properties : Properties,
	@SerializedName("geometry") val geometry : Geometry
)