package com.scionova.dexarmcontrol.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    fun provideApiService(
        // Potential dependencies of this type
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://0.0.0.0:50505")
            .build()
            .create(ApiService::class.java)
    }
}