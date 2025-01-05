package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.repository.LocalRepository

class ClearBINUseCase(private val repository: LocalRepository) {

    suspend fun execute(bins:List<BINInfoEntity>){
        repository.clearBins(bins)
    }
}