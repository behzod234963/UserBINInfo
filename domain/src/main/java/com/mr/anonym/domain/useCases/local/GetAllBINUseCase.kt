package com.mr.anonym.domain.useCases.local

import androidx.lifecycle.LiveData
import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllBINUseCase(private val repository: LocalRepository) {

    fun execute(): Flow<List<BINInfoEntity>> =
        repository.getAllBINData()

}