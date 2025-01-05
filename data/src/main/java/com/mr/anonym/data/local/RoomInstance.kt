package com.mr.anonym.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.SearchHistoryEntity

@Database(entities = [BINInfoEntity::class,SearchHistoryEntity::class], version = 1)
abstract class RoomInstance :RoomDatabase(){

    abstract val dao:BINDao
}