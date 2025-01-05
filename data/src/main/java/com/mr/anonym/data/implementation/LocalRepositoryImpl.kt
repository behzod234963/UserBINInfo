package com.mr.anonym.data.implementation

import com.mr.anonym.data.local.BINDao
import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(private val dao:BINDao):LocalRepository {
    override suspend fun insertBIN(bin: BINInfoEntity) {
        dao.insertBIN(bin)
    }

    override fun getAllBINData(): Flow<List<BINInfoEntity>> =
        dao.getAllBINData()

    override suspend fun deleteBin(bin: BINInfoEntity) {
        dao.deleteBin(bin)
    }

    override suspend fun clearBins(bins: List<BINInfoEntity>) {
        dao.clearBins(bins)
    }

    override suspend fun insertSearchHistory(history: SearchHistoryEntity) {
        dao.insertSearchHistory(history)
    }

    override fun getAllSearchedHistory(): Flow<List<SearchHistoryEntity>> = dao.getAllSearchedHistory()

    override suspend fun clearSearchHistory(histories:List<SearchHistoryEntity>) {
        dao.clearSearchHistory(histories)
    }
}