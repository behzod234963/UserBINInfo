package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.domain.repository.LocalRepository

class ClearSearchHistory(private val repository: LocalRepository) {

    suspend fun execute(histories:List<SearchHistoryEntity>){
        repository.clearSearchHistory(histories)
    }
}