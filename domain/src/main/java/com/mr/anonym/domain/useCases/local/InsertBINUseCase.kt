package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.BINInfoEntity
import com.mr.anonym.domain.repository.LocalRepository

class InsertBINUseCase(private val repository: LocalRepository) {

    suspend fun execute(bin:BINInfoEntity){
        repository.insertBIN(bin)
    }
}