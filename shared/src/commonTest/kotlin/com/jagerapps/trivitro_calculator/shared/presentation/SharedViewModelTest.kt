package com.jagerapps.trivitro_calculator.shared.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFaqs
import com.jaegerapps.trivitro_calculator.shared.domain.use_cases.GetFilters
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedUiState
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedViewModel
import com.jagerapps.trivitro_calculator.shared.domain.FakeSupabaseRepo
import io.github.jan.supabase.network.SupabaseApi
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.mock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SharedViewModelTest {

    private lateinit var viewModel: SharedViewModel
    private lateinit var getFaqs: GetFaqs
    private lateinit var getFilters: GetFilters
    private lateinit var repo: SupabaseApi

   /* @Mock
    val api = mock(classOf<SupabaseApi>())
    @BeforeTest
    fun setup() {
        repo = FakeSupabaseRepo()
        getFilters = GetFilters(repo)
        getFaqs = GetFaqs(repo)
        viewModel = SharedViewModel(getFilters, getFaqs, CoroutineScope(Dispatchers.Default))
    }

    @Test
    fun `State is updated with Filters and Faqs - Return success`() = runBlocking {

        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())

            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()

            val expectedFilterList = listOf(
                PoolFilter("Pentair", "TR40", 175, 140, 0, 0, 3),
                PoolFilter("Pentair", "TR50", 225, 180, 0, 0, 4),
                PoolFilter("Pentair", "TR60", 325, 260, 182, 78, 4),
            )
            val expectedFaqsList = listOf(
                Faq(
                    "How do I install this?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
                ),
                Faq(
                    "Why is this good",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
                ),
                Faq(
                    "Where can i purchase this?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
                ),
                Faq(
                    "Product difference?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
                ),
                Faq(
                    "Where can I find the calculatios?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
                ),
                Faq(
                    "How are the calculations done?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
                )
            )
            assertThat(state.loaded).isTrue()
            assertThat(state.isLoading).isFalse()
            assertThat(state.faqsList).isEqualTo(expectedFaqsList)
            assertThat(state.poolFilterList).isEqualTo(expectedFilterList)
        }

    }
    @Test
    fun `State is updated with Filters and Faqs - Return error`() = runBlocking{
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(SharedUiState())

            viewModel.onEvent(SharedUiEvent.LoadData)

            val state = awaitItem()
            repo
        }
    }*/
}