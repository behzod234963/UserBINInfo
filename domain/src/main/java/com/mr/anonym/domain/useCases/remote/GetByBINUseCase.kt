package com.mr.anonym.domain.useCases.remote

import android.util.Log
import com.mr.anonym.domain.model.BINModel
import com.mr.anonym.domain.repository.RemoteRepository
import com.mr.anonym.domain.utils.Resource
import retrofit2.Response
import kotlin.math.log

class GetByBINUseCase(private val repository: RemoteRepository) {

    suspend fun execute(bin:String):BINModel?{
        return repository.getByBIN(bin).body().let {
            if (it != null){
                Log.d("NetworkLogging", "execute: $it")
                return@let it
            }else{
                return null
            }
        }
    }
}