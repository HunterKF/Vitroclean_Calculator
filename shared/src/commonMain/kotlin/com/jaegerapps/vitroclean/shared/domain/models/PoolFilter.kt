package com.jaegerapps.vitroclean.shared.domain.models

/*A pool filter*/
data class PoolFilter(
    val manufacturer: String,
    val model: String,
    val recommendedSandLoad: Int,
    val recommendedVitrocleanVfaLoad: Int,
    val recommendedPebble: Int,
    val fiftyBagVitroclean: Double,
    val fiftyBagPebble: Double,
)
