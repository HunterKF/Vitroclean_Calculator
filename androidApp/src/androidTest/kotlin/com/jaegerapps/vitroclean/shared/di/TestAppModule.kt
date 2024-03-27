package com.jaegerapps.vitroclean.shared.di

import com.jaegerapps.vitroclean.shared.data.FakeSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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