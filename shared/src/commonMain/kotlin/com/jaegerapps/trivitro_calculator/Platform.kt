package com.jaegerapps.trivitro_calculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform