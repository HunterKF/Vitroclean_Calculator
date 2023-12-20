package com.jaegerapps.trivitro_calculator.shared.domain.use_cases

import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo

class GetFilters(
    private val repo: TrivitroSupabaseRepo
) {
    suspend operator fun invoke(): Resource<List<PoolFilter>> {
        return try {
            val filters = repo.getFilters().data
            if (!filters.isNullOrEmpty()) {
                println("Success!")
                Resource.Success(filters)
            } else {
                println("There was an error! empty or null")

                Resource.Error(throwable = Throwable(message = "Pool Filter list was empty"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("There was an error!")
            Resource.Error(throwable = e)
        }
    }
}