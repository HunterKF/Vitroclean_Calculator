package com.jaegerapps.trivitro_calculator.shared.presentation

sealed class SharedUiEvent {
    data object LoadData: SharedUiEvent()
    data object OnRetry: SharedUiEvent()
}
