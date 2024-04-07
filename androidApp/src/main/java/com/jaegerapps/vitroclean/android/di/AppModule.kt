package com.jaegerapps.vitroclean.android.di

import com.jaegerapps.vitroclean.shared.data.local.LocalDataSource
import com.jaegerapps.vitroclean.shared.data.local.LocalDataSourceImpl
import com.jaegerapps.vitroclean.shared.data.repo.VitrocleanRepoImpl
import com.jaegerapps.vitroclean.shared.data.remote.HttpClientFactory
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSource
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSourceImpl
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
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
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }
    @Provides
    @Singleton
    fun provideRemoteDataSource(client: HttpClient): RemoteDataSource {
        return RemoteDataSourceImpl(client)
    }


    @Provides
    @Singleton
    fun provideKtorClient(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): VitrocleanRepo {
        return VitrocleanRepoImpl(
            remoteDataSource, localDataSource
        )
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