package com.jaegerapps.trivitro_calculator.shared.presentation.home

import com.jaegerapps.trivitro_calculator.core.domain.util.toCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel {
    private val _state = MutableStateFlow(HomeUiState())

    val state = _state.toCommonStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnNavigate -> TODO()
            HomeUiEvent.OnClick -> {
                println("On click is done")
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