package com.jaegerapps.vitroclean.core.domain.util

import com.jaegerapps.vitroclean.shared.domain.NetworkError

sealed class Resource<T>(val data: T?, val networkError: NetworkError? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(networkError: NetworkError) : Resource<T>(null, networkError)
}
