package com.jaegerapps.vitroclean.shared.presentation.home

sealed class HomeUiEvent {
    data class OnNavigate(val route: String, val arguments: String?): HomeUiEvent()
    object OnClick: HomeUiEvent()
    object OnDismiss: HomeUiEvent()
    data class OnAccept(val navigateToLink: () -> Unit): HomeUiEvent()
}
