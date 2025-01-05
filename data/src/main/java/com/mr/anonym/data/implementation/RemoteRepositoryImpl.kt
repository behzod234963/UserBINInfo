package com.mr.anonym.data.implementation

import com.mr.anonym.data.remote.ApiService
import com.mr.anonym.domain.model.BINModel
import com.mr.anonym.domain.repository.RemoteRepository
import retrofit2.Response

class RemoteRepositoryImpl(private val api:ApiService):RemoteRepository {
    override suspend fun getByBIN(bin: String): Response<BINModel> {
        return api.getByBIN(bin)
    }
}