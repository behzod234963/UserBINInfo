package com.mr.anonym.userbininfo.di.module

import com.mr.anonym.data.implementation.LocalRepositoryImpl
import com.mr.anonym.data.implementation.RemoteRepositoryImpl
import com.mr.anonym.data.local.BINDao
import com.mr.anonym.data.remote.ApiService
import com.mr.anonym.domain.repository.RemoteRepository
import com.mr.anonym.domain.repository.LocalRepository
import com.mr.anonym.domain.useCases.local.ClearBINUseCase
import com.mr.anonym.domain.useCases.local.ClearSearchHistory
import com.mr.anonym.domain.useCases.local.DeleteBINUseCase
import com.mr.anonym.domain.useCases.local.GetAllBINUseCase
import com.mr.anonym.domain.useCases.local.GetAllHistoryUseCase
import com.mr.anonym.domain.useCases.local.InsertBINUseCase
import com.mr.anonym.domain.useCases.local.InsertSearchHistoryUseCase
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.GetByBINUseCase
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideRemoteUseCases(repository: RemoteRepository) = RemoteUseCases(
        getByBinUseCase = GetByBINUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideLocalUseCases(repository: LocalRepository) = LocalUseCases(
        insertBIN = InsertBINUseCase(repository),
        getAllBIN = GetAllBINUseCase(repository),
        deleteBIN = DeleteBINUseCase(repository),
        clearBIN = ClearBINUseCase(repository),
        insertSearchHistory = InsertSearchHistoryUseCase(repository),
        getAllSearchHistory = GetAllHistoryUseCase(repository),
        clearSearchHistory = ClearSearchHistory(repository)
    )

    @Provides
    @Singleton
    fun provideLocalRepository(dao:BINDao):LocalRepository =
        LocalRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideRemoteRepository(api:ApiService):RemoteRepository =
        RemoteRepositoryImpl(api)
}