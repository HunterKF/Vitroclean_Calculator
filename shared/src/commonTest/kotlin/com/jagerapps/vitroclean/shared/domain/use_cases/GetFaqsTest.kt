package com.jagerapps.vitroclean.shared.domain.use_cases

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.domain.SupabaseException
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.domain.use_cases.GetFaqs
import com.jagerapps.vitroclean.shared.faqsExample
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetFaqsTest {

    @Mock
    val repo = mock(classOf<TrivitroSupabaseRepo>())

    private lateinit var getFaqs: GetFaqs

    @BeforeTest
    fun setup() {
        getFaqs = GetFaqs(repo)
    }

    @Test
    fun `GetFaqs - Return all Faqs`() = runBlocking {
        coEvery { repo.getFaqs() }.returns(Resource.Success(data = faqsExample))
        val result = getFaqs.invoke()
        assertThat(result.data).isEqualTo(faqsExample)
    }
    @Test
    fun `GetFaqs - Return error SERVER_ERROR`() = runBlocking {
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable = SupabaseException(error = NetworkError.SERVER_ERROR)))
        val result = getFaqs.invoke()

        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.SERVER_ERROR)
    }
    @Test
    fun `GetFaqs - Return error CLIENT_ERROR`() = runBlocking {
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable = SupabaseException(error = NetworkError.CLIENT_ERROR)))
        val result = getFaqs.invoke()

        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.CLIENT_ERROR)
    }
    @Test
    fun `GetFaqs - Return error SERVICE_UNAVAILABLE`() = runBlocking {
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable = SupabaseException(error = NetworkError.SERVICE_UNAVAILABLE)))
        val result = getFaqs.invoke()

        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.SERVICE_UNAVAILABLE)
    }
    @Test
    fun `GetFaqs - Return error UNKNOWN_ERROR`() = runBlocking {
        coEvery { repo.getFaqs() }.returns(Resource.Error(throwable = SupabaseException(error = NetworkError.UNKNOWN_ERROR)))
        val result = getFaqs.invoke()

        assertThat((result.throwable as? SupabaseException)?.error).isEqualTo(NetworkError.UNKNOWN_ERROR)
    }
}