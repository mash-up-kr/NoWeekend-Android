package team.noweekend.core.remote.model.onboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardTagsRequest(
    @SerialName("scheduleTags")
    val scheduleTags: List<String>,
)
