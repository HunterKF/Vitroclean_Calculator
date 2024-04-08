package com.jaegerapps.vitroclean.shared.presentation.home

import com.jaegerapps.vitroclean.core.domain.util.toCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel {
    private val _state = MutableStateFlow(HomeUiState())

    val state = _state.toCommonStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnNavigate -> {
                //This is handled natively. We keep this hear because we can filter the event natively using a when block
                println("Navigating to ${event.route}")
            }

            HomeUiEvent.OnClick -> {
                //This is when the user clicks on the navigation image. It will open a popup asking the user if they want to leave the app
                _state.update {
                    it.copy(
                        showPopUp = true
                    )
                }
            }

            is HomeUiEvent.OnAccept -> {
                //Accepting to leave the app from the pop up
                _state.update {
                    it.copy(
                        showPopUp = false
                    )
                }
                //This will launch an intent to the browser. This is done natively, we are passing it in as a lambda function.
                event.navigateToLink()
            }

            HomeUiEvent.OnDismiss -> {
                //Dismisses the pop up, closing it
                _state.update {
                    it.copy(
                        showPopUp = false
                    )
                }
            }
        }
    }
}