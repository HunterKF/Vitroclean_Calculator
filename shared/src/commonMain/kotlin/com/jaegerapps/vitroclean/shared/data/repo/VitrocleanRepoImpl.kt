package com.jaegerapps.vitroclean.shared.data.repo

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.data.local.LocalDataSource
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import com.jaegerapps.vitroclean.shared.data.mappers.toFaq
import com.jaegerapps.vitroclean.shared.data.mappers.toPoolFilter
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSource
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

class VitrocleanRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource

    ) : VitrocleanRepo {

    override suspend fun getFilters(): Resource<List<PoolFilter>> {
        val result = remoteDataSource.getFiltersFromDb()
        return if (result.data != null) {
            Resource.Success(result.data.map { it.toPoolFilter()})
        } else {
            Resource.Error(result.networkError ?: NetworkError.SERVICE_UNAVAILABLE)
        }
    }


    override suspend fun getFaqs(): Resource<List<Faq>> {

        val result = remoteDataSource.getFaqsFromDb()
        return if (result.data != null) {
            Resource.Success(result.data.map { it.toFaq() })
        } else {
            Resource.Error(result.networkError ?: NetworkError.SERVICE_UNAVAILABLE)
        }
    }

    override suspend fun getOnBoarding(): Resource<Boolean> {
        return localDataSource.getOnBoardingFromLocal()
    }

    override suspend fun toggleOnboarding() {
        localDataSource.turnOnboardingFalseLocal()
    }

}