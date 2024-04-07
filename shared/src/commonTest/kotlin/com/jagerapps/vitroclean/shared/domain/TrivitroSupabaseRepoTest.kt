package com.jagerapps.vitroclean.shared.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.domain.SupabaseException
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import com.jagerapps.vitroclean.shared.faqsExample
import com.jagerapps.vitroclean.shared.filterExample
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class TrivitroSupabaseRepoTest {

    @Mock
    val repo = mock(classOf<VitrocleanRepo>())

    @Test
    fun `getFilters - Return Success`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat(result.data).isEqualTo(filterExample)
    }
    @Test
    fun `getFilters - Return Error SERVER_ERROR`() = runBlocking {
        val networkError =  NetworkError.SERVER_ERROR
        coEvery { repo.getFilters() }.returns(Resource.Error(networkError = networkError))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.SERVER_ERROR)
    }
    @Test
    fun `getFilters - Return Error CLIENT_ERROR`() = runBlocking {
        val networkError = NetworkError.CLIENT_ERROR
        coEvery { repo.getFilters() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.CLIENT_ERROR)
    }
    @Test
    fun `getFilters - Return Error UNKNOWN_ERROR`() = runBlocking {
        val networkError = NetworkError.UNKNOWN_ERROR
        coEvery { repo.getFilters() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.UNKNOWN_ERROR)
    }
    @Test
    fun `getFilters - Return Error SERVICE_UNAVAILABLE`() = runBlocking {
        val networkError = NetworkError.SERVICE_UNAVAILABLE
        coEvery { repo.getFilters() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
    }
    @Test
    fun `getFaqs - Return Success`() = runBlocking {
        coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat(result.data).isEqualTo(faqsExample)
    }
    @Test
    fun `getFaqs - Return Error SERVER_ERROR`() = runBlocking {
        val networkError = NetworkError.SERVER_ERROR
        coEvery { repo.getFaqs() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.SERVER_ERROR)
    }
    @Test
    fun `getFaqs - Return Error UNKNOWN_ERROR`() = runBlocking {
        val networkError = NetworkError.UNKNOWN_ERROR
        coEvery { repo.getFaqs() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.UNKNOWN_ERROR)
    }
    @Test
    fun `getFaqs - Return Error CLIENT_ERROR`() = runBlocking {
        val networkError = NetworkError.CLIENT_ERROR
        coEvery { repo.getFaqs() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.CLIENT_ERROR)
    }
    @Test
    fun `getFaqs - Return Error SERVICE_UNAVAILABLE`() = runBlocking {
        val networkError = NetworkError.SERVICE_UNAVAILABLE
        coEvery { repo.getFaqs() }.returns(Resource.Error(networkError =networkError ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat(result.networkError).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
    }
}