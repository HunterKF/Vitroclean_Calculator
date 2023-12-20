package com.jaegerapps.trivitro_calculator.shared.data.mappers

import com.jaegerapps.trivitro_calculator.shared.data.remote.dtos.FaqDto
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq

fun FaqDto.toFaq(): Faq {
    return Faq(question, answer)
}