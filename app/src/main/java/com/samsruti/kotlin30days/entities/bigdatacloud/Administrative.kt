/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.entities.bigdatacloud

import com.google.gson.annotations.SerializedName

data class Administrative (

	@SerializedName("order") val order : Int,
	@SerializedName("adminLevel") val adminLevel : Int,
	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String,
	@SerializedName("isoName") val isoName : String,
	@SerializedName("isoCode") val isoCode : String,
	@SerializedName("wikidataId") val wikidataId : String
)