package team.noweekend.core.domain.repository

import team.noweekend.core.model.onboard.OnboardProfileParam
import team.noweekend.core.model.onboard.OnboardTags
import team.noweekend.core.model.onboard.OnboardTagsParam
import team.noweekend.core.model.onboard.OnboardVacationParam

interface OnboardRepository {
    suspend fun getTagList(): OnboardTags
    suspend fun requestTags(param: OnboardTagsParam)
    suspend fun requestProfile(param: OnboardProfileParam)
    suspend fun requestVacation(param: OnboardVacationParam)
    suspend fun getIsOnboardFinished(): Boolean
    suspend fun setOnboardFinished(isFinished: Boolean)
}
