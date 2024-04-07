package com.jaegerapps.vitroclean.shared.data

import com.jaegerapps.vitroclean.BuildKonfig.API_KEY
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.vitroclean.shared.data.mappers.toFaq
import com.jaegerapps.vitroclean.shared.data.mappers.toPoolFilter
import com.jaegerapps.vitroclean.shared.data.remote.dtos.FaqDto
import com.jaegerapps.vitroclean.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.domain.SupabaseException
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter
import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.utils.io.errors.IOException

class SupabaseKtroRepoImpl(
    private val httpClient: HttpClient,

) : TrivitroSupabaseRepo {
    private val settings = Settings()

    override suspend fun getFilters(): Resource<List<PoolFilter>> {
        val result = try {
            httpClient.get() {
                url(HttpRoutes.VF_FILTER_CHART)
                header("apikey", API_KEY)
            }

        } catch (e: IOException) {
            return Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE))
        }
        println(result.status)
        println(result.call)
        println(result.request)
        val error = errorHandling(result)
        error?.let {
            return Resource.Error(error.throwable!!)
        }

        return try {
            val filters = result.body<List<PoolFilterDto>>().map { it.toPoolFilter() }
            Resource.Success(filters)
        } catch (e: Exception) {
            return Resource.Error(SupabaseException(NetworkError.SERVER_ERROR))
        }
    }

    private fun errorHandling(result: HttpResponse): Resource.Error<NetworkError>? {
        return when (result.status.value) {
            in 200..299 -> null
            500 -> Resource.Error(SupabaseException(NetworkError.SERVER_ERROR))

            in 400..499 -> {
                Resource.Error(SupabaseException(NetworkError.CLIENT_ERROR))
            }
            else -> Resource.Error(SupabaseException(NetworkError.UNKNOWN_ERROR))
        }
    }

    override suspend fun getFaqs(): Resource<List<Faq>> {

        val result = try {
            httpClient.get {
                url(HttpRoutes.FAQS)
                parameter(key = "apikey", value =  API_KEY )
            }
        } catch (e: IOException) {
            return Resource.Error(SupabaseException(NetworkError.SERVICE_UNAVAILABLE))
        }
        val error = errorHandling(result)
        error?.let {
            return Resource.Error(error.throwable!!)
        }
        return try {
            val faqs = result.body<List<FaqDto>>().map { it.toFaq() }
            Resource.Success(faqs)
        } catch (e: Exception) {
            return Resource.Error(SupabaseException(NetworkError.SERVER_ERROR))
        }
    }

    override suspend fun getOnBoarding(): Resource<Boolean> {
        val showOnboarding = settings.getBoolean("show_onboarding", true)
        return Resource.Success(showOnboarding)
    }

    override suspend fun toggleOnboarding() {
       settings.putBoolean("show_onboarding", false)
    }

}