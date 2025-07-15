package team.noweekend.core.remote.model.onboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardTagsResponse(
    @SerialName("tags")
    val tags: List<String>,
)
