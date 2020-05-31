package com.samsruti.kotlin30days.entities.essential

import com.google.gson.annotations.SerializedName

data class Geometry (

	@SerializedName("type") val type : String,
	@SerializedName("coordinates") val coordinates : List<Double>
)