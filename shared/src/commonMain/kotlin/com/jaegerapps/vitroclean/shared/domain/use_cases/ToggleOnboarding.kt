package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo

class ToggleOnboarding(
    private val repo: VitrocleanRepo,
) {
    suspend operator fun invoke() {
        repo.toggleOnboarding()
    }
}