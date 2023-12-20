package com.jaegerapps.trivitro_calculator.di

import com.jaegerapps.trivitro_calculator.shared.data.SupabaseKtroRepoImpl
import com.jaegerapps.trivitro_calculator.shared.data.remote.HttpClientFactory
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters

class AppModule {

    val repo: TrivitroSupabaseRepo by lazy {
        SupabaseKtroRepoImpl(HttpClientFactory().create())
    }

    val getFilters by lazy {
        GetFilters(repo)
    }

    val getFaqs by lazy {
        GetFaqs(repo)
    }

}