package com.mr.anonym.domain.repository

import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

//    Bin info dao
    suspend fun insertBIN(bin: BINInfoEntity)

    fun getAllBINData(): Flow<List<BINInfoEntity>>

    suspend fun deleteBin(bin: BINInfoEntity)

    suspend fun clearBins(bins:List<BINInfoEntity>)

    //  Search History Dao
    suspend fun insertSearchHistory(history: SearchHistoryEntity)

    fun getAllSearchedHistory(): Flow<List<SearchHistoryEntity>>

    suspend fun clearSearchHistory(histories:List<SearchHistoryEntity>)
}