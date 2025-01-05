package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.domain.repository.LocalRepository

class InsertSearchHistoryUseCase(private val repository: LocalRepository) {

    suspend fun execute(history:SearchHistoryEntity){
        repository.insertSearchHistory(history)
    }
}