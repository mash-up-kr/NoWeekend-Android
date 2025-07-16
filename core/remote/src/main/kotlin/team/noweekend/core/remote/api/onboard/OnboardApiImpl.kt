package team.noweekend.core.remote.api.onboard

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.base.postApiCall
import team.noweekend.core.remote.model.onboard.OnboardProfileRequest
import team.noweekend.core.remote.model.onboard.OnboardTagsRequest
import team.noweekend.core.remote.model.onboard.OnboardTagsResponse
import team.noweekend.core.remote.model.onboard.OnboardVacationRequest
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class OnboardApiImpl @Inject constructor(
    @BasicClient private val client: HttpClient,
) : OnboardApi {
    override suspend fun getTagList(): OnboardTagsResponse {
        return client.getApiCall(
            path = "/api/v1/user/onboarding/tag",
        )
    }

    override suspend fun requestTags(tagsRequest: OnboardTagsRequest) {
        client.postApiCall<String>(
            path = "/api/v1/user/onboarding/tag",
            body = tagsRequest,
        )
    }

    override suspend fun requestProfile(profileRequest: OnboardProfileRequest) {
        client.postApiCall<String>(
            path = "/api/v1/user/onboarding/profile",
            body = profileRequest,
        )
    }

    override suspend fun requestVacation(vacationRequest: OnboardVacationRequest) {
        client.postApiCall<String>(
            path = "/api/v1/user/onboarding/leave",
            body = vacationRequest,
        )
    }
}
