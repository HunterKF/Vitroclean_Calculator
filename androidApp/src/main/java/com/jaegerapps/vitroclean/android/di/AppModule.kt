package com.jaegerapps.vitroclean.android.di

import com.jaegerapps.vitroclean.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.vitroclean.shared.data.remote.HttpClientFactory
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetOnboarding
import com.jaegerapps.vitroclean.shared.domain.use_cases.ToggleOnboarding
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
    @Provides
    @Singleton
    fun provideGetOnboarding(repo: TrivitroSupabaseRepo): GetOnboarding {
        return GetOnboarding(repo)
    }
    @Provides
    @Singleton
    fun provideToggleOnboarding(repo: TrivitroSupabaseRepo): ToggleOnboarding {
        return ToggleOnboarding(repo)
    }
}