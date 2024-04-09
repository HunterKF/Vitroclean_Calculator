package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo

class GetFilters(
    private val repo: VitrocleanRepo
) {
    suspend operator fun invoke(): Resource<List<PoolFilter>> {

        val filters = repo.getFilters()
        if (!filters.data.isNullOrEmpty()) {
            Resource.Success(filters.data)
        } else {
            Resource.Error(filters.networkError!!)
        }
        println("Attempting to get filters: $filters")
        return filters

    }
}