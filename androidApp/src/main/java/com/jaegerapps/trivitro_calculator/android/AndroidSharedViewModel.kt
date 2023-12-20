package com.jaegerapps.trivitro_calculator.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AndroidSharedViewModel @Inject constructor(
    private val getFilters: GetFilters,
    private val getFaqs: GetFaqs,
) : ViewModel() {
    private val viewModel by lazy {
        SharedViewModel(
            getFilters = getFilters,
            getFaqs = getFaqs,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: SharedUiEvent) {
        viewModel.onEvent(event)
    }
}