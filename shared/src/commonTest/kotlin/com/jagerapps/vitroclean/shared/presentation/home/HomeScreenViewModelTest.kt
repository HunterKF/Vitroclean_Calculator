package com.jagerapps.vitroclean.shared.presentation.home

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.jaegerapps.vitroclean.shared.presentation.home.HomeScreenViewModel
import com.jaegerapps.vitroclean.shared.presentation.home.HomeUiEvent
import com.jaegerapps.vitroclean.shared.presentation.home.HomeUiState
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class HomeScreenViewModelTest {
    private lateinit var viewModel: HomeScreenViewModel

    @BeforeTest
    fun setup() {
        viewModel = HomeScreenViewModel()
    }

    @Test
    fun `OnClick - Expect showPopUp true`() = runBlocking{
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(HomeUiState())
            viewModel.onEvent(HomeUiEvent.OnClick)
            val state = awaitItem()
            assertThat(state.showPopUp).isTrue()
        }
    }
    @Test
    fun `OnAccept - Expect showPopUp false`() = runBlocking{
        viewModel.state.test {
            var toggled = false
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(HomeUiState())
            viewModel.onEvent(HomeUiEvent.OnClick)
            val state = awaitItem()
            assertThat(state.showPopUp).isTrue()
            viewModel.onEvent(HomeUiEvent.OnAccept(navigateToLink = { toggled = true}))
            val nextState = awaitItem()
            assertThat(nextState.showPopUp).isFalse()
            assertThat(toggled).isTrue()
        }
    }
    @Test
    fun `OnDismiss - Expect showPopUp false`() = runBlocking{
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(HomeUiState())
            viewModel.onEvent(HomeUiEvent.OnClick)
            val state = awaitItem()
            assertThat(state.showPopUp).isTrue()
            viewModel.onEvent(HomeUiEvent.OnDismiss)
            val nextState = awaitItem()
            assertThat(nextState.showPopUp).isFalse()
        }
    }
}