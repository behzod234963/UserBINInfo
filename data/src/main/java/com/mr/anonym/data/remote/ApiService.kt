package com.mr.anonym.data.remote

import com.mr.anonym.domain.model.BINModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{bin}")
    suspend fun getByBIN(@Path("bin")bin:String):Response<BINModel>
}