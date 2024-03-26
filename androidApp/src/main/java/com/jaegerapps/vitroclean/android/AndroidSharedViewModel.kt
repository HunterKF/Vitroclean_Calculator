package com.jaegerapps.vitroclean.android

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jaegerapps.vitroclean.shared.presentation.SharedUiEvent
import com.jaegerapps.vitroclean.shared.presentation.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AndroidSharedViewModel @Inject constructor(
    private val getFilters: GetFilters,
    private val getFaqs: GetFaqs,
) : ViewModel(), LifecycleObserver {
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