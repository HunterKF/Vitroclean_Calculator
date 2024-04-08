package com.jaegerapps.vitroclean.shared.domain

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

//Repo for the base app, used in SharedViewModel
interface VitrocleanRepo {
    suspend fun getFilters(): Resource<List<PoolFilter>>
    suspend fun getFaqs(): Resource<List<Faq>>
    //Checks if user went through the onboarding
    suspend fun getOnBoarding(): Resource<Boolean>
    //changes showonboarding to false
    suspend fun toggleOnboarding()
}