package com.jaegerapps.vitroclean.di

import com.jaegerapps.vitroclean.shared.data.local.LocalDataSource
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSource
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetOnboarding
import com.jaegerapps.vitroclean.shared.domain.use_cases.ToggleOnboarding
import com.jaegerapps.vitroclean.testing.FakeLocalDataSource
import com.jaegerapps.vitroclean.testing.FakeRemoteDataSource
import com.jaegerapps.vitroclean.testing.FakeSupabaseRepo

class TestAppModule: AppModule {
    override val localDataSource: LocalDataSource = FakeLocalDataSource()
    override val remoteDataSource: RemoteDataSource = FakeRemoteDataSource()
    override val repo: VitrocleanRepo = FakeSupabaseRepo()

    override val getFilters: GetFilters = GetFilters(repo)
    override val getFaqs: GetFaqs = GetFaqs(repo)
    override val getOnboarding: GetOnboarding = GetOnboarding(repo)
    override val toggleOnboarding: ToggleOnboarding = ToggleOnboarding(repo)
}
