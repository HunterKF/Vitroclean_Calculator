package com.jaegerapps.vitroclean.di

import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.testing.FakeSupabaseRepo

class TestAppModule: AppModule {
    override val repo: TrivitroSupabaseRepo = FakeSupabaseRepo()
    override val getFilters: GetFilters = GetFilters(repo)
    override val getFaqs: GetFaqs = GetFaqs(repo)
}