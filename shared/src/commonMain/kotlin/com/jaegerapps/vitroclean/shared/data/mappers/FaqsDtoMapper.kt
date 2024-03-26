package com.jaegerapps.vitroclean.shared.data.mappers

import com.jaegerapps.vitroclean.shared.data.remote.dtos.FaqDto
import com.jaegerapps.vitroclean.shared.domain.models.Faq

fun FaqDto.toFaq(): Faq {
    return Faq(question, answer)
}