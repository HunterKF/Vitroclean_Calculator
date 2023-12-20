package com.jaegerapps.trivitro_calculator.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

actual class HttpClientFactory {
    /*ANDROID*/
    actual fun create(): HttpClient {
        return HttpClient(
            Android
        ) {
            install(ContentNegotiation) {
                json()
            }
        }

    }
}