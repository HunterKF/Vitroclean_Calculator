package com.jaegerapps.vitroclean.shared.domain

enum class NetworkError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class SupabaseException(val error: NetworkError): Exception()