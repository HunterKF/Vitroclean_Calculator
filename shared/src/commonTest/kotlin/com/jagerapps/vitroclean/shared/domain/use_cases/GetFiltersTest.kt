package com.jagerapps.vitroclean.shared.domain.use_cases

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.domain.SupabaseException
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFilters
import com.jagerapps.vitroclean.shared.filterExample
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetFiltersTest {

    @Mock
    val repo = mock(classOf<VitrocleanRepo>())

    private lateinit var getFilters: GetFilters

    @BeforeTest
    fun setup() {
        getFilters = GetFilters(repo)
    }

    @Test
    fun `GetFilters - Return all Filters`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
        val result = getFilters.invoke()
        assertThat(result.data).isEqualTo(filterExample)
    }
    @Test
    fun `GetFilters - Return error SERVER_ERROR`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Error(NetworkError.SERVER_ERROR))
        val result = getFilters.invoke()

        assertThat(result.networkError).isEqualTo(NetworkError.SERVER_ERROR)
    }
    @Test
    fun `GetFilters - Return error CLIENT_ERROR`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Error(NetworkError.CLIENT_ERROR))
        val result = getFilters.invoke()

        assertThat(result.networkError).isEqualTo(NetworkError.CLIENT_ERROR)
    }
    @Test
    fun `GetFilters - Return error SERVICE_UNAVAILABLE`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Error(NetworkError.SERVICE_UNAVAILABLE))
        val result = getFilters.invoke()

        assertThat(result.networkError).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
    }
    @Test
    fun `GetFilters - Return error UNKNOWN_ERROR`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Error(NetworkError.UNKNOWN_ERROR))
        val result = getFilters.invoke()

        assertThat(result.networkError).isEqualTo(NetworkError.UNKNOWN_ERROR)
    }
}