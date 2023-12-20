package com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen.components.*
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculateUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculatorMode
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculatorState
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

@Composable
fun CalculatorScreen(
    calculatorState: CalculatorState,
    onCalculatorEvent: (CalculateUiEvent) -> Unit,
) {

    Surface(color = MaterialTheme.colors.background) {
        when (calculatorState.mode) {
            CalculatorMode.BY_FILTER -> {
                ByFilterScreen(
                    poolFilterList = calculatorState.poolFilterList,
                    isChoosingManufacturer = calculatorState.isChoosingManufacturer,
                    isChoosingFilter = calculatorState.isChoosingFilter,
                    manufacturerText = calculatorState.manufacturerText,
                    filterText = calculatorState.filterText,
                    selectedFilter = calculatorState.selectedFilter,
                    onCalculatorEvent = {
                        onCalculatorEvent(it)
                    },
                )
            }

            CalculatorMode.BY_CUBIC_FEET -> {
                ByNumber(
                    displayText = calculatorState.input,
                    subtext = stringResource(R.string.subtext_cubic_feet),
                    selectedFilter = calculatorState.selectedFilter,
                    onCalculatorEvent = { onCalculatorEvent(it) }
                )
            }
            CalculatorMode.BY_SAND -> {
                ByNumber(
                    displayText = calculatorState.input,
                    subtext = stringResource(R.string.subtext_sand_needed),
                    selectedFilter = calculatorState.selectedFilter,
                    onCalculatorEvent = { onCalculatorEvent(it) }
                )
            }
        }
    }
}

@Composable
fun ByFilterScreen(
    poolFilterList: List<PoolFilter>,
    selectedFilter: PoolFilter? = null,
    manufacturerText: String? = null,
    filterText: String? = null,
    isChoosingFilter: Boolean = false,
    isChoosingManufacturer: Boolean = false,
    onCalculatorEvent: (CalculateUiEvent) -> Unit,
) {
    Column {
        TopBarCalc(
            onBackClick = { onCalculatorEvent(CalculateUiEvent.OnNavigate) },
            modifier = Modifier.shadow(5.dp)
        ) {
            DropDownBox(
                list = poolFilterList.map { it.manufacturer }.distinct(),
                currentItem = manufacturerText,
                defaultText = stringResource(R.string.dropdown_select_manufacturer),
                onEvent = {
                    onCalculatorEvent(CalculateUiEvent.SelectManufacturer(it))
                },
                onToggleEvent = { onCalculatorEvent(CalculateUiEvent.ToggleManufacturerDropdown) },
                modifier = Modifier.fillMaxWidth(0.9f),
                showDropDown = isChoosingManufacturer
            )
            Spacer(Modifier.height(12.dp))
            DropDownBox(
                list = poolFilterList.filter { it.manufacturer == manufacturerText }
                    .map { it.model },
                currentItem = filterText,
                defaultText = stringResource(R.string.dropdown_select_model),
                onEvent = { filterString ->
                    onCalculatorEvent(CalculateUiEvent.SelectFilter(filterString))
                },
                onToggleEvent = { onCalculatorEvent(CalculateUiEvent.ToggleFilterDropdown) },
                modifier = Modifier.fillMaxWidth(0.9f),
                showDropDown = isChoosingFilter
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedFilter != null) {
                StatDisplayColumn(selectedFilter)
            } else {
                EmptyPrompt(
                    text = "Start calculating",
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_calculator
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ByFilterScreenPreview() {
    val selectedFilter = PoolFilter("Pentair", "TR40", 175, 140, 0, 0, 3)
    val exampleList = listOf(
        PoolFilter("Pentair", "TR40", 175, 140, 0, 0, 3),
        PoolFilter("Pentair", "TR50", 225, 180, 0, 0, 4),
        PoolFilter("Pentair", "TR60", 325, 260, 182, 78, 4),
        PoolFilter("Pentair", "TR60 ClearPro", 325, 260, 182, 78, 4),
        PoolFilter("Pentair", "TR100", 600, 480, 336, 144, 7),
        PoolFilter("Pentair", "TR100HD", 600, 480, 336, 144, 7),
        PoolFilter("Pentair", "TR100C-3", 600, 480, 336, 144, 7),
        PoolFilter("Pentair", "TR-140", 925, 740, 518, 222, 11),
        PoolFilter("Pentair", "TR140C-3", 925, 740, 518, 222, 11),
        PoolFilter("Hayward", "S144T", 50, 40, 40, 0, 1),
        PoolFilter("Hayward", "S160T", 150, 120, 120, 0, 3),
        PoolFilter("Waterway Carefree Top-Mount", "FS02629-B (26\")", 300, 240, 168, 72, 4)
    )
    TrivitroTheme {
        ByFilterScreen(
            poolFilterList = exampleList,
            isChoosingManufacturer = false,
            isChoosingFilter = true,
            manufacturerText = "Pentair",
            filterText = "TR40",
            selectedFilter = null,
            onCalculatorEvent = {},
        )
    }
}

@Composable
fun ByNumber(
    displayText: String,
    subtext: String,
    selectedFilter: PoolFilter?,
    onCalculatorEvent: (CalculateUiEvent) -> Unit,
) {
    //ByNumber is used for all number cases. Inside the ViewModel, we have access to the mode
    //So in the ViewModel, we do calculations based on the mode, not here.
    Column {
        TopBarCalc(
            onBackClick = { onCalculatorEvent(CalculateUiEvent.OnNavigate) },
            modifier = Modifier.shadow(5.dp)
        ) {
            NumberDisplay(
                text = displayText,
                subtext = subtext,
                modifier = Modifier.fillMaxWidth(0.9f),
                onClick = { onCalculatorEvent(it) })
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedFilter != null) {
                StatDisplayColumn(selectedFilter)
            } else {
                EmptyPrompt(
                    text = stringResource(R.string.prompt_start_calculating),
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_calculator
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ByNumberPreview() {
    TrivitroTheme() {
        Surface(color = MaterialTheme.colors.surface) {
            ByNumber(
                displayText = "0",
                subtext = "sand needed",
                selectedFilter = null,
                onCalculatorEvent = {})
        }

    }
}


