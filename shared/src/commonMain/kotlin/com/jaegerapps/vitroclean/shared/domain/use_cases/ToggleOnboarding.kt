package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class ToggleOnboarding(
    private val repo: TrivitroSupabaseRepo,
) {
    suspend operator fun invoke() {
        val settings = Settings()
        val currentValue = settings.getBooleanOrNull("show_onboarding")
        println("Here is the current value of showOnboarding: $currentValue")
        repo.toggleOnboarding()
    }
}