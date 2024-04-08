package com.jaegerapps.vitroclean.testing

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.data.local.LocalDataSource

class FakeLocalDataSource: LocalDataSource {
    override suspend fun getOnBoardingFromLocal(): Resource<Boolean> {
        return Resource.Success(true)
    }

    override suspend fun turnOnboardingFalseLocal() {
        println("turnOnboardingFalseLocal has been called")
    }
}