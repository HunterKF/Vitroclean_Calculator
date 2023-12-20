package com.jaegerapps.trivitro_calculator.shared.presentation

import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.core.domain.util.toCommonStateFlow
import com.jaegerapps.trivitro_calculator.shared.domain.NetworkError
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import com.jaegerapps.trivitro_calculator.shared.domain.SupabaseException
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel(
    private val getFilters: GetFilters,
    private val getFaqs: GetFaqs,
    private val coroutineScope: CoroutineScope?,
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(SharedUiState())

    val state = _state.toCommonStateFlow()


    fun onEvent(event: SharedUiEvent) {
        when (event) {
            SharedUiEvent.LoadData -> {
                if (state.value.poolFilterList.isNotEmpty() && state.value.faqsList.isNotEmpty() && state.value.error == null) {
                    _state.update {
                        it.copy(
                            error = null,
                            isLoading = true
                        )
                    }
                } else {
                    loadData()
                }
            }
            is SharedUiEvent.OnRetry -> {
                _state.update {
                    it.copy(
                        error = null,
                        isLoading = true
                    )
                }
            }
        }
    }

    private fun loadData() {
        println("Load data is being called")

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val filters = async { getFilters() }.await()
            val faqs = async { getFaqs() }.await()
            // Update the state after successful loading
            _state.update { it.copy(isLoading = false, faqsList = faqs, poolFilterList = filters, loaded = filters.isNotEmpty() && faqs.isNotEmpty() && state.value.error == null) }
            println("loadData is complete")
            println("final state: ${state.value}")
        }
    }

    private suspend fun getFaqs(): List<Faq> {
        when (val result = getFaqs.invoke()) {
            is Resource.Success -> {
                result.data?.let { faqs ->
                    return faqs
                }
            }
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        loaded = false,
                        error = (result.throwable as? SupabaseException)?.error
                    )
                }
                return emptyList()

            }
        }
        return emptyList()
    }

    private suspend fun getFilters(): List<PoolFilter> {

        when (val result = getFilters.invoke()) {
            is Resource.Success -> {
                result.data?.let { filterList ->
                    return filterList
                }
            }
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        loaded = false,
                        error = (result.throwable as? SupabaseException)?.error
                    )
                }
                return emptyList()
            }
        }
        return emptyList()
    }
}