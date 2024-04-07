package com.jaegerapps.vitroclean.shared.di

import com.jaegerapps.vitroclean.shared.data.FakeSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetOnboarding
import com.jaegerapps.vitroclean.shared.domain.use_cases.ToggleOnboarding
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
    fun provideKtorClient(): VitrocleanRepo {
        return FakeSupabaseRepo()
    }

    @Provides
    @Singleton
    fun provideGetFilters(repo: VitrocleanRepo): GetFilters {
        return GetFilters(repo)
    }

    @Provides
    @Singleton
    fun provideGetFaqs(repo: VitrocleanRepo): GetFaqs {
        return GetFaqs(repo)
    }
    @Provides
    @Singleton
    fun provideGetOnboarding(repo: VitrocleanRepo): GetOnboarding {
        return GetOnboarding(repo)
    }
    @Provides
    @Singleton
    fun provideToggleOnboarding(repo: VitrocleanRepo): ToggleOnboarding {
        return ToggleOnboarding(repo)
    }
}