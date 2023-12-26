package com.jagerapps.trivitro_calculator.shared.domain

import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.TrivitroSupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import com.jagerapps.trivitro_calculator.shared.filterExample
import com.jagerapps.trivitro_calculator.shared.faqsExample

class FakeSupabaseRepo : TrivitroSupabaseRepo {


    override suspend fun getFilters(): Resource<List<PoolFilter>> {
        return Resource.Success(filterExample)

    }

    override suspend fun getFaqs(): Resource<List<Faq>> {
        return Resource.Success(faqsExample)

    }

}