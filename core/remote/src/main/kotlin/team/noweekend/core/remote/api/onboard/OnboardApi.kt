package team.noweekend.core.remote.api.onboard

import team.noweekend.core.remote.model.onboard.OnboardProfileRequest
import team.noweekend.core.remote.model.onboard.OnboardTagsRequest
import team.noweekend.core.remote.model.onboard.OnboardTagsResponse
import team.noweekend.core.remote.model.onboard.OnboardVacationRequest
import team.noweekend.core.remote.model.schedule.response.OnboardResponse

interface OnboardApi {
    suspend fun getTagList(): OnboardTagsResponse
    suspend fun requestTags(tagsRequest: OnboardTagsRequest)
    suspend fun requestProfile(profileRequest: OnboardProfileRequest)
    suspend fun requestVacation(vacationRequest: OnboardVacationRequest)
}
