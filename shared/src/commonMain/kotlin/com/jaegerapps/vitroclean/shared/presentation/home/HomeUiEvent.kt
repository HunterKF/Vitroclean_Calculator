package com.jaegerapps.vitroclean.shared.presentation.home

sealed class HomeUiEvent {
    //navigate screen
    data class OnNavigate(val route: String, val arguments: String?): HomeUiEvent()
    //guidelink click
    object OnClick: HomeUiEvent()
    //pop up button, dismiss pop up
    object OnDismiss: HomeUiEvent()
    //accept to leave app
    data class OnAccept(val navigateToLink: () -> Unit): HomeUiEvent()
}
