package com.mr.anonym.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BINDao {

//    Bin info dao
    @Insert
    suspend fun insertBIN(bin:BINInfoEntity)

    @Query("SELECT * FROM bin_info")
    fun getAllBINData():Flow<List<BINInfoEntity>>

    @Delete
    suspend fun deleteBin(bin:BINInfoEntity)

    @Delete
    suspend fun clearBins(bins:List<BINInfoEntity>)

//  Search History Dao
    @Insert
    suspend fun insertSearchHistory(history:SearchHistoryEntity)

    @Query("SELECT * FROM SEARCH_HISTORY")
    fun getAllSearchedHistory():Flow<List<SearchHistoryEntity>>

    @Delete
    suspend fun clearSearchHistory(histories:List<SearchHistoryEntity>)
}