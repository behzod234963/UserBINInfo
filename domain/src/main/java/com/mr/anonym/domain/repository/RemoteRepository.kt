package com.mr.anonym.domain.repository

import com.mr.anonym.domain.model.BINModel
import retrofit2.Response

interface RemoteRepository {

    suspend fun getByBIN(bin:String):Response<BINModel>
}