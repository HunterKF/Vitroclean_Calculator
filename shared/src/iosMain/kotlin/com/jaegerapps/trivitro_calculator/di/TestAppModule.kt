package com.jaegerapps.trivitro_calculator.di

import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import com.jaegerapps.trivitro_calculator.testing.FakeSupabaseRepo

class TestAppModule: AppModule {
    override val repo: TrivitroSupabaseRepo = FakeSupabaseRepo()
    override val getFilters: GetFilters = GetFilters(repo)
    override val getFaqs: GetFaqs = GetFaqs(repo)
}