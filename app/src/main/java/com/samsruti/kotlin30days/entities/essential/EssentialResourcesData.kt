package com.samsruti.kotlin30days.entities.essential

import com.google.gson.annotations.SerializedName

data class EssentialResourcesData (

	@SerializedName("type") val type : String,
	@SerializedName("lastupdated") val lastupdated : String,
	@SerializedName("features") val features : List<Features>
)