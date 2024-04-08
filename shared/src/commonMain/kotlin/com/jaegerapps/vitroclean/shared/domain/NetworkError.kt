package com.jaegerapps.vitroclean.shared.domain

//When an error happens in remote, we return Resource.Error(NetworkError...)
//This is used to get a string to display on the UI
enum class NetworkError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

