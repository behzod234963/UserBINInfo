package com.mr.anonym.domain.model

import com.google.gson.annotations.SerializedName

data class BINModel(

	val country: Country,
	val bank: Bank? = null,
	val scheme: String,
	val type: String,
	val brand: String? = null
)

data class Country(
	val emoji: String,
	val latitude: Int,
	val alpha2: String,
	val name: String,
	val numeric: String,
	val currency: String,
	val longitude: Int
)
data class Bank(
	val name: String
)
