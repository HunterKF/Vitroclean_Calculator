package com.jaegerapps.trivitro_calculator.shared.domain.models

data class PoolFilter(
    val manufacturer: String,
    val model: String,
    val recommendedSandLoad: Int,
    val recommendedVitrocleanVfaLoad: Int,
    val recommendedPebble: Int,
    val fiftyBagVitroclean: Int,
    val fiftyBagPebble: Int,
)
