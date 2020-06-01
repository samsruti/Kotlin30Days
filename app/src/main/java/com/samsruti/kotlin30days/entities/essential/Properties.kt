/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.entities.essential

import com.google.gson.annotations.SerializedName

data class Properties (

	@SerializedName("recordid") val recordid : Int,
	@SerializedName("name") val name : String,
	@SerializedName("desc") val desc : String,
	@SerializedName("geoTag") val geoTag : String,
	@SerializedName("addr") val addr : String,
	@SerializedName("state") val state : String,
	@SerializedName("phone") val phone : String,
	@SerializedName("contact") val contact : String,
	@SerializedName("priority") val priority : Int,
	@SerializedName("icon") val icon : String
)