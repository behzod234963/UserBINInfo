package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val history:String
)
