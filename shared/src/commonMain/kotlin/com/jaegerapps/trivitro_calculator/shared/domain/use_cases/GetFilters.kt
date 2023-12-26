package com.jaegerapps.trivitro_calculator.shared.domain.use_cases

import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo

class GetFilters(
    private val repo: TrivitroSupabaseRepo
) {
    suspend operator fun invoke(): Resource<List<PoolFilter>> {

        return try {
            val filters = repo.getFilters()
            if (!filters.data.isNullOrEmpty()) {
                Resource.Success(filters.data)
            } else {
                println("filters.throwable?.message")
                println(filters.throwable?.message)
                Resource.Error(throwable = filters.throwable!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println(e)
            Resource.Error(throwable = e)
        }
    }
}