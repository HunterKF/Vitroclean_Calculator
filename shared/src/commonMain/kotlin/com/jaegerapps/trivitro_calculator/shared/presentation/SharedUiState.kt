package com.jaegerapps.trivitro_calculator.shared.presentation

import com.jaegerapps.trivitro_calculator.shared.domain.NetworkError
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

data class SharedUiState(
    val poolFilterList: List<PoolFilter> = emptyList(),
    val faqsList: List<Faq> = emptyList(),
    val isLoading: Boolean = true,
    val loaded: Boolean = false,
    val error: NetworkError? = null
)
