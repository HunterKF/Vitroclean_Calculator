package com.jaegerapps.vitroclean.di

import com.jaegerapps.vitroclean.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.vitroclean.shared.data.remote.HttpClientFactory
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters

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