package com.jaegerapps.vitroclean.shared.presentation.home

import com.jaegerapps.vitroclean.core.domain.util.toCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel {
    private val _state = MutableStateFlow(HomeUiState())

    val state = _state.toCommonStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnNavigate -> TODO()
            HomeUiEvent.OnClick -> {
                _state.update {
                    it.copy(
                        showPopUp = true
                    )
                }
                println("State: ${state.value}")

            }
            is HomeUiEvent.OnAccept -> {
                _state.update {
                    it.copy(
                        showPopUp = false
                    )
                }
                event.navigateToLink()
            }
            HomeUiEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        showPopUp = false
                    )
                }
            }
        }
    }
}