package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

class GetOnboarding(
    private val repo: TrivitroSupabaseRepo
) {
    suspend operator fun invoke(): Resource<Boolean> {

        return try {
            val filters = repo.getOnBoarding()
            if (filters.data != null) {
                Resource.Success(filters.data)
            } else {
                Resource.Error(Throwable())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println(e)
            Resource.Error(throwable = e)
        }
    }
}