package com.jaegerapps.vitroclean.di

import com.jaegerapps.vitroclean.shared.data.local.LocalDataSource
import com.jaegerapps.vitroclean.shared.data.local.LocalDataSourceImpl
import com.jaegerapps.vitroclean.shared.data.repo.VitrocleanRepoImpl
import com.jaegerapps.vitroclean.shared.data.remote.HttpClientFactory
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSource
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSourceImpl
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetOnboarding
import com.jaegerapps.vitroclean.shared.domain.use_cases.ToggleOnboarding

interface AppModule {
    val localDataSource: LocalDataSource
    val remoteDataSource: RemoteDataSource
    val repo: VitrocleanRepo
    val getFilters: GetFilters
    val getFaqs: GetFaqs
    val getOnboarding: GetOnboarding
    val toggleOnboarding: ToggleOnboarding
}
class AppModuleImpl: AppModule {
    override val localDataSource: LocalDataSource by lazy {
        LocalDataSourceImpl()
    }
    override val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSourceImpl(HttpClientFactory().create())
    }

    override val repo: VitrocleanRepo by lazy {
        VitrocleanRepoImpl(
            remoteDataSource, localDataSource
        )
    }


    override val getFilters by lazy {
        GetFilters(repo)
    }

    override val getFaqs by lazy {
        GetFaqs(repo)
    }
    override val getOnboarding: GetOnboarding by lazy {
        GetOnboarding(repo)
    }
    override val toggleOnboarding: ToggleOnboarding by lazy {
        ToggleOnboarding(repo)
    }

}