package com.jaegerapps.vitroclean.android.presentation.contact_us_screen

import androidx.lifecycle.ViewModel
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactUiEvent
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactViewModel


class AndroidContactViewModel: ViewModel() {
    private val viewModel by lazy {
        ContactViewModel()
    }
    val state = viewModel.state

    fun onEvent(event: ContactUiEvent) {
        viewModel.onEvent(event)
    }
}