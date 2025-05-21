package team.noweekend.data.repository

import team.noweekend.core.domain.repository.SampleRepository
import team.noweekend.core.remote.api.SampleApi
import javax.inject.Inject

internal class SampleRepositoryImpl @Inject constructor(
    private val sampleApi: SampleApi
) : SampleRepository {
    override suspend fun getSample() {
        return sampleApi.getApiCall()
    }
}
