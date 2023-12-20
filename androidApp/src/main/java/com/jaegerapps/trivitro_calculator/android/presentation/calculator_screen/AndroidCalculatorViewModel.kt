package com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen

import androidx.lifecycle.ViewModel
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculateUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculatorViewModel


class AndroidCalculatorViewModel: ViewModel() {
        private val viewModel by lazy {
            CalculatorViewModel()
        }
        val state = viewModel.state

        fun onEvent(event: CalculateUiEvent) {
            viewModel.onEvent(event)
        }
}