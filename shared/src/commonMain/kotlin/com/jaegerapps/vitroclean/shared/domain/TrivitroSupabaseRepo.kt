package com.jaegerapps.vitroclean.shared.domain

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

interface TrivitroSupabaseRepo {
    suspend fun getFilters(): Resource<List<PoolFilter>>
    suspend fun getFaqs(): Resource<List<Faq>>
}