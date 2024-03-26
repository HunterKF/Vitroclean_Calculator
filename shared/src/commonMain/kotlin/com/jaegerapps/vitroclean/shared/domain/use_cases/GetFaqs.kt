package com.jaegerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.TrivitroSupabaseRepo

class GetFaqs(
    private val repo: TrivitroSupabaseRepo
) {
    suspend operator fun invoke(): Resource<List<Faq>> {
        return try {
           val faqs = repo.getFaqs()
           if (!faqs.data.isNullOrEmpty()) {
               Resource.Success(faqs.data)
           } else {
               Resource.Error(faqs.throwable!!)
           }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}