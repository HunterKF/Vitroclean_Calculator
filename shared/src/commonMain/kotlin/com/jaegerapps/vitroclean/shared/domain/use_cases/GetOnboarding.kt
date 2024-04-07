package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo

class GetOnboarding(
    private val repo: VitrocleanRepo
) {
    suspend operator fun invoke(): Resource<Boolean> {
        return repo.getOnBoarding()
    }
}