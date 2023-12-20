package com.jaegerapps.trivitro_calculator.shared.data.remote.dtos

@kotlinx.serialization.Serializable
data class FaqDto(
    val id: Int,
    val question: String,
    val answer: String
)
