package com.jagerapps.vitroclean.shared.presentation.calculator

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter
import com.jaegerapps.vitroclean.shared.presentation.calculator.CalculateUiEvent
import com.jaegerapps.vitroclean.shared.presentation.calculator.CalculatorMode
import com.jaegerapps.vitroclean.shared.presentation.calculator.CalculatorState
import com.jaegerapps.vitroclean.shared.presentation.calculator.CalculatorViewModel
import com.jagerapps.vitroclean.shared.filterExample
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @BeforeTest
    fun setup() {
        viewModel = CalculatorViewModel()
    }
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
            //EXPECT NOTHING TO HAPPEN - Can't select filter w/o manufacturer text

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
            //Drop down the man
            viewModel.onEvent(CalculateUiEvent.ToggleManufacturerDropdown)
            val toggleManufacturerState = awaitItem()
            assertThat(toggleManufacturerState.isChoosingManufacturer).isTrue()

            viewModel.onEvent(CalculateUiEvent.ToggleFilterDropdown)
            /*EXPECT NOTHING TO HAPPEN - The state doesn't change at all, b/c you can't select the filter without selecting a manufacturer*/


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
                recommendedVitrocleanVfaLoad = 4,
                recommendedSandLoad = 5,
                recommendedPebble = 0,
                fiftyBagPebble = 0.0,
                fiftyBagVitroclean = 0.1

            )
            assertThat(oneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(oneDigitState.input).isEqualTo("5")
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("50"))
            val twoDigitState = awaitItem()
            val twoExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 40,
                recommendedSandLoad = 50,
                recommendedPebble = 0,
                fiftyBagPebble = 0.0,
                fiftyBagVitroclean = 0.8
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
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("300"))
            val oneDigitState = awaitItem()

            assertThat(240).isEqualTo(oneDigitState.selectedFilter?.recommendedVitrocleanVfaLoad) // (300 * .80)
            assertThat(300).isEqualTo(oneDigitState.selectedFilter?.recommendedSandLoad)
            assertThat(0, ).isEqualTo(oneDigitState.selectedFilter?.recommendedPebble)
            assertThat(0.0).isEqualTo(oneDigitState.selectedFilter?.fiftyBagPebble)
            assertThat(4.8).isEqualTo(oneDigitState.selectedFilter?.fiftyBagVitroclean)
            assertThat(oneDigitState.input).isEqualTo("300")
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
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("5"))
            val oneDigitState = awaitItem()

            assertThat(oneDigitState.selectedFilter?.recommendedVitrocleanVfaLoad).isEqualTo(280)
            assertThat(oneDigitState.selectedFilter?.recommendedSandLoad).isEqualTo(500)
            assertThat(oneDigitState.selectedFilter?.recommendedPebble ).isEqualTo(120)
            assertThat(oneDigitState.selectedFilter?.fiftyBagPebble).isEqualTo(2.4)
            assertThat(oneDigitState.selectedFilter?.fiftyBagVitroclean).isEqualTo(5.6)
            assertThat(oneDigitState.input).isEqualTo("5")
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("50"))
            val twoDigitState = awaitItem()
            assertThat(twoDigitState.input).isEqualTo("50")

            assertThat(twoDigitState.selectedFilter?.recommendedVitrocleanVfaLoad).isEqualTo(2800)
            assertThat(twoDigitState.selectedFilter?.recommendedSandLoad).isEqualTo(5000)
            assertThat(twoDigitState.selectedFilter?.recommendedPebble ).isEqualTo(1200)
            assertThat(twoDigitState.selectedFilter?.fiftyBagPebble).isEqualTo(24.0)
            assertThat(twoDigitState.selectedFilter?.fiftyBagVitroclean).isEqualTo(56.0)

            viewModel.onEvent(CalculateUiEvent.OnNumberChange("5"))
            val returnOneDigitState = awaitItem()

            assertThat(returnOneDigitState.selectedFilter?.recommendedVitrocleanVfaLoad).isEqualTo(280)
            assertThat(returnOneDigitState.selectedFilter?.recommendedSandLoad).isEqualTo(500)
            assertThat(returnOneDigitState.selectedFilter?.recommendedPebble ).isEqualTo(120)
            assertThat(returnOneDigitState.selectedFilter?.fiftyBagPebble).isEqualTo(2.4)
            assertThat(returnOneDigitState.selectedFilter?.fiftyBagVitroclean).isEqualTo(5.6)
            assertThat(returnOneDigitState.input).isEqualTo("5")
        }
    }
    @Test
    fun `OnNumberChange - Mode BY_CUBIC_FEET - Enter 8 char - except taker 7`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(CalculatorState())
            viewModel.onEvent(CalculateUiEvent.AddList(filterExample))
            val listState = awaitItem()
            assertThat(listState.poolFilterList).isEqualTo(filterExample)
            viewModel.onEvent(CalculateUiEvent.ChangeMode("by_cubic_feet"))
            val modeState = awaitItem()
            assertThat(modeState.mode).isEqualTo(CalculatorMode.BY_CUBIC_FEET)
            viewModel.onEvent(CalculateUiEvent.OnNumberChange("10000000"))
            val oneDigitState = awaitItem()
            val oneExpectedResult = PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = 56000000,
                recommendedSandLoad = 100000000,
                recommendedPebble = 24000000,
                fiftyBagPebble = 480000.0,
                fiftyBagVitroclean = 1120000.0

            )
            assertThat(oneDigitState.selectedFilter).isEqualTo(oneExpectedResult)
            assertThat(oneDigitState.input).isEqualTo("1000000")
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
