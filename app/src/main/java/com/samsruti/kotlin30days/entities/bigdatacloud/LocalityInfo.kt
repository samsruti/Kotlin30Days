package com.samsruti.kotlin30days.entities.bigdatacloud

import com.google.gson.annotations.SerializedName

data class LocalityInfo (
	@SerializedName("administrative") val administrative : List<Administrative>?,
	@SerializedName("informative") val informative : List<Informative>?
)