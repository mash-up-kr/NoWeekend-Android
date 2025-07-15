package team.noweekend.core.remote.model.schedule.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OnboardResponse(
    @SerialName("data")
    val data: String,
)
