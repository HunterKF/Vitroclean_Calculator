package com.jaegerapps.vitroclean.shared.presentation.calculator

import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter


sealed class CalculateUiEvent {
    //User inputs a new number
    data class OnNumberChange(val value: String): CalculateUiEvent()
    //navigate back, handled locally
    data object OnNavigate: CalculateUiEvent()
    //Selects a filter on filter mode
    data class SelectFilter(val filter: String): CalculateUiEvent()
    //Opens the manufacturer drop down
    data object ToggleManufacturerDropdown: CalculateUiEvent()
    //Selects a manufacturer
    data class SelectManufacturer(val text: String): CalculateUiEvent()
    //Opens the filter drop down
    data object ToggleFilterDropdown: CalculateUiEvent()
    //Changes the filter mode, takes a string. Done natively on navigate
    data class ChangeMode(val mode: String): CalculateUiEvent()
    //Takes a list from the shared vm
    data class AddList(val list: List<PoolFilter>): CalculateUiEvent()
}
