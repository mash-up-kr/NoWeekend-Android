package team.noweekend.data.repository

import team.noweekend.core.domain.repository.OnboardRepository
import team.noweekend.core.local.datastore.LocalDataStore
import team.noweekend.core.model.onboard.OnboardProfileParam
import team.noweekend.core.model.onboard.OnboardTags
import team.noweekend.core.model.onboard.OnboardTagsParam
import team.noweekend.core.model.onboard.OnboardVacationParam
import team.noweekend.core.remote.api.onboard.OnboardApi
import team.noweekend.data.mapper.toDomainModel
import team.noweekend.data.mapper.toRequestBody
import javax.inject.Inject

class OnboardRepositoryImpl @Inject constructor(
    private val onboardApi: OnboardApi,
    private val dataStore: LocalDataStore,
) : OnboardRepository {
    override suspend fun getTagList(): OnboardTags {
        return onboardApi.getTagList().toDomainModel()
    }

    override suspend fun requestTags(param: OnboardTagsParam) {
        return onboardApi.requestTags(param.toRequestBody())
    }

    override suspend fun requestProfile(param: OnboardProfileParam) {
        return onboardApi.requestProfile(param.toRequestBody())
    }

    override suspend fun requestVacation(param: OnboardVacationParam) {
        return onboardApi.requestVacation(param.toRequestBody())
    }

    override suspend fun getIsOnboardFinished(): Boolean {
        return dataStore.getOnboardIsFinished()
    }

    override suspend fun setOnboardFinished(isFinished: Boolean) {
        dataStore.setOnboardIsFinished(isFinished = isFinished)
    }
}
