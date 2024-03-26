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
                    _state.update {
                        it.copy(
                            isChoosingFilter = !it.isChoosingFilter,
                            isChoosingManufacturer = false
                        )
                    }
                }
            }

            is CalculateUiEvent.OnNumberChange -> {
                if (event.value.isNotBlank()) {
                    val value = event.value.filter { it.isDigit() }.take(7)
                    when (state.value.mode) {
                        CalculatorMode.BY_CUBIC_FEET -> {
                            _state.update {
                                it.copy(
                                    input = if (!value.isNullOrBlank()) value else "0",
                                    selectedFilter = if (value != "") CalculatorFunctions.createStatsByCubicFeet(
                                        value.toInt()
                                    ) else null
                                )
                            }
                        }
                        CalculatorMode.BY_SAND -> {
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

                        }
                    }
                } else {
                    _state.update {
                        it.copy(
                            input = "",
                            selectedFilter = null
                        )
                    }
                }
            }
            CalculateUiEvent.OnNavigate -> {

            }
            is CalculateUiEvent.ChangeMode -> {
                println(event.mode)
                _state.update {
                    it.copy(
                        mode = returnModeFromString(event.mode)
                    )
                }

            }
            is CalculateUiEvent.AddList -> {
                _state.update {
                    it.copy(
                        poolFilterList = event.list
                    )
                }

            }
        }
    }

    private fun returnModeFromString(mode: String): CalculatorMode {
        println("Changing mode")
        return when (mode) {
            "by_filter" -> CalculatorMode.BY_FILTER
            "by_cubic_feet" -> CalculatorMode.BY_CUBIC_FEET
            "by_sand" -> CalculatorMode.BY_SAND
            else -> CalculatorMode.BY_FILTER
        }
    }
}

