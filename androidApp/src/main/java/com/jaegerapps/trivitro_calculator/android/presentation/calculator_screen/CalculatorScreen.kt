package com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
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
import kotlinx.coroutines.launch

@Composable
fun CalculatorScreen(
    calculatorState: CalculatorState,
    onCalculatorEvent: (CalculateUiEvent) -> Unit,
) {

    Surface(color = MaterialTheme.colors.surface) {
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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        backgroundColor = MaterialTheme.colors.surface
    ) { innerPadding ->

        Column {
            TopBarCalc(
                onBackClick = { onCalculatorEvent(CalculateUiEvent.OnNavigate) },
                modifier = Modifier
                    .padding(innerPadding)
                    .shadow(5.dp)
            ) {
                DropDownBox(
                    list = poolFilterList.map { it.manufacturer }.distinct(),
                    currentItem = manufacturerText,
                    defaultText = stringResource(R.string.dropdown_select_manufacturer),
                    onEvent = {
                        onCalculatorEvent(CalculateUiEvent.SelectManufacturer(it))
                    },
                    onToggleEvent = { onCalculatorEvent(CalculateUiEvent.ToggleManufacturerDropdown) },
                    modifier = Modifier.fillMaxWidth(0.9f).testTag(stringResource(R.string.dropdown_select_manufacturer)),
                    showDropDown = isChoosingManufacturer
                )
                Spacer(Modifier.height(12.dp))
                DropDownBox(
                    list = poolFilterList.filter { it.manufacturer == manufacturerText }
                        .map { it.model },
                    currentItem = filterText,
                    defaultText = stringResource(R.string.dropdown_select_model),
                    onEvent = { filterString ->
                        if (!manufacturerText.isNullOrEmpty()) {
                            onCalculatorEvent(CalculateUiEvent.SelectFilter(filterString))
                        }
                    },
                    onToggleEvent = {
                        if (manufacturerText.isNullOrEmpty()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(context.getString(R.string.prompt_select_manufacturer_first))
                            }
                        } else {
                            onCalculatorEvent(CalculateUiEvent.ToggleFilterDropdown)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .testTag(stringResource(R.string.dropdown_select_model)),
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
                        text = stringResource(id = R.string.prompt_start_calculating),
                        icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_calculator
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ByFilterScreenPreview() {
    val selectedFilter = PoolFilter("Pentair", "TR40", 175, 140, 0, 0.0, 3.0)
    val exampleList = listOf(
        PoolFilter("Pentair", "TR40", 175, 140, 0, 0.0, 3.0),
        PoolFilter("Pentair", "TR50", 225, 180, 0, 0.0, 4.0),
        PoolFilter("Pentair", "TR60", 325, 260, 182, 78.0, 4.0),
        PoolFilter("Pentair", "TR60 ClearPro", 325, 260, 182, 78.0, 4.0),
        PoolFilter("Pentair", "TR100", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR100HD", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR100C-3", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR-140", 925, 740, 518, 222.0, 11.0),
        PoolFilter("Pentair", "TR140C-3", 925, 740, 518, 222.0, 11.0),
        PoolFilter("Hayward", "S144T", 50, 40, 40, 0.0, 1.0),
        PoolFilter("Hayward", "S160T", 150, 120, 120, 0.0, 3.0),
        PoolFilter("Waterway Carefree Top-Mount", "FS02629-B (26\")", 300, 240, 168, 72.0, 4.0)
    )
    TrivitroTheme {
        ByFilterScreen(
            poolFilterList = exampleList,
            isChoosingManufacturer = false,
            isChoosingFilter = true,
            manufacturerText = "Pentair",
            filterText = "TR40",
            selectedFilter = selectedFilter,
            onCalculatorEvent = {},
        )
    }
}
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ByFilterScreenPreview_DARK() {
    val selectedFilter = PoolFilter("Pentair", "TR60", 325, 260, 182, 78.0, 4.0)

    val exampleList = listOf(
        PoolFilter("Pentair", "TR40", 175, 140, 0, 0.0, 3.0),
        PoolFilter("Pentair", "TR50", 225, 180, 0, 0.0, 4.0),
        PoolFilter("Pentair", "TR60", 325, 260, 182, 78.0, 4.0),
        PoolFilter("Pentair", "TR60 ClearPro", 325, 260, 182, 78.0, 4.0),
        PoolFilter("Pentair", "TR100", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR100HD", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR100C-3", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR-140", 925, 740, 518, 222.0, 11.0),
        PoolFilter("Pentair", "TR140C-3", 925, 740, 518, 222.0, 11.0),
        PoolFilter("Hayward", "S144T", 50, 40, 40, 0.0, 1.0),
        PoolFilter("Hayward", "S160T", 150, 120, 120, 0.0, 3.0),
        PoolFilter("Waterway Carefree Top-Mount", "FS02629-B (26\")", 300, 240, 168, 72.0, 4.0)
    )
    TrivitroTheme {
        ByFilterScreen(
            poolFilterList = exampleList,
            isChoosingManufacturer = false,
            isChoosingFilter = true,
            manufacturerText = "Pentair",
            filterText = "TR40",
            selectedFilter = selectedFilter,
            onCalculatorEvent = {},
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ByNumber(
    displayText: String,
    subtext: String,
    selectedFilter: PoolFilter?,
    onCalculatorEvent: (CalculateUiEvent) -> Unit,
) {
    //ByNumber is used for all number cases. Inside the ViewModel, we have access to the mode
    //So in the ViewModel, we do calculations based on the mode, not here.
    val controller = LocalSoftwareKeyboardController.current

    Column {
        TopBarCalc(
            onBackClick = {
                controller?.hide()
                onCalculatorEvent(CalculateUiEvent.OnNavigate)
            },
            modifier = Modifier.shadow(5.dp)
        ) {
            NumberDisplay(
                text = displayText,
                subtext = subtext,
                modifier = Modifier.fillMaxWidth(0.9f),
                onClick = { onCalculatorEvent(it) },
                controller = controller
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

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
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ByNumberPreview_DARK() {
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


