package com.pinup.barapp.di

import com.pinup.barapp.data.remote.ApiService
import com.pinup.barapp.data.remote.RetrofitClient
import com.pinup.barapp.data.repositories.MatchRepositoryImpl
import com.pinup.barapp.domain.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetrofitClient.apiService

    @Provides
    @Singleton
    fun provideMatchRepository(api: ApiService): MatchRepository = MatchRepositoryImpl(api)
}
