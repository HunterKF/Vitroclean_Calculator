package com.jaegerapps.vitroclean.shared.presentation.calculator

import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

data class CalculatorState(
    //for sand and cubic feet
    val input: String = "",
    //for filters
    val poolFilterList: List<PoolFilter> = emptyList(),
    val selectedFilter: PoolFilter? = null,
    val manufacturerText: String? = null,
    val filterText: String? = null,
    val isChoosingFilter: Boolean = false,
    val isChoosingManufacturer: Boolean = false,
    //Used for the calculator screen, this enables us to have a single screen where it changes based on the mode
    val mode: CalculatorMode = CalculatorMode.BY_FILTER
)

enum class CalculatorMode {
    BY_FILTER,
    BY_CUBIC_FEET,
    BY_SAND
}

fun returnModeFromString(mode: String): CalculatorMode {
    //This is how we change the mode. We get a navigation argument and the convert it into a mode.
    return when (mode) {
        "by_filter" -> CalculatorMode.BY_FILTER
        "by_cubic_feet" -> CalculatorMode.BY_CUBIC_FEET
        "by_sand" -> CalculatorMode.BY_SAND
        else -> CalculatorMode.BY_FILTER
    }
}