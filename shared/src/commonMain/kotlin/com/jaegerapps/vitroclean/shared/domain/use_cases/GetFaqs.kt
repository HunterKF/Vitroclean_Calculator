package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo

class GetFaqs(
    private val repo: VitrocleanRepo
) {
    suspend operator fun invoke(): Resource<List<Faq>> {
        val faqs = repo.getFaqs()
        if (!faqs.data.isNullOrEmpty()) {
            Resource.Success(faqs.data)
        } else {
            Resource.Error(faqs.networkError!!)
        }
        return faqs
    }
}