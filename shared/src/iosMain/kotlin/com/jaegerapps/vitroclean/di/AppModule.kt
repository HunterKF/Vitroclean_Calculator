package com.jaegerapps.vitroclean.di

import com.jaegerapps.vitroclean.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.vitroclean.shared.data.remote.HttpClientFactory
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetOnboarding
import com.jaegerapps.vitroclean.shared.domain.use_cases.ToggleOnboarding

interface AppModule {
    val repo: TrivitroSupabaseRepo
    val getFilters: GetFilters
    val getFaqs: GetFaqs
    val getOnboarding: GetOnboarding
    val toggleOnboarding: ToggleOnboarding
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
    override val getOnboarding: GetOnboarding by lazy {
        GetOnboarding(repo)
    }
    override val toggleOnboarding: ToggleOnboarding by lazy {
        ToggleOnboarding(repo)
    }

}