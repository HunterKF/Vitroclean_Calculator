package com.jaegerapps.trivitro_calculator.shared.di

import com.jaegerapps.trivitro_calculator.shared.data.FakeSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.trivitro_calculator.shared.data.remote.HttpClientFactory
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {


    @Provides
    @Singleton
    fun provideKtorClient(): TrivitroSupabaseRepo {
        return FakeSupabaseRepo()
    }

    @Provides
    @Singleton
    fun provideGetFilters(repo: TrivitroSupabaseRepo): GetFilters {
        return GetFilters(repo)
    }

    @Provides
    @Singleton
    fun provideGetFaqs(repo: TrivitroSupabaseRepo): GetFaqs {
        return GetFaqs(repo)
    }
}