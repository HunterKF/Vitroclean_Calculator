package com.jagerapps.trivitro_calculator.shared.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.NetworkError
import com.jaegerapps.trivitro_calculator.shared.domain.SupabaseException
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedUiState
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedViewModel
import com.jagerapps.trivitro_calculator.shared.faqsExample
import com.jagerapps.trivitro_calculator.shared.filterExample
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import io.mockative.once
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SharedViewModelTest {

    private lateinit var viewModel: SharedViewModel
    private lateinit var getFaqs: GetFaqs
    private lateinit var getFilters: GetFilters

    @Mock
    val repo = mock(classOf<TrivitroSupabaseRepo>())

    @BeforeTest
    fun setup() {
        getFilters = GetFilters(repo)
        getFaqs = GetFaqs(repo)
        viewModel = SharedViewModel(getFilters, getFaqs, CoroutineScope(Dispatchers.Default))
    }


    @Test
    fun `LoadData - State is updated with Filters and Faqs - Return success`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
            coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)

            assertThat(state.loaded).isTrue()
            assertThat(state.isLoading).isFalse()
            assertThat(state.faqsList).isEqualTo(faqsExample)
            assertThat(state.poolFilterList).isEqualTo(filterExample)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFilters fails - Return error UNKNOWN_ERROR`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Error(SupabaseException(NetworkError.UNKNOWN_ERROR)))
            coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEqualTo(faqsExample)
            assertThat(nextState.poolFilterList).isEmpty()
            assertThat(nextState.error).isEqualTo(NetworkError.UNKNOWN_ERROR)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFilters fails - Return error CLIENT_ERROR`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Error(SupabaseException(NetworkError.CLIENT_ERROR)))
            coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEqualTo(faqsExample)
            assertThat(nextState.poolFilterList).isEmpty()
            assertThat(nextState.error).isEqualTo(NetworkError.CLIENT_ERROR)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFilters fails - Return error SERVER_ERROR`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Error(SupabaseException(NetworkError.SERVER_ERROR)))
            coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEqualTo(faqsExample)
            assertThat(nextState.poolFilterList).isEmpty()
            assertThat(nextState.error).isEqualTo(NetworkError.SERVER_ERROR)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFilters fails - Return error SERVICE_UNAVAILABLE`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE)))
            coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)

            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEqualTo(faqsExample)
            assertThat(nextState.poolFilterList).isEmpty()
            assertThat(nextState.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFaqs fails - Return error UNKNOWN_ERROR`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
            coEvery { repo.getFaqs() }.returns(Resource.Error(SupabaseException(NetworkError.UNKNOWN_ERROR)))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEmpty()
            assertThat(nextState.poolFilterList).isEqualTo(filterExample)
            assertThat(nextState.error).isEqualTo(NetworkError.UNKNOWN_ERROR)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFaqs fails - Return error CLIENT_ERROR`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
            coEvery { repo.getFaqs() }.returns(Resource.Error(SupabaseException(NetworkError.CLIENT_ERROR)))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEmpty()
            assertThat(nextState.poolFilterList).isEqualTo(filterExample)
            assertThat(nextState.error).isEqualTo(NetworkError.CLIENT_ERROR)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFaqs fails - Return error SERVER_ERROR`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
            coEvery { repo.getFaqs() }.returns(Resource.Error(SupabaseException(NetworkError.SERVER_ERROR)))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEmpty()
            assertThat(nextState.poolFilterList).isEqualTo(filterExample)
            assertThat(nextState.error).isEqualTo(NetworkError.SERVER_ERROR)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - getFaqs fails - Return error SERVICE_UNAVAILABLE`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
            coEvery { repo.getFaqs() }.returns(Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE)))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEmpty()
            assertThat(nextState.poolFilterList).isEqualTo(filterExample)
            assertThat(nextState.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
        }
    }
    @Test
    fun `LoadData - State is updated with Filters and Faqs - both fail - Return error SERVICE_UNAVAILABLE`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE)))
            coEvery { repo.getFaqs() }.returns(Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE)))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            assertThat(state.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val nextState = awaitItem()
            assertThat(nextState.loaded).isFalse()
            assertThat(nextState.isLoading).isFalse()
            assertThat(nextState.faqsList).isEmpty()
            assertThat(nextState.poolFilterList).isEmpty()
            assertThat(nextState.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
        }
    }
    @Test
    fun `Retry - Network fail - retry - Return loaded state`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())
            coEvery { repo.getFilters() }.returns(Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE)))
            coEvery { repo.getFaqs() }.returns(Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE)))
            viewModel.onEvent(SharedUiEvent.LoadData)

            val loadingState = awaitItem()
            assertThat(loadingState.isLoading).isTrue()

            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val afterLoadState = awaitItem()
            assertThat(afterLoadState.loaded).isFalse()
            assertThat(afterLoadState.isLoading).isFalse()
            assertThat(afterLoadState.faqsList).isEmpty()
            assertThat(afterLoadState.poolFilterList).isEmpty()
            assertThat(afterLoadState.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)

            coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
            coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
            viewModel.onEvent(SharedUiEvent.OnRetry)

            val retryState = awaitItem()
            assertThat(retryState.error).isNull()
            assertThat(retryState.isLoading).isTrue()


            coVerify { repo.getFilters() }.wasInvoked(once)
            coVerify { repo.getFaqs() }.wasInvoked(once)
            val afterRetryState = awaitItem()
            assertThat(afterRetryState.loaded).isTrue()
            assertThat(afterRetryState.isLoading).isFalse()
            assertThat(afterRetryState.faqsList).isEqualTo(faqsExample)
            assertThat(afterRetryState.poolFilterList).isEqualTo(filterExample)
            assertThat(afterRetryState.error).isNull()
        }
    }
}