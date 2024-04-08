package com.jaegerapps.vitroclean.shared.presentation.calculator

import com.jaegerapps.vitroclean.core.domain.util.toCommonStateFlow
import com.jaegerapps.vitroclean.shared.domain.use_cases.CalculatorFunctions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel {
    private val _state = MutableStateFlow(CalculatorState())

    val state = _state.toCommonStateFlow()


    fun onEvent(event: CalculateUiEvent) {
        when (event) {
            CalculateUiEvent.ToggleManufacturerDropdown -> {
                //opens drop down, nullifies selected filter and text, closes the filter drop down
                _state.update {
                    it.copy(
                        isChoosingManufacturer = !it.isChoosingManufacturer,
                        isChoosingFilter = false,
                        selectedFilter = null,
                        filterText = null
                    )
                }
            }
            is CalculateUiEvent.SelectManufacturer -> {
                //selects a manufacturer. manufacturerText is a text to display on the UI and then will filter the filters based on the text
                _state.update {
                    it.copy(
                        manufacturerText = event.text,
                        isChoosingManufacturer = false,
                        selectedFilter = null,
                        filterText = null
                    )
                }
            }

            is CalculateUiEvent.SelectFilter -> {
                //takes a filter name as a string, then gets the first pool filter that matches the filter name string.
                //selectedFilter is a PoolFilter that is used to make the calculations
                //filterText is for displaying the filter name in the dropdown box
                _state.update {
                    it.copy(
                        selectedFilter = state.value.poolFilterList.first { poolFilter -> poolFilter.model == event.filter },
                        filterText = event.filter,
                        isChoosingFilter = false,
                        isChoosingManufacturer = false
                    )
                }
            }
            CalculateUiEvent.ToggleFilterDropdown -> {
                if (!state.value.manufacturerText.isNullOrEmpty()) {
                    //Only can be opened if the manufacturer has been selected
                    //Natively, it will have a snackbar if it is empty
                    _state.update {
                        it.copy(
                            isChoosingFilter = !it.isChoosingFilter,
                            isChoosingManufacturer = false
                        )
                    }
                }
            }

            is CalculateUiEvent.OnNumberChange -> {
                //Happens in cubic feet mode and sand needed mode
                //First check to make sure value exists, on iOS this will crash w/o this.
                if (event.value.isNotBlank()) {
                    //ensure that it is only digits and we only take 7 characters
                    val value = event.value.filter { it.isDigit() }.take(7)
                    when (state.value.mode) {
                        CalculatorMode.BY_CUBIC_FEET -> {
                            //Have to add a default value, on iOS it threw a lot of NullPointExceptions, so this is the solution.
                            //If blank, reset the selected filter back to null. We are use a selectedFilter to display the stats
                            _state.update {
                                it.copy(
                                    input = value.ifBlank { "0" },
                                    selectedFilter = if (value != "") CalculatorFunctions.createStatsByCubicFeet(
                                        value.toInt()
                                    ) else null
                                )
                            }
                        }
                        CalculatorMode.BY_SAND -> {
                            //Have to add a default value, on iOS it threw a lot of NullPointExceptions, so this is the solution.
                            //If blank, reset the selected filter back to null. We are use a selectedFilter to display the stats
                            _state.update {
                                it.copy(
                                    input = if (!value.isNullOrBlank()) value else "0",
                                    selectedFilter = if (value != "") CalculatorFunctions.createStatsBySandNeeded(
                                        value.toInt()
                                    ) else null
                                )
                            }
                        }
                        else -> {
                            println("This can't happen")
                        }
                    }
                } else {
                    //If it is blank, I got a lot of NullPointExceptions when the input was null, so if the input is blank, we reset it back to an empty string
                    _state.update {
                        it.copy(
                            input = "",
                            selectedFilter = null
                        )
                    }
                }
            }
            CalculateUiEvent.OnNavigate -> {
                //Just for navigating back. Handled natively
                println("Navigating back}")
            }
            is CalculateUiEvent.ChangeMode -> {
                //When the screen is navigated to, we change the mode here.
                _state.update {
                    it.copy(
                        mode = returnModeFromString(event.mode)
                    )
                }

            }
            is CalculateUiEvent.AddList -> {
                //Add the list of filters from the SharedVM to the CalculatorVM
                _state.update {
                    it.copy(
                        poolFilterList = event.list
                    )
                }

            }
        }
    }

}

