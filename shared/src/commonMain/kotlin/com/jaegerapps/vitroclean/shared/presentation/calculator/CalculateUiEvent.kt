package com.jaegerapps.vitroclean.shared.presentation.calculator

import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter


sealed class CalculateUiEvent {
    data class OnNumberChange(val value: String): CalculateUiEvent()
    data object OnNavigate: CalculateUiEvent()
    data class SelectFilter(val filter: String): CalculateUiEvent()
    data object ToggleManufacturerDropdown: CalculateUiEvent()
    data class SelectManufacturer(val text: String): CalculateUiEvent()
    data object ToggleFilterDropdown: CalculateUiEvent()
    data class ChangeMode(val mode: String): CalculateUiEvent()
    data class AddList(val list: List<PoolFilter>): CalculateUiEvent()
}
