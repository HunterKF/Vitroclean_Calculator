package com.jaegerapps.vitroclean.shared.data.local

import com.jaegerapps.vitroclean.core.domain.util.Resource

interface LocalDataSource {
    suspend fun getOnBoardingFromLocal(): Resource<Boolean>
    suspend fun turnOnboardingFalseLocal()
}