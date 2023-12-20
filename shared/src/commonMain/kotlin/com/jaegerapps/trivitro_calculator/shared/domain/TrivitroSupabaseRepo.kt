package com.jaegerapps.trivitro_calculator.shared.domain

import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

interface TrivitroSupabaseRepo {
    suspend fun getFilters(): Resource<List<PoolFilter>>
    suspend fun getFaqs(): Resource<List<Faq>>
}