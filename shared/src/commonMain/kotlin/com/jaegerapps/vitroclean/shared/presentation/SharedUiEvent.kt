package com.jaegerapps.vitroclean.shared.presentation

sealed class SharedUiEvent {
    data object LoadData: SharedUiEvent()
    data object ToggleOnboarding: SharedUiEvent()
    data object OnRetry: SharedUiEvent()
}
