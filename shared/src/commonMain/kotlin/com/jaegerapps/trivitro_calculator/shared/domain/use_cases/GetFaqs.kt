package com.jaegerapps.trivitro_calculator.shared.domain.use_cases

import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo

class GetFaqs(
    private val repo: TrivitroSupabaseRepo
) {
    suspend operator fun invoke(): Resource<List<Faq>> {
        return try {
           val faqs = repo.getFaqs().data
           if (faqs != null && faqs.isNotEmpty()) {
               Resource.Success(faqs)
           } else {
               Resource.Error(throwable = Throwable(message = "Faq list was empty"))
           }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}