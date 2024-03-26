package com.jaegerapps.vitroclean.android.presentation.home_screen

import androidx.lifecycle.ViewModel
import com.jaegerapps.vitroclean.shared.presentation.home.HomeScreenViewModel
import com.jaegerapps.vitroclean.shared.presentation.home.HomeUiEvent

class AndroidHomeScreenViewModel(): ViewModel() {
    private val viewModel by lazy {
        HomeScreenViewModel()
    }
    val state = viewModel.state

    fun onEvent(event: HomeUiEvent) {
        viewModel.onEvent(event)
    }
}