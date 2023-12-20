package com.jaegerapps.trivitro_calculator.shared.data

import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.data.mappers.toFaq
import com.jaegerapps.trivitro_calculator.shared.data.mappers.toPoolFilter
import com.jaegerapps.trivitro_calculator.shared.data.remote.dtos.FaqDto
import com.jaegerapps.trivitro_calculator.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.trivitro_calculator.shared.domain.NetworkError
import com.jaegerapps.trivitro_calculator.shared.domain.SupabaseException
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import io.github.jan.supabase.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class SupabaseKtroRepoImpl(
    private val httpClient: HttpClient
): TrivitroSupabaseRepo {
    override suspend fun getFilters(): Resource<List<PoolFilter>> {
        val result = try {
            httpClient.get() {
                url(HttpRoutes.VF_FILTER_CHART)
                header("apikey", HttpRoutes.API_KEY)
            }


        } catch (e: IOException) {
            throw SupabaseException(NetworkError.SERVICE_UNAVAILABLE)
        }
        when(result.status.value) {
            in 200..299 -> Unit
            500 -> throw SupabaseException(NetworkError.SERVER_ERROR)
            in 400..499 -> {
                throw SupabaseException(NetworkError.CLIENT_ERROR)
            }
            else -> throw SupabaseException(NetworkError.UNKNOWN_ERROR)
        }

        return try {
            val filters = result.body<List<PoolFilterDto>>().map { it.toPoolFilter()}
            Resource.Success(filters)
        } catch (e: Exception) {
            throw SupabaseException(NetworkError.SERVER_ERROR)
        }
    }

    override suspend fun getFaqs(): Resource<List<Faq>> {
        val result = try {
            httpClient.get {
                url(HttpRoutes.FAQS)
                parameter(key = "apikey", value = HttpRoutes.API_KEY)
            }
        } catch (e: IOException) {
            throw SupabaseException(NetworkError.SERVICE_UNAVAILABLE)
        }
        when(result.status.value) {
            in 200..299 -> Unit
            500 -> throw SupabaseException(NetworkError.SERVER_ERROR)
            in 400..499 -> throw SupabaseException(NetworkError.CLIENT_ERROR)
            else -> throw SupabaseException(NetworkError.UNKNOWN_ERROR)
        }
        return try {
            val faqs = result.body<List<FaqDto>>().map { it.toFaq()}
            println("here are he faqs $faqs")
            Resource.Success(faqs)
        } catch (e: Exception) {
            throw SupabaseException(NetworkError.SERVER_ERROR)
        }
    }

}