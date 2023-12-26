package com.jagerapps.trivitro_calculator.shared.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.NetworkError
import com.jaegerapps.trivitro_calculator.shared.domain.SupabaseException
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jagerapps.trivitro_calculator.shared.faqsExample
import com.jagerapps.trivitro_calculator.shared.filterExample
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class TrivitroSupabaseRepoTest {

    @Mock
    val repo = mock(classOf<TrivitroSupabaseRepo>())

    @Test
    fun `getFilters - Return Success`() = runBlocking {
        coEvery { repo.getFilters() }.returns(Resource.Success(data = filterExample))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat(result.data).isEqualTo(filterExample)
    }
    @Test
    fun `getFilters - Return Error SERVER_ERROR`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.SERVER_ERROR)
        coEvery { repo.getFilters() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.SERVER_ERROR)
    }
    @Test
    fun `getFilters - Return Error CLIENT_ERROR`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.CLIENT_ERROR)
        coEvery { repo.getFilters() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.CLIENT_ERROR)
    }
    @Test
    fun `getFilters - Return Error UNKNOWN_ERROR`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.UNKNOWN_ERROR)
        coEvery { repo.getFilters() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.UNKNOWN_ERROR)
    }
    @Test
    fun `getFilters - Return Error SERVICE_UNAVAILABLE`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.SERVICE_UNAVAILABLE)
        coEvery { repo.getFilters() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFilters()
        coVerify { repo.getFilters() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
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
        val throwable = SupabaseException(error = NetworkError.SERVER_ERROR)
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.SERVER_ERROR)
    }
    @Test
    fun `getFaqs - Return Error UNKNOWN_ERROR`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.UNKNOWN_ERROR)
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.UNKNOWN_ERROR)
    }
    @Test
    fun `getFaqs - Return Error CLIENT_ERROR`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.CLIENT_ERROR)
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.CLIENT_ERROR)
    }
    @Test
    fun `getFaqs - Return Error SERVICE_UNAVAILABLE`() = runBlocking {
        val throwable = SupabaseException(error = NetworkError.SERVICE_UNAVAILABLE)
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable =throwable ))
        val result = repo.getFaqs()
        coVerify { repo.getFaqs() }.wasInvoked()
        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
    }
}