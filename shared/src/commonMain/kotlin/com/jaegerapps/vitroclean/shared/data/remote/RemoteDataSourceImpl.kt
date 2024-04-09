package com.jaegerapps.vitroclean.shared.data.remote

import com.jaegerapps.vitroclean.BuildKonfig
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.data.HttpRoutes
import com.jaegerapps.vitroclean.shared.data.remote.dtos.FaqDto
import com.jaegerapps.vitroclean.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import io.github.jan.supabase.exceptions.HttpRequestException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.utils.io.errors.IOException

class RemoteDataSourceImpl(
    private val httpClient: HttpClient,
    ): RemoteDataSource {
    override suspend fun getFiltersFromDb(): Resource<List<PoolFilterDto>> {
        val result = try {
            httpClient.get() {
                url(HttpRoutes.VF_FILTER_CHART)
                header("apikey", BuildKonfig.API_KEY)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error(NetworkError.SERVICE_UNAVAILABLE)
        }
        println(result.status)
        println(result.call)
        println(result.request)
        val error = errorHandling(result)
        error?.let {
            return Resource.Error(error.networkError!!)
        }

        val filterDtos = result.body<List<PoolFilterDto>>()

        return  Resource.Success(filterDtos)
    }

    override suspend fun getFaqsFromDb(): Resource<List<FaqDto>> {
        val result = try {
            httpClient.get {
                url(HttpRoutes.FAQS)
                parameter(key = "apikey", value = BuildKonfig.API_KEY)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error(NetworkError.SERVICE_UNAVAILABLE)
        }
        val error = errorHandling(result)
        error?.let {
            return Resource.Error(error.networkError ?: NetworkError.SERVICE_UNAVAILABLE)
        }
        return try {
            val faqDtos = result.body<List<FaqDto>>()
            Resource.Success(faqDtos)
        } catch (e: Exception) {
            return Resource.Error(NetworkError.SERVICE_UNAVAILABLE)
        }
    }
    private fun errorHandling(result: HttpResponse): Resource.Error<NetworkError>? {
        return when (result.status.value) {
            in 200..299 -> null
            500 -> Resource.Error(NetworkError.SERVER_ERROR)

            in 400..499 -> {
                Resource.Error(NetworkError.CLIENT_ERROR)
            }

            else -> Resource.Error(NetworkError.UNKNOWN_ERROR)
        }
    }
}