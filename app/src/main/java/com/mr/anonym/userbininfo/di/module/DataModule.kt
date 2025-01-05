package com.mr.anonym.userbininfo.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mr.anonym.data.local.BINDao
import com.mr.anonym.data.local.DataStoreInstance
import com.mr.anonym.data.local.RoomInstance
import com.mr.anonym.data.remote.ApiService
import com.mr.anonym.userbininfo.presentation.utils.BASE_URL
import com.mr.anonym.userbininfo.presentation.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context:Context):DataStoreInstance = DataStoreInstance(context)

    @Provides
    @Singleton
    fun provideRoomInstance(application: Application):RoomInstance =
        Room.databaseBuilder(
            application,
            RoomInstance::class.java,
            DATABASE_NAME,
        ).build()
    @Provides
    @Singleton
    fun provideDao(room:RoomInstance):BINDao = room.dao

}