package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllHistoryUseCase(private val repository: LocalRepository) {

    fun execute(): Flow<List<SearchHistoryEntity>> =
        repository.getAllSearchedHistory()
}