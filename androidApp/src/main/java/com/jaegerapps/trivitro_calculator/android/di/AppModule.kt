package com.jaegerapps.trivitro_calculator.android.di

import com.jaegerapps.trivitro_calculator.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.trivitro_calculator.shared.data.remote.HttpClientFactory
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSupabaseClient(): HttpClient {
        return HttpClientFactory().create()
    }


    @Provides
    @Singleton
    fun provideKtorClient(client: HttpClient): TrivitroSupabaseRepo {
        return SupabaseKtroRepoImpl(client)
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