package com.jaegerapps.vitroclean.shared.data.local

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.russhwolf.settings.Settings

class LocalDataSourceImpl: LocalDataSource {
    private val settings = Settings()
    override suspend fun getOnBoardingFromLocal(): Resource<Boolean> {
        val showOnboarding = settings.getBooleanOrNull("show_onboarding")
        return Resource.Success(showOnboarding ?: true)

    }

    override suspend fun turnOnboardingFalseLocal() {
        settings.putBoolean("show_onboarding", false)
    }
}