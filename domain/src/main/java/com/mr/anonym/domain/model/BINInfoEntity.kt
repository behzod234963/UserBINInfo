package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("bin_info")
data class BINInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val binNumber:String = "",
    val schema:String,
    val type:String,
    val brand:String = "",
    val countryName:String,
    val numeric:String,
    val alpha2:String,
    val emoji:String,
    val currency:String,
    val latitude:Int,
    val longitude:Int,
    val bankName:String = ""
)
