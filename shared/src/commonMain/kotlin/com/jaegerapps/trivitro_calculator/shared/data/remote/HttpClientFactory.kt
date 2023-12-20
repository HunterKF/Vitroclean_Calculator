package com.jaegerapps.trivitro_calculator.shared.data.remote

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}