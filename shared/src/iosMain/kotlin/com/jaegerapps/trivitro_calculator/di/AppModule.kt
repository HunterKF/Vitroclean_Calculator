package com.jaegerapps.trivitro_calculator.di

import com.jaegerapps.trivitro_calculator.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.trivitro_calculator.shared.data.remote.HttpClientFactory
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters

interface AppModule {
    val repo: TrivitroSupabaseRepo
    val getFilters: GetFilters
    val getFaqs: GetFaqs
}
class AppModuleImpl: AppModule {

    override val repo: TrivitroSupabaseRepo by lazy {
        SupabaseKtroRepoImpl(HttpClientFactory().create())
    }

    override val getFilters by lazy {
        GetFilters(repo)
    }

    override val getFaqs by lazy {
        GetFaqs(repo)
    }

}