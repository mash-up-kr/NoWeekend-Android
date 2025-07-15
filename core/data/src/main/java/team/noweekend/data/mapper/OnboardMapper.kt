package team.noweekend.data.mapper

import team.noweekend.core.model.onboard.Onboard
import team.noweekend.core.model.onboard.OnboardProfileParam
import team.noweekend.core.model.onboard.OnboardTags
import team.noweekend.core.model.onboard.OnboardTagsParam
import team.noweekend.core.model.onboard.OnboardVacationParam
import team.noweekend.core.remote.model.onboard.OnboardProfileRequest
import team.noweekend.core.remote.model.onboard.OnboardTagsRequest
import team.noweekend.core.remote.model.onboard.OnboardTagsResponse
import team.noweekend.core.remote.model.onboard.OnboardVacationRequest
import team.noweekend.core.remote.model.schedule.response.OnboardResponse

fun OnboardResponse.toDomainModel(): Onboard {
    return Onboard(
        data = data,
    )
}

fun OnboardTagsResponse.toDomainModel(): OnboardTags {
    return OnboardTags(tags = tags)
}

fun OnboardTagsParam.toRequestBody(): OnboardTagsRequest {
    return OnboardTagsRequest(
        scheduleTags = scheduleTags,
    )
}

fun OnboardProfileParam.toRequestBody(): OnboardProfileRequest {
    return OnboardProfileRequest(
        nickname = nickname,
        birthDate = birthDate,
    )
}

fun OnboardVacationParam.toRequestBody(): OnboardVacationRequest {
    return OnboardVacationRequest(
        days = days,
        hours = hours,
    )
}
