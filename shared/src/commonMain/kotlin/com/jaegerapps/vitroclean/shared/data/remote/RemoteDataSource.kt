package com.jaegerapps.vitroclean.shared.data.remote

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.data.remote.dtos.FaqDto
import com.jaegerapps.vitroclean.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

interface RemoteDataSource {
    suspend fun getFiltersFromDb(): Resource<List<PoolFilterDto>>
    suspend fun getFaqsFromDb(): Resource<List<FaqDto>>
}