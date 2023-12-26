package com.jagerapps.trivitro_calculator.shared.presentation.calculator

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculateUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculatorMode
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculatorState
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculatorViewModel
import com.jagerapps.trivitro_calculator.shared.filterExample
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @BeforeTest
    fun setup() {
        viewModel = CalculatorViewModel()
    }
    /*
    * viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
        }
    * */
    @Test
    fun `AddList - Expect state to have list`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
        }
    }
    @Test
    fun `ToggleManufacturerDropdown - filterText is null - Expect state isChoosing to toggle`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ToggleManufacturerDropdown)
            val toggledState = awaitItem()
            assertThat(toggledState.isChoosingManufacturer).isTrue()

        }
    }
    @Test
    fun `SelectFilter - select filter- Expect state isChoosing to toggle`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ToggleManufacturerDropdown)
            val toggleManufacturerState = awaitItem()
            assertThat(toggleManufacturerState.isChoosingManufacturer).isTrue()
            viewModel.onEvent(CalculateUiEvent.ToggleFilterDropdown)
            val toggleFilterState = awaitItem()
            assertThat(toggleFilterState.isChoosingFilter).isTrue()
            assertThat(toggleFilterState.isChoosingManufacturer).isFalse()

            viewModel.onEvent(CalculateUiEvent.SelectManufacturer(text = filterExample.first().manufacturer))
            val selectState = awaitItem()
            assertThat(selectState.manufacturerText).isEqualTo("Pentair")
            viewModel.onEvent(CalculateUiEvent.SelectFilter(filter = filterExample.first().model))
            val filterState = awaitItem()
            assertThat(filterState.filterText).isEqualTo("TR40")
            assertThat(filterState.selectedFilter).isEqualTo(filterExample.first())

        }
    }
    @Test
    fun `ToggleManufacturerDropdown - filterText is not null - Expect state selectedFilter to be null`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ToggleManufacturerDropdown)
            val toggleManufacturerState = awaitItem()
            assertThat(toggleManufacturerState.isChoosingManufacturer).isTrue()
            viewModel.onEvent(CalculateUiEvent.ToggleFilterDropdown)
            val toggleFilterState = awaitItem()
            assertThat(toggleFilterState.isChoosingFilter).isTrue()
            assertThat(toggleFilterState.isChoosingManufacturer).isFalse()

            viewModel.onEvent(CalculateUiEvent.SelectManufacturer(text = filterExample.first().manufacturer))
            val selectState = awaitItem()
            assertThat(selectState.manufacturerText).isEqualTo("Pentair")
            viewModel.onEvent(CalculateUiEvent.SelectFilter(filter = filterExample.first().model))
            val filterState = awaitItem()
            assertThat(filterState.filterText).isEqualTo("TR40")
            assertThat(filterState.selectedFilter).isEqualTo(filterExample.first())
            viewModel.onEvent(CalculateUiEvent.ToggleManufacturerDropdown)
            val manufacturerState = awaitItem()
            assertThat(manufacturerState.filterText).isNull()
            assertThat(manufacturerState.manufacturerText).isEqualTo("Pentair")
            assertThat(manufacturerState.selectedFilter).isNull()
        }
    }

    @Test
    fun `ChangeMode - Update state with by_sand`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_sand"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_SAND)

        }
    }
    @Test
    fun `ChangeMode - Update state with by_filter`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_filter"))
            assertThat(listState.mode).isEqualTo(CalculatorMode.BY_FILTER)
        }
    }
    @Test
    fun `ChangeMode - Update state with by_cubic_feet`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)

        }
    }
    @Test
    fun `OnNumberChange - Mode BY_SAND - Enter one - two - one and get results`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_sand"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_SAND)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("5"))
            val oneDigitState = awaitItem()
            val oneExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 0,
                recommendedSandLoad = 1,
                recommendedPebble = 2,
                fiftyBagPebble = 0,
                fiftyBagVitroclean = 12

            )
            assertThat(oneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(oneDigitState.input).isEqualTo("5")
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("50"))
            val twoDigitState = awaitItem()
            val twoExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 5,
                recommendedSandLoad = 10,
                recommendedPebble = 20,
                fiftyBagPebble = 5,
                fiftyBagVitroclean = 125

            )
            assertThat(twoDigitState.selectedFilter).isEqualTo(twoExpectedResult)
            assertThat(twoDigitState.input).isEqualTo("50")

            viewModel.onEvent(CalculateUiEvent.OnNumberChange("5"))
            val returnOneDigitState = awaitItem()
            assertThat(returnOneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(returnOneDigitState.input).isEqualTo("5")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_SAND - Enter 4 char - except taker 3`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_sand"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_SAND)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("5222"))
            val oneDigitState = awaitItem()
            val oneExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 52,
                recommendedSandLoad = 104,
                recommendedPebble = 208,
                fiftyBagPebble = 52,
                fiftyBagVitroclean = 1305

            )
            assertThat(oneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(oneDigitState.input).isEqualTo("522")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_SAND - Enter blank - Expect 0`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_sand"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_SAND)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("522"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.input).isEqualTo("522")
            viewModel.onEvent(CalculateUiEvent.OnNumberChange(""))
            val blankState = awaitItem()

            assertThat(blankState.input).isEqualTo("")
            assertThat(blankState.selectedFilter).isNull()
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_SAND - Enter letters - Expect blank`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_sand"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_SAND)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("asdasd"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.input).isEqualTo("0")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_SAND - Enter letters and numbers- Expect blank numbers`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_sand"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_SAND)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("asdasd123"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.input).isEqualTo("123")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_CUBIC_FEET - Enter one - two - one and get results`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("1"))
            val oneDigitState = awaitItem()
            val oneExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 2,
                recommendedSandLoad = 3,
                recommendedPebble = 4,
                fiftyBagPebble = 5,
                fiftyBagVitroclean = 6

            )
            assertThat(oneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(oneDigitState.input).isEqualTo("1")
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("10"))
            val twoDigitState = awaitItem()
            val twoExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 20,
                recommendedSandLoad = 30,
                recommendedPebble = 40,
                fiftyBagPebble = 50,
                fiftyBagVitroclean = 60

            )
            assertThat(twoDigitState.selectedFilter).isEqualTo(twoExpectedResult)
            assertThat(twoDigitState.input).isEqualTo("10")

            viewModel.onEvent(CalculateUiEvent.OnNumberChange("1"))
            val returnOneDigitState = awaitItem()
            assertThat(returnOneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(returnOneDigitState.input).isEqualTo("1")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_CUBIC_FEET - Enter 4 char - except taker 3`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("1001"))
            val oneDigitState = awaitItem()
            val oneExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 200,
                recommendedSandLoad = 300,
                recommendedPebble = 400,
                fiftyBagPebble = 500,
                fiftyBagVitroclean = 600

            )
            assertThat(oneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(oneDigitState.input).isEqualTo("100")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_CUBIC_FEET - Enter blank - Expect 0`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("522"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.input).isEqualTo("522")
            viewModel.onEvent(CalculateUiEvent.OnNumberChange(""))
            val blankState = awaitItem()

            assertThat(blankState.input).isEqualTo("")
            assertThat(blankState.selectedFilter).isNull()
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_CUBIC_FEET - Enter letters - Expect blank`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("asdasd"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.input).isEqualTo("0")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_CUBIC_FEET - Enter letters and numbers- Expect blank numbers`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("asdasd123"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.input).isEqualTo("123")
        }
    }
}
