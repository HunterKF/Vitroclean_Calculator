package com.jaegerapps.vitroclean.shared.presentation

import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

data class SharedUiState(
    val poolFilterList: List<PoolFilter> = emptyList(),
    val faqsList: List<Faq> = emptyList(),
    val isLoading: Boolean = true,
    val loaded: Boolean = false,
    val showOnboarding: Boolean = false,
    val error: NetworkError? = null
)
